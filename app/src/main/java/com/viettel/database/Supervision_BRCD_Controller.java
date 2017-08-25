package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Entity;
import com.viettel.database.field.Supervision_BRCD_Field;

import java.util.List;

public class Supervision_BRCD_Controller {
	private Context mContext = null;
	public static String[] allColumn = new String[] {
			Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_BRCD_Field.COLUMN_DESCS,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
			Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
			Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
			Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
			Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
			Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
			Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
			Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,
			Supervision_BRCD_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Field.COLUMN_EMPLOYEE_ID };

	public static String[] allColumnUpate = new String[] {
			Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
			Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_BRCD_Field.COLUMN_DESCS,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
			Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
			Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
			Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
			Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
			Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
			Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
			Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
			Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,

	};

	public static final String CREATE_SUPERVISION_CONSTR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
			+ " TEXT PRIMARY KEY ,"
			+ Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_DESCS
			+ " TEXT,"
			+ Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL
			+ " INTEGER,"

			+ Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT"
			+ ")";

	public Supervision_BRCD_Controller(Context pContext) {
		this.mContext = pContext;
	}

	/*
	 * Them moi bang quan he giua yeu cau va cong trinh
	 * 
	 * @param addItem Cong trinh duoc them moi
	 * 
	 * @return true neu them moi thanh cong , false neu them moi that bai
	 */
	public boolean addItem(Supervision_BRCD_Entity addItem) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
				addItem.getSUPERVISION_BRCD_ID());
		values.put(Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSUPERVISION_CONSTR_ID());
		values.put(Supervision_BRCD_Field.COLUMN_DESCS, addItem.getDESCS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
				addItem.getBRANCH_CABLE_TYPE());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
				addItem.getBRANCH_NUM_CABLE());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
				addItem.getTRUNK_NUM_CABLE_HT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
				addItem.getTRUNK_NUM_CABLE_BT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
				addItem.getTRUNK_NUM_CABLE_CS());
		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
				addItem.getBRANCH_NUM_CHEST());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
				addItem.getTRUNK_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
				addItem.getTRUNK_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
				addItem.getBRANCH_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,
				addItem.getBRANCH_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_PROCESS_ID,
				addItem.getPROCESS_ID());

		values.put(Supervision_BRCD_Field.COLUMN_SYNC_STATUS,
				addItem.getSYNC_STATUS());

		values.put(Supervision_BRCD_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEMPLOYEE_ID());

		values.put(Supervision_BRCD_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		// Inserting Row
		db.insert(Supervision_BRCD_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		bResult = true;
		return bResult;
	}

	/*
	 * Lay mot cong trinh giam sat
	 * 
	 * @param itemId Id cong trinh
	 * 
	 * @return Cong trinh duoc giam sat
	 */
	public Supervision_BRCD_Entity getItem(long itemId) {
		Supervision_BRCD_Entity curItem = new Supervision_BRCD_Entity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_BRCD_Field.TABLE_NAME, allColumn,
						Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID
								+ "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	public void trunCate() {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		db.execSQL("delete from " + Supervision_BRCD_Field.TABLE_NAME);
		KttsDatabaseHelper.INSTANCE.close();

	}

	public void updateSupervisionBRCD(long supervision_brcd_id,
			Supervision_BRCD_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSUPERVISION_CONSTR_ID());
		values.put(Supervision_BRCD_Field.COLUMN_DESCS, itemData.getDESCS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
				itemData.getBRANCH_CABLE_TYPE());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
				itemData.getBRANCH_NUM_CABLE());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
				itemData.getTRUNK_NUM_CABLE_HT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
				itemData.getTRUNK_NUM_CABLE_BT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
				itemData.getTRUNK_NUM_CABLE_CS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
				itemData.getBRANCH_NUM_CHEST());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
				itemData.getTRUNK_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
				itemData.getTRUNK_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
				itemData.getBRANCH_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,
				itemData.getBRANCH_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		values.put(Supervision_BRCD_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		String[] sqlPara = new String[] { String.valueOf(supervision_brcd_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Field.TABLE_NAME, values,
				Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID + "= ?",
				sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void updateSupervisionBRCD_row(long supervision_brcd_id,
			int num_chest) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST, num_chest);

		String[] sqlPara = new String[] { String.valueOf(supervision_brcd_id) };
		// Update Row
		int in = db.update(Supervision_BRCD_Field.TABLE_NAME, values,
				Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID + "= ?",
				sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public boolean updateDesign(Supervision_BRCD_Entity itemData) {
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSUPERVISION_CONSTR_ID());
		values.put(Supervision_BRCD_Field.COLUMN_DESCS, itemData.getDESCS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
				itemData.getBRANCH_CABLE_TYPE());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
				itemData.getBRANCH_NUM_CABLE());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
				itemData.getTRUNK_NUM_CABLE_HT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
				itemData.getTRUNK_NUM_CABLE_BT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
				itemData.getTRUNK_NUM_CABLE_CS());
		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
				itemData.getBRANCH_NUM_CHEST());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
				itemData.getTRUNK_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
				itemData.getTRUNK_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
				itemData.getBRANCH_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,
				itemData.getBRANCH_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		values.put(Supervision_BRCD_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSUPERVISION_CONSTR_ID()) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateAllColumn(Supervision_BRCD_Entity itemData) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSUPERVISION_CONSTR_ID());
		values.put(Supervision_BRCD_Field.COLUMN_DESCS, itemData.getDESCS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE,
				itemData.getBRANCH_CABLE_TYPE());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE,
				itemData.getBRANCH_NUM_CABLE());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT,
				itemData.getTRUNK_NUM_CABLE_HT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT,
				itemData.getTRUNK_NUM_CABLE_BT());
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS,
				itemData.getTRUNK_NUM_CABLE_CS());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST,
				itemData.getBRANCH_NUM_CHEST());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL,
				itemData.getTRUNK_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL,
				itemData.getTRUNK_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL,
				itemData.getBRANCH_PILLAR_NEW_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL,
				itemData.getBRANCH_PILLAR_OLD_TOTAL());

		values.put(Supervision_BRCD_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSUPERVISION_CONSTR_ID()) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	/**
	 * cap nhat loai tuyen cong trinh
	 * 
	 * @param id
	 *            Id cong trinh
	 * @param idType
	 *            Id loai cong trinh(Tuyen ngam, tuyen treo)
	 * @return
	 */

	/**
	 * Ham update tien do cong trinh
	 * 
	 * @param id
	 *            Id cong trinh
	 * @param idProgress
	 *            Id tien do cong trinh
	 * @return
	 */

	/**
	 * Ham update trang thai dong bo
	 * 
	 * @param id
	 *            Id cong trinh
	 * @return
	 */
	public boolean updateSyncStatus(long id) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.EDIT);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateChest(long id, int chest) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST, chest);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateCable(long id, int cable) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE, cable);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateTrunkCable_HT(long id, int cable) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT, cable);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateTrunkCable_BT(long id, int cable) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT, cable);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updateTrunkCable_CS(long id, int cable) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS, cable);
		String sqlclause = Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	private Supervision_BRCD_Entity converCursorToItem(Cursor cursor) {
		Supervision_BRCD_Entity curItem = new Supervision_BRCD_Entity();
		try {
			curItem.setSUPERVISION_CONSTR_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setSUPERVISION_CONSTR_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_DESCS)));
			curItem.setSUPERVISION_BRCD_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID)));

			curItem.setBRANCH_CABLE_TYPE(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE)));

			curItem.setBRANCH_NUM_CABLE(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE)));
			curItem.setTRUNK_NUM_CABLE_HT(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT)));
			curItem.setTRUNK_NUM_CABLE_BT(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT)));
			curItem.setTRUNK_NUM_CABLE_CS(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS)));

			curItem.setBRANCH_NUM_CHEST(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST)));

			curItem.setTRUNK_PILLAR_NEW_TOTAL(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL)));

			curItem.setTRUNK_PILLAR_OLD_TOTAL(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL)));

			curItem.setBRANCH_PILLAR_NEW_TOTAL(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL)));

			curItem.setBRANCH_PILLAR_OLD_TOTAL(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL)));

			curItem.setPROCESS_ID(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_PROCESS_ID)));

			curItem.setSYNC_STATUS(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Field.COLUMN_SYNC_STATUS)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	/**
	 * 
	 * @param sTable
	 * @param values
	 * @param sqlClause
	 * @param sqlPara
	 * @return
	 */
	public boolean updateDB(String sTable, ContentValues values,
			String sqlClause, String[] sqlPara) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		int iRow = db.update(sTable, values, sqlClause, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
		if (iRow > 0) {
			bResult = true;
		}
		return bResult;
	}

	/**
	 * Ham kiem tra xem ban ghi da ton tai chua
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_BRCD_Field.TABLE_NAME,
						new String[] { Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID },
						Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID
								+ "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			bResult = true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	/**
	 * Ham cap nhat gia tri dong bo tu server ve
	 * 
	 * @param listData
	 * @return
	 */
	public boolean updateGetData(List<Supervision_BRCD_Entity> listData) {
		boolean bResult = false;
		for (Supervision_BRCD_Entity curItem : listData) {
			if (this.checkExitItem(curItem.getSUPERVISION_BRCD_ID())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}

	public Supervision_BRCD_Entity getSupervisionBRCD_brcd(long idBrcd) {

		Supervision_BRCD_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID + " = ?";

		String[] sqlPara = new String[] { String.valueOf(idBrcd) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Entity();
			brcdEntity
					.setSUPERVISION_BRCD_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID)));

			brcdEntity
					.setTRUNK_PILLAR_NEW_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL)));

			brcdEntity
					.setTRUNK_PILLAR_OLD_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL)));

			brcdEntity
					.setBRANCH_CABLE_TYPE(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE)));

			brcdEntity
					.setBRANCH_NUM_CABLE(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE)));
			brcdEntity
					.setTRUNK_NUM_CABLE_HT(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT)));
			brcdEntity
					.setTRUNK_NUM_CABLE_BT(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT)));
			brcdEntity
					.setTRUNK_NUM_CABLE_CS(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS)));

			brcdEntity
					.setBRANCH_NUM_CHEST(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST)));

			brcdEntity
					.setBRANCH_PILLAR_NEW_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL)));

			brcdEntity
					.setBRANCH_PILLAR_OLD_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}

	public Supervision_BRCD_Entity getSupervisionBRCD_Sup(long idSuperConstr) {

		Supervision_BRCD_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Field.COLUMN_SUPERVISION_CONSTR_ID + " = ? and IS_ACTIVE = 1 ";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Entity();
			brcdEntity
					.setSUPERVISION_BRCD_ID(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID)));

			brcdEntity
					.setTRUNK_PILLAR_NEW_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_NEW_TOTAL)));

			brcdEntity
					.setTRUNK_PILLAR_OLD_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_PILLAR_OLD_TOTAL)));

			brcdEntity
					.setBRANCH_CABLE_TYPE(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_CABLE_TYPE)));

			brcdEntity
					.setBRANCH_NUM_CABLE(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CABLE)));
			brcdEntity
					.setTRUNK_NUM_CABLE_HT(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_HT)));
			brcdEntity
					.setTRUNK_NUM_CABLE_BT(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_BT)));
			brcdEntity
					.setTRUNK_NUM_CABLE_CS(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_TRUNK_NUM_CABLE_CS)));

			brcdEntity
					.setBRANCH_NUM_CHEST(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_NUM_CHEST)));

			brcdEntity
					.setBRANCH_PILLAR_NEW_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_NEW_TOTAL)));

			brcdEntity
					.setBRANCH_PILLAR_OLD_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Field.COLUMN_BRANCH_PILLAR_OLD_TOTAL)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}
}
