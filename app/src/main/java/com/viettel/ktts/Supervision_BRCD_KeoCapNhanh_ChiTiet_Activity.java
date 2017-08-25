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
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Design_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Supervision_BRCD_Drop_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_design_Field;
import com.viettel.database.field.Supervision_BRCD_Ttb_Field;
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
public class Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity extends
		HomeBaseActivity {
	/* controll */
	private View spvBRCD_KCN_CHTView;
	private TextView tv_constr_brcd_keocapnhanh_dropdown,
			tv_constr_brcd_keocapnhanh_station_code_value,
			tv_constr_brcd_keocapnhanh_value, tv_constr_brcd_keocapnhanh_name,
			tv_chonloaicap_keocapnhanh;
	private TextView tv_loaihai_drop_keocapnhanh, tv_loaibon_drop_keocapnhanh,
			tv_loaitam_drop_keocapnhanh, tv_loaimuoihai_drop_keocapnhanh;

	private EditText ed_socotcosan_keocapnhanh, ed_socotchongmoi_keocapnhanh,
			ed_chieudai_keocapnhanh;
	private EditText tv_sotuthuebao_loaihai_drop_keocapnhanh,
			tv_sotuthuebao_loaibon_drop_keocapnhanh,
			tv_sotuthuebao_loaitam_drop_keocapnhanh,
			tv_sotuthuebao_loaimuoihai_drop_keocapnhanh;

	private Button btn_constr_brcd_luu_keocapnhanh,
			btn_constr_brcd_back_keocapnhanh;

	private Bundle bundleData;

	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listDesignLoaiCap = null;
	private List<DropdownItemEntity> listDesignInfoLoaiCapDropGo = null;
	private List<DropdownItemEntity> listDesignSoSoiDropGoLoaihai = null;

	private List<DropdownItemEntity> listDesignSoSoiDropGoLoaiBon = null;
	private List<DropdownItemEntity> listDesignSoSoiDropGoLoaiTam = null;
	private List<DropdownItemEntity> listDesignSoSoiDropGoLoaiMH = null;

	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	private Supervision_BRCD_Kcn_Design_Entity brcd_kcn_design = new Supervision_BRCD_Kcn_Design_Entity();
	private Supervision_BRCD_Kcn_Design_Controller brcd_kcn_design_Controller = new Supervision_BRCD_Kcn_Design_Controller(
			this);
	private Supervision_BRCD_Drop_Controller supervisionDROPController = new Supervision_BRCD_Drop_Controller(
			this);
	private List<Supervision_BRCD_Drop_Entity> listSupervisionDROP = new ArrayList<Supervision_BRCD_Drop_Entity>();

	private Supervision_BRCD_Ttb_Controller supervisionTTBController = new Supervision_BRCD_Ttb_Controller(
			this);
	private List<Supervision_BRCD_Ttb_Entity> listSupervisionTTB = new ArrayList<Supervision_BRCD_Ttb_Entity>();
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_BRCD_Kcn_Entity itemData_kcn;
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

	private Supervision_BRCD_Drop_Entity brcd_Drop_Entity = new Supervision_BRCD_Drop_Entity();
	private Supervision_BRCD_Ttb_Entity brcd_Ttb_Entity = new Supervision_BRCD_Ttb_Entity();
	private boolean outOfKey = false;
	private float positionTouch = 0f;

	private int sosoidrop_loaihai_old, sosoidrop_loaihai_new;
	private int sotu_loaihai_old, sotu_loaihai_new;
	private int sosoidrop_loaibon_old, sosoidrop_loaibon_new;
	private int sotu_loaibon_old, sotu_loaibon_new;
	private int sosoidrop_loaitam_old, sosoidrop_loaitam_new;
	private int sotu_loaitam_old, sotu_loaitam_new;
	private int sosoidrop_loaimh_old, sosoidrop_loaimh_new;
	private int sotu_loaimh_old, sotu_loaimh_new;
	private long supervition_branch_cable_design_id;
	private TextView supervision_bg_tank_complete_date;
	private Button rl_supervision_bg_tank_bt_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_keocapnhanh_chitiet_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_KCN_CHTView = addView(R.layout.supervision_brcd_keocapnhanh_chitiet_activity, R.id.rl_supervision_line_bg_tank);
		this.tv_constr_brcd_keocapnhanh_dropdown = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_dropdown);
		this.tv_constr_brcd_keocapnhanh_dropdown.setOnClickListener(this);

		this.tv_chonloaicap_keocapnhanh = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_chonloaicap_keocapnhanh);
		this.tv_chonloaicap_keocapnhanh.setOnClickListener(this);

		this.tv_loaihai_drop_keocapnhanh = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_loaihai_drop_keocapnhanh);
		this.tv_loaihai_drop_keocapnhanh.setOnClickListener(this);

		this.tv_loaibon_drop_keocapnhanh = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_loaibon_drop_keocapnhanh);
		this.tv_loaibon_drop_keocapnhanh.setOnClickListener(this);

		this.tv_loaitam_drop_keocapnhanh = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_loaitam_drop_keocapnhanh);
		this.tv_loaitam_drop_keocapnhanh.setOnClickListener(this);

		this.tv_loaimuoihai_drop_keocapnhanh = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_loaimuoihai_drop_keocapnhanh);
		this.tv_loaimuoihai_drop_keocapnhanh.setOnClickListener(this);

		this.btn_constr_brcd_back_keocapnhanh = (Button) spvBRCD_KCN_CHTView
				.findViewById(R.id.btn_constr_brcd_back_keocapnhanh);
		this.btn_constr_brcd_back_keocapnhanh.setOnClickListener(this);

		this.tv_sotuthuebao_loaihai_drop_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_sotuthuebao_loaihai_drop_keocapnhanh);

		this.tv_sotuthuebao_loaibon_drop_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_sotuthuebao_loaibon_drop_keocapnhanh);

		this.tv_sotuthuebao_loaitam_drop_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_sotuthuebao_loaitam_drop_keocapnhanh);

		this.tv_sotuthuebao_loaimuoihai_drop_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_sotuthuebao_loaimuoihai_drop_keocapnhanh);

		this.tv_constr_brcd_keocapnhanh_station_code_value = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_station_code_value);

		this.tv_constr_brcd_keocapnhanh_value = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_value);

		this.tv_constr_brcd_keocapnhanh_name = (TextView) spvBRCD_KCN_CHTView
				.findViewById(R.id.tv_constr_brcd_keocapnhanh_chitiet_name);

		this.btn_constr_brcd_luu_keocapnhanh = (Button) spvBRCD_KCN_CHTView
				.findViewById(R.id.btn_constr_brcd_luu_keocapnhanh);
		this.btn_constr_brcd_luu_keocapnhanh.setOnClickListener(this);

		this.ed_socotcosan_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.ed_socotcosan_keocapnhanh);

		this.ed_socotchongmoi_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.ed_socotchongmoi_keocapnhanh);

		this.ed_chieudai_keocapnhanh = (EditText) spvBRCD_KCN_CHTView
				.findViewById(R.id.ed_chieudai_keocapnhanh);

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

		case R.id.tv_constr_brcd_keocapnhanh_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_chonloaicap_keocapnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignLoaiCap);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_loaihai_drop_keocapnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignSoSoiDropGoLoaihai);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_loaibon_drop_keocapnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignSoSoiDropGoLoaiBon);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_loaitam_drop_keocapnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignSoSoiDropGoLoaiTam);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_loaimuoihai_drop_keocapnhanh:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignSoSoiDropGoLoaiMH);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_constr_brcd_back_keocapnhanh:
			Intent it = new Intent(
					Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
					Supervision_BRCD_KeoCapNhanhActivity.class);
			bundleData = new Bundle();

			bundleData.putSerializable(IntentConstants.INTENT_DATA, itemData);
			bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
					itemData_kcn.getSUPERVISION_BRCD_ID());
			it.putExtras(bundleData);
			this.startActivity(it);
			break;
		case R.id.btn_constr_brcd_luu_keocapnhanh:
			String messageError = "";
			messageError = this.checkVailid();

			// TODO handle Error DanhDue ExOICTIF
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
				// Toast.makeText(getApplicationContext(), messageError,
				// Toast.LENGTH_LONG).show();
			} else {
				Supervision_BRCD_KeoCapNhanhActivity.isMonitoring = false;
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
		final Header myActionBar = (Header) spvBRCD_KCN_CHTView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this);
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
							itemData_kcn.getSUPERVISION_BRCD_ID());
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBrcdBackgroupActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_chonloaicap_keocapnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}

			if (typeItem
					.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_loaihai_drop_keocapnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem
					.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_loaibon_drop_keocapnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem
					.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_loaitam_drop_keocapnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			if (typeItem
					.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_loaimuoihai_drop_keocapnhanh.setText(dropdownItem
							.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
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
		this.itemData_kcn = (Supervision_BRCD_Kcn_Entity) bundleData
				.getSerializable(IntentConstants.INTENT_BRCD_KCN);

		this.tv_constr_brcd_keocapnhanh_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_keocapnhanh_value.setText(itemData.getConstrCode());
		this.tv_constr_brcd_keocapnhanh_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		this.listDesignLoaiCap = GloablUtils.getListbrcdLoaicapnhanh("");
		this.listDesignInfoLoaiCapDropGo = GloablUtils
				.getListbrcdLoaicapdrop("");
		this.listDesignSoSoiDropGoLoaihai = GloablUtils
				.getListbrcdSoSoidrop_LoaiHai("");
		this.listDesignSoSoiDropGoLoaiBon = GloablUtils
				.getListbrcdSoSoidrop_LoaiBon("");
		this.listDesignSoSoiDropGoLoaiTam = GloablUtils
				.getListbrcdSoSoidrop_LoaiTam("");
		this.listDesignSoSoiDropGoLoaiMH = GloablUtils
				.getListbrcdSoSoidrop_LoaiMH("");
		tv_constr_brcd_keocapnhanh_name.setText(itemData_kcn.getCable_Code());
		brcd_kcn_design = brcd_kcn_design_Controller
				.getSupervisionBRCD_Kcn_design(itemData_kcn
						.getSupervition_branch_cable_id());
		if (brcd_kcn_design != null) {
			tv_chonloaicap_keocapnhanh.setText(""
					+ brcd_kcn_design.getCable_Type());
			ed_socotcosan_keocapnhanh.setText(""
					+ brcd_kcn_design.getPillar_Old_Number());
			ed_socotchongmoi_keocapnhanh.setText(""
					+ brcd_kcn_design.getPillar_New_Number());
			ed_chieudai_keocapnhanh.setText("" + brcd_kcn_design.getLength());
			tv_loaihai_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getCable_Drop_Num_Two());

			tv_loaibon_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getCable_Drop_Num_Four());

			tv_loaitam_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getCable_Drop_Num_Eight());

			tv_loaimuoihai_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getCable_Drop_Num_Twelve());

			tv_sotuthuebao_loaihai_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getNum_Box_Two());

			tv_sotuthuebao_loaibon_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getNum_Box_Four());

			tv_sotuthuebao_loaitam_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getNum_Box_Eight());

			tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.setText(""
					+ brcd_kcn_design.getNum_Box_Twelve());
			brcd_kcn_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			supervition_branch_cable_design_id = brcd_kcn_design
					.getSupervition_branch_design_id();

		} else {
			brcd_kcn_design = new Supervision_BRCD_Kcn_Design_Entity();
			brcd_kcn_design.setSync_Status(Constants.SYNC_STATUS.EDIT);

		}
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.btn_constr_brcd_luu_keocapnhanh.setVisibility(View.GONE);
		}
	}

	private void getSupvBRCD_Kcn() {
		if (brcd_kcn_design == null) {
			brcd_kcn_design = new Supervision_BRCD_Kcn_Design_Entity();
		} else {
			sosoidrop_loaihai_old = brcd_kcn_design.getCable_Drop_Num_Two();
			sotu_loaihai_old = brcd_kcn_design.getNum_Box_Two();
			sosoidrop_loaibon_old = brcd_kcn_design.getCable_Drop_Num_Four();
			sotu_loaibon_old = brcd_kcn_design.getNum_Box_Four();
			sosoidrop_loaitam_old = brcd_kcn_design.getCable_Drop_Num_Eight();
			sotu_loaitam_old = brcd_kcn_design.getNum_Box_Eight();
			sosoidrop_loaimh_old = brcd_kcn_design.getCable_Drop_Num_Twelve();
			sotu_loaimh_old = brcd_kcn_design.getNum_Box_Twelve();
			brcd_kcn_design
					.setCable_Type((Integer.parseInt(tv_chonloaicap_keocapnhanh
							.getText().toString())));
			brcd_kcn_design.setPillar_Old_Number(Integer
					.parseInt(ed_socotcosan_keocapnhanh.getText().toString()));
			if (ed_socotchongmoi_keocapnhanh.getText().toString().equals("")) {

				brcd_kcn_design.setPillar_New_Number(0);
			} else {
				brcd_kcn_design.setPillar_New_Number(Integer
						.parseInt(ed_socotchongmoi_keocapnhanh.getText()
								.toString()));
			}
			brcd_kcn_design.setLength(Integer.parseInt(ed_chieudai_keocapnhanh
					.getText().toString()));

			if (tv_loaihai_drop_keocapnhanh.getText().toString()
					.equals("--Chọn--")) {
				brcd_kcn_design.setCable_Drop_Num_Two(0);
			} else {
				brcd_kcn_design.setCable_Drop_Num_Two(Integer
						.parseInt(tv_loaihai_drop_keocapnhanh.getText()
								.toString()));
			}
			if (tv_loaibon_drop_keocapnhanh.getText().toString()
					.equals("--Chọn--")) {
				brcd_kcn_design.setCable_Drop_Num_Four(0);
			} else {
				brcd_kcn_design.setCable_Drop_Num_Four(Integer
						.parseInt(tv_loaibon_drop_keocapnhanh.getText()
								.toString()));
			}
			if (tv_loaitam_drop_keocapnhanh.getText().toString()
					.equals("--Chọn--")) {
				brcd_kcn_design.setCable_Drop_Num_Eight(0);
			} else {
				brcd_kcn_design.setCable_Drop_Num_Eight(Integer
						.parseInt(tv_loaitam_drop_keocapnhanh.getText()
								.toString()));
			}
			if (tv_loaimuoihai_drop_keocapnhanh.getText().toString()
					.equals("--Chọn--")) {
				brcd_kcn_design.setCable_Drop_Num_Twelve(0);
			} else {
				brcd_kcn_design.setCable_Drop_Num_Twelve(Integer
						.parseInt(tv_loaimuoihai_drop_keocapnhanh.getText()
								.toString()));
			}
			if (tv_sotuthuebao_loaihai_drop_keocapnhanh.getText().toString()
					.equals("")) {
				brcd_kcn_design.setNum_Box_Two(0);
			} else {
				brcd_kcn_design.setNum_Box_Two(Integer
						.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
								.getText().toString()));
			}
			if (tv_sotuthuebao_loaibon_drop_keocapnhanh.getText().toString()
					.equals("")) {
				brcd_kcn_design.setNum_Box_Four(0);
			} else {
				brcd_kcn_design.setNum_Box_Four(Integer
						.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
								.getText().toString()));
			}
			if (tv_sotuthuebao_loaitam_drop_keocapnhanh.getText().toString()
					.equals("")) {
				brcd_kcn_design.setNum_Box_Eight(0);
			} else {
				brcd_kcn_design.setNum_Box_Eight(Integer
						.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
								.getText().toString()));
			}
			if (tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().equals("")) {
				brcd_kcn_design.setNum_Box_Twelve(0);
			} else {
				brcd_kcn_design.setNum_Box_Twelve(Integer
						.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
								.getText().toString()));
			}
			brcd_kcn_design.setSupervition_branch_cable_id(itemData_kcn
					.getSupervition_branch_cable_id());

		}
	}

	private void saveDataTank() {

		getSupvBRCD_Kcn();
		long idUser = GlobalInfo.getInstance().getUserId();
		long idSupervion = Ktts_KeyController.getInstance().getKttsNextKey(
				Supervision_BRCD_Kct_design_Field.TABLE_NAME);
		if (idSupervion == 0) {
			Toast.makeText(Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
					StringUtil.getString(R.string.text_out_of_key),
					Toast.LENGTH_SHORT).show();

		}
		if (brcd_kcn_design.getSync_Status() == Constants.SYNC_STATUS.EDIT) {
			brcd_kcn_design.setSupervition_branch_design_id(idSupervion);

			brcd_kcn_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn_design.setProcessId(0);
			brcd_kcn_design.setEmployeeId(idUser);

			brcd_kcn_design_Controller.addItem(brcd_kcn_design);
			if (!tv_loaihai_drop_keocapnhanh.getText().equals("--Chọn--")) {
				int sosoidrop_loaihai = Integer
						.parseInt(tv_loaihai_drop_keocapnhanh.getText()
								.toString().trim());
				addDropAndGo(sosoidrop_loaihai, 2, idSupervion, idUser);

			}
			if (!tv_sotuthuebao_loaihai_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				int sotuthuebao_loaihai = Integer
						.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
								.getText().toString().trim());
				addTTB(sotuthuebao_loaihai, 2, idSupervion, idUser);

			}
			if (!tv_loaibon_drop_keocapnhanh.getText().equals("--Chọn--")) {
				int sosoidrop_loaibon = Integer
						.parseInt(tv_loaibon_drop_keocapnhanh.getText()
								.toString().trim());
				addDropAndGo(sosoidrop_loaibon, 4, idSupervion, idUser);

			}
			if (!tv_sotuthuebao_loaibon_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				int sotuthuebao_loaibon = Integer
						.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
								.getText().toString().trim());
				addTTB(sotuthuebao_loaibon, 4, idSupervion, idUser);
			}
			if (!tv_loaitam_drop_keocapnhanh.getText().equals("--Chọn--")) {
				int sosoidrop_loaitam = Integer
						.parseInt(tv_loaitam_drop_keocapnhanh.getText()
								.toString().trim());
				addDropAndGo(sosoidrop_loaitam, 8, idSupervion, idUser);

			}
			if (!tv_sotuthuebao_loaitam_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				int sotuthuebao_loaitam = Integer
						.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
								.getText().toString().trim());
				addTTB(sotuthuebao_loaitam, 8, idSupervion, idUser);

			}
			if (!tv_loaimuoihai_drop_keocapnhanh.getText().equals("--Chọn--")) {
				int sosoidrop_loaimh = Integer
						.parseInt(tv_loaimuoihai_drop_keocapnhanh.getText()
								.toString().trim());
				addDropAndGo(sosoidrop_loaimh, 12, idSupervion, idUser);

			}
			if (!tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")) {
				int sotuthuebao_loaimh = Integer
						.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
								.getText().toString().trim());
				addTTB(sotuthuebao_loaimh, 12, idSupervion, idUser);

			}

			sosoidrop_loaihai_old = 0;
			if (!tv_loaihai_drop_keocapnhanh.getText().toString().trim()
					.equals("--Chọn--")) {
				sosoidrop_loaihai_old = Integer
						.parseInt(tv_loaihai_drop_keocapnhanh.getText()
								.toString().trim());

			}
			sotu_loaihai_old = 0;
			if (!tv_sotuthuebao_loaihai_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaihai_old = Integer
						.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
								.getText().toString().trim());

			}
			sosoidrop_loaibon_old = 0;
			if (!tv_loaibon_drop_keocapnhanh.getText().equals("--Chọn--")) {
				sosoidrop_loaibon_old = Integer
						.parseInt(tv_loaibon_drop_keocapnhanh.getText()
								.toString().trim());

			}
			sotu_loaibon_old = 0;
			if (!tv_sotuthuebao_loaibon_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaibon_old = Integer
						.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
								.getText().toString().trim());

			}
			sosoidrop_loaitam_old = 0;
			if (!tv_loaitam_drop_keocapnhanh.getText().equals("--Chọn--")) {
				sosoidrop_loaitam_old = Integer
						.parseInt(tv_loaitam_drop_keocapnhanh.getText()
								.toString().trim());

			}
			sotu_loaitam_old = 0;
			if (!tv_sotuthuebao_loaitam_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaitam_old = Integer
						.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
								.getText().toString().trim());

			}
			sosoidrop_loaimh_old = 0;
			if (!tv_loaimuoihai_drop_keocapnhanh.getText().equals("--Chọn--")) {
				sosoidrop_loaimh_old = Integer
						.parseInt(tv_loaimuoihai_drop_keocapnhanh.getText()
								.toString().trim());

			}
			sotu_loaimh_old = 0;
			if (!tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")) {
				sotu_loaimh_old = Integer
						.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
								.getText().toString().trim());

			}
		}

		else if (brcd_kcn_design.getSync_Status() == Constants.SYNC_STATUS.ADD) {
			brcd_kcn_design.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn_design.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn_design.setProcessId(0);
			brcd_kcn_design.setEmployeeId(idUser);
			brcd_kcn_design
					.setSupervition_branch_design_id(supervition_branch_cable_design_id);
			brcd_kcn_design_Controller.updateSupervisionBRCD_Kcn_Design(
					brcd_kcn_design.getSupervition_branch_design_id(),
					brcd_kcn_design);
			
			if (tv_loaihai_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaihai_new = 0;
			}
			else {
				sosoidrop_loaihai_new = Integer
						.parseInt(tv_loaihai_drop_keocapnhanh.getText().toString()
								.trim());
			}
			
			UpdateaddDropAndGo(sosoidrop_loaihai_old, sosoidrop_loaihai_new, 2,
					idUser);

			if (tv_sotuthuebao_loaihai_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaihai_new = 0;

				UpdateaddTTB(sotu_loaihai_old, sotu_loaihai_new, 2, idUser);

			} else {
				sotu_loaihai_new = Integer
						.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
								.getText().toString().trim());
				UpdateaddTTB(sotu_loaihai_old, sotu_loaihai_new, 2, idUser);
			}

			if (tv_loaibon_drop_keocapnhanh.getText()
					.toString().equals("--Chọn--")) {
				sosoidrop_loaibon_new = 0;
			}
			else {
				sosoidrop_loaibon_new = Integer
						.parseInt(tv_loaibon_drop_keocapnhanh.getText()
								.toString().trim());
			}

			UpdateaddDropAndGo(sosoidrop_loaibon_old, sosoidrop_loaibon_new, 4,
					idUser);

			if (tv_sotuthuebao_loaibon_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaibon_new = 0;
				UpdateaddTTB(sotu_loaibon_old, sotu_loaibon_new, 4, idUser);
			} else {
				sotu_loaibon_new = Integer
						.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
								.getText().toString().trim());
				UpdateaddTTB(sotu_loaibon_old, sotu_loaibon_new, 4, idUser);
			}
			
			
			if (tv_loaitam_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaitam_new = 0;
			}
			else {
				sosoidrop_loaitam_new = Integer
						.parseInt(tv_loaitam_drop_keocapnhanh.getText().toString()
								.trim());
			}
			
			UpdateaddDropAndGo(sosoidrop_loaitam_old, sosoidrop_loaitam_new, 8,
					idUser);

			if (tv_sotuthuebao_loaitam_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaitam_new = 0;
				UpdateaddTTB(sotu_loaitam_old, sotu_loaitam_new, 8, idUser);
			} else {
				sotu_loaitam_new = Integer
						.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
								.getText().toString().trim());
				UpdateaddTTB(sotu_loaitam_old, sotu_loaitam_new, 8, idUser);

			}
			
			if (tv_loaimuoihai_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaimh_new = 0;
			}
			else {
				sosoidrop_loaimh_new = Integer
						.parseInt(tv_loaimuoihai_drop_keocapnhanh.getText()
								.toString().trim());
			}

			UpdateaddDropAndGo(sosoidrop_loaimh_old, sosoidrop_loaimh_new, 12,
					idUser);

			if (tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")) {
				sotu_loaimh_new = 0;
				UpdateaddTTB(sotu_loaimh_old, sotu_loaimh_new, 12, idUser);
			} else {
				sotu_loaimh_new = Integer
						.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
								.getText().toString().trim());
				UpdateaddTTB(sotu_loaimh_old, sotu_loaimh_new, 12, idUser);
			}

			if (tv_loaihai_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaihai_old = 0;
			}
			else {
				sosoidrop_loaihai_old = Integer
						.parseInt(tv_loaihai_drop_keocapnhanh.getText().toString()
								.trim());
			}
			
			
			if (tv_sotuthuebao_loaihai_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaihai_old = 0;
			} else {
				sotu_loaihai_old = Integer
						.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
								.getText().toString().trim());
			}
			
			if (tv_loaibon_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaibon_old = 0;
			}
			else {
				sosoidrop_loaibon_old = Integer
						.parseInt(tv_loaibon_drop_keocapnhanh.getText().toString()
								.trim());
			}
			
			if (tv_sotuthuebao_loaibon_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaibon_old = 0;
			} else {
				sotu_loaibon_old = Integer
						.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
								.getText().toString().trim());
			}
			
			if (tv_loaitam_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaitam_old = 0;
			}
			else {
				sosoidrop_loaitam_old = Integer
						.parseInt(tv_loaitam_drop_keocapnhanh.getText().toString()
								.trim());
			}
			
			if (tv_sotuthuebao_loaitam_drop_keocapnhanh.getText().toString()
					.trim().equals("")) {
				sotu_loaitam_old = 0;
			} else {
				sotu_loaitam_old = Integer
						.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
								.getText().toString().trim());
			}
			
			if (tv_loaimuoihai_drop_keocapnhanh.getText().toString().equals("--Chọn--")) {
				sosoidrop_loaimh_old = 0;
			}
			else {
				sosoidrop_loaimh_old = Integer
						.parseInt(tv_loaimuoihai_drop_keocapnhanh.getText()
								.toString().trim());
			}
			
			
			if (tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")) {
				sotu_loaimh_old = 0;
			} else {
				sotu_loaimh_old = Integer
						.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
								.getText().toString().trim());
			}

		}

	}

	private void addDropAndGo(int sosoidrop, int loaicap, long idSupervion,
			long idUser) {
		for (int i = 0; i < sosoidrop; i++) {
			long idSupervion_drop = Ktts_KeyController.getInstance()
					.getKttsNextKey(Supervision_BRCD_Drop_Field.TABLE_NAME);
			if (idSupervion_drop == 0) {
				Toast.makeText(
						Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_Drop_Entity.setSupervition_branch_drop_id(idSupervion_drop);
			brcd_Drop_Entity.setSUPERVISION_BRCD_ID(itemData_kcn
					.getSUPERVISION_BRCD_ID());
			brcd_Drop_Entity.setSupervition_branch_design_id(idSupervion);
			brcd_Drop_Entity.setCab_type(loaicap);
			brcd_Drop_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_Drop_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_Drop_Entity.setProcessId(0);
			brcd_Drop_Entity.setEmployeeId(idUser);
			brcd_Drop_Entity.setName(""
					+ tv_constr_brcd_keocapnhanh_name.getText().toString()
							.trim() + "_SC" + (i + 1));
			supervisionDROPController.addItem(brcd_Drop_Entity);

		}

	}

	private void UpdateaddDropAndGo(int sosoicu, int sosoimoi, int loaicap,
			long idUser) {
		if (sosoimoi < sosoicu) {
			listSupervisionDROP = supervisionDROPController
					.getAllbrcd_drop_design(
							brcd_kcn_design.getSupervition_branch_design_id(),
							loaicap);
			for (int i = 0; i < (sosoicu - sosoimoi); i++) {
				supervisionDROPController.deleteDropEntity(listSupervisionDROP
						.get(listSupervisionDROP.size() - (i + 1)));
			}
		} else {
			for (int i = 0; i < (sosoimoi - sosoicu); i++) {
				long idSupervion_drop = Ktts_KeyController.getInstance()
						.getKttsNextKey(Supervision_BRCD_Drop_Field.TABLE_NAME);
				if (idSupervion_drop == 0) {
					Toast.makeText(
							Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
							StringUtil.getString(R.string.text_out_of_key),
							Toast.LENGTH_SHORT).show();

				}
				brcd_Drop_Entity
						.setSupervition_branch_drop_id(idSupervion_drop);
				brcd_Drop_Entity.setSUPERVISION_BRCD_ID(itemData_kcn
						.getSUPERVISION_BRCD_ID());
				brcd_Drop_Entity
						.setSupervition_branch_design_id(brcd_kcn_design
								.getSupervition_branch_design_id());

				brcd_Drop_Entity.setCab_type(loaicap);
				brcd_Drop_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
				brcd_Drop_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
				brcd_Drop_Entity.setProcessId(0);
				brcd_Drop_Entity.setEmployeeId(idUser);
				brcd_Drop_Entity.setName(""
						+ tv_constr_brcd_keocapnhanh_name.getText().toString()
								.trim() + "_SC"
						+ (sosoidrop_loaihai_old + i + 1));
				supervisionDROPController.addItem(brcd_Drop_Entity);
			}
		}
	}

	private void addTTB(int sotuthuebao, int loaicap, long idSupervion,
			long idUser) {
		for (int i = 0; i < sotuthuebao; i++) {
			long idSupervion_ttb = Ktts_KeyController.getInstance()
					.getKttsNextKey(Supervision_BRCD_Ttb_Field.TABLE_NAME);
			if (idSupervion_ttb == 0) {
				Toast.makeText(
						Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();

			}
			brcd_Ttb_Entity.setSupervition_branch_box_id(idSupervion_ttb);
			brcd_Ttb_Entity.setSUPERVISION_BRCD_ID(itemData_kcn
					.getSUPERVISION_BRCD_ID());
			brcd_Ttb_Entity.setSupervition_branch_design_id(idSupervion);
			brcd_Ttb_Entity.setCab_type(loaicap);
			brcd_Ttb_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_Ttb_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_Ttb_Entity.setProcessId(0);
			brcd_Ttb_Entity.setEmployeeId(idUser);
			brcd_Ttb_Entity.setBox_Name(""
					+ tv_constr_brcd_keocapnhanh_name.getText().toString()
							.trim() + "_SN" + (i + 1));
			supervisionTTBController.addItem(brcd_Ttb_Entity);

		}
	}

	private void UpdateaddTTB(int sotucu, int sotumoi, int loaicap, long idUser) {
		if (sotumoi < sotucu) {
			listSupervisionTTB = supervisionTTBController.getAllbrcd_ttb(
					brcd_kcn_design.getSupervition_branch_design_id(), loaicap);
			for (int i = 0; i < (sotucu - sotumoi); i++) {
				supervisionTTBController.deleteTtbEntity(listSupervisionTTB
						.get(listSupervisionTTB.size() - (i + 1)));
			}
		} else {
			for (int i = 0; i < (sotumoi - sotucu); i++) {
				long idSupervion_ttb = Ktts_KeyController.getInstance()
						.getKttsNextKey(Supervision_BRCD_Ttb_Field.TABLE_NAME);
				if (idSupervion_ttb == 0) {
					Toast.makeText(
							Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
							StringUtil.getString(R.string.text_out_of_key),
							Toast.LENGTH_SHORT).show();

				}
				brcd_Ttb_Entity.setSupervition_branch_box_id(idSupervion_ttb);
				brcd_Ttb_Entity.setSUPERVISION_BRCD_ID(itemData_kcn
						.getSUPERVISION_BRCD_ID());
				brcd_Ttb_Entity.setSupervition_branch_design_id(brcd_kcn_design
						.getSupervition_branch_design_id());
				brcd_Ttb_Entity.setCab_type(loaicap);
				brcd_Ttb_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
				brcd_Ttb_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
				brcd_Ttb_Entity.setProcessId(0);
				brcd_Ttb_Entity.setEmployeeId(idUser);
				brcd_Ttb_Entity.setBox_Name(""
						+ tv_constr_brcd_keocapnhanh_name.getText().toString()
								.trim() + "_SN"
						+ (sosoidrop_loaihai_old + i + 1));
				supervisionTTBController.addItem(brcd_Ttb_Entity);
			}
		}
	}

	private String checkVailid() {
		String sReslut = "";
		try {
			if (tv_chonloaicap_keocapnhanh.getText().toString()
					.equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_loai_cap_nhanh);

			} else if (ed_socotcosan_keocapnhanh.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_cot_co_san);
				ed_socotcosan_keocapnhanh.requestFocus();
			} else if (ed_chieudai_keocapnhanh.getText().toString().trim()
					.equals("")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_chieu_dai_cap);
				ed_chieudai_keocapnhanh.requestFocus();
			} else if (tv_loaihai_drop_keocapnhanh.getText().toString()
					.equals("--Chọn--")
					&& tv_loaibon_drop_keocapnhanh.getText().toString()
							.equals("--Chọn--")
					&& tv_loaitam_drop_keocapnhanh.getText().toString()
							.equals("--Chọn--")
					&& tv_loaimuoihai_drop_keocapnhanh.getText().toString()
							.equals("--Chọn--")) {
				sReslut = StringUtil.getString(R.string.brcd_chon_so_soi_drop);
			} else if (tv_sotuthuebao_loaihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")
					&& !(tv_loaihai_drop_keocapnhanh.getText().toString())
							.equals("0")
					&& !(tv_loaihai_drop_keocapnhanh.getText().toString())
							.equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_loai_hai);

			} else if (tv_sotuthuebao_loaibon_drop_keocapnhanh.getText()
					.toString().trim().equals("")
					&& !(tv_loaibon_drop_keocapnhanh.getText().toString())
							.equals("0")
					&& !(tv_loaibon_drop_keocapnhanh.getText().toString())
							.equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_loai_bon);
			} else if (tv_sotuthuebao_loaitam_drop_keocapnhanh.getText()
					.toString().trim().equals("")
					&& !(tv_loaitam_drop_keocapnhanh.getText().toString())
							.equals("0")
					&& !(tv_loaitam_drop_keocapnhanh.getText().toString())
							.equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_loai_tam);
			} else if (tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.getText()
					.toString().trim().equals("")
					&& !(tv_loaimuoihai_drop_keocapnhanh.getText().toString())
							.equals("0")
					&& !(tv_loaimuoihai_drop_keocapnhanh.getText().toString())
							.equals("--Chọn--")) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_loai_muoi_hai);
			} else if (Integer.parseInt(tv_sotuthuebao_loaihai_drop_keocapnhanh
					.getText().toString()) > 20) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_vuot_qua);
				tv_sotuthuebao_loaihai_drop_keocapnhanh.requestFocus();
			} else if (Integer.parseInt(tv_sotuthuebao_loaibon_drop_keocapnhanh
					.getText().toString()) > 20) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_vuot_qua);
				tv_sotuthuebao_loaibon_drop_keocapnhanh.requestFocus();
			} else if (Integer.parseInt(tv_sotuthuebao_loaitam_drop_keocapnhanh
					.getText().toString()) > 20) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_vuot_qua);
				tv_sotuthuebao_loaitam_drop_keocapnhanh.requestFocus();
			} else if (Integer
					.parseInt(tv_sotuthuebao_loaimuoihai_drop_keocapnhanh
							.getText().toString()) > 20) {
				sReslut = StringUtil
						.getString(R.string.brcd_chon_so_tu_thue_bao_vuot_qua);
				tv_sotuthuebao_loaimuoihai_drop_keocapnhanh.requestFocus();
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
				Toast.makeText(
						Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			Toast.makeText(Supervision_BRCD_KeoCapNhanh_ChiTiet_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}