package com.viettel.ktts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.viettel.actionbar.Header;
import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.service.GpsServices;
import com.viettel.view.base.HomeBaseActivity;

public class GoogleMapActivity extends HomeBaseActivity implements
		OnMapClickListener, OnClickListener {

	private final String TAG = this.getClass().getSimpleName();

	private GoogleMap googleMap;
	private MarkerOptions googleMarker;
	private LatLng pPoint;
	private String sTitle = "";
	private String sTitleValue = "";
	private double dLong = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private double dLat = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private MapFragment fm;
	private View gmapView;
	private TextView tv_name_title;
	private TextView tv_name_value;
	private TextView tv_long_value;
	private TextView tv_lat_value;
	private Button btn_map_save;

	private final int REQUEST_GPS = 101;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.google_map_layout);
		setTitle(getString(R.string.text_location));
		initView();
		initMap();
//		setHeader();
//		setData();
	}

	private void initView() {
		gmapView = addView(R.layout.google_map_layout, R.id.rl_map);
		this.tv_name_title = (TextView) gmapView.findViewById(R.id.tv_name_title);
		this.tv_name_value = (TextView) gmapView.findViewById(R.id.tv_name_value);
		this.tv_long_value = (TextView) gmapView.findViewById(R.id.tv_long_value);
		this.tv_lat_value = (TextView) gmapView.findViewById(R.id.tv_lat_value);
		this.btn_map_save = (Button) gmapView.findViewById(R.id.btn_map_save);
		this.btn_map_save.setOnClickListener(this);
	}

	private void setData() {
		Bundle bundle = this.getIntent().getExtras();

		this.sTitle = bundle.getString(IntentConstants.INTENT_MAP_TITLE);
		this.sTitleValue = bundle.getString(IntentConstants.INTENT_MAP_VALUE);
		this.dLong = bundle.getDouble(IntentConstants.INTENT_LONG);
		this.dLat = bundle.getDouble(IntentConstants.INTENT_LAT);

		if (dLong == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
			dLong = GpsServices.longLocation;
			Log.d(TAG,"Long = " + dLong);
		}
		if (dLat == Constants.ID_DOUBLE_ENTITY_DEFAULT) {
			dLat = GpsServices.latLocation;
			Log.d(TAG,"Lat = " + dLat);
		}
		

		this.tv_name_title.setText(this.sTitle);
		this.tv_name_value.setText(this.sTitleValue);
		this.tv_long_value.setText(String.valueOf(dLong));
		this.tv_lat_value.setText(String.valueOf(dLat));
		/* Ve diem hien tai tren ban do */
		try {

			this.pPoint = new LatLng(dLat, dLong);
			this.googleMarker = new MarkerOptions().position(pPoint)
					.snippet(sTitle).title(sTitle);
			
			googleMap.addMarker(googleMarker);
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pPoint,
					16));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onMapClick(LatLng point) {
		try {
			this.pPoint = point;
			this.dLat = point.latitude;
			this.dLong = point.longitude;
			this.googleMap.clear();
			this.googleMarker = new MarkerOptions().position(pPoint)
					.snippet(sTitle).title(sTitle);
			this.googleMap.addMarker(this.googleMarker);
			this.tv_long_value.setText(String.valueOf(pPoint.longitude));
			this.tv_lat_value.setText(String.valueOf(pPoint.latitude));
			googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pPoint,
					16));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_map_save:			
			Intent returnIntent = new Intent();
			returnIntent.putExtra(IntentConstants.INTENT_LONG, this.dLong);
			returnIntent.putExtra(IntentConstants.INTENT_LAT, this.dLat);
			setResult(RESULT_OK, returnIntent);
			this.finish();
			break;
		default:
			break;
		}

	}

	private void initMap() {

		if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GoogleMapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
			ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_GPS);
			Log.d(TAG, "startGPSService() returned: " + "ACCESS_FINE_LOCATION");
			return;
		}

		if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(GoogleMapActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
			ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_GPS);
			Log.d(TAG, "startGPSService() returned: " + "ACCESS_COARSE_LOCATION");
			return;
		}

		try {

			fm = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.mapview));
			fm.getMapAsync(new OnMapReadyCallback() {
				@Override
				public void onMapReady(GoogleMap googleMap) {
					GoogleMapActivity.this.googleMap = googleMap;

					if (Build.VERSION.SDK_INT >= 23 &&
							ContextCompat.checkSelfPermission(GoogleMapActivity.this,
									android.Manifest.permission.ACCESS_FINE_LOCATION)
									!= PackageManager.PERMISSION_GRANTED &&
							ContextCompat.checkSelfPermission(GoogleMapActivity.this,
									android.Manifest.permission.ACCESS_COARSE_LOCATION)
									!= PackageManager.PERMISSION_GRANTED) {
						return;
					}

					GoogleMapActivity.this.googleMap.setMyLocationEnabled(true);
					GoogleMapActivity.this.googleMap.getUiSettings().setZoomControlsEnabled(true); //
					GoogleMapActivity.this.googleMap.getUiSettings().setCompassEnabled(true); //
					GoogleMapActivity.this.googleMap.getUiSettings().setMyLocationButtonEnabled(true);
					GoogleMapActivity.this.googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
					GoogleMapActivity.this.googleMap.setOnMapClickListener(GoogleMapActivity.this);
					setData();
				}
			});
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {

			case REQUEST_GPS: {
				if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					initMap();
				} else {
					super.onRequestPermissionsResult(requestCode, permissions, grantResults);
				}
			}
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) gmapView.findViewById(R.id.actionbar);
		myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
		// icon back
		myActionBar.setBackAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoHomeActivity(new Bundle());
				finish();
			}

			@Override
			public int getDrawable() {
				return R.drawable.backicon;
			}
		});
		// buttom home
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoHomeActivity(new Bundle());
				finish();
			}

			@Override
			public int getDrawable() {
				return R.drawable.home;
			}
		});
		/* Dong bo du lieu */
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
			}

			@Override
			public int getDrawable() {
				return R.drawable.icon_rotate;
			}
		});
		// buttom loguot
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoLogOut();
			}

			@Override
			public int getDrawable() {
				return R.drawable.logout;
			}
		});
	}
}
