package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Sub_Entity;
import com.viettel.database.field.Supervision_BRCD_Sub_Field;

public class Supervision_BRCD_Sub_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
			Supervision_BRCD_Sub_Field.COLUMN_DESCS,
			Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE,
			Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID,
			Supervision_BRCD_Sub_Field.COLUMN_STATUS,
			Supervision_BRCD_Sub_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Sub_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Sub_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Sub_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Sub_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Sub_Field.COLUMN_DESCS
			+ " TEXT,"
			+ Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE
			+ " INTEGER,"
			+ Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID
			+ " TEXT,"
			+ Supervision_BRCD_Sub_Field.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Sub_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Sub_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Sub_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Sub_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_BRCD_Sub_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Sub_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
				addItem.getSUPERVISION_TRUNK_SUB_ID());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE,
				addItem.getNum_device());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID,
				addItem.getProduct_id());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_STATUS,
				addItem.getStatus());
		values.put(Supervision_BRCD_Sub_Field.COLUMN_DESCS, addItem.getDesc());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Sub_Field.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}
	public void trunCate() {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		db.execSQL("delete from " + Supervision_BRCD_Sub_Field.TABLE_NAME);
		KttsDatabaseHelper.INSTANCE.close();

	}
	public void updateSupervisionBRCD_MX(long supervision_bts_id,
			Supervision_BRCD_Sub_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
				itemData.getSUPERVISION_TRUNK_SUB_ID());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE,
				itemData.getNum_device());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID,
				itemData.getProduct_id());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_STATUS,
				itemData.getStatus());
		values.put(Supervision_BRCD_Sub_Field.COLUMN_DESCS, itemData.getDesc());

		values.put(Supervision_BRCD_Sub_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Sub_Field.TABLE_NAME, values,
				Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Sub_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Sub_Entity curItem = new Supervision_BRCD_Sub_Entity();
		try {

			curItem.setSUPERVISION_TRUNK_SUB_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID)));
			curItem.setNum_device(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE)));
			curItem.setProduct_id(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID)));
			curItem.setDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_DESCS)));
			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_STATUS)));
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Sub_Entity getSupervisionBRCD_SUB(long idbrcd) {

		Supervision_BRCD_Sub_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Sub_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID + " = ? and IS_ACTIVE = 1";

		String[] sqlPara = new String[] { String.valueOf(idbrcd) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Sub_Entity();

			brcdEntity
					.setSUPERVISION_TRUNK_SUB_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID)));
			brcdEntity
					.setNum_device(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_NUM_DEVICE)));
			brcdEntity
					.setProduct_id(cu.getString(cu
							.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_PRODUCT_ID)));
			brcdEntity.setDesc(cu.getString(cu
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_DESCS)));
			brcdEntity.setStatus(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_STATUS)));
			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			

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
