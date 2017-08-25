package com.viettel.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.ktts.SupervisionBtsActivity;
import com.viettel.ktts.SupervisionBtsCatWorkActivity;
import com.viettel.ktts.SupervisionBtsEquipActivity;
import com.viettel.ktts.SupervisionBtsKLActivity;
import com.viettel.ktts.SupervisionBtsMeasureActivity;
import com.viettel.ktts.SupervisionBtsPillar;
import com.viettel.ktts.SupervisionBtsPowerPoleActivity;
import com.viettel.ktts.SupervisionBtsVibaActivity;
import com.viettel.ktts.SupervisionBts_CNV_List_Activity;
import com.viettel.ktts.Supervision_CNDTC_Activity;

public class SupervisionBtsController extends AbstractController {

	static SupervisionBtsController instance;

	protected SupervisionBtsController() {
	}

	public static SupervisionBtsController getInstance() {
		if (instance == null) {
			instance = new SupervisionBtsController();
		}
		return instance;
	}

	@Override
	public void handleViewEvent(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleSwitchActivity(ActionEvent e) {
		// TODO Auto-generated method stub
		Activity base = (Activity) e.sender;
		Intent intent;
		Bundle extras;
		switch (e.action) {
		case ActionEventConstant.GOTO_SUPERVISION_BTS_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_BTS_PILLAR_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsPillar.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsPillar"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_POSITION_BTS_PILLAR_ACTIVITY:
			extras = (Bundle) e.viewData;
			// intent = new Intent(base, PositionBtsPillarActivity.class);
			// intent.setComponent(new ComponentName("com.viettel.ktts",
			// "com.viettel.ktts.PositionBtsPillarActivity"));
			// if (extras != null) {
			// intent.putExtras(extras);
			// }
			// base.startActivityf(intent);
			break;

		case ActionEventConstant.GOTO_SUPERVISION_BTS_CAT_WORK_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsCatWorkActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsCatWorkActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_BTS_POWER_POLE_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsPowerPoleActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsPowerPoleActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_BTS_EQUIP_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsEquipActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsEquipActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_BTS_VIBA_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsVibaActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsVibaActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_BTS_MEASURE_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsMeasureActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsMeasureActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		
		case ActionEventConstant.GOTO_SUPERVISION_MEASURE_CONSTR_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsMeasureActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsMeasure_ConstrActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
			//HungTN add new 24/08/2016
		case ActionEventConstant.GOTO_SUPERVISION_CNDTC_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, Supervision_CNDTC_Activity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.Supervision_CNDTC_Activity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		case ActionEventConstant.GOTO_SUPERVISION_CNV_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBts_CNV_List_Activity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBts_CNV_List_Activity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
			//---
		case ActionEventConstant.GOTO_SUPERVISION_BTS_KL_ACTIVITY:
			extras = (Bundle) e.viewData;
			intent = new Intent(base, SupervisionBtsKLActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsKLActivity"));
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		}
	}

}
