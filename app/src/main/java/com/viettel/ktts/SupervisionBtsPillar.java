package com.viettel.ktts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Contruction_AcceptancePopup;
import com.viettel.actionbar.Contruction_UnqualifyPopup;
import com.viettel.actionbar.Edit_Text_Popup;
import com.viettel.actionbar.Header;
import com.viettel.actionbar.Image_ViewGalleryPopup;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Cat_Cause_Constr_AcceptanceController;
import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Cause_Bts_Pillar_AntenController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_Bts_Pillar_AntenController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Acceptance_Bts_PillarEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Acceptance_Bts_PillarField;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Pillar_AntenField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_Pillar_AntenField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.control.Bts_PillarAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsPillar extends SupervisionBtsBaseActivity implements OnTabChangeListener {

    private static final String TAG = SupervisionBtsPillar.class.getSimpleName();
    private View spvBTS_PillarView;
    /**
     * textview
     */
    private TextView supervision_bts_tv_matram;
    private TextView supervision_bts_tv_mact;

    /**
     * edittext
     */
    // dien giai lap dung cot
    private EditText supervision_bts_pillar_et_diengiai_ldc;
    // dien giai thi cong tiep dia
    private EditText supervision_bts_pillar_et_diengiai_tctd;

    /**
     * combobox
     */
    // combobox loai thong tin
    private TextView supervision_bts_pillar_tv_thietke;
    private TextView supervision_bts_pillar_notification_tv;

    // de thong bao khi thiet ke cot anten la co san
    private RelativeLayout supervision_bts_pillar_notification;

    // combobox danh gia chat luong cot
    private RelativeLayout supervision_bts_pillar_cb_chondgclc;
    private TextView supervision_bts_pillar_tv_chondgclc;

    // combobox danh gia chat luong lap dung cot
    private RelativeLayout supervision_bts_pillar_cb_dgclldc;
    private TextView supervision_bts_pillar_tv_dgclldc;

    private TextView supervision_bts_complete_date;

    // combobox nguyen nhan khong dat lap dung cot
    private RelativeLayout supervision_bts_pillar_cb_nnkd_ldc;
    private TextView supervision_bts_pillar_tv_nnkd_ldc;

    // combobox trang thai
    private RelativeLayout supervision_bts_pillar_cb_trangthai;
    private TextView supervision_bts_pillar_tv_trangthai;

    // combobox nguyen nhan khong dat thi cong tiep dia
    private RelativeLayout supervision_bts_pillar_cb_nnkd_tctd;
    private TextView supervision_bts_pillar_tv_nnkd_tctd;

    private LinearLayout ll_supervision_bts_pillar_thicong;

    /**
     * button luu
     */
    private Button save;

    /**
     * button hoan thanh (cap nhat ngay hoan thanh cho hang muc giam sat)
     */
    private Button rl_supervision_bts_bt_complete;
    // private Button add;
    // private Button delete;

    private Button supervision_bts_pillar_ib_vitri;

    private Edit_Text_Popup editTextPopup;
    private TabHost tabs;

	/* Hien thi popup */
    // private Point pNnkd_ldc;
    // private Point pNnkd_tctd;

    private Menu_DropdownPopup dropdownPopupMenuInfomation;
    private Menu_DropdownPopup dropdownPopupMenuDgclc;
    private Menu_DropdownPopup dropdownPopupMenuDgclldc;
    // private Menu_DropdownPopup dropdownPopupMenuNnkd_ldc;
    private Menu_DropdownPopup dropdownPopupMenuStatus;
    // private Menu_DropdownPopup dropdownPopupMenuNnkd_tctd;
//	private Image_ViewGalleryPopup imgViewPopup;

    private Contruction_UnqualifyPopup contructionUnqualifyPopup;

    private List<DropdownItemEntity> listInfomation = null;
    private List<DropdownItemEntity> listDgclc = null;
    private List<DropdownItemEntity> listDgclldc = null;
    // private final List<DropdownItemEntity> listNnkd_ldc = null;
    private List<DropdownItemEntity> listStatus = null;
    // private final List<DropdownItemEntity> listNnkd_tctd = null;

    private int isInfomation;
    private int isDgclc = -1;
    private int isDgclldc = -1;

    private int countNnkdCheckLdc;
    private int countNnkdCheckTctd;
    // private final int isNnkd_ldc = 0;
    private int isStatus = -1;
    // private final int isNnkd_tctd = 0;
    private ListView listview;
    private boolean outOfKey = false;
    private String sChoice;

    float cordinate_X;
    float cordinate_Y;

    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Cause_Bts_Cat_WorkController causeCatWorkController;
    private Supervision_BtsEntity btsEntity;

    private List<Cause_Bts_Pillar_AntenEntity> listData;
    /*
     * Danh sach nguyen nhan khong dat cua mong Mo da chuyen doi de hien thi
	 * item
	 */
    private List<Supervision_LBG_UnqualifyItemEntity> listPillarAntenUnqualifyItem;
    /*
	 * Danh sach nguyen nhan khong dat cua mong Mx (x # 0) da chuyen doi de hien
	 * thi item
	 */
    private List<Supervision_LBG_UnqualifyItemEntity> listPillarAntenUnqualifyItemDif;
    private Cause_Bts_Pillar_AntenEntity curEditItem = null;
    private Bts_PillarAdapter bts_PillarAdapter;
    private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;

    private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkItemLdc;
    private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkItemTctd;

    private Supv_Constr_Attach_FileController supvConstrAttachFileController;
    private Cause_Bts_Cat_WorkEntity curEditItemTctd = null;

    private Cause_Bts_Cat_WorkEntity curEditItemLdc = null;
    private EditText txtCopyPaste = null;

    private double longitute = Constants.ID_DOUBLE_ENTITY_DEFAULT;
    private double latitute = Constants.ID_DOUBLE_ENTITY_DEFAULT;

    private boolean choicePillarAnten = false;
    private boolean choicePillarAntenDf = false;
    private boolean choiceLdc = false;
    private boolean choiceTctd = false;
    private int tabHeight = 40;
    private ConfirmDialog confirmSave;
    private Cause_Bts_Pillar_AntenController btsPillarAntenController;
    private int iField = 0;

    private List<Supervision_LBG_UnqualifyItemEntity> listPillarAntenAcceptanceItem;
    private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
    private Contruction_AcceptancePopup contruoctionAcceptancePopup;

    private List<Supervision_LBG_UnqualifyItemEntity> listLdcAcceptanceItem;
    private List<Supervision_LBG_UnqualifyItemEntity> listTctdAcceptanceItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_bts_pillar);
        setTitle(getString(R.string.supervision_bts_title));
        initComponent();
