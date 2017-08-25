package com.viettel.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.content.ContextCompat;

import com.viettel.constants.Constants;

public class GpsServices extends Service {
	public static double longLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	public static double latLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private Thread gpsThread;
	private LocationManager lm;
	private LocationManager lmNetwork;
	private GPSListener GpsLocationListener;
	String provider;

	@Override
	public void onCreate() {
		super.onCreate();
		gpsThread = new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				
				lm = (LocationManager) getSystemService(LOCATION_SERVICE);
				lmNetwork = (LocationManager) getSystemService(LOCATION_SERVICE);
				
				GpsLocationListener = new GPSListener();
				int minTime = 1 * 1000; // 1s
				float minDistance = 30; // 1m

				if (Build.VERSION.SDK_INT >= 23 &&
						ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
						ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
					return;
				}
				
				Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
				if(lastKnownLocation == null){
					lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					if(lastKnownLocation != null){
						longLocation = lastKnownLocation.getLongitude();
						latLocation = lastKnownLocation.getLatitude();
					}
				} else {
					longLocation = lastKnownLocation.getLongitude();
					latLocation = lastKnownLocation.getLatitude();
				}
		        
				lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
						minTime, minDistance, GpsLocationListener);
				
				lmNetwork.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
						minTime, minDistance, GpsLocationListener);
				Looper.loop();
			}
		});
	
		gpsThread.start();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
//		Log.i("vao onDestroy", "vao onDestroy");
		if (Build.VERSION.SDK_INT >= 23 &&
				ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
				ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			return;
		}
		if(lm != null){
			lm.removeUpdates(GpsLocationListener);
		}
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private class GPSListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			longLocation = location.getLongitude();
			latLocation = location.getLatitude();
//			Log.i("long-lat", longLocation + "] - [" + latLocation);
//			 Toast.makeText(getApplicationContext(), "Da lay vi tri moi",
//			 Toast.LENGTH_LONG).show();
		}

		@Override
		public void onProviderDisabled(String provider) {
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			
		}

	}

}
