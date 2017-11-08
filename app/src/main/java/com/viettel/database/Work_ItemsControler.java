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
//            Work_ItemsField.ENGINEERING_COST,
//            Work_ItemsField.DEVICE_COST,
//            Work_ItemsField.OTHER_COST,
//            Work_ItemsField.CONTINGENCY_COST,
//            Work_ItemsField.INVEST_PROJECT_ID,
            Work_ItemsField.CONSTR_ID,
            Work_ItemsField.CONTRACT_ID,
//            Work_ItemsField.CONSTR_FORM,
//            Work_ItemsField.SUPER_FORM,
            Work_ItemsField.STATUS,
            Work_ItemsField.REAL_COMPLETE_DATE,

//            Work_ItemsField.TOTAL_REAL_COST,
//            Work_ItemsField.LIQUIDATE_COST,
//            Work_ItemsField.LIQUIDATE_DATE,
            Work_ItemsField.START_BY,
            Work_ItemsField.UPDATE_STATUS_BY,
            Work_ItemsField.ACCEPT_BY,
//            Work_ItemsField.NORMS_ID,
            Work_ItemsField.STATUS_ID,
            Work_ItemsField.WORK_ITEM_TYPE_NAME,
            Work_ItemsField.CONSTR_TYPE,
//            Work_ItemsField.CABLE_TYPE,
//            Work_ItemsField.TOTAL_WORK,
//            Work_ItemsField.CURRENT_WORK,
//            Work_ItemsField.MATERIAL_TYPE,
//            Work_ItemsField.CONSTR_DESIGN_ID,
//            Work_ItemsField.PROVINCE_ID,
            BaseField.COLUMN_IS_ACTIVE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Work_ItemsField.ACCEPT_NOTE_CODE/*,
            Work_ItemsField.CONSTR_NODE_ID*/
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
//            + Work_ItemsField.ENGINEERING_COST
//            + " INTEGER,"
//            + Work_ItemsField.DEVICE_COST
//            + " INTEGER,"
//            + Work_ItemsField.OTHER_COST
//            + " INTEGER,"
//            + Work_ItemsField.CONTINGENCY_COST
//            + " INTEGER,"
//            + Work_ItemsField.INVEST_PROJECT_ID
//            + " INTEGER,"
            + Work_ItemsField.CONTRACT_ID
            + " TEXT,"
            + Work_ItemsField.STATUS
            + " INTEGER,"
            + Work_ItemsField.REAL_COMPLETE_DATE
            + " TEXT,"
//            + Work_ItemsField.TOTAL_REAL_COST
//            + " INTEGER,"
//            + Work_ItemsField.LIQUIDATE_COST
//            + " INTEGER,"
//            + Work_ItemsField.LIQUIDATE_DATE
//            + " TEXT,"
            + Work_ItemsField.START_BY
            + " INTEGER,"
            + Work_ItemsField.UPDATE_STATUS_BY
            + " INTEGER,"
            + Work_ItemsField.ACCEPT_BY
            + " INTEGER,"
//            + Work_ItemsField.NORMS_ID
//            + " INTEGER,"
            + Work_ItemsField.WORK_ITEM_CODE
            + " TEXT,"
//            + Work_ItemsField.CONSTR_FORM
//            + " INTEGER,"
//            + Work_ItemsField.SUPER_FORM
//            + " INTEGER,"
            + Work_ItemsField.CONSTRUCTOR_ID
            + " INTEGER,"
            + Work_ItemsField.SUPERVISOR_ID
            + " INTEGER,"
//            + Work_ItemsField.CABLE_TYPE
//            + " TEXT,"
//            + Work_ItemsField.TOTAL_WORK
//            + " INTEGER,"
//            + Work_ItemsField.CURRENT_WORK
//            + " INTEGER,"
//            + Work_ItemsField.MATERIAL_TYPE
//            + " INTEGER,"
//            + Work_ItemsField.CONSTR_DESIGN_ID
//            + " INTEGER,"
//            + Work_ItemsField.PROVINCE_ID
//            + " INTEGER,"
            + Work_ItemsField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            /*+ Work_ItemsField.CONSTR_NODE_ID
            + " INTEGER,"*/
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
                .query(Work_ItemsField.TABLE_NAME, allColumn,
                        Work_ItemsField.CONSTR_ID + "=? AND "
                                + Work_ItemsField.COLUMN_IS_ACTIVE + "= 1",
                        new String[]{String.valueOf(constructId)}, null, null,
                        null, null);
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

    public ArrayList<Work_ItemsEntity> getWItemsByNode(int constrIdFromNode) {
        ArrayList<Work_ItemsEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from WORK_ITEMS where CONSTR_ID = " + constrIdFromNode;
        @SuppressLint("Recycle")
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

    public ArrayList<String> getAllWorkItemName() {
        ArrayList<String> allItemName = new ArrayList<>();
        String query = "select WORK_ITEMS.WORK_ITEM_NAME from WORK_ITEMS INNER JOIN CONSTR_CONSTRUCTIONS on WORK_ITEMS.CONSTR_ID = CONSTR_CONSTRUCTIONS.CONSTRUCT_ID ;";
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        @SuppressLint("Recycle")
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                String worlName = cursor.getString(0);
                allItemName.add(worlName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return allItemName;

    }

    public ArrayList<Work_ItemsEntity> getItemsByCatWorkItemType(long itemTypeId) {
        ArrayList<Work_ItemsEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Work_ItemsField.TABLE_NAME, allColumn,
                        Work_ItemsField.ITEM_TYPE_ID + "=? AND "
                                + Work_ItemsField.COLUMN_IS_ACTIVE + "= 1",
                        new String[]{String.valueOf(itemTypeId)}, null, null,
                        null, null);
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
        Cursor cursor = db
                .query(Work_ItemsField.TABLE_NAME, null, null, null, null, null, null);
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
//        Log.e(TAG, "getItem: " + constructId + " " + cat_work_item_id );
        Work_ItemsEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Work_ItemsField.TABLE_NAME, allColumn,
                        Work_ItemsField.CONSTR_ID + "=? AND "
                                + Work_ItemsField.ITEM_TYPE_ID + "=?",
                        new String[]{String.valueOf(constructId),
                                String.valueOf(cat_work_item_id)}, null, null,
                        null, null);
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

        String sqlclause = Work_ItemsField.WORK_ITEM_ID
                + "= ?";
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
//        values.put(Work_ItemsField.CONSTR_NODE_ID, addItem.getNodeID());
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
//            curItem.setNodeID(cursor.getLong(cursor
//                    .getColumnIndex(Work_ItemsField.CONSTR_NODE_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
}
