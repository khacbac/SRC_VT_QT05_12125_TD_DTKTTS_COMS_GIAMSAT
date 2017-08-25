package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Bts_Power_PoleEntity;
import com.viettel.database.field.Cause_Bts_Power_PoleField;
import com.viettel.database.field.Supervision_Bts_Power_PoleField;

public class Supervision_Bts_Power_PoleController {
	private Context mContext = null;

	public Supervision_Bts_Power_PoleController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
			Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME,
			Supervision_Bts_Power_PoleField.COLUMN_STATUS,
			Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC,
			Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
			Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
			Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID,
			Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID ,
			Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID};

	public static final String CREATE_SUPERVISION_BTS_POWER_POLE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Bts_Power_PoleField.TABLE_NAME
			+ "("
			+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
			+ " TEXT PRIMARY KEY ,"
			+ Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME
			+ " TEXT,"
			+ Supervision_Bts_Power_PoleField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT,"
			+ Supervision_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public long insertSupervisionBtsPowerPole(
			Supervision_Bts_Power_PoleEntity entity, long idPowerPole) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(
				Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
				idPowerPole);

		values.put(Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME,
				entity.getPower_POLE_NAME());
		// values.put(Supervision_Bts_Pillar_AntenField.COLUMN_STATUS,
		// entity.getStatus());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC,
				entity.getFail_DESC());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_ID,
				entity.getSupervision_BTS_ID());

		values.put(Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
				entity.getIsActive());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID,
				entity.getProcessId());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID,
				entity.getEmployeeId());
		
		values.put(
				Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID,
				entity.getSupervisionConstrId());

		// Inserting Row
		Long idBtsLast = db.insert(Supervision_Bts_Power_PoleField.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idBtsLast;
	}

	public void updateSupervisionBtsPowerPole(
			Supervision_Bts_Power_PoleEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME,
				entity.getPower_POLE_NAME());

		values.put(Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC,
				entity.getFail_DESC());

		values.put(Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
				entity.getIsActive());
		values.put(Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID,
				entity.getProcessId());

		String[] sqlPara = new String[] { String.valueOf(entity.getSupervision_BTS_POWER_POLE_ID()) };
		db.update(
				Supervision_Bts_Power_PoleField.TABLE_NAME,
				values,
				Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
						+ "= ?" , sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteSupervision_Bts_Power_PoleEntity(
			Supervision_Bts_Power_PoleEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}

		String[] sqlPara = new String[] { String.valueOf(pDelItem.getSupervision_BTS_POWER_POLE_ID()) };
		
		// Update Row
		db.update(
				Supervision_Bts_Power_PoleField.TABLE_NAME,
				values,
				Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
						+ "= ?",
						sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}

}
