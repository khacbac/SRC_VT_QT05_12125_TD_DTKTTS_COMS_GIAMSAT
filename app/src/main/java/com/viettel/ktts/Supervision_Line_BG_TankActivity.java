package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_TankEntity;
import com.viettel.database.entity.Cause_Line_BG_TankEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Line_Bg_TankField;
import com.viettel.database.field.Cause_Line_BG_TankField;
import com.viettel.database.field.Supervision_Line_BG_TankField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
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
public class Supervision_Line_BG_TankActivity extends LineBaseActivity {
	/* controll */
	private View spvLineBG_TankView;
	private ListView lv_constr_line_tank_list;
	private TextView tv_constr_line_tank_dropdown;
	private TextView tv_constr_line_tank_info_line_station_code_value;
	private TextView tv_constr_line_tank_info_line_value;
	private EditText edt_constr_line_tank_search_value;
	private ImageView iv_constr_line_tank_search;
	private Button btn_constr_line_tank_save;
	private RelativeLayout rl_supervision_line_bg_tank;
	/* bien dropdown */
	private int iDesignInfo = 0;
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
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
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

	private Bundle bundleData;

	private TextView supervision_bg_tank_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_tank_activity);
		setTitle(getString(R.string.line_background_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvLineBG_TankView = addView(R.layout.supervision_line_bg_tank_activity, R.id.rl_supervision_line_bg_tank);
		this.lv_constr_line_tank_list = (ListView) spvLineBG_TankView
				.findViewById(R.id.lv_constr_line_tank_list);
		this.tv_constr_line_tank_dropdown = (TextView) spvLineBG_TankView
				.findViewById(R.id.tv_constr_line_tank_dropdown);
		this.tv_constr_line_tank_dropdown.setOnClickListener(this);

		this.tv_constr_line_tank_info_line_value = (TextView) spvLineBG_TankView
				.findViewById(R.id.tv_constr_line_tank_info_line_value);

		this.tv_constr_line_tank_info_line_station_code_value = (TextView) spvLineBG_TankView
				.findViewById(R.id.tv_constr_line_tank_info_line_station_code_value);
		this.btn_constr_line_tank_save = (Button) spvLineBG_TankView
				.findViewById(R.id.btn_constr_line_tank_save);
		this.btn_constr_line_tank_save.setOnClickListener(this);

		this.edt_constr_line_tank_search_value = (EditText) spvLineBG_TankView
				.findViewById(R.id.edt_constr_line_tank_search_value);

		this.iv_constr_line_tank_search = (ImageView) spvLineBG_TankView
				.findViewById(R.id.iv_constr_line_tank_search);
		this.iv_constr_line_tank_search.setOnClickListener(this);

		rl_supervision_line_bg_tank = (RelativeLayout) spvLineBG_TankView
				.findViewById(R.id.rl_supervision_line_bg_tank);

		this.supervision_bg_tank_complete_date = (TextView) spvLineBG_TankView
				.findViewById(R.id.supervision_bg_tank_complete_date);

		this.rl_supervision_bg_tank_bt_complete = (Button) spvLineBG_TankView
				.findViewById(R.id.rl_supervision_bg_tank_bt_complete);
		this.rl_supervision_bg_tank_bt_complete.setOnClickListener(this);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		// final int heightScreen = displaymetrics.heightPixels;

//		this.rl_supervision_line_bg_tank.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_supervision_line_bg_tank
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_supervision_line_bg_tank
//										.getRootView().getHeight();
//								int heightDifference = screenHeight
//										- (r.bottom - r.top);
//								int resourceId = getResources()
//										.getIdentifier("status_bar_height",
//												"dimen", "android");
//								if (resourceId > 0) {
//									heightDifference -= getResources()
//											.getDimensionPixelSize(resourceId);
//								}
//
//								if (heightDifference > 0) {
//									if (positionTouch >= heightDifference) {
//										rl_supervision_line_bg_tank
//												.setScrollY(0);
//									} else {
//										if ((positionTouch + heightDifference) > screenHeight) {
//											rl_supervision_line_bg_tank.setScrollY(heightDifference
//													- (screenHeight - (Math
//															.round(positionTouch) + heightDifference)));
//										} else
//											rl_supervision_line_bg_tank
//													.setScrollY(heightDifference - 25);
//									}
//								} else {
//									rl_supervision_line_bg_tank.setScrollY(0);
//								}
//
//							}
//						});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_supervision_bg_tank_bt_complete:
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		case R.id.tv_constr_line_tank_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_constr_line_tank_save:
			String messageError = "";
			for (Supervision_Line_BG_TankGSEntity curItemData : listSupervisionGS) {
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
		case R.id.iv_constr_line_tank_search:
			String sFilter = this.edt_constr_line_tank_search_value.getText()
					.toString();
			this.supervisionAdapter.getFilter().filter(sFilter);
			break;
		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineBG_TankView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_TankActivity.this);
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
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoLineBackgroupActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
			this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Supervision_Line_BG_TankGSEntity) data;
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
		super.onResume();
	}

	private void setData() {
		bundleData = this.getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA_EX);
		this.iDesignInfo = bundleData.getInt(IntentConstants.INTENT_DESIGNINFO);
		this.tv_constr_line_tank_dropdown.setText(GloablUtils
				.getStringLineBackgroundInfo(iDesignInfo));

		supervisionLBGController = new Supervision_Line_BG_TankController(this);
		causeLineBGTankController = new Cause_Line_BG_TankController(this);
		supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
				this);

		this.tv_constr_line_tank_info_line_value.setText(this.itemData
				.getConstrCode());
		this.tv_constr_line_tank_info_line_station_code_value.setText(itemData
				.getStationCode());
		/* Nguyen nhan khong dat du be */
		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(Constants.UNQUALIFY_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(Constants.ACCEPTANCE_TYPE.BG_TANK,
						Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);
		/* Drop down */
		this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");
		this.refreshListView();

		showCompleteDate(itemData, Constants.PROGRESS_TYPE.LINE_BACKGROUND,
				Constants.PROGRESS_TYPE.BG_TANK,
				supervision_bg_tank_complete_date,
				rl_supervision_bg_tank_bt_complete);

		/* Set endable va disable voi cong trinh da hoan thanh */
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			// this.rl_supervision_bg_tank_bt_complete.setVisibility(View.GONE);
			this.btn_constr_line_tank_save.setVisibility(View.GONE);
		}
	}

	private void refreshListView() {
		listSupervisionGS = supervisionLBGController
				.getAllTankGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		/* Truong hop Load luu du lieu */
		if (listSupervisionGS.size() == 0) {
			long iNumberTank = 0;
			if (this.supervision_Line_BackgroundData.getTank_Old_Total() != -1) {
				iNumberTank = this.supervision_Line_BackgroundData
						.getTank_New_Total()
						+ this.supervision_Line_BackgroundData
								.getTank_Old_Total();
			} else
				iNumberTank = this.supervision_Line_BackgroundData
						.getTank_New_Total();
			for (int i = 0; i < iNumberTank; i++) {
				Supervision_Line_BG_TankGSEntity curItem = new Supervision_Line_BG_TankGSEntity();
				curItem.setIdTank(i + 1);
				curItem.setTankName(GloablUtils.getTankName(i + 1));
				curItem.setNew(true);
				curItem.setEdited(false);
				listSupervisionGS.add(curItem);
			}
		} else {
			/* Gan danh sach loi cho danh sach be */
			for (Supervision_Line_BG_TankGSEntity curItemSupervisonGS : listSupervisionGS) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeLineBGTankController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getIdTank());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeLineBGTankController
						.getAllTanAcceptByTankId(curItemSupervisonGS
								.getIdTank());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Line_Bg_TankField.TABLE_NAME,
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
									Cause_Line_BG_TankField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListCauseUniQualify(listUnqualifyItem);
			}

			long iNumberTank = 0;
			if (this.supervision_Line_BackgroundData.getTank_Old_Total() != -1) {
				iNumberTank = this.supervision_Line_BackgroundData
						.getTank_New_Total()
						+ this.supervision_Line_BackgroundData
								.getTank_Old_Total();
			} else
				iNumberTank = this.supervision_Line_BackgroundData
						.getTank_New_Total();

			if (listSupervisionGS.size() < iNumberTank) {
				int sizeTank = listSupervisionGS.size();

				for (int i = 0; i < (iNumberTank - sizeTank); i++) {
					Supervision_Line_BG_TankGSEntity itemTank = new Supervision_Line_BG_TankGSEntity();
					itemTank.setTankName(GloablUtils.getTankName(sizeTank + i
							+ 1));
					listSupervisionGS.add(itemTank);
				}
			}
		}
		this.supervisionAdapter = new Supervision_Line_BGAdapter(this,
				listSupervisionGS);

		this.lv_constr_line_tank_list.setAdapter(supervisionAdapter);
		this.lv_constr_line_tank_list.setItemsCanFocus(true);

	}

	private void saveDataTank() {
		/* Tinh toan gia tri */
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			/* Bat dau luu thong tin */
			for (Supervision_Line_BG_TankGSEntity curItemData : listSupervisionGS) {
				/* Them moi */
				if (curItemData.isNew()) {
					Supervision_Line_BG_TankEntity addItem = new Supervision_Line_BG_TankEntity();
					addItem.setTank_Name(curItemData.getTankName());
					addItem.setNum_Part(curItemData.getNumberPart());
					addItem.setFail_Desc(curItemData.getFailDesc());
					addItem.setLatLocation(curItemData.getLatLocation());
					addItem.setLonLocation(curItemData.getLonLocation());
					addItem.setStatus(curItemData.getStatus());
					addItem.setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
					addItem.setSync_Status(Constants.SYNC_STATUS.ADD);
					addItem.setIsActive(Constants.ISACTIVE.ACTIVE);
					addItem.setProcessId(0);
					addItem.setEmployeeId(idUser);

					long idAddTank = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Line_BG_TankField.TABLE_NAME);

					if (idAddTank == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					addItem.setSupervision_Line_Bg_Tank_Id(idAddTank);
					addItem.setSupervisionConstrId(supervision_Line_BackgroundData
							.getSupervisionConstrId());
					supervisionLBGController.addItem(addItem);
					/* Add nguyen nhan khong dat */
					if (addItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
						List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
							Cause_Line_BG_TankEntity addCauseItem = new Cause_Line_BG_TankEntity();
							addCauseItem
									.setCat_Cause_Constr_Unqualify_Id(curItemUnqualify
											.getCat_Cause_Constr_Unqualify_Id());
							addCauseItem
									.setSupervision_Line_Bg_Tank_Id(idAddTank);
							addCauseItem
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
							addCauseItem.setProcessId(0);
							addCauseItem.setEmployeeId(idUser);
							addCauseItem
									.setSupervisionConstrId(supervision_Line_BackgroundData
											.getSupervision_Constr_Id());

							long iAddCause = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Line_BG_TankField.TABLE_NAME);
							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem.setCause_Line_Bg_Tank_Id(iAddCause);
							this.causeLineBGTankController
									.addItem(addCauseItem);

							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.itemData,
													itemFile.getFile_Path(),
													iAddCause,
													Cause_Line_BG_TankField.TABLE_NAME);

								}
							}
						}
					} else {
						// neu dat thi save anh nghiem thu
						List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
								.getListAcceptance();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
							Acceptance_Line_Bg_TankEntity addCauseItem = new Acceptance_Line_Bg_TankEntity();
							addCauseItem
									.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
											.getCat_Cause_Constr_Acceptance_Id());
							addCauseItem
									.setSupervision_Line_Bg_Tank_Id(idAddTank);
							addCauseItem
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
							addCauseItem.setProcessId(0);
							addCauseItem.setEmployeeId(idUser);

							long iAddCause = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Acceptance_Line_Bg_TankField.TABLE_NAME);
							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem
									.setAcceptance_Line_Bg_Tank_Id(iAddCause);
							this.causeLineBGTankController
									.addItem(addCauseItem);

							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.itemData,
													itemFile.getFile_Path(),
													iAddCause,
													Acceptance_Line_Bg_TankField.TABLE_NAME);

								}
							}
						}
					}
					/* Cap nhat chinh sua */
				} else {
					/* 1.Thong tin be co chinh sua */
					if (curItemData.isEdited()) {
						if (curItemData.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
							curItemData
									.setSync_Status(Constants.SYNC_STATUS.EDIT);
						}
						this.supervisionLBGController
								.updateAllColumn(curItemData);
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
//								if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
//									this.causeLineBGTankController
//											.deleteAccept(addItemCause);
//								}

								if (addItemCause.getLstNewAttachFile().size() > 0
										|| (addItemCause.getLstNewAttachFile()
												.size() == 0 && addItemCause
												.getLstAttachFile().size() == 0)) {
									List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
											.getLstAttachFile(
													Acceptance_Line_Bg_TankField.TABLE_NAME,
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
														this.itemData,
														itemFile.getFile_Path(),
														addItemCause
																.getCause_Line_Bg_Id(),
														Acceptance_Line_Bg_TankField.TABLE_NAME);

									}
								}

							}
							/* 2. Them moi nghiem thu */
							else {
								if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
									Acceptance_Line_Bg_TankEntity addCauseItem = new Acceptance_Line_Bg_TankEntity();
									addCauseItem
											.setCat_Cause_Constr_Acceptance_Id(addItemCause
													.getCat_Cause_Constr_Acceptance_Id());
									addCauseItem
											.setSupervision_Line_Bg_Tank_Id(curItemData
													.getIdTank());
									addCauseItem
											.setSync_Status(Constants.SYNC_STATUS.ADD);
									addCauseItem
											.setIsActive(Constants.ISACTIVE.ACTIVE);
									addCauseItem.setProcessId(0);
									addCauseItem.setEmployeeId(idUser);

									long iAddCause = Ktts_KeyController
											.getInstance()
											.getKttsNextKey(
													Acceptance_Line_Bg_TankField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setAcceptance_Line_Bg_Tank_Id(iAddCause);
									this.causeLineBGTankController
											.addItem(addCauseItem);
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
															Acceptance_Line_Bg_TankField.TABLE_NAME);

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
									this.causeLineBGTankController
											.delete(addItemCause);
								}
								/* 1.2. Update lai nguyen nhan khong dat */
								else {
									// xoa nguyen nhan khong dat khi chuyen
									// trang thai tu khong dat sang dat
									// if (curItemData.getStatus() ==
									// Constants.SUPERVISION_STATUS.DAT) {
									// this.causeLineBGTankController
									// .delete(addItemCause);
									// }

									if (addItemCause.getLstNewAttachFile()
											.size() > 0
											|| (addItemCause
													.getLstNewAttachFile()
													.size() == 0 && addItemCause
													.getLstAttachFile().size() == 0)) {
										// danh sach anh da co
										List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
												.getLstAttachFile(
														Cause_Line_BG_TankField.TABLE_NAME,
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
															this.itemData,
															itemFile.getFile_Path(),
															addItemCause
																	.getCause_Line_Bg_Id(),
															Cause_Line_BG_TankField.TABLE_NAME);

										}
									}
								}
							}
							/* 2. Them moi nguyen nhan khong dat */
							else {
								if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
									Cause_Line_BG_TankEntity addCauseItem = new Cause_Line_BG_TankEntity();
									addCauseItem
											.setCat_Cause_Constr_Unqualify_Id(addItemCause
													.getCat_Cause_Constr_Unqualify_Id());
									addCauseItem
											.setSupervision_Line_Bg_Tank_Id(curItemData
													.getIdTank());
									addCauseItem
											.setSync_Status(Constants.SYNC_STATUS.ADD);
									addCauseItem
											.setIsActive(Constants.ISACTIVE.ACTIVE);
									addCauseItem.setProcessId(0);
									addCauseItem.setEmployeeId(idUser);
									addCauseItem
											.setSupervisionConstrId(supervision_Line_BackgroundData
													.getSupervision_Constr_Id());

									long iAddCause = Ktts_KeyController
											.getInstance()
											.getKttsNextKey(
													Cause_Line_BG_TankField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setCause_Line_Bg_Tank_Id(iAddCause);
									this.causeLineBGTankController
											.addItem(addCauseItem);
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
															Cause_Line_BG_TankField.TABLE_NAME);

										}
									}
								}

							}
						}
					}
				}
			}
			//
			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(itemData
					.getSupervision_Constr_Id());

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			bundleData = this.getIntent().getExtras();
			boolean bDeny = checkCauseDenyBackgroundLine(bundleData);
			Log.i("Check_Deny", String.valueOf(bDeny));
			if (bDeny) {
				constr_Controller.updateStatus(
						itemData.getSupervision_Constr_Id(), 0);
			} else {
				constr_Controller.updateStatus(
						itemData.getSupervision_Constr_Id(), 1);
			}

		} catch (Exception e) {
			Log.e("error", e.toString());
		}
	}

	private String checkVailid(Supervision_Line_BG_TankGSEntity itemCheck) {
		String sReslut = "";
		try {
			int countNnkdCheck = 0;

			for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
				if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
					countNnkdCheck++;
					break;
				}
			}
			if (itemCheck.getNumberPart() > 0
					|| (itemCheck.getStatus() >= 0)
					|| (itemCheck.getStatus() == Constants.TANK_STATUS.KHONG_DAT && countNnkdCheck < 1)
					|| !itemCheck.getLatLocation().equals(
							Constants.ID_DOUBLE_ENTITY_DEFAULT)
					|| !itemCheck.getLonLocation().equals(
							Constants.ID_DOUBLE_ENTITY_DEFAULT)) {
				if (itemCheck.getNumberPart() < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_line_tank_sodan_empty);
					sReslut += itemCheck.getTankName();
				} else if (itemCheck.getStatus() < 0) {
					sReslut = StringUtil
							.getString(R.string.constr_line_tank_danhgia_empty);
					sReslut += itemCheck.getTankName();
				} else if (itemCheck.getStatus() == Constants.TANK_STATUS.KHONG_DAT
						&& countNnkdCheck < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_line_tank_nn_khongdat_tempty);
					sReslut += itemCheck.getTankName();
				} else if (itemCheck.getLatLocation() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
					sReslut = StringUtil
							.getString(R.string.constr_line_tank_location);
					sReslut += itemCheck.getTankName();
				} else if (itemCheck.getLonLocation() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
					sReslut = StringUtil
							.getString(R.string.constr_line_tank_location);
					sReslut += itemCheck.getTankName();
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
			refreshListView();
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
			saveDataTank();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_Line_BG_TankActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_Line_BG_TankActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}