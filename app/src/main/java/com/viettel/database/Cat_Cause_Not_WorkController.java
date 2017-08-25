package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Cause_Not_WorkField;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Cause_Not_WorkController {
    private Context mContext = null;

    public Cat_Cause_Not_WorkController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Cat_Cause_Not_WorkField.CAT_CAUSE_NOT_WORK_ID,
            Cat_Cause_Not_WorkField.NAME,
            Cat_Cause_Not_WorkField.CODE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Cat_Cause_Not_WorkField.COLUMN_IS_ACTIVE,
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Cause_Not_WorkField.TABLE_NAME
            + "("
            + Cat_Cause_Not_WorkField.CAT_CAUSE_NOT_WORK_ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Cause_Not_WorkField.NAME
            + " TEXT,"
            + Cat_Cause_Not_WorkField.CODE
            + " TEXT,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Cat_Cause_Not_WorkField.COLUMN_IS_ACTIVE
            + " INTEGER"
            + " )";
}
