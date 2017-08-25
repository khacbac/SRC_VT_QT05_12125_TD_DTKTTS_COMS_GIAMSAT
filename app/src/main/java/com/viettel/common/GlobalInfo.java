package com.viettel.common;

import android.app.Application;
import android.content.Context;

import com.viettel.constants.Constants;
import com.viettel.utils.PreferenceUtil;
import com.viettel.utils.StringUtil;

public class GlobalInfo extends Application {
	private static GlobalInfo instance = null;
	/* application context */
	private Context appContext;// activity application
	private Context activityContext;// activity context
	private String ClientId = "";
	private String FilePath = "";
	private String FullName = "";
	private String GroupCode = "";
	private String EmployeeCode = "";
	private long UserId = 0;
	// phan luu xuong file
	private Profile profile = new Profile();

	@Override
	public void onCreate() {
		super.onCreate();
		appContext = this;
	}

	public static GlobalInfo getInstance() {
		if (instance == null) {
			instance = new GlobalInfo();
		}
		return instance;
	}

	public void setAppContext(Context context) {
		this.appContext = context;

	}

	public Context getAppContext() {
		if (appContext == null) {
			appContext = new GlobalInfo();
		}
		return appContext;
	}

	public void setActivityContext(Context context) {
		this.activityContext = context;

	}

	public Context getActivityContext() {
		return activityContext;
	}

	// check xem nguoi dung da check in hay chua
	public boolean isCheckIn() {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return preUtil.getBooleanPreference(Constants.CHECK_IN_STATUS_SETTING);
		} catch (Exception e) {
		}
		return false;
	}
	
	public boolean isCheckIn(Context mContext) {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(mContext);
			return preUtil.getBooleanPreference(Constants.CHECK_IN_STATUS_SETTING);
		} catch (Exception e) {
		}
		return false;
	}

	public void setCheckIn(boolean checkIn) {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			preUtil.setBooleanPrefernce(Constants.CHECK_IN_STATUS_SETTING, checkIn);
		} catch (Exception e) {
		}
	}
	
	public void setCheckIn(boolean checkIn,Context mContext) {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(mContext);
			preUtil.setBooleanPrefernce(Constants.CHECK_IN_STATUS_SETTING, checkIn);
		} catch (Exception e) {
		}
	}
	
	//get set id cua table chekcin
	public void setCheckInTBId(Context mContext,long supv_locate_id){
		try {
			PreferenceUtil preUtil = new PreferenceUtil(mContext);
			preUtil.setLongPreference(Constants.CHECKIN_TABLE_ID_SETTING, supv_locate_id);
		} catch (Exception e) {
		}
	}
	
	public long getCheckInTBId(Context mContext){
		try {
			PreferenceUtil preUtil = new PreferenceUtil(mContext);
			return preUtil.getLongPreference(Constants.CHECKIN_TABLE_ID_SETTING);
		} catch (Exception e) {
		}
		return -1;
	}
	
	// check xem nguoi dung da login hay chua
	public boolean isLogin() {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return preUtil.getBooleanPreference(Constants.CHECK_LOGIN_STATUS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public void setCheckLogin(boolean isLogin) {
		try {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			preUtil.setBooleanPrefernce(Constants.CHECK_LOGIN_STATUS, isLogin);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getClientId() {
		if (StringUtil.isNullOrEmpty(ClientId)) {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return preUtil.getStringPreference(Constants.CLIENTID_SETTING);
		} else {
			return ClientId;
		}
	}

	public void setClientId(String clientId) {
		ClientId = clientId;
	}

	public String getEmployeeCode() {
		return EmployeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		EmployeeCode = employeeCode;
	}

	public static void setInstance(GlobalInfo instance) {
		GlobalInfo.instance = instance;
	}

	public String getFilePath() {
		if (StringUtil.isNullOrEmpty(FilePath)) {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return preUtil.getStringPreference(Constants.FILE_PATH_SETTING);
		} else {
			return FilePath;
		}
	}

	public void setFilePath(String filePath) {
		FilePath = filePath;
	}

	public String getFullName() {
		if (StringUtil.isNullOrEmpty(FullName)) {
			try {
				PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
				return preUtil.getStringPreference(Constants.USER_FULLNAME_SETTING);
			} catch (Exception e) {
				return FullName;
			}
			
		} else {
			return FullName;
		}
	}

	public void setFullName(String fullName) {
		FullName = fullName;
	}

	public long getUserId() {
		if (UserId == 0) {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return Long.parseLong(preUtil.getStringPreference(Constants.USER_ID_SETTING));
		} else {
			return UserId;
		}
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public String getGroupCode() {
		if (StringUtil.isNullOrEmpty(GroupCode)) {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			return preUtil.getStringPreference(Constants.GROUP_CODE_SETTING);
		} else {
			return GroupCode;
		}
	}

	public void setGroupCode(String groupCode) {
		GroupCode = groupCode;
	}

	public boolean saveGlobalInfo() {
		boolean bResult = false;
		try {
			PreferenceUtil preUtil = new PreferenceUtil(GlobalInfo.getInstance().getAppContext());
			preUtil.setStringPreference(Constants.USER_FULLNAME_SETTING, GlobalInfo.getInstance().getFullName());

			preUtil.setStringPreference(Constants.USER_ID_SETTING,
					String.valueOf(GlobalInfo.getInstance().getUserId()));

			preUtil.setStringPreference(Constants.GROUP_CODE_SETTING,
					String.valueOf(GlobalInfo.getInstance().getGroupCode()));

			preUtil.setStringPreference(Constants.EMPLOYEE_CODE_SETTING,
					String.valueOf(GlobalInfo.getInstance().getEmployeeCode()));
			preUtil.setStringPreference(Constants.FILE_PATH_SETTING, GlobalInfo.getInstance().getFilePath());

			preUtil.setStringPreference(Constants.CLIENTID_SETTING, GlobalInfo.getInstance().getClientId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bResult;
	}
}
