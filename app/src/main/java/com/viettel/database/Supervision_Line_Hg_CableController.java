package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_Hg_CableEntity;
import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
import com.viettel.database.field.Supervision_Line_Hg_CableField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_Hg_CableController {
	public static final String[] allColumn = new String[] {
			Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
			Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HANG_ID,
			Supervision_Line_Hg_CableField.COLUMN_FROM_DISTANCE,
			Supervision_Line_Hg_CableField.COLUMN_TO_DISTANCE,
			Supervision_Line_Hg_CableField.COLUMN_STATUS,
			Supervision_Line_Hg_CableField.COLUMN_FAIL_DESC,
			Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS,
			Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE,
			Supervision_Line_Hg_CableField.COLUMN_PROCESS_ID,
			Supervision_Line_Hg_CableField.COLUMN_EMPLOYEE_ID,
			Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID};

	private Context mContext = null;
	public static final String CREATE_SUPERVISION_LINE_BG_CABLE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_Hg_CableField.TABLE_NAME
			+ "("
			+ Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HANG_ID
			+ " TEXT,"
			+ Supervision_Line_Hg_CableField.COLUMN_FROM_DISTANCE
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_TO_DISTANCE
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_Hg_CableField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_Line_Hg_CableController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_Line_Hg_CableEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(
				Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
				addItem.getSupervision_LINE_HG_CABLE_ID());

		values.put(
				Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HANG_ID,
				addItem.getSupervision_LINE_HANG_ID());

		values.put(Supervision_Line_Hg_CableField.COLUMN_FROM_DISTANCE,
				addItem.getFrom_DISTANCE());

		values.put(Supervision_Line_Hg_CableField.COLUMN_TO_DISTANCE,
				addItem.getTo_DISTANCE());

		values.put(Supervision_Line_Hg_CableField.COLUMN_STATUS,
				addItem.getStatus());

		values.put(Supervision_Line_Hg_CableField.COLUMN_FAIL_DESC,
				addItem.getFail_DESC());

		values.put(Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_Hg_CableField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_Hg_CableField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idAddItem = db.insert(Supervision_Line_Hg_CableField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public boolean updateAllColumn(Supervision_Line_Hg_CableGSEntity updateItem) {
		boolean bResult = true;

		ContentValues values = new ContentValues();
		values.put(Supervision_Line_Hg_CableField.COLUMN_FROM_DISTANCE,
				updateItem.getFromDistance());

		values.put(Supervision_Line_Hg_CableField.COLUMN_TO_DISTANCE,
				updateItem.getToDistance());

		values.put(Supervision_Line_Hg_CableField.COLUMN_STATUS,
				updateItem.getStatus());

		values.put(Supervision_Line_Hg_CableField.COLUMN_FAIL_DESC,
				updateItem.getFailDesc());

		values.put(Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());

		values.put(Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE,
				updateItem.getIsActive());

		values.put(Supervision_Line_Hg_CableField.COLUMN_PROCESS_ID,
				updateItem.getProcessId());

		String sqlclause = Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(updateItem
				.getIdCable()) };
		bResult = this.updateDB(Supervision_Line_Hg_CableField.TABLE_NAME,
				values, sqlclause, sqlPara);

		return bResult;
	}

	public boolean delete(long idItem, int iStatus) {

		ContentValues values = new ContentValues();

		values.put(Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		
		String sqlclause = Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(idItem) };
		return this.updateDB(Supervision_Line_Hg_CableField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	/* Lay danh sach be giam sat */
	public List<Supervision_Line_Hg_CableGSEntity> getAllCableGSBySupervistionLineHang(
			long idLineBackground) {
		List<Supervision_Line_Hg_CableGSEntity> listItem = new ArrayList<Supervision_Line_Hg_CableGSEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supervision_Line_Hg_CableField.TABLE_NAME,
				allColumn,
				Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HANG_ID
						+ " = ? AND "
						+ Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE
						+ " = ?",
				new String[] { String.valueOf(idLineBackground),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_Hg_CableGSEntity curItem = new Supervision_Line_Hg_CableGSEntity();
				curItem = this.converCursorToCableGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	private Supervision_Line_Hg_CableGSEntity converCursorToCableGS(
			Cursor cursor) {
		Supervision_Line_Hg_CableGSEntity curItem = new Supervision_Line_Hg_CableGSEntity();
		curItem.setIdCable(cursor.getLong(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID)));

		curItem.setSupervision_Line_HangId(cursor.getLong(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HANG_ID)));

		curItem.setFromDistance(cursor.getLong(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_FROM_DISTANCE)));

		curItem.setToDistance(cursor.getLong(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_TO_DISTANCE)));

		curItem.setStatus(cursor.getInt(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_STATUS)));

		curItem.setFailDesc(cursor.getString(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_FAIL_DESC)));

		curItem.setSync_Status(cursor.getInt(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_SYNC_STATUS)));

		curItem.setIsActive(cursor.getInt(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_IS_ACTIVE)));

		curItem.setProcessId(cursor.getLong(cursor
				.getColumnIndex(Supervision_Line_Hg_CableField.COLUMN_PROCESS_ID)));
		curItem.setNew(false);
		curItem.setEdited(false);
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
