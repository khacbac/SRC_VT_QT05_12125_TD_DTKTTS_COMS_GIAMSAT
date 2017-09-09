package com.viettel.ktts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
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
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_HangController;
import com.viettel.database.Supervision_Line_Hg_FiberController;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Line_Hg_FiberEntity;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.field.Supervision_Line_HangField;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.service.GpsServices;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.Date;
import java.util.List;

/**
 * giam sat thiet ke tuyen treo
 *
 * @author datht1
 *
 */

public class Supervision_Line_HG_DesignActivity extends LineBaseActivity {
	private final String TAG = this.getClass().getSimpleName();
	private View spvLineHG_DesignView;
	private TabHost tabHost;
	private int tabHeight = 40;
	private int tabWidth = 50;
	/* controll */
	private TextView tv_constr_line_hang_dropdown;
	private TextView tv_constr_line_hang_info_line_value;
	private TextView tv_constr_line_hang_info_line_station_code_value;
	private TextView tv_constr_line_hang_design_type_dropdown;
	private TextView tv_constr_line_hang_review_dropdown;
	private EditText edt_constr_line_hang_inter_value;
	private EditText edt_constr_line_hang_long_value;
	private EditText edt_constr_line_hang_startcode_value;
	private EditText edt_constr_line_hang_endcode_value;
	private Button btn_constr_line_hang_save;
	/* control be */
	private EditText edt_line_hang_design_pillar_size;
	private CheckBox rbt_line_hang_design_pillar_be_tong;
	private CheckBox rbt_line_hang_design_pillar_go;
	private EditText edt_line_hang_design_pillar_chieu_cao;
	private EditText edt_line_hang_design_pillar_long_new;
	private EditText edt_line_hang_design_pillar_size_old;
	private EditText edt_line_hang_design_pillar_long_old;
	/* control cable */
	private TextView edt_constr_line_hang_loai_cap;
	private EditText edt_constr_line_hang_khoang_vuot;
	private EditText edt_constr_line_hang_tong_mx;
	private EditText edt_constr_line_hang_deployday_value;
	/* bien dropdown */
	private int iDesignInfo = 0;
	private int iDesignType = -1;
	private int iDesignAssess = -1;
	private int iCableType = -1;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listDesignType = null;
	private List<DropdownItemEntity> listDesignAssess = null;
	private List<DropdownItemEntity> listCableType = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Menu_DropdownPopup dropdownPopupMenuDesignType;
	private Menu_DropdownPopup dropdownPopupMenuDesignAssess;
	private Menu_DropdownPopup dropdownPopupMenuCableType;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_ConstrEntity supervision_ConstrData;
	private Supervision_Line_HangEntity supervision_Line_HangData;
	private Supervision_ConstrController supervision_ConstrController;
	private Supervision_Line_HangController supervision_Line_HangController;
	private Supervision_Line_Hg_FiberController supervisionLineFiberController;
	private List<Supervision_Line_Hg_FiberEntity> listLineHGFiber;

	// private boolean fiberNumChange = false;

	//hungkm -checkin
	private Constr_ConstructionController constr_ConstructionController;
	private Constr_ConstructionEntity constr_ConstructionData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_hang_activity);

		setTitle(getString(R.string.line_hang_header_title));
		this.initView();
