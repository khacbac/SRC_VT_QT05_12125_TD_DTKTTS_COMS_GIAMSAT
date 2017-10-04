package com.viettel.ktts;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.Constants.BTS_HOUSE_TYPE;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_ProgressEntity;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.gsct.activity.info.InfoActivity;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Constr_ConstructionAdapter;
import com.viettel.view.control.VNMEditTextClearable;
import com.viettel.view.control.VNMTextViewCombo;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends HomeBaseActivity {
    private View homeView;
    private TableLayout tl_constr_construction_search;
    private Button constr_construction_btn_search;
    private ListView lv_constr_construction_list;
    private TextView trl_constr_construction_search_row3_tv_matram;
    private TextView trl_constr_construction_search_row1_tv_td;
    private TextView trl_constr_construction_search_row3_tv_matuyen;
    private TextView trl_constr_construction_search_row4_tv_diemdau;
    private TextView trl_constr_construction_search_row4_tv_diemcuoi;
    private RelativeLayout rl_constr_construction_space;

    private VNMTextViewCombo rl_constr_construction_search_lct_tv_lct;
    private VNMTextViewCombo rl_constr_construction_search_lct_tv_tt;
    private VNMTextViewCombo rl_constr_construction_search_lct_tv_td;
    private VNMEditTextClearable trl_constr_construction_search_row1_edt_mct;
    private VNMEditTextClearable trl_constr_construction_search_row3_edt_matram;
    private VNMEditTextClearable trl_constr_construction_search_row3_edt_matuyen;
    private VNMEditTextClearable trl_constr_construction_search_row4_edt_diemdau;
    private VNMEditTextClearable trl_constr_construction_search_row4_edt_diemcuoi;
    /* khai bao bien dung tim kiem */
    private String sAll = "Tất cả";
    private int iStatus = Constants.SEARCH_VALUE_DEFAULT;
    /* Loai cong trinh */
    private int iDesignType = Constants.CONSTR_TYPE.BTS;
    private int iProgress = Constants.SEARCH_VALUE_DEFAULT;
    /* Ma tram or Ma tuyen */
    private String stationCode;
    /* Diem dau */
    private String startPointCode;
    /* Diem cuoi */
    private String endPointCode;
    /* Hien thi popup */
    private Menu_DropdownPopup dropdownPopupMenuProgress;
    private Menu_DropdownPopup dropdownPopupMenuDesignType;
    private Menu_DropdownPopup dropdownPopupMenuStatus;
    private List<DropdownItemEntity> listProgress = null;
    private List<DropdownItemEntity> listDesignType = null;
    private List<DropdownItemEntity> listStatus = null;
    private List<Constr_Construction_EmployeeEntity> listData = null;
    private Constr_ConstructionAdapter constr_ConstructionAdapter = null;
    // bien phan trang
    private boolean bFirst = true;
    private boolean bEndList = false;
    private boolean bLoaded = true;
    protected int itemLoaded = 0;
    protected int itemPerLoad = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.constr_sonstruction_title));
        /* set controll */
        this.initView();
        initData();
        new loadMore().execute();
    }

    private void initView() {
        homeView = addView(R.layout.home_activity, R.id.rl_login);
        this.trl_constr_construction_search_row1_tv_td = (TextView) homeView.findViewById(R.id.trl_constr_construction_search_row1_tv_td);
        this.tl_constr_construction_search = (TableLayout) homeView
                .findViewById(R.id.tl_constr_construction_search);
        this.constr_construction_btn_search = (Button) homeView
                .findViewById(R.id.constr_construction_btn_search);
        this.constr_construction_btn_search.setOnClickListener(this);

        this.lv_constr_construction_list = (ListView) homeView
                .findViewById(R.id.lv_constr_construction_list);
        this.lv_constr_construction_list.setOnScrollListener(Scroll);

        this.rl_constr_construction_space = (RelativeLayout) homeView
                .findViewById(R.id.rl_constr_construction_space);

        this.rl_constr_construction_search_lct_tv_lct = (VNMTextViewCombo) homeView
                .findViewById(R.id.rl_constr_construction_search_lct_tv_lct);
        this.rl_constr_construction_search_lct_tv_lct.setOnClickListener(this);
        this.rl_constr_construction_search_lct_tv_tt = (VNMTextViewCombo) homeView
                .findViewById(R.id.rl_constr_construction_search_lct_tv_tt);
        this.rl_constr_construction_search_lct_tv_tt.setOnClickListener(this);
        this.rl_constr_construction_search_lct_tv_td = (VNMTextViewCombo) homeView
                .findViewById(R.id.rl_constr_construction_search_lct_tv_td);
        this.rl_constr_construction_search_lct_tv_td.setOnClickListener(this);

        this.trl_constr_construction_search_row1_edt_mct = (VNMEditTextClearable) homeView
                .findViewById(R.id.trl_constr_construction_search_row1_edt_mct);

        this.trl_constr_construction_search_row3_edt_matram = (VNMEditTextClearable) homeView
                .findViewById(R.id.trl_constr_construction_search_row3_edt_matram);

        this.trl_constr_construction_search_row3_edt_matuyen = (VNMEditTextClearable) homeView
                .findViewById(R.id.trl_constr_construction_search_row3_edt_matuyen);

        this.trl_constr_construction_search_row4_edt_diemdau = (VNMEditTextClearable) homeView
                .findViewById(R.id.trl_constr_construction_search_row4_edt_diemdau);

        this.trl_constr_construction_search_row4_edt_diemcuoi = (VNMEditTextClearable) homeView
                .findViewById(R.id.trl_constr_construction_search_row4_edt_diemcuoi);

        this.trl_constr_construction_search_row3_tv_matram = (TextView) homeView
                .findViewById(R.id.trl_constr_construction_search_row3_tv_matram);

        this.trl_constr_construction_search_row3_tv_matuyen = (TextView) homeView
                .findViewById(R.id.trl_constr_construction_search_row3_tv_matuyen);

        this.trl_constr_construction_search_row4_tv_diemdau = (TextView) homeView
                .findViewById(R.id.trl_constr_construction_search_row4_tv_diemdau);

        this.trl_constr_construction_search_row4_tv_diemcuoi = (TextView) homeView
                .findViewById(R.id.trl_constr_construction_search_row4_tv_diemcuoi);

        this.listDesignType = GloablUtils.getListConstructionType(sAll);
        this.listProgress = GloablUtils.getListConstructionProgress(sAll);
        this.listStatus = GloablUtils.getListConstructionStatus(sAll);

        this.rl_constr_construction_search_lct_tv_lct
                .setText(getText(R.string.constr_sonstruction_type_bts));
        this.rl_constr_construction_search_lct_tv_tt
                .setText(getText(R.string.text_search_value_default));
        this.rl_constr_construction_search_lct_tv_td
                .setText(getText(R.string.text_search_value_default));
        //KienPV add new 10/09/2016
        this.trl_constr_construction_search_row1_edt_mct.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                findValue();
            }
        });
        this.trl_constr_construction_search_row3_edt_matram.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                findValue();
            }
        });
        //---
        hideDesignType();

    }

    @Override
    protected void onResume() {
        GlobalInfo.getInstance().setCheckLogin(true);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        gotoLogOut();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.constr_construction_btn_search:
                findValue();
                break;
            case R.id.rl_constr_construction_search_lct_tv_lct:
                this.dropdownPopupMenuDesignType = new Menu_DropdownPopup(this, this.listDesignType);
                dropdownPopupMenuDesignType.show(v);
                break;
            case R.id.rl_constr_construction_search_lct_tv_td:
                this.dropdownPopupMenuProgress = new Menu_DropdownPopup(this, this.listProgress);
                dropdownPopupMenuProgress.show(v);
                break;
            case R.id.rl_constr_construction_search_lct_tv_tt:
                this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this, this.listStatus);
                dropdownPopupMenuStatus.show(v);
                break;
            default:
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                break;
        }
    }

    //KienPV add new 10/09/2016
    private void findValue() {
        this.itemLoaded = 0;
        this.bEndList = false;
        this.listData.clear();
        this.constr_ConstructionAdapter.notifyDataSetChanged();
        new loadMore().execute();
    }

    // ---
    @Override
    public void onItemSelected(int position) {
        super.onItemSelected(position);
        switch (position) {
            case 0:
                break;
            case 1:
                int iVisit = tl_constr_construction_search.getVisibility();
                if (iVisit == View.GONE) {
                    changeIconSearch(position, R.drawable.icon_up, getString(R.string.arrow_up));
                    tl_constr_construction_search.setVisibility(View.VISIBLE);
                } else {
                    changeIconSearch(position, R.drawable.icon_down, getString(R.string.arrow_down));
                    tl_constr_construction_search.setVisibility(View.GONE);
                }
                break;

            default:
                break;
        }
    }

    private void setHeader() {
        final Header myActionBar = (Header) homeView.findViewById(R.id.actionbar);
        myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
        // buttom search
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {

                int iVisit = tl_constr_construction_search.getVisibility();
                if (iVisit == View.GONE) {
                    tl_constr_construction_search.setVisibility(View.VISIBLE);
                } else {
                    tl_constr_construction_search.setVisibility(View.GONE);
                }
            }

            @Override
            public int getDrawable() {
                return R.drawable.search;
            }
        });
		/* Dong bo du lieu */
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                requestSync(HomeActivity.this);
            }

            @Override
            public int getDrawable() {
                return R.drawable.icon_rotate;
            }
        });
        // buttom loguot
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoLogOut();
            }

            @Override
            public int getDrawable() {
                return R.drawable.logout;
            }
        });
    }

