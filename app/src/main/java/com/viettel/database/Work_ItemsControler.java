package com.viettel.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Work_ItemsField;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Work_ItemsControler {
    private static Context mContext = null;
    private static final String TAG = "Work_ItemsControler";
    private static Work_ItemsControler work_itemsControler;

    public Work_ItemsControler(Context pContext) {
        mContext = pContext;
    }

    public static Work_ItemsControler getInstance(Context context) {
        mContext = context;
        if (work_itemsControler == null) {
            work_itemsControler = new Work_ItemsControler(mContext);
        }
        return work_itemsControler;
    }

    public static final String[] allColumn = new String[]{
            Work_ItemsField.WORK_ITEM_ID,
            Work_ItemsField.ITEM_TYPE_ID,
            Work_ItemsField.WORK_ITEM_CODE,
            Work_ItemsField.WORK_ITEM_NAME,
            Work_ItemsField.WORK_ITEM_ADDRESS,
            Work_ItemsField.CONSTRUCTOR_ID,
            Work_ItemsField.SUPERVISOR_ID,
            Work_ItemsField.UPDATE_DATE,
            Work_ItemsField.STARTING_DATE,
            Work_ItemsField.COMPLETE_DATE,
            Work_ItemsField.CONSTR_ID,
            Work_ItemsField.CONTRACT_ID,
            Work_ItemsField.STATUS,
            Work_ItemsField.REAL_COMPLETE_DATE,
            Work_ItemsField.START_BY,
            Work_ItemsField.UPDATE_STATUS_BY,
            Work_ItemsField.ACCEPT_BY,
            Work_ItemsField.STATUS_ID,
            Work_ItemsField.WORK_ITEM_TYPE_NAME,
            Work_ItemsField.CONSTR_TYPE,
            BaseField.COLUMN_IS_ACTIVE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Work_ItemsField.ACCEPT_NOTE_CODE
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Work_ItemsField.TABLE_NAME
            + "("
            + Work_ItemsField.WORK_ITEM_ID
            + " INTEGER PRIMARY KEY,"
            + Work_ItemsField.WORK_ITEM_NAME
            + " TEXT,"
            + Work_ItemsField.WORK_ITEM_TYPE_NAME
            + " TEXT,"
            + Work_ItemsField.WORK_ITEM_ADDRESS
            + " TEXT,"
            + Work_ItemsField.UPDATE_DATE
            + " TEXT,"
            + Work_ItemsField.STARTING_DATE
            + " TEXT,"
            + Work_ItemsField.COMPLETE_DATE
            + " TEXT,"
            + Work_ItemsField.CONSTR_ID
            + " TEXT,"
            + Work_ItemsField.STATUS_ID
            + " INTEGER,"
            + Work_ItemsField.ITEM_TYPE_ID
            + " INTEGER,"
            + Work_ItemsField.CONSTR_TYPE
            + " INTEGER,"
            + Work_ItemsField.CONTRACT_ID
            + " TEXT,"
            + Work_ItemsField.STATUS
            + " INTEGER,"
            + Work_ItemsField.REAL_COMPLETE_DATE
            + " TEXT,"
            + Work_ItemsField.START_BY
            + " INTEGER,"
            + Work_ItemsField.UPDATE_STATUS_BY
            + " INTEGER,"
            + Work_ItemsField.ACCEPT_BY
            + " INTEGER,"
            + Work_ItemsField.WORK_ITEM_CODE
            + " TEXT,"
            + Work_ItemsField.CONSTRUCTOR_ID
            + " INTEGER,"
            + Work_ItemsField.SUPERVISOR_ID
            + " INTEGER,"
            + Work_ItemsField.COLUMN_RECENT_CHECK
            + " TEXT,"
            + Work_ItemsField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Work_ItemsField.COLUMN_CONSTRUCT_ID
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Work_ItemsField.ACCEPT_NOTE_CODE
            + " TEXT"
            + " )";


    public ArrayList<Work_ItemsEntity> getItems(long constructId) {
        Log.e(TAG, "getItems: " + constructId);
        ArrayList<Work_ItemsEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Work_ItemsField.TABLE_NAME, allColumn, Work_ItemsField.CONSTR_ID + "=? AND " + Work_ItemsField.COLUMN_IS_ACTIVE + "= 1", new String[]{String.valueOf(constructId)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Work_ItemsEntity curItem = this.converCursorToItem(cursor);
                Log.d(TAG, "getItems: From Controler - Name = " + curItem.getWork_item_name());
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<Work_ItemsEntity> getListWorkTest(long constrId) {
        ArrayList<Work_ItemsEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from work_items where work_items.constr_id = " + constrId;
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Work_ItemsEntity curItem = this.converCursorToItem(cursor);
                Log.d(TAG, "getItems: From Controler - Name = " + curItem.getWork_item_name());
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<String> getListColumn() {
        ArrayList<String> allColums = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Work_ItemsField.TABLE_NAME, null, null, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        Collections.addAll(allColums, columns);
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return allColums;
    }

    public Work_ItemsEntity getWorkByCatTest(long catId, long constrId) {
        Work_ItemsEntity ret = null;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from work_items where work_items.item_type_id = "+catId+" and work_items.constr_id = "+constrId;
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor.moveToFirst()) {
            ret = this.converCursorToItem(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public Work_ItemsEntity getItem(long constructId, long cat_work_item_id) {
        Work_ItemsEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Work_ItemsField.TABLE_NAME, allColumn, Work_ItemsField.CONSTR_ID + "=? AND " + Work_ItemsField.ITEM_TYPE_ID + "=?", new String[]{String.valueOf(constructId), String.valueOf(cat_work_item_id)}, null, null, null, null);
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
    public Work_ItemsEntity getItem(long cat_work_item_id) {
//        Log.e(TAG, "getItem: " + constructId + " " + cat_work_item_id );
        Work_ItemsEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from work_items where item_type_id = " + cat_work_item_id;
        Cursor cursor = db.rawQuery(strQuery,null);
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

    public boolean addItem(Work_ItemsEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        // Inserting Row
        db.insert(Work_ItemsField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    public boolean updateItem(Work_ItemsEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);
        String sqlclause = Work_ItemsField.WORK_ITEM_ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(addItem.getId())};
        db.update(Work_ItemsField.TABLE_NAME, values, sqlclause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    private ContentValues convertItemToValues(Work_ItemsEntity addItem) {
        ContentValues values = new ContentValues();

        values.put(Work_ItemsField.WORK_ITEM_ID, addItem.getId());
        values.put(Work_ItemsField.WORK_ITEM_NAME, addItem.getWork_item_name());
        values.put(Work_ItemsField.CONSTR_ID, addItem.getConstr_id());
        values.put(Work_ItemsField.ITEM_TYPE_ID, addItem.getItem_type_id());
        values.put(Work_ItemsField.STARTING_DATE, addItem.getStarting_date());
        values.put(Work_ItemsField.UPDATE_DATE, addItem.getUpdate_date());
        values.put(Work_ItemsField.COMPLETE_DATE, addItem.getComplete_date());
        values.put(Work_ItemsField.STATUS, addItem.getStatus());
        values.put(Work_ItemsField.STATUS_ID, addItem.getStatus_id());
        values.put(Work_ItemsField.ACCEPT_NOTE_CODE, addItem.getAccept_note_code());
        values.put(Work_ItemsField.COLUMN_PROCESS_ID, addItem.getProcessId());
        values.put(Work_ItemsField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
        values.put(Work_ItemsField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(Work_ItemsField.COLUMN_IS_ACTIVE, addItem.getIsActive());
        values.put(Work_ItemsField.COLUMN_RECENT_CHECK, addItem.getRecentCheck());
        return values;
    }


    private Work_ItemsEntity converCursorToItem(Cursor cursor) {
        Work_ItemsEntity curItem = new Work_ItemsEntity();
        try {
            curItem.setId(cursor.getLong(cursor
                    .getColumnIndex(Work_ItemsField.WORK_ITEM_ID)));
            curItem.setWork_item_name(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.WORK_ITEM_NAME)));
            curItem.setConstr_id(cursor.getLong(cursor
                    .getColumnIndex(Work_ItemsField.CONSTR_ID)));
            curItem.setItem_type_id(cursor.getLong(cursor
                    .getColumnIndex(Work_ItemsField.ITEM_TYPE_ID)));
            curItem.setStarting_date(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.STARTING_DATE)));
            curItem.setUpdate_date(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.UPDATE_DATE)));
            curItem.setComplete_date(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.COMPLETE_DATE)));
            curItem.setStatus(cursor.getInt(cursor
                    .getColumnIndex(Work_ItemsField.STATUS)));
            curItem.setStatus_id(cursor.getInt(cursor
                    .getColumnIndex(Work_ItemsField.STATUS_ID)));
            curItem.setAccept_note_code(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.ACCEPT_NOTE_CODE)));
            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Work_ItemsField.COLUMN_PROCESS_ID)));
            curItem.setEmployeeId(cursor.getLong(cursor
                    .getColumnIndex(Work_ItemsField.COLUMN_EMPLOYEE_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Work_ItemsField.COLUMN_SYNC_STATUS)));
            curItem.setIsActive(cursor.getInt(cursor
                    .getColumnIndex(Work_ItemsField.COLUMN_IS_ACTIVE)));
            curItem.setRecentCheck(cursor.getString(cursor
                    .getColumnIndex(Work_ItemsField.COLUMN_RECENT_CHECK)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
}
