package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Measure_ConstrEntity;
import com.viettel.database.field.Supervision_Measure_ConstrField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Measure_ConstrController {
	public static final String[] allColumn = new String[] {
		Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
		Supervision_Measure_ConstrField.COLUMN_NAME,
		Supervision_Measure_ConstrField.COLUMN_TYPE,
		Supervision_Measure_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
		Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS,
		Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE,
		Supervision_Measure_ConstrField.COLUMN_PROCESS_ID,
		Supervision_Measure_ConstrField.COLUMN_EMPLOYEE_ID};
	
	private Context mContext = null;
	
	public static final String CREATE_SUPERVISION_MEASURE_CONSTR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Measure_ConstrField.TABLE_NAME
			+ "("
			+ Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Measure_ConstrField.COLUMN_NAME
			+ " TEXT,"
			+ Supervision_Measure_ConstrField.COLUMN_TYPE
			+ " INTEGER,"
			+ Supervision_Measure_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT,"
			+ Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Measure_ConstrField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Measure_ConstrField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Supervision_Measure_ConstrController(Context pContext) {
		this.mContext = pContext;
	}
	
	public boolean addItem(Supervision_Measure_ConstrEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Measure_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(
				Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
				addItem.getSupervisionMeasureConstrId());

		values.put(Supervision_Measure_ConstrField.COLUMN_NAME,
				addItem.getName());

		values.put(Supervision_Measure_ConstrField.COLUMN_TYPE,
				addItem.getType());

		values.put(Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Measure_ConstrField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Measure_ConstrField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		
		// Inserting Row
		idAddItem = db.insert(Supervision_Measure_ConstrField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}
	
	public boolean updateItem(Supervision_Measure_ConstrEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_Measure_ConstrField.COLUMN_NAME,
				updateItem.getName());

		values.put(Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String
				.valueOf(updateItem.getSupervisionMeasureConstrId()) };
		bResult = this.updateDB(Supervision_Measure_ConstrField.TABLE_NAME,
				values, sqlclause, sqlPara);

		return bResult;
	}
	
	public boolean delete(Supervision_Measure_ConstrEntity pDelItem) {
		ContentValues values = new ContentValues();

		values.put(Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		
		String sqlclause = Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getSupervisionMeasureConstrId()) };
		return this.updateDB(Supervision_Measure_ConstrField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}
	
	/* Lay danh sach do kich thuoc */
	public List<Supervision_Measure_ConstrEntity> getAllMeasureConstr(
			long idSupvConstr, int iType) {
		List<Supervision_Measure_ConstrEntity> listItem = new ArrayList<Supervision_Measure_ConstrEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Measure_ConstrField.TABLE_NAME,
						allColumn,
						Supervision_Measure_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
								+ " = ? AND "+Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE+ " = ? AND "
								+ Supervision_Measure_ConstrField.COLUMN_TYPE + " = ? ",
						new String[] { String.valueOf(idSupvConstr),String.valueOf(Constants.IS_ACTIVE),
							String.valueOf(iType)},
						null,
						null,
						Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
						null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Measure_ConstrEntity curItem = new Supervision_Measure_ConstrEntity();
				curItem = this.converCursorToMeasureConstr(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}
	
	private Supervision_Measure_ConstrEntity converCursorToMeasureConstr(Cursor cursor) {
		Supervision_Measure_ConstrEntity curItem = new Supervision_Measure_ConstrEntity();
		try {
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_SUPERVISION_CONSTR_ID)));
			
			curItem.setSupervisionMeasureConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID)));

			curItem.setName(cursor.getString(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_NAME)));

			curItem.setType(cursor.getInt(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_TYPE)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Measure_ConstrField.COLUMN_PROCESS_ID)));

			curItem.setNew(false);
			curItem.setEdited(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}
	
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
