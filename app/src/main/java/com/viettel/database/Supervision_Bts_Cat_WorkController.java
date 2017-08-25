package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;

public class Supervision_Bts_Cat_WorkController {
	private Context mContext = null;

	public Supervision_Bts_Cat_WorkController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
			Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN,
			Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL,
			Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE,
			Supervision_Bts_Cat_WorkField.COLUMN_STATUS,
			Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC,
			Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
			Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
			Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
			Supervision_Bts_Cat_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
			Supervision_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID,
			Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_SUPERVISION_BTS_CAT_WORK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Bts_Cat_WorkField.TABLE_NAME
			+ "("
			+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE
			+ " INTEGER,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public void insertBtsCatWorkEntity(
			Supervision_Bts_Cat_WorkEntity catWorkEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID,
				catWorkEntity.getSupervisionConstrId());
		values.put(
				Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
				catWorkEntity.getSupervision_Bts_Cat_Work_Id());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_STATUS,
				catWorkEntity.getStatus());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC,
				catWorkEntity.getFail_Desc());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE,
				catWorkEntity.getPulling_Wire_Type());
		values.put(
				Supervision_Bts_Cat_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
				catWorkEntity.getCat_Supervision_Constr_Work_Id());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_ID,
				catWorkEntity.getSupervision_Bts_Id());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN,
				catWorkEntity.getDimension_Design());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL,
				catWorkEntity.getDimension_Real());

		values.put(Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
				catWorkEntity.getSync_Status());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
				catWorkEntity.getIsActive());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
				catWorkEntity.getProcessId());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID,
				catWorkEntity.getEmployeeId());

		// Inserting Row
		db.insert(Supervision_Bts_Cat_WorkField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void updateBtsCatWorkEntity(
			Supervision_Bts_Cat_WorkEntity catWorkEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_STATUS,
				catWorkEntity.getStatus());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC,
				catWorkEntity.getFail_Desc());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE,
				catWorkEntity.getPulling_Wire_Type());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN,
				catWorkEntity.getDimension_Design());
		values.put(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL,
				catWorkEntity.getDimension_Real());

		values.put(Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
				catWorkEntity.getSync_Status());
		// values.put(Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
		// catWorkEntity.getIsActive());
		// values.put(Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
		// catWorkEntity.getProcessId());

		// Updatting Row
		String[] sqlPara = new String[] { String.valueOf(catWorkEntity
				.getSupervision_Bts_Cat_Work_Id()) };
		int index = db.update(
				Supervision_Bts_Cat_WorkField.TABLE_NAME,
				values,
				Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}
	
	public void deleteAccept(
			Supervision_LBG_UnqualifyItemEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Acceptance_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String[] sqlPara = new String[] { String.valueOf(pDelItem.getCause_Line_Bg_Id()) };

		// Update Row
		db.update(Acceptance_Bts_Cat_WorkField.TABLE_NAME, values,
				Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID
						+ "= ? ", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}
}
