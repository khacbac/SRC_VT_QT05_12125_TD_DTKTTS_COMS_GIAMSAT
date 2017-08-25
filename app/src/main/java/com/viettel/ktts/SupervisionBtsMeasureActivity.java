package com.viettel.ktts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Cat_Supv_Constr_MeasureController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_EquipController;
import com.viettel.database.Supervision_Bts_MeasureController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Cat_Supv_Constr_MeasureEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_EquipEntity;
import com.viettel.database.entity.Supervision_Bts_MeasureEntity;
import com.viettel.database.field.Supervision_Bts_MeasureField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.control.Bts_ExpandlistAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SupervisionBtsMeasureActivity extends SupervisionBtsBaseActivity {
    private View spvBts_MeasureView;
    /* Hien thi popup */
    private Menu_DropdownPopup dropdownPopupMenuInfomation;
    private List<DropdownItemEntity> listInfomation = null;
    private Menu_DropdownPopup dropdownPopupMenuStatus;
    private List<DropdownItemEntity> listStatus = null;

    /**
     * combobox
     */
    // combobox loai thong tin
    private TextView supervision_bts_measure_tv_thietke;
    private RelativeLayout supervision_bts_measure_cb_status;
    private TextView supervision_bts_measure_tv_status;

    /**
     * textview
     */
    private TextView supervision_bts_tv_matram;
    private TextView supervision_bts_tv_mact;

    // edittext
    private EditText supervision_bts_et_kqdo;
    private EditText supervision_bts_et_donvi;
    private EditText supervision_bts_et_loaimaydo;
    private EditText supervision_bts_et_serial;
    private EditText supervision_bts_et_nguoido;
    private EditText supervision_bts_et_sdt;
    private Button save;

    // expandlist
    private ExpandableListView expListView;

    private LinearLayout viewLayout;

    private int isInfomation;
    private int isStatus = -1;
    private boolean outOfKey = false;

    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Supervision_BtsEntity btsEntity;

    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<Cat_Supv_Constr_MeasureEntity>> listDataChild;
    // private List<Cat_Supv_Constr_MeasureEntity> listConstrMeasure;
    private Cat_Supv_Constr_MeasureEntity curItemClick;
    private Supervision_Bts_MeasureEntity supvBtsMeasureEntity;
    private boolean isClick = false;
    private Supervision_Bts_EquipController btsEquipController;
    private Supervision_Bts_EquipEntity btsEquipEntity;

    View view = null;
    Animation animation;
    private ConfirmDialog confirmSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_bts_measure_activity);
        setTitle(getString(R.string.supervision_bts_title));
        initComponent();
