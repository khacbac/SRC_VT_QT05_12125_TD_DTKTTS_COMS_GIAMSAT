package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;
import com.viettel.database.field.Cat_Supervision_Constr_WorkField;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Cat_WorkController {
	private Context mContext = null;

	public Cause_Bts_Cat_WorkController(Context pContext) {
		this.mContext = pContext;
	}

	public static final String[] allColumn = new String[] {
			Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
			Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
			Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
			Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
			Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
			Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
			Cause_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID,
			Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID };

	public static final String CREATE_CAUSE_BTS_CAT_WORK_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Cause_Bts_Cat_WorkField.TABLE_NAME
			+ "("
			+ Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID
			+ " TEXT PRIMARY KEY ,"
			+ Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
			+ " TEXT,"
			+ Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
			+ " TEXT,"
			+ Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Cause_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID
			+ " TEXT)";

	public long getListKeyUnqualify(
			List<Supervision_LBG_UnqualifyItemEntity> list) {
		Ktts_KeyController kttsKey = new Ktts_KeyController();

		return kttsKey.getKttsNextKey(Cause_Bts_Cat_WorkField.TABLE_NAME);
	}

	public void insertCause_Bts_Cat_WorkEntity(
			Supervision_LBG_UnqualifyItemEntity unqualify,
			Supervision_Bts_Cat_WorkEntity btsCatWorkEntity, long idCause,
			long idUser) {
		// ArrayList<Integer> ListId = new ArrayList<Integer>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();
		values.put(Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_CONSTR_ID,
				unqualify.getSupervisionConstrId());

		values.put(Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
				idCause);
		values.put(Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
				btsCatWorkEntity.getSupervision_Bts_Cat_Work_Id());
		values.put(
				Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
				unqualify.getCat_Cause_Constr_Unqualify_Id());

		values.put(Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
				unqualify.getSync_Status());

		values.put(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
				unqualify.getIsActive());

		values.put(Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
				unqualify.getProcessId());

		values.put(Cause_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID, idUser);

		long indexInsert = db.insert(Cause_Bts_Cat_WorkField.TABLE_NAME, null, values);
		Log.d("indexInsert", indexInsert +"");

		KttsDatabaseHelper.INSTANCE.close();

		// return ListId;
	}

	public Cause_Bts_Cat_WorkEntity getCause_Bts_Cat_WorkEntity(
			String workcode, String worktype, long idBts) {
		Cause_Bts_Cat_WorkEntity result = new Cause_Bts_Cat_WorkEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		String sqlQuery = "SELECT S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_STATUS
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID
				+ " FROM "
				+ Supervision_Bts_Cat_WorkField.TABLE_NAME
				+ " S, "
				+ Cat_Supervision_Constr_WorkField.TABLE_NAME
				+ " CS, "
				+ Supervision_BtsField.TABLE_NAME
				+ " SB "
				+ "WHERE S."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " = ? AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_ID
				+ " = SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " "
				+ "AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ " = CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ " " + "AND CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE + " = "
				+ Constants.IS_ACTIVE + " AND CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE
				+ " = ? AND "
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE + " = ? ";

		// System.out.println(sqlQuery);
		String[] sqlPara = new String[] { String.valueOf(idBts), workcode,
				worktype };

		Cursor cu = db.rawQuery(sqlQuery, sqlPara);
		Supervision_Bts_Cat_WorkEntity bts_Cat_WorkEntity = new Supervision_Bts_Cat_WorkEntity();

		while (cu.moveToNext()) {
			bts_Cat_WorkEntity
					.setPulling_Wire_Type(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE)));
			bts_Cat_WorkEntity
					.setStatus(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_STATUS)));
			bts_Cat_WorkEntity
					.setFail_Desc(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC)));
			bts_Cat_WorkEntity
					.setIsActive(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			bts_Cat_WorkEntity
					.setSync_Status(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS)));

			bts_Cat_WorkEntity
					.setProcessId(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));
			bts_Cat_WorkEntity
					.setSupervision_Bts_Cat_Work_Id(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID)));
		}
		cu.close();

		result.setBts_Cat_WorkEntity(bts_Cat_WorkEntity);

		String queryCause = "SELECT CC."
				+ Cat_Cause_Constr_UnQualifyField.COLUMN_NAME
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ ",CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID
				+ ",CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID
				+ " "
				+ " FROM "
				+ Cause_Bts_Cat_WorkField.TABLE_NAME
				+ " CB, "
				+ " "
				+ Supervision_Bts_Cat_WorkField.TABLE_NAME
				+ " S, "
				+ Cat_Cause_Constr_UnQualifyField.TABLE_NAME
				+ " CC "
				+ " WHERE "
				+ " CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " = S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " "
				+ "AND CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ " = CC."
				+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ " "
				+ "AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " = ? " + " AND CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE + " = "
				+ Constants.ISACTIVE.ACTIVE;

		sqlPara = new String[] { String.valueOf(bts_Cat_WorkEntity
				.getSupervision_Bts_Cat_Work_Id()) };

		Cursor cur = db.rawQuery(queryCause, sqlPara);

		List<Cat_Cause_Constr_UnQualifyEntity> listUnqualify = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();

		List<Supervision_LBG_UnqualifyItemEntity> listItemUnqualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		while (cur.moveToNext()) {
			Supervision_LBG_UnqualifyItemEntity itemUnqualify = new Supervision_LBG_UnqualifyItemEntity();
			itemUnqualify
					.setCause_Line_Bg_Id(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID)));
			itemUnqualify
					.setCat_Cause_Constr_Unqualify_Id(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
			itemUnqualify
					.setTitle(cur.getString(cur
							.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			itemUnqualify.setIsActive(cur.getInt(cur
					.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			itemUnqualify
					.setSync_Status(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS)));

			itemUnqualify
					.setProcessId(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));

			itemUnqualify.setUnqulify(true);
			listItemUnqualify.add(itemUnqualify);

			Cat_Cause_Constr_UnQualifyEntity unqualify = new Cat_Cause_Constr_UnQualifyEntity();
			unqualify
					.setCat_Cause_Constr_Unquality_Id(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
			unqualify.setIs_Active(cur.getInt(cur
					.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			unqualify
					.setProcessId(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));
			unqualify
					.setName(cur.getString(cur
							.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			listUnqualify.add(unqualify);
		}

		result.setListConstrUnqualifyEntity(listUnqualify);
		result.setListCauseUniQualify(listItemUnqualify);
		cur.close();
		List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = getAllTanAcceptByTankId(
				bts_Cat_WorkEntity.getSupervision_Bts_Cat_Work_Id(), worktype, db);
		result.setListAcceptance(listAcceptItem);
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public Cause_Bts_Cat_WorkEntity getCause_Bts_Cat_WorkEntity(String sType,
			long idBts) {
		Cause_Bts_Cat_WorkEntity result = new Cause_Bts_Cat_WorkEntity();

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		String sqlQuery = "SELECT S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_STATUS
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID
				+ ", S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL
				+ " FROM "
				+ Supervision_Bts_Cat_WorkField.TABLE_NAME
				+ " S, "
				+ Cat_Supervision_Constr_WorkField.TABLE_NAME
				+ " CS, "
				+ Supervision_BtsField.TABLE_NAME
				+ " SB "
				+ "WHERE S."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " = ?  AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_ID
				+ " = SB."
				+ Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID
				+ " "
				+ "AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ " = CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID
				+ " " + "AND CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE + " = "
				+ Constants.IS_ACTIVE + " AND CS."
				+ Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE + " = ? ";

		// System.out.println(sqlQuery);
		String[] sqlPara = new String[] { String.valueOf(idBts), sType };

		Cursor cu = db.rawQuery(sqlQuery, sqlPara);
		Supervision_Bts_Cat_WorkEntity bts_Cat_WorkEntity = new Supervision_Bts_Cat_WorkEntity();

		while (cu.moveToNext()) {
			bts_Cat_WorkEntity
					.setPulling_Wire_Type(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_PULLING_WIRE_TYPE)));
			bts_Cat_WorkEntity
					.setStatus(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_STATUS)));
			bts_Cat_WorkEntity
					.setFail_Desc(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_FAIL_DESC)));
			bts_Cat_WorkEntity
					.setSupervision_Bts_Cat_Work_Id(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID)));

			bts_Cat_WorkEntity
					.setIsActive(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			bts_Cat_WorkEntity
					.setSync_Status(cu.getInt(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_SYNC_STATUS)));

			bts_Cat_WorkEntity
					.setProcessId(cu.getLong(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));

			bts_Cat_WorkEntity
					.setDimension_Design(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_DESIGN)));

			bts_Cat_WorkEntity
					.setDimension_Real(cu.getString(cu
							.getColumnIndex(Supervision_Bts_Cat_WorkField.COLUMN_DIMENSION_REAL)));
		}
		cu.close();

		result.setBts_Cat_WorkEntity(bts_Cat_WorkEntity);

		String queryCause = "SELECT CC."
				+ Cat_Cause_Constr_UnQualifyField.COLUMN_NAME
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ ",CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID
				+ ",CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS
				+ ", CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID
				+ " "
				+ " FROM "
				+ Cause_Bts_Cat_WorkField.TABLE_NAME
				+ " CB, "
				+ " "
				+ Supervision_Bts_Cat_WorkField.TABLE_NAME
				+ " S, "
				+ Cat_Cause_Constr_UnQualifyField.TABLE_NAME
				+ " CC "
				+ " WHERE "
				+ " CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " = S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " "
				+ "AND CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ " = CC."
				+ Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID
				+ " "
				+ "AND S."
				+ Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
				+ " = ? " + "AND CB."
				+ Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE + " = "
				+ Constants.ISACTIVE.ACTIVE;

		sqlPara = new String[] { String.valueOf(bts_Cat_WorkEntity
				.getSupervision_Bts_Cat_Work_Id()) };
		Cursor cur = db.rawQuery(queryCause, sqlPara);

		List<Cat_Cause_Constr_UnQualifyEntity> listUnqualify = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();

		List<Supervision_LBG_UnqualifyItemEntity> listItemUnqualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

		while (cur.moveToNext()) {
			Supervision_LBG_UnqualifyItemEntity itemUnqualify = new Supervision_LBG_UnqualifyItemEntity();
			itemUnqualify
					.setCause_Line_Bg_Id(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID)));
			itemUnqualify.setIsActive(cur.getInt(cur
					.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			itemUnqualify
					.setSync_Status(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS)));

			itemUnqualify
					.setProcessId(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));

			itemUnqualify
					.setCat_Cause_Constr_Unqualify_Id(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
			itemUnqualify
					.setTitle(cur.getString(cur
							.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			itemUnqualify.setUnqulify(true);

			listItemUnqualify.add(itemUnqualify);

			Cat_Cause_Constr_UnQualifyEntity unqualify = new Cat_Cause_Constr_UnQualifyEntity();
			unqualify
					.setCat_Cause_Constr_Unquality_Id(cur.getInt(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID)));
			unqualify.setIs_Active(cur.getInt(cur
					.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			unqualify
					.setProcessId(cur.getLong(cur
							.getColumnIndex(Cause_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));

			unqualify
					.setName(cur.getString(cur
							.getColumnIndex(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME)));
			listUnqualify.add(unqualify);
		}

		result.setListConstrUnqualifyEntity(listUnqualify);
		result.setListCauseUniQualify(listItemUnqualify);
		cur.close();
		//
		List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = getAllTanAcceptByTankId(
				bts_Cat_WorkEntity.getSupervision_Bts_Cat_Work_Id(), sType, db);
		result.setListAcceptance(listAcceptItem);
		KttsDatabaseHelper.INSTANCE.close();
		return result;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getAllTanAcceptByTankId(
			long iTankId,String sType,SQLiteDatabase db) {
		List<Supervision_LBG_UnqualifyItemEntity> listItem = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
		
		if(sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK)
				|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER)
						|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP)
								|| sType.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU)){
			sType = Constants.UNQUALIFY_TYPE.SUB_TYPE_THIET_BI;
		}
		
		if(sType.equals(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW)
				|| sType.equals(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH)){
			sType = Constants.UNQUALIFY_TYPE.SUB_TYPE_VIBA;
		}
		
		Cursor cursor = db.query(
				Acceptance_Bts_Cat_WorkField.TABLE_NAME,
				Acceptance_Bts_Cat_WorkController.allColumn,
				Acceptance_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
						+ " = ? AND "
						+ Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE + "= ? AND "
			            + Acceptance_Bts_Cat_WorkField.COLUMN_TYPE + " = ?",
				new String[] { String.valueOf(iTankId),
						String.valueOf(Constants.ISACTIVE.ACTIVE), sType }, null,
				null, Acceptance_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID, null);
		if (cursor.moveToFirst()) {
			do {
				Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
				curItem = this.converAcceptToUnqualifyItem(cursor);
				listItem.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return listItem;
	}

	private Supervision_LBG_UnqualifyItemEntity converAcceptToUnqualifyItem(
			Cursor cursor) {
		Supervision_LBG_UnqualifyItemEntity curItem = new Supervision_LBG_UnqualifyItemEntity();
		try {
			curItem.setCause_Line_Bg_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID)));

			curItem.setCat_Cause_Constr_Acceptance_Id(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID)));

			curItem.setSync_Status(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Bts_Cat_WorkField.COLUMN_SYNC_STATUS)));

			curItem.setIsActive(cursor.getInt(cursor
					.getColumnIndex(Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE)));

			curItem.setProcessId(cursor.getLong(cursor
					.getColumnIndex(Acceptance_Bts_Cat_WorkField.COLUMN_PROCESS_ID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

	public boolean addItem(Acceptance_Bts_Cat_WorkEntity addItem) {
		Long idItem = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(
				Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,
				addItem.getAcceptance_Bts_Cat_Work_Id());

		values.put(
				Acceptance_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
				addItem.getSupervision_Bts_Cat_Work_Id());

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_TYPE, addItem.getType());

		values.put(
				Acceptance_Bts_Cat_WorkField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
				addItem.getCat_Cause_Constr_Acceptance_Id());

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
				addItem.getSync_Status());

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
				addItem.getIsActive());

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		values.put(Acceptance_Bts_Cat_WorkField.COLUMN_EMPLOYEE_ID,
				addItem.getEmployeeId());
		// Inserting Row
		idItem = db.insert(Acceptance_Bts_Cat_WorkField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();

		if (idItem > 0) {
			return true;
		}
		return false;
	}

	public void deleteCause_Bts_Cat_WorkEntity(
			Supervision_LBG_UnqualifyItemEntity pDelItem) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();

		values.put(Cause_Bts_Cat_WorkField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		if (pDelItem.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			values.put(Cause_Bts_Cat_WorkField.COLUMN_SYNC_STATUS,
					Constants.SYNC_STATUS.EDIT);
		}

		String[] sqlPara = new String[] { String.valueOf(pDelItem
				.getCause_Line_Bg_Id()) };

		// Update Row
		db.update(Cause_Bts_Cat_WorkField.TABLE_NAME, values,
				Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID + "= ? ",
				sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
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

	public void deleteCause_Bts_Cat_WorkEntity(Cause_Bts_Cat_WorkEntity item) {
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String[] sqlPara = new String[] { String.valueOf(item
				.getBts_Cat_WorkEntity().getSupervision_Bts_Cat_Work_Id()) };

		db.delete(Cause_Bts_Cat_WorkField.TABLE_NAME,
				Cause_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID
						+ "= ? ", sqlPara);

		KttsDatabaseHelper.INSTANCE.close();
	}
}
