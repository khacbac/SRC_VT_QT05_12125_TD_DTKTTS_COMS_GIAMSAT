package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;

/**
 * @author cuongdk3
 *
 */
public class Acceptance_Bts_Cat_WorkController {
	
	public static final String[] allColumn = new String[] {
		Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,
		Acceptance_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
		Acceptance_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
		Acceptance_Bts_Cat_WorkField.COLUMN_TYPE,
		Acceptance_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
		Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
		Acceptance_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
		Acceptance_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID};
	
	@SuppressWarnings("unused")
	private Context mContext = null;
	
	public static final String CREATE_ACCEPTANCE_BTS_CAT_WORK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Acceptance_Bts_Cat_WorkField.TABLE_NAME
			+ "("
			+ Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID
			+ " TEXT PRIMARY KEY,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
			+ " TEXT,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " INTEGER,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_TYPE
			+ " TEXT,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Acceptance_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Acceptance_Bts_Cat_WorkController(Context pContext) {
		this.mContext = pContext;
	}
}
