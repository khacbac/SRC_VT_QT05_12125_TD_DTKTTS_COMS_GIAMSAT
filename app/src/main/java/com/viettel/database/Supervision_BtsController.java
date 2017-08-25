package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_ConstrField;

public class Supervision_BtsController {
	private Context mContext = null;

	public Supervision_BtsController(Context pContext) {
		this.mContext = pContext;

	}

	public static final String[] allColumn = new String[] {
			Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE,
			Supervision_BtsField.COLUMN_PILLAR_ANTEN,
			Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE,
			Supervision_BtsField.COLUMN_PILLAR_HIGH,
			Supervision_BtsField.COLUMN_FOUNDATION_NUM,
			Supervision_BtsField.COLUMN_HOUSE_TYPE,
			Supervision_BtsField.COLUMN_HOUSE_NAME,
			Supervision_BtsField.COLUMN_HOUSE_GENERATOR,
			Supervision_BtsField.COLUMN_PILLAR_LONGITUDE,
			Supervision_BtsField.COLUMN_PILLAR_STATUS_QUALITY,
			Supervision_BtsField.COLUMN_PILLAR_STATUS_WORKING,
			Supervision_BtsField.COLUMN_PILLAR_LATITUDE,
			Supervision_BtsField.COLUMN_POWER_CONNECT_POINT,
			Supervision_BtsField.COLUMN_POWER_POLE_NEW_TOTAL,
			Supervision_BtsField.COLUMN_POWER_CABLE_TYPE,
			Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID,
			Supervision_BtsField.COLUMN_PROCESS_ID,
			Supervision_BtsField.COLUMN_SYNC_STATUS,
			Supervision_BtsField.COLUMN_IS_ACTIVE,
			Supervision_BtsField.COLUMN_EMPLOYEE_ID };

	public static final String CREATE_SUPERVISION_BTS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_BtsField.TABLE_NAME
			+ "("
			+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT PRIMARY KEY,"
			+ Supervision_BtsField.COLUMN_PILLAR_ANTEN
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_PILLAR_HIGH
			+ " REAL,"
			+ Supervision_BtsField.COLUMN_FOUNDATION_NUM
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_HOUSE_TYPE
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_HOUSE_NAME
			+ " TEXT,"
			+ Supervision_BtsField.COLUMN_HOUSE_GENERATOR
			+ " INTEGER DEFAULT -1,"
			+ Supervision_BtsField.COLUMN_PILLAR_LONGITUDE
			+ " REAL DEFAULT -1,"
			+ Supervision_BtsField.COLUMN_PILLAR_STATUS_QUALITY
			+ " INTEGER DEFAULT -1,"
			+ Supervision_BtsField.COLUMN_PILLAR_STATUS_WORKING
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_PILLAR_LATITUDE
			+ " REAL DEFAULT -1,"
			+ Supervision_BtsField.COLUMN_POWER_CONNECT_POINT
			+ " INTEGER DEFAULT 0,"
			+ Supervision_BtsField.COLUMN_POWER_POLE_NEW_TOTAL
			+ " INTEGER DEFAULT -1,"
			+ Supervision_BtsField.COLUMN_POWER_CABLE_TYPE
			+ " TEXT,"
			+ Supervision_BtsField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT,"
			+ Supervision_BtsField.COLUMN_EMPLOYEE_ID + " TEXT" + " )";

	public boolean insertSupervisionBts(Supervision_BtsEntity btsEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
				btsEntity.getSupervision_Bts_Id());

		values.put(Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID, btsEntity
				.getSupervision_ConstrEntity().getSupervision_Constr_Id());
		values.put(Supervision_BtsField.COLUMN_PILLAR_ANTEN,
				btsEntity.getPillar_ANTEN());
		values.put(Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE,
				btsEntity.getPillar_ANTEN_TYPE());
		values.put(Supervision_BtsField.COLUMN_PILLAR_HIGH,
				btsEntity.getPillar_HIGH());
		values.put(Supervision_BtsField.COLUMN_FOUNDATION_NUM,
				btsEntity.getFoundation_NUM());
		values.put(Supervision_BtsField.COLUMN_HOUSE_NAME,
				btsEntity.getHouse_NAME());
		values.put(Supervision_BtsField.COLUMN_HOUSE_TYPE,
				btsEntity.getHouse_TYPE());
		values.put(Supervision_BtsField.COLUMN_HOUSE_GENERATOR,
				btsEntity.getHouse_GENERATOR());
		values.put(Supervision_BtsField.COLUMN_SYNC_STATUS,
				btsEntity.getSync_Status());
		values.put(Supervision_BtsField.COLUMN_IS_ACTIVE,
				btsEntity.getIsActive());
		values.put(Supervision_BtsField.COLUMN_PROCESS_ID,
				btsEntity.getProcessId());
		values.put(Supervision_BtsField.COLUMN_EMPLOYEE_ID,
				btsEntity.getEmployeeId());
		values.put(Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE,
				btsEntity.getConstructionType());

