package com.viettel.gsct.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.gsct.fragment.BtsNhatkyFragment;
import com.viettel.gsct.fragment.TruyenDanNgamTiendoFragment;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin2 on 04/04/2017.
 */

public class LineHangActivity extends LineBaseActivity {
    private static final String TAG = "BtsActivity";
    public static final String ARG_INFO = "LineBackgroundActivity.INFO";

    @BindView(R.id.tv_tram)
    TextView tvTram;
    @BindView(R.id.tv_ma_cong_trinh)
    TextView tvMaCongTrinh;
    @BindView(R.id.sp_thong_tin)
    AppCompatSpinner spThongTin;
    @BindView(R.id.btnNhatKyTab)
    Button mBtnCapNhatNhatKy;
    @BindView(R.id.btnTienDoTab)
    Button mBtnCapNhatTienDo;
    @BindView(R.id.fr_content)
    FrameLayout mFrameLayoutNhatKy;
    @BindView(R.id.fr_content_tiendo)
    FrameLayout mFrameLayoutTienDo;

    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Supervision_BtsEntity btsEntity;
    private boolean flagFirstTime = true;
    private int infoId = 0;
    private BtsNhatkyFragment fragmentCapNhatNhatKy;
    private BaseFragment fragmentCapNhatTienDo;
    private boolean mIsCoThiCong = true;
    private boolean mIsClickCapNhatTienDo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bts);
        addView(R.layout.activity_bts_thietket, R.id.root_view);
        initVariables();
        initViews();
        initHeader();
    }

    public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
        return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret = super.onCreateOptionsMenu(menu);
        mSave.setVisible(true);
        return ret;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoHomeActivity(new Bundle());
        finish();
    }

    private void initVariables() {
        constr_ConstructionItem = getConstr_Construction_Employee();
        infoId = getIntent().getExtras().getInt(ARG_INFO, 0);
    }

    private void initHeader() {
        setTitle(getString(R.string.line_hang_header_title));
        tvTram.setText(constr_ConstructionItem
                .getStationCode());
        tvMaCongTrinh.setText(String.valueOf(constr_ConstructionItem
                .getConstrCode()));

        Supervision_BtsController bts_Controller = new Supervision_BtsController(
                this);
        btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
                .getSupervision_Constr_Id());
    }


    private void initViews() {
        Log.d(TAG,"Ban dang ở màn hình LineHangActivity nhé !");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_line_hang_info, R.layout.spinner_textview);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spThongTin.setAdapter(adapter);
        spThongTin.setSelection(infoId - 1);
        if (infoId == Constants.LINE_HANG_INFO.NHAT_KY_TIEN_DO_INFO) {
            capNhatTrangThaiNhatKyClick();
            fragmentCapNhatNhatKy = (BtsNhatkyFragment) BtsNhatkyFragment.newInstance(BtsNhatkyFragment.TYPE_TUYEN_TREO);
            fragmentCapNhatTienDo = TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_TREO);
        }
//        else if (infoId == Constants.LINE_HANG_INFO.TIEN_DO_INFO) {
//            fragmentCapNhatNhatKy = TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_TREO);
//        }

        if (fragmentCapNhatNhatKy != null) {
            fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragmentCapNhatNhatKy).commit();
        }
        if (fragmentCapNhatTienDo != null) {
            fragmentCapNhatTienDo.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_content_tiendo,fragmentCapNhatTienDo).commit();
        }
        fragmentCapNhatNhatKy.setOnCheckSwitchCombatStatus(new BtsNhatkyFragment.InterfaceCheckSwitchCombatStatus() {
            @Override
            public void checkSwitchCombatStatus(boolean isChecked) {
                if (isChecked) {
                    mBtnCapNhatTienDo.setVisibility(View.VISIBLE);
                    mIsCoThiCong = true;
                } else {
                    mBtnCapNhatTienDo.setVisibility(View.GONE);
                    mIsCoThiCong = false;
                }
            }
        });
        spThongTin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundleMonitorData = new Bundle();
                bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA, constr_ConstructionItem);
                int isInfomation = position + 1;
                bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO, isInfomation);
                if (flagFirstTime) {
                    flagFirstTime = false;
                    return;
                }
