package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_Constr_REQEntity;
import com.viettel.database.field.Supervision_Constr_REQField;
import com.viettel.utils.DateConvert;

import java.util.Date;
import java.util.List;

/**
 * Lop lay du lieu bang quan he nguoi dung va contrinh
 * 
 * @author datht1
 * 
 */
public class Supervision_Constr_REQController {
	private Context mContext = null;
	public static String[] allColumn = new String[] {
			Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
			Supervision_Constr_REQField.COLUMN_REQ_EMPLOYEE_ID,
			Supervision_Constr_REQField.COLUMN_RECV_EMPLOYEE_ID,
			Supervision_Constr_REQField.COLUMN_GROUP_ID,
			Supervision_Constr_REQField.COLUMN_CODE,
			Supervision_Constr_REQField.COLUMN_REQUEST_NAME,
			Supervision_Constr_REQField.COLUMN_RECV_NAME,
			Supervision_Constr_REQField.COLUMN_CONSTR_TYPE,
			Supervision_Constr_REQField.COLUMN_REQUEST_DATE,
			Supervision_Constr_REQField.COLUMN_FINISH_DATE,
			Supervision_Constr_REQField.COLUMN_PLAN_NAME,
			Supervision_Constr_REQField.COLUMN_CREATOR_ID,
			Supervision_Constr_REQField.COLUMN_CREATE_DATE,
			Supervision_Constr_REQField.COLUMN_IS_ACTIVE,
			Supervision_Constr_REQField.COLUMN_PROCESS_ID, 
			Supervision_Constr_REQField.COLUMN_EMPLOYEE_ID};

	public static final String CREATE_SUPERVISION_CONSTR_REQ_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Constr_REQField.TABLE_NAME
			+ "("
			+ Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID
			+ " TEXT PRIMARY KEY ,"
			+ Supervision_Constr_REQField.COLUMN_REQ_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_RECV_EMPLOYEE_ID
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_GROUP_ID
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_CODE
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_REQUEST_NAME
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_RECV_NAME
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_CONSTR_TYPE
			+ " INTEGER,"
			+ Supervision_Constr_REQField.COLUMN_REQUEST_DATE
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_FINISH_DATE
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_PLAN_NAME
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_CREATOR_ID
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_CREATE_DATE
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_IS_ACTIVE
			+ " TEXT,"
			+ Supervision_Constr_REQField.COLUMN_EMPLOYEE_ID
			+ " INTEGER,"
			+ Supervision_Constr_REQField.COLUMN_PROCESS_ID + " INTEGER" + " )";

	public Supervision_Constr_REQController(Context pContext) {
		this.mContext = pContext;
	}

	/* Add login */
	public void addItem(Supervision_Constr_REQEntity addItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
				addItem.getSupervision_Constr_Req_Id());

		values.put(Supervision_Constr_REQField.COLUMN_REQ_EMPLOYEE_ID,
				addItem.getRecv_Name());

		values.put(Supervision_Constr_REQField.COLUMN_RECV_EMPLOYEE_ID,
				addItem.getRecv_Employee_Id());

		values.put(Supervision_Constr_REQField.COLUMN_GROUP_ID,
				addItem.getGroup_Id());

		values.put(Supervision_Constr_REQField.COLUMN_CODE, addItem.getCode());

		values.put(Supervision_Constr_REQField.COLUMN_REQUEST_NAME,
				addItem.getRequest_Name());

		values.put(Supervision_Constr_REQField.COLUMN_RECV_NAME,
				addItem.getRecv_Name());

		values.put(Supervision_Constr_REQField.COLUMN_CONSTR_TYPE,
				addItem.getConstr_Type());

		Date date = addItem.getRequest_Date();
		String sRequestDate = DateConvert.convertDateToString(date);
		values.put(Supervision_Constr_REQField.COLUMN_REQUEST_DATE,
				sRequestDate);

		date = addItem.getFinish_Date();
		String sFinishDate = DateConvert.convertDateToString(date);
		values.put(Supervision_Constr_REQField.COLUMN_FINISH_DATE, sFinishDate);

		values.put(Supervision_Constr_REQField.COLUMN_PLAN_NAME,
				addItem.getPlan_Name());
		values.put(Supervision_Constr_REQField.COLUMN_CREATOR_ID,
				addItem.getCreator_Id());

		date = addItem.getCreate_Date();
		String sCreateDate = DateConvert.convertDateToString(date);
		values.put(Supervision_Constr_REQField.COLUMN_CREATE_DATE, sCreateDate);

		values.put(Supervision_Constr_REQField.COLUMN_IS_ACTIVE,
				addItem.getIs_Active());

		values.put(Supervision_Constr_REQField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		// Inserting Row
		db.insert(Supervision_Constr_REQField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
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
				.query(Supervision_Constr_REQField.TABLE_NAME,
						new String[] { Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID },
						Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID
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
	public boolean updateGetData(List<Supervision_Constr_REQEntity> listData) {
		boolean bResult = false;
		for (Supervision_Constr_REQEntity curItem : listData) {
			if (this.checkExitItem(curItem.getSupervision_Constr_Req_Id())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}
}
