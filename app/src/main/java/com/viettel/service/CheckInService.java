package com.viettel.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.viettel.common.ActionEventConstant;
import com.viettel.common.CheckInUtil;
import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.database.Supervision_LocateController;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.ktts.HomeActivity;
import com.viettel.ktts.LoginActivity;
import com.viettel.ktts.R;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CheckInService extends Service {
	private Timer timer = new Timer();
	private NotificationManager mNM;
	Context mContext;
	public static final String VT_ACTION = "com.viettel.ktts.BROADCAST";
	public static final long distance_require_time = 4*60*60*1000;
//	public static final long refresh_time = 5*60*1000;

//	public static final long distance_require_time = 3*60*1000;
	public static final long refresh_time = 5*1000;
	
	private Supervision_LocateController supvLocateController;
	private Supervision_Locate_Entity supervision_Locate_Entity;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		supvLocateController = new Supervision_LocateController(mContext);
		supervision_Locate_Entity = supvLocateController.getSupvLocateItem(GlobalInfo.getInstance().getCheckInTBId(mContext));
//		Toast.makeText(this, getString(R.string.checkin_notify), Toast.LENGTH_LONG).show();
		Log.e("CheckinService", "supv locate id2: "+GlobalInfo.getInstance().getCheckInTBId(mContext));
		Log.e("CheckinService", "onCreate: " + GlobalInfo.getInstance().isCheckIn(mContext));
		mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		if(!GlobalInfo.getInstance().isCheckIn(mContext)){
			stopSelf();
		}
		startCountDownTime();
	}

	@Override
	public void onDestroy() {
//		Toast.makeText(this, getString(R.string.checkout_notify), Toast.LENGTH_LONG).show();
		super.onDestroy();
	}

	@SuppressWarnings({ "unused", "deprecation" })
	private void showNotification() {
//		CharSequence text = getText(R.string.force_checkout_notify);
//		Notification notification = new Notification(R.drawable.ic_ktts, text, System.currentTimeMillis());
//		PendingIntent contentIntent;
//		if (GlobalInfo.getInstance().isLogin()) {
//			contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, HomeActivity.class), 0);
//		} else {
//			contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, LoginActivity.class), 0);
//		}
//		//notification.setLatestEventInfo(this, getText(R.string.checkout_notify), text, contentIntent);
//		try {
//			mNM.notify(Constants.NOTIFICATION_MANAGER, notification);
//		} catch (IllegalArgumentException e) {
//			e.printStackTrace();
//		}
	}

	public void startCountDownTime() {
		timer.schedule(new CountDownTimer(), 0, refresh_time);
	}

	private Handler timeHandle = new Handler(new Callback() {

		@Override
		public boolean handleMessage(Message msg) {
			// thao tac tai day
			if(!GlobalInfo.getInstance().isCheckIn(mContext)){
				stopSelf();
				timer.cancel();
				return true;
			}else if ((System.currentTimeMillis() - supervision_Locate_Entity.getCheckinDate().getTime()) >= distance_require_time) {
//				supervision_Locate_Entity.setIsCheckin(Constants.SUPV_LOCATE_CO_STATUS.BI_CHECKOUT);
//				supervision_Locate_Entity.setCheckoutDate(new Date(System.currentTimeMillis()));
//				boolean updateDB = supvLocateController.updateSupervisionLocateEntity(supervision_Locate_Entity);
				Date checkoutDate = new Date(supervision_Locate_Entity.getCheckinDate().getTime()+distance_require_time);
				boolean updateDB = CheckInUtil.updateDataAfterCheckOut(mContext, supvLocateController, Constants.SUPV_LOCATE_CO_STATUS.BI_CHECKOUT,checkoutDate);
				if(updateDB){
					timer.cancel();
					stopSelf();
					showNotification();
					GlobalInfo.getInstance().setCheckIn(false,mContext);
					sentBroadcast(ActionEventConstant.ACTIVITY_CHECKOUT, new Bundle());
					Log.d("CheckinService", "bi thoat");
					return true;
				}
			}
			return false;
		}
	});

	public class CountDownTimer extends TimerTask {

		@Override
		public void run() {
			timeHandle.sendEmptyMessage(0);
		}

	}
	
	public void sentBroadcast(int action, Bundle bundle) {
		Intent intent = new Intent(VT_ACTION);
		bundle.putInt(Constants.RECEIVE_ACTION, action);
		bundle.putInt(Constants.RECEIVE_HASHCODE, intent.getClass().hashCode());
		intent.putExtras(bundle);
		sendBroadcast(intent, "com.viettel.permission.BROADCAST");
	}

}
