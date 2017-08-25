package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.database.entity.CatConstructEntity;
import com.viettel.database.entity.Cat_Constr_TeamEntity;
import com.viettel.database.entity.Constr_ObStructionEntity;
import com.viettel.database.entity.Constr_ObStruction_TypeEntity;
import com.viettel.database.field.Cat_Construct_Field;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_ObStruction_TypeField;
import com.viettel.utils.DateConvert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supervision_CNVController {
    private Context mContext = null;
    public static final String CREATE_CONSTR_OBSTRUCTION_TYPE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Constr_ObStruction_TypeField.TABLE_NAME
            + "("
            + Constr_ObStruction_TypeField.COLUMN_ID
            + " TEXT PRIMARY KEY,"
            + Constr_ObStruction_TypeField.COLUMN_NAME
            + " TEXT,"
            + Constr_ObStruction_TypeField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Constr_ObStruction_TypeField.COLUMN_DESCRIPTION
            + " TEXT,"
            + Constr_ObStruction_TypeField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + Constr_ObStruction_TypeField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Constr_ObStruction_TypeField.COLUMN_SYNC_STATUS
            + " INTEGER,"
            + Constr_ObStruction_TypeField.COLUMN_TYPE + " TEXT)";

    public static final String CREATE_CONSTR_OBSTRUCTION_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Constr_ObStructionField.TABLE_NAME
            + "("
            + Constr_ObStructionField.COLUMN_ID
            + " TEXT PRIMARY KEY,"
            + Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_UPDATED_DATE
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_UPDATED_BY
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_DESCRIPTION
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_CONSTRUCT_ID
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_PREV_STATUS_ID
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_START_DATE
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_END_DATE
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_SOLVING_METHOD
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_CREATED_DATE
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_UPDATE_DESCRIPTION
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_EMPLOYEE_ID
            + " TEXT,"
            + Constr_ObStructionField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Constr_ObStructionField.COLUMN_SYNC_STATUS
            + " INTEGER,"
            + Constr_ObStructionField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + Constr_ObStructionField.CONSTR_WORK_LOGS_ID
            + " INTEGER,"
            + Constr_ObStructionField.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

    public static final String[] allColumnConstr_Obstruction_Type = new String[]{
            Constr_ObStruction_TypeField.COLUMN_ID,
            Constr_ObStruction_TypeField.COLUMN_NAME,
            Constr_ObStruction_TypeField.COLUMN_IS_ACTIVE,
            Constr_ObStruction_TypeField.COLUMN_DESCRIPTION,
            Constr_ObStruction_TypeField.COLUMN_TYPE,
            Constr_ObStruction_TypeField.COLUMN_PROCESS_ID};

    public static final String[] allColumnConstr_Obstruction = new String[]{
            Constr_ObStructionField.COLUMN_ID,
            Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID,
            Constr_ObStructionField.COLUMN_UPDATED_DATE,
            Constr_ObStructionField.COLUMN_UPDATED_BY,
            Constr_ObStructionField.COLUMN_DESCRIPTION,
            Constr_ObStructionField.COLUMN_CONSTRUCT_ID,
            Constr_ObStructionField.COLUMN_PREV_STATUS_ID,
            Constr_ObStructionField.COLUMN_START_DATE,
            Constr_ObStructionField.COLUMN_END_DATE,
            Constr_ObStructionField.COLUMN_SOLVING_METHOD,
            Constr_ObStructionField.COLUMN_CREATED_DATE,
            Constr_ObStructionField.COLUMN_UPDATE_DESCRIPTION,
            Constr_ObStructionField.COLUMN_EMPLOYEE_ID,
            Constr_ObStructionField.COLUMN_PROCESS_ID,
            Constr_ObStructionField.COLUMN_SYNC_STATUS,
            Constr_ObStructionField.COLUMN_IS_ACTIVE,
            Constr_ObStructionField.CONSTR_WORK_LOGS_ID,
            Constr_ObStructionField.COLUMN_SUPERVISION_CONSTR_ID};

    public Supervision_CNVController(Context pContext) {
        this.mContext = pContext;
    }

    public List<Constr_ObStructionEntity> getAllCNV(String constrId) {
        //return getListTest();

        List<Constr_ObStructionEntity> listItem = new
                ArrayList<Constr_ObStructionEntity>();
        try {
            SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
            Cursor cursor = db.query(Constr_ObStructionField.TABLE_NAME,
                    allColumnConstr_Obstruction,
                    Constr_ObStructionField.COLUMN_IS_ACTIVE + " = ? " + " AND " + Constr_ObStructionField.COLUMN_CONSTRUCT_ID + " = ? ",
                    new String[]{String.valueOf(Constants.IS_ACTIVE), constrId}, null,
                    null, Constr_ObStructionField.COLUMN_CREATED_DATE, null);
            int i = 0;
            if (cursor.moveToFirst()) {
                do {
                    i++;
                    Constr_ObStructionEntity curItem = new Constr_ObStructionEntity();
                    curItem = this.convertCursorToCNV(cursor);
                    curItem.setStt(i);
                    listItem.add(curItem);
                } while (cursor.moveToNext());
            }
            KttsDatabaseHelper.INSTANCE.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return contact list
        return listItem;
    }

    private static final String TAG = "Supervision_CNVControll";
    private Constr_ObStructionEntity convertCursorToCNV(Cursor cu) {
        Constr_ObStructionEntity curItem = new Constr_ObStructionEntity();
        curItem.setConstrObStructionId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_ID)));
        curItem.setConstrObStructionTypeId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID)));

        curItem.setConstructId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_CONSTRUCT_ID)));

        curItem.setCreatedDate(DateConvert.convertStringToDate(cu.getString(cu.getColumnIndex(Constr_ObStructionField.COLUMN_CREATED_DATE))));

        curItem.setDescription(cu.getString(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_DESCRIPTION)));

        curItem.setEndDate(DateConvert.convertStringToDate(cu.getString(cu.getColumnIndex(Constr_ObStructionField.COLUMN_END_DATE))));

        curItem.setPrevStatusId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_PREV_STATUS_ID)));

        curItem.setStartDate(DateConvert.convertStringToDate(cu.getString(cu.getColumnIndex(Constr_ObStructionField.COLUMN_START_DATE))));
        curItem.setSupervisionConstrId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_SUPERVISION_CONSTR_ID)));

        curItem.setUpdateDate(DateConvert.convertStringToDate(cu.getString(cu.getColumnIndex(Constr_ObStructionField.COLUMN_UPDATED_DATE))));
        curItem.setUpdatedBy(cu.getString(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_UPDATED_BY)));

        curItem.setUpdateDescription(cu.getString(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_UPDATE_DESCRIPTION)));
        curItem.setSolvingMethod(cu.getString(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_SOLVING_METHOD)));
        curItem.setSyncStatus(cu.getInt(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_SYNC_STATUS)));
        curItem.setIsActive(cu.getInt(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_IS_ACTIVE)));
        curItem.setProcessId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_PROCESS_ID)));
        curItem.setEmployeeId(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.COLUMN_EMPLOYEE_ID)));
        curItem.setConstr_work_logs_id(cu.getLong(cu
                .getColumnIndex(Constr_ObStructionField.CONSTR_WORK_LOGS_ID)));
        return curItem;
    }

    // Hungtn test
    private static List<Constr_ObStructionEntity> getListTest() {
        List<Constr_ObStructionEntity> listItem = new ArrayList<Constr_ObStructionEntity>();
        Constr_ObStructionEntity curItem = new Constr_ObStructionEntity();
        curItem.setConstrObStructionId(222222);

        curItem.setConstrObStructionTypeId(121212);

        curItem.setConstructId(33333);

        curItem.setCreatedDate(DateConvert.convertStringToDate("22/10/2016"));

        curItem.setDescription("Mo ta nay nhe");

        curItem.setEndDate(DateConvert.convertStringToDate("11/11/2016"));

        curItem.setPrevStatusId(444444);

        curItem.setStartDate(DateConvert.convertStringToDate("08/08/2015"));
        curItem.setSupervisionConstrId(123456);
        curItem.setStt(1);
        curItem.setType("ABC");
        curItem.setDescription("vao day nhe");
        curItem.setUpdateDate(DateConvert.convertStringToDate("09/08/2015"));
        curItem.setUpdatedBy("Hungtn");

        curItem.setUpdateDescription("test test update");
        curItem.setResult("Da thanh cong");
        listItem.add(curItem);
        listItem.add(curItem);
        return listItem;
    }

    public boolean addItemConstrObStruction(Constr_ObStructionEntity addItem) {
        boolean bResult = false;
        try {
            SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
            ContentValues values = new ContentValues();
            values.put(Constr_ObStructionField.COLUMN_ID, addItem.getConstrObStructionId());
            values.put(Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID, addItem.getConstrObStructionTypeId());
            Date date = addItem.getCreatedDate();
            String createdDate = DateConvert.convertDateToString(date);
            values.put(Constr_ObStructionField.COLUMN_CREATED_DATE, createdDate);
            values.put(Constr_ObStructionField.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);
            values.put(Constr_ObStructionField.COLUMN_PROCESS_ID, addItem.getProcessId());
            values.put(Constr_ObStructionField.COLUMN_SUPERVISION_CONSTR_ID, addItem.getSupervisionConstrId());
            values.put(Constr_ObStructionField.COLUMN_SYNC_STATUS, Constants.SYNC_STATUS.ADD);
            values.put(Constr_ObStructionField.COLUMN_EMPLOYEE_ID, addItem.getEmployeeId());
            values.put(Constr_ObStructionField.COLUMN_CONSTRUCT_ID, addItem.getConstructId());
            values.put(Constr_ObStructionField.COLUMN_DESCRIPTION, addItem.getDescription());
            values.put(Constr_ObStructionField.COLUMN_EMPLOYEE_ID, addItem.getCat_Employee_id());
            values.put(Constr_ObStructionField.CONSTR_WORK_LOGS_ID, addItem.getConstr_work_logs_id());
            values.put(Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID, addItem.getConstrObStructionTypeId());
            values.put(Constr_ObStructionField.COLUMN_UPDATE_DESCRIPTION, "");
            values.put(Constr_ObStructionField.COLUMN_END_DATE, "");
            values.put(Constr_ObStructionField.COLUMN_PREV_STATUS_ID, "");
            values.put(Constr_ObStructionField.COLUMN_SOLVING_METHOD, "");
            values.put(Constr_ObStructionField.COLUMN_START_DATE, "");
            values.put(Constr_ObStructionField.COLUMN_UPDATED_BY, "");
            values.put(Constr_ObStructionField.COLUMN_UPDATED_DATE, "");

            // Inserting Row
            long iRow = db.insert(Constr_ObStructionField.TABLE_NAME, null, values);
            KttsDatabaseHelper.INSTANCE.close();
            if (iRow > 0) {
                bResult = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bResult;
    }

    public CatConstructEntity getItemCatConstruct(String code) {
        CatConstructEntity curItem = new CatConstructEntity();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Cat_Construct_Field.TABLE_NAME,
                new String[]{Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID},
                Cat_Construct_Field.COLUMN_CODE + "=? AND "
                        + Cat_Construct_Field.COLUMN_IS_ACTIVE + " = ?",
                new String[]{code, String.valueOf(Constants.IS_ACTIVE)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            curItem.setCatConstructId(cursor.getLong(cursor
                    .getColumnIndex(Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID)));
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public Constr_ObStruction_TypeEntity getItemConstrObStructionType(
            String type) {
        Constr_ObStruction_TypeEntity curItem = new Constr_ObStruction_TypeEntity();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Constr_ObStruction_TypeField.TABLE_NAME,
                new String[]{Constr_ObStruction_TypeField.COLUMN_ID},
                Constr_ObStruction_TypeField.COLUMN_TYPE + "=? AND "
                        + Constr_ObStruction_TypeField.COLUMN_IS_ACTIVE
                        + " = ?",
                new String[]{type, String.valueOf(Constants.IS_ACTIVE)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            curItem.setConstrObStructionTypeId(cursor.getLong(cursor
                    .getColumnIndex(Constr_ObStruction_TypeField.COLUMN_ID)));
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public ArrayList<Constr_ObStruction_TypeEntity> getItemConstrObStructionTypes() {
        ArrayList<Constr_ObStruction_TypeEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Constr_ObStruction_TypeField.TABLE_NAME,
                new String[]{Constr_ObStruction_TypeField.COLUMN_ID, Constr_ObStruction_TypeField.COLUMN_NAME},
                        Constr_ObStruction_TypeField.COLUMN_IS_ACTIVE + " = ?",
                new String[]{String.valueOf(Constants.IS_ACTIVE)},
                null, null, Constr_ObStruction_TypeField.COLUMN_ID + " ASC", null);

        if (cursor.moveToFirst()) {
            do {
                Constr_ObStruction_TypeEntity curItem = new Constr_ObStruction_TypeEntity();
                curItem.setConstrObStructionTypeId(cursor.getLong(cursor
                        .getColumnIndex(Constr_ObStruction_TypeField.COLUMN_ID)));
                curItem.setName(cursor.getString(cursor
                        .getColumnIndex(Constr_ObStruction_TypeField.COLUMN_NAME)));
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    /**
     * Ham update trang thai dong bo
     *
     * @param id Id cong trinh
     * @return
     */
    public boolean updateSyncStatus(long id) {
        ContentValues values = new ContentValues();
        values.put(Constr_ObStructionField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Constr_ObStructionField.COLUMN_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Constr_ObStructionField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public Constr_ObStructionEntity getItemConstrObStruction(long constr_work_logs_id) {
        Constr_ObStructionEntity curItem = new Constr_ObStructionEntity();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.query(Constr_ObStructionField.TABLE_NAME,
                Supervision_CNVController.allColumnConstr_Obstruction,
                Constr_ObStructionField.CONSTR_WORK_LOGS_ID + "=?",
                new String[]{String.valueOf(constr_work_logs_id)},
                null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            curItem = convertCursorToCNV(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public boolean updateConstrObStruction(Constr_ObStructionEntity entity) {
        boolean result = false;
        try {
            ContentValues values = new ContentValues();
            values.put(Constr_ObStructionField.COLUMN_SOLVING_METHOD,
                    entity.getSolvingMethod());

            values.put(Constr_ObStructionField.COLUMN_UPDATE_DESCRIPTION,
                    entity.getDescription());
            Date date = entity.getCreatedDate();
            String updateDate = DateConvert.convertDateToString(date);
            values.put(Constr_ObStructionField.COLUMN_UPDATED_DATE, updateDate);

            values.put(Constr_ObStructionField.COLUMN_UPDATED_BY,
                    entity.getUpdatedBy());

            values.put(Constr_ObStructionField.COLUMN_SYNC_STATUS, Constants.SYNC_STATUS.EDIT);
            values.put(Constr_ObStructionField.COLUMN_CONSTR_OBSTRUCTION_TYPE_ID, entity.getConstrObStructionTypeId());
            values.put(Constr_ObStructionField.CONSTR_WORK_LOGS_ID, entity.getConstr_work_logs_id());
            String sqlclause = Constr_ObStructionField.COLUMN_ID + "= ?";
            String[] sqlPara = new String[]{String.valueOf(entity
                    .getConstrObStructionId())};
            result = this.updateDB(Constr_ObStructionField.TABLE_NAME, values,
                    sqlclause, sqlPara);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(Constr_ObStructionEntity pDellItem) {
        ContentValues values = new ContentValues();
        // Xoa ban ghi cu trong ngay
        values.put(Constr_ObStructionField.COLUMN_IS_ACTIVE,
                Constants.ISACTIVE.DEACTIVE);

        values.put(Constr_ObStructionField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.ADD);

        String sqlclause = Constr_ObStructionField.COLUMN_ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(pDellItem.getConstrObStructionId())};
        return this.updateDB(Constr_ObStructionField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    /**
     * @param sTable
     * @param values
     * @param sqlClause
     * @param sqlPara
     * @return
     */
    public boolean updateDB(String sTable, ContentValues values,
                            String sqlClause, String[] sqlPara) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        int iRow = db.update(sTable, values, sqlClause, sqlPara);
        KttsDatabaseHelper.INSTANCE.close();
        if (iRow > 0) {
            bResult = true;
        }
        return bResult;
    }

    /**
     * Ham kiem tra xem ban ghi da ton tai chua
     *
     * @param itemId Id cong trinh
     * @return Cong trinh duoc giam sat
     */
    public boolean checkExitItem(long itemId) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Constr_ObStructionField.TABLE_NAME,
                        new String[]{Constr_ObStructionField.COLUMN_ID},
                        Constr_ObStructionField.COLUMN_ID
                                + "=?",
                        new String[]{String.valueOf(itemId)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            bResult = true;
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return bResult;
    }

    /**
     * Ham cap nhat gia tri dong bo tu server ve
     *
     * @param listData
     * @return
     */
    public boolean updateGetData(List<Constr_ObStructionEntity> listData) {
        boolean bResult = false;
        for (Constr_ObStructionEntity curItem : listData) {
            if (this.checkExitItem(curItem.getConstrObStructionId())) {
                // TODO viet ham update
            } else {
                this.addItemConstrObStruction(curItem);
            }
        }
        return bResult;
    }

}
