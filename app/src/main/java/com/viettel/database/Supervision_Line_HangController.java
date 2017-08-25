package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supervision_Line_HangField;

/**
 * Lop database truy van bang giam sat cap treo
 * 
 * @author cuongdk3
 * 
 */
public class Supervision_Line_HangController {
	private Context mContext = null;

	public static final String[] allColumn = new String[] {
			Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
			Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL,
			Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE,
			Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH,
			Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG,
			Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL,
			Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG,
			Supervision_Line_HangField.COLUMN_CABLE_RANGE,
			Supervision_Line_HangField.COLUMN_CABLE_TYPE,
			Supervision_Line_HangField.COLUMN_MX_TOTAL,
			Supervision_Line_HangField.COLUMN_LONG_TOTAL,
			Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
			Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
			Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
			Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
			Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
			Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
			Supervision_Line_HangField.COLUMN_SYNC_STATUS,
			Supervision_Line_HangField.COLUMN_IS_ACTIVE,
			Supervision_Line_HangField.COLUMN_PROCESS_ID,
			Supervision_Line_HangField.COLUMN_EMPLOYEE_ID };

	public static final String CREATE_SUPERVISION_LINE_HANG_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Line_HangField.TABLE_NAME
			+ "("
			+ Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE
			+ " INTEGER ,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG
			+ " REAL DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG
			+ " REAL DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_CABLE_RANGE
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_CABLE_TYPE
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_MX_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Line_HangField.COLUMN_LONG_TOTAL
			+ " REAL,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_PERSON
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_GROUP
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL
			+ " TEXT,"
			+ Supervision_Line_HangField.COLUMN_MEASURE_STATUS
			+ " INTEGER,"
			+ Supervision_Line_HangField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Line_HangField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Line_HangField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Line_HangField.COLUMN_EMPLOYEE_ID
			+ " TEXT" + ")";

	public Supervision_Line_HangController(Context pContext) {
		this.mContext = pContext;
	}

	/**
	 * 
	 * @param addItem
	 * @return
	 */
//	public boolean addItem(Supervision_Line_HangEntity addItem) {
//		Long idAddItem = 0L;
//		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
//		ContentValues values = new ContentValues();
//
//		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
//				addItem.getSupervision_Line_Hang_Id());
//
//		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
//				addItem.getSupervision_Constr_Id());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL,
//				addItem.getPillar_New_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE,
//				addItem.getPillar_New_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH,
//				addItem.getPillar_New_High());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG,
//				addItem.getPillar_New_Long());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL,
//				addItem.getPillar_Old_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG,
//				addItem.getPillar_Old_Long());
//
//		values.put(Supervision_Line_HangField.COLUMN_CABLE_RANGE,
//				addItem.getCable_Range());
//
//		values.put(Supervision_Line_HangField.COLUMN_CABLE_TYPE,
//				addItem.getCable_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_MX_TOTAL,
//				addItem.getMx_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
//				addItem.getLong_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
//				addItem.getMeasure_Person());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
//				addItem.getMeasure_Person_Mobile());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
//				addItem.getMeasure_Group());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
//				addItem.getMeasure_Machine_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
//				addItem.getMeasure_Machine_Serial());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
//				addItem.getMeasure_Status());
//
//		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
//				addItem.getSync_Status());
//
//		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
//				addItem.getIsActive());
//
//		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
//				addItem.getProcessId());
//
//		values.put(Supervision_Line_HangField.COLUMN_EMPLOYEE_ID,
//				addItem.getEmployeeId());
//
//		// Inserting Row
//		idAddItem = db.insert(Supervision_Line_HangField.TABLE_NAME, null,
//				values);
//		KttsDatabaseHelper.INSTANCE.close();
//
//		if (idAddItem > 0) {
//			return true;
//		}
//		return false;
//	}
	
