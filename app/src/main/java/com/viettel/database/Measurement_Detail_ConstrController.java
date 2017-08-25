package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Measurement_Detail_ConstrEntity;
import com.viettel.database.field.Measurement_Detail_ConstrField;
import com.viettel.database.field.Supervision_Measure_ConstrField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cuongdk3
 *
 */
public class Measurement_Detail_ConstrController {
	public static final String[] allColumn = new String[] {
		Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
		Measurement_Detail_ConstrField.COLUMN_NAME_COMPONENT,
		Measurement_Detail_ConstrField.COLUMN_LONG_VALUE,
		Measurement_Detail_ConstrField.COLUMN_WIDTH_VALUE,
		Measurement_Detail_ConstrField.COLUMN_HEIGH_VALUE,
		Measurement_Detail_ConstrField.COLUMN_DIAMETER,
		Measurement_Detail_ConstrField.COLUMN_OTHER_VALUE,
		Measurement_Detail_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
		
		Measurement_Detail_ConstrField.COLUMN_SYNC_STATUS,
		Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE,
		Measurement_Detail_ConstrField.COLUMN_PROCESS_ID,
		Measurement_Detail_ConstrField.COLUMN_EMPLOYEE_ID};
	
	private Context mContext = null;
	
	public static final String CREATE_MEASUREMENT_DETAIL_CONSTR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Measurement_Detail_ConstrField.TABLE_NAME
			+ "("
			+ Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID
			+ " TEXT PRIMARY KEY,"
			+ Measurement_Detail_ConstrField.COLUMN_NAME_COMPONENT
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_LONG_VALUE
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_WIDTH_VALUE
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_HEIGH_VALUE
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_DIAMETER
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_OTHER_VALUE
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID
			+ " TEXT,"
			+ Measurement_Detail_ConstrField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Measurement_Detail_ConstrField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Measurement_Detail_ConstrField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Measurement_Detail_ConstrController(Context pContext) {
		this.mContext = pContext;
	}
	
	public boolean addItem(Measurement_Detail_ConstrEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
				addItem.getMeasurementDetailConstrId());

		values.put(Measurement_Detail_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
				addItem.getSupervisionMeasureConstrId());

		values.put(
				Measurement_Detail_ConstrField.COLUMN_NAME_COMPONENT,
				addItem.getNameComponent());
		
		values.put(
				Measurement_Detail_ConstrField.COLUMN_LONG_VALUE,
				addItem.getLongValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_WIDTH_VALUE,
				addItem.getWidthValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_HEIGH_VALUE,
				addItem.getHeighValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_DIAMETER,
				addItem.getDiameter());
		
		values.put(
				Measurement_Detail_ConstrField.COLUMN_OTHER_VALUE,
				addItem.getOtherValue());

		values.put(Measurement_Detail_ConstrField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Measurement_Detail_ConstrField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Measurement_Detail_ConstrField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Measurement_Detail_ConstrField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateItem(Measurement_Detail_ConstrEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(
				Measurement_Detail_ConstrField.COLUMN_NAME_COMPONENT,
				updateItem.getNameComponent());
		
		values.put(
				Measurement_Detail_ConstrField.COLUMN_LONG_VALUE,
				updateItem.getLongValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_WIDTH_VALUE,
				updateItem.getWidthValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_HEIGH_VALUE,
				updateItem.getHeighValue());
		values.put(
				Measurement_Detail_ConstrField.COLUMN_DIAMETER,
				updateItem.getDiameter());
		
		values.put(
				Measurement_Detail_ConstrField.COLUMN_OTHER_VALUE,
				updateItem.getOtherValue());
		
		values.put(Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String
				.valueOf(updateItem.getMeasurementDetailConstrId()) };
		bResult = this.updateDB(Measurement_Detail_ConstrField.TABLE_NAME,
				values, sqlclause, sqlPara);

		return bResult;
	}
	
	public boolean delete(Measurement_Detail_ConstrEntity pDelItem) {
		ContentValues values = new ContentValues();

		values.put(Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Measurement_Detail_ConstrField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		
		String sqlclause = Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getMeasurementDetailConstrId()) };
		return this.updateDB(Measurement_Detail_ConstrField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}
	
	public List<Measurement_Detail_ConstrEntity> getAllMeasureDetailyByMeasureDetailId(
			long iMeasureDetailId) {
		List<Measurement_Detail_ConstrEntity> listItem = new ArrayList<Measurement_Detail_ConstrEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Measurement_Detail_ConstrField.TABLE_NAME,
				allColumn,
				Measurement_Detail_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID
						+ " = ? AND "
						+ Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE + "= ?",
				new String[] { String.valueOf(iMeasureDetailId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Measurement_Detail_ConstrEntity curItem = new Measurement_Detail_ConstrEntity();
				curItem = this.converCursorToObject(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}
	
	private Measurement_Detail_ConstrEntity converCursorToObject(
			Cursor cursor) {
		Measurement_Detail_ConstrEntity curItem = new Measurement_Detail_ConstrEntity();
		try {
			curItem.setSupervisionMeasureConstrId(cursor.getLong(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID)));

			curItem.setMeasurementDetailConstrId(cursor.getLong(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID)));
			
			curItem.setNameComponent(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_NAME_COMPONENT)));
			
			curItem.setLongValue(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_LONG_VALUE)));
			
			curItem.setWidthValue(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_WIDTH_VALUE)));
			
			curItem.setHeighValue(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_HEIGH_VALUE)));
			
			curItem.setDiameter(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_DIAMETER)));
			
			curItem.setOtherValue(cursor.getString(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_OTHER_VALUE)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Measurement_Detail_ConstrField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
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
