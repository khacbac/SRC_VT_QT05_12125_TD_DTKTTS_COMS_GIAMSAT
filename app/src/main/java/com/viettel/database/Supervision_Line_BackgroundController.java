package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supervision_Line_BackgroundField;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BackgroundController {
	private Context mContext = null;

	public static String[] allColumn = new String[] {
			Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
			Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
			Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
			Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
			Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
			Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
			Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
			Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
			Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
			Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
			Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
			Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
			Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
			Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
			Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
			Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
			Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
			Supervision_Line_BackgroundField.COLUMN_EMPLOYEE_ID };

	public static final String CREATE_SUPERVISION_LINE_BACKGROUND_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_BackgroundField.TABLE_NAME
			+ "("
			+ Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL
			+ " REAL,"
			+ Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_BURY_TYPE
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_BURY_LONG
			+ " REAL DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_PIPE_NUM
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MX_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL
			+ " TEXT,"
			+ Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS
			+ " INTEGER,"
			+ Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_BackgroundField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_BackgroundField.COLUMN_EMPLOYEE_ID
			+ " TEXT" + " )";

	public Supervision_Line_BackgroundController(Context pContext) {
		this.mContext = pContext;
	}

	public boolean addItemTank(Supervision_Line_BackgroundEntity addItem){
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
				addItem.getSupervision_Line_Background_Id());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervision_Constr_Id());

		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
				addItem.getLong_Total());

		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
				addItem.getTank_New_Total());

		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
				addItem.getTank_New_One_Part());

		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
				addItem.getTank_New_Two_Part());

		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
				addItem.getTank_New_Three_Part());

		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
				addItem.getTank_Old_Total());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
				addItem.getMeasure_Person());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
				addItem.getMeasure_Person_Mobile());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
				addItem.getMeasure_Group());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
				addItem.getMeasure_Machine_Type());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
				addItem.getMeasure_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_BackgroundField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		Long lIdAddItem = db.insert(
				Supervision_Line_BackgroundField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (lIdAddItem > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addItemCable(Supervision_Line_BackgroundEntity addItem){
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
				addItem.getSupervision_Line_Background_Id());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervision_Constr_Id());

		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
				addItem.getLong_Total());

		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
				addItem.getBury_Type());

		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
				addItem.getBury_Long());

		values.put(Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
				addItem.getCable_type());

		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
				addItem.getPipe_Number());

		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
				addItem.getPipe_Type());

		values.put(Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
				addItem.getMx_Total());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
				addItem.getMeasure_Person());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
				addItem.getMeasure_Person_Mobile());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
				addItem.getMeasure_Group());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
				addItem.getMeasure_Machine_Type());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
				addItem.getMeasure_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_BackgroundField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		Long lIdAddItem = db.insert(
				Supervision_Line_BackgroundField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		if (lIdAddItem > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param addItem
	 * @return
	 */
//	public boolean addItem(Supervision_Line_BackgroundEntity addItem) {
//		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
//		ContentValues values = new ContentValues();
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
//				addItem.getSupervision_Line_Background_Id());
//
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
//				addItem.getSupervision_Constr_Id());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
//				addItem.getLong_Total());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
//				addItem.getTank_New_Total());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
//				addItem.getTank_New_One_Part());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
//				addItem.getTank_New_Two_Part());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
//				addItem.getTank_New_Three_Part());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
//				addItem.getTank_Old_Total());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
//				addItem.getBury_Type());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
//				addItem.getBury_Long());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
//				addItem.getCable_type());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
//				addItem.getPipe_Number());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
//				addItem.getPipe_Type());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
//				addItem.getMx_Total());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
//				addItem.getMeasure_Person());
//
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
//				addItem.getMeasure_Person_Mobile());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
//				addItem.getMeasure_Group());
//
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
//				addItem.getMeasure_Machine_Type());
//
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
//				addItem.getMeasure_Machine_Serial());
//
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
//				addItem.getMeasure_Machine_Serial());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
//				addItem.getMeasure_Status());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
//				addItem.getSync_Status());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
//				addItem.getIsActive());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
//				addItem.getProcessId());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_EMPLOYEE_ID,
//				addItem.getEmployeeId());
//		// Inserting Row
//		Long lIdAddItem = db.insert(
//				Supervision_Line_BackgroundField.TABLE_NAME, null, values);
//		KttsDatabaseHelper.INSTANCE.close();
//		if (lIdAddItem > 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	/**
	 * Lay mot cong trinh giam sat
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public Supervision_Line_BackgroundEntity getItemBySupervisionConstrId(
			long itemId) {
		Supervision_Line_BackgroundEntity curItem = new Supervision_Line_BackgroundEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		Cursor cursor = db.query(Supervision_Line_BackgroundField.TABLE_NAME,
				allColumn,
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID
						+ " = ? and "+Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE + " = ? ", new String[] { String.valueOf(itemId), "1" },
				null, null, null, null);
		if (cursor.moveToLast()) {
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	/**
	 * Ham lay cac ban ghi chua dong bo
	 * 
	 * @param pIdUser
	 * @return
	 */

	public List<Supervision_Line_BackgroundEntity> getSyncSupervisionLineBgByUser(
			long pIdUser) {
		List<Supervision_Line_BackgroundEntity> listItem = new ArrayList<Supervision_Line_BackgroundEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS
				+ " = ?" + " OR "
				+ Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS + " = ?";
		Cursor cursor = db.query(Supervision_Line_BackgroundField.TABLE_NAME,
				allColumn, sqlCause,
				new String[] { String.valueOf(Constants.SYNC_STATUS.ADD),
						String.valueOf(Constants.SYNC_STATUS.EDIT) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_Line_BackgroundEntity curItem = new Supervision_Line_BackgroundEntity();
				curItem = this.converCursorToItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}
	
	private Supervision_Line_BackgroundEntity converCursorToItem(Cursor cursor) {
		Supervision_Line_BackgroundEntity curItem = new Supervision_Line_BackgroundEntity();
		try {
			curItem.setSupervision_Line_Background_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID)));

			curItem.setSupervision_Constr_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID)));

			curItem.setLong_Total(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL)));

			curItem.setTank_New_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL)));

			curItem.setTank_New_One_Part(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART)));

			curItem.setTank_New_Two_Part(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART)));

			curItem.setTank_New_Three_Part(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART)));

			curItem.setTank_Old_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL)));

			curItem.setTank_New_Three_Part(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART)));

			curItem.setBury_Type(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_BURY_TYPE)));

			curItem.setBury_Long(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_BURY_LONG)));

			curItem.setCable_type(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE)));

			curItem.setPipe_Number(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_PIPE_NUM)));

			curItem.setPipe_Type(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE)));

			curItem.setMx_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MX_TOTAL)));

			curItem.setMeasure_Person(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON)));

			curItem.setMeasure_Person_Mobile(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE)));

			curItem.setMeasure_Group(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP)));

			curItem.setMeasure_Machine_Type(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE)));

			curItem.setMeasure_Machine_Serial(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL)));

			curItem.setMeasure_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public boolean updateContrType(long id, int idType, int iSyncStatus) {
		ContentValues values = new ContentValues();

		values.put(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE, idType);
		values.put(Supervision_ConstrField.COLUMN_SYNC_STATUS, iSyncStatus);

		String sqlclause = Supervision_ConstrField.COLUMN_CONSTRUCT_ID + "= ?";
		String[] sqlPara = new String[] { String.valueOf(id) };

		return this.updateDB(Supervision_ConstrField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

//	public boolean updateAllColumn(Supervision_Line_BackgroundEntity itemData) {
//		ContentValues values = new ContentValues();
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
//				itemData.getSupervision_Constr_Id());
//		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
//				itemData.getLong_Total());
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
//				itemData.getTank_New_Total());
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
//				itemData.getTank_New_One_Part());
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
//				itemData.getTank_New_Two_Part());
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
//				itemData.getTank_New_Three_Part());
//		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
//				itemData.getTank_Old_Total());
//		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
//				itemData.getBury_Type());
//		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
//				itemData.getBury_Long());
//		values.put(Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
//				itemData.getCable_type());
//		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
//				itemData.getPipe_Type());
//		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
//				itemData.getPipe_Number());
//		values.put(Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
//				itemData.getMx_Total());
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
//				itemData.getMeasure_Person());
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
//				itemData.getMeasure_Person_Mobile());
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
//				itemData.getMeasure_Group());
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
//				itemData.getMeasure_Machine_Type());
//		values.put(
//				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
//				itemData.getMeasure_Machine_Serial());
//		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
//				itemData.getMeasure_Status());
//		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
//				itemData.getSync_Status());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
//				itemData.getIsActive());
//
//		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
//				itemData.getProcessId());
//
//		String sqlclause = Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
//				+ "= ?";
//		String[] sqlPara = new String[] { String.valueOf(itemData
//				.getSupervision_Line_Background_Id()) };
//		return this.updateDB(Supervision_Line_BackgroundField.TABLE_NAME,
//				values, sqlclause, sqlPara);
//	}
	
	public boolean updateTank(Supervision_Line_BackgroundEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());
		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
				itemData.getLong_Total());
		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
				itemData.getTank_New_Total());
		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
				itemData.getTank_New_One_Part());
		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
				itemData.getTank_New_Two_Part());
		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
				itemData.getTank_New_Three_Part());
		values.put(Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
				itemData.getTank_Old_Total());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());
		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Background_Id()) };
		return this.updateDB(Supervision_Line_BackgroundField.TABLE_NAME,
				values, sqlclause, sqlPara);
	}
	
	public boolean updateCable(Supervision_Line_BackgroundEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());
		values.put(Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
				itemData.getLong_Total());
		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
				itemData.getBury_Type());
		values.put(Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
				itemData.getBury_Long());
		values.put(Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
				itemData.getCable_type());
		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
				itemData.getPipe_Type());
		values.put(Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
				itemData.getPipe_Number());
		values.put(Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
				itemData.getMx_Total());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());
		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Background_Id()) };
		return this.updateDB(Supervision_Line_BackgroundField.TABLE_NAME,
				values, sqlclause, sqlPara);
	}
	
	public boolean updateMx(Supervision_Line_BackgroundEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());
		values.put(
				Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());
		values.put(Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());
		values.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_BackgroundField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Background_Id()) };
		return this.updateDB(Supervision_Line_BackgroundField.TABLE_NAME,
				values, sqlclause, sqlPara);
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
}