//	private void requestSync() {
//		if (this.check3GWifi()) {
//			/* Kiem tray xem co dang dong bo khong */
////			showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
//			// if (SyncModel.bRunning) {
//			// /* Neu dang dong bo */
//			// } else {
//			// SyncModel.bRunning = true;
//			// SyncModel.bStop = false;
//			Bundle bundle = new Bundle();
//			ActionEvent e = new ActionEvent();
//			e.action = ActionEventConstant.REQEST_SYNC;
//			e.viewData = bundle;
//			e.isBlockRequest = true;
//			e.sender = this;
//			Home_Controller.getInstance().handleViewEvent(e, HomeActivity.this);
//			// }
//		} else {
//			this.show3GWifiOffline();
//		}
//	}

    public void hideDesignType() {
        if (iDesignType == Constants.CONSTR_TYPE.BTS) {
            trl_constr_construction_search_row3_tv_matuyen
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_edt_matuyen
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_tv_matram
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row3_edt_matram
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row4_tv_diemdau
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_edt_diemdau
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_tv_diemcuoi
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_edt_diemcuoi
                    .setVisibility(View.GONE);
            rl_constr_construction_space.setVisibility(View.VISIBLE);
            rl_constr_construction_search_lct_tv_td.setVisibility(View.VISIBLE);
            trl_constr_construction_search_row1_tv_td
                    .setVisibility(View.VISIBLE);
        } else if (iDesignType == Constants.CONSTR_TYPE.BRCD
                || iDesignType == Constants.CONSTR_TYPE.SH) {

            trl_constr_construction_search_row3_tv_matuyen
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_edt_matuyen
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_tv_matram
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row3_edt_matram
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row4_tv_diemdau
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_edt_diemdau
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_tv_diemcuoi
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row4_edt_diemcuoi
                    .setVisibility(View.GONE);
            rl_constr_construction_space.setVisibility(View.VISIBLE);
            rl_constr_construction_search_lct_tv_td.setVisibility(View.INVISIBLE);
            trl_constr_construction_search_row1_tv_td.setVisibility(View.INVISIBLE);
        } else {
            trl_constr_construction_search_row3_tv_matram
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_edt_matram
                    .setVisibility(View.GONE);
            trl_constr_construction_search_row3_tv_matuyen
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row3_edt_matuyen
                    .setVisibility(View.VISIBLE);

            trl_constr_construction_search_row4_tv_diemdau
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row4_edt_diemdau
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row4_tv_diemcuoi
                    .setVisibility(View.VISIBLE);
            trl_constr_construction_search_row4_edt_diemcuoi
                    .setVisibility(View.VISIBLE);

            rl_constr_construction_space.setVisibility(View.GONE);
            rl_constr_construction_search_lct_tv_td.setVisibility(View.VISIBLE);
            trl_constr_construction_search_row1_tv_td
                    .setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onEvent(int eventType, View control, Object data) {
        switch (eventType) {
            case OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_INFO:

                Intent intent = new Intent(this, InfoActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.HomeActivity"));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(IntentConstants.INTENT_DATA, (Constr_Construction_EmployeeEntity) data);
                startActivity(intent);
                break;
            case OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_MONITOR:
                Constr_Construction_EmployeeEntity constr_ConstructionItem = (Constr_Construction_EmployeeEntity) data;
                Bundle bundleMonitorData = new Bundle();
                //TODO hungtn add test
                //constr_ConstructionEmployeeItem.setSupvConstrType(5);
                //
                Log.e("TAG", "onEvent: " + constr_ConstructionItem.getSupvConstrType());
                bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
                        constr_ConstructionItem);
                if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.BTS) {
                    this.gotoSupervisionBTSActivity(bundleMonitorData);
//				this.finish();
                } else if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.LINE_BACKGROUND) {
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO);
                    this.gotoLineBackgroupActivity(bundleMonitorData);
//				this.finish();
                } else if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.LINE_HANG) {
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            Constants.LINE_HANG_INFO.THIE_TKE_INFO);
                    this.gotoLineHangActivity(bundleMonitorData);