		// Inserting Row
		long idBtsLast = db.insert(Supervision_BtsField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();
		if (idBtsLast > 0) {
			return true;
		} else {
			return false;
		}

	}

	public void updateSupervisionBts(long supervision_bts_id,
			Supervision_BtsEntity btsEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();	
//		values.put(Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID,
//				btsEntity.getSupervisionConstrId());
		values.put(Supervision_BtsField.COLUMN_PILLAR_ANTEN,
				btsEntity.getPillar_ANTEN());
		values.put(Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE,
				btsEntity.getPillar_ANTEN_TYPE());
		values.put(Supervision_BtsField.COLUMN_PILLAR_HIGH,
				btsEntity.getPillar_HIGH());
		values.put(Supervision_BtsField.COLUMN_FOUNDATION_NUM,
				btsEntity.getFoundation_NUM());
		values.put(Supervision_BtsField.COLUMN_HOUSE_NAME,
				btsEntity.getHouse_NAME());
		values.put(Supervision_BtsField.COLUMN_HOUSE_TYPE,
				btsEntity.getHouse_TYPE());
		values.put(Supervision_BtsField.COLUMN_HOUSE_GENERATOR,
				btsEntity.getHouse_GENERATOR());

		values.put(Supervision_BtsField.COLUMN_SYNC_STATUS,
				btsEntity.getSync_Status());
		
		values.put(Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE,
				btsEntity.getConstructionType());
//		values.put(Supervision_BtsField.COLUMN_IS_ACTIVE,
//				btsEntity.getIsActive());
//		values.put(Supervision_BtsField.COLUMN_PROCESS_ID,
//				btsEntity.getProcessId());

		String[] sqlPara = new String[] { String.valueOf(supervision_bts_id) };
		// Update Row
		db.update(Supervision_BtsField.TABLE_NAME, values,
				Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + "= ?"
						, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void updatePillarStatusQuality(Supervision_BtsEntity btsEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		// values.put(Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_NOTE_ID,
		// supervision_constr_note_id);
		values.put(Supervision_BtsField.COLUMN_PILLAR_STATUS_QUALITY,
				btsEntity.getPillar_STATUS_QUALITY());
//		values.put(Supervision_BtsField.COLUMN_PILLAR_LONGITUDE,
//				btsEntity.getPillar_LONGITUDE());
//		values.put(Supervision_BtsField.COLUMN_PILLAR_LATITUDE,
//				btsEntity.getPillar_LATITUDE());
		values.put(Supervision_BtsField.COLUMN_SYNC_STATUS,
				btsEntity.getSync_Status());

		String[] sqlPara = new String[] { String.valueOf(btsEntity.getSupervision_Bts_Id()) };
		// Update Row
		db.update(Supervision_BtsField.TABLE_NAME, values,
				Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}
	
	public void updateLongLat(Supervision_BtsEntity btsEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		// values.put(Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_NOTE_ID,
		// supervision_constr_note_id);
		values.put(Supervision_BtsField.COLUMN_PILLAR_LONGITUDE,
				btsEntity.getPillar_LONGITUDE());
		values.put(Supervision_BtsField.COLUMN_PILLAR_LATITUDE,
				btsEntity.getPillar_LATITUDE());
		values.put(Supervision_BtsField.COLUMN_SYNC_STATUS,
				btsEntity.getSync_Status());

		String[] sqlPara = new String[] { String.valueOf(btsEntity.getSupervision_Bts_Id()) };
		// Update Row
		db.update(Supervision_BtsField.TABLE_NAME, values,
				Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + "= ?"
						 , sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public void updateNumberNewPoleTotal(Supervision_BtsEntity btsEntity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		// values.put(Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_NOTE_ID,
		// supervision_constr_note_id);
		values.put(Supervision_BtsField.COLUMN_POWER_POLE_NEW_TOTAL,
				btsEntity.getPower_POLE_NEW_TOTAL());
		values.put(Supervision_BtsField.COLUMN_POWER_CABLE_TYPE,
				btsEntity.getPower_CABLE_TYPE());
		values.put(Supervision_BtsField.COLUMN_POWER_CONNECT_POINT,
				btsEntity.getPower_CONNECT_POINT());
		values.put(Supervision_BtsField.COLUMN_SYNC_STATUS,
				btsEntity.getSync_Status());

		String[] sqlPara = new String[] { String.valueOf(btsEntity.getSupervision_Bts_Id()) };
		// Update Row
		db.update(Supervision_BtsField.TABLE_NAME, values,
				Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + "= ?", sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}

	public Supervision_BtsEntity getSupervisionBts(long idSuperConstr) {

		Supervision_BtsEntity btsEntity = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String query = "SELECT BTS."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + ",BTS."
				+ Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE + "" + ",BTS."
				+ Supervision_BtsField.COLUMN_PILLAR_ANTEN + ",BTS."
				+ Supervision_BtsField.COLUMN_PILLAR_HIGH + "," + "BTS."
				+ Supervision_BtsField.COLUMN_FOUNDATION_NUM + ",BTS."
				+ Supervision_BtsField.COLUMN_HOUSE_TYPE + "," + "BTS."
				+ Supervision_BtsField.COLUMN_HOUSE_NAME + ",BTS."
				+ Supervision_BtsField.COLUMN_HOUSE_GENERATOR + ",S."
				+ Supervision_ConstrField.COLUMN_DESIGN_TYPE + "," + "S."
				+ Supervision_ConstrField.COLUMN_DESIGN_ASSESS + ",S."
				+ Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION + ",S."
				+ Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID + ","
				+ "BTS." + Supervision_BtsField.COLUMN_PILLAR_STATUS_QUALITY
				+ ",BTS." + Supervision_BtsField.COLUMN_PILLAR_LONGITUDE + ","
				+ "BTS." + Supervision_BtsField.COLUMN_PILLAR_LATITUDE
				+ ",BTS." + Supervision_BtsField.COLUMN_POWER_POLE_NEW_TOTAL
				+ "," + "BTS." + Supervision_BtsField.COLUMN_SYNC_STATUS + ","
				+ "BTS." + Supervision_BtsField.COLUMN_IS_ACTIVE + "," + "BTS."
				+ Supervision_BtsField.COLUMN_PROCESS_ID + "," 
				+ "BTS."
				+ Supervision_BtsField.COLUMN_POWER_CABLE_TYPE 
				+ ",BTS."
				+ Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE
				+ ",BTS."
				+ Supervision_BtsField.COLUMN_POWER_CONNECT_POINT + " FROM "
				+ Supervision_BtsField.TABLE_NAME + " BTS,"
				+ Supervision_ConstrField.TABLE_NAME + " S " + " WHERE " + "S."
				+ Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID
				+ " = BTS." + Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID
				+ " AND " + "BTS."
				+ Supervision_BtsField.COLUMN_SUPERVISION_CONSTR_ID + " = ? and BTS.IS_ACTIVE = 1";

		String[] sqlPara = new String[] { String.valueOf(idSuperConstr)};
		
		Cursor cu = db.rawQuery(query, sqlPara);
		while (cu.moveToNext()) {
			btsEntity = new Supervision_BtsEntity();
			btsEntity
					.setSupervision_Bts_Id(cu.getLong(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID)));

			btsEntity.setConstructionType(cu.getInt(cu.getColumnIndex(Supervision_BtsField.COLUMN_CONSTRUCTION_TYPE)));
			
			btsEntity
					.setPillar_ANTEN_TYPE(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_ANTEN_TYPE)));

			btsEntity.setPillar_ANTEN(cu.getInt(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_ANTEN)));

			btsEntity.setPillar_HIGH(cu.getDouble(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_HIGH)));

			btsEntity
					.setFoundation_NUM(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_FOUNDATION_NUM)));

			btsEntity.setHouse_TYPE(cu.getInt(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_HOUSE_TYPE)));

