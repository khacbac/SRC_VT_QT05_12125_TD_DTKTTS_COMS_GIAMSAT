package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.field.Supervision_BRCD_Kct_Field;

import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Kct_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
			Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Kct_Field.COLUMN_LENGTH,
			Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE,
			Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE,
			Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Kct_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Kct_Field.COLUMN_EMPLOYEE_ID, };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Kct_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID

			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE
			+ " TEXT,"
			+ Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_Field.COLUMN_LENGTH
			+ " INTEGER,"

			+ Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kct_Field.COLUMN_EMPLOYEE_ID + " TEXT)";

	public Supervision_BRCD_Kct_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Kct_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
				addItem.getSUPERVISION_TRUNK_CABLE_ID());

		values.put(Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSupervition_brcd_id());


		values.put(Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());
		values.put(Supervision_BRCD_Kct_Field.COLUMN_LENGTH,
				addItem.getLength());
		values.put(Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE,
				addItem.getCable_code());
		values.put(Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE,
				addItem.getCable_type());

		values.put(Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Kct_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Kct_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Kct_Field.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void trunCate_kct() {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		db.execSQL("delete from " + Supervision_BRCD_Kct_Field.TABLE_NAME);
		KttsDatabaseHelper.INSTANCE.close();

	}

	public void updateSupervisionBRCD_kct(long supervision_brcd_kctdg_id,
			Supervision_BRCD_Kct_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID,
				itemData.getSupervition_brcd_id());

		values.put(Supervision_BRCD_Kct_Field.COLUMN_LENGTH,
				itemData.getLength());
		values.put(Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE,
				itemData.getCable_code());
		values.put(Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE,
				itemData.getCable_type());


		values.put(Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String
				.valueOf(supervision_brcd_kctdg_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Kct_Field.TABLE_NAME, values,
				Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Kct_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Kct_Entity curItem = new Supervision_BRCD_Kct_Entity();
		try {

			curItem.setSUPERVISION_TRUNK_CABLE_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID)));
			curItem.setSupervition_brcd_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID)));
			curItem.setLength(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_LENGTH)));
			curItem.setCable_code(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE)));
			curItem.setCable_type(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE)));
			curItem.setNew(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public List<Supervision_BRCD_Kct_Entity> getAllbrcd_kct(
			long supervition_brcd_id) {
		List<Supervision_BRCD_Kct_Entity> listItem = new ArrayList<Supervision_BRCD_Kct_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supervision_BRCD_Kct_Field.TABLE_NAME,
				allColumn,
				Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID
						+ " = ? AND "
						+ Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE + " = ?",
				new String[] { String.valueOf(supervition_brcd_id),
						String.valueOf(Constants.IS_ACTIVE) }, null, null,
				Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_BRCD_Kct_Entity curItem = new Supervision_BRCD_Kct_Entity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	public List<Supervision_BRCD_Kct_Entity> getAllbrcd_kct_type(
			long supervition_brcd_id, int cable_type) {
		List<Supervision_BRCD_Kct_Entity> listItem = new ArrayList<Supervision_BRCD_Kct_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supervision_BRCD_Kct_Field.TABLE_NAME,
				allColumn,
				Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID
						+ " = ? AND "
						+ Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE
						+ " = ? AND "
						+ Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE + " = ?",
				new String[] { String.valueOf(supervition_brcd_id),
						String.valueOf(cable_type),
						String.valueOf(Constants.IS_ACTIVE) }, null, null,
				Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_BRCD_Kct_Entity curItem = new Supervision_BRCD_Kct_Entity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	public boolean deleteKctEntity(Supervision_BRCD_Kct_Entity itemDelete) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Kct_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);

		values.put(Supervision_BRCD_Kct_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getSUPERVISION_TRUNK_CABLE_ID()) };
		bResult = this.updateDB(Supervision_BRCD_Kct_Field.TABLE_NAME, values,
				sqlclause, sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public Supervision_BRCD_Kct_Entity getSupervisionBRCD_kct(long idSuperConstr) {

		Supervision_BRCD_Kct_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Kct_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Kct_Entity();

			brcdEntity
					.setSUPERVISION_TRUNK_CABLE_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID)));
			brcdEntity.setLength(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_LENGTH)));
			brcdEntity
					.setCable_code(cu.getString(cu
							.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_CABLE_CODE)));
			brcdEntity
					.setCable_type(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kct_Field.COLUMN_CABLE_TYPE)));
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
