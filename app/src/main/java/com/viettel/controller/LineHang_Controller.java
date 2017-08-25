/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.gsct.activity.LineBackgroundActivity;
import com.viettel.gsct.activity.LineHangActivity;
import com.viettel.ktts.AboutActivity;
import com.viettel.ktts.HomeActivity;
import com.viettel.ktts.MakePlanActivity;
import com.viettel.ktts.SupervisionBts_CNV_List_Activity;
import com.viettel.ktts.Supervision_CNDTC_Activity;
import com.viettel.ktts.Supervision_Line_HG_CableActivity;
import com.viettel.ktts.Supervision_Line_HG_DesignActivity;
import com.viettel.ktts.Supervision_Line_HG_KLActivity;
import com.viettel.ktts.Supervision_Line_HG_MXActivity;
import com.viettel.ktts.Supervision_Line_HG_Measure_ConstrActivity;
import com.viettel.ktts.Supervision_Line_HG_PillarActivity;

/**
 * Ham dieu khi luong du lieu trang chu
 * 
 * @author datht1
 * 
 */
public class LineHang_Controller extends AbstractController {
	static LineHang_Controller instance;

	protected LineHang_Controller() {
	}

	public static LineHang_Controller getInstance() {
		if (instance == null) {
			instance = new LineHang_Controller();
		}
		return instance;
	}

	public void handleViewEvent(final ActionEvent e) {

	}

	@Override
	public void handleSwitchActivity(ActionEvent e) {
		Activity base = (Activity) e.sender;
		Intent intent;
		Bundle extras;
		switch (e.action) {
		case ActionEventConstant.GOTO_HOME_ACTIVITY: {
			extras = (Bundle) e.viewData;
			intent = new Intent(base, HomeActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.HomeActivity"));
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		}
		case ActionEventConstant.GOTO_PLAN_ACTIVITY: {
			extras = (Bundle) e.viewData;
			intent = new Intent(base, MakePlanActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.MakePlanActivity"));
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		}
		case ActionEventConstant.GOTO_ABOUT_ACTIVITY: {
			extras = (Bundle) e.viewData;
			intent = new Intent(base, AboutActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.AboutActivity"));
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			if (extras != null) {
				intent.putExtras(extras);
			}
			base.startActivity(intent);
			break;
		}
		case ActionEventConstant.GOTO_SUPERVISION_LINE_HANG_ACTIVITY: {
			extras = (Bundle) e.viewData;
			int iDesignInfo = extras.getInt(IntentConstants.INTENT_DESIGNINFO);
			switch (iDesignInfo) {
			case Constants.LINE_HANG_INFO.THIE_TKE_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_DesignActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_DesignActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;

				case Constants.LINE_HANG_INFO.NHAT_KY_INFO:
					intent = new Intent(base,
							LineHangActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_DesignActivity"));
					if (extras != null) {
						intent.putExtras(extras);
						intent.putExtra(LineBackgroundActivity.ARG_INFO, iDesignInfo);
					}
					base.startActivity(intent);
					break;

				case Constants.LINE_HANG_INFO.TIEN_DO_INFO:
					intent = new Intent(base, LineHangActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_DesignActivity"));
					if (extras != null) {
						intent.putExtras(extras);
						intent.putExtra(LineBackgroundActivity.ARG_INFO, iDesignInfo);
					}
					base.startActivity(intent);
					break;
			case Constants.LINE_HANG_INFO.GS_COT_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_PillarActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_PillarActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			case Constants.LINE_HANG_INFO.GS_CAP_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_CableActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_CableActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			case Constants.LINE_HANG_INFO.MEASURE_CONSTR_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_Measure_ConstrActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_Measure_ConstrActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			case Constants.LINE_HANG_INFO.GS_HNDK_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_MXActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_MXActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
				//---HungTN add new 25/08/2016
			case Constants.LINE_HANG_INFO.CAP_NHAT_DOI_THI_CONG:
				intent = new Intent(base,
						Supervision_CNDTC_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_CNDTC_Activity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
			case Constants.LINE_HANG_INFO.CAP_NHAT_VUONG:
				intent = new Intent(base,
						SupervisionBts_CNV_List_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.SupervisionBts_CNV_List_Activity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
				//---
			case Constants.LINE_HANG_INFO.KET_LUAN_INFO:
				intent = new Intent(base,
						Supervision_Line_HG_KLActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_HG_KLActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			default:
				break;
			}
			break;
		}

		}
	}
}