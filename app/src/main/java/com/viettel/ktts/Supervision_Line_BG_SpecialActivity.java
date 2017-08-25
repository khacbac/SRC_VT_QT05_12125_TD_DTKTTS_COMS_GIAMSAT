package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Contruction_AcceptancePopup;
import com.viettel.actionbar.Contruction_UnqualifyPopup;
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
import com.viettel.database.Cause_Line_BG_SpecialController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Special_LocationController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_SpecialEntity;
import com.viettel.database.entity.Cause_Line_BG_SpecialEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Special_LocationGSEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Line_Bg_SpecialField;
import com.viettel.database.field.Cause_Line_BG_SpecialField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_BG_SpecialAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_SpecialActivity extends LineBaseActivity {
	private View spvLineBGSpclView;
	private ListView lv_line_bg_special_list;
	private TextView tv_line_bg_special_dropdown;
	private TextView tv_line_bg_special_info_line_station_code_value;
	private TextView tv_line_bg_special_info_line_value;
	private TextView supervision_bg_special_complete_date;
	private Button btn_line_bg_special_save;
	private Button rl_supervision_bg_special_bt_complete;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<Supervision_LBG_UnqualifyItemEntity> listSpecialUnqualifyItem;
	/* Tuyen ngam giam sat */
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemConstrData;
	/* bien dropdown */
	private int iDesignInfo = 1;
	private List<Special_LocationGSEntity> listSupervisionSpecialGS;
	private Special_LocationController supervisionLBGSpecialController;
	private Cause_Line_BG_SpecialController causeLineBGPSpecialController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	private Supervision_Line_BG_SpecialAdapter supervisionSpecialAdapter;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Special_LocationGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
//	private Image_ViewGalleryPopup imgViewPopup;
	private boolean outOfKey = false;
	private Bundle bundleData;

	private List<Supervision_LBG_UnqualifyItemEntity> listSpecialAcceptanceItem;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_special_activity);
		setTitle(getString(R.string.line_background_header_title));
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvLineBGSpclView = addView(R.layout.supervision_line_bg_special_activity, R.id.rl_supervision_line_bg_special);
		this.lv_line_bg_special_list = (ListView) spvLineBGSpclView
				.findViewById(R.id.lv_line_bg_special_list);
		this.tv_line_bg_special_dropdown = (TextView) spvLineBGSpclView
				.findViewById(R.id.tv_line_bg_special_dropdown);
		this.tv_line_bg_special_dropdown.setOnClickListener(this);

		this.supervision_bg_special_complete_date = (TextView) spvLineBGSpclView
				.findViewById(R.id.supervision_bg_special_complete_date);

		this.tv_line_bg_special_info_line_value = (TextView) spvLineBGSpclView
				.findViewById(R.id.tv_line_bg_special_info_line_value);

		this.tv_line_bg_special_info_line_station_code_value = (TextView) spvLineBGSpclView
				.findViewById(R.id.tv_line_bg_special_info_line_station_code_value);
		this.btn_line_bg_special_save = (Button) spvLineBGSpclView
				.findViewById(R.id.btn_line_bg_special_save);
		this.btn_line_bg_special_save.setOnClickListener(this);

		this.rl_supervision_bg_special_bt_complete = (Button) spvLineBGSpclView
				.findViewById(R.id.rl_supervision_bg_special_bt_complete);

		this.rl_supervision_bg_special_bt_complete.setOnClickListener(this);
	}

	private void setData() {
		try {
			bundleData = this.getIntent().getExtras();
			this.itemConstrData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA_EX);
			this.iDesignInfo = bundleData
					.getInt(IntentConstants.INTENT_DESIGNINFO);

			this.tv_line_bg_special_dropdown.setText(GloablUtils
					.getStringLineBackgroundInfo(iDesignInfo));

			this.tv_line_bg_special_info_line_value.setText(this.itemConstrData
					.getConstrCode());

			this.tv_line_bg_special_info_line_station_code_value
					.setText(itemConstrData.getStationCode());

			supervisionLBGSpecialController = new Special_LocationController(
					this);
			causeLineBGPSpecialController = new Cause_Line_BG_SpecialController(
					this);
			supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
					this);

			listSpecialUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(
					this).getAllUnQualifyItemByName(
					Constants.UNQUALIFY_TYPE.BG_SPECIAL_LOCATION,
					Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

			listSpecialAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
					this).getAllUnQualifyItemByName(
					Constants.ACCEPTANCE_TYPE.BG_SPECIAL_LOCATION,
					Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

			refreshListView();

			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");

			showCompleteDate(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_SPECIAL,
					supervision_bg_special_complete_date,
					rl_supervision_bg_special_bt_complete);

			if (itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_bg_special_save.setVisibility(View.GONE);
				// this.rl_supervision_bg_special_bt_complete.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.gotoHomeActivity(new Bundle());
		}
	}

	private void refreshListView() {
		listSupervisionSpecialGS = supervisionLBGSpecialController
				.getAllSpecialGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		/* Truong hop Load luu du lieu */
		if (listSupervisionSpecialGS.size() > 0) {
			for (Special_LocationGSEntity curItemSupervisonGS : listSupervisionSpecialGS) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeLineBGPSpecialController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getIdSpecial());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeLineBGPSpecialController
						.getAllSpecialAcceptByTankId(curItemSupervisonGS
								.getIdSpecial());

				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Line_Bg_SpecialField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListAcceptance(listAcceptItem);

				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Cause_Line_BG_SpecialField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				curItemSupervisonGS.setListCauseUniQualify(listUnqualifyItem);
			}
		}
		this.supervisionSpecialAdapter = new Supervision_Line_BG_SpecialAdapter(
				this, listSupervisionSpecialGS);
		this.lv_line_bg_special_list.setAdapter(supervisionSpecialAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click nut hoan thanh
		case R.id.rl_supervision_bg_special_bt_complete:
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		case R.id.btn_line_bg_special_save:
			String messageError = "";
			for (Special_LocationGSEntity curItemData : listSupervisionSpecialGS) {
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
			break;

		case R.id.tv_line_bg_special_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;

		default:
			// this.hideKeyboard(v);
			break;
		}
	}

	private String checkVailid(Special_LocationGSEntity itemCheck) {
		String sReslut = "";
		try {
			int countNnkdCheck = 0;

			for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
				if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
					countNnkdCheck++;
					break;
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
			if (itemCheck.getStatus() == Constants.TANK_STATUS.DAT
					&& countAcceptCheck < 1) {
				sReslut = StringUtil
						.getString(R.string.constr_take_acceptance_not_enough);
			}

			if (itemCheck.getStatus() == Constants.TANK_STATUS.KHONG_DAT
					&& countNnkdCheck < 1) {
				sReslut = StringUtil
						.getString(R.string.constr_line_special_nn_khongdat_tempty);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
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
				if (this.curEditItem.getIdField() == Constants.BG_SPECIAL_EDIT.LOCATION_START) {
					this.curEditItem.setLatStartLocation(data.getDoubleExtra(
							IntentConstants.INTENT_LAT,
							Constants.ID_DOUBLE_ENTITY_DEFAULT));
					this.curEditItem.setLonStartLocation(data.getDoubleExtra(
							IntentConstants.INTENT_LONG,
							Constants.ID_DOUBLE_ENTITY_DEFAULT));
					this.curEditItem.setEdited(true);
					this.supervisionSpecialAdapter.notifyDataSetChanged();
				} else {
					this.curEditItem.setLatEndLocation(data.getDoubleExtra(
							IntentConstants.INTENT_LAT,
							Constants.ID_DOUBLE_ENTITY_DEFAULT));
					this.curEditItem.setLonEndLocation(data.getDoubleExtra(
							IntentConstants.INTENT_LONG,
							Constants.ID_DOUBLE_ENTITY_DEFAULT));
					this.curEditItem.setEdited(true);
					this.supervisionSpecialAdapter.notifyDataSetChanged();
				}

			}
			break;
		default:
			break;
		}
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
							itemConstrData);
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.gotoLineBackgroupActivity(bundleData);
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		case OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY:
			this.curEditItem = (Special_LocationGSEntity) data;
			if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
				// Gan gia tri nguyen nhan loi
				this.setUnqualify();
				contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listSpecialUnqualifyItem);
				contruoctionUnqualifyPopup.showAtCenter();
			} else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
				this.setAcceptance();
				contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
						this, null, this.listSpecialAcceptanceItem);
				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
			this.curEditItem = (Special_LocationGSEntity) data;
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Special_LocationGSEntity) data;
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			this.supervisionSpecialAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionSpecialAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
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
		// chup anh nguyen nhan loi
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
		case OnEventControlListener.EVENT_LOCATION_START:
			this.curEditItem = (Special_LocationGSEntity) data;
			this.getLocation(this.curEditItem.getLonStartLocation(),
					this.curEditItem.getLatStartLocation(),
					StringUtil.getString(R.string.constr_line_name),
					this.curEditItem.getSpecialName());
			break;
		case OnEventControlListener.EVENT_LOCATION_END:
			this.curEditItem = (Special_LocationGSEntity) data;
			this.getLocation(this.curEditItem.getLonEndLocation(),
					this.curEditItem.getLatEndLocation(),
					StringUtil.getString(R.string.constr_line_name),
					this.curEditItem.getSpecialName());
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

			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(itemConstrData);
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
			saveCompleteDay(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_SPECIAL, outOfKey);
			showCompleteDate(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_SPECIAL,
					supervision_bg_special_complete_date,
					rl_supervision_bg_special_bt_complete);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	private void saveDataSpecial() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			/* Bat dau luu thong tin */
			for (Special_LocationGSEntity curItemData : listSupervisionSpecialGS) {

				/* 1.Thong tin be co chinh sua */
				if (curItemData.isEdited()) {
					if (curItemData.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
						curItemData.setSync_Status(Constants.SYNC_STATUS.EDIT);
					}
					this.supervisionLBGSpecialController
							.updateColumn(curItemData);
					/* 2. Cap nhat nguyen nhan loi */
					List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemData
							.getListCauseUniQualify();

					List<Supervision_LBG_UnqualifyItemEntity> listAddAccept = curItemData
							.getListAcceptance();

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddAccept) {
						/* 1. Chinh sua nghiem thu */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {

							// xoa nghiem thu khi chuyen
							// trang thai tu khong dat sang dat
							// if (curItemData.getStatus() ==
							// Constants.SUPERVISION_STATUS.CHUADAT) {
							// this.causeLineBGPSpecialController
							// .deleteAccept(addItemCause);
							// }

							if (addItemCause.getLstNewAttachFile().size() > 0
									|| (addItemCause.getLstNewAttachFile()
											.size() == 0 && addItemCause
											.getLstAttachFile().size() == 0)) {

								List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
										.getLstAttachFile(
												Acceptance_Line_Bg_SpecialField.TABLE_NAME,
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
													this.itemConstrData,
													itemFile.getFile_Path(),
													addItemCause
															.getCause_Line_Bg_Id(),
													Acceptance_Line_Bg_SpecialField.TABLE_NAME);

								}
							}

						}
						/* 2. Them moi nghiem thu */
						else {
							if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
								Acceptance_Line_Bg_SpecialEntity addCauseItem = new Acceptance_Line_Bg_SpecialEntity();
								addCauseItem
										.setCat_Cause_Constr_Acceptance_Id(addItemCause
												.getCat_Cause_Constr_Acceptance_Id());
								addCauseItem.setSpecial_Location_Id(curItemData
										.getIdSpecial());
								addCauseItem
										.setSync_Status(Constants.SYNC_STATUS.ADD);
								addCauseItem
										.setIsActive(Constants.ISACTIVE.ACTIVE);
								addCauseItem.setProcessId(0);
								addCauseItem.setEmployeeId(idUser);

								long iAddCause = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Acceptance_Line_Bg_SpecialField.TABLE_NAME);

								if (iAddCause == 0) {
									outOfKey = true;
									return;
								} else
									outOfKey = false;

								addCauseItem
										.setAcceptance_Line_Bg_Special_Id(iAddCause);
								this.causeLineBGPSpecialController
										.addItem(addCauseItem);
								// Luu anh nguyen nhan loi neu co
								for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
										.getLstNewAttachFile()) {
									if (!StringUtil.isNullOrEmpty(itemFile
											.getFile_Path())) {
										this.supvConstrAttachFileController
												.coppyAndAddAttachFile(
														this.itemConstrData,
														itemFile.getFile_Path(),
														iAddCause,
														Acceptance_Line_Bg_SpecialField.TABLE_NAME);

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
								this.causeLineBGPSpecialController
										.delete(addItemCause);
							}
							/* 1.2. Update lai nguyen nhan khong dat */
							else {
								// xoa nguyen nhan khong dat khi chuyen
								// trang thai tu khong dat sang dat
								// if (curItemData.getStatus() ==
								// Constants.SUPERVISION_STATUS.DAT) {
								// this.causeLineBGPSpecialController
								// .delete(addItemCause);
								// }

								if (addItemCause.getLstNewAttachFile().size() > 0
										|| (addItemCause.getLstNewAttachFile()
												.size() == 0 && addItemCause
												.getLstAttachFile().size() == 0)) {

									List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
											.getLstAttachFile(
													Cause_Line_BG_SpecialField.TABLE_NAME,
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
														this.itemConstrData,
														itemFile.getFile_Path(),
														addItemCause
																.getCause_Line_Bg_Id(),
														Cause_Line_BG_SpecialField.TABLE_NAME);

									}
								}
							}
						}
						/* 2. Them moi nguyen nhan khong dat */
						else {
							if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
								Cause_Line_BG_SpecialEntity addCauseItem = new Cause_Line_BG_SpecialEntity();
								addCauseItem
										.setCat_Cause_Constr_Unqualify_Id(addItemCause
												.getCat_Cause_Constr_Unqualify_Id());
								addCauseItem.setSpecial_Location_Id(curItemData
										.getIdSpecial());
								addCauseItem
										.setSync_Status(Constants.SYNC_STATUS.ADD);
								addCauseItem
										.setIsActive(Constants.ISACTIVE.ACTIVE);
								addCauseItem.setProcessId(0);
								addCauseItem.setEmployeeId(idUser);

								long iAddCause = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Cause_Line_BG_SpecialField.TABLE_NAME);

								if (iAddCause == 0) {
									outOfKey = true;
									return;
								} else
									outOfKey = false;

								addCauseItem
										.setCause_Line_Bg_Special_Id(iAddCause);
								this.causeLineBGPSpecialController
										.addItem(addCauseItem);
								// Luu anh nguyen nhan loi neu co
								for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
										.getLstNewAttachFile()) {
									if (!StringUtil.isNullOrEmpty(itemFile
											.getFile_Path())) {
										this.supvConstrAttachFileController
												.coppyAndAddAttachFile(
														this.itemConstrData,
														itemFile.getFile_Path(),
														iAddCause,
														Cause_Line_BG_SpecialField.TABLE_NAME);

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
			constr_Controller.updateSyncStatus(itemConstrData
					.getSupervision_Constr_Id());

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			bundleData = this.getIntent().getExtras();
			boolean bDeny = checkCauseDenyBackgroundLine(bundleData);
			Log.i("Check_Deny", String.valueOf(bDeny));
			if (bDeny) {
				constr_Controller.updateStatus(
						itemConstrData.getSupervision_Constr_Id(), 0);
			} else {
				constr_Controller.updateStatus(
						itemConstrData.getSupervision_Constr_Id(), 1);
			}

		} catch (Exception e) {
			Log.e("error", e.toString());
		}
	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listSpecialAcceptanceItem) {
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listSpecialAcceptanceItem) {
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listSpecialUnqualifyItem) {
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listSpecialUnqualifyItem) {
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

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			saveDataSpecial();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_Line_BG_SpecialActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_Line_BG_SpecialActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineBGSpclView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_SpecialActivity.this);
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
			// refreshListView();
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
