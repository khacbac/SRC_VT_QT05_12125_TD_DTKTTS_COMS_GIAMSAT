package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Cat_Milestone_ConstrEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Milestone_ConstrField;
import com.viettel.database.field.Cat_Work_Item_TypesField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Milestone_ConstrControler {
    private Context mContext = null;
    private static final String TAG = "Cat_Milestone_ConstrControler";

    public Cat_Milestone_ConstrControler(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Cat_Milestone_ConstrField.CAT_MILESTONE_CONSTR_ID,
            Cat_Milestone_ConstrField.NAME,
            Cat_Milestone_ConstrField.CODE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_IS_ACTIVE,
            BaseField.COLUMN_EMPLOYEE_ID
             };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Milestone_ConstrField.TABLE_NAME
            + "("
            + Cat_Milestone_ConstrField.CAT_MILESTONE_CONSTR_ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Milestone_ConstrField.NAME
            + " TEXT,"
            + Cat_Milestone_ConstrField.CODE
            + " TEXT,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + BaseField.COLUMN_IS_ACTIVE
            + " INTEGER"

            + " )";

    public ArrayList<Cat_Milestone_ConstrEntity> getItem(long id) {
//        Log.e(TAG, "getItem: " + constructType );
        ArrayList<Cat_Milestone_ConstrEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Milestone_ConstrField.TABLE_NAME, allColumn,
                        Cat_Milestone_ConstrField.CAT_MILESTONE_CONSTR_ID + "=?",
                        new String[] { String.valueOf(id)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Milestone_ConstrEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Cat_Milestone_ConstrEntity converCursorToItem(Cursor cursor) {
        Cat_Milestone_ConstrEntity curItem = new Cat_Milestone_ConstrEntity();
        curItem.setId(cursor.getInt(cursor
                .getColumnIndex(Cat_Milestone_ConstrField.CAT_MILESTONE_CONSTR_ID)));
        curItem.setName(cursor.getString(cursor
                .getColumnIndex(Cat_Milestone_ConstrField.NAME)));
        curItem.setCode(cursor.getString(cursor
                .getColumnIndex(Cat_Milestone_ConstrField.CODE)));
        return curItem;
    }
}
