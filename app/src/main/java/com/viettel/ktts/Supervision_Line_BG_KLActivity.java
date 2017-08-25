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
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_CableController;
import com.viettel.database.Supervision_Line_BG_MxController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_CableGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.dialog.ConclusionDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 * 
 */
public class Supervision_Line_BG_KLActivity extends LineBaseActivity {
	/* controll */
	private View spvLineBG_KLView;
	private TextView tv_line_bg_kl_dropdown;
	private TextView tv_line_bg_kl_info_line_station_code_value;
	private TextView tv_line_bg_kl_info_line_value;
	private CheckBox rd_line_bg_kl_tt_dat;
	private CheckBox rd_line_bg_kl_tt_khongdat;
	// private RadioButton rd_line_bg_kl_td_hoanthanh;
	// private RadioButton rd_line_bg_kl_td_chuahoanthanh;
	private Button btn_line_bg_kl_save;
	/* value int */
	private int iDesignInfo = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemEmployeeData;
	/* Tuyen ngam giam sat */
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private List<Supervision_Line_BG_CableGSEntity> listSupervisionCableGS;
	private List<Supervision_Line_BG_MXGSEntity> listLineBGMX;
	private Supervision_Line_BG_TankController supervisionLBGController;
	private Cause_Line_BG_TankController causeLineBGTankController;

