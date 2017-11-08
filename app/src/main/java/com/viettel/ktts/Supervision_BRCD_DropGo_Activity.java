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
import com.viettel.database.Cause_Brcd_Drop_Controller;
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
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
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Brcd_Drop_Field;
import com.viettel.database.field.Cause_Brcd_Drop_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_BRCD_DROP_Adapter;
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
public class Supervision_BRCD_DropGo_Activity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_DROPGOView;
	private TextView tv_constr_brcd_dropgo_dropdown;
	private TextView tv_constr_brcd_dropgo_station_code_value;
	private TextView tv_constr_brcd_dropgo_info_line_value;
	private ScrollView scroll_brcd_drop;
	private ListView lv_constr_brcd_drop_list;
	private Button rl_supervision_dropgo_save, rl_supervision_dropgo_ht;

	private Bundle bundleData;
	
	public static boolean isMonitoring = true; // Xu ly event EVENT_CONFIRM_OK xac nhan luu thong tin giam sat hay luu trang thai moi lan giam sat

	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.DROP_GO;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private List<Supervision_BRCD_Drop_Entity> listSupervisionGS;
	private Supervision_Line_BG_TankController supervisionLBGController;
	private Cause_Line_BG_TankController causeLineBGTankController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_BRCD_Drop_Entity curEditItem = new Supervision_BRCD_Drop_Entity();
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = new Supervision_LBG_UnqualifyItemEntity();
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = new Supervision_LBG_UnqualifyItemEntity();
	private Supervision_Line_BGAdapter supervisionAdapter;
	private Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();
	private List<Supervision_BRCD_Kcn_Entity> listSupervisionKcn;
	private Supervision_BRCD_Kcn_Entity kcn_Entity = new Supervision_BRCD_Kcn_Entity();
	private Supervision_BRCD_Controller brcd_Controller = new Supervision_BRCD_Controller(
			this);

	private Supervision_BRCD_Kcn_Design_Entity kcn_design_Entity = new Supervision_BRCD_Kcn_Design_Entity();
	private Supervision_BRCD_Kcn_Design_Controller brcd_kcn_design_Controller = new Supervision_BRCD_Kcn_Design_Controller(
			this);
	private Supervision_BRCD_Kcn_Controller brcd_kcn_Controller = new Supervision_BRCD_Kcn_Controller(
			this);

	private Supervision_BRCD_Drop_Controller supervisionDROPController = new Supervision_BRCD_Drop_Controller(
			this);
	private List<Supervision_BRCD_Drop_Entity> listSupervisionDROP;
	private Supervision_BRCD_DROP_Adapter supervisionDropAdapter;
	private Cause_Brcd_Drop_Controller causeBrcdDropController = new Cause_Brcd_Drop_Controller(
			this);

	private boolean outOfKey = false;
	private float positionTouch = 0f;
	private long supervition_brcd_id;
	private int iField = 0;
	private TextView supervision_brcd_drop_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_dropgo_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_DROPGOView = addView(R.layout.supervision_brcd_dropgo_activity, R.id.rl_supervision_line_bg_tank);
		this.scroll_brcd_drop = (ScrollView) spvBRCD_DROPGOView.findViewById(R.id.scroll_brcd_drop);
		this.tv_constr_brcd_dropgo_dropdown = (TextView) spvBRCD_DROPGOView
				.findViewById(R.id.tv_constr_brcd_dropgo_dropdown);
		this.tv_constr_brcd_dropgo_dropdown.setOnClickListener(this);

		this.tv_constr_brcd_dropgo_station_code_value = (TextView) spvBRCD_DROPGOView
				.findViewById(R.id.tv_constr_brcd_dropgo_station_code_value);
		this.supervision_brcd_drop_complete_date = (TextView) spvBRCD_DROPGOView
				.findViewById(R.id.supervision_brcd_dropandgo_complete_date);
		this.tv_constr_brcd_dropgo_info_line_value = (TextView) spvBRCD_DROPGOView
				.findViewById(R.id.tv_constr_brcd_dropgo_info_line_value);
		this.rl_supervision_dropgo_save = (Button) spvBRCD_DROPGOView
				.findViewById(R.id.rl_supervision_dropandgo_save);
		this.rl_supervision_dropgo_save.setOnClickListener(this);
		this.rl_supervision_dropgo_ht = (Button) spvBRCD_DROPGOView
				.findViewById(R.id.rl_supervision_dropgo_ht);
		this.rl_supervision_dropgo_ht.setOnClickListener(this);
		this.lv_constr_brcd_drop_list = (ListView) spvBRCD_DROPGOView.findViewById(R.id.lv_constr_brcd_dropgo_list);

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

		case R.id.rl_supervision_dropgo_ht:
			ConfirmDialog confirmPerfect = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmPerfect.show();
			break;
		case R.id.tv_constr_brcd_dropgo_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.rl_supervision_dropandgo_save:
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save));
			confirmSave.show();
			
			isMonitoring = false;

			break;
		// }
		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_DROPGOView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_DropGo_Activity.this);
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
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_CHITIET:
			this.curEditItem = (Supervision_BRCD_Drop_Entity) data;

			if (curEditItem.getDrop_code().equals("")) {
				this.showDialog("Bạn chưa nhập mã Drop & Go cho nhánh này");

			} else {

				saveDataTank();
				this.curEditItem = (Supervision_BRCD_Drop_Entity) data;
				Intent it = new Intent(Supervision_BRCD_DropGo_Activity.this,
						Supervision_BRCD_DropGo_ChiTiet_Activity.class);
				Bundle bundleData = new Bundle();

				bundleData.putSerializable(IntentConstants.INTENT_DATA,
						itemData);

				bundleData.putSerializable(IntentConstants.INTENT_BRCD_DROP,
						curEditItem);
				it.putExtras(bundleData);
				startActivity(it);

			}
			break;
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_GIAM_SAT:
			this.curEditItem = (Supervision_BRCD_Drop_Entity) data;

			if (curEditItem.getDrop_code().equals("")) {
				this.showDialog("Bạn chưa nhập mã Drop & Go cho nhánh này");

			} else {

				saveDataTank();
				this.curEditItem = (Supervision_BRCD_Drop_Entity) data;
				Intent it_gs = new Intent(
						Supervision_BRCD_DropGo_Activity.this,
						Supervision_BRCD_GiamSat_DropGo_Activity.class);
				Bundle bundleData_gs = new Bundle();
				bundleData_gs.putSerializable(IntentConstants.INTENT_BRCD_DROP,
						curEditItem);
				bundleData_gs.putSerializable(IntentConstants.INTENT_DATA,
						itemData);
				bundleData_gs.putLong(IntentConstants.INTENT_BRCD_ID, supervition_brcd_id);
				it_gs.putExtras(bundleData_gs);
				startActivity(it_gs);

			}
			break;
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_DELETE:
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.showDialog("Công trình này đã hoàn thành nên không thể xóa");
			} else {
				this.curEditItem = (Supervision_BRCD_Drop_Entity) data;
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
										deleteDropGo(curEditItem);
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
		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				if (this.iDesignInfo != dropdownItem.getId()) {
					showProgressDialog(StringUtil.getString(R.string.text_loading));
					Bundle bundleData_drop = new Bundle();
					bundleData_drop.putSerializable(
							IntentConstants.INTENT_DATA, itemData);
					bundleData_drop.putSerializable(
							IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);
					bundleData_drop.putLong(IntentConstants.INTENT_BRCD_ID,
							supervition_brcd_id);
					bundleData_drop.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBrcdBackgroupActivity(bundleData_drop);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		/* Chon nguyen nhan khong dat */

		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
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
			saveCompleteDay(itemData, Constants.PROGRESS_TYPE.BRCD_TYPE,
					Constants.PROGRESS_TYPE.BRCD_DROPGO, outOfKey);
			showCompleteDate(itemData, Constants.PROGRESS_TYPE.BRCD_TYPE,
					Constants.PROGRESS_TYPE.BRCD_DROPGO,
					supervision_brcd_drop_complete_date,
					rl_supervision_dropgo_ht);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	protected void deleteDropGo(Supervision_BRCD_Drop_Entity curEditItem_drop) {

		int sosoicap = 0;
		kcn_design_Entity = brcd_kcn_design_Controller
				.getSupervisionBRCD_Kcn_design_id(curEditItem_drop
						.getSupervition_branch_design_id());

		switch (curEditItem_drop.getCab_type()) {
		case 2:
			sosoicap = kcn_design_Entity.getCable_Drop_Num_Two();
			boolean updateTwo = brcd_kcn_design_Controller.updateDropNumTwo(
					kcn_design_Entity.getSupervition_branch_design_id(),
					(sosoicap - 1));
			break;
		case 4:
			sosoicap = kcn_design_Entity.getCable_Drop_Num_Four();
			boolean updateFour = brcd_kcn_design_Controller.updateDropNumFour(
					kcn_design_Entity.getSupervition_branch_design_id(),
					(sosoicap - 1));
			break;
		case 8:
			sosoicap = kcn_design_Entity.getCable_Drop_Num_Eight();
			boolean updateEight = brcd_kcn_design_Controller
					.updateDropNumEight(
							kcn_design_Entity.getSupervition_branch_design_id(),
							(sosoicap - 1));
			break;
		case 12:
			sosoicap = kcn_design_Entity.getCable_Drop_Num_Twelve();
			boolean updateTwelve = brcd_kcn_design_Controller
					.updateDropNumTwelve(
							kcn_design_Entity.getSupervition_branch_design_id(),
							(sosoicap - 1));
			break;

		default:
			break;
		}

		supervisionDROPController.deleteDropEntity(curEditItem_drop);

		refreshListView();
		lv_constr_brcd_drop_list.setSelection(curEditItem_drop.getPosition());

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
//				String picturePath = StringUtil.getPath(this, selectedImage);
////				String[] filePathColumn = { MediaStore.Images.Media.DATA };
////				Cursor cursor = getContentResolver().query(selectedImage,
////						filePathColumn, null, null, null);
////				cursor.moveToFirst();
////				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////				String picturePath = cursor.getString(columnIndex);
////				cursor.close();
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
		super.onResume();
	}

	private void setData() {
		bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.tv_constr_brcd_dropgo_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		this.tv_constr_brcd_dropgo_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_dropgo_info_line_value.setText(itemData
				.getConstrCode());
		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(Constants.UNQUALIFY_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(Constants.ACCEPTANCE_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

		this.supervition_brcd_id = bundleData
				.getLong(IntentConstants.INTENT_BRCD_ID);
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.rl_supervision_dropgo_save.setVisibility(View.GONE);
		}
		this.refreshListView();

	}

	private void refreshListView() {
		brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
				.getSupervision_Constr_Id());
		listSupervisionDROP = new ArrayList<Supervision_BRCD_Drop_Entity>();
		listSupervisionDROP = supervisionDROPController
				.getAllbrcd_drop(supervition_brcd_id);
		this.supervisionDropAdapter = new Supervision_BRCD_DROP_Adapter(this,
				listSupervisionDROP);
		this.lv_constr_brcd_drop_list.setAdapter(supervisionDropAdapter);

		lv_constr_brcd_drop_list.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll_brcd_drop.requestDisallowInterceptTouchEvent(true);
				int action = event.getActionMasked();
				switch (action) {
				case MotionEvent.ACTION_UP:
					scroll_brcd_drop.requestDisallowInterceptTouchEvent(false);
					break;
				}
				return false;
			}
		});
		if (listSupervisionDROP.size() != 0) {
			for (Supervision_BRCD_Drop_Entity curItemSupervisonGS : listSupervisionDROP) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeBrcdDropController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getSupervition_branch_drop_id());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeBrcdDropController
						.getAllTanAcceptByTankId(curItemSupervisonGS
								.getSupervition_branch_drop_id());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Brcd_Drop_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListAcceptance(listAcceptItem);
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Brcd_Drop_Field.TABLE_NAME,
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
		long idUser = GlobalInfo.getInstance().getUserId();
		for (Supervision_BRCD_Drop_Entity curItemData : listSupervisionDROP) {
			Supervision_BRCD_Drop_Entity brcd_kcn = new Supervision_BRCD_Drop_Entity();
			brcd_kcn.setCab_type(curItemData.getCab_type());
			brcd_kcn.setDrop_code(curItemData.getDrop_code());
			brcd_kcn.setName(curItemData.getName());
			brcd_kcn.setCab_type(curItemData.getCab_type());
			brcd_kcn.setSUPERVISION_BRCD_ID(supervition_brcd_id);
			brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn.setProcessId(0);
			brcd_kcn.setEmployeeId(idUser);
			brcd_kcn.setSupervition_branch_drop_id(curItemData
					.getSupervition_branch_drop_id());
			supervisionDROPController.updateSupervisionBRCD_Drop(
					brcd_kcn.getSupervition_branch_drop_id(), brcd_kcn);

		}

	}
	
	private void saveDataGS() {
		long idUser = GlobalInfo.getInstance().getUserId();
		for (Supervision_BRCD_Drop_Entity curItemData : listSupervisionDROP) {
			Supervision_BRCD_Drop_Entity brcd_kcn = new Supervision_BRCD_Drop_Entity();
			brcd_kcn.setCab_type(curItemData.getCab_type());
			brcd_kcn.setDrop_code(curItemData.getDrop_code());
			brcd_kcn.setName(curItemData.getName());
			brcd_kcn.setCab_type(curItemData.getCab_type());
			brcd_kcn.setSUPERVISION_BRCD_ID(supervition_brcd_id);
			brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn.setProcessId(0);
			brcd_kcn.setEmployeeId(idUser);
			brcd_kcn.setSupervition_branch_drop_id(curItemData
					.getSupervition_branch_drop_id());
			supervisionDROPController.updateSupervisionBRCD_Drop(
					brcd_kcn.getSupervition_branch_drop_id(), brcd_kcn);

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

	}

	// private String checkVailid(Supervision_BRCD_Drop_Entity itemCheck) {
	// String sReslut = "";
	// try {
	// if (itemCheck.getStart_Point() > 0) {
	//
	// if (itemCheck.getEnd_Point() == -1) {
	//
	// sReslut = StringUtil
	// .getString(R.string.brcd_chon_thieu_diemcuoi);
	// }
	// } else if (itemCheck.getEnd_Point() > 0) {
	//
	// if (itemCheck.getStart_Point() == -1) {
	//
	// sReslut = StringUtil
	// .getString(R.string.brcd_chon_thieu_diemdau);
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return sReslut;
	// }

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
				Toast.makeText(Supervision_BRCD_DropGo_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			refreshListView();
			Toast.makeText(Supervision_BRCD_DropGo_Activity.this,
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
				Toast.makeText(Supervision_BRCD_DropGo_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			refreshListView();
			Toast.makeText(Supervision_BRCD_DropGo_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}