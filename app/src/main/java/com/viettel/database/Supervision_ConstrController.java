package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supv_Constr_DailyField;
import com.viettel.utils.DateConvert;
import com.viettel.utils.StringUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Supervision_ConstrController {
    private Context mContext = null;
    public static String[] allColumn = new String[]{
            Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
            Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE,
            Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
            Supervision_ConstrField.COLUMN_SUPERVISION_STATUS,
            Supervision_ConstrField.COLUMN_IS_ACCEPT,
            Supervision_ConstrField.COLUMN_ACCEPT_DATE,
            Supervision_ConstrField.COLUMN_IS_LOCK,
            Supervision_ConstrField.COLUMN_FINISH_DATE,
            Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL,
            Supervision_ConstrField.COLUMN_DESIGN_TYPE,
            Supervision_ConstrField.COLUMN_DESIGN_ASSESS,
            Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY,
            Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION,
            Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE,
            Supervision_ConstrField.COLUMN_CONSTRUCT_ID,
            Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID,

            // bo sung tien do
            Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID,
            Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY,
            Supervision_ConstrField.COLUMN_HAND_OVER_DATE,
            Supervision_ConstrField.COLUMN_STARTED_DATE,
            Supervision_ConstrField.COLUMN_COMPLETED_DATE,
            // end

            Supervision_ConstrField.COLUMN_LINE_TYPE,
            Supervision_ConstrField.COLUMN_CONSTRUCTORNAME,
            Supervision_ConstrField.COLUMN_SUPERVISORNAME,
            Supervision_ConstrField.COLUMN_STATUSNAME,
            Supervision_ConstrField.COLUMN_SYS_CREATE_DATE,

            Supervision_ConstrField.COLUMN_PROCESS_ID,
            Supervision_ConstrField.COLUMN_SYNC_STATUS,
            Supervision_ConstrField.COLUMN_IS_ACTIVE,
            Supervision_ConstrField.COLUMN_EMPLOYEE_ID};

    public static String[] allColumnUpate = new String[]{
            Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
            Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
            Supervision_ConstrField.COLUMN_SUPERVISION_STATUS,
            Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE,
            Supervision_ConstrField.COLUMN_ACCEPT_DATE,
            Supervision_ConstrField.COLUMN_FINISH_DATE,
            Supervision_ConstrField.COLUMN_DESIGN_TYPE,

            Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID,
            Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY,
            Supervision_ConstrField.COLUMN_HAND_OVER_DATE,
            Supervision_ConstrField.COLUMN_STARTED_DATE,
            Supervision_ConstrField.COLUMN_COMPLETED_DATE,

            Supervision_ConstrField.COLUMN_LINE_TYPE,
            Supervision_ConstrField.COLUMN_CONSTRUCTORNAME,
            Supervision_ConstrField.COLUMN_SUPERVISORNAME,
            Supervision_ConstrField.COLUMN_STATUSNAME,
            Supervision_ConstrField.COLUMN_SYS_CREATE_DATE,

            Supervision_ConstrField.COLUMN_DESIGN_ASSESS,
            Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION,
            Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE,
            Supervision_ConstrField.COLUMN_SYNC_STATUS};

    public static String[] allColumnDailyUpate = new String[]{
            Supv_Constr_DailyField.COLUMN_SUPV_CONSTR_DAILY_ID,
            Supv_Constr_DailyField.COLUMN_SUPERVISION_CONSTR_ID,
            Supv_Constr_DailyField.COLUMN_SYNC_DATE,
            Supv_Constr_DailyField.COLUMN_LONGITUDE,
            Supv_Constr_DailyField.COLUMN_LATITUDE};

    public static final String CREATE_SUPERVISION_CONSTR_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Supervision_ConstrField.TABLE_NAME
            + "("
            + Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
            + " TEXT PRIMARY KEY ,"
            + Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_SUPERVISION_STATUS
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_IS_ACCEPT
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_ACCEPT_DATE
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY
            + " INTEGER,"

            + Supervision_ConstrField.COLUMN_HAND_OVER_DATE
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_STARTED_DATE
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_COMPLETED_DATE
            + " TEXT,"

            + Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID
            + " INTEGER,"

            + Supervision_ConstrField.COLUMN_IS_LOCK
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_FINISH_DATE
            + " TEXT, "
            + Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_DESIGN_TYPE
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_DESIGN_ASSESS
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_CONSTRUCT_ID
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_LINE_TYPE
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_CONSTRUCTORNAME
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_SUPERVISORNAME
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_STATUSNAME
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_SYS_CREATE_DATE
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID
            + " TEXT,"
            + Supervision_ConstrField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_SYNC_STATUS
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Supervision_ConstrField.COLUMN_EMPLOYEE_ID
            + " TEXT" + ")";

    public Supervision_ConstrController(Context pContext) {
        this.mContext = pContext;
    }

    /*
     * Them moi bang quan he giua yeu cau va cong trinh
     *
     * @param addItem Cong trinh duoc them moi
     *
     * @return true neu them moi thanh cong , false neu them moi that bai
     */
    public boolean addItem(Supervision_ConstrEntity addItem) {
        boolean bResult = false;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        ContentValues values = new ContentValues();

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
                addItem.getSupervision_Constr_Id());

        values.put(Supervision_ConstrField.COLUMN_CONSTRUCT_ID,
                addItem.getConstruct_Id());

        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE,
                addItem.getSupv_Constr_Type());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                addItem.getSupervision_Progress());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS,
                addItem.getSupervision_Status());

        values.put(Supervision_ConstrField.COLUMN_IS_ACCEPT,
                addItem.getIs_Accept());

        values.put(Supervision_ConstrField.COLUMN_ACCEPT_DATE,
                DateConvert.convertDateToString(addItem.getAccept_Date()));

        values.put(Supervision_ConstrField.COLUMN_IS_LOCK, addItem.getIs_Lock());

        values.put(Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL,
                addItem.getSupv_Group_Level());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_TYPE,
                addItem.getDesign_Type());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_ASSESS,
                addItem.getDesign_Assess());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION,
                addItem.getDesign_Description());

        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE,
                addItem.getSupv_Constr_Note_Code());

        values.put(Supervision_ConstrField.COLUMN_LINE_TYPE,
                addItem.getLineType());

        values.put(Supervision_ConstrField.COLUMN_CONSTRUCTORNAME,
                addItem.getConstructorName());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISORNAME,
                addItem.getSupervisorName());

        values.put(Supervision_ConstrField.COLUMN_STATUSNAME,
                addItem.getStatusName());

        values.put(Supervision_ConstrField.COLUMN_SYS_CREATE_DATE,
                addItem.getSysCreateDate());

        values.put(Supervision_ConstrField.COLUMN_CONSTRUCT_ID,
                addItem.getConstruct_Id());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
                addItem.getSupervision_Constr_Req_Id());

        values.put(Supervision_ConstrField.COLUMN_PROCESS_ID,
                addItem.getProcessId());

        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                addItem.getSyncStatus());

        values.put(Supervision_ConstrField.COLUMN_EMPLOYEE_ID,
                addItem.getEmployeeId());

        // Inserting Row
        db.insert(Supervision_ConstrField.TABLE_NAME, null, values);
        KttsDatabaseHelper.INSTANCE.close();
        bResult = true;
        return bResult;
    }

    /*
     * Lay mot cong trinh giam sat
     *
     * @param itemId Id cong trinh
     *
     * @return Cong trinh duoc giam sat
     */
    public Supervision_ConstrEntity getItem(long itemId) {
        Supervision_ConstrEntity curItem = new Supervision_ConstrEntity();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Supervision_ConstrField.TABLE_NAME, allColumn,
                        Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                                + "=?",
                        new String[]{String.valueOf(itemId)}, null, null,
                        null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            curItem = this.converCursorToItem(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public Supervision_ConstrEntity getItemByConstructId(long constructId) {
        Supervision_ConstrEntity curItem = new Supervision_ConstrEntity();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Supervision_ConstrField.TABLE_NAME, allColumn,
                        Supervision_ConstrField.COLUMN_CONSTRUCT_ID + "=?",
                        new String[]{String.valueOf(constructId)}, null, null,
                        null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            curItem = this.converCursorToItem(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    public boolean updateDesign(Supervision_ConstrEntity itemData) {
        ContentValues values = new ContentValues();

        values.put(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION,
                itemData.getDesign_Description());
        values.put(Supervision_ConstrField.COLUMN_DESIGN_TYPE,
                itemData.getDesign_Type());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_ASSESS,
                itemData.getDesign_Assess());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                itemData.getSupervision_Progress());

        values.put(Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY,
                itemData.getDeployExpectedDay());

        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);

        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(itemData
                .getSupervision_Constr_Id())};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateprogress(Supervision_ConstrEntity itemData) {
        ContentValues values = new ContentValues();


        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                itemData.getSupervision_Progress());


        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.ADD);

        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(itemData
                .getSupervision_Constr_Id())};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateProgressAndDay(long idSupv, int iProgress) {
        ContentValues values = new ContentValues();
        if (iProgress == 1) {
            values.put(Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID, 2);
            values.put(Supervision_ConstrField.COLUMN_HAND_OVER_DATE,
                    DateConvert.convertDateToString(Calendar.getInstance()
                            .getTime()));
        } else {
            if (iProgress == 2) {
                values.put(Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID, 3);
                values.put(Supervision_ConstrField.COLUMN_STARTED_DATE,
                        DateConvert.convertDateToString(Calendar.getInstance()
                                .getTime()));
            } else {
                if (iProgress == 3) {
                    values.put(Supervision_ConstrField.COLUMN_CAT_PROGRESS_WORK_ID, 4);
                    values.put(Supervision_ConstrField.COLUMN_COMPLETED_DATE,
                            DateConvert.convertDateToString(Calendar.getInstance()
                                    .getTime()));
                }
            }
        }

        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);

        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(idSupv)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateAllColumn(Supervision_ConstrEntity itemData) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE,
                itemData.getSupv_Constr_Type());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                itemData.getSupervision_Progress());

        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS,
                itemData.getSupervision_Status());

        values.put(Supervision_ConstrField.COLUMN_IS_ACCEPT,
                itemData.getIs_Accept());

        values.put(Supervision_ConstrField.COLUMN_ACCEPT_DATE,
                DateConvert.convertDateToString(itemData.getAccept_Date()));

        values.put(Supervision_ConstrField.COLUMN_IS_LOCK,
                itemData.getIs_Lock());

        values.put(Supervision_ConstrField.COLUMN_FINISH_DATE,
                DateConvert.convertDateToString(itemData.getFinish_Date()));

        values.put(Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL,
                itemData.getSupv_Group_Level());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_TYPE,
                itemData.getDesign_Type());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_ASSESS,
                itemData.getDesign_Assess());

        values.put(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION,
                itemData.getDesign_Description());

        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE,
                itemData.getSupv_Constr_Note_Code());

        values.put(Supervision_ConstrField.COLUMN_CONSTRUCT_ID,
                itemData.getConstruct_Id());

