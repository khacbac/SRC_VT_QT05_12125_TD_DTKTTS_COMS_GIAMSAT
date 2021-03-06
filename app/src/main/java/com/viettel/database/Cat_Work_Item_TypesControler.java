package com.viettel.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Work_Item_TypesField;

import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Work_Item_TypesControler {
    private static Context mContext = null;
    private static final String TAG = "Cat_Work_Item_TypesCont";
    private static Cat_Work_Item_TypesControler cwitController;

    public Cat_Work_Item_TypesControler(Context pContext) {
        mContext = pContext;
    }

    public static Cat_Work_Item_TypesControler getInstance(Context context) {
        mContext = context;
        if (cwitController == null) {
            cwitController = new Cat_Work_Item_TypesControler(context);
        }
        return cwitController;
    }

    public static final String[] allColumn = new String[] {
            Cat_Work_Item_TypesField.ITEM_TYPE_ID,
            Cat_Work_Item_TypesField.ITEM_TYPE_NAME,
            Cat_Work_Item_TypesField.DESCRIPTION,
            Cat_Work_Item_TypesField.COLUMN_IS_ACTIVE,
            Cat_Work_Item_TypesField.CODE,
            Cat_Work_Item_TypesField.CONSTR_TYPE_ID,
            Cat_Work_Item_TypesField.TEMP_GROUP,
            Cat_Work_Item_TypesField.UNIT_NAME,
            Cat_Work_Item_TypesField.ORDER_NUM,
            BaseField.COLUMN_PROCESS_ID,
            BaseField.COLUMN_SYNC_STATUS,
            BaseField.COLUMN_EMPLOYEE_ID,
            Cat_Work_Item_TypesField.PARENT_D
             };

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Cat_Work_Item_TypesField.TABLE_NAME
            + "("
            + Cat_Work_Item_TypesField.ITEM_TYPE_ID
            + " INTEGER PRIMARY KEY,"
            + Cat_Work_Item_TypesField.ITEM_TYPE_NAME
            + " TEXT,"
            + Cat_Work_Item_TypesField.DESCRIPTION
            + " TEXT,"
            + Cat_Work_Item_TypesField.COLUMN_IS_ACTIVE
            + " INTEGER,"
            + Cat_Work_Item_TypesField.CODE
            + " STRING,"
            + Cat_Work_Item_TypesField.CONSTR_TYPE_ID
            + " INTEGER,"
            + Cat_Work_Item_TypesField.ORDER_NUM
            + " INTEGER,"
            + Cat_Work_Item_TypesField.TEMP_GROUP
            + " TEXT,"
            + Cat_Work_Item_TypesField.UNIT_NAME
            + " TEXT,"
            + BaseField.COLUMN_PROCESS_ID
            + " INTEGER,"
            + BaseField.COLUMN_SYNC_STATUS
            + " INTEGER DEFAULT 0,"
            + BaseField.COLUMN_EMPLOYEE_ID
            + " INTEGER,"
            + Cat_Work_Item_TypesField.PARENT_D
            + " INTEGER"
            + " )";

    public ArrayList<Cat_Work_Item_TypesEntity> getCates(long constructType) {
//        Log.e(TAG, "getItem: " + constructType );
        ArrayList<Cat_Work_Item_TypesEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db
                .query(Cat_Work_Item_TypesField.TABLE_NAME, allColumn,
                        Cat_Work_Item_TypesField.CONSTR_TYPE_ID + "=?",
                        new String[] { String.valueOf(constructType)}, null, null,
                        null, null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Work_Item_TypesEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public Cat_Work_Item_TypesEntity getCatWorkItemType(long id) {
        Cat_Work_Item_TypesEntity curItem = null;
        String strQuery = "select * from cat_work_item_types where id = " + id;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                curItem = this.converCursorToItem(cursor);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        KttsDatabaseHelper.INSTANCE.close();
        return curItem;
    }

    // Write for test.
    public Cat_Work_Item_TypesEntity getCateByWItemTest(long id, String code) {
        Cat_Work_Item_TypesEntity catWorkItem = null;
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select *  from cat_work_item_types where id = "+ id +" and code = " + "'"+code+"'";
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor.moveToFirst()) {
            catWorkItem = converCursorToItem(cursor);
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return catWorkItem;
    }

    public ArrayList<Cat_Work_Item_TypesEntity> getCatWorkByNode(int constrIdFromNode) {
        ArrayList<Cat_Work_Item_TypesEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        String strQuery = "select * from cat_work_item_types inner join constr_constructions on cat_work_item_types.constr_type_id = constr_constructions.constr_type where constr_constructions.construct_id = " + constrIdFromNode;
        Cursor cursor = db.rawQuery(strQuery,null);
        if (cursor.moveToFirst()) {
            do {
                Cat_Work_Item_TypesEntity curItem = this.converCursorToItem(cursor);
                ret.add(curItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    public ArrayList<Cat_Work_Item_TypesEntity> getCates(String[] codes) {
        ArrayList<Cat_Work_Item_TypesEntity> ret = new ArrayList<>();
        SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
        for (String code : codes){
            Cursor cursor = db
                    .query(Cat_Work_Item_TypesField.TABLE_NAME, allColumn,
                            Cat_Work_Item_TypesField.CODE + " = ? AND "
                                    + Cat_Work_Item_TypesField.CONSTR_TYPE_ID +"=? AND "
                                    + Cat_Work_Item_TypesField.COLUMN_IS_ACTIVE +"=1",
                            new String[] {code, "82"}, null, null,
                            null, null);
            if (cursor.moveToFirst()) {
                do {
                    Cat_Work_Item_TypesEntity curItem = this.converCursorToItem(cursor);
                    ret.add(curItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }

        KttsDatabaseHelper.INSTANCE.close();
        return ret;
    }

    private Cat_Work_Item_TypesEntity converCursorToItem(Cursor cursor) {
        Cat_Work_Item_TypesEntity curItem = new Cat_Work_Item_TypesEntity();
        curItem.setItem_type_id(cursor.getLong(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.ITEM_TYPE_ID)));
        curItem.setItem_type_name(cursor.getString(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.ITEM_TYPE_NAME)));
        curItem.setTemp_group(cursor.getString(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.TEMP_GROUP)));
        curItem.setConstr_type_id(cursor.getLong(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.CONSTR_TYPE_ID)));
        curItem.setUnitName(cursor.getString(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.UNIT_NAME)));
        curItem.setCode(cursor.getString(cursor
                .getColumnIndex(Cat_Work_Item_TypesField.CODE)));
        return curItem;
    }
}
