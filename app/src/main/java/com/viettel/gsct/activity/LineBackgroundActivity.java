package com.viettel.gsct.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.KeyEventCommon;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.SubWorkItemTienDoNgamView;
import com.viettel.gsct.View.WorkItemTienDoNgamView;
import com.viettel.gsct.fragment.BtsNhatkyFragment;
import com.viettel.gsct.fragment.TruyenDanNgamTiendoFragment;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.listener.InterfacePassDataFromNhatKyToActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin2 on 04/04/2017.
 */

public class LineBackgroundActivity extends LineBaseActivity implements InterfacePassDataFromNhatKyToActivity {
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
    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;
    @BindView(R.id.layoutHeader)
    LinearLayout mLayoutHeader;

    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
    private Supervision_BtsEntity btsEntity;
    private int infoId = 0;
    // All Fragment.
    private BtsNhatkyFragment fragmentCapNhatNhatKy;
    private TruyenDanNgamTiendoFragment fragmentCapNhatTienDo;
    private CapNhatNhatKyTienDoPreviewFragment mCapNhatNhatKyTienDoPreviewFragment;
    // All bien check.
    private boolean flagFirstTime = true;
    private boolean mHasClickBtnTienDo = false;
    private boolean mIsCoThiCong = true;
    private boolean mIsClickCapNhatTienDo = false;
    private boolean mIsClickCapNhatNhatKy = false;
    private boolean mIsClickPreview = false;
    private boolean mCheckCapNhatTienDo = false;
    private boolean mCheckCapNhatNhatKy = false;
    private boolean mCheckPreview = false;

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
        if (mIsClickPreview) {
            onFragmentNhatKyClick();
        } else {
            gotoHomeActivity(new Bundle());
            finish();
        }
    }

    private void initVariables() {
        constr_ConstructionItem = getConstr_Construction_Employee();
        this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA_EX);
        infoId = getIntent().getExtras().getInt(ARG_INFO, 0);
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

    private void initHeader() {
        setTitle(getString(R.string.line_background_header_title));
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
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_line_background_info, R.layout.spinner_textview);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spThongTin.setAdapter(adapter);
        spThongTin.setSelection(infoId - 1);
        if (infoId == Constants.LINE_BACKGROUND_INFO.NHAT_KY_TIEN_DO_INFO) {
            fragmentCapNhatNhatKy = (BtsNhatkyFragment) BtsNhatkyFragment.newInstance(BtsNhatkyFragment.TYPE_TUYEN_NGAM);
            fragmentCapNhatTienDo = (TruyenDanNgamTiendoFragment) TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_NGAM);
            mCapNhatNhatKyTienDoPreviewFragment = new CapNhatNhatKyTienDoPreviewFragment();
        }
        if (fragmentCapNhatNhatKy != null) {
            fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
        }
        if (fragmentCapNhatTienDo != null) {
            fragmentCapNhatTienDo.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
        }
        if (mCapNhatNhatKyTienDoPreviewFragment == null) {
            return;
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fr_content, fragmentCapNhatNhatKy, KeyEventCommon.KEY_NHATKY_FRAGMENT)
                .add(R.id.fr_content, fragmentCapNhatTienDo, KeyEventCommon.KEY_TIENDO_FRAGMENT)
                .add(R.id.fr_content, mCapNhatNhatKyTienDoPreviewFragment,
                        KeyEventCommon.KEY_PREVIEW_FRAGMENT)
                .hide(fragmentCapNhatTienDo)
                .hide(mCapNhatNhatKyTienDoPreviewFragment)
                .commit();

        mBtnCapNhatNhatKy.setBackgroundResource(R.drawable.action_button_focused);
        mBtnCapNhatTienDo.setBackgroundResource(R.drawable.action_button_not_focus);

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
                bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA_EX, supervision_Line_BackgroundData);
                int isInfomation = position + 1;
                bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO, isInfomation);
                if (flagFirstTime) {
                    flagFirstTime = false;
                    return;
                }
                Log.e(TAG, "onItemSelected: " + isInfomation );
                switch (isInfomation) {
//                    case Constants.BTS_INFO.THIET_TKE_INFO:
//                        gotoSupervisionBtsActivity(bundleMonitorData);
//                        break;
                    case Constants.LINE_BACKGROUND_INFO.NHAT_KY_TIEN_DO_INFO:
//                        fragmentCapNhatNhatKy = (BtsNhatkyFragment) BtsNhatkyFragment.newInstance(BtsNhatkyFragment.TYPE_TUYEN_NGAM);
//                        fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragmentCapNhatNhatKy).commit();
//
//
//                        fragmentCapNhatTienDo = (TruyenDanNgamTiendoFragment) TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_NGAM);
//                        fragmentCapNhatTienDo.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content_tiendo, fragmentCapNhatTienDo).commit();
                        onFragmentNhatKyClick();
                        break;
//                    case Constants.LINE_BACKGROUND_INFO.TIEN_DO_INFO:
//                        fragmentCapNhatNhatKy = TruyenDanNgamTiendoFragment.newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_NGAM);
//                        fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragmentCapNhatNhatKy).commit();
//                        break;
                    default:
                        gotoLineBackgroupActivity(bundleMonitorData);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
            if (!checkValidateFromCapNhatNhatKy()) {
                return false;
            }
            if (mIsCoThiCong) {
                if (!mHasClickBtnTienDo) {
                    fragmentCapNhatNhatKy.showError(getString(R.string.str_check_da_cap_nhat_tien_do));
                    mBtnCapNhatTienDo.performClick();
                    return false;
                }
                if (!mIsClickPreview) {
                    showPreviewNhatKyTienDoClick();
                    mIsClickCapNhatNhatKy = false;
                    mIsClickCapNhatTienDo = false;
                    mIsClickPreview = true;
                }
                fragmentCapNhatNhatKy.onPassDataFromNhatKyTuyenNgamToActivity();
//                fragmentCapNhatTienDo.onPassDataFromTienDoToActivity();
                fragmentCapNhatTienDo.save();
                fragmentCapNhatNhatKy.save();
            } else {
                fragmentCapNhatNhatKy.save();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnNhatKyTab)
    public void onBtnCapNhatNhatKyClick() {
        if (!mIsClickCapNhatNhatKy) {
            onFragmentNhatKyClick();
        }
    }

    @OnClick(R.id.btnTienDoTab)
    public void onBtnCapNhatTienDoClick() {
        mHasClickBtnTienDo = true;
        if (!checkValidateFromCapNhatNhatKy()) {
            return;
        }
        if (!mIsClickCapNhatTienDo) {
            onFragmentTienDoClick();
        }
    }

    private boolean checkValidateFromCapNhatNhatKy() {
        if (mIsCoThiCong && fragmentCapNhatNhatKy.checkValidateNumberCapNhatNhatKy()) {
            fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_number));
            return false;
        }
        if (!fragmentCapNhatNhatKy.checkValidateEdtNoiDungCongViec()) {
            fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_noidung_congviec));
            return false;
        }
        if (!fragmentCapNhatNhatKy.checkValidateEdtThayDoiBoSung()) {
            fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_thaydoi));
            return false;
        }
        if (!fragmentCapNhatNhatKy.checkValidateEdtYKienGiamSat()) {
            fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_y_kien_giam_sat));
            return false;
        }
        if (!fragmentCapNhatNhatKy.checkValidateEdtYKienThiCong()) {
            fragmentCapNhatNhatKy.showError(getString(R.string.str_check_validate_edt_y_kien_thi_cong));
            return false;
        }
        return true;
    }

    /**
     * When click button cap nhat nhat ky.
     */
    private void onFragmentNhatKyClick() {
        // isNhatKy = true, isTienDo = false, isPreview = false.
        showFragmentFollowEvent(true,false,false);
        setColorForBtnNhatKyTienDoClick(mBtnCapNhatNhatKy);
        setVisibilityForLayoutPreview(false);
        setCheckFlagFollowEventClick(mCheckCapNhatNhatKy);
    }

    /**
     * When click button cap nhat tien do.
     */
    private void onFragmentTienDoClick() {
        // isNhatKy = false, isTienDo = true, isPreview = false.
        showFragmentFollowEvent(false,true,false);
        setColorForBtnNhatKyTienDoClick(mBtnCapNhatTienDo);
        setVisibilityForLayoutPreview(false);
        setCheckFlagFollowEventClick(mCheckCapNhatTienDo);
    }

    /**
     * When switch to preview screen.
     */
    private void showPreviewNhatKyTienDoClick() {
        // isNhatKy = false, isTienDo = false, isPreview = true.
        showFragmentFollowEvent(false,false,true);
        setVisibilityForLayoutPreview(true);
        setCheckFlagFollowEventClick(mCheckPreview);
    }

    /**
     * Set color for button when click.
     * @param isBtnClick Button.
     */
    private void setColorForBtnNhatKyTienDoClick (Button isBtnClick) {
        mBtnCapNhatNhatKy.setBackgroundResource(R.drawable.action_button_not_focus);
        mBtnCapNhatTienDo.setBackgroundResource(R.drawable.action_button_not_focus);
        isBtnClick.setBackgroundResource(R.drawable.action_button_focused);
    }

    /**
     * Set visibility status for layout when switch to preview.
     * @param isLayoutPreview boolean.
     */
    private void setVisibilityForLayoutPreview(boolean isLayoutPreview) {
        if (isLayoutPreview) {
            mLayoutHeader.setVisibility(View.GONE);
            mLayoutRoot.setVisibility(View.GONE);
        } else {
            mLayoutHeader.setVisibility(View.VISIBLE);
            mLayoutRoot.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set flag for events click.
     * @param isItemClick boolean.
     */
    private void setCheckFlagFollowEventClick(boolean isItemClick) {
        mCheckCapNhatNhatKy = false;
        mCheckCapNhatTienDo = false;
        mCheckPreview = false;
        isItemClick = true;
    }

    /**
     * Hide and show fragment follow events click.
     * @param isNhatKy boolean.
     * @param isTienDo boolean.
     * @param isPreview boolean.
     */
    private void showFragmentFollowEvent(boolean isNhatKy,boolean isTienDo,boolean isPreview) {
        BtsNhatkyFragment nhatkyFragment = (BtsNhatkyFragment)
                getSupportFragmentManager().findFragmentByTag(KeyEventCommon.KEY_NHATKY_FRAGMENT);
        TruyenDanNgamTiendoFragment tiendoFragment = (TruyenDanNgamTiendoFragment)
                getSupportFragmentManager().findFragmentByTag(KeyEventCommon.KEY_TIENDO_FRAGMENT);
        CapNhatNhatKyTienDoPreviewFragment previewFragment = (CapNhatNhatKyTienDoPreviewFragment)
                getSupportFragmentManager().findFragmentByTag(KeyEventCommon.KEY_PREVIEW_FRAGMENT);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (isNhatKy) {
            ft.show(nhatkyFragment);
        } else {
            ft.hide(nhatkyFragment);
        }
        if (isTienDo) {
            ft.show(tiendoFragment);
        } else {
            ft.hide(tiendoFragment);
        }
        if (isPreview) {
            ft.show(previewFragment);
        } else {
            ft.hide(previewFragment);
        }
        ft.commit();
    }

    /**
     * Pass data from cap nhat nhat ky to activity.
     * @param listData HashMap<String, String>.
     */
    @Override
    public void passDataFromNhatKyToActivity(HashMap<String, String> listData) {
        if (mCapNhatNhatKyTienDoPreviewFragment != null) {
            if (listData != null) {
                listData.put(KeyEventCommon.KEY_TEN_TRAM_TUYEN,""+tvTram.getText());
                mCapNhatNhatKyTienDoPreviewFragment.updateDataForCapNhatNhatKy(listData,KeyEventCommon.KEY_TUYEN_NGAM);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataFromTuyenNgamTienDoEvent(LinearLayout layoutRoot) {
        mCapNhatNhatKyTienDoPreviewFragment.initDataForTuyenNgamTienDoExpandable(layoutRoot);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