//                Log.e(TAG, "onItemSelected: " + isInfomation );
                switch (isInfomation) {
//                    case Constants.BTS_INFO.THIET_TKE_INFO:
//                        gotoSupervisionBtsActivity(bundleMonitorData);
//                        break;
                    case Constants.LINE_HANG_INFO.NHAT_KY_TIEN_DO_INFO:
                        capNhatTrangThaiNhatKyClick();

                        fragmentCapNhatNhatKy = (BtsNhatkyFragment) BtsNhatkyFragment.newInstance(BtsNhatkyFragment.TYPE_TUYEN_TREO);
                        fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragmentCapNhatNhatKy).commit();

                        fragmentCapNhatTienDo = TruyenDanNgamTiendoFragment.newInstance(BtsNhatkyFragment.TYPE_TUYEN_TREO);
                        fragmentCapNhatTienDo.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content_tiendo, fragmentCapNhatTienDo).commit();
                        break;
//                    case Constants.LINE_HANG_INFO.TIEN_DO_INFO:
//                        fragmentCapNhatNhatKy = TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_TREO);
//                        fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragmentCapNhatNhatKy).commit();
//                        break;
                    default:
                        gotoLineHangActivity(bundleMonitorData);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:
                // SyncModel.bRunning = false;

                SyncTask.closeDialog();

                Toast.makeText(this,
                        StringUtil.getString(R.string.text_sync_success),
                        Toast.LENGTH_LONG).show();
                break;
            default:
                super.handleModelViewEvent(modelEvent);
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (item.getItemId() == R.id.action_save) {
            if (fragmentCapNhatNhatKy == null) {
                return false;
            }
            if (fragmentCapNhatTienDo == null) {
                return false;
            }
            if (!fragmentCapNhatNhatKy.checkValidateEdtNoiDungCongViec()) {
                fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_noidung_congviec));
                mBtnCapNhatNhatKy.performClick();
                return false;
            }
            if (!fragmentCapNhatNhatKy.checkValidateEdtThayDoiBoSung()) {
                fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_thaydoi));
                return false;
            }
            if (!fragmentCapNhatNhatKy.checkValidateEdtYKienGiamSat()) {
                fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_y_kien_giam_sat));
                mBtnCapNhatNhatKy.performClick();
                return false;
            }
            if (!fragmentCapNhatNhatKy.checkValidateEdtYKienThiCong()) {
                fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_y_kien_thi_cong));
                mBtnCapNhatNhatKy.performClick();
                return false;
            }
            if (mIsCoThiCong) {
                if (fragmentCapNhatNhatKy.checkValidateNumberCapNhatNhatKy()) {
                    fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_number));
                    mBtnCapNhatNhatKy.performClick();
                    return false;
                }
                if (!mIsClickCapNhatTienDo) {
                    fragmentCapNhatNhatKy.showError(getString(R.string.str_check_da_cap_nhat_tien_do));
                    mBtnCapNhatTienDo.performClick();
                    return false;
                }
                fragmentCapNhatTienDo.save();
                fragmentCapNhatNhatKy.save();
            } else {
                fragmentCapNhatNhatKy.save();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Lister button cap nhat ky click.
    @OnClick(R.id.btnNhatKyTab)
    public void onBtnCapNhatNhatKyClick() {
        capNhatTrangThaiNhatKyClick();
    }

    // Lister button cap nhat tien do click.
    @OnClick(R.id.btnTienDoTab)
    public void onBtnCapNhatTienDoClick() {
        mIsClickCapNhatTienDo = true;
        capNhatTrangThaiTienDoClick();
    }

    private void capNhatTrangThaiNhatKyClick() {
        mFrameLayoutNhatKy.setVisibility(View.VISIBLE);
        mFrameLayoutTienDo.setVisibility(View.GONE);
        mBtnCapNhatNhatKy.setBackgroundColor(Color.CYAN);
        mBtnCapNhatTienDo.setBackgroundColor(Color.WHITE);
    }

    private void capNhatTrangThaiTienDoClick() {
        mFrameLayoutNhatKy.setVisibility(View.GONE);
        mFrameLayoutTienDo.setVisibility(View.VISIBLE);
        mBtnCapNhatNhatKy.setBackgroundColor(Color.WHITE);
        mBtnCapNhatTienDo.setBackgroundColor(Color.CYAN);
    }
}
