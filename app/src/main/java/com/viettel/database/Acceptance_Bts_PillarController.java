package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.Acceptance_Bts_PillarField;

/**
 * @author cuongdk3
 *
 */
public class Acceptance_Bts_PillarController {
	public static final String[] allColumn = new String[] {
		Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
		Acceptance_Bts_PillarField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
		Acceptance_Bts_PillarField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
		Acceptance_Bts_PillarField.COLUMN_SYNC_STATUS,
		Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE,
		Acceptance_Bts_PillarField.COLUMN_PROCESS_ID,
		Acceptance_Bts_PillarField.COLUMN_EMPLOYEE_ID};
	
	@SuppressWarnings("unused")
	private Context mContext = null;
	
	public static final String CREATE_ACCEPTANCE_BTS_PILLAR_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Acceptance_Bts_PillarField.TABLE_NAME
			+ "("
			+ Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID
			+ " TEXT PRIMARY KEY,"
			+ Acceptance_Bts_PillarField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
			+ " TEXT,"
			+ Acceptance_Bts_PillarField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " INTEGER,"
			+ Acceptance_Bts_PillarField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Acceptance_Bts_PillarField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Acceptance_Bts_PillarField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Acceptance_Bts_PillarController(Context pContext) {
		this.mContext = pContext;
	}
}
