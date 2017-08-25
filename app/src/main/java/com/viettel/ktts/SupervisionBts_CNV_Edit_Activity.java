package com.viettel.ktts;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Image_ViewGalleryPopup;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.DateConvert;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Constr_ObStructionEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author hungtn
 * 
 */
public class SupervisionBts_CNV_Edit_Activity extends
		SupervisionBtsBaseActivity {
	private View spvBTS_CatWorkView;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;
	private Constr_ObStructionEntity cnvEntity;
	private Constr_ObStructionEntity curCnvEntity;
	/* Hien thi popup */
	private Menu_DropdownPopup dropdownPopupMenuInfomation;

	private List<DropdownItemEntity> listInfomation = null;
	boolean isView = false;
	private int isInfomation = 1;
	private ImageView imgCapture;
//	private Image_ViewGalleryPopup imgViewPopup;
	/**
	 * combobox
	 */
	// thong tin thiet ke
	private RelativeLayout supervision_bts_cnv_cb_thietke;
	private TextView supervision_bts_cnv_thietke;

	/**
	 * text view
	 */
	// ma tram
	private TextView supervision_bts_tv_matram;
	// ma cong trinh
	private TextView supervision_bts_tv_mact;

	/**
	 * button
	 */
	// private Button ibtnSave;
	private RadioGroup radioGroup;
	private RadioButton radioButtonTKT;
	private RadioButton radioButtonHCT;
	private TextView edDate;
	// private ImageView imgCalendar;
	private String loaiVuong;
	private DatePickerDialog fromDatePickerDialog;
	private TextView txtCnvLoaiVuong;
	private RadioGroup rdgKetQuaXuLy;
	private TextView txtDateVuong;
	private EditText editDienGiai;
	private Button btnSave;
	private OnEventControlListener onEvent;
	
	// private ConfirmDialog confirmSave;
	//KienPV add new 09/09/2016
	private EditText edt_des;
	private RadioGroup rdg_loaivuong;
	private String listloaiVuong;
	//--

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.supervision_bts_cat_work_activity);
		//setTitle(getString(R.string.supervision_bts_title));
		initComponent();
		// setHeader();
		isInfomation = getIntent().getExtras().getInt(IntentConstants.INTENT_DESIGNINFO);
		constr_ConstructionItem = getConstr_Construction_Employee();
		isView = getIntent().getExtras().getBoolean(IntentConstants.INTENT_CNV_ITEM_VIEW);
		Log.e("edit: ",isView+"");
		cnvEntity = getConstr_ObStruction();
		initData();
		setData();
		closeProgressDialog();

	}
	//KienPV add new 09/09/2016
    private void setTitleView(){
    	switch (constr_ConstructionItem.getSupvConstrType()) {
		case Constants.CONSTR_TYPE.BTS:
			setTitle(getString(R.string.supervision_bts_title));		
			break;
		case Constants.CONSTR_TYPE.LINE_BACKGROUND:
			setTitle(getString(R.string.line_background_header_title));
			break;
		case Constants.CONSTR_TYPE.LINE:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			break;
		case Constants.CONSTR_TYPE.LINE_HANG:
			setTitle(getString(R.string.line_hang_header_title));
			break;
		case Constants.CONSTR_TYPE.SH:
			setTitle(getString(R.string.line_background_header_title_sub_mt));
			break;
		case Constants.CONSTR_TYPE.BRCD:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			break;
		default:
			break;
		}
    }
    private String converTypeToString(String type){
    	String result ="";
    	if(type.equals("TKDT")){result = getString(R.string.cnv_Thiet_ke_du_toan);}
		if(type.equals("GPXD")){result = getString(R.string.cnv_gpxd);}
		if(type.equals("DK")){result = getString(R.string.cnv_dan_kien);}
		if(type.equals("QH")){result = getString(R.string.cnv_quy_hoach);}
		if(type.equals("XN4B")){result = getString(R.string.cnv_xac_nhan_4_ben);}
		if(type.equals("KHAC")){result = getString(R.string.cnv_khac);}
    	return result;
    }
  //--
	public void setData() {
		setTitleView();	
		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());
	}

	public void refreshForm() {
		// KienPV add new 9/9/2016
		// comment code
		// String sChoice = getResources().getString(R.string.text_choice1);

		// ---

	}

	public void initData() {
		String sChoice = "";
		// KienPV add new 9/9/2016
		// sChoice = getResources().getString(R.string.text_choice1);
		switch (constr_ConstructionItem.getSupvConstrType()) {
		case Constants.CONSTR_TYPE.BTS:
			setTitle(getString(R.string.supervision_bts_title));
			listInfomation = GloablUtils.getListBTSInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringBTSInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.LINE_BACKGROUND:
			setTitle(getString(R.string.line_background_header_title));
			listInfomation = GloablUtils.getListLineBackgroundInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringLineBackgroundInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.LINE:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			break;
		case Constants.CONSTR_TYPE.LINE_HANG:
			setTitle(getString(R.string.line_hang_header_title));
			listInfomation = GloablUtils.getListLineHangInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringLineHangInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.SH:
			setTitle(getString(R.string.line_background_header_title_sub_mt));
			listInfomation = GloablUtils.getListSubInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringSubInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.BRCD:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			listInfomation = GloablUtils.getListbrcdBackgroundInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringBRCDBackgroundInfo(isInfomation));
			break;
		default:
			break;
		}
		//--

		// Lay du lieu tu trong db
		// Supervision_CNVController cnvController = new
		// Supervision_CNVController(
		// this);
		// listData = cnvController.getAllCNV();
		// listAdapter = new ItemSupervision_CNV_List(this,listData);
		// lvCnv.setAdapter(listAdapter);
		txtCnvLoaiVuong.setText(converTypeToString(cnvEntity.getType()));
		listloaiVuong = cnvEntity.getType().trim();
		edDate.setText(DateConvert.convertDateToString(cnvEntity.getCreatedDate()));
		editDienGiai.setText(cnvEntity.getUpdateDescription());
		//KienPV add new 09/09/2015
		edt_des.setText(cnvEntity.getDescription());
		//---
		if ("TKT".equals(cnvEntity.getSolvingMethod())) {
			radioButtonTKT.setChecked(true);
		} else {
			radioButtonHCT.setChecked(true);
		}

		if (isView) {
			txtCnvLoaiVuong.setVisibility(View.VISIBLE);
			rdg_loaivuong.setVisibility(View.GONE);
			btnSave.setVisibility(View.INVISIBLE);
			radioButtonHCT.setEnabled(false);
			radioButtonTKT.setEnabled(false);
			editDienGiai.setFocusable(false);
			editDienGiai.setEnabled(false);
			editDienGiai.setCursorVisible(false);			
			editDienGiai.setKeyListener(null);
			edt_des.setFocusable(false);
			edt_des.setEnabled(false);
			edt_des.setCursorVisible(false);
			edt_des.setKeyListener(null);
			
		}else{
			if(cnvEntity.getType().equals("TKDT")){rdg_loaivuong.check(R.id.rd_thietkedutoan);}
			if(cnvEntity.getType().equals("GPXD")){rdg_loaivuong.check(R.id.rd_gpxd);}
			if(cnvEntity.getType().equals("DK")){rdg_loaivuong.check(R.id.rd_dankien);}
			if(cnvEntity.getType().equals("QH")){rdg_loaivuong.check(R.id.rd_quyhoach);}
			if(cnvEntity.getType().equals("XN4B")){rdg_loaivuong.check(R.id.rd_xacnhan4ben);}
			if(cnvEntity.getType().equals("KHAC")){rdg_loaivuong.check(R.id.rd_khac);}
			if("TKT".equals(cnvEntity.getSolvingMethod())){
				Log.e("TKT: ","ok");
				txtCnvLoaiVuong.setVisibility(View.GONE);
				rdg_loaivuong.setVisibility(View.VISIBLE);
			}else{
				txtCnvLoaiVuong.setVisibility(View.VISIBLE);
				txtCnvLoaiVuong.setText(converTypeToString(listloaiVuong));
				rdg_loaivuong.setVisibility(View.GONE);
			}
		}
	}

	public void initComponent() {
		spvBTS_CatWorkView = addView(R.layout.supervision_cnv_edit_activity,
				R.id.rlspv_bt_catwork);

		// combobox
		setSupervision_bts_cnv_cb_thietke((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cnv_search_thietke));
		supervision_bts_cnv_thietke = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_dropdown);
		supervision_bts_cnv_thietke.setOnClickListener(this);

		// textview
		supervision_bts_tv_matram = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_station_code_value);
		supervision_bts_tv_mact = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_value);

		// button
		btnSave = (Button) spvBTS_CatWorkView
				.findViewById(R.id.btn_bts_cnv_save);
		// lvCnv = (ListView)spvBTS_CatWorkView.findViewById(R.id.lv_cnv_list);
		radioGroup = (RadioGroup) spvBTS_CatWorkView
				.findViewById(R.id.rdg_ketqua);
		
		edDate = (TextView) spvBTS_CatWorkView.findViewById(R.id.tv_ngay_vuong);
		txtCnvLoaiVuong = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_loai_vuong);
		editDienGiai = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.edt_dien_giai);
		radioButtonTKT = (RadioButton) spvBTS_CatWorkView
				.findViewById(R.id.rd_trienkhaitiep);
		radioButtonHCT = (RadioButton) spvBTS_CatWorkView
				.findViewById(R.id.rd_huycongtrinh);
		onEvent = (OnEventControlListener) this;
		imgCapture = (ImageView) spvBTS_CatWorkView
				.findViewById(R.id.iv_capture);
		//KienPV add new 09/09/2016
		edt_des = (EditText) spvBTS_CatWorkView.findViewById(R.id.edt_des);
		rdg_loaivuong = (RadioGroup)spvBTS_CatWorkView.findViewById(R.id.rdg_loaivuong);
		if(isView){
			if(this.curCnvEntity.getLstAttachFile().size() > 0){imgCapture.setImageResource(R.drawable.icon_image_exit);}else{imgCapture.setImageResource(R.drawable.icon_img_take);
			}
		}
		rdg_loaivuong.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rd_thietkedutoan:
					listloaiVuong = "TKDT";
					break;
				case R.id.rd_gpxd:
					listloaiVuong = "GPXD";
					break;
				case R.id.rd_dankien:
					listloaiVuong = "DK";
					break;
				case R.id.rd_quyhoach:
					listloaiVuong = "QH";
					break;
				case R.id.rd_xacnhan4ben:
					listloaiVuong = "XN4B";
					break;
				case R.id.rd_khac:
					listloaiVuong = "KHAC";
					break;
				default:
					break;
				}
			}
		});
		//--
		imgCapture.setOnClickListener(this);
		// imgCalendar = (ImageView)
		// spvBTS_CatWorkView.findViewById(R.id.imgDate);

		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rd_trienkhaitiep:
					loaiVuong = "TKT";
					txtCnvLoaiVuong.setVisibility(View.GONE);
					rdg_loaivuong.setVisibility(View.VISIBLE);
					break;
				case R.id.rd_huycongtrinh:
					loaiVuong = "HCT";
					txtCnvLoaiVuong.setVisibility(View.VISIBLE);
					txtCnvLoaiVuong.setText(converTypeToString(listloaiVuong));
					rdg_loaivuong.setVisibility(View.GONE);
					break;
				default:
					break;
				}
			}
		});
		btnSave.setOnClickListener(this);

	}

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	public Constr_ObStructionEntity getConstr_ObStruction() {
		return (Constr_ObStructionEntity) getIntent().getExtras().getSerializable(IntentConstants.INTENT_CNV_ITEM);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click nut hoan thanh
		case R.id.rl_supervision_bts_cat_work_complete:
			if (!GlobalInfo.getInstance().isCheckIn()) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.checkin_require),
						getString(R.string.text_close));
				break;
			}
			// confirmSave = new ConfirmDialog(this,
			// StringUtil.getString(R.string.text_confirm_save),
			// OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			// confirmSave.show();
			break;
		case R.id.iv_capture:
			if (cnvEntity != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_ACCEPTANCE_TAKE_PHOTO,
						null, cnvEntity);

			}
			break;
		// click combobox thong tin
		case R.id.tv_cnv_dropdown:
			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);
			dropdownPopupMenuInfomation.show(v);
			break;
		case R.id.btn_bts_cnv_save:
			ConfirmDialog confirmSave = new ConfirmDialog(
					SupervisionBts_CNV_Edit_Activity.this,
					StringUtil.getString(R.string.text_confirm_save));
			confirmSave.show();
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
			Drawable ic_combo = getResources().getDrawable(
					R.drawable.icon_combo);
			ic_combo.setBounds(0, 0, ic_combo.getIntrinsicWidth(),
					ic_combo.getIntrinsicHeight());

			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				isInfomation = dropdownItem.getId();

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,isInfomation);
				this.supervision_bts_cnv_thietke.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();

				this.showProgressDialog(StringUtil.getString(R.string.text_loading));
				// KienPV add new  9/9/2016
				gotoSupvConstrType(constr_ConstructionItem.getSupvConstrType(),isInfomation,bundleMonitorData);
				closeProgressDialog();
				return;
				//--