//		setHeader();
//		JSONArray jsonScDataTest = SqlliteSyncModel
//		.getDataJsonSyncTest(Supervision_ProgressField.TABLE_NAME,
//				Supervision_ProgressController.allColumn, null, 0,
//				1000);

        isInfomation = getIntent().getExtras().getInt(
                IntentConstants.INTENT_DESIGNINFO);

        initData();

        constr_ConstructionItem = getConstr_Construction_Employee();

        setData();
        closeProgressDialog();
    }

    public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
        return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);
    }

    public void initComponent() {
        spvBTS_PillarView = addView(R.layout.supervision_bts_pillar, R.id.rl_spv_bts_pillar);
        // textview
        supervision_bts_tv_matram = (TextView) spvBTS_PillarView
                .findViewById(R.id.rl_supervision_bts_tv_matram);
        supervision_bts_tv_mact = (TextView) spvBTS_PillarView
                .findViewById(R.id.rl_supervision_bts_tv_mact);
        supervision_bts_pillar_notification_tv = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_notification_tv);

        supervision_bts_complete_date = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_complete_date);

        // edit text
        supervision_bts_pillar_et_diengiai_ldc = (EditText) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_lapdung_row3_et_diengiai);
        supervision_bts_pillar_et_diengiai_tctd = (EditText) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_thicongtiepdia_row2_et_diengiai);
        registerForContextMenu(supervision_bts_pillar_et_diengiai_ldc);
        registerForContextMenu(supervision_bts_pillar_et_diengiai_tctd);

        // button
        save = (Button) spvBTS_PillarView
                .findViewById(R.id.rl_supervision_bts_pillar_row6_1_bt_save);
        save.setOnClickListener(this);

        rl_supervision_bts_bt_complete = (Button) spvBTS_PillarView
                .findViewById(R.id.rl_supervision_bts_bt_complete);
        rl_supervision_bts_bt_complete.setOnClickListener(this);

        supervision_bts_pillar_ib_vitri = (Button) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_ib_vitri);
        supervision_bts_pillar_ib_vitri.setOnClickListener(this);

        // combobox
        supervision_bts_pillar_tv_thietke = (TextView) spvBTS_PillarView
                .findViewById(R.id.rl_supervision_bts_cat_work_tv_thietke);
        supervision_bts_pillar_tv_thietke.setOnClickListener(this);

        supervision_bts_pillar_notification = (RelativeLayout) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_notification);
        ll_supervision_bts_pillar_thicong = (LinearLayout) spvBTS_PillarView
                .findViewById(R.id.ll_supervision_bts_pillar_thicong);

        // init tabhost
        String tcc = StringUtil
                .getString(R.string.supervision_bts_pillar_tv_thicongcot);
        tabs = (TabHost) spvBTS_PillarView.findViewById(R.id.supervision_bts_pillar_tabhost);
        tabs.setup();
        TabHost.TabSpec specTcc = tabs.newTabSpec(tcc);

        specTcc.setContent(R.id.supervision_bts_pillar_tab1);
        specTcc.setIndicator(makeTabIndicator(tcc));

        String ldc = StringUtil
                .getString(R.string.supervision_bts_pillar_tv_lapdungcot);
        TabHost.TabSpec specLdc = tabs.newTabSpec(ldc);
        specLdc.setContent(R.id.supervision_bts_pillar_tab2);
        specLdc.setIndicator(makeTabIndicator(ldc));

        String tctd = StringUtil
                .getString(R.string.supervision_bts_pillar_tl_lapdung_row3_tv_thicongtiepdia);
        TabHost.TabSpec specTctd = tabs.newTabSpec(tctd);
        specTctd.setContent(R.id.supervision_bts_pillar_tab3);
        specTctd.setIndicator(makeTabIndicator(tctd));

        tabs.addTab(specTcc);
        tabs.addTab(specLdc);
        tabs.addTab(specTctd);
        tabs.setOnTabChangedListener(this);

        supervision_bts_pillar_tv_chondgclc = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_lapdung_row1_chondgclc);
        supervision_bts_pillar_tv_chondgclc.setOnClickListener(this);

        supervision_bts_pillar_tv_dgclldc = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_lapdung_row2_chondgclldc);
        supervision_bts_pillar_tv_dgclldc.setOnClickListener(this);

        supervision_bts_pillar_tv_nnkd_ldc = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_lapdung_row2_chonnnkd);
        supervision_bts_pillar_tv_nnkd_ldc.setOnClickListener(this);

        supervision_bts_pillar_tv_trangthai = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_lapdung_row1_chontrangthai);

        supervision_bts_pillar_tv_trangthai.setOnClickListener(this);

        supervision_bts_pillar_tv_nnkd_tctd = (TextView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_tl_thicongtiepdia_row1_chonnnkd);
        supervision_bts_pillar_tv_nnkd_tctd.setOnClickListener(this);

        listview = (ListView) spvBTS_PillarView
                .findViewById(R.id.supervision_bts_pillar_thicong_lv);
    }

    public void clearFocus() {
        supervision_bts_pillar_et_diengiai_ldc.setFocusableInTouchMode(false);
        supervision_bts_pillar_et_diengiai_ldc.clearFocus();

        supervision_bts_pillar_et_diengiai_tctd.setFocusableInTouchMode(false);
        supervision_bts_pillar_et_diengiai_tctd.clearFocus();

        supervision_bts_pillar_et_diengiai_ldc.setFocusableInTouchMode(true);
        supervision_bts_pillar_et_diengiai_tctd.setFocusableInTouchMode(true);
    }

    private TextView makeTabIndicator(String text) {
        TelephonyManager manager = (TelephonyManager)this
                .getSystemService(Context.TELEPHONY_SERVICE);
        if(manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE){
            Log.d(TAG,"Tablet");
        }else{
            Log.d(TAG,"Mobile");
            tabHeight = 120;
        }
        TextView tabView = new TextView(this);
        LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
                tabHeight, 1);
        lp3.setMargins(1, 0, 1, 0);
        tabView.setLayoutParams(lp3);
        tabView.setText(text);
        tabView.setTextColor(getResources().getColor(R.color.black_color));
        tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        tabView.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.tab_indicator_bts));
        tabView.setPadding(13, 0, 13, 0);
        return tabView;
    }

    public void setData() {

        btsPillarAntenController = new Cause_Bts_Pillar_AntenController(this);

        sChoice = getResources().getString(R.string.text_choice1);
        supervision_bts_tv_matram.setText(constr_ConstructionItem
                .getStationCode());
        supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
                .getConstrCode()));
        Supervision_BtsController bts_Controller = new Supervision_BtsController(
                this);
        btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
                .getSupervision_Constr_Id());

        if (btsEntity.getPillar_ANTEN() == Constants.BTS_POS_PILLAR_ANTEN.CO_SAN) {
            supervision_bts_pillar_notification.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
            ll_supervision_bts_pillar_thicong.setVisibility(View.GONE);
        }

        if (btsEntity.getPillar_ANTEN_TYPE() == Constants.BTS_PILLAR_ANTEN_TYPE.COT_COC) {
            supervision_bts_pillar_notification.setVisibility(View.VISIBLE);
            listview.setVisibility(View.GONE);
            supervision_bts_pillar_notification_tv
                    .setText(getResources()
                            .getString(
                                    R.string.trl_supervision_bts_pillar_notification_tv));
            ll_supervision_bts_pillar_thicong.setVisibility(View.GONE);
        }

        Cat_Cause_Constr_UnQualifyController unqualifyController
                = new Cat_Cause_Constr_UnQualifyController(this);

        supvConstrAttachFileController = new Supv_Constr_Attach_FileController(this);

        refreshThicongcot();

        /**
         * lay du lieu nguyen nhan loi cot
         */
        listPillarAntenUnqualifyItem = unqualifyController
                .getAllUnQualifyItemByName(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_PILLAR,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        listPillarAntenUnqualifyItemDif = unqualifyController
                .getAllUnQualifyItemByName(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_PILLAR_SUB,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        listPillarAntenAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
                this).getAllUnQualifyItemByName(
                Constants.ACCEPTANCE_TYPE.BTS_PILLAR,
                Constants.UNQUALIFY_TYPE.BTS_TYPE);

        // lay du lieu nguyen ngan loi lap dung cot
        listUnqualifyCatWorkItemLdc = unqualifyController
                .getAllUnQualifyItemByName(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_LDCAT,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        listLdcAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
                .getAllUnQualifyItemByName(
                        Constants.ACCEPTANCE_TYPE.BTS_LAP_DUNG_COT,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        listTctdAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
                .getAllUnQualifyItemByName(
                        Constants.ACCEPTANCE_TYPE.BTS_TIEP_DIA,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        // lay du lieu nguyen ngan loi tiep dia
        listUnqualifyCatWorkItemTctd = unqualifyController
                .getAllUnQualifyItemByName(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS,
                        Constants.UNQUALIFY_TYPE.BTS_TYPE);

        if (btsEntity.getPillar_STATUS_QUALITY() >= 0) {
            isDgclc = btsEntity.getPillar_STATUS_QUALITY();
            if (isDgclc < 0) {
                supervision_bts_pillar_tv_chondgclc.setText(sChoice);
            } else
                supervision_bts_pillar_tv_chondgclc.setText(GloablUtils
                        .getStringBtsAssessPillar(isDgclc));
        }

        causeCatWorkController = new Cause_Bts_Cat_WorkController(this);

        refreshLapdungcot();

        refreshTiepdia();

        // neu trang thai cua thiet ke bts cho cot la co san thi khong danh
        // gia hang muc cot
        if (btsEntity.getPillar_ANTEN() == Constants.BTS_POS_PILLAR_ANTEN.CO_SAN) {
            listview.setEnabled(false);
        }
        // neu danh gia chat luong lap dung cot la khong dam bao de lap dung
        // thi khong danh
        // gia hang muc lap dung cot
        if (isDgclc == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
            supervision_bts_pillar_tv_dgclldc.setEnabled(false);
            supervision_bts_pillar_tv_nnkd_ldc.setEnabled(false);
            supervision_bts_pillar_et_diengiai_ldc.setEnabled(false);
        }

        showCompleteDate(constr_ConstructionItem,
                Constants.PROGRESS_TYPE.BTS_TYPE,
                Constants.PROGRESS_TYPE.THI_CONG_COT_BTS,
                supervision_bts_complete_date, rl_supervision_bts_bt_complete);

        if (constr_ConstructionItem.getConstrProgressId() == Constants
                .SUPERVISION_PROGRESS.COMPLETED
                || constr_ConstructionItem.getConstrProgressId() == Constants
                .SUPERVISION_PROGRESS.UNCOMPLETED
                || constr_ConstructionItem.getCatStationTypeId() == Constants
                .STATION_TYPE.TYPE_COSITE) {
            this.save.setVisibility(View.GONE);
//			this.rl_supervision_bts_bt_complete.setVisibility(View.GONE);
        }
        if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
                && btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
            this.save.setVisibility(View.GONE);
//			this.rl_supervision_bts_bt_complete.setVisibility(View.GONE);
        }

    }

    // load lai tab thi cong cot
    public void refreshThicongcot() {
        listData = new ArrayList<Cause_Bts_Pillar_AntenEntity>();

        listData = btsPillarAntenController
                .getListCauseBts_PillarAntenEntity(btsEntity
                        .getSupervision_Bts_Id());

        int found_num_bts = btsEntity.getFoundation_NUM() + 1;

        if (listData.size() != 0) {
            // truong hop da co du lieu
			/* Gan danh sach loi cho danh sach cot */
            for (Cause_Bts_Pillar_AntenEntity curItemSupervison : this.listData) {
                curItemSupervison.setNew(false);

                List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItemSupervison
                        .getListCauseUniQualify();

				/* Gan anh nghiem thu */
                for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curItemSupervison
                        .getListAcceptance()) {
                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                            = this.supvConstrAttachFileController.getLstAttachFile(
                            Acceptance_Bts_PillarField.TABLE_NAME,
                            curUnqualifyItem.getCause_Line_Bg_Id());
                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                        curUnqualifyItem.getLstAttachFile().add(itemFile);
                    }

                }

				/* Gan anh nguyen nhan loi */
                for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {

                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                            = this.supvConstrAttachFileController.getLstAttachFile(
                            Cause_Bts_Pillar_AntenField.TABLE_NAME,
                            curUnqualifyItem.getCause_Line_Bg_Id());
                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                        curUnqualifyItem.getLstAttachFile().add(itemFile);
                    }
                }
                curItemSupervison.setListCauseUniQualify(listUnqualifyItem);
            }

            if (listData.size() < (btsEntity.getFoundation_NUM() + 1)) {
                for (int i = listData.size(); i < btsEntity.getFoundation_NUM() + 1; i++) {
                    Cause_Bts_Pillar_AntenEntity cause_bts_pillarAnten
                            = new Cause_Bts_Pillar_AntenEntity();
                    cause_bts_pillarAnten
                            .getSupervision_Bts_Pillar_AntenEntity()
                            .setFOUNDATION_NAME(GloablUtils.getPillarName(i));
                    cause_bts_pillarAnten
                            .getSupervision_Bts_Pillar_AntenEntity()
                            .setSupervision_BTS_ID(
                                    btsEntity.getSupervision_Bts_Id());
                    cause_bts_pillarAnten
                            .getSupervision_Bts_Pillar_AntenEntity().setStatus(
                            Constants.BTS_ASSESS_PILLAR.DAT);
                    cause_bts_pillarAnten.setNew(true);
                    cause_bts_pillarAnten.setEdited(false);

                    listData.add(cause_bts_pillarAnten);
                }

            }
        } else
            // truong hop chua co du lieu
            for (int i = 0; i < found_num_bts; i++) {
                Cause_Bts_Pillar_AntenEntity cause_bts_pillarAnten
                        = new Cause_Bts_Pillar_AntenEntity();
                cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
                        .setFOUNDATION_NAME(GloablUtils.getPillarName(i));
                cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
                        .setSupervision_BTS_ID(
                                btsEntity.getSupervision_Bts_Id());
                cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
                        .setStatus(-1);
                cause_bts_pillarAnten.setNew(true);
                cause_bts_pillarAnten.setEdited(false);

                listData.add(cause_bts_pillarAnten);
            }

        bts_PillarAdapter = new Bts_PillarAdapter(this, listData);

        listview.setAdapter(bts_PillarAdapter);
    }

    // load lai tab lap dung cot
    public void refreshLapdungcot() {
        // lay thuc the de set du lieu ung voi lap dung cot
        curEditItemLdc = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT,
                btsEntity.getSupervision_Bts_Id());

        countNnkdCheckLdc = 0;

        for (int i = 0; i < this.curEditItemLdc.getListCauseUniQualify().size(); i++) {
            if (!curEditItemLdc.getListCauseUniQualify().get(i).isDelete()) {
                countNnkdCheckLdc++;
                break;
            }
        }

        // set du lieu vao thi cong lap dung cot
        if (curEditItemLdc.getBts_Cat_WorkEntity().getStatus() >= 0) {
            isDgclldc = curEditItemLdc.getBts_Cat_WorkEntity().getStatus();

            if (isDgclldc < 0) {
                supervision_bts_pillar_tv_dgclldc.setText(sChoice);
            } else
                supervision_bts_pillar_tv_dgclldc.setText(GloablUtils
                        .getStringBtsCatWorkStatus(isDgclldc));

            List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curEditItemLdc
                    .getListCauseUniQualify();

			/* Gan anh nghiem thu */
            for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curEditItemLdc
                    .getListAcceptance()) {
                List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                        = this.supvConstrAttachFileController
                        .getLstAttachFile(
                                Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                                curUnqualifyItem.getCause_Line_Bg_Id());
                for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                    curUnqualifyItem.getLstAttachFile().add(itemFile);
                }

            }

			/* Gan anh nguyen nhan loi */
            for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
                List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                        = this.supvConstrAttachFileController
                        .getLstAttachFile(Cause_Bts_Cat_WorkField.TABLE_NAME,
                                curUnqualifyItem.getCause_Line_Bg_Id());
                for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                    curUnqualifyItem.getLstAttachFile().add(itemFile);
                }
            }
            curEditItemLdc.setListCauseUniQualify(listUnqualifyItem);

            int countAcceptCheck = 0;

            for (int i = 0; i < this.curEditItemLdc.getListAcceptance().size(); i++) {
                if (curEditItemLdc.getListAcceptance().get(i)
                        .getLstNewAttachFile().size() > 0
                        || curEditItemLdc.getListAcceptance().get(i)
                        .getLstAttachFile().size() > 0) {
                    countAcceptCheck++;
                    break;
                }
            }

            if (isDgclldc == Constants.TANK_STATUS.DAT) {
                if (countAcceptCheck > 0) {
                    supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                            .getString(R.string.text_view_dot));
                } else {
                    supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                            .getString(R.string.text_empty));
                }
            } else {
                if (curEditItemLdc.getListCauseUniQualify().size() > 0) {
                    supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                            .getString(R.string.text_view_dot));
                } else
                    supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                            .getString(R.string.text_empty));
            }

            supervision_bts_pillar_et_diengiai_ldc.setText(curEditItemLdc
                    .getBts_Cat_WorkEntity().getFail_Desc());
        }
    }

    // load lai tab thi cong tiep dia
    public void refreshTiepdia() {

        // lay thuc the de set du lieu ung voi thi cong tiep dia
        curEditItemTctd = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                Constants.BTS_CONSTR_WORK.WORK_CODE_TDBTS,
                btsEntity.getSupervision_Bts_Id());

        countNnkdCheckTctd = 0;

        for (int i = 0; i < this.curEditItemTctd.getListCauseUniQualify()
                .size(); i++) {
            if (!curEditItemTctd.getListCauseUniQualify().get(i).isDelete()) {
                countNnkdCheckTctd++;
                break;
            }
        }

        // set du lieu vao thi cong tiep dia
        if (curEditItemTctd.getBts_Cat_WorkEntity().getStatus() >= 0) {
            isStatus = curEditItemTctd.getBts_Cat_WorkEntity().getStatus();

            if (isStatus < 0) {
                supervision_bts_pillar_tv_trangthai.setText(sChoice);
            } else
                supervision_bts_pillar_tv_trangthai.setText(GloablUtils
                        .getStringBtsCatWorkStatus(isStatus));

            List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curEditItemTctd
                    .getListCauseUniQualify();

			/* Gan anh nghiem thu */
            for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curEditItemTctd
                    .getListAcceptance()) {
                List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                        = this.supvConstrAttachFileController.getLstAttachFile(
                        Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                        curUnqualifyItem.getCause_Line_Bg_Id());
                for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                    curUnqualifyItem.getLstAttachFile().add(itemFile);
                }

            }

			/* Gan anh nguyen nhan loi */
            for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
                List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                        = this.supvConstrAttachFileController.getLstAttachFile(
                        Cause_Bts_Cat_WorkField.TABLE_NAME,
                        curUnqualifyItem.getCause_Line_Bg_Id());
                for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                    curUnqualifyItem.getLstAttachFile().add(itemFile);
                }
            }
            curEditItemTctd.setListCauseUniQualify(listUnqualifyItem);

            int countAcceptCheck = 0;

            for (int i = 0; i < this.curEditItemTctd.getListAcceptance().size(); i++) {
                if (curEditItemTctd.getListAcceptance().get(i)
                        .getLstNewAttachFile().size() > 0
                        || curEditItemTctd.getListAcceptance().get(i)
                        .getLstAttachFile().size() > 0) {
                    countAcceptCheck++;
                    break;
                }
            }

            if (isStatus == Constants.TANK_STATUS.DAT) {
                if (countAcceptCheck > 0) {
                    supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                            .getString(R.string.text_view_dot));
                } else {
                    supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                            .getString(R.string.text_empty));
                }
            } else {
                if (curEditItemTctd.getListCauseUniQualify().size() > 0) {
                    supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                            .getString(R.string.text_view_dot));
                } else
                    supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                            .getString(R.string.text_empty));
            }

            supervision_bts_pillar_et_diengiai_tctd.setText(curEditItemTctd
                    .getBts_Cat_WorkEntity().getFail_Desc());
        }
    }

    public void initData() {

        this.curEditItemLdc = new Cause_Bts_Cat_WorkEntity();
        this.curEditItemTctd = new Cause_Bts_Cat_WorkEntity();

		/* Drop down */
        String sChoice = "";
        listInfomation = GloablUtils.getListBTSInfo(sChoice);
        sChoice = getResources().getString(R.string.text_choice1);
        supervision_bts_pillar_tv_thietke.setText(GloablUtils
                .getStringBTSInfo(isInfomation));

        listDgclc = GloablUtils.getListBtsAssessPillar(sChoice);
        supervision_bts_pillar_tv_chondgclc.setText(GloablUtils
                .getStringBtsAssessPillar(isDgclc));
        supervision_bts_pillar_tv_chondgclc.setText(sChoice);

        listDgclldc = GloablUtils.getListBtsAssessBuildPillar(sChoice);
        supervision_bts_pillar_tv_dgclldc.setText(GloablUtils
                .getStringBtsAssessPillar(isDgclldc));
        supervision_bts_pillar_tv_dgclldc.setText(sChoice);

        listStatus = GloablUtils.getListBtsStatusPillar(sChoice);

        supervision_bts_pillar_tv_trangthai.setText(sChoice);
    }

    // tao thuc the bts cat work de insert vao co so du lieu ung voi danh sach
    // nguyen nhan loi ung voi thi cong lap dung cot
    public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkLdcEntity() {
        Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
        result.setStatus(isDgclldc);
        result.setFail_Desc(supervision_bts_pillar_et_diengiai_ldc.getText()
                .toString());
        // result.setListConstrUnqualifyEntity(list);
        return result;
    }

    // tao thuc the bts cat work de insert vao co so du lieu ung voi danh sach
    // nguyen nhan loi ung voi thi cong tiep dia
    public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkTdEntity() {
        Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
        result.setStatus(isStatus);
        result.setFail_Desc(supervision_bts_pillar_et_diengiai_tctd.getText()
                .toString());
        // result.setListConstrUnqualifyEntity(list);
        return result;
    }

    public Supervision_BtsEntity getSupervisionBtsEntity() {
        Supervision_BtsEntity result = new Supervision_BtsEntity();
        result.setSupervision_Bts_Id(btsEntity.getSupervision_Bts_Id());
        result.setPillar_STATUS_QUALITY(isDgclc);
        result.setPillar_LONGITUDE((double) longitute);
        result.setPillar_LATITUDE((double) latitute);
        return result;
    }

    private String checkVailid(Cause_Bts_Pillar_AntenEntity itemCheck) {
        String sReslut = "";
        try {
            int countNnkdCheck = 0;

            for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
                if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
                    countNnkdCheck++;
                    break;
                }
            }
            if ((itemCheck.getSupervision_Bts_Pillar_AntenEntity().getStatus() >= 0)
                    || (itemCheck.getSupervision_Bts_Pillar_AntenEntity()
                    .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT
                    && countNnkdCheck < 1)) {
                if (itemCheck.getSupervision_Bts_Pillar_AntenEntity()
                        .getStatus() < 0) {
                    sReslut = StringUtil
                            .getString(R.string.constr_pillar_anten_danhgia_empty);
                    sReslut += itemCheck
                            .getSupervision_Bts_Pillar_AntenEntity()
                            .getFOUNDATION_NAME();
                } else if (itemCheck.getSupervision_Bts_Pillar_AntenEntity()
                        .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT
                        && countNnkdCheck < 1) {
                    sReslut = StringUtil
                            .getString(R.string.constr_pillar_anten_nn_khongdat_tempty);
                    sReslut += itemCheck
                            .getSupervision_Bts_Pillar_AntenEntity()
                            .getFOUNDATION_NAME();
                }
            }

            int countAcceptCheck = 0;

            for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
                if ((itemCheck.getListAcceptance().get(i).getLstNewAttachFile()
                        .size() > 0 || itemCheck.getListAcceptance().get(i)
                        .getLstAttachFile().size() > 0)
                        && (i == 0)) {
                    countAcceptCheck++;
                }
            }
            if (itemCheck.getSupervision_Bts_Pillar_AntenEntity().getStatus()
                    == Constants.TANK_STATUS.DAT && countAcceptCheck < 1) {
                sReslut = StringUtil
                        .getString(R.string.constr_take_acceptance_not_enough);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sReslut;
    }

    private String checkVailidLdc(Cause_Bts_Cat_WorkEntity itemCheck) {
        String sReslut = "";
        try {
            if (isDgclc > 0) {
                if (isDgclldc < 0) {
                    sReslut = StringUtil
                            .getString(R.string.constr_pillar_anten_danhgia_ldc_empty);
                    tabs.setCurrentTab(1);
                    supervision_bts_pillar_tv_dgclldc.setError(Html
                            .fromHtml("<font color='green'>"
                                    + getString(R.string.field_is_null)
                                    + "</font>"));
                    Toast.makeText(getApplicationContext(), sReslut,
                            Toast.LENGTH_LONG).show();

                    clearFocus();
                } else if (isDgclldc == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
                        && countNnkdCheckLdc < 1) {
                    tabs.setCurrentTab(1);
                    sReslut = StringUtil
                            .getString(R.string.constr_pillar_anten_nn_khongdat_ldc_tempty);
                    Toast.makeText(getApplicationContext(), sReslut,
                            Toast.LENGTH_LONG).show();

                    clearFocus();
                }

                int countAcceptCheck = 0;

                for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
                    if ((itemCheck.getListAcceptance().get(i)
                            .getLstNewAttachFile().size() > 0 || itemCheck
                            .getListAcceptance().get(i).getLstAttachFile()
                            .size() > 0)
                            && (i == 0)) {
                        countAcceptCheck++;
                    }
                }
                if (isDgclldc == Constants.TANK_STATUS.DAT
                        && countAcceptCheck < 1) {
                    sReslut = StringUtil
                            .getString(R.string.constr_take_acceptance_not_enough);
                    Toast.makeText(getApplicationContext(), sReslut,
                            Toast.LENGTH_LONG).show();

                    clearFocus();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sReslut;
    }

    private String checkVailidTctd(Cause_Bts_Cat_WorkEntity itemCheck) {
        String sReslut = "";
        try {
            if (isStatus < 0) {
                sReslut = StringUtil
                        .getString(R.string.constr_pillar_anten_danhgia_tctd_empty);
                tabs.setCurrentTab(2);
                supervision_bts_pillar_tv_trangthai
                        .setError(Html
                                .fromHtml("<font color='green'>"
                                        + getString(R.string.field_is_null)
                                        + "</font>"));
                Toast.makeText(getApplicationContext(), sReslut,
                        Toast.LENGTH_LONG).show();

                clearFocus();

            } else if (isStatus == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
                    && countNnkdCheckTctd < 1) {
                tabs.setCurrentTab(2);
                sReslut = StringUtil
                        .getString(R.string.constr_pillar_anten_nn_khongdat_tctd_tempty);
                Toast.makeText(getApplicationContext(), sReslut,
                        Toast.LENGTH_LONG).show();

                clearFocus();
            } else if (isStatus == Constants.BTS_CAT_WORK_STATUS.DAT) {
                int countAcceptCheck = 0;

                for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
                    if ((itemCheck.getListAcceptance().get(i)
                            .getLstNewAttachFile().size() > 0 || itemCheck
                            .getListAcceptance().get(i).getLstAttachFile()
                            .size() > 0)
                            && (i == 0)) {
                        countAcceptCheck++;
                    }
                }
                if (countAcceptCheck < 1) {
                    sReslut = StringUtil
                            .getString(R.string.constr_take_acceptance_not_enough);
                    Toast.makeText(getApplicationContext(), sReslut,
                            Toast.LENGTH_LONG).show();

                    clearFocus();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sReslut;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // click hoan thanh
            case R.id.rl_supervision_bts_bt_complete:
                if (!GlobalInfo.getInstance().isCheckIn()) {
                    showAlertDialogCheckInRequire(this, getString(R.string.checkin_require),
                            getString(R.string.text_close));
                    break;
                }
                confirmSave = new ConfirmDialog(this,
                        StringUtil
                                .getString(R.string.text_confirm_save),
                        OnEventControlListener.EVENT_COMPLETE_PROGRESS);
                confirmSave.show();
                break;
            // click save
            case R.id.rl_supervision_bts_pillar_row6_1_bt_save:
                if (!GlobalInfo.getInstance().isCheckIn()) {
                    showAlertDialogCheckInRequire(this, getString(R.string.checkin_require),
                            getString(R.string.text_close));
                    break;
                }
                // neu cot anten trong thiet ke bts la co san thi khong danh gia
                // hang muc cot, co san co gia tri la 3
                String messageError = "";
                if (btsEntity.getPillar_LONGITUDE() == -1
                        && btsEntity.getPillar_LATITUDE() == -1) {
                    this.showDialog(StringUtil
                            .getString(R.string.constr_pillar_anten_vitri_chuachot));
                    return;
                }
                // neu la tab thi cong cot
                if (tabs.getCurrentTab() == 0) {
                    for (Cause_Bts_Pillar_AntenEntity curItemData : listData) {
                        messageError = this.checkVailid(curItemData);
                        if (!StringUtil.isNullOrEmpty(messageError)) {
                            break;
                        }
                    }
                    if (!StringUtil.isNullOrEmpty(messageError)) {
                        this.showDialog(messageError);
                    } else {
                        confirmSave = new ConfirmDialog(this,
                                StringUtil.getString(R.string.text_confirm_save));
                        confirmSave.show();

                    }
                } else {
                    // neu la tab lap dung cot anten va thang cap ngoai troi
                    if (tabs.getCurrentTab() == 1) {
                        if (isDgclc < 0) {
                            supervision_bts_pillar_tv_chondgclc.setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                            Toast.makeText(
                                    getApplicationContext(),
                                    StringUtil
                                            .getString(R.string.constr_pillar_anten_dtclc_tempty),
                                    Toast.LENGTH_LONG).show();

                            clearFocus();

                        } else if (StringUtil.isNullOrEmpty(this
                                .checkVailidLdc(curEditItemLdc))) {
                            confirmSave = new ConfirmDialog(this,
                                    StringUtil
                                            .getString(R.string.text_confirm_save));
                            confirmSave.show();
                        }
                    } else {
                        // neu la tab thi cong tiep dia
                        if (tabs.getCurrentTab() == 2) {
                            if (StringUtil.isNullOrEmpty(this
                                    .checkVailidTctd(curEditItemTctd))) {
                                confirmSave = new ConfirmDialog(
                                        this,
                                        StringUtil
                                                .getString(R.string.text_confirm_save));
                                confirmSave.show();
                            }
                        }
                    }
                }

                break;

            // click vitri
            case R.id.supervision_bts_pillar_ib_vitri:
                Bundle data = new Bundle();
                // data.putSerializable(IntentConstants.INTENT_DATA,
                // constr_ConstructionEmployeeItem);
                Log.d(TAG, "Pillar Long = " + btsEntity.getPillar_LONGITUDE());
                Log.d(TAG, "Pillar Lat = " + btsEntity.getPillar_LATITUDE());
                data.putDouble(IntentConstants.INTENT_LONG,
                        btsEntity.getPillar_LONGITUDE());
                data.putDouble(IntentConstants.INTENT_LAT,
                        btsEntity.getPillar_LATITUDE());

                Intent intent = new Intent(SupervisionBtsPillar.this,
                        GoogleMapActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.GoogleMapActivity"));
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(data);

                startActivityForResult(intent, Constants.LOCATION_RESULT);
                // gotoSupervisionBtsPillarPosition(data);
                break;
            // click combobox thong tin
            case R.id.rl_supervision_bts_cat_work_tv_thietke:

                this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
                        this.listInfomation);

                dropdownPopupMenuInfomation.show(v);
                break;

            // click combobox danh gia chat luong cot
            case R.id.supervision_bts_pillar_tl_lapdung_row1_chondgclc:

                this.dropdownPopupMenuDgclc = new Menu_DropdownPopup(this,
                        this.listDgclc);
                dropdownPopupMenuDgclc.show(v);
                break;

            // click combobox danh gia chat luong lap dung cot
            case R.id.supervision_bts_pillar_tl_lapdung_row2_chondgclldc:

                this.dropdownPopupMenuDgclldc = new Menu_DropdownPopup(this,
                        this.listDgclldc);
                dropdownPopupMenuDgclldc.show(v);
                break;
            // click combobox nguyen nhan khong dat lap dung cot
            case R.id.supervision_bts_pillar_tl_lapdung_row2_chonnnkd:
                // this.curEditItemLdc = new Cause_Bts_Cat_WorkEntity();
                choiceLdc = true;
                choiceTctd = false;
                choicePillarAnten = false;
                if (isDgclldc == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                    setUnqualifyLdc();
                    // this.setUnqualifyCurTankItem(curlistPillarAntenUnqualifyItem);
                    contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
                            this, null, this.listUnqualifyCatWorkItemLdc);
                    contructionUnqualifyPopup.showAtCenter();
                } else if (isDgclldc == Constants.TANK_STATUS.DAT) {
                    this.setLdcAcceptance();
                    contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
                            this, null, this.listLdcAcceptanceItem);

                    contruoctionAcceptancePopup.showAtCenter();

                } else {
                    Toast.makeText(
                            this,
                            StringUtil
                                    .getString(R.string.constr_line_tank_unqualify_choice_message),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            // click combobox trang thai
            case R.id.supervision_bts_pillar_tl_lapdung_row1_chontrangthai:

                this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this,
                        this.listStatus);
                dropdownPopupMenuStatus.show(v);
                break;
            // click combobox nguyen nhan khong dat thi cong tiep dia
            case R.id.supervision_bts_pillar_tl_thicongtiepdia_row1_chonnnkd:
                choiceLdc = false;
                choiceTctd = true;
                choicePillarAnten = false;
                if (isStatus == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                    setUnqualifyTctd();
                    // this.setUnqualifyCurTankItem(curlistPillarAntenUnqualifyItem);
                    contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
                            this, null, this.listUnqualifyCatWorkItemTctd);
                    contructionUnqualifyPopup.showAtCenter();
                } else if (isStatus == Constants.TANK_STATUS.DAT) {
                    this.setTctdAcceptance();
                    contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
                            this, null, this.listTctdAcceptanceItem);

                    contruoctionAcceptancePopup.showAtCenter();

                } else {
                    Toast.makeText(
                            this,
                            StringUtil
                                    .getString(R.string.constr_line_tank_unqualify_choice_message),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    public boolean checkNewFound() {
        boolean ok = false;
        if ((listData.size() - 1) != btsEntity.getFoundation_NUM()) {
            for (Cause_Bts_Pillar_AntenEntity curItemSupervison : this.listData) {
                if (curItemSupervison.isNew()) {
                    ok = true;
                }
            }
        }

        return ok;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onEvent(int eventType, View control, Object data) {
        switch (eventType) {
            case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
                DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
                String typeItem = dropdownItem.getType();
                Drawable ic_combo = getResources().getDrawable(
                        R.drawable.icon_combo);
                ic_combo.setBounds(0, 0, ic_combo.getIntrinsicWidth(),
                        ic_combo.getIntrinsicHeight());

                if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
                    isInfomation = dropdownItem.getId();

                    Bundle bundleMonitorData = new Bundle();
                    bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
                            constr_ConstructionItem);
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            isInfomation);

                    this.supervision_bts_pillar_tv_thietke.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuInfomation.dismiss();

                    this.showProgressDialog(StringUtil
                            .getString(R.string.text_loading));

                    gotoBtsActivity(bundleMonitorData, isInfomation);

                    finish();
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.ASSESS_PILLAR)) {
                    isDgclc = dropdownItem.getId();
                    if (this.isDgclc >= 0) {
                        supervision_bts_pillar_tv_chondgclc
                                .setError(null, ic_combo);

                        if (this.isDgclc == 0) {
                            supervision_bts_pillar_tv_dgclldc.setError(null,
                                    ic_combo);
                        }
                    }

                    this.supervision_bts_pillar_tv_chondgclc.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuDgclc.dismiss();

                    if (isDgclc == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                        supervision_bts_pillar_tv_dgclldc.setEnabled(false);
                        supervision_bts_pillar_tv_nnkd_ldc.setEnabled(false);
                        supervision_bts_pillar_et_diengiai_ldc.setEnabled(false);
                    } else {
                        supervision_bts_pillar_tv_dgclldc.setEnabled(true);
                        supervision_bts_pillar_tv_nnkd_ldc.setEnabled(true);
                        supervision_bts_pillar_et_diengiai_ldc.setEnabled(true);
                    }
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.ASSESS_BUILD_PILLAR)) {
                    isDgclldc = dropdownItem.getId();
                    if (this.isDgclldc >= 0) {
                        supervision_bts_pillar_tv_dgclldc.setError(null, ic_combo);

                        if (isDgclldc == 0) {
                            countNnkdCheckLdc = 0;

                            for (int i = 0; i < this.curEditItemLdc
                                    .getListCauseUniQualify().size(); i++) {
                                if (!curEditItemLdc.getListCauseUniQualify().get(i)
                                        .isDelete()) {
                                    countNnkdCheckLdc++;
                                    break;
                                }
                            }

                            if (countNnkdCheckLdc > 0) {
                                supervision_bts_pillar_tv_nnkd_ldc
                                        .setText(StringUtil
                                                .getString(R.string.text_view_dot));
                            } else
                                supervision_bts_pillar_tv_nnkd_ldc
                                        .setText(StringUtil
                                                .getString(R.string.text_empty));
                        } else {
                            countNnkdCheckLdc = 0;

                            for (int i = 0; i < this.curEditItemLdc
                                    .getListAcceptance().size(); i++) {
                                if (this.curEditItemLdc.getListAcceptance().get(i)
                                        .getLstNewAttachFile().size() > 0
                                        || this.curEditItemLdc.getListAcceptance()
                                        .get(i).getLstAttachFile().size() > 0) {
                                    countNnkdCheckLdc++;
                                    break;
                                }
                            }

                            if (countNnkdCheckLdc > 0) {
                                supervision_bts_pillar_tv_nnkd_ldc
                                        .setText(StringUtil
                                                .getString(R.string.text_view_dot));
                            } else
                                supervision_bts_pillar_tv_nnkd_ldc
                                        .setText(StringUtil
                                                .getString(R.string.text_empty));
                        }
                    }

                    this.supervision_bts_pillar_tv_dgclldc.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuDgclldc.dismiss();
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.STATUS_PILLAR)) {
                    isStatus = dropdownItem.getId();
                    if (this.isStatus >= 0) {
                        supervision_bts_pillar_tv_trangthai
                                .setError(null, ic_combo);
                        if (isStatus == 0) {
                            countNnkdCheckTctd = 0;

                            for (int i = 0; i < this.curEditItemTctd
                                    .getListCauseUniQualify().size(); i++) {
                                if (!curEditItemTctd.getListCauseUniQualify()
                                        .get(i).isDelete()) {
                                    countNnkdCheckTctd++;
                                    break;
                                }
                            }

                            if (countNnkdCheckTctd > 0) {
                                supervision_bts_pillar_tv_nnkd_tctd
                                        .setText(StringUtil
                                                .getString(R.string.text_view_dot));
                            } else
                                supervision_bts_pillar_tv_nnkd_tctd
                                        .setText(StringUtil
                                                .getString(R.string.text_empty));
                        } else {
                            countNnkdCheckTctd = 0;

                            for (int i = 0; i < this.curEditItemTctd
                                    .getListAcceptance().size(); i++) {
                                if (this.curEditItemTctd.getListAcceptance().get(i)
                                        .getLstNewAttachFile().size() > 0
                                        || this.curEditItemTctd.getListAcceptance()
                                        .get(i).getLstAttachFile().size() > 0) {
                                    countNnkdCheckTctd++;
                                    break;
                                }
                            }

                            if (countNnkdCheckTctd > 0) {
                                supervision_bts_pillar_tv_nnkd_tctd
                                        .setText(StringUtil
                                                .getString(R.string.text_view_dot));
                            } else
                                supervision_bts_pillar_tv_nnkd_tctd
                                        .setText(StringUtil
                                                .getString(R.string.text_empty));
                        }
                    }

                    this.supervision_bts_pillar_tv_trangthai.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuStatus.dismiss();
                }
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                break;

            // chon nguyen nhan khong dat
            case OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY:
                this.curEditItem = (Cause_Bts_Pillar_AntenEntity) data;
                int positionM = listData.indexOf(curEditItem);
                choiceLdc = false;
                choiceTctd = false;
                if (positionM != 0) {
                    choicePillarAnten = false;
                    choicePillarAntenDf = true;
                    if (this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                        setUnqualifyDf();
                        contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
                                this, null, this.listPillarAntenUnqualifyItemDif);
                        contructionUnqualifyPopup.showAtCenter();
                    } else if (this.curEditItem
                            .getSupervision_Bts_Pillar_AntenEntity().getStatus()
                            == Constants.BTS_ASSESS_PILLAR.DAT) {
                        this.setAcceptance();
                        contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
                                this, null, this.listPillarAntenAcceptanceItem);
                        contruoctionAcceptancePopup.showAtCenter();

                    } else {
                        Toast.makeText(
                                this,
                                StringUtil
                                        .getString(R.string.constr_line_tank_unqualify_choice_message),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    choicePillarAnten = true;
                    choicePillarAntenDf = false;
                    if (this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                        setUnqualify();
                        contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
                                this, null, this.listPillarAntenUnqualifyItem);
                        contructionUnqualifyPopup.showAtCenter();
                    } else if (this.curEditItem
                            .getSupervision_Bts_Pillar_AntenEntity().getStatus()
                            == Constants.BTS_ASSESS_PILLAR.DAT) {
                        this.setAcceptance();
                        contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
                                this, null, this.listPillarAntenAcceptanceItem);
                        contruoctionAcceptancePopup.showAtCenter();

                    } else {
                        Toast.makeText(
                                this,
                                StringUtil
                                        .getString(R.string.constr_line_tank_unqualify_choice_message),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            // chon copy paste trong dien giai
            case OnEventControlListener.EVENT_COPY_PASTE:
                this.txtCopyPaste = (EditText) data;
                registerForContextMenu(txtCopyPaste);
                break;
            // chon danh gia la dat
            case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
                this.curEditItem = (Cause_Bts_Pillar_AntenEntity) data;
                this.curEditItem.setEdited(true);
                break;
            // chon danh gia la khong dat
            case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
                this.curEditItem = (Cause_Bts_Pillar_AntenEntity) data;
                this.curEditItem.setEdited(true);
                break;
            // click confirm yes dialog chac chan them du lieu vao database
            case OnEventControlListener.EVENT_CONFIRM_OK:
                SaveAsyncTask task = new SaveAsyncTask();
                task.execute();
                break;
            // click edit text
            case OnEventControlListener.EVENT_SUPERVISION_EDIT:
                this.curEditItem = (Cause_Bts_Pillar_AntenEntity) data;
                this.iField = this.curEditItem.getiField();
                switch (iField) {
                    case Constants.BTS_PILLAR_EDIT.FAIL_DESC:
                        String sFailDescTextValue = this.curEditItem
                                .getSupervision_Bts_Pillar_AntenEntity().getFail_DESC();
                        editTextPopup = new Edit_Text_Popup(this, null,
                                sFailDescTextValue,
                                InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
                        editTextPopup.showAtCenter();
                        break;
                    case Constants.BTS_PILLAR_EDIT.THIET_KE:
                        String sDesignTextValue = this.curEditItem
                                .getSupervision_Bts_Pillar_AntenEntity()
                                .getDimension_Design();
                        Log.d(TAG, "onEvent: Thiet ke - sDesignText = " + sDesignTextValue);
                        editTextPopup = new Edit_Text_Popup(this, null,
                                sDesignTextValue, InputType.TYPE_CLASS_TEXT, true, 100);
                        editTextPopup.showAtCenter();
                        break;
                    case Constants.BTS_PILLAR_EDIT.THUC_TE:
                        String sRealTextValue = this.curEditItem
                                .getSupervision_Bts_Pillar_AntenEntity()
                                .getDimension_Real();
                        editTextPopup = new Edit_Text_Popup(this, null, sRealTextValue,
                                InputType.TYPE_CLASS_TEXT, true, 100);
                        editTextPopup.showAtCenter();
                        break;
                    default:
                        break;
                }
                break;

            case OnEventControlListener.EVENT_SET_TEXT:
                String sSetTextValue = (String) data;

                if (iField == Constants.BTS_PILLAR_EDIT.FAIL_DESC) {
                    this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .setFail_DESC(sSetTextValue);
                } else if (iField == Constants.BTS_PILLAR_EDIT.THIET_KE) {
                    this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .setDimension_Design(sSetTextValue);
                } else if (iField == Constants.BTS_PILLAR_EDIT.THUC_TE) {
                    this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .setDimension_Real(sSetTextValue);
                }
                this.curEditItem.setEdited(true);
                this.bts_PillarAdapter.notifyDataSetChanged();
                editTextPopup.hidePopup();
                break;

		/* Xu ly su kien anh */
            case OnEventControlListener.EVENT_UNQUALIFY_CHECK_UCHECK:
                Supervision_LBG_UnqualifyItemEntity unqualifyItem
                        = (Supervision_LBG_UnqualifyItemEntity) data;
                if (unqualifyItem.isUnqulify()) {
                    unqualifyItem.setDelete(false);
                } else {
                    unqualifyItem.setDelete(true);
                }
                this.contructionUnqualifyPopup.refreshData();
                break;
		/* Dong anh nghiem thu */
            case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
                if (choicePillarAnten || choicePillarAntenDf) {
                    this.saveAcceptance();
                    contruoctionAcceptancePopup.hidePopup();
                    this.bts_PillarAdapter.notifyDataSetChanged();
                    this.curEditItem.setEdited(true);
                }

                if (choiceLdc) {
                    saveLdcAcceptance();
                    countNnkdCheckLdc = 0;

                    for (int i = 0; i < this.curEditItemLdc.getListAcceptance()
                            .size(); i++) {
                        if (this.curEditItemLdc.getListAcceptance().get(i)
                                .getLstNewAttachFile().size() > 0
                                || this.curEditItemLdc.getListAcceptance().get(i)
                                .getLstAttachFile().size() > 0) {
                            countNnkdCheckLdc++;
                            break;
                        }
                    }

                    if (countNnkdCheckLdc > 0) {
                        supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                                .getString(R.string.text_view_dot));
                    } else
                        supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                                .getString(R.string.text_empty));
                }

                if (choiceTctd) {
                    saveTctdAcceptance();
                    countNnkdCheckTctd = 0;

                    for (int i = 0; i < this.curEditItemTctd.getListAcceptance()
                            .size(); i++) {
                        if (this.curEditItemTctd.getListAcceptance().get(i)
                                .getLstNewAttachFile().size() > 0
                                || this.curEditItemTctd.getListAcceptance().get(i)
                                .getLstAttachFile().size() > 0) {
                            countNnkdCheckTctd++;
                            break;
                        }
                    }

                    if (countNnkdCheckTctd > 0) {
                        supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                                .getString(R.string.text_view_dot));
                    } else
                        supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                                .getString(R.string.text_empty));
                }

                contruoctionAcceptancePopup.hidePopup();

                break;
		/* Dong nguyen nhan khong dat */
            case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
                if (choicePillarAnten) {
                    this.saveUnqualify();
                    contructionUnqualifyPopup.hidePopup();
                    this.bts_PillarAdapter.notifyDataSetChanged();
                    this.curEditItem.setEdited(true);
                }

                if (choicePillarAntenDf) {
                    this.saveUnqualifyDif();
                    contructionUnqualifyPopup.hidePopup();
                    this.bts_PillarAdapter.notifyDataSetChanged();
                    this.curEditItem.setEdited(true);
                }

                if (choiceTctd) {
                    this.saveUnqualifyTctd();
                    contructionUnqualifyPopup.hidePopup();
                    countNnkdCheckTctd = 0;

                    for (int i = 0; i < this.curEditItemTctd
                            .getListCauseUniQualify().size(); i++) {
                        if (!curEditItemTctd.getListCauseUniQualify().get(i)
                                .isDelete()) {
                            countNnkdCheckTctd++;
                            break;
                        }
                    }

                    if (countNnkdCheckTctd > 0) {
                        supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                                .getString(R.string.text_view_dot));
                    } else
                        supervision_bts_pillar_tv_nnkd_tctd.setText(StringUtil
                                .getString(R.string.text_empty));
                }

                if (choiceLdc) {
                    this.saveUnqualifyLdc();
                    contructionUnqualifyPopup.hidePopup();
                    countNnkdCheckLdc = 0;

                    for (int i = 0; i < this.curEditItemLdc
                            .getListCauseUniQualify().size(); i++) {
                        if (!curEditItemLdc.getListCauseUniQualify().get(i)
                                .isDelete()) {
                            countNnkdCheckLdc++;
                            break;
                        }
                    }

                    if (countNnkdCheckLdc > 0) {
                        supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                                .getString(R.string.text_view_dot));
                    } else
                        supervision_bts_pillar_tv_nnkd_ldc.setText(StringUtil
                                .getString(R.string.text_empty));
                }

                break;
            case OnEventControlListener.EVENT_ACCEPTANCE_TAKE_PHOTO:
                this.curAcceptanceItem = (Supervision_LBG_UnqualifyItemEntity) data;
                List<ImageEntity> listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */

                // gan anh co san
                for (Supv_Constr_Attach_FileEntity itemAttach : curAcceptanceItem
                        .getLstAttachFile()) {
                    if (itemAttach != null
                            && itemAttach.getSupv_Constr_Attach_file_Id() > 0) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId((int) itemAttach
                                .getSupv_Constr_Attach_file_Id());
                        addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
                                + itemAttach.getFile_Path());
                        addImgView.setStatusApprove(itemAttach.getStatusApprove());
                        addImgView.setReasonDeny(itemAttach.getResonDeny());
                        listImgView.add(addImgView);
                    }
                }
                // gan anh moi them hoac chup anh moi
                for (Supv_Constr_Attach_FileEntity itemNewAttachFile : curAcceptanceItem
                        .getLstNewAttachFile()) {
                    if (!StringUtil.isNullOrEmpty(itemNewAttachFile.getFile_Path())) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId(-1);
                        addImgView.setUrl(itemNewAttachFile.getFile_Path());
                        addImgView.setStatusApprove(itemNewAttachFile.getStatusApprove());
                        addImgView.setReasonDeny(itemNewAttachFile.getResonDeny());
                        listImgView.add(addImgView);
                    }
                }

                this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
                        listImgView);
                this.imgViewPopup.hideShowButton();
                this.imgViewPopup.showAtCenter();
                break;
            case OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO:
                this.curUnqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
                listImgView = new ArrayList<ImageEntity>();
                // gan anh co san
                for (Supv_Constr_Attach_FileEntity itemAttach : curUnqualifyItem
                        .getLstAttachFile()) {
                    if (itemAttach != null
                            && itemAttach.getSupv_Constr_Attach_file_Id() > 0) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId((int) itemAttach
                                .getSupv_Constr_Attach_file_Id());
                        addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
                                + itemAttach.getFile_Path());
                        addImgView.setStatusApprove(itemAttach.getStatusApprove());
                        addImgView.setReasonDeny(itemAttach.getResonDeny());
                        listImgView.add(addImgView);
                    }
                }
                // gan anh moi them hoac chup anh moi
                for (Supv_Constr_Attach_FileEntity itemNewAttachFile : curUnqualifyItem
                        .getLstNewAttachFile()) {
                    if (!StringUtil.isNullOrEmpty(itemNewAttachFile.getFile_Path())) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId(-1);
                        addImgView.setUrl(itemNewAttachFile.getFile_Path());
                        addImgView.setStatusApprove(itemNewAttachFile.getStatusApprove());
                        addImgView.setReasonDeny(itemNewAttachFile.getResonDeny());
                        listImgView.add(addImgView);
                    }
                }

                this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
                        listImgView);
                this.imgViewPopup.hideShowButton();
                this.imgViewPopup.showAtCenter();
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_NEW:
                this.takePhoto(constr_ConstructionItem);
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
                List<ImageEntity> lstData = (List<ImageEntity>) data;

                if (choicePillarAnten || choicePillarAntenDf) {
                    if (this.curEditItem.getSupervision_Bts_Pillar_AntenEntity()
                            .getStatus() == Constants.BTS_ASSESS_PILLAR.DAT) {
                        this.curAcceptanceItem.getLstAttachFile().clear();
                        this.curAcceptanceItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curAcceptanceItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contruoctionAcceptancePopup.refreshData();
                    } else if (this.curEditItem
                            .getSupervision_Bts_Pillar_AntenEntity().getStatus()
                            == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                        this.curUnqualifyItem.getLstAttachFile().clear();
                        this.curUnqualifyItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curUnqualifyItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contructionUnqualifyPopup.refreshData();
                    }
                }

                if (choiceLdc) {
                    if (isDgclldc == Constants.TANK_STATUS.DAT) {
                        this.curAcceptanceItem.getLstAttachFile().clear();
                        this.curAcceptanceItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curAcceptanceItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contruoctionAcceptancePopup.refreshData();
                    } else if (isDgclldc == Constants.TANK_STATUS.KHONG_DAT) {
                        this.curUnqualifyItem.getLstAttachFile().clear();
                        this.curUnqualifyItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curUnqualifyItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contructionUnqualifyPopup.refreshData();
                    }
                }

                if (choiceTctd) {
                    if (isStatus == Constants.TANK_STATUS.DAT) {
                        this.curAcceptanceItem.getLstAttachFile().clear();
                        this.curAcceptanceItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curAcceptanceItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contruoctionAcceptancePopup.refreshData();
                    } else if (isStatus == Constants.TANK_STATUS.KHONG_DAT) {
                        this.curUnqualifyItem.getLstAttachFile().clear();
                        this.curUnqualifyItem.getLstNewAttachFile().clear();
                        for (ImageEntity imageEntity : lstData) {
                            Supv_Constr_Attach_FileEntity curAttachFile
                                    = new Supv_Constr_Attach_FileEntity();
                            curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                    .getId());
                            curAttachFile.setFile_Path(imageEntity.getUrl());
                            curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
                            curAttachFile.setResonDeny(imageEntity.getReasonDeny());
                            this.curUnqualifyItem.getLstNewAttachFile().add(
                                    curAttachFile);
                        }
                        this.contructionUnqualifyPopup.refreshData();
                    }
                }

                this.imgViewPopup.hidePopup();

                break;
            case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

                this.imgViewPopup.deleteImageData();
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
                this.selectPhoto();
                break;
            case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
                // save ngay hoan thanh thi cong cot
                if (tabs.getCurrentTab() == 0) {
                    saveCompleteDay(constr_ConstructionItem,
                            Constants.PROGRESS_TYPE.BTS_TYPE,
                            Constants.PROGRESS_TYPE.THI_CONG_COT_BTS, outOfKey);
                    showCompleteDate(constr_ConstructionItem,
                            Constants.PROGRESS_TYPE.BTS_TYPE,
                            Constants.PROGRESS_TYPE.THI_CONG_COT_BTS,
                            supervision_bts_complete_date,
                            rl_supervision_bts_bt_complete);
                } else {
                    // save ngay hoan thanh lap dung cot
                    if (tabs.getCurrentTab() == 1) {
                        saveCompleteDay(constr_ConstructionItem,
                                Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.LAP_DUNG_COT_ANTEN_BTS,
                                outOfKey);
                        showCompleteDate(constr_ConstructionItem,
                                Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.LAP_DUNG_COT_ANTEN_BTS,
                                supervision_bts_complete_date,
                                rl_supervision_bts_bt_complete);
                    } else {
                        // save ngay hoan thanh tiep dia
                        saveCompleteDay(constr_ConstructionItem,
                                Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.TIEP_DIA_BTS, outOfKey);
                        showCompleteDate(constr_ConstructionItem,
                                Constants.PROGRESS_TYPE.BTS_TYPE,
                                Constants.PROGRESS_TYPE.TIEP_DIA_BTS,
                                supervision_bts_complete_date,
                                rl_supervision_bts_bt_complete);
                    }
                }

                break;
            default:
                super.onEvent(eventType, control, data);
                break;
        }
    }

    /* Ghi nghiem thu vao danh sach ong */
    private void saveAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
                /* Them moi */
                Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
                addItem.setCat_Cause_Constr_Acceptance_Id(curCauseUniqualify
                        .getCat_Cause_Constr_Acceptance_Id());
                addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
                addItem.setUnqulify(true);
                addItem.setLstNewAttachFile(curCauseUniqualify
                        .getLstNewAttachFile());
                addItem.setTitle(curCauseUniqualify.getTitle());
                curListUnqualify.add(addItem);
            }
        }
    }

    /**
     * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
     */
    private void setAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                curCauseUniqualify.setDeleteImage(false);
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    /* Ghi nghiem thu vao danh sach ong */
    private void saveLdcAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemLdc
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listLdcAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
				/* Them moi */
                Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
                addItem.setCat_Cause_Constr_Acceptance_Id(curCauseUniqualify
                        .getCat_Cause_Constr_Acceptance_Id());
                addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
                addItem.setUnqulify(true);
                addItem.setLstNewAttachFile(curCauseUniqualify
                        .getLstNewAttachFile());
                addItem.setTitle(curCauseUniqualify.getTitle());
                curListUnqualify.add(addItem);
            }
        }
    }

    /**
     * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
     */
    private void setLdcAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemLdc
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listLdcAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                curCauseUniqualify.setDeleteImage(false);
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    /* Ghi nghiem thu vao danh sach ong */
    private void saveTctdAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemTctd
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTctdAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
				/* Them moi */
                Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
                addItem.setCat_Cause_Constr_Acceptance_Id(curCauseUniqualify
                        .getCat_Cause_Constr_Acceptance_Id());
                addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
                addItem.setUnqulify(true);
                addItem.setLstNewAttachFile(curCauseUniqualify
                        .getLstNewAttachFile());
                addItem.setTitle(curCauseUniqualify.getTitle());
                curListUnqualify.add(addItem);
            }
        }
    }

    /**
     * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
     */
    private void setTctdAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemTctd
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTctdAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                curCauseUniqualify.setDeleteImage(false);
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            confirmSave.dismiss();
            showProgressDialog(StringUtil.getString(R.string.text_progcessing));
        }

        @Override
        protected Void doInBackground(Void... params) {
            saveData();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (outOfKey) {
                Toast.makeText(SupervisionBtsPillar.this,
                        StringUtil.getString(R.string.text_out_of_key),
                        Toast.LENGTH_SHORT).show();
                closeProgressDialog();
                return;
            }

            refreshThicongcot();
            refreshLapdungcot();
            refreshTiepdia();

            Toast.makeText(SupervisionBtsPillar.this,
                    getResources().getString(R.string.text_update_success),
                    Toast.LENGTH_LONG).show();
            closeProgressDialog();
        }

    }

    @Override
    public void actionBeforAccept() {
        super.actionBeforAccept();

        if (!curEditItem.isNew()) {
            deletePillarAntenRow(curEditItem);
        }
        listData.remove(curEditItem);
        this.bts_PillarAdapter.notifyDataSetChanged();

    }

    public void deletePillarAntenRow(Cause_Bts_Pillar_AntenEntity itemDelete) {
        Supervision_Bts_Pillar_AntenController btsPillarAntenController
                = new Supervision_Bts_Pillar_AntenController(this);

        btsPillarAntenController
                .deleteSupervision_Bts_PillarAntenEntity(itemDelete
                        .getSupervision_Bts_Pillar_AntenEntity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.TAKE_PICTURE_RESULT:
                if (resultCode == Activity.RESULT_OK) {

                    ImageEntity addImgView = new ImageEntity();
                    addImgView.setId(1);
                    addImgView.setUrl(imgUri.getPath());

                    this.imgViewPopup.setImageData(addImgView);

                }
                break;
//		case Constants.SELECT_PICTURE_RESULT:
//			if (resultCode == Activity.RESULT_OK && data != null) {
//				Uri selectedImage = data.getData();
//
//				String picturePath = StringUtil.getPath(this, selectedImage);
//				Log.e("SELECT_PICTURE_RESULT", "onActivityResult: " + data );
//				Log.e("SELECT_PICTURE_RESULT", "onActivityResult: " + data.getData() );
////				if (selectedImage.toString().startsWith("file:///")) {
////					picturePath = selectedImage.toString().substring(7);
////				} else {
////					String[] filePathColumn = { MediaStore.Images.Media.DATA };
////					Cursor cursor = getContentResolver().query(selectedImage,
////							filePathColumn, null, null, null);
////					cursor.moveToFirst();
////					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////					picturePath = cursor.getString(columnIndex);
////					cursor.close();
////				}
//
//
//				ImageEntity addImgView = new ImageEntity();
//				addImgView.setId(1);
//				addImgView.setUrl(picturePath);
//
//				this.imgViewPopup.setImageData(addImgView);
//			}
//			break;
            case Constants.LOCATION_RESULT:
                if (data != null) {
                    Bundle dataBundle = data.getExtras();
                    longitute = dataBundle.getDouble(IntentConstants.INTENT_LONG);
                    latitute = dataBundle.getDouble(IntentConstants.INTENT_LAT);

                    if (longitute != -1 && latitute != -1) {
                        btsEntity.setPillar_LONGITUDE((double) longitute);
                        btsEntity.setPillar_LATITUDE((double) latitute);
                    }
                }

                break;
            default:
                break;
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    public boolean querryThicongcot() {

        try {
            long idUser = GlobalInfo.getInstance().getUserId();
            Supervision_Bts_Pillar_AntenController btsPillarAntenController
                    = new Supervision_Bts_Pillar_AntenController(this);
            Cause_Bts_Pillar_AntenController causeBtsPillarAntenController
                    = new Cause_Bts_Pillar_AntenController(this);

            for (Cause_Bts_Pillar_AntenEntity curItemData : listData) {
                List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
                        .getListCauseUniQualify();
                // tao moi (insert tat ca truong du lieu cot vao database)
                if (curItemData.isNew()) {
                    long idBtsPillarLast = Ktts_KeyController
                            .getInstance()
                            .getKttsNextKey(
                                    Supervision_Bts_Pillar_AntenField.TABLE_NAME);

                    if (idBtsPillarLast == 0) {
                        return true;
                    }

                    curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                    curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                    curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .setProcessId(0);
                    curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .setEmployeeId(idUser);
                    curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .setSupervisionConstrId(
                                    btsEntity.getSupervision_ConstrEntity()
                                            .getSupervision_Constr_Id());

                    btsPillarAntenController
                            .insertSupervisionBtsPillarAnten(curItemData
                                            .getSupervision_Bts_Pillar_AntenEntity(),
                                    idBtsPillarLast);

                    Cause_Bts_Pillar_AntenEntity causeBtsPillarEntity
                            = new Cause_Bts_Pillar_AntenEntity();
                    causeBtsPillarEntity
                            .getSupervision_Bts_Pillar_AntenEntity()
                            .setSupervision_BTS_PILLAR_ANTEN_ID(idBtsPillarLast);

                    causeBtsPillarEntity
                            .setListCauseUniQualify(curListUnqualify);

                    if (curItemData.getSupervision_Bts_Pillar_AntenEntity()
                            .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {

                        ArrayList<Long> ListIdCause = new ArrayList<Long>();

                        for (Supervision_LBG_UnqualifyItemEntity unqualify : causeBtsPillarEntity
                                .getListCauseUniQualify()) {
                            long lastId = Ktts_KeyController
                                    .getInstance()
                                    .getKttsNextKey(
                                            Cause_Bts_Pillar_AntenField.TABLE_NAME);
                            if (lastId == 0) {
                                return true;
                            }

                            unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
                            unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
                            unqualify.setProcessId(0);
                            unqualify.setSupervisionConstrId(btsEntity
                                    .getSupervision_ConstrEntity()
                                    .getSupervision_Constr_Id());

                            causeBtsPillarAntenController
                                    .insertCauseBts_PillarAntenEntity(
                                            unqualify,
                                            causeBtsPillarEntity
                                                    .getSupervision_Bts_Pillar_AntenEntity(),
                                            lastId, idUser);
                            ListIdCause.add(lastId);

                        }

                        int dem = 0;
                        for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {

                            for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                    .getLstNewAttachFile()) {
                                if (!StringUtil.isNullOrEmpty(itemFile
                                        .getFile_Path())) {
                                    this.supvConstrAttachFileController
                                            .coppyAndAddAttachFile(
                                                    this.constr_ConstructionItem,
                                                    itemFile.getFile_Path(),
                                                    ListIdCause.get(dem),
                                                    Cause_Bts_Pillar_AntenField.TABLE_NAME);

                                }
                            }
                            dem++;
                        }
                    } else {
                        // neu dat thi save anh nghiem thu
                        List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = curItemData
                                .getListAcceptance();
                        for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
                            Acceptance_Bts_PillarEntity addCauseItem = new Acceptance_Bts_PillarEntity();
                            addCauseItem
                                    .setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
                                            .getCat_Cause_Constr_Acceptance_Id());
                            addCauseItem
                                    .setSupv_Bts_Pillar_Anten_Id(idBtsPillarLast);
                            addCauseItem
                                    .setSync_Status(Constants.SYNC_STATUS.ADD);
                            addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
                            addCauseItem.setProcessId(0);
                            addCauseItem.setEmployeeId(idUser);

                            long iAddCause = Ktts_KeyController
                                    .getInstance()
                                    .getKttsNextKey(
                                            Acceptance_Bts_PillarField.TABLE_NAME);
                            if (iAddCause == 0) {
                                outOfKey = true;
                                return true;
                            } else
                                outOfKey = false;

                            addCauseItem.setAcceptance_Bts_Pillar_Id(iAddCause);
                            causeBtsPillarAntenController.addItem(addCauseItem);

                            for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                    .getLstNewAttachFile()) {
                                if (!StringUtil.isNullOrEmpty(itemFile
                                        .getFile_Path())) {
                                    this.supvConstrAttachFileController
                                            .coppyAndAddAttachFile(
                                                    this.constr_ConstructionItem,
                                                    itemFile.getFile_Path(),
                                                    iAddCause,
                                                    Acceptance_Bts_PillarField.TABLE_NAME);

                                }
                            }
                        }
                    }

                } else {
                    // update nhung du lieu cot da sua
                    if (curItemData.isEdited()) {

                        if (curItemData.getSupervision_Bts_Pillar_AntenEntity()
                                .getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                            curItemData.getSupervision_Bts_Pillar_AntenEntity()
                                    .setSync_Status(Constants.SYNC_STATUS.EDIT);
                        }

                        btsPillarAntenController
                                .updateSupervisionBtsPillarAnten(curItemData
                                        .getSupervision_Bts_Pillar_AntenEntity());

                        Cause_Bts_Pillar_AntenEntity causeBtsPillarEntity
                                = new Cause_Bts_Pillar_AntenEntity();
                        causeBtsPillarEntity
                                .getSupervision_Bts_Pillar_AntenEntity()
                                .setSupervision_BTS_PILLAR_ANTEN_ID(
                                        curItemData
                                                .getSupervision_Bts_Pillar_AntenEntity()
                                                .getSupervision_BTS_PILLAR_ANTEN_ID());
                        causeBtsPillarEntity.setListCauseUniQualify(curItemData
                                .getListCauseUniQualify());

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : curItemData
                                .getListAcceptance()) {
							/* 1. Chinh sua nghiem thu */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {

                                // xoa nghiem thu khi chuyen
                                // trang thai tu khong dat sang dat
                                // if (curItemData
                                // .getSupervision_Bts_Pillar_AntenEntity()
                                // .getStatus() ==
                                // Constants.SUPERVISION_STATUS.CHUADAT) {
                                // causeBtsPillarAntenController
                                // .deleteAccept(addItemCause);
                                // }

                                if (addItemCause.getLstNewAttachFile().size() > 0
                                        || (addItemCause.getLstNewAttachFile()
                                        .size() == 0 && addItemCause
                                        .getLstAttachFile().size() == 0)) {
                                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                            = this.supvConstrAttachFileController.getLstAttachFile(
                                            Acceptance_Bts_PillarField.TABLE_NAME,
                                            addItemCause
                                                    .getCause_Line_Bg_Id());

                                    // xoa anh cu di

                                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                        supvConstrAttachFileController
                                                .delete(itemFile);
                                    }
                                }

                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {

                                    // them anh moi vao
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        addItemCause
                                                                .getCause_Line_Bg_Id(),
                                                        Acceptance_Bts_PillarField.TABLE_NAME);

                                    }
                                }

                            }
							/* 2. Them moi nghiem thu */
                            else {
                                if (curItemData
                                        .getSupervision_Bts_Pillar_AntenEntity()
                                        .getStatus() == Constants.SUPERVISION_STATUS.DAT) {
                                    Acceptance_Bts_PillarEntity addCauseItem
                                            = new Acceptance_Bts_PillarEntity();
                                    addCauseItem
                                            .setCat_Cause_Constr_Acceptance_Id(addItemCause
                                                    .getCat_Cause_Constr_Acceptance_Id());
                                    addCauseItem
                                            .setSupv_Bts_Pillar_Anten_Id(curItemData
                                                    .getSupervision_Bts_Pillar_AntenEntity()
                                                    .getSupervision_BTS_PILLAR_ANTEN_ID());
                                    addCauseItem
                                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                                    addCauseItem
                                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                                    addCauseItem.setProcessId(0);
                                    addCauseItem.setEmployeeId(idUser);

                                    long iAddCause = Ktts_KeyController
                                            .getInstance()
                                            .getKttsNextKey(
                                                    Acceptance_Bts_PillarField.TABLE_NAME);

                                    if (iAddCause == 0) {
                                        outOfKey = true;
                                        return true;
                                    } else
                                        outOfKey = false;

                                    addCauseItem
                                            .setAcceptance_Bts_Pillar_Id(iAddCause);
                                    causeBtsPillarAntenController
                                            .addItem(addCauseItem);
                                    // causeBtsPillarAntenController
                                    // .insertCauseBts_PillarAntenEntity(
                                    // addItemCause,
                                    // addCauseItem
                                    // .getSupervision_Bts_Pillar_AntenEntity(),
                                    // lastId, idUser);
                                    // Luu anh nguyen nhan loi neu co
                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            iAddCause,
                                                            Acceptance_Bts_PillarField.TABLE_NAME);

                                        }
                                    }
                                }

                            }
                        }

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : curItemData
                                .getListCauseUniQualify()) {
                            // xoa het nguyen nhan khong dat cu
                            // cauCatWorkController
                            // .deleteCause_Bts_Cat_WorkEntity(unqualify);
							/* 1. Chinh sua nguyen nhan khong dat */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
                                if (addItemCause.isDelete()) {
                                    causeBtsPillarAntenController
                                            .deleteCause_Bts_Pillar_AntenEntity(addItemCause);
                                }
								/* 1.2. Update lai nguyen nhan khong dat */
                                else {
                                    // xoa nguyen nhan khong dat khi chuyen
                                    // trang
                                    // thai tu khong dat sang dat
//									if (curItemData
//											.getSupervision_Bts_Pillar_AntenEntity()
//											.getStatus() == Constants.BTS_ASSESS_PILLAR.DAT) {
//										causeBtsPillarAntenController
//												.deleteCause_Bts_Pillar_AntenEntity(addItemCause);
//									}

                                    if (addItemCause.getLstNewAttachFile()
                                            .size() > 0
                                            || (addItemCause
                                            .getLstNewAttachFile()
                                            .size() == 0 && addItemCause
                                            .getLstAttachFile().size() == 0)) {
                                        List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                                = this.supvConstrAttachFileController.getLstAttachFile(
                                                Cause_Bts_Pillar_AntenField.TABLE_NAME,
                                                addItemCause
                                                        .getCause_Line_Bg_Id());

                                        // xoa anh cu di

                                        for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                            supvConstrAttachFileController
                                                    .delete(itemFile);
                                        }
                                    }

                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {

                                        // them anh moi vao
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            addItemCause
                                                                    .getCause_Line_Bg_Id(),
                                                            Cause_Bts_Pillar_AntenField.TABLE_NAME);

                                        }
                                    }
                                }
                            }
							/* 2. Them moi nguyen nhan khong dat */
                            else {
                                if (curItemData
                                        .getSupervision_Bts_Pillar_AntenEntity()
                                        .getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {

                                    ArrayList<Long> ListIdCause = new ArrayList<Long>();

                                    long lastId = Ktts_KeyController
                                            .getInstance()
                                            .getKttsNextKey(
                                                    Cause_Bts_Pillar_AntenField.TABLE_NAME);

                                    if (lastId == 0) {
                                        return true;
                                    }

                                    Cause_Bts_Pillar_AntenEntity addCauseItem
                                            = new Cause_Bts_Pillar_AntenEntity();
                                    addItemCause
                                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                                    addItemCause
                                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                                    addItemCause.setProcessId(0);
                                    addItemCause
                                            .setSupervisionConstrId(btsEntity
                                                    .getSupervision_ConstrEntity()
                                                    .getSupervision_Constr_Id());

                                    addCauseItem
                                            .getSupervision_Bts_Pillar_AntenEntity()
                                            .setSupervision_BTS_PILLAR_ANTEN_ID(
                                                    curItemData
                                                            .getSupervision_Bts_Pillar_AntenEntity()
                                                            .getSupervision_BTS_PILLAR_ANTEN_ID());

                                    causeBtsPillarAntenController
                                            .insertCauseBts_PillarAntenEntity(
                                                    addItemCause,
                                                    addCauseItem
                                                            .getSupervision_Bts_Pillar_AntenEntity(),
                                                    lastId, idUser);
                                    ListIdCause.add(lastId);

                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            lastId,
                                                            Cause_Bts_Pillar_AntenField.TABLE_NAME);

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            Log.e("error", e.toString());
        }

        return false;

        // refreshThicongcot();
    }

    private void saveData() {
		/* Tinh toan gia tri */
        try {
            long idUser = GlobalInfo.getInstance().getUserId();
            Cat_Supervision_Constr_WorkController constrWorkController
                    = new Cat_Supervision_Constr_WorkController(this);
            Supervision_Bts_Cat_WorkController catWorkController
                    = new Supervision_Bts_Cat_WorkController(this);

            Supervision_BtsController bts_Controller = new Supervision_BtsController(this);

            Cause_Bts_Cat_WorkController cauCatWorkController = new Cause_Bts_Cat_WorkController(
                    this);

            if (btsEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                btsEntity.setSync_Status(Constants.SYNC_STATUS.EDIT);
            }

            bts_Controller.updateLongLat(btsEntity);

            // phan lap dung cot
            if (tabs.getCurrentTab() == 0) {
                if (btsEntity.getPillar_ANTEN() != Constants.BTS_POS_PILLAR_ANTEN.CO_SAN) {
                    // int found_num_bts = btsEntity.getFoundation_NUM();
                    // insert, update cot neu thiet ke la co san
                    if (querryThicongcot()) {
                        outOfKey = true;
                        return;
                    } else
                        outOfKey = false;

                }
            }

            // phan lap dung cot
            if (tabs.getCurrentTab() == 1) {
                // cap nhat vao bts phan danh gia chat luong cot

                btsEntity.setPillar_STATUS_QUALITY(isDgclc);
                if (btsEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                    btsEntity.setSync_Status(Constants.SYNC_STATUS.EDIT);
                }

                bts_Controller.updatePillarStatusQuality(btsEntity);

                Supervision_Bts_Cat_WorkEntity btsCatWorkLdcEntity
                        = getSupervisionBtsCatWorkLdcEntity();

                if (curEditItemLdc.getBts_Cat_WorkEntity().getStatus() < 0) {
                    /**
                     * xu li insert danh gia thi cong lap dung cot
                     */
                    if (isDgclc == Constants.BTS_ASSESS_PILLAR.DAT) {
                        // them constr work co work_code tuong ung vao csdl
                        int idConstrWorkLdc = constrWorkController
                                .getConstrWorkEntityByWorkCodeReturnId(
                                        Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT);

                        // btsCatWork
                        btsCatWorkLdcEntity
                                .setCat_Supervision_Constr_Work_Id(idConstrWorkLdc);
                        btsCatWorkLdcEntity.setSupervision_Bts_Id(btsEntity
                                .getSupervision_Bts_Id());

                        long idBtsCatWorkLdc = Ktts_KeyController
                                .getInstance()
                                .getKttsNextKey(
                                        Supervision_Bts_Cat_WorkField.TABLE_NAME);
                        if (idBtsCatWorkLdc == 0) {
                            outOfKey = true;
                            return;
                        } else
                            outOfKey = false;

                        btsCatWorkLdcEntity
                                .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLdc);

                        btsCatWorkLdcEntity
                                .setSync_Status(Constants.SYNC_STATUS.ADD);
                        btsCatWorkLdcEntity
                                .setIsActive(Constants.ISACTIVE.ACTIVE);
                        btsCatWorkLdcEntity.setProcessId(0);
                        btsCatWorkLdcEntity.setEmployeeId(idUser);
                        btsCatWorkLdcEntity.setSupervisionConstrId(btsEntity
                                .getSupervision_ConstrEntity()
                                .getSupervision_Constr_Id());

                        catWorkController
                                .insertBtsCatWorkEntity(btsCatWorkLdcEntity);

                        Cause_Bts_Cat_WorkEntity causeCatWorkLdcEntity
                                = new Cause_Bts_Cat_WorkEntity();
                        causeCatWorkLdcEntity
                                .getBts_Cat_WorkEntity()
                                .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLdc);
                        // causeCatWorkLdcEntity
                        // .setListConstrUnqualifyEntity(listUnqualifyLdcEntity);
                        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curEditItemLdc
                                .getListCauseUniQualify();
                        causeCatWorkLdcEntity
                                .setListCauseUniQualify(curListUnqualify);

                        if (btsCatWorkLdcEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

                            ArrayList<Long> ListIdCause = new ArrayList<Long>();

                            for (Supervision_LBG_UnqualifyItemEntity unqualify : causeCatWorkLdcEntity
                                    .getListCauseUniQualify()) {
                                long lastId = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Cause_Bts_Cat_WorkField.TABLE_NAME);

                                if (lastId == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                unqualify
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                unqualify
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                unqualify.setProcessId(0);
                                unqualify.setSupervisionConstrId(btsEntity
                                        .getSupervision_ConstrEntity()
                                        .getSupervision_Constr_Id());

                                cauCatWorkController
                                        .insertCause_Bts_Cat_WorkEntity(
                                                unqualify,
                                                causeCatWorkLdcEntity
                                                        .getBts_Cat_WorkEntity(),
                                                lastId, idUser);
                                ListIdCause.add(lastId);

                            }

                            // ArrayList<Integer> ListIdCause =
                            int dem = 0;
                            for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
                                // Luu anh nguyen nhan loi neu co
                                for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        ListIdCause.get(dem),
                                                        Cause_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }
                                dem++;
                            }

                        } else {
                            // neu dat thi save anh nghiem thu
                            List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = curEditItemLdc
                                    .getListAcceptance();
                            for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
                                Acceptance_Bts_Cat_WorkEntity addCauseItem
                                        = new Acceptance_Bts_Cat_WorkEntity();
                                addCauseItem
                                        .setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
                                                .getCat_Cause_Constr_Acceptance_Id());
                                addCauseItem
                                        .setType(Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT);
                                addCauseItem
                                        .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLdc);
                                addCauseItem
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                addCauseItem
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                addCauseItem.setProcessId(0);
                                addCauseItem.setEmployeeId(idUser);

                                long iAddCause = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Acceptance_Bts_Cat_WorkField.TABLE_NAME);
                                if (iAddCause == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                addCauseItem
                                        .setAcceptance_Bts_Cat_Work_Id(iAddCause);
                                cauCatWorkController.addItem(addCauseItem);

                                for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        iAddCause,
                                                        Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }
                            }
                        }

                    }

                } else {
                    /**
                     * xu li update lap dung cot
                     */
                    if (isDgclc == Constants.BTS_ASSESS_PILLAR.DAT) {
                        btsCatWorkLdcEntity
                                .setSupervision_Bts_Cat_Work_Id(curEditItemLdc
                                        .getBts_Cat_WorkEntity()
                                        .getSupervision_Bts_Cat_Work_Id());

                        if (curEditItemLdc.getBts_Cat_WorkEntity()
                                .getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                            btsCatWorkLdcEntity
                                    .setSync_Status(Constants.SYNC_STATUS.EDIT);
                        } else {
                            btsCatWorkLdcEntity.setSync_Status(curEditItemLdc
                                    .getBts_Cat_WorkEntity().getSync_Status());
                        }
                        catWorkController
                                .updateBtsCatWorkEntity(btsCatWorkLdcEntity);

                        Cause_Bts_Cat_WorkEntity causeCatWorkLdcEntity
                                = new Cause_Bts_Cat_WorkEntity();
                        causeCatWorkLdcEntity
                                .getBts_Cat_WorkEntity()
                                .setSupervision_Bts_Cat_Work_Id(
                                        btsCatWorkLdcEntity
                                                .getSupervision_Bts_Cat_Work_Id());

                        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curEditItemLdc
                                .getListCauseUniQualify();
                        causeCatWorkLdcEntity
                                .setListCauseUniQualify(curListUnqualify);

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemLdc
                                .getListAcceptance()) {
							/* 1. Chinh sua nghiem thu */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {

                                // xoa nghiem thu khi chuyen
                                // trang thai tu khong dat sang dat
                                // if (isDgclldc ==
                                // Constants.SUPERVISION_STATUS.CHUADAT) {
                                // catWorkController.deleteAccept(addItemCause);
                                // }

                                if (addItemCause.getLstNewAttachFile().size() > 0
                                        || (addItemCause.getLstNewAttachFile()
                                        .size() == 0 && addItemCause
                                        .getLstAttachFile().size() == 0)) {
                                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                            = this.supvConstrAttachFileController.getLstAttachFile(
                                            Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                                            addItemCause
                                                    .getCause_Line_Bg_Id());
                                    // xoa anh cu di

                                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                        supvConstrAttachFileController
                                                .delete(itemFile);
                                    }
                                }

                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {

                                    // them anh moi vao
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        addItemCause
                                                                .getCause_Line_Bg_Id(),
                                                        Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }

                            }
							/* 2. Them moi nghiem thu */
                            else {
                                if (isDgclldc == Constants.SUPERVISION_STATUS.DAT) {
                                    Acceptance_Bts_Cat_WorkEntity addCauseItem
                                            = new Acceptance_Bts_Cat_WorkEntity();
                                    addCauseItem
                                            .setCat_Cause_Constr_Acceptance_Id(addItemCause
                                                    .getCat_Cause_Constr_Acceptance_Id());
                                    addCauseItem
                                            .setSupervision_Bts_Cat_Work_Id(curEditItemLdc
                                                    .getBts_Cat_WorkEntity()
                                                    .getSupervision_Bts_Cat_Work_Id());
                                    addCauseItem
                                            .setType(Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT);
                                    addCauseItem
                                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                                    addCauseItem
                                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                                    addCauseItem.setProcessId(0);
                                    addCauseItem.setEmployeeId(idUser);

                                    long iAddCause = Ktts_KeyController
                                            .getInstance()
                                            .getKttsNextKey(
                                                    Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                    if (iAddCause == 0) {
                                        outOfKey = true;
                                        return;
                                    } else
                                        outOfKey = false;

                                    addCauseItem
                                            .setAcceptance_Bts_Cat_Work_Id(iAddCause);

                                    cauCatWorkController.addItem(addCauseItem);

                                    // Luu anh nguyen nhan loi neu co
                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            iAddCause,
                                                            Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                        }
                                    }
                                }

                            }
                        }

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemLdc
                                .getListCauseUniQualify()) {
                            // xoa het nguyen nhan khong dat cu
                            // cauCatWorkController
                            // .deleteCause_Bts_Cat_WorkEntity(unqualify);
							/* 1. Chinh sua nguyen nhan khong dat */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
                                if (addItemCause.isDelete()) {
                                    cauCatWorkController
                                            .deleteCause_Bts_Cat_WorkEntity(addItemCause);
                                }
								/* 1.2. Update lai nguyen nhan khong dat */
                                else {
                                    // xoa nguyen nhan khong dat khi chuyen
                                    // trang
                                    // thai tu khong dat sang dat
//									if (btsCatWorkLdcEntity.getStatus()
//                                  == Constants.BTS_CAT_WORK_STATUS.DAT) {
//										cauCatWorkController
//												.deleteCause_Bts_Cat_WorkEntity(addItemCause);
//									}

                                    if (addItemCause.getLstNewAttachFile()
                                            .size() > 0
                                            || (addItemCause
                                            .getLstNewAttachFile()
                                            .size() == 0 && addItemCause
                                            .getLstAttachFile().size() == 0)) {
                                        List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                                = this.supvConstrAttachFileController.getLstAttachFile(
                                                Cause_Bts_Cat_WorkField.TABLE_NAME,
                                                addItemCause
                                                        .getCause_Line_Bg_Id());

                                        // xoa anh cu di

                                        for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                            supvConstrAttachFileController
                                                    .delete(itemFile);
                                        }
                                    }

                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {

                                        // them anh moi vao
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            addItemCause
                                                                    .getCause_Line_Bg_Id(),
                                                            Cause_Bts_Cat_WorkField.TABLE_NAME);

                                        }
                                    }

                                }
                            }
							/* 2. Them moi nguyen nhan khong dat */
                            else {
                                if (btsCatWorkLdcEntity.getStatus()
                                        == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

                                    ArrayList<Long> ListIdCause = new ArrayList<Long>();

                                    long lastId = Ktts_KeyController
                                            .getInstance()
                                            .getKttsNextKey(
                                                    Cause_Bts_Cat_WorkField.TABLE_NAME);

                                    if (lastId == 0) {
                                        outOfKey = true;
                                        return;
                                    } else
                                        outOfKey = false;

                                    Cause_Bts_Cat_WorkEntity addCauseItem
                                            = new Cause_Bts_Cat_WorkEntity();
                                    addItemCause
                                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                                    addItemCause
                                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                                    addItemCause.setProcessId(0);
                                    addItemCause
                                            .setSupervisionConstrId(btsEntity
                                                    .getSupervision_ConstrEntity()
                                                    .getSupervision_Constr_Id());

                                    addCauseItem
                                            .getBts_Cat_WorkEntity()
                                            .setSupervision_Bts_Cat_Work_Id(
                                                    curEditItemLdc
                                                            .getBts_Cat_WorkEntity()
                                                            .getSupervision_Bts_Cat_Work_Id());

                                    cauCatWorkController
                                            .insertCause_Bts_Cat_WorkEntity(
                                                    addItemCause,
                                                    addCauseItem
                                                            .getBts_Cat_WorkEntity(),
                                                    lastId, idUser);
                                    ListIdCause.add(lastId);

                                    // Luu anh nguyen nhan loi neu co

                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.constr_ConstructionItem,
                                                            itemFile.getFile_Path(),
                                                            lastId,
                                                            Cause_Bts_Cat_WorkField.TABLE_NAME);

                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

            // phan thi cong tiep dia
            if (tabs.getCurrentTab() == 2) {
                Supervision_Bts_Cat_WorkEntity btsCatWorkTdEntity = getSupervisionBtsCatWorkTdEntity();

                List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curEditItemTctd
                        .getListCauseUniQualify();
                if (curEditItemTctd.getBts_Cat_WorkEntity().getStatus() < 0) {
                    /**
                     * xu li insert thi cong tiep dia
                     */

                    // kttsKey
                    // .getKttsNextKey(Cat_Supervision_Constr_WorkField.TABLE_NAME);
                    int idConstrWorkTd = constrWorkController
                            .getConstrWorkEntityByWorkCodeReturnId(
                                    Constants.BTS_CONSTR_WORK.WORK_CODE_TDBTS);

                    btsCatWorkTdEntity
                            .setCat_Supervision_Constr_Work_Id(idConstrWorkTd);
                    btsCatWorkTdEntity.setSupervision_Bts_Id(btsEntity
                            .getSupervision_Bts_Id());
                    long idBtsCatWorkTd = Ktts_KeyController.getInstance()
                            .getKttsNextKey(
                                    Supervision_Bts_Cat_WorkField.TABLE_NAME);

                    if (idBtsCatWorkTd == 0) {
                        outOfKey = true;
                        return;
                    } else
                        outOfKey = false;
                    btsCatWorkTdEntity
                            .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkTd);

                    btsCatWorkTdEntity
                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                    btsCatWorkTdEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
                    btsCatWorkTdEntity.setProcessId(0);
                    btsCatWorkTdEntity.setEmployeeId(idUser);
                    btsCatWorkTdEntity.setSupervisionConstrId(btsEntity
                            .getSupervision_ConstrEntity()
                            .getSupervision_Constr_Id());

                    catWorkController
                            .insertBtsCatWorkEntity(btsCatWorkTdEntity);

                    Cause_Bts_Cat_WorkEntity causeCatWorkTdEntity = new Cause_Bts_Cat_WorkEntity();
                    causeCatWorkTdEntity.getBts_Cat_WorkEntity()
                            .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkTd);
                    // causeCatWorkTdEntity
                    // .setListConstrUnqualifyEntity(listUnqualifyTdEntity);
                    causeCatWorkTdEntity.setListCauseUniQualify(curEditItemTctd
                            .getListCauseUniQualify());

                    if (btsCatWorkTdEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
                        // ArrayList<Integer> ListIdCause = cauCatWorkController
                        // .insertCause_Bts_Cat_WorkEntity(causeCatWorkTdEntity);

                        ArrayList<Long> ListIdCause = new ArrayList<Long>();

                        for (Supervision_LBG_UnqualifyItemEntity unqualify : causeCatWorkTdEntity
                                .getListCauseUniQualify()) {
                            long lastId = Ktts_KeyController.getInstance()
                                    .getKttsNextKey(
                                            Cause_Bts_Cat_WorkField.TABLE_NAME);
                            if (lastId == 0) {
                                outOfKey = true;
                                return;
                            } else
                                outOfKey = false;

                            unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
                            unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
                            unqualify.setProcessId(0);
                            unqualify.setSupervisionConstrId(btsEntity
                                    .getSupervision_ConstrEntity()
                                    .getSupervision_Constr_Id());
                            cauCatWorkController
                                    .insertCause_Bts_Cat_WorkEntity(unqualify,
                                            causeCatWorkTdEntity
                                                    .getBts_Cat_WorkEntity(),
                                            lastId, idUser);
                            ListIdCause.add(lastId);

                        }
                        int dem = 0;
                        for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
                            // Luu anh nguyen nhan loi neu co

                            for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                    .getLstNewAttachFile()) {
                                if (!StringUtil.isNullOrEmpty(itemFile
                                        .getFile_Path())) {
                                    this.supvConstrAttachFileController
                                            .coppyAndAddAttachFile(
                                                    this.constr_ConstructionItem,
                                                    itemFile.getFile_Path(),
                                                    ListIdCause.get(dem),
                                                    Cause_Bts_Cat_WorkField.TABLE_NAME);

                                }
                            }
                            dem++;
                        }
                    } else {
                        // neu dat thi save anh nghiem thu
                        List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = curEditItemTctd
                                .getListAcceptance();
                        for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
                            Acceptance_Bts_Cat_WorkEntity addCauseItem
                                    = new Acceptance_Bts_Cat_WorkEntity();
                            addCauseItem
                                    .setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
                                            .getCat_Cause_Constr_Acceptance_Id());
                            addCauseItem
                                    .setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS);
                            addCauseItem
                                    .setSupervision_Bts_Cat_Work_Id(idBtsCatWorkTd);
                            addCauseItem
                                    .setSync_Status(Constants.SYNC_STATUS.ADD);
                            addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
                            addCauseItem.setProcessId(0);
                            addCauseItem.setEmployeeId(idUser);

                            long iAddCause = Ktts_KeyController
                                    .getInstance()
                                    .getKttsNextKey(
                                            Acceptance_Bts_Cat_WorkField.TABLE_NAME);
                            if (iAddCause == 0) {
                                outOfKey = true;
                                return;
                            } else
                                outOfKey = false;

                            addCauseItem
                                    .setAcceptance_Bts_Cat_Work_Id(iAddCause);
                            cauCatWorkController.addItem(addCauseItem);

                            for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                    .getLstNewAttachFile()) {
                                if (!StringUtil.isNullOrEmpty(itemFile
                                        .getFile_Path())) {
                                    this.supvConstrAttachFileController
                                            .coppyAndAddAttachFile(
                                                    this.constr_ConstructionItem,
                                                    itemFile.getFile_Path(),
                                                    iAddCause,
                                                    Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                }
                            }
                        }
                    }

                } else {
                    /**
                     * xu li update thi cong tiep dia
                     */
                    btsCatWorkTdEntity
                            .setSupervision_Bts_Cat_Work_Id(curEditItemTctd
                                    .getBts_Cat_WorkEntity()
                                    .getSupervision_Bts_Cat_Work_Id());

                    if (curEditItemTctd.getBts_Cat_WorkEntity()
                            .getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                        btsCatWorkTdEntity
                                .setSync_Status(Constants.SYNC_STATUS.EDIT);
                    } else {
                        btsCatWorkTdEntity.setSync_Status(curEditItemTctd
                                .getBts_Cat_WorkEntity().getSync_Status());
                    }

                    catWorkController
                            .updateBtsCatWorkEntity(btsCatWorkTdEntity);

                    Cause_Bts_Cat_WorkEntity causeCatWorkTdEntity = new Cause_Bts_Cat_WorkEntity();
                    causeCatWorkTdEntity.getBts_Cat_WorkEntity()
                            .setSupervision_Bts_Cat_Work_Id(
                                    btsCatWorkTdEntity
                                            .getSupervision_Bts_Cat_Work_Id());

                    causeCatWorkTdEntity.setListCauseUniQualify(curEditItemTctd
                            .getListCauseUniQualify());

                    for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemTctd
                            .getListAcceptance()) {
						/* 1. Chinh sua nghiem thu */
                        if (addItemCause.getCause_Line_Bg_Id() > 0) {

                            // xoa nghiem thu khi chuyen
                            // trang thai tu khong dat sang dat
                            // if (isStatus ==
                            // Constants.SUPERVISION_STATUS.CHUADAT) {
                            // catWorkController.deleteAccept(addItemCause);
                            // }

                            if (addItemCause.getLstNewAttachFile().size() > 0
                                    || (addItemCause.getLstNewAttachFile()
                                    .size() == 0 && addItemCause
                                    .getLstAttachFile().size() == 0)) {
                                List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                        = this.supvConstrAttachFileController.getLstAttachFile(
                                        Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                                        addItemCause
                                                .getCause_Line_Bg_Id());

                                // xoa anh cu di
                                for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                    supvConstrAttachFileController
                                            .delete(itemFile);
                                }
                            }

                            for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                    .getLstNewAttachFile()) {

                                // them anh moi vao
                                if (!StringUtil.isNullOrEmpty(itemFile
                                        .getFile_Path())) {
                                    this.supvConstrAttachFileController
                                            .coppyAndAddAttachFile(
                                                    this.constr_ConstructionItem,
                                                    itemFile.getFile_Path(),
                                                    addItemCause
                                                            .getCause_Line_Bg_Id(),
                                                    Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                }
                            }

                        }
						/* 2. Them moi nghiem thu */
                        else {
                            if (isStatus == Constants.SUPERVISION_STATUS.DAT) {
                                Acceptance_Bts_Cat_WorkEntity addCauseItem
                                        = new Acceptance_Bts_Cat_WorkEntity();
                                addCauseItem
                                        .setCat_Cause_Constr_Acceptance_Id(addItemCause
                                                .getCat_Cause_Constr_Acceptance_Id());
                                addCauseItem
                                        .setSupervision_Bts_Cat_Work_Id(curEditItemTctd
                                                .getBts_Cat_WorkEntity()
                                                .getSupervision_Bts_Cat_Work_Id());
                                addCauseItem
                                        .setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS);
                                addCauseItem
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                addCauseItem
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                addCauseItem.setProcessId(0);
                                addCauseItem.setEmployeeId(idUser);

                                long iAddCause = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                if (iAddCause == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                addCauseItem
                                        .setAcceptance_Bts_Cat_Work_Id(iAddCause);

                                cauCatWorkController.addItem(addCauseItem);

                                // Luu anh nguyen nhan loi neu co
                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        iAddCause,
                                                        Acceptance_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }
                            }

                        }
                    }

                    for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemTctd
                            .getListCauseUniQualify()) {
                        // xoa het nguyen nhan khong dat cu
                        // cauCatWorkController
                        // .deleteCause_Bts_Cat_WorkEntity(unqualify);
						/* 1. Chinh sua nguyen nhan khong dat */
                        if (addItemCause.getCause_Line_Bg_Id() > 0) {
							/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
                            if (addItemCause.isDelete()) {
                                cauCatWorkController
                                        .deleteCause_Bts_Cat_WorkEntity(addItemCause);
                            }
							/* 1.2. Update lai nguyen nhan khong dat */
                            else {
                                // xoa nguyen nhan khong dat khi chuyen trang
                                // thai
                                // tu khong dat sang dat
//								if (btsCatWorkTdEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
//									cauCatWorkController
//											.deleteCause_Bts_Cat_WorkEntity(addItemCause);
//								}

                                if (addItemCause.getLstNewAttachFile().size() > 0
                                        || (addItemCause.getLstNewAttachFile()
                                        .size() == 0 && addItemCause
                                        .getLstAttachFile().size() == 0)) {
                                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile
                                            = this.supvConstrAttachFileController
                                            .getLstAttachFile(
                                                    Cause_Bts_Cat_WorkField.TABLE_NAME,
                                                    addItemCause
                                                            .getCause_Line_Bg_Id());

                                    // xoa anh cu di

                                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                        supvConstrAttachFileController
                                                .delete(itemFile);
                                    }
                                }

                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {

                                    // them anh moi vao
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        addItemCause
                                                                .getCause_Line_Bg_Id(),
                                                        Cause_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }

                            }
                        }
						/* 2. Them moi nguyen nhan khong dat */
                        else {
                            if (btsCatWorkTdEntity.getStatus()
                                    == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

                                ArrayList<Long> ListIdCause = new ArrayList<Long>();

                                long lastId = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Cause_Bts_Cat_WorkField.TABLE_NAME);

                                if (lastId == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                Cause_Bts_Cat_WorkEntity addCauseItem = new Cause_Bts_Cat_WorkEntity();
                                addItemCause
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                addItemCause
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                addItemCause.setProcessId(0);
                                addItemCause.setSupervisionConstrId(btsEntity
                                        .getSupervision_ConstrEntity()
                                        .getSupervision_Constr_Id());

                                addCauseItem
                                        .getBts_Cat_WorkEntity()
                                        .setSupervision_Bts_Cat_Work_Id(
                                                curEditItemTctd
                                                        .getBts_Cat_WorkEntity()
                                                        .getSupervision_Bts_Cat_Work_Id());

                                cauCatWorkController
                                        .insertCause_Bts_Cat_WorkEntity(
                                                addItemCause,
                                                addCauseItem
                                                        .getBts_Cat_WorkEntity(),
                                                lastId, idUser);
                                ListIdCause.add(lastId);

                                // Luu anh nguyen nhan loi neu co
                                // if (!StringUtil.isNullOrEmpty(addItemCause
                                // .getNewAttachFile().getFile_Path())) {
                                // this.supvConstrAttachFileController
                                // .coppyAndAddAttachFile(
                                // this.constr_ConstructionEmployeeItem,
                                // addItemCause
                                // .getNewAttachFile()
                                // .getFile_Path(),
                                // lastId,
                                // Cause_Bts_Cat_WorkField.TABLE_NAME);
                                //
                                // }
                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.constr_ConstructionItem,
                                                        itemFile.getFile_Path(),
                                                        lastId,
                                                        Cause_Bts_Cat_WorkField.TABLE_NAME);

                                    }
                                }
                            }
                        }
                    }
                }
            }

            // cap nhat trang thai cong trinh
            Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
                    this);
            constr_Controller.updateSyncStatus(constr_ConstructionItem
                    .getSupervision_Constr_Id());


            // TODO: Thiet lap ket luan. DanhDue ExOICTIF
            boolean bDeny = checkCauseDeny(constr_ConstructionItem);
            Log.i("Check_Deny", String.valueOf(bDeny));
            // Toast.makeText(getApplicationContext(),
            // String.valueOf(checkCauseDeny(constr_ConstructionEmployeeItem)), Toast.LENGTH_LONG).show();
            if (bDeny) {
                constr_Controller.updateStatus(
                        constr_ConstructionItem.getSupervision_Constr_Id(), 0);
            } else {
                constr_Controller.updateStatus(
                        constr_ConstructionItem.getSupervision_Constr_Id(), 1);
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            // Toast.makeText(getApplicationContext(), e.getMessage(),
            // Toast.LENGTH_LONG).show();
        }

    }

    /* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
    private void saveUnqualify() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenUnqualifyItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());

            if (curItem == null) {
				/* Them moi */
                if (curCauseUniqualify.isUnqulify()) {
                    Supervision_LBG_UnqualifyItemEntity addItem
                            = new Supervision_LBG_UnqualifyItemEntity();
                    addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
                            .getCat_Cause_Constr_Unqualify_Id());
                    addItem.setCause_Line_Bg_Id(this.curEditItem
                            .getCause_BTS_PILLAR_ANTEN_ID());
                    addItem.setUnqulify(true);
                    // addItem.setNewAttachFile(curCauseUniqualify
                    // .getNewAttachFile());
                    addItem.setLstNewAttachFile(curCauseUniqualify
                            .getLstNewAttachFile());
                    addItem.setTitle(curCauseUniqualify.getTitle());
                    curListUnqualify.add(addItem);
                }
            } else {
                if (curCauseUniqualify.isUnqulify()) {
                    curItem.setUnqulify(true);
                    curItem.setDelete(curCauseUniqualify.isDelete());
                    curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());

                } else {
					/* Danh dau xoa nguyen nhan khong dat */
                    curItem.setDelete(true);
                    curItem.setProcessId(curItem.getProcessId());
                    curItem.setSync_Status(curItem.getSync_Status());
                }
            }
            // if (curItem == null) {
            // /* Them moi */
            // if (curCauseUniqualify.isUnqulify()) {
            // Supervision_LBG_UnqualifyItemEntity addItem = new
            // Supervision_LBG_UnqualifyItemEntity();
            // addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
            // .getCat_Cause_Constr_Unqualify_Id());
            // addItem.setCause_Line_Bg_Id(this.curEditItem
            // .getCause_BTS_PILLAR_ANTEN_ID());
            // addItem.setUnqulify(true);
            // addItem.setNewAttachFile(curCauseUniqualify
            // .getNewAttachFile());
            // addItem.setTitle(curCauseUniqualify.getTitle());
            // curListUnqualify.add(addItem);
            // }
            // } else {
            // if (curCauseUniqualify.isUnqulify()) {
            // curItem.setUnqulify(true);
            // curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());
            // if (StringUtil.isNullOrEmpty(curCauseUniqualify
            // .getNewAttachFile().getFile_Path())) {
            // curItem.setNewAttachFile(curCauseUniqualify
            // .getNewAttachFile());
            // }
            // } else {
            // // TODO khi xoa nguyen nhan loi phai xoa anh di
            // curListUnqualify.remove(curItem);
            // }
            // }

        }
    }

    private void setUnqualify() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenUnqualifyItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                // curCauseUniqualify
                // .setAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify.setDeleteImage(false);
                // curCauseUniqualify
                // .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                // curCauseUniqualify.setAttachFile(curItem.getAttachFile());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                // curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    private void saveUnqualifyDif() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenUnqualifyItemDif) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());

            if (curItem == null) {
				/* Them moi */
                if (curCauseUniqualify.isUnqulify()) {
                    Supervision_LBG_UnqualifyItemEntity addItem
                            = new Supervision_LBG_UnqualifyItemEntity();
                    addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
                            .getCat_Cause_Constr_Unqualify_Id());
                    addItem.setCause_Line_Bg_Id(this.curEditItem
                            .getCause_BTS_PILLAR_ANTEN_ID());
                    addItem.setUnqulify(true);
                    // addItem.setNewAttachFile(curCauseUniqualify
                    // .getNewAttachFile());
                    addItem.setLstNewAttachFile(curCauseUniqualify
                            .getLstNewAttachFile());
                    addItem.setTitle(curCauseUniqualify.getTitle());
                    curListUnqualify.add(addItem);
                }
            } else {
                if (curCauseUniqualify.isUnqulify()) {
                    curItem.setUnqulify(true);
                    curItem.setDelete(curCauseUniqualify.isDelete());
                    curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());

                } else {
					/* Danh dau xoa nguyen nhan khong dat */
                    curItem.setDelete(true);
                    curItem.setProcessId(curItem.getProcessId());
                    curItem.setSync_Status(curItem.getSync_Status());
                }
            }
        }
    }

    private void setUnqualifyDf() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPillarAntenUnqualifyItemDif) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                // curCauseUniqualify
                // .setAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify.setDeleteImage(false);
                // curCauseUniqualify
                // .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                // curCauseUniqualify.setAttachFile(curItem.getAttachFile());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                // curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    private void setUnqualifyLdc() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemLdc
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItemLdc) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());

            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                // curCauseUniqualify
                // .setAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify.setDeleteImage(false);
                // curCauseUniqualify
                // .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                // curCauseUniqualify.setAttachFile(curItem.getAttachFile());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                // curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }

        }
    }

    /* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
    private void saveUnqualifyLdc() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemLdc
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItemLdc) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());

            if (curItem == null) {
				/* Them moi */
                if (curCauseUniqualify.isUnqulify()) {
                    Supervision_LBG_UnqualifyItemEntity addItem
                            = new Supervision_LBG_UnqualifyItemEntity();
                    addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
                            .getCat_Cause_Constr_Unqualify_Id());
                    addItem.setCause_Line_Bg_Id(this.curEditItemLdc
                            .getCause_Bts_Cat_Work_Id());
                    addItem.setUnqulify(true);
                    // addItem.setNewAttachFile(curCauseUniqualify
                    // .getNewAttachFile());
                    addItem.setLstNewAttachFile(curCauseUniqualify
                            .getLstNewAttachFile());
                    addItem.setTitle(curCauseUniqualify.getTitle());
                    curListUnqualify.add(addItem);
                }
            } else {
                if (curCauseUniqualify.isUnqulify()) {
                    curItem.setUnqulify(true);
                    curItem.setDelete(curCauseUniqualify.isDelete());
                    curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());

                } else {
					/* Danh dau xoa nguyen nhan khong dat */
                    curItem.setDelete(true);
                }
            }

        }
    }

    /* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
    private void saveUnqualifyTctd() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemTctd
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItemTctd) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());

            if (curItem == null) {
				/* Them moi */
                if (curCauseUniqualify.isUnqulify()) {
                    Supervision_LBG_UnqualifyItemEntity addItem
                            = new Supervision_LBG_UnqualifyItemEntity();
                    addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
                            .getCat_Cause_Constr_Unqualify_Id());
                    addItem.setCause_Line_Bg_Id(this.curEditItemTctd
                            .getCause_Bts_Cat_Work_Id());
                    addItem.setUnqulify(true);
                    // addItem.setNewAttachFile(curCauseUniqualify
                    // .getNewAttachFile());
                    addItem.setLstNewAttachFile(curCauseUniqualify
                            .getLstNewAttachFile());
                    addItem.setTitle(curCauseUniqualify.getTitle());
                    curListUnqualify.add(addItem);
                }
            } else {
                if (curCauseUniqualify.isUnqulify()) {
                    curItem.setUnqulify(true);
                    curItem.setDelete(curCauseUniqualify.isDelete());
                    curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());

                } else {
					/* Danh dau xoa nguyen nhan khong dat */
                    curItem.setDelete(true);
                }
            }

        }
    }

    private void setUnqualifyTctd() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemTctd
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItemTctd) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                // curCauseUniqualify
                // .setAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify.setDeleteImage(false);
                // curCauseUniqualify
                // .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                // curCauseUniqualify.setAttachFile(curItem.getAttachFile());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                // curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    /* Lay nghiem thu tu id */
    public Supervision_LBG_UnqualifyItemEntity getCauseAcceptFromList(
            List<Supervision_LBG_UnqualifyItemEntity> listData,
            long idCauseAccept) {
        for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listData) {
            if (curUnqualifyItem.getCat_Cause_Constr_Acceptance_Id() == idCauseAccept) {
                return curUnqualifyItem;
            }
        }
        return null;
    }

    /* Lay nguyen nhan loi tu id */
    public Supervision_LBG_UnqualifyItemEntity getCauseUnqualifyFromList(
            List<Supervision_LBG_UnqualifyItemEntity> listData,
            long idCauseUnqualify) {
        for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listData) {
            if (curUnqualifyItem.getCat_Cause_Constr_Unqualify_Id() == idCauseUnqualify) {
                return curUnqualifyItem;
            }
        }
        return null;
    }

    private void setHeader() {
        final Header myActionBar = (Header) spvBTS_PillarView.findViewById(R.id.actionbar);
        myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
        // icon back
        myActionBar.setBackAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoHomeActivity(new Bundle());
                finish();
            }

            @Override
            public int getDrawable() {
                return R.drawable.backicon;
            }
        });
        // buttom home
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoHomeActivity(new Bundle());
                finish();
            }

            @Override
            public int getDrawable() {
                return R.drawable.home;
            }
        });
		/* Dong bo du lieu */
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                requestSync(SupervisionBtsPillar.this);
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

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        gotoHomeActivity(new Bundle());
        finish();
    }

    public RelativeLayout getSupervision_bts_pillar_cb_chondgclc() {
        return supervision_bts_pillar_cb_chondgclc;
    }

    public void setSupervision_bts_pillar_cb_chondgclc(
            RelativeLayout supervision_bts_pillar_cb_chondgclc) {
        this.supervision_bts_pillar_cb_chondgclc = supervision_bts_pillar_cb_chondgclc;
    }

    public RelativeLayout getSupervision_bts_pillar_cb_dgclldc() {
        return supervision_bts_pillar_cb_dgclldc;
    }

    public void setSupervision_bts_pillar_cb_dgclldc(
            RelativeLayout supervision_bts_pillar_cb_dgclldc) {
        this.supervision_bts_pillar_cb_dgclldc = supervision_bts_pillar_cb_dgclldc;
    }

    public RelativeLayout getSupervision_bts_pillar_cb_nnkd_ldc() {
        return supervision_bts_pillar_cb_nnkd_ldc;
    }

    public void setSupervision_bts_pillar_cb_nnkd_ldc(
            RelativeLayout supervision_bts_pillar_cb_nnkd_ldc) {
        this.supervision_bts_pillar_cb_nnkd_ldc = supervision_bts_pillar_cb_nnkd_ldc;
    }

    public RelativeLayout getSupervision_bts_pillar_cb_trangthai() {
        return supervision_bts_pillar_cb_trangthai;
    }

    public void setSupervision_bts_pillar_cb_trangthai(
            RelativeLayout supervision_bts_pillar_cb_trangthai) {
        this.supervision_bts_pillar_cb_trangthai = supervision_bts_pillar_cb_trangthai;
    }

    public RelativeLayout getSupervision_bts_pillar_cb_nnkd_tctd() {
        return supervision_bts_pillar_cb_nnkd_tctd;
    }

    public void setSupervision_bts_pillar_cb_nnkd_tctd(
            RelativeLayout supervision_bts_pillar_cb_nnkd_tctd) {
        this.supervision_bts_pillar_cb_nnkd_tctd = supervision_bts_pillar_cb_nnkd_tctd;
    }

    //	private void requestSync() {
