package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Kct_design_Entity;
import com.viettel.database.field.Supervision_BRCD_Kct_design_Field;

public class Supervision_BRCD_Kct_design_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
			Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH,
			Supervision_BRCD_Kct_design_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Kct_design_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Kct_design_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Kct_design_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Kct_design_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_BRCD_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_BRCD_Kct_design_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Kct_design_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
				addItem.getSUPERVISION_TRUNK_DESIGN_ID());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSupervition_brcd_id());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH, addItem.getTrunk_cable_length());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db
				.insert(Supervision_BRCD_Kct_design_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_kct_design(
			long supervision_brcd_kctdg_id,
			Supervision_BRCD_Kct_design_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();



		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH,
				itemData.getTrunk_cable_length());

		values.put(Supervision_BRCD_Kct_design_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String
				.valueOf(supervision_brcd_kctdg_id) };
		// Update Row
		int in = db
				.update(Supervision_BRCD_Kct_design_Field.TABLE_NAME,
						values,
						Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID
								+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Kct_design_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Kct_design_Entity curItem = new Supervision_BRCD_Kct_design_Entity();
		try {

			curItem.setSUPERVISION_TRUNK_DESIGN_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID)));
			curItem.setSupervition_brcd_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_BRCD_ID)));
			curItem.setTrunk_cable_length(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Kct_design_Entity getSupervisionBRCD_kct_design(
			long idSuperConstr) {

		Supervision_BRCD_Kct_design_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Kct_design_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Kct_design_Entity();

			brcdEntity
					.setSUPERVISION_TRUNK_DESIGN_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID)));
			brcdEntity
					.setTrunk_cable_length(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kct_design_Field.COLUMN_TRUNK_CABLE_LENGTH)));
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
