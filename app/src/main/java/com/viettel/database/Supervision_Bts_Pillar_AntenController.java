package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Bts_Pillar_AntenEntity;
import com.viettel.database.field.Supervision_Bts_Pillar_AntenField;

public class Supervision_Bts_Pillar_AntenController {
	private Context mContext = null;

	public Supervision_Bts_Pillar_AntenController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
			Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME,			
			Supervision_Bts_Pillar_AntenField.COLUMN_STATUS,
			Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC,
			Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN,
			Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL,
			Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
			Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID,
			Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
			Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID,
			Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID};

	public static final String CREATE_SUPERVISION_BTS_PILLAR_ANTEN_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Bts_Pillar_AntenField.TABLE_NAME
			+ "("
			+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_LONGITUDE
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_LATITUDE
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE
			+ " INTEGER DEFAULT 1,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT,"
			+ Supervision_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public long insertSupervisionBtsPillarAnten(
			Supervision_Bts_Pillar_AntenEntity entity, long idPillar) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
				idPillar);

		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME,
				entity.getFOUNDATION_NAME());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_STATUS,
				entity.getStatus());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC,
				entity.getFail_DESC());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN,
				entity.getDimension_Design());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL,
				entity.getDimension_Real());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_BTS_ID,
				entity.getSupervision_BTS_ID());

		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
				entity.getIsActive());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID,
				entity.getProcessId());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID,
				entity.getEmployeeId());
		values.put(
				Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID,
				entity.getSupervisionConstrId());

		// Inserting Row
		Long idBtsLast = db.insert(
				Supervision_Bts_Pillar_AntenField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idBtsLast;
	}

	public void updateSupervisionBtsPillarAnten(
			Supervision_Bts_Pillar_AntenEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME,
				entity.getFOUNDATION_NAME());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_STATUS,
				entity.getStatus());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC,
				entity.getFail_DESC());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN,
				entity.getDimension_Design());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL,
				entity.getDimension_Real());
//		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
//				entity.getIsActive());
		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
//		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID,
//				entity.getProcessId());
		
		String[] sqlPara = new String[] { String.valueOf(entity.getSupervision_BTS_PILLAR_ANTEN_ID()) };
		
		db.update(
				Supervision_Bts_Pillar_AntenField.TABLE_NAME,
				values,
				Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
						+ "= ?" ,
						sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteSupervision_Bts_PillarAntenEntity(
			Supervision_Bts_Pillar_AntenEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String[] sqlPara = new String[] { String.valueOf(pDelItem.getSupervision_BTS_PILLAR_ANTEN_ID()) };

		// Update Row
		db.update(
				Supervision_Bts_Pillar_AntenField.TABLE_NAME,
				values,
				Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
						+ "= ?" ,
						sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}

}
