package com.viettel.database.field;

import android.content.Context;

/**
 * Created by doanLV4 on 10/5/2017.
 */

public class ConstrNodeDesignController implements BaseField {
    private static final String TAG = ConstrNodeDesignController.class.getSimpleName();
    private Context mContext = null;

    public ConstrNodeDesignController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[]{
            ConstrNodeDesignItemsField.CONSTR_NODE_DESIGN_ID,
            ConstrNodeDesignItemsField.CONSTR_NODE_ID,
            ConstrNodeDesignItemsField.CAT_NODE_ITEM_DESIGN_ID,
            ConstrNodeDesignItemsField.NUM_ITEM,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_IS_ACTIVE,
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + ConstrNodeDesignItemsField.TABLE_NAME
            + "("
            + ConstrNodeDesignItemsField.CONSTR_NODE_DESIGN_ID
            + " INTEGER PRIMARY KEY,"
            + ConstrNodeDesignItemsField.CONSTR_NODE_ID
            + " INTEGER,"
            + ConstrNodeDesignItemsField.CAT_NODE_ITEM_DESIGN_ID
            + " INTEGER,"
            + ConstrNodeDesignItemsField.NUM_ITEM
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + BaseField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Sub_Work_ItemField.CAT_SUB_WORK_ITEM_ID
            + " INTEGER"
            + " )";
}