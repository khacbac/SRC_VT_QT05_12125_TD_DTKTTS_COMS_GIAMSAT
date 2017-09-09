/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ErrorConstants;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.gsct.activity.GponActivity;
import com.viettel.gsct.activity.LineBackgroundActivity;
import com.viettel.ktts.AboutActivity;
import com.viettel.ktts.ConstructionSetLineActivity;
import com.viettel.ktts.HomeActivity;
import com.viettel.ktts.MakePlanActivity;
import com.viettel.ktts.SupervisionBtsActivity;
import com.viettel.ktts.SupervisionBts_CNV_List_Activity;
import com.viettel.ktts.Supervision_BRCD_DropGo_Activity;
import com.viettel.ktts.Supervision_BRCD_KLActivity;
import com.viettel.ktts.Supervision_BRCD_KeoCTActivity;
import com.viettel.ktts.Supervision_BRCD_KeoCapNhanhActivity;
import com.viettel.ktts.Supervision_BRCD_MTrucActivity;
import com.viettel.ktts.Supervision_BRCD_Sub_Activity;
import com.viettel.ktts.Supervision_BRCD_Thongtintk_Activity;
import com.viettel.ktts.Supervision_BRCD_TuNhanhActivity;
import com.viettel.ktts.Supervision_BRCD_TuThueBaoActivity;
import com.viettel.ktts.Supervision_CNDTC_Activity;
import com.viettel.ktts.Supervision_Line_BG_DesignActivity;
import com.viettel.ktts.Supervision_Line_BG_Measure_ConstrActivity;
import com.viettel.ktts.Supervision_Line_BG_PipeActivity;
import com.viettel.ktts.Supervision_Line_BG_SpecialActivity;
import com.viettel.ktts.Supervision_Line_BG_TankActivity;
import com.viettel.ktts.Supervision_Line_HG_DesignActivity;
import com.viettel.sync.SyncModel;
import com.viettel.sync.SyncQueue;
import com.viettel.sync.SyncTask;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.view.base.BaseActivity;

/**
 * Ham dieu khi luong du lieu trang chu
 *
 * @author datht1
 *
 */
public class Home_Controller extends AbstractController {
	static Home_Controller instance;

	protected Home_Controller() {
	}

	public static Home_Controller getInstance() {
		if (instance == null) {
			instance = new Home_Controller();
		}
		return instance;
	}

