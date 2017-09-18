package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.Cause_Line_BG_CableController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_CableController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Cause_Line_BG_CableEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_CableEntity;
import com.viettel.database.entity.Supervision_Line_BG_CableGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Cause_Line_BG_CableField;
import com.viettel.database.field.Cause_Line_BG_TankField;
import com.viettel.database.field.Supervision_Line_BG_CableField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_BG_CableAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 * 
 */
public class Supervision_Line_BG_CableActivity extends LineBaseActivity {
	/* controll */
	private View spvLineBG_CableView;
	private ListView lv_line_bg_cable_list;
	private TextView tv_line_bg_cable_dropdown;
	private TextView tv_line_bg_cable_info_line_station_code_value;
	private TextView tv_line_bg_cable_info_line_value;
	private Button btn_line_bg_cable_save;
	private Button btn_line_bg_cable_add;
	private RelativeLayout rl_supervision_line_bg_cable;
	/* Bien set Type Text */
	private int iField = 0;
	/* bien dropdown */
	private int iDesignInfo = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemConstrData;
	/* Tuyen ngam giam sat */
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	/* Danh sach ong giam sat */
	private List<Supervision_Line_BG_CableGSEntity> listSupervisionCableGS;
	private List<Supervision_Line_BG_CableGSEntity> listSupervisionCableDeleteGS;
	private Supervision_Line_BG_CableController supervisionLBGCableController;
	private Cause_Line_BG_CableController causeLineBGPCableController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listCableUnqualifyItem;
	/* Item ong cap dang sua dung popup */
	private Supervision_Line_BG_CableGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_Line_BG_CableAdapter supervisionCableAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;

