package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Cat_Plan_For_Constr_Gsct;
import com.viettel.database.entity.Cat_Plan_For_Constr_Gsct;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Plan_For_ConstrField;
import com.viettel.database.field.Cat_Plan_For_ConstrField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Plan_For_ConstrControler {
    private Context mContext = null;
    private static final String TAG = "Cat_Milestone_ConstrControler";

    public Cat_Plan_For_ConstrControler(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[]{
            Cat_Plan_For_ConstrField.PLAN_ID,
            Cat_Plan_For_ConstrField.PLAN_CODE,
            Cat_Plan_For_ConstrField.PLAN_NAME,
            Cat_Plan_For_ConstrField.CREATED_DATE,
            Cat_Plan_For_ConstrField.START_DATE,
            Cat_Plan_For_ConstrField.END_DATE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_IS_ACTIVE
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Plan_For_ConstrField.TABLE_NAME
            + "("
            + Cat_Plan_For_ConstrField.PLAN_ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Plan_For_ConstrField.PLAN_CODE
            + " TEXT,"
            + Cat_Plan_For_ConstrField.PLAN_NAME
            + " CREATED_DATE,"
            + Cat_Plan_For_ConstrField.CREATED_DATE
            + " TEXT,"
            + Cat_Plan_For_ConstrField.START_DATE
            + " TEXT,"
            + Cat_Plan_For_ConstrField.END_DATE
            + " TEXT,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + BaseField.COLUMN_IS_ACTIVE
            + " INTEGER"
            + " )";

    public ArrayList<Cat_Plan_For_Constr_Gsct> getItem(long id) {
//        Log.e(TAG, "getItem: " + constructType );
        ArrayList<Cat_Plan_For_Constr_Gsct> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Plan_For_ConstrField.TABLE_NAME, allColumn,
                        Cat_Plan_For_ConstrField.PLAN_ID + "=?",
                        new String[]{String.valueOf(id)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Plan_For_Constr_Gsct curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Cat_Plan_For_Constr_Gsct converCursorToItem(Cursor cursor) {
        Cat_Plan_For_Constr_Gsct curItem = new Cat_Plan_For_Constr_Gsct();
        curItem.setId(cursor.getInt(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.PLAN_ID)));
        curItem.setPlanCode(cursor.getString(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.PLAN_CODE)));
        curItem.setPlanName(cursor.getString(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.PLAN_NAME)));
        curItem.setCreateDate(cursor.getString(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.CREATED_DATE)));
        curItem.setStartDate(cursor.getString(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.START_DATE)));
        curItem.setEndDate(cursor.getString(cursor
                .getColumnIndex(Cat_Plan_For_ConstrField.END_DATE)));
        return curItem;
    }
}
