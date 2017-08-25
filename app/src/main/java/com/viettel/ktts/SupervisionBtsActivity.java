package com.viettel.ktts;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.Constants.BTS_PILLAR_ANTEN_TYPE;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Cause_Bts_Pillar_AntenController;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Pillar_AntenController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.service.GpsServices;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupervisionBtsActivity extends SupervisionBtsBaseActivity {

	private View spvBTSView;
	/* Hien thi popup */
	private Menu_DropdownPopup dropdownPopupMenuInfomation;
	private Menu_DropdownPopup dropdownPopupMenuDesignType;
	private Menu_DropdownPopup dropdownPopupMenuAssess;
	private Menu_DropdownPopup dropdownPopupMenuPillarAnten;
	private Menu_DropdownPopup dropdownPopupMenuPillarType;
	private Menu_DropdownPopup dropdownPopupMenuHouseType;
	private Menu_DropdownPopup dropdownPopupMenuFactoryType;
	private Menu_DropdownPopup dropdownPopupMenuFoundNumType;
	private Menu_DropdownPopup dropdownPopupMenuConstructionType;

	private List<DropdownItemEntity> listInfomation = null;
	private List<DropdownItemEntity> listDesignType = null;
	private List<DropdownItemEntity> listAssess = null;
	private List<DropdownItemEntity> listPillarAnten = null;
	private List<DropdownItemEntity> listPillarType = null;
	private List<DropdownItemEntity> listHouseType = null;
	private List<DropdownItemEntity> listFactoryType = null;
	private List<DropdownItemEntity> listConstructionType = null;

	private List<DropdownItemEntity> listFoundNum = null;

	/* Component */
	/**
	 * combobox
	 */

	// combobox chon loai xay dung
	private TextView cb_supervision_bts_chon_loai_xay_dung;
	// combobox loai thong tin
	private TextView supervision_bts_cb_tv_thietke;
	// combobox loai thiet ke
	private TextView supervision_bts_cb_tv_loaithietke;
	// combobox loai danh gia
	private TextView supervision_bts_cb_tv_chondanhgia;
	// combobox loai cot anten
	private TextView supervision_bts_cb_tv_chonanten;
	// combobox loai cot
	private TextView supervision_bts_cb_tv_chonloaicot;
	// combobox loai nha
	private TextView supervision_bts_cb_tv_chonloainha;
	// combobox loai nha may no
	private TextView supervision_bts_cb_tv_chonnhamayno;

	// combobox so mong co
	// so mong co
	private TextView supervision_bts_cb_tv_somongco;

	/**
	 * edit text
	 */
	// loai tram
	private EditText trl_supervision_bts_tv_chon_loai_tram;
	// dien giai
	private EditText supervision_bts_body_et_diengiai;
	// do cao
	private EditText supervision_bts_body_et_docao;
	// so mong co
	// private EditText supervision_bts_body_et_somongco;
	// ten nha
	private EditText supervision_bts_body_et_tennha;

	// thoi gian trien khai du kien
	private EditText supervision_bts_body_et_deployday;

	/**
	 * text view
	 */
	// hien thi ma tram
	private TextView supervision_bts_tv_matram;
	// hien thi ma cong trinh
	private TextView supervision_bts_tv_mact;

	/**
	 * button
	 */
	private Button save;

	private int isInfomation = 1;
	private int isDesignType = -1;
	private int isAssess = -1;
	private int isPillarAnten = -1;
	private int isPillarType = -1;
	private int isHouseType = -1;
	private int isFactory = -1;
	private int isFoundNum = -1;
	private int isConstructionType = -1;

	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;
	private Supervision_ConstrController supervision_ConstrController;
	private Supervision_ConstrEntity supervision_ConstrData;
	private List<Cause_Bts_Pillar_AntenEntity> listDataPillar;
	private Supervision_BtsEntity bts_Entity;
	private Supervision_BtsController bts_Controller;
	private Cause_Bts_Pillar_AntenController btsPillarAntenController;
	private ConfirmDialog confirmSave;

	// hunkgm - get lat long form constr_contruction table
	private Constr_ConstructionController constr_ConstructionController;
	private Constr_ConstructionEntity constr_ConstructionData;

	// private JSONArray jsonHgDataTest;
	private static final String TAG = "SupervisionBtsActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
		// setContentView(R.layout.supervision_constr_bts);
		setTitle(getString(R.string.supervision_bts_title));
		initComponent();
		// setHeader();
		initData();

		constr_ConstructionItem = getConstr_Construction_Employee();

		setData();

		closeProgressDialog();

	}

	public void initComponent() {
		spvBTSView = addView(R.layout.supervision_constr_bts,
				R.id.rl_spv_constr);
		// textview
		supervision_bts_tv_matram = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_tv_mact);

		// button
		save = (Button) spvBTSView
				.findViewById(R.id.rl_supervision_bts_body_row6_1_bt_save);
		save.setOnClickListener(this);

		btsPillarAntenController = new Cause_Bts_Pillar_AntenController(this);
		// edittext
		trl_supervision_bts_tv_chon_loai_tram = (EditText) spvBTSView
				.findViewById(R.id.trl_supervision_bts_body_row2_tv_chon_loai_tram);
		supervision_bts_body_et_diengiai = (EditText) spvBTSView
				.findViewById(R.id.trl_supervision_bts_body_row2_et_diengiai);
		registerForContextMenu(supervision_bts_body_et_diengiai);
		supervision_bts_body_et_docao = (EditText) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row3_1_et_docao);
		supervision_bts_body_et_tennha = (EditText) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row4_1_et_tennha);
		// thoi gian trien khai du kien
		supervision_bts_body_et_deployday = (EditText) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row4_1_et_deployday);

		// combobox
		cb_supervision_bts_chon_loai_xay_dung = (TextView) spvBTSView
				.findViewById(R.id.cb_supervision_bts_body_row2_tv_chon_loai_xay_dung);
		cb_supervision_bts_chon_loai_xay_dung.setOnClickListener(this);

		supervision_bts_cb_tv_thietke = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_tv_thietke);
		supervision_bts_cb_tv_thietke.setOnClickListener(this);

		supervision_bts_cb_tv_loaithietke = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_body_tv_chonthietke);
		supervision_bts_cb_tv_loaithietke.setOnClickListener(this);

		// supervision_bts_cb_chondanhgia = (RelativeLayout)
		// findViewById(R.id.rl_supervision_bts_body_chondanhgia);
		supervision_bts_cb_tv_chondanhgia = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_body_tv_chondanhgia);
		supervision_bts_cb_tv_chondanhgia.setOnClickListener(this);

		supervision_bts_cb_tv_chonanten = (TextView) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row3_1_tv_chonanten);
		supervision_bts_cb_tv_chonanten.setOnClickListener(this);

		// supervision_bts_cb_chonloaicot = (RelativeLayout)
		// findViewById(R.id.ll_supervision_bts_body_row3_1_chonloaicot);

		supervision_bts_cb_tv_chonloaicot = (TextView) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row3_1_tv_chonloaicot);
		supervision_bts_cb_tv_chonloaicot.setOnClickListener(this);

		// supervision_bts_cb_chonloainha = (RelativeLayout)
		// findViewById(R.id.ll_supervision_bts_body_row4_1_chonloainha);
		supervision_bts_cb_tv_chonloainha = (TextView) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row4_1_tv_chonloainha);
		supervision_bts_cb_tv_chonloainha.setOnClickListener(this);

		// supervision_bts_cb_chonnhamayno = (RelativeLayout)
		// findViewById(R.id.rl_supervision_bts_body_row5_1_chonnhamayno);
		supervision_bts_cb_tv_chonnhamayno = (TextView) spvBTSView
				.findViewById(R.id.rl_supervision_bts_body_row5_1_tv_chonnhamayno);
		supervision_bts_cb_tv_chonnhamayno.setOnClickListener(this);

		supervision_bts_cb_tv_somongco = (TextView) spvBTSView
				.findViewById(R.id.ll_supervision_bts_body_row4_1_et_somongco);
		supervision_bts_cb_tv_somongco.setOnClickListener(this);

		bts_Controller = new Supervision_BtsController(this);

		listDataPillar = new ArrayList<Cause_Bts_Pillar_AntenEntity>();

	}

	public void clearFocus() {
		supervision_bts_body_et_diengiai.setFocusableInTouchMode(false);
		supervision_bts_body_et_diengiai.clearFocus();

		supervision_bts_body_et_docao.setFocusableInTouchMode(false);
		supervision_bts_body_et_docao.clearFocus();

		supervision_bts_body_et_tennha.setFocusableInTouchMode(false);
		supervision_bts_body_et_tennha.clearFocus();

		supervision_bts_body_et_deployday.setFocusableInTouchMode(false);
		supervision_bts_body_et_deployday.clearFocus();

		supervision_bts_body_et_diengiai.setFocusableInTouchMode(true);
		supervision_bts_body_et_docao.setFocusableInTouchMode(true);
		supervision_bts_body_et_tennha.setFocusableInTouchMode(true);
		supervision_bts_body_et_deployday.setFocusableInTouchMode(true);
	}

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	public void setData() {

		String sChoice = getResources().getString(R.string.text_choice1);

		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		Log.d("vietpc","getStationCode:"+ constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));
		Log.d("vietpc","getConstrCode:"+ constr_ConstructionItem
				.getConstrCode());
		trl_supervision_bts_tv_chon_loai_tram.setText(GloablUtils
				.getStringStationType((int) constr_ConstructionItem
						.getCatStationTypeId()));

		this.supervision_ConstrController = new Supervision_ConstrController(
				this);
		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		this.supervision_ConstrData = this.supervision_ConstrController
				.getItem(constr_ConstructionItem.getSupervision_Constr_Id());
		constr_ConstructionController = new Constr_ConstructionController(this);
		constr_ConstructionData = constr_ConstructionController
				.getItem(supervision_ConstrData.getConstruct_Id());

		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		if (btsEntity != null) {
			listDataPillar = btsPillarAntenController
					.getListCauseBts_PillarAntenEntity(btsEntity
							.getSupervision_Bts_Id());
			isDesignType = btsEntity.getSupervision_ConstrEntity()
					.getDesign_Type();

			isConstructionType = btsEntity.getConstructionType();

			// neu loai tram la cosite, thi loai xay dung nhan gia tri la nang
			// cap
			// va ko dc chon
			if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {

				cb_supervision_bts_chon_loai_xay_dung
						.setText(GloablUtils
								.getStringConstructionBtsType(Constants.CONSTRUCTION_TYPE.NANG_CAP));
				cb_supervision_bts_chon_loai_xay_dung.setEnabled(false);
			} else {
				if (isConstructionType > 0) {
					cb_supervision_bts_chon_loai_xay_dung.setText(GloablUtils
							.getStringConstructionBtsType(isConstructionType));
				} else {
					cb_supervision_bts_chon_loai_xay_dung.setText(sChoice);
				}

				// neu khong phai cosite thi cho phep chon loai xay dung la xay
				// moi hay nang cap
				cb_supervision_bts_chon_loai_xay_dung.setEnabled(true);
			}

			if (isDesignType < 1) {
				supervision_bts_cb_tv_loaithietke.setText(sChoice);
			} else
				supervision_bts_cb_tv_loaithietke.setText(GloablUtils
						.getStringBtsDesignType(isDesignType));

			isAssess = btsEntity.getSupervision_ConstrEntity()
					.getDesign_Assess();
			if (isAssess < 1) {
				supervision_bts_cb_tv_chondanhgia.setText(sChoice);
			} else
				supervision_bts_cb_tv_chondanhgia.setText(GloablUtils
						.getStringBtsDesignAssess(isAssess));

			supervision_bts_body_et_diengiai.setText(btsEntity
					.getSupervision_ConstrEntity().getDesign_Description());

			if (supervision_ConstrData.getDeployExpectedDay() > 0) {
				supervision_bts_body_et_deployday
						.setText(String.valueOf(supervision_ConstrData
								.getDeployExpectedDay()));
			}

			isPillarAnten = btsEntity.getPillar_ANTEN();
			if (isPillarAnten < 1) {
				supervision_bts_cb_tv_chonanten.setText(sChoice);
			} else
				supervision_bts_cb_tv_chonanten.setText(GloablUtils
						.getStringBtsPillarAnten(isPillarAnten));

			isPillarType = btsEntity.getPillar_ANTEN_TYPE();
			if (isPillarType < 1) {
				supervision_bts_cb_tv_chonloaicot.setText(sChoice);
			} else {

				supervision_bts_cb_tv_chonloaicot.setText(GloablUtils
						.getStringBtsPillarType(isPillarType));
			}

			supervision_bts_body_et_docao.setText(StringUtil
					.formatNumber((btsEntity.getPillar_HIGH())));

			isFoundNum = btsEntity.getFoundation_NUM();
			if (isFoundNum < 1) {
				supervision_bts_cb_tv_somongco.setText(sChoice);
			} else {

				supervision_bts_cb_tv_somongco.setText(GloablUtils
						.getStringBtsFoundNum(isFoundNum));
			}

			if (isPillarType == Constants.BTS_PILLAR_ANTEN_TYPE.TU_DUNG) {
				isFoundNum = 0;
				this.supervision_bts_cb_tv_somongco.setText("0");
				this.supervision_bts_cb_tv_somongco.setEnabled(false);
			} else {

				this.supervision_bts_cb_tv_somongco.setEnabled(true);
			}

			supervision_bts_body_et_tennha.setText(btsEntity.getHouse_NAME());

			isHouseType = btsEntity.getHouse_TYPE();
			if (isHouseType < 1) {
				supervision_bts_cb_tv_chonloainha.setText(sChoice);
			} else
				supervision_bts_cb_tv_chonloainha.setText(GloablUtils
						.getStringBtsHouseType(isHouseType));

			isFactory = btsEntity.getHouse_GENERATOR();
			if (isFactory < 1) {
				supervision_bts_cb_tv_chonnhamayno.setText(sChoice);
			} else
				supervision_bts_cb_tv_chonnhamayno.setText(GloablUtils
						.getStringBtsFactoryType(isFactory));
		}

		/* Set endable va disable voi cong trinh da hoan thanh */
		if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.save.setVisibility(View.GONE);
		}

		// showAlertDialogCheckInRequire(this,
		// "Current Location: "+GpsServices.latLocation+", "
		// +GpsServices.longLocation+"\n Constr Location: "
		// +constr_ConstructionData.getLatitude()+", "+constr_ConstructionData.getLongtitude(),
		// "Close");
	}

	public String checkValidation() {
		String sResult = "";

		if (isDesignType < 0) {
			sResult = StringUtil
					.getString(R.string.line_background_design_type_is_null);

			supervision_bts_cb_tv_loaithietke.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			supervision_bts_cb_tv_loaithietke.requestFocus();
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;

		}

		if (isAssess < 0) {
			sResult = StringUtil
					.getString(R.string.line_background_design_assess_is_null);

			supervision_bts_cb_tv_chondanhgia.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		if (isConstructionType <= 0) {
			sResult = StringUtil
					.getString(R.string.line_background_construction_type_is_null);

			cb_supervision_bts_chon_loai_xay_dung.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			cb_supervision_bts_chon_loai_xay_dung.requestFocus();
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;

		}

		//
		if (isPillarAnten < 0) {
			sResult = StringUtil.getString(R.string.bts_pillar_assess_is_null);

			supervision_bts_cb_tv_chonanten.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		if (isPillarType < 0) {
			sResult = StringUtil.getString(R.string.bts_pillar_type_is_null);

			supervision_bts_cb_tv_chonloaicot.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		if (isPillarType != Constants.BTS_PILLAR_ANTEN_TYPE.TU_DUNG
				&& isFoundNum == 0) {
			sResult = StringUtil.getString(R.string.bts_found_num_is_null);

			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		if (supervision_bts_body_et_docao.getText().toString().isEmpty()) {
			sResult = StringUtil.getString(R.string.bts_pillar_hight_is_null);

			supervision_bts_body_et_docao.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			supervision_bts_body_et_docao.requestFocus();
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			return sResult;
		}

		if (isPillarType != BTS_PILLAR_ANTEN_TYPE.TU_DUNG) {
			if (isFoundNum < 0) {
				sResult = StringUtil
						.getString(R.string.bts_pillar_found_num_is_null);

				supervision_bts_cb_tv_somongco
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				// tv_constr_line_background_design_type_dropdown.re
				Toast.makeText(getApplicationContext(), sResult,
						Toast.LENGTH_LONG).show();
				clearFocus();
				return sResult;
			}
		}

		if (isHouseType < 0) {
			sResult = StringUtil
					.getString(R.string.bts_pillar_house_type_is_null);

			supervision_bts_cb_tv_chonloainha.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		// check thoi gian trien khai du kien
		if (supervision_bts_body_et_deployday.getText().toString().isEmpty()) {
			sResult = StringUtil.getString(R.string.bts_deploy_day_is_null);

			supervision_bts_body_et_deployday.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			supervision_bts_body_et_deployday.requestFocus();
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			return sResult;
		}

		if (Integer.parseInt(supervision_bts_body_et_deployday.getText()
				.toString()) <= 0) {
			sResult = StringUtil.getString(R.string.bts_deploy_day_is_integer);

			supervision_bts_body_et_deployday.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_background_design_type_dropdown.re
			Toast.makeText(getApplicationContext(), sResult, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return sResult;
		}

		return sResult;
	}

	@Override
	public void onClick(View v) {
		if (!GlobalInfo.getInstance().isCheckIn()
				&& v.getId() != R.id.btn_popup_cancel
				&& v.getId() != R.id.btn_popup_save_plan) {
			showAlertDialogCheckInRequire(this,
					getString(R.string.checkin_require),
					getString(R.string.text_close));
			return;
		}

		switch (v.getId()) {
		// click save
		case R.id.rl_supervision_bts_body_row6_1_bt_save:
			String sValid = this.checkValidation();
			if (!GlobalInfo.getInstance().isCheckIn()) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.checkin_require),
						getString(R.string.text_close));
				break;
			}
			if (StringUtil.isNullOrEmpty(sValid)) {
				confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}

			// ConfirmDialog confirmSave = new ConfirmDialog(this,
			// StringUtil.getString(R.string.text_confirm_save));
			// confirmSave.show();

			break;
		// click combobox thong tin
		case R.id.rl_supervision_bts_tv_thietke:
			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
			break;
		// click combobox loai thiet ke
		case R.id.rl_supervision_bts_body_tv_chonthietke:
			this.dropdownPopupMenuDesignType = new Menu_DropdownPopup(this,
					this.listDesignType);
			dropdownPopupMenuDesignType.show(v);
			break;
		// click combobox danh gia
		case R.id.rl_supervision_bts_body_tv_chondanhgia:
			this.dropdownPopupMenuAssess = new Menu_DropdownPopup(this,
					this.listAssess);
			dropdownPopupMenuAssess.show(v);
			break;
		// click combobox loai xay dung
		case R.id.cb_supervision_bts_body_row2_tv_chon_loai_xay_dung:
			this.dropdownPopupMenuConstructionType = new Menu_DropdownPopup(
					this, this.listConstructionType);
			dropdownPopupMenuConstructionType.show(v);
			break;

		// click combobox cot anten
		case R.id.ll_supervision_bts_body_row3_1_tv_chonanten:
			this.dropdownPopupMenuPillarAnten = new Menu_DropdownPopup(this,
					this.listPillarAnten);
			dropdownPopupMenuPillarAnten.show(v);
			break;
		// click combobox chon loai cot
		case R.id.ll_supervision_bts_body_row3_1_tv_chonloaicot:
			this.dropdownPopupMenuPillarType = new Menu_DropdownPopup(this,
					this.listPillarType);
			dropdownPopupMenuPillarType.show(v);
			break;
		// click combobox chon loai nha
		case R.id.ll_supervision_bts_body_row4_1_tv_chonloainha:
			this.dropdownPopupMenuHouseType = new Menu_DropdownPopup(this,
					this.listHouseType);
			dropdownPopupMenuHouseType.show(v);
			break;
		// click combobox chon nha may no
		case R.id.rl_supervision_bts_body_row5_1_tv_chonnhamayno:
			this.dropdownPopupMenuFactoryType = new Menu_DropdownPopup(this,
					this.listFactoryType);
			dropdownPopupMenuFactoryType.show(v);
			break;

		// click combobox chon so mong co
		case R.id.ll_supervision_bts_body_row4_1_et_somongco:
			this.dropdownPopupMenuFoundNumType = new Menu_DropdownPopup(this,
					this.listFoundNum);
			dropdownPopupMenuFoundNumType.show(v);
			break;
		// click chon save tren popup check in
		case R.id.btn_popup_save_plan:
			if (edtMakePlan.getText().toString().trim().length() <= 0) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.input_plan_require),
						getString(R.string.text_close));
				break;
			} else if (addnewSupvLocatetoDB()) {
				isCheckIn = !isCheckIn;
				GlobalInfo.getInstance().setCheckIn(isCheckIn);
				changeMenuCheckIn(R.drawable.icon_checkout,
						getString(R.string.check_out));
				startService();
				popupWindow.dismiss();
			}
			break;
		// dong popup check in
		case R.id.btn_popup_cancel:
			popupWindow.dismiss();
			break;

		default:
			break;
		}
	}

	public boolean addnewSupvLocatetoDB() {
		long supv_locate_id = Ktts_KeyController.getInstance().getKttsNextKey(
				Supervision_Locate_Field.TABLE_NAME);
		if (supv_locate_id == 0) {
			showAlertDialogCheckInRequire(SupervisionBtsActivity.this,
					getString(R.string.text_out_of_key),
					getString(R.string.text_close_button));
			return false;
		}
		GlobalInfo.getInstance().setCheckInTBId(this, supv_locate_id);
		// update new data
		Supervision_Locate_Entity supvEntity = new Supervision_Locate_Entity();
		supvEntity.setIsActive(1);
		// set new id
		supvEntity.setLocateId(supv_locate_id);
		supvEntity.setPlan(edtMakePlan.getText().toString());
		supvEntity.setCheckinDate(new Date(System.currentTimeMillis()));
		supvEntity.setCheckinLatitude(String.valueOf(GpsServices.latLocation));
		supvEntity.setCheckinLongtitude(String
				.valueOf(GpsServices.longLocation));
		supvEntity.setSuppervisionId(constr_ConstructionItem
				.getSupervision_Constr_Id());
		supvEntity.setEmployeeId(GlobalInfo.getInstance().getUserId());
		// supvEntity.setProvinceId(constr_ConstructionData.getProvinceCode());
		supvEntity.setProcessId(0);
		supvEntity.setSync_Status(0);
		return supv_locate_controller.insertSupervisionLocateEntity(supvEntity);
	}

	@Override
	public void actionCheckInButton() {
		super.actionCheckInButton();
		// isCheckIn = !isCheckIn;
		// GlobalInfo.getInstance().setCheckIn(isCheckIn);
		// kiểm tra điều kiện check in
		boolean canCheckIn = false;
		String alertMessage = "";
		// reqLat = Double.parseDouble(constr_ConstructionData.getLatitude());
		// reqLong =
		// Double.parseDouble(constr_ConstructionData.getLongtitude());
		if (constr_ConstructionData.getLatitude() != null
				&& constr_ConstructionData.getLongtitude() != null) {
			reqLat = (constr_ConstructionData.getLatitude()
					.equalsIgnoreCase("")) ? -1.0 : Double
					.parseDouble(constr_ConstructionData.getLatitude());
			reqLong = (constr_ConstructionData.getLongtitude()
					.equalsIgnoreCase("")) ? -1.0 : Double
					.parseDouble(constr_ConstructionData.getLongtitude());
		} else {
			reqLat = -1;
			reqLong = -1;
		}
		Log.d("ktts_checkin", "req latlng: " + reqLat + ", " + reqLong);

		showPopupPlan((View) findViewById(R.id.action_checkin));
	}

	public void getSupvConstr() {
		if (supervision_ConstrData == null) {
			supervision_ConstrData = new Supervision_ConstrEntity();
		}
		supervision_ConstrData.setConstruct_Id(constr_ConstructionItem
				.getConstructId());
		supervision_ConstrData.setDesign_Type(isDesignType);
		supervision_ConstrData.setDesign_Assess(isAssess);
		supervision_ConstrData
				.setDesign_Description(supervision_bts_body_et_diengiai
						.getText().toString());
		supervision_ConstrData.setDeployExpectedDay(Integer
				.parseInt(supervision_bts_body_et_deployday.getText()
						.toString()));
		supervision_ConstrData
				.setSupervision_Progress(Constants.SUPERVISION_PROGRESS.DOING);

	}

	private void saveData() {
		try {

			long idUser = GlobalInfo.getInstance().getUserId();

			getSupvConstr();

			bts_Entity = getSupervisionBtsEntity();

			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);

			supervision_ConstrData
					.setSupervision_Constr_Id(constr_ConstructionItem
							.getSupervision_Constr_Id());

			boolean bupdatedesign = constr_Controller
					.updateDesign(supervision_ConstrData);
			/* Loi khong load lai duoc do khong update duoc thiet ke */
			if (!bupdatedesign) {
				throw new Exception("2001");
			}

			if (btsEntity != null) {

				if (btsEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
					bts_Entity.setSync_Status(Constants.SYNC_STATUS.EDIT);
				} else {
					bts_Entity.setSync_Status(btsEntity.getSync_Status());
				}
				bts_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);

				if (isFoundNum != -1
						&& (isFoundNum + 1) < listDataPillar.size()
						&& listDataPillar.size() > 0) {

					this.showAskOptionDialog(
							StringUtil.getString(R.string.text_delete_title),
							getResources().getString(
									R.string.text_confirm_found_num,
									listDataPillar.size()));
					return;
				} else {
					bts_Controller.updateSupervisionBts(
							btsEntity.getSupervision_Bts_Id(), bts_Entity);

					Toast toast = Toast.makeText(this, getResources()
							.getString(R.string.update_database_complete),
							Toast.LENGTH_LONG);

					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}

			} else {
				bts_Entity.getSupervision_ConstrEntity()
						.setSupervision_Constr_Id(
								constr_ConstructionItem
										.getSupervision_Constr_Id());

				long idSupervion = Ktts_KeyController.getInstance()
						.getKttsNextKey(Supervision_BtsField.TABLE_NAME);

				if (idSupervion == 0) {
					Toast.makeText(this,
							StringUtil.getString(R.string.text_out_of_key),
							Toast.LENGTH_SHORT).show();
					return;
				}

				bts_Entity.setSupervision_Bts_Id(idSupervion);
				bts_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
				bts_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
				bts_Entity.setProcessId(0);
				bts_Entity.setEmployeeId(idUser);

				bts_Controller.insertSupervisionBts(bts_Entity);
				Toast toast = Toast.makeText(
						this,
						getResources().getString(
								R.string.insert_database_complete),
						Toast.LENGTH_LONG);

				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}

			btsEntity = bts_Controller
					.getSupervisionBts(constr_ConstructionItem
							.getSupervision_Constr_Id());

			supervision_bts_body_et_docao.setText(StringUtil
					.formatNumber((btsEntity.getPillar_HIGH())));

		} catch (Exception e) {
			Log.e("error", e.toString());
			this.showDialog(StringUtil.getString(R.string.text_update_error));
		}
	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		for (int i = isFoundNum + 1; i < listDataPillar.size(); i++) {
			deletePillarAntenRow(listDataPillar.get(i));
		}

		listDataPillar = btsPillarAntenController
				.getListCauseBts_PillarAntenEntity(btsEntity
						.getSupervision_Bts_Id());

		bts_Controller.updateSupervisionBts(btsEntity.getSupervision_Bts_Id(),
				bts_Entity);

		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		supervision_bts_body_et_docao.setText(StringUtil
				.formatNumber((btsEntity.getPillar_HIGH())));

		Toast toast = Toast.makeText(this,
				getResources().getString(R.string.update_database_complete),
				Toast.LENGTH_LONG);

		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}

	public Supervision_BtsEntity getSupervisionBtsEntity() {
		Supervision_BtsEntity btsEntity = new Supervision_BtsEntity();

		btsEntity.setPillar_ANTEN(isPillarAnten);
		btsEntity.setPillar_ANTEN_TYPE(isPillarType);
		btsEntity
				.setPillar_HIGH(Double
						.parseDouble(supervision_bts_body_et_docao.getText()
								.toString()));
		if (isFoundNum != -1) {
			btsEntity.setFoundation_NUM(Integer
					.parseInt(supervision_bts_cb_tv_somongco.getText()
							.toString()));
		} else {
			btsEntity.setFoundation_NUM(isFoundNum);
		}

		btsEntity.setHouse_NAME(supervision_bts_body_et_tennha.getText()
				.toString().trim());
		btsEntity.setHouse_TYPE(isHouseType);
		btsEntity.setHouse_GENERATOR(isFactory);
		btsEntity.setConstructionType(isConstructionType);
		return btsEntity;
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

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				if (btsEntity != null
						&& this.supervision_ConstrData.getDeployExpectedDay() > 0) {
					this.supervision_bts_cb_tv_thietke.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuInfomation.dismiss();
				}
				if (isInfomation == Constants.BTS_INFO.THIET_TKE_INFO) {

					gotoSupervisionBtsActivity(bundleMonitorData);
				} else {
					if (this.supervision_ConstrData.getDeployExpectedDay() == 0) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_deploy_day_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}
				}
				if (isInfomation == Constants.BTS_INFO.NHAT_KY_INFO) {
					gotoNhatkyBtsActivity(bundleMonitorData);
				}

				if (isInfomation == Constants.BTS_INFO.TIEN_DO_INFO) {
					gotoTiendoBtsActivity(bundleMonitorData);
				}


				if (isInfomation == Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO) {

					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsPillarActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.KEO_DIEN_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO) {

					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsEquipActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.LAP_DAT_VIBA_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsVibaActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsMeasureActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.MEASURE_CONSTR_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionMeasureConstrActivity(bundleMonitorData);
				}

				// HungTN add new 24/08/2016
				if (isInfomation == Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionCNDTCActivity(bundleMonitorData);
				}
				if (isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG) {
					// hungtn test comment
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionCNVCActivity(bundleMonitorData);
				}
				// ---

				if (isInfomation == Constants.BTS_INFO.KET_LUAN_INFO) {
					if (btsEntity == null) {
						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_infomation_design_not_fill));
						this.dropdownPopupMenuInfomation.dismiss();
						return;
					}

					gotoSupervisionBtsKLActivity(bundleMonitorData);

				}

				this.showProgressDialog(StringUtil
						.getString(R.string.text_loading));
				finish();

			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_TYPE)) {
				isDesignType = dropdownItem.getId();
				if (this.isDesignType > 0) {
					supervision_bts_cb_tv_loaithietke.setError(null, ic_combo);
				}
				this.supervision_bts_cb_tv_loaithietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuDesignType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_ASSESS)) {
				isAssess = dropdownItem.getId();
				if (this.isAssess > 0) {
					supervision_bts_cb_tv_chondanhgia.setError(null, ic_combo);
				}
				this.supervision_bts_cb_tv_chondanhgia.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuAssess.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.CONSTRUCTION_TYPE)) {
				isConstructionType = dropdownItem.getId();
				if (this.isConstructionType > 0) {
					cb_supervision_bts_chon_loai_xay_dung.setError(null,
							ic_combo);
				}
				this.cb_supervision_bts_chon_loai_xay_dung.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuConstructionType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.POSITION_PILLAR_ANTEN)) {
				isPillarAnten = dropdownItem.getId();
				if (this.isPillarAnten > 0) {
					supervision_bts_cb_tv_chonanten.setError(null, ic_combo);
				}
				this.supervision_bts_cb_tv_chonanten.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuPillarAnten.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE)) {
				isPillarType = dropdownItem.getId();
				if (this.isPillarType > 0) {
					supervision_bts_cb_tv_chonloaicot.setError(null, ic_combo);

					if (isPillarType == Constants.BTS_PILLAR_ANTEN_TYPE.TU_DUNG) {
						isFoundNum = 0;
						this.supervision_bts_cb_tv_somongco.setText("0");
						this.supervision_bts_cb_tv_somongco.setEnabled(false);
					} else {
						// this.supervision_bts_cb_tv_somongco.setText(getResources().getString(R.string.text_choice1));
						this.supervision_bts_cb_tv_somongco.setEnabled(true);
					}
				}
				this.supervision_bts_cb_tv_chonloaicot.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuPillarType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.HOUSE_TYPE)) {
				isHouseType = dropdownItem.getId();
				if (this.isHouseType > 0) {
					supervision_bts_cb_tv_chonloainha.setError(null, ic_combo);
				}
				this.supervision_bts_cb_tv_chonloainha.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuHouseType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.FACTORY_TYPE)) {
				isFactory = dropdownItem.getId();
				// if (this.isFactory > 0) {
				// supervision_bts_cb_tv_chonnhamayno.setError(null,
				// ic_combo);
				// }
				this.supervision_bts_cb_tv_chonnhamayno.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuFactoryType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.FOUND_NUM)) {
				isFoundNum = dropdownItem.getId();
				if (this.isFoundNum > 0) {
					supervision_bts_cb_tv_somongco.setError(null, ic_combo);
				}
				this.supervision_bts_cb_tv_somongco.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuFoundNumType.dismiss();
			}
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			break;
		// click confirm yes dialog chac chan them du lieu vao database
		case OnEventControlListener.EVENT_CONFIRM_OK:
			// saveData();
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
			super.onPreExecute();
			confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					saveData();

				}
			});

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			closeProgressDialog();
		}

	}

	public void deletePillarAntenRow(Cause_Bts_Pillar_AntenEntity itemDelete) {
		Supervision_Bts_Pillar_AntenController btsPillarController = new Supervision_Bts_Pillar_AntenController(
				this);

		btsPillarController.deleteSupervision_Bts_PillarAntenEntity(itemDelete
				.getSupervision_Bts_Pillar_AntenEntity());

	}

	public void initData() {
		/* Drop down */
		String sChoice = "";
		listInfomation = GloablUtils.getListBTSInfo(sChoice);

		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_cb_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

		listDesignType = GloablUtils.getListBtsDesignType(sChoice);
		supervision_bts_cb_tv_loaithietke.setText(sChoice);

		listAssess = GloablUtils.getListBtsDesignAssess(sChoice);
		supervision_bts_cb_tv_chondanhgia.setText(sChoice);

		listPillarAnten = GloablUtils.getListBtsPosPillarAnten(sChoice);
		supervision_bts_cb_tv_chonanten.setText(sChoice);

		listPillarType = GloablUtils.getListBtsPillarType(sChoice);
		supervision_bts_cb_tv_chonloaicot.setText(sChoice);

		listHouseType = GloablUtils.getListBtsHouseType(sChoice);
		supervision_bts_cb_tv_chonloainha.setText(sChoice);

		listFactoryType = GloablUtils.getListBtsFactoryType(sChoice);
		supervision_bts_cb_tv_chonnhamayno.setText(sChoice);

		listFoundNum = GloablUtils.getListBtsFoundNum(sChoice);
		supervision_bts_cb_tv_somongco.setText(sChoice);

		listConstructionType = GloablUtils.getListConstructionBtsType(sChoice);
		cb_supervision_bts_chon_loai_xay_dung.setText(sChoice);

	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBTSView
				.findViewById(R.id.actionbar);
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
				requestSync(SupervisionBtsActivity.this);
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
		super.onBackPressed();
		gotoHomeActivity(new Bundle());
		finish();
	}

	// private void requestSync() {
	//
	// if (this.check3GWifi()) {
	// showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
	// Bundle bundle = new Bundle();
	// ActionEvent e = new ActionEvent();
	// e.action = ActionEventConstant.REQEST_SYNC;
	// e.viewData = bundle;
	// e.isBlockRequest = true;
	// e.sender = this;
	// Home_Controller.getInstance().handleViewEvent(e,
	// SupervisionBtsActivity.this);
	// } else {
	// this.show3GWifiOffline();
	// }
	// }

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:
			// closeProgressDialog();
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
