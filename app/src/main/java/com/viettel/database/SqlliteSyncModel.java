/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.ConstrNodeItemsField;
import com.viettel.database.field.Supv_Constr_Attach_FileField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.sync.SyncModel;
import com.viettel.sync.SyncTableInfo;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Thu vien thao tac voi slqlite
 * 
 * @author: Datnv5
 * @throws:
 */
public class SqlliteSyncModel {

	protected static SqlliteSyncModel instance;

	protected SqlliteSyncModel() {

	}

	public static SqlliteSyncModel getInstance() {
		if (instance == null) {
			instance = new SqlliteSyncModel();
		}
		return instance;
	}

	/**
	 * Ham lay gia tri maxprocessId cua mot bang
	 * 
	 * @param pTableName
	 * @return
	 */
	public long getMaxProcessId(String pTableName) {
		long iResult = 0;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		Cursor cursor = db.query(pTableName, new String[] { "MAX("
				+ BaseField.COLUMN_PROCESS_ID + ")" }, null, null, null, null,
				null);
		try {
			cursor.moveToFirst();
			iResult = cursor.getLong(0);
		} finally {
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return iResult;
	}

	/**
	 * Ham lay gia tri maxprocessId cua mot bang
	 * 
	 * @param pTableName
	 * @return
	 */
	public long getMaxProcessId(String pTableName, long idUser) {
		long iResult = 0;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		Cursor cursor = db.query(pTableName, new String[] { "MAX("
				+ BaseField.COLUMN_PROCESS_ID + ")" },
				BaseField.COLUMN_EMPLOYEE_ID + " = ?",
				new String[] { String.valueOf(idUser) }, null, null, null);
		try {
			cursor.moveToFirst();
			iResult = cursor.getLong(0);
		} finally {
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return iResult;
	}

	/**
	 * Ham kiem tra xem 1 item da ton tai chua
	 * 
	 * @param pTableName
	 *            : Ten bang
	 * @param pColumnId
	 *            : truong khoa cua bang
	 * @return
	 */
	public boolean checkExitItem(String pTableName, String pColumnId,
			String pItemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		Cursor cursor = db.query(pTableName, new String[] { pColumnId },
				pColumnId + "=?", new String[] { pItemId }, null, null, null);
		try {
			if (cursor.moveToFirst()) {
				bResult = true;
			}

		} finally {
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	/**
	 * Ham insert du lieu su dung transaction
	 * 
	 * @param strTblname
	 * @param hmValues
	 */
	@SuppressWarnings("rawtypes")
	public static void insertDataTransaction(String strTblname,
			HashMap<String, String> hmValues) {
		// queryValues CHUA TRUONG CAN CHEN VA GIA TRI CUA TRUONG
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		String strSql = String
				.format("insert OR REPLACE into %s (", strTblname);
		String strValue = " values (";

		List<String> values = new ArrayList<String>();
		Set<?> set = hmValues.entrySet();
		Iterator<?> i = set.iterator();
		Boolean first = true;
		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			String column = (String) me.getKey();
			String value = (String) me.getValue();
			if (!first) {
				strSql += ",";
				strValue += ",";
			}
			strSql += String.format(" %s ", column);
			strValue += " ? ";
			values.add(value);
			first = false;
		}

		db.beginTransaction();
		try {
			strSql += ")" + strValue + ")";
			SQLiteStatement insert = db.compileStatement(strSql);
			for (int j = 0; j < values.size(); j++) {
				int index = j + 1;
				insert.bindString(index, values.get(j));
			}
			insert.execute();
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction();
		}
		KttsDatabaseHelper.INSTANCE.close();
	}

	/**
	 * Ham insert du lieu khong su dung transaction
	 * 
	 * @param strTblname
	 * @param hmValues
	 */
	@SuppressWarnings("rawtypes")
	public static boolean insertData(String strTblname,
			HashMap<String, String> hmValues) {
		// queryValues CHUA TRUONG CAN CHEN VA GIA TRI CUA TRUONG
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		ContentValues values = new ContentValues();
		Set<?> set = hmValues.entrySet();
		Iterator<?> i = set.iterator();
        Log.d(TAG, "insertData: table name = " + strTblname);
        while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			String column = (String) me.getKey();
			String value = (String) me.getValue();
//			if (strTblname.equals(Work_ItemsField.TABLE_NAME)) {
//				Log.d(TAG, "insertData: value work item = " + value);
//			}
			if (strTblname.equals(ConstrNodeItemsField.TABLE_NAME)) {
				Log.d(TAG, "insertData node : column = " + column + " --- " + "value = "+ value);
			}
			values.put(column, value);
		}
		long resutl = db.insert(strTblname, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (resutl > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Update du lieu cho bang
	 * 
	 * @param strTblname
	 * @param strColumnId
	 * @param hmValues
	 * @param listWhere
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static int updateData(String strTblname, String strColumnId,
			HashMap<String, String> hmValues, HashMap<String, String> listWhere) {
		int returnResult = 0;
		try {
			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
					.open(SyncModel.mContext);
			ContentValues values = new ContentValues();
			Set<?> set = hmValues.entrySet();
			Iterator<?> i = set.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String column = (String) me.getKey();
				String value = (String) me.getValue();
				values.put(column, value);
			}
			List<String> vl_columns = new ArrayList<String>();
			String whereQuery = "";
			if (listWhere != null && listWhere.size() > 0) {
				// ADD THEM DIEU KIEN
				Set<?> set1 = listWhere.entrySet();
				Iterator<?> j = set1.iterator();
				int first = 1;
				while (j.hasNext()) {
					Map.Entry me = (Map.Entry) j.next();
					String textWhere = (String) me.getKey();
					String value = (String) me.getValue();
					if (first != 1)
						whereQuery += " AND ";
					whereQuery += String.format(" %s ?", textWhere);
					vl_columns.add(value);
					first = 0;
				}
				String[] columnsArray = new String[vl_columns.size()];
				vl_columns.toArray(columnsArray);
				returnResult = db.update(strTblname, values, whereQuery,
						columnsArray);
			} else {
				returnResult = db.update(strTblname, values, strColumnId
						+ " = ?", new String[] { hmValues.get(strColumnId) });
			}
			return returnResult;
		} catch (Exception e) {
			Log.i("Update Process Erorr", "Update process, syncstatus errror");
		} finally {
			KttsDatabaseHelper.INSTANCE.close();
		}
		return returnResult;
	}

	/**
	 * Lay du lieu tu bang co dieu kien, neu khong co dieu kien thi truyen bien
	 * la null
	 * 
	 * @param db
	 * @param str_Table
	 * @param columnsGet
	 * @param arrWhere
	 * @param listOrderby
	 * @param start
	 * @param limit
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<HashMap<String, String>> getDataByWhere(
			String str_Table, List<String> columnsGet,
			HashMap<String, String> arrWhere, List<String> listOrderby,
			int start, int limit) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		ArrayList<HashMap<String, String>> wordList;
		/* Mang chua cac gia tri de add vao chuoi qua param */
		List<String> vl_columns = new ArrayList<String>();
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = " SELECT  ";
		int index;
		if (columnsGet == null) {
			selectQuery += " * ";
		} else {
			for (index = 0; index < columnsGet.size(); ++index) {
				if (index > 0)
					selectQuery += " , ";
				selectQuery += String.format(" %s ", columnsGet.get(index));
			}
		}
		selectQuery += String.format(" FROM %s WHERE 1=1 ", str_Table);

		if (arrWhere != null && arrWhere.size() > 0) {
			// ADD THEM DIEU KIEN
			Set<?> set = arrWhere.entrySet();
			Iterator<?> i = set.iterator();
			while (i.hasNext()) {
				Map.Entry me = (Map.Entry) i.next();
				String textWhere = (String) me.getKey();
				String value = (String) me.getValue();
				selectQuery += String.format(" AND %s  ? ", textWhere);
				vl_columns.add(value);
			}
		}

		if (listOrderby != null && listOrderby.size() > 0) {
			selectQuery += " ORDER BY ";
			for (index = 0; index < listOrderby.size(); ++index) {
				if (index > 0)
					selectQuery += ",";
				selectQuery += String.format(" %s ", listOrderby.get(index));
			}
		}

		if (limit != 0 && start >= 0) {
			selectQuery += String.format(" LIMIT %s, %s", start, limit);
		}
		// conver mang de add vao parameter
		String[] columnsArray = new String[vl_columns.size()];
		vl_columns.toArray(columnsArray);
		Cursor cursor = db.rawQuery(selectQuery, columnsArray);
		// do du lieu ra wordList
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < columnsGet.size(); ++j)
					map.put(columnsGet.get(j), cursor.getString(j));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return wordList;
	}

	/**
	 * Lay du lieu tu bang co dieu kien, neu khong co dieu kien thi truyen bien
	 * la null
	 * 
	 * @param db
	 * @param str_Table
	 * @param columnsGet
	 * @param arrWhere
	 * @param listOrderby
	 * @param start
	 * @param limit
	 * @return
	 */

	public static ArrayList<HashMap<String, String>> getDataSync(
			String str_Table, String[] columnsGet, List<String> listOrderby,
			int start, int limit) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
				.open(SyncModel.mContext);
		ArrayList<HashMap<String, String>> wordList;
		/* Mang chua cac gia tri de add vao chuoi qua param */
		List<String> vl_columns = new ArrayList<String>();
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = " SELECT  ";
		int index;
		int length = columnsGet.length;
		if (length == 0) {
			selectQuery += " * ";
		} else {

			for (index = 0; index < length; ++index) {
				if (index > 0)
					selectQuery += " , ";
				selectQuery += String.format(" %s ", columnsGet[index]);
			}
		}
		selectQuery += String.format(" FROM %s WHERE "
				+ BaseField.COLUMN_SYNC_STATUS + " = "
				+ Constants.SYNC_STATUS.ADD + " OR "
				+ BaseField.COLUMN_SYNC_STATUS + " = "
				+ Constants.SYNC_STATUS.EDIT, str_Table);

		if (listOrderby != null && listOrderby.size() > 0) {
			selectQuery += " ORDER BY ";
			for (index = 0; index < listOrderby.size(); ++index) {
				if (index > 0)
					selectQuery += ",";
				selectQuery += String.format(" %s ", listOrderby.get(index));
			}
		}

		if (limit != 0 && start >= 0) {
			selectQuery += String.format(" LIMIT %s, %s", start, limit);
		}
		// conver mang de add vao parameter
		String[] columnsArray = new String[vl_columns.size()];
		vl_columns.toArray(columnsArray);
		Cursor cursor = db.rawQuery(selectQuery, columnsArray);
		// do du lieu ra wordList
		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> map = new HashMap<String, String>();
				for (int j = 0; j < length; ++j)
					map.put(columnsGet[j], cursor.getString(cursor
							.getColumnIndex(columnsGet[j])));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return wordList;
	}

	/**
	 * Lay du lieu cua mot bang thanh json
	 * 
	 * @param str_Table
	 * @param columnsGet
	 * @param listOrderby
	 * @param start
	 * @param limit
	 * @return
	 */

	public static JSONArray getDataJsonSync(long idUser, String str_Table,
			String[] columnsGet, List<String> listOrderby, int start, int limit) {
		JSONArray arr = new JSONArray();
		try {
			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
					.open(SyncModel.mContext);
			/* Mang chua cac gia tri de add vao chuoi qua param */
			List<String> vl_columns = new ArrayList<String>();
			String selectQuery = " SELECT  ";
			int index;
			int length = columnsGet.length;
			if (length == 0) {
				selectQuery += " * ";
			} else {

				for (index = 0; index < length; ++index) {
					if (index > 0)
						selectQuery += " , ";
					selectQuery += String.format(" %s ", columnsGet[index]);
				}
			}
			// if(str_Table.equalsIgnoreCase(Supervision_ConstrField.TABLE_NAME)){
			// selectQuery += String.format(
			// " FROM %s WHERE " + BaseField.COLUMN_EMPLOYEE_ID + " = "
			// + String.valueOf(idUser) +" AND "
			// +Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS
			// + " IN (1,2) ", str_Table);
			// } else
			selectQuery += String.format(
					" FROM %s WHERE " + BaseField.COLUMN_EMPLOYEE_ID + " = "
							+ String.valueOf(idUser) + " AND ("
							+ BaseField.COLUMN_SYNC_STATUS + " = "
							+ Constants.SYNC_STATUS.ADD + " OR "
							+ BaseField.COLUMN_SYNC_STATUS + " = "
							+ Constants.SYNC_STATUS.EDIT + " )", str_Table);

			if (listOrderby != null && listOrderby.size() > 0) {
				selectQuery += " ORDER BY ";
				for (index = 0; index < listOrderby.size(); ++index) {
					if (index > 0)
						selectQuery += ",";
					selectQuery += String
							.format(" %s ", listOrderby.get(index));
				}
			}

			if (limit != 0 && start >= 0) {
				selectQuery += String.format(" LIMIT %s, %s", start, limit);
			}
			// conver mang de add vao parameter
			String[] columnsArray = new String[vl_columns.size()];
			vl_columns.toArray(columnsArray);
//			Log.e("TAG", "getDataJsonSync: " + selectQuery );
			Cursor cursor = db.rawQuery(selectQuery, columnsArray);
			// do du lieu ra wordList
			if (cursor.moveToFirst()) {
				do {
					JSONObject itemJson = new JSONObject();
					for (int j = 0; j < length; ++j) {
						String sColumnName = columnsGet[j];
						String sValue = cursor.getString(cursor
								.getColumnIndex(sColumnName));
						itemJson.put(sColumnName, sValue);
					}

					arr.put(itemJson);
				} while (cursor.moveToNext());
			}
			KttsDatabaseHelper.INSTANCE.close();
			// return contact list

		} catch (Exception e) {
			e.printStackTrace();
//			Log.e("Error", e.getMessage());
		}
		return arr;
	}
	
	public static JSONArray getDataJsonSyncFile(long idUser, String str_Table,
			String[] columnsGet, List<String> listOrderby, int start, int limit) {
		JSONArray arr = new JSONArray();
		try {
			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
					.open(SyncModel.mContext);
			/* Mang chua cac gia tri de add vao chuoi qua param */
			List<String> vl_columns = new ArrayList<String>();
			String selectQuery = " SELECT  ";
			int index;
			int length = columnsGet.length;
			if (length == 0) {
				selectQuery += " * ";
			} else {

				for (index = 0; index < length; ++index) {
					if (index > 0)
						selectQuery += " , ";
					selectQuery += String.format(" %s ", columnsGet[index]);
				}
			}
			// if(str_Table.equalsIgnoreCase(Supervision_ConstrField.TABLE_NAME)){
			// selectQuery += String.format(
			// " FROM %s WHERE " + BaseField.COLUMN_EMPLOYEE_ID + " = "
			// + String.valueOf(idUser) +" AND "
			// +Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS
			// + " IN (1,2) ", str_Table);
			// } else
			selectQuery += String.format(
					" FROM %s WHERE " + BaseField.COLUMN_EMPLOYEE_ID + " = "
							+ String.valueOf(idUser) + " AND ("
							+ BaseField.COLUMN_SYNC_STATUS + " = "
							+ Constants.SYNC_STATUS.ADD + " OR "
							+ BaseField.COLUMN_SYNC_STATUS + " = "
							+ Constants.SYNC_STATUS.EDIT + " ) AND  " + BaseField.COLUMN_IS_ACTIVE + " = 0 ", str_Table);

			if (listOrderby != null && listOrderby.size() > 0) {
				selectQuery += " ORDER BY ";
				for (index = 0; index < listOrderby.size(); ++index) {
					if (index > 0)
						selectQuery += ",";
					selectQuery += String
							.format(" %s ", listOrderby.get(index));
				}
			}

			if (limit != 0 && start >= 0) {
				selectQuery += String.format(" LIMIT %s, %s", start, limit);
			}
			// conver mang de add vao parameter
			String[] columnsArray = new String[vl_columns.size()];
			vl_columns.toArray(columnsArray);
			Cursor cursor = db.rawQuery(selectQuery, columnsArray);
			// do du lieu ra wordList
			if (cursor.moveToFirst()) {
				do {
					JSONObject itemJson = new JSONObject();
					for (int j = 0; j < length; ++j) {
						String sColumnName = columnsGet[j];
						String sValue = cursor.getString(cursor
								.getColumnIndex(sColumnName));
						itemJson.put(sColumnName, sValue);
					}

					arr.put(itemJson);
				} while (cursor.moveToNext());
			}
			KttsDatabaseHelper.INSTANCE.close();
			// return contact list

		} catch (Exception e) {
			Log.i("Error", e.getMessage());
		}
		return arr;
	}

	/**
	 * Lay du lieu cua bang login_log_constr thanh json
	 * 
	 * @param str_Table
	 * @param columnsGet
	 * @param listOrderby
	 * @param start
	 * @param limit
	 * @return
	 */

	public static JSONArray getDataJsonFromLogUser(long idUser,
			String str_Table, String[] columnsGet, int start, int limit) {
		JSONArray arr = new JSONArray();
		try {
			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
					.open(SyncModel.mContext);
			/* Mang chua cac gia tri de add vao chuoi qua param */
			List<String> vl_columns = new ArrayList<String>();
			String selectQuery = " SELECT  ";
			int index;
			int length = columnsGet.length;
			if (length == 0) {
				selectQuery += " * ";
			} else {

				for (index = 0; index < length; ++index) {
					if (index > 0)
						selectQuery += " , ";
					selectQuery += String.format(" %s ", columnsGet[index]);
				}
			}

			selectQuery += String.format(" FROM %s ", str_Table);

			if (limit != 0 && start >= 0) {
				selectQuery += String.format(" LIMIT %s, %s", start, limit);
			}
			// conver mang de add vao parameter
			String[] columnsArray = new String[vl_columns.size()];
			vl_columns.toArray(columnsArray);
			Cursor cursor = db.rawQuery(selectQuery, columnsArray);
			// do du lieu ra wordList
			if (cursor.moveToFirst()) {
				do {
					JSONObject itemJson = new JSONObject();
					for (int j = 0; j < length; ++j) {
						String sColumnName = columnsGet[j];
						String sValue = cursor.getString(cursor
								.getColumnIndex(sColumnName));
						itemJson.put(sColumnName, sValue);
					}

					arr.put(itemJson);
				} while (cursor.moveToNext());
			}
			KttsDatabaseHelper.INSTANCE.close();
			// return contact list

		} catch (Exception e) {
			Log.i("Error", e.getMessage());
		}
		return arr;
	}

//	public static void getColumnName() {
//		try {
//			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
//					.open(SyncModel.mContext);
//			Cursor cursor = db.rawQuery("SELECT * FROM "
//					+ "ACCEPTANCE_BTS_CAT_WORK" + " LIMIT 1", null);
//			String[] colNames = cursor.getColumnNames();
//			Log.i("dfg", "dfg");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		KttsDatabaseHelper.INSTANCE.close();
//	}

	public static JSONArray getDataJsonSyncTest(String str_Table,
			String[] columnsGet, List<String> listOrderby, int start, int limit) {
		JSONArray arr = new JSONArray();
		try {
			SQLiteDatabase db = KttsDatabaseHelper.INSTANCE
					.open(SyncModel.mContext);
			/* Mang chua cac gia tri de add vao chuoi qua param */
			List<String> vl_columns = new ArrayList<String>();
			String selectQuery = " SELECT  ";
			int index;
			int length = columnsGet.length;
			if (length == 0) {
				selectQuery += " * ";
			} else {

				for (index = 0; index < length; ++index) {
					if (index > 0)
						selectQuery += " , ";
					selectQuery += String.format(" %s ", columnsGet[index]);
				}
			}
			selectQuery += String.format(" FROM %s WHERE "
					+ BaseField.COLUMN_PROCESS_ID + " > -1 ", str_Table);

			if (listOrderby != null && listOrderby.size() > 0) {
				selectQuery += " ORDER BY ";
				for (index = 0; index < listOrderby.size(); ++index) {
					if (index > 0)
						selectQuery += ",";
					selectQuery += String
							.format(" %s ", listOrderby.get(index));
				}
			}

			if (limit != 0 && start >= 0) {
				selectQuery += String.format(" LIMIT %s, %s", start, limit);
			}
			// conver mang de add vao parameter
			String[] columnsArray = new String[vl_columns.size()];
			vl_columns.toArray(columnsArray);
			Cursor cursor = db.rawQuery(selectQuery, columnsArray);
			// do du lieu ra wordList
			if (cursor.moveToFirst()) {
				do {
					JSONObject itemJson = new JSONObject();
					for (int j = 0; j < length; ++j) {
						String sColumnName = columnsGet[j];
						String sValue = cursor.getString(cursor
								.getColumnIndex(sColumnName));
						if (sValue == null) {
							sValue = "null";
						}
						itemJson.put(sColumnName, sValue);
					}

					arr.put(itemJson);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			Log.i("Error", e.getMessage());
		} finally {
			KttsDatabaseHelper.INSTANCE.close();
		}
		return arr;
	}

	private static final String TAG = "SqlliteSyncModel";
	/**
	 * Ham them moi hoac sua du lieu update tu server ve
	 * 
	 * @param listUdpate
	 *            Danh sach du lieu them moi hoac sua
	 * @param tableInfo
	 *            Thong tin bang dung them moi hoac sua du lieu
	 */
	public static void updateInsertGetData(
			ArrayList<HashMap<String, String>> listUdpate,
			SyncTableInfo tableInfo) {
        Log.d(TAG, "updateInsertGetData: " + tableInfo.getTableName() + " called --> size = " + listUdpate.size());

        for (HashMap<String, String> map : listUdpate) {
			
			String sKey = map.get(tableInfo.getColumnKey());
			boolean bExit = SqlliteSyncModel.getInstance().checkExitItem(
					tableInfo.getTableName(), tableInfo.getColumnKey(), sKey);
			if (bExit) {
				HashMap<String, String> hmClause = new HashMap<String, String>();
				hmClause.put(tableInfo.getColumnKey() + "=", sKey);
				SqlliteSyncModel.updateData(tableInfo.getTableName(),
						tableInfo.getColumnKey(), map, hmClause);
			} else {
				SqlliteSyncModel.insertData(tableInfo.getTableName(), map);
			}
		}

	}

	/**
	 * Ham update va insert File tu server ve. Truong hop nay phai dua cot
	 * IS_DOWNLOAD ve 0 de danh dau lay du lieu file
	 * 
	 * @param listUdpate
	 *            Danh sach du lieu them moi hoac sua
	 * @param tableInfo
	 *            Thong tin bang dung them moi hoac sua du lieu
	 */
	public static void updateInsertGetFileData(
			ArrayList<HashMap<String, String>> listUdpate,
			SyncTableInfo tableInfo) {
		for (HashMap<String, String> map : listUdpate) {
			map.put(Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD, "0");
			map.put(Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD, "1");
			String sKey = map.get(tableInfo.getColumnKey());
			boolean bExit = SqlliteSyncModel.getInstance().checkExitItem(
					tableInfo.getTableName(), tableInfo.getColumnKey(), sKey);
			if (bExit) {
				HashMap<String, String> hmClause = new HashMap<String, String>();
				hmClause.put(tableInfo.getColumnKey() + "=", sKey);
				SqlliteSyncModel.updateData(tableInfo.getTableName(),
						tableInfo.getColumnKey(), map, hmClause);
			} else {
				SqlliteSyncModel.insertData(tableInfo.getTableName(), map);
			}
		}

	}

	/**
	 * Ham cap nhat lai trang thai dong bo va processId cua ban ghi da dong bo
	 * len server
	 * 
	 * @param listUdpate
	 *            Danh sach du lieu them moi hoac sua
	 * @param tableInfo
	 *            Thong tin bang dung them moi hoac sua du lieu
	 */
	public static void updateSyncStatusProcessId(
			ArrayList<HashMap<String, String>> listUdpate,
			SyncTableInfo tableInfo) {
		for (HashMap<String, String> map : listUdpate) {
			String sKey = map.get(tableInfo.getColumnKey());
			String sProcessId = map.get(BaseField.COLUMN_PROCESS_ID);
			HashMap<String, String> hmClause = new HashMap<String, String>();
			HashMap<String, String> hmValues = new HashMap<String, String>();
			hmValues.put(BaseField.COLUMN_SYNC_STATUS,
					String.valueOf(Constants.SYNC_STATUS.UPDATED));
			hmValues.put(BaseField.COLUMN_PROCESS_ID, sProcessId);

			hmClause.put(tableInfo.getColumnKey() + "=", sKey);
			int iupdate = SqlliteSyncModel.updateData(tableInfo.getTableName(),
					tableInfo.getColumnKey(), hmValues, hmClause);
			Log.e(TAG, "updateSyncStatusProcessId: " + tableInfo.getColumnKey() + " " + sKey + " " + sProcessId);
			if (iupdate == 0) {
				Log.i("Update Process Erorr",
						"Update process, syncstatus errror"
								+ tableInfo.getTableName());
			}

		}

	}

	/**
	 * Ham cap nhat lai trang thai la da download file thanh cong
	 * 
	 * @param pIdFile
	 */
	public static void updateDownloadFileSuccess(long pIdFile) {
		HashMap<String, String> hmClause = new HashMap<String, String>();
		HashMap<String, String> hmValues = new HashMap<String, String>();
		hmValues.put(Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD, "1");
		hmClause.put(
				Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
						+ "=", String.valueOf(pIdFile));
		SqlliteSyncModel.updateData(Supv_Constr_Attach_FileField.TABLE_NAME,
				Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
				hmValues, hmClause);

	}

	/**
	 * Ham cap nhat lai trang thai la da download file thanh cong
	 * 
	 * @param pIdFile
	 */
	public static void updateUploadFileSuccess(long pIdFile) {
		HashMap<String, String> hmClause = new HashMap<String, String>();
		HashMap<String, String> hmValues = new HashMap<String, String>();
		hmValues.put(Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD, "1");
		hmClause.put(
				Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
						+ "=", String.valueOf(pIdFile));
		SqlliteSyncModel.updateData(Supv_Constr_Attach_FileField.TABLE_NAME,
				Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
				hmValues, hmClause);

	}

}