	private Supervision_Line_BG_CableController supervisionLBGCableController;
	private Supervision_Line_BG_MxController supervisionLineBGMXController;
	private Supervision_ConstrController supervisionConstroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_kl_activity);
		setTitle(getString(R.string.line_background_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		setData();
	}

	private void initView() {
		spvLineBG_KLView = addView(R.layout.supervision_line_bg_kl_activity, R.id.rl_login);
		this.tv_line_bg_kl_dropdown = (TextView) spvLineBG_KLView
				.findViewById(R.id.tv_line_bg_kl_dropdown);
		this.tv_line_bg_kl_dropdown.setOnClickListener(this);

		this.tv_line_bg_kl_info_line_station_code_value = (TextView) spvLineBG_KLView
				.findViewById(R.id.tv_line_bg_kl_info_line_station_code_value);

		this.tv_line_bg_kl_info_line_value = (TextView) spvLineBG_KLView
				.findViewById(R.id.tv_line_bg_kl_info_line_value);
		this.btn_line_bg_kl_save = (Button) spvLineBG_KLView
				.findViewById(R.id.btn_line_bg_kl_save);
		this.btn_line_bg_kl_save.setOnClickListener(this);

		this.rd_line_bg_kl_tt_dat = (CheckBox) spvLineBG_KLView
				.findViewById(R.id.rd_line_bg_kl_tt_dat);
		rd_line_bg_kl_tt_dat.setOnClickListener(this);

		this.rd_line_bg_kl_tt_khongdat = (CheckBox) spvLineBG_KLView
				.findViewById(R.id.rd_line_bg_kl_tt_khongdat);
		rd_line_bg_kl_tt_khongdat.setOnClickListener(this);

		// this.rd_line_bg_kl_td_hoanthanh = (RadioButton) this
		// .findViewById(R.id.rd_line_bg_kl_td_hoanthanh);
		//
		// this.rd_line_bg_kl_td_chuahoanthanh = (RadioButton) this
		// .findViewById(R.id.rd_line_bg_kl_td_chuahoanthanh);

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
		case R.id.rd_line_bg_kl_tt_dat:
			if (rd_line_bg_kl_tt_dat.isChecked()) {
				rd_line_bg_kl_tt_khongdat.setChecked(false);
			}
			break;
		// click chon khong dat
		case R.id.rd_line_bg_kl_tt_khongdat:
			if (rd_line_bg_kl_tt_khongdat.isChecked()) {
				rd_line_bg_kl_tt_dat.setChecked(false);
			}
			break;
		case R.id.tv_line_bg_kl_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_line_bg_kl_save:
			String sMessage = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(sMessage)) {
				this.showDialog(sMessage);
				break;
			} else if (rd_line_bg_kl_tt_dat.isChecked()) {
				String sMessageKhongDat = this.checkKhongDat();
				if (!StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					this.showDialog(sMessageKhongDat);
					break;
				}
			}
			String sStatus = "";
			if (this.rd_line_bg_kl_tt_dat.isChecked()) {
				sStatus = rd_line_bg_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_line_bg_kl_tt_khongdat.getText().toString();
			}
			String sProgress = StringUtil
					.getString(R.string.constr_sonstruction_progress_complete);
			// String sProgress = "";
			// if (this.rd_line_bg_kl_td_hoanthanh.isChecked()) {
			// sProgress = rd_line_bg_kl_td_hoanthanh.getText().toString();
			// } else {
			// sProgress = rd_line_bg_kl_td_chuahoanthanh.getText().toString();
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
		case OnEventControlListener.EVENT_SUPERVISION_BG_OK:
			new SaveAsyncTask().execute();
			// this.saveDataKL();
			break;
		case OnEventControlListener.EVENT_CONFIRM_TANK_SAVE_DAT_OK:
			String sStatus = "";
			if (this.rd_line_bg_kl_tt_dat.isChecked()) {
				sStatus = rd_line_bg_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_line_bg_kl_tt_khongdat.getText().toString();
			}
			String sProgress = StringUtil
					.getString(R.string.constr_sonstruction_progress_complete);
			// String sProgress = "";
			// if (this.rd_line_bg_kl_td_hoanthanh.isChecked()) {
			// sProgress = rd_line_bg_kl_td_hoanthanh.getText().toString();
			// } else {
			// sProgress = rd_line_bg_kl_td_chuahoanthanh.getText().toString();
			// }
			ConclusionDialog conDialog = new ConclusionDialog(this, sStatus,
					sProgress);
			conDialog.show();
			break;
		case OnEventControlListener.EVENT_CONFIRM_BG_KL_OK:
			this.gotoHomeActivity(new Bundle());
			this.finish();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineBG_KLView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_KLActivity.this);
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
			this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA_EX);
			this.tv_line_bg_kl_info_line_station_code_value
					.setText(itemEmployeeData.getStationCode());
			this.tv_line_bg_kl_info_line_value.setText(this.itemEmployeeData
					.getConstrCode());
			this.iDesignInfo = bundleData
					.getInt(IntentConstants.INTENT_DESIGNINFO);
			this.tv_line_bg_kl_dropdown.setText(GloablUtils
					.getStringLineBackgroundInfo(iDesignInfo));

			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");
			supervisionLBGController = new Supervision_Line_BG_TankController(
					this);
			causeLineBGTankController = new Cause_Line_BG_TankController(this);
			listSupervisionGS = supervisionLBGController
					.getAllTankGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
			/* Truong hop Load luu du lieu */
			if (listSupervisionGS.size() == 0) {
				long iNumberTank = this.supervision_Line_BackgroundData
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
					List<Supervision_LBG_UnqualifyItemEntity> curUnqualifyItem = this.causeLineBGTankController
							.getAllTankUnqulifyByTankId(curItemSupervisonGS
									.getIdTank());
					curItemSupervisonGS
							.setListCauseUniQualify(curUnqualifyItem);
				}
			}
			// this.supervisionLineFiberController = new
			// Supervision_Line_BG_FiberController(
			// this);
			// this.listLineBGFiber = this.supervisionLineFiberController
			// .getAllFiberBySupervistionLineBackground(this.supervision_Line_BackgroundData
			// .getSupervision_Line_Background_Id());

			supervisionLBGCableController = new Supervision_Line_BG_CableController(
					this);
			listSupervisionCableGS = supervisionLBGCableController
					.getAllCableGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());

			supervisionLineBGMXController = new Supervision_Line_BG_MxController(
					this);
			this.listLineBGMX = this.supervisionLineBGMXController
					.getAllMXGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());

			// lay du lieu phan ket luan
			Supervision_ConstrEntity constrItem = supervisionConstroller
					.getItem(itemEmployeeData.getSupervision_Constr_Id());

			if (constrItem.getSupervision_Progress() == Constants.SUPERVISION_PROGRESS.COMPLETED) {
				if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.DAT) {
					rd_line_bg_kl_tt_dat.setChecked(true);
				} else {
					rd_line_bg_kl_tt_khongdat.setChecked(true);
				}
			}

			// if(constrItem.getSupervision_Progress() ==
			// Constants.SUPERVISION_PROGRESS.COMPLETED){
			// rd_line_bg_kl_td_hoanthanh.setChecked(true);
			// } else {
			// rd_line_bg_kl_td_chuahoanthanh.setChecked(true);
			// }

			/* Set endable va disable voi cong trinh da hoan thanh */
			if (itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_bg_kl_save.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.gotoHomeActivity(new Bundle());
		}
	}

	private String checkVailid() {
		String sResult = "";
		if (!rd_line_bg_kl_tt_dat.isChecked()
				&& !rd_line_bg_kl_tt_khongdat.isChecked()) {
			sResult = StringUtil
					.getString(R.string.supervision_bts_kl_danhgia_chooice_status_null);
			return sResult;
		}
		/* Kiem tra giam sat be cap */
		if (listSupervisionGS.size() == 0) {
			sResult = StringUtil.getString(R.string.line_bg_mx_kl_no_add_tank);
			return sResult;
		}
		/*
		 * if (this.listLineBGFiber.size() == 0) { sResult =
		 * StringUtil.getString(R.string.line_bg_mx_kl_no_add_fiber); return
		 * sResult; }
		 */
		/* kiem tra thong tin do soi */
		/* vailid du lieu do kiem */
		if (this.supervision_Line_BackgroundData.getMeasure_Status() == Constants.SEARCH_VALUE_DEFAULT) {
			sResult = StringUtil.getString(R.string.line_bg_mx_fiber_status);
			return sResult;
		}
		if (StringUtil.isNullOrEmpty(this.supervision_Line_BackgroundData
				.getMeasure_Person())) {
			sResult = StringUtil.getString(R.string.line_bg_mx_fiber_nd);
			return sResult;
		}
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_BackgroundData
		// .getMeasure_Machine_Type())) {
		// sResult = StringUtil.getString(R.string.line_bg_mx_fiber_md);
		// return sResult;
		// }
		if (StringUtil.isNullOrEmpty(this.supervision_Line_BackgroundData
				.getMeasure_Group())) {
			sResult = StringUtil.getString(R.string.line_bg_mx_fiber_dv);
			return sResult;
		}
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_BackgroundData
		// .getMeasure_Person_Mobile())) {
		// sResult = StringUtil.getString(R.string.line_bg_mx_fiber_sdt);
		// return sResult;
		// }
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_BackgroundData
		// .getMeasure_Machine_Serial())) {
		// sResult = StringUtil.getString(R.string.line_bg_mx_fiber_serialmay);
		// return sResult;
		// }
		return sResult;
	}

	/* Cảnh báo có phần không đạt */
	private String checkKhongDat() {
		String sResult = "";
		for (Supervision_Line_BG_TankGSEntity checkItem : listSupervisionGS) {
			if (checkItem.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sResult = StringUtil
						.getString(R.string.line_bg_mx_kl_tank_khongdat);
				return sResult;
			}
		}
		// if (this.listSupervisionCableGS.size() > 0) {
		// sResult = StringUtil
		// .getString(R.string.line_bg_mx_kl_cable_khongdat);
		// return sResult;
		// }
		int numberCableKDat = 0;
		for (Supervision_Line_BG_CableGSEntity itemCableGS : listSupervisionCableGS) {
			if (itemCableGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				numberCableKDat++;
				break;
			}
		}
		if (numberCableKDat > 0) {
			sResult = StringUtil
					.getString(R.string.line_hg_mx_kl_cable_khongdat);
			return sResult;
		}
		if (this.listLineBGMX.size() > 0) {
			sResult = StringUtil.getString(R.string.line_bg_mx_kl_mx_khongdat);
			return sResult;
		}
		if (this.supervision_Line_BackgroundData.getMeasure_Status() == Constants.SUPERVISION_STATUS.CHUADAT) {
			sResult = StringUtil
					.getString(R.string.line_bg_mx_kl_fiber_khongdat);
			return sResult;
		}
		return sResult;
	}

	private void saveDataKL() {
		try {
			/* update SUPERVISION_CONSTR */
			Supervision_ConstrController superConstrController = new Supervision_ConstrController(
					this);
			int iStatus = -1;
			if (this.rd_line_bg_kl_tt_dat.isChecked()) {
				iStatus = Constants.SUPERVISION_STATUS.DAT;
			} else {
				iStatus = Constants.SUPERVISION_STATUS.CHUADAT;
			}
			int iProgress = Constants.SUPERVISION_PROGRESS.COMPLETED;
			// if (this.rd_line_bg_kl_td_hoanthanh.isChecked()) {
			// iProgress = Constants.SUPERVISION_PROGRESS.COMPLETED;
			// } else {
			// iProgress = Constants.SUPERVISION_PROGRESS.UNCOMPLETED;
			// }
			String sNoteCode = superConstrController.getNoteCode(GlobalInfo
					.getInstance().getGroupCode());

			superConstrController.updateStatusProgegressCode(
					this.itemEmployeeData.getConstructId(), iStatus, iProgress,
					sNoteCode);

			this.showDialog(StringUtil.getString(R.string.text_update_success),
					OnEventControlListener.EVENT_CONFIRM_BG_KL_OK);
		} catch (Exception e) {
			e.printStackTrace();
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