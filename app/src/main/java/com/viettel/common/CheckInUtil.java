package com.viettel.common;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.View;

import com.viettel.constants.Constants;
import com.viettel.database.Supervision_LocateController;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.ktts.R;
import com.viettel.service.GpsServices;
import com.viettel.view.base.BaseActivity;

import java.util.Date;

public class CheckInUtil {
	public static boolean checkDistanceBetween2Point(double reqLat, double reqLong) {
		boolean canCheck = false;
		float[] results = new float[1];
		Location.distanceBetween(reqLat, reqLong, GpsServices.latLocation,
				GpsServices.longLocation, results);
		Log.d("hungkm", "distanceBetween : "+results[0]);
		if (results[0] <= Constants.MIN_DISTANCE_FROM_STATION) {
			canCheck = true;
		}
		return canCheck;
	}
	
	public static boolean updateDataAfterCheckOut(Context mContext,Supervision_LocateController supervision_LocateController,int typeCheckOut,Date checkoutDate){
		Supervision_Locate_Entity supervision_Locate_Entity = supervision_LocateController.getSupvLocateItem(GlobalInfo.getInstance().getCheckInTBId(mContext));
		supervision_Locate_Entity.setLogoutLatitude(String.valueOf(GpsServices.latLocation));
		supervision_Locate_Entity.setLogoutLongtitude(String.valueOf(GpsServices.longLocation));
		supervision_Locate_Entity.setCheckoutDate(checkoutDate);
		supervision_Locate_Entity.setIsCheckin(typeCheckOut);
		return supervision_LocateController.updateSupervisionLocateEntity(supervision_Locate_Entity);
	}
	
	public static void checkInRequire(Activity activity,View v){
		if(!GlobalInfo.getInstance().isCheckIn(activity) && v.getId()!=R.id.btn_popup_cancel && v.getId()!=R.id.btn_popup_save_plan){
			BaseActivity activity2 = (BaseActivity) activity;
			activity2.showAlertDialogCheckInRequire(activity, activity.getString(R.string.checkin_require), activity.getString(R.string.text_close));
			return;
		}
	}
	
	
}
