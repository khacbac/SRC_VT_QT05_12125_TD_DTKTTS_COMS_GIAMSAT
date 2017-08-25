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
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_Drop_Design_Controller;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Design_Entity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Supervision_BRCD_Drop_Design_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_Line_BGAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_DropGo_ChiTiet_Activity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_DROPGO_CHTView;
	private TextView tv_constr_brcd_dropgo_dropdown,
			tv_constr_brcd_dropgo_station_code_value,
			tv_constr_brcd_dropgo_value, tv_constr_brcd_dropgo_name,
			tv_chonloaicap_dropgo;

	private EditText ed_socotcosan_dropgo, ed_socotchongmoi_dropgo,
			ed_chieudai_dropgo;

	private Button btn_constr_brcd_luu_dropgo, btn_constr_brcd_back_dropgo;

	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.DROP_GO;
	private int iDesignInfo_loaicap;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listDesignInfo_Loaicap = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;

	private Bundle bundleData;
	
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_Line_BG_TankController supervisionLBGController;
	private Cause_Line_BG_TankController causeLineBGTankController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;

	private Supervision_BRCD_Drop_Design_Entity brcd_drop_design = new Supervision_BRCD_Drop_Design_Entity();
	private Supervision_BRCD_Drop_Design_Controller brcd_drop_design_Controller = new Supervision_BRCD_Drop_Design_Controller(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_Line_BG_TankGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Supervision_Line_BGAdapter supervisionAdapter;
	private Supervision_BRCD_Drop_Entity itemData_drop;
	private boolean outOfKey = false;
	private float positionTouch = 0f;

	private TextView supervision_bg_tank_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_dropgo_chitiet_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_DROPGO_CHTView = addView(R.layout.supervision_brcd_dropgo_chitiet_activity, R.id.rl_supervision_line_bg_tank);
		this.btn_constr_brcd_back_dropgo = (Button) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.btn_constr_brcd_back_dropgo);
		this.btn_constr_brcd_back_dropgo.setOnClickListener(this);
		this.tv_constr_brcd_dropgo_dropdown = (TextView) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.tv_constr_brcd_dropgo_dropdown);
		this.tv_constr_brcd_dropgo_dropdown.setOnClickListener(this);

		this.tv_chonloaicap_dropgo = (TextView) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.tv_chonloaicap_dropgo);
		this.tv_chonloaicap_dropgo.setOnClickListener(this);

		this.tv_constr_brcd_dropgo_station_code_value = (TextView) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.tv_constr_brcd_dropgo_station_code_value);

		this.tv_constr_brcd_dropgo_value = (TextView) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.tv_constr_brcd_dropgo_value);

		this.tv_constr_brcd_dropgo_name = (TextView) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.tv_constr_brcd_dropgo_name);

		this.btn_constr_brcd_luu_dropgo = (Button) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.btn_constr_brcd_luu_dropgo);
		this.btn_constr_brcd_luu_dropgo.setOnClickListener(this);

		this.ed_socotcosan_dropgo = (EditText) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.ed_socotcosan_dropgo);

		this.ed_socotchongmoi_dropgo = (EditText) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.ed_socotchongmoi_dropgo);

		this.ed_chieudai_dropgo = (EditText) spvBRCD_DROPGO_CHTView
				.findViewById(R.id.ed_chieudai_dropgo);

		// final int heightScreen = displaymetrics.heightPixels;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.tv_constr_brcd_dropgo_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_constr_brcd_back_dropgo:
			Intent it = new Intent(
					Supervision_BRCD_DropGo_ChiTiet_Activity.this,
					Supervision_BRCD_DropGo_Activity.class);
			bundleData = new Bundle();

			bundleData.putSerializable(IntentConstants.INTENT_DATA, itemData);
			bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
					itemData_drop.getSUPERVISION_BRCD_ID());
			it.putExtras(bundleData);
			this.startActivity(it);
			break;
		case R.id.btn_constr_brcd_luu_dropgo:
			String messageError = "";
			messageError = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
				// Toast.makeText(getApplicationContext(), messageError,
				// Toast.LENGTH_LONG).show();
			} else {
				
				Supervision_BRCD_DropGo_Activity.isMonitoring = false;
				
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			break;

		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_DROPGO_CHTView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_DropGo_ChiTiet_Activity.this);
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
					bundleData = new Bundle();
					bundleData.putSerializable(IntentConstants.INTENT_DATA,
							itemData);
					bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
							itemData_drop.getSUPERVISION_BRCD_ID());
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);

					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBrcdBackgroupActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();

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
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void setData() {
		bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.itemData_drop = (Supervision_BRCD_Drop_Entity) bundleData
				.getSerializable(IntentConstants.INTENT_BRCD_DROP);
		this.tv_constr_brcd_dropgo_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_dropgo_value.setText(itemData.getConstrCode());
		this.tv_constr_brcd_dropgo_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));

		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		this.listDesignInfo_Loaicap = GloablUtils.getListbrcdLoaicapdrop("");
		this.tv_constr_brcd_dropgo_name.setText(""
				+ itemData_drop.getDrop_code());
		brcd_drop_design = brcd_drop_design_Controller
				.getSupervisionBRCD_Drop_design(itemData_drop
						.getSupervition_branch_drop_id());
		tv_chonloaicap_dropgo.setText("" + itemData_drop.getCab_type());
		if (brcd_drop_design != null) {

			ed_socotcosan_dropgo.setText(""
					+ brcd_drop_design.getPillar_Old_Number());
			ed_socotchongmoi_dropgo.setText(""
					+ brcd_drop_design.getPillar_New_Number());
			ed_chieudai_dropgo.setText("" + brcd_drop_design.getCable_length());

			brcd_drop_design.setSync_Status(Constants.SYNC_STATUS.ADD);

		} else {
			brcd_drop_design = new Supervision_BRCD_Drop_Design_Entity();
			brcd_drop_design.setSync_Status(Constants.SYNC_STATUS.EDIT);

		}
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.btn_constr_brcd_luu_dropgo.setVisibility(View.GONE);
		}

	}

	private void saveDataTank() {
		getSupvBRCD_Drop();

		long idUser = GlobalInfo.getInstance().getUserId();
		long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
				Supervision_BRCD_Drop_Design_Field.TABLE_NAME);
		if (idSupervion == 0) {
			Toast.makeText(Supervision_BRCD_DropGo_ChiTiet_Activity.this,
					StringUtil.getString(R.string.text_out_of_key),
					Toast.LENGTH_SHORT).show();

		}
		if (brcd_drop_design.getSync_Status() == Constants.SYNC_STATUS.EDIT) {

			brcd_drop_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_drop_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_drop_design.setProcessId(0);
			brcd_drop_design.setEmployeeId(idUser);
			brcd_drop_design.setSupervition_branch_drop_design_id(idSupervion);
			brcd_drop_design.setSupervition_branch_drop_id(itemData_drop
					.getSupervition_branch_drop_id());
			brcd_drop_design_Controller.addItem(brcd_drop_design);
		} else if (brcd_drop_design.getSync_Status() == Constants.SYNC_STATUS.ADD) {
			brcd_drop_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_drop_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_drop_design.setProcessId(0);
			brcd_drop_design.setEmployeeId(idUser);
			brcd_drop_design_Controller.updateSupervisionBRCD_Drop_Design(
					brcd_drop_design.getSupervition_branch_drop_design_id(),
					brcd_drop_design);
		}
		
