package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Constr_Team_ProgressEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Constr_Team_ProgressField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Constr_Team_ProgressController {
    private static final String TAG = "Constr_Team_ProgressCon";
    private Context mContext = null;

    public Constr_Team_ProgressController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[] {
            Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID,
            Constr_Team_ProgressField.NUM_OF_TEAM,
            Constr_Team_ProgressField.CREATE_DATE,
            Constr_Team_ProgressField.CREATOR_ID,
            Constr_Team_ProgressField.CAT_CONSTR_TEAM_ID,
            Constr_Team_ProgressField.CONSTRUCT_ID,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Constr_Team_ProgressField.CONSTR_WORK_LOGS_ID
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Constr_Team_ProgressField.TABLE_NAME
            + "("
            + Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID
            + " INTEGER PRIMARY KEY,"
            + Constr_Team_ProgressField.NUM_OF_TEAM
            + " INTEGER,"
            + Constr_Team_ProgressField.CREATE_DATE
            + " TEXT,"
            + Constr_Team_ProgressField.CREATOR_ID
            + " INTEGER,"
            + Constr_Team_ProgressField.CAT_CONSTR_TEAM_ID
            + " INTEGER,"
            + Constr_Team_ProgressField.CONSTRUCT_ID
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Constr_Team_ProgressField.CONSTR_WORK_LOGS_ID
            + " INTEGER"
            + " )";

    public ArrayList<Constr_Team_ProgressEntity> getItems(long constr_work_log_id) {
        Log.e(TAG, "getItem: " + constr_work_log_id );
        ArrayList<Constr_Team_ProgressEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Constr_Team_ProgressField.TABLE_NAME, allColumn,
                        Constr_Team_ProgressField.CONSTR_WORK_LOGS_ID + "=?",
                        new String[] { String.valueOf(constr_work_log_id) }, null, null,
                        null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext())
                ret.add(converCursorToItem(cursor));
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public boolean addItem(Constr_Team_ProgressEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        // Inserting Row
        db.insert(Constr_Team_ProgressField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    public boolean updateItem(Constr_Team_ProgressEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = convertItemToValues(addItem);

        String sqlclause = Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID
                + "= ?";
        String[] sqlPara = new String[] { String.valueOf(addItem.getConstr_team_progress_id())};

        db.update(Constr_Team_ProgressField.TABLE_NAME, values, sqlclause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    private ContentValues convertItemToValues(Constr_Team_ProgressEntity addItem){
        ContentValues values = new ContentValues();

        values.put(Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID, addItem.getConstr_team_progress_id());
        values.put(Constr_Team_ProgressField.NUM_OF_TEAM, addItem.getNum_of_team());
        values.put(Constr_Team_ProgressField.CREATE_DATE, addItem.getCreated_date());
        values.put(Constr_Team_ProgressField.CREATOR_ID, addItem.getCreator_id());
        values.put(Constr_Team_ProgressField.CAT_CONSTR_TEAM_ID, addItem.getCat_constr_team_id());
        values.put(Constr_Team_ProgressField.CONSTRUCT_ID, addItem.getConstruct_id());
        values.put(Constr_Team_ProgressField.CONSTR_WORK_LOGS_ID, addItem.getConstr_work_logs_id());
        values.put(Constr_Team_ProgressField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
        values.put(Constr_Team_ProgressField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(Constr_Team_ProgressField.COLUMN_PROCESS_ID, addItem.getProcessId());
        return values;
    }


    private Constr_Team_ProgressEntity converCursorToItem(Cursor cursor) {
        Constr_Team_ProgressEntity curItem = new Constr_Team_ProgressEntity();
        try {
            curItem.setConstr_work_logs_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CONSTR_WORK_LOGS_ID)));
            curItem.setConstruct_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CONSTRUCT_ID)));
            curItem.setCat_constr_team_id(cursor.getInt(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CAT_CONSTR_TEAM_ID)));
            curItem.setCreator_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CREATOR_ID)));
            curItem.setCreated_date(cursor.getString(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CREATE_DATE)));
            curItem.setNum_of_team(cursor.getInt(cursor
                    .getColumnIndex(Constr_Team_ProgressField.NUM_OF_TEAM)));
            curItem.setConstr_team_progress_id(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID)));
            curItem.setEmployeeId(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.COLUMN_EMPLOYEE_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Constr_Team_ProgressField.COLUMN_SYNC_STATUS)));
            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Constr_Team_ProgressField.COLUMN_PROCESS_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }
}