//		setHeader();
		initTabHost();
	}

	@Override
	public void onClick(View v) {
		if(!GlobalInfo.getInstance().isCheckIn() && v.getId()!=R.id.btn_popup_cancel && v.getId()!=R.id.btn_popup_save_plan){
			showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
			return;
		}
		switch (v.getId()) {
			case R.id.tv_constr_line_hang_dropdown:
				this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
						this.listDesignInfo);
				dropdownPopupMenuDesignInfo.show(v);
				break;
			case R.id.tv_constr_line_hang_design_type_dropdown:

				this.dropdownPopupMenuDesignType = new Menu_DropdownPopup(this,

						this.listDesignType);
				dropdownPopupMenuDesignType.show(v);
				break;
			case R.id.tv_constr_line_hang_review_dropdown:
				this.dropdownPopupMenuDesignAssess = new Menu_DropdownPopup(this,

						this.listDesignAssess);
				dropdownPopupMenuDesignAssess.show(v);
				break;
			case R.id.tv_constr_line_hang_lc_dropdown:
				this.dropdownPopupMenuCableType = new Menu_DropdownPopup(this,

						this.listCableType);
				dropdownPopupMenuCableType.show(v);
				break;
			case R.id.rbt_line_hang_design_pillar_be_tong:
				rbt_line_hang_design_pillar_be_tong.setChecked(true);
				rbt_line_hang_design_pillar_go.setChecked(false);
				break;
			case R.id.rbt_line_hang_design_pillar_go:
				rbt_line_hang_design_pillar_be_tong.setChecked(false);
				rbt_line_hang_design_pillar_go.setChecked(true);
				break;
			case R.id.btn_constr_line_hang_save:
			/* Ghi thong tin */
			/* hien thi confirm ghi thong tin */
				boolean bValid = this.checkValid();
				if (bValid) {
					ConfirmDialog confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();

				}
				// else {
				// Toast.makeText(this, sValid, Toast.LENGTH_SHORT).show();
				// }
				break;
			// click chon save tren popup check in
			case R.id.btn_popup_save_plan:
				if(edtMakePlan.getText().toString().trim().length()<=0){
					showAlertDialogCheckInRequire(this, getString(R.string.input_plan_require), getString(R.string.text_close));
					break;
				}else if (addnewSupvLocatetoDB()) {
					isCheckIn = !isCheckIn;
					GlobalInfo.getInstance().setCheckIn(isCheckIn);
					changeMenuCheckIn(R.drawable.icon_checkout, getString(R.string.check_out));
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

	@Override
	public void actionCheckInButton() {
		super.actionCheckInButton();
		boolean canCheckIn = false;
		String alertMessage = "";
		if(constr_ConstructionData.getLatitude()!=null && constr_ConstructionData.getLongtitude()!=null){
			reqLat = (constr_ConstructionData.getLatitude().equalsIgnoreCase(""))?-1.0:Double.parseDouble(constr_ConstructionData.getLatitude());
			reqLong = (constr_ConstructionData.getLongtitude().equalsIgnoreCase(""))?-1.0:Double.parseDouble(constr_ConstructionData.getLongtitude());
		}else {
			reqLat = -1;
			reqLong = -1;
		}
		Log.d("ktts_checkin", "req latlng: "+reqLat+", "+reqLong);
		/*if(GlobalInfo.getInstance().getCheckInTBId(this)<0){
			canCheckIn = true;
		}else if ((reqLat < 0) || (reqLong < 0) ) {
			canCheckIn = false;
			alertMessage = getString(R.string.checkin_gps_require);
		}else if (!CheckInUtil.checkDistanceBetween2Point(reqLat, reqLong)) {
			canCheckIn = false;
			alertMessage = getString(R.string.checkin_distance_require);
		}else {
			canCheckIn = true;
		}*/



		//else {
		//	long startTimeLater = supv_locate_controller.getSupvLocateItem(GlobalInfo.getInstance().getCheckInTBId(this)).getCheckinDate().getTime();
		//	long diffTime  = System.currentTimeMillis() - startTimeLater;
		//	if(diffTime < CheckInService.distance_require_time){
		//		canCheckIn = false;
		//		alertMessage = getString(R.string.checkin_time_require);
		//	} else {
		//		canCheckIn = true;
		//	}
		//}

		//Log.d("hungkm", "can checkin : "+canCheckIn);
		//if(!canCheckIn){
		//	showAlertDialogCheckInRequire(this, alertMessage, getString(R.string.text_close));
		//}else{
		//	showPopupPlan((View) findViewById(R.id.action_checkin));
		//}

		showPopupPlan((View) findViewById(R.id.action_checkin));
	}

	public boolean addnewSupvLocatetoDB(){
		long supv_locate_id = Ktts_KeyController.getInstance().getKttsNextKey(Supervision_Locate_Field.TABLE_NAME);
		if(supv_locate_id==0){
			showAlertDialogCheckInRequire(Supervision_Line_HG_DesignActivity.this,
					getString(R.string.text_out_of_key),
					getString(R.string.text_close_button));
			return false;
		}
		GlobalInfo.getInstance().setCheckInTBId(this, supv_locate_id);
		//update new data
		Supervision_Locate_Entity supvEntity = new Supervision_Locate_Entity();
		supvEntity.setIsActive(1);
		//set new id
		supvEntity.setLocateId(supv_locate_id);
		supvEntity.setPlan(edtMakePlan.getText().toString());
		supvEntity.setCheckinDate(new Date(System.currentTimeMillis()));
		supvEntity.setCheckinLatitude(String.valueOf(GpsServices.latLocation));
		supvEntity.setCheckinLongtitude(String.valueOf(GpsServices.longLocation));
		supvEntity.setSuppervisionId(itemData.getSupervision_Constr_Id());
		supvEntity.setEmployeeId(GlobalInfo.getInstance().getUserId());
//		supvEntity.setProvinceId(constr_ConstructionData.getProvinceCode());
		supvEntity.setProcessId(0);
		supvEntity.setSync_Status(0);
		return supv_locate_controller.insertSupervisionLocateEntity(supvEntity);
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineHG_DesignView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_HG_DesignActivity.this);
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

	private void initView() {
		spvLineHG_DesignView = addView(R.layout.supervision_line_hang_activity, R.id.rl_supervision_line_hg_design);
		this.tv_constr_line_hang_dropdown = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_dropdown);
		this.tv_constr_line_hang_dropdown.setOnClickListener(this);

		this.tv_constr_line_hang_info_line_value = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_info_line_value);

		this.tv_constr_line_hang_info_line_station_code_value = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_info_line_station_code_value);

		this.tv_constr_line_hang_design_type_dropdown = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_design_type_dropdown);
		this.tv_constr_line_hang_design_type_dropdown.setText(getResources()
				.getString(R.string.text_choice1));
		this.tv_constr_line_hang_design_type_dropdown.setOnClickListener(this);

		this.tv_constr_line_hang_review_dropdown = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_review_dropdown);
		this.tv_constr_line_hang_review_dropdown.setText(getResources()
				.getString(R.string.text_choice1));
		this.tv_constr_line_hang_review_dropdown.setOnClickListener(this);

		this.edt_constr_line_hang_startcode_value = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_startcode_value);

		this.edt_constr_line_hang_endcode_value = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_endcode_value);

		this.edt_constr_line_hang_inter_value = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_inter_value);
		this.edt_constr_line_hang_deployday_value = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_deployday_value);

		registerForContextMenu(edt_constr_line_hang_inter_value);

		this.edt_constr_line_hang_long_value = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_long_value);

		this.btn_constr_line_hang_save = (Button) spvLineHG_DesignView
				.findViewById(R.id.btn_constr_line_hang_save);
		this.btn_constr_line_hang_save.setOnClickListener(this);
		/* cotrol cot */
		this.edt_line_hang_design_pillar_size = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_line_hang_design_pillar_size);

		this.rbt_line_hang_design_pillar_be_tong = (CheckBox) spvLineHG_DesignView
				.findViewById(R.id.rbt_line_hang_design_pillar_be_tong);
		this.rbt_line_hang_design_pillar_be_tong.setOnClickListener(this);

		this.rbt_line_hang_design_pillar_go = (CheckBox) spvLineHG_DesignView
				.findViewById(R.id.rbt_line_hang_design_pillar_go);
		this.rbt_line_hang_design_pillar_go.setOnClickListener(this);

		this.edt_line_hang_design_pillar_chieu_cao = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_line_hang_design_pillar_chieu_cao);

		this.edt_line_hang_design_pillar_long_new = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_line_hang_design_pillar_long_new);
		// this.setTextNumberFormat(this.edt_line_hang_design_pillar_long_new);

		this.edt_line_hang_design_pillar_size_old = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_line_hang_design_pillar_size_old);

		this.edt_line_hang_design_pillar_long_old = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_line_hang_design_pillar_long_old);
		/* control cable */

		this.edt_constr_line_hang_loai_cap = (TextView) spvLineHG_DesignView
				.findViewById(R.id.tv_constr_line_hang_lc_dropdown);
		edt_constr_line_hang_loai_cap.setOnClickListener(this);

		this.edt_constr_line_hang_tong_mx = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_tong_mx);

		this.edt_constr_line_hang_khoang_vuot = (EditText) spvLineHG_DesignView
				.findViewById(R.id.edt_constr_line_hang_khoang_vuot);

	}

	public void clearFocus() {
		edt_constr_line_hang_startcode_value.setFocusableInTouchMode(false);
		edt_constr_line_hang_startcode_value.clearFocus();

		edt_constr_line_hang_endcode_value.setFocusableInTouchMode(false);
		edt_constr_line_hang_endcode_value.clearFocus();

		edt_constr_line_hang_inter_value.setFocusableInTouchMode(false);
		edt_constr_line_hang_inter_value.clearFocus();

		edt_constr_line_hang_long_value.setFocusableInTouchMode(false);
		edt_constr_line_hang_long_value.clearFocus();

		edt_line_hang_design_pillar_size.setFocusableInTouchMode(false);
		edt_line_hang_design_pillar_size.clearFocus();

		edt_line_hang_design_pillar_chieu_cao.setFocusableInTouchMode(false);
		edt_line_hang_design_pillar_chieu_cao.clearFocus();

		edt_line_hang_design_pillar_long_new.setFocusableInTouchMode(false);
		edt_line_hang_design_pillar_long_new.clearFocus();

		edt_line_hang_design_pillar_size_old.setFocusableInTouchMode(false);
		edt_line_hang_design_pillar_size_old.clearFocus();

		edt_line_hang_design_pillar_long_old.setFocusableInTouchMode(false);
		edt_line_hang_design_pillar_long_old.clearFocus();

		edt_constr_line_hang_tong_mx.setFocusableInTouchMode(false);
		edt_constr_line_hang_tong_mx.clearFocus();

		edt_constr_line_hang_khoang_vuot.setFocusableInTouchMode(false);
		edt_constr_line_hang_khoang_vuot.clearFocus();

		edt_constr_line_hang_startcode_value.setFocusableInTouchMode(true);
		edt_constr_line_hang_endcode_value.setFocusableInTouchMode(true);
		edt_constr_line_hang_inter_value.setFocusableInTouchMode(true);
		edt_constr_line_hang_long_value.setFocusableInTouchMode(true);
		edt_line_hang_design_pillar_size.setFocusableInTouchMode(true);
		edt_line_hang_design_pillar_chieu_cao.setFocusableInTouchMode(true);
		edt_line_hang_design_pillar_long_new.setFocusableInTouchMode(true);
		edt_line_hang_design_pillar_size_old.setFocusableInTouchMode(true);
		edt_line_hang_design_pillar_long_old.setFocusableInTouchMode(true);
		edt_constr_line_hang_tong_mx.setFocusableInTouchMode(true);
		edt_constr_line_hang_khoang_vuot.setFocusableInTouchMode(true);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setData();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		gotoHomeActivity(new Bundle());
		finish();
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
					this.iDesignInfo = dropdownItem.getId();
					this.dropdownPopupMenuDesignInfo.dismiss();

					if (this.iDesignInfo == Constants.LINE_HANG_INFO.NHAT_KY_TIEN_DO_INFO /*|| this.iDesignInfo == Constants.LINE_HANG_INFO.TIEN_DO_INFO*/) {
						Bundle bundleData = new Bundle();
						bundleData.putSerializable(IntentConstants.INTENT_DATA,
								itemData);
						bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
								supervision_Line_HangData);
						bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
								this.iDesignInfo);
						this.gotoLineHangActivity(bundleData);
					} else {
						if (this.supervision_Line_HangData
								.getSupervision_Line_Hang_Id() > 0) {
							if (this.supervision_ConstrData.getDeployExpectedDay() == 0) {
								this.showDialog(StringUtil
										.getString(R.string.supervision_deploy_day_not_fill));
								return;
							}
							Bundle bundleData = new Bundle();
							bundleData.putSerializable(IntentConstants.INTENT_DATA,
									itemData);
							bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
									supervision_Line_HangData);
							bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
									this.iDesignInfo);
							this.gotoLineHangActivity(bundleData);
						} else {
							this.showDialog(StringUtil
									.getString(R.string.line_hang_design_noinfo_message));
						}
					}

					this.dropdownPopupMenuDesignInfo.dismiss();
				}
				if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_TYPE)) {
					this.iDesignType = dropdownItem.getId();

					if (this.iDesignType > 0) {
						tv_constr_line_hang_design_type_dropdown.setError(null,
								ic_combo);
					}

					this.tv_constr_line_hang_design_type_dropdown
							.setText(dropdownItem.getTitle());
					this.dropdownPopupMenuDesignType.dismiss();
				}
				if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_ASSESS)) {
					this.iDesignAssess = dropdownItem.getId();
					if (this.iDesignAssess > 0) {
						tv_constr_line_hang_review_dropdown
								.setError(null, ic_combo);
					}
					this.tv_constr_line_hang_review_dropdown.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignAssess.dismiss();
				}
				if (typeItem.equals(Constants.DROPDOWN_TYPE.CABLE_TYPE)) {
					this.iCableType = dropdownItem.getId();
					if (this.iCableType > 0) {
						edt_constr_line_hang_loai_cap.setError(null, ic_combo);
					}

					this.edt_constr_line_hang_loai_cap.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuCableType.dismiss();
				}
				getWindow().setSoftInputMode(
						WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
				break;
			case OnEventControlListener.EVENT_CONFIRM_OK:
				new SaveAsyncTask().execute();
				// this.saveDataDesign();

				break;
			default:
				super.onEvent(eventType, control, data);
				break;
		}
	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();

		// fiberNumChange = false;
		if (tabHost.getCurrentTab() == 0) {
			supervision_Line_HangController
					.updatePillar(supervision_Line_HangData);
		} else {
			for (int i = (iCableType); i < listLineHGFiber.size(); i++) {
				deleteFiberRow(listLineHGFiber.get(i));
			}
			supervision_Line_HangController
					.updateCable(supervision_Line_HangData);
		}

		Toast toast = Toast.makeText(this,
				getResources().getString(R.string.update_database_complete),
				Toast.LENGTH_LONG);

		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

		this.setData();
	}

	// xoa soi
	public void deleteFiberRow(Supervision_Line_Hg_FiberEntity itemDelete) {
		Supervision_Line_Hg_FiberController bgFiberController = new Supervision_Line_Hg_FiberController(
				this);

		bgFiberController.deleteHgFiberEntity(itemDelete);
	}

	private void setData() {
		try {

			// JSONArray jsonBtsTest = SqlliteSyncModel.getDataJsonSyncTest(
			// Supervision_Line_HangField.TABLE_NAME,
			// Supervision_Line_HangController.allColumn, null, 0,
			// Constants.NUMBER_MAX_ITEM_SYNC);

			Bundle bundleData = this.getIntent().getExtras();
			this.itemData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_ConstrController = new Supervision_ConstrController(
					this);
			this.supervision_Line_HangController = new Supervision_Line_HangController(
					this);
			this.supervision_ConstrData = this.supervision_ConstrController
					.getItem(this.itemData.getSupervision_Constr_Id());
			this.supervision_Line_HangData = supervision_Line_HangController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());
			// set data control cho ung dung
			if (this.supervision_ConstrData.getDesign_Type() > 0) {
				this.iDesignType = supervision_ConstrData.getDesign_Type();
				this.tv_constr_line_hang_design_type_dropdown
						.setText(GloablUtils.getStringDesignType(iDesignType));
			}
			this.edt_constr_line_hang_inter_value
					.setText(supervision_ConstrData.getDesign_Description());
			if (this.supervision_ConstrData.getDesign_Assess() > 0) {
				this.iDesignAssess = supervision_ConstrData.getDesign_Assess();

				this.tv_constr_line_hang_review_dropdown.setText(GloablUtils
						.getStringDesignAssess(iDesignAssess));
			}
			this.edt_constr_line_hang_startcode_value.setText(itemData
					.getStartPointCode());
			this.edt_constr_line_hang_endcode_value.setText(itemData
					.getEndPointCode());

			if (this.supervision_Line_HangData.getSupervision_Line_Hang_Id() > 0) {

				if (supervision_ConstrData.getDeployExpectedDay() > 0) {
					this.edt_constr_line_hang_deployday_value.setText(String
							.valueOf(supervision_ConstrData
									.getDeployExpectedDay()));
				}
				this.edt_constr_line_hang_long_value
						.setText(StringUtil
								.formatNumber(supervision_Line_HangData
										.getLong_Total()));

				if (supervision_Line_HangData.getPillar_New_Type() == Constants.LINE_HANG_PILLAR_NEW_TYPE.BE_TONG) {
					this.rbt_line_hang_design_pillar_be_tong.setChecked(true);
					this.rbt_line_hang_design_pillar_go.setChecked(false);
				} else if (supervision_Line_HangData.getPillar_New_Type() == Constants.LINE_HANG_PILLAR_NEW_TYPE.GO) {
					this.rbt_line_hang_design_pillar_be_tong.setChecked(false);
					this.rbt_line_hang_design_pillar_go.setChecked(true);
				}

				if (supervision_Line_HangData.getPillar_New_Total() >= 0) {
					this.edt_line_hang_design_pillar_size.setText(String
							.valueOf(supervision_Line_HangData
									.getPillar_New_Total()));
				}

				if (supervision_Line_HangData.getPillar_New_High() >= 0) {
					this.edt_line_hang_design_pillar_chieu_cao.setText(String
							.valueOf(supervision_Line_HangData
									.getPillar_New_High()));
				}

				if (supervision_Line_HangData.getPillar_New_Long() >= 0) {
					this.edt_line_hang_design_pillar_long_new
							.setText(StringUtil
									.formatNumber(supervision_Line_HangData
											.getPillar_New_Long()));
				}

				if (supervision_Line_HangData.getPillar_Old_Total() >= 0) {
					this.edt_line_hang_design_pillar_size_old.setText(String
							.valueOf(supervision_Line_HangData
									.getPillar_Old_Total()));
				}

				if (supervision_Line_HangData.getPillar_Old_Long() >= 0) {
					this.edt_line_hang_design_pillar_long_old
							.setText(StringUtil
									.formatNumber(supervision_Line_HangData
											.getPillar_Old_Long()));
				}

				this.iCableType = supervision_Line_HangData.getCable_Type();
				if (iCableType != -1) {
					this.edt_constr_line_hang_loai_cap.setText(String
							.valueOf(this.iCableType));
				}

				if (supervision_Line_HangData.getCable_Range() >= 0) {
					this.edt_constr_line_hang_khoang_vuot
							.setText(String.valueOf(supervision_Line_HangData
									.getCable_Range()));
				}

				if (supervision_Line_HangData.getMx_Total() >= 0) {
					this.edt_constr_line_hang_tong_mx.setText(String
							.valueOf(supervision_Line_HangData.getMx_Total()));
				}
				// chua do du lieu het

			}/**/

			this.tv_constr_line_hang_info_line_value.setText(this.itemData
					.getConstrCode());

			this.tv_constr_line_hang_info_line_station_code_value
					.setText(itemData.getStationCode());
			// Drop down

			String sChoice = getResources().getString(R.string.text_choice1);
			this.listDesignInfo = GloablUtils.getListLineHangInfo("");
			this.listDesignType = GloablUtils.getListLineDesignType(sChoice);
			this.listDesignAssess = GloablUtils
					.getListLineDesignAssess(sChoice);
			this.listCableType = GloablUtils.getListLineCableType(sChoice);

			/* Khong hien thi luu khi cong trinh da ket luan */
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_constr_line_hang_save.setVisibility(View.GONE);

			}
			//hungkm
			constr_ConstructionController = new Constr_ConstructionController(this);
			constr_ConstructionData = constr_ConstructionController.getItem(supervision_ConstrData.getConstruct_Id());
		} catch (Exception e) {
			e.printStackTrace();
			// this.gotoHomeActivity(new Bundle());
		}
	}

	private void initTabHost() {
		tabHost = (TabHost) spvLineHG_DesignView.findViewById(R.id.tabHost);
		tabHost.setup();
		String sPillar = getString(R.string.line_hang_design_pillar);
		TabSpec specPillar = tabHost.newTabSpec(sPillar);
		specPillar.setContent(R.id.ll_constr_line_hang_tab_pillar);
		specPillar.setIndicator(makeTabIndicator(sPillar));

		String sCable = getString(R.string.line_hang_design_cable);
		TabSpec specCable = tabHost.newTabSpec(sCable);
		specCable.setContent(R.id.ll_constr_line_hang_tab_cable);
		specCable.setIndicator(makeTabIndicator(sCable));
		tabHost.addTab(specPillar);
		tabHost.addTab(specCable);
	}

	private TextView makeTabIndicator(String text) {
		TelephonyManager manager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
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
		tabView.setWidth(tabWidth);
		tabView.setText(text);
		tabView.setTextColor(getResources().getColor(R.color.white_color));
		tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		tabView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tab_indicator));
		tabView.setPadding(13, 0, 13, 0);
		return tabView;
	}

	/**
	 * Ham check vailid du lieu truoc khi ghi
	 *
	 * @return
	 */
	private boolean checkValid() {
		String sError = "";
		if (this.iDesignType == Constants.SEARCH_VALUE_DEFAULT) {
			sError = StringUtil
					.getString(R.string.line_background_design_type_is_null);

			tv_constr_line_hang_design_type_dropdown.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			// tv_constr_line_hang_design_type_dropdown.requestFocus();
			Toast.makeText(getApplicationContext(), sError, Toast.LENGTH_LONG)
					.show();
			clearFocus();
			return false;
		}

		if (this.iDesignAssess == Constants.SEARCH_VALUE_DEFAULT) {
			sError = StringUtil
					.getString(R.string.line_background_design_assess_is_null);

			tv_constr_line_hang_review_dropdown.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			Toast.makeText(getApplicationContext(), sError, Toast.LENGTH_LONG)
					.show();

			clearFocus();
			return false;
		}

		String sLongValue = edt_constr_line_hang_long_value.getText()
				.toString().trim();
		if (StringUtil.isNullOrEmpty(sLongValue) || sLongValue.equals("0")) {
			sError = StringUtil
					.getString(R.string.line_background_design_long_is_null);
			if (sLongValue.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_equal_zero);
			}
			edt_constr_line_hang_long_value.setError(Html
					.fromHtml("<font color='green'>" + sError + "</font>"));
			edt_constr_line_hang_long_value.requestFocus();
			return false;
		}

		String deployValue = edt_constr_line_hang_deployday_value.getText()
				.toString().trim();

		if (StringUtil.isNullOrEmpty(deployValue) || deployValue.equals("0")) {
			sError = StringUtil.getString(R.string.field_is_null);
			if (deployValue.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_equal_zero);
			}
			edt_constr_line_hang_deployday_value.setError(Html
					.fromHtml("<font color='green'>" + sError + "</font>"));
			edt_constr_line_hang_deployday_value.requestFocus();
			return false;
		}

		if (tabHost.getCurrentTab() == 0) {
			String sPillarSize = edt_line_hang_design_pillar_size.getText()
					.toString().trim();
			if (StringUtil.isNullOrEmpty(sPillarSize)
					|| sPillarSize.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_null);
				if (sPillarSize.equals("0")) {
					sError = StringUtil.getString(R.string.field_is_equal_zero);
				}
				tabHost.setCurrentTab(0);
				edt_line_hang_design_pillar_size.setError(Html
						.fromHtml("<font color='green'>" + sError + "</font>"));
				edt_line_hang_design_pillar_size.requestFocus();
				return false;
			}

			if (StringUtil.isNullOrEmpty(edt_line_hang_design_pillar_chieu_cao
					.getText().toString())) {
				sError = StringUtil
						.getString(R.string.edt_line_hang_design_pillar_chieu_cao_is_null);
				tabHost.setCurrentTab(0);
				edt_line_hang_design_pillar_chieu_cao
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				edt_line_hang_design_pillar_chieu_cao.requestFocus();

				return false;
			}

			if (StringUtil.isNullOrEmpty(edt_line_hang_design_pillar_long_new
					.getText().toString())) {
				sError = StringUtil
						.getString(R.string.edt_line_hang_design_pillar_long_new_is_null);
				tabHost.setCurrentTab(0);
				edt_line_hang_design_pillar_long_new
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				edt_line_hang_design_pillar_long_new.requestFocus();

				// Toast.makeText(getApplicationContext(), sError,
				// Toast.LENGTH_LONG)
				// .show();
				return false;
			}
			/* Kiem tra thong tin chieu dai */
			// chieu dai tuyen
			double dTotalLong = Double.valueOf(edt_constr_line_hang_long_value
					.getText().toString());
			// chieu dai moi
			double dNewlong = Double
					.valueOf(edt_line_hang_design_pillar_long_new.getText()
							.toString());
			// chieu dai cu
			double dOldlong = 0d;
			if (!StringUtil.isNullOrEmpty(edt_line_hang_design_pillar_long_old
					.getText().toString().trim())) {
				dOldlong = Double.valueOf(edt_line_hang_design_pillar_long_old
						.getText().toString());
			}

			double total = (dNewlong + dOldlong);

			if (total > dTotalLong) {
				sError = StringUtil
						.getString(R.string.line_hang_design_long_message);
				edt_constr_line_hang_long_value.requestFocus();
				Toast.makeText(getApplicationContext(), sError,
						Toast.LENGTH_LONG).show();
				return false;
			}
		} else {
			if (this.iCableType == Constants.SEARCH_VALUE_DEFAULT) {
				sError = StringUtil
						.getString(R.string.edt_constr_line_hang_loai_cap_is_null);
				tabHost.setCurrentTab(1);
				edt_constr_line_hang_loai_cap
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				edt_constr_line_hang_loai_cap.requestFocus();

				Toast.makeText(getApplicationContext(), sError,
						Toast.LENGTH_LONG).show();

				clearFocus();
				return false;
			}

			if (StringUtil.isNullOrEmpty(edt_constr_line_hang_khoang_vuot
					.getText().toString())) {
				sError = StringUtil
						.getString(R.string.edt_constr_line_hang_khoang_vuot_is_null);
				tabHost.setCurrentTab(1);
				edt_constr_line_hang_khoang_vuot
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				edt_constr_line_hang_khoang_vuot.requestFocus();

				// Toast.makeText(getApplicationContext(), sError,
				// Toast.LENGTH_LONG)
				// .show();
				return false;
			}

			if (StringUtil.isNullOrEmpty(edt_constr_line_hang_tong_mx.getText()
					.toString())) {
				sError = StringUtil
						.getString(R.string.edt_constr_line_hang_tong_mx_is_null);
				tabHost.setCurrentTab(1);
				edt_constr_line_hang_tong_mx
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				edt_constr_line_hang_tong_mx.requestFocus();

				// Toast.makeText(getApplicationContext(), sError,
				// Toast.LENGTH_LONG)
				// .show();
				return false;
			}

			Integer iLoaiCap = Integer.parseInt(edt_constr_line_hang_loai_cap
					.getText().toString());

			if (iLoaiCap < 0 || iLoaiCap >= 100) {
				tabHost.setCurrentTab(1);
				sError = StringUtil
						.getString(R.string.line_hang_design_cap_type_message);
				Toast.makeText(getApplicationContext(), sError,
						Toast.LENGTH_LONG).show();
				return false;
			}

			Integer iKhongVuot = Integer
					.parseInt(edt_constr_line_hang_khoang_vuot.getText()
							.toString());

			if (iKhongVuot <= 0) {
				tabHost.setCurrentTab(1);
				sError = StringUtil
						.getString(R.string.line_hang_design_khoang_vuot_message);
				Toast.makeText(getApplicationContext(), sError,
						Toast.LENGTH_LONG).show();
				return false;
			}
		}

		return true;
	}

	private void saveDataDesign() {
		try {
			this.supervisionLineFiberController = new Supervision_Line_Hg_FiberController(
					this);
			this.listLineHGFiber = this.supervisionLineFiberController
					.getAllFiberBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());

			long idUser = GlobalInfo.getInstance().getUserId();
			long ipillar_new_total = Constants.ID_ENTITY_DEFAULT;
			int ipillar_new_type = Constants.ID_ENTITY_DEFAULT;
			long ipillar_new_high = Constants.ID_ENTITY_DEFAULT;
			double ipillar_new_long = Constants.ID_ENTITY_DEFAULT;
			long ipillar_old_total = Constants.ID_ENTITY_DEFAULT;
			double ipillar_old_long = Constants.ID_ENTITY_DEFAULT;
			long icable_range = Constants.ID_ENTITY_DEFAULT;
			int icable_type = Constants.ID_ENTITY_DEFAULT;
			long imx_total = Constants.ID_ENTITY_DEFAULT;
			double ilong_total = Constants.ID_ENTITY_DEFAULT;

			String sDesignDescription = edt_constr_line_hang_inter_value
					.getText().toString();
			supervision_ConstrData.setDesign_Description(sDesignDescription);

			supervision_ConstrData.setDesign_Type(iDesignType);

			supervision_ConstrData.setDesign_Assess(iDesignAssess);

			supervision_ConstrData.setDeployExpectedDay(Integer
					.parseInt(edt_constr_line_hang_deployday_value.getText()
							.toString().trim()));

			supervision_ConstrData
					.setSupervision_Progress(Constants.SUPERVISION_PROGRESS.DOING);

			String slong_total = edt_constr_line_hang_long_value.getText()
					.toString();
			ilong_total = StringUtil.convertToDoubleRound2(slong_total);

			/* Sua thong tin thiet ke */
			supervision_ConstrController.updateDesign(supervision_ConstrData);

			if (this.supervision_Line_HangData == null) {
				this.supervision_Line_HangData = new Supervision_Line_HangEntity();
			}

			this.supervision_Line_HangData.setLong_Total(ilong_total);

			if (tabHost.getCurrentTab() == 0) {
				String spillar_new_total = edt_line_hang_design_pillar_size
						.getText().toString();
				ipillar_new_total = Long.parseLong(spillar_new_total);

				if (rbt_line_hang_design_pillar_be_tong.isChecked())
					ipillar_new_type = Constants.LINE_HANG_PILLAR_NEW_TYPE.BE_TONG;
				else if (rbt_line_hang_design_pillar_go.isChecked())
					ipillar_new_type = Constants.LINE_HANG_PILLAR_NEW_TYPE.GO;

				String spillar_new_high = edt_line_hang_design_pillar_chieu_cao
						.getText().toString();
				ipillar_new_high = Long.parseLong(spillar_new_high);

				String spillar_new_long = edt_line_hang_design_pillar_long_new
						.getText().toString();
				ipillar_new_long = StringUtil
						.convertToDoubleRound2(spillar_new_long);

				String spillar_old_total = edt_line_hang_design_pillar_size_old
						.getText().toString().trim();
				if (!StringUtil.isNullOrEmpty(spillar_old_total)) {
					ipillar_old_total = Long.parseLong(spillar_old_total);
				}

				String spillar_old_long = edt_line_hang_design_pillar_long_old
						.getText().toString().trim();
				if (!StringUtil.isNullOrEmpty(spillar_old_long)) {
					ipillar_old_long = StringUtil
							.convertToDoubleRound2(spillar_old_long);
				}
				this.supervision_Line_HangData
						.setPillar_New_Total(ipillar_new_total);
				this.supervision_Line_HangData
						.setPillar_New_Type(ipillar_new_type);
				this.supervision_Line_HangData
						.setPillar_New_High(ipillar_new_high);
				this.supervision_Line_HangData
						.setPillar_New_Long(ipillar_new_long);
				this.supervision_Line_HangData
						.setPillar_Old_Total(ipillar_old_total);
				this.supervision_Line_HangData
						.setPillar_Old_Long(ipillar_old_long);

				if (this.supervision_Line_HangData
						.getSupervision_Line_Hang_Id() > 0) {

					this.supervision_Line_HangData
							.setSync_Status(Constants.SYNC_STATUS.ADD);
					supervision_Line_HangController
							.updatePillar(supervision_Line_HangData);
				} else {
					/* Tao moi ban ghi giam sat tuyen treo */
					this.supervision_Line_HangData
							.setSupervision_Constr_Id(this.supervision_ConstrData
									.getSupervision_Constr_Id());

					long idSupervion = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Line_HangField.TABLE_NAME);
					if (idSupervion == 0) {
						Toast.makeText(this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();
						return;
					}

					this.supervision_Line_HangData
							.setSupervision_Line_Hang_Id(idSupervion);
					this.supervision_Line_HangData
							.setSync_Status(Constants.SYNC_STATUS.ADD);

					this.supervision_Line_HangData
							.setIsActive(Constants.ISACTIVE.ACTIVE);

					this.supervision_Line_HangData.setProcessId(0);
					this.supervision_Line_HangData.setEmployeeId(idUser);

					this.supervision_Line_HangController
							.addPillar(supervision_Line_HangData);

					/* Thiet lap lai id cho item */
					supervision_Line_HangData
							.setSupervision_Line_Hang_Id(idSupervion);
				}
			} else {
				String scable_type = edt_constr_line_hang_loai_cap.getText()
						.toString();
				icable_type = StringUtil.convertToInt(scable_type);

				String smx_total = edt_constr_line_hang_tong_mx.getText()
						.toString();
				imx_total = Long.parseLong(smx_total);

				String scable_range = edt_constr_line_hang_khoang_vuot
						.getText().toString();
				icable_range = Long.parseLong(scable_range);

				this.supervision_Line_HangData.setCable_Range(icable_range);
				this.supervision_Line_HangData.setCable_Type(icable_type);
				this.supervision_Line_HangData.setMx_Total(imx_total);

				if (this.supervision_Line_HangData
						.getSupervision_Line_Hang_Id() > 0) {

					this.supervision_Line_HangData
							.setSync_Status(Constants.SYNC_STATUS.ADD);

					if ((iCableType < listLineHGFiber.size() && listLineHGFiber
							.size() > 0)) {
						// fiberNumChange = true;
						// ConfirmDialog confirmSave = new ConfirmDialog(this,
						// StringUtil
						// .getString(R.string.text_confirm_fiber_num));
						// confirmSave.show();
						this.showAskOptionDialog(
								StringUtil
										.getString(R.string.text_delete_title),
								StringUtil
										.getString(R.string.text_confirm_fiber_num));
						return;
					} else
						supervision_Line_HangController
								.updateCable(supervision_Line_HangData);
				} else {
					/* Tao moi ban ghi giam sat tuyen treo */
					this.supervision_Line_HangData
							.setSupervision_Constr_Id(this.supervision_ConstrData
									.getSupervision_Constr_Id());

					long idSupervion = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Line_HangField.TABLE_NAME);
					if (idSupervion == 0) {
						Toast.makeText(this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();
						return;
					}

					this.supervision_Line_HangData
							.setSupervision_Line_Hang_Id(idSupervion);
					this.supervision_Line_HangData
							.setSync_Status(Constants.SYNC_STATUS.ADD);

					this.supervision_Line_HangData
							.setIsActive(Constants.ISACTIVE.ACTIVE);

					this.supervision_Line_HangData.setProcessId(0);
					this.supervision_Line_HangData.setEmployeeId(idUser);

					this.supervision_Line_HangController
							.addCable(supervision_Line_HangData);

					/* Thiet lap lai id cho item */
					supervision_Line_HangData
							.setSupervision_Line_Hang_Id(idSupervion);
				}
			}

			/* Tao moi thong tin thiet ke */
			// if (this.supervision_Line_HangData.getSupervision_Line_Hang_Id()
			// > 0) {
			//
			// this.supervision_Line_HangData
			// .setSync_Status(Constants.SYNC_STATUS.ADD);
			//
			// if ((iCableType < listLineHGFiber.size() && listLineHGFiber
			// .size() > 0)) {
			// // fiberNumChange = true;
			// // ConfirmDialog confirmSave = new ConfirmDialog(this,
			// // StringUtil
			// // .getString(R.string.text_confirm_fiber_num));
			// // confirmSave.show();
			// this.showAskOptionDialog(StringUtil
			// .getString(R.string.text_delete_title), StringUtil
			// .getString(R.string.text_confirm_fiber_num));
			// return;
			// } else
			// supervision_Line_HangController
			// .updateAllColumn(supervision_Line_HangData);
			// } else {
			// /* Tao moi ban ghi giam sat tuyen treo */
			// this.supervision_Line_HangData
			// .setSupervision_Constr_Id(this.supervision_ConstrData
			// .getSupervision_Constr_Id());
			//
			// long idSupervion = Ktts_KeyController.getInstance()
			// .getKttsNextKey(Supervision_Line_HangField.TABLE_NAME);
			// if (idSupervion == 0) {
			// Toast.makeText(this,
			// StringUtil.getString(R.string.text_out_of_key),
			// Toast.LENGTH_SHORT).show();
			// return;
			// }
			//
			// this.supervision_Line_HangData
			// .setSupervision_Line_Hang_Id(idSupervion);
			// this.supervision_Line_HangData
			// .setSync_Status(Constants.SYNC_STATUS.ADD);
			//
			// this.supervision_Line_HangData
			// .setIsActive(Constants.ISACTIVE.ACTIVE);
			//
			// this.supervision_Line_HangData.setProcessId(0);
			// this.supervision_Line_HangData.setEmployeeId(idUser);
			//
			// this.supervision_Line_HangController
			// .addItem(supervision_Line_HangData);
			//
			// /* Thiet lap lai id cho item */
			// supervision_Line_HangData
			// .setSupervision_Line_Hang_Id(idSupervion);
			// }
			this.setData();
			Toast.makeText(
					this,
					StringUtil
							.getString(R.string.line_hang_save_susses_message),
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
				this.supervision_Line_HangData = supervision_Line_HangController
						.getItemBySupervisionConstrId(this.supervision_ConstrData
								.getSupervision_Constr_Id());
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

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					saveDataDesign();
				}
			});

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			closeProgressDialog();
		}

	}
}