//				if (isInfomation == Constants.BTS_INFO.THIET_TKE_INFO) {
//					gotoSupervisionBtsActivity(bundleMonitorData);
//				}
//
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO) {
//					gotoSupervisionBtsPillarActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO) {
//					gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.KEO_DIEN_INFO) {
//					gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO) {
//					gotoSupervisionBtsEquipActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.LAP_DAT_VIBA_INFO) {
//
//					gotoSupervisionBtsVibaActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO) {
//
//					gotoSupervisionBtsMeasureActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.MEASURE_CONSTR_INFO) {
//					gotoSupervisionMeasureConstrActivity(bundleMonitorData);
//				}
//
//				// HungTN add new 24/08/2016
//				if (isInfomation == Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG) {
//					gotoSupervisionCNDTCActivity(bundleMonitorData);
//				}
//				// ---
//				// KienPV add new 08/09/2016
//				 if(isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG){
//				 closeProgressDialog();
//				 return;
//				 }
//				 //--
//				if (isInfomation == Constants.BTS_INFO.KET_LUAN_INFO) {
//
//					gotoSupervisionBtsKLActivity(bundleMonitorData);
//				}
//				//finish();
			}

		case OnEventControlListener.EVENT_CONFIRM_OK:

			SaveAsyncTask task = new SaveAsyncTask();
			task.execute();
			break;

		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:

			break;
		// chup anh nghiem thu
		case OnEventControlListener.EVENT_ACCEPTANCE_TAKE_PHOTO:
			this.curCnvEntity = (Constr_ObStructionEntity) data;
			List<ImageEntity> listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */

			// gan anh co san
			for (Supv_Constr_Attach_FileEntity itemAttach : curCnvEntity
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
			for (Supv_Constr_Attach_FileEntity itemNewAttachFile : curCnvEntity.getLstNewAttachFile()) {
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

			this.imgViewPopup.hidePopup();
			// if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
			this.curCnvEntity.getLstAttachFile().clear();
			this.curCnvEntity.getLstNewAttachFile().clear();
			for (ImageEntity imageEntity : lstData) {
				Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
				curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity.getId());
				curAttachFile.setFile_Path(imageEntity.getUrl());
				this.curCnvEntity.getLstNewAttachFile().add(curAttachFile);
			}
			// this.contruoctionAcceptancePopup.refreshData();

			// this.supervisionAdapter.notifyDataSetChanged();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(constr_ConstructionItem);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:
			this.imgViewPopup.deleteImageData();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	private boolean result1;

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			// closeProgressDialog();
			// showProgressDialog(StringUtil.getString(R.string.text_progcessing));
			
		}

		@SuppressLint("SimpleDateFormat")
		@Override
		protected Void doInBackground(Void... params) {
			Supervision_CNVController controller = new Supervision_CNVController(
					SupervisionBts_CNV_Edit_Activity.this);

			//KienPV add new 09/09/2016
			cnvEntity.setDescription(edt_des.getText().toString().trim());
			cnvEntity.setUpdateDescription(editDienGiai.getText().toString().trim());
			cnvEntity.setLstAttachFile(curCnvEntity.getLstNewAttachFile());
			cnvEntity.setType(listloaiVuong);
			//---
			cnvEntity.setSolvingMethod(loaiVuong);
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = sdf.format(c.getTime());
			cnvEntity.setUpdateDate(DateConvert.convertStringToDate(strDate));
			cnvEntity.setUpdatedBy("" + GlobalInfo.getInstance().getUserId());
			result1 = controller.updateConstrObStruction(cnvEntity);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			closeProgressDialog();
			if (result1) {
				showDialog(StringUtil
						.getString(R.string.update_database_complete));
			} else {
				showDialog(StringUtil.getString(R.string.text_update_error));
			}
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
//			if (resultCode == Activity.RESULT_OK) {
//				this.curEditItem.setLatLocation(data.getDoubleExtra(
//						IntentConstants.INTENT_LAT,
//						Constants.ID_DOUBLE_ENTITY_DEFAULT));
//				this.curEditItem.setLonLocation(data.getDoubleExtra(
//						IntentConstants.INTENT_LONG,
//						Constants.ID_DOUBLE_ENTITY_DEFAULT));
//				this.curEditItem.setEdited(true);
//				// listSupervisionGS.
//				this.supervisionAdapter.notifyDataSetChanged();
//			}
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
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

	private void setHeader() {
		final Header myActionBar = (Header) spvBTS_CatWorkView
				.findViewById(R.id.actionbar);
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
				requestSync(SupervisionBts_CNV_Edit_Activity.this);
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		gotoSupervisionCNVCActivity(getIntent().getExtras());
		finish();
	}

	public RelativeLayout getSupervision_bts_cnv_cb_thietke() {
		return supervision_bts_cnv_cb_thietke;
	}

	public void setSupervision_bts_cnv_cb_thietke(
			RelativeLayout supervision_bts_cnv_cb_thietke) {
		this.supervision_bts_cnv_cb_thietke = supervision_bts_cnv_cb_thietke;
	}

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:

			refreshForm();
			// closeProgressDialog();
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
	// KienPV add new 09/09/2016
	public void gotoSupvConstrType(int type, int itemId,Bundle bundleMonitorData) {
		switch (type) {
		case Constants.CONSTR_TYPE.BTS:
			gotoBtsActivity(bundleMonitorData, type);
//				switch (itemId) {
//				case Constants.BTS_INFO.THIET_TKE_INFO:
//					gotoSupervisionBtsActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO:
//					gotoSupervisionBtsPillarActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO:
//					gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.KEO_DIEN_INFO:
//					gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO:
//					gotoSupervisionBtsEquipActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.LAP_DAT_VIBA_INFO:
//					gotoSupervisionBtsVibaActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO:
//					gotoSupervisionBtsMeasureActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.MEASURE_CONSTR_INFO:
//					 gotoSupervisionMeasureConstrActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG:
//					gotoSupervisionCNDTCActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.CAP_NHAT_VUONG:
//					 closeProgressDialog();
//					break;
//				case Constants.BTS_INFO.KET_LUAN_INFO:
//					gotoSupervisionBtsKLActivity(bundleMonitorData);
//					break;
//				default:
//					break;
//				}
			break;
		case Constants.CONSTR_TYPE.LINE_BACKGROUND:
			switch (itemId) {
			case Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO:
				gotoSupervisionBtsActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO:
			case Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO:
			case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.LINE_BACKGROUND_INFO.MEASURE_CONSTR_INFO:
				 gotoSupervisionMeasureConstrActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;			
			default:
				break;
			}
			
			break;
		case Constants.CONSTR_TYPE.LINE_HANG:
				switch (itemId) {
				case Constants.LINE_HANG_INFO.THIE_TKE_INFO:		
					gotoSupervisionBtsActivity(bundleMonitorData);
					break;
				case Constants.LINE_HANG_INFO.GS_COT_INFO:
				case Constants.LINE_HANG_INFO.GS_CAP_INFO:
				case Constants.LINE_HANG_INFO.GS_HNDK_INFO:
				case Constants.LINE_HANG_INFO.CAP_NHAT_VUONG:
					break;
				case Constants.LINE_HANG_INFO.MEASURE_CONSTR_INFO:
					gotoSupervisionMeasureConstrActivity(bundleMonitorData);
					break;
				case Constants.LINE_HANG_INFO.CAP_NHAT_DOI_THI_CONG:
					gotoSupervisionCNDTCActivity(bundleMonitorData);
					break;				
				case Constants.LINE_HANG_INFO.KET_LUAN_INFO:
					gotoSupervisionBtsKLActivity(bundleMonitorData);
					break;
				default:
					break;
				}
			break;
		case Constants.CONSTR_TYPE.BRCD:
			switch (itemId) {
			case Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK:
			case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC:
			case Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC:
			case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH:
			case Constants.BRCD_BACKGROUND_INFO.TU_NHANH:
			case Constants.BRCD_BACKGROUND_INFO.DROP_GO:
			case Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO:
				// no method
				break;
			case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			case Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;
			default:
				break;
			}
			break;
		case Constants.CONSTR_TYPE.SH:
			 switch (itemId) {
			case Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI:
			case Constants.SUBHEADEND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.SUBHEADEND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;
			case Constants.SUBHEADEND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
   //---

}
