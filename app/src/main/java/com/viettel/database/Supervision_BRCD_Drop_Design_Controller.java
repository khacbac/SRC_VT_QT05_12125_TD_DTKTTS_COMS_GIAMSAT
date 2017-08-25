package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Drop_Design_Entity;
import com.viettel.database.field.Supervision_BRCD_Drop_Design_Field;

public class Supervision_BRCD_Drop_Design_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,

			Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
			Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE,
			Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH,
			Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM,
			Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM,
			Supervision_BRCD_Drop_Design_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Drop_Design_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Drop_Design_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Drop_Design_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Drop_Design_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM
			+ " INTEGER,"

			+ Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_BRCD_Drop_Design_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Drop_Design_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
				addItem.getSupervition_branch_drop_design_id());

		values.put(
				Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,
				addItem.getSupervition_branch_drop_id());

		values.put(
				Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE,
				addItem.getCable_Type());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH,
				addItem.getCable_length());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM,
				addItem.getPillar_Old_Number());
		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM,
				addItem.getPillar_New_Number());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_Drop_Design(long supervision_bts_id,
			Supervision_BRCD_Drop_Design_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(
				Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE,
				itemData.getCable_Type());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH,
				itemData.getCable_length());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM,
				itemData.getPillar_Old_Number());
		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM,
				itemData.getPillar_New_Number());

		values.put(Supervision_BRCD_Drop_Design_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db
				.update(Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
						values,
						Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID
								+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Drop_Design_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Drop_Design_Entity curItem = new Supervision_BRCD_Drop_Design_Entity();
		try {

			curItem.setSupervition_branch_drop_design_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID)));
			curItem.setSupervition_branch_drop_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID)));

			curItem.setCable_Type(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE)));
			curItem.setCable_length(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH)));
			curItem.setPillar_Old_Number(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM)));
			curItem.setPillar_New_Number(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM)));
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Drop_Design_Entity getSupervisionBRCD_Drop_design(
			long idSuperConstr) {

		Supervision_BRCD_Drop_Design_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Drop_Design_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID
				+ " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Drop_Design_Entity();

			brcdEntity
					.setSupervition_branch_drop_design_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID)));
			brcdEntity
					.setSupervition_branch_drop_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID)));
			brcdEntity
					.setCable_Type(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_TYPE)));
			brcdEntity
					.setCable_length(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_CABLE_LENGTH)));
			brcdEntity
					.setPillar_Old_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_OLD_NUM)));
			brcdEntity
					.setPillar_New_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_PILLAR_NEW_NUM)));

			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_CONSTR_ID)));

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
