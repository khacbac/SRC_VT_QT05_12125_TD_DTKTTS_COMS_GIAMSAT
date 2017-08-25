package com.viettel.camera.kutcamera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.viettel.camera.common.DateConvert;
import com.viettel.camera.entity.Constr_Construction_EmployeeEntity;
import com.viettel.camera.service.GPSTracker;
import com.viettel.ktts.R;
import com.viettel.camera.service.GpsServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";
    //	ImageView image;
    Activity context;
    Preview preview;
    Camera camera;
    //	Button exitButton;
    ImageView fotoButton;
    private ImageView mImgFlash;
    LinearLayout progressLayout;
    SeekBar seekBar;
    String path = "/sdcard/KutCamera/cache/images/";
    public static TextView infoCoordinates;
    public static TextView infoTime;
    private Intent intentGpsService;
    private Constr_Construction_EmployeeEntity constrCode;

    private static final int REQUEST_CAMERA = 101;
    private static final int REQUEST_GPS = 102;
    int currentZoom = 1;

    private OrientationEventListener mOrientationListener;
    private String strOrientation = null;
    private final String NORMAL_ORIENTATION = "Normal";
    private final String UPSIDE_DOWN_ORIENTATION = "UpsideDown";
    private final String RIGHT_POINT_UP_ORIENTATION = "RightPointUp";
    private final String LEFT_POINT_UP_ORIENTATION = "LeftPointUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        context = this;
        infoCoordinates = (TextView) findViewById(R.id.tv_info_coordinates);
        mImgFlash = (ImageView) findViewById(R.id.imageCameraFlash);
        infoTime = (TextView) findViewById(R.id.tv_info_time);
        fotoButton = (ImageView) findViewById(R.id.imageView_foto);
        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        preview = new Preview(this,
                (SurfaceView) findViewById(R.id.KutCameraFragment));
        FrameLayout frame = (FrameLayout) findViewById(R.id.preview);
        frame.addView(preview);
        preview.setKeepScreenOn(true);

        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            return;
        }
        setCameraFlashOff();
        mOrientationListener = new OrientationEventListener(MainActivity.this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int orientation) {
                if (0 <= orientation && orientation < 30) {
                    strOrientation = NORMAL_ORIENTATION;
                } else if (80 <= orientation && orientation < 100) {
                    strOrientation = LEFT_POINT_UP_ORIENTATION;
                } else if (160 <= orientation && orientation < 200) {
                    strOrientation = UPSIDE_DOWN_ORIENTATION;
                } else if (260 <= orientation && orientation < 300) {
                    strOrientation = RIGHT_POINT_UP_ORIENTATION;
                }
            }
        };

        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        } else {
            mOrientationListener.disable();
        }

        fotoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                doTakePicture();
            }
        });
        mImgFlash.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImgFlash.getTag().equals("FlashOff")) {
                    setCameraFlashOn();
                } else if(mImgFlash.getTag().equals("FlashOn")) {
                    setCameraFlashAuto();
                } else if (mImgFlash.getTag().equals("FlashAuto")) {
                    setCameraFlashOff();
                }
            }
        });
        infoCoordinates.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    infoCoordinates.setText("Long: " + GpsServices.formatNumber(longitude) + " , Lat: " + GpsServices.formatNumber(latitude));
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    infoCoordinates.setText("Click to get location");
                    gps.showSettingsAlert();
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLocation();
            }
        },2000);
    }

    private void setCameraFlashOn() {
        if(android.os.Build.VERSION.SDK_INT > 15)
        {
            // for API above 15
            mImgFlash.setBackground(getResources().getDrawable(R.drawable.flash_on));
        }
        else
        {
            // for API below 15
            mImgFlash.setBackgroundDrawable(getResources().getDrawable(R.drawable.flash_on));
        }
        mImgFlash.setTag("FlashOn");
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        camera.setParameters(parameters);
    }

    private void setCameraFlashOff() {
        if(android.os.Build.VERSION.SDK_INT > 15)
        {
            // for API above 15
            mImgFlash.setBackground(getResources().getDrawable(R.drawable.flash_off));
        }
        else
        {
            // for API below 15
            mImgFlash.setBackgroundDrawable(getResources().getDrawable(R.drawable.flash_off));
        }
        mImgFlash.setTag("FlashOff");
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(parameters);
    }

    private void setCameraFlashAuto() {
        if(android.os.Build.VERSION.SDK_INT > 15)
        {
            // for API above 15
            mImgFlash.setBackground(getResources().getDrawable(R.drawable.flash_auto));
        }
        else
        {
            // for API below 15
            mImgFlash.setBackgroundDrawable(getResources().getDrawable(R.drawable.flash_auto));
        }
        mImgFlash.setTag("FlashAuto");
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        camera.setParameters(parameters);
    }

    void getLocation(){
        GPSTracker gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            infoCoordinates.setText("Long: " + GpsServices.formatNumber(longitude) + " , Lat: " + GpsServices.formatNumber(latitude));
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            infoCoordinates.setText("Click to get location");
            gps.showSettingsAlert();
        }
    }

    private void startGPSService() {
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_GPS);
            Log.d(TAG, "startGPSService() returned: " + "ACCESS_FINE_LOCATION");
            return;
        }

        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_GPS);
            Log.d(TAG, "startGPSService() returned: " + "ACCESS_COARSE_LOCATION");
            return;
        }

		/* dang ky Gps */
        intentGpsService = new Intent(this, GpsServices.class);
        Log.d(TAG, "startGPSService() called");
        this.startService(intentGpsService);
    }

    private void doTakePicture() {
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);
            return;
        }
        Log.d(TAG, "doTakePicture() called");
        try {
            takeFocusedPicture();
        } catch (Exception e) {
            Log.e(TAG, "doTakePicture: ", e);
        }
        fotoButton.setClickable(false);
        progressLayout.setVisibility(View.VISIBLE);
    }

    private void openCamera() {
        Log.d(TAG, "openCamera() called");
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            Log.d(TAG, "openCamera() returned: ");
            return;
        }

        camera = getCameraInstance();
        Log.d(TAG, "openCamera() " + (camera == null));
        if (camera != null) {
            setCameraDisplayOrientation(context, CameraInfo.CAMERA_FACING_BACK,
                    camera);
            preview.setCamera(camera);
            seekBar.setMax(camera.getParameters().getMaxZoom());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(progress==0) progress = 1;
                    if(progress<=camera.getParameters().getMaxZoom()){
                        Camera.Parameters parameters = camera.getParameters();
                        parameters.setZoom(progress);
                        camera.setParameters(parameters);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case REQUEST_CAMERA: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					openCamera();

                    final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
//					startGPSService();
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                return;
            }

            case REQUEST_GPS: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startGPSService();
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                return;
            }
        }
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            Log.e(TAG, "getCameraInstance: ", e);
            // cannot get camera or does not exist
        }
        return camera;
    }

    // @Override
    // protected void onResume() {
    // super.onResume();
    // // TODO Auto-generated method stub
    //
    // }

    private void setCameraDisplayOrientation(Activity activity, int cameraId,
                                             Camera camera) {
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {

            try {
                Log.d(TAG, "onAutoFocus() called with: success = [" + success + "], camera = [" + camera + "]");
                camera.takePicture(mShutterCallback, null, jpegCallback);
            } catch (Exception e) {

            }

        }
    };

    ShutterCallback mShutterCallback = new ShutterCallback() {

        @Override
        public void onShutter() {
            // TODO Auto-generated method stub

        }
    };

    public void takeFocusedPicture() {
        Log.d(TAG, "takeFocusedPicture() called");
        camera.autoFocus(mAutoFocusCallback);
    }

    PictureCallback rawCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // Log.d(TAG, "onPictureTaken - raw");
        }
    };

    public Bitmap combineImages(Bitmap background, Bitmap foreground, boolean top) {

        int width = 0, height = 0;
        Bitmap cs;

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas comboImage = new Canvas(cs);
        background = Bitmap.createScaledBitmap(background, width, height, true);

        comboImage.drawBitmap(background, 0, 0, null);
        if (top) {
            comboImage.drawBitmap(foreground, 0, 0, null);
        } else {
            comboImage.drawBitmap(foreground, 0, height - foreground.getHeight(), null);
        }
        return cs;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            //Do something
            doTakePicture();
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (intentGpsService != null) {
                stopService(intentGpsService);
            }
            finish();
        }
        return true;
    }

    PictureCallback jpegCallback = new PictureCallback() {
        @SuppressWarnings("deprecation")
        public void onPictureTaken(byte[] data, Camera camera) {
            String constrCode = "";
            String supervisorName = "";
            String employeeCode = "";
            String strOrien = strOrientation;
            try {
                constrCode = getIntent().getExtras().getString("constrCode");
                supervisorName = getIntent().getExtras().getString("supervisorName");
                employeeCode = getIntent().getExtras().getString("employeeCode");
            } catch (Exception e) {
                e.printStackTrace();
            }

            infoTime.setText(
                    getString(R.string.constr_code) + " " + constrCode +
                            "     " +
                            DateConvert.convertDateToString(Calendar.getInstance().getTime()) + "\n" +
                            getString(R.string.supervisor_name) + " " + supervisorName + "     " +
                            getString(R.string.employee_code) + " " + employeeCode
            );
            Uri saveUri = null;
            try {
                saveUri = (Uri) getIntent().getExtras().getParcelable(MediaStore.EXTRA_OUTPUT);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (saveUri != null) {
                // Save the bitmap to the specified URI (use a try/catch
                // block)
                OutputStream outputStream = null;
                try {
                    outputStream = getContentResolver().openOutputStream(saveUri);
                    outputStream.write(data); // write your bitmap here
                    outputStream.close();

                    Bitmap background = BitmapFactory.decodeFile(saveUri
                            .getPath());
                    switch (strOrien) {
                        case NORMAL_ORIENTATION: {
                            background = rotateBitmap(background,90);
                            break;
                        }
                        case UPSIDE_DOWN_ORIENTATION: {
                            background = rotateBitmap(background,270);
                            break;
                        }
                        case LEFT_POINT_UP_ORIENTATION: {
                            background = rotateBitmap(background,180);
                            break;
                        }
                        default:
                            break;
                    }

                    View u = findViewById(R.id.tv_info_coordinates);
                    Bitmap foreground = returnBitmapFromView(u);

                    Bitmap combined = combineImages(background, foreground, false);

                    View viewTime = findViewById(R.id.tv_info_time);

                    Bitmap foregroundTime = returnBitmapFromView(viewTime);

                    combined = combineImages(combined, foregroundTime, true);

                    if (strOrien.equals(NORMAL_ORIENTATION) ||
                            strOrien.equals(UPSIDE_DOWN_ORIENTATION)) {
                        combined = Bitmap.createScaledBitmap(combined,
                                (int) (combined.getHeight() * 1.5),
                                combined.getWidth(),
                                false);
                    }

                    outputStream = getContentResolver().openOutputStream(
                            saveUri);
                    combined.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);
                    outputStream.flush();
                    outputStream.close();


//					mImage.setVisibility(View.VISIBLE);
//					infoCoordinates.setVisibility(View.INVISIBLE);
//					mImage.setImageBitmap(combined);
                    setResult(RESULT_OK);
                    finish();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            fotoButton.setClickable(true);
//			camera.startPreview();
            progressLayout.setVisibility(View.GONE);
//			exitButton.setClickable(true);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(camera!=null) camera.release();
        mOrientationListener.disable();
    }

    public Bitmap returnBitmapFromView(View u) {
//		View u = findViewById(R.id.tv_info_coordinates);
        u.setDrawingCacheEnabled(true);

        int totalHeight = infoCoordinates.getHeight();
        int totalWidth = infoCoordinates.getWidth();
//		u.layout(0, 0, totalWidth, totalHeight);
        u.buildDrawingCache(true);
        Bitmap foreground = Bitmap.createBitmap(u.getDrawingCache());
        u.setDrawingCacheEnabled(false);

        return foreground;
    }


    @Override
    public void onBackPressed() {
        stopService(intentGpsService);
        finish();
        System.exit(0);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    private Bitmap rotateBitmap(Bitmap bitmap,int degree) {
        Matrix mtBackground = new Matrix();
        mtBackground.postRotate(degree);
        bitmap = Bitmap.createBitmap(
                bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(),
                mtBackground, true);
        return bitmap;
    }

}


/*
package com.viettel.camera.kutcamera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.viettel.camera.common.DateConvert;
import com.viettel.camera.entity.Constr_Construction_EmployeeEntity;
import com.viettel.camera.service.GPSTracker;
import com.viettel.ktts.R;
import com.viettel.camera.service.GpsServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnTouchListener {
    private static final String TAG = "MainActivity";
    //	ImageView image;
    Activity context;
    Preview preview;
    Camera camera;
    //	Button exitButton;
    ImageView fotoButton;
    LinearLayout progressLayout;
    SeekBar seekBar;
    String path = "/sdcard/KutCamera/cache/images/";
    public static TextView infoCoordinates;
    public static TextView infoTime;
    private Intent intentGpsService;
    private Constr_Construction_EmployeeEntity constrCode;

    private static final int REQUEST_CAMERA = 101;
    private static final int REQUEST_GPS = 102;
    int currentZoom = 1;

    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        context = this;
        infoCoordinates = (TextView) findViewById(R.id.tv_info_coordinates);
        infoTime = (TextView) findViewById(R.id.tv_info_time);
        fotoButton = (ImageView) findViewById(R.id.imageView_foto);
        progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        seekBar = (SeekBar)findViewById(R.id.seek_bar);
        preview = new Preview(this,
                (SurfaceView) findViewById(R.id.KutCameraFragment));
        FrameLayout frame = (FrameLayout) findViewById(R.id.preview);
        frame.addView(preview);
        preview.setKeepScreenOn(true);

        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            openCamera();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            return;
        }
        fotoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                doTakePicture();
            }
        });
        infoCoordinates.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                GPSTracker gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    infoCoordinates.setText("Long: " + GpsServices.formatNumber(longitude) + " , Lat: " + GpsServices.formatNumber(latitude));
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    infoCoordinates.setText("Click to get location");
                    gps.showSettingsAlert();
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getLocation();
            }
        },2000);
    }

    void getLocation(){
        GPSTracker gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            // \n is for new line
            infoCoordinates.setText("Long: " + GpsServices.formatNumber(longitude) + " , Lat: " + GpsServices.formatNumber(latitude));
//                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            infoCoordinates.setText("Click to get location");
            gps.showSettingsAlert();
        }
    }

    private void startGPSService() {
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_GPS);
            Log.d(TAG, "startGPSService() returned: " + "ACCESS_FINE_LOCATION");
            return;
        }

        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_GPS);
            Log.d(TAG, "startGPSService() returned: " + "ACCESS_COARSE_LOCATION");
            return;
        }

		*/
/* dang ky Gps *//*

        intentGpsService = new Intent(this, GpsServices.class);
        Log.d(TAG, "startGPSService() called");
        this.startService(intentGpsService);
    }

    private void doTakePicture() {
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA);
            return;
        }
        Log.d(TAG, "doTakePicture() called");
        try {
            takeFocusedPicture();
        } catch (Exception e) {
            Log.e(TAG, "doTakePicture: ", e);
        }
        fotoButton.setClickable(false);
        progressLayout.setVisibility(View.VISIBLE);
    }

    private void openCamera() {
        Log.d(TAG, "openCamera() called");
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
            Log.d(TAG, "openCamera() returned: ");
            return;
        }

        camera = getCameraInstance();
        Log.d(TAG, "openCamera() " + (camera == null));
        if (camera != null) {
            setCameraDisplayOrientation(context, CameraInfo.CAMERA_FACING_BACK,
                    camera);
            preview.setCamera(camera);
            seekBar.setMax(camera.getParameters().getMaxZoom());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(progress==0) progress = 1;
                    if(progress<=camera.getParameters().getMaxZoom()){
                        Camera.Parameters parameters = camera.getParameters();
                        parameters.setZoom(progress);
                        camera.setParameters(parameters);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            case REQUEST_CAMERA: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					openCamera();

                    final Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
//					startGPSService();
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                return;
            }

            case REQUEST_GPS: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startGPSService();
                } else {
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                return;
            }
        }
    }

    private Camera getCameraInstance() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (Exception e) {
            Log.e(TAG, "getCameraInstance: ", e);
            // cannot get camera or does not exist
        }
        return camera;
    }

    // @Override
    // protected void onResume() {
    // super.onResume();
    // // TODO Auto-generated method stub
    //
    // }

    private void setCameraDisplayOrientation(Activity activity, int cameraId,
                                             Camera camera) {
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360; // compensate the mirror
        } else { // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {

            try {
                Log.d(TAG, "onAutoFocus() called with: success = [" + success + "], camera = [" + camera + "]");
                camera.takePicture(mShutterCallback, null, jpegCallback);
            } catch (Exception e) {

            }

        }
    };

    ShutterCallback mShutterCallback = new ShutterCallback() {

        @Override
        public void onShutter() {
            // TODO Auto-generated method stub

        }
    };

    public void takeFocusedPicture() {
        Log.d(TAG, "takeFocusedPicture() called");
        camera.autoFocus(mAutoFocusCallback);
    }

    PictureCallback rawCallback = new PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {
            // Log.d(TAG, "onPictureTaken - raw");
        }
    };

    public Bitmap combineImages(Bitmap background, Bitmap foreground, boolean top) {

        int width = 0, height = 0;
        Bitmap cs;

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = getWindowManager().getDefaultDisplay().getHeight();

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas comboImage = new Canvas(cs);
        background = Bitmap.createScaledBitmap(background, width, height, true);

        comboImage.drawBitmap(background, 0, 0, null);
        if (top) {
            comboImage.drawBitmap(foreground, 0, 0, null);
        } else {
            comboImage.drawBitmap(foreground, 0, height - foreground.getHeight(), null);
        }
        return cs;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown() called with: keyCode = [" + keyCode + "], event = [" + event + "]");
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
            //Do something
            doTakePicture();
        }
        return true;
    }

    PictureCallback jpegCallback = new PictureCallback() {
        @SuppressWarnings("deprecation")
        public void onPictureTaken(byte[] data, Camera camera) {
            String constrCode = "";
            String supervisorName = "";
            String employeeCode = "";
            try {
                constrCode = getIntent().getExtras().getString("constrCode");
                supervisorName = getIntent().getExtras().getString("supervisorName");
                employeeCode = getIntent().getExtras().getString("employeeCode");
            } catch (Exception e) {
                e.printStackTrace();
            }

            infoTime.setText(
                    getString(R.string.constr_code) + " " + constrCode +
                            "     " +
                            DateConvert.convertDateToString(Calendar.getInstance().getTime()) + "\n" +
                            getString(R.string.supervisor_name) + " " + supervisorName + "     " +
                            getString(R.string.employee_code) + " " + employeeCode
            );
            Uri saveUri = null;
            try {
                saveUri = (Uri) getIntent().getExtras().getParcelable(MediaStore.EXTRA_OUTPUT);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            if (saveUri != null) {
                // Save the bitmap to the specified URI (use a try/catch
                // block)
                OutputStream outputStream = null;
                try {
                    outputStream = getContentResolver().openOutputStream(saveUri);
                    outputStream.write(data); // write your bitmap here
                    outputStream.close();

                    Bitmap background = BitmapFactory.decodeFile(saveUri
                            .getPath());
                    View u = findViewById(R.id.tv_info_coordinates);
                    Bitmap foreground = returnBitmapFromView(u);

                    Bitmap combined = combineImages(background, foreground, false);

                    View viewTime = findViewById(R.id.tv_info_time);

                    Bitmap foregroundTime = returnBitmapFromView(viewTime);

                    combined = combineImages(combined, foregroundTime, true);

                    outputStream = getContentResolver().openOutputStream(
                            saveUri);
                    combined.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();


//					mImage.setVisibility(View.VISIBLE);
//					infoCoordinates.setVisibility(View.INVISIBLE);
//					mImage.setImageBitmap(combined);
                    setResult(RESULT_OK);
                    finish();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

            fotoButton.setClickable(true);
//			camera.startPreview();
            progressLayout.setVisibility(View.GONE);
//			exitButton.setClickable(true);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(camera!=null) camera.release();
    }

    public Bitmap returnBitmapFromView(View u) {
//		View u = findViewById(R.id.tv_info_coordinates);
        u.setDrawingCacheEnabled(true);

        int totalHeight = infoCoordinates.getHeight();
        int totalWidth = infoCoordinates.getWidth();
//		u.layout(0, 0, totalWidth, totalHeight);
        u.buildDrawingCache(true);
        Bitmap foreground = Bitmap.createBitmap(u.getDrawingCache());
        u.setDrawingCacheEnabled(false);

        return foreground;
    }


    @Override
    public void onBackPressed() {
        stopService(intentGpsService);
        finish();
        System.exit(0);
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
*/
