package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.entity.Plan_Constr_DetailEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Plan_Constr_DetailField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Plan_Constr_DetailController {
    private Context mContext = null;
    private static final String TAG = "Plan_Constr_DetailCont";

    public Plan_Constr_DetailController(Context pContext) {
        this.mContext = pContext;
    }

    public static final String[] allColumn = new String[]{
            Plan_Constr_DetailField.PLAN_CONSTR_DETAIL_ID,
            Plan_Constr_DetailField.CONSTRUCT_ID,
            Plan_Constr_DetailField.PLAN_ID,
            Plan_Constr_DetailField.PLAN_NAME,
            Plan_Constr_DetailField.FINISH_DATE,
            Plan_Constr_DetailField.REAL_FINISH_DATE,
            Plan_Constr_DetailField.REAL_VALUE,
            Plan_Constr_DetailField.MILESTONE_NAME,
            Plan_Constr_DetailField.CAT_MILESTONE_CONSTR_ID,
            Plan_Constr_DetailField.EXPECTED_VALUE,
            BaseField.COLUMN_IS_ACTIVE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID
    };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Plan_Constr_DetailField.TABLE_NAME
            + "("
            + Plan_Constr_DetailField.PLAN_CONSTR_DETAIL_ID
            + " INTEGER PRIMARY KEY,"
            + Plan_Constr_DetailField.CONSTRUCT_ID
            + " INTEGER,"
            + Plan_Constr_DetailField.PLAN_ID
            + " INTEGER,"
            + Plan_Constr_DetailField.CAT_MILESTONE_CONSTR_ID
            + " INTEGER,"
            + Plan_Constr_DetailField.PLAN_NAME
            + " TEXT,"
            + Plan_Constr_DetailField.MILESTONE_NAME
            + " TEXT,"
            + Plan_Constr_DetailField.FINISH_DATE
            + " TEXT,"
            + Plan_Constr_DetailField.REAL_FINISH_DATE
            + " TEXT,"
            + Plan_Constr_DetailField.REAL_VALUE
            + " REAL,"
            + Plan_Constr_DetailField.EXPECTED_VALUE
            + " TEXT,"
            + Plan_Constr_DetailField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER"
            + " )";

    public ArrayList<Plan_Constr_DetailEntity> getItem(long constructId) {
//        Log.e(TAG, "getItem: " + constructType );
        ArrayList<Plan_Constr_DetailEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Plan_Constr_DetailField.TABLE_NAME, allColumn,
                        Plan_Constr_DetailField.CONSTRUCT_ID + "=?",
                        new String[]{String.valueOf(constructId)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Plan_Constr_DetailEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public int updateLuyKe(long constructId, String catMilestoneConstrCode, double luyke) {
        int ret = 0;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor;

        String MY_QUERY = "update PLAN_CONSTR_DETAIL SET REAL_VALUE = ?, SYNC_STATUS = 2 " +
                "WHERE CONTRUCT_ID = ? and " +
                "CAT_MILESTONE_CONSTR_ID in (select ID from CAT_MILESTONE_CONSTR b where b.CODE = ?);";

        cursor = db.rawQuery(MY_QUERY, new String[]{String.valueOf(luyke),
                String.valueOf(constructId), catMilestoneConstrCode});
        cursor.moveToFirst();
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public String getRealFinishDate(long constructId, String catMilestoneConstrCode) {
        String ret;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor;

        String MY_QUERY = "select REAL_FINISH_DATE from PLAN_CONSTR_DETAIL " +
                "WHERE CONTRUCT_ID = ? and " +
                "CAT_MILESTONE_CONSTR_ID in (select ID from CAT_MILESTONE_CONSTR b where b.CODE = ?);";
        cursor = db.rawQuery(MY_QUERY,
                new String[]{String.valueOf(constructId), catMilestoneConstrCode});
        cursor.moveToFirst();
        ret = cursor.getString(0);
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public int updateRealFinishDate(long constructId,
                                    String catMilestoneConstrCode, String finishDate) {
        int ret = 0;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor;

        Log.e(TAG, "updateRealFinishDate() called with: constructId = [" + constructId
                + "], catMilestoneConstrCode = [" + catMilestoneConstrCode
                + "], finishDate = [" + finishDate + "]");
        String MY_QUERY = "update PLAN_CONSTR_DETAIL SET REAL_FINISH_DATE = ?, SYNC_STATUS = 2 " +
                "WHERE CONTRUCT_ID = ? and " +
                "CAT_MILESTONE_CONSTR_ID in (select ID from CAT_MILESTONE_CONSTR b where b.CODE = ?);";
        Log.e(TAG, MY_QUERY );
//        cursor = db.rawQuery(MY_QUERY, null);
        cursor = db.rawQuery(MY_QUERY, new String[]{finishDate,
                String.valueOf(constructId), catMilestoneConstrCode});
        cursor.moveToFirst();
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<PlanEntity> getPlans(long constructId) {
        ArrayList<PlanEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

        String querryCatPlan = "select max(PLAN_ID) from PLAN_CONSTR_DETAIL where CONTRUCT_ID = ?;";
        Cursor cursor = db.rawQuery(querryCatPlan, new String[]{String.valueOf(constructId)});
        cursor.moveToFirst();
        long maxPlanId= cursor.getLong(0);
        Log.e(TAG, "getPlans: " + cursor.getLong(0));
        cursor.close();
        String MY_QUERY = "select b.NAME ten_moc,  a.FINISH_DATE ngay_du_kien, " +
                "c.CODE ma_ke_hoach, c.NAME, " +
                "c.START_DATE ngay_bat_dau, c.END_DATE ngay_ket_thuc " +
                "from PLAN_CONSTR_DETAIL a " +
                "join CAT_MILESTONE_CONSTR b on a.CAT_MILESTONE_CONSTR_ID = b.ID " +
                "join CAT_PLAN_FOR_CONSTR_GSCT c on a.PLAN_ID = c.ID " +
//                "and c.IS_ACTIVE =1 " +
                "where a.CONTRUCT_ID = ? and a.PLAN_ID = ?;";
//                + "and <c.CREATED_DATE là max>;";

        cursor = db.rawQuery(MY_QUERY,
                new String[]{String.valueOf(constructId), String.valueOf(maxPlanId)});

        if (cursor.moveToFirst()) {
            do {
                PlanEntity curItem = new PlanEntity();
                curItem.finishDate = cursor.getString(cursor.getColumnIndex("ngay_du_kien"));
                curItem.tenmoc = cursor.getString(cursor.getColumnIndex("ten_moc"));
                curItem.planCode = cursor.getString(2);
                curItem.planName = cursor.getString(3);
                curItem.startDate = cursor.getString(4);
                curItem.endDate = cursor.getString(5);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();

        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Plan_Constr_DetailEntity converCursorToItem(Cursor cursor) {
        Plan_Constr_DetailEntity curItem = new Plan_Constr_DetailEntity();

        curItem.setId(cursor.getLong(cursor
                .getColumnIndex(Plan_Constr_DetailField.PLAN_CONSTR_DETAIL_ID)));
        curItem.setConstructId(cursor.getLong(cursor
                .getColumnIndex(Plan_Constr_DetailField.CONSTRUCT_ID)));
        curItem.setPlanId(cursor.getLong(cursor
                .getColumnIndex(Plan_Constr_DetailField.PLAN_ID)));
        curItem.setPlanName(cursor.getString(cursor
                .getColumnIndex(Plan_Constr_DetailField.PLAN_NAME)));
        curItem.setFinishDate(cursor.getString(cursor
                .getColumnIndex(Plan_Constr_DetailField.FINISH_DATE)));
        curItem.setRealFinishDate(cursor.getString(cursor
                .getColumnIndex(Plan_Constr_DetailField.REAL_FINISH_DATE)));
        curItem.setRealValue(cursor.getDouble(cursor
                .getColumnIndex(Plan_Constr_DetailField.REAL_VALUE)));
        curItem.setMilestoneName(cursor.getString(cursor
                .getColumnIndex(Plan_Constr_DetailField.MILESTONE_NAME)));
        curItem.setExpectedValue(cursor.getString(cursor
                .getColumnIndex(Plan_Constr_DetailField.EXPECTED_VALUE)));
        return curItem;
    }

    public static class PlanEntity {
        public String planName;
        public String planCode;
        public String startDate;
        public String endDate;
        public String tenmoc;
        public String finishDate;
    }
}

