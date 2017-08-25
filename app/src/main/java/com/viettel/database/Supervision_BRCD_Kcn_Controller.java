package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.field.Supervision_BRCD_Kcn_Field;

import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Kcn_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
			Supervision_BRCD_Kcn_Field.COLUMN_CABLE_NAME,
			Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE,
			Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Kcn_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Kcn_Field.COLUMN_EMPLOYEE_ID};

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Kcn_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_CABLE_NAME
			+ " TEXT,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE
			+ " TEXT,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID
			+ " TEXT,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			
			+ Supervision_BRCD_Kcn_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Supervision_BRCD_Kcn_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Kcn_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
				addItem.getSupervition_branch_cable_id());
		values.put(
				Supervision_BRCD_Kcn_Field.COLUMN_CABLE_NAME,
				addItem.getCable_Name());

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSUPERVISION_BRCD_ID());

		

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE,
				addItem.getCable_Code());



		values.put(Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Kcn_Field.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_Kcn(long supervision_bts_id,
			Supervision_BRCD_Kcn_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
				itemData.getSupervition_branch_cable_id());
		values.put(
				Supervision_BRCD_Kcn_Field.COLUMN_CABLE_NAME,
				itemData.getCable_Name());

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID,
				itemData.getSUPERVISION_BRCD_ID());

		

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE,
				itemData.getCable_Code());



		values.put(Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Kcn_Field.TABLE_NAME, values,
				Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Kcn_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Kcn_Entity curItem = new Supervision_BRCD_Kcn_Entity();
		try {

			curItem.setSupervition_branch_cable_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID)));
			curItem.setCable_Code(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE)));
			curItem.setCable_Name(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_CABLE_NAME)));
			
			curItem.setSUPERVISION_BRCD_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Kcn_Entity getSupervisionBRCD_Kcn(long idSuperConstr) {

		Supervision_BRCD_Kcn_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Kcn_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Kcn_Entity();

			brcdEntity
					.setSupervition_branch_cable_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID)));
			brcdEntity
					.setCable_Code(cu.getString(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_CABLE_CODE)));
			brcdEntity
					.setSUPERVISION_BRCD_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}

	public List<Supervision_BRCD_Kcn_Entity> getAllbrcd_kcn(
			long supervition_brcd_id) {
		List<Supervision_BRCD_Kcn_Entity> listItem = new ArrayList<Supervision_BRCD_Kcn_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supervision_BRCD_Kcn_Field.TABLE_NAME,
				allColumn,
				Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRCD_ID
						+ " = ? AND "
						+ Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE + " = ?",
				new String[] { String.valueOf(supervition_brcd_id),
						String.valueOf(Constants.IS_ACTIVE) }, null, null,
				Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
				null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_BRCD_Kcn_Entity curItem = new Supervision_BRCD_Kcn_Entity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}
	public boolean deleteKcnEntity(Supervision_BRCD_Kcn_Entity itemDelete){
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Kcn_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);		
		
		values.put(Supervision_BRCD_Kcn_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getSupervition_branch_cable_id()) };
		bResult = this.updateDB(Supervision_BRCD_Kcn_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
		
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
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
