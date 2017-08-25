package com.viettel.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SyncServices extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		SyncTimerTask syncTask = new SyncTimerTask();
		Timer syncTimer = new Timer();
		syncTimer.schedule(syncTask, new Date().getTime() + 3 * 60 * 100,
				15 * 1000);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	class SyncTimerTask extends TimerTask {
		public void run() {
			// SyncModel.getInstance().requestConstr_Constructions(
			// new ActionEvent());
		}
	}
}
