package com.viettel.ktts;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_DropGo_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_MxController;
import com.viettel.database.Supervision_BRCD_Tn_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_DropGo_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_MXEntity;
import com.viettel.database.entity.Supervision_BRCD_Tn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.dialog.ConclusionDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_KLActivity extends LineBaseActivity {
	/* controll */
	private View spvBRCD_KLView;
	private TextView rl_supervision_brcd_equip_tv_thietke;
	private TextView rl_supervision_brcd_equip_tv_matram;
	private TextView rl_supervision_brcd_equip_tv_mact;
	private CheckBox rd_brcd_kl_tt_dat;
	private CheckBox rd_brcd_kl_tt_khongdat;
	// private RadioButton rd_line_hg_kl_td_hoanthanh;
	// private RadioButton rd_line_hg_kl_td_chuahoanthanh;
	private Button btn_brcd_kl_save;
	/* value int */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemEmployeeData;
	/* Tuyen ngam giam sat */
	private Supervision_Line_HangEntity supervision_Line_HangData;

	// private List<Supervision_Line_Hg_FiberEntity> listLineHGFiber;
	private List<Supervision_BRCD_GiamSat_DropGo_Entity> listSupervisionGiamSatDropGo;
	private List<Supervision_BRCD_GiamSat_DropGo_Entity> listSupervisionGiamSatAllDropGo;
	private List<Supervision_BRCD_Drop_Entity> listSupervisionDropGo;
	private List<Supervision_BRCD_GiamSat_Kcn_Entity> listSupervisionGiamSatKcn;
	private List<Supervision_BRCD_GiamSat_Kcn_Entity> listSupervisionGiamSatAllKcn;
	private List<Supervision_BRCD_Kcn_Entity> listSupervisionKcn;
	private List<Supervision_BRCD_Kct_Entity> listSupervisionKct;
	private List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSKct;
	private List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSAllKct;
	private Supervision_BRCD_MXEntity listSupervisionMt;
	private List<Supervision_BRCD_Tn_Entity> listSupervisionTn;
	private List<Supervision_BRCD_Ttb_Entity> listSupervisionTtb;

	// private Supervision_Line_Hg_FiberController
	// supervisionLineFiberController;
	private Supervision_BRCD_GiamSat_DropGo_Controller supervisionGiamSatDropGoController;
	private Supervision_BRCD_GiamSat_Kcn_Controller supervisionGiamSatKcnController;
	private Supervision_BRCD_GiamSat_Kct_Controller supervisionGiamSatKctController;
	private Supervision_BRCD_Drop_Controller supervisionDropGoController;
	private Supervision_BRCD_Kcn_Controller supervisionKcnController;
	private Supervision_BRCD_Kct_Controller supervisionKctController;
	private Supervision_BRCD_MxController supervisionMxConstroller;
	private Supervision_BRCD_Tn_Controller supervisionTnController;

	private Supervision_BRCD_Ttb_Controller supervisionTtbController;
	private Supervision_ConstrController supervisionConstroller;
	private long supervition_brcd_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_kl_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		setData();
	}

	private void initView() {
		spvBRCD_KLView = addView(R.layout.supervision_brcd_kl_activity, R.id.rl_login);
		this.rl_supervision_brcd_equip_tv_thietke = (TextView) spvBRCD_KLView
				.findViewById(R.id.rl_supervision_brcd_equip_tv_thietke);
		this.rl_supervision_brcd_equip_tv_thietke.setOnClickListener(this);

		this.rl_supervision_brcd_equip_tv_matram = (TextView) spvBRCD_KLView
				.findViewById(R.id.rl_supervision_brcd_equip_tv_matram);

		this.rl_supervision_brcd_equip_tv_mact = (TextView) spvBRCD_KLView
				.findViewById(R.id.rl_supervision_brcd_equip_tv_mact);
		this.btn_brcd_kl_save = (Button) spvBRCD_KLView
				.findViewById(R.id.btn_brcd_kl_save);
		this.btn_brcd_kl_save.setOnClickListener(this);

		this.rd_brcd_kl_tt_dat = (CheckBox) spvBRCD_KLView
				.findViewById(R.id.rd_brcd_kl_tt_dat);
		rd_brcd_kl_tt_dat.setOnClickListener(this);

		this.rd_brcd_kl_tt_khongdat = (CheckBox) spvBRCD_KLView
				.findViewById(R.id.rd_brcd_kl_tt_khongdat);
		rd_brcd_kl_tt_khongdat.setOnClickListener(this);

		// this.rd_line_hg_kl_td_hoanthanh = (RadioButton) this
		// .findViewById(R.id.rd_line_hg_kl_td_hoanthanh);
		//
		// this.rd_line_hg_kl_td_chuahoanthanh = (RadioButton) this
		// .findViewById(R.id.rd_line_hg_kl_td_chuahoanthanh);

		supervisionConstroller = new Supervision_ConstrController(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click chon dat
		case R.id.rd_brcd_kl_tt_dat:
			if (rd_brcd_kl_tt_dat.isChecked()) {
				rd_brcd_kl_tt_khongdat.setChecked(false);
			}
			break;
		// click chon khong dat
		case R.id.rd_brcd_kl_tt_khongdat:
			if (rd_brcd_kl_tt_khongdat.isChecked()) {
				rd_brcd_kl_tt_dat.setChecked(false);
			}
			break;
		case R.id.rl_supervision_brcd_equip_tv_thietke:
			this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_brcd_kl_save:
			String sMessage = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(sMessage)) {
				this.showDialog(sMessage);
				break;
			} else if (rd_brcd_kl_tt_dat.isChecked()) {
				String sMessageKhongDat = "";
				sMessageKhongDat = checkKhongDat();

				if (!StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					this.showDialog(sMessageKhongDat);
				}
				
				break;
			} else if (rd_brcd_kl_tt_khongdat.isChecked()) {
				String sMessageDat = "";
				sMessageDat = checkKhongDat();

				if (StringUtil.isNullOrEmpty(sMessageDat)) {
					this.showDialog("Tất cả các công trình đã đạt nên không thể kết luận không đạt");
					break;
				}

				
			}
			String sStatus = "";
			if (this.rd_brcd_kl_tt_dat.isChecked()) {
				sStatus = rd_brcd_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_brcd_kl_tt_khongdat.getText().toString();
			}
			String sProgress = StringUtil
					.getString(R.string.constr_sonstruction_progress_complete);
			// if (this.rd_line_hg_kl_td_hoanthanh.isChecked()) {
			// sProgress = rd_line_hg_kl_td_hoanthanh.getText().toString();
			// } else {
			// sProgress = rd_line_hg_kl_td_chuahoanthanh.getText().toString();
			// }
			ConclusionDialog conDialog = new ConclusionDialog(this, sStatus,
					sProgress);
			conDialog.show();
			break;
		default:
			break;
		}
	}

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
							itemEmployeeData);
					bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
							supervition_brcd_id);
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_HangData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBRCDActivity(bundleData);
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}

			break;
		case OnEventControlListener.EVENT_SUPERVISION_BG_OK:
			new SaveAsyncTask().execute();
			// this.saveDataKL();
			break;
		case OnEventControlListener.EVENT_CONFIRM_TANK_SAVE_DAT_OK:
			String sStatus = "";
			if (this.rd_brcd_kl_tt_dat.isChecked()) {
				sStatus = rd_brcd_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_brcd_kl_tt_khongdat.getText().toString();
			}
			String sProgress = StringUtil
					.getString(R.string.constr_sonstruction_progress_complete);
			// if (this.rd_line_hg_kl_td_hoanthanh.isChecked()) {
			// sProgress = rd_line_hg_kl_td_hoanthanh.getText().toString();
			// } else {
			// sProgress = rd_line_hg_kl_td_chuahoanthanh.getText().toString();
			// }
			ConclusionDialog conDialog = new ConclusionDialog(this, sStatus,
					sProgress);
			conDialog.show();
			break;
		case OnEventControlListener.EVENT_CONFIRM_HG_KL_OK:
			this.gotoHomeActivity(new Bundle());
			this.finish();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_KLView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_KLActivity.this);
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
			Bundle bundleData = this.getIntent().getExtras();
			this.itemEmployeeData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_Line_HangData = (Supervision_Line_HangEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA_EX);

			this.iDesignInfo = bundleData
					.getInt(IntentConstants.INTENT_DESIGNINFO);

			this.rl_supervision_brcd_equip_tv_thietke.setText(GloablUtils
					.getStringBRCDBackgroundInfo(iDesignInfo));
			this.rl_supervision_brcd_equip_tv_matram.setText(itemEmployeeData
					.getStationCode());
			this.rl_supervision_brcd_equip_tv_mact
					.setText(this.itemEmployeeData.getConstrCode());
			/* Drop down */

			this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
			this.supervition_brcd_id = bundleData
					.getLong(IntentConstants.INTENT_BRCD_ID);
			this.supervisionGiamSatDropGoController = new Supervision_BRCD_GiamSat_DropGo_Controller(
					this);
			this.supervisionGiamSatKcnController = new Supervision_BRCD_GiamSat_Kcn_Controller(
					this);
			this.supervisionKctController = new Supervision_BRCD_Kct_Controller(
					this);
			this.supervisionGiamSatKctController = new Supervision_BRCD_GiamSat_Kct_Controller(
					this);
			this.supervisionKcnController = new Supervision_BRCD_Kcn_Controller(
					this);
			this.supervisionDropGoController = new Supervision_BRCD_Drop_Controller(
					this);
			this.supervisionMxConstroller = new Supervision_BRCD_MxController(
					this);
			this.supervisionTnController = new Supervision_BRCD_Tn_Controller(
					this);
			this.supervisionTtbController = new Supervision_BRCD_Ttb_Controller(
					this);

			this.listSupervisionDropGo = this.supervisionDropGoController
					.getAllbrcd_drop(supervition_brcd_id);
			this.listSupervisionKcn = this.supervisionKcnController
					.getAllbrcd_kcn(supervition_brcd_id);
			this.listSupervisionKct = supervisionKctController
					.getAllbrcd_kct(supervition_brcd_id);

			this.listSupervisionMt = this.supervisionMxConstroller
					.getSupervisionBRCD_MX(supervition_brcd_id);
			this.listSupervisionTn = this.supervisionTnController
					.getAllbrcd_tn(supervition_brcd_id);

			this.listSupervisionTtb = supervisionTtbController
					.getAllbrcd_ttb_brcd_id(supervition_brcd_id);

			for (int i = 0; i < listSupervisionDropGo.size(); i++) {
				this.listSupervisionGiamSatDropGo = this.supervisionGiamSatDropGoController
						.getAllbrcd_kcn(listSupervisionDropGo.get(i)
								.getSupervition_branch_drop_id());
				
				if (listSupervisionGiamSatAllDropGo == null) {
					listSupervisionGiamSatAllDropGo = new ArrayList<Supervision_BRCD_GiamSat_DropGo_Entity>();
				}
				
				this.listSupervisionGiamSatAllDropGo
						.addAll(listSupervisionGiamSatDropGo);

			}
			for (int i = 0; i < listSupervisionKct.size(); i++) {
				this.listSupervisionGSKct = this.supervisionGiamSatKctController
						.getAllbrcd_Kct(listSupervisionKct.get(i)
								.getSUPERVISION_TRUNK_CABLE_ID());
				if(listSupervisionGSAllKct == null){
					listSupervisionGSAllKct = new ArrayList<Supervision_BRCD_GiamSat_Kct_Entity>();
				} 
				
					this.listSupervisionGSAllKct.addAll(listSupervisionGSKct);
				
				

			}

			for (int i = 0; i < listSupervisionKcn.size(); i++) {

				this.listSupervisionGiamSatKcn = this.supervisionGiamSatKcnController
						.getAllbrcd_kcn(listSupervisionKcn.get(i)
								.getSupervition_branch_cable_id());
				
				if (listSupervisionGiamSatAllKcn == null) {
					listSupervisionGiamSatAllKcn = new ArrayList<Supervision_BRCD_GiamSat_Kcn_Entity>();
				}
				
				this.listSupervisionGiamSatAllKcn
						.addAll(listSupervisionGiamSatKcn);

			}

			// lay du lieu phan ket luan
			Supervision_ConstrEntity constrItem = supervisionConstroller
					.getItem(itemEmployeeData.getSupervision_Constr_Id());

			if (constrItem.getSupervision_Progress() == Constants.SUPERVISION_PROGRESS.COMPLETED) {
				if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.DAT) {
					rd_brcd_kl_tt_dat.setChecked(true);
				} else {
					rd_brcd_kl_tt_khongdat.setChecked(true);
				}
			}

			if (itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_brcd_kl_save.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String checkVailid() {
		String sResult = "";
		if (!rd_brcd_kl_tt_dat.isChecked()
				&& !rd_brcd_kl_tt_khongdat.isChecked()) {
			sResult = StringUtil
					.getString(R.string.supervision_bts_kl_danhgia_chooice_status_null);
			return sResult;
		}
		
		if (listSupervisionGSAllKct == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_keo_cap_truc_chua_gs);
			return sResult;
		}
		
		if (listSupervisionMt == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_mxt_chua_gs);
			return sResult;
		}
		
		if (listSupervisionGiamSatAllKcn == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_keo_cap_nhanh_chua_gs);
			return sResult;
		}
		
		if (listSupervisionTn == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_tu_nhanh_chua_gs);
			return sResult;
		}

		if (listSupervisionGiamSatAllDropGo == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_drop_go_chua_gs);
			return sResult;
		}

		if (listSupervisionTtb == null) {
			sResult = StringUtil
					.getString(R.string.txt_thicong_tu_thue_bao_chua_gs);
			return sResult;
		}
		/* kiem tra thong tin do soi */
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_HangData
		// .getMeasure_Person_Mobile())) {
		// sResult = StringUtil.getString(R.string.line_hg_mx_fiber_sdt);
		// return sResult;
		// }
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_HangData
		// .getMeasure_Machine_Serial())) {
		// sResult = StringUtil.getString(R.string.line_hg_mx_fiber_serialmay);
		// return sResult;
		// }
		return sResult;
	}

	/* Cảnh báo có phần không đạt */
	private String checkKhongDat() {
		String sResult = "";

		if (listSupervisionMt.getStatus() == 0) {
			sResult = StringUtil.getString(R.string.line_hg_mx_kl_mx_khongdat);
			return sResult;
		}

		for (Supervision_BRCD_GiamSat_DropGo_Entity itemDropGoGS : listSupervisionGiamSatAllDropGo) {
			if (itemDropGoGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil.getString(R.string.brcd_dropgo_khongdat);
				return sResult;
			}
		}

		for (Supervision_BRCD_GiamSat_Kcn_Entity itemKcnGS : listSupervisionGiamSatAllKcn) {
			if (itemKcnGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil.getString(R.string.brcd_kcn_khongdat);
				return sResult;
			}
		}

		for (Supervision_BRCD_GiamSat_Kct_Entity itemKct : listSupervisionGSAllKct) {
			if (itemKct.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil.getString(R.string.brcd_kct_khongdat);
				return sResult;
			}
		}

		for (Supervision_BRCD_Tn_Entity itemTn : listSupervisionTn) {
			if (itemTn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil.getString(R.string.brcd_tn_khongdat);
				return sResult;
			}
		}

		for (Supervision_BRCD_Ttb_Entity itemTtb : listSupervisionTtb) {
			if (itemTtb.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil.getString(R.string.brcd_ttb_khongdat);
				return sResult;
			}
		}

		return sResult;
	}

	private void saveDataKL() {
		try {
			/* update SUPERVISION_CONSTR */
			Supervision_ConstrController superConstrController = new Supervision_ConstrController(
					this);
			int iStatus = -1;
			if (this.rd_brcd_kl_tt_dat.isChecked()) {
				iStatus = Constants.SUPERVISION_STATUS.DAT;
			} else {
				iStatus = Constants.SUPERVISION_STATUS.CHUADAT;
			}
			int iProgress = Constants.SUPERVISION_PROGRESS.COMPLETED;
			String sNoteCode = superConstrController.getNoteCode(GlobalInfo
					.getInstance().getGroupCode());

			superConstrController.updateStatusProgegressCode(
					this.itemEmployeeData.getConstructId(), iStatus, iProgress,
					sNoteCode);
			this.showDialog(StringUtil.getString(R.string.text_update_success),
					OnEventControlListener.EVENT_CONFIRM_HG_KL_OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					saveDataKL();
				}
			});

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			closeProgressDialog();
		}

	}

}