//				this.finish();

                } else if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.BRCD) {
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK);
                    this.gotoBRCDActivity(bundleMonitorData);
//				this.finish();
                } else if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.SH) {
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK);
                    this.gotoSubActivity(bundleMonitorData);
//				this.finish();
                } else {
                    Toast.makeText(
                            this,
                            StringUtil
                                    .getString(R.string.constr_sonstruction_no_set_line_type),
                            Toast.LENGTH_SHORT).show();
                }
                break;
		/* Cap nhat tien do cong trinh */
            case OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_PROGRESS:
                Constr_Construction_EmployeeEntity constructionItem = (Constr_Construction_EmployeeEntity) data;
                if (constructionItem.getDeployExpectedDay() == 0) {
                    this.showDialog(StringUtil
                            .getString(R.string.constr_deployday_home_is_empty));
                    return;
                }
                if (constructionItem.getCatProgressWorkId() == 3) {
                    if (constructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.BTS) {
                        Supervision_BtsEntity btsEntity = new Supervision_BtsController(
                                this).getSupervisionBts(constructionItem
                                .getSupervision_Constr_Id());

                        Supervision_ProgressEntity tcc = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.THI_CONG_COT_BTS);
                        Supervision_ProgressEntity ldc = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.LAP_DUNG_COT_ANTEN_BTS);
                        Supervision_ProgressEntity tctd = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.TIEP_DIA_BTS);
                        Supervision_ProgressEntity pm = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.PHONG_MAY);
                        Supervision_ProgressEntity nmn = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.NHA_MAY_NO);
                        Supervision_ProgressEntity kdpm = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.KEO_DAY_DIEN_PHONG_MAY_NO);
                        Supervision_ProgressEntity kd = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.KEO_DAY_DIEN);
                        Supervision_ProgressEntity ldtb = getSupervisionProgress(
                                constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI);
                        // Supervision_ProgressEntity ldvb = getSupervisionProgress(
                        // constructionItem, Constants.PROGRESS_TYPE.BTS_TYPE,
                        // Constants.PROGRESS_TYPE.LAP_DAT_VIBA);

                        if (btsEntity.getHouse_TYPE() == BTS_HOUSE_TYPE.CO_SAN) {
                            pm = null;
                        }
                        if (btsEntity.getPillar_ANTEN() == Constants.BTS_POS_PILLAR_ANTEN.CO_SAN
                                || btsEntity.getPillar_ANTEN_TYPE() == Constants.BTS_PILLAR_ANTEN_TYPE.COT_COC) {
                            tcc = null;
                        }
                        if (btsEntity.getHouse_GENERATOR() == Constants.ID_ENTITY_DEFAULT) {
                            nmn = null;
                            kdpm = null;
                        }
                        if ((constructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE && btsEntity
                                .getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP)
                                || constructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
                            tcc = null;
                            ldc = null;
                            tctd = null;
                            pm = null;
                            nmn = null;
                            kdpm = null;
                            kd = null;
                            // ldvb = null;
                        }

                        if ((tcc != null && tcc.getSupervisionProgressId() <= 0)
                                || (ldc != null && ldc.getSupervisionProgressId() <= 0)
                                || (tctd != null && tctd.getSupervisionProgressId() <= 0)
                                || (pm != null && pm.getSupervisionProgressId() <= 0)
                                || (nmn != null && nmn.getSupervisionProgressId() <= 0)
                                || (kdpm != null && kdpm.getSupervisionProgressId() <= 0)
                                || (kd != null && kd.getSupervisionProgressId() <= 0)
                                || ldtb.getSupervisionProgressId() <= 0
                            // || (ldvb != null && ldvb.getSupervisionProgressId() <= 0)
                                ) {
                            this.showDialog(getString(
                                    R.string.text_tiendo_not_complete,
                                    constructionItem.getNameProgress()));
                            return;
                        }
                    } else {

                        if (constructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.LINE) {
                            this.showDialog(getString(
                                    R.string.text_tiendo_loaituyen,
                                    constructionItem.getNameProgress()));
                            return;
                        } else {
                            if (constructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.LINE_BACKGROUND) {
                                Supervision_ProgressEntity tank = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                                        Constants.PROGRESS_TYPE.BG_TANK);
                                Supervision_ProgressEntity pipe = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                                        Constants.PROGRESS_TYPE.BG_PIPE);
                                Supervision_ProgressEntity cable = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                                        Constants.PROGRESS_TYPE.BG_CABLE);
                                Supervision_ProgressEntity special = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                                        Constants.PROGRESS_TYPE.BG_SPECIAL);
                                if (tank.getSupervisionProgressId() <= 0
                                        || pipe.getSupervisionProgressId() <= 0
                                        || cable.getSupervisionProgressId() <= 0
                                        || special.getSupervisionProgressId() <= 0) {
                                    this.showDialog(getString(
                                            R.string.text_tiendo_not_complete,
                                            constructionItem.getNameProgress()));
                                    return;
                                }
                            } else {
                                Supervision_ProgressEntity pillar = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_HANG,
                                        Constants.PROGRESS_TYPE.HG_PILLAR);
                                Supervision_ProgressEntity cable = getSupervisionProgress(
                                        constructionItem,
                                        Constants.PROGRESS_TYPE.LINE_HANG,
                                        Constants.PROGRESS_TYPE.HG_CABLE);
                                if (pillar.getSupervisionProgressId() <= 0
                                        || cable.getSupervisionProgressId() <= 0) {
                                    this.showDialog(getString(
                                            R.string.text_tiendo_not_complete,
                                            constructionItem.getNameProgress()));
                                    return;
                                }
                            }
                        }

                    }

                }
                if (constructionItem.getCatProgressWorkId() == 4) {
                    this.showDialog(getString(R.string.text_tiendo_complete,
                            constructionItem.getNameProgress()));
                    return;
                }
                ConfirmDialog confirmDialog = new ConfirmDialog(this,
                        constructionItem);
                confirmDialog.show();
                break;
            // ghi lai tien do cho cong trinh
            case OnEventControlListener.EVENT_CONFIRM_PROGRESS:
                Constr_Construction_EmployeeEntity constrItem = (Constr_Construction_EmployeeEntity) data;

                SaveAsyncTask task = new SaveAsyncTask(
                        constrItem.getSupervision_Constr_Id(),
                        constrItem.getCatProgressWorkId());

                task.execute();
                break;
		/* Chuyen form thiet lap laoi tuyen cong trinh */
            case OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_TYPE_OF_LINE:
                Constr_Construction_EmployeeEntity constr_ConstructionTypeOfLineItem = (Constr_Construction_EmployeeEntity) data;
                Bundle bundleData = new Bundle();
                bundleData.putSerializable(IntentConstants.INTENT_DATA,
                        constr_ConstructionTypeOfLineItem);
                this.gotoSetLineActivity(bundleData);
