package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Constr_Work_LogsEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Constr_Work_LogsField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Constr_Work_LogsController {
    private Context mContext = null;
    private static final String TAG = "Constr_Work_LogsControl";

    public Constr_Work_LogsController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Constr_Work_LogsField.CONSTR_WORK_LOGS_ID,
            Constr_Work_LogsField.LOG_DATE,
            Constr_Work_LogsField.WORK_CONTENT,
            Constr_Work_LogsField.CREATED_DATE,
            Constr_Work_LogsField.STATUS_CA,
            Constr_Work_LogsField.COLUMN_IS_ACTIVE,
            Constr_Work_LogsField.CREATED_USER_ID,
            Constr_Work_LogsField.ADDITION_CHANGE_ARISE,
            Constr_Work_LogsField.CONTRACTOR_COMMENTS,
            Constr_Work_LogsField.MONITOR_COMMENTS,
            Constr_Work_LogsField.APPROVAL_DATE,
            Constr_Work_LogsField.ESTIMATES_WORK_ITEM_ID,
            Constr_Work_LogsField.A_MONITOR_ID,
            Constr_Work_LogsField.B_CONSTRUCT_ID,
            Constr_Work_LogsField.CONSTRUCT_ID,
            Constr_Work_LogsField.CAT_CAUSE_NOT_WORK_ID,
            Constr_Work_LogsField.CAT_WEATHER_ID,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Constr_Work_LogsField.IS_WORK
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Constr_Work_LogsField.TABLE_NAME
            + "("
            + Constr_Work_LogsField.CONSTR_WORK_LOGS_ID
            + " INTEGER PRIMARY KEY,"
            + Constr_Work_LogsField.LOG_DATE
            + " TEXT,"
            + Constr_Work_LogsField.WORK_CONTENT
            + " TEXT,"
            + Constr_Work_LogsField.CREATED_DATE
            + " TEXT,"
            + Constr_Work_LogsField.STATUS_CA
            + " INTEGER,"
            + Constr_Work_LogsField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Constr_Work_LogsField.CREATED_USER_ID
            + " INTEGER,"
            + Constr_Work_LogsField.ADDITION_CHANGE_ARISE
            + " TEXT,"
            + Constr_Work_LogsField.CONTRACTOR_COMMENTS
            + " TEXT,"
            + Constr_Work_LogsField.MONITOR_COMMENTS
            + " TEXT,"
            + Constr_Work_LogsField.APPROVAL_DATE
            + " TEXT,"
            + Constr_Work_LogsField.ESTIMATES_WORK_ITEM_ID
            + " INTEGER,"
            + Constr_Work_LogsField.A_MONITOR_ID
            + " INTEGER,"
            + Constr_Work_LogsField.B_CONSTRUCT_ID
            + " INTEGER,"
            + Constr_Work_LogsField.CONSTRUCT_ID
            + " INTEGER,"
            + Constr_Work_LogsField.CAT_CAUSE_NOT_WORK_ID
            + " INTEGER,"
            + Constr_Work_LogsField.CAT_WEATHER_ID
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Constr_Work_LogsField.IS_WORK
            + " INTEGER"
            + " )";

    public Constr_Work_LogsEntity getItem(long itemId, String date) {
        Log.e(TAG, "getItem: " + itemId +" " +date);
        Constr_Work_LogsEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Constr_Work_LogsField.TABLE_NAME, allColumn,
                        Constr_Work_LogsField.CONSTRUCT_ID + "=? AND "
                                + Constr_Work_LogsField.LOG_DATE +  "=?",
                        new String[] { String.valueOf(itemId), date }, null, null,
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

    public List<Constr_Work_LogsEntity> getAllItem(long itemId) {
        List<Constr_Work_LogsEntity> listItems = new ArrayList<>();
        Constr_Work_LogsEntity curItem;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Constr_Work_LogsField.TABLE_NAME,allColumn,
                Constr_Work_LogsField.CONSTRUCT_ID + "=?",
                new String[]{String.valueOf(itemId)},null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                Constr_Work_LogsEntity item = this.converCursorToItem(cursor);
                listItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return listItems;
    }

    public List<String> getListLogDate(long itemId) {
        List<String> listDate = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Constr_Work_LogsField.TABLE_NAME,allColumn,
                Constr_Work_LogsField.CONSTRUCT_ID + "=?",
                new String[]{String.valueOf(itemId)},null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                String date = this.converCursorToItem(cursor).getLog_date();
                listDate.add(date);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return listDate;
    }

    public boolean addItem(Constr_Work_LogsEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        // Inserting Row
        db.insert(Constr_Work_LogsField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    public boolean updateItem(Constr_Work_LogsEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        String sqlclause = Constr_Work_LogsField.CONSTR_WORK_LOGS_ID
                + "= ?";
        String[] sqlPara = new String[] { String.valueOf(addItem.getConstr_work_logs_id())};

        db.update(Constr_Work_LogsField.TABLE_NAME, values, sqlclause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    private ContentValues convertItemToValues(Constr_Work_LogsEntity addItem){
        ContentValues values = new ContentValues();

        values.put(Constr_Work_LogsField.CONSTR_WORK_LOGS_ID, addItem.getConstr_work_logs_id());
        values.put(Constr_Work_LogsField.LOG_DATE, addItem.getLog_date());
        values.put(Constr_Work_LogsField.WORK_CONTENT, addItem.getWork_content());
        values.put(Constr_Work_LogsField.CREATED_DATE, addItem.getCreated_date());
        values.put(Constr_Work_LogsField.CREATED_USER_ID, addItem.getCreated_user_id());
        values.put(Constr_Work_LogsField.ADDITION_CHANGE_ARISE, addItem.getAddition_change_arise());
        values.put(Constr_Work_LogsField.CONTRACTOR_COMMENTS, addItem.getConstructor_comments());
        values.put(Constr_Work_LogsField.MONITOR_COMMENTS, addItem.getMonitor_comments());
        values.put(Constr_Work_LogsField.APPROVAL_DATE, addItem.getApproval_date());
        values.put(Constr_Work_LogsField.ESTIMATES_WORK_ITEM_ID, addItem.getEstimate_work_item_id());
        values.put(Constr_Work_LogsField.A_MONITOR_ID, addItem.getA_monitor_id());
        values.put(Constr_Work_LogsField.B_CONSTRUCT_ID, addItem.getB_monitor_id());
        values.put(Constr_Work_LogsField.STATUS_CA, addItem.getStatus_ca());
        values.put(Constr_Work_LogsField.IS_ACTIVE, addItem.getIs_active());
        values.put(Constr_Work_LogsField.CONSTRUCT_ID, addItem.getConstruct_id());
        values.put(Constr_Work_LogsField.CAT_CAUSE_NOT_WORK_ID, addItem.getCat_cause_not_work_id());
        values.put(Constr_Work_LogsField.IS_WORK, addItem.getIs_work());
        values.put(Constr_Work_LogsField.COLUMN_PROCESS_ID, addItem.getProcessId());
        values.put(Constr_Work_LogsField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(Constr_Work_LogsField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
        values.put(Constr_Work_LogsField.CAT_WEATHER_ID, addItem.getCat_Weather_id());
        return values;
    }


    private Constr_Work_LogsEntity converCursorToItem(Cursor cursor) {
        Constr_Work_LogsEntity curItem = new Constr_Work_LogsEntity();
        try {
            curItem.setConstr_work_logs_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Work_LogsField.CONSTR_WORK_LOGS_ID)));
            curItem.setLog_date(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.LOG_DATE)));
            curItem.setWork_content(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.WORK_CONTENT)));
            curItem.setCreated_date(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.CREATED_DATE)));
            curItem.setStatus_ca(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.STATUS_CA)));
            curItem.setIs_active(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.COLUMN_IS_ACTIVE)));
            curItem.setCreated_user_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Work_LogsField.CREATED_USER_ID)));
            curItem.setAddition_change_arise(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.ADDITION_CHANGE_ARISE)));
            curItem.setConstructor_comments(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.CONTRACTOR_COMMENTS)));
            curItem.setMonitor_comments(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.MONITOR_COMMENTS)));
            curItem.setApproval_date(cursor.getString(cursor
                    .getColumnIndex(Constr_Work_LogsField.APPROVAL_DATE)));
            curItem.setEstimate_work_item_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.ESTIMATES_WORK_ITEM_ID)));
            curItem.setA_monitor_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.A_MONITOR_ID)));
            curItem.setB_monitor_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.B_CONSTRUCT_ID)));
            curItem.setConstruct_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Work_LogsField.CONSTRUCT_ID)));
            curItem.setCat_cause_not_work_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.CAT_CAUSE_NOT_WORK_ID)));

            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Constr_Work_LogsField.COLUMN_PROCESS_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.COLUMN_SYNC_STATUS)));
            curItem.setEmployeeId(cursor.getLong(cursor
                    .getColumnIndex(Constr_Work_LogsField.COLUMN_EMPLOYEE_ID)));
            curItem.setIs_work(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.IS_WORK)));
            curItem.setCat_Weather_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Work_LogsField.CAT_WEATHER_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
}
