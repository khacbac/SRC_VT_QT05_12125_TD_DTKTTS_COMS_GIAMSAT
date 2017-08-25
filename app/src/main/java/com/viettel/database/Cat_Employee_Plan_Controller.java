package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.common.DateConvert;
import com.viettel.common.DateUtil;
import com.viettel.constants.Constants;
import com.viettel.database.entity.Cat_Employee_Plan_Entity;
import com.viettel.database.field.Cat_Employee_Plan_Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cat_Employee_Plan_Controller {
	private Context mContext = null;
	public Cat_Employee_Plan_Controller(Context context){
		this.mContext = context;
	}
	public static final String[] allColumn = new String[] {
			Cat_Employee_Plan_Field.COLUMN_PLAN_ID,
			Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID,
			Cat_Employee_Plan_Field.COLUMN_PLAN_TEXT,
			Cat_Employee_Plan_Field.COLUMN_FROM_DATE,
			Cat_Employee_Plan_Field.COLUMN_TO_DATE,
			Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE,
			Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS,
			Cat_Employee_Plan_Field.COLUMN_PROCESS_ID };

	public static final String CREATE_PLAN_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Employee_Plan_Field.TABLE_NAME + "("
			+ Cat_Employee_Plan_Field.COLUMN_PLAN_ID +" TEXT PRIMARY KEY,"
			+ Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID + " TEXT,"
			+ Cat_Employee_Plan_Field.COLUMN_PLAN_TEXT + " TEXT,"
			+ Cat_Employee_Plan_Field.COLUMN_FROM_DATE + " TEXT,"
			+ Cat_Employee_Plan_Field.COLUMN_TO_DATE + " TEXT,"
			+ Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE + " INTEGER,"
			+ Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS + " INTEGER,"
			+ Cat_Employee_Plan_Field.COLUMN_PROCESS_ID 
			+ " INTEGER)";
	
	public boolean insertPlanEntity(Cat_Employee_Plan_Entity cat_Employee_Plan_Entity){
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Cat_Employee_Plan_Field.COLUMN_PLAN_ID, cat_Employee_Plan_Entity.getPlanID());
		values.put(Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID, cat_Employee_Plan_Entity.getEmployeeID());
		values.put(Cat_Employee_Plan_Field.COLUMN_PLAN_TEXT, cat_Employee_Plan_Entity.getPlanText());
		values.put(Cat_Employee_Plan_Field.COLUMN_FROM_DATE, DateUtil.convertDateToStringPlan(cat_Employee_Plan_Entity.getFromDate()));
		values.put(Cat_Employee_Plan_Field.COLUMN_TO_DATE, DateConvert.convertDateToString(cat_Employee_Plan_Entity.getToDate()));
		values.put(Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE, cat_Employee_Plan_Entity.getIsActive());
		values.put(Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS, cat_Employee_Plan_Entity.getSync_Status());
		values.put(Cat_Employee_Plan_Field.COLUMN_PROCESS_ID, cat_Employee_Plan_Entity.getProcessId());
		
		// Inserting Row
		db.insert(Cat_Employee_Plan_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		bResult = true;
		return bResult;
	}
	
	public Cat_Employee_Plan_Entity getPlanItem(long itemId){
		Cat_Employee_Plan_Entity curItem = new Cat_Employee_Plan_Entity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Employee_Plan_Field.TABLE_NAME, allColumn,
						Cat_Employee_Plan_Field.COLUMN_PLAN_ID
								+ "=? AND "+Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE
								+ "=?",
						new String[] { String.valueOf(itemId),String.valueOf(Constants.ISACTIVE.ACTIVE) }, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}
	
	public boolean checkItemByDate(Date fromDate){
		Cat_Employee_Plan_Entity curItem = new Cat_Employee_Plan_Entity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Employee_Plan_Field.TABLE_NAME, allColumn,
						Cat_Employee_Plan_Field.COLUMN_FROM_DATE
								+ "=? AND " + Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE
								+ "=?",
						new String[] {DateUtil.convertDateToStringPlan(fromDate),String.valueOf(Constants.ISACTIVE.ACTIVE)}, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
			if(curItem.getFromDate()!=null){
				return true;
			}
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return false;
	}
	
	public boolean checkItemByID(long itemId){
		Cat_Employee_Plan_Entity curItem = new Cat_Employee_Plan_Entity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Cat_Employee_Plan_Field.TABLE_NAME, allColumn,
						Cat_Employee_Plan_Field.COLUMN_PLAN_ID
								+ "=? AND " + Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE
								+ "=?",
						new String[] { String.valueOf(itemId) ,String.valueOf(Constants.ISACTIVE.ACTIVE)}, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
			if(curItem.getFromDate()!=null){
				return true;
			}
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return false;
	}
	
	
	public List<Cat_Employee_Plan_Entity> getListPlan(int start,int limit,long employeeId){
		List<Cat_Employee_Plan_Entity> lstPlan = new ArrayList<Cat_Employee_Plan_Entity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
//		String querry ="SELECt * from " + Cat_Employee_Plan_Field.TABLE_NAME
//				+" WHERE IS_ACTIVE = " + Constants.ISACTIVE.ACTIVE
//				+" ORDER BY " + Cat_Employee_Plan_Field.COLUMN_FROM_DATE +" DESC";
		Cursor cursor = db
				.query(Cat_Employee_Plan_Field.TABLE_NAME, allColumn,
						Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE
								+ "=?"+ " AND "+Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID+"=?",
						new String[] { String.valueOf(Constants.ISACTIVE.ACTIVE),String.valueOf(employeeId) }, null, null,
						Cat_Employee_Plan_Field.COLUMN_TO_DATE+" DESC",start+","+limit);
		
//		Cursor cursor = db
//				.query(Cat_Employee_Plan_Field.TABLE_NAME, allColumn,
//						Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE
//								+ "=?",
//						new String[] { String.valueOf(Constants.ISACTIVE.ACTIVE) }, null, null,
//						Cat_Employee_Plan_Field.COLUMN_FROM_DATE+" DESC",null);
//		Cursor cursor = db.rawQuery(querry, null);
		if (cursor.moveToFirst()) {
			do {
				Cat_Employee_Plan_Entity curItem = new Cat_Employee_Plan_Entity();
				curItem = this.converCursorToItem(cursor);
				lstPlan.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return lstPlan;
	}
	
	
	public Cat_Employee_Plan_Entity converCursorToItem(Cursor cursor){
		Cat_Employee_Plan_Entity cat_Employee_Plan_Entity = new Cat_Employee_Plan_Entity();
		try {
			cat_Employee_Plan_Entity.setPlanID(cursor.getLong(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_PLAN_ID)));
			cat_Employee_Plan_Entity.setEmployeeID(cursor.getLong(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID)));
			cat_Employee_Plan_Entity.setPlanText(cursor.getString(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_PLAN_TEXT)));

			cat_Employee_Plan_Entity.setFromDate(DateConvert.convertStringToDate(cursor.getString(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_TO_DATE))));
			cat_Employee_Plan_Entity.setToDate(DateUtil.convertStringToDatePlan(cursor.getString(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_FROM_DATE))));
			cat_Employee_Plan_Entity.setIsActive(cursor.getInt(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE)));
			cat_Employee_Plan_Entity.setSync_Status(cursor.getInt(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS)));
			cat_Employee_Plan_Entity.setProcessId(cursor.getLong(cursor.getColumnIndex(Cat_Employee_Plan_Field.COLUMN_PROCESS_ID)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cat_Employee_Plan_Entity;
	}
	
	public boolean updatePlanEntity(Cat_Employee_Plan_Entity cat_Employee_Plan_Entity){
		boolean bResult = false;
//		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Cat_Employee_Plan_Field.COLUMN_PLAN_ID, cat_Employee_Plan_Entity.getPlanID());
		values.put(Cat_Employee_Plan_Field.COLUMN_EMPLOYEE_ID, cat_Employee_Plan_Entity.getEmployeeID());
		values.put(Cat_Employee_Plan_Field.COLUMN_PLAN_TEXT, cat_Employee_Plan_Entity.getPlanText());
		values.put(Cat_Employee_Plan_Field.COLUMN_FROM_DATE, DateUtil.convertDateToStringPlan(cat_Employee_Plan_Entity.getFromDate()));
		values.put(Cat_Employee_Plan_Field.COLUMN_TO_DATE, DateConvert.convertDateToString(cat_Employee_Plan_Entity.getToDate()));
		values.put(Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE, cat_Employee_Plan_Entity.getIsActive());
		values.put(Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS, cat_Employee_Plan_Entity.getSync_Status());
//		values.put(Cat_Employee_Plan_Field.COLUMN_PROCESS_ID, cat_Employee_Plan_Entity.getProcessId());
		
		// Inserting Row
		String sqlclause = Cat_Employee_Plan_Field.COLUMN_PLAN_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(cat_Employee_Plan_Entity.getPlanID()) };
		bResult = this.updateDB(Cat_Employee_Plan_Field.TABLE_NAME, values, sqlclause, sqlPara);
		return bResult;
	}
	
	/**
	 * 
	 * @param sTable
	 * @param values
	 * @param sqlClause
	 * @param sqlPara
	 * @return
	 */
	public boolean updateDB(String sTable, ContentValues values,
			String sqlClause, String[] sqlPara) {
		boolean bResult = true;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		int iRow = db.update(sTable, values, sqlClause, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
		if (iRow > 0) {
			bResult = true;
		}
		return bResult;
	}
	
	public boolean deletePlanEntity(Cat_Employee_Plan_Entity planEntity){
		boolean bResult = false;
		ContentValues values = new ContentValues();
		values.put(Cat_Employee_Plan_Field.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (planEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Cat_Employee_Plan_Field.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String sqlclause = Cat_Employee_Plan_Field.COLUMN_PLAN_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(planEntity.getPlanID()) };
		bResult = this.updateDB(Cat_Employee_Plan_Field.TABLE_NAME, values,
				sqlclause, sqlPara);
		return bResult;
	}
	
//	public boolean deleteItem(Cat_Employee_Plan_Entity plan_Entity){
//		boolean wasDel = false;
//		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
//		db.delete(Cat_Employee_Plan_Field.TABLE_NAME, Cat_Employee_Plan_Field.COLUMN_PLAN_ID + " = ? ", new String[]{String.valueOf(plan_Entity.getPlanID())});
//		KttsDatabaseHelper.INSTANCE.close();
//		wasDel = true;
//		return wasDel;
//	}
	
}
