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
import com.viettel.database.Cause_Line_Hg_CableController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_Hg_CableController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Cause_Line_Hg_CableEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Line_Hg_CableEntity;
import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Cause_Line_Hg_CableField;
import com.viettel.database.field.Supervision_Line_Hg_CableField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_HG_CableAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Giam sat thong tin cong va ranh cap
 * 
 * @author datht1
 * 
 */
public class Supervision_Line_HG_CableActivity extends LineBaseActivity {
	/* controll */
	private View spvLineHG_CableView;
	private ListView lv_line_hg_cable_list;
	private TextView tv_line_hg_cable_dropdown;
	private TextView tv_line_hg_cable_info_line_station_code_value;
	private TextView tv_line_hg_cable_info_line_value;
	private Button btn_line_hg_cable_save;
	private Button btn_line_hg_cable_add;
	private RelativeLayout rl_supervision_line_hg_cable;
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
	/* Tuyen treo giam sat */
	private Supervision_Line_HangEntity supervision_Line_HangData;
	/* Danh sach cột giám sat */
	private List<Supervision_Line_Hg_CableGSEntity> listSupervisionCableGS;
	private List<Supervision_Line_Hg_CableGSEntity> listSupervisionCableDeleteGS;
	private Supervision_Line_Hg_CableController supervisionLHGCableController;
	private Cause_Line_Hg_CableController cause_Line_Hg_CableController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	/* Danh sach nguyen nhan khong dat cua cá»™t da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listCableUnqualifyItem;
	/* Item dang su dung popup */
	private Supervision_Line_Hg_CableGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_Line_HG_CableAdapter supervisionCableAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;

	private Bundle bundleData;

