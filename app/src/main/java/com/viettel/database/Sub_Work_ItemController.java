package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Sub_Work_ItemField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Sub_Work_ItemController {
    private static final String TAG = "Sub_Work_ItemController";
    private Context mContext = null;

    public Sub_Work_ItemController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[]{
            Sub_Work_ItemField.ID,
            Sub_Work_ItemField.FINISHED_DATE,
            Sub_Work_ItemField.WORK_ITEM_ID,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_IS_ACTIVE,
            Sub_Work_ItemField.CAT_SUB_WORK_ITEM_ID
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Sub_Work_ItemField.TABLE_NAME
            + "("
            + Sub_Work_ItemField.ID
            + " INTEGER PRIMARY KEY,"
            + Sub_Work_ItemField.FINISHED_DATE
            + " TEXT,"
            + Sub_Work_ItemField.WORK_ITEM_ID
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

    public ArrayList<Sub_Work_ItemEntity> getItems(long work_item_id) {
//        Log.e(TAG, "getItems: " + work_item_id );
        ArrayList<Sub_Work_ItemEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_ItemField.TABLE_NAME, allColumn,
                        Sub_Work_ItemField.WORK_ITEM_ID + "=?",
                        new String[]{String.valueOf(work_item_id)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Sub_Work_ItemEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<Sub_Work_ItemEntity> getListItems(long work_item_id) {
//        Log.e(TAG, "getItems: " + work_item_id );
        ArrayList<Sub_Work_ItemEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_ItemField.TABLE_NAME, allColumn,
                        Sub_Work_ItemField.WORK_ITEM_ID + "=? AND "
                        + Sub_Work_ItemField.COLUMN_IS_ACTIVE + "=1",
                        new String[]{String.valueOf(work_item_id)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Sub_Work_ItemEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public Sub_Work_ItemEntity getItem(long work_item_id, long cat_sub_work_item_id) {
        Log.e(TAG, "getItem: " + work_item_id + " " + cat_sub_work_item_id);
        Sub_Work_ItemEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_ItemField.TABLE_NAME, allColumn,
                        Sub_Work_ItemField.WORK_ITEM_ID + "=? AND "
                                + Sub_Work_ItemField.CAT_SUB_WORK_ITEM_ID + "=?",
                        new String[]{String.valueOf(work_item_id),
                                String.valueOf(cat_sub_work_item_id)}, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            curItem = this.converCursorToItem(cursor);
        } else {
            curItem = null;
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public boolean addItem(Sub_Work_ItemEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        // Inserting Row
        db.insert(Sub_Work_ItemField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    public boolean updateItem(Sub_Work_ItemEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        String sqlclause = Sub_Work_ItemField.ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(addItem.getId())};

        db.update(Sub_Work_ItemField.TABLE_NAME, values, sqlclause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    private ContentValues convertItemToValues(Sub_Work_ItemEntity addItem) {
        ContentValues values = new ContentValues();


        values.put(Sub_Work_ItemField.ID, addItem.getId());
        values.put(Sub_Work_ItemField.FINISHED_DATE, addItem.getFinishDate());
        values.put(Sub_Work_ItemField.WORK_ITEM_ID, addItem.getWork_item_id());
        values.put(Sub_Work_ItemField.CAT_SUB_WORK_ITEM_ID, addItem.getCat_sub_work_item_id());
        values.put(Sub_Work_ItemField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
        values.put(Sub_Work_ItemField.COLUMN_PROCESS_ID, addItem.getProcessId());
        values.put(Sub_Work_ItemField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(Sub_Work_ItemField.COLUMN_IS_ACTIVE, addItem.getIsActive());
        return values;
    }


    private Sub_Work_ItemEntity converCursorToItem(Cursor cursor) {
        Sub_Work_ItemEntity curItem = new Sub_Work_ItemEntity();
        try {
            curItem.setId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_ItemField.ID)));
            curItem.setFinishDate(cursor.getString(cursor
                    .getColumnIndex(Sub_Work_ItemField.FINISHED_DATE)));
            curItem.setWork_item_id(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_ItemField.WORK_ITEM_ID)));
            curItem.setCat_sub_work_item_id(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_ItemField.CAT_SUB_WORK_ITEM_ID)));
            curItem.setEmployeeId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_ItemField.COLUMN_EMPLOYEE_ID)));
            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_ItemField.COLUMN_PROCESS_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Sub_Work_ItemField.COLUMN_SYNC_STATUS)));
            curItem.setIsActive(cursor.getInt(cursor
                    .getColumnIndex(Sub_Work_ItemField.COLUMN_IS_ACTIVE)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
}
