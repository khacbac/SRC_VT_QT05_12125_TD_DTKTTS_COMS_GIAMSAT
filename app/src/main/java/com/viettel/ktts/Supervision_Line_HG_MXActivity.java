package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
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
import com.viettel.database.Cause_Line_Hg_MxController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_HangController;
import com.viettel.database.Supervision_Line_Hg_FiberController;
import com.viettel.database.Supervision_Line_Hg_MxController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Cause_Line_Hg_MxEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Line_Hg_FiberEntity;
import com.viettel.database.entity.Supervision_Line_Hg_MxEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Cause_Line_Hg_MxField;
import com.viettel.database.field.Supervision_Line_Hg_FiberField;
import com.viettel.database.field.Supervision_Line_Hg_MxField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_HG_FiberAdapter;
import com.viettel.view.control.Supervision_Line_HG_MxAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 * 
 */
public class Supervision_Line_HG_MXActivity extends LineBaseActivity {
	private View spvLineHG_MXView;
	private TabHost tabHost;
	private int tabHeight = 40;
	private int tabWidth = 150;
	private int iField = -1;
	/* 1 la nhap mang xong, 2 la nhap soi */
	private int iTypeField = -1;
	/* controll */
	private TextView tv_line_hg_mx_dropdown;
	private TextView tv_supervision_line_hg_mx_info_line_station_code_value;
	private TextView tv_supervision_line_hg_mx_info_line_value;
	private TextView tv_supervision_line_hg_mx_dropdown_trangthai;
	private EditText edt_supervision_line_hg_mx_nguoido;
	private EditText edt_supervision_line_hg_mx_maydo;
	private EditText edt_supervision_line_hg_mx_donvi;
	private EditText edt_supervision_line_hg_mx_sdt;
	private EditText edt_supervision_line_hg_mx_serialmay;
	private Button btn_line_hg_mx_save;
	private Button btn_line_hg_mx_add;
	private ListView lv_line_hg_mx_list;
	private ListView lv_line_hg_mx_fiber_list;
	/* bien dropdown */
	private int iDesignInfo = Constants.SEARCH_VALUE_DEFAULT;
	private int iStatus = Constants.SEARCH_VALUE_DEFAULT;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private List<DropdownItemEntity> listStatus = null;
	private Menu_DropdownPopup dropdownPopupMenuStatus;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_ConstrEntity supervision_ConstrData;
	private Supervision_Line_HangEntity supervision_Line_HangData;
	private Supervision_ConstrController supervision_ConstrController;
	private Supervision_Line_HangController supervision_Line_HangController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listMXUnqualifyItem;
	private List<Supervision_Line_HG_MXGSEntity> listLineHGMX;
	private List<Supervision_Line_HG_MXGSEntity> listLineHGMXDelete;
	private List<Supervision_Line_Hg_FiberEntity> listLineHGFiber;
	private Supervision_Line_Hg_MxController supervisionLineHGMXController;
	private Supervision_Line_Hg_FiberController supervisionLineFiberController;
	private Cause_Line_Hg_MxController causeLineHGMXController;
	private Supervision_Line_HG_MxAdapter supervisionLineHGMXAdapter;
	private Supervision_Line_HG_FiberAdapter supervisionLineHGFiberAdapter;
	/* Item ong cap dang sua dung popup */
	private Supervision_Line_HG_MXGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_Line_Hg_FiberEntity curEditFiberItem = null;
	private boolean outOfKey = false;
	private Bundle bundleData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_hg_mx_activity);
		setTitle(getString(R.string.line_hang_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		initTabHost();
		setData();
	}

	private void initView() {
		spvLineHG_MXView = addView(R.layout.supervision_line_hg_mx_activity, R.id.rl_login);
		this.tv_line_hg_mx_dropdown = (TextView) spvLineHG_MXView
				.findViewById(R.id.tv_line_hg_mx_dropdown);
		this.tv_line_hg_mx_dropdown.setOnClickListener(this);

		this.tv_supervision_line_hg_mx_info_line_station_code_value = (TextView) spvLineHG_MXView
				.findViewById(R.id.tv_line_hg_mx_info_line_station_code_value);

		this.tv_supervision_line_hg_mx_info_line_value = (TextView) spvLineHG_MXView
				.findViewById(R.id.tv_line_hg_mx_info_line_value);

		this.tv_supervision_line_hg_mx_dropdown_trangthai = (TextView) spvLineHG_MXView
				.findViewById(R.id.tv_supervision_line_hg_mx_dropdown_trangthai);
		this.tv_supervision_line_hg_mx_dropdown_trangthai
				.setOnClickListener(this);

		this.edt_supervision_line_hg_mx_nguoido = (EditText) spvLineHG_MXView
				.findViewById(R.id.edt_supervision_line_hg_mx_nguoido);

		this.edt_supervision_line_hg_mx_maydo = (EditText) spvLineHG_MXView
				.findViewById(R.id.edt_supervision_line_hg_mx_maydo);

		this.edt_supervision_line_hg_mx_donvi = (EditText) spvLineHG_MXView
				.findViewById(R.id.edt_supervision_line_hg_mx_donvi);

		this.edt_supervision_line_hg_mx_sdt = (EditText) spvLineHG_MXView
				.findViewById(R.id.edt_supervision_line_hg_mx_sdt);

		this.edt_supervision_line_hg_mx_serialmay = (EditText) spvLineHG_MXView
				.findViewById(R.id.edt_supervision_line_hg_mx_serialmay);

		this.btn_line_hg_mx_save = (Button) spvLineHG_MXView
				.findViewById(R.id.btn_line_hg_mx_save);
		this.btn_line_hg_mx_save.setOnClickListener(this);

		this.btn_line_hg_mx_add = (Button) spvLineHG_MXView
				.findViewById(R.id.btn_line_hg_mx_add);
		this.btn_line_hg_mx_add.setOnClickListener(this);

		this.lv_line_hg_mx_list = (ListView) spvLineHG_MXView
				.findViewById(R.id.lv_line_hg_mx_list);
		this.lv_line_hg_mx_fiber_list = (ListView) spvLineHG_MXView
				.findViewById(R.id.lv_line_hg_mx_fiber_list);
		lv_line_hg_mx_fiber_list
				.setOnTouchListener(new ListView.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						int action = event.getAction();
						switch (action) {
						case MotionEvent.ACTION_DOWN:
							// Disallow ScrollView to intercept touch events.
							v.getParent().requestDisallowInterceptTouchEvent(
									true);
							break;

						case MotionEvent.ACTION_UP:
							// Allow ScrollView to intercept touch events.
							v.getParent().requestDisallowInterceptTouchEvent(
									false);
							break;
						}

						// Handle ListView touch events.
						v.onTouchEvent(event);
						return true;
					}
				});
	}

	private void initTabHost() {
		tabHost = (TabHost) spvLineHG_MXView.findViewById(R.id.tabHost);
		tabHost.setup();
		String sMXKD = getString(R.string.line_hg_mx_mangxongkhongdat);
		TabSpec specTank = tabHost.newTabSpec(sMXKD);
		specTank.setContent(R.id.ll_line_hg_mx_mxkhongdat);
		specTank.setIndicator(makeTabIndicator(sMXKD));

		String sDSK = getString(R.string.line_hg_mx_doancap_dokiemsoi);
		TabSpec specCable = tabHost.newTabSpec(sDSK);
		specCable.setContent(R.id.ll_supervision_line_hg_mx_dokiem);
		specCable.setIndicator(makeTabIndicator(sDSK));
		tabHost.addTab(specTank);
		tabHost.addTab(specCable);
		/* Chi hien thi them moi mang xong o tab mang xong */
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				int i = tabHost.getCurrentTab();
				if (i == 0) {
					btn_line_hg_mx_add.setVisibility(View.VISIBLE);
				} else {
					btn_line_hg_mx_add.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_line_hg_mx_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_supervision_line_hg_mx_dropdown_trangthai:
			this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this,
					this.listStatus);
			dropdownPopupMenuStatus.show(v);
			break;
		case R.id.btn_line_hg_mx_add:
			// TODO: kiem tra vuot so luong
			if(this.listLineHGMX.size() == supervision_Line_HangData.getMx_Total()){
				this.showDialog(StringUtil.getString(R.string.line_mx_enough));
				return;
			}
			Supervision_Line_HG_MXGSEntity addItem = new Supervision_Line_HG_MXGSEntity();
			this.listLineHGMX.add(addItem);
			this.supervisionLineHGMXAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_line_hg_mx_save:
			String messageError = "";
			if (tabHost.getCurrentTab() == 0) {
				messageError = this.checkVailid();
				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);
				} else {
					ConfirmDialog confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			} else {
				if (tabHost.getCurrentTab() == 1) {
					if (!checkDoKiem()) {
						messageError = checkFiber();
						if (!StringUtil.isNullOrEmpty(messageError)) {

							this.showDialog(messageError);
						} else {
							ConfirmDialog confirmSave = new ConfirmDialog(
									this,
									StringUtil
											.getString(R.string.text_confirm_save));
							confirmSave.show();
						}
					}
				}
			}

			// if (!StringUtil.isNullOrEmpty(messageError)) {
			// this.showDialog(messageError);
			// } else {
			// if (!checkDoKiem()) {
			// messageError = checkFiber();
			// if (!StringUtil.isNullOrEmpty(messageError)) {
			//
			// this.showDialog(messageError);
			// } else {
			// ConfirmDialog confirmSave = new ConfirmDialog(this,
			// StringUtil
			// .getString(R.string.text_confirm_save));
			// confirmSave.show();
			// }
			// }
			//
			// }
			break;
		default:
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
		case Constants.GET_LOCATION_RESULT:
			if (resultCode == Activity.RESULT_OK) {
				this.curEditItem.setLatItude(data.getDoubleExtra(
						IntentConstants.INTENT_LAT,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				this.curEditItem.setLongItude(data.getDoubleExtra(
						IntentConstants.INTENT_LONG,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				this.curEditItem.setEdited(true);
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
							itemData);
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_HangData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoLineHangActivity(bundleData);
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_STATUS)) {
				this.iStatus = dropdownItem.getId();

				if (this.iStatus > 0) {
					tv_supervision_line_hg_mx_dropdown_trangthai.setError(null);
				}

				this.tv_supervision_line_hg_mx_dropdown_trangthai
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuStatus.dismiss();
			}
			break;

		/* chon chinh sua gird view */
		case OnEventControlListener.EVENT_SUPERVISION_MX_EDIT:
			this.curEditItem = (Supervision_Line_HG_MXGSEntity) data;
			this.iField = this.curEditItem.getIdField();
			this.iTypeField = 1;
			switch (iField) {
			case Constants.BG_MX_EDIT.NAME:
				String sFromDistanceTextValue = "";
				sFromDistanceTextValue = String.valueOf(this.curEditItem
						.getMxName());

				editTextPopup = new Edit_Text_Popup(this, null,
						sFromDistanceTextValue, InputType.TYPE_CLASS_TEXT,
						false, 5);
				editTextPopup.showAtCenter();
				break;
			case Constants.BG_MX_EDIT.UNQUALIFY:
				// Gan gia tri nguyen nhan loi
				this.setUnqualify();
				contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listMXUnqualifyItem);
				contruoctionUnqualifyPopup.showAtCenter();
				break;
			case Constants.BG_MX_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem.getFailDesc();
				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.BG_MX_EDIT.DELETE:
				this.showAskOptionDialog(StringUtil
						.getString(R.string.text_delete_title), StringUtil
						.getString(R.string.line_hg_pipe_delete_message));
				break;
			default:
				break;
			}
			break;
		/* chon nghi nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionLineHGMXAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;
			if (iTypeField == 1) {
				switch (this.iField) {
				case Constants.BG_MX_EDIT.NAME:
					this.curEditItem.setMxName(sSetTextValue.trim());
					break;
				case Constants.BG_MX_EDIT.FAIL_DESC:
					this.curEditItem.setFailDesc(sSetTextValue);
					break;

				default:
					break;
				}
				this.curEditItem.setEdited(true);
				this.supervisionLineHGMXAdapter.notifyDataSetChanged();
			} else if (iTypeField == 2) {
				if (!StringUtil.isNullOrEmpty(sSetTextValue)) {
					this.curEditFiberItem.setMeasure_Value_Db(Float
							.parseFloat(sSetTextValue));
					this.supervisionLineHGFiberAdapter.notifyDataSetChanged();
				}
			}
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
			// Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			// san co
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
			this.takePhoto(itemData);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_SUPERVISION_FIBER_EDIT:
			this.curEditFiberItem = (Supervision_Line_Hg_FiberEntity) data;
			this.iField = this.curEditFiberItem.getIdField();
			this.iTypeField = 2;
			String sTextValue = String.valueOf(this.curEditFiberItem
					.getMeasure_Value_Db());
			if (this.curEditFiberItem
					.getMeasure_Value_Db() == -1f) {
				sTextValue = "";
			}
			editTextPopup = new Edit_Text_Popup(this, null, sTextValue,
					InputType.TYPE_NUMBER_FLAG_DECIMAL, false, 10);
			editTextPopup.showAtCenter();
			break;
		case OnEventControlListener.EVENT_LOCATION:
			this.curEditItem = (Supervision_Line_HG_MXGSEntity) data;
			String sMxName = this.curEditItem.getMxName();
			if (StringUtil.isNullOrEmpty(sMxName)) {
				sMxName = "";
			}
			this.getLocation(this.curEditItem.getLongItude(),
					this.curEditItem.getLatItude(),
					StringUtil.getString(R.string.line_hg_mx_doancap_tenmx),
					sMxName);
			break;
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			// this.saveDataMX();
			// this.refreshData();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineHG_MXView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_HG_MXActivity.this);
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
			bundleData = this.getIntent().getExtras();
			this.itemData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_ConstrController = new Supervision_ConstrController(
					this);
			this.supervision_Line_HangController = new Supervision_Line_HangController(
					this);
			this.causeLineHGMXController = new Cause_Line_Hg_MxController(this);
			supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
					this);

			this.supervision_ConstrData = this.supervision_ConstrController
					.getItem(this.itemData.getSupervision_Constr_Id());

			this.supervision_Line_HangData = supervision_Line_HangController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());

			this.tv_supervision_line_hg_mx_info_line_value
					.setText(this.itemData.getConstrCode());

			this.iStatus = this.supervision_Line_HangData.getMeasure_Status();
			this.tv_supervision_line_hg_mx_dropdown_trangthai
					.setText(GloablUtils
							.getStringConstructionStatus(this.iStatus));

			this.edt_supervision_line_hg_mx_nguoido
					.setText(this.supervision_Line_HangData.getMeasure_Person());

			this.edt_supervision_line_hg_mx_maydo
					.setText(this.supervision_Line_HangData
							.getMeasure_Machine_Type());

			this.edt_supervision_line_hg_mx_donvi
					.setText(this.supervision_Line_HangData.getMeasure_Group());

			this.edt_supervision_line_hg_mx_sdt
					.setText(this.supervision_Line_HangData
							.getMeasure_Person_Mobile());

			this.edt_supervision_line_hg_mx_serialmay
					.setText(this.supervision_Line_HangData
							.getMeasure_Machine_Serial());

			this.tv_supervision_line_hg_mx_info_line_station_code_value
					.setText(itemData.getStationCode());

			/* Man xong list */
			this.supervisionLineHGMXController = new Supervision_Line_Hg_MxController(
					this);

			this.supervisionLineFiberController = new Supervision_Line_Hg_FiberController(
					this);
			this.listLineHGMXDelete = new ArrayList<Supervision_Line_HG_MXGSEntity>();
			this.listLineHGMX = this.supervisionLineHGMXController
					.getAllMXGSBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());

			for (Supervision_Line_HG_MXGSEntity curItemSupervisonMX : listLineHGMX) {
				List<Supervision_LBG_UnqualifyItemEntity> curListUnqualifyItem = this.causeLineHGMXController
						.getAllMxUnqulifyByMxId(curItemSupervisonMX.getIdMX());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curListUnqualifyItem) {
					// Supv_Constr_Attach_FileEntity curAttachFile =
					// this.supvConstrAttachFileController
					// .getAttachFile(Cause_Line_Hg_MxField.TABLE_NAME,
					// curUnqualifyItem.getCause_Line_Bg_Id());
					// curUnqualifyItem.setAttachFile(curAttachFile);
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Line_Hg_MxField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				curItemSupervisonMX
						.setListCauseUniQualify(curListUnqualifyItem);
			}

			this.listLineHGFiber = this.supervisionLineFiberController
					.getAllFiberBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());
			if (this.listLineHGFiber.size() == 0) {
				this.getFirstFiberList(this.supervision_Line_HangData
						.getCable_Type());
			} else {
				// this.listLineBGFiber.addAll(curListLineBGFiber);
				int sizeFiber = listLineHGFiber.size();

				if (listLineHGFiber.size() < (supervision_Line_HangData
						.getCable_Type())) {
					for (int i = 0; i < (supervision_Line_HangData
							.getCable_Type() - sizeFiber); i++) {
						Supervision_Line_Hg_FiberEntity fiberItem = new Supervision_Line_Hg_FiberEntity();

						fiberItem
								.setSupervision_Line_Hang_Id(this.supervision_Line_HangData
										.getSupervision_Line_Hang_Id());
						fiberItem.setFiber_Name(GloablUtils
								.getFiberName(sizeFiber + i + 1));
						fiberItem.setNew(true);
						fiberItem.setEdit(true);
						listLineHGFiber.add(fiberItem);
					}

				}
			}

			this.supervisionLineHGMXAdapter = new Supervision_Line_HG_MxAdapter(
					this, listLineHGMX);
			this.supervisionLineHGFiberAdapter = new Supervision_Line_HG_FiberAdapter(
					this, listLineHGFiber);

			this.lv_line_hg_mx_list.setAdapter(this.supervisionLineHGMXAdapter);
			this.lv_line_hg_mx_fiber_list
					.setAdapter(this.supervisionLineHGFiberAdapter);
			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineHangInfo("");
			this.listStatus = GloablUtils.getListConstructionStatus("");

			this.tv_line_hg_mx_dropdown
					.setText(GloablUtils
							.getStringLineBackgroundInfo(Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO));
			listMXUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
					.getAllUnQualifyItemByName(Constants.UNQUALIFY_TYPE.HG_MX,
							Constants.UNQUALIFY_TYPE.LINE_HANG);
			/* Khong hien thi luu khi cong trinh da ket luan */
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_hg_mx_save.setVisibility(View.GONE);
				this.btn_line_hg_mx_add.setVisibility(View.GONE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void refreshData() {
		List<Supervision_Line_HG_MXGSEntity> curListLineHGMX = this.supervisionLineHGMXController
				.getAllMXGSBySupervistionLineHang(this.supervision_Line_HangData
						.getSupervision_Line_Hang_Id());
		for (Supervision_Line_HG_MXGSEntity curItemSupervisonMX : curListLineHGMX) {
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualifyItem = this.causeLineHGMXController
					.getAllMxUnqulifyByMxId(curItemSupervisonMX.getIdMX());
			/* Gan anh nguyen nhan loi */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curListUnqualifyItem) {
				// Supv_Constr_Attach_FileEntity curAttachFile =
				// this.supvConstrAttachFileController
				// .getAttachFile(Cause_Line_Hg_MxField.TABLE_NAME,
				// curUnqualifyItem.getCause_Line_Bg_Id());
				// curUnqualifyItem.setAttachFile(curAttachFile);
				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(Cause_Line_Hg_MxField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}
			}
			curItemSupervisonMX.setListCauseUniQualify(curListUnqualifyItem);
		}
		this.listLineHGMX.clear();
		this.listLineHGMX.addAll(curListLineHGMX);
		this.supervisionLineHGMXAdapter.notifyDataSetChanged();

		this.listLineHGFiber.clear();
		List<Supervision_Line_Hg_FiberEntity> curListLineBGFiber = this.supervisionLineFiberController
				.getAllFiberBySupervistionLineHang(this.supervision_Line_HangData
						.getSupervision_Line_Hang_Id());
		if (curListLineBGFiber.size() == 0) {
			this.getFirstFiberList(this.supervision_Line_HangData
					.getCable_Type());
		} else {
			this.listLineHGFiber.addAll(curListLineBGFiber);
		}
		this.supervisionLineHGFiberAdapter.notifyDataSetChanged();

	}

	private void saveDataMX() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();

			if (tabHost.getCurrentTab() == 0) {
				/* Xoa nhung mang xong xac dinh xoa */
				for (Supervision_Line_HG_MXGSEntity itemDelete : this.listLineHGMXDelete) {
					this.supervisionLineHGMXController.delete(
							itemDelete.getIdMX(), itemDelete.getSync_Status());
				}
				this.listLineHGMXDelete.clear();
				/* Bat dau luu thong tin */
				for (Supervision_Line_HG_MXGSEntity curItemData : listLineHGMX) {
					/* Them moi */
					if (curItemData.isNew()) {
						Supervision_Line_Hg_MxEntity addItem = new Supervision_Line_Hg_MxEntity();
						addItem.setMxName(curItemData.getMxName());
						addItem.setFail_Desc(curItemData.getFailDesc());
						addItem.setLatItude(curItemData.getLatItude());
						addItem.setLongItude(curItemData.getLongItude());
						curItemData.setStatus(Constants.MX_STATUS.KHONG_DAT);
						addItem.setSupervision_Line_Hang_Id(this.supervision_Line_HangData
								.getSupervision_Line_Hang_Id());
						addItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addItem.setProcessId(0);
						addItem.setEmployeeId(idUser);
						addItem.setSupervisionConstrId(supervision_Line_HangData
								.getSupervision_Constr_Id());

						long idAddMx = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Supervision_Line_Hg_MxField.TABLE_NAME);

						if (idAddMx == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addItem.setSupervision_Line_Hg_Mx_Id(idAddMx);

						supervisionLineHGMXController.addItem(addItem);
						/* Add nguyen nhan khong dat */
						// if (addItem.getStatus() ==
						// Constants.MX_STATUS.KHONG_DAT)
						// {
						List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
							Cause_Line_Hg_MxEntity addCauseItem = new Cause_Line_Hg_MxEntity();
							addCauseItem
									.setCat_Cause_Constr_Unqualify_Id(curItemUnqualify
											.getCat_Cause_Constr_Unqualify_Id());
							addCauseItem.setSupervision_Line_Bg_MX_Id(idAddMx);
							addCauseItem
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
							addCauseItem.setProcessId(0);
							addCauseItem.setEmployeeId(idUser);
							addCauseItem
									.setSupervisionConstrId(supervision_Line_HangData
											.getSupervision_Constr_Id());

							long iAddCause = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Line_Hg_MxField.TABLE_NAME);

							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem.setCause_Line_Bg_Mx_Id(iAddCause);
							this.causeLineHGMXController.addItem(addCauseItem);

							// Luu anh nguyen nhan loi neu co
							// if (!StringUtil.isNullOrEmpty(curItemUnqualify
							// .getNewAttachFile().getFile_Path())) {
							//
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.itemData,
							// curItemUnqualify
							// .getNewAttachFile()
							// .getFile_Path(),
							// iAddCause,
							// Cause_Line_Hg_MxField.TABLE_NAME);
							// }
							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.itemData,
													itemFile.getFile_Path(),
													iAddCause,
													Cause_Line_Hg_MxField.TABLE_NAME);

								}
							}
						}
						// }
						/* Cap nhat chinh sua */
					} else {
						/* 1.Thong tin be co chinh sua */
						if (curItemData.isEdited()) {

							curItemData
									.setSync_Status(Constants.SYNC_STATUS.ADD);

							this.supervisionLineHGMXController
									.updateAllColumn(curItemData);

							/* 2. Cap nhat nguyen nhan loi */
							List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemData
									.getListCauseUniQualify();
							for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
								/*
								 * 1. Chinh sua nguyen nhan khong dat( Khong sua
								 * lai cac gia tri dong bo)
								 */
								if (addItemCause.getCause_Line_Bg_Id() > 0) {
									/*
									 * 1.1. Xoa nguyen nhan khong dat danh dau
									 * xoa
									 */
									if (addItemCause.isDelete()) {
										this.causeLineHGMXController
												.delete(addItemCause);
									}
									/* 1.2. Update lai nguyen nhan khong dat */
									else {
										
										
										if (addItemCause.getLstNewAttachFile()
												.size() > 0 || (addItemCause.getLstNewAttachFile().size() == 0
														&& addItemCause.getLstAttachFile().size() == 0)) {
											List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
													.getLstAttachFile(
															Cause_Line_Hg_MxField.TABLE_NAME,
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
											if (!StringUtil
													.isNullOrEmpty(itemFile
															.getFile_Path())) {
												this.supvConstrAttachFileController
														.coppyAndAddAttachFile(
																this.itemData,
																itemFile.getFile_Path(),
																addItemCause
																		.getCause_Line_Bg_Id(),
																Cause_Line_Hg_MxField.TABLE_NAME);

											}
										}
										/* 1.2.1 Thay doi anh */
										// if (!StringUtil
										// .isNullOrEmpty(addItemCause
										// .getNewAttachFile()
										// .getFile_Path())) {
										// long idAttachFile = addItemCause
										// .getAttachFile()
										// .getSupv_Constr_Attach_file_Id();
										// /*
										// * Neu da ton tai file luu trong
										// * bang attachment thi chi thay doi
										// * ten va duong dan
										// */
										// if (idAttachFile > 0) {
										//
										// // TODO cho getFileName co the
										// // van
										// // trung can xem lai
										// String sFileName = FileManager
										// .getFileName();
										//
										// String sFilePath = FileManager
										// .getSaveFilePath(
										// String.valueOf(this.itemData
										// .getConstructId()),
										// sFileName);
										//
										// FileManager
										// .coppyFile(
										// addItemCause
										// .getNewAttachFile()
										// .getFile_Path(),
										// sFilePath);
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
										// /*
										// * Khong ton tai file thi lai them
										// * moi
										// */
										// else {
										// this.supvConstrAttachFileController
										// .coppyAndAddAttachFile(
										// this.itemData,
										// addItemCause
										// .getNewAttachFile()
										// .getFile_Path(),
										// addItemCause
										// .getCause_Line_Bg_Id(),
										// Cause_Line_Hg_MxField.TABLE_NAME);
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
									Cause_Line_Hg_MxEntity addCauseItem = new Cause_Line_Hg_MxEntity();
									addCauseItem
											.setCat_Cause_Constr_Unqualify_Id(addItemCause
													.getCat_Cause_Constr_Unqualify_Id());
									addCauseItem
											.setSupervision_Line_Bg_MX_Id(curItemData
													.getIdMX());
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
													Cause_Line_Hg_MxField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setCause_Line_Bg_Mx_Id(iAddCause);
									this.causeLineHGMXController
											.addItem(addCauseItem);
									// Luu anh nguyen nhan loi neu co
									// if
									// (!StringUtil.isNullOrEmpty(addItemCause
									// .getNewAttachFile().getFile_Path())) {
									// this.supvConstrAttachFileController
									// .coppyAndAddAttachFile(
									// this.itemData,
									// addItemCause
									// .getNewAttachFile()
									// .getFile_Path(),
									// iAddCause,
									// Cause_Line_Hg_MxField.TABLE_NAME);
									// }
									for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
											.getLstNewAttachFile()) {
										if (!StringUtil.isNullOrEmpty(itemFile
												.getFile_Path())) {
											this.supvConstrAttachFileController
													.coppyAndAddAttachFile(
															this.itemData,
															itemFile.getFile_Path(),
															iAddCause,
															Cause_Line_Hg_MxField.TABLE_NAME);

										}
									}
								}
							}
						}
					}
				}
			} else {
				if (tabHost.getCurrentTab() == 1) {
					/* cap nhat do kiem soi */
					this.supervision_Line_HangData
							.setMeasure_Status(this.iStatus);
					this.supervision_Line_HangData
							.setMeasure_Person(this.edt_supervision_line_hg_mx_nguoido
									.getText().toString().trim());
					this.supervision_Line_HangData
							.setMeasure_Person_Mobile(this.edt_supervision_line_hg_mx_sdt
									.getText().toString().trim());
					this.supervision_Line_HangData
							.setMeasure_Group(this.edt_supervision_line_hg_mx_donvi
									.getText().toString().trim());
					this.supervision_Line_HangData
							.setMeasure_Machine_Type(this.edt_supervision_line_hg_mx_maydo
									.getText().toString().trim());
					this.supervision_Line_HangData
							.setMeasure_Machine_Serial(this.edt_supervision_line_hg_mx_serialmay
									.getText().toString().trim());

					supervision_Line_HangData
							.setSync_Status(Constants.SYNC_STATUS.ADD);
					this.supervision_Line_HangController
							.updateMx(supervision_Line_HangData);
					for (Supervision_Line_Hg_FiberEntity itemFiber : this.listLineHGFiber) {
						/* Them moi */
						if (itemFiber.isNew()) {

							long idFiber = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Supervision_Line_Hg_FiberField.TABLE_NAME);

							if (idFiber == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							itemFiber.setSupervision_Line_Hg_Fiber_Id(idFiber);
							itemFiber.setIsActive(Constants.ISACTIVE.ACTIVE);
							itemFiber.setSync_Status(Constants.SYNC_STATUS.ADD);
							itemFiber.setProcessId(0);
							itemFiber.setEmployeeId(idUser);
							itemFiber
									.setSupervisionConstrId(supervision_Line_HangData
											.getSupervision_Constr_Id());
							this.supervisionLineFiberController
									.addItem(itemFiber);

						}
						/* edit */
						else {
							itemFiber.setSync_Status(Constants.SYNC_STATUS.ADD);
							this.supervisionLineFiberController
									.updateAllColumn(itemFiber);
						}
					}
				}
			}
			
			//cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(itemData.getSupervision_Constr_Id());
			
			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			bundleData = this.getIntent().getExtras();
			boolean bDeny = checkCauseDenyHangLine(bundleData);
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
			// this.showDialog(StringUtil.getString(R.string.text_update_error));
		}
	}

	public boolean checkDoKiem() {

		/* vailid du lieu do kiem */
		String sReslut = "";
		if (this.iStatus == Constants.SEARCH_VALUE_DEFAULT) {
			sReslut = StringUtil.getString(R.string.line_hg_mx_fiber_status);
			tv_supervision_line_hg_mx_dropdown_trangthai.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			tv_supervision_line_hg_mx_dropdown_trangthai.requestFocus();
			tabHost.setCurrentTab(1);
			return true;
		}
		if (StringUtil.isNullOrEmpty(this.edt_supervision_line_hg_mx_nguoido
				.getText().toString())) {
			tabHost.setCurrentTab(1);
			edt_supervision_line_hg_mx_nguoido.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			edt_supervision_line_hg_mx_nguoido.requestFocus();

			sReslut = StringUtil.getString(R.string.line_hg_mx_fiber_nd);
			Toast.makeText(getApplicationContext(), sReslut, Toast.LENGTH_LONG)
					.show();
			// sReslut = StringUtil.getString(R.string.line_hg_mx_fiber_nd);
			return true;
		}
		if (StringUtil.isNullOrEmpty(this.edt_supervision_line_hg_mx_donvi
				.getText().toString())) {
			edt_supervision_line_hg_mx_donvi.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			edt_supervision_line_hg_mx_donvi.requestFocus();
			tabHost.setCurrentTab(1);
			sReslut = StringUtil.getString(R.string.line_hg_mx_donvi_dv);
			Toast.makeText(getApplicationContext(), sReslut, Toast.LENGTH_LONG)
					.show();
			// sReslut = StringUtil.getString(R.string.line_hg_mx_fiber_dv);
			return true;
		}

		if (!StringUtil.isNullOrEmpty(edt_supervision_line_hg_mx_sdt.getText()
				.toString())) {

			if (!isValidPhoneNumber(edt_supervision_line_hg_mx_sdt.getText()
					.toString())) {
				edt_supervision_line_hg_mx_sdt.setError(Html
						.fromHtml("<font color='green'>"
								+ getString(R.string.fomat_is_wrong)
								+ "</font>"));
				tabHost.setCurrentTab(1);
				edt_supervision_line_hg_mx_sdt.requestFocus();
				Toast.makeText(
						getApplicationContext(),
						StringUtil
								.getString(R.string.supervision_bts_measure_tl_row2_sdt_is_null),
						Toast.LENGTH_LONG).show();

				return true;
			}
		}
		return false;
	}

	private String checkVailid() {
		String sReslut = "";
		for (Supervision_Line_HG_MXGSEntity itemMX : this.listLineHGMX) {
			sReslut = this.checkVailidMXItem(itemMX);
			if (!StringUtil.isNullOrEmpty(sReslut)) {
				return sReslut;
			}
		}

		return sReslut;
	}

	private String checkVailidMXItem(Supervision_Line_HG_MXGSEntity itemCheck) {
		String sReslut = "";
		try {
//			int countNnkdCheck = 0;
//
//			for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
//				if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
//					countNnkdCheck++;
//					break;
//				}
//			}
			if (StringUtil.isNullOrEmpty(itemCheck.getMxName())) {
				sReslut = StringUtil
						.getString(R.string.line_hg_mx_name_message);
			}

//			else if (itemCheck.getLatItude() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
//				sReslut = StringUtil
//						.getString(R.string.line_hg_mx_vailid_message);
//			} else if (itemCheck.getLongItude() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
//				sReslut = StringUtil
//						.getString(R.string.line_hg_mx_vailid_message);
//			} 
//			else if (countNnkdCheck < 1) {
//				sReslut = StringUtil
//						.getString(R.string.line_hg_mx_vailid_message);
//			} 
			else if (itemCheck.getLatItude().equals(
					Constants.ID_DOUBLE_ENTITY_DEFAULT)) {
				sReslut = StringUtil
						.getString(R.string.constr_line_tank_location);

			} else if (itemCheck.getLongItude().equals(
					Constants.ID_DOUBLE_ENTITY_DEFAULT)) {
				sReslut = StringUtil
						.getString(R.string.constr_line_tank_location);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	public String checkFiber() {
		String sReslut = "";
		for (Supervision_Line_Hg_FiberEntity itemFiber : this.listLineHGFiber) {
			sReslut = this.checkVailidFiberItem(itemFiber);
			if (!StringUtil.isNullOrEmpty(sReslut)) {
				return sReslut;
			}
		}

		return sReslut;
	}

	public static final boolean isValidPhoneNumber(CharSequence target) {
		if (target == null || TextUtils.isEmpty(target)) {
			return false;
		} else {
			return android.util.Patterns.PHONE.matcher(target).matches();
		}
	}

	private String checkVailidFiberItem(
			Supervision_Line_Hg_FiberEntity itemCheck) {
		String sReslut = "";
		try {
			if (itemCheck.getMeasure_Value_Db() == -1f) {
				sReslut = StringUtil
						.getString(R.string.line_hg_mx_vailid_fiber);
				sReslut += itemCheck.getFiber_Name();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	/* Xoa mang xong sau khi confirm */
	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		if (this.curEditItem.getIdMX() > 0) {
			this.listLineHGMXDelete.add(curEditItem);
		}
		this.listLineHGMX.remove(this.curEditItem);
		this.supervisionLineHGMXAdapter.notifyDataSetChanged();
	}

	/**
	 * Ham tao header cua tab
	 * 
	 * @param text
	 * @return
	 */
	private TextView makeTabIndicator(String text) {
		TextView tabView = new TextView(this);
		LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				tabHeight, 1);
		lp3.setMargins(1, 0, 1, 0);
		tabView.setLayoutParams(lp3);
		tabView.setWidth(tabWidth);
		tabView.setText(text);
		tabView.setTextColor(getResources().getColor(R.color.white_color));
		tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		tabView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tab_indicator));
		tabView.setPadding(13, 0, 13, 0);
		return tabView;
	}

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : this.listMXUnqualifyItem) {
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listMXUnqualifyItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
			if (curItem == null) {
				curCauseUniqualify.setUnqulify(false);
				curCauseUniqualify
						.setAttachFile(new Supv_Constr_Attach_FileEntity());
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
//				curCauseUniqualify.setAttachFile(curItem.getAttachFile());
				curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
//				curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
				curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
				curCauseUniqualify.setLstNewAttachFile(curItem
						.getLstNewAttachFile());
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

	/**
	 * Ham set gia tri soi cho giam sat
	 * 
	 * @param iNumber
	 *            so soi
	 */
	private void getFirstFiberList(int iNumber) {
		for (int i = 0; i < iNumber; i++) {
			Supervision_Line_Hg_FiberEntity addItem = new Supervision_Line_Hg_FiberEntity();
			addItem.setStt(i + 1);
			addItem.setSupervision_Line_Hang_Id(this.supervision_Line_HangData
					.getSupervision_Line_Hang_Id());
			addItem.setFiber_Name(GloablUtils.getFiberName(i + 1));
			addItem.setNew(true);
			addItem.setEdit(true);
			listLineHGFiber.add(addItem);
		}
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
			// refresh lai du lieu
			this.supervision_Line_HangData = supervision_Line_HangController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());
			refreshData();
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
			saveDataMX();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			

			if (outOfKey) {
				Toast.makeText(Supervision_Line_HG_MXActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshData();
			
			Toast.makeText(Supervision_Line_HG_MXActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}