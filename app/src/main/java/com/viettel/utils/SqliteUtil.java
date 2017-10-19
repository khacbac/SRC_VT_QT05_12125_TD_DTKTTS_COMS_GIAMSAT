package com.viettel.utils;

import android.util.Log;

import com.viettel.database.field.BaseField;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class SqliteUtil {
	private static final String TAG = SqliteUtil.class.getSimpleName();

	public static ArrayList<HashMap<String, String>> converJsonToHasMap(
			JSONObject entry, String[] columnsGet) {
		ArrayList<HashMap<String, String>> listResult = new ArrayList<HashMap<String, String>>();
		if (entry == null) {
			return listResult;
		}
		try {
			if (entry.isNull("listResult")) {
				return listResult;
			}
			int length = columnsGet.length;
			JSONArray jsonMainArray = entry.getJSONArray("listResult");
			int total = jsonMainArray.length();
			for (int i = 0; i < total; i++) {
				JSONObject jsonItem = jsonMainArray.getJSONObject(i);
				HashMap<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < length; j++) {
					String sColumn = columnsGet[j];
					Log.d(TAG, "converJsonToHasMap: need get = " + sColumn);
					String sValue = jsonItem.getString(columnsGet[j]);
					// TODO neu server tra ve thi thoi khong co doan nay
					if (sColumn.equals(BaseField.COLUMN_SYNC_STATUS)
							&& StringUtil.isNullOrEmpty(sValue)) {
						sValue = "1";
					}
					map.put(sColumn, sValue);
				}
				listResult.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}


	/**
	 * Ham dung covert to HashMap co tu dong them userId danh cho cac bang cong
	 * trinh
	 * 
	 * @param entry
	 * @param columnsGet
	 * @param idUser
	 * @return
	 */
	public static ArrayList<HashMap<String, String>> converJsonToHasMap(
			JSONObject entry, String[] columnsGet, long idUser) {
		ArrayList<HashMap<String, String>> listResult = new ArrayList<HashMap<String, String>>();
		if (entry == null) {
			return listResult;
		}
		try {
			if (entry.isNull("listResult")) {
				return listResult;
			}
			int length = columnsGet.length;
			JSONArray jsonMainArray = entry.getJSONArray("listResult");
			int total = jsonMainArray.length();
			for (int i = 0; i < total; i++) {
				JSONObject jsonItem = jsonMainArray.getJSONObject(i);
				HashMap<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < length; j++) {
					String sColumn = columnsGet[j];
					String sValue = jsonItem.getString(columnsGet[j]);
					// TODO neu server tra ve thi thoi khong co doan nay
					if (sColumn.equals(BaseField.COLUMN_SYNC_STATUS)
							&& StringUtil.isNullOrEmpty(sValue)) {
						sValue = "1";
					}
					map.put(sColumn, sValue);
				}
				map.put(BaseField.COLUMN_EMPLOYEE_ID, String.valueOf(idUser));
				listResult.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
}
