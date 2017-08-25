package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.CatProgressWorkEntity;
import com.viettel.database.field.Cat_Progress_WorkField;

import java.util.List;

public class Cat_Progress_WorkController {

	private Context mContext = null;

	public static String[] allColumn = new String[] {
			Cat_Progress_WorkField.COLUMN_ID,
			Cat_Progress_WorkField.COLUMN_NAME,
			Cat_Progress_WorkField.COLUMN_IS_ACTIVE,
			Cat_Progress_WorkField.COLUMN_PROCESS_ID };

	public static final String CREATE_CAT_PROGRESS_WORK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Progress_WorkField.TABLE_NAME
			+ "("
			+ Cat_Progress_WorkField.COLUMN_ID
			+ " TEXT PRIMARY KEY,"
			+ Cat_Progress_WorkField.COLUMN_NAME
			+ " TEXT,"
			+ Cat_Progress_WorkField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cat_Progress_WorkField.COLUMN_PROCESS_ID + " INTEGER" + ")";

	public Cat_Progress_WorkController(Context pContext) {
		this.mContext = pContext;
	}

	public String getName(int id) {
		String resultName = "";
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Cat_Progress_WorkField.TABLE_NAME,
				new String[] { Cat_Progress_WorkField.COLUMN_NAME },
				Cat_Progress_WorkField.COLUMN_ID + " = ?  AND "
						+ Cat_Progress_WorkField.COLUMN_IS_ACTIVE + " = ? ",
				new String[] { String.valueOf(id),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);

		if (cursor.moveToFirst()) {
			resultName = cursor.getString(cursor
					.getColumnIndex(Cat_Progress_WorkField.COLUMN_NAME));
		}
		KttsDatabaseHelper.INSTANCE.close();

		return resultName;

	}

	public Long addItem(CatProgressWorkEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Cat_Progress_WorkField.COLUMN_ID, addItem.getId());
		values.put(Cat_Progress_WorkField.COLUMN_NAME, addItem.getName());

		values.put(Cat_Progress_WorkField.COLUMN_IS_ACTIVE,
				addItem.getIs_Active());

		values.put(Cat_Progress_WorkField.COLUMN_PROCESS_ID,
				addItem.getProcessId());
		// Inserting Row
		db.insert(Cat_Progress_WorkField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idItem;
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
				.query(Cat_Progress_WorkField.TABLE_NAME,
						new String[] { Cat_Progress_WorkField.COLUMN_ID },
						Cat_Progress_WorkField.COLUMN_ID + "=?",
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
	public boolean updateGetData(List<CatProgressWorkEntity> listData) {
		boolean bResult = false;
		for (CatProgressWorkEntity curItem : listData) {
			if (this.checkExitItem(curItem.getId())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}
}
