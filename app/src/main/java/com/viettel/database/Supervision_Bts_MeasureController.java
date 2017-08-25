package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_Bts_MeasureEntity;
import com.viettel.database.field.Cat_Supv_Constr_MeasureField;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_MeasureField;

public class Supervision_Bts_MeasureController {
	private Context mContext = null;

	public Supervision_Bts_MeasureController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_NAME,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP,
			Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS,
			Supervision_Bts_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
			Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_Bts_MeasureField.COLUMN_PROCESS_ID,
			Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS,
			Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE,
			Supervision_Bts_MeasureField.COLUMN_EMPLOYEE_ID,
			Supervision_Bts_MeasureField.COLUMN_SUPERVISION_CONSTR_ID};

	public static final String CREATE_SUPERVISION_BTS_MEASURE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Bts_MeasureField.TABLE_NAME
			+ "("
			+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_NAME
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE
			+ " REAL,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS
			+ " INTEGER DEFAULT -1,"
			+ Supervision_Bts_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT,"
			+ Supervision_Bts_MeasureField.COLUMN_EMPLOYEE_ID + " TEXT," 
			+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Long insertBtsMeasureEntity(Supervision_Bts_MeasureEntity item) {
		Long idAddItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Bts_MeasureField.COLUMN_SUPERVISION_CONSTR_ID,
				item.getSupervisionConstrId());
		
		values.put(
				Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
				item.getSupervision_BTS_MEASURE_ID());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS,
				item.getMeasure_Status());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE,
				item.getMeasure_VALUE());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL,
				item.getMeasure_MACHINE_SERIAL());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE,
				item.getMeasure_MACHINE_TYPE());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP,
				item.getMeasure_GROUP());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON,
				item.getMeasure_PERSON());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE,
				item.getMeasure_PERSON_MOBILE());
		values.put(Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_ID,
				item.getSupervision_Bts_Id());

		values.put(Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS,
				item.getSync_Status());
		values.put(Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE,
				item.getIsActive());
		values.put(Supervision_Bts_MeasureField.COLUMN_PROCESS_ID,
				item.getProcessId());
		values.put(Supervision_Bts_MeasureField.COLUMN_EMPLOYEE_ID,
				item.getEmployeeId());
		values.put(
				Supervision_Bts_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
				item.getCat_Supv_Constr_Measure_Id());

		// Inserting Row
		idAddItem = db.insert(Supervision_Bts_MeasureField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		return idAddItem;
	}

	public void updateBtsMeasureEntity(Supervision_Bts_MeasureEntity item,
			long id) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS,
				item.getMeasure_Status());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE,
				item.getMeasure_VALUE());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL,
				item.getMeasure_MACHINE_SERIAL());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE,
				item.getMeasure_MACHINE_TYPE());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP,
				item.getMeasure_GROUP());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON,
				item.getMeasure_PERSON());
		values.put(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE,
				item.getMeasure_PERSON_MOBILE());
		values.put(Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS,
				item.getSync_Status());
//		values.put(Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE,
//				item.getIsActive());
		String[] sqlPara = new String[] { String.valueOf(id) };
		db.update(Supervision_Bts_MeasureField.TABLE_NAME, values,
				Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID
						+ "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public Supervision_Bts_MeasureEntity getBtsMeasureEntity(long idBts,
			long cat_constr_measure_id) {
		Supervision_Bts_MeasureEntity result = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		String sqlQuery = "SELECT S."
				+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID
				+ ", "
				+ "S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS
				+ ", S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE
				+ ", S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL
				+ ""
				+ ", S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE
				+ ", S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP
				+ ", S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON
				+ ",S."
				+ Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE
				+ " "
				+ ","
				+ "S."
				+ Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS
				+ ","
				+ "S."
				+ Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE
				+ ","
				+ "S."
				+ Supervision_Bts_MeasureField.COLUMN_PROCESS_ID
				+ " FROM "
				+ Supervision_Bts_MeasureField.TABLE_NAME
				+ " S, "
				+ Cat_Supv_Constr_MeasureField.TABLE_NAME
				+ " CS, "
				+ Supervision_BtsField.TABLE_NAME
				+ " SB "
				+ "WHERE S."
				+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_ID
				+ " = ? "
				+ " AND S."
				+ Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_ID
				+ " = SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " "
				+ "AND S."
				+ Supervision_Bts_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
				+ " = CS."
				+ Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
				+ " "
				+ " AND S."
				+ Supervision_Bts_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID
				+ " = '" + cat_constr_measure_id + "' ";

		String[] sqlPara = new String[] { String.valueOf(idBts) };
		Cursor cu = db.rawQuery(sqlQuery, sqlPara);

		while (cu.moveToNext()) {
			result = new Supervision_Bts_MeasureEntity();
			result.setSupervision_BTS_MEASURE_ID(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID)));

			result.setMeasure_Status(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_STATUS)));

			result.setMeasure_VALUE(cu.getFloat(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_VALUE)));

			result.setMeasure_MACHINE_SERIAL(cu.getString(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_SERIAL)));

			result.setMeasure_MACHINE_TYPE(cu.getString(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_MACHINE_TYPE)));

			result.setMeasure_GROUP(cu.getString(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_GROUP)));

			result.setMeasure_PERSON(cu.getString(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON)));

			result.setMeasure_PERSON_MOBILE(cu.getString(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_MEASURE_PERSON_MOBILE)));

			result.setSync_Status(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_SYNC_STATUS)));

			result.setIsActive(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_IS_ACTIVE)));

			result.setProcessId(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_MeasureField.COLUMN_PROCESS_ID)));
		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;

	}
}
