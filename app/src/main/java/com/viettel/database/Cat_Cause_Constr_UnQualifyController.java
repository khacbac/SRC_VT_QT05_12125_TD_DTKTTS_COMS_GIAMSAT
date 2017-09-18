package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;

import java.util.ArrayList;
import java.util.List;

public class Cat_Cause_Constr_UnQualifyController {
	private Context mContext = null;
	public static String[] allColumn = new String[] {
			Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cat_Cause_Constr_UnQualifyField.COLUMN_NAME,
			Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE,
			Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE,
			Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY,
			Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE,
			Cat_Cause_Constr_UnQualifyField.COLUMN_PROCESS_ID,
			Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS };

	public static final String CREATE_CAT_CAUSE_CONSTR_UNQUALIFY_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Cause_Constr_UnQualifyField.TABLE_NAME
			+ "("
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " TEXT PRIMARY KEY,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_NAME
			+ " TEXT,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY
			+ " TEXT,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE
			+ " INTEGER,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE
			+ " TEXT,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS
			+ " INTEGER,"
			+ Cat_Cause_Constr_UnQualifyField.COLUMN_PROCESS_ID
			+ " INTEGER"
			+ ")";

	public Cat_Cause_Constr_UnQualifyController(Context pContext) {
		this.mContext = pContext;
	}

	/* Add nguyen nhan loi */
	public Long addItem(Cat_Cause_Constr_UnQualifyEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				addItem.getCat_Cause_Constr_Unquality_Id());
		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME,
				addItem.getName());
		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE,
				addItem.getType());

		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE,
				addItem.getSub_Type());
		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY,
				addItem.getCategory());

		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE,
				addItem.getIs_Active());
		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS,
				addItem.getIsSerious());

		values.put(Cat_Cause_Constr_UnQualifyField.COLUMN_PROCESS_ID,
				addItem.getProcessId());
		// Inserting Row
		db.insert(Cat_Cause_Constr_UnQualifyField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idItem;
	}

	/**
	 * Lay danh sach loi cong trinh
	 * 
	 * @return
	 */
	public List<Cat_Cause_Constr_UnQualifyEntity> getAllUnQualifyByName(
			String sSubType, int iType) {
		List<Cat_Cause_Constr_UnQualifyEntity> listItem = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
				allColumn, Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE
						+ " = ? AND "
						+ Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE + " =? "
						+ " ORDER BY "
						+ Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS,
				new String[] { sSubType, String.valueOf(iType) }, null, null,
				null, null);
		if (cursor.moveToFirst()) {
			do {
				Cat_Cause_Constr_UnQualifyEntity curItem = new Cat_Cause_Constr_UnQualifyEntity();
				curItem = this.converCursorToItem(cursor);
				// Adding contact to list
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}

	private Cat_Cause_Constr_UnQualifyEntity converCursorToItem(Cursor cursor) {
		Cat_Cause_Constr_UnQualifyEntity curItem = new Cat_Cause_Constr_UnQualifyEntity();
		try {
			curItem.setCat_Cause_Constr_Unquality_Id(cursor.getLong(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));

			curItem.setName(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));

			curItem.setType(Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE))));
			curItem.setCategory(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY)));

			curItem.setSub_Type(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE)));

			curItem.setIs_Active(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE)));
			curItem.setIsSerious(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {

		}
		return curItem;
	}

	/**
	 * Lay danh sach loi va chuyen thanh danh sach loi object
	 * 
	 * @return
	 */
	public List<Supervision_LBG_UnqualifyItemEntity> getAllUnQualifyItemByName(
			String sSubType, int iType) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
						allColumn,
						Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE
								+ " = ? AND "
								+ Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE
								+ " =? AND "
								+ Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE
								+ " = 1 ",
						new String[] { sSubType, String.valueOf(iType) },
						null,
						null,
						Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS
								+ " , "
								+ Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY
								+ " , "
								+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
						null);
		//-----
//		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
//		Cursor cursor = db
//				.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
//						allColumn,
//						Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS
//								+ " = 1 "  
//								+ " AND " +
//								 Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE
//								+ " = 1 "
//						,
//						null,//new String[] { sSubType, String.valueOf(iType) },
//						null,
//						null,
//						Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE
//						+ " , "+
//						Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS
//						+ " , "+
//						Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY
//						+ " , "+
//								 Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
//								,
//						null);
		//
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

	public int getCount() {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
				allColumn, null, null, null, null, null, null);
		return cursor.getCount();

	}

	/**
	 * Lay danh sach loi va chuyen thanh danh sach loi object
	 * 
	 * @return
	 */
	public List<Supervision_LBG_UnqualifyItemEntity> getAllUnQualifyHGItemByName(
			String sSubType, int iType) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
				allColumn, Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE
						+ " = ? AND "
						+ Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE + " =? ",
				new String[] { sSubType, String.valueOf(iType) }, null, null,
				
						 Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS,
				null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converCursorToLHGItem(cursor);
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
			curItem.setCat_Cause_Constr_Unqualify_Id(cursor.getLong(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));

			curItem.setTitle(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			curItem.setCategory(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY)));
			curItem.setIsSerious(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS)));
			
			curItem.setType(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_ACTIVE)));
			
			curItem.setCause_Line_Bg_Id(0);
			curItem.setUnqulify(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converCursorToLHGItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCat_Cause_Constr_Unqualify_Id(cursor.getLong(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));

			curItem.setTitle(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			curItem.setCategory(cursor.getString(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CATEGORY)));
			curItem.setIsSerious(cursor.getInt(cursor
					.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_IS_SERIOUS)));
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
				.query(Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
						new String[] { Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID },
						Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
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
	public boolean updateGetData(List<Cat_Cause_Constr_UnQualifyEntity> listData) {
		boolean bResult = false;
		for (Cat_Cause_Constr_UnQualifyEntity curItem : listData) {
			if (this.checkExitItem(curItem.getCat_Cause_Constr_Unquality_Id())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}

}
