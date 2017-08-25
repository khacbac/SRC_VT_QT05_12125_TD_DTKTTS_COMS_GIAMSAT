package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.utils.DateConvert;

public class Supervision_LocateController {
	private Context mContext;

	public Supervision_LocateController(Context mContext) {
		this.mContext = mContext;
	}
	
	public static final String[] allColumn = {
			Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID,
			Supervision_Locate_Field.COLUMN_EMPLOYEE_ID,
			Supervision_Locate_Field.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_Locate_Field.COLUMN_IS_ACTIVE,
			Supervision_Locate_Field.COLUMN_CHECK_IN_STATUS,
			Supervision_Locate_Field.COLUMN_CHECK_IN_LATITUDE,
			Supervision_Locate_Field.COLUMN_CHECK_IN_LONGTITUDE,
			Supervision_Locate_Field.COLUMN_CHECK_OUT_LATITUDE,
			Supervision_Locate_Field.COLUMN_CHECK_OUT_LONGTITUDE,
			Supervision_Locate_Field.COLUMN_CHECK_IN_DATE,
			Supervision_Locate_Field.COLUMN_CHECK_OUT_DATE,
			Supervision_Locate_Field.COLUMN_USER_PLAN,
			Supervision_Locate_Field.COLUMN_CAT_PROVINCES,
			Supervision_Locate_Field.COLUMN_SYNC_STATUS,
			Supervision_Locate_Field.COLUMN_PROCESS_ID
	};
	
	public static final String CREATE_SUPERVISION_LOCATE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Locate_Field.TABLE_NAME + "("
			+ Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID +" TEXT PRIMARY KEY,"
			+ Supervision_Locate_Field.COLUMN_EMPLOYEE_ID + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_SUPERVISION_CONSTR_ID + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_IS_ACTIVE + " INTEGER,"
			+ Supervision_Locate_Field.COLUMN_CHECK_IN_STATUS + " INTEGER,"
			+ Supervision_Locate_Field.COLUMN_CHECK_IN_LATITUDE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CHECK_IN_LONGTITUDE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CHECK_OUT_LATITUDE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CHECK_OUT_LONGTITUDE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CHECK_IN_DATE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CHECK_OUT_DATE + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_USER_PLAN + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_CAT_PROVINCES + " TEXT,"
			+ Supervision_Locate_Field.COLUMN_SYNC_STATUS + " INTEGER,"
			+ Supervision_Locate_Field.COLUMN_PROCESS_ID
			+ " INTEGER)";
	
