package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.Acceptance_Line_Bg_TankField;

public class Acceptance_Line_Bg_TankController {
	public static final String[] allColumn = new String[] {
		Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID,
		Acceptance_Line_Bg_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
		Acceptance_Line_Bg_TankField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
		Acceptance_Line_Bg_TankField.COLUMN_SYNC_STATUS,
		Acceptance_Line_Bg_TankField.COLUMN_IS_ACTIVE,
		Acceptance_Line_Bg_TankField.COLUMN_PROCESS_ID,
		Acceptance_Line_Bg_TankField.COLUMN_EMPLOYEE_ID};
	
	@SuppressWarnings("unused")
	private Context mContext = null;
	
	public static final String CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Acceptance_Line_Bg_TankField.TABLE_NAME
			+ "("
			+ Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID
			+ " TEXT PRIMARY KEY,"
			+ Acceptance_Line_Bg_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID
			+ " TEXT,"
			+ Acceptance_Line_Bg_TankField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " INTEGER,"
			+ Acceptance_Line_Bg_TankField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Acceptance_Line_Bg_TankField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Acceptance_Line_Bg_TankField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Acceptance_Line_Bg_TankField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Acceptance_Line_Bg_TankController(Context pContext) {
		this.mContext = pContext;
	}
}
