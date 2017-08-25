package com.viettel.database;

import android.content.Context;

import com.viettel.database.field.BaseField;
import com.viettel.database.field.Work_Items_ValueField;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Work_Items_ValueControler {
    private Context mContext = null;

    public Work_Items_ValueControler(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Work_Items_ValueField.WORK_ITEMS_VALUE_ID,
            Work_Items_ValueField.UPDATE_DATE,
            Work_Items_ValueField.VALUE,
            Work_Items_ValueField.WORK_ITEMS_ID,
            Work_Items_ValueField.CAT_WORK_ITEM_ID,
            Work_Items_ValueField.UPDATE_WEEK,
            Work_Items_ValueField.CONSTRUCT_ID,
            Work_Items_ValueField.UPDATE_MONTH,
            Work_Items_ValueField.UPDATE_YEAR,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Work_Items_ValueField.WORK_ITEM_ID
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Work_Items_ValueField.TABLE_NAME
            + "("
            + Work_Items_ValueField.WORK_ITEMS_VALUE_ID
            + " INTEGER PRIMARY KEY,"
            + Work_Items_ValueField.UPDATE_DATE
            + " TEXT,"
            + Work_Items_ValueField.VALUE
            + " TEXT,"
            + Work_Items_ValueField.WORK_ITEMS_ID
            + " INTEGER,"
            + Work_Items_ValueField.CAT_WORK_ITEM_ID
            + " INTEGER,"
            + Work_Items_ValueField.UPDATE_WEEK
            + " INTEGER,"
            + Work_Items_ValueField.CONSTRUCT_ID
            + " INTEGER,"
            + Work_Items_ValueField.UPDATE_MONTH
            + " INTEGER,"
            + Work_Items_ValueField.UPDATE_YEAR
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Work_Items_ValueField.WORK_ITEM_ID
            + " INTEGER"
            + " )";
}