//		values.put(Supervision_ConstrField.COLUMN_LINE_TYPE,
//				itemData.getLineType());
//
//		values.put(Supervision_ConstrField.COLUMN_CONSTRUCTORNAME,
//				itemData.getConstructorName());
//
//		values.put(Supervision_ConstrField.COLUMN_SUPERVISORNAME,
//				itemData.getSupervisorName());
//
//		values.put(Supervision_ConstrField.COLUMN_STATUSNAME,
//				itemData.getStatusName());


        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
                itemData.getSupervision_Constr_Req_Id());

        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(itemData
                .getSupervision_Constr_Id())};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    /**
     * cap nhat loai tuyen cong trinh
     *
     * @param id     Id cong trinh
     * @param idType Id loai cong trinh(Tuyen ngam, tuyen treo)
     * @return
     */
    public boolean updateSupvContrType(long id, int idType) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE, idType);
        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateStatus(long iIdConstr, int iStatus) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS, iStatus);
        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(iIdConstr)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    /**
     * Ham update tien do cong trinh
     *
     * @param id         Id cong trinh
     * @param idProgress Id tien do cong trinh
     * @return
     */
    public boolean updateContrProgress(long id, int idProgress) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                idProgress);
        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Supervision_ConstrField.COLUMN_CONSTRUCT_ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    /**
     * Ham update trang thai dong bo
     *
     * @param id Id cong trinh
     * @return
     */
    public boolean updateSyncStatus(long id) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
                + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateStatusProgegress(long id, int idStatus, int idProgress) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                idProgress);
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS, idStatus);
        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);
        String sqlclause = Supervision_ConstrField.COLUMN_CONSTRUCT_ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    public boolean updateStatusProgegressCode(long id, int idStatus,
                                              int idProgress, String pNoteCode) {
        ContentValues values = new ContentValues();
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS,
                idProgress);
        values.put(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS, idStatus);

        values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS,
                Constants.SYNC_STATUS.EDIT);

        values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE,
                pNoteCode);
        String sqlclause = Supervision_ConstrField.COLUMN_CONSTRUCT_ID + "= ?";
        String[] sqlPara = new String[]{String.valueOf(id)};
        return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
                sqlclause, sqlPara);
    }

    private Supervision_ConstrEntity converCursorToItem(Cursor cursor) {
        Supervision_ConstrEntity curItem = new Supervision_ConstrEntity();
        try {
            curItem.setSupervision_Constr_Id(cursor.getLong(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID)));

            curItem.setSupv_Constr_Type(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE)));

            curItem.setSupervision_Progress(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS)));

            curItem.setSupervision_Status(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS)));

            curItem.setIs_Accept(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_IS_ACCEPT)));

            curItem.setAccept_Date(DateConvert.convertStringToDate(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_ACCEPT_DATE))));

            curItem.setDeployExpectedDay(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_DEPLOY_EXPECTED_DAY)));

            curItem.setIs_Lock(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_IS_LOCK)));

            curItem.setSupv_Group_Level(cursor.getLong(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL)));

            curItem.setDesign_Type(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_TYPE)));

            curItem.setDesign_Assess(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_ASSESS)));

            curItem.setDesign_Description(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION)));

            curItem.setSupv_Constr_Note_Code(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE)));

            curItem.setLineType(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_LINE_TYPE)));
            curItem.setConstructorName(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_CONSTRUCTORNAME)));
            curItem.setSupervisorName(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISORNAME)));
            curItem.setStatusName(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_STATUSNAME)));
            curItem.setSysCreateDate(cursor.getString(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SYS_CREATE_DATE)));

            curItem.setSupervision_Constr_Req_Id(cursor.getLong(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID)));

            curItem.setConstruct_Id(cursor.getLong(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_CONSTRUCT_ID)));

            curItem.setProcessId(cursor.getLong(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_PROCESS_ID)));

            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(Supervision_ConstrField.COLUMN_SYNC_STATUS)));

			/* Ngay hoan thanh cong trinh */
            String sDateFinish = cursor
                    .getString(cursor
                            .getColumnIndex(Supervision_ConstrField.COLUMN_FINISH_DATE));
            if (!StringUtil.isNullOrEmpty(sDateFinish)) {
                curItem.setFinish_Date(DateConvert
                        .convertStringToDate(sDateFinish));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
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
                .query(Supervision_ConstrField.TABLE_NAME,
                        new String[]{Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID},
                        Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
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
    public boolean updateGetData(List<Supervision_ConstrEntity> listData) {
        boolean bResult = false;
        for (Supervision_ConstrEntity curItem : listData) {
            if (this.checkExitItem(curItem.getSupervision_Constr_Id())) {
                // TODO viet ham update
            } else {
                this.addItem(curItem);
            }
        }
        return bResult;
    }

    public String getNoteCode(String pGroupCode) throws Exception {
        String sResult = "";
        sResult += "BBDGCLCT_" + pGroupCode;
        Date curDate = new Date();
        int month = curDate.getMonth() + 1;
        if (month < 10) {
            sResult += "/0" + month;
        } else {
            sResult += "/" + month;
        }
        int year = curDate.getYear() + 1900;
        sResult += "/" + year;

        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        int count = 0;
        Cursor cursor = db.rawQuery("select count(*) from "
                + Supervision_ConstrField.TABLE_NAME + " where "
                + Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE
                + " is not null", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0 && cursor.getColumnCount() > 0) {
            count = cursor.getInt(0);
        }
        KttsDatabaseHelper.INSTANCE.close();
        count += 1;
        String sNoteCode = "";
        if (count < 10) {
            sNoteCode = "00000" + count;
        } else if (count < 100) {
            sNoteCode = "0000" + count;
        } else if (count < 1000) {
            sNoteCode = "000" + count;
        } else if (count < 10000) {
            sNoteCode = "00" + count;
        } else if (count < 100000) {
            sNoteCode = "0" + count;
        } else {
            sNoteCode = count + "";
        }
        sResult += "/" + sNoteCode;
        return sResult;
    }
}
