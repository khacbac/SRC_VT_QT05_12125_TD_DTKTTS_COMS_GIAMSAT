package com.viettel.ktts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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
import com.viettel.database.Cause_Brcd_Kcn_Controller;
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Tn_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
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
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_BRCD_KCN_Adapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_KeoCapNhanhActivity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_KCNView;
	private ListView lv_constr_brcd_kcnhanh_list;
	private TextView tv_constr_brcd_keocapnhanh_main_dropdown;
	private TextView tv_constr_brcd_keocapnhanh_main_station_code_value;
	private TextView tv_constr_brcd_keocapnhanh_main_info_line_value;
	private ScrollView scroll_brcd_kcn;
	private Button rl_supervision_keocapnhanh_main_save;
	private int iField = 0;
	
	private Bundle bundleData;
	
	public static boolean isMonitoring = true; // Xu ly event EVENT_CONFIRM_OK xac nhan luu thong tin giam sat hay luu trang thai moi lan giam sat
	
	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH;
	private int sosoicap = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_Line_BG_TankController supervisionLBGController;
	private Cause_Line_BG_TankController causeLineBGTankController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_BRCD_Kcn_Entity curEditItem = new Supervision_BRCD_Kcn_Entity();
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;

	private List<Supervision_BRCD_Kcn_Entity> listSupervisionKCN;
	private Supervision_BRCD_KCN_Adapter supervisionKcnAdapter;

	private Cause_Brcd_Kcn_Controller causeBrcdKcnController = new Cause_Brcd_Kcn_Controller(
			this);
	private Supervision_BRCD_Kcn_Entity brcd_kcn = new Supervision_BRCD_Kcn_Entity();
	private Supervision_BRCD_Controller brcd_Controller = new Supervision_BRCD_Controller(
			this);

	private Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();

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

	private boolean outOfKey = false;

	private long supervition_brcd_id;
	private float positionTouch = 0f;

	private TextView supervision_bg_tank_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_keocapnhanh_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_KCNView = addView(R.layout.supervision_brcd_keocapnhanh_activity, R.id.rl_supervision_line_bg_tank);
		this.scroll_brcd_kcn = (ScrollView) spvBRCD_KCNView.findViewById(R.id.scroll_brcd_kcn);
		this.tv_constr_brcd_keocapnhanh_main_dropdown = (TextView) spvBRCD_KCNView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_main_dropdown);
		this.tv_constr_brcd_keocapnhanh_main_dropdown.setOnClickListener(this);
		this.rl_supervision_keocapnhanh_main_save = (Button) spvBRCD_KCNView
				.findViewById(R.id.rl_supervision_keocapnhanh_main_save);
		this.rl_supervision_keocapnhanh_main_save.setOnClickListener(this);
		this.tv_constr_brcd_keocapnhanh_main_station_code_value = (TextView) spvBRCD_KCNView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_main_station_code_value);

		this.tv_constr_brcd_keocapnhanh_main_info_line_value = (TextView) spvBRCD_KCNView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_main_info_line_value);

		this.lv_constr_brcd_kcnhanh_list = (ListView) spvBRCD_KCNView.findViewById(R.id.lv_constr_brcd_kcnhanh_list);

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

		case R.id.tv_constr_brcd_keocapnhanh_main_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.rl_supervision_keocapnhanh_main_save:

				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
				isMonitoring = false;
			
			break;

		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_KCNView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_KeoCapNhanhActivity.this);
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
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);
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
		/* Chon nguyen nhan khong dat */

		// case Constants.BG_PIPE_EDIT.FAIL_DESC:
		// String sFailDescText = this.curEditItem.getDesc();
		// editTextPopup = new Edit_Text_Popup(this, null, sFailDescText,
		// InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
		// editTextPopup.showAtCenter();
		// break;

		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_CHI_TIET:

			this.curEditItem = (Supervision_BRCD_Kcn_Entity) data;
			if (curEditItem.getCable_Code().equals("")) {
				this.showDialog("Bạn chưa nhập mã nhánh");

			} else {

				saveDataTank();
				Intent it = new Intent(
						Supervision_BRCD_KeoCapNhanhActivity.this,
						Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.class);
				bundleData = new Bundle();
				bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
						supervition_brcd_id);

				bundleData.putSerializable(IntentConstants.INTENT_DATA,
						itemData);
				bundleData.putSerializable(IntentConstants.INTENT_BRCD_KCN,
						curEditItem);
				it.putExtras(bundleData);
				startActivity(it);

			}
			break;
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_GIAM_SAT:
			this.curEditItem = (Supervision_BRCD_Kcn_Entity) data;

			if (curEditItem.getCable_Code().equals("")) {
				this.showDialog("Bạn chưa nhập mã nhánh");

			} else {

				saveDataTank();
				Intent it_gs = new Intent(
						Supervision_BRCD_KeoCapNhanhActivity.this,
						Supervision_BRCD_GiamSat_Kcn_Activity.class);
				Bundle bundleData_gs = new Bundle();
				bundleData_gs.putLong(IntentConstants.INTENT_BRCD_ID,
						supervition_brcd_id);
				bundleData_gs.putSerializable(IntentConstants.INTENT_BRCD_KCN,
						curEditItem);
				bundleData_gs.putSerializable(IntentConstants.INTENT_DATA,
						itemData);
				it_gs.putExtras(bundleData_gs);
				startActivity(it_gs);

			}
			break;
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_DELETE:
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.showDialog("Công trình này đã hoàn thành nên không thể xóa");
			} else {
				this.curEditItem = (Supervision_BRCD_Kcn_Entity) data;
				AlertDialog dialogAskOption = new AlertDialog.Builder(this)
						.setTitle(R.string.text_delete_title)
						.setMessage(R.string.line_bg_pipe_delete_message)
						.setIcon(R.drawable.ic_launcher)

						.setPositiveButton(
								StringUtil
										.getString(R.string.text_delete_button),
								new DialogInterface.OnClickListener() {

									public void onClick(DialogInterface dialog,
											int whichButton) {
										deleteKcn(curEditItem);
										dialog.dismiss();
									}

								})

						.setNegativeButton(
								StringUtil
										.getString(R.string.text_close_button),
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										dialog.dismiss();

									}
								}).create();
				dialogAskOption.show();
			}
			break;

		/* Dong anh nghiem thu */

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
				
			}
			else {
				new SaveDataGSAsyncTask().execute();
				break;
			}
			
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

	protected void deleteKcn(Supervision_BRCD_Kcn_Entity curEditItem_kcn) {
		supervisionKCNController.deleteKcnEntity(curEditItem_kcn);
		brcd_Entity = brcd_Controller
				.getSupervisionBRCD_brcd(supervition_brcd_id);
		sosoicap = brcd_Entity.getBRANCH_NUM_CABLE();
		boolean update = brcd_Controller.updateCable(supervition_brcd_id,
				(sosoicap - 1));

		brcd_kcn_design = brcd_kcn_design_Controller
				.getSupervisionBRCD_Kcn_design(curEditItem_kcn
						.getSupervition_branch_cable_id());

		listSupervisionDROP = new ArrayList<Supervision_BRCD_Drop_Entity>();
		listSupervisionTTB = new ArrayList<Supervision_BRCD_Ttb_Entity>();
		if (brcd_kcn_design != null) {
			listSupervisionDROP = supervisionDROPController
					.getAllbrcd_drop_design_full_type(brcd_kcn_design
							.getSupervition_branch_design_id());
			for (int j = 0; j < listSupervisionDROP.size(); j++) {
				supervisionDROPController.deleteDropEntity(listSupervisionDROP
						.get(j));
			}

			listSupervisionTTB = supervisionTTBController
					.getAllbrcd_ttb_full_type(brcd_kcn_design
							.getSupervition_branch_design_id());
			for (int j = 0; j < listSupervisionTTB.size(); j++) {
				supervisionTTBController.deleteTtbEntity(listSupervisionTTB
						.get(j));
			}

		}
		refreshListView();

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
		bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.tv_constr_brcd_keocapnhanh_main_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		this.tv_constr_brcd_keocapnhanh_main_station_code_value
				.setText(itemData.getStationCode());
		this.tv_constr_brcd_keocapnhanh_main_info_line_value.setText(itemData
				.getConstrCode());
		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");

		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(Constants.UNQUALIFY_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(Constants.ACCEPTANCE_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

		this.supervition_brcd_id = bundleData.getLong(IntentConstants.INTENT_BRCD_ID);
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.rl_supervision_keocapnhanh_main_save.setVisibility(View.GONE);
		}
		this.refreshListView();

	}

	private void refreshListView() {
		brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
				.getSupervision_Constr_Id());
		listSupervisionKCN = new ArrayList<Supervision_BRCD_Kcn_Entity>();

		listSupervisionKCN = supervisionKCNController
				.getAllbrcd_kcn(supervition_brcd_id);

		this.supervisionKcnAdapter = new Supervision_BRCD_KCN_Adapter(this,
				listSupervisionKCN);
		this.lv_constr_brcd_kcnhanh_list.setAdapter(supervisionKcnAdapter);
		lv_constr_brcd_kcnhanh_list
				.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						scroll_brcd_kcn
								.requestDisallowInterceptTouchEvent(true);
						int action = event.getActionMasked();
						switch (action) {
						case MotionEvent.ACTION_UP:
							scroll_brcd_kcn
									.requestDisallowInterceptTouchEvent(false);
							break;
						}
						return false;
					}
				});

	}

	private void saveDataTank() {

		for (Supervision_BRCD_Kcn_Entity curItemData : listSupervisionKCN) {

			long idUser = GlobalInfo.getInstance().getUserId();
			Supervision_BRCD_Kcn_Entity brcd_kcn = new Supervision_BRCD_Kcn_Entity();

			brcd_kcn.setCable_Name(curItemData.getCable_Name());
			brcd_kcn.setCable_Code(curItemData.getCable_Code());
			brcd_kcn.setSUPERVISION_BRCD_ID(supervition_brcd_id);
			brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn.setProcessId(0);
			brcd_kcn.setEmployeeId(idUser);

			brcd_kcn.setSupervition_branch_cable_id(curItemData
					.getSupervition_branch_cable_id());
			brcd_kcn_Controller.updateSupervisionBRCD_Kcn(
					brcd_kcn.getSupervition_branch_cable_id(), brcd_kcn);

		}
		
	}
	
	private void saveDataGS() {

		for (Supervision_BRCD_Kcn_Entity curItemData : listSupervisionKCN) {

			long idUser = GlobalInfo.getInstance().getUserId();
			Supervision_BRCD_Kcn_Entity brcd_kcn = new Supervision_BRCD_Kcn_Entity();

			brcd_kcn.setCable_Name(curItemData.getCable_Name());
			brcd_kcn.setCable_Code(curItemData.getCable_Code());
			brcd_kcn.setSUPERVISION_BRCD_ID(supervition_brcd_id);
			brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn.setProcessId(0);
			brcd_kcn.setEmployeeId(idUser);

			brcd_kcn.setSupervition_branch_cable_id(curItemData
					.getSupervition_branch_cable_id());
			brcd_kcn_Controller.updateSupervisionBRCD_Kcn(
					brcd_kcn.getSupervition_branch_cable_id(), brcd_kcn);

		}
		
		// TODO: Thiet lap ket luan. DanhDue ExOICTIF
		Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
				this);
		bundleData = this.getIntent().getExtras();
		boolean bDeny = checkBRCDCauseDeny(bundleData);
		Log.i("Check_Deny", String.valueOf(bDeny));
		if (bDeny) {
			constr_Controller.updateStatus(
					itemData.getSupervision_Constr_Id(), 0);
		} else {
			constr_Controller.updateStatus(
					itemData.getSupervision_Constr_Id(), 1);
		}
		
	}

