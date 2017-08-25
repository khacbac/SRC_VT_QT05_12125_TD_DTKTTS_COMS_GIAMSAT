package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Acceptance_Bts_PillarEntity;
import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Supervision_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Acceptance_Bts_PillarField;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;
import com.viettel.database.field.Cause_Bts_Pillar_AntenField;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_Pillar_AntenField;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Pillar_AntenController {
	private Context mContext = null;

	public Cause_Bts_Pillar_AntenController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
			Cause_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
			Cause_Bts_Pillar_AntenField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
			Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
			Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID,
			Cause_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID,
			Cause_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_CAUSE_BTS_PILLAR_ANTEN_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cause_Bts_Pillar_AntenField.TABLE_NAME
			+ "("
			+ Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID
			+ " TEXT PRIMARY KEY,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
			+ " TEXT,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " TEXT,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cause_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID
			+ " TEXT," 
			+ Cause_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public void insertCauseBts_PillarAntenEntity(
			Supervision_LBG_UnqualifyItemEntity unqualify,
			Supervision_Bts_Pillar_AntenEntity btsPillarEntity, long idCause,
			long idUser) {

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();
		values.put(
				Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
				idCause);
		values.put(Cause_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
				btsPillarEntity.getSupervision_BTS_PILLAR_ANTEN_ID());
		values.put(
				Cause_Bts_Pillar_AntenField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				unqualify.getCat_Cause_Constr_Unqualify_Id());

		values.put(Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
				unqualify.getSync_Status());

		values.put(Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
				unqualify.getIsActive());

		values.put(Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID,
				unqualify.getProcessId());

		values.put(Cause_Bts_Pillar_AntenField.COLUMN_EMPLOYEE_ID, idUser);
		values.put(
				Cause_Bts_Pillar_AntenField.COLUMN_SUPERVISION_CONSTR_ID,
				unqualify.getSupervisionConstrId());

		db.insert(Cause_Bts_Pillar_AntenField.TABLE_NAME, null, values);


		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteCause_Bts_Pillar_AntenEntity(
			Supervision_LBG_UnqualifyItemEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String[] sqlPara = new String[] { String.valueOf(pDelItem.getCause_Line_Bg_Id()) };

		// Update Row
		db.update(Cause_Bts_Pillar_AntenField.TABLE_NAME, values,
				Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID
						+ "= ? ", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}
	
	public void deleteAccept(
			Supervision_LBG_UnqualifyItemEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Acceptance_Bts_PillarField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}
		
		String[] sqlPara = new String[] { String.valueOf(pDelItem.getCause_Line_Bg_Id()) };

		// Update Row
		db.update(Acceptance_Bts_PillarField.TABLE_NAME, values,
				Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID
						+ "= ? ", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}

	public void deleteCause_Bts_Pillar_AntenEntity(
			Cause_Bts_Pillar_AntenEntity curItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		// for (Cat_Cause_Constr_UnQualifyEntity
		// cat_Cause_Constr_UnQualifyEntity :
		// curItem.getList_Cat_Cause_Constr_UnQualifyEntity()) {
		String[] sqlPara = new String[] { String.valueOf(curItem.getSupervision_Bts_Pillar_AntenEntity()
				.getSupervision_BTS_PILLAR_ANTEN_ID()) };
		
		db.delete(Cause_Bts_Pillar_AntenField.TABLE_NAME,
				Cause_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
						+ "= ? ", sqlPara);
		// }

		KttsDatabaseHelper.INSTANCE.close(); // Closing database connection
	}
	
	public boolean addItem(Acceptance_Bts_PillarEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
				addItem.getAcceptance_Bts_Pillar_Id());

		values.put(Acceptance_Bts_PillarField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
				addItem.getSupv_Bts_Pillar_Anten_Id());

		values.put(
				Acceptance_Bts_PillarField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
				addItem.getCat_Cause_Constr_Acceptance_Id());

		values.put(Acceptance_Bts_PillarField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Acceptance_Bts_PillarField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Acceptance_Bts_PillarField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Acceptance_Bts_PillarField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public List<Cause_Bts_Pillar_AntenEntity> getListCauseBts_PillarAntenEntity(
			long idBts) {
		List<Cause_Bts_Pillar_AntenEntity> result = new ArrayList<Cause_Bts_Pillar_AntenEntity>();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String queryBtsPillar = "SELECT S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
				+ ", S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME
				+ ", S." + Supervision_Bts_Pillar_AntenField.COLUMN_STATUS
				+ ", " + "S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC + ", "
				+ "S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN
				+ ", " + "S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL + " "
				+ ", S." + Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE
				+ ", S." + Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS
				+ ", S." + Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID
				+ " FROM " + Supervision_BtsField.TABLE_NAME + " SB, "
				+ Supervision_Bts_Pillar_AntenField.TABLE_NAME + " S WHERE "
				+ "SB." + Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " = S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPERVISION_BTS_ID
				+ " AND " + " SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID + " = ? " + " AND S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE + " = "
				+ Constants.ISACTIVE.ACTIVE + " ORDER BY S."
				+ Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME;
		
		String[] sqlPara = new String[] { String.valueOf(idBts) };

		Cursor cu = db.rawQuery(queryBtsPillar, sqlPara);

		List<Supervision_Bts_Pillar_AntenEntity> listBtsPillarAntenEntity = new ArrayList<Supervision_Bts_Pillar_AntenEntity>();

		while (cu.moveToNext()) {
			Supervision_Bts_Pillar_AntenEntity btsPillarAntenEntity = new Supervision_Bts_Pillar_AntenEntity();
			btsPillarAntenEntity
					.setSupervision_BTS_PILLAR_ANTEN_ID(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID)));
			btsPillarAntenEntity
					.setFOUNDATION_NAME(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_FOUNDATION_NAME)));
			btsPillarAntenEntity
					.setStatus(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_STATUS)));
			btsPillarAntenEntity
					.setFail_DESC(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_FAIL_DESC)));

			btsPillarAntenEntity
					.setIsActive(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE)));

			btsPillarAntenEntity
					.setSync_Status(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS)));

			btsPillarAntenEntity
					.setProcessId(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_PROCESS_ID)));
			btsPillarAntenEntity
					.setDimension_Design(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_DESIGN)));
			btsPillarAntenEntity
					.setDimension_Real(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Pillar_AntenField.COLUMN_DIMENSION_REAL)));
			listBtsPillarAntenEntity.add(btsPillarAntenEntity);
		}
		cu.close();

		Cursor cur = null;
		for (Supervision_Bts_Pillar_AntenEntity item : listBtsPillarAntenEntity) {
			Cause_Bts_Pillar_AntenEntity causePillar = new Cause_Bts_Pillar_AntenEntity();
			causePillar.setSupervision_Bts_Pillar_AntenEntity(item);
			String querryCausePillar = "SELECT CC."
					+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ ",CC."
					+ Cat_Cause_Constr_UnQualifyField.COLUMN_NAME
					+ ",CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID
					+ ",CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE
					+ ", CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_SYNC_STATUS
					+ ", CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID
					+ " FROM "
					+ Cause_Bts_Pillar_AntenField.TABLE_NAME
					+ " CB, "
					+ Cat_Cause_Constr_UnQualifyField.TABLE_NAME
					+ " CC,"
					+ Supervision_Bts_Pillar_AntenField.TABLE_NAME
					+ " S WHERE "
					+ "CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
					+ " = S."
					+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
					+ " "
					+ "AND CB."
					+ Cause_Bts_Pillar_AntenField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ " = CC."
					+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
					+ " "
					+ "AND S."
					+ Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
					+ " = ? "
					+ "AND CB." + Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE
					+ " = " + Constants.ISACTIVE.ACTIVE;

			sqlPara = new String[] { String.valueOf(item.getSupervision_BTS_PILLAR_ANTEN_ID()) };
			
			cur = db.rawQuery(querryCausePillar, sqlPara);

			List<Cat_Cause_Constr_UnQualifyEntity> listUnqualify = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
			List<Supervision_LBG_UnqualifyItemEntity> listItemUnqualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
			while (cur.moveToNext()) {
				Cat_Cause_Constr_UnQualifyEntity unqualify = new Cat_Cause_Constr_UnQualifyEntity();
				Supervision_LBG_UnqualifyItemEntity itemUnqualify = new Supervision_LBG_UnqualifyItemEntity();
				itemUnqualify
						.setCause_Line_Bg_Id(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID)));

				itemUnqualify
						.setCat_Cause_Constr_Unqualify_Id(cur.getLong(cur
								.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
				unqualify
						.setCat_Cause_Constr_Unquality_Id(cur.getInt(cur
								.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
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
				unqualify
						.setName(cur.getString(cur
								.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
				unqualify
						.setIs_Active(cur.getInt(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_IS_ACTIVE)));

				unqualify
						.setProcessId(cur.getLong(cur
								.getColumnIndex(Cause_Bts_Pillar_AntenField.COLUMN_PROCESS_ID)));
				itemUnqualify.setUnqulify(true);
				listUnqualify.add(unqualify);
				listItemUnqualify.add(itemUnqualify);
			}
			cur.close();
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = getAllTanAcceptByTankId(item.getSupervision_BTS_PILLAR_ANTEN_ID(),db);
			causePillar.setListAcceptance(listAcceptItem);
			
			causePillar.setList_Cat_Cause_Constr_UnQualifyEntity(listUnqualify);
			causePillar.setListCauseUniQualify(listItemUnqualify);
			
			result.add(causePillar);
		}
		
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}
	
	public List<Supervision_LBG_UnqualifyItemEntity> getAllTanAcceptByTankId(
			long iTankId,SQLiteDatabase db) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		Cursor cursor = db.query(
				Acceptance_Bts_PillarField.TABLE_NAME,
				Acceptance_Bts_PillarController.allColumn,
				Acceptance_Bts_PillarField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID
						+ " = ? AND "
						+ Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE + "= ?",
				new String[] { String.valueOf(iTankId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, Acceptance_Bts_PillarField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converAcceptToUnqualifyItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
//		KttsDatabaseHelper.INSTANCE.close();
		return listItem;
	}
	
	private Supervision_LBG_UnqualifyItemEntity converAcceptToUnqualifyItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCause_Line_Bg_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID)));

			curItem.setCat_Cause_Constr_Acceptance_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_PillarField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Bts_PillarField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Bts_PillarField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_PillarField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}
}
