package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_DropGo_Entity;
import com.viettel.database.field.Supervision_BRCD_GiamSat_DropGo_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kcn_Field;

import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_GiamSat_DropGo_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_START_SECTION,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_END_SECTION,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_START_SECTION
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_END_SECTION
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC
			+ " TEXT,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
			+ " TEXT,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_BRCD_GiamSat_DropGo_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_GiamSat_DropGo_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
				addItem.getSupervition_branch_design_id());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_START_SECTION,
				addItem.getStart_section());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_END_SECTION,
				addItem.getEnd_section());

		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
				addItem.getSupervision_dropgo_fiber_id());

		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS,
				addItem.getStatus());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC,
				addItem.getDesc());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public void updateSupervisionBRCD_Kcn(long supervision_bts_id,
			Supervision_BRCD_GiamSat_DropGo_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
				itemData.getSupervition_branch_design_id());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_END_SECTION,
				itemData.getEnd_section());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_START_SECTION,
				itemData.getStart_section());

		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
				itemData.getSupervision_dropgo_fiber_id());

		values.put(
				Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS,
				itemData.getStatus());
		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC,
				itemData.getDesc());

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db
				.update(Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
						values,
						Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID
								+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_GiamSat_DropGo_Entity converCursorToMXGS(
			Cursor cursor) {
		Supervision_BRCD_GiamSat_DropGo_Entity curItem = new Supervision_BRCD_GiamSat_DropGo_Entity();
		try {

			curItem.setSupervition_branch_design_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID)));
			curItem.setStart_section(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_START_SECTION)));
			curItem.setEnd_section(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_END_SECTION)));
			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS)));
			curItem.setDesc(cursor.getString(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC)));
			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setSupervision_dropgo_fiber_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_GiamSat_DropGo_Entity getSupervisionBRCD_Kcn(
			long idSuperConstr) {

		Supervision_BRCD_GiamSat_DropGo_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

				+ Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME
				+ " WHERE "

				+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_GiamSat_DropGo_Entity();

			brcdEntity
					.setSupervition_branch_design_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID)));
			brcdEntity
					.setDesc(cu.getString(cu
							.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_DESC)));
			brcdEntity
			.setStart_section(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_START_SECTION)));
	brcdEntity
			.setEnd_section(cu.getInt(cu
					.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_END_SECTION)));
			brcdEntity
					.setStatus(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_STATUS)));
			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			brcdEntity
					.setSupervision_dropgo_fiber_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}

	public List<Supervision_BRCD_GiamSat_DropGo_Entity> getAllbrcd_kcn(
			long supervition_brcd_id) {
		List<Supervision_BRCD_GiamSat_DropGo_Entity> listItem = new ArrayList<Supervision_BRCD_GiamSat_DropGo_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
						allColumn,
						Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
								+ " = ? AND "
								+ Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE
								+ " = ?",
						new String[] { String.valueOf(supervition_brcd_id),
								String.valueOf(Constants.IS_ACTIVE) },
						null,
						null,
						Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
						null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_BRCD_GiamSat_DropGo_Entity curItem = new Supervision_BRCD_GiamSat_DropGo_Entity();
				curItem = this.converCursorToMXGS(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listItem;
	}

	public boolean deleteGSKcnEntity(
			Supervision_BRCD_GiamSat_DropGo_Entity itemDelete) {
		boolean bResult = true;
		ContentValues values = new ContentValues();

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);

		values.put(Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemDelete
				.getSupervision_dropgo_fiber_id()) };
		bResult = this.updateDB(
				Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME, values,
				sqlclause, sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
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
