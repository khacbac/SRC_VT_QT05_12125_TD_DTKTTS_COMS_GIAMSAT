package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.CatConstructEntity;
import com.viettel.database.entity.CatConstructProgressEntity;
import com.viettel.database.entity.ConstrProgressEntity;
import com.viettel.database.entity.Supervision_CNDTCEntity;
import com.viettel.database.field.Cat_Construct_Field;
import com.viettel.database.field.Constr_Progress_Field;
import com.viettel.database.field.Supervision_CNDTCField;
import com.viettel.utils.DateConvert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Supervision_CNDTCController {
	private Context mContext = null;
	public static final String CREATE_SUPERVISION_CNDTC_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_CNDTCField.TABLE_NAME
			+ "("
			+ Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_CNDTCField.COLUMN_CONSTRUCT_ID
			+ " TEXT,"
			+ Supervision_CNDTCField.COLUMN_REPORT_DATE
			+ " TEXT,"
			+ Supervision_CNDTCField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_CNDTCField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_CNDTCField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_CNDTCField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_CNDTCField.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public static final String CREATE_CAT_CONSTRUCT_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cat_Construct_Field.TABLE_NAME
			+ "("
			+ Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID
			+ " TEXT PRIMARY KEY,"
			+ Cat_Construct_Field.COLUMN_CODE
			+ " TEXT,"
			+ Cat_Construct_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Constr_Progress_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cat_Construct_Field.COLUMN_NAME + " TEXT)";

	public static final String CREATE_CONSTR_PROGRESS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Constr_Progress_Field.TABLE_NAME
			+ "("
			+ Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID
			+ " TEXT PRIMARY KEY,"
			+ Constr_Progress_Field.COLUMN_CAT_CONSTRUCT_ID
			+ " TEXT,"
			+ Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID
			+ " TEXT,"
			+ Constr_Progress_Field.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Constr_Progress_Field.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Constr_Progress_Field.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Constr_Progress_Field.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_NUMBER_EMPLOYEES
			+ " INTEGER,"
			+ Constr_Progress_Field.COLUMN_SUPERVISION_CONSTR_ID + " TEXT)";

	public static final String[] allColumnConstrProgressContruct = new String[] {
			Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
			Supervision_CNDTCField.COLUMN_CONSTRUCT_ID,
			Supervision_CNDTCField.COLUMN_REPORT_DATE,
			Supervision_CNDTCField.COLUMN_EMPLOYEE_ID,
			Supervision_CNDTCField.COLUMN_PROCESS_ID,
			Supervision_CNDTCField.COLUMN_SYNC_STATUS,
			Supervision_CNDTCField.COLUMN_IS_ACTIVE,
			Supervision_CNDTCField.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String[] allColumnCatContruct = new String[] {
			Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID,
			Cat_Construct_Field.COLUMN_CODE, Cat_Construct_Field.COLUMN_NAME,
			Cat_Construct_Field.COLUMN_IS_ACTIVE,Cat_Construct_Field.COLUMN_PROCESS_ID};

	public static final String[] allColumnConstrProgress = new String[] {
			Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
			Constr_Progress_Field.COLUMN_CAT_CONSTRUCT_ID,
			Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID ,
			Constr_Progress_Field.COLUMN_EMPLOYEE_ID,
			Constr_Progress_Field.COLUMN_PROCESS_ID,
			Constr_Progress_Field.COLUMN_SYNC_STATUS,
			Constr_Progress_Field.COLUMN_IS_ACTIVE,
			Constr_Progress_Field.COLUMN_SUPERVISION_CONSTR_ID,
			Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_NUMBER_EMPLOYEES};

	public Supervision_CNDTCController(Context pContext) {
		this.mContext = pContext;
	}

	/*
	 * Them moi bang cap nhat doi thi cong
	 * 
	 * @param addItem cap nhat doi thi cong
	 * 
	 * @return true neu them moi thanh cong , false neu them moi that bai
	 */
	public boolean addItem(Supervision_CNDTCEntity addItem) {
		boolean bResult = false;

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
				addItem.getSupervisionProgressContructId());

		values.put(Supervision_CNDTCField.COLUMN_CONSTRUCT_ID,
				addItem.getConstructId());
		
		Date date = addItem.getReportDate();
		String reportDate = DateConvert.convertDateToString(date);
		values.put(Supervision_CNDTCField.COLUMN_REPORT_DATE,
				reportDate);

		values.put(Supervision_CNDTCField.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		values.put(Supervision_CNDTCField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_CNDTCField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_CNDTCField.COLUMN_SYNC_STATUS,
				addItem.getSyncStatus());

		values.put(Supervision_CNDTCField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		db.insert(Supervision_CNDTCField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		bResult = true;
		return bResult;
	}
	
	public boolean updateConstrProgressConstruct(Supervision_CNDTCEntity addItem){
		boolean bResult = false;
		ContentValues values = new ContentValues();

		values.put(Supervision_CNDTCField.COLUMN_CONSTRUCT_ID,
				addItem.getConstructId());
		
		Date date = addItem.getReportDate();
		String reportDate = DateConvert.convertDateToString(date);
		values.put(Supervision_CNDTCField.COLUMN_REPORT_DATE,
				reportDate);

		values.put(Supervision_CNDTCField.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		values.put(Supervision_CNDTCField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Supervision_CNDTCField.COLUMN_SYNC_STATUS,
				addItem.getSyncStatus());

		values.put(Supervision_CNDTCField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		
		// Inserting Row
		String sqlclause = Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(addItem.getSupervisionProgressContructId()) };
		bResult = this.updateDB(Supervision_CNDTCField.TABLE_NAME, values, sqlclause, sqlPara);
		return bResult;
	}
	

	public boolean addItemConstrProcess(ConstrProgressEntity addItem) {
		boolean bResult = false;

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
				addItem.getConstrProgressId());

		values.put(Constr_Progress_Field.COLUMN_CAT_CONSTRUCT_ID,
				addItem.getCatConstructId());

		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
				addItem.getConstrProgressConstructId());
		
		values.put(Constr_Progress_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		values.put(Constr_Progress_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Constr_Progress_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Constr_Progress_Field.COLUMN_SYNC_STATUS,
				addItem.getSyncStatus());

		values.put(Constr_Progress_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_NUMBER_EMPLOYEES,
				addItem.getConstrProgressNumberEmployees());

		// Inserting Row
		db.insert(Constr_Progress_Field.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		bResult = true;
		return bResult;
	}
	
	public boolean updateConstrProgress(ConstrProgressEntity addItem){
		boolean bResult = false;
		ContentValues values = new ContentValues();
//		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
//				addItem.getConstrProgressId());

		values.put(Constr_Progress_Field.COLUMN_CAT_CONSTRUCT_ID,
				addItem.getCatConstructId());

//		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
//				addItem.getConstrProgressConstructId());
		
		values.put(Constr_Progress_Field.COLUMN_IS_ACTIVE, Constants.IS_ACTIVE);

		values.put(Constr_Progress_Field.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Constr_Progress_Field.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervisionConstrId());

		values.put(Constr_Progress_Field.COLUMN_SYNC_STATUS,
				addItem.getSyncStatus());

		values.put(Constr_Progress_Field.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		values.put(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_NUMBER_EMPLOYEES,
				addItem.getConstrProgressNumberEmployees());
		
		// Inserting Row
		String sqlclause = Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(addItem.getConstrProgressId()) };
		bResult = this.updateDB(Constr_Progress_Field.TABLE_NAME, values, sqlclause, sqlPara);
		return bResult;
	}

	//KienPV add new 12/09/2016
	public List<CatConstructProgressEntity> getItemCatConstruct(){
		//KienPV test
		//return datatest();
		String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		String[] q = {date};
		String sql = "Select CAT_CONSTRUCT.*,CONSTR_PROGRESS.CONSTR_PROGRESS_NUMBER_EMPLOYEES From CONSTR_PROGRESS"+ 
				" INNER JOIN CONSTR_PROGRESS_CONSTRUCT ON CONSTR_PROGRESS_CONSTRUCT.CONSTR_PROGRESS_CONSTRUCT_ID = CONSTR_PROGRESS.CONSTR_PROGRESS_CONTRUCT_ID"+
				" INNER JOIN CAT_CONSTRUCT ON CAT_CONSTRUCT.CAT_CONSTRUCT_ID = CONSTR_PROGRESS.CAT_CONSTRUCT_ID"+
				" WHERE CONSTR_PROGRESS_CONSTRUCT.REPORT_DATE = ?";
		List<CatConstructProgressEntity> result = new ArrayList<CatConstructProgressEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		try{
			Cursor cursor = db.rawQuery(sql, q);
			if (cursor != null) {
				cursor.moveToFirst();
				while(!cursor.isAfterLast()){
					CatConstructProgressEntity entity = cursorToValue(cursor);
					result.add(entity);
			        cursor.moveToNext();
				}
			}
			cursor.close();
			KttsDatabaseHelper.INSTANCE.close();
		}catch(SQLException ex){
			ex.getMessage();
		};
		return result;
	}
	public List<CatConstructProgressEntity> datatest(){
		List<CatConstructProgressEntity> result = new ArrayList<CatConstructProgressEntity>();
		CatConstructProgressEntity en = new CatConstructProgressEntity();
		en.setCatConstructId(1);
		en.setCode("XD");
		en.setNumber(10);
		en.setIsActive(1);
		en.setName("Xay dung");
		result.add(en);
		CatConstructProgressEntity en1 = new CatConstructProgressEntity();
		en1.setCatConstructId(2);
		en1.setCode("LD");
		en1.setIsActive(1);
		en1.setName("Lap dung");
		en1.setNumber(200);
		result.add(en1);
		CatConstructProgressEntity en2 = new CatConstructProgressEntity();
		en2.setCatConstructId(3);
		en2.setCode("TB");
		en2.setIsActive(0);
		en2.setNumber(400);
		en2.setName("Thiet bi");
		result.add(en2);
		return result;
	}
	//KienPV add new 12/09/2016
	private CatConstructProgressEntity cursorToValue(Cursor cursor) {
		CatConstructProgressEntity result = new CatConstructProgressEntity();
		result.setCatConstructId(cursor.getLong(0));
		result.setCode(cursor.getString(1));
		result.setIsActive(cursor.getInt(2));
		result.setName(cursor.getString(3));
		result.setNumber(cursor.getInt(4));
		return result;
    }
	//---
	public CatConstructEntity getItemCatConstruct(String code) {
		CatConstructEntity curItem = new CatConstructEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Cat_Construct_Field.TABLE_NAME,
				new String[] { Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID },
				Cat_Construct_Field.COLUMN_CODE + "=? AND "
						+ Cat_Construct_Field.COLUMN_IS_ACTIVE + " = ?",
				new String[] { code, String.valueOf(Constants.IS_ACTIVE) },
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

	/**
	 * Ham update trang thai dong bo
	 * 
	 * @param id
	 *            Id cong trinh
	 * @return
	 */
	public boolean updateSyncStatus(long id) {
		ContentValues values = new ContentValues();
		values.put(Supervision_CNDTCField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.EDIT);
		String sqlclause = Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };
		return this.updateDB(Supervision_CNDTCField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

//	public boolean delete(Date reportDate,String constructId) {
//		boolean bResult = false;
//		ContentValues values = new ContentValues();
//		// Xoa ban ghi cu trong ngay
//		values.put(Supervision_CNDTCField.COLUMN_IS_ACTIVE,
//				Constants.ISACTIVE.DEACTIVE);
//
//		values.put(Supervision_CNDTCField.COLUMN_SYNC_STATUS,
//				Constants.SYNC_STATUS.EDIT);
//
//		String sqlclause = Supervision_CNDTCField.COLUMN_REPORT_DATE + "= ? " + " AND " + Supervision_CNDTCField.COLUMN_CONSTRUCT_ID + " = ? ";
//		String[] sqlPara = new String[] { DateConvert.convertDateToString(reportDate),constructId };
//		bResult = this.updateDB(Supervision_CNDTCField.TABLE_NAME, values,
//				sqlclause, sqlPara);
//		return bResult;
//	}
	
	public boolean checkItemByDate(Date reportDate,String constructId){
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_CNDTCField.TABLE_NAME, allColumnConstrProgressContruct,
						Supervision_CNDTCField.COLUMN_REPORT_DATE
								+ "=? AND " + Supervision_CNDTCField.COLUMN_CONSTRUCT_ID
								+ "=?",
						new String[] {DateConvert.convertDateToString(reportDate),constructId}, null, null,
						null, null);
		if (cursor != null && cursor.moveToFirst()) {
			bResult =  true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}
	
	public Supervision_CNDTCEntity converCursorToItem(Cursor cursor){
		Supervision_CNDTCEntity supervision_CNDTCEntity = new Supervision_CNDTCEntity();
		try {
			supervision_CNDTCEntity.setConstructId(cursor.getLong(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_CONSTRUCT_ID)));
			supervision_CNDTCEntity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_EMPLOYEE_ID)));
			supervision_CNDTCEntity.setIsActive(cursor.getInt(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_IS_ACTIVE)));
			supervision_CNDTCEntity.setReportDate((DateConvert.convertStringToDate(cursor.getString(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_REPORT_DATE)))));
			supervision_CNDTCEntity.setSyncStatus(cursor.getInt(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_SYNC_STATUS)));
			supervision_CNDTCEntity.setProcessId(cursor.getLong(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_PROCESS_ID)));
			supervision_CNDTCEntity.setSupervisionConstrId(cursor.getLong(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_SUPERVISION_CONSTR_ID)));
			supervision_CNDTCEntity.setSupervisionProgressContructId(cursor.getLong(cursor.getColumnIndex(Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID)));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return supervision_CNDTCEntity;
	}
	
	public Supervision_CNDTCEntity getCNDTCItem(Date reportDate,String constructId){
		Supervision_CNDTCEntity curItem = new Supervision_CNDTCEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_CNDTCField.TABLE_NAME, allColumnConstrProgressContruct,
						Supervision_CNDTCField.COLUMN_CONSTRUCT_ID
								+ "=? AND "+Supervision_CNDTCField.COLUMN_IS_ACTIVE
								+ "=? AND " + Supervision_CNDTCField.COLUMN_REPORT_DATE + " =? ",
						new String[] { constructId,String.valueOf(Constants.ISACTIVE.ACTIVE),DateConvert.convertDateToString(reportDate) }, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}
	
	public ConstrProgressEntity converCursorToConstrItem(Cursor cursor){
		ConstrProgressEntity constrProgressEntity = new ConstrProgressEntity();
		try {
			constrProgressEntity.setEmployeeId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_EMPLOYEE_ID)));
			constrProgressEntity.setIsActive(cursor.getInt(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_IS_ACTIVE)));
			constrProgressEntity.setSyncStatus(cursor.getInt(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_SYNC_STATUS)));
			constrProgressEntity.setProcessId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_PROCESS_ID)));
			constrProgressEntity.setSupervisionConstrId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_SUPERVISION_CONSTR_ID)));
			constrProgressEntity.setCatConstructId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_CAT_CONSTRUCT_ID)));
			constrProgressEntity.setConstrProgressConstructId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID)));
			constrProgressEntity.setConstrProgressNumberEmployees(cursor.getInt(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_NUMBER_EMPLOYEES)));
			constrProgressEntity.setConstrProgressId(cursor.getLong(cursor.getColumnIndex(Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID)));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return constrProgressEntity;
	}
	
	public ConstrProgressEntity getConstrProgressItem(long constrProgressId){
		ConstrProgressEntity curItem = new ConstrProgressEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Constr_Progress_Field.TABLE_NAME, allColumnConstrProgress,
						Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID
								+ "=? AND "+Supervision_CNDTCField.COLUMN_IS_ACTIVE
								+ "=? ",
						new String[] { String.valueOf(constrProgressId),String.valueOf(Constants.ISACTIVE.ACTIVE) }, null, null,
						null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem = this.converCursorToConstrItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
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
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_CNDTCField.TABLE_NAME,
						new String[] { Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID },
						Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID
								+ "=?",
						new String[] { String.valueOf(itemId) }, null, null,
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
	public boolean updateGetData(List<Supervision_CNDTCEntity> listData) {
		boolean bResult = false;
		for (Supervision_CNDTCEntity curItem : listData) {
			if (this.checkExitItem(curItem.getSupervisionProgressContructId())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}

}
