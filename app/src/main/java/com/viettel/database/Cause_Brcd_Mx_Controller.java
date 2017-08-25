package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Acceptance_Line_Bg_TankEntity;
import com.viettel.database.entity.Cause_Line_BG_TankEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Acceptance_Brcd_Mx_Field;
import com.viettel.database.field.Cause_Brcd_Mx_Field;

import java.util.ArrayList;
import java.util.List;

public class Cause_Brcd_Mx_Controller {
	public static final String[] allColumn = new String[] {
			Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
			Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID,
			Cause_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cause_Brcd_Mx_Field.COLUMN_SYNC_STATUS,
			Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE,
			Cause_Brcd_Mx_Field.COLUMN_PROCESS_ID,
			Cause_Brcd_Mx_Field.COLUMN_EMPLOYEE_ID,
			Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_CONSTR_ID };

	private Context mContext = null;
	public static final String CREATE_CAUSE_LINE_BG_TANK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cause_Brcd_Mx_Field.TABLE_NAME
			+ "("
			+ Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID
			+ " TEXT PRIMARY KEY,"
			+ Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID
			+ " TEXT,"
			+ Cause_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " TEXT,"
			+ Cause_Brcd_Mx_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cause_Brcd_Mx_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cause_Brcd_Mx_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public Cause_Brcd_Mx_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Cause_Line_BG_TankEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
				addItem.getCause_Line_Bg_Tank_Id());

		values.put(Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID,
				addItem.getSupervision_Line_Bg_Tank_Id());

		values.put(Cause_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				addItem.getCat_Cause_Constr_Unqualify_Id());

		values.put(Cause_Brcd_Mx_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE, addItem.getIsActive());

		values.put(Cause_Brcd_Mx_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Cause_Brcd_Mx_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Cause_Brcd_Mx_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public boolean addItem(Acceptance_Line_Bg_TankEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID,
				addItem.getAcceptance_Line_Bg_Tank_Id());

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID,
				addItem.getSupervision_Line_Bg_Tank_Id());

		values.put(
				Acceptance_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
				addItem.getCat_Cause_Constr_Acceptance_Id());

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Acceptance_Brcd_Mx_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public boolean delete(Supervision_LBG_UnqualifyItemEntity pDelItem) {
		ContentValues values = new ContentValues();

		values.put(Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);

		values.put(Cause_Brcd_Mx_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getCause_Line_Bg_Id()) };
		return this.updateDB(Cause_Brcd_Mx_Field.TABLE_NAME, values, sqlclause,
				sqlPara);
	}

	public boolean deleteAccept(Supervision_LBG_UnqualifyItemEntity pDelItem) {
		ContentValues values = new ContentValues();

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);

		values.put(Acceptance_Brcd_Mx_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getCause_Line_Bg_Id()) };
		return this.updateDB(Acceptance_Brcd_Mx_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getAllTankUnqulifyByTankId(
			long iTankId) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Cause_Brcd_Mx_Field.TABLE_NAME,
				allColumn,
				Cause_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID
						+ " = ? AND " + Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE
						+ "= ?",
				new String[] { String.valueOf(iTankId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converUnqualifyToUnqualifyItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getAllTanAcceptByTankId(
			long iTankId) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Acceptance_Brcd_Mx_Field.TABLE_NAME,
				Acceptance_Brcd_Mx_Controller.allColumn,
				Acceptance_Brcd_Mx_Field.COLUMN_SUPERVISION_TRUNK_MX_ID
						+ " = ? AND "
						+ Acceptance_Brcd_Mx_Field.COLUMN_IS_ACTIVE + "= ?",
				new String[] { String.valueOf(iTankId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null,
				Acceptance_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
				null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converAcceptToUnqualifyItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converAcceptToUnqualifyItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCause_Line_Bg_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID)));

			curItem.setCat_Cause_Constr_Acceptance_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Brcd_Mx_Field.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Brcd_Mx_Field.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Brcd_Mx_Field.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converUnqualifyToUnqualifyItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCause_Line_Bg_Id(cursor.getLong(cursor
					.getColumnIndex(Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID)));

			curItem.setCat_Cause_Constr_Unqualify_Id(cursor.getLong(cursor
					.getColumnIndex(Cause_Brcd_Mx_Field.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Cause_Brcd_Mx_Field.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Cause_Brcd_Mx_Field.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Cause_Brcd_Mx_Field.COLUMN_PROCESS_ID)));

			curItem.setUnqulify(true);
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
