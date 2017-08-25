package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Sub_Work_ItemField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Sub_Work_ItemControler {
    private static final String TAG = "Cat_Sub_Work_ItemContro";
    private Context mContext = null;

    public Cat_Sub_Work_ItemControler(Context pContext) {
        this.mContext = pContext;
    }


    public static final String[] allColumn = new String[] {
            Cat_Sub_Work_ItemField.ID,
            Cat_Sub_Work_ItemField.CAT_WORK_ITEM_TYPE_ID,
            Cat_Sub_Work_ItemField.NAME,
            Cat_Sub_Work_ItemField.CODE,
            Cat_Sub_Work_ItemField.DESCRIPTION,
            Cat_Sub_Work_ItemField.UNIT_NAME,
            Cat_Sub_Work_ItemField.ORDER_NUM,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_IS_ACTIVE
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Sub_Work_ItemField.TABLE_NAME
            + "("
            + Cat_Sub_Work_ItemField.ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Sub_Work_ItemField.CAT_WORK_ITEM_TYPE_ID
            + " INTEGER,"
            + Cat_Sub_Work_ItemField.NAME
            + " TEXT,"
            + Cat_Sub_Work_ItemField.CODE
            + " TEXT,"
            + Cat_Sub_Work_ItemField.DESCRIPTION
            + " TEXT,"
            + Cat_Sub_Work_ItemField.UNIT_NAME
            + " TEXT,"
            + Cat_Sub_Work_ItemField.ORDER_NUM
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + BaseField.COLUMN_IS_ACTIVE
            + " INTEGER"
            + " )";

    public Cat_Sub_Work_ItemEntity getCatSubWorkItem(long catSubWorkItemId) {
//        Log.e(TAG, "getCatSubWorkItem() called with: catSubWorkItemId = [" + catSubWorkItemId + "]");
        Cat_Sub_Work_ItemEntity ret = null;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Sub_Work_ItemField.TABLE_NAME, allColumn,
                        Cat_Sub_Work_ItemField.ID + "=?",
                        new String[] { String.valueOf(catSubWorkItemId)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
             ret = this.converCursorToItem(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<Cat_Sub_Work_ItemEntity> getsubCates(long catWorkItemID) {
        ArrayList<Cat_Sub_Work_ItemEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Sub_Work_ItemField.TABLE_NAME, allColumn,
                        Cat_Sub_Work_ItemField.CAT_WORK_ITEM_TYPE_ID + "=? AND "
                        + Cat_Sub_Work_ItemField.COLUMN_IS_ACTIVE + "=1",
                        new String[] { String.valueOf(catWorkItemID)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Sub_Work_ItemEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Cat_Sub_Work_ItemEntity converCursorToItem(Cursor cursor) {
        Cat_Sub_Work_ItemEntity curItem = new Cat_Sub_Work_ItemEntity();
        curItem.setId(cursor.getLong(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.ID)));
        curItem.setCat_work_item_type_id(cursor.getLong(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.CAT_WORK_ITEM_TYPE_ID)));
        curItem.setName(cursor.getString(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.NAME)));
        curItem.setCode(cursor.getString(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.CODE)));
        curItem.setUnitName(cursor.getString(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.UNIT_NAME)));
        curItem.setIs_active(cursor.getInt(cursor
                .getColumnIndex(Cat_Sub_Work_ItemField.COLUMN_IS_ACTIVE)));
        return curItem;
    }
}
