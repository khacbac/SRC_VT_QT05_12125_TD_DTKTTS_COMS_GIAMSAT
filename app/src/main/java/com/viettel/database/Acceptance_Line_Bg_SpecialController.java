package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.Acceptance_Line_Bg_SpecialField;

public class Acceptance_Line_Bg_SpecialController {
	public static final String[] allColumn = new String[] {
		Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID,
		Acceptance_Line_Bg_SpecialField.COLUMN_SPECIAL_LOCATION_ID,
		Acceptance_Line_Bg_SpecialField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
		Acceptance_Line_Bg_SpecialField.COLUMN_SYNC_STATUS,
		Acceptance_Line_Bg_SpecialField.COLUMN_IS_ACTIVE,
		Acceptance_Line_Bg_SpecialField.COLUMN_PROCESS_ID,
		Acceptance_Line_Bg_SpecialField.COLUMN_EMPLOYEE_ID};
	
	@SuppressWarnings("unused")
	private Context mContext = null;
	
	public static final String CREATE_ACCEPTANCE_LINE_BG_SPECIAL_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Acceptance_Line_Bg_SpecialField.TABLE_NAME
			+ "("
			+ Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID
			+ " TEXT PRIMARY KEY,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_SPECIAL_LOCATION_ID
			+ " TEXT,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID
			+ " INTEGER,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Acceptance_Line_Bg_SpecialField.COLUMN_EMPLOYEE_ID
			+ " TEXT)";

	public Acceptance_Line_Bg_SpecialController(Context pContext) {
		this.mContext = pContext;
	}
}
