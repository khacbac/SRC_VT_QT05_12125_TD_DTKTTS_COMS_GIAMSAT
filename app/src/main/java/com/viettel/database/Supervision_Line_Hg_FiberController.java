package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_Hg_FiberEntity;
import com.viettel.database.field.Supervision_Line_Hg_FiberField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_Hg_FiberController {

	public static final String[] allColumn = new String[] {
			Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
			Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID,
			Supervision_Line_Hg_FiberField.COLUMN_FIBER_NAME,
			Supervision_Line_Hg_FiberField.COLUMN_MEASURE_VALUE_DB,
			Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS,
			Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE,
			Supervision_Line_Hg_FiberField.COLUMN_PROCESS_ID,
			Supervision_Line_Hg_FiberField.COLUMN_EMPLOYEE_ID,
			Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_CONSTR_ID};

	private Context mContext = null;
	public static final String CREATE_SUPERVISION_LINE_BG_FIBER_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_Hg_FiberField.TABLE_NAME
			+ "("
			+ Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID
			+ " TEXT,"
			+ Supervision_Line_Hg_FiberField.COLUMN_FIBER_NAME
			+ " TEXT,"
			+ Supervision_Line_Hg_FiberField.COLUMN_MEASURE_VALUE_DB
			+ " REAL,"
			+ Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_Hg_FiberField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_Hg_FiberField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_Line_Hg_FiberController(Context pContext) {
		this.mContext = pContext;
	}
	
	public boolean deleteHgFiberEntity(Supervision_Line_Hg_FiberEntity itemDelete){
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);		

		String sqlclause = Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getSupervision_Line_Hg_Fiber_Id()) };
		bResult = this.updateDB(Supervision_Line_Hg_FiberField.TABLE_NAME,
				values, sqlclause, sqlPara);
		
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public boolean addItem(Supervision_Line_Hg_FiberEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(
				Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(
				Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
				addItem.getSupervision_Line_Hg_Fiber_Id());
		values.put(
				Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID,
				addItem.getSupervision_Line_Hang_Id());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_FIBER_NAME,
				addItem.getFiber_Name());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_MEASURE_VALUE_DB,
				addItem.getMeasure_Value_Db());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_PROCESS_ID,
				addItem.getProcessId());
		
		values.put(Supervision_Line_Hg_FiberField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idAddItem = db.insert(Supervision_Line_Hg_FiberField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public boolean updateAllColumn(Supervision_Line_Hg_FiberEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID,
				updateItem.getSupervision_Line_Hang_Id());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_FIBER_NAME,
				updateItem.getFiber_Name());

		values.put(Supervision_Line_Hg_FiberField.COLUMN_MEASURE_VALUE_DB,
				updateItem.getMeasure_Value_Db());
		
		values.put(Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());

		String sqlclause = Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(updateItem
				.getSupervision_Line_Hg_Fiber_Id()) };
		bResult = this.updateDB(Supervision_Line_Hg_FiberField.TABLE_NAME,
				values, sqlclause, sqlPara);
		return bResult;
	}

	/* lay tat ca danh sach soi giam sat */
	public List<Supervision_Line_Hg_FiberEntity> getAllFiberBySupervistionLineHang(
			long idLineBackground) {
		List<Supervision_Line_Hg_FiberEntity> listItem = new ArrayList<Supervision_Line_Hg_FiberEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Supervision_Line_Hg_FiberField.TABLE_NAME,
				allColumn,
				Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID
						+ " = ? AND " + Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE+ " = ?",
				new String[] { String.valueOf(idLineBackground),String.valueOf(Constants.IS_ACTIVE) }, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_Hg_FiberEntity curItem = new Supervision_Line_Hg_FiberEntity();
				curItem = this.converCursorToFiber(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	private Supervision_Line_Hg_FiberEntity converCursorToFiber(Cursor cursor) {
		Supervision_Line_Hg_FiberEntity curItem = new Supervision_Line_Hg_FiberEntity();
		try {
			curItem.setSupervision_Line_Hg_Fiber_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID)));

			curItem.setSupervision_Line_Hang_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HANG_ID)));

			curItem.setMeasure_Value_Db(cursor.getFloat(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_MEASURE_VALUE_DB)));

			curItem.setFiber_Name(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_FIBER_NAME)));
			
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_Hg_FiberField.COLUMN_PROCESS_ID)));

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
