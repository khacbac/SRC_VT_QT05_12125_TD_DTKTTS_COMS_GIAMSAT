package com.viettel.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CheckInReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
			Intent serviceIntent = new Intent(context, CheckInService.class);
			context.startService(serviceIntent);
		}
	}
}
