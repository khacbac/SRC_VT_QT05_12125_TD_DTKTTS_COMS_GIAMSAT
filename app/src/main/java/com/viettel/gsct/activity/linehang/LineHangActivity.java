package com.viettel.gsct.activity.linehang;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.viettel.gsct.preview.common.NhatKyTienDoPreviewFragment;
import com.viettel.gsct.fragment.nhatky.BtsNhatkyFragment;
import com.viettel.gsct.fragment.tiendo.other.TruyenDanNgamTiendoFragment;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.DeactivatedViewPager;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.CapNhatNhatKyTienDoPagerAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedHashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by admin2 on 04/04/2017.
 */

public class LineHangActivity extends LineBaseActivity implements ViewPager.OnPageChangeListener{
    private final String TAG = this.getClass().getSimpleName();
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
    @BindView(R.id.layout_root)
    LinearLayout mLayoutRoot;
    @BindView(R.id.layoutHeader)
    LinearLayout mLayoutHeader;
    @BindView(R.id.fr_content)
    FrameLayout mFrameLayoutPreview;
    @BindView(R.id.view_pager_content)
    DeactivatedViewPager mViewPagerContent;
    private boolean isSaved = false;
    // All constructor.
    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Supervision_BtsEntity btsEntity;
    // All fragment.
    private BtsNhatkyFragment fragmentCapNhatNhatKy;
    private TruyenDanNgamTiendoFragment fragmentCapNhatTienDo;
    private NhatKyTienDoPreviewFragment mNhatKyTienDoPreviewFragment;
    private CapNhatNhatKyTienDoPagerAdapter pagerAdapter;
    // All check boolean.
    private int infoId = 0;
    private boolean flagFirstTime = true;
    private boolean mIsCoThiCong = true;
    private boolean mHasClickBtnTienDo = false;;
    // All Key.
    private static final int KEY_SWITCH_NHATKY  = 0;
    private static final int KEY_SWITCH_TIENDO  = 1;

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
        if (mFrameLayoutPreview.getVisibility() == View.VISIBLE) {
            onShowNhatKyTienDoPreview(false);
            isSaved = false;
        } else {
            gotoHomeActivity(new Bundle());
            finish();
        }
    }

    private void initVariables() {
        constr_ConstructionItem = getConstr_Construction_Employee();
        Log.d(TAG,"Contruction id = " + constr_ConstructionItem.getConstructId());
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
//            capNhatTrangThaiNhatKyClick();
            fragmentCapNhatNhatKy = (BtsNhatkyFragment) BtsNhatkyFragment
                    .newInstance(BtsNhatkyFragment.TYPE_TUYEN_TREO);
            fragmentCapNhatTienDo = (TruyenDanNgamTiendoFragment) TruyenDanNgamTiendoFragment
                    .newInstance(TruyenDanNgamTiendoFragment.TYPE_TUYEN_TREO);
            mNhatKyTienDoPreviewFragment = (NhatKyTienDoPreviewFragment)
                    NhatKyTienDoPreviewFragment.newInstance();
        }

        if (fragmentCapNhatNhatKy != null) {
            fragmentCapNhatNhatKy.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
        }
        if (fragmentCapNhatTienDo != null) {
            fragmentCapNhatTienDo
                    .setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
        }
        if (mNhatKyTienDoPreviewFragment != null) {
            mNhatKyTienDoPreviewFragment
                    .setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
        }

        // Init Preview Fragment.
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fr_content, mNhatKyTienDoPreviewFragment)
                .commit();
        // Init Nhat Ky va Tien Do fragment.
        pagerAdapter = new CapNhatNhatKyTienDoPagerAdapter(
                getSupportFragmentManager(),
                fragmentCapNhatNhatKy,
                fragmentCapNhatTienDo);

        mViewPagerContent.setAdapter(pagerAdapter);
        mViewPagerContent.setOnPageChangeListener(this);

        mBtnCapNhatNhatKy.setBackgroundResource(R.drawable.action_button_focused);
        mBtnCapNhatTienDo.setBackgroundResource(R.drawable.action_button_not_focus);
        fragmentCapNhatNhatKy.setOnCheckSwitchCombatStatus(new BtsNhatkyFragment
                .InterfaceCheckSwitchCombatStatus() {
            @Override
            public void checkSwitchCombatStatus(boolean isChecked) {
                if (isChecked) {
                    mBtnCapNhatTienDo.setVisibility(View.VISIBLE);
                    mIsCoThiCong = true;
                    mViewPagerContent.setEnabledSwipe(true);
                } else {
                    mBtnCapNhatTienDo.setVisibility(View.GONE);
                    mIsCoThiCong = false;
                    mViewPagerContent.setEnabledSwipe(false);
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
                    case Constants.LINE_HANG_INFO.NHAT_KY_TIEN_DO_INFO:
                        if (mViewPagerContent != null) {
                            mViewPagerContent.setCurrentItem(KEY_SWITCH_NHATKY);
                        }
                        break;
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
            if (!listenerValidateFromNhatKy(fragmentCapNhatNhatKy,mIsCoThiCong)) {
                return false;
            }
            if (mIsCoThiCong) {
                if (!mHasClickBtnTienDo) {
                    fragmentCapNhatNhatKy.showError(getString(R.string.str_check_da_cap_nhat_tien_do));
                    mBtnCapNhatTienDo.performClick();
                    return false;
                }
                if (!listenerValidateFromTienDo(fragmentCapNhatTienDo)) {
                    return false;
                }
                if (mFrameLayoutPreview.getVisibility() == View.VISIBLE) {
                    if (!isSaved) {
                        // Save nhat ky.
                        listenerSaveFromNhatKy(fragmentCapNhatNhatKy);
                        // Save tien do.
                        listenerSaveFromTienDo(fragmentCapNhatTienDo);
                        isSaved = true;
                    }
                } else {
                    Toast.makeText(this,""+getResources()
                            .getString(R.string.str_thong_bao_truoc_khi_luu),Toast.LENGTH_SHORT)
                            .show();
                    showPreviewNhatKyTienDoClick();
                    fragmentCapNhatNhatKy.registerListenerEventBusTuyenTreoNhatKy();
                    fragmentCapNhatTienDo.registerListenerEventBus();
                }
            } else {
                // Save nhat ky.
                listenerSaveFromNhatKy(fragmentCapNhatNhatKy);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Lister button cap nhat ky click.
    @OnClick(R.id.btnNhatKyTab)
    public void onBtnCapNhatNhatKyClick() {
        setColorForBtnNhatKyTienDoClick(mBtnCapNhatNhatKy);
        mViewPagerContent.setCurrentItem(KEY_SWITCH_NHATKY);
    }

    // Lister button cap nhat tien do click.
    @OnClick(R.id.btnTienDoTab)
    public void onBtnCapNhatTienDoClick() {
        mHasClickBtnTienDo = true;
        if (!listenerValidateFromNhatKy(fragmentCapNhatNhatKy,mIsCoThiCong)) {
            return;
        }
        setColorForBtnNhatKyTienDoClick(mBtnCapNhatTienDo);
        mViewPagerContent.setCurrentItem(KEY_SWITCH_TIENDO);
    }

    /**
     * When switch to preview screen.
     */
    private void showPreviewNhatKyTienDoClick() {
        onShowNhatKyTienDoPreview(true);
    }

    private void onShowNhatKyTienDoPreview(boolean isShow) {
        if (isShow) {
            setVisibilityForLayoutPreview(true);
            mFrameLayoutPreview.setVisibility(View.VISIBLE);
        } else {
            setVisibilityForLayoutPreview(false);
            mFrameLayoutPreview.setVisibility(View.GONE);
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataFromTuyenNgamTienDoEvent(LinearLayout layoutRoot) {
        mNhatKyTienDoPreviewFragment.initDataForTuyenNgamTienDoExpandable(layoutRoot);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataFromCapNhatTuyenNgamNhatKy(LinkedHashMap<String,String> hashMaps) {
        hashMaps.put(KeyEventCommon.KEY_TEN_TRAM_TUYEN, "" + tvTram.getText());
        mNhatKyTienDoPreviewFragment.initDataForNhatKy(
                hashMaps,
                KeyEventCommon.KEY_DOI_TUYENTREO_ARR,
                KeyEventCommon.KEY_TEN_HM_TUYENTREO_ARR);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        if (mViewPagerContent == null) {
            return;
        }
        switch (position) {
            case KEY_SWITCH_NHATKY:
                setColorForBtnNhatKyTienDoClick(mBtnCapNhatNhatKy);
                break;
            case KEY_SWITCH_TIENDO:
                mHasClickBtnTienDo = true;
                if (listenerValidateFromNhatKy(fragmentCapNhatNhatKy,mIsCoThiCong)) {
                    setColorForBtnNhatKyTienDoClick(mBtnCapNhatTienDo);
                } else {
                    mViewPagerContent.setCurrentItem(KEY_SWITCH_NHATKY);
                    mHasClickBtnTienDo = true;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

}