	private Button rl_supervision_hg_cable_bt_complete;
	private TextView supervision_hg_cable_complete_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_hg_cable_activity);
		setTitle(getString(R.string.line_hang_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvLineHG_CableView = addView(R.layout.supervision_line_hg_cable_activity, R.id.rl_supervision_line_hg_cable);
		this.lv_line_hg_cable_list = (ListView) spvLineHG_CableView
				.findViewById(R.id.lv_line_hg_cable_list);
		this.tv_line_hg_cable_dropdown = (TextView) spvLineHG_CableView
				.findViewById(R.id.tv_line_hg_cable_dropdown);
		this.tv_line_hg_cable_dropdown.setOnClickListener(this);

		this.tv_line_hg_cable_info_line_value = (TextView) spvLineHG_CableView
				.findViewById(R.id.tv_line_hg_cable_info_line_value);

		this.tv_line_hg_cable_info_line_station_code_value = (TextView) spvLineHG_CableView
				.findViewById(R.id.tv_line_hg_cable_info_line_station_code_value);
		this.btn_line_hg_cable_save = (Button) spvLineHG_CableView
				.findViewById(R.id.btn_line_hang_cable_save);
		this.btn_line_hg_cable_save.setOnClickListener(this);
		this.btn_line_hg_cable_add = (Button) spvLineHG_CableView
				.findViewById(R.id.btn_line_hang_cable_add);
		this.btn_line_hg_cable_add.setOnClickListener(this);

		rl_supervision_line_hg_cable = (RelativeLayout) spvLineHG_CableView
				.findViewById(R.id.rl_supervision_line_hg_cable);

		this.rl_supervision_hg_cable_bt_complete = (Button) spvLineHG_CableView
				.findViewById(R.id.rl_supervision_hg_cable_bt_complete);
		this.rl_supervision_hg_cable_bt_complete.setOnClickListener(this);

		this.supervision_hg_cable_complete_date = (TextView) spvLineHG_CableView
				.findViewById(R.id.supervision_hg_cable_complete_date);

//		this.rl_supervision_line_hg_cable.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_supervision_line_hg_cable
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_supervision_line_hg_cable
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
//									// rl_supervision_line_hg_cable
//									// .setScrollY(
//									// heightDifference
//									// - Math.round(positionTouch)+ 15);
//									// rl_supervision_line_hg_cable
//									// .setScrollY(
//									// heightScreen - heightDifference - 30);
//									if (positionTouch >= heightDifference) {
//										rl_supervision_line_hg_cable
//												.setScrollY(0);
//									} else {
//										if ((positionTouch + heightDifference) > screenHeight) {
//											rl_supervision_line_hg_cable.setScrollY(heightDifference
//													- (screenHeight - (Math
//															.round(positionTouch) + heightDifference)));
//										} else
//											rl_supervision_line_hg_cable
//													.setScrollY(heightDifference - 25);
//									}
//								} else {
//									rl_supervision_line_hg_cable.setScrollY(0);
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

	private void setHeader() {
		final Header myActionBar = (Header) spvLineHG_CableView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_HG_CableActivity.this);
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

	private void setData() {
		try {

			// JSONArray bg = SqlliteSyncModel.getDataJsonSyncTest(
			// Supervision_Line_Hg_CableField.TABLE_NAME,
			// Supervision_Line_Hg_CableController.allColumn, null, 0,
			// Constants.NUMBER_MAX_ITEM_SYNC);
			//
			// JSONArray cau = SqlliteSyncModel.getDataJsonSyncTest(
			// Cause_Line_Hg_CableField.TABLE_NAME,
			// Cause_Line_Hg_CableController.allColumn, null, 0,
			// Constants.NUMBER_MAX_ITEM_SYNC);

			bundleData = this.getIntent().getExtras();
			this.itemConstrData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_Line_HangData = (Supervision_Line_HangEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA_EX);
			this.iDesignInfo = bundleData
					.getInt(IntentConstants.INTENT_DESIGNINFO);
			this.listSupervisionCableDeleteGS = new ArrayList<Supervision_Line_Hg_CableGSEntity>();
			this.tv_line_hg_cable_dropdown.setText(GloablUtils
					.getStringLineHangInfo(iDesignInfo));
			this.supervisionLHGCableController = new Supervision_Line_Hg_CableController(
					this);
			this.cause_Line_Hg_CableController = new Cause_Line_Hg_CableController(
					this);
			this.supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
					this);
			this.tv_line_hg_cable_info_line_value.setText(this.itemConstrData
					.getConstrCode());

			this.tv_line_hg_cable_info_line_station_code_value
					.setText(itemConstrData.getStationCode());
			/* Nguyen nhan khong dat */
			listCableUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(
					this).getAllUnQualifyItemByName(
					Constants.UNQUALIFY_TYPE.HG_CABLE,
					Constants.UNQUALIFY_TYPE.LINE_HANG);
			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineHangInfo("");
			listSupervisionCableGS = supervisionLHGCableController
					.getAllCableGSBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());
			/* Truong hop Load luu du lieu */
			if (listSupervisionCableGS.size() > 0) {
				/* Gan danh sach loi cho danh sach ong */
				for (Supervision_Line_Hg_CableGSEntity itemPillarGS : listSupervisionCableGS) {
					List<Supervision_LBG_UnqualifyItemEntity> listLHGUnqualify = cause_Line_Hg_CableController
							.getAllCableUnqulifyByCableId(itemPillarGS
									.getIdCable());
					/* Gan anh nguyen nhan loi */
					for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listLHGUnqualify) {
						// Supv_Constr_Attach_FileEntity curAttachFile =
						// this.supvConstrAttachFileController
						// .getAttachFile(
						// Cause_Line_Hg_CableField.TABLE_NAME,
						// curUnqualifyItem.getCause_Line_Bg_Id());
						// curUnqualifyItem.setAttachFile(curAttachFile);
						List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
								.getLstAttachFile(
										Cause_Line_Hg_CableField.TABLE_NAME,
										curUnqualifyItem.getCause_Line_Bg_Id());
						// curUnqualifyItem.setAttachFile(curAttachFile);
						for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
							curUnqualifyItem.getLstAttachFile().add(itemFile);
						}
					}
					itemPillarGS.setListCauseUniQualify(listLHGUnqualify);
				}
			}
			this.supervisionCableAdapter = new Supervision_Line_HG_CableAdapter(
					this, listSupervisionCableGS);
			this.lv_line_hg_cable_list.setAdapter(supervisionCableAdapter);

			showCompleteDate(itemConstrData, Constants.PROGRESS_TYPE.LINE_HANG,
					Constants.PROGRESS_TYPE.HG_CABLE,
					supervision_hg_cable_complete_date,
					rl_supervision_hg_cable_bt_complete);

			if (itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_hg_cable_save.setVisibility(View.GONE);
				// this.rl_supervision_hg_cable_bt_complete.setVisibility(View.GONE);
				this.btn_line_hg_cable_add.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			//handle exception
		}
	}

	private void refreshListView() {
		List<Supervision_Line_Hg_CableGSEntity> curlistSupervisionPillarGS = supervisionLHGCableController
				.getAllCableGSBySupervistionLineHang(this.supervision_Line_HangData
						.getSupervision_Line_Hang_Id());
		/* Truong hop Load luu du lieu */
		if (curlistSupervisionPillarGS.size() > 0) {
			/* Gan danh sach loi cho danh sach ong */
			for (Supervision_Line_Hg_CableGSEntity itemCableGS : curlistSupervisionPillarGS) {
				List<Supervision_LBG_UnqualifyItemEntity> listLHGUnqualify = cause_Line_Hg_CableController
						.getAllCableUnqulifyByCableId(itemCableGS.getIdCable());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listLHGUnqualify) {
					// Supv_Constr_Attach_FileEntity curAttachFile =
					// this.supvConstrAttachFileController
					// .getAttachFile(Cause_Line_Hg_CableField.TABLE_NAME,
					// curUnqualifyItem.getCause_Line_Bg_Id());
					// curUnqualifyItem.setAttachFile(curAttachFile);
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Cause_Line_Hg_CableField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					// curUnqualifyItem.setAttachFile(curAttachFile);
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				itemCableGS.setListCauseUniQualify(listLHGUnqualify);
			}
		}
		this.listSupervisionCableGS.clear();
		this.listSupervisionCableGS.addAll(curlistSupervisionPillarGS);
		this.supervisionCableAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_supervision_hg_cable_bt_complete:
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		case R.id.btn_line_hang_cable_add:
			Supervision_Line_Hg_CableGSEntity addItem = new Supervision_Line_Hg_CableGSEntity();
			this.listSupervisionCableGS.add(addItem);
			this.supervisionCableAdapter.notifyDataSetChanged();
			break;
		case R.id.tv_line_hg_cable_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_line_hang_cable_save:
			String messageError = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
			} else {
				confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
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
							supervision_Line_HangData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoLineHangActivity(bundleData);
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
			this.curEditItem = (Supervision_Line_Hg_CableGSEntity) data;
			break;
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Supervision_Line_Hg_CableGSEntity) data;
			break;
		/* Chon nguyen nhan khong dat */
		case OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT:
			this.curEditItem = (Supervision_Line_Hg_CableGSEntity) data;
			this.iField = this.curEditItem.getIdField();
			switch (iField) {
			// case Constants.HG_CABLE_EDIT.FROM_DISTANCE:
			// String sFromDistanceTextValue = String.valueOf(this.curEditItem
			// .getFromDistance());
			// long iFromDistance = 0;
			// if (!StringUtil.isNullOrEmpty(sFromDistanceTextValue)) {
			// iFromDistance = Long.parseLong(sFromDistanceTextValue);
			// }
			// if (iFromDistance == Constants.ID_ENTITY_DEFAULT) {
			// sFromDistanceTextValue = "";
			// }
			// editTextPopup = new Edit_Text_Popup(this, null,
			// sFromDistanceTextValue, InputType.TYPE_CLASS_NUMBER,
			// false, 4);
			// editTextPopup.showAtCenter();
			// break;
			// case Constants.HG_CABLE_EDIT.TO_DISTANCE:
			// String sToDistanceTextValue = String.valueOf(this.curEditItem
			// .getToDistance());
			// long iToDistance = 0;
			// if (!StringUtil.isNullOrEmpty(sToDistanceTextValue)) {
			// iToDistance = Long.parseLong(sToDistanceTextValue);
			// }
			// if (iToDistance == Constants.ID_ENTITY_DEFAULT) {
			// sToDistanceTextValue = "";
			// }
			// editTextPopup = new Edit_Text_Popup(this, null,
			// sToDistanceTextValue, InputType.TYPE_CLASS_NUMBER,
			// false, 4);
			// editTextPopup.showAtCenter();
			// break;
			case Constants.HG_CABLE_EDIT.UNQUALIFY:
				if (this.curEditItem.getStatus() == Constants.SUPERVISION_STATUS.DAT ||
                        this.curEditItem.getStatus() == Constants.SUPERVISION_STATUS.CHUA_DANH_GIA
				|| this.curEditItem.getStatus() == -1) {
					Toast.makeText(
							this,
							StringUtil.getString(R.string.line_hang_cable_cause_choice),
							Toast.LENGTH_SHORT).show();
				} else {
					// Gan gia tri nguyen nhan loi
					this.setUnqualify();
					contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listCableUnqualifyItem);
					contruoctionUnqualifyPopup.showAtCenter();
				}
				break;
			case Constants.HG_CABLE_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem.getFailDesc();
				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.HG_CABLE_EDIT.DELETE:
				this.showAskOptionDialog(StringUtil.getString(R.string.text_delete_title),
                        StringUtil.getString(R.string.line_bg_pipe_delete_message));
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
			// case Constants.HG_CABLE_EDIT.FROM_DISTANCE:
			// if (!StringUtil.isNullOrEmpty(sSetTextValue)) {
			// this.curEditItem.setFromDistance(Long
			// .parseLong(sSetTextValue));
			// } else
			// this.curEditItem
			// .setFromDistance(Constants.ID_ENTITY_DEFAULT);
			// break;
			// case Constants.HG_CABLE_EDIT.TO_DISTANCE:
			// if (!StringUtil.isNullOrEmpty(sSetTextValue)) {
			// this.curEditItem.setToDistance(Long
			// .parseLong(sSetTextValue));
			// } else
			// this.curEditItem.setToDistance(Constants.ID_ENTITY_DEFAULT);
			// break;
			case Constants.HG_CABLE_EDIT.FAIL_DESC:
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
		/* Thoat khoi man hinh chon anh refresh lai list nguyen nhan khong dat */
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
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			// this.saveDataCable();
			// this.refreshListView();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			saveCompleteDay(itemConstrData, Constants.PROGRESS_TYPE.LINE_HANG,
					Constants.PROGRESS_TYPE.HG_CABLE, outOfKey);
			showCompleteDate(itemConstrData, Constants.PROGRESS_TYPE.LINE_HANG,
					Constants.PROGRESS_TYPE.HG_CABLE,
					supervision_hg_cable_complete_date,
					rl_supervision_hg_cable_bt_complete);
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

	private void saveDataCable() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			/* Xoa cac Cable */
			for (Supervision_Line_Hg_CableGSEntity curItemData : this.listSupervisionCableDeleteGS) {
				supervisionLHGCableController.delete(curItemData.getIdCable(),
						curItemData.getSync_Status());
			}
			/* Them du lieu */
			for (Supervision_Line_Hg_CableGSEntity curItemData : this.listSupervisionCableGS) {
				/* Them moi */
				if (curItemData.isNew()) {
					Supervision_Line_Hg_CableEntity addItem = new Supervision_Line_Hg_CableEntity();
					addItem.setFrom_DISTANCE(curItemData.getFromDistance());
					addItem.setTo_DISTANCE(curItemData.getToDistance());
					addItem.setFail_DESC(curItemData.getFailDesc());
					addItem.setStatus(curItemData.getStatus());
					addItem.setSupervision_LINE_HANG_ID(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());
					addItem.setSync_Status(Constants.SYNC_STATUS.ADD);
					addItem.setIsActive(Constants.ISACTIVE.ACTIVE);
					addItem.setProcessId(0);
					addItem.setEmployeeId(idUser);
					addItem.setSupervisionConstrId(supervision_Line_HangData
							.getSupervisionConstrId());

					long idAddCable = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Line_Hg_CableField.TABLE_NAME);

					if (idAddCable == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					addItem.setSupervision_LINE_HG_CABLE_ID(idAddCable);
					supervisionLHGCableController.addItem(addItem);
					/* Add nguyen nhan khong dat */
					if (addItem.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
						List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
							Cause_Line_Hg_CableEntity addCauseItem = new Cause_Line_Hg_CableEntity();
							addCauseItem
									.setCat_Cause_Constr_Unqualify_Id(curItemUnqualify
											.getCat_Cause_Constr_Unqualify_Id());
							addCauseItem
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
							addCauseItem.setProcessId(0);
							addCauseItem.setEmployeeId(idUser);
							addCauseItem
									.setSupervisionConstrId(supervision_Line_HangData
											.getSupervision_Constr_Id());

							addCauseItem
									.setSupervision_Line_Hg_Cable_Id(idAddCable);
							long iAddCause = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Cause_Line_Hg_CableField.TABLE_NAME);

							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem.setCause_Line_Hg_Cable_Id(iAddCause);
							this.cause_Line_Hg_CableController
									.addItem(addCauseItem);

							// Luu anh nguyen nhan loi neu co
							// if (!StringUtil.isNullOrEmpty(curItemUnqualify
							// .getNewAttachFile().getFile_Path())) {
							//
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.itemConstrData,
							// curItemUnqualify
							// .getNewAttachFile()
							// .getFile_Path(),
							// iAddCause,
							// Cause_Line_Hg_CableField.TABLE_NAME);
							// }
							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.itemConstrData,
													itemFile.getFile_Path(),
													iAddCause,
													Cause_Line_Hg_CableField.TABLE_NAME);

								}
							}
						}
					}
					/* Cap nhat chinh sua */
				} else {
					/* 1.Thong tin be co chinh sua */
					if (curItemData.isEdited()) {
						curItemData.setSync_Status(Constants.SYNC_STATUS.ADD);
						this.supervisionLHGCableController
								.updateAllColumn(curItemData);
						/* 2. Cap nhat nguyen nhan loi */
						List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemData
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
							/*
							 * 1. Chinh sua nguyen nhan khong dat( Khong sua lai
							 * cac gia tri dong bo)
							 */
							if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
								if (addItemCause.isDelete()) {
									this.cause_Line_Hg_CableController
											.delete(addItemCause);
								}
								/* 1.2. Update lai nguyen nhan khong dat */
								else {
									if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
										this.cause_Line_Hg_CableController
												.delete(addItemCause);
									}

									if (addItemCause.getLstNewAttachFile()
											.size() > 0
											|| (addItemCause
													.getLstNewAttachFile()
													.size() == 0 && addItemCause
													.getLstAttachFile().size() == 0)) {
										List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
												.getLstAttachFile(
														Cause_Line_Hg_CableField.TABLE_NAME,
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
															Cause_Line_Hg_CableField.TABLE_NAME);

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
									// // cho getFileName co the van
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
									// Cause_Line_Hg_CableField.TABLE_NAME);
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
								if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
									Cause_Line_Hg_CableEntity addCauseItem = new Cause_Line_Hg_CableEntity();
									addCauseItem
											.setCat_Cause_Constr_Unqualify_Id(addItemCause
													.getCat_Cause_Constr_Unqualify_Id());
									addCauseItem
											.setSupervision_Line_Hg_Cable_Id(curItemData
													.getIdCable());
									addCauseItem
											.setSync_Status(Constants.SYNC_STATUS.ADD);
									addCauseItem
											.setIsActive(Constants.ISACTIVE.ACTIVE);
									addCauseItem.setProcessId(0);
									addCauseItem.setEmployeeId(idUser);
									addCauseItem
											.setSupervisionConstrId(supervision_Line_HangData
													.getSupervision_Constr_Id());

									long iAddCause = Ktts_KeyController
											.getInstance()
											.getKttsNextKey(
													Cause_Line_Hg_CableField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setCause_Line_Hg_Cable_Id(iAddCause);
									this.cause_Line_Hg_CableController
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
									// Cause_Line_Hg_CableField.TABLE_NAME);
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
															Cause_Line_Hg_CableField.TABLE_NAME);

										}
									}
								}

							}
						}
					}
				}
			}
			/* Xoa cap duoc luu */
			for (Supervision_Line_Hg_CableGSEntity curItemDelete : listSupervisionCableDeleteGS) {
				supervisionLHGCableController.delete(
						curItemDelete.getIdCable(),
						curItemDelete.getSync_Status());

			}
			this.listSupervisionCableDeleteGS.clear();

			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(itemConstrData
					.getSupervision_Constr_Id());
			// this.showDialog(StringUtil.getString(R.string.text_update_success));

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			bundleData = this.getIntent().getExtras();
			boolean bDeny = checkCauseDenyHangLine(bundleData);
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
			// Toast.makeText(this,
			// StringUtil.getString(R.string.text_update_error),
			// Toast.LENGTH_SHORT).show();
			// this.showDialog(StringUtil.getString(R.string.text_update_error));
		}
	}

	private String checkVailid() {
		String sReslut = "";
		for (int i = 0, size = this.listSupervisionCableGS.size(); i < size; i++) {
			sReslut = this.checkVailidCableItem(listSupervisionCableGS.get(i));
			if (!StringUtil.isNullOrEmpty(sReslut)) {
				return sReslut;
			}
		}

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
					&& listSupervisionCableGS.get(i).getToDistance() > supervision_Line_HangData
							.getLong_Total()) {
				return StringUtil
						.getString(R.string.line_hang_cable_todislast_eual_longtotal);
			}
		}
		return sReslut;
	}

	private String checkVailidCableItem(
			Supervision_Line_Hg_CableGSEntity itemCheck) {
		String sReslut = "";
		int countNnkdCheck = 0;

		for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
			if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		if (itemCheck.getFromDistance() == Constants.ID_ENTITY_DEFAULT) {
			sReslut = StringUtil.getString(R.string.line_hang_cable_from_valid);
		} else if (itemCheck.getToDistance() == Constants.ID_ENTITY_DEFAULT) {
			sReslut = StringUtil.getString(R.string.line_hang_cable_to_valid);
		} else if (itemCheck.getFromDistance() > itemCheck.getToDistance()) {
			sReslut = StringUtil
					.getString(R.string.line_hang_cable_fromdis_less_todis)
					+ " " + (listSupervisionCableGS.indexOf(itemCheck) + 1);
		}

		else if (itemCheck.getStatus() == Constants.ID_ENTITY_DEFAULT) {
			sReslut = StringUtil
					.getString(R.string.line_hang_cable_status_valid);
		}

		else if (countNnkdCheck < 1
				&& itemCheck.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
			sReslut = StringUtil
					.getString(R.string.line_hang_pilar_catcause_valid);
		}
		return sReslut;
	}

	public boolean checkToDisBeforeEqualFromDisLast(long toDisBefore,
			long fromDisLast) {
		if (toDisBefore != fromDisLast) {
			return true;
		}
		return false;
	}

	/* Xoa mang xong sau khi confirm */
	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		if (this.curEditItem.getIdCable() > 0) {
			this.listSupervisionCableDeleteGS.add(this.curEditItem);
		}
		this.listSupervisionCableGS.remove(this.curEditItem);
		this.supervisionCableAdapter.notifyDataSetChanged();
	}

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : this.listCableUnqualifyItem) {
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
				// curCauseUniqualify
				// .setAttachFile(new Supv_Constr_Attach_FileEntity());
				curCauseUniqualify.setDeleteImage(false);
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
				// curCauseUniqualify.setAttachFile(curItem.getAttachFile());
				curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
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
			saveDataCable();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_Line_HG_CableActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_Line_HG_CableActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}
