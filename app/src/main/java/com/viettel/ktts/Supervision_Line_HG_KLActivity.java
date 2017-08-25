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
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_Hg_CableController;
import com.viettel.database.Supervision_Line_Hg_MxController;
import com.viettel.database.Supervision_Line_Hg_PillarController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_HG_PillarGSEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
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
public class Supervision_Line_HG_KLActivity extends LineBaseActivity {
	/* controll */
	private View spvLine_HGKLView;
	private TextView tv_line_hg_kl_dropdown;
	private TextView tv_line_hg_kl_info_line_station_code_value;
	private TextView tv_line_hg_kl_info_line_value;
	private CheckBox rd_line_hg_kl_tt_dat;
	private CheckBox rd_line_hg_kl_tt_khongdat;
	// private RadioButton rd_line_hg_kl_td_hoanthanh;
	// private RadioButton rd_line_hg_kl_td_chuahoanthanh;
	private Button btn_line_hg_kl_save;
	/* value int */
	private int iDesignInfo = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemEmployeeData;
	/* Tuyen ngam giam sat */
	private Supervision_Line_HangEntity supervision_Line_HangData;

	// private List<Supervision_Line_Hg_FiberEntity> listLineHGFiber;
	private List<Supervision_Line_Hg_CableGSEntity> listSupervisionCableGS;
	private List<Supervision_Line_HG_PillarGSEntity> listSupervisionPillarGS;
	private List<Supervision_Line_HG_MXGSEntity> listLineHGMX;

