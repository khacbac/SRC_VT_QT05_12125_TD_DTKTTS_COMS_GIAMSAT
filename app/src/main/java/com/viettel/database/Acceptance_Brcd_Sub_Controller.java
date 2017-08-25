package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.Acceptance_Brcd_Sub_Field;

public class Acceptance_Brcd_Sub_Controller {
	public static final String[] allColumn = new String[] {
		Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID,
		Acceptance_Brcd_Sub_Field.COLUMN_SUPERVISION_SUB_ID,
		Acceptance_Brcd_Sub_Field.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
		Acceptance_Brcd_Sub_Field.COLUMN_SYNC_STATUS,
		Acceptance_Brcd_Sub_Field.COLUMN_IS_ACTIVE,
		Acceptance_Brcd_Sub_Field.COLUMN_PROCESS_ID,
		Acceptance_Brcd_Sub_Field.COLUMN_EMPLOYEE_ID};
	
	@SuppressWarnings("unused")
	private Context mContext = null;
	
	public static final String CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Acceptance_Brcd_Sub_Field.TABLE_NAME
			+ "("
			+ Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID
			+ " TEXT PRIMARY KEY,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_SUPERVISION_SUB_ID
			+ " TEXT,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " INTEGER,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Acceptance_Brcd_Sub_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Acceptance_Brcd_Sub_Controller(Context pContext) {
		this.mContext = pContext;
	}
}
