package com.viettel.camera.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;


import com.viettel.camera.kutcamera.MainActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;


@SuppressLint("HandlerLeak")
public class GpsServices extends Service {
    private static final String TAG = "GpsServices";
    public static double longLocation = -1;
    public static double latLocation = -1;
    private Thread gpsThread;
    private LocationManager lm;
    private LocationManager lmNetwork;
    private GPSListener GpsLocationListener;
    String provider;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String returnedValue = (String) msg.obj;

            if (MainActivity.infoCoordinates != null) {
                MainActivity.infoCoordinates.setText(returnedValue);
            }

        }//handleMessage
    };

    /**
     * Chuyen doi so duoi dinh dang
     *
     * @param d
     * @return
     */
    public static String formatNumber(double d) {
        if (d == 0) {
            return "0";
        } else {
            Locale locale = Locale.US;
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(locale);
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat format = new DecimalFormat("##########.###",
                    otherSymbols);
            return format.format(d);
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
        gpsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
//				Toast.makeText(getApplicationContext(), "Start Service", Toast.LENGTH_SHORT).show();
                lm = (LocationManager) getSystemService(LOCATION_SERVICE);
                lmNetwork = (LocationManager) getSystemService(LOCATION_SERVICE);

                GpsLocationListener = new GPSListener();
                int minTime = 1 * 1000; // 1s
                float minDistance = 30; // 1m
                Log.d(TAG, "run() called 0");
                if (Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                Log.d(TAG, "run() called lastKnownLocation: " + (lastKnownLocation == null));
                if (lastKnownLocation == null) {
                    lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastKnownLocation != null) {
                        longLocation = lastKnownLocation.getLongitude();
                        latLocation = lastKnownLocation.getLatitude();
                    }
                } else {
                    Log.d(TAG, "run: 4");
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
        if (lm != null) {
            if (Build.VERSION.SDK_INT >= 23 &&
                    ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(GpsServices.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            lm.removeUpdates(GpsLocationListener);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append(", ");
                }
                strAdd = strReturnedAddress.toString();
                Log.w(TAG, "" + strReturnedAddress.toString());
            } else {
                Log.w(TAG, "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w(TAG, "Canont get Address!");
        }
        return strAdd;
    }

    private class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            longLocation = location.getLongitude();
            latLocation = location.getLatitude();
            Log.d(TAG, "onLocationChanged() called with: location = [" + location + "]");

            String address = getCompleteAddressString(latLocation, longLocation);
            float distance = Math.round(30 * location.getAccuracy() / 100);

            String infoCoordinates = address + "(Long: " + formatNumber(longLocation) + " , " + "Lat: " + formatNumber(latLocation) + ")"
                    + " ?" + distance + "m";

            Message msg = myHandler.obtainMessage(1, infoCoordinates);
            myHandler.sendMessage(msg);

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