	//	private static final String TAG = "Home_Controller";
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
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SUPERVISION_SUB_ACTIVITY: {
				extras = (Bundle) e.viewData;
				intent = new Intent(base, Supervision_BRCD_Sub_Activity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.Supervision_BRCD_Sub_Activity"));
				// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SUPERVISION_BRCD_MT_ACTIVITY: {
				extras = (Bundle) e.viewData;
				intent = new Intent(base, Supervision_BRCD_MTrucActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.Supervision_BRCD_MTrucActivity"));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SUPERVISION_BRCD_TTTK_ACTIVITY: {
				extras = (Bundle) e.viewData;
				intent = new Intent(base, Supervision_BRCD_Thongtintk_Activity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.Supervision_BRCD_Thongtintk_Activity"));
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SET_LINE_ACTIVITY: {
				extras = (Bundle) e.viewData;
				intent = new Intent(base, ConstructionSetLineActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.ConstructionSetLineActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SUPERVISION_BTS_ACTIVITY: {
				extras = (Bundle) e.viewData;
				intent = new Intent(base, SupervisionBtsActivity.class);
//			intent.setComponent(new ComponentName("com.viettel.ktts",
//					"com.viettel.ktts.SupervisionBtsActivity"));
				if (extras != null) {
					intent.putExtras(extras);
				}
				base.startActivity(intent);
				break;
			}
			case ActionEventConstant.GOTO_SUPERVISION_LINE_BACKGROUND_ACTIVITY: {
				extras = (Bundle) e.viewData;
				int iDesignInfo = extras.getInt(IntentConstants.INTENT_DESIGNINFO);
				switch (iDesignInfo) {
					case Constants.LINE_BACKGROUND_INFO.NHAT_KY_TIEN_DO_INFO:
						intent = new Intent(base, LineBackgroundActivity.class);
						if (extras != null) {
							extras.putInt(LineBackgroundActivity.ARG_INFO, iDesignInfo);
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
//				case Constants.LINE_BACKGROUND_INFO.TIEN_DO_INFO:
//					intent = new Intent(base, LineBackgroundActivity.class);
//					if (extras != null) {
//						extras.putInt(LineBackgroundActivity.ARG_INFO, iDesignInfo);
//						intent.putExtras(extras);
//					}
//					base.startActivity(intent);
//					break;
					case Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_DesignActivity.class);
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_SpecialActivity.class);
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.LINE_BACKGROUND_INFO.MEASURE_CONSTR_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_Measure_ConstrActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_Measure_ConstrActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_TankActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_TankActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_PipeActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_PipeActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_PipeActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_CableActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_PipeActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_MXActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---Hungtn add new 25/08/2016
					case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
						intent = new Intent(base, Supervision_CNDTC_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_CNDTC_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG:
						intent = new Intent(base,
								SupervisionBts_CNV_List_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.SupervisionBts_CNV_List_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---
					case Constants.LINE_BACKGROUND_INFO.KET_LUAN_INFO:
						intent = new Intent(base,
								Supervision_Line_BG_PipeActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Line_BG_KLActivity"));
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
			case ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY: {
				extras = (Bundle) e.viewData;
				int iDesignInfo = extras.getInt(IntentConstants.INTENT_DESIGNINFO);
				switch (iDesignInfo) {
					case Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC:
						intent = new Intent(base, Supervision_BRCD_MTrucActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_MTrucActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.BRCD_BACKGROUND_INFO.NHAT_KY_TIEN_DO_INFO:
						intent = new Intent(base, GponActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_MTrucActivity"));
						if (extras != null) {
							extras.putInt(GponActivity.ARG_INFO, iDesignInfo);
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
//
//					case Constants.BRCD_BACKGROUND_INFO.TIEN_DO:
//						intent = new Intent(base, GponActivity.class);
////				intent.setComponent(new ComponentName("com.viettel.ktts",
////						"com.viettel.ktts.Supervision_BRCD_MTrucActivity"));
//						if (extras != null) {
//							extras.putInt(GponActivity.ARG_INFO, iDesignInfo);
//							intent.putExtras(extras);
//						}
//						base.startActivity(intent);
//						break;

					case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC:
						intent = new Intent(base, Supervision_BRCD_KeoCTActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_KeoCTActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.BRCD_BACKGROUND_INFO.TU_NHANH:
						intent = new Intent(base,
								Supervision_BRCD_TuNhanhActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_TuNhanhActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO:
						intent = new Intent(base,
								Supervision_BRCD_TuThueBaoActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_TuThueBaoActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;

					case Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO:
						intent = new Intent(base, Supervision_BRCD_KLActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_KLActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK:
						intent = new Intent(base,
								Supervision_BRCD_Thongtintk_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_Thongtintk_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH:
						intent = new Intent(base,
								Supervision_BRCD_KeoCapNhanhActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_KeoCapNhanhActivity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.BRCD_BACKGROUND_INFO.DROP_GO:
						intent = new Intent(base,
								Supervision_BRCD_DropGo_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_DropGo_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---Hungtn add new 25/08/
					case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
						intent = new Intent(base, Supervision_CNDTC_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_CNDTC_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG:
						intent = new Intent(base,
								SupervisionBts_CNV_List_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.SupervisionBts_CNV_List_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---
					default:
						break;
				}
				break;
			}
			case ActionEventConstant.GOTO_SUB_BACKGROUND_ACTIVITY: {
				extras = (Bundle) e.viewData;
				int iDesignInfo = extras.getInt(IntentConstants.INTENT_DESIGNINFO);
				switch (iDesignInfo) {
					case Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI:
						intent = new Intent(base, Supervision_BRCD_MTrucActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_BRCD_Sub_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---Hungtn add new 25/08/2016
					case Constants.SUBHEADEND_INFO.CAP_NHAT_DOI_THI_CONG:
						intent = new Intent(base, Supervision_CNDTC_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_CNDTC_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					case Constants.SUBHEADEND_INFO.CAP_NHAT_VUONG:
						intent = new Intent(base,
								SupervisionBts_CNV_List_Activity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.SupervisionBts_CNV_List_Activity"));
						if (extras != null) {
							intent.putExtras(extras);
						}
						base.startActivity(intent);
						break;
					// ---

					case Constants.SUBHEADEND_INFO.KET_LUAN_INFO:
						intent = new Intent(base, Supervision_BRCD_KeoCTActivity.class);
//				intent.setComponent(new ComponentName("com.viettel.ktts",
//						"com.viettel.ktts.Supervision_Sub_KL_Activity"));
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
					default:
						break;
				}
				break;
			}

		}
	}

	public void handleViewEvent(final ActionEvent e) {
		AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				HTTPRequest request = null;
				BaseActivity baseActivity = (BaseActivity) e.sender;
				switch (e.action) {
				/* lay du lieu lich hop */
					case ActionEventConstant.REQEST_LOGIN:
						SyncModel.bStop = false;
						request = SyncModel.getInstance().requestLoginHTTP(e);
						break;
					case ActionEventConstant.REQEST_SYNC:
						SyncQueue.getInstance().resetData();
						SyncModel.getInstance().syncKttsKey(e);
						break;
					default:
						break;
				}

				if (request != null && baseActivity != null) {
					e.request = request;
				}
				return null;
			}
		};
		task.execute();
	}

	public void handleViewEvent(final ActionEvent e, final Context context) {
		SyncTask sync = new SyncTask(e, context);

		sync.execute();
	}

	/*
	 * @author: xu ly du lieu tra ve tu model
	 * 
	 * @throws:
	 */
	@Override
	public void handleModelEvent(final ModelEvent modelEvent) {
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_CODE_SUCCESS) {
			final ActionEvent e = modelEvent.getActionEvent();
			if (e.sender != null) {
				final BaseActivity sender = (BaseActivity) e.sender;
				if (sender.isFinished) {
					return;
				}
				sender.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						sender.handleModelViewEvent(modelEvent);
					}
				});
			} else {
				handleErrorModelEvent(modelEvent);
			}
		} else {
			handleErrorModelEvent(modelEvent);
		}
	}
}