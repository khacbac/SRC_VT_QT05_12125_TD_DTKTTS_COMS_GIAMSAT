package com.viettel.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.viettel.constants.Constants;

public class PreferenceUtil {
	private SharedPreferences shareSetting = null;
	private int mode = 0;

	public PreferenceUtil(Context context) {
		mode = Context.MODE_PRIVATE;
		shareSetting = context.getSharedPreferences(Constants.FILE_SETTING, mode);
	}

	/**
	 * Ham lay gia String cua setting
	 * 
	 * @param key
	 *            : gia tri ghi vao setting
	 * @return
	 */
	public String getStringPreference(String key) {
		String sResultSetting = "";
		try {
			sResultSetting = shareSetting.getString(key, "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sResultSetting;
	}

	/**
	 * Ham ghi gia tri setting
	 * 
	 * @param key
	 *            : khoa luu gia tri
	 * @param value
	 *            : gia tri luu
	 * @return
	 */

	public boolean setStringPreference(String key, String value) {
		try {
			SharedPreferences.Editor editor = shareSetting.edit();
			editor.putString(key, value);
			editor.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/* ham luu gia tri boolean bang shareprefrence */
	public boolean setBooleanPrefernce(String key, boolean value) {
		try {
			SharedPreferences.Editor editor = shareSetting.edit();
			editor.putBoolean(key, value);
			editor.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	/* ham get gia tri boolean tu shareprefrence */
	public boolean getBooleanPreference(String key) {
		boolean bSetting = false;
		try {
			bSetting = shareSetting.getBoolean(key, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return bSetting;
	}
	
	public boolean setLongPreference(String key, long value){
		try {
			SharedPreferences.Editor editor = shareSetting.edit();
			editor.putLong(key, value);
			editor.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public long getLongPreference(String key){
		long sLongSetting = -1;
		try {
			sLongSetting = shareSetting.getLong(key, -1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sLongSetting;
	}

}
