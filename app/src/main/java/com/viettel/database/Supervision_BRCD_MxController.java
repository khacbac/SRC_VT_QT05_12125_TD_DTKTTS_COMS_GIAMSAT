package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_MXEntity;
import com.viettel.database.field.Supervision_BRCD_MXField;

public class Supervision_BRCD_MxController {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_MXField.COLUMN_DESCS,
			Supervision_BRCD_MXField.COLUMN_LONGITUDE,
			Supervision_BRCD_MXField.COLUMN_LATITUDE,
			Supervision_BRCD_MXField.COLUMN_STATUS,
			Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
			Supervision_BRCD_MXField.COLUMN_SYNC_STATUS,
			Supervision_BRCD_MXField.COLUMN_IS_ACTIVE,
			Supervision_BRCD_MXField.COLUMN_PROCESS_ID,
			Supervision_BRCD_MXField.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_MXField.TABLE_NAME
			+ "("
			+ Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_MXField.COLUMN_DESCS
			+ " TEXT,"
			+ Supervision_BRCD_MXField.COLUMN_LONGITUDE
			+ " TEXT,"
			+ Supervision_BRCD_MXField.COLUMN_LATITUDE
			+ " TEXT,"
			+ Supervision_BRCD_MXField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID
			+ " TEXT,"
			+ Supervision_BRCD_MXField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_MXField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_MXField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_MXField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public Supervision_BRCD_MxController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_MXEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
				addItem.getSUPERVISION_TRUNK_MX_ID());

		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSUPERVISION_BRCD_ID());

		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_BRCD_MXField.COLUMN_LONGITUDE,
				addItem.getLongItude());

		values.put(Supervision_BRCD_MXField.COLUMN_LATITUDE,
				addItem.getLatItude());

		values.put(Supervision_BRCD_MXField.COLUMN_STATUS, addItem.getStatus());
		values.put(Supervision_BRCD_MXField.COLUMN_DESCS, addItem.getDesc());

		values.put(Supervision_BRCD_MXField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_MXField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_MXField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_MXField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db
				.insert(Supervision_BRCD_MXField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_MX(long supervision_bts_id,
			Supervision_BRCD_MXEntity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
				itemData.getSUPERVISION_TRUNK_MX_ID());

		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID,
				itemData.getSUPERVISION_BRCD_ID());

		values.put(Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_MXField.COLUMN_LONGITUDE,
				itemData.getLongItude());

		values.put(Supervision_BRCD_MXField.COLUMN_LATITUDE,
				itemData.getLatItude());

		values.put(Supervision_BRCD_MXField.COLUMN_STATUS, itemData.getStatus());
		values.put(Supervision_BRCD_MXField.COLUMN_DESCS, itemData.getDesc());

		values.put(Supervision_BRCD_MXField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db
				.update(Supervision_BRCD_MXField.TABLE_NAME, values,
						Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID
								+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_MXEntity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_MXEntity curItem = new Supervision_BRCD_MXEntity();
		try {

			curItem.setSUPERVISION_TRUNK_MX_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID)));
			curItem.setLongItude(cursor.getDouble(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_LONGITUDE)));
			curItem.setLatItude(cursor.getDouble(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_LATITUDE)));
			curItem.setDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_DESCS)));
			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_STATUS)));
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setSUPERVISION_BRCD_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_MXEntity getSupervisionBRCD_MX(long idbrcd) {

		Supervision_BRCD_MXEntity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_MXField.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idbrcd) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_MXEntity();

			brcdEntity
					.setSUPERVISION_TRUNK_MX_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID)));
			brcdEntity
					.setLongItude(cu.getDouble(cu
							.getColumnIndex(Supervision_BRCD_MXField.COLUMN_LONGITUDE)));
			brcdEntity.setLatItude(cu.getDouble(cu
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_LATITUDE)));
			brcdEntity.setDesc(cu.getString(cu
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_DESCS)));
			brcdEntity.setStatus(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_MXField.COLUMN_STATUS)));
			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_CONSTR_ID)));
			brcdEntity
					.setSUPERVISION_BRCD_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_MXField.COLUMN_SUPERVISION_BRCD_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
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
