package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import com.viettel.database.Cause_Brcd_Sub_Controller;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Sub_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_TankEntity;
import com.viettel.database.entity.Cause_Line_BG_TankEntity;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Sub_Entity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Brcd_Sub_Field;
import com.viettel.database.field.Cause_Brcd_Sub_Field;
import com.viettel.database.field.Supervision_BRCD_Sub_Field;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.service.GpsServices;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_Line_BGAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_Sub_Activity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_SUBView;
	private TextView tv_constr_sub_dropdown, tv_supervision_sub_nn_khongdat;
	private TextView tv_constr_sub_station_code_value;
	private TextView tv_constr_sub_value;
	private EditText ed_supervision_sub_descs;
	private TextView tv_sub_chonsoluongthietbi, tv_sub_hangsanxuat;
	private CheckBox rdo_supervision_sub_dat, rdo_supervision_sub_khongdat;
	private Button btn_constr_sub_save;
	
	private Bundle bundleData;

	private RelativeLayout rl_supervision_line_bg_tank;
	/* bien dropdown */
	private int iDesignInfo = 1;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listHangSXInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;

	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_BRCD_Controller brcd_Controller = new Supervision_BRCD_Controller(
			this);
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_BRCD_Sub_Controller SubControl = new Supervision_BRCD_Sub_Controller(
			this);
	private Cause_Brcd_Sub_Controller causeLineBrcdMxController = new Cause_Brcd_Sub_Controller(
			this);
	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = new Supervision_LBG_UnqualifyItemEntity();
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = new Supervision_LBG_UnqualifyItemEntity();
	private Supervision_Line_BGAdapter supervisionAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;
	private int check_Status = -1;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;
	private long supervition_brcd_id;

	private TextView supervision_bg_tank_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	private Supervision_BRCD_Sub_Entity Sub_Entity = new Supervision_BRCD_Sub_Entity();
	private double longitute = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private double latitute = Constants.ID_DOUBLE_ENTITY_DEFAULT;

	private Supervision_ConstrEntity supervision_ConstrData;
	
	//hunkgm - get lat long form constr_contruction table
	private Supervision_ConstrController supervision_ConstrController;
	private Constr_ConstructionController constr_ConstructionController;
	private Constr_ConstructionEntity constr_ConstructionData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_sub_activity);
		setTitle(getString(R.string.line_background_header_title_sub_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		// Button btn_constr_sub_trancate = (Button) this
		// .findViewById(R.id.btn_constr_sub_trancate);
		// btn_constr_sub_trancate.setOnClickListener(new View.OnClickListener()
		// {
		//
		// @Override
		// public void onClick(View v) {
		// SubControl.trunCate();
		// setData();
		//
		// }
		// });
		spvBRCD_SUBView = addView(R.layout.supervision_brcd_sub_activity, R.id.rl_supervision_line_bg_tank);
		this.tv_constr_sub_dropdown = (TextView) spvBRCD_SUBView
				.findViewById(R.id.tv_constr_sub_dropdown);
		this.tv_constr_sub_dropdown.setOnClickListener(this);

		this.tv_supervision_sub_nn_khongdat = (TextView) spvBRCD_SUBView
				.findViewById(R.id.tv_supervision_sub_nn_khongdat);
		this.tv_supervision_sub_nn_khongdat.setOnClickListener(this);

		this.tv_constr_sub_value = (TextView) spvBRCD_SUBView
				.findViewById(R.id.tv_constr_sub_value);

		this.tv_constr_sub_station_code_value = (TextView) spvBRCD_SUBView
				.findViewById(R.id.tv_constr_sub_station_code_value);
		this.btn_constr_sub_save = (Button) spvBRCD_SUBView
				.findViewById(R.id.btn_constr_sub_save);
		this.btn_constr_sub_save.setOnClickListener(this);

		this.ed_supervision_sub_descs = (EditText) spvBRCD_SUBView
				.findViewById(R.id.ed_supervision_sub_descs);

		this.rdo_supervision_sub_khongdat = (CheckBox) spvBRCD_SUBView.findViewById(R.id.rdo_supervision_sub_khongdat);
		rdo_supervision_sub_khongdat.setOnClickListener(this);

		this.rdo_supervision_sub_dat = (CheckBox) spvBRCD_SUBView.findViewById(R.id.rdo_supervision_sub_dat);
		rdo_supervision_sub_dat.setOnClickListener(this);
		this.tv_sub_chonsoluongthietbi = (TextView) spvBRCD_SUBView.findViewById(R.id.tv_sub_chonsoluongthietbi);
		this.tv_sub_hangsanxuat = (TextView) spvBRCD_SUBView.findViewById(R.id.tv_sub_hangsanxuat);
		tv_sub_hangsanxuat.setOnClickListener(this);
		// final int heightScreen = displaymetrics.heightPixels;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		if(!GlobalInfo.getInstance().isCheckIn() && v.getId()!=R.id.btn_popup_cancel && v.getId()!=R.id.btn_popup_save_plan){
			showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
			return;
		}
		switch (v.getId()) {
		case R.id.tv_supervision_sub_nn_khongdat:

			if (check_Status == 0) {
				// Gan gia tri nguyen nhan loi
				this.setUnqualify();
				contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listTankUnqualifyItem);
				contruoctionUnqualifyPopup.showAtCenter();
			} else if (check_Status == 1) {
				this.setAcceptance();
				contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
						this, null, this.listTankAcceptanceItem);
				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.line_background_accept_choice),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.rdo_supervision_sub_khongdat:
			rdo_supervision_sub_dat.setChecked(false);
			checkhienthi();
			if (check_Status == -1 || check_Status == 1) {
				check_Status = 0;

			} else {
				check_Status = -1;

			}
			break;
		case R.id.rdo_supervision_sub_dat:
			rdo_supervision_sub_khongdat.setChecked(false);
			checkhienthi();
			if (check_Status == -1 || check_Status == 0) {
				check_Status = 1;

			} else {
				check_Status = -1;

			}
			break;
		case R.id.tv_constr_sub_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sub_hangsanxuat:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listHangSXInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_constr_sub_save:
			String messageError = "";

			String messageError_image = "";
			messageError = this.checkVailid();
			messageError_image = this.checkVailid_Image(Sub_Entity);
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
				// Toast.makeText(getApplicationContext(), messageError,
				// Toast.LENGTH_LONG).show();
			} else if (!StringUtil.isNullOrEmpty(messageError_image)) {
				this.showDialog(messageError_image);
			} else {
				long idUser = GlobalInfo.getInstance().getUserId();

				Sub_Entity.setSupervisionConstrId(itemData
						.getSupervision_Constr_Id());
				Sub_Entity.setEmployeeId(idUser);
				Sub_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
				Sub_Entity.setStatus(check_Status);
				if (check_Status == -1) {
					this.showDialog(StringUtil
							.getString(R.string.constr_cat_work_nn_khongdat_sub_tempty));
				} else {
					ConfirmDialog confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			}
			break;
			// click chon save tren popup check in
		case R.id.btn_popup_save_plan:
			if(edtMakePlan.getText().toString().trim().length()<=0){
				showAlertDialogCheckInRequire(this, getString(R.string.input_plan_require), getString(R.string.text_close));
				break;
			} else if (addnewSupvLocatetoDB()) {
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
	
	public boolean addnewSupvLocatetoDB(){
		long supv_locate_id = Ktts_KeyController.getInstance().getKttsNextKey(Supervision_Locate_Field.TABLE_NAME);
		if(supv_locate_id==0){
			showAlertDialogCheckInRequire(Supervision_BRCD_Sub_Activity.this,
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
	
	@Override
	public void actionCheckInButton() {
		super.actionCheckInButton();
		// isCheckIn = !isCheckIn;
		// GlobalInfo.getInstance().setCheckIn(isCheckIn);
		// kiểm tra điều kiện check in
		boolean canCheckIn = false;
		String alertMessage = "";
//		reqLat = Double.parseDouble(constr_ConstructionData.getLatitude());
//		reqLong = Double.parseDouble(constr_ConstructionData.getLongtitude());
		if(constr_ConstructionData.getLatitude()!=null && constr_ConstructionData.getLongtitude()!=null){
			reqLat = (constr_ConstructionData.getLatitude().equalsIgnoreCase(""))?-1.0:Double.parseDouble(constr_ConstructionData.getLatitude());
			reqLong = (constr_ConstructionData.getLongtitude().equalsIgnoreCase(""))?-1.0:Double.parseDouble(constr_ConstructionData.getLongtitude());
		}else {
			reqLat = -1;
			reqLong = -1;
		}
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
				
				
				
//				else {
//					long startTimeLater = supv_locate_controller.getSupvLocateItem(GlobalInfo.getInstance().getCheckInTBId(this)).getCheckinDate().getTime();
//					long diffTime  = System.currentTimeMillis() - startTimeLater;
//					if(diffTime < CheckInService.distance_require_time){
//						canCheckIn = false;
//						alertMessage = getString(R.string.checkin_time_require);
//					} else {
//						canCheckIn = true;
//					}
//				}
				
//				Log.d("hungkm", "can checkin : "+canCheckIn);
//				if(!canCheckIn){
//					showAlertDialogCheckInRequire(this, alertMessage, getString(R.string.text_close));
//				}else{
//					showPopupPlan((View) findViewById(R.id.action_checkin));
//				}
				
				showPopupPlan((View) findViewById(R.id.action_checkin));
	}
	
	

	private String checkVailid_Image(Supervision_BRCD_Sub_Entity sub_Entity) {
		String sReslut = "";
		try {

			if (check_Status == 1) {
				countAcceptCheck = 0;
				if (sub_Entity.getListAcceptance().size() == 0) {

					for (int j = 0; j < listTankAcceptanceItem.size(); j++) {
						if (listTankAcceptanceItem.get(j).getIs_Mandory() == 1) {
							countAcceptCheck++;
							break;
						}
					}
					if (countAcceptCheck > 0) {
						sReslut = StringUtil
								.getString(R.string.constr_take_acceptance_not_enough);
					}

				} else {

					for (int j = 0; j < listTankAcceptanceItem.size(); j++) {
						if ((sub_Entity.getListAcceptance().get(j)
								.getLstNewAttachFile().size() != 0 || sub_Entity
								.getListAcceptance().get(j).getLstAttachFile()
								.size() != 0)

								&& listTankAcceptanceItem.get(j)
										.getIs_Mandory() == 1) {
							countAcceptCheck++;
						}
					}
					if (countAcceptCheck == 0) {
						sReslut = StringUtil
								.getString(R.string.constr_take_acceptance_not_enough);
					}
				}

			} else {
				for (int i = 0; i < sub_Entity.getListCauseUniQualify().size(); i++) {
					if (!sub_Entity.getListCauseUniQualify().get(i).isDelete()) {
						countNnkdCheck++;
						break;
					}
				}
				for (int j = 0; j < listTankAcceptanceItem.size(); j++) {

					if (listTankAcceptanceItem.get(j).getIs_Mandory() == 1) {
						for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : sub_Entity
								.getListAcceptance()) {
							if (curUnqualifyItem
									.getCat_Cause_Constr_Acceptance_Id() == listTankAcceptanceItem
									.get(j).getCat_Cause_Constr_Acceptance_Id()) {
								List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
										.getLstAttachFile(
												Acceptance_Brcd_Sub_Field.TABLE_NAME,
												curUnqualifyItem
														.getCause_Line_Bg_Id());
								if (lstCurAttachFile.size() > 0) {
									countAcceptCheck++;
									break;
								}

							}

						}

					}

				}

				if (countNnkdCheck < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_line_special_nn_khongdat_tempty);

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return sReslut;
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_SUBView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_Sub_Activity.this);
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

	@SuppressWarnings("unchecked")
	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				if (this.iDesignInfo != dropdownItem.getId()) {

					Supervision_BRCD_Sub_Entity Sub_Entity = new Supervision_BRCD_Sub_Entity();
					Sub_Entity = SubControl.getSupervisionBRCD_SUB(itemData
							.getSupervision_Constr_Id());
					if (Sub_Entity == null) {
						Toast.makeText(getApplicationContext(),
								"Dữ liệu chưa có", Toast.LENGTH_LONG).show();
						this.dropdownPopupMenuDesignInfo.dismiss();

					} else {
						bundleData = new Bundle();
						bundleData.putSerializable(IntentConstants.INTENT_DATA,
								itemData);
						bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
								supervition_brcd_id);
						bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
								dropdownItem.getId());
						this.gotoSubBackgroupActivity(bundleData);
						this.dropdownPopupMenuDesignInfo.dismiss();
						this.finish();
					}
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_HSX)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {

					this.tv_sub_hangsanxuat.setText(dropdownItem.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		/* Chon nguyen nhan khong dat */
		// case OnEventControlListener.EVENT_SUPERVISION_TANK_EDIT:
		// this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
		// switch (this.curEditItem.getiField()) {

		// case Constants.BG_TANK_EDIT.UNQUALIFY:
		// if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT)
		// {
		// // Gan gia tri nguyen nhan loi
		// this.setUnqualify();
		// contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
		// this, null, this.listTankUnqualifyItem);
		// contruoctionUnqualifyPopup.showAtCenter();
		// } else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT)
		// {
		// this.setAcceptance();
		// contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
		// this, null, this.listTankAcceptanceItem);
		// contruoctionAcceptancePopup.showAtCenter();
		//
		// } else {
		// Toast.makeText(
		// this,
		// StringUtil
		// .getString(R.string.line_background_accept_choice),
		// Toast.LENGTH_SHORT).show();
		// }
		// break;
		// case Constants.BG_TANK_EDIT.FAIL_DESC:
		// String sFailDescTextValue = this.Sub_Entity.getDesc();
		// editTextPopup = new Edit_Text_Popup(this, null,
		// sFailDescTextValue,
		// InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
		// editTextPopup.showAtCenter();
		// break;
		// default:
		// break;
		// }
		// break;

		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			// this.Sub_Entity.setEdited(true);
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			// this.curEditItem.setEdited(true);
			break;

		// case OnEventControlListener.EVENT_SET_TEXT:
		// String sSetTextValue = (String) data;
		// if (this.curEditItem.getiField() == Constants.BG_TANK_EDIT.NUM_PART)
		// {
		// boolean bIsEmpty = StringUtil.isNullOrEmpty(sSetTextValue);
		// if (bIsEmpty) {
		// this.showDialog(StringUtil
		// .getString(R.string.text_empty_message));
		// return;
		// }
		// this.curEditItem.setNumberPart(Long.parseLong(sSetTextValue));
		// }
		// if (this.curEditItem.getiField() == Constants.BG_TANK_EDIT.FAIL_DESC)
		// {
		// this.curEditItem.setFailDesc(sSetTextValue);
		// }
		// this.curEditItem.setEdited(true);
		// this.supervisionAdapter.notifyDataSetChanged();
		// editTextPopup.hidePopup();
		// break;
		/* Xu ly su kien anh */
		case OnEventControlListener.EVENT_UNQUALIFY_CHECK_UCHECK:
			Supervision_LBG_UnqualifyItemEntity unqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
			if (unqualifyItem.isUnqulify()) {
				unqualifyItem.setDelete(false);
			} else {
				unqualifyItem.setDelete(true);
			}
			this.contruoctionUnqualifyPopup.refreshData();
			break;

		// chup anh nghiem thu
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
					listImgView.add(addImgView);
				}
			}

			this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
					listImgView);
			this.imgViewPopup.hideShowButton();
			this.imgViewPopup.showAtCenter();
			break;
		// chup anh nguyen nhan loi
		case OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO:
			this.curUnqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
			listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */

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
					listImgView.add(addImgView);
				}
			}

			this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
					listImgView);
			this.imgViewPopup.hideShowButton();
			this.imgViewPopup.showAtCenter();
			break;
		// case OnEventControlListener.EVENT_LOCATION:
		// this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
		// this.getLocation(this.Sub_Entity.get,
		// this.curEditItem.getLatLocation(),
		// StringUtil.getString(R.string.constr_line_tank_name),
		// this.curEditItem.getTankName());
		// break;
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;

			this.imgViewPopup.hidePopup();
			if (check_Status == 1) {
				this.curAcceptanceItem.getLstAttachFile().clear();
				this.curAcceptanceItem.getLstNewAttachFile().clear();
				for (ImageEntity imageEntity : lstData) {
					Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
					curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
							.getId());
					curAttachFile.setFile_Path(imageEntity.getUrl());
					this.curAcceptanceItem.getLstNewAttachFile().add(
							curAttachFile);
				}
				this.contruoctionAcceptancePopup.refreshData();
			} else if (check_Status == 0) {
				this.curUnqualifyItem.getLstAttachFile().clear();
				this.curUnqualifyItem.getLstNewAttachFile().clear();
				for (ImageEntity imageEntity : lstData) {
					Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
					curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
							.getId());
					curAttachFile.setFile_Path(imageEntity.getUrl());
					this.curUnqualifyItem.getLstNewAttachFile().add(
							curAttachFile);
				}
				this.contruoctionUnqualifyPopup.refreshData();
			}

			// this.supervisionAdapter.notifyDataSetChanged();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(itemData);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			saveCompleteDay(itemData, Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_TANK, outOfKey);
			showCompleteDate(itemData, Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_TANK,
					supervision_bg_tank_complete_date,
					rl_supervision_bg_tank_bt_complete);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
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
//			if (resultCode == Activity.RESULT_OK) {
//				Uri selectedImage = data.getData();
////				String[] filePathColumn = { MediaStore.Images.Media.DATA };
////				Cursor cursor = getContentResolver().query(selectedImage,
////						filePathColumn, null, null, null);
////				cursor.moveToFirst();
////				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////				String picturePath = cursor.getString(columnIndex);
////				cursor.close();
//				String picturePath = StringUtil.getPath(this, selectedImage);
//				ImageEntity addImgView = new ImageEntity();
//				addImgView.setId(1);
//				addImgView.setUrl(picturePath);
//
//				this.imgViewPopup.setImageData(addImgView);
//			}
//			break;
		// case Constants.GET_LOCATION_RESULT:
		// if (resultCode == Activity.RESULT_OK) {
		// this.Sub_Entity.setLatLocation(data.getDoubleExtra(
		// IntentConstants.INTENT_LAT,
		// Constants.ID_DOUBLE_ENTITY_DEFAULT));
		// this.curEditItem.setLonLocation(data.getDoubleExtra(
		// IntentConstants.INTENT_LONG,
		// Constants.ID_DOUBLE_ENTITY_DEFAULT));
		// this.curEditItem.setEdited(true);
		// // listSupervisionGS.
		// this.supervisionAdapter.notifyDataSetChanged();
		// }
		// break;
		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		gotoHomeActivity(new Bundle());
		finish();
	}

	private void setData() {
		bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.tv_constr_sub_station_code_value
				.setText(itemData.getStationCode());
		this.tv_constr_sub_value.setText(itemData.getConstrCode());
		this.tv_constr_sub_dropdown.setText(GloablUtils
				.getStringSubInfo(iDesignInfo));
		this.supervition_brcd_id = bundleData.getLong(IntentConstants.INTENT_BRCD_ID);

		/* Drop down */
		this.listDesignInfo = GloablUtils.getListSubInfo("");
		this.listHangSXInfo = GloablUtils.getListSubHSX("");

		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.LAP_DAT_THIET_BI,
						Constants.UNQUALIFY_TYPE.SUB_HEADEND);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.LAP_DAT_THIET_BI,
						Constants.UNQUALIFY_TYPE.SUB_HEADEND);

		Sub_Entity = SubControl.getSupervisionBRCD_SUB(itemData
				.getSupervision_Constr_Id());
		if (Sub_Entity != null) {
			if (Sub_Entity.getDesc() != null
					|| !Sub_Entity.getDesc().equals("")) {
				ed_supervision_sub_descs.setText(Sub_Entity.getDesc()
						.toString());
			}
			if (Sub_Entity.getStatus() == 1) {
				rdo_supervision_sub_dat.setChecked(true);
				check_Status = 1;
			} else {
				rdo_supervision_sub_khongdat.setChecked(true);
				check_Status = 0;
			}
			tv_sub_chonsoluongthietbi.setText("" + Sub_Entity.getNum_device());

			if (Sub_Entity.getProduct_id().equals("130")) {
				tv_sub_hangsanxuat.setText("NOKIA");
			} else if (Sub_Entity.getProduct_id().equals("1")) {
				tv_sub_hangsanxuat.setText("HUAWEI");

			} else if (Sub_Entity.getProduct_id().equals("91")) {
				tv_sub_hangsanxuat.setText("ERICSSON");

			} else if (Sub_Entity.getProduct_id().equals("70")) {
				tv_sub_hangsanxuat.setText("ZTE");

			} else if (Sub_Entity.getProduct_id().equals("90")) {
				tv_sub_hangsanxuat.setText("ALCATEL");

			}
			Sub_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			listnguyennhan();
			checkhienthi();
		} else {
			Sub_Entity = new Supervision_BRCD_Sub_Entity();
			Sub_Entity.setSync_Status(Constants.SYNC_STATUS.EDIT);
		}
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.btn_constr_sub_save.setVisibility(View.GONE);
		}
		
		this.supervision_ConstrController = new Supervision_ConstrController(this);
		this.supervision_ConstrData = this.supervision_ConstrController
				.getItem(itemData.getSupervision_Constr_Id());
		constr_ConstructionController = new Constr_ConstructionController(this);
		constr_ConstructionData = constr_ConstructionController.getItem(supervision_ConstrData.getConstruct_Id());


	}

	private void checkhienthi() {
		for (int i = 0; i < this.Sub_Entity.getListCauseUniQualify().size(); i++) {
			if (!Sub_Entity.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		for (int i = 0; i < this.Sub_Entity.getListAcceptance().size(); i++) {
			if (Sub_Entity.getListAcceptance().get(i).getLstNewAttachFile()
					.size() > 0
					|| Sub_Entity.getListAcceptance().get(i).getLstAttachFile()
							.size() > 0) {
				countAcceptCheck++;
				break;
			}
		}

		if (check_Status == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				tv_supervision_sub_nn_khongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				tv_supervision_sub_nn_khongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				tv_supervision_sub_nn_khongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				tv_supervision_sub_nn_khongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}

	}

	private void listnguyennhan() {
		List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeLineBrcdMxController
				.getAllTankUnqulifyByTankId(Sub_Entity
						.getSUPERVISION_TRUNK_SUB_ID());
		List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeLineBrcdMxController
				.getAllTanAcceptByTankId(Sub_Entity
						.getSUPERVISION_TRUNK_SUB_ID());

		/* Gan anh nguyen nhan loi */
		for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
			List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
					.getLstAttachFile(Acceptance_Brcd_Sub_Field.TABLE_NAME,
							curUnqualifyItem.getCause_Line_Bg_Id());
			for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
				curUnqualifyItem.getLstAttachFile().add(itemFile);
			}

		}
		Sub_Entity.setListAcceptance(listAcceptItem);
		/* Gan anh nguyen nhan loi */
		for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
			List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
					.getLstAttachFile(Cause_Brcd_Sub_Field.TABLE_NAME,
							curUnqualifyItem.getCause_Line_Bg_Id());
			for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
				curUnqualifyItem.getLstAttachFile().add(itemFile);
			}

		}
		Sub_Entity.setListCauseUniQualify(listUnqualifyItem);

	}

	public void UpdateSupvConstr() {
		if (supervision_ConstrData == null) {
			supervision_ConstrData = new Supervision_ConstrEntity();
		}
		if(supervision_ConstrController==null){
			supervision_ConstrController = new Supervision_ConstrController(
				this);
		}

		supervision_ConstrData.setSupervision_Constr_Id(itemData
				.getSupervision_Constr_Id());

		supervision_ConstrData
				.setSupervision_Progress(Constants.SUPERVISION_PROGRESS.DOING);
		boolean bupdatedesign = supervision_ConstrController
				.updateprogress(supervision_ConstrData);
	}

	private void saveDataTank() {
		getSupvBRCDMX();

		UpdateSupvConstr();

		long idUser = GlobalInfo.getInstance().getUserId();
		if (Sub_Entity.getSync_Status() == Constants.SYNC_STATUS.EDIT) {
			long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
					Supervision_BRCD_Sub_Field.TABLE_NAME);
			if (idSupervion == 0) {
				Toast.makeText(Supervision_BRCD_Sub_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}

			Sub_Entity.setSupervisionConstrId(itemData
					.getSupervision_Constr_Id());
			Sub_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			Sub_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
			Sub_Entity.setProcessId(0);
			Sub_Entity.setEmployeeId(idUser);
			Sub_Entity.setSUPERVISION_TRUNK_SUB_ID(idSupervion);
			SubControl.addItem(Sub_Entity);
//			long idAddTank = Ktts_KeyController.getInstance().getKttsNextKey(
//					Supervision_BRCD_Sub_Field.TABLE_NAME);

			if (idSupervion == 0) {
				outOfKey = true;
				return;
			} else
				outOfKey = false;
			if (check_Status == 0) {

				List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
						.getListCauseUniQualify();
				for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
					Cause_Line_BG_TankEntity addCauseItem = new Cause_Line_BG_TankEntity();
					addCauseItem
							.setCat_Cause_Constr_Unqualify_Id(curItemUnqualify
									.getCat_Cause_Constr_Unqualify_Id());
					addCauseItem.setSupervision_Line_Bg_Tank_Id(idSupervion);
					addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
					addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
					addCauseItem.setProcessId(0);
					addCauseItem.setEmployeeId(idUser);
					addCauseItem.setSupervisionConstrId(idSupervion);

					long iAddCause = Ktts_KeyController.getInstance()
							.getKttsNextKey(Cause_Brcd_Sub_Field.TABLE_NAME);
					if (iAddCause == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					addCauseItem.setCause_Line_Bg_Tank_Id(iAddCause);
					this.causeLineBrcdMxController.addItem(addCauseItem);

					for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
							.getLstNewAttachFile()) {
						if (!StringUtil.isNullOrEmpty(itemFile.getFile_Path())) {
							this.supvConstrAttachFileController
									.coppyAndAddAttachFile(this.itemData,
											itemFile.getFile_Path(), iAddCause,
											Cause_Brcd_Sub_Field.TABLE_NAME);

						}
					}
				}
			} else {
				// neu dat thi save anh nghiem thu
				List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
						.getListAcceptance();
				for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
					Acceptance_Line_Bg_TankEntity addCauseItem = new Acceptance_Line_Bg_TankEntity();
					addCauseItem
							.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
									.getCat_Cause_Constr_Acceptance_Id());
					addCauseItem.setSupervision_Line_Bg_Tank_Id(idSupervion);
					addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
					addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
					addCauseItem.setProcessId(0);
					addCauseItem.setEmployeeId(idUser);

					long iAddCause = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Acceptance_Brcd_Sub_Field.TABLE_NAME);
					if (iAddCause == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					addCauseItem.setAcceptance_Line_Bg_Tank_Id(iAddCause);
					this.causeLineBrcdMxController.addItem(addCauseItem);

					for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
							.getLstNewAttachFile()) {
						if (!StringUtil.isNullOrEmpty(itemFile.getFile_Path())) {
							this.supvConstrAttachFileController
									.coppyAndAddAttachFile(
											this.itemData,
											itemFile.getFile_Path(),
											iAddCause,
											Acceptance_Brcd_Sub_Field.TABLE_NAME);

						}
					}
				}
			}

		}

		else if (Sub_Entity.getSync_Status() == Constants.SYNC_STATUS.ADD) {
			Sub_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			Sub_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
			Sub_Entity.setProcessId(0);
			Sub_Entity.setEmployeeId(idUser);
			SubControl.updateSupervisionBRCD_MX(
					Sub_Entity.getSUPERVISION_TRUNK_SUB_ID(), Sub_Entity);

			List<Supervision_LBG_UnqualifyItemEntity> listAddCause = this.Sub_Entity
					.getListCauseUniQualify();
			List<Supervision_LBG_UnqualifyItemEntity> listAddAccept = this.Sub_Entity
					.getListAcceptance();

			for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddAccept) {
				/* 1. Chinh sua nghiem thu */
				if (addItemCause.getCause_Line_Bg_Id() > 0) {

					// xoa nghiem thu khi chuyen
					// trang thai tu khong dat sang dat
//					if (Sub_Entity.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
//						this.causeLineBrcdMxController
//								.deleteAccept(addItemCause);
//					}

					if (addItemCause.getLstNewAttachFile().size() > 0
							|| (addItemCause.getLstNewAttachFile().size() == 0 && addItemCause
									.getLstAttachFile().size() == 0)) {
						List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
								.getLstAttachFile(
										Acceptance_Brcd_Sub_Field.TABLE_NAME,
										addItemCause.getCause_Line_Bg_Id());

						// xoa anh cu di
						for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
							supvConstrAttachFileController.delete(itemFile);
						}
					}

					for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
							.getLstNewAttachFile()) {

						// them anh moi vao
						if (!StringUtil.isNullOrEmpty(itemFile.getFile_Path())) {
							this.supvConstrAttachFileController
									.coppyAndAddAttachFile(
											this.itemData,
											itemFile.getFile_Path(),
											addItemCause.getCause_Line_Bg_Id(),
											Acceptance_Brcd_Sub_Field.TABLE_NAME);

						}
					}

				}
				/* 2. Them moi nghiem thu */
				else {
					if (Sub_Entity.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
						Acceptance_Line_Bg_TankEntity addCauseItem = new Acceptance_Line_Bg_TankEntity();
						addCauseItem
								.setCat_Cause_Constr_Acceptance_Id(addItemCause
										.getCat_Cause_Constr_Acceptance_Id());
						addCauseItem.setSupervision_Line_Bg_Tank_Id(Sub_Entity
								.getSUPERVISION_TRUNK_SUB_ID());

						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);

						long iAddCause = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Acceptance_Brcd_Sub_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setAcceptance_Line_Bg_Tank_Id(iAddCause);
						this.causeLineBrcdMxController.addItem(addCauseItem);
						// Luu anh nguyen nhan loi neu co
						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(
												this.itemData,
												itemFile.getFile_Path(),
												iAddCause,
												Acceptance_Brcd_Sub_Field.TABLE_NAME);

							}
						}
					}

				}
			}

			for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
				/* 1. Chinh sua nguyen nhan khong dat */
				if (addItemCause.getCause_Line_Bg_Id() > 0) {
					/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
					if (addItemCause.isDelete()) {
						this.causeLineBrcdMxController.delete(addItemCause);
					}
					/* 1.2. Update lai nguyen nhan khong dat */
					else {
						// xoa nguyen nhan khong dat khi chuyen
						// trang thai tu khong dat sang dat
						// if (brcdMx_Entity.getStatus() ==
						// Constants.SUPERVISION_STATUS.DAT) {
						// this.causeLineBGTankController
						// .delete(addItemCause);
						// }

						if (addItemCause.getLstNewAttachFile().size() > 0
								|| (addItemCause.getLstNewAttachFile().size() == 0 && addItemCause
										.getLstAttachFile().size() == 0)) {
							// danh sach anh da co
							List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
									.getLstAttachFile(
											Cause_Brcd_Sub_Field.TABLE_NAME,
											addItemCause.getCause_Line_Bg_Id());

							// xoa anh cu di
							for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
								supvConstrAttachFileController.delete(itemFile);
							}
						}

						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {

							// them anh moi vao
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(this.itemData,
												itemFile.getFile_Path(),
												addItemCause
														.getCause_Line_Bg_Id(),
												Cause_Brcd_Sub_Field.TABLE_NAME);

							}
						}
					}
				}
				/* 2. Them moi nguyen nhan khong dat */
				else {
					if (Sub_Entity.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
						Cause_Line_BG_TankEntity addCauseItem = new Cause_Line_BG_TankEntity();
						addCauseItem
								.setCat_Cause_Constr_Unqualify_Id(addItemCause
										.getCat_Cause_Constr_Unqualify_Id());
						addCauseItem.setSupervision_Line_Bg_Tank_Id(Sub_Entity
								.getSUPERVISION_TRUNK_SUB_ID());
						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);
						addCauseItem.setSupervisionConstrId(itemData
								.getSupervision_Constr_Id());

						long iAddCause = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(Cause_Brcd_Sub_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setCause_Line_Bg_Tank_Id(iAddCause);
						this.causeLineBrcdMxController.addItem(addCauseItem);
						// Luu anh nguyen nhan loi neu co
						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(this.itemData,
												itemFile.getFile_Path(),
												iAddCause,
												Cause_Brcd_Sub_Field.TABLE_NAME);

							}
						}
					}
				}
			}

		}
		
		// TODO: Thiet lap ket luan. DanhDue ExOICTIF
		if(supervision_ConstrController==null){
			supervision_ConstrController = new Supervision_ConstrController(
				this);
		}
		bundleData = this.getIntent().getExtras();
		boolean bDeny = checkSuperSubheadendCauseDeny(bundleData);
		Log.i("Check_Deny", String.valueOf(bDeny));
		if (bDeny) {
			supervision_ConstrController.updateStatus(
					itemData.getSupervision_Constr_Id(), 0);
		} else {
			supervision_ConstrController.updateStatus(
					itemData.getSupervision_Constr_Id(), 1);
		}

	}

	private void getSupvBRCDMX() {
		if (Sub_Entity == null) {
			Sub_Entity = new Supervision_BRCD_Sub_Entity();
		}
		Sub_Entity.setDesc(ed_supervision_sub_descs.getText().toString());
		Sub_Entity.setNum_device(Integer.parseInt(tv_sub_chonsoluongthietbi
				.getText().toString()));
		String hangsanxuat = tv_sub_hangsanxuat.getText().toString();
		if (hangsanxuat.equals("NOKIA")) {
			Sub_Entity.setProduct_id("" + 130);
		} else if (hangsanxuat.equals("HUAWEI")) {
			Sub_Entity.setProduct_id("" + 1);
		} else if (hangsanxuat.equals("ERICSSON")) {
			Sub_Entity.setProduct_id("" + 91);
		} else if (hangsanxuat.equals("ZTE")) {
			Sub_Entity.setProduct_id("" + 70);
		} else if (hangsanxuat.equals("ALCATEL")) {
			Sub_Entity.setProduct_id("" + 90);
		}
	}

	private String checkVailid() {
		String sReslut = "";
		try {
			if (tv_sub_chonsoluongthietbi.getText().toString().equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_luong_thiet_bi);
				tv_sub_chonsoluongthietbi.requestFocus();

			} else if (tv_sub_hangsanxuat.getText().equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_hang_san_xuat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankAcceptanceItem) {
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

				addItem.setLstNewAttachFile(curCauseUniqualify
						.getLstNewAttachFile());
				addItem.setTitle(curCauseUniqualify.getTitle());
				curListUnqualify.add(addItem);
			}
		}
		Log.i("", "");
	}

	/**
	 * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setAcceptance() {

		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankAcceptanceItem) {
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

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankUnqualifyItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
			if (curItem == null) {
				/* Them moi */
				if (curCauseUniqualify.isUnqulify()) {
					Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
					addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
							.getCat_Cause_Constr_Unqualify_Id());
					addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
					addItem.setUnqulify(true);
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

	/**
	 * Ham set lai nguyen nhan loi cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.Sub_Entity
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankUnqualifyItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
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
			SyncTask.closeDialog();
//			closeProgressDialog();
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
			saveDataTank();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_BRCD_Sub_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			
//			setData();
			Sub_Entity = SubControl.getSupervisionBRCD_SUB(itemData
					.getSupervision_Constr_Id());
			Sub_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			listnguyennhan();
			checkhienthi();
			
			Toast.makeText(Supervision_BRCD_Sub_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}