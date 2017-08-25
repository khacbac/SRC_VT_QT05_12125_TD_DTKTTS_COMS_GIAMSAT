package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Design_Entity;
import com.viettel.database.field.Supervision_BRCD_Kcn_Design_Field;

public class Supervision_BRCD_Kcn_Design_Controller {

	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,

			Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM,

			Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE,

			Supervision_BRCD_Kcn_Design_Field.COLUMN_SYNC_STATUS,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_IS_ACTIVE,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_PROCESS_ID,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_EMPLOYEE_ID,
			Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BRCD_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BRCD_Kcn_Design_Field.TABLE_NAME
			+ "("
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM
			+ " INTEGER,"

			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_BRCD_Kcn_Design_Controller(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItem(Supervision_BRCD_Kcn_Design_Entity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
				addItem.getSupervition_branch_design_id());

		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
				addItem.getSupervition_branch_cable_id());

		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH,
				addItem.getLength());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM,
				addItem.getPillar_Old_Number());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM,
				addItem.getPillar_New_Number());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE,
				addItem.getCable_Type());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO,
				addItem.getCable_Drop_Num_Two());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO,
				addItem.getNum_Box_Two());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR,
				addItem.getCable_Drop_Num_Four());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR,
				addItem.getNum_Box_Four());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT,
				addItem.getCable_Drop_Num_Eight());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT,
				addItem.getNum_Box_Eight());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE,
				addItem.getCable_Drop_Num_Twelve());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE,
				addItem.getNum_Box_Twelve());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	public boolean updateDropNumTwo(long id, int dropNum) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO,
				dropNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateDropNumFour(long id, int dropNum) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR,
				dropNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateDropNumEight(long id, int dropNum) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT,
				dropNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateDropNumTwelve(long id, int dropNum) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE,
				dropNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateBoxNumTwo(long id, int boxNum) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO, boxNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateBoxNumFour(long id, int boxNum) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR,
				boxNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateBoxNumEight(long id, int boxNum) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT,
				boxNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public boolean updateBoxNumTwelve(long id, int boxNum) {
		ContentValues values = new ContentValues();
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE,
				boxNum);
		String sqlclause = Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
				values, sqlclause, sqlPara);
	}

	public void updateSupervisionBRCD_Kcn_Design(long supervision_bts_id,
			Supervision_BRCD_Kcn_Design_Entity itemData) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
				itemData.getSupervition_branch_cable_id());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervisionConstrId());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH,
				itemData.getLength());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM,
				itemData.getPillar_Old_Number());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM,
				itemData.getPillar_New_Number());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE,
				itemData.getCable_Type());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO,
				itemData.getCable_Drop_Num_Two());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO,
				itemData.getNum_Box_Two());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR,
				itemData.getCable_Drop_Num_Four());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR,
				itemData.getNum_Box_Four());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT,
				itemData.getCable_Drop_Num_Eight());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT,
				itemData.getNum_Box_Eight());
		values.put(
				Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE,
				itemData.getCable_Drop_Num_Twelve());
		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE,
				itemData.getNum_Box_Twelve());

		values.put(Supervision_BRCD_Kcn_Design_Field.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		int in = db
				.update(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
						values,
						Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
								+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	private Supervision_BRCD_Kcn_Design_Entity converCursorToMXGS(Cursor cursor) {
		Supervision_BRCD_Kcn_Design_Entity curItem = new Supervision_BRCD_Kcn_Design_Entity();
		try {

			curItem.setSupervition_branch_design_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID)));
			curItem.setSupervition_branch_cable_id(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID)));

			curItem.setLength(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH)));
			curItem.setPillar_Old_Number(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM)));
			curItem.setPillar_New_Number(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM)));

			curItem.setCable_Type(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE)));
			curItem.setCable_Drop_Num_Two(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO)));
			curItem.setNum_Box_Two(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO)));
			curItem.setCable_Drop_Num_Four(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR)));
			curItem.setNum_Box_Four(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR)));
			curItem.setCable_Drop_Num_Eight(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT)));
			curItem.setNum_Box_Eight(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT)));
			curItem.setCable_Drop_Num_Twelve(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE)));
			curItem.setNum_Box_Twelve(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE)));

			curItem.setSupervisionConstrId(cursor.getLong(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			curItem.setProcessId(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PROCESS_ID)));
			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SYNC_STATUS)));
			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_IS_ACTIVE)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public Supervision_BRCD_Kcn_Design_Entity getSupervisionBRCD_Kcn_design(
			long idSuperConstr) {

		Supervision_BRCD_Kcn_Design_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Kcn_Design_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID
				+ " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Kcn_Design_Entity();

			brcdEntity
					.setSupervition_branch_design_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID)));
			brcdEntity
					.setSupervition_branch_cable_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID)));
			brcdEntity
					.setLength(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH)));
			brcdEntity
					.setPillar_Old_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM)));
			brcdEntity
					.setPillar_New_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM)));
			brcdEntity
					.setCable_Type(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE)));
			brcdEntity
					.setCable_Drop_Num_Two(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO)));
			brcdEntity
					.setNum_Box_Two(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO)));
			brcdEntity
					.setCable_Drop_Num_Four(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR)));
			brcdEntity
					.setNum_Box_Four(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR)));
			brcdEntity
					.setCable_Drop_Num_Eight(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT)));
			brcdEntity
					.setNum_Box_Eight(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT)));
			brcdEntity
					.setCable_Drop_Num_Twelve(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE)));
			brcdEntity
					.setNum_Box_Twelve(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE)));

			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
	}

	public Supervision_BRCD_Kcn_Design_Entity getSupervisionBRCD_Kcn_design_id(
			long idSuperConstr) {

		Supervision_BRCD_Kcn_Design_Entity brcdEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT * FROM "

		+ Supervision_BRCD_Kcn_Design_Field.TABLE_NAME + " WHERE "

		+ Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID
				+ " = ?";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr) };

		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			brcdEntity = new Supervision_BRCD_Kcn_Design_Entity();

			brcdEntity
					.setSupervition_branch_design_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID)));
			brcdEntity
					.setSupervition_branch_cable_id(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID)));
			brcdEntity
					.setLength(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_LENGTH)));
			brcdEntity
					.setPillar_Old_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_OLD_NUM)));
			brcdEntity
					.setPillar_New_Number(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_PILLAR_NEW_NUM)));
			brcdEntity
					.setCable_Type(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_TYPE)));
			brcdEntity
					.setCable_Drop_Num_Two(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWO)));
			brcdEntity
					.setNum_Box_Two(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWO)));
			brcdEntity
					.setCable_Drop_Num_Four(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_FOUR)));
			brcdEntity
					.setNum_Box_Four(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_FOUR)));
			brcdEntity
					.setCable_Drop_Num_Eight(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_EIGHT)));
			brcdEntity
					.setNum_Box_Eight(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_EIGHT)));
			brcdEntity
					.setCable_Drop_Num_Twelve(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_CABLE_DROP_NUM_TWELVE)));
			brcdEntity
					.setNum_Box_Twelve(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_NUM_BOX_TWELVE)));
			brcdEntity
					.setSupervisionConstrId(cu.getInt(cu
							.getColumnIndex(Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_CONSTR_ID)));

		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return brcdEntity;
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
