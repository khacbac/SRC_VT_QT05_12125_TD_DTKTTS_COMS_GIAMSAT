package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Cat_Cause_Constr_AcceptanceEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Cat_Cause_Constr_AcceptanceField;

import java.util.ArrayList;
import java.util.List;

public class Cat_Cause_Constr_AcceptanceController {
	private Context mContext = null;
	public static String[] allColumn = new String[] {
			Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
			Cat_Cause_Constr_AcceptanceField.COLUMN_NAME,
			Cat_Cause_Constr_AcceptanceField.COLUMN_TYPE,
			Cat_Cause_Constr_AcceptanceField.COLUMN_SUB_TYPE,
			Cat_Cause_Constr_AcceptanceField.COLUMN_IS_MANDATORY,
			Cat_Cause_Constr_AcceptanceField.COLUMN_CATEGORY,
			Cat_Cause_Constr_AcceptanceField.COLUMN_IS_ACTIVE,
			Cat_Cause_Constr_AcceptanceField.COLUMN_PROCESS_ID };

	public static final String CREATE_CAT_CAUSE_CONSTR_ACCEPTANCE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Cause_Constr_AcceptanceField.TABLE_NAME
			+ "("
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " NUMERIC PRIMARY KEY,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_NAME
			+ " TEXT,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_CATEGORY
			+ " TEXT,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_TYPE
			+ " INTEGER,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_IS_MANDATORY
			+ " INTEGER,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_SUB_TYPE
			+ " TEXT,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cat_Cause_Constr_AcceptanceField.COLUMN_PROCESS_ID
			+ " INTEGER" + ")";

	public Cat_Cause_Constr_AcceptanceController(Context pContext) {
		this.mContext = pContext;
	}

	/* Add nguyen nhan loi */
	public Long addItem(Cat_Cause_Constr_AcceptanceEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
				addItem.getCat_Cause_Constr_Acceptance_Id());
		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_NAME,
				addItem.getName());
		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_TYPE,
				addItem.getType());

		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_SUB_TYPE,
				addItem.getSub_Type());
		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_IS_MANDATORY,
				addItem.getIs_Mandatory());
		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_CATEGORY,
				addItem.getCategory());

		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_IS_ACTIVE,
				addItem.getIs_Active());

		values.put(Cat_Cause_Constr_AcceptanceField.COLUMN_PROCESS_ID,
				addItem.getProcessId());
		// Inserting Row
		db.insert(Cat_Cause_Constr_AcceptanceField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idItem;
	}

	/**
	 * Lay danh sach nghiem thu va chuyen thanh danh sach nghiem thu object
	 * 
	 * @return
	 */
	public List<Supervision_LBG_UnqualifyItemEntity> getAllUnQualifyItemByName(
			String sSubType, int iType) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
						allColumn,
						Cat_Cause_Constr_AcceptanceField.COLUMN_SUB_TYPE
								+ " = ? AND "
								+ Cat_Cause_Constr_AcceptanceField.COLUMN_TYPE
								+ " =? AND "
								+ Cat_Cause_Constr_AcceptanceField.COLUMN_IS_ACTIVE
								+ " = 1 ",
						new String[] { sSubType, String.valueOf(iType) },
						null,
						null,
						Cat_Cause_Constr_AcceptanceField.COLUMN_IS_MANDATORY
						+ " , "
						+Cat_Cause_Constr_AcceptanceField.COLUMN_CATEGORY
								+ " , "
								+ Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
						null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converCursorToLBGItem(cursor);
				// Adding contact to list
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converCursorToLBGItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCat_Cause_Constr_Acceptance_Id(cursor.getLong(cursor
					.getColumnIndex(Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID)));

			curItem.setTitle(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_AcceptanceField.COLUMN_NAME)));
			curItem.setCategory(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_AcceptanceField.COLUMN_CATEGORY)));
			curItem.setIs_Mandory(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_AcceptanceField.COLUMN_IS_MANDATORY)));
			curItem.setCause_Line_Bg_Id(0);
			curItem.setUnqulify(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
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
				.query(Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
						new String[] { Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID },
						Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
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
	public boolean updateGetData(
			List<Cat_Cause_Constr_AcceptanceEntity> listData) {
		boolean bResult = false;
		for (Cat_Cause_Constr_AcceptanceEntity curItem : listData) {
			if (this.checkExitItem(curItem.getCat_Cause_Constr_Acceptance_Id())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}
}
