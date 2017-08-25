package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.database.entity.Supervision_Bts_EquipEntity;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_EquipField;

public class Supervision_Bts_EquipController {
	private Context mContext = null;

	public Supervision_Bts_EquipController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
			Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID,
			Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID,
			Supervision_Bts_EquipField.COLUMN_2G_TOTAL,
			Supervision_Bts_EquipField.COLUMN_3G_TOTAL,
			Supervision_Bts_EquipField.COLUMN_2G_TYPE,
			Supervision_Bts_EquipField.COLUMN_3G_TYPE,
			Supervision_Bts_EquipField.COLUMN_TRANS_TYPE,
			Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID,
			Supervision_Bts_EquipField.COLUMN_TRANS_FREQ,
			Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION,
			Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID,
			Supervision_Bts_EquipField.COLUMN_PROCESS_ID,
			Supervision_Bts_EquipField.COLUMN_SYNC_STATUS,
			Supervision_Bts_EquipField.COLUMN_IS_ACTIVE,
			Supervision_Bts_EquipField.COLUMN_EMPLOYEE_ID ,
			Supervision_Bts_EquipField.COLUMN_SUPERVISION_CONSTR_ID};

	public static final String CREATE_SUPERVISION_BTS_EQUIP_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supervision_Bts_EquipField.TABLE_NAME
			+ "("
			+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID
			+ " TEXT PRIMARY KEY ,"
			+ Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID
			+ " TEXT,"
			+ Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID
			+ " TEXT,"
			+ Supervision_Bts_EquipField.COLUMN_2G_TOTAL
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_3G_TOTAL
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_3G_TYPE
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_2G_TYPE
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_TRANS_TYPE
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID
			+ " TEXT,"
			+ Supervision_Bts_EquipField.COLUMN_TRANS_FREQ
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION
			+ " REAL,"
			+ Supervision_Bts_EquipField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID
			+ " TEXT,"
			+ Supervision_Bts_EquipField.COLUMN_EMPLOYEE_ID + " TEXT," 
			+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public Supervision_Bts_EquipEntity getSupervision_Bts_EquipEntity(long idBts) {
		Supervision_Bts_EquipEntity result = null;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlQuery = "SELECT S."
				+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID
				+ ", S." + Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID
				+ ", S." + Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID
				+ ", S." + Supervision_Bts_EquipField.COLUMN_2G_TOTAL + " "
				+ ", S." + Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID
				+ " " + ", S." + Supervision_Bts_EquipField.COLUMN_TRANS_FREQ
				+ " " + ", S." + Supervision_Bts_EquipField.COLUMN_TRANS_TYPE
				+ " " + "," + "S."
				+ Supervision_Bts_EquipField.COLUMN_SYNC_STATUS + "," + "S."
				+ Supervision_Bts_EquipField.COLUMN_IS_ACTIVE + "," + "S."
				+ Supervision_Bts_EquipField.COLUMN_PROCESS_ID + ", S."
				+ Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION + " "
				+ ", S." + Supervision_Bts_EquipField.COLUMN_3G_TOTAL 
				+ ", S." + Supervision_Bts_EquipField.COLUMN_2G_TYPE 
				+ ", S."
				+ Supervision_Bts_EquipField.COLUMN_3G_TYPE + "" + " FROM "
				+ Supervision_Bts_EquipField.TABLE_NAME + " S, "
				+ Supervision_BtsField.TABLE_NAME + " SB " + "WHERE S."
				+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID + " = ? "
				+ " AND SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + " = S."
				+ Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID + "";

		String[] sqlPara = new String[] { String.valueOf(idBts) };
		Cursor cu = db.rawQuery(sqlQuery, sqlPara);

		while (cu.moveToNext()) {
			result = new Supervision_Bts_EquipEntity();
			result.setSupervision_BTS_EQUIP_ID(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID)));
			result.setBts_2g_Company_Id(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID)));
			result.setBts_3g_Company_Id(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID)));
			result.set_2g_TOTAL(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_2G_TOTAL)));
			result.set_3g_TOTAL(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_3G_TOTAL)));
			result.set_2g_TYPE(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_2G_TYPE)));
			result.set_3g_TYPE(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_3G_TYPE)));

			result.setTrans_Company_Id(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID)));
			result.setTrans_freq(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_TRANS_FREQ)));
			result.setTrans_Type(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_TRANS_TYPE)));
			result.setTrans_viba_dimension(cu.getFloat(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION)));
			result.setSync_Status(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_SYNC_STATUS)));

			result.setIsActive(cu.getInt(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_IS_ACTIVE)));

			result.setProcessId(cu.getLong(cu
					.getColumnIndex(Supervision_Bts_EquipField.COLUMN_PROCESS_ID)));
		}
		cu.close();
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public Long insertSupervisionBtsEquip(Supervision_Bts_EquipEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();

		values.put(Supervision_Bts_EquipField.COLUMN_SUPERVISION_CONSTR_ID,
				entity.getSupervisionConstrId());
		
		values.put(Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
				entity.getSupervision_BTS_EQUIP_ID());
		values.put(Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID,
				entity.getSupervision_BTS_ID());
		values.put(Supervision_Bts_EquipField.COLUMN_2G_TOTAL,
				entity.get_2g_TOTAL());
		values.put(Supervision_Bts_EquipField.COLUMN_3G_TOTAL,
				entity.get_3g_TOTAL());
		values.put(Supervision_Bts_EquipField.COLUMN_2G_TYPE,
				entity.get_2g_TYPE());
		values.put(Supervision_Bts_EquipField.COLUMN_3G_TYPE,
				entity.get_3g_TYPE());
		values.put(Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID,
				entity.getBts_2g_Company_Id());
		
		values.put(Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID,
				entity.getBts_3g_Company_Id());

		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID,
				entity.getTrans_Company_Id());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_FREQ,
				entity.getTrans_freq());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_TYPE,
				entity.getTrans_Type());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION,
				entity.getTrans_viba_dimension());

		values.put(Supervision_Bts_EquipField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
		values.put(Supervision_Bts_EquipField.COLUMN_IS_ACTIVE,
				entity.getIsActive());
		values.put(Supervision_Bts_EquipField.COLUMN_PROCESS_ID,
				entity.getProcessId());
		values.put(Supervision_Bts_EquipField.COLUMN_EMPLOYEE_ID,
				entity.getEmployeeId());

		// Inserting Row
		Long idBtsEquipLast = db.insert(Supervision_Bts_EquipField.TABLE_NAME,
				null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return idBtsEquipLast;
	}

	public void updateSupervisionBtsEquip(Supervision_Bts_EquipEntity entity) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Supervision_Bts_EquipField.COLUMN_2G_TOTAL,
				entity.get_2g_TOTAL());
		values.put(Supervision_Bts_EquipField.COLUMN_3G_TOTAL,
				entity.get_3g_TOTAL());
		values.put(Supervision_Bts_EquipField.COLUMN_2G_TYPE,
				entity.get_2g_TYPE());
		values.put(Supervision_Bts_EquipField.COLUMN_3G_TYPE,
				entity.get_3g_TYPE());
		values.put(Supervision_Bts_EquipField.COLUMN_BTS_2G_COMPANY_ID,
				entity.getBts_2g_Company_Id());
		
		values.put(Supervision_Bts_EquipField.COLUMN_BTS_3G_COMPANY_ID,
				entity.getBts_3g_Company_Id());

		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_COMPANY_ID,
				entity.getTrans_Company_Id());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_FREQ,
				entity.getTrans_freq());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_TYPE,
				entity.getTrans_Type());
		values.put(Supervision_Bts_EquipField.COLUMN_TRANS_VIBA_DIMENSION,
				entity.getTrans_viba_dimension());

		values.put(Supervision_Bts_EquipField.COLUMN_SYNC_STATUS,
				entity.getSync_Status());
//		values.put(Supervision_Bts_EquipField.COLUMN_IS_ACTIVE,
//				entity.getIsActive());
//		values.put(Supervision_Bts_EquipField.COLUMN_PROCESS_ID,
//				entity.getProcessId());
		String[] sqlPara = new String[] { String.valueOf(entity
				.getSupervision_BTS_ID()) };
		
		db.update(Supervision_Bts_EquipField.TABLE_NAME, values,
				Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_ID + "= ?"
						, sqlPara);
		KttsDatabaseHelper.INSTANCE.close();
	}
}