			btsEntity.setHouse_NAME(cu.getString(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_HOUSE_NAME)));

			btsEntity
					.setHouse_GENERATOR(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_HOUSE_GENERATOR)));

			Supervision_ConstrEntity constrEntity = new Supervision_ConstrEntity();

			constrEntity
					.setDesign_Type(cu.getInt(cu
							.getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_TYPE)));

			constrEntity
					.setDesign_Assess(cu.getInt(cu
							.getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_ASSESS)));

			constrEntity
					.setDesign_Description(cu.getString(cu
							.getColumnIndex(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION)));

			constrEntity
					.setSupervision_Constr_Id(cu.getInt(cu
							.getColumnIndex(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID)));

			btsEntity
					.setPillar_STATUS_QUALITY(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_STATUS_QUALITY)));

			btsEntity
					.setPillar_LONGITUDE(cu.getDouble(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_LONGITUDE)));

			btsEntity
					.setPillar_LATITUDE(cu.getDouble(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_PILLAR_LATITUDE)));

			btsEntity
					.setPower_POLE_NEW_TOTAL(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_POWER_POLE_NEW_TOTAL)));

			btsEntity
					.setPower_CABLE_TYPE(cu.getString(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_POWER_CABLE_TYPE)));

			btsEntity
					.setPower_CONNECT_POINT(cu.getInt(cu
							.getColumnIndex(Supervision_BtsField.COLUMN_POWER_CONNECT_POINT)));

			btsEntity.setSync_Status(cu.getInt(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_SYNC_STATUS)));

			btsEntity.setIsActive(cu.getInt(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_IS_ACTIVE)));

			btsEntity.setProcessId(cu.getLong(cu
					.getColumnIndex(Supervision_BtsField.COLUMN_PROCESS_ID)));

			btsEntity.setSupervision_ConstrEntity(constrEntity);
		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return btsEntity;
	}
}
