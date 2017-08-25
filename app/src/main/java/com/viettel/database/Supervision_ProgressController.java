package com.viettel.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_ProgressEntity;
import com.viettel.database.field.Supervision_ProgressField;

import java.text.SimpleDateFormat;

public class Supervision_ProgressController {
	public static final String[] allColumn = new String[] {
			Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
			Supervision_ProgressField.COLUMN_COMPLETE_DATE,
			Supervision_ProgressField.COLUMN_TYPE,
			Supervision_ProgressField.COLUMN_SUB_TYPE,
			Supervision_ProgressField.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_ProgressField.COLUMN_SYNC_STATUS,
			Supervision_ProgressField.COLUMN_PROCESS_ID,
			Supervision_ProgressField.COLUMN_EMPLOYEE_ID };

	private Context mContext = null;

	public static final String CREATE_SUPERVISION_PROGRESS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_ProgressField.TABLE_NAME
			+ "("
			+ Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_ProgressField.COLUMN_COMPLETE_DATE
			+ " TEXT,"
			+ Supervision_ProgressField.COLUMN_TYPE
			+ " INTEGER,"
			+ Supervision_ProgressField.COLUMN_SUB_TYPE
			+ " TEXT,"
			+ Supervision_ProgressField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_ProgressField.COLUMN_SUPERVISION_CONSTR_ID
			+ " INTEGER,"
			+ Supervision_ProgressField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_ProgressField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Supervision_ProgressController(Context pContext) {
		this.mContext = pContext;
	}

	@SuppressLint("SimpleDateFormat")
	public boolean addItem(Supervision_ProgressEntity addItem) {
		String sFormat = "yyyy-dd-MM";
		SimpleDateFormat dateView = new SimpleDateFormat(
				sFormat);
		
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
				addItem.getSupervisionProgressId());
		values.put(Supervision_ProgressField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		values.put(Supervision_ProgressField.COLUMN_TYPE, addItem.getType());
		values.put(Supervision_ProgressField.COLUMN_SUB_TYPE,
				addItem.getSubType());
		values.put(Supervision_ProgressField.COLUMN_COMPLETE_DATE,
				dateView.format(addItem.getCompleteDate()));
		values.put(Supervision_ProgressField.COLUMN_SUB_TYPE,
				addItem.getSubType());

		values.put(Supervision_ProgressField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_ProgressField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_ProgressField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Supervision_ProgressField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public Supervision_ProgressEntity getSupvProgress(long idSupvConstr, int iType, String subType) {
		Supervision_ProgressEntity curItem = new Supervision_ProgressEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		Cursor cursor = db
				.query(Supervision_ProgressField.TABLE_NAME,
						new String[] {
								Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
								Supervision_ProgressField.COLUMN_COMPLETE_DATE },
						Supervision_ProgressField.COLUMN_SUPERVISION_CONSTR_ID
								+ " = ? AND "
								+ Supervision_ProgressField.COLUMN_TYPE
								+ " = ? AND "
								+ Supervision_ProgressField.COLUMN_SUB_TYPE
								+ " = ? ",
						new String[] { String.valueOf(idSupvConstr),
								String.valueOf(iType), subType },
						null,
						null,
						Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
						null);
		if (cursor.moveToLast()) {

			curItem = this.converCursorToItem(cursor);
			// dateResult = cursor
			// .getString(cursor
			// .getColumnIndex(Supervision_ProgressField.COLUMN_COMPLETE_DATE));
		}
		cursor.close();

		KttsDatabaseHelper.INSTANCE.close();

		return curItem;
	}

	private Supervision_ProgressEntity converCursorToItem(Cursor cursor) {
		Supervision_ProgressEntity curItem = new Supervision_ProgressEntity();
		try {
			String sFormat = "yyyy-dd-MM";
			SimpleDateFormat dateView = new SimpleDateFormat(
					sFormat);
			
			curItem.setSupervisionProgressId(cursor.getLong(cursor
					.getColumnIndex(Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID)));

			curItem.setCompleteDate(dateView.parse(cursor.getString(cursor
					.getColumnIndex(Supervision_ProgressField.COLUMN_COMPLETE_DATE))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}
}
