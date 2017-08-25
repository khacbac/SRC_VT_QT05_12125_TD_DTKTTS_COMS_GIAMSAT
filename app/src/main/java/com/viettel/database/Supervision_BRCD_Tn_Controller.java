package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Tn_Entity;
import com.viettel.database.field.Supervision_BRCD_Tn_Field;

import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Tn_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
			Supervision_BRCD_Tn_Field.COLUMN_CABINET_NAME,
			Supervision_BRCD_Tn_Field.COLUMN_DESC,
			Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE,
			Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE,
			Supervision_BRCD_Tn_Field.COLUMN_STATUS,
			Supervision_BRCD_Tn_Field.COLUMN_LATITUDE,
			Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Tn_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Tn_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Tn_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Tn_Field.COLUMN_CABINET_NAME
			+ " TEXT,"
			+ Supervision_BRCD_Tn_Field.COLUMN_DESC
			+ " TEXT,"
			+ Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE
			+ " TEXT,"
			+ Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_LATITUDE
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID
			+ " TEXT,"
			+ Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Tn_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public Supervision_BRCD_Tn_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Tn_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
				addItem.getSupervition_branch_cabinet_id());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_CABINET_NAME,
				addItem.getCabinet_Name());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSUPERVISION_BRCD_ID());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE,
				addItem.getCable_code());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE,
				addItem.getLongitude());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_LATITUDE,
				addItem.getLatitude());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_STATUS, addItem.getStatus());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_DESC, addItem.getDesc());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Tn_Field.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_Tn(long supervision_bts_id,
			Supervision_BRCD_Tn_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
				itemData.getSupervition_branch_cabinet_id());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_CABINET_NAME,
				itemData.getCabinet_Name());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID,
				itemData.getSUPERVISION_BRCD_ID());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE,
				itemData.getLongitude());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE,
				itemData.getCable_code());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_LATITUDE,
				itemData.getLatitude());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_STATUS,
				itemData.getStatus());
		values.put(Supervision_BRCD_Tn_Field.COLUMN_DESC, itemData.getDesc());

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Tn_Field.TABLE_NAME, values,
				Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Tn_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Tn_Entity curItem = new Supervision_BRCD_Tn_Entity();
		try {

			curItem.setSupervition_branch_cabinet_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID)));
			curItem.setLongitude(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE)));
			curItem.setLatitude(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_LATITUDE)));
			curItem.setCable_code(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE)));
			curItem.setCabinet_Name(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_CABINET_NAME)));
			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_STATUS)));
			curItem.setDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_DESC)));
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setSUPERVISION_BRCD_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Tn_Entity getSupervisionBRCD_Tn(long idSuperConstr) {

		Supervision_BRCD_Tn_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Tn_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Tn_Entity();

			brcdEntity
					.setSupervition_branch_cabinet_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID)));
			brcdEntity
					.setLongitude(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_LONGITUDE)));
			brcdEntity
					.setLatitude(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_LATITUDE)));
			brcdEntity.setDesc(cu.getString(cu
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_DESC)));
			brcdEntity.setStatus(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_STATUS)));
			brcdEntity.setCable_code(cu.getString(cu
					.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_CABLE_CODE)));
			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			brcdEntity
					.setSUPERVISION_BRCD_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}

	public List<Supervision_BRCD_Tn_Entity> getAllbrcd_tn(
			long supervition_brcd_id) {
		List<Supervision_BRCD_Tn_Entity> listItem = new ArrayList<Supervision_BRCD_Tn_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supervision_BRCD_Tn_Field.TABLE_NAME,
				allColumn,
				Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRCD_ID
						+ " = ? AND "
						+ Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE + " = ?",
				new String[] { String.valueOf(supervition_brcd_id),
						String.valueOf(Constants.IS_ACTIVE) }, null, null,
				Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
				null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_BRCD_Tn_Entity curItem = new Supervision_BRCD_Tn_Entity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	public boolean deleteTnEntity(Supervision_BRCD_Tn_Entity itemDelete) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Tn_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);

		values.put(Supervision_BRCD_Tn_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getSupervition_branch_cabinet_id()) };
		bResult = this.updateDB(Supervision_BRCD_Tn_Field.TABLE_NAME, values,
				sqlclause, sqlPara);

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
