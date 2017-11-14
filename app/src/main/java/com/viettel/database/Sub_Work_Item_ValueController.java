package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.gsct.utils.GSCTUtils;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Sub_Work_Item_ValueController {
    private static final String TAG = "Sub_Work_Item_ValueCont";
    private static Context mContext = null;
    private static Sub_Work_Item_ValueController swivController;

    public Sub_Work_Item_ValueController(Context pContext) {
        mContext = pContext;
    }

    public static Sub_Work_Item_ValueController getInstance (Context context) {
        mContext = context;
        if (swivController == null) {
            swivController = new Sub_Work_Item_ValueController(context);
        }
        return swivController;
    }

    public static final String[] allColumn = new String[] {
            Sub_Work_Item_ValueField.ID,
            Sub_Work_Item_ValueField.VALUE_2,
            Sub_Work_Item_ValueField.ADDED_DATE,
            Sub_Work_Item_ValueField.EXPECTATION_VALUE,
            Sub_Work_Item_ValueField.NOTE,
            Sub_Work_Item_ValueField.TEAM_NUMBER,
            Sub_Work_Item_ValueField.TOTAL_PROGRESS,
            Sub_Work_Item_ValueField.VALUE,
            Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_IS_ACTIVE,
            Sub_Work_Item_ValueField.WORK_ITEM_ID,
            Sub_Work_Item_ValueField.VALUE_ITEM,
            Sub_Work_Item_ValueField.CONSTR_NODE_ID
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Sub_Work_Item_ValueField.TABLE_NAME
            + "("
            + Sub_Work_Item_ValueField.ID
            + " INTEGER PRIMARY KEY,"
            + Sub_Work_Item_ValueField.VALUE_2
            + " TEXT,"
            + Sub_Work_Item_ValueField.ADDED_DATE
            + " TEXT,"
            + Sub_Work_Item_ValueField.EXPECTATION_VALUE
            + " TEXT,"
            + Sub_Work_Item_ValueField.NOTE
            + " TEXT,"
            + Sub_Work_Item_ValueField.TEAM_NUMBER
            + " INTEGER,"
            + Sub_Work_Item_ValueField.TOTAL_PROGRESS
            + " INTEGER,"
            + Sub_Work_Item_ValueField.VALUE
            + " REAL,"
            + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID
            + " INTEGER,"
            + BaseField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Sub_Work_Item_ValueField.WORK_ITEM_ID
            + " INTEGER,"
            + Sub_Work_Item_ValueField.CONSTR_NODE_ID
            + " INTEGER,"
            + Sub_Work_Item_ValueField.VALUE_ITEM
            + " REAL"
            + " )";

    public Sub_Work_Item_ValueEntity getItem(long work_item_id, long cat_sub_work_item_id) {
//        Log.e(TAG, "getItem: " + work_item_id + " " + cat_sub_work_item_id );
        Sub_Work_Item_ValueEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_Item_ValueField.TABLE_NAME, allColumn,
                        Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                                + Sub_Work_Item_ValueField.ADDED_DATE +  "=?",
                        new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id) , GSCTUtils.getDateNow()}, null, null,
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

    public Sub_Work_Item_ValueEntity getItemByNode(long work_item_id, long cat_sub_work_item_id, long nodeId) {
//        Log.e(TAG, "getItem: " + work_item_id + " " + cat_sub_work_item_id );
        Sub_Work_Item_ValueEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_Item_ValueField.TABLE_NAME, allColumn,
                        Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                                + Sub_Work_Item_ValueField.ADDED_DATE +  "=? AND "
                                + Sub_Work_Item_ValueField.CONSTR_NODE_ID + "=?",
                        new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id) , GSCTUtils.getDateNow(), String.valueOf(nodeId)}, null, null,
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

    // Truong hop dac biet,danh cho item do kiem.
    public Sub_Work_Item_ValueEntity getItemByNodeDoKiem(long work_item_id, long cat_sub_work_item_id, long nodeId) {
//        Log.e(TAG, "getItem: " + work_item_id + " " + cat_sub_work_item_id );
        Sub_Work_Item_ValueEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Sub_Work_Item_ValueField.TABLE_NAME, allColumn,
                        Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                                + Sub_Work_Item_ValueField.CONSTR_NODE_ID + "=?",
                        new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id) , String.valueOf(nodeId)}, null, null,
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

    // Doi voi truong hop la cac item cap nhat theo cong trinh.
    public double getLuyke(long work_item_id, long cat_sub_work_item_id) {
//        Log.e(TAG, "getLuyke: " + work_item_id + " " + cat_sub_work_item_id );
        double ret = 0L;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor  = db.rawQuery("select sum(" + Sub_Work_Item_ValueField.VALUE + ") from " + Sub_Work_Item_ValueField.TABLE_NAME + " where "
                + Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=?;", new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id)});
        if(cursor.moveToFirst())
            ret = cursor.getDouble(0);
        else
            ret = 0;
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    // Doi voi cac truong hop la cap nhat theo node.
    public double getLuykeByNode(long work_item_id, long cat_sub_work_item_id,long nodeId) {
//        Log.e(TAG, "getLuyke: " + work_item_id + " " + cat_sub_work_item_id );
        double ret = 0L;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor  = db.rawQuery("select sum(" + Sub_Work_Item_ValueField.VALUE + ") from " + Sub_Work_Item_ValueField.TABLE_NAME + " where "
                + Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                + Sub_Work_Item_ValueField.CONSTR_NODE_ID + "=?;", new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id), String.valueOf(nodeId)});
        if(cursor.moveToFirst())
            ret = cursor.getDouble(0);
        else
            ret = 0;
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public double getLuykeHanNoiByNode(long work_item_id, long cat_sub_work_item_id,long nodeId) {
//        Log.e(TAG, "getLuyke: " + work_item_id + " " + cat_sub_work_item_id );
        double ret = 0L;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor  = db.rawQuery("select sum(" + Sub_Work_Item_ValueField.VALUE_ITEM + ") from " + Sub_Work_Item_ValueField.TABLE_NAME + " where "
                + Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                + Sub_Work_Item_ValueField.CONSTR_NODE_ID + "=?;", new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id), String.valueOf(nodeId)});
        if(cursor.moveToFirst())
            ret = cursor.getDouble(0);
        else
            ret = 0;
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public double getOldLuyke(long work_item_id, long cat_sub_work_item_id) {
//        Log.e(TAG, "getLuyke: " + work_item_id + " " + cat_sub_work_item_id );
        double ret = 0L;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor  = db.rawQuery("select sum(" + Sub_Work_Item_ValueField.VALUE + ") from " + Sub_Work_Item_ValueField.TABLE_NAME + " where "
                + Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                + Sub_Work_Item_ValueField.ADDED_DATE + " != " + "'" + GSCTUtils.getDateNow() + "';",
                new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id)});
        if(cursor.moveToFirst())
            ret = cursor.getDouble(0);
        else
            ret = 0;
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public double getOldLuykeHanNoi(long work_item_id, long cat_sub_work_item_id) {
//        Log.e(TAG, "getLuyke: " + work_item_id + " " + cat_sub_work_item_id );
        double ret = 0L;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor  = db.rawQuery("select sum(" + Sub_Work_Item_ValueField.VALUE_ITEM + ") from " + Sub_Work_Item_ValueField.TABLE_NAME + " where "
                        + Sub_Work_Item_ValueField.WORK_ITEM_ID + "=? AND "
                        + Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID +  "=? AND "
                        + Sub_Work_Item_ValueField.ADDED_DATE + " != " + "'" + GSCTUtils.getDateNow() + "';",
                new String[] { String.valueOf(work_item_id), String.valueOf(cat_sub_work_item_id)});
        if(cursor.moveToFirst())
            ret = cursor.getDouble(0);
        else
            ret = 0;
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public boolean addItem(Sub_Work_Item_ValueEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        // Inserting Row
        db.insert(Sub_Work_Item_ValueField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    public boolean updateItem(Sub_Work_Item_ValueEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        String sqlclause = Sub_Work_Item_ValueField.ID
                + "= ?";
        String[] sqlPara = new String[] { String.valueOf(addItem.getId())};

        db.update(Sub_Work_Item_ValueField.TABLE_NAME, values, sqlclause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    private ContentValues convertItemToValues(Sub_Work_Item_ValueEntity addItem){
        ContentValues values = new ContentValues();

        values.put(Sub_Work_Item_ValueField.WORK_ITEM_ID, addItem.getWork_item_id());
        values.put(Sub_Work_Item_ValueField.ID, addItem.getId());
        values.put(Sub_Work_Item_ValueField.VALUE, addItem.getValue());
        values.put(Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID, addItem.getCat_sub_work_item_id());
        values.put(Sub_Work_Item_ValueField.ADDED_DATE, addItem.getAdded_date());
        values.put(Sub_Work_Item_ValueField.COLUMN_PROCESS_ID, addItem.getProcessId());
        values.put(Sub_Work_Item_ValueField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
        values.put(Sub_Work_Item_ValueField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(Sub_Work_Item_ValueField.COLUMN_IS_ACTIVE, addItem.getIsActive());
        values.put(Sub_Work_Item_ValueField.VALUE_ITEM, addItem.getValue_item());
        values.put(Sub_Work_Item_ValueField.CONSTR_NODE_ID, addItem.getConstr_node_id());
        return values;
    }


    private Sub_Work_Item_ValueEntity converCursorToItem(Cursor cursor) {
        Sub_Work_Item_ValueEntity curItem = new Sub_Work_Item_ValueEntity();
        try {
            curItem.setId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.ID)));
            curItem.setWork_item_id(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.WORK_ITEM_ID)));
            curItem.setCat_sub_work_item_id(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.CAT_SUB_WORK_ITEM_ID)));
            curItem.setValue(cursor.getDouble(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.VALUE)));
            curItem.setAdded_date(cursor.getString(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.ADDED_DATE)));
            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.COLUMN_PROCESS_ID)));
            curItem.setEmployeeId(cursor.getLong(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.COLUMN_EMPLOYEE_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.COLUMN_SYNC_STATUS)));
            curItem.setIsActive(cursor.getInt(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.COLUMN_IS_ACTIVE)));
            curItem.setValue_item(cursor.getDouble(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.VALUE_ITEM)));
            curItem.setConstr_node_id(cursor.getInt(cursor
                    .getColumnIndex(Sub_Work_Item_ValueField.CONSTR_NODE_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
    

}