//			this.finish();
                break;
            case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
                //KienPV add new 10/09/2016
                findValue();
                //--
                DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
                String typeItem = dropdownItem.getType();
                if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_PROGRESS)) {
                    this.iProgress = dropdownItem.getId();
                    this.rl_constr_construction_search_lct_tv_td
                            .setText(dropdownItem.getTitle());
                    this.dropdownPopupMenuProgress.dismiss();
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_TYPE)) {
                    this.iDesignType = dropdownItem.getId();
                    hideDesignType();
                    this.rl_constr_construction_search_lct_tv_lct
                            .setText(dropdownItem.getTitle());
                    this.dropdownPopupMenuDesignType.dismiss();
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_STATUS)) {
                    this.iStatus = dropdownItem.getId();
                    this.rl_constr_construction_search_lct_tv_tt
                            .setText(dropdownItem.getTitle());
                    this.dropdownPopupMenuStatus.dismiss();
                }
                break;
            default:
                super.onEvent(eventType, control, data);
                break;
        }
    }

    private void saveData(long idSupv, int iProgress) {
        try {
            Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
                    this);
            constr_Controller.updateProgressAndDay(idSupv, iProgress);
        } catch (Exception e) {
            Log.e("error", e.toString());
            this.showDialog(StringUtil.getString(R.string.text_update_error));
        }
    }

    class SaveAsyncTask extends AsyncTask<Void, Void, Void> {
        private long idSupv;
        private int iProgress;

        public SaveAsyncTask(long idSupv, int iProgress) {
            super();
            this.idSupv = idSupv;
            this.iProgress = iProgress;
        }

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            showProgressDialog(StringUtil.getString(R.string.text_progcessing));
        }

        @Override
        protected Void doInBackground(Void... params) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    saveData(idSupv, iProgress);

                }
            });

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            try {
                // initData();
                // new loadMore().execute();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            initData();
            new loadMore().execute();
            closeProgressDialog();
        }

    }

    private void initData() {
        this.listData = new ArrayList<Constr_Construction_EmployeeEntity>();
        this.itemLoaded = 0;
        constr_ConstructionAdapter = new Constr_ConstructionAdapter(this,
                listData);
        lv_constr_construction_list.setAdapter(constr_ConstructionAdapter);
    }

    private void getData(int itemLoaded) {
        String sCode = trl_constr_construction_search_row1_edt_mct.getText()
                .toString();
        if (iDesignType == Constants.CONSTR_TYPE.BTS
                || iDesignType == Constants.CONSTR_TYPE.BRCD
                || iDesignType == Constants.CONSTR_TYPE.SH) {
            stationCode = trl_constr_construction_search_row3_edt_matram
                    .getText().toString();
        } else {
            stationCode = trl_constr_construction_search_row3_edt_matuyen
                    .getText().toString();
        }
        startPointCode = trl_constr_construction_search_row4_edt_diemdau
                .getText().toString();
        endPointCode = trl_constr_construction_search_row4_edt_diemcuoi
                .getText().toString();
        List<Constr_Construction_EmployeeEntity> curListData = new Constr_ConstructionController(
                this).seachConstr_ConstructionByUser(GlobalInfo.getInstance()
                        .getUserId(), sCode, this.iStatus, this.iDesignType,
                this.iProgress, stationCode, startPointCode, endPointCode,
                this.itemLoaded, this.itemPerLoad);
//        for (Constr_Construction_EmployeeEntity entity : curListData) {
//            Log.e(TAG, "getData: " + entity.getConstrCode() );
//        }
        // JSONArray jsonScData = SqlliteSyncModel.getDataJsonSyncTest(
        // Supervision_ConstrField.TABLE_NAME,
        // Supervision_ConstrController.allColumn, null, 0,
        // Constants.NUMBER_MAX_ITEM_SYNC);

        this.listData.addAll(curListData);
        Log.d("vietpc", "currentPosition" + currentPosition);
        lv_constr_construction_list.setSelectionFromTop(currentPosition, 0);
        constr_ConstructionAdapter.notifyDataSetChanged();
    }

    /* phan hien thi phan trang */
    OnScrollListener Scroll = new OnScrollListener() {

        public void onScrollStateChanged(AbsListView view, int scrollState) {
            // TODO Auto-generated method stub
        }

        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (view.getLastVisiblePosition() == totalItemCount - 1) {
                if (bLoaded && !bEndList && !bFirst) {
                    new loadMore().execute();
                }
            }
        }
    };
    private int currentPosition = 0; // vi tri hien tai

    private class loadMore extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            showProgressDialog(StringUtil.getString(R.string.text_loading));
            bLoaded = false;
        }

        @Override
        protected Void doInBackground(Void... params) {
            runOnUiThread(new Runnable() {

                public void run() {
                    currentPosition = lv_constr_construction_list
                            .getFirstVisiblePosition();
                    getData(itemLoaded);
                }
            });

            return null;
        }

        protected void onPostExecute(Void unused) {
            // Xac dinh ket thuc list chua
            itemLoaded = itemLoaded + itemPerLoad;
            if (listData.size() < itemLoaded) {
                bEndList = true;
            }
            // Thay doi khong phai lan dau lay du lieu
            if (bFirst) {
                bFirst = false;
            }
            bLoaded = true;
            closeProgressDialog();
        }
    }

    private static final String TAG = "HomeActivity";
    @Override
    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        Log.e(TAG, "handleModelViewEvent: action " + e.action  );
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:
                // SyncModel.bRunning = false;

                SyncTask.closeDialog();

                this.itemLoaded = 0;
                this.bEndList = false;
                this.listData.clear();
                this.constr_ConstructionAdapter.notifyDataSetChanged();
                new loadMore().execute();
                Toast.makeText(this,
                        StringUtil.getString(R.string.text_sync_success),
                        Toast.LENGTH_LONG).show();
                break;
            default:
                super.handleModelViewEvent(modelEvent);
                break;
        }
//		closeProgressDialog();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_collapse:
                int iVisit = tl_constr_construction_search.getVisibility();
                if (iVisit == View.GONE) {
                    tl_constr_construction_search.setVisibility(View.VISIBLE);
                    item.setIcon(R.drawable.icon_up);
                } else {
                    tl_constr_construction_search.setVisibility(View.GONE);
                    item.setIcon(R.drawable.icon_down);
                }
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GlobalInfo.getInstance().setCheckLogin(false);
    }

}
