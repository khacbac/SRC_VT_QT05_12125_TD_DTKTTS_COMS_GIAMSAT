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
import com.viettel.database.Supervision_BRCD_Sub_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BRCD_Sub_Entity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
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
public class Supervision_Sub_KL_Activity extends LineBaseActivity {
	/* controll */
	private View spvSUB_KLView;
	private TextView rl_supervision_sub_equip_tv_thietke;
	private TextView rl_supervision_sub_equip_tv_matram;
	private TextView rl_supervision_sub_equip_tv_mact;
	private CheckBox rd_sub_kl_tt_dat;
	private CheckBox rd_sub_kl_tt_khongdat;
	// private RadioButton rd_line_hg_kl_td_hoanthanh;
	// private RadioButton rd_line_hg_kl_td_chuahoanthanh;
	private Button btn_sub_kl_save;
	/* value int */
	private int iDesignInfo = 2;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemEmployeeData;
	/* Tuyen ngam giam sat */
	private Supervision_Line_HangEntity supervision_Line_HangData;

	// private List<Supervision_Line_Hg_FiberEntity> listLineHGFiber;
	private Supervision_BRCD_Sub_Entity SupervisionSub;

	// private Supervision_Line_Hg_FiberController
	// supervisionLineFiberController;
	private Supervision_BRCD_Sub_Controller supervisionSubController;
	private Supervision_ConstrController supervisionConstroller;
	private long supervition_brcd_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_sub_kl_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		setData();
	}

	private void initView() {
		spvSUB_KLView = addView(R.layout.supervision_sub_kl_activity, R.id.rl_login);
		this.rl_supervision_sub_equip_tv_thietke = (TextView) spvSUB_KLView
				.findViewById(R.id.rl_supervision_sub_equip_tv_thietke);
		this.rl_supervision_sub_equip_tv_thietke.setOnClickListener(this);

		this.rl_supervision_sub_equip_tv_matram = (TextView) spvSUB_KLView
				.findViewById(R.id.rl_supervision_sub_equip_tv_matram);

		this.rl_supervision_sub_equip_tv_mact = (TextView) spvSUB_KLView
				.findViewById(R.id.rl_supervision_sub_equip_tv_mact);
		this.btn_sub_kl_save = (Button) spvSUB_KLView.findViewById(R.id.btn_sub_kl_save);
		this.btn_sub_kl_save.setOnClickListener(this);

		this.rd_sub_kl_tt_dat = (CheckBox) spvSUB_KLView
				.findViewById(R.id.rd_sub_kl_tt_dat);
		rd_sub_kl_tt_dat.setOnClickListener(this);

		this.rd_sub_kl_tt_khongdat = (CheckBox) spvSUB_KLView
				.findViewById(R.id.rd_sub_kl_tt_khongdat);
		rd_sub_kl_tt_khongdat.setOnClickListener(this);

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
		case R.id.rd_sub_kl_tt_dat:
			if (rd_sub_kl_tt_dat.isChecked()) {
				rd_sub_kl_tt_khongdat.setChecked(false);
			}
			break;
		// click chon khong dat
		case R.id.rd_sub_kl_tt_khongdat:
			if (rd_sub_kl_tt_khongdat.isChecked()) {
				rd_sub_kl_tt_dat.setChecked(false);
			}
			break;
		case R.id.rl_supervision_sub_equip_tv_thietke:
			this.listDesignInfo = GloablUtils.getListSubInfo("");
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.btn_sub_kl_save:
			String sMessage = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(sMessage)) {
				this.showDialog(sMessage);
				break;
			} else if (rd_sub_kl_tt_dat.isChecked()) {
				String sMessageKhongDat = this.checkKhongDat();
				if (!StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					this.showDialog(sMessageKhongDat);
					break;
				}
			} else if (rd_sub_kl_tt_khongdat.isChecked()) {
				String sMessageDat = this.checkDat();
				if (!StringUtil.isNullOrEmpty(sMessageDat)) {
					this.showDialog(sMessageDat);
					break;
				}
			}
			String sStatus = "";
			if (this.rd_sub_kl_tt_dat.isChecked()) {
				sStatus = rd_sub_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_sub_kl_tt_khongdat.getText().toString();
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
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoSUBActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
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
			if (this.rd_sub_kl_tt_dat.isChecked()) {
				sStatus = rd_sub_kl_tt_dat.getText().toString();
			} else {
				sStatus = rd_sub_kl_tt_khongdat.getText().toString();
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
		final Header myActionBar = (Header) spvSUB_KLView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Sub_KL_Activity.this);
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

			this.rl_supervision_sub_equip_tv_thietke.setText(GloablUtils
					.getStringSubInfo(iDesignInfo));
			this.rl_supervision_sub_equip_tv_matram.setText(itemEmployeeData
					.getStationCode());
			this.rl_supervision_sub_equip_tv_mact.setText(this.itemEmployeeData
					.getConstrCode());
			/* Drop down */

			this.listDesignInfo = GloablUtils.getListSubInfo("");
			this.supervition_brcd_id = bundleData
					.getLong(IntentConstants.INTENT_BRCD_ID);
			this.supervisionSubController = new Supervision_BRCD_Sub_Controller(
					this);

			this.SupervisionSub = this.supervisionSubController
					.getSupervisionBRCD_SUB(itemEmployeeData
							.getSupervision_Constr_Id());

			// lay du lieu phan ket luan
			Supervision_ConstrEntity constrItem = supervisionConstroller
					.getItem(itemEmployeeData.getSupervision_Constr_Id());

			if (constrItem.getSupervision_Progress() == Constants.SUPERVISION_PROGRESS.COMPLETED) {
				if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.DAT) {
					rd_sub_kl_tt_dat.setChecked(true);
				} else {
					rd_sub_kl_tt_khongdat.setChecked(true);
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
				this.btn_sub_kl_save.setVisibility(View.GONE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String checkVailid() {
		String sResult = "";
		if (!rd_sub_kl_tt_dat.isChecked() && !rd_sub_kl_tt_khongdat.isChecked()) {
			sResult = StringUtil
					.getString(R.string.supervision_bts_kl_danhgia_chooice_status_null);
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
		if (this.SupervisionSub.getStatus() == 0) {
			sResult = StringUtil.getString(R.string.sub_hg_mx_kl_mx_khongdat);
		}

		return sResult;
	}

	private String checkDat() {
		String sResult = "";
		if (this.SupervisionSub.getStatus() == 1) {
			sResult = StringUtil.getString(R.string.sub_hg_mx_kl_mx_dat);
		}

		return sResult;
	}

	private void saveDataKL() {
		try {
			/* update SUPERVISION_CONSTR */
			Supervision_ConstrController superConstrController = new Supervision_ConstrController(
					this);
			int iStatus = -1;
			if (this.rd_sub_kl_tt_dat.isChecked()) {
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