//		setHeader();

        isInfomation = getIntent().getExtras().getInt(
                IntentConstants.INTENT_DESIGNINFO);
        initData();

        constr_ConstructionItem = getConstr_Construction_Employee();
        setData();
        closeProgressDialog();
        // new loadMore().execute();
    }

    public void initComponent() {
        spvBts_MeasureView = addView(R.layout.supervision_bts_measure_activity, R.id.rl_spv_bts_measure);
        // textview
        supervision_bts_tv_matram = (TextView) spvBts_MeasureView.findViewById(R.id.rl_supervision_bts_measure_tv_matram);
        supervision_bts_tv_mact = (TextView) spvBts_MeasureView.findViewById(R.id.rl_supervision_bts_measure_tv_mact);

        // combobox
        // supervision_bts_measure_cb_thietke = (RelativeLayout)
        // findViewById(R.id.rl_supervision_bts_measure_search_thietke);
        supervision_bts_measure_tv_thietke = (TextView) spvBts_MeasureView.findViewById(R.id.rl_supervision_bts_measure_tv_thietke);
        supervision_bts_measure_tv_thietke.setOnClickListener(this);

        // expandlist
        expListView = (ExpandableListView) spvBts_MeasureView.findViewById(R.id.rl_supervision_bts_measure_header_expandlist);

        viewLayout = (LinearLayout) spvBts_MeasureView.findViewById(R.id.rl_supervision_bts_measure_header);

        getView();
        // expListView.animate().translationX(400);
    }

    public void clearFocus() {
        supervision_bts_et_kqdo.setFocusableInTouchMode(false);
        supervision_bts_et_kqdo.clearFocus();

        supervision_bts_et_donvi.setFocusableInTouchMode(false);
        supervision_bts_et_donvi.clearFocus();

        supervision_bts_et_loaimaydo.setFocusableInTouchMode(false);
        supervision_bts_et_loaimaydo.clearFocus();

        supervision_bts_et_serial.setFocusableInTouchMode(false);
        supervision_bts_et_serial.clearFocus();

        supervision_bts_et_nguoido.setFocusableInTouchMode(false);
        supervision_bts_et_nguoido.clearFocus();

        supervision_bts_et_sdt.setFocusableInTouchMode(false);
        supervision_bts_et_sdt.clearFocus();

        supervision_bts_et_kqdo.setFocusableInTouchMode(true);
        supervision_bts_et_donvi.setFocusableInTouchMode(true);
        supervision_bts_et_loaimaydo.setFocusableInTouchMode(true);
        supervision_bts_et_serial.setFocusableInTouchMode(true);
        supervision_bts_et_nguoido.setFocusableInTouchMode(true);
        supervision_bts_et_sdt.setFocusableInTouchMode(true);
    }

    public void getView() {
        LayoutInflater infalInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = new View(this);
        view = infalInflater.inflate(
                R.layout.supervision_bts_measure_body_layout, null);

        setSupervision_bts_measure_cb_status((RelativeLayout) view
                .findViewById(R.id.rl_supervision_bts_measure_tl_row1_chontrangthai));

        supervision_bts_measure_tv_status = (TextView) view
                .findViewById(R.id.rl_supervision_bts_measure_tl_row1_tv_chontrangthai);
        supervision_bts_measure_tv_status.setOnClickListener(this);

        supervision_bts_et_kqdo = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row2_et_ketquado);
        supervision_bts_et_donvi = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row2_et_donvi);
        supervision_bts_et_loaimaydo = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row3_et_loaimaydo);
        supervision_bts_et_serial = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row3_et_serial);
        supervision_bts_et_nguoido = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row2_et_nguoido);
        supervision_bts_et_sdt = (EditText) view
                .findViewById(R.id.supervision_bts_measure_tl_row4_et_phone);
        save = (Button) view
                .findViewById(R.id.rl_supervision_bts_measurelayout_save);
        save.setOnClickListener(this);
    }

    public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
        return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);
    }

    public void initData() {
        /* Drop down */
        String sChoice = "";
        listInfomation = GloablUtils.getListBTSInfo(sChoice);
        sChoice = getResources().getString(R.string.text_choice1);
        supervision_bts_measure_tv_thietke.setText(GloablUtils
                .getStringBTSInfo(isInfomation));

        listStatus = GloablUtils.getListBtsStatusPillar(sChoice);

        supervision_bts_measure_tv_status.setText(sChoice);
    }

    public void setData() {
        // String sChoice = getResources().getString(R.string.text_choice1);

        supervision_bts_tv_matram.setText(constr_ConstructionItem
                .getStationCode());
        supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
                .getConstrCode()));

        Supervision_BtsController bts_Controller = new Supervision_BtsController(
                this);

        btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
                .getSupervision_Constr_Id());

        btsEquipController = new Supervision_Bts_EquipController(this);
        btsEquipEntity = btsEquipController            .getSupervision_Bts_EquipEntity(btsEntity.getSupervision_Bts_Id());

        handleExpandlist();

    }

    public void handleExpandlist() {
        prepareListData();

        listAdapter = new Bts_ExpandlistAdapter(this, listDataHeader,
                listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.expandGroup(0, true);
        // expListView.expandGroup(1, true);
        animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // expListView.setAnimation(animation);

        // animation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        expListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent,
                                                View v, int groupPosition, int childPosition,
                                                long id) {

                        isClick = true;
                        curItemClick = listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition);
                        Supervision_Bts_MeasureController measureController = new Supervision_Bts_MeasureController(
                                SupervisionBtsMeasureActivity.this);

                        supvBtsMeasureEntity = measureController
                                .getBtsMeasureEntity(btsEntity
                                        .getSupervision_Bts_Id(), curItemClick
                                        .getCat_Supv_Constr_Measure_Id());

                        Log.i("hihi", curItemClick.getMeasure_Code());

                        getView();

                        if (btsEquipEntity != null) {
                            if (curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_2G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_2G)) {
                                if (btsEquipEntity.get_2g_TOTAL() == -1l ||
                                        btsEquipEntity.get_2g_TOTAL() == 0l) {
                                    save.setVisibility(View.GONE);
                                }
                            }
                            if (curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_3G)
                                    || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_3G)) {
                                if (btsEquipEntity.get_3g_TOTAL() == -1l ||
                                        btsEquipEntity.get_3g_TOTAL() == 0l) {
                                    save.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            save.setVisibility(View.GONE);
                        }

                        if (curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_CAPACITY_VIBA)
                                || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_OBTAIN_VIBA)
                                || curItemClick.getMeasure_Code().equals(Constants.BTS_CONSTR_MEASURE.WORK_CODE_BER_VIBA)) {
                            save.setVisibility(View.VISIBLE);
                        }

                        // set du lien len form tu database
                        if (supvBtsMeasureEntity != null) {
                            supervision_bts_measure_tv_status.setText(GloablUtils
                                    .getStringBtsCatWorkStatus(supvBtsMeasureEntity
                                            .getMeasure_Status()));
                            isStatus = supvBtsMeasureEntity.getMeasure_Status();
                            supervision_bts_et_kqdo.setText(StringUtil
                                    .formatNumber(supvBtsMeasureEntity
                                            .getMeasure_VALUE()));
                            supervision_bts_et_donvi
                                    .setText(supvBtsMeasureEntity
                                            .getMeasure_GROUP());
                            supervision_bts_et_loaimaydo
                                    .setText(supvBtsMeasureEntity
                                            .getMeasure_MACHINE_TYPE());
                            supervision_bts_et_serial
                                    .setText(supvBtsMeasureEntity
                                            .getMeasure_MACHINE_SERIAL());
                            supervision_bts_et_nguoido
                                    .setText(supvBtsMeasureEntity
                                            .getMeasure_PERSON());
                            supervision_bts_et_sdt.setText(supvBtsMeasureEntity
                                    .getMeasure_PERSON_MOBILE());
                        } else {

                            isStatus = Constants.ID_ENTITY_DEFAULT;
                            String sChoice = getResources().getString(
                                    R.string.text_choice1);
                            supervision_bts_measure_tv_status.setText(sChoice);
                        }

                        viewLayout.removeAllViews();

                        viewLayout.addView(view);

                        view.setAnimation(animation);

                        if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
                                || constr_ConstructionItem
                                .getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED
                                || constr_ConstructionItem
                                .getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
                            save.setVisibility(View.GONE);
                        }
                        if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
                                && btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
                            save.setVisibility(View.GONE);
                        }
                        return false;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (isClick) {
            isClick = false;
            viewLayout.removeAllViews();

            handleExpandlist();

            viewLayout.addView(expListView);
        } else {
            gotoHomeActivity(new Bundle());
            finish();
        }
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Cat_Supv_Constr_MeasureEntity>>();

        // Tieu de cho expandlist
        listDataHeader.add(StringUtil
                .getString(R.string.bts_measure_tile_expandlist_feeder_2G));
        listDataHeader.add(StringUtil
                .getString(R.string.bts_measure_tile_expandlist_feeder_3G));
        listDataHeader.add(StringUtil
                .getString(R.string.bts_measure_tile_expandlist_viba));

        Cat_Supv_Constr_MeasureController constrMeasureController = new Cat_Supv_Constr_MeasureController(
                this);

        //2g
        Cat_Supv_Constr_MeasureEntity constrMeasureAll2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumper2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureFeeder2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureConnecter2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureAnten2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_2G);

        //3g
        Cat_Supv_Constr_MeasureEntity constrMeasureAll3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumper3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureFeeder3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureConnecter3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureAnten3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_3G);

        // them du lieu vao tung group title
        List<Cat_Supv_Constr_MeasureEntity> feeder2G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
        feeder2G.add(constrMeasureAll2G);
        feeder2G.add(constrMeasureJumper2G);
        feeder2G.add(constrMeasureFeeder2G);
        feeder2G.add(constrMeasureJumperFeeder2G);
        feeder2G.add(constrMeasureConnecter2G);
        feeder2G.add(constrMeasureAnten2G);
        feeder2G.add(constrMeasureResoantAnten2G);

        List<Cat_Supv_Constr_MeasureEntity> feeder3G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
        feeder3G.add(constrMeasureAll3G);
        feeder3G.add(constrMeasureJumper3G);
        feeder3G.add(constrMeasureFeeder3G);
        feeder3G.add(constrMeasureJumperFeeder3G);
        feeder3G.add(constrMeasureConnecter3G);
        feeder3G.add(constrMeasureAnten3G);
        feeder3G.add(constrMeasureResoantAnten3G);

        List<Cat_Supv_Constr_MeasureEntity> viba = new ArrayList<Cat_Supv_Constr_MeasureEntity>();

        Cat_Supv_Constr_MeasureEntity constrMeasureCapacityViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_CAPACITY_VIBA);

        Cat_Supv_Constr_MeasureEntity constrMeasureObtainViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_OBTAIN_VIBA);

        Cat_Supv_Constr_MeasureEntity constrMeasureBerViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_BER_VIBA);

        viba.add(constrMeasureCapacityViba);
        viba.add(constrMeasureObtainViba);
        viba.add(constrMeasureBerViba);

        listDataChild.put(listDataHeader.get(0), feeder2G); // Header, Child data
        listDataChild.put(listDataHeader.get(1), feeder3G);
        listDataChild.put(listDataHeader.get(2), viba);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // click combobox thong tin
            case R.id.rl_supervision_bts_measure_tv_thietke:

                this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
                        this.listInfomation);

                dropdownPopupMenuInfomation.show(v);
                break;
            // click status chon trang thai
            case R.id.rl_supervision_bts_measure_tl_row1_tv_chontrangthai:

                this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this,
                        this.listStatus);

                dropdownPopupMenuStatus.show(v);
                break;
            // click save
            case R.id.rl_supervision_bts_measurelayout_save:
                if (!GlobalInfo.getInstance().isCheckIn()) {
                    showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
                    break;
                }
                if (isStatus < 0) {
                    supervision_bts_measure_tv_status
                            .setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                    Toast.makeText(
                            getApplicationContext(),
                            StringUtil
                                    .getString(R.string.supervision_bts_measure_tl_row2_status_is_null),
                            Toast.LENGTH_LONG).show();

                    clearFocus();
                    // this.showDialog(StringUtil
                    // .getString(R.string.supervision_bts_measure_field_null));
                } else if (StringUtil.isNullOrEmpty(supervision_bts_et_kqdo
                        .getText().toString())) {
                    supervision_bts_et_kqdo
                            .setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                    supervision_bts_et_kqdo.requestFocus();
                    Toast.makeText(
                            getApplicationContext(),
                            StringUtil
                                    .getString(R.string.supervision_bts_measure_tl_row2_ketquado_is_null),
                            Toast.LENGTH_LONG).show();
                } else if (StringUtil.isNullOrEmpty(supervision_bts_et_donvi
                        .getText().toString())) {
                    supervision_bts_et_donvi
                            .setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                    supervision_bts_et_donvi.requestFocus();
                    Toast.makeText(
                            getApplicationContext(),
                            StringUtil
                                    .getString(R.string.supervision_bts_measure_tl_row2_donvi_is_null),
                            Toast.LENGTH_LONG).show();
                } else if (StringUtil.isNullOrEmpty(supervision_bts_et_serial
                        .getText().toString())) {
                    supervision_bts_et_serial
                            .setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                    supervision_bts_et_serial.requestFocus();
                    Toast.makeText(
                            getApplicationContext(),
                            StringUtil
                                    .getString(R.string.supervision_bts_measure_tl_row2_serial_is_null),
                            Toast.LENGTH_LONG).show();
                } else if (StringUtil.isNullOrEmpty(supervision_bts_et_nguoido
                        .getText().toString())) {
                    supervision_bts_et_nguoido
                            .setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.field_is_null)
                                            + "</font>"));
                    supervision_bts_et_nguoido.requestFocus();
                    Toast.makeText(
                            getApplicationContext(),
                            StringUtil
                                    .getString(R.string.supervision_bts_measure_tl_row2_nguoido_is_null),
                            Toast.LENGTH_LONG).show();
                } else {
                    if (!StringUtil.isNullOrEmpty(supervision_bts_et_sdt.getText()
                            .toString())) {

                        if (!isValidPhoneNumber(supervision_bts_et_sdt.getText()
                                .toString())) {
                            supervision_bts_et_sdt.setError(Html
                                    .fromHtml("<font color='green'>"
                                            + getString(R.string.fomat_is_wrong)
                                            + "</font>"));
                            supervision_bts_et_sdt.requestFocus();
                            Toast.makeText(
                                    getApplicationContext(),
                                    StringUtil
                                            .getString(R.string.supervision_bts_measure_tl_row2_sdt_is_null),
                                    Toast.LENGTH_LONG).show();

                            return;
                        }
                    }

                    confirmSave = new ConfirmDialog(this,
                            StringUtil.getString(R.string.text_confirm_save));
                    confirmSave.show();

                }

                break;
            default:
                break;
        }
    }

    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (target == null || TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

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

                    this.supervision_bts_measure_tv_thietke.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuInfomation.dismiss();

                    this.showProgressDialog(StringUtil
                            .getString(R.string.text_loading));

                    Bundle bundleMonitorData = new Bundle();
                    bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
                            constr_ConstructionItem);
                    bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
                            isInfomation);

                    gotoBtsActivity(bundleMonitorData, isInfomation);

                    finish();
                }
                if (typeItem.equals(Constants.DROPDOWN_TYPE.STATUS_PILLAR)) {
                    isStatus = dropdownItem.getId();
                    if (isStatus >= 0) {
                        this.supervision_bts_measure_tv_status.setError(null,
                                ic_combo);
                    }
                    this.supervision_bts_measure_tv_status.setText(dropdownItem
                            .getTitle());
                    this.dropdownPopupMenuStatus.dismiss();

                }
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                break;
            case OnEventControlListener.EVENT_CONFIRM_OK:
                // this.saveData();
                SaveAsyncTask task = new SaveAsyncTask();
                task.execute();
                break;
            default:
                super.onEvent(eventType, control, data);
                break;
        }
    }

    class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
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
                Toast.makeText(SupervisionBtsMeasureActivity.this,
                        StringUtil.getString(R.string.text_out_of_key),
                        Toast.LENGTH_SHORT).show();
                closeProgressDialog();
                return;
            }

            viewLayout.removeAllViews();

            viewLayout.addView(expListView);

            Toast toast = Toast.makeText(SupervisionBtsMeasureActivity.this,
                    getResources().getString(R.string.text_update_success),
                    Toast.LENGTH_LONG);

            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            handleExpandlist();
            closeProgressDialog();
        }

    }

    public Supervision_Bts_MeasureEntity getBtsMeasureEntity() {
        Supervision_Bts_MeasureEntity result = new Supervision_Bts_MeasureEntity();
        result.setMeasure_Status(isStatus);
        // ket qua do
        result.setMeasure_VALUE(Float.parseFloat(supervision_bts_et_kqdo
                .getText().toString()));
        // serial may
        result.setMeasure_MACHINE_SERIAL(supervision_bts_et_serial.getText()
                .toString());
        // loai may
        result.setMeasure_MACHINE_TYPE(supervision_bts_et_loaimaydo.getText()
                .toString());
        // don vi
        result.setMeasure_GROUP(supervision_bts_et_donvi.getText().toString());
        // nguoi do
        result.setMeasure_PERSON(supervision_bts_et_nguoido.getText()
                .toString());
        // so dien thoai
        result.setMeasure_PERSON_MOBILE(supervision_bts_et_sdt.getText()
                .toString());
        // id bts
        result.setSupervision_Bts_Id(btsEntity.getSupervision_Bts_Id());
        // id giam sat do kiem
        result.setCat_Supv_Constr_Measure_Id(curItemClick
                .getCat_Supv_Constr_Measure_Id());
        return result;
    }

    public void saveData() {

        try {
            long idUser = GlobalInfo.getInstance().getUserId();
            Supervision_Bts_MeasureController btsMeasureController = new Supervision_Bts_MeasureController(
                    this);
            Supervision_Bts_MeasureEntity btsMeasureEntity = getBtsMeasureEntity();

            if (supvBtsMeasureEntity == null) {
                btsMeasureEntity.setSync_Status(Constants.SYNC_STATUS.ADD);
                btsMeasureEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
                btsMeasureEntity.setProcessId(0);
                btsMeasureEntity.setEmployeeId(idUser);
                btsMeasureEntity.setSupervisionConstrId(btsEntity
                        .getSupervision_ConstrEntity()
                        .getSupervision_Constr_Id());

                long idMeasure = Ktts_KeyController
                        .getInstance()
                        .getKttsNextKey(Supervision_Bts_MeasureField.TABLE_NAME);

                if (idMeasure == 0) {
                    outOfKey = true;
                    return;
                } else
                    outOfKey = false;

                btsMeasureEntity.setSupervision_BTS_MEASURE_ID(idMeasure);
                btsMeasureController.insertBtsMeasureEntity(btsMeasureEntity);
            } else {
                if (supvBtsMeasureEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
                    btsMeasureEntity.setSync_Status(Constants.SYNC_STATUS.EDIT);
                } else {
                    btsMeasureEntity.setSync_Status(supvBtsMeasureEntity
                            .getSync_Status());
                }
                btsMeasureController.updateBtsMeasureEntity(btsMeasureEntity,
                        supvBtsMeasureEntity.getSupervision_BTS_MEASURE_ID());
            }

            //cap nhat trang thai cong trinh
            Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
                    this);
            constr_Controller.updateSyncStatus(constr_ConstructionItem.getSupervision_Constr_Id());

            // TODO: Thiet lap ket luan. DanhDue ExOICTIF
            boolean bDeny = checkCauseDeny(constr_ConstructionItem);
            Log.i("Check_Deny", String.valueOf(bDeny));
            // Toast.makeText(getApplicationContext(), String.valueOf(checkCauseDeny(constr_ConstructionEmployeeItem)), Toast.LENGTH_LONG).show();
            if (bDeny) {
                constr_Controller.updateStatus(
                        constr_ConstructionItem.getSupervision_Constr_Id(), 0);
            } else {
                constr_Controller.updateStatus(
                        constr_ConstructionItem.getSupervision_Constr_Id(), 1);
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            // /this.showDialog(StringUtil.getString(R.string.text_update_error));
            // Toast toast = Toast.makeText(this,
            // getResources().getString(R.string.text_update_error),
            // Toast.LENGTH_LONG);
            //
            // toast.setGravity(Gravity.CENTER, 0, 0);
            // toast.show();
        }

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    private void setHeader() {
        final Header myActionBar = (Header) spvBts_MeasureView.findViewById(R.id.actionbar);
        myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
        // icon back
        myActionBar.setBackAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                if (isClick) {
                    isClick = false;
                    viewLayout.removeAllViews();

                    handleExpandlist();

                    viewLayout.addView(expListView);
                } else {
                    gotoHomeActivity(new Bundle());
                    finish();
                }

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
                requestSync(SupervisionBtsMeasureActivity.this);
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

    public RelativeLayout getSupervision_bts_measure_cb_status() {
        return supervision_bts_measure_cb_status;
    }

    public void setSupervision_bts_measure_cb_status(
            RelativeLayout supervision_bts_measure_cb_status) {
        this.supervision_bts_measure_cb_status = supervision_bts_measure_cb_status;
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
    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:
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
}