	public boolean addPillar(Supervision_Line_HangEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
				addItem.getSupervision_Line_Hang_Id());

		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervision_Constr_Id());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL,
				addItem.getPillar_New_Total());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE,
				addItem.getPillar_New_Type());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH,
				addItem.getPillar_New_High());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG,
				addItem.getPillar_New_Long());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL,
				addItem.getPillar_Old_Total());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG,
				addItem.getPillar_Old_Long());

		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
				addItem.getLong_Total());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
				addItem.getMeasure_Person());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
				addItem.getMeasure_Person_Mobile());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
				addItem.getMeasure_Group());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
				addItem.getMeasure_Machine_Type());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
				addItem.getMeasure_Status());

		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_HangField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_Line_HangField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}
	
	public boolean addCable(Supervision_Line_HangEntity addItem) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
				addItem.getSupervision_Line_Hang_Id());

		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
				addItem.getSupervision_Constr_Id());

		values.put(Supervision_Line_HangField.COLUMN_CABLE_RANGE,
				addItem.getCable_Range());

		values.put(Supervision_Line_HangField.COLUMN_CABLE_TYPE,
				addItem.getCable_Type());

		values.put(Supervision_Line_HangField.COLUMN_MX_TOTAL,
				addItem.getMx_Total());

		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
				addItem.getLong_Total());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
				addItem.getMeasure_Person());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
				addItem.getMeasure_Person_Mobile());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
				addItem.getMeasure_Group());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
				addItem.getMeasure_Machine_Type());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
				addItem.getMeasure_Machine_Serial());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
				addItem.getMeasure_Status());

		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Supervision_Line_HangField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());

		// Inserting Row
		idAddItem = db.insert(Supervision_Line_HangField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idAddItem > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Lay mot cong trinh giam sat
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public Supervision_Line_HangEntity getItemBySupervisionConstrId(long itemId) {
		Supervision_Line_HangEntity curItem = new Supervision_Line_HangEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Supervision_Line_HangField.TABLE_NAME, allColumn,
						Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID
								+ "=? and "+Supervision_Line_HangField.COLUMN_IS_ACTIVE + " = ? ",
						new String[] { String.valueOf(itemId), "1" }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	private Supervision_Line_HangEntity converCursorToItem(Cursor cursor) {
		Supervision_Line_HangEntity curItem = new Supervision_Line_HangEntity();
		try {
			curItem.setSupervision_Line_Hang_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID)));

			curItem.setSupervision_Constr_Id(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID)));

			curItem.setPillar_New_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL)));

			curItem.setPillar_New_Type(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE)));

			curItem.setPillar_New_High(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH)));

			curItem.setPillar_New_Long(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG)));

			curItem.setPillar_Old_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL)));

			curItem.setPillar_Old_Long(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG)));

			curItem.setCable_Range(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_CABLE_RANGE)));

			curItem.setCable_Type(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_CABLE_TYPE)));

			curItem.setMx_Total(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MX_TOTAL)));

			curItem.setLong_Total(cursor.getDouble(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_LONG_TOTAL)));

			curItem.setMeasure_Person(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_PERSON)));

			curItem.setMeasure_Person_Mobile(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE)));

			curItem.setMeasure_Group(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_GROUP)));

			curItem.setMeasure_Machine_Type(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE)));

			curItem.setMeasure_Machine_Serial(cursor.getString(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL)));

			curItem.setMeasure_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_MEASURE_STATUS)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_PROCESS_ID)));

			curItem.setEmployeeId(cursor.getLong(cursor
					.getColumnIndex(Supervision_Line_HangField.COLUMN_EMPLOYEE_ID)));

		} catch (Exception e) {

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

//	public boolean updateAllColumn(Supervision_Line_HangEntity itemData) {
//		ContentValues values = new ContentValues();
//		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
//				itemData.getSupervision_Constr_Id());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL,
//				itemData.getPillar_New_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE,
//				itemData.getPillar_New_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH,
//				itemData.getPillar_New_High());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG,
//				itemData.getPillar_New_Long());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL,
//				itemData.getPillar_Old_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG,
//				itemData.getPillar_Old_Long());
//
//		values.put(Supervision_Line_HangField.COLUMN_CABLE_RANGE,
//				itemData.getCable_Range());
//
//		values.put(Supervision_Line_HangField.COLUMN_CABLE_TYPE,
//				itemData.getCable_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_MX_TOTAL,
//				itemData.getMx_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
//				itemData.getLong_Total());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
//				itemData.getMeasure_Person());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
//				itemData.getMeasure_Person_Mobile());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
//				itemData.getMeasure_Group());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
//				itemData.getMeasure_Machine_Type());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
//				itemData.getMeasure_Machine_Serial());
//
//		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
//				itemData.getMeasure_Status());
//
//		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
//				itemData.getSync_Status());
//
//		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
//				itemData.getIsActive());
//
//		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
//				itemData.getProcessId());
//
//		String sqlclause = Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID
//				+ "= ?";
//		String[] sqlPara = new String[] { String.valueOf(itemData
//				.getSupervision_Line_Hang_Id()) };
//		return this.updateDB(Supervision_Line_HangField.TABLE_NAME, values,
//				sqlclause, sqlPara);
//	}
	
	public boolean updateMx(Supervision_Line_HangEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());

		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Hang_Id()) };
		return this.updateDB(Supervision_Line_HangField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updatePillar(Supervision_Line_HangEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TOTAL,
				itemData.getPillar_New_Total());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_TYPE,
				itemData.getPillar_New_Type());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_HIGH,
				itemData.getPillar_New_High());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_NEW_LONG,
				itemData.getPillar_New_Long());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_TOTAL,
				itemData.getPillar_Old_Total());

		values.put(Supervision_Line_HangField.COLUMN_PILLAR_OLD_LONG,
				itemData.getPillar_Old_Long());

		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
				itemData.getLong_Total());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());

		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Hang_Id()) };
		return this.updateDB(Supervision_Line_HangField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}
	
	public boolean updateCable(Supervision_Line_HangEntity itemData) {
		ContentValues values = new ContentValues();
		values.put(Supervision_Line_HangField.COLUMN_SUPERVISION_CONSTR_ID,
				itemData.getSupervision_Constr_Id());

		values.put(Supervision_Line_HangField.COLUMN_CABLE_RANGE,
				itemData.getCable_Range());

		values.put(Supervision_Line_HangField.COLUMN_CABLE_TYPE,
				itemData.getCable_Type());

		values.put(Supervision_Line_HangField.COLUMN_MX_TOTAL,
				itemData.getMx_Total());

		values.put(Supervision_Line_HangField.COLUMN_LONG_TOTAL,
				itemData.getLong_Total());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON,
				itemData.getMeasure_Person());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_PERSON_MOBILE,
				itemData.getMeasure_Person_Mobile());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_GROUP,
				itemData.getMeasure_Group());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_TYPE,
				itemData.getMeasure_Machine_Type());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_MACHINE_SERIAL,
				itemData.getMeasure_Machine_Serial());

		values.put(Supervision_Line_HangField.COLUMN_MEASURE_STATUS,
				itemData.getMeasure_Status());

		values.put(Supervision_Line_HangField.COLUMN_SYNC_STATUS,
				itemData.getSync_Status());

		values.put(Supervision_Line_HangField.COLUMN_IS_ACTIVE,
				itemData.getIsActive());

		values.put(Supervision_Line_HangField.COLUMN_PROCESS_ID,
				itemData.getProcessId());

		String sqlclause = Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(itemData
				.getSupervision_Line_Hang_Id()) };
		return this.updateDB(Supervision_Line_HangField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}
	
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