//	private String checkVailid(
//			List<Supervision_BRCD_Kcn_Entity> listSupervisionkeocapnhanh) {
//		String sReslut = "";
//		try {
//			for (int i = 0; i < listSupervisionkeocapnhanh.size(); i++) {
//				if (listSupervisionkeocapnhanh.get(i).getEnd_Point() < listSupervisionkeocapnhanh
//						.get(i).getStart_Point()) {
//					sReslut = "Tại "
//							+ listSupervisionkeocapnhanh.get(i).getCable_Name()
//							+ StringUtil
//									.getString(R.string.brcd_diemcuoinhohondau);
//				} else if (i > 0
//						&& listSupervisionkeocapnhanh.get(i).getStart_Point() != -1
//						&& listSupervisionkeocapnhanh.get(i).getEnd_Point() != -1) {
//
//					if (listSupervisionkeocapnhanh.get(i).getStart_Point() < listSupervisionkeocapnhanh
//							.get(i - 1).getEnd_Point()) {
//
//						sReslut = listSupervisionkeocapnhanh.get(i)
//								.getCable_Name()
//								+ " có "
//								+ StringUtil
//										.getString(R.string.brcd_diemdaunhohoncuoi)
//								+ listSupervisionkeocapnhanh.get(i - 1)
//										.getCable_Name();
//					} else if (listSupervisionkeocapnhanh.get(i - 1)
//							.getStart_Point() == -1
//							&& listSupervisionkeocapnhanh.get(i - 1)
//									.getEnd_Point() == -1) {
//						sReslut = StringUtil
//								.getString(R.string.brcd_nhapthieudulieu_tainhanh)
//								+ listSupervisionkeocapnhanh.get(i - 1)
//										.getCable_Name();
//					}
//				}
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return sReslut;
//	}

	/* Ghi nghiem thu vao danh sach ong */

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
				Toast.makeText(Supervision_BRCD_KeoCapNhanhActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			refreshListView();
			Toast.makeText(Supervision_BRCD_KeoCapNhanhActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
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
				Toast.makeText(Supervision_BRCD_KeoCapNhanhActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			refreshListView();
			Toast.makeText(Supervision_BRCD_KeoCapNhanhActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}