//		// TODO: Thiet lap ket luan. DanhDue ExOICTIF
//		Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
//				this);
//		bundleData = this.getIntent().getExtras();
//		boolean bDeny = checkBRCDCauseDeny(bundleData);
//		Log.i("Check_Deny", String.valueOf(bDeny));
//		if (bDeny) {
//			constr_Controller.updateStatus(
//					itemData.getSupervision_Constr_Id(), 0);
//		} else {
//			constr_Controller.updateStatus(
//					itemData.getSupervision_Constr_Id(), 1);
//		}
		
	}

	private void getSupvBRCD_Drop() {
		if (brcd_drop_design == null) {
			brcd_drop_design = new Supervision_BRCD_Drop_Design_Entity();
		} else {

			brcd_drop_design
					.setCable_Type(Integer.parseInt(tv_chonloaicap_dropgo
							.getText().toString().trim()));
			brcd_drop_design
					.setPillar_Old_Number(Integer.parseInt(ed_socotcosan_dropgo
							.getText().toString().trim()));
			if (ed_socotchongmoi_dropgo.getText().toString().trim().equals("")) {
				brcd_drop_design.setPillar_New_Number(0);
			} else {
				brcd_drop_design.setPillar_New_Number(Integer
						.parseInt(ed_socotchongmoi_dropgo.getText().toString()
								.trim()));
			}
			brcd_drop_design.setCable_length(Integer
					.parseInt(ed_chieudai_dropgo.getText().toString().trim()));
		}

	}

	private String checkVailid() {
		String sReslut = "";
		try {
			if (ed_socotcosan_dropgo.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_cot_co_san);
				ed_socotcosan_dropgo.requestFocus();
			} else if (ed_chieudai_dropgo.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_chieu_dai_cap);
				ed_chieudai_dropgo.requestFocus();
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
				Toast.makeText(Supervision_BRCD_DropGo_ChiTiet_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			Toast.makeText(Supervision_BRCD_DropGo_ChiTiet_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}