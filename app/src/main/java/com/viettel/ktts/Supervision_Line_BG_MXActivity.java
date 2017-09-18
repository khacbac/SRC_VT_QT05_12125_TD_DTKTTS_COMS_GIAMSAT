package com.viettel.ktts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
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
import com.viettel.database.Cause_Line_BG_MXController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_FiberController;
import com.viettel.database.Supervision_Line_BG_MxController;
import com.viettel.database.Supervision_Line_BackgroundController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Cause_Line_BG_MXEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_FiberEntity;
import com.viettel.database.entity.Supervision_Line_BG_MXEntity;
import com.viettel.database.entity.Supervision_Line_BG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Cause_Line_BG_MXField;
import com.viettel.database.field.Supervision_Line_BG_FiberField;
import com.viettel.database.field.Supervision_Line_BG_MXField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_BG_FiberAdapter;
import com.viettel.view.control.Supervision_Line_BG_MxAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 *
 */
public class Supervision_Line_BG_MXActivity extends LineBaseActivity {
	private static final String TAG = Supervision_Line_BG_MXActivity.class.getSimpleName();
	private View spvLineBG_MXView;
	private TabHost tabHost;
	private int tabHeight = 40;
	private int tabWidth = 150;
	private int iField = -1;
	/* 1 la nhap mang xong, 2 la nhap soi */
	private int iTypeField = -1;
	/* controll */
	private TextView tv_supervision_line_bg_mx_dropdown;
	private TextView tv_supervision_line_bg_mx_info_line_station_code_value;
	private TextView tv_supervision_line_bg_mx_info_line_value;
	private TextView tv_supervision_line_bg_mx_dropdown_trangthai;
	private EditText edt_supervision_line_bg_mx_nguoido;
	private EditText edt_supervision_line_bg_mx_maydo;
	private EditText edt_supervision_line_bg_mx_donvi;
	private EditText edt_supervision_line_bg_mx_sdt;
	private EditText edt_supervision_line_bg_mx_serialmay;
	private Button btn_line_bg_mx_save;
	private Button btn_line_bg_mx_add;
	private ListView lv_line_bg_mx_list;
	private ListView lv_line_bg_mx_fiber_list;
	/* bien dropdown */
	private int iDesignInfo = Constants.SEARCH_VALUE_DEFAULT;
	private int iStatus = Constants.SEARCH_VALUE_DEFAULT;
	private boolean outOfKey = false;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private List<DropdownItemEntity> listStatus = null;
	private Menu_DropdownPopup dropdownPopupMenuStatus;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	
	private Bundle bundleData;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_ConstrEntity supervision_ConstrData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private Supervision_ConstrController supervision_ConstrController;
	private Supervision_Line_BackgroundController supervision_Line_BackgroundController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listMXUnqualifyItem;
	private List<Supervision_Line_BG_MXGSEntity> listLineBGMX;
	private List<Supervision_Line_BG_MXGSEntity> listDelLineBGMX;
	private List<Supervision_Line_BG_FiberEntity> listLineBGFiber;
	private Supervision_Line_BG_MxController supervisionLineBGMXController;
	private Supervision_Line_BG_FiberController supervisionLineFiberController;
	private Cause_Line_BG_MXController causeLineBGMXController;
	private Supervision_Line_BG_MxAdapter supervisionLineBGMXAdapter;
	private Supervision_Line_BG_FiberAdapter supervisionLineBGFiberAdapter;
	/* Item ong cap dang sua dung popup */
	private Supervision_Line_BG_MXGSEntity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_Line_BG_FiberEntity curEditFiberItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_mx_activity);
		setTitle(getString(R.string.line_background_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		initTabHost();
		setData();
	}

	private void initView() {
		spvLineBG_MXView = addView(R.layout.supervision_line_bg_mx_activity, R.id.rl_login);
		this.tv_supervision_line_bg_mx_dropdown = (TextView) spvLineBG_MXView
				.findViewById(R.id.tv_supervision_line_bg_mx_dropdown);
		this.tv_supervision_line_bg_mx_dropdown.setOnClickListener(this);

		this.tv_supervision_line_bg_mx_info_line_station_code_value = (TextView) spvLineBG_MXView
				.findViewById(R.id.tv_supervision_line_bg_mx_info_line_station_code_value);

		this.tv_supervision_line_bg_mx_info_line_value = (TextView) spvLineBG_MXView
				.findViewById(R.id.tv_supervision_line_bg_mx_info_line_value);

		this.tv_supervision_line_bg_mx_dropdown_trangthai = (TextView) spvLineBG_MXView
				.findViewById(R.id.tv_supervision_line_bg_mx_dropdown_trangthai);
		this.tv_supervision_line_bg_mx_dropdown_trangthai
				.setOnClickListener(this);

		this.edt_supervision_line_bg_mx_nguoido = (EditText) spvLineBG_MXView
				.findViewById(R.id.edt_supervision_line_bg_mx_nguoido);

		this.edt_supervision_line_bg_mx_maydo = (EditText) spvLineBG_MXView
				.findViewById(R.id.edt_supervision_line_bg_mx_maydo);

		this.edt_supervision_line_bg_mx_donvi = (EditText) spvLineBG_MXView
				.findViewById(R.id.edt_supervision_line_bg_mx_donvi);

		this.edt_supervision_line_bg_mx_sdt = (EditText) spvLineBG_MXView
				.findViewById(R.id.edt_supervision_line_bg_mx_sdt);

		this.edt_supervision_line_bg_mx_serialmay = (EditText) spvLineBG_MXView
				.findViewById(R.id.edt_supervision_line_bg_mx_serialmay);

		this.btn_line_bg_mx_save = (Button) spvLineBG_MXView
				.findViewById(R.id.btn_line_bg_mx_save);
		this.btn_line_bg_mx_save.setOnClickListener(this);

		this.btn_line_bg_mx_add = (Button) spvLineBG_MXView
				.findViewById(R.id.btn_line_bg_mx_add);
		this.btn_line_bg_mx_add.setOnClickListener(this);

		this.lv_line_bg_mx_list = (ListView) spvLineBG_MXView
				.findViewById(R.id.lv_line_bg_mx_list);
		this.lv_line_bg_mx_fiber_list = (ListView) spvLineBG_MXView
				.findViewById(R.id.lv_line_bg_mx_fiber_list);
		lv_line_bg_mx_fiber_list
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
		tabHost = (TabHost) spvLineBG_MXView.findViewById(R.id.tabHost);
		tabHost.setup();
		String sMXKD = getString(R.string.line_bg_mx_mangxongkhongdat);
		TabSpec specTank = tabHost.newTabSpec(sMXKD);
		specTank.setContent(R.id.ll_line_bg_mx_mxkhongdat);
		specTank.setIndicator(makeTabIndicator(sMXKD));

		String sDSK = getString(R.string.line_bg_mx_doancap_dokiemsoi);
		TabSpec specCable = tabHost.newTabSpec(sDSK);
		specCable.setContent(R.id.ll_supervision_line_bg_mx_dokiem);
		specCable.setIndicator(makeTabIndicator(sDSK));
		tabHost.addTab(specTank);
		tabHost.addTab(specCable);
		/* Chi hien thi them moi mang xong o tab mang xong */
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				int i = tabHost.getCurrentTab();
				if (i == 0) {
					btn_line_bg_mx_add.setVisibility(View.VISIBLE);
				} else {
					btn_line_bg_mx_add.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_supervision_line_bg_mx_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_supervision_line_bg_mx_dropdown_trangthai:
			this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this,
					this.listStatus);
			dropdownPopupMenuStatus.show(v);
			break;
		case R.id.btn_line_bg_mx_add:
			//  kiem tra vuot so luong
			if(this.listLineBGMX.size() == supervision_Line_BackgroundData.getMx_Total()){
				this.showDialog(StringUtil.getString(R.string.line_mx_enough));
				return;
			}
			Supervision_Line_BG_MXGSEntity addItem = new Supervision_Line_BG_MXGSEntity();
			this.listLineBGMX.add(addItem);
			this.supervisionLineBGMXAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_line_bg_mx_save:

			String messageError = "";
			if (tabHost.getCurrentTab() == 0) {
				messageError = this.checkVailidMx();
				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);
				} else {
					ConfirmDialog confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			} else {
				if (tabHost.getCurrentTab() == 1) {
					messageError = this.checkVailidFiber();
					if (!StringUtil.isNullOrEmpty(messageError)) {
						this.showDialog(messageError);
					} else {
						if (!checkDoKiem()) {
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
			// ConfirmDialog confirmSave = new ConfirmDialog(this,
			// StringUtil.getString(R.string.text_confirm_save));
			// confirmSave.show();
			// }
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
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_STATUS)) {
				this.iStatus = dropdownItem.getId();
				if (this.iStatus > 0) {
					tv_supervision_line_bg_mx_dropdown_trangthai.setError(null);
				}

				this.tv_supervision_line_bg_mx_dropdown_trangthai
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuStatus.dismiss();
			}
			break;
		/* chon chinh sua gird view */
		case OnEventControlListener.EVENT_SUPERVISION_MX_EDIT:
			this.curEditItem = (Supervision_Line_BG_MXGSEntity) data;
			this.iField = this.curEditItem.getIdField();
			this.iTypeField = 1;
			switch (iField) {
			case Constants.BG_MX_EDIT.NAME:
				String sFromDistanceTextValue = "";
				sFromDistanceTextValue = String.valueOf(this.curEditItem
						.getMxName());

				editTextPopup = new Edit_Text_Popup(this, null,
						sFromDistanceTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, false, 4);
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
						.getString(R.string.line_bg_pipe_delete_message));
				break;
			default:
				break;
			}
			break;
		/* chon nghi nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			this.supervisionLineBGMXAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;
			if (iTypeField == 1) {
				switch (this.iField) {
				case Constants.BG_MX_EDIT.NAME:
					this.curEditItem.setMxName(sSetTextValue);
					break;
				case Constants.BG_MX_EDIT.FAIL_DESC:
					this.curEditItem.setFailDesc(sSetTextValue);
					break;

				default:
					break;
				}
				this.curEditItem.setEdited(true);
				this.supervisionLineBGMXAdapter.notifyDataSetChanged();
			} else if (iTypeField == 2) {
				if (!StringUtil.isNullOrEmpty(sSetTextValue)) {
					this.curEditFiberItem.setMeasure_Value_Db(Float
							.parseFloat(sSetTextValue));
					this.supervisionLineBGFiberAdapter.notifyDataSetChanged();
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
				Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
				curAttachFile
						.setSupv_Constr_Attach_file_Id(imageEntity.getId());
				curAttachFile.setFile_Path(imageEntity.getUrl());
				this.curUnqualifyItem.getLstNewAttachFile().add(curAttachFile);

			}
			this.imgViewPopup.hidePopup();
			this.contruoctionUnqualifyPopup.refreshData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(itemData);
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
		case OnEventControlListener.EVENT_SUPERVISION_FIBER_EDIT:
			this.curEditFiberItem = (Supervision_Line_BG_FiberEntity) data;
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
			this.curEditItem = (Supervision_Line_BG_MXGSEntity) data;
			String sMxName = this.curEditItem.getMxName();
			if (StringUtil.isNullOrEmpty(sMxName)) {
				sMxName = "";
			}
			this.getLocation(this.curEditItem.getLongItude(),
					this.curEditItem.getLatItude(),
					StringUtil.getString(R.string.line_bg_mx_doancap_tenmx),
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
		final Header myActionBar = (Header) spvLineBG_MXView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_MXActivity.this);
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

			this.supervision_ConstrController = new Supervision_ConstrController(
					this);
			this.supervision_Line_BackgroundController = new Supervision_Line_BackgroundController(
					this);
			this.causeLineBGMXController = new Cause_Line_BG_MXController(this);

			supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
					this);

			this.itemData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);

			this.supervision_ConstrData = this.supervision_ConstrController
					.getItem(this.itemData.getSupervision_Constr_Id());
			this.supervision_Line_BackgroundData = supervision_Line_BackgroundController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());
			this.tv_supervision_line_bg_mx_info_line_value
					.setText(this.itemData.getConstrCode());
			this.iStatus = this.supervision_Line_BackgroundData
					.getMeasure_Status();
			this.tv_supervision_line_bg_mx_dropdown_trangthai
					.setText(GloablUtils
							.getStringConstructionStatus(this.iStatus));
			this.edt_supervision_line_bg_mx_nguoido
					.setText(this.supervision_Line_BackgroundData
							.getMeasure_Person());
			this.edt_supervision_line_bg_mx_maydo
					.setText(this.supervision_Line_BackgroundData
							.getMeasure_Machine_Type());
			this.edt_supervision_line_bg_mx_donvi
					.setText(this.supervision_Line_BackgroundData
							.getMeasure_Group());
			this.edt_supervision_line_bg_mx_sdt
					.setText(this.supervision_Line_BackgroundData
							.getMeasure_Person_Mobile());
			this.edt_supervision_line_bg_mx_serialmay
					.setText(this.supervision_Line_BackgroundData
							.getMeasure_Machine_Serial());
			this.tv_supervision_line_bg_mx_info_line_station_code_value
					.setText(itemData.getStationCode());
			/* Man xong list */
			this.supervisionLineBGMXController = new Supervision_Line_BG_MxController(
					this);

			this.supervisionLineFiberController = new Supervision_Line_BG_FiberController(
					this);
			this.listDelLineBGMX = new ArrayList<Supervision_Line_BG_MXGSEntity>();

			this.listLineBGMX = this.supervisionLineBGMXController
					.getAllMXGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
			for (Supervision_Line_BG_MXGSEntity curItemSupervisonMX : listLineBGMX) {
				List<Supervision_LBG_UnqualifyItemEntity> curListUnqualifyItem = this.causeLineBGMXController
						.getAllMxUnqulifyByMxId(curItemSupervisonMX.getIdMX());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curListUnqualifyItem) {
//					Supv_Constr_Attach_FileEntity curAttachFile = this.supvConstrAttachFileController
//							.getAttachFile(Cause_Line_BG_MXField.TABLE_NAME,
//									curUnqualifyItem.getCause_Line_Bg_Id());
//					curUnqualifyItem.setAttachFile(curAttachFile);
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Line_BG_MXField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				curItemSupervisonMX
						.setListCauseUniQualify(curListUnqualifyItem);
			}

			this.listLineBGFiber = this.supervisionLineFiberController
					.getAllFiberBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
			if (this.listLineBGFiber.size() == 0) {
				this.getFirstFiberList(this.supervision_Line_BackgroundData
						.getCable_type());
			} else {
				// this.listLineBGFiber.addAll(curListLineBGFiber);
				int sizeFiber = listLineBGFiber.size();

				if (listLineBGFiber.size() < (supervision_Line_BackgroundData
						.getCable_type())) {
					for (int i = 0; i < (supervision_Line_BackgroundData
							.getCable_type() - sizeFiber); i++) {
						Supervision_Line_BG_FiberEntity fiberItem = new Supervision_Line_BG_FiberEntity();

						fiberItem
								.setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
										.getSupervision_Line_Background_Id());
						fiberItem.setFiber_Name(GloablUtils
								.getFiberName(sizeFiber + i + 1));
						fiberItem.setNew(true);
						fiberItem.setEdit(true);
						listLineBGFiber.add(fiberItem);
					}

				}
			}

			this.supervisionLineBGMXAdapter = new Supervision_Line_BG_MxAdapter(
					this, listLineBGMX);
			this.supervisionLineBGFiberAdapter = new Supervision_Line_BG_FiberAdapter(
					this, listLineBGFiber);

			this.lv_line_bg_mx_list.setAdapter(this.supervisionLineBGMXAdapter);
			this.lv_line_bg_mx_fiber_list
					.setAdapter(this.supervisionLineBGFiberAdapter);
			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");
			this.listStatus = GloablUtils.getListConstructionStatus("");

			this.tv_supervision_line_bg_mx_dropdown
					.setText(GloablUtils
							.getStringLineBackgroundInfo(Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO));
			listMXUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
					.getAllUnQualifyItemByName(Constants.UNQUALIFY_TYPE.BG_MX,
							Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);
			/* Set endable va disable voi cong trinh da hoan thanh */
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_bg_mx_save.setVisibility(View.GONE);
				this.btn_line_bg_mx_add.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			// handle exception
		}

	}

	private void refreshData() {
		List<Supervision_Line_BG_MXGSEntity> curListLineBGMX = this.supervisionLineBGMXController
				.getAllMXGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		for (Supervision_Line_BG_MXGSEntity curItemSupervisonMX : curListLineBGMX) {
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualifyItem = this.causeLineBGMXController
					.getAllMxUnqulifyByMxId(curItemSupervisonMX.getIdMX());
			/* Gan anh nguyen nhan loi */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curListUnqualifyItem) {
				// Supv_Constr_Attach_FileEntity curAttachFile =
				// this.supvConstrAttachFileController
				// .getAttachFile(Cause_Line_BG_MXField.TABLE_NAME,
				// curUnqualifyItem.getCause_Line_Bg_Id());
				// curUnqualifyItem.setAttachFile(curAttachFile);
				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(Cause_Line_BG_MXField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}
			}
			curItemSupervisonMX.setListCauseUniQualify(curListUnqualifyItem);
		}
		this.listLineBGMX.clear();
		this.listLineBGMX.addAll(curListLineBGMX);
		this.supervisionLineBGMXAdapter.notifyDataSetChanged();
		// this.supervisionLineBGMXAdapter = new Supervision_Line_BG_MxAdapter(
		// this, listLineBGMX);

		this.listLineBGFiber.clear();
		List<Supervision_Line_BG_FiberEntity> curListLineBGFiber = this.supervisionLineFiberController
				.getAllFiberBySupervistionLineBackground(this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		if (curListLineBGFiber.size() == 0) {
			this.getFirstFiberList(this.supervision_Line_BackgroundData
					.getCable_type());
		} else {
			this.listLineBGFiber.addAll(curListLineBGFiber);

			if (listLineBGFiber.size() < (supervision_Line_BackgroundData
					.getCable_type())) {
				for (int i = 0; i < (supervision_Line_BackgroundData
						.getCable_type() - listLineBGFiber.size()); i++) {
					Supervision_Line_BG_FiberEntity fiberItem = new Supervision_Line_BG_FiberEntity();
					listLineBGFiber.add(fiberItem);
				}

			}
		}

		this.supervisionLineBGFiberAdapter.notifyDataSetChanged();
		// this.supervisionLineBGFiberAdapter = new
		// Supervision_Line_BG_FiberAdapter(
		// this, listLineBGFiber);
		//
		//
		// this.lv_line_bg_mx_list.setAdapter(this.supervisionLineBGMXAdapter);
		// this.lv_line_bg_mx_fiber_list
		// .setAdapter(this.supervisionLineBGFiberAdapter);
	}

	private void saveDataMX() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();

			if (tabHost.getCurrentTab() == 0) {
				/* Bat dau luu thong tin */
				for (Supervision_Line_BG_MXGSEntity curItemData : listLineBGMX) {
					/* Them moi */
					if (curItemData.isNew()) {
						Supervision_Line_BG_MXEntity addItem = new Supervision_Line_BG_MXEntity();
						addItem.setMxName(curItemData.getMxName());
						addItem.setFail_Desc(curItemData.getFailDesc());
						addItem.setLatItude(curItemData.getLatItude());
						addItem.setLongItude(curItemData.getLongItude());
						curItemData.setStatus(Constants.MX_STATUS.KHONG_DAT);
						addItem.setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
								.getSupervision_Line_Background_Id());
						addItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addItem.setProcessId(0);
						addItem.setEmployeeId(idUser);
						addItem.setSupervisionConstrId(supervision_Line_BackgroundData
								.getSupervisionConstrId());

						long idAddMx = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Supervision_Line_BG_MXField.TABLE_NAME);

						if (idAddMx == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;
						addItem.setSupervision_Line_Bg_Mx_Id(idAddMx);

						supervisionLineBGMXController.addItem(addItem);
						/* Add nguyen nhan khong dat */
						// if (addItem.getStatus() ==
						// Constants.MX_STATUS.KHONG_DAT) {
						List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemData
								.getListCauseUniQualify();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
							Cause_Line_BG_MXEntity addCauseItem = new Cause_Line_BG_MXEntity();
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
									.setSupervisionConstrId(supervision_Line_BackgroundData
											.getSupervisionConstrId());
							long iAddCause = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Line_BG_MXField.TABLE_NAME);

							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem.setCause_Line_Bg_Mx_Id(iAddCause);
							this.causeLineBGMXController.addItem(addCauseItem);
							// Neu chon moi anh thi ghi file moi vao
							// if (!StringUtil.isNullOrEmpty(curItemUnqualify
							// .getNewAttachFile().getFile_Path())) {
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.itemData,
							// curItemUnqualify
							// .getNewAttachFile()
							// .getFile_Path(),
							// iAddCause,
							// Cause_Line_BG_MXField.TABLE_NAME);
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
													Cause_Line_BG_MXField.TABLE_NAME);

								}
							}
						}
						// }

					}
					/* Cap nhat chinh sua */
					else {
						/* 1.Thong tin be co chinh sua */
						if (curItemData.isEdited()) {
							curItemData
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							this.supervisionLineBGMXController
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
										this.causeLineBGMXController.delete(
												addItemCause
														.getCause_Line_Bg_Id(),
												addItemCause.getSync_Status());
									}
									/* 1.2. Update lai nguyen nhan khong dat */
									else {
										
										
										if (addItemCause.getLstNewAttachFile()
												.size() > 0 || (addItemCause.getLstNewAttachFile().size() == 0
														&& addItemCause.getLstAttachFile().size() == 0)) {
											
											List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
													.getLstAttachFile(
															Cause_Line_BG_MXField.TABLE_NAME,
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
																Cause_Line_BG_MXField.TABLE_NAME);

											}
										}
										// /* 1.2.1 Thay doi anh */
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
										// //cho getFileName co the
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
										// Cause_Line_BG_MXField.TABLE_NAME);
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
									Cause_Line_BG_MXEntity addCauseItem = new Cause_Line_BG_MXEntity();
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
											.setSupervisionConstrId(supervision_Line_BackgroundData
													.getSupervisionConstrId());
									long iAddCause = Ktts_KeyController
											.getInstance()
											.getKttsNextKey(
													Cause_Line_BG_MXField.TABLE_NAME);

									if (iAddCause == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									addCauseItem
											.setCause_Line_Bg_Mx_Id(iAddCause);
									this.causeLineBGMXController
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
									// Cause_Line_BG_MXField.TABLE_NAME);
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
															Cause_Line_BG_MXField.TABLE_NAME);

										}
									}
								}
							}
						}
					}
				}
				/* Xoa mang xong da ton tai */
				for (Supervision_Line_BG_MXGSEntity curDelItem : this.listDelLineBGMX) {
					// co can xoa luon nguyen nhan khong dat va anh khong?
					this.supervisionLineBGMXController.delete(
							curDelItem.getIdMX(), curDelItem.getSync_Status());
				}
				this.listDelLineBGMX.clear();
			} else {
				if (tabHost.getCurrentTab() == 1) {
					/* cap nhat do kiem soi */
					this.supervision_Line_BackgroundData
							.setMeasure_Status(this.iStatus);
					this.supervision_Line_BackgroundData
							.setMeasure_Person(this.edt_supervision_line_bg_mx_nguoido
									.getText().toString());
					this.supervision_Line_BackgroundData
							.setMeasure_Person_Mobile(this.edt_supervision_line_bg_mx_sdt
									.getText().toString());
					this.supervision_Line_BackgroundData
							.setMeasure_Group(this.edt_supervision_line_bg_mx_donvi
									.getText().toString());
					this.supervision_Line_BackgroundData
							.setMeasure_Machine_Type(this.edt_supervision_line_bg_mx_maydo
									.getText().toString());
					this.supervision_Line_BackgroundData
							.setMeasure_Machine_Serial(this.edt_supervision_line_bg_mx_serialmay
									.getText().toString());

					supervision_Line_BackgroundData
							.setSync_Status(Constants.SYNC_STATUS.ADD);
					this.supervision_Line_BackgroundController
							.updateMx(supervision_Line_BackgroundData);
					for (Supervision_Line_BG_FiberEntity itemFiber : this.listLineBGFiber) {
						/* Them moi */
						if (itemFiber.isNew()) {

							itemFiber.setSync_Status(Constants.SYNC_STATUS.ADD);
							itemFiber.setIsActive(Constants.ISACTIVE.ACTIVE);
							itemFiber.setProcessId(0);
							itemFiber.setEmployeeId(idUser);
							itemFiber
									.setSupervisionConstrId(supervision_Line_BackgroundData
											.getSupervision_Constr_Id());
							long idFiber = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Supervision_Line_BG_FiberField.TABLE_NAME);
							itemFiber.setSupervision_Line_Bg_Fiber_Id(idFiber);
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
			// Toast.makeText(this,
			// StringUtil.getString(R.string.text_update_error),
			// Toast.LENGTH_SHORT).show();
		}
	}

	public boolean checkDoKiem() {
		/* vailid du lieu do kiem */
		if (this.iStatus == Constants.SEARCH_VALUE_DEFAULT) {
			// sReslut = StringUtil.getString(R.string.line_bg_mx_fiber_status);
			tabHost.setCurrentTab(1);
			tv_supervision_line_bg_mx_dropdown_trangthai.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));

			tv_supervision_line_bg_mx_dropdown_trangthai.requestFocus();
			return true;
		}
		if (StringUtil.isNullOrEmpty(this.edt_supervision_line_bg_mx_nguoido
				.getText().toString())) {
			// sReslut = StringUtil.getString(R.string.line_bg_mx_fiber_nd);
			tabHost.setCurrentTab(1);
			edt_supervision_line_bg_mx_nguoido.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			edt_supervision_line_bg_mx_nguoido.requestFocus();
			return true;
		}
		// if (StringUtil.isNullOrEmpty(this.edt_supervision_line_bg_mx_maydo
		// .getText().toString())) {
		// //sReslut = StringUtil.getString(R.string.line_bg_mx_fiber_md);
		// tabHost.setCurrentTab(1);
		// edt_supervision_line_bg_mx_maydo.setError(Html
		// .fromHtml("<font color='green'>"
		// + getString(R.string.field_is_null) + "</font>"));
		// edt_supervision_line_bg_mx_maydo.requestFocus();
		// return true;
		// }
		if (StringUtil.isNullOrEmpty(this.edt_supervision_line_bg_mx_donvi
				.getText().toString())) {
			// sReslut = StringUtil.getString(R.string.line_bg_mx_fiber_dv);
			tabHost.setCurrentTab(1);
			edt_supervision_line_bg_mx_donvi.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			edt_supervision_line_bg_mx_donvi.requestFocus();
			return true;
		}

		if (!StringUtil.isNullOrEmpty(edt_supervision_line_bg_mx_sdt.getText()
				.toString())) {

			if (!isValidPhoneNumber(edt_supervision_line_bg_mx_sdt.getText()
					.toString())) {
				edt_supervision_line_bg_mx_sdt.setError(Html
						.fromHtml("<font color='green'>"
								+ getString(R.string.fomat_is_wrong)
								+ "</font>"));
				edt_supervision_line_bg_mx_sdt.requestFocus();
				Toast.makeText(
						getApplicationContext(),
						StringUtil
								.getString(R.string.supervision_bts_measure_tl_row2_sdt_is_null),
						Toast.LENGTH_LONG).show();

				return true;
			}
		}
		// if (StringUtil.isNullOrEmpty(this.edt_supervision_line_bg_mx_sdt
		// .getText().toString())) {
		// //sReslut = StringUtil.getString(R.string.line_bg_mx_fiber_sdt);
		// tabHost.setCurrentTab(1);
		// edt_supervision_line_bg_mx_sdt.setError(Html
		// .fromHtml("<font color='green'>"
		// + getString(R.string.field_is_null) + "</font>"));
		// edt_supervision_line_bg_mx_sdt.requestFocus();
		// return true;
		// }
		// if
		// (StringUtil.isNullOrEmpty(this.edt_supervision_line_bg_mx_serialmay
		// .getText().toString())) {
		// //sReslut =
		// StringUtil.getString(R.string.line_bg_mx_fiber_serialmay);
		// tabHost.setCurrentTab(1);
		// edt_supervision_line_bg_mx_serialmay.setError(Html
		// .fromHtml("<font color='green'>"
		// + getString(R.string.field_is_null) + "</font>"));
		// edt_supervision_line_bg_mx_serialmay.requestFocus();
		// return true;
		// }
		return false;
	}

	private String checkVailidMx() {
		String sReslut = "";
		for (Supervision_Line_BG_MXGSEntity itemMX : this.listLineBGMX) {
			sReslut = this.checkVailidMXItem(itemMX);
			if (!StringUtil.isNullOrEmpty(sReslut)) {
				return sReslut;
			}
		}

		return sReslut;
	}

	private String checkVailidFiber() {
		String sReslut = "";

		/* vailid do soi */
		for (Supervision_Line_BG_FiberEntity itemFiber : this.listLineBGFiber) {
			sReslut = this.checkVailidFiberItem(itemFiber);
			if (!StringUtil.isNullOrEmpty(sReslut)) {
				return sReslut;
			}
		}

		return sReslut;
	}

	private String checkVailidMXItem(Supervision_Line_BG_MXGSEntity itemCheck) {
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
						.getString(R.string.line_bg_mx_vailid_message);
			}
			// else if (StringUtil.isNullOrEmpty(itemCheck.getFailDesc())) {
			// sReslut = StringUtil
			// .getString(R.string.line_bg_mx_vailid_message);
			// }