	public boolean insertSupervisionLocateEntity(Supervision_Locate_Entity supervision_Locate_Entity){
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID, supervision_Locate_Entity.getLocateId());
		values.put(Supervision_Locate_Field.COLUMN_EMPLOYEE_ID, supervision_Locate_Entity.getEmployeeId());
		values.put(Supervision_Locate_Field.COLUMN_SUPERVISION_CONSTR_ID, supervision_Locate_Entity.getSuppervisionId());
		values.put(Supervision_Locate_Field.COLUMN_IS_ACTIVE, supervision_Locate_Entity.getIsActive());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_STATUS, supervision_Locate_Entity.getIsCheckin());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_LATITUDE, supervision_Locate_Entity.getCheckinLatitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_LONGTITUDE, supervision_Locate_Entity.getCheckinLongtitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_LATITUDE, supervision_Locate_Entity.getLogoutLatitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_LONGTITUDE, supervision_Locate_Entity.getLogoutLongtitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_DATE, DateConvert.convertDateToStringCheckIn(supervision_Locate_Entity.getCheckinDate()));
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_DATE, DateConvert.convertDateToStringCheckIn(supervision_Locate_Entity.getCheckoutDate()));
		values.put(Supervision_Locate_Field.COLUMN_USER_PLAN, supervision_Locate_Entity.getPlan().trim().replaceAll("\\u000A", " "));
		values.put(Supervision_Locate_Field.COLUMN_CAT_PROVINCES, supervision_Locate_Entity.getProvinceId());
		values.put(Supervision_Locate_Field.COLUMN_SYNC_STATUS, supervision_Locate_Entity.getSync_Status());
		values.put(Supervision_Locate_Field.COLUMN_PROCESS_ID, supervision_Locate_Entity.getProcessId());
		
		// Inserting Row
		db.insert(Supervision_Locate_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		bResult = true;
		return bResult;
	}
	
	public Supervision_Locate_Entity getSupvLocateItem(long itemId){
		Supervision_Locate_Entity curItem = new Supervision_Locate_Entity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Locate_Field.TABLE_NAME, allColumn,
						Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID
								+ "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}
	
	public Supervision_Locate_Entity converCursorToItem(Cursor cursor){
		Supervision_Locate_Entity curItem = new Supervision_Locate_Entity();
		try {
			curItem.setLocateId(cursor.getLong(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID)));
			curItem.setEmployeeId(cursor.getLong(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_EMPLOYEE_ID)));
			curItem.setSuppervisionId(cursor.getLong(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setProvinceId(cursor.getLong(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CAT_PROVINCES)));
			curItem.setIsActive(cursor.getInt(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_IS_ACTIVE)));
			curItem.setIsCheckin(cursor.getInt(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_IN_STATUS)));
			curItem.setSync_Status(cursor.getInt(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_SYNC_STATUS)));
			curItem.setCheckinLatitude(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_IN_LATITUDE)));
			curItem.setCheckinLongtitude(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_IN_LONGTITUDE)));
			curItem.setLogoutLatitude(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_OUT_LATITUDE)));
			curItem.setLogoutLongtitude(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_OUT_LONGTITUDE)));
			curItem.setPlan(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_USER_PLAN)));
			curItem.setCheckinDate(DateConvert.convertStringToDateCheckIn(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_IN_DATE))));
			curItem.setCheckoutDate(DateConvert.convertStringToDateCheckIn(cursor.getString(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_CHECK_OUT_DATE))));
			curItem.setProcessId(cursor.getLong(cursor.getColumnIndex(Supervision_Locate_Field.COLUMN_PROCESS_ID)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return curItem;
	}
	
	public boolean updateSupervisionLocateEntity(Supervision_Locate_Entity supervision_Locate_Entity){
		boolean bResult = false;
		
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID, supervision_Locate_Entity.getLocateId());
		values.put(Supervision_Locate_Field.COLUMN_EMPLOYEE_ID, supervision_Locate_Entity.getEmployeeId());
		values.put(Supervision_Locate_Field.COLUMN_SUPERVISION_CONSTR_ID, supervision_Locate_Entity.getSuppervisionId());
		values.put(Supervision_Locate_Field.COLUMN_IS_ACTIVE, supervision_Locate_Entity.getIsActive());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_STATUS, supervision_Locate_Entity.getIsCheckin());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_LATITUDE, supervision_Locate_Entity.getCheckinLatitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_LONGTITUDE, supervision_Locate_Entity.getCheckinLongtitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_LATITUDE, supervision_Locate_Entity.getLogoutLatitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_LONGTITUDE, supervision_Locate_Entity.getLogoutLongtitude());
		values.put(Supervision_Locate_Field.COLUMN_CHECK_IN_DATE, DateConvert.convertDateToStringCheckIn(supervision_Locate_Entity.getCheckinDate()));
		values.put(Supervision_Locate_Field.COLUMN_CHECK_OUT_DATE, DateConvert.convertDateToStringCheckIn(supervision_Locate_Entity.getCheckoutDate()));
		values.put(Supervision_Locate_Field.COLUMN_USER_PLAN, supervision_Locate_Entity.getPlan());
		values.put(Supervision_Locate_Field.COLUMN_CAT_PROVINCES, supervision_Locate_Entity.getProvinceId());
		values.put(Supervision_Locate_Field.COLUMN_SYNC_STATUS, supervision_Locate_Entity.getSync_Status());
//		values.put(Supervision_Locate_Field.COLUMN_PROCESS_ID, supervision_Locate_Entity.getProcessId());

		// Inserting Row
		String sqlclause = Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(supervision_Locate_Entity.getLocateId()) };
		
		bResult = this.updateDB(Supervision_Locate_Field.TABLE_NAME, values, sqlclause, sqlPara);
		return bResult;
	}
	
	

	/**
	 * 
	 * @param sTable
	 * @param values
	 * @param sqlClause
	 * @param sqlPara
	 * @return
	 */
	public boolean updateDB(String sTable, ContentValues values,
			String sqlClause, String[] sqlPara) {
		boolean bResult = true;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		int iRow = db.update(sTable, values, sqlClause, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
		if (iRow > 0) {
			bResult = true;
		}
		return bResult;
	}
	
	public boolean deleteSupLocateEntity(Supervision_Locate_Entity supervision_Locate_Entity){
		boolean bResult = false;
		ContentValues values = new ContentValues();
		values.put(Supervision_Locate_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (supervision_Locate_Entity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Supervision_Locate_Field.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String sqlclause = Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(supervision_Locate_Entity.getLocateId()) };
		bResult = this.updateDB(Supervision_Locate_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
		return bResult;
	}
	
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Locate_Field.TABLE_NAME,
						new String[] { Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID },
						Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID + "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			bResult = true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}
}
