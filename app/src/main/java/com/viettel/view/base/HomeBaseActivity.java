package com.viettel.view.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_DropGo_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_MxController;
import com.viettel.database.Supervision_BRCD_Sub_Controller;
import com.viettel.database.Supervision_BRCD_Tn_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_DropGo_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_MXEntity;
import com.viettel.database.entity.Supervision_BRCD_Sub_Entity;
import com.viettel.database.entity.Supervision_BRCD_Tn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.ktts.HomeActivity;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeBaseActivity extends BaseActivity {
	private static final String TAG = HomeBaseActivity.class.getSimpleName();

	protected void gotoMakePlanActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_PLAN_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}
	
	protected void gotoAboutActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_ABOUT_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}


	
	protected void gotoHomeActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_HOME_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoSetLineActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SET_LINE_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoSupervisionBTSActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoBrcdBackgroupActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_BRCD);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoSubBackgroupActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.CONSTR_TYPE_SUB.GS);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUB_BACKGROUND_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoLineBackgroupActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_BRCD);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_LINE_BACKGROUND_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoLineHangActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_LINE_HANG_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoBRCDActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_BRCD_TTTK_ACTIVITY;

		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoBRCDActivityFromFragment(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK);
		e.viewData = data;
//		e.action = ActionEventConstant.GOTO_SUPERVISION_BRCD_TTTK_ACTIVITY;
		e.action = ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY;

		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoSubActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_SUB_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	public boolean checkBRCDCauseDeny(Bundle bundleData) {
		String sMessage = "";

		List<Supervision_BRCD_GiamSat_Kcn_Entity> listSupervisionGiamSatAllKcn = new ArrayList<Supervision_BRCD_GiamSat_Kcn_Entity>();
		List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSAllKct = new ArrayList<Supervision_BRCD_GiamSat_Kct_Entity>();
		List<Supervision_BRCD_GiamSat_DropGo_Entity> listSupervisionGiamSatAllDropGo = new ArrayList<Supervision_BRCD_GiamSat_DropGo_Entity>();

		long supervition_brcd_id = bundleData
				.getLong(IntentConstants.INTENT_BRCD_ID);
		Supervision_BRCD_GiamSat_DropGo_Controller supervisionGiamSatDropGoController = new Supervision_BRCD_GiamSat_DropGo_Controller(
				this);
		Supervision_BRCD_GiamSat_Kcn_Controller supervisionGiamSatKcnController = new Supervision_BRCD_GiamSat_Kcn_Controller(
				this);
		Supervision_BRCD_Kct_Controller supervisionKctController = new Supervision_BRCD_Kct_Controller(
				this);
		Supervision_BRCD_GiamSat_Kct_Controller supervisionGiamSatKctController = new Supervision_BRCD_GiamSat_Kct_Controller(
				this);
		Supervision_BRCD_Kcn_Controller supervisionKcnController = new Supervision_BRCD_Kcn_Controller(
				this);
		Supervision_BRCD_Drop_Controller supervisionDropGoController = new Supervision_BRCD_Drop_Controller(
				this);
		Supervision_BRCD_MxController supervisionMxConstroller = new Supervision_BRCD_MxController(
				this);
		Supervision_BRCD_Tn_Controller supervisionTnController = new Supervision_BRCD_Tn_Controller(
				this);
		Supervision_BRCD_Ttb_Controller supervisionTtbController = new Supervision_BRCD_Ttb_Controller(
				this);

		List<Supervision_BRCD_Drop_Entity> listSupervisionDropGo = new ArrayList<Supervision_BRCD_Drop_Entity>();

		listSupervisionDropGo = supervisionDropGoController
				.getAllbrcd_drop(supervition_brcd_id);

		List<Supervision_BRCD_Kcn_Entity> listSupervisionKcn = new ArrayList<Supervision_BRCD_Kcn_Entity>();

		listSupervisionKcn = supervisionKcnController
				.getAllbrcd_kcn(supervition_brcd_id);

		List<Supervision_BRCD_Kct_Entity> listSupervisionKct = new ArrayList<Supervision_BRCD_Kct_Entity>();

		listSupervisionKct = supervisionKctController
				.getAllbrcd_kct(supervition_brcd_id);

		Supervision_BRCD_MXEntity listSupervisionMt = new Supervision_BRCD_MXEntity();

		listSupervisionMt = supervisionMxConstroller
				.getSupervisionBRCD_MX(supervition_brcd_id);

		List<Supervision_BRCD_Tn_Entity> listSupervisionTn = new ArrayList<Supervision_BRCD_Tn_Entity>();

		listSupervisionTn = supervisionTnController
				.getAllbrcd_tn(supervition_brcd_id);

		List<Supervision_BRCD_Ttb_Entity> listSupervisionTtb = new ArrayList<Supervision_BRCD_Ttb_Entity>();

		listSupervisionTtb = supervisionTtbController
				.getAllbrcd_ttb_brcd_id(supervition_brcd_id);

		for (int i = 0; i < listSupervisionDropGo.size(); i++) {
			List<Supervision_BRCD_GiamSat_DropGo_Entity> listSupervisionGiamSatDropGo = supervisionGiamSatDropGoController
					.getAllbrcd_kcn(listSupervisionDropGo.get(i)
							.getSupervition_branch_drop_id());

			listSupervisionGiamSatAllDropGo
					.addAll(listSupervisionGiamSatDropGo);

		}
		for (int i = 0; i < listSupervisionKct.size(); i++) {
			List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSKct = supervisionGiamSatKctController
					.getAllbrcd_Kct(listSupervisionKct.get(i)
							.getSUPERVISION_TRUNK_CABLE_ID());

			listSupervisionGSAllKct.addAll(listSupervisionGSKct);

		}

		for (int i = 0; i < listSupervisionKcn.size(); i++) {

			List<Supervision_BRCD_GiamSat_Kcn_Entity> listSupervisionGiamSatKcn = supervisionGiamSatKcnController
					.getAllbrcd_kcn(listSupervisionKcn.get(i)
							.getSupervition_branch_cable_id());

			listSupervisionGiamSatAllKcn.addAll(listSupervisionGiamSatKcn);

		}

		// if (listSupervisionMt.getStatus() == 0 && listSupervisionMt != null)
		// {
		// sMessage = StringUtil.getString(R.string.line_hg_mx_kl_mx_khongdat);
		// Log.i("Check Deny", sMessage);
		// }

		if (listSupervisionMt != null && listSupervisionMt.getStatus() != -1) {
			if (listSupervisionMt.getStatus() == 0) {
				sMessage = StringUtil
						.getString(R.string.line_hg_mx_kl_mx_khongdat);
				Log.i("Check Deny", sMessage);
			}

		}

		for (Supervision_BRCD_GiamSat_DropGo_Entity itemDropGoGS : listSupervisionGiamSatAllDropGo) {
			if (itemDropGoGS.getStatus() != -1
					&& itemDropGoGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil.getString(R.string.brcd_dropgo_khongdat);
				Log.i("Check Deny", sMessage);
			}
		}
		for (Supervision_BRCD_GiamSat_Kcn_Entity itemKcnGS : listSupervisionGiamSatAllKcn) {
			if (itemKcnGS.getStatus() != -1
					&& itemKcnGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil.getString(R.string.brcd_kcn_khongdat);
				Log.i("Check Deny", sMessage);
			}
		}
		for (Supervision_BRCD_GiamSat_Kct_Entity itemKct : listSupervisionGSAllKct) {
			if (itemKct.getStatus() != -1
					&& itemKct.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil.getString(R.string.brcd_kct_khongdat);
				Log.i("Check Deny", sMessage);
			}
		}
		for (Supervision_BRCD_Tn_Entity itemTn : listSupervisionTn) {
			if (itemTn.getStatus() != -1
					&& itemTn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil.getString(R.string.brcd_tn_khongdat);
				Log.i("Check Deny", sMessage);
			}
		}
		for (Supervision_BRCD_Ttb_Entity itemTtb : listSupervisionTtb) {
			if (itemTtb.getStatus() != -1
					&& itemTtb.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil.getString(R.string.brcd_ttb_khongdat);
				Log.i("Check Deny", sMessage);
			}
		}

		if (sMessage.equals("")) {
			return false;
		} else {
			return true;
		}

	}

	public boolean checkSuperSubheadendCauseDeny(Bundle bundleData) {
		String sMessage = "";

		Constr_Construction_EmployeeEntity itemEmployeeData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		// Supervision_Line_HangEntity supervision_Line_HangData =
		// (Supervision_Line_HangEntity) bundleData
		// .getSerializable(IntentConstants.INTENT_DATA_EX);

		// int iDesignInfo = bundleData
		// .getInt(IntentConstants.INTENT_DESIGNINFO);

		/* Drop down */

		// List<DropdownItemEntity> listDesignInfo =
		// GloablUtils.getListSubInfo("");
		// long supervition_brcd_id = bundleData
		// .getLong(IntentConstants.INTENT_BRCD_ID);
		Supervision_BRCD_Sub_Controller supervisionSubController = new Supervision_BRCD_Sub_Controller(
				this);

		Supervision_BRCD_Sub_Entity SupervisionSub = supervisionSubController
				.getSupervisionBRCD_SUB(itemEmployeeData
						.getSupervision_Constr_Id());

		// lay du lieu phan ket luan
		// Supervision_ConstrEntity constrItem = supervisionConstroller
		// .getItem(itemEmployeeData.getSupervision_Constr_Id());

		if (SupervisionSub.getStatus() != -1 && SupervisionSub.getStatus() == 0) {
			sMessage = StringUtil.getString(R.string.sub_hg_mx_kl_mx_khongdat);
			Log.i("Check_Deny", sMessage);
		}

		if (sMessage.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onItemSelected(int position) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onItemSelected: " + position );
		switch (position) {
		case 0:
			if(getLocalClassName().toString().contains(HomeActivity.class.getSimpleName().toString())){
				return;
			}
			gotoHomeActivity(new Bundle());
			finish();
			break;
		case 1:
			
			break;
		case 2:
			gotoMakePlanActivity(new Bundle());
			
			break;
		case 3:
			requestSync(this);
	
			break;
		case 4:
			gotoAboutActivity(new Bundle());
			break;
		case 5:
			
			break;
		case 6:
			gotoLogOut();
			break;

		default:
			break;
		}
		
	}

}