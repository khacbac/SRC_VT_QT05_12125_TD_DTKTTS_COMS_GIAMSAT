package com.viettel.ktts;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ErrorConstants;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.database.EmployeeController;
import com.viettel.database.Login_Log_ConstrController;
import com.viettel.database.entity.EmployeeEntity;
import com.viettel.database.entity.Login_Log_ConstrEntity;
import com.viettel.service.GpsServices;
import com.viettel.utils.PreferenceUtil;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressLint("SimpleDateFormat")
public class LoginActivity extends HomeBaseActivity {
    private EditText editTextUser;
	private EditText editTextPassword;
    private TextView textViewResult;
    private String sUserName = "";
	private String sPassWord = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.login);
        View loginView = addView(R.layout.login, R.id.login_act);
		if (getSupportActionBar() != null)
			getSupportActionBar().hide();
		mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//		SimpleDateFormat gmtDateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		gmtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+7"));
//		gmtDateFormat.setLenient(true);
		
		
		editTextUser = (EditText) loginView.findViewById(R.id.editTextUserName);
		editTextPassword = (EditText) loginView.findViewById(R.id.editTextUserPass);
		textViewResult = (TextView) loginView.findViewById(R.id.tvResult);
        Button lgButton = (Button) loginView.findViewById(R.id.btnLogin);
		lgButton.setOnClickListener(this);
		this.editTextUser.setText(this.getLoginNameSetting());
		/* Load Client Id de thuc hien he thong */
		GlobalInfo.getInstance().setClientId(this.getClientIdSetting());
		if (StringUtil.isNullOrEmpty(GlobalInfo.getInstance().getClientId())) {
			PreferenceUtil preUtil = new PreferenceUtil(this);
			String macAddress = this.getMacAddress();
			boolean bIsSave = preUtil.setStringPreference(
					Constants.CLIENTID_SETTING, macAddress);
			if (bIsSave) {
				GlobalInfo.getInstance().setClientId(macAddress);
			}
		}
		// GlobalInfo.FilePath = this.getFilesDir().getPath();
		GlobalInfo.getInstance().setFilePath(
				Environment.getExternalStorageDirectory().getPath());
		/* dang ky Gps */
        Intent intentGpsService = new Intent(this, GpsServices.class);
		this.startService(intentGpsService);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		GlobalInfo.getInstance().setCheckLogin(false);
		NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNM.cancel(Constants.NOTIFICATION_MANAGER);
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btnLogin:
			Log.d("Location", "gps : "+GpsServices.latLocation+", "+GpsServices.longLocation);
			/* Check open GPS */
			if (this.checkOpenGps()) {
				sUserName = editTextUser.getText().toString().trim();
				sPassWord = editTextPassword.getText().toString().trim();
				String clientId = this.getMacAddress();
				/* Check login online */
				if (this.check3GWifi()) {
					this.requestLogin(sUserName, sPassWord, clientId);
				}
				/* Check login offline */
				else {
					EmployeeEntity loginAcount = this.getLoginOffline(
							sUserName, sPassWord);
					if (loginAcount.getId() > 0) {
						GlobalInfo.getInstance().setFullName(
								loginAcount.getFullName());
						GlobalInfo.getInstance().setUserId(loginAcount.getId());
						GlobalInfo.getInstance().setGroupCode(
								loginAcount.getGroupCode());
						GlobalInfo.getInstance().setEmployeeCode(loginAcount.getCode());
						GlobalInfo.getInstance().saveGlobalInfo();
						this.setLoginNameSetting(sUserName);

						// insert login offline.
						Login_Log_ConstrEntity loginLogConstr = new Login_Log_ConstrEntity();
						loginLogConstr.setUserName(sUserName);
						loginLogConstr.setMac(this.getMacAddress());
						SimpleDateFormat dateView = new SimpleDateFormat(
								"yyyy-dd-MM HH:mm:ss");

						loginLogConstr.setLogDate(dateView.format(Calendar
								.getInstance().getTime()));

						new Login_Log_ConstrController(this)
								.insertLoginLogConstr(loginLogConstr);
						this.gotoHomeActivity(new Bundle());
						this.finish();
					} else {
						this.textViewResult
								.setText(getString(R.string.login_offline_error_messager));
					}
				}
				editTextPassword.setText(StringUtil.EMPTY_STRING);

			} else {
				this.showDialog(StringUtil
						.getString(R.string.text_map_location_no_open));
			}
			break;
		default:
			super.onClick(view);
			break;
		}
	}

    /**
     * Yeu cau gui login.
     * @param userName String.
     * @param passWord String.
     * @param clientId String.
     */
	private void requestLogin(String userName, String passWord, String clientId) {
		showProgressDialog(StringUtil.getString(R.string.text_loading));
		Bundle bundle = new Bundle();
		bundle.putString(IntentConstants.INTENT_LOGIN_NAME, userName);
		bundle.putString(IntentConstants.INTENT_PASSWORD, passWord);
		bundle.putString(IntentConstants.INTENT_CLIENTID, clientId);

		ActionEvent e = new ActionEvent();
		e.action = ActionEventConstant.REQEST_LOGIN;
		e.viewData = bundle;
		e.isBlockRequest = true;
		e.sender = this;
		Home_Controller.getInstance().handleViewEvent(e);
	}

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_LOGIN:
			EmployeeEntity loginAcount = this.getLoginOffline(sUserName,
					sPassWord);
            if (loginAcount.getId() <= 0) {
                EmployeeController loginControll;
                loginControll = new EmployeeController(this);
                loginAcount = (EmployeeEntity) modelEvent.getModelData();
                loginControll.addLogin(loginAcount);
                /* Tao du lieu gia de test */
                // CreateDataTest createdb = new CreateDataTest(this);
                // createdb.createData();
            }
            GlobalInfo.getInstance().setFullName(loginAcount.getFullName());
			GlobalInfo.getInstance().setUserId(loginAcount.getId());
			GlobalInfo.getInstance().setGroupCode(loginAcount.getGroupCode());
			GlobalInfo.getInstance().setEmployeeCode(loginAcount.getCode());
			GlobalInfo.getInstance().saveGlobalInfo();
			this.setLoginNameSetting(sUserName);
			this.gotoHomeActivity(new Bundle());
			this.finish();
			closeProgressDialog();
			break;
		case ActionEventConstant.REQEST_SYNC:
			this.setLoginNameSetting(sUserName);
			this.gotoHomeActivity(new Bundle());
			this.finish();
			closeProgressDialog();
			break;
		default:
			super.handleModelViewEvent(modelEvent);
			break;
		}
	}

	@Override
	public void handleErrorModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_LOGIN:
			if (modelEvent.getModelCode() == ErrorConstants.ERROR_NO_CONNECTION) {
				textViewResult
						.setText(Constants.MESSAGE_ERROR_NO_CONNECT_SERVER);
				Toast.makeText(getApplicationContext(),
						Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, Toast.LENGTH_SHORT).show();
			} else {
				textViewResult
						.setText(getString(R.string.login_error_messager));
				Toast.makeText(getApplicationContext(), getString(R.string.login_error_messager),
                        Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			super.handleErrorModelViewEvent(modelEvent);
			break;
		}
		closeProgressDialog();
	}

	/**
	 * Ham an ban phim khi bam ra ngoai man hinh
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View view = getCurrentFocus();
		if (view instanceof EditText)
			hideKeyboard();
		return super.dispatchTouchEvent(event);
	}

}