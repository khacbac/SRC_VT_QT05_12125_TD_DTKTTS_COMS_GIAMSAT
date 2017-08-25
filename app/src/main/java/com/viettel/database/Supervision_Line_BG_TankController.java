package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BG_TankEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.field.Supervision_Line_BG_TankField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_TankController {
	public static final String[] allColumn = new String[] {
			Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
			Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
			Supervision_Line_BG_TankField.COLUMN_TANK_NAME,
			Supervision_Line_BG_TankField.COLUMN_NUM_PART,
			Supervision_Line_BG_TankField.COLUMN_LONGITUDE,
			Supervision_Line_BG_TankField.COLUMN_LATITUDE,
			Supervision_Line_BG_TankField.COLUMN_STATUS,
			Supervision_Line_BG_TankField.COLUMN_FAIL_DESC,
			Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS,
			Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE,
			Supervision_Line_BG_TankField.COLUMN_PROCESS_ID,
			Supervision_Line_BG_TankField.COLUMN_EMPLOYEE_ID,
			Supervision_Line_BG_TankField.COLUMN_SUPERVISION_CONSTR_ID};

	private Context mContext = null;
	public static final String CREATE_SUPERVISION_LINE_BG_TANK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_BG_TankField.TABLE_NAME
			+ "("
			+ Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
			+ " TEXT,"
			+ Supervision_Line_BG_TankField.COLUMN_TANK_NAME
			+ " TEXT,"
			+ Supervision_Line_BG_TankField.COLUMN_LONGITUDE
			+ " TEXT,"
			+ Supervision_Line_BG_TankField.COLUMN_LATITUDE
			+ " TEXT,"
			+ Supervision_Line_BG_TankField.COLUMN_NUM_PART
			+ " INTEGER,"
			+ Supervision_Line_BG_TankField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Line_BG_TankField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_BG_TankField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_BG_TankField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Supervision_Line_BG_TankField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_Line_BG_TankController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_Line_BG_TankEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BG_TankField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(
				Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
				addItem.getSupervision_Line_Bg_Tank_Id());
		values.put(
				Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
				addItem.getSupervision_Line_Background_Id());

		values.put(Supervision_Line_BG_TankField.COLUMN_TANK_NAME,
				addItem.getTank_Name());

		values.put(Supervision_Line_BG_TankField.COLUMN_NUM_PART,
				addItem.getNum_Part());

		values.put(Supervision_Line_BG_TankField.COLUMN_LONGITUDE,
				addItem.getLonLocation());

		values.put(Supervision_Line_BG_TankField.COLUMN_LATITUDE,
				addItem.getLatLocation());

		values.put(Supervision_Line_BG_TankField.COLUMN_STATUS,
				addItem.getStatus());

		values.put(Supervision_Line_BG_TankField.COLUMN_FAIL_DESC,
				addItem.getFail_Desc());

		values.put(Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_BG_TankField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_BG_TankField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idAddItem = db.insert(Supervision_Line_BG_TankField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}
	
	public boolean deleteTankEntity(Supervision_Line_BG_TankGSEntity itemDelete){
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);		
		
		values.put(Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getIdTank()) };
		bResult = this.updateDB(Supervision_Line_BG_TankField.TABLE_NAME,
				values, sqlclause, sqlPara);
		
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public boolean updateAllColumn(Supervision_Line_BG_TankGSEntity updateItem) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_Line_BG_TankField.COLUMN_NUM_PART,
				updateItem.getNumberPart());

		values.put(Supervision_Line_BG_TankField.COLUMN_LATITUDE,
				updateItem.getLatLocation());

		values.put(Supervision_Line_BG_TankField.COLUMN_LONGITUDE,
				updateItem.getLonLocation());

		values.put(Supervision_Line_BG_TankField.COLUMN_STATUS,
				updateItem.getStatus());

		values.put(Supervision_Line_BG_TankField.COLUMN_FAIL_DESC,
				updateItem.getFailDesc());

		values.put(Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS,
				updateItem.getSync_Status());

		values.put(Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE,
				updateItem.getIsActive());

		values.put(Supervision_Line_BG_TankField.COLUMN_PROCESS_ID,
				updateItem.getProcessId());
		String sqlclause = Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID
				+ "= ?";
		String[] sqlPara = new String[] { String
				.valueOf(updateItem.getIdTank()) };
		bResult = this.updateDB(Supervision_Line_BG_TankField.TABLE_NAME,
				values, sqlclause, sqlPara);

		return bResult;
	}

	public List<Supervision_Line_BG_TankEntity> getAllBySupervistionLineBackground(
			int idLineBackground) {
		List<Supervision_Line_BG_TankEntity> listItem = new ArrayList<Supervision_Line_BG_TankEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Line_BG_TankField.TABLE_NAME,
						allColumn,
						Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
								+ " = ? ",
						new String[] { String.valueOf(idLineBackground) },
						null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_BG_TankEntity curItem = new Supervision_Line_BG_TankEntity();
				curItem = this.converCursorToItem(cursor);
				// Adding contact to list
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	/* Lay danh sach be giam sat */
	public List<Supervision_Line_BG_TankGSEntity> getAllTankGSBySupervistionLineBackground(
			long idLineBackground) {
		List<Supervision_Line_BG_TankGSEntity> listItem = new ArrayList<Supervision_Line_BG_TankGSEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Line_BG_TankField.TABLE_NAME,
						allColumn,
						Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
								+ " = ? AND "+Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE+ " = ?",
						new String[] { String.valueOf(idLineBackground),String.valueOf(Constants.IS_ACTIVE)  },
						null,
						null,
						Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
						null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_BG_TankGSEntity curItem = new Supervision_Line_BG_TankGSEntity();
				curItem = this.converCursorToTankGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	private Supervision_Line_BG_TankEntity converCursorToItem(Cursor cursor) {
		Supervision_Line_BG_TankEntity curItem = new Supervision_Line_BG_TankEntity();
		try {
			curItem.setSupervision_Line_Bg_Tank_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID)));

			curItem.setSupervision_Line_Background_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID)));

			curItem.setTank_Name(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_TANK_NAME)));

			curItem.setNum_Part(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_NUM_PART)));

			curItem.setLonLocation(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_LONGITUDE)));

			curItem.setLatLocation(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_LATITUDE)));

			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_STATUS)));

			curItem.setFail_Desc(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_FAIL_DESC)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	private Supervision_Line_BG_TankGSEntity converCursorToTankGS(Cursor cursor) {
		Supervision_Line_BG_TankGSEntity curItem = new Supervision_Line_BG_TankGSEntity();
		try {
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SUPERVISION_CONSTR_ID)));
			
			curItem.setIdTank(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID)));

			curItem.setTankName(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_TANK_NAME)));

			curItem.setNumberPart(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_NUM_PART)));

			curItem.setLonLocation(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_LONGITUDE)));

			curItem.setLatLocation(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_LATITUDE)));

			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_STATUS)));

			curItem.setFailDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_FAIL_DESC)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BG_TankField.COLUMN_PROCESS_ID)));

			curItem.setNew(false);
			curItem.setEdited(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
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
