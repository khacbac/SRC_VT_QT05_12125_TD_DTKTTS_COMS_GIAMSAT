package com.viettel.ktts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.viettel.database.Cause_Brcd_Kct_Controller;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kct_design_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_design_Entity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Brcd_Kct_Field;
import com.viettel.database.field.Cause_Brcd_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_design_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_BRCD_KCT_Adapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_KeoCTActivity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_KEOCTView;
	private ListView lv_constr_brcd_kct_list;
	private TextView tv_constr_brcd_kct_dropdown;
	private TextView tv_constr_brcd_kct_station_code_value;
	private TextView tv_constr_brcd_kct_stationcode;
	private EditText supervision_textview_chieudai_kct;
	private ScrollView scroll_brcd_kct;
	private Button rl_supervision_brcd_save;
	private int iField = 0;
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC;
	private List<DropdownItemEntity> listDesignInfo = null;

	private boolean isMonitoring = true; // Xu ly event EVENT_CONFIRM_OK xac
											// nhan luu thong tin giam sat hay
											// luu trang thai moi lan giam sat

	// private Bundle bundleData;

	// private List<DropdownItemEntity> listDesignLoaiCap = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;

	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Supervision_BRCD_Controller brcd_Controller = new Supervision_BRCD_Controller(
			this);
	private Constr_Construction_EmployeeEntity itemData;
	private long supervition_brcd_id;
	private Supervision_BRCD_Kct_design_Entity brcd_kct_design = new Supervision_BRCD_Kct_design_Entity();

	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_BRCD_Kct_Controller supervisionKCTController = new Supervision_BRCD_Kct_Controller(
			this);

	private Supervision_BRCD_Kct_design_Controller brcd_kct_design_Controller = new Supervision_BRCD_Kct_design_Controller(
			this);
	private Supervision_BRCD_Kct_Controller brcd_kct_Controller = new Supervision_BRCD_Kct_Controller(
			this);
	private Cause_Brcd_Kct_Controller causeBrcdKctController = new Cause_Brcd_Kct_Controller(
			this);
	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
			this);
	/* Danh sach nguyen nhan khong dat cua be ad chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Danh sach keo cap truc cua bang rong co dinh */
	private List<Supervision_BRCD_Kct_Entity> listSupervisionKCT;
	private Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();
	/* Item be cap dang sua dung popup */
	private Supervision_BRCD_Kct_Entity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Supervision_BRCD_KCT_Adapter supervisionKctAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;
	private int sosoicap = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_kct_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));

		// test
		// JSONArray jsonScDataTest = SqlliteSyncModel
		// .getDataJsonSyncTest(Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
		// Cat_Cause_Constr_AcceptanceController.allColumn, null, 0,
		// 1000);
	
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_KEOCTView = addView(R.layout.supervision_brcd_kct_activity, R.id.rl_supervision_line_bg_tank);
		this.lv_constr_brcd_kct_list = (ListView) spvBRCD_KEOCTView
				.findViewById(R.id.lv_constr_brcd_kct_list);
		this.tv_constr_brcd_kct_dropdown = (TextView) spvBRCD_KEOCTView
				.findViewById(R.id.tv_constr_brcd_kct_dropdown);
		this.tv_constr_brcd_kct_dropdown.setOnClickListener(this);

		this.tv_constr_brcd_kct_station_code_value = (TextView) spvBRCD_KEOCTView
				.findViewById(R.id.tv_constr_brcd_kct_station_code_value);

		this.tv_constr_brcd_kct_stationcode = (TextView) spvBRCD_KEOCTView
				.findViewById(R.id.tv_constr_brcd_kct_stationcode);
		this.tv_constr_brcd_kct_stationcode.setOnClickListener(this);

		this.supervision_textview_chieudai_kct = (EditText) spvBRCD_KEOCTView
				.findViewById(R.id.supervision_textview_chieudai_kct);

		this.rl_supervision_brcd_save = (Button) spvBRCD_KEOCTView
				.findViewById(R.id.rl_supervision_brcd_save);
		this.scroll_brcd_kct = (ScrollView) spvBRCD_KEOCTView.findViewById(R.id.scroll_brcd_kct);

		this.rl_supervision_brcd_save.setOnClickListener(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.tv_constr_brcd_kct_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;

		case R.id.rl_supervision_brcd_save:
			String messageError = "";
			String messageError_point = "";

			messageError = this.checkVailid_Length();
			messageError_point = this.checkVailid_Poin(listSupervisionKCT);
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);

			} else if (!StringUtil.isNullOrEmpty(messageError_point)) {
				this.showDialog(messageError_point);
			} else {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			
			isMonitoring = false;
			
			break;
		default:
			break;
		}
	}

	private String checkVailid_Length() {
		String sReslut = "";
		try {

			if (supervision_textview_chieudai_kct.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_chieu_dai_cap_truc);
				supervision_textview_chieudai_kct.requestFocus();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sReslut;
	}

	private String checkVailid_Poin(
			List<Supervision_BRCD_Kct_Entity> listSupervisionkeocaptruc) {
		String sReslut = "";
		int lenght = 0;
		if (!supervision_textview_chieudai_kct.getText().toString().equals("")) {
			lenght = Integer.parseInt(supervision_textview_chieudai_kct
					.getText().toString());
		}

		int lenght_sum = 0;
		try {

			if (listSupervisionkeocaptruc.size() > 0) {
				for (int i = 0; i < listSupervisionkeocaptruc.size(); i++) {
					if (listSupervisionkeocaptruc.get(i).getLength() > 0) {
						lenght_sum += listSupervisionkeocaptruc.get(i)
								.getLength();
					}
				}
				if (lenght_sum > lenght) {

					sReslut = "Chiều dài đoạn cáp đã vượt quá chiều dài tổng là "
							+ (lenght_sum - lenght) + "m";
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_KEOCTView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_KeoCTActivity.this);
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
					showProgressDialog(StringUtil.getString(R.string.text_loading));
					Bundle bundleData = new Bundle();
					bundleData.putSerializable(IntentConstants.INTENT_DATA,
							itemData);
					bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
							supervition_brcd_id);

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
		case Constants.BG_PIPE_EDIT.FAIL_DESC:
			String sFailDescTextValue = this.curEditItem.getFAIL_DESC();
			editTextPopup = new Edit_Text_Popup(this, null, sFailDescTextValue,
					InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
			editTextPopup.showAtCenter();
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
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionKctAdapter.notifyDataSetChanged();
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			this.supervisionKctAdapter.notifyDataSetChanged();
			break;
		// chup anh nghiem thu

		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_GIAM_SAT:
			this.curEditItem = (Supervision_BRCD_Kct_Entity) data;

			if (curEditItem.getCable_code().equals("")) {
				this.showDialog("Bạn chưa nhập mã cáp trục cho nhánh này");

			} else if (curEditItem.getLength() == -1) {
				this.showDialog("Bạn chưa nhập chiều dài cho nhánh này");

			} else {
				String messageError = this.checkVailid_Length();
				String messageError_point = this
						.checkVailid_Poin(listSupervisionKCT);
				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);

				} else if (!StringUtil.isNullOrEmpty(messageError_point)) {
					this.showDialog(messageError_point);

				} else {
					saveDataTank();
					this.curEditItem = (Supervision_BRCD_Kct_Entity) data;
					Intent it_gs = new Intent(
							Supervision_BRCD_KeoCTActivity.this,
							Supervision_BRCD_GiamSat_KeoCT_Activity.class);
					Bundle bundleData_gs = new Bundle();
					bundleData_gs.putSerializable(
							IntentConstants.INTENT_BRCD_TRUC, curEditItem);
					bundleData_gs.putSerializable(IntentConstants.INTENT_DATA,
							itemData);
					bundleData_gs
							.putLong(IntentConstants.INTENT_BRCD_ID,
									supervition_brcd_id);
					it_gs.putExtras(bundleData_gs);
					startActivity(it_gs);
				}
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

		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT:
			this.curEditItem = (Supervision_BRCD_Kct_Entity) data;
			this.iField = this.curEditItem.getIdField();
			switch (iField) {

			case Constants.BG_PIPE_EDIT.FAIL_DESC:
				String sFailDescText = this.curEditItem.getFAIL_DESC();
				editTextPopup = new Edit_Text_Popup(this, null, sFailDescText,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.BG_PIPE_EDIT.DELETE:
				if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
						|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
					this.showDialog("Công trình này đã hoàn thành nên không thể xóa");
				} else {
					AlertDialog dialogAskOption = new AlertDialog.Builder(this)
							.setTitle(R.string.text_delete_title)
							.setMessage(R.string.line_bg_pipe_delete_message)
							.setIcon(R.drawable.ic_launcher)

							.setPositiveButton(
									StringUtil
											.getString(R.string.text_delete_button),
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											deleteKct(curEditItem);
											dialog.dismiss();
										}

									})

							.setNegativeButton(
									StringUtil
											.getString(R.string.text_close_button),
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();

										}
									}).create();
					dialogAskOption.show();
				}
				break;
			default:
				break;
			}
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;

			this.curEditItem.setFAIL_DESC(sSetTextValue.toString());

			this.supervisionKctAdapter.notifyDataSetChanged();
			editTextPopup.hidePopup();
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

			if (isMonitoring == false) {
				new SaveAsyncTask().execute();
				break;

			} else {
				new SaveDataGSAsyncTask().execute();
				break;
			}

		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	protected void deleteKct(Supervision_BRCD_Kct_Entity curEditItem_Kct) {
		// new SaveAsyncTask().execute();

		int sosoicap = 0;

		brcd_Entity = brcd_Controller
				.getSupervisionBRCD_brcd(supervition_brcd_id);

		switch (curEditItem_Kct.getCable_type()) {
		case 24:
			sosoicap = brcd_Entity.getTRUNK_NUM_CABLE_HT();
			boolean update_HT = brcd_Controller.updateTrunkCable_HT(
					supervition_brcd_id, (sosoicap - 1));
			break;
		case 48:
			sosoicap = brcd_Entity.getTRUNK_NUM_CABLE_BT();
			boolean update_BT = brcd_Controller.updateTrunkCable_BT(
					supervition_brcd_id, (sosoicap - 1));
			break;
		case 96:
			sosoicap = brcd_Entity.getTRUNK_NUM_CABLE_CS();
			boolean update_CS = brcd_Controller.updateTrunkCable_CS(
					supervition_brcd_id, (sosoicap - 1));
			break;

		default:
			break;
		}

		supervisionKCTController.deleteKctEntity(curEditItem_Kct);
		// supervisionKctAdapter.notifyDataSetChanged();
		refreshListView();

		lv_constr_brcd_kct_list.setSelection(curEditItem_Kct.getPosition());

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

		// JSONArray jsonScDataTest = SqlliteSyncModel
		// .getDataJsonSyncTest(
		// Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
		// Cat_Cause_Constr_AcceptanceController.allColumn,
		// null, 0, 1000);
		Bundle bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.tv_constr_brcd_kct_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		this.tv_constr_brcd_kct_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_kct_stationcode.setText(itemData.getConstrCode());

		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.KEO_CAP_TRUC,
						Constants.UNQUALIFY_TYPE.BRCD);
		Log.d(listTankUnqualifyItem.toString(), "loi");
		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.KEO_CAP_TRUC,
						Constants.UNQUALIFY_TYPE.BRCD);

		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		// this.listDesignLoaiCap = GloablUtils.getListbrcdLoaicaptruc("");
		this.supervition_brcd_id = bundleData
				.getLong(IntentConstants.INTENT_BRCD_ID);

		brcd_kct_design = brcd_kct_design_Controller
				.getSupervisionBRCD_kct_design(supervition_brcd_id);
		if (brcd_kct_design != null) {
			supervision_textview_chieudai_kct.setText(String
					.valueOf(brcd_kct_design.getTrunk_cable_length()));
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.ADD);

		} else {
			brcd_kct_design = new Supervision_BRCD_Kct_design_Entity();
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.EDIT);

		}
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.rl_supervision_brcd_save.setVisibility(View.GONE);
		}
		this.refreshListView();
	}

	private void refreshListView() {
		// lay danh sach keo cap truc

		listSupervisionKCT = new ArrayList<Supervision_BRCD_Kct_Entity>();
		Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();
		brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
				.getSupervision_Constr_Id());

		listSupervisionKCT = supervisionKCTController
				.getAllbrcd_kct(supervition_brcd_id);

		this.supervisionKctAdapter = new Supervision_BRCD_KCT_Adapter(this,
				listSupervisionKCT);
		this.lv_constr_brcd_kct_list.setAdapter(supervisionKctAdapter);
		// this.lv_constr_brcd_kct_list.setItemsCanFocus(true);
		lv_constr_brcd_kct_list.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll_brcd_kct.requestDisallowInterceptTouchEvent(true);
				int action = event.getActionMasked();
				switch (action) {
				case MotionEvent.ACTION_UP:
					scroll_brcd_kct.requestDisallowInterceptTouchEvent(false);
					break;
				}
				return false;
			}
		});
		if (listSupervisionKCT.size() != 0) {

			for (Supervision_BRCD_Kct_Entity curItemSupervisonGS : listSupervisionKCT) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeBrcdKctController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getSUPERVISION_TRUNK_CABLE_ID());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeBrcdKctController
						.getAllTanAcceptByTankId(curItemSupervisonGS
								.getSUPERVISION_TRUNK_CABLE_ID());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Brcd_Kct_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListAcceptance(listAcceptItem);
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Brcd_Kct_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListCauseUniQualify(listUnqualifyItem);
			}

		}

	}

	private void saveDataTank() {
		getSupvBRCD_Kct();
		// brcd_kct_Controller.trunCate_kct();
		long idUser = GlobalInfo.getInstance().getUserId();
		if (brcd_kct_design.getSync_Status() == Constants.SYNC_STATUS.EDIT) {
			long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
					Supervision_BRCD_Kct_design_Field.TABLE_NAME);
			if (idSupervion == 0) {
				Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct_design.setProcessId(0);
			brcd_kct_design.setEmployeeId(idUser);
			brcd_kct_design.setSupervition_brcd_id(supervition_brcd_id);
			brcd_kct_design.setSUPERVISION_TRUNK_DESIGN_ID(idSupervion);
			brcd_kct_design_Controller.addItem(brcd_kct_design);
			//
			// Toast toast = Toast
			// .makeText(
			// this,
			// getResources().getString(
			// R.string.insert_database_complete),
			// Toast.LENGTH_LONG);
			//
			// toast.setGravity(Gravity.CENTER, 0, 0);
			// toast.show();
		}

		else if (brcd_kct_design.getSync_Status() == Constants.SYNC_STATUS.ADD) {
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct_design.setProcessId(0);
			brcd_kct_design.setEmployeeId(idUser);
			brcd_kct_design_Controller.updateSupervisionBRCD_kct_design(
					brcd_kct_design.getSUPERVISION_TRUNK_DESIGN_ID(),
					brcd_kct_design);

			// Toast.makeText(
			// getApplicationContext(),
			// getResources().getString(R.string.update_database_complete),
			// Toast.LENGTH_LONG).show();

			// toast.setGravity(Gravity.CENTER, 0, 0);

		}

		for (Supervision_BRCD_Kct_Entity curItemData : listSupervisionKCT) {
			Supervision_BRCD_Kct_Entity brcd_kct = new Supervision_BRCD_Kct_Entity();

			brcd_kct.setCable_code(curItemData.getCable_code());
			brcd_kct.setCable_type(curItemData.getCable_type());
			brcd_kct.setLength(curItemData.getLength());
			brcd_kct.setFAIL_DESC(curItemData.getFAIL_DESC());
			brcd_kct.setSupervition_brcd_id(supervition_brcd_id);
			brcd_kct.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct.setProcessId(0);
			brcd_kct.setEmployeeId(idUser);
			brcd_kct.setSUPERVISION_TRUNK_CABLE_ID(curItemData
					.getSUPERVISION_TRUNK_CABLE_ID());
			brcd_kct_Controller.updateSupervisionBRCD_kct(
					brcd_kct.getSUPERVISION_TRUNK_CABLE_ID(), brcd_kct);

		}

	}
	
	private void saveDataGS() {
		getSupvBRCD_Kct();
		// brcd_kct_Controller.trunCate_kct();
		long idUser = GlobalInfo.getInstance().getUserId();
		if (brcd_kct_design.getSync_Status() == Constants.SYNC_STATUS.EDIT) {
			long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
					Supervision_BRCD_Kct_design_Field.TABLE_NAME);
			if (idSupervion == 0) {
				Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct_design.setProcessId(0);
			brcd_kct_design.setEmployeeId(idUser);
			brcd_kct_design.setSupervition_brcd_id(supervition_brcd_id);
			brcd_kct_design.setSUPERVISION_TRUNK_DESIGN_ID(idSupervion);
			brcd_kct_design_Controller.addItem(brcd_kct_design);
			//
			// Toast toast = Toast
			// .makeText(
			// this,
			// getResources().getString(
			// R.string.insert_database_complete),
			// Toast.LENGTH_LONG);
			//
			// toast.setGravity(Gravity.CENTER, 0, 0);
			// toast.show();
		}

		else if (brcd_kct_design.getSync_Status() == Constants.SYNC_STATUS.ADD) {
			brcd_kct_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct_design.setProcessId(0);
			brcd_kct_design.setEmployeeId(idUser);
			brcd_kct_design_Controller.updateSupervisionBRCD_kct_design(
					brcd_kct_design.getSUPERVISION_TRUNK_DESIGN_ID(),
					brcd_kct_design);

			// Toast.makeText(
			// getApplicationContext(),
			// getResources().getString(R.string.update_database_complete),
			// Toast.LENGTH_LONG).show();

			// toast.setGravity(Gravity.CENTER, 0, 0);

		}

		for (Supervision_BRCD_Kct_Entity curItemData : listSupervisionKCT) {
			Supervision_BRCD_Kct_Entity brcd_kct = new Supervision_BRCD_Kct_Entity();

			brcd_kct.setCable_code(curItemData.getCable_code());
			brcd_kct.setCable_type(curItemData.getCable_type());
			brcd_kct.setLength(curItemData.getLength());
			brcd_kct.setFAIL_DESC(curItemData.getFAIL_DESC());
			brcd_kct.setSupervition_brcd_id(supervition_brcd_id);
			brcd_kct.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct.setProcessId(0);
			brcd_kct.setEmployeeId(idUser);
			brcd_kct.setSUPERVISION_TRUNK_CABLE_ID(curItemData
					.getSUPERVISION_TRUNK_CABLE_ID());
			brcd_kct_Controller.updateSupervisionBRCD_kct(
					brcd_kct.getSUPERVISION_TRUNK_CABLE_ID(), brcd_kct);

		}

		// TODO: Thiet lap ket luan. DanhDue ExOICTIF
		Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
				this);
		Bundle bundleData = this.getIntent().getExtras();
		boolean bDeny = checkBRCDCauseDeny(bundleData);
		Log.i("Check_Deny", String.valueOf(bDeny));
		if (bDeny) {
			constr_Controller.updateStatus(itemData.getSupervision_Constr_Id(),
					0);
		} else {
			constr_Controller.updateStatus(itemData.getSupervision_Constr_Id(),
					1);
		}

	}

	private void getSupvBRCD_Kct() {
		if (brcd_kct_design == null) {
			brcd_kct_design = new Supervision_BRCD_Kct_design_Entity();
		} else {

			brcd_kct_design.setTrunk_cable_length(Integer
					.parseInt(supervision_textview_chieudai_kct.getText()
							.toString()));

		}
	}

	/* Ghi nghiem thu vao danh sach ong */

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

	// private void requestSync() {
	// if (this.check3GWifi()) {
	// showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
	// Bundle bundle = new Bundle();
	// ActionEvent e = new ActionEvent();
	// e.action = ActionEventConstant.REQEST_SYNC;
	// e.viewData = bundle;
	// e.isBlockRequest = true;
	// e.sender = this;
	// Home_Controller.getInstance().handleViewEvent(e);
	// } else {
	// this.show3GWifiOffline();
	// }
	// }

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:
			SyncTask.closeDialog();
			refreshListView();
			// closeProgressDialog();
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
				Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

	class SaveDataGSAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			saveDataGS();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_BRCD_KeoCTActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}