package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Cat_Constr_TeamEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Constr_TeamField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Constr_TeamController {
    private Context mContext = null;

    public Cat_Constr_TeamController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Cat_Constr_TeamField.CAT_CONSTR_TEAM_ID,
            Cat_Constr_TeamField.NAME,
            Cat_Constr_TeamField.CODE,
            Cat_Constr_TeamField.CONSTR_TYPE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Cat_Constr_TeamField.COLUMN_IS_ACTIVE
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Constr_TeamField.TABLE_NAME
            + "("
            + Cat_Constr_TeamField.CAT_CONSTR_TEAM_ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Constr_TeamField.NAME
            + " TEXT,"
            + Cat_Constr_TeamField.CODE
            + " TEXT,"
            + Cat_Constr_TeamField.CONSTR_TYPE
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Cat_Constr_TeamField.COLUMN_IS_ACTIVE
            + " INTEGER"
            + " )";

    public ArrayList<Cat_Constr_TeamEntity> getCates(long constructType) {
        ArrayList<Cat_Constr_TeamEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Constr_TeamField.TABLE_NAME, allColumn,
                        Cat_Constr_TeamField.CONSTR_TYPE + "=?",
                        new String[] { String.valueOf(constructType)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Constr_TeamEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public Cat_Constr_TeamEntity getItem(long id) {
        Cat_Constr_TeamEntity ret = null;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Constr_TeamField.TABLE_NAME, allColumn,
                        Cat_Constr_TeamField.CAT_CONSTR_TEAM_ID + "=?",
                        new String[] { String.valueOf(id)}, null, null,
                        null, null);
        cursor.moveToFirst();
        ret = this.converCursorToItem(cursor);
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Cat_Constr_TeamEntity converCursorToItem(Cursor cursor) {
        Cat_Constr_TeamEntity curItem = new Cat_Constr_TeamEntity();
        curItem.setCat_constr_team_id(cursor.getLong(cursor
                .getColumnIndex(Cat_Constr_TeamField.CAT_CONSTR_TEAM_ID)));
        curItem.setName(cursor.getString(cursor
                .getColumnIndex(Cat_Constr_TeamField.NAME)));
        curItem.setConstr_type(cursor.getLong(cursor
                .getColumnIndex(Cat_Constr_TeamField.CONSTR_TYPE)));
        curItem.setCode(cursor.getString(cursor
                .getColumnIndex(Cat_Constr_TeamField.CODE)));
        return curItem;
    }
}
