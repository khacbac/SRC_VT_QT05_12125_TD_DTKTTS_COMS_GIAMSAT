package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Tn_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.database.entity.Supervision_BRCD_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Design_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_Tn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Supervision_BRCD_Field;
import com.viettel.database.field.Supervision_BRCD_Kcn_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Tn_Field;
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
public class Supervision_BRCD_Thongtintk_Activity extends HomeBaseActivity {
	private static final String TAG = Supervision_BRCD_Thongtintk_Activity.class.getSimpleName();
	/* controll */
	private View spvBRCD_TTTKView;
	private TextView tv_constr_brcd_tttk_dropdown,
			tv_constr_brcd_tttk_station_code_value, tv_constr_brcd_tttk_value,
			tv_chonloaicap_capnhanh, tv_sotunhanh_capnhanh,
			tv_sosoicapnhanh_tunhanh, tv_sosoicaptruc_loaihaitu,
			tv_sosoicaptruc_loaibontam, tv_sosoicaptruc_loaichinsau;

	private EditText ed_socottrongmoi_captruc, ed_socotchongmoi_capnhanh,
			ed_socotcosan_captruc, ed_socotcosan_capnhanh;

	private Button btn_constr_brcd_ghilai;
	
	private Bundle bundleData;

	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listSosoicaptruc_ht = null;
	private List<DropdownItemEntity> listSosoicaptruc_bt = null;
	private List<DropdownItemEntity> listSosoicaptruc_cs = null;
	private List<DropdownItemEntity> listLoaicapnhanh = null;
	private List<DropdownItemEntity> listSosoicapnhanh = null;
	private List<DropdownItemEntity> listSotunhanh = null;

	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;

	/* Bien co so du lieu */
	private Supervision_BtsEntity bts_Entity;
	private Supervision_BRCD_Controller brcd_Controller;
	private Supervision_BRCD_Entity brcd_Entity;
	private Constr_Construction_EmployeeEntity itemData;

	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_Line_BG_TankGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Supervision_Line_BGAdapter supervisionAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;
	private int num_cab_branch_old = 0;
	private int num_cab_branch_new = 0;
	private int sotunhanh_cu = 0, sotunhanh_moi = 0;
	private int sosoicaptruccu_ht = 0, sosoicaptrucmoi_ht = 0;
	private int sosoicaptruccu_bt = 0, sosoicaptrucmoi_bt = 0;
	private int sosoicaptruccu_cs = 0, sosoicaptrucmoi_cs = 0;
	private TextView supervision_bg_tank_complete_date;
	private Supervision_ConstrEntity supervision_ConstrData;
	private Button rl_supervision_bg_tank_bt_complete;
	private List<Supervision_BRCD_Kcn_Entity> listSupervisionKCN;
	private Supervision_BRCD_Kcn_Design_Entity brcd_kcn_design = new Supervision_BRCD_Kcn_Design_Entity();
	private Supervision_BRCD_Kcn_Design_Controller brcd_kcn_design_Controller = new Supervision_BRCD_Kcn_Design_Controller(
			this);
	private Supervision_BRCD_Kcn_Controller supervisionKCNController = new Supervision_BRCD_Kcn_Controller(
			this);
	private Supervision_BRCD_Drop_Controller supervisionDROPController = new Supervision_BRCD_Drop_Controller(
			this);
	private Supervision_BRCD_Ttb_Controller supervisionTTBController = new Supervision_BRCD_Ttb_Controller(
			this);
	private List<Supervision_BRCD_Drop_Entity> listSupervisionDROP;
	private List<Supervision_BRCD_Ttb_Entity> listSupervisionTTB;
	private List<Supervision_BRCD_Tn_Entity> listSupervisionTN;
	private List<Supervision_BRCD_Kct_Entity> listSupervisionKCT;
	private Supervision_BRCD_Tn_Controller brcd_tn_Controller = new Supervision_BRCD_Tn_Controller(
			this);
	private Supervision_BRCD_Kcn_Controller brcd_kcn_Controller = new Supervision_BRCD_Kcn_Controller(
			this);
	private Supervision_BRCD_Kct_Controller brcd_kct_Controller = new Supervision_BRCD_Kct_Controller(
			this);
	
