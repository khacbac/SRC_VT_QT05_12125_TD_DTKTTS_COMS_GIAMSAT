package com.viettel.view.base;

import android.os.Bundle;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.controller.LineHang_Controller;
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Supervision_Line_BG_CableController;
import com.viettel.database.Supervision_Line_BG_MxController;
import com.viettel.database.Supervision_Line_BG_PipeController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supervision_Line_BackgroundController;
import com.viettel.database.Supervision_Line_HangController;
import com.viettel.database.Supervision_Line_Hg_CableController;
import com.viettel.database.Supervision_Line_Hg_MxController;
import com.viettel.database.Supervision_Line_Hg_PillarController;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_CableGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_PipeGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_HG_PillarGSEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
import com.viettel.ktts.R;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;

import java.util.List;

public class LineBaseActivity extends HomeBaseActivity {
	protected void gotoHomeActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_HOME_ACTIVITY;
		e.sender = this;
		LineHang_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoLineHangActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_LINE_HANG_ACTIVITY;
		e.sender = this;
		LineHang_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoBRCDActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_LINE_BG);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	protected void gotoSUBActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.CONSTR_TYPE_SUB.GS);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUB_BACKGROUND_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}

	public boolean checkCauseDenyHangLine(Bundle bundleData) {
		String sMessage = "";

		Supervision_Line_HangEntity supervision_Line_HangData = (Supervision_Line_HangEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA_EX);
		
		Supervision_Line_HangController supervision_Line_HangController = new Supervision_Line_HangController(
				this);
		Supervision_Line_HangEntity supervision_Line_Hang = supervision_Line_HangController
				.getItemBySupervisionConstrId(supervision_Line_HangData
						.getSupervision_Constr_Id());
		
		Supervision_Line_Hg_CableController supervisionLHGCableController = new Supervision_Line_Hg_CableController(
				this);

		Supervision_Line_Hg_MxController supervisionLineHGMXController = new Supervision_Line_Hg_MxController(
				this);
		Supervision_Line_Hg_PillarController supervisionLHGPillarController = new Supervision_Line_Hg_PillarController(
				this);
		List<Supervision_Line_HG_MXGSEntity> listLineHGMX = supervisionLineHGMXController
				.getAllMXGSBySupervistionLineHang(supervision_Line_HangData
						.getSupervision_Line_Hang_Id());
		List<Supervision_Line_Hg_CableGSEntity> listSupervisionCableGS = supervisionLHGCableController
				.getAllCableGSBySupervistionLineHang(supervision_Line_HangData
						.getSupervision_Line_Hang_Id());
		List<Supervision_Line_HG_PillarGSEntity> listSupervisionPillarGS = supervisionLHGPillarController
				.getAllPillarGSBySupervistionLineHang(supervision_Line_HangData
						.getSupervision_Line_Hang_Id());

		if (listLineHGMX.size() > 0) {
			sMessage = StringUtil.getString(R.string.line_hg_mx_kl_mx_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
			// return sResult;
		}

		int numberCableKDat = 0;
		for (Supervision_Line_Hg_CableGSEntity itemCableGS : listSupervisionCableGS) {
			if (itemCableGS.getStatus() != -1) {
				if (itemCableGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
					numberCableKDat++;
					break;
				}
			}
		}
		if (numberCableKDat > 0) {
			sMessage = StringUtil
					.getString(R.string.line_hg_mx_kl_cable_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
			// return sResult;
		}
		if (listSupervisionPillarGS.size() > 0) {
			sMessage = StringUtil.getString(R.string.line_hg_pl_kl_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
			// return sResult;
		}
		if (supervision_Line_Hang.getMeasure_Status() != -1) {
			if (supervision_Line_Hang.getMeasure_Status() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil
						.getString(R.string.line_hg_mx_kl_fiber_khongdat);
				Log.i("Check_Hang_Line_Deny", sMessage);
				// return sResult;
			}
		}

		if (sMessage.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkCauseDenyBackgroundLine(Bundle bundleData) {
		String sMessage = "";

		Supervision_Line_BackgroundEntity supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA_EX);
		
		Supervision_Line_BackgroundController supervision_Line_BackgroundController = new Supervision_Line_BackgroundController(
				this);
		Supervision_Line_BackgroundEntity supervision_Line_Background = supervision_Line_BackgroundController
				.getItemBySupervisionConstrId(supervision_Line_BackgroundData
						.getSupervision_Constr_Id());

		/* Drop down */
		Supervision_Line_BG_TankController supervisionLBGController = new Supervision_Line_BG_TankController(
				this);
		Cause_Line_BG_TankController causeLineBGTankController = new Cause_Line_BG_TankController(
				this);
		List<Supervision_Line_BG_TankGSEntity> listSupervisionGS = supervisionLBGController
				.getAllTankGSBySupervistionLineBackground(supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		/* Truong hop Load luu du lieu */
		if (listSupervisionGS.size() == 0) {
			long iNumberTank = supervision_Line_BackgroundData
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
				List<Supervision_LBG_UnqualifyItemEntity> curUnqualifyItem = causeLineBGTankController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getIdTank());
				curItemSupervisonGS.setListCauseUniQualify(curUnqualifyItem);
			}
		}

		Supervision_Line_BG_CableController supervisionLBGCableController = new Supervision_Line_BG_CableController(
				this);
		List<Supervision_Line_BG_CableGSEntity> listSupervisionCableGS = supervisionLBGCableController
				.getAllCableGSBySupervistionLineBackground(supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());

		Supervision_Line_BG_MxController supervisionLineBGMXController = new Supervision_Line_BG_MxController(
				this);
		List<Supervision_Line_BG_MXGSEntity> listLineBGMX = supervisionLineBGMXController
				.getAllMXGSBySupervistionLineBackground(supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		
		Supervision_Line_BG_PipeController supervisionLBGPipeController = new Supervision_Line_BG_PipeController(this);
		
		List<Supervision_Line_BG_PipeGSEntity> listSupervisionPipeGS = supervisionLBGPipeController
				.getAllPipeGSBySupervistionLineBackground(supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id());
		
		for (Supervision_Line_BG_PipeGSEntity checkItem : listSupervisionPipeGS) {
			if (checkItem.getStatus() != -1
					&& checkItem.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil
						.getString(R.string.line_bg_mx_kl_tank_khongdat);
				Log.i("Check_Hang_Line_Deny", sMessage);
			}
		}
		
		// TODO
		for (Supervision_Line_BG_TankGSEntity checkItem : listSupervisionGS) {
			if (checkItem.getStatus() != -1
					&& checkItem.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				sMessage = StringUtil
						.getString(R.string.line_bg_mx_kl_tank_khongdat);
				Log.i("Check_Hang_Line_Deny", sMessage);
			}
		}
		int numberCableKDat = 0;
		for (Supervision_Line_BG_CableGSEntity itemCableGS : listSupervisionCableGS) {
			if (itemCableGS.getStatus() != -1
					&& itemCableGS.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
				numberCableKDat++;
				break;
			}
		}
		if (numberCableKDat > 0) {
			sMessage = StringUtil
					.getString(R.string.line_hg_mx_kl_cable_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
		}
		if (listLineBGMX.size() > 0) {
			sMessage = StringUtil.getString(R.string.line_bg_mx_kl_mx_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
		}
		if (supervision_Line_Background.getMeasure_Status() != -1 && supervision_Line_Background.getMeasure_Status() == Constants.SUPERVISION_STATUS.CHUADAT) {
			sMessage = StringUtil
					.getString(R.string.line_bg_mx_kl_fiber_khongdat);
			Log.i("Check_Hang_Line_Deny", sMessage);
		}

		if (sMessage.equals("")) {
			return false;
		} else {
			return true;
		}
	}
}