//			else if (itemCheck.getLatItude() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
//				sReslut = StringUtil
//						.getString(R.string.line_bg_mx_vailid_message);
//			} else if (itemCheck.getLongItude() == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
//				sReslut = StringUtil
//						.getString(R.string.line_bg_mx_vailid_message);
//			} 
//			else 
//				if (countNnkdCheck < 1) {
//				sReslut = StringUtil
//						.getString(R.string.line_bg_mx_vailid_message);
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

	private String checkVailidFiberItem(
			Supervision_Line_BG_FiberEntity itemCheck) {
		String sReslut = "";
		try {
			if (itemCheck.getMeasure_Value_Db() == -1f) {
				sReslut = StringUtil
						.getString(R.string.line_bg_mx_vailid_fiber);
				sReslut += itemCheck.getFiber_Name();
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	/* Xoa mang xong sau khi confirm */
	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		if (this.curEditItem.getIdMX() > 0) {
			this.listDelLineBGMX.add(this.curEditItem);
		}
		this.listLineBGMX.remove(this.curEditItem);
		this.supervisionLineBGMXAdapter.notifyDataSetChanged();
	}

	/**
	 * Ham tao header cua tab
	 * 
	 * @param text
	 * @return
	 */
	private TextView makeTabIndicator(String text) {
		TelephonyManager manager = (TelephonyManager)this
				.getSystemService(Context.TELEPHONY_SERVICE);
		if(manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE){
			Log.d(TAG,"Tablet");
		}else{
			Log.d(TAG,"Mobile");
			tabHeight = 120;
		}
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
	/* 1. Tim nguyen nhan khong dat trong danh sach tat ca nguyen nhan */
	/* 2. Voi nguyen nhan da ton tai thi sua, va nguyen nhan moi thi them vao */
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
				curCauseUniqualify.setDelete(curItem.isDelete());
				curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
				curCauseUniqualify.setLstNewAttachFile(curItem
						.getLstNewAttachFile());
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
			Supervision_Line_BG_FiberEntity addItem = new Supervision_Line_BG_FiberEntity();
			addItem.setStt(i + 1);
			addItem.setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
					.getSupervision_Line_Background_Id());
			addItem.setFiber_Name(GloablUtils.getFiberName(i + 1));
			addItem.setNew(true);
			addItem.setEdit(true);
			listLineBGFiber.add(addItem);
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
			this.supervision_Line_BackgroundData = supervision_Line_BackgroundController
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
				Toast.makeText(Supervision_Line_BG_MXActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			
			refreshData();

			Toast.makeText(Supervision_Line_BG_MXActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}