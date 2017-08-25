package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.database.entity.Supervision_Bts_Power_PoleEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;
import com.viettel.database.field.Cause_Bts_Pillar_AntenField;
import com.viettel.database.field.Cause_Bts_Power_PoleField;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_Power_PoleField;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Power_PoleController {
	private Context mContext = null;

	public Cause_Bts_Power_PoleController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
			Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
			Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
			Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
			Cause_Bts_Power_PoleField.COLUMN_PROCESS_ID,
			Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cause_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID,
			Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID, 
	};

	public static final String CREATE_CAUSE_BTS_POWER_POLE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cause_Bts_Power_PoleField.TABLE_NAME
			+ "("
			+ Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID
			+ " TEXT PRIMARY KEY,"
			+ Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
			+ " TEXT,"
			+ Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cause_Bts_Power_PoleField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " INTEGER,"
			+ Cause_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)"
			;

	public void insertCauseBts_PowerPoleEntity(
			Supervision_LBG_UnqualifyItemEntity unqualify,
			Supervision_Bts_Power_PoleEntity btsPowerPoleEntity, long idCause, long idUser) {

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();
		values.put(Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
				idCause);

		values.put(
				Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
				btsPowerPoleEntity.getSupervision_BTS_POWER_POLE_ID());
		values.put(
				Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				unqualify.getCat_Cause_Constr_Unqualify_Id());

		values.put(Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
				unqualify.getSync_Status());

		values.put(Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
				unqualify.getIsActive());

		values.put(Cause_Bts_Power_PoleField.COLUMN_PROCESS_ID,
				unqualify.getProcessId());
		
		values.put(Cause_Bts_Power_PoleField.COLUMN_EMPLOYEE_ID,
				idUser);
		
		values.put(Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_CONSTR_ID,
				unqualify.getSupervisionConstrId());
		
		db.insert(Cause_Bts_Power_PoleField.TABLE_NAME, null, values);

		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteCause_Bts_Power_PoleEntity(
			Supervision_LBG_UnqualifyItemEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}

		String[] sqlPara = new String[] { String.valueOf(pDelItem.getCause_Line_Bg_Id()) };
		
		// Update Row
		db.update(Cause_Bts_Power_PoleField.TABLE_NAME, values,
				Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID + "= ? ", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteCause_Bts_Power_PoleEntity(
			Cause_Bts_Power_PoleEntity curItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		
		String[] sqlPara = new String[] { String.valueOf(curItem.getBts_PowerPoleEntity()
				.getSupervision_BTS_POWER_POLE_ID()) };
		
		db.delete(Cause_Bts_Power_PoleField.TABLE_NAME,
				Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
						+ "= ?", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}

	public List<Cause_Bts_Power_PoleEntity> getListCauseBts_Power_PoleEntity(
			long idBts) {
		List<Cause_Bts_Power_PoleEntity> result = new ArrayList<Cause_Bts_Power_PoleEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String queryBtsPowerPole = "SELECT S."
				+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
				+ ", S."
				+ Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME + ", "
				+ "S." + Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC + " "
				+ ", S." + Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE
				+ ", S." + Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS
				+ ", S." + Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID
				+ " FROM " + Supervision_BtsField.TABLE_NAME + " SB, "
				+ Supervision_Bts_Power_PoleField.TABLE_NAME + " S WHERE "
				+ "SB." + Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " = S."
				+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_ID
				+ " AND " + " SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + " = ? " + " AND S."
				+ Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE + " = "
				+ Constants.ISACTIVE.ACTIVE;
		
		String[] sqlPara = new String[] { String.valueOf(idBts) };

		Cursor cu = db.rawQuery(queryBtsPowerPole, sqlPara);

		List<Supervision_Bts_Power_PoleEntity> listBtsPowerPoleEntity = new ArrayList<Supervision_Bts_Power_PoleEntity>();

		while (cu.moveToNext()) {
			Supervision_Bts_Power_PoleEntity btsPowerPoleEntity = new Supervision_Bts_Power_PoleEntity();
			btsPowerPoleEntity
					.setSupervision_BTS_POWER_POLE_ID(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID)));
			btsPowerPoleEntity
					.setPower_POLE_NAME(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_POWER_POLE_NAME)));

			btsPowerPoleEntity
					.setIsActive(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_IS_ACTIVE)));

			btsPowerPoleEntity
					.setSync_Status(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_SYNC_STATUS)));

			btsPowerPoleEntity
					.setProcessId(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_PROCESS_ID)));
			// btsPowerPoleEntity.setStatus(cu.getInt(2));
			btsPowerPoleEntity
					.setFail_DESC(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Power_PoleField.COLUMN_FAIL_DESC)));
			listBtsPowerPoleEntity.add(btsPowerPoleEntity);
		}
		cu.close();

		for (Supervision_Bts_Power_PoleEntity item : listBtsPowerPoleEntity) {
			Cause_Bts_Power_PoleEntity causePowerPole = new Cause_Bts_Power_PoleEntity();
			causePowerPole.setBts_PowerPoleEntity(item);
			String querryCausePillar = "SELECT CB."
					+ Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ ",CC."
					+ Cat_Cause_Constr_UnQualifyField.COLUMN_NAME
					+ ",CB."
					+ Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID
					+ " "
					+ ",CB."
					+ Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE
					+ ", CB."
					+ Cause_Bts_Power_PoleField.COLUMN_SYNC_STATUS
					+ ", CB."
					+ Cause_Bts_Power_PoleField.COLUMN_PROCESS_ID
					+ " FROM "
					+ Cause_Bts_Power_PoleField.TABLE_NAME
					+ " CB, "
					+ Cat_Cause_Constr_UnQualifyField.TABLE_NAME
					+ " CC,"
					+ Supervision_Bts_Power_PoleField.TABLE_NAME
					+ " S WHERE "
					+ "CB."
					+ Cause_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
					+ " = S."
					+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
					+ " "
					+ "AND CB."
					+ Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ " = CC."
					+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ " "
					+ "AND S."
					+ Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID
					+ " = ? "
					+ " AND CB." + Cause_Bts_Power_PoleField.COLUMN_IS_ACTIVE
					+ " = " + Constants.ISACTIVE.ACTIVE;

			sqlPara = new String[] { String.valueOf(item.getSupervision_BTS_POWER_POLE_ID()) };
			
			Cursor cur = db.rawQuery(querryCausePillar, sqlPara);

			List<Cat_Cause_Constr_UnQualifyEntity> listUnqualify = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
			List<Supervision_LBG_UnqualifyItemEntity> listItemUnqualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

			while (cur.moveToNext()) {
				Cat_Cause_Constr_UnQualifyEntity unqualify = new Cat_Cause_Constr_UnQualifyEntity();
				unqualify
						.setCat_Cause_Constr_Unquality_Id(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
				unqualify
						.setName(cur.getString(cur
								.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));

				unqualify
						.setIs_Active(cur.getInt(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE)));

				unqualify
						.setProcessId(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID)));
				listUnqualify.add(unqualify);

				Supervision_LBG_UnqualifyItemEntity itemUnqualify = new Supervision_LBG_UnqualifyItemEntity();
				itemUnqualify
						.setCause_Line_Bg_Id(cur.getInt(cur
								.getColumnIndex(Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID)));
				itemUnqualify
						.setCat_Cause_Constr_Unqualify_Id(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Power_PoleField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
				itemUnqualify
						.setTitle(cur.getString(cur
								.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));

				itemUnqualify
						.setIsActive(cur.getInt(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE)));

				itemUnqualify
						.setSync_Status(cur.getInt(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS)));

				itemUnqualify
						.setProcessId(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID)));
				itemUnqualify.setUnqulify(true);				
				listItemUnqualify.add(itemUnqualify);
			}
			cur.close();
			causePowerPole.setListConstrUnqualifyEntity(listUnqualify);
			causePowerPole.setListCauseUniQualify(listItemUnqualify);
			result.add(causePowerPole);
		}
		KttsDatabaseHelper.INSTANCE.close();

		return result;
	}

}
