package com.viettel.controller;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSTracker extends Service implements LocationListener {

	private static Context mContext;

	// flag for GPS status
	boolean isGPSEnabled = false;

	public boolean isGPSEnabled() {
		return isGPSEnabled;
	}

	public void setGPSEnabled(boolean isGPSEnabled) {
		this.isGPSEnabled = isGPSEnabled;
	}

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	boolean isNetworkConnect = false;

	Location location; // location
	double latitude; // latitude
	double longitude; // longitude

	// The minimum distance to change Updates in meters
	private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 10f; // 10
																		// meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 5 * 1; // 1 minute

	// Declaring a Location Manager
	protected LocationManager locationManager;

	List<Address> addresses = null;

	public GPSTracker(Context context) {
		GPSTracker.mContext = context;
		locationManager = (LocationManager) mContext
				.getSystemService(Context.LOCATION_SERVICE);

		// getting GPS status
		isGPSEnabled = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// getting network status
		isNetworkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}

	public void Location() {
		this.location = getLocationfromWireless();
	}

	public Location getLocationfromWireless() throws SecurityException {
		try {
			// List<String> providers = locationManager.getProviders(true);
			// Toast.makeText(mContext, "hihihih", Toast.LENGTH_SHORT).show();
			// if(!providers.isEmpty()) {
			// for(int i=0;i<providers.size();i++){
			// Toast.makeText(mContext, ""+providers.get(i),
			// Toast.LENGTH_SHORT).show();
			// Log.v("",providers.get(i));
			// }
			// } else {
			// Log.d("ManualLog", "No provider");
			// }
			// if GPS Enabled get lat/long using GPS Services
			if (isGPSEnabled) {
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
						MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
				if (locationManager != null) {
					location = locationManager
							.getLastKnownLocation(LocationManager.GPS_PROVIDER);
					Log.i("", "nha cung cap gps: " + location);
					if (location != null) {
						latitude = location.getLatitude();
						longitude = location.getLongitude();
						Log.i("", "1:" + latitude);
						Log.i("", "2:" + longitude);
					} else {
						Toast.makeText(mContext,
								"Can't get location from gps provider!",
								Toast.LENGTH_SHORT).show();
					}
				}
			}

			if (isNetworkEnabled) {
				Log.i("", "nha cung cap mang");
				if (location == null) {
					try {
						locationManager.requestLocationUpdates(
								LocationManager.NETWORK_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (RuntimeException e) {
						e.printStackTrace();
					}

					// Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	public void setLocationfromGPS() {
		this.location = getLocationfromGPS();
	}

	public Location getLocationfromGPS() throws SecurityException {
		try {
			// if GPS Enabled get lat/long using GPS Services
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
					MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
			if (locationManager != null) {
				location = locationManager
						.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				Log.i("", "nha cung cap gps: " + location);
				if (location != null) {
					latitude = location.getLatitude();
					longitude = location.getLongitude();
					Log.i("", "1:" + latitude);
					Log.i("", "2:" + longitude);
				} else {
					Toast.makeText(mContext,
							"Can't get location from gps provider!",
							Toast.LENGTH_SHORT).show();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	public static boolean hasConnection() {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}

	/**
	 * Stop using GPS listener Calling this function will stop using GPS in your
	 * app
	 * */
	public void stopUsingGPS() {
		if (locationManager != null) {
			locationManager.removeUpdates(GPSTracker.this);
		}
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude() {
		if (location != null) {
			latitude = location.getLatitude();
		}

		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude() {
		if (location != null) {
			longitude = location.getLongitude();
		}

		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * 
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	/**
	 * Get list of address by latitude and longitude
	 * 
	 * @return null or List<Address>
	 */
	public List<Address> getGeocoderAddress(Context context) {
		if (location != null) {
			Geocoder geocoder = new Geocoder(context, Locale.getDefault());
			try {
				List<Address> addresses = geocoder.getFromLocation(latitude,
						longitude, 1);
				this.addresses = addresses;
				return addresses;
			} catch (IOException e) {
				e.printStackTrace();

				Log.i("Error : Geocoder", "Impossible to connect to Geocoder",
						e);
			}
		}
		this.addresses = null;
		return null;
	}

	/**
	 * Try to get AddressLine
	 * 
	 * @return null or addressLine
	 */
	public String getAddressLine(Context context) {
		String addressLine = "";
		if (addresses != null && addresses.size() > 0) {
			for (int i = 0, size = addresses.size(); i < size; i++) {
				Address address = addresses.get(i);
				if (address.getMaxAddressLineIndex() != -1) {
					for (int j = 0, length = address.getMaxAddressLineIndex(); j < length; j++) {
						addressLine += address.getAddressLine(j) + ", ";
					}
				}

			}
			int index = addressLine.lastIndexOf(",");

			return addressLine.substring(0, index);
		} else {
			return null;
		}
	}

	/**
	 * Try to get Locality
	 * 
	 * @return null or locality
	 */
	public String getLocality(Context context) {
		String locality = "";
		if (addresses != null && addresses.size() > 0) {
			for (int i = 0, size = addresses.size(); i < size; i++) {
				Address address = addresses.get(i);

				locality += address.getAdminArea() + ", ";
			}
			int index = locality.lastIndexOf(",");
			return locality.substring(0, index);
		} else {
			return null;
		}
	}

	/**
	 * Try to get Postal Code
	 * 
	 * @return null or postalCode
	 */
	public String getPostalCode(Context context) {
		String postalCode = "";
		if (addresses != null && addresses.size() > 0) {
			for (int i = 0, size = addresses.size(); i < size; i++) {
				Address address = addresses.get(i);

				postalCode += address.getPostalCode() + ", ";
			}

			return postalCode;
		} else {
			return null;
		}
	}

	/**
	 * Try to get CountryName
	 * 
	 * @return null or postalCode
	 */
	public String getCountryName(Context context) {
		String countryName = "";

		List<Address> addresses = getGeocoderAddress(context);
		if (addresses != null && addresses.size() > 0) {
			for (int i = 0, size = addresses.size(); i < size; i++) {
				Address address = addresses.get(i);

				countryName += address.getLocale().getDisplayCountry() + ", ";
			}

			int index = countryName.lastIndexOf(",");
			return countryName.substring(0, index);
		} else {
			return null;
		}
	}

	public boolean isNetworkConnect() {
		return isNetworkConnect;
	}

	public void setNetworkConnect(boolean isNetworkConnect) {
		this.isNetworkConnect = isNetworkConnect;
	}

	public boolean isNetworkEnabled() {
		return isNetworkEnabled;
	}

	public void setNetworkEnabled(boolean isNetworkEnabled) {
		this.isNetworkEnabled = isNetworkEnabled;
	}

	@Override
	public void onLocationChanged(Location location) {
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		// Toast.makeText(GPSTracker.mContext,
		// "vao onLocationChanged " + location.getProvider(),
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Toast.makeText(GPSTracker.mContext,
		// "vao onProviderDisabled " + provider, Toast.LENGTH_SHORT)
		// .show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Toast.makeText(GPSTracker.mContext,
		// "vao onProviderEnabled " + provider, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Toast.makeText(GPSTracker.mContext, "vao onStatusChanged",
		// Toast.LENGTH_SHORT).show();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
}
