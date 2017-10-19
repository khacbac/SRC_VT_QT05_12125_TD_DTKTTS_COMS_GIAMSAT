package com.viettel.database.field;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.KttsDatabaseHelper;
import com.viettel.database.entity.ConstrNodeEntity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by doanLV4 on 10/5/2017.
 */

public class ConstrNodeController {
    private Context mContext = null;
    private static final String TAG = ConstrNodeController.class.getSimpleName();

    public ConstrNodeController() {
    }

    public ConstrNodeController(Context mContext) {
        this.mContext = mContext;
    }

    public static final String[] allColumn = new String[]{
            ConstrNodeItemsField.CONSTR_NODE_ID,
            ConstrNodeItemsField.NODE_CODE,
            ConstrNodeItemsField.NODE_NAME,
            ConstrNodeItemsField.NUM_POPULATION,
            ConstrNodeItemsField.NUM_PORT,
            ConstrNodeItemsField.CREATOR_ID,
            ConstrNodeItemsField.CREATE_DATE,
//            ConstrNodeItemsField.UPDATOR_ID,
            ConstrNodeItemsField.UPDATE_DATE,
            ConstrNodeItemsField.CONSTRUCT_ID,
            BaseField.COLUMN_IS_ACTIVE,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_EMPLOYEE_ID,
            BaseField.COLUMN_SYNC_STATUS
    };

    public ArrayList<String> getListColumn() {
        ArrayList<String> listColumn = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(ConstrNodeItemsField.TABLE_NAME, null, null, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        Collections.addAll(listColumn, columns);
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return listColumn;
    }

    public ArrayList<ConstrNodeEntity> getListNode() {
        ArrayList<ConstrNodeEntity> listNode = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(ConstrNodeItemsField.TABLE_NAME, allColumn,
                        null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ConstrNodeEntity curItem = this.converCursorToItem(cursor);
                listNode.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();

        return listNode;
    }

    public ArrayList<ConstrNodeEntity> getListNodeByConstrId(long constrId) {
        ArrayList<ConstrNodeEntity> listNode = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(ConstrNodeItemsField.TABLE_NAME, allColumn,
                        ConstrNodeItemsField.CONSTRUCT_ID + " = ?",
                        new String[]{String.valueOf(constrId)}, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ConstrNodeEntity curItem = this.converCursorToItem(cursor);
                listNode.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();

        return listNode;
    }

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + ConstrNodeItemsField.TABLE_NAME
            + "("
            + ConstrNodeItemsField.CONSTR_NODE_ID
            + " INTEGER PRIMARY KEY,"
            + ConstrNodeItemsField.NODE_CODE
            + " TEXT,"
            + ConstrNodeItemsField.NODE_NAME
            + " TEXT,"
            + ConstrNodeItemsField.NUM_POPULATION
            + " INTEGER,"
            + ConstrNodeItemsField.NUM_PORT
            + " INTEGER,"
            + ConstrNodeItemsField.CREATOR_ID
            + " INTEGER,"
            + ConstrNodeItemsField.CREATE_DATE
            + " TEXT,"
//            + ConstrNodeItemsField.UPDATOR_ID
//            + " INTEGER,"
            + ConstrNodeItemsField.UPDATE_DATE
            + " TEXT,"
            + ConstrNodeItemsField.CONSTRUCT_ID
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


    private ContentValues convertItemToValues(ConstrNodeEntity addItem) {
        ContentValues values = new ContentValues();

        values.put(ConstrNodeItemsField.CONSTR_NODE_ID, addItem.getNodeID());
        values.put(ConstrNodeItemsField.NODE_CODE, addItem.getNodeCode());
        values.put(ConstrNodeItemsField.NODE_NAME, addItem.getNodeName());
        values.put(ConstrNodeItemsField.NUM_POPULATION, addItem.getNodePopulation());
        values.put(ConstrNodeItemsField.NUM_PORT, addItem.getNumPort());
        values.put(ConstrNodeItemsField.CREATOR_ID, addItem.getCreatorId());
        values.put(ConstrNodeItemsField.CREATE_DATE, addItem.getCreatorDate());
//        values.put(ConstrNodeItemsField.UPDATOR_ID, addItem.getUpdatorId());
        values.put(ConstrNodeItemsField.UPDATE_DATE, addItem.getUpdateDate());
        values.put(ConstrNodeItemsField.CONSTRUCT_ID, addItem.getContructorId());
        values.put(BaseField.COLUMN_IS_ACTIVE, addItem.getIsActive());
        values.put(BaseField.COLUMN_PROCESS_ID, addItem.getProcessId());
        values.put(BaseField.COLUMN_SYNC_STATUS, addItem.getSyncStatus());
        values.put(BaseField.COLUMN_EMPLOYEE_ID, addItem.getCatEmployeeId());
        return values;
    }

    private ConstrNodeEntity converCursorToItem(Cursor cursor) {
        ConstrNodeEntity curItem = new ConstrNodeEntity();
        try {
            curItem.setNodeID(cursor.getLong(cursor
                    .getColumnIndex(ConstrNodeItemsField.CONSTR_NODE_ID)));
            curItem.setNodeCode(cursor.getString(cursor
                    .getColumnIndex(ConstrNodeItemsField.NODE_CODE)));
            curItem.setNodeName(cursor.getString(cursor
                    .getColumnIndex(ConstrNodeItemsField.NODE_NAME)));
            curItem.setNodePopulation(cursor.getString(cursor
                    .getColumnIndex(ConstrNodeItemsField.NUM_POPULATION)));
            curItem.setNumPort(cursor.getInt(cursor
                    .getColumnIndex(ConstrNodeItemsField.NUM_PORT)));
            curItem.setCreatorId(cursor.getInt(cursor
                    .getColumnIndex(ConstrNodeItemsField.CREATOR_ID)));
            curItem.setCreatorDate(cursor.getString(cursor
                    .getColumnIndex(ConstrNodeItemsField.CREATE_DATE)));
//            curItem.setUpdatorId(cursor.getInt(cursor
//                    .getColumnIndex(ConstrNodeItemsField.UPDATOR_ID)));
            curItem.setUpdateDate(cursor.getString(cursor
                    .getColumnIndex(ConstrNodeItemsField.UPDATE_DATE)));
            curItem.setContructorId(cursor.getInt(cursor
                    .getColumnIndex(ConstrNodeItemsField.CONSTRUCT_ID)));
            curItem.setIsActive(cursor.getInt(cursor
                    .getColumnIndex(BaseField.COLUMN_IS_ACTIVE)));
            curItem.setProcessId(cursor.getInt(cursor
                    .getColumnIndex(BaseField.COLUMN_PROCESS_ID)));
            curItem.setSyncStatus(cursor.getInt(cursor
                    .getColumnIndex(BaseField.COLUMN_SYNC_STATUS)));
            curItem.setCatEmployeeId(cursor.getInt(cursor
                    .getColumnIndex(BaseField.COLUMN_EMPLOYEE_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curItem;
    }

    /*-------------------------------- Make for test --------------------------------*/

    public ArrayList<ConstrNodeEntity> getListNodeTest() {
        ArrayList<ConstrNodeEntity> listNode = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from constr_node where constr_node.id between 10 and 12";
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor.moveToFirst()) {
            do {
                ConstrNodeEntity entity = this.converCursorToItem(cursor);
                listNode.add(entity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();

        return listNode;
    }
}
