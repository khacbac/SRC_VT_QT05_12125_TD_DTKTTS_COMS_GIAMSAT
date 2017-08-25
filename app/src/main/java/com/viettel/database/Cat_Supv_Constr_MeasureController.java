package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cat_Supv_Constr_MeasureEntity;
import com.viettel.database.field.Cat_Supv_Constr_MeasureField;

import java.util.List;

public class Cat_Supv_Constr_MeasureController {
	private Context mContext = null;

	public Cat_Supv_Constr_MeasureController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
			Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME,
			Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE,
			Cat_Supv_Constr_MeasureField.COLUMN_IS_ACTIVE,
			Cat_Supv_Constr_MeasureField.COLUMN_PROCESS_ID };

	public static final String CREATE_CAT_SUPV_CONSTR_MEASURE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Supv_Constr_MeasureField.TABLE_NAME
			+ "("
			+ Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
			+ " TEXT PRIMARY KEY,"
			+ Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME
			+ " TEXT,"
			+ Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE
			+ " TEXT,"
			+ Cat_Supv_Constr_MeasureField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cat_Supv_Constr_MeasureField.COLUMN_PROCESS_ID
			+ " INTEGER"
			+ " )";

	public boolean insertConstrMeasureEntity(
			Cat_Supv_Constr_MeasureEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
				entity.getCat_Supv_Constr_Measure_Id());
		values.put(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME,
				entity.getMeasure_Name());
		values.put(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE,
				entity.getMeasure_Code());
		values.put(Cat_Supv_Constr_MeasureField.COLUMN_IS_ACTIVE,
				Constants.IS_ACTIVE);
		values.put(Cat_Supv_Constr_MeasureField.COLUMN_PROCESS_ID,
				entity.getProcessId());

		// Inserting Row
		Long idBtsLast = db.insert(Cat_Supv_Constr_MeasureField.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (idBtsLast > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Cat_Supv_Constr_MeasureEntity getConstrMeasureEntityByWorkCode(
			String workcode) {
		Cat_Supv_Constr_MeasureEntity result = new Cat_Supv_Constr_MeasureEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlQuery = "SELECT "
				+ Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
				+ ", " + Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME
				+ ", " + Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE
				+ " FROM " + Cat_Supv_Constr_MeasureField.TABLE_NAME
				+ " WHERE " + Cat_Supv_Constr_MeasureField.COLUMN_IS_ACTIVE
				+ "  = " + Constants.IS_ACTIVE + " AND "
				+ Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE + " = ? ";

		String[] sqlPara = new String[] { workcode };
		
		Cursor cur = db.rawQuery(sqlQuery, sqlPara);

		while (cur.moveToNext()) {
			result.setCat_Supv_Constr_Measure_Id(cur.getLong(cur
					.getColumnIndex(Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID)));
			result.setMeasure_Name(cur.getString(cur
					.getColumnIndex(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_NAME)));
			result.setMeasure_Code(cur.getString(cur
					.getColumnIndex(Cat_Supv_Constr_MeasureField.COLUMN_MEASURE_CODE)));
		}
		cur.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	/*
	 * Ham kiem tra xem ban ghi da ton tai chua
	 * 
	 * @param itemId Id cong trinh
	 * 
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Supv_Constr_MeasureField.TABLE_NAME,
						new String[] { Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID },
						Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
								+ " = ?",
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
	public boolean updateGetData(List<Cat_Supv_Constr_MeasureEntity> listData) {
		boolean bResult = false;
		for (Cat_Supv_Constr_MeasureEntity curItem : listData) {
			if (this.checkExitItem(curItem.getCat_Supv_Constr_Measure_Id())) {
				// TODO viet ham update
			} else {
				this.insertConstrMeasureEntity(curItem);
			}
		}
		return bResult;
	}

}
