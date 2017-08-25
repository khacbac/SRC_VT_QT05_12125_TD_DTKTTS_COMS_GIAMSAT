package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Special_LocationEntity;
import com.viettel.database.entity.Special_LocationGSEntity;
import com.viettel.database.field.Special_LocationField;

import java.util.ArrayList;
import java.util.List;

public class Special_LocationController {
	private Context mContext = null;

	public Special_LocationController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
			Special_LocationField.COLUMN_SPECIAL_LOCATION_TYPE,
			Special_LocationField.COLUMN_SUPPLIE_TYPE,
			Special_LocationField.COLUMN_BRIDGE_NAME,
			Special_LocationField.COLUMN_FROM_STAGE,
			Special_LocationField.COLUMN_TO_STAGE,
			Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
			Special_LocationField.COLUMN_SYNC_STATUS,
			Special_LocationField.COLUMN_IS_ACTIVE,
			Special_LocationField.COLUMN_PROCESS_ID,
			Special_LocationField.COLUMN_EMPLOYEE_ID,
			Special_LocationField.COLUMN_SUPERVISION_CONSTR_ID,
			Special_LocationField.COLUMN_STATUS,
			Special_LocationField.COLUMN_LON_START,
			Special_LocationField.COLUMN_LON_END,
			Special_LocationField.COLUMN_LAT_START,
			Special_LocationField.COLUMN_LAT_END};

	public static final String CREATE_SPECIAL_LOCATION_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Special_LocationField.TABLE_NAME
			+ "("
			+ Special_LocationField.COLUMN_SPECIAL_LOCATION_ID
			+ " TEXT PRIMARY KEY,"
			+ Special_LocationField.COLUMN_SPECIAL_LOCATION_TYPE
			+ " INTEGER,"
			+ Special_LocationField.COLUMN_STATUS
			+ " INTEGER DEFAULT -1,"
			+ Special_LocationField.COLUMN_LON_START
			+ " TEXT DEFAULT -1,"
			+ Special_LocationField.COLUMN_LON_END
			+ " TEXT DEFAULT -1,"
			+ Special_LocationField.COLUMN_LAT_START
			+ " TEXT DEFAULT -1,"
			+ Special_LocationField.COLUMN_LAT_END
			+ " TEXT DEFAULT -1,"
			+ Special_LocationField.COLUMN_SUPPLIE_TYPE
			+ " TEXT,"
			+ Special_LocationField.COLUMN_BRIDGE_NAME
			+ " TEXT,"
			+ Special_LocationField.COLUMN_FROM_STAGE
			+ " TEXT,"
			+ Special_LocationField.COLUMN_TO_STAGE
			+ " TEXT,"
			+ Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
			+ " TEXT,"
			+ Special_LocationField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Special_LocationField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Special_LocationField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Special_LocationField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Special_LocationField.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public boolean insertItemBridge(Special_LocationEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
				addItem.getSpecialLocationId());

		values.put(Special_LocationField.COLUMN_FROM_STAGE,
				addItem.getFromStage());

		values.put(Special_LocationField.COLUMN_TO_STAGE, addItem.getToStage());

		values.put(Special_LocationField.COLUMN_BRIDGE_NAME,
				addItem.getBridgeName());

		values.put(Special_LocationField.COLUMN_SUPPLIE_TYPE,
				addItem.getSupplieType());

		values.put(Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
				addItem.getSupervisionLineBackgroundId());

		values.put(Special_LocationField.COLUMN_SPECIAL_LOCATION_TYPE,
				addItem.getSpecialLocationType());

		values.put(Special_LocationField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Special_LocationField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Special_LocationField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Special_LocationField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Special_LocationField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public boolean updateAllColumn(Special_LocationEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();
		values.put(Special_LocationField.COLUMN_FROM_STAGE,
				updateItem.getFromStage());
		values.put(Special_LocationField.COLUMN_TO_STAGE,
				updateItem.getToStage());
		values.put(Special_LocationField.COLUMN_BRIDGE_NAME,
				updateItem.getBridgeName());
		values.put(Special_LocationField.COLUMN_SUPPLIE_TYPE,
				updateItem.getSupplieType());
		values.put(Special_LocationField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());
		String sqlclause = Special_LocationField.COLUMN_SPECIAL_LOCATION_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(updateItem
				.getSpecialLocationId()) };
		bResult = this.updateDB(Special_LocationField.TABLE_NAME, values,
				sqlclause, sqlPara);
		return bResult;
	}
	
	public boolean updateColumn(Special_LocationGSEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();
		values.put(Special_LocationField.COLUMN_STATUS,
				updateItem.getStatus());
		values.put(Special_LocationField.COLUMN_LAT_START,
				updateItem.getLatStartLocation());
		values.put(Special_LocationField.COLUMN_LAT_END,
				updateItem.getLatEndLocation());
		values.put(Special_LocationField.COLUMN_LON_START,
				updateItem.getLonStartLocation());
		values.put(Special_LocationField.COLUMN_LON_END,
				updateItem.getLonEndLocation());
		values.put(Special_LocationField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());
		String sqlclause = Special_LocationField.COLUMN_SPECIAL_LOCATION_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(updateItem
				.getIdSpecial()) };
		bResult = this.updateDB(Special_LocationField.TABLE_NAME, values,
				sqlclause, sqlPara);
		return bResult;
	}

	public void deleteItem(Special_LocationEntity itemDel) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Special_LocationField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		values.put(Special_LocationField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		String[] sqlPara = new String[] { String.valueOf(itemDel
				.getSpecialLocationId()) };
		// Update Row
		db.update(Special_LocationField.TABLE_NAME, values,
				Special_LocationField.COLUMN_SPECIAL_LOCATION_ID + "= ?",
				sqlPara);

		KttsDatabaseHelper.INSTANCE.close();

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
		boolean bResult = true;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		int iRow = db.update(sTable, values, sqlClause, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
		if (iRow > 0) {
			bResult = true;
		}
		return bResult;
	}

	public List<Special_LocationEntity> getListBridge(long idLineBackground,
			long iType) {
		List<Special_LocationEntity> result = new ArrayList<Special_LocationEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Special_LocationField.TABLE_NAME,
				allColumn,
				Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
						+ " = ?  AND " + Special_LocationField.COLUMN_IS_ACTIVE
						+ " = ? AND "
						+ Special_LocationField.COLUMN_SPECIAL_LOCATION_TYPE
						+ " = ?",
				new String[] { String.valueOf(idLineBackground),
						String.valueOf(Constants.ISACTIVE.ACTIVE),
						String.valueOf(iType) }, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Special_LocationEntity curItem = new Special_LocationEntity();
				curItem = this.converCursorToBridge(cursor);
				result.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();

		return result;
	}
	
	/* Lay danh sach be giam sat */
	public List<Special_LocationGSEntity> getAllSpecialGSBySupervistionLineBackground(
			long idLineBackground) {
		List<Special_LocationGSEntity> listItem = new ArrayList<Special_LocationGSEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Special_LocationField.TABLE_NAME,
						allColumn,
						Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
								+ " = ? AND "
								+ Special_LocationField.COLUMN_IS_ACTIVE
								+ " = ?",
						new String[] { String.valueOf(idLineBackground),
								String.valueOf(Constants.ISACTIVE.ACTIVE) },
						null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Special_LocationGSEntity curItem = new Special_LocationGSEntity();
				curItem = this.converCursorToSpecialGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}
	
	private Special_LocationGSEntity converCursorToSpecialGS(
			Cursor cursor) {
		Special_LocationGSEntity curItem = new Special_LocationGSEntity();
		
		curItem.setIdSpecial(cursor.getLong(cursor
				.getColumnIndex(Special_LocationField.COLUMN_SPECIAL_LOCATION_ID)));

		curItem.setSpecialName(cursor.getString(cursor
				.getColumnIndex(Special_LocationField.COLUMN_BRIDGE_NAME)));

		curItem.setStatus(cursor.getInt(cursor
				.getColumnIndex(Special_LocationField.COLUMN_STATUS)));
		
		curItem.setLonStartLocation(cursor.getDouble(cursor
				.getColumnIndex(Special_LocationField.COLUMN_LON_START)));
		
		curItem.setLonEndLocation(cursor.getDouble(cursor
				.getColumnIndex(Special_LocationField.COLUMN_LON_END)));
		
		curItem.setLatStartLocation(cursor.getDouble(cursor
				.getColumnIndex(Special_LocationField.COLUMN_LAT_START)));
		
		curItem.setLatEndLocation(cursor.getDouble(cursor
				.getColumnIndex(Special_LocationField.COLUMN_LAT_END)));

		curItem.setSync_Status(cursor.getInt(cursor
				.getColumnIndex(Special_LocationField.COLUMN_SYNC_STATUS)));

		curItem.setIsActive(cursor.getInt(cursor
				.getColumnIndex(Special_LocationField.COLUMN_IS_ACTIVE)));

		curItem.setProcessId(cursor.getLong(cursor
				.getColumnIndex(Special_LocationField.COLUMN_PROCESS_ID)));

		curItem.setNew(false);
		curItem.setEdited(false);
		return curItem;
	}

	private Special_LocationEntity converCursorToBridge(Cursor cursor) {
		Special_LocationEntity curItem = new Special_LocationEntity();
		try {
			curItem.setSpecialLocationId(cursor.getLong(cursor
					.getColumnIndex(Special_LocationField.COLUMN_SPECIAL_LOCATION_ID)));

			curItem.setFromStage(cursor.getString(cursor
					.getColumnIndex(Special_LocationField.COLUMN_FROM_STAGE)));

			curItem.setToStage(cursor.getString(cursor
					.getColumnIndex(Special_LocationField.COLUMN_TO_STAGE)));

			curItem.setBridgeName(cursor.getString(cursor
					.getColumnIndex(Special_LocationField.COLUMN_BRIDGE_NAME)));

			curItem.setSupplieType(cursor.getString(cursor
					.getColumnIndex(Special_LocationField.COLUMN_SUPPLIE_TYPE)));

			curItem.setSpecialLocationType(cursor.getInt(cursor
					.getColumnIndex(Special_LocationField.COLUMN_SPECIAL_LOCATION_TYPE)));

			curItem.setSupervisionLineBackgroundId(cursor.getLong(cursor
					.getColumnIndex(Special_LocationField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Special_LocationField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Special_LocationField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Special_LocationField.COLUMN_PROCESS_ID)));

			curItem.setNew(false);
			curItem.setEdited(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}
}
