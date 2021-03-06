package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cause_Line_Hg_CableEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Cause_Line_BG_CableField;
import com.viettel.database.field.Cause_Line_Hg_CableField;

import java.util.ArrayList;
import java.util.List;

public class Cause_Line_Hg_CableController {
	public static final String[] allColumn = new String[] {
			Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,
			Cause_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
			Cause_Line_Hg_CableField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cause_Line_Hg_CableField.COLUMN_SYNC_STATUS,
			Cause_Line_Hg_CableField.COLUMN_IS_ACTIVE,
			Cause_Line_Hg_CableField.COLUMN_PROCESS_ID,
			Cause_Line_Hg_CableField.COLUMN_EMPLOYEE_ID,
			Cause_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID};

	private Context mContext = null;
	public static final String CREATE_CAUSE_LINE_HG_CABLE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cause_Line_Hg_CableField.TABLE_NAME
			+ "("
			+ Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID
			+ " TEXT PRIMARY KEY,"
			+ Cause_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID
			+ " TEXT,"
			+ Cause_Line_Hg_CableField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " TEXT,"
			+ Cause_Line_Hg_CableField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Cause_Line_Hg_CableField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cause_Line_Hg_CableField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cause_Line_Hg_CableField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Cause_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Cause_Line_Hg_CableController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Cause_Line_Hg_CableEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Cause_Line_Hg_CableField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());
		
		values.put(Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,
				addItem.getCause_Line_Hg_Cable_Id());
		values.put(
				Cause_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
				addItem.getSupervision_Line_Hg_Cable_Id());
		values.put(
				Cause_Line_Hg_CableField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				addItem.getCat_Cause_Constr_Unqualify_Id());

		values.put(Cause_Line_Hg_CableField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Cause_Line_Hg_CableField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Cause_Line_Hg_CableField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Cause_Line_Hg_CableField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idItem = db.insert(Cause_Line_Hg_CableField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public boolean delete(Supervision_LBG_UnqualifyItemEntity pDelItem) {
		ContentValues values = new ContentValues();

		values.put(Cause_Line_Hg_CableField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);	
		values.put(Cause_Line_Hg_CableField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		String sqlclause = Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getCause_Line_Bg_Id()) };
		return this.updateDB(Cause_Line_Hg_CableField.TABLE_NAME, values,
				sqlclause, sqlPara);
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

	public boolean delete(int idItem) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sClause = Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID
				+ " = ?";
		String[] sParas = new String[] { String.valueOf(idItem) };
		int iResult = db.delete(Cause_Line_Hg_CableField.TABLE_NAME, sClause,
				sParas);
		if (iResult > 0) {
			bResult = true;
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getAllCableUnqulifyByCableId(
			long iCableId) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Cause_Line_Hg_CableField.TABLE_NAME,
				allColumn,
				Cause_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID
						+ " = ? AND "
						+ Cause_Line_Hg_CableField.COLUMN_IS_ACTIVE + " = ?",
				new String[] { String.valueOf(iCableId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converUnqualifyToUnqualifyItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converUnqualifyToUnqualifyItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCause_Line_Bg_Id(cursor.getLong(cursor
					.getColumnIndex(Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID)));

			curItem.setCat_Cause_Constr_Unqualify_Id(cursor.getLong(cursor
					.getColumnIndex(Cause_Line_Hg_CableField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Cause_Line_BG_CableField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Cause_Line_BG_CableField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Cause_Line_BG_CableField.COLUMN_PROCESS_ID)));

			curItem.setUnqulify(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

}
