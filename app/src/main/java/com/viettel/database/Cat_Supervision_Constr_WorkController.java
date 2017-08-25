package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.field.Cat_Supervision_Constr_WorkField;

import java.util.List;

public class Cat_Supervision_Constr_WorkController {
	private Context mContext = null;

	public Cat_Supervision_Constr_WorkController(Context pContext) {
		this.mContext = pContext;
	}

	public static String[] allColumn = new String[] {
			Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
			Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME,
			Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE,
			Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE,
			Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE,
			Cat_Supervision_Constr_WorkField.COLUMN_PROCESS_ID };

	public static final String CREATE_CAT_SUPERVISION_CONSTR_WORK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Supervision_Constr_WorkField.TABLE_NAME
			+ "("
			+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
			+ " TEXT PRIMARY KEY,"
			+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME
			+ " TEXT,"
			+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE
			+ " TEXT,"
			+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE
			+ " TEXT,"
			+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cat_Supervision_Constr_WorkField.COLUMN_PROCESS_ID
			+ " INTEGER"
			+ ")";

	public int insertConstrWork(String workCode) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();
		// values.put(Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
		// idConstrWork);
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE, workCode);
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE,
				Constants.IS_ACTIVE);

		// Inserting Row
		Long returnId = db.insert(Cat_Supervision_Constr_WorkField.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close(); // Closing database connection\
		return returnId.intValue();
	}

	public boolean insertConstrWorkEntity(
			Cat_Supervision_Constr_WorkEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
				entity.getCat_Supervision_Constr_Work_Id());
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE,
				entity.getWork_Code());
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE,
				entity.getWork_Type());
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE,
				Constants.IS_ACTIVE);
		values.put(Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME,
				entity.getWork_Name());

		// Inserting Row
		Long idBtsLast = db.insert(Cat_Supervision_Constr_WorkField.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (idBtsLast > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Cat_Supervision_Constr_WorkEntity getListConstrWorkEntityByWorkCode(
			String workcode) {
		Cat_Supervision_Constr_WorkEntity result = new Cat_Supervision_Constr_WorkEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlQuery = "SELECT "
				+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ ", " + Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME
				+ "," + Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE
				+ " FROM " + " " + Cat_Supervision_Constr_WorkField.TABLE_NAME
				+ " " + " WHERE "
				+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE + "="
				+ Constants.IS_ACTIVE + " AND WORK_CODE = ?";

		String[] sqlPara = new String[] { workcode };
		
		Cursor cur = db.rawQuery(sqlQuery, sqlPara);

		while (cur.moveToNext()) {
			result.setCat_Supervision_Constr_Work_Id(cur.getLong(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID)));
			result.setWork_Name(cur.getString(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME)));
			result.setWork_Code(cur.getString(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE)));
		}
		cur.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public Cat_Supervision_Constr_WorkEntity getListConstrWorkEntityByWorkCodeAndWorkType(
			String workcode, String worktype) {
		Cat_Supervision_Constr_WorkEntity result = new Cat_Supervision_Constr_WorkEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlQuery = "SELECT "
				+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ ", " + Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME
				+ "," + Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE
				+ " FROM " + " " + Cat_Supervision_Constr_WorkField.TABLE_NAME
				+ " " + " WHERE "
				+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE + "="
				+ Constants.IS_ACTIVE + " AND "
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE + " = ? " + "AND "
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE + " = ?  ";
		
		String[] sqlPara = new String[] { workcode,worktype };
		
//		System.out.println(sqlQuery);
//		System.out.println("--------------------------------");

		Cursor cur = db.rawQuery(sqlQuery, sqlPara);

		while (cur.moveToNext()) {
			result.setCat_Supervision_Constr_Work_Id(cur.getLong(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID)));
			result.setWork_Name(cur.getString(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME)));
			result.setWork_Code(cur.getString(cur
					.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE)));
		}
		cur.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public int getConstrWorkEntityByWorkCodeReturnId(String workcode) {
		int idResult = -1;

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlQuery = "SELECT "
				+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ " FROM " + " " + Cat_Supervision_Constr_WorkField.TABLE_NAME
				+ " " + " WHERE "
				+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE + "="
				+ Constants.IS_ACTIVE + " AND "
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE + " = ? ";

		String[] sqlPara = new String[] { workcode };
		
		// System.out.println(sqlQuery);
		Cursor cur = db.rawQuery(sqlQuery, sqlPara);

		while (cur.moveToNext()) {
			idResult = cur
					.getInt(cur
							.getColumnIndex(Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID));
		}
		cur.close();
		KttsDatabaseHelper.INSTANCE.close();
		return idResult;
	}

	/**
	 * Ham kiem tra xem ban ghi da ton tai chua
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Supervision_Constr_WorkField.TABLE_NAME,
						new String[] { Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID },
						Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
								+ "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			bResult = true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	/**
	 * Ham cap nhat gia tri dong bo tu server ve
	 * 
	 * @param listData
	 * @return
	 */
	public boolean updateGetData(
			List<Cat_Supervision_Constr_WorkEntity> listData) {
		boolean bResult = false;
		for (Cat_Supervision_Constr_WorkEntity curItem : listData) {
			if (this.checkExitItem(curItem.getCat_Supervision_Constr_Work_Id())) {
				// TODO viet ham update
			} else {
				this.insertConstrWorkEntity(curItem);
			}
		}
		return bResult;
	}

}