	private Button rl_supervision_bg_cable_bt_complete;
	private TextView supervision_bg_cable_complete_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_cable_activity);
		setTitle(getString(R.string.line_background_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();

	}

	private void initView() {
		spvLineBG_CableView = addView(R.layout.supervision_line_bg_cable_activity, R.id.rl_supervision_line_bg_cable);
		this.lv_line_bg_cable_list = (ListView) spvLineBG_CableView
				.findViewById(R.id.lv_line_bg_cable_list);
		this.tv_line_bg_cable_dropdown = (TextView) spvLineBG_CableView
				.findViewById(R.id.tv_line_bg_cable_dropdown);
		this.tv_line_bg_cable_dropdown.setOnClickListener(this);

		this.tv_line_bg_cable_info_line_value = (TextView) spvLineBG_CableView
				.findViewById(R.id.tv_line_bg_cable_info_line_value);

		this.tv_line_bg_cable_info_line_station_code_value = (TextView) spvLineBG_CableView
				.findViewById(R.id.tv_line_bg_cable_info_line_station_code_value);
		this.btn_line_bg_cable_save = (Button) spvLineBG_CableView
				.findViewById(R.id.btn_line_bg_cable_save);
		this.btn_line_bg_cable_save.setOnClickListener(this);
		this.btn_line_bg_cable_add = (Button) spvLineBG_CableView
				.findViewById(R.id.btn_line_bg_cable_add);
		this.btn_line_bg_cable_add.setOnClickListener(this);

		this.supervision_bg_cable_complete_date = (TextView) spvLineBG_CableView
				.findViewById(R.id.supervision_bg_cable_complete_date);

		this.rl_supervision_bg_cable_bt_complete = (Button) spvLineBG_CableView
				.findViewById(R.id.rl_supervision_bg_cable_bt_complete);

		this.rl_supervision_bg_cable_bt_complete.setOnClickListener(this);

		this.rl_supervision_line_bg_cable = (RelativeLayout) spvLineBG_CableView.findViewById(R.id.rl_supervision_line_bg_cable);
//		rl_supervision_line_bg_cable.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_supervision_line_bg_cable
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_supervision_line_bg_cable
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
//										rl_supervision_line_bg_cable
//												.setScrollY(0);
//									} else {
//										if ((positionTouch + heightDifference) > screenHeight) {
//											rl_supervision_line_bg_cable.setScrollY(heightDifference
//													- (screenHeight - (Math
//															.round(positionTouch) + heightDifference)));
//										} else
//											rl_supervision_line_bg_cable
//													.setScrollY(heightDifference - 25);
//									}
//								} else {
//									rl_supervision_line_bg_cable.setScrollY(0);
//								}
//							}
//						});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_supervision_bg_cable_bt_complete:
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		case R.id.btn_line_bg_cable_add:
			Supervision_Line_BG_CableGSEntity addItem = new Supervision_Line_BG_CableGSEntity();
			this.listSupervisionCableGS.add(addItem);
			this.supervisionCableAdapter.notifyDataSetChanged();
			break;
		case R.id.tv_line_bg_cable_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_line_bg_cable_save:
			String messageError = "";
			for (Supervision_Line_BG_CableGSEntity curItemData : listSupervisionCableGS) {
				messageError = this.checkVailid(curItemData);
				if (!StringUtil.isNullOrEmpty(messageError)) {
					break;
				}
			}
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
			} else {
				messageError = checkVailidCableItem();
				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);
				} else {
					confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			}
			break;
		default:
			// this.hideKeyboard(v);
			break;
		}
	}

	private String checkVailidCableItem() {
		String sReslut = "";

		for (int i = 0, size = this.listSupervisionCableGS.size(); i < size; i++) {
			long toDistance = listSupervisionCableGS.get(i).getToDistance();

			if (i == 0 && listSupervisionCableGS.get(i).getFromDistance() != 1) {
				return StringUtil
						.getString(R.string.line_hang_cable_fromdis_begin_equal_1);
			}
			// kiem tra xem den khoang truoc co bang tu khoang sau hay khong
			if ((i) < (size - 1)) {
				long fromDistance = listSupervisionCableGS.get(i + 1)
						.getFromDistance();
				if (checkToDisBeforeEqualFromDisLast(toDistance, fromDistance)) {
					return (StringUtil
							.getString(R.string.line_hang_cable_todisbefore)
							+ " "
							+ (i + 1)
							+ " "
							+ StringUtil
									.getString(R.string.line_hang_cable_not_equal)
							+ " "
							+ StringUtil
									.getString(R.string.line_hang_cable_fromdislast)
							+ " " + (i + 2));
				}
			}

			// neu la phan tu cuoi cung thi kiem tra den khoang co bang tong
			// chieu dai tuyen hay khong
			if (i == (size - 1)
					&& listSupervisionCableGS.get(i).getToDistance() > supervision_Line_BackgroundData
							.getLong_Total()) {
				return StringUtil
						.getString(R.string.line_hang_cable_todislast_eual_longtotal);
			}
		}
		return sReslut;
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineBG_CableView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_CableActivity.this);
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
					Bundle bundleData = new Bundle();
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
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
			this.curEditItem = (Supervision_Line_BG_CableGSEntity) data;
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Supervision_Line_BG_CableGSEntity) data;
			break;
		/* Chon nguyen nhan khong dat */
		case OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT:
			this.curEditItem = (Supervision_Line_BG_CableGSEntity) data;
			this.iField = this.curEditItem.getIdField();
			switch (iField) {

			case Constants.BG_CABLE_EDIT.UNQUALIFY:
				// Gan gia tri nguyen nhan loi
				if (this.curEditItem.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
					Toast.makeText(
							this,
							StringUtil
									.getString(R.string.line_hang_cable_cause_choice),
							Toast.LENGTH_SHORT).show();
				} else {
					this.setUnqualify();
					contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listCableUnqualifyItem);
					contruoctionUnqualifyPopup.showAtCenter();
				}

				break;
			case Constants.BG_CABLE_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem.getFailDesc();
				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.BG_CABLE_EDIT.DELETE:
				this.showAskOptionDialog(StringUtil
						.getString(R.string.text_delete_title), StringUtil
						.getString(R.string.line_bg_pipe_delete_message));
				break;
			default:
				break;
			}
			break;

		/* chon nghi nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			this.curEditItem.setEdited(true);
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionCableAdapter.notifyDataSetChanged();
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;
			switch (this.iField) {
			// case Constants.BG_CABLE_EDIT.FROM_DISTANCE:
			// if (StringUtil.isNullOrEmpty(sSetTextValue)) {
			// this.showDialog(StringUtil
			// .getString(R.string.text_empty_message));
			// return;
			// }
			// this.curEditItem.setFromDistance(Long.parseLong(sSetTextValue));
			// break;
			// case Constants.BG_CABLE_EDIT.TO_DISTANCE:
			// if (StringUtil.isNullOrEmpty(sSetTextValue)) {
			// this.showDialog(StringUtil
			// .getString(R.string.text_empty_message));
			// return;
			// }
			// this.curEditItem.setToDistance(Long.parseLong(sSetTextValue));
			// break;
			case Constants.BG_CABLE_EDIT.FAIL_DESC:
				this.curEditItem.setFailDesc(sSetTextValue);
				break;

			default:
				break;
			}
			this.curEditItem.setEdited(true);
			this.supervisionCableAdapter.notifyDataSetChanged();
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

		case OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO:
			this.curUnqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
			List<ImageEntity> listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */
			// if (StringUtil.isNullOrEmpty(this.curUnqualifyItem
			// .getNewAttachFile().getFile_Path())) {
			// if (this.curUnqualifyItem.getAttachFile() != null
			// && this.curUnqualifyItem.getAttachFile()
			// .getSupv_Constr_Attach_file_Id() > 0
			// && !this.curUnqualifyItem.isDeleteImage()) {
			// ImageEntity addImgView = new ImageEntity();
			// addImgView.setId(1);
			// addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
			// + this.curUnqualifyItem.getAttachFile()
			// .getFile_Path());
			// listImgView.add(addImgView);
			// }
			// } else {
			// ImageEntity addImgView = new ImageEntity();
			// addImgView.setId(1);
			// addImgView.setUrl(this.curUnqualifyItem.getNewAttachFile()
			// .getFile_Path());
			// listImgView.add(addImgView);
			// }
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
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;
			this.curUnqualifyItem.getLstAttachFile().clear();
			this.curUnqualifyItem.getLstNewAttachFile().clear();
			for (ImageEntity imageEntity : lstData) {
				// if(imageEntity.isAttack()){
				// this.curUnqualifyItem.getLstAttachFile().add(imageEntity);
				// } else {
				// if(imageEntity.isNewAttack()){
				Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
				curAttachFile
						.setSupv_Constr_Attach_file_Id(imageEntity.getId());
				curAttachFile.setFile_Path(imageEntity.getUrl());
				this.curUnqualifyItem.getLstNewAttachFile().add(curAttachFile);
				// }
				// }
			}
			this.imgViewPopup.hidePopup();
			this.contruoctionUnqualifyPopup.refreshData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(itemConstrData);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:
			// if (this.curUnqualifyItem.getAttachFile()
			// .getSupv_Constr_Attach_file_Id() > 0) {
			// this.curUnqualifyItem.setDeleteImage(true);
			// } else {
			// this.curUnqualifyItem.getNewAttachFile().setFile_Path("");
			// }
			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			// this.saveDataCable();
			// this.refreshListView();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			saveCompleteDay(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_CABLE, outOfKey);
			showCompleteDate(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_CABLE,
					supervision_bg_cable_complete_date,
					rl_supervision_bg_cable_bt_complete);
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
				// this.curUnqualifyItem.getNewAttachFile().setFile_Path(
				// imgUri.getPath());
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
//				// this.curUnqualifyItem.getNewAttachFile().setFile_Path(
//				// picturePath);
//				this.imgViewPopup.setImageData(addImgView);
//			}
//			break;
		default:
			break;
		}
	}

	private void setData() {
		try {

			Bundle bundleData = this.getIntent().getExtras();
			this.itemConstrData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA_EX);
			this.iDesignInfo = bundleData
					.getInt(IntentConstants.INTENT_DESIGNINFO);
			this.listSupervisionCableDeleteGS = new ArrayList<Supervision_Line_BG_CableGSEntity>();
			this.tv_line_bg_cable_dropdown.setText(GloablUtils
					.getStringLineBackgroundInfo(iDesignInfo));
			supervisionLBGCableController = new Supervision_Line_BG_CableController(
					this);
			causeLineBGPCableController = new Cause_Line_BG_CableController(
					this);
			supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
					this);

			this.tv_line_bg_cable_info_line_value.setText(this.itemConstrData
					.getConstrCode());

			this.tv_line_bg_cable_info_line_station_code_value
					.setText(itemConstrData.getStationCode());
			/* Nguyen nhan khong dat du be */
			listCableUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(
					this).getAllUnQualifyItemByName(
					Constants.UNQUALIFY_TYPE.BG_CABLE,
					Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);
			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");

			this.refreshListView();

			showCompleteDate(itemConstrData,
					Constants.PROGRESS_TYPE.LINE_BACKGROUND,
					Constants.PROGRESS_TYPE.BG_CABLE,
					supervision_bg_cable_complete_date,
					rl_supervision_bg_cable_bt_complete);

			if (itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_bg_cable_save.setVisibility(View.GONE);
				// this.rl_supervision_bg_cable_bt_complete
				// .setVisibility(View.GONE);
				this.btn_line_bg_cable_add.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			this.gotoHomeActivity(new Bundle());
		}
	}

	private void refreshListView() {
		listSupervisionCableGS = supervisionLBGCableController
				.getAllCableGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		/* Truong hop Load luu du lieu */
		if (listSupervisionCableGS.size() > 0) {
			/* Gan danh sach loi cho danh sach ong */
			for (Supervision_Line_BG_CableGSEntity itemCableGS : listSupervisionCableGS) {
				List<Supervision_LBG_UnqualifyItemEntity> listLBGUnqualify = causeLineBGPCableController
						.getAllCableUnqulifyByCableId(itemCableGS.getIdCable());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listLBGUnqualify) {

					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Cause_Line_BG_CableField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				itemCableGS.setListCauseUniQualify(listLBGUnqualify);
			}
		}
		this.supervisionCableAdapter = new Supervision_Line_BG_CableAdapter(
				this, listSupervisionCableGS);
		this.lv_line_bg_cable_list.setAdapter(supervisionCableAdapter);
	}

	private void saveDataCable() {
		/* Tinh toan gia tri */
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			for (Supervision_Line_BG_CableGSEntity curItemAdd : listSupervisionCableGS) {
				if (curItemAdd.isNew() || curItemAdd.isEdited()) {
					long idCable = 0;
					Supervision_Line_BG_CableEntity itemAddCable = new Supervision_Line_BG_CableEntity();
					itemAddCable
							.setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
									.getSupervision_Line_Background_Id());
					itemAddCable.setFromDistance(curItemAdd.getFromDistance());
					itemAddCable.setToDistance(curItemAdd.getToDistance());
					itemAddCable.setFail_Desc(curItemAdd.getFailDesc());
					itemAddCable.setStatus(curItemAdd.getStatus());
					if (curItemAdd.isNew()) {
						itemAddCable.setSync_Status(Constants.SYNC_STATUS.ADD);
						itemAddCable.setIsActive(Constants.ISACTIVE.ACTIVE);
						itemAddCable.setProcessId(0);
						itemAddCable.setEmployeeId(idUser);
						idCable = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Supervision_Line_BG_CableField.TABLE_NAME);

						if (idCable == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						itemAddCable.setSupervision_Line_Bg_Cable_Id(idCable);
						itemAddCable
								.setSupervisionConstrId(supervision_Line_BackgroundData
										.getSupervisionConstrId());
						supervisionLBGCableController.addItem(itemAddCable);
						/* Tao moi nguyen nhan loi */
						List<Supervision_LBG_UnqualifyItemEntity> listCauseUnqualify = curItemAdd
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity curUnqulifyAdd : listCauseUnqualify) {
							Cause_Line_BG_CableEntity causeUnqualifyAdd = new Cause_Line_BG_CableEntity();
							causeUnqualifyAdd
									.setCat_Cause_Constr_Unqualify_Id(curUnqulifyAdd
											.getCat_Cause_Constr_Unqualify_Id());
							causeUnqualifyAdd
									.setSupervision_Line_Bg_Cable_Id(idCable);
							causeUnqualifyAdd
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							causeUnqualifyAdd
									.setIsActive(Constants.ISACTIVE.ACTIVE);
							causeUnqualifyAdd.setProcessId(0);
							causeUnqualifyAdd.setEmployeeId(idUser);
							causeUnqualifyAdd
									.setSupervisionConstrId(supervision_Line_BackgroundData
											.getSupervision_Constr_Id());
							long iAddCause = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Cause_Line_BG_CableField.TABLE_NAME);

							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;
							causeUnqualifyAdd
									.setCause_Line_Bg_Cable_Id(iAddCause);

							causeLineBGPCableController
									.addItem(causeUnqualifyAdd);
							// Luu anh nguyen nhan loi neu co
							// if (!StringUtil.isNullOrEmpty(curUnqulifyAdd
							// .getNewAttachFile().getFile_Path())) {
							//
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.itemConstrData,
							// curUnqulifyAdd
							// .getNewAttachFile()
							// .getFile_Path(),
							// iAddCause,
							// Cause_Line_BG_CableField.TABLE_NAME);
							// }
							for (Supv_Constr_Attach_FileEntity itemFile : curUnqulifyAdd
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.itemConstrData,
													itemFile.getFile_Path(),
													iAddCause,
													Cause_Line_BG_CableField.TABLE_NAME);

								}
							}
						}
					} else {
						idCable = curItemAdd.getIdCable();
						curItemAdd.setSync_Status(Constants.SYNC_STATUS.ADD);
						supervisionLBGCableController
								.updateAllColumn(curItemAdd);
						/* 2. Cap nhat nguyen nhan loi */
						List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemAdd
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
							/*
							 * 1. Chinh sua nguyen nhan khong dat( Khong sua lai
							 * cac gia tri dong bo)
							 */
							if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
								if (addItemCause.isDelete()) {
									this.causeLineBGPCableController
											.delete(addItemCause);
								}
								/* 1.2. Update lai nguyen nhan khong dat */
								else {
									// if (curItemAdd.getStatus() ==
									// Constants.SUPERVISION_STATUS.DAT) {
									// this.causeLineBGPCableController
									// .delete(addItemCause);
									// }

									if (addItemCause.getLstNewAttachFile()
											.size() > 0
											|| (addItemCause
													.getLstNewAttachFile()
													.size() == 0 && addItemCause
													.getLstAttachFile().size() == 0)) {

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
															this.itemConstrData,
															itemFile.getFile_Path(),
															addItemCause
																	.getCause_Line_Bg_Id(),
															Cause_Line_BG_CableField.TABLE_NAME);

										}
									}
									/* 1.2.1 Thay doi anh */
									// if
									// (!StringUtil.isNullOrEmpty(addItemCause
									// .getNewAttachFile().getFile_Path())) {
									// long idAttachFile = addItemCause
									// .getAttachFile()
									// .getSupv_Constr_Attach_file_Id();
									// /*
									// * Neu da ton tai file luu trong bang
									// * attachment thi chi thay doi ten va
									// * duong dan
									// */
									// if (idAttachFile > 0) {
									//
									// // TODO cho getFileName co the van
									// // trung can xem lai
									// String sFileName = FileManager
									// .getFileName();
									//
									// String sFilePath = FileManager
									// .getSaveFilePath(
									// String.valueOf(this.itemConstrData
									// .getConstructId()),
									// sFileName);
									//
									// FileManager.coppyFile(addItemCause
									// .getNewAttachFile()
									// .getFile_Path(), sFilePath);
									//
									// supvConstrAttachFileController
									// .updatePathNameFile(
									// idAttachFile,
									// sFilePath,
									// sFileName,
									// addItemCause
									// .getAttachFile()
									// .getSync_Status());
									// }
									// /* Khong ton tai file thi lai them moi */
									// else {
									// this.supvConstrAttachFileController
									// .coppyAndAddAttachFile(
									// this.itemConstrData,
									// addItemCause
									// .getNewAttachFile()
									// .getFile_Path(),
									// addItemCause
									// .getCause_Line_Bg_Id(),
									// Cause_Line_BG_CableField.TABLE_NAME);
									// }
									// } else {
									// /* 1.2.2 Xoa anh */
									// if (addItemCause.isDeleteImage()) {
									// supvConstrAttachFileController
									// .delete(addItemCause
									// .getAttachFile());
									// }
									// }
								}
							}
							/* 2. Them moi nguyen nhan khong dat */
							else {
								if (curItemAdd.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
									Cause_Line_BG_CableEntity addCauseItem = new Cause_Line_BG_CableEntity();
									addCauseItem
											.setCat_Cause_Constr_Unqualify_Id(addItemCause
													.getCat_Cause_Constr_Unqualify_Id());
									addCauseItem
											.setSupervision_Line_Bg_Cable_Id(curItemAdd
													.getIdCable());
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
													Cause_Line_BG_CableField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setCause_Line_Bg_Cable_Id(iAddCause);
									this.causeLineBGPCableController
											.addItem(addCauseItem);
									// Luu anh nguyen nhan loi neu co
									// if
									// (!StringUtil.isNullOrEmpty(addItemCause
									// .getNewAttachFile().getFile_Path())) {
									// this.supvConstrAttachFileController
									// .coppyAndAddAttachFile(
									// this.itemConstrData,
									// addItemCause
									// .getNewAttachFile()
									// .getFile_Path(),
									// iAddCause,
									// Cause_Line_BG_CableField.TABLE_NAME);
									// }
									for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
											.getLstNewAttachFile()) {
										if (!StringUtil.isNullOrEmpty(itemFile
												.getFile_Path())) {
											this.supvConstrAttachFileController
													.coppyAndAddAttachFile(
															this.itemConstrData,
															itemFile.getFile_Path(),
															iAddCause,
															Cause_Line_BG_CableField.TABLE_NAME);

										}
									}
								}
							}
						}
					}
				}
			}
			/* Xoa cap duoc luu */
			for (Supervision_Line_BG_CableGSEntity curItemDelete : listSupervisionCableDeleteGS) {
				supervisionLBGCableController.delete(
						curItemDelete.getIdCable(),
						curItemDelete.getSync_Status());

			}
			this.listSupervisionCableDeleteGS.clear();
			// this.showDialog(StringUtil.getString(R.string.text_update_success));

			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(itemConstrData
					.getSupervision_Constr_Id());

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			Bundle bundleData = this.getIntent().getExtras();
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

	/* Xoa ong sau khi confirm */
	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		/* Luu cap xoa vao db */
		if (this.curEditItem.getIdCable() > 0) {
			this.listSupervisionCableDeleteGS.add(this.curEditItem);
		}
		this.listSupervisionCableGS.remove(this.curEditItem);
		this.supervisionCableAdapter.notifyDataSetChanged();
	}

	private String checkVailid(Supervision_Line_BG_CableGSEntity itemCheck) {
		String sReslut = "";
		try {
			if (itemCheck.getFromDistance() < 0) {
				sReslut = StringUtil
						.getString(R.string.line_bg_pipe_unvalid_message);

				return sReslut;
			}
			if (itemCheck.getToDistance() < 0) {
				sReslut = StringUtil
						.getString(R.string.line_bg_pipe_unvalid_message);
				return sReslut;
			}

			if (itemCheck.getFromDistance() > itemCheck.getToDistance()) {
				sReslut = StringUtil
						.getString(R.string.line_hang_cable_fromdis_less_todis)
						+ " " + (listSupervisionCableGS.indexOf(itemCheck) + 1);

				return sReslut;
			}
			int countNnkdCheck = 0;

			for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
				if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
					countNnkdCheck++;
					break;
				}
			}
			if (countNnkdCheck < 1
					&& itemCheck.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sReslut = StringUtil
						.getString(R.string.line_bg_pilar_catcause_valid);
				return sReslut;
			}
			// if (StringUtil.isNullOrEmpty(itemCheck.getFailDesc())) {
			// sReslut = StringUtil
			// .getString(R.string.line_bg_pipe_unvalid_message);
			// return sReslut;
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listCableUnqualifyItem) {
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
					// addItem.setNewAttachFile(curCauseUniqualify
					// .getNewAttachFile());
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
					// if (StringUtil.isNullOrEmpty(curCauseUniqualify
					// .getNewAttachFile().getFile_Path())) {
					// curItem.setNewAttachFile(curCauseUniqualify
					// .getNewAttachFile());
					// }
				} else {
					/* Danh dau xoa nguyen nhan khong dat */
					curItem.setDelete(true);
				}
			}
		}
	}

	private void setUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listCableUnqualifyItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
			if (curItem == null) {
				curCauseUniqualify.setUnqulify(false);
				curCauseUniqualify.setDeleteImage(false);
				// curCauseUniqualify
				// .setAttachFile(new Supv_Constr_Attach_FileEntity());
				// curCauseUniqualify
				// .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
				curCauseUniqualify
						.setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
				curCauseUniqualify
						.setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
				curCauseUniqualify
						.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
			} else {
				curCauseUniqualify.setUnqulify(curItem.isUnqulify());
				curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
				// curCauseUniqualify.setAttachFile(curItem.getAttachFile());
				// curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
				curCauseUniqualify.setLstNewAttachFile(curItem
						.getLstNewAttachFile());
				curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
				curCauseUniqualify.setDelete(curItem.isDelete());
				curCauseUniqualify.setCause_Line_Bg_Id(curItem
						.getCause_Line_Bg_Id());
			}
		}
	}

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
			refreshListView();
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
			saveDataCable();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_Line_BG_CableActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_Line_BG_CableActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}