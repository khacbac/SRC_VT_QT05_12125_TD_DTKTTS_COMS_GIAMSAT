package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.EmployeeEntity;
import com.viettel.database.field.EmployeeField;

public class EmployeeController {
	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			EmployeeField.COLUMN_EMPLOYEE_ID,
			EmployeeField.COLUMN_EMPLOYEE_NAME,
			EmployeeField.COLUMN_EMPLOYEE_PASS,
			EmployeeField.COLUMN_EMPLOYEE_CODE,
			EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE,
			EmployeeField.COLUMN_EMPLOYEE_FULLNAME,
			EmployeeField.COLUMN_EMPLOYEE_EMAIL,
			EmployeeField.COLUMN_EMPLOYEE_GROUP_ID,
			EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME };

	public static final String CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ EmployeeField.TABLE_NAME
			+ "("
			+ EmployeeField.COLUMN_EMPLOYEE_ID
			+ " TEXT PRIMARY KEY,"
			+ EmployeeField.COLUMN_EMPLOYEE_NAME
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_PASS
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_CODE
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_FULLNAME
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_EMAIL
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_GROUP_ID
			+ " TEXT,"
			+ EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME + " TEXT" + ")";

	public EmployeeController(Context pContext) {
		this.mContext = pContext;
	}

	/* Add login */
	public boolean addLogin(EmployeeEntity login) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(EmployeeField.COLUMN_EMPLOYEE_ID, login.getId());
		values.put(EmployeeField.COLUMN_EMPLOYEE_NAME, login.getUserName());
		values.put(EmployeeField.COLUMN_EMPLOYEE_PASS, login.getPassWord());
		values.put(EmployeeField.COLUMN_EMPLOYEE_FULLNAME, login.getFullName());
		values.put(EmployeeField.COLUMN_EMPLOYEE_CODE, login.getCode());
		values.put(EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE,
				login.getGroupCode());
		values.put(EmployeeField.COLUMN_EMPLOYEE_EMAIL, login.getEmail());
		values.put(EmployeeField.COLUMN_EMPLOYEE_GROUP_ID, login.getGroupId());
		values.put(EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME,
				login.getGroupName());
		// Inserting Row
		Long rowId = db.insert(EmployeeField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (rowId > 0) {
			return true;
		} else {
			return false;
		}
	}

	/* Get login */
	public EmployeeEntity getLogin(int id) {
		EmployeeEntity login = new EmployeeEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(EmployeeField.TABLE_NAME, allColumn,
				EmployeeField.COLUMN_EMPLOYEE_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			login = convertCursorToEntity(cursor);
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return login;
	}

	/* Get login */
	public boolean checkLogin(String sUserName, String sPassWord) {
		boolean isLogin = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(EmployeeField.TABLE_NAME, new String[] {
				EmployeeField.COLUMN_EMPLOYEE_ID,
				EmployeeField.COLUMN_EMPLOYEE_NAME,
				EmployeeField.COLUMN_EMPLOYEE_PASS },
				EmployeeField.COLUMN_EMPLOYEE_NAME + "=?" + " AND "
						+ EmployeeField.COLUMN_EMPLOYEE_PASS + "=?",
				new String[] { sUserName, sPassWord }, null, null, null, null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				isLogin = true;
			}
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return isLogin;
	}

	/* Get login */
	public EmployeeEntity getLoginAcount(String sUserName, String sPassWord) {
		EmployeeEntity itemEmployee = new EmployeeEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(EmployeeField.TABLE_NAME, allColumn,
				EmployeeField.COLUMN_EMPLOYEE_NAME + "=?" + " AND "
						+ EmployeeField.COLUMN_EMPLOYEE_PASS + "=?",
				new String[] { sUserName, sPassWord }, null, null, null, null);
		if (cursor.moveToFirst()) {
			itemEmployee = this.convertCursorToEntity(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return itemEmployee;
	}

	private EmployeeEntity convertCursorToEntity(Cursor cursor) {
		EmployeeEntity itemEmployee = new EmployeeEntity();
		itemEmployee.setId(cursor.getLong(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_ID)));

		itemEmployee.setUserName(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_NAME)));

		itemEmployee.setPassWord(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_PASS)));

		itemEmployee.setCode(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_CODE)));

		itemEmployee.setGroupCode(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE)));

		itemEmployee.setFullName(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_FULLNAME)));

		itemEmployee.setEmail(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_EMAIL)));

		itemEmployee.setGroupId(cursor.getLong(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_GROUP_ID)));

		itemEmployee.setGroupName(cursor.getString(cursor
				.getColumnIndex(EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME)));

		return itemEmployee;
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

}