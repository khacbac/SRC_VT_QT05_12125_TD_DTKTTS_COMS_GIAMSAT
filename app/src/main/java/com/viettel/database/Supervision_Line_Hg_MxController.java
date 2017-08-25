package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.database.entity.Supervision_Line_Hg_MxEntity;
import com.viettel.database.field.Supervision_Line_Hg_MxField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_Hg_MxController {
	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
			Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HANG_ID,
			Supervision_Line_Hg_MxField.COLUMN_MX_NAME,
			Supervision_Line_Hg_MxField.COLUMN_LONGITUDE,
			Supervision_Line_Hg_MxField.COLUMN_LATITUDE,
			Supervision_Line_Hg_MxField.COLUMN_STATUS,
			Supervision_Line_Hg_MxField.COLUMN_FAIL_DESC,
			Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS,
			Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE,
			Supervision_Line_Hg_MxField.COLUMN_PROCESS_ID,
			Supervision_Line_Hg_MxField.COLUMN_EMPLOYEE_ID,
			Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_CONSTR_ID};

	public static final String CREATE_SUPERVISION_LINE_HG_MX_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_Hg_MxField.TABLE_NAME
			+ "("
			+ Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HANG_ID
			+ " TEXT,"
			+ Supervision_Line_Hg_MxField.COLUMN_MX_NAME
			+ " TEXT,"
			+ Supervision_Line_Hg_MxField.COLUMN_LONGITUDE
			+ " TEXT,"
			+ Supervision_Line_Hg_MxField.COLUMN_LATITUDE
			+ " TEXT,"
			+ Supervision_Line_Hg_MxField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Line_Hg_MxField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_Hg_MxField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_Hg_MxField.COLUMN_EMPLOYEE_ID + " TEXT," 
			+ Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_Line_Hg_MxController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_Line_Hg_MxEntity addItem) {
		Long idAddItem = 0L;

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(
				Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
				addItem.getSupervision_Line_Hg_Mx_Id());

		values.put(Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HANG_ID,
				addItem.getSupervision_Line_Hang_Id());

		values.put(Supervision_Line_Hg_MxField.COLUMN_MX_NAME,
				addItem.getMxName());

		values.put(Supervision_Line_Hg_MxField.COLUMN_LONGITUDE,
				addItem.getLongItude());

		values.put(Supervision_Line_Hg_MxField.COLUMN_LATITUDE,
				addItem.getLatItude());

		values.put(Supervision_Line_Hg_MxField.COLUMN_STATUS,
				addItem.getStatus());
		values.put(Supervision_Line_Hg_MxField.COLUMN_FAIL_DESC,
				addItem.getFail_Desc());

		values.put(Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_Hg_MxField.COLUMN_PROCESS_ID,
				addItem.getProcessId());
		
		values.put(Supervision_Line_Hg_MxField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idAddItem = db.insert(Supervision_Line_Hg_MxField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public boolean delete(long idItem, int iStatus) {

		ContentValues values = new ContentValues();

		values.put(Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		
		String sqlclause = Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(idItem) };
		return this.updateDB(Supervision_Line_Hg_MxField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateAllColumn(Supervision_Line_HG_MXGSEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();
		values.put(Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());
		values.put(Supervision_Line_Hg_MxField.COLUMN_FAIL_DESC,
				updateItem.getFailDesc());
		values.put(Supervision_Line_Hg_MxField.COLUMN_MX_NAME,
				updateItem.getMxName());
		String sqlclause = Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(updateItem.getIdMX()) };
		bResult = this.updateDB(Supervision_Line_Hg_MxField.TABLE_NAME, values,
				sqlclause, sqlPara);
		return bResult;
	}

	/* Lay danh sach be giam sat */
	public List<Supervision_Line_HG_MXGSEntity> getAllMXGSBySupervistionLineHang(
			long idLineBackground) {
		List<Supervision_Line_HG_MXGSEntity> listItem = new ArrayList<Supervision_Line_HG_MXGSEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Line_Hg_MxField.TABLE_NAME,
						allColumn,
						Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HANG_ID
								+ " = ? AND "
								+ Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE
								+ " = ?",
						new String[] { String.valueOf(idLineBackground),
								String.valueOf(Constants.ISACTIVE.ACTIVE) },
						null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_HG_MXGSEntity curItem = new Supervision_Line_HG_MXGSEntity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	private Supervision_Line_HG_MXGSEntity converCursorToMXGS(Cursor cursor) {
		Supervision_Line_HG_MXGSEntity curItem = new Supervision_Line_HG_MXGSEntity();
		try {
			curItem.setIdMX(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID)));

			curItem.setMxName(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_MX_NAME)));

			curItem.setLongItude(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_LONGITUDE)));

			curItem.setLatItude(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_LATITUDE)));

			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_STATUS)));

			curItem.setFailDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_FAIL_DESC)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_Hg_MxField.COLUMN_PROCESS_ID)));
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