	// private Supervision_Line_Hg_FiberController
	// supervisionLineFiberController;
	private Supervision_Line_Hg_CableController supervisionLHGCableController;
	private Supervision_Line_Hg_MxController supervisionLineHGMXController;
	private Supervision_Line_Hg_PillarController supervisionLHGPillarController;
	private Supervision_ConstrController supervisionConstroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_hg_kl_activity);
		setTitle(getString(R.string.line_hang_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		setData();
	}

	private void initView() {
		spvLine_HGKLView = addView(R.layout.supervision_line_hg_kl_activity, R.id.rl_login);
		this.tv_line_hg_kl_dropdown = (TextView) spvLine_HGKLView
				.findViewById(R.id.tv_line_hg_kl_dropdown);
		this.tv_line_hg_kl_dropdown.setOnClickListener(this);

		this.tv_line_hg_kl_info_line_station_code_value = (TextView) spvLine_HGKLView
				.findViewById(R.id.tv_line_hg_kl_info_line_station_code_value);

		this.tv_line_hg_kl_info_line_value = (TextView) spvLine_HGKLView
				.findViewById(R.id.tv_line_hg_kl_info_line_value);
		this.btn_line_hg_kl_save = (Button) spvLine_HGKLView
				.findViewById(R.id.btn_line_hg_kl_save);
		this.btn_line_hg_kl_save.setOnClickListener(this);

		this.rd_line_hg_kl_tt_dat = (CheckBox) spvLine_HGKLView
				.findViewById(R.id.rd_line_hg_kl_tt_dat);
		rd_line_hg_kl_tt_dat.setOnClickListener(this);

		this.rd_line_hg_kl_tt_khongdat = (CheckBox) spvLine_HGKLView
				.findViewById(R.id.rd_line_hg_kl_tt_khongdat);
		rd_line_hg_kl_tt_khongdat.setOnClickListener(this);

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
		case R.id.rd_line_hg_kl_tt_dat:
			if (rd_line_hg_kl_tt_dat.isChecked()) {
				rd_line_hg_kl_tt_khongdat.setChecked(false);
			}
			break;
		// click chon khong dat
		case R.id.rd_line_hg_kl_tt_khongdat:
			if (rd_line_hg_kl_tt_khongdat.isChecked()) {
				rd_line_hg_kl_tt_dat.setChecked(false);
			}
			break;
		case R.id.tv_line_hg_kl_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_line_hg_kl_save:
			String sMessage = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(sMessage)) {
				this.showDialog(sMessage);
				break;
			} else if (rd_line_hg_kl_tt_dat.isChecked()) {
				String sMessageKhongDat = this.checkKhongDat();
				if (!StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					this.showDialog(sMessageKhongDat);
					break;
				}
			}
			String sStatus = "";
			if (this.rd_line_hg_kl_tt_dat.isChecked()) {
				sStatus = rd_line_hg_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_line_hg_kl_tt_khongdat.getText().toString();
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
		case OnEventControlListener.EVENT_SUPERVISION_BG_OK:
			new SaveAsyncTask().execute();
			// this.saveDataKL();
			break;
		case OnEventControlListener.EVENT_CONFIRM_TANK_SAVE_DAT_OK:
			String sStatus = "";
			if (this.rd_line_hg_kl_tt_dat.isChecked()) {
				sStatus = rd_line_hg_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_line_hg_kl_tt_khongdat.getText().toString();
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
		final Header myActionBar = (Header) spvLine_HGKLView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_HG_KLActivity.this);
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
			this.tv_line_hg_kl_dropdown.setText(GloablUtils
					.getStringLineHangInfo(iDesignInfo));
			this.tv_line_hg_kl_info_line_station_code_value
					.setText(itemEmployeeData.getStationCode());
			this.tv_line_hg_kl_info_line_value.setText(this.itemEmployeeData
					.getConstrCode());
			/* Drop down */
			this.listDesignInfo = GloablUtils.getListLineHangInfo("");
			this.supervisionLHGCableController = new Supervision_Line_Hg_CableController(
					this);
			this.supervisionLineHGMXController = new Supervision_Line_Hg_MxController(
					this);
			this.supervisionLHGPillarController = new Supervision_Line_Hg_PillarController(
					this);
			this.listLineHGMX = this.supervisionLineHGMXController
					.getAllMXGSBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());
			this.listSupervisionCableGS = this.supervisionLHGCableController
					.getAllCableGSBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());
			this.listSupervisionPillarGS = supervisionLHGPillarController
					.getAllPillarGSBySupervistionLineHang(this.supervision_Line_HangData
							.getSupervision_Line_Hang_Id());

			// lay du lieu phan ket luan
			Supervision_ConstrEntity constrItem = supervisionConstroller
					.getItem(itemEmployeeData.getSupervision_Constr_Id());

			if (constrItem.getSupervision_Progress() == Constants.SUPERVISION_PROGRESS.COMPLETED) {
				if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.DAT) {
					rd_line_hg_kl_tt_dat.setChecked(true);
				} else {
					rd_line_hg_kl_tt_khongdat.setChecked(true);
				}
			}

			// if(constrItem.getSupervision_Progress() ==
			// Constants.SUPERVISION_PROGRESS.COMPLETED){
			// rd_line_hg_kl_td_hoanthanh.setChecked(true);
			// } else {
			// rd_line_hg_kl_td_chuahoanthanh.setChecked(true);
			// }

			if (itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemEmployeeData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_line_hg_kl_save.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String checkVailid() {
		String sResult = "";
		if (!rd_line_hg_kl_tt_dat.isChecked()
				&& !rd_line_hg_kl_tt_khongdat.isChecked()) {
			sResult = StringUtil
					.getString(R.string.supervision_bts_kl_danhgia_chooice_status_null);
			return sResult;
		}
		/* kiem tra thong tin do soi */
		/* vailid du lieu do kiem */
		if (this.supervision_Line_HangData.getMeasure_Status() == Constants.SEARCH_VALUE_DEFAULT) {
			sResult = StringUtil.getString(R.string.line_hg_mx_fiber_status);
			return sResult;
		}
		if (StringUtil.isNullOrEmpty(this.supervision_Line_HangData
				.getMeasure_Person())) {
			sResult = StringUtil.getString(R.string.line_hg_mx_fiber_nd);
			return sResult;
		}
		// if (StringUtil.isNullOrEmpty(this.supervision_Line_HangData
		// .getMeasure_Machine_Type())) {
		// sResult = StringUtil.getString(R.string.line_hg_mx_fiber_md);
		// return sResult;
		// }
		if (StringUtil.isNullOrEmpty(this.supervision_Line_HangData
				.getMeasure_Group())) {
			sResult = StringUtil.getString(R.string.line_hg_mx_fiber_dv);
			return sResult;
		}
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
		if (this.listLineHGMX.size() > 0) {
			sResult = StringUtil.getString(R.string.line_hg_mx_kl_mx_khongdat);
			return sResult;
		}

		int numberCableKDat = 0;
		for (Supervision_Line_Hg_CableGSEntity itemCableGS : listSupervisionCableGS) {
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
		if (this.listSupervisionPillarGS.size() > 0) {
			sResult = StringUtil.getString(R.string.line_hg_pl_kl_khongdat);
			return sResult;
		}
		if (this.supervision_Line_HangData.getMeasure_Status() == Constants.SUPERVISION_STATUS.CHUADAT) {
			sResult = StringUtil
					.getString(R.string.line_hg_mx_kl_fiber_khongdat);
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
			if (this.rd_line_hg_kl_tt_dat.isChecked()) {
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