//		if (this.check3GWifi()) {
//			showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
//			Bundle bundle = new Bundle();
//			ActionEvent e = new ActionEvent();
//			e.action = ActionEventConstant.REQEST_SYNC;
//			e.viewData = bundle;
//			e.isBlockRequest = true;
//			e.sender = this;
//			Home_Controller.getInstance().handleViewEvent(e);
//		} else {
//			this.show3GWifiOffline();
//		}
//	}
    @Override
    public void onTabChanged(String tabId) {
        // TODO Auto-generated method stub
        int i = tabs.getCurrentTab();
        if (i == 0) {
            showCompleteDate(constr_ConstructionItem,
                    Constants.PROGRESS_TYPE.BTS_TYPE,
                    Constants.PROGRESS_TYPE.THI_CONG_COT_BTS,
                    supervision_bts_complete_date,
                    rl_supervision_bts_bt_complete);
        } else {
            if (i == 1) {
                showCompleteDate(constr_ConstructionItem,
                        Constants.PROGRESS_TYPE.BTS_TYPE,
                        Constants.PROGRESS_TYPE.LAP_DUNG_COT_ANTEN_BTS,
                        supervision_bts_complete_date,
                        rl_supervision_bts_bt_complete);
            } else {
                showCompleteDate(constr_ConstructionItem,
                        Constants.PROGRESS_TYPE.BTS_TYPE,
                        Constants.PROGRESS_TYPE.TIEP_DIA_BTS,
                        supervision_bts_complete_date,
                        rl_supervision_bts_bt_complete);
            }
        }
    }

    @Override
    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:

                refreshThicongcot();

                refreshLapdungcot();

                refreshTiepdia();

//			closeProgressDialog();
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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