	//hunkgm - get lat long form constr_contruction table
	private Supervision_ConstrController supervision_ConstrController;
	private Constr_ConstructionController constr_ConstructionController;
	private Constr_ConstructionEntity constr_ConstructionData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_tttk_activity);
		brcd_Controller = new Supervision_BRCD_Controller(
				getApplicationContext());

		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_TTTKView = addView(R.layout.supervision_brcd_tttk_activity, R.id.rl_supervision_line_bg_tank);
		this.tv_constr_brcd_tttk_dropdown = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_constr_brcd_tttk_dropdown);
		this.tv_constr_brcd_tttk_dropdown.setOnClickListener(this);
		this.tv_sosoicaptruc_loaihaitu = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_sosoicaptruc_loaihaitu);
		this.tv_sosoicaptruc_loaihaitu.setOnClickListener(this);
		this.tv_sosoicaptruc_loaibontam = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_sosoicaptruc_loaibontam);
		this.tv_sosoicaptruc_loaibontam.setOnClickListener(this);
		this.tv_sosoicaptruc_loaichinsau = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_sosoicaptruc_loaichinsau);
		this.tv_sosoicaptruc_loaichinsau.setOnClickListener(this);

		this.tv_chonloaicap_capnhanh = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_chonloaicap_capnhanh);
		this.tv_chonloaicap_capnhanh.setOnClickListener(this);

		this.tv_sosoicapnhanh_tunhanh = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_sosoicapnhanh_tunhanh);
		this.tv_sosoicapnhanh_tunhanh.setOnClickListener(this);

		this.tv_sotunhanh_capnhanh = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_sotunhanh_capnhanh);
		this.tv_sotunhanh_capnhanh.setOnClickListener(this);

		this.tv_constr_brcd_tttk_station_code_value = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_constr_brcd_tttk_station_code_value);

		this.tv_constr_brcd_tttk_value = (TextView) spvBRCD_TTTKView
				.findViewById(R.id.tv_constr_brcd_tttk_value);

		this.btn_constr_brcd_ghilai = (Button) spvBRCD_TTTKView
				.findViewById(R.id.btn_constr_brcd_ghilai);
		this.btn_constr_brcd_ghilai.setOnClickListener(this);

		this.ed_socottrongmoi_captruc = (EditText) spvBRCD_TTTKView
				.findViewById(R.id.ed_socottrongmoi_captruc);

		this.ed_socotchongmoi_capnhanh = (EditText) spvBRCD_TTTKView
				.findViewById(R.id.ed_socotchongmoi_capnhanh);

		this.ed_socotcosan_captruc = (EditText) spvBRCD_TTTKView
				.findViewById(R.id.ed_socotcosan_captruc);

		this.ed_socotcosan_capnhanh = (EditText) spvBRCD_TTTKView
				.findViewById(R.id.ed_socotcosan_capnhanh);

		// final int heightScreen = displaymetrics.heightPixels;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		gotoHomeActivity(new Bundle());
		finish();
	}

	@Override
	public void onClick(View v) {
		if(!GlobalInfo.getInstance().isCheckIn() && v.getId()!=R.id.btn_popup_cancel && v.getId()!=R.id.btn_popup_save_plan){
			showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
			return;
		}
		switch (v.getId()) {
		case R.id.tv_chonloaicap_capnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listLoaicapnhanh);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sosoicapnhanh_tunhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listSosoicapnhanh);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sotunhanh_capnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listSotunhanh);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_constr_brcd_tttk_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sosoicaptruc_loaihaitu:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listSosoicaptruc_ht);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sosoicaptruc_loaibontam:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listSosoicaptruc_bt);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_sosoicaptruc_loaichinsau:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listSosoicaptruc_cs);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_constr_brcd_ghilai:
			if(!GlobalInfo.getInstance().isCheckIn()){
				showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
				break;
			}
			String messageError = "";
			messageError = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
				// Toast.makeText(getApplicationContext(), messageError,
				// Toast.LENGTH_LONG).show();
			} else {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save_change));
				confirmSave.show();
			}
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

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_TTTKView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_Thongtintk_Activity.this);
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
					Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();
					brcd_Entity = brcd_Controller
							.getSupervisionBRCD_Sup(itemData
									.getSupervision_Constr_Id());
					Log.d(TAG, "onEvent: spvs id = " + itemData.getSupervision_Constr_Id());
					if (brcd_Entity == null) {
						Toast.makeText(getApplicationContext(),
								"Dữ liệu chưa có", Toast.LENGTH_LONG).show();
						this.dropdownPopupMenuDesignInfo.dismiss();

					} else {
                        showProgressDialog(StringUtil.getString(R.string.text_loading));
						bundleData = new Bundle();

						bundleData.putSerializable(IntentConstants.INTENT_DATA,
								itemData);
						bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
								brcd_Entity.getSUPERVISION_BRCD_ID());

						bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
								dropdownItem.getId());
						this.gotoBrcdBackgroupActivity(bundleData);
						this.dropdownPopupMenuDesignInfo.dismiss();
						this.finish();
					}
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SO)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_sosoicapnhanh_tunhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_sosoicaptruc_loaihaitu.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_sosoicaptruc_loaibontam.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_sosoicaptruc_loaichinsau.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_TU)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_sotunhanh_capnhanh.setText(dropdownItem.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_chonloaicap_capnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		/* Chon nguyen nhan khong dat */
		case OnEventControlListener.EVENT_SUPERVISION_TANK_EDIT:
			this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
			switch (this.curEditItem.getiField()) {

			case Constants.BG_TANK_EDIT.UNQUALIFY:
				if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
					// Gan gia tri nguyen nhan loi
					this.setUnqualify();
					contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listTankUnqualifyItem);
					contruoctionUnqualifyPopup.showAtCenter();
				} else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
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
			case Constants.BG_TANK_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem.getFailDesc();
				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			default:
				break;
			}
			break;

		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			this.supervisionAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;

		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;
			if (this.curEditItem.getiField() == Constants.BG_TANK_EDIT.NUM_PART) {
				boolean bIsEmpty = StringUtil.isNullOrEmpty(sSetTextValue);
				if (bIsEmpty) {
					this.showDialog(StringUtil
							.getString(R.string.text_empty_message));
					return;
				}
				this.curEditItem.setNumberPart(Long.parseLong(sSetTextValue));
			}
			if (this.curEditItem.getiField() == Constants.BG_TANK_EDIT.FAIL_DESC) {
				this.curEditItem.setFailDesc(sSetTextValue);
			}
			this.curEditItem.setEdited(true);
			this.supervisionAdapter.notifyDataSetChanged();
			editTextPopup.hidePopup();
			break;
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
		case OnEventControlListener.EVENT_LOCATION:
			this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
			this.getLocation(this.curEditItem.getLonLocation(),
					this.curEditItem.getLatLocation(),
					StringUtil.getString(R.string.constr_line_tank_name),
					this.curEditItem.getTankName());
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;

			this.imgViewPopup.hidePopup();
			if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
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
			} else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
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
		case Constants.GET_LOCATION_RESULT:
			if (resultCode == Activity.RESULT_OK) {
				this.curEditItem.setLatLocation(data.getDoubleExtra(
						IntentConstants.INTENT_LAT,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				this.curEditItem.setLonLocation(data.getDoubleExtra(
						IntentConstants.INTENT_LONG,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				this.curEditItem.setEdited(true);
				// listSupervisionGS.
				this.supervisionAdapter.notifyDataSetChanged();
			}
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
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
	
	public boolean addnewSupvLocatetoDB(){
		long supv_locate_id = Ktts_KeyController.getInstance().getKttsNextKey(Supervision_Locate_Field.TABLE_NAME);
		if(supv_locate_id==0){
			showAlertDialogCheckInRequire(Supervision_BRCD_Thongtintk_Activity.this,
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

	private void setData() {
		brcd_Entity = new Supervision_BRCD_Entity();
		bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.tv_constr_brcd_tttk_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_tttk_value.setText(itemData.getConstrCode());
		this.tv_constr_brcd_tttk_dropdown.setText(GloablUtils.getStringBRCDBackgroundInfo(iDesignInfo));

		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		// this.listLoaicaptruc = GloablUtils.getListbrcdLoaicaptruc("");
		this.listLoaicapnhanh = GloablUtils.getListbrcdLoaicapnhanh("");
		this.listSosoicapnhanh = GloablUtils.getListbrcdsosoicap("");
		this.listSosoicaptruc_ht = GloablUtils.getListbrcdsosoicaptruc_ht("");
		this.listSosoicaptruc_bt = GloablUtils.getListbrcdsosoicaptruc_bt("");
		this.listSosoicaptruc_cs = GloablUtils.getListbrcdsosoicaptruc_cs("");
		this.listSotunhanh = GloablUtils.getListbrcdsotunhanh("");

		brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
				.getSupervision_Constr_Id());
		if (brcd_Entity != null) {
			num_cab_branch_old = brcd_Entity.getBRANCH_NUM_CABLE();
			sotunhanh_cu = brcd_Entity.getBRANCH_NUM_CHEST();
			sosoicaptruccu_ht = brcd_Entity.getTRUNK_NUM_CABLE_HT();
			sosoicaptruccu_bt = brcd_Entity.getTRUNK_NUM_CABLE_BT();
			sosoicaptruccu_cs = brcd_Entity.getTRUNK_NUM_CABLE_CS();
			ed_socottrongmoi_captruc.setText(String.valueOf(brcd_Entity
					.getTRUNK_PILLAR_NEW_TOTAL()));
			if (brcd_Entity.getBRANCH_PILLAR_NEW_TOTAL() < 0) {
				ed_socotchongmoi_capnhanh.setText(StringUtil
						.getString(R.string.text_empty));
			} else {
				ed_socotchongmoi_capnhanh.setText(String.valueOf(brcd_Entity
						.getBRANCH_PILLAR_NEW_TOTAL()));
			}
			ed_socotcosan_captruc.setText(String.valueOf(brcd_Entity
					.getTRUNK_PILLAR_OLD_TOTAL()));
			if (brcd_Entity.getBRANCH_PILLAR_OLD_TOTAL() < 0) {
				ed_socotcosan_capnhanh.setText(StringUtil
						.getString(R.string.text_empty));
			} else {
				ed_socotcosan_capnhanh.setText(String.valueOf(brcd_Entity
						.getBRANCH_PILLAR_OLD_TOTAL()));
			}
			tv_sosoicaptruc_loaihaitu.setText(String.valueOf(brcd_Entity
					.getTRUNK_NUM_CABLE_HT()));
			tv_sosoicaptruc_loaibontam.setText(String.valueOf(brcd_Entity
					.getTRUNK_NUM_CABLE_BT()));
			tv_sosoicaptruc_loaichinsau.setText(String.valueOf(brcd_Entity
					.getTRUNK_NUM_CABLE_CS()));
			tv_chonloaicap_capnhanh.setText(String.valueOf(brcd_Entity
					.getBRANCH_CABLE_TYPE()));
			tv_sotunhanh_capnhanh.setText(String.valueOf(brcd_Entity
					.getBRANCH_NUM_CHEST()));
			tv_sosoicapnhanh_tunhanh.setText(String.valueOf(brcd_Entity
					.getBRANCH_NUM_CABLE()));
			brcd_Entity.setSYNC_STATUS(Constants.SYNC_STATUS.ADD);
		} else {
			brcd_Entity = new Supervision_BRCD_Entity();
			brcd_Entity.setSYNC_STATUS(Constants.SYNC_STATUS.EDIT);
		}
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.btn_constr_brcd_ghilai.setVisibility(View.GONE);
		}
		
		this.supervision_ConstrController = new Supervision_ConstrController(this);
		this.supervision_ConstrData = this.supervision_ConstrController
				.getItem(itemData.getSupervision_Constr_Id());
		constr_ConstructionController = new Constr_ConstructionController(this);
		constr_ConstructionData = constr_ConstructionController.getItem(supervision_ConstrData.getConstruct_Id());
	}

	public void getSupvBRCD() {
		if (brcd_Entity == null) {
			brcd_Entity = new Supervision_BRCD_Entity();
		}

		sosoicaptruccu_ht = brcd_Entity.getTRUNK_NUM_CABLE_HT();
		sosoicaptruccu_bt = brcd_Entity.getTRUNK_NUM_CABLE_BT();
		sosoicaptruccu_cs = brcd_Entity.getTRUNK_NUM_CABLE_CS();

		num_cab_branch_new = Integer.parseInt(tv_sosoicapnhanh_tunhanh
				.getText().toString());
		brcd_Entity.setBRANCH_CABLE_TYPE(Integer
				.parseInt(tv_chonloaicap_capnhanh.getText().toString()));
		brcd_Entity.setTRUNK_PILLAR_NEW_TOTAL(Integer
				.parseInt(ed_socottrongmoi_captruc.getText().toString()));
		brcd_Entity.setTRUNK_PILLAR_OLD_TOTAL(Integer
				.parseInt(ed_socotcosan_captruc.getText().toString()));
		if (ed_socotchongmoi_capnhanh.getText().toString().equals("")) {
			brcd_Entity.setBRANCH_PILLAR_NEW_TOTAL(0);
		} else {
			brcd_Entity.setBRANCH_PILLAR_NEW_TOTAL(Integer
					.parseInt(ed_socotchongmoi_capnhanh.getText().toString()));
		}
		if (ed_socotcosan_capnhanh.getText().toString().equals("")) {
			brcd_Entity.setBRANCH_PILLAR_OLD_TOTAL(0);
		} else {
			brcd_Entity.setBRANCH_PILLAR_OLD_TOTAL(Integer
					.parseInt(ed_socotcosan_capnhanh.getText().toString()));
		}

		brcd_Entity.setBRANCH_NUM_CABLE(Integer
				.parseInt(tv_sosoicapnhanh_tunhanh.getText().toString()));
		brcd_Entity.setBRANCH_NUM_CHEST(Integer.parseInt(tv_sotunhanh_capnhanh
				.getText().toString()));

		if (tv_sosoicaptruc_loaihaitu.getText().toString().equals("--Chọn--")) {
			brcd_Entity.setTRUNK_NUM_CABLE_HT(0);
		} else {
			brcd_Entity.setTRUNK_NUM_CABLE_HT(Integer
					.parseInt(tv_sosoicaptruc_loaihaitu.getText().toString()));
		}
		if (tv_sosoicaptruc_loaibontam.getText().toString().equals("--Chọn--")) {
			brcd_Entity.setTRUNK_NUM_CABLE_BT(0);
		} else {
			brcd_Entity.setTRUNK_NUM_CABLE_BT(Integer
					.parseInt(tv_sosoicaptruc_loaibontam.getText().toString()));
		}
		if (tv_sosoicaptruc_loaichinsau.getText().toString().equals("--Chọn--")) {
			brcd_Entity.setTRUNK_NUM_CABLE_CS(0);
		} else {
			brcd_Entity
					.setTRUNK_NUM_CABLE_CS(Integer
							.parseInt(tv_sosoicaptruc_loaichinsau.getText()
									.toString()));
		}
		sotunhanh_moi = Integer.parseInt(tv_sotunhanh_capnhanh.getText()
				.toString().trim());

	}

	public void UpdateSupvConstr() {
		if (supervision_ConstrData == null) {
			supervision_ConstrData = new Supervision_ConstrEntity();
		}
		if(supervision_ConstrController == null){
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
		UpdateSupvConstr();
		getSupvBRCD();
		long idUser = GlobalInfo.getInstance().getUserId();

		brcd_Entity.setSUPERVISION_CONSTR_ID(itemData
				.getSupervision_Constr_Id());
		brcd_Entity.setEMPLOYEE_ID(idUser);
		brcd_Entity.setIS_ACTIVE(Constants.ISACTIVE.ACTIVE);

		brcd_Controller = new Supervision_BRCD_Controller(this);

		if (brcd_Entity.getSYNC_STATUS() == Constants.SYNC_STATUS.EDIT) {
			brcd_Entity.setSYNC_STATUS(Constants.SYNC_STATUS.ADD);
			long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
					Supervision_BRCD_Field.TABLE_NAME);
			if (idSupervion == 0) {
				Toast.makeText(Supervision_BRCD_Thongtintk_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_Entity.setSUPERVISION_BRCD_ID(idSupervion);
			brcd_Controller.addItem(brcd_Entity);

			if (sotunhanh_moi > 0) {
				for (int i = 0; i < sotunhanh_moi; i++) {

					Supervision_BRCD_Tn_Entity brcd_kcn = new Supervision_BRCD_Tn_Entity();
					long idSupervion_tn = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_BRCD_Tn_Field.TABLE_NAME);
					if (idSupervion_tn == 0) {
						Toast.makeText(
								Supervision_BRCD_Thongtintk_Activity.this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();

					}
					brcd_kcn.setSupervisionConstrId(itemData
							.getSupervision_Constr_Id());
					brcd_kcn.setEmployeeId(idUser);
					brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
					brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
					brcd_kcn.setSUPERVISION_BRCD_ID(idSupervion);
					brcd_kcn.setCabinet_Name("DN0" + (i + 1));
					brcd_kcn.setSupervition_branch_cabinet_id(idSupervion_tn);
					brcd_tn_Controller.addItem(brcd_kcn);
				}
			}
			if (num_cab_branch_new > 0) {
				for (int i = 0; i < num_cab_branch_new; i++) {

					Supervision_BRCD_Kcn_Entity brcd_kcn = new Supervision_BRCD_Kcn_Entity();
					long idSupervion_kcn = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_BRCD_Kcn_Field.TABLE_NAME);
					if (idSupervion_kcn == 0) {
						Toast.makeText(
								Supervision_BRCD_Thongtintk_Activity.this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();

					}
					brcd_kcn.setEmployeeId(idUser);
					brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
					brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
					brcd_kcn.setSUPERVISION_BRCD_ID(idSupervion);
					brcd_kcn.setCable_Name("Nhánh " + (i + 1));
					brcd_kcn.setSupervition_branch_cable_id(idSupervion_kcn);
					brcd_kcn_Controller.addItem(brcd_kcn);
				}
			}

			if (!tv_sosoicaptruc_loaihaitu.getText().equals("--Chọn--")) {

				sosoicaptrucmoi_ht = Integer.parseInt(tv_sosoicaptruc_loaihaitu
						.getText().toString().trim());
				addTrunkCable(sosoicaptrucmoi_ht, 24, idSupervion, idUser);

			}
			if (!tv_sosoicaptruc_loaibontam.getText().equals("--Chọn--")) {

				sosoicaptrucmoi_bt = Integer
						.parseInt(tv_sosoicaptruc_loaibontam.getText()
								.toString().trim());
				addTrunkCable(sosoicaptrucmoi_bt, 48, idSupervion, idUser);

			}
			if (!tv_sosoicaptruc_loaichinsau.getText().equals("--Chọn--")) {

				sosoicaptrucmoi_cs = Integer
						.parseInt(tv_sosoicaptruc_loaichinsau.getText()
								.toString().trim());
				addTrunkCable(sosoicaptrucmoi_cs, 96, idSupervion, idUser);

			}

			sotunhanh_cu = Integer.parseInt(tv_sotunhanh_capnhanh.getText()
					.toString().trim());
			sosoicaptruccu_ht = 0;
			if (!tv_sosoicaptruc_loaihaitu.getText().equals("--Chọn--")) {
				sosoicaptruccu_ht = Integer.parseInt(tv_sosoicaptruc_loaihaitu
						.getText().toString().trim());

			}
			sosoicaptruccu_bt = 0;
			if (!tv_sosoicaptruc_loaibontam.getText().equals("--Chọn--")) {
				sosoicaptruccu_bt = Integer.parseInt(tv_sosoicaptruc_loaibontam
						.getText().toString().trim());

			}
			sosoicaptruccu_cs = 0;
			if (!tv_sosoicaptruc_loaichinsau.getText().equals("--Chọn--")) {
				sosoicaptruccu_cs = Integer
						.parseInt(tv_sosoicaptruc_loaichinsau.getText()
								.toString().trim());

			}

			num_cab_branch_old = Integer.parseInt(tv_sosoicapnhanh_tunhanh
					.getText().toString());

		}

		else if (brcd_Entity.getSYNC_STATUS() == Constants.SYNC_STATUS.ADD) {
			// num_cab_branch_old = brcd_Entity.getBRANCH_NUM_CABLE();
			// sotunhanh_cu = brcd_Entity.getBRANCH_NUM_CHEST();
			// sosoicaptruccu = brcd_Entity.getTRUNK_NUM_CABLE();
			sotunhanh_moi = Integer.parseInt(tv_sotunhanh_capnhanh.getText()
					.toString().trim());
			sosoicaptrucmoi_ht = Integer.parseInt(tv_sosoicaptruc_loaihaitu
					.getText().toString().trim());
			sosoicaptrucmoi_bt = Integer.parseInt(tv_sosoicaptruc_loaibontam
					.getText().toString().trim());
			sosoicaptrucmoi_cs = Integer.parseInt(tv_sosoicaptruc_loaichinsau
					.getText().toString().trim());
			num_cab_branch_new = Integer.parseInt(tv_sosoicapnhanh_tunhanh
					.getText().toString());
			// if(check_load > 0){
			// Bundle bun = getIntent().getExtras();
			// this.itemData = (Constr_Construction_EmployeeEntity) bun
			// .getSerializable(IntentConstants.INTENT_DATA);
			// brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
			// .getSupervision_Constr_Id());
			// num_cab_branch_old = brcd_Entity.getBRANCH_NUM_CABLE();
			// sotunhanh_cu = brcd_Entity.getBRANCH_NUM_CHEST();
			// sosoicaptruccu = brcd_Entity.getTRUNK_NUM_CABLE();
			// sotunhanh_moi = Integer.parseInt(tv_sotunhanh_capnhanh.getText()
			// .toString().trim());
			// sosoicaptrucmoi =
			// Integer.parseInt(tv_sosoicaptruc.getText().toString()
			// .trim());
			// num_cab_branch_new = Integer.parseInt(tv_sosoicapnhanh_tunhanh
			// .getText().toString());

			// }
			brcd_Entity.setSYNC_STATUS(Constants.SYNC_STATUS.ADD);
			brcd_Controller.updateSupervisionBRCD(
					brcd_Entity.getSUPERVISION_BRCD_ID(), brcd_Entity);
			if (num_cab_branch_old > num_cab_branch_new) {
				listSupervisionKCN = new ArrayList<Supervision_BRCD_Kcn_Entity>();

				listSupervisionKCN = supervisionKCNController
						.getAllbrcd_kcn(brcd_Entity.getSUPERVISION_BRCD_ID());
				for (int i = 0; i < (num_cab_branch_old - num_cab_branch_new); i++) {
					supervisionKCNController.deleteKcnEntity(listSupervisionKCN
							.get((listSupervisionKCN.size() - (i + 1))));

					brcd_kcn_design = brcd_kcn_design_Controller
							.getSupervisionBRCD_Kcn_design(listSupervisionKCN
									.get((listSupervisionKCN.size() - (i + 1)))
									.getSupervition_branch_cable_id());

					listSupervisionDROP = new ArrayList<Supervision_BRCD_Drop_Entity>();
					listSupervisionTTB = new ArrayList<Supervision_BRCD_Ttb_Entity>();
					if (brcd_kcn_design != null) {
						listSupervisionDROP = supervisionDROPController
								.getAllbrcd_drop_design_full_type(brcd_kcn_design
										.getSupervition_branch_design_id());
						for (int j = 0; j < listSupervisionDROP.size(); j++) {
							supervisionDROPController
									.deleteDropEntity(listSupervisionDROP
											.get(j));
						}

						listSupervisionTTB = supervisionTTBController
								.getAllbrcd_ttb_full_type(brcd_kcn_design
										.getSupervition_branch_design_id());
						for (int j = 0; j < listSupervisionTTB.size(); j++) {
							supervisionTTBController
									.deleteTtbEntity(listSupervisionTTB.get(j));
						}

					}
				}

			} else if (num_cab_branch_old < num_cab_branch_new) {
				for (int i = 0; i < (num_cab_branch_new - num_cab_branch_old); i++) {

					Supervision_BRCD_Kcn_Entity brcd_kcn = new Supervision_BRCD_Kcn_Entity();
					long idSupervion_kcn = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_BRCD_Kcn_Field.TABLE_NAME);
					if (idSupervion_kcn == 0) {
						Toast.makeText(
								Supervision_BRCD_Thongtintk_Activity.this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();

					}
					brcd_kcn.setEmployeeId(idUser);
					brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
					brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
					brcd_kcn.setSUPERVISION_BRCD_ID(brcd_Entity
							.getSUPERVISION_BRCD_ID());
					brcd_kcn.setCable_Name("Nhánh "
							+ (num_cab_branch_old + i + 1));
					brcd_kcn.setSupervition_branch_cable_id(idSupervion_kcn);
					brcd_kcn_Controller.addItem(brcd_kcn);

				}

			}
			if (sotunhanh_cu > sotunhanh_moi) {
				listSupervisionTN = new ArrayList<Supervision_BRCD_Tn_Entity>();
				listSupervisionTN = brcd_tn_Controller
						.getAllbrcd_tn(brcd_Entity.getSUPERVISION_BRCD_ID());
				for (int i = 0; i < (sotunhanh_cu - sotunhanh_moi); i++) {
					brcd_tn_Controller.deleteTnEntity(listSupervisionTN
							.get((listSupervisionTN.size() - (i + 1))));
				}

			} else if (sotunhanh_cu < sotunhanh_moi) {
				for (int i = 0; i < (sotunhanh_moi - sotunhanh_cu); i++) {
					Supervision_BRCD_Tn_Entity brcd_kcn = new Supervision_BRCD_Tn_Entity();
					long idSupervion_tn = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_BRCD_Tn_Field.TABLE_NAME);
					if (idSupervion_tn == 0) {
						Toast.makeText(
								Supervision_BRCD_Thongtintk_Activity.this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();

					}
					brcd_kcn.setSupervisionConstrId(itemData
							.getSupervision_Constr_Id());
					brcd_kcn.setEmployeeId(idUser);
					brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
					brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
					brcd_kcn.setSUPERVISION_BRCD_ID(brcd_Entity
							.getSUPERVISION_BRCD_ID());
					brcd_kcn.setCabinet_Name("DN0" + (sotunhanh_cu + i + 1));
					brcd_kcn.setSupervition_branch_cabinet_id(idSupervion_tn);
					brcd_tn_Controller.addItem(brcd_kcn);
				}
			}

			if (tv_sosoicaptruc_loaihaitu.getText().toString().trim()
					.equals("")) {
				sosoicaptrucmoi_ht = 0;

				UpdateaddSCT(sosoicaptruccu_ht, sosoicaptrucmoi_ht, 24, idUser);

			} else {
				sosoicaptrucmoi_ht = Integer.parseInt(tv_sosoicaptruc_loaihaitu
						.getText().toString().trim());
				UpdateaddSCT(sosoicaptruccu_ht, sosoicaptrucmoi_ht, 24, idUser);
			}

			if (tv_sosoicaptruc_loaibontam.getText().toString().trim()
					.equals("")) {
				sosoicaptrucmoi_bt = 0;

				UpdateaddSCT(sosoicaptruccu_bt, sosoicaptrucmoi_bt, 48, idUser);

			} else {
				sosoicaptrucmoi_bt = Integer
						.parseInt(tv_sosoicaptruc_loaibontam.getText()
								.toString().trim());
				UpdateaddSCT(sosoicaptruccu_bt, sosoicaptrucmoi_bt, 48, idUser);
			}
			if (tv_sosoicaptruc_loaichinsau.getText().toString().trim()
					.equals("")) {
				sosoicaptrucmoi_cs = 0;

				UpdateaddSCT(sosoicaptruccu_cs, sosoicaptrucmoi_cs, 96, idUser);

			} else {
				sosoicaptrucmoi_cs = Integer
						.parseInt(tv_sosoicaptruc_loaichinsau.getText()
								.toString().trim());
				UpdateaddSCT(sosoicaptruccu_cs, sosoicaptrucmoi_cs, 96, idUser);
			}

			// num_cab_branch_old = brcd_Entity.getBRANCH_NUM_CABLE();
			// sotunhanh_cu = brcd_Entity.getBRANCH_NUM_CHEST();
			// sosoicaptruccu = brcd_Entity.getTRUNK_NUM_CABLE();
			sotunhanh_cu = Integer.parseInt(tv_sotunhanh_capnhanh.getText()
					.toString().trim());
			sosoicaptruccu_ht = Integer.parseInt(tv_sosoicaptruc_loaihaitu
					.getText().toString().trim());
			sosoicaptruccu_bt = Integer.parseInt(tv_sosoicaptruc_loaibontam
					.getText().toString().trim());
			sosoicaptruccu_cs = Integer.parseInt(tv_sosoicaptruc_loaichinsau
					.getText().toString().trim());
			num_cab_branch_old = Integer.parseInt(tv_sosoicapnhanh_tunhanh
					.getText().toString());

		}

	}

	private void UpdateaddSCT(int sosoicaptruccu, int sosoicaptrucmoi,
			int loaicap, long idUser) {
		if (sosoicaptrucmoi < sosoicaptruccu) {
			// listSupervisionKCT = new
			// ArrayList<Supervision_BRCD_Kct_Entity>();
			listSupervisionKCT = brcd_kct_Controller.getAllbrcd_kct_type(
					brcd_Entity.getSUPERVISION_BRCD_ID(), loaicap);
			for (int i = 0; i < (sosoicaptruccu - sosoicaptrucmoi); i++) {
				brcd_kct_Controller.deleteKctEntity(listSupervisionKCT
						.get((listSupervisionKCT.size() - (i + 1))));
			}
		} else {
			for (int i = 0; i < (sosoicaptrucmoi - sosoicaptruccu); i++) {
				Supervision_BRCD_Kct_Entity brcd_kct = new Supervision_BRCD_Kct_Entity();
				long idSupervion_tn = Ktts_KeyController.getInstance()
						.getKttsNextKey(Supervision_BRCD_Kct_Field.TABLE_NAME);
				if (idSupervion_tn == 0) {
					Toast.makeText(Supervision_BRCD_Thongtintk_Activity.this,
							StringUtil.getString(R.string.text_out_of_key),
							Toast.LENGTH_SHORT).show();

				}
				brcd_kct.setSupervisionConstrId(itemData
						.getSupervision_Constr_Id());
				brcd_kct.setEmployeeId(idUser);
				brcd_kct.setIsActive(Constants.ISACTIVE.ACTIVE);
				brcd_kct.setSync_Status(Constants.SYNC_STATUS.ADD);
				brcd_kct.setCable_type(loaicap);
				brcd_kct.setSupervition_brcd_id(brcd_Entity
						.getSUPERVISION_BRCD_ID());
				brcd_kct.setSUPERVISION_TRUNK_CABLE_ID(idSupervion_tn);
				brcd_kct_Controller.addItem(brcd_kct);
			}
		}

	}

	private void addTrunkCable(int sosoicaptrucmoi_ht, int loaicap,
			long idSupervion, long idUser) {
		for (int i = 0; i < sosoicaptrucmoi_ht; i++) {

			Supervision_BRCD_Kct_Entity brcd_kct = new Supervision_BRCD_Kct_Entity();
			long idSupervion_kct = Ktts_KeyController.getInstance()
					.getKttsNextKey(Supervision_BRCD_Kct_Field.TABLE_NAME);
			if (idSupervion_kct == 0) {
				Toast.makeText(Supervision_BRCD_Thongtintk_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_kct.setSupervisionConstrId(itemData.getSupervision_Constr_Id());
			brcd_kct.setEmployeeId(idUser);
			brcd_kct.setCable_type(loaicap);
			brcd_kct.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct.setSupervition_brcd_id(idSupervion);
			brcd_kct.setSUPERVISION_TRUNK_CABLE_ID(idSupervion_kct);
			brcd_kct.setLength(0);
			brcd_kct_Controller.addItem(brcd_kct);
		}
	}

	private String checkVailid() {
		String sReslut = "";

		try {
			if (ed_socottrongmoi_captruc.getText().toString().trim().equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_cot_chong_moi);
				ed_socottrongmoi_captruc.requestFocus();
			} else if (ed_socotcosan_captruc.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_cot_co_san);
				ed_socotcosan_captruc.requestFocus();
			}  else if (tv_chonloaicap_capnhanh.getText().equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_loai_cap_nhanh);
			} else if (tv_sosoicapnhanh_tunhanh.getText().equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_soi_cap_nhanh);
			} else if (tv_sotunhanh_capnhanh.getText().equals("--Chọn--")) {
				sReslut = StringUtil.getString(R.string.brcd_chon_so_tu_nhanh);
				ed_socottrongmoi_captruc.requestFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
				Toast.makeText(Supervision_BRCD_Thongtintk_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			Toast.makeText(Supervision_BRCD_Thongtintk_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}