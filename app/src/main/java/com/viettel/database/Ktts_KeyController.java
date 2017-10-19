package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.common.GlobalInfo;
import com.viettel.database.entity.Ktts_KeyEntity;
import com.viettel.database.field.Acceptance_Brcd_Box_Field;
import com.viettel.database.field.Acceptance_Brcd_Drop_Field;
import com.viettel.database.field.Acceptance_Brcd_Kcn_Field;
import com.viettel.database.field.Acceptance_Brcd_Kct_Field;
import com.viettel.database.field.Acceptance_Brcd_Mx_Field;
import com.viettel.database.field.Acceptance_Brcd_Sub_Field;
import com.viettel.database.field.Acceptance_Brcd_Tn_Field;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Acceptance_Bts_PillarField;
import com.viettel.database.field.Acceptance_Line_Bg_PipeField;
import com.viettel.database.field.Acceptance_Line_Bg_SpecialField;
import com.viettel.database.field.Acceptance_Line_Bg_TankField;
import com.viettel.database.field.Cat_Cause_Not_WorkField;
import com.viettel.database.field.Cat_Constr_TeamField;
import com.viettel.database.field.Cat_Employee_Plan_Field;
import com.viettel.database.field.Cat_Sub_Work_ItemField;
import com.viettel.database.field.Cat_Supervision_Constr_WorkField;
import com.viettel.database.field.Cat_Supv_Constr_MeasureField;
import com.viettel.database.field.Cause_Brcd_Box_Field;
import com.viettel.database.field.Cause_Brcd_Drop_Field;
import com.viettel.database.field.Cause_Brcd_Kcn_Field;
import com.viettel.database.field.Cause_Brcd_Kct_Field;
import com.viettel.database.field.Cause_Brcd_Mx_Field;
import com.viettel.database.field.Cause_Brcd_Sub_Field;
import com.viettel.database.field.Cause_Brcd_Tn_Field;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Pillar_AntenField;
import com.viettel.database.field.Cause_Bts_Power_PoleField;
import com.viettel.database.field.Cause_Line_BG_CableField;
import com.viettel.database.field.Cause_Line_BG_MXField;
import com.viettel.database.field.Cause_Line_BG_PipeField;
import com.viettel.database.field.Cause_Line_BG_SpecialField;
import com.viettel.database.field.Cause_Line_BG_TankField;
import com.viettel.database.field.Cause_Line_Hg_CableField;
import com.viettel.database.field.Cause_Line_Hg_MxField;
import com.viettel.database.field.Cause_Line_Hg_PillarField;
import com.viettel.database.field.ConstrNodeItemsField;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_Progress_Field;
import com.viettel.database.field.Constr_Team_ProgressField;
import com.viettel.database.field.Constr_Work_LogsField;
import com.viettel.database.field.Ktts_KeyField;
import com.viettel.database.field.Measurement_Detail_ConstrField;
import com.viettel.database.field.Special_LocationField;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Supervision_BRCD_Drop_Design_Field;
import com.viettel.database.field.Supervision_BRCD_Drop_Field;
import com.viettel.database.field.Supervision_BRCD_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_DropGo_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kcn_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Kcn_Design_Field;
import com.viettel.database.field.Supervision_BRCD_Kcn_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_design_Field;
import com.viettel.database.field.Supervision_BRCD_MXField;
import com.viettel.database.field.Supervision_BRCD_Sub_Field;
import com.viettel.database.field.Supervision_BRCD_Tn_Field;
import com.viettel.database.field.Supervision_BRCD_Ttb_Field;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_EquipField;
import com.viettel.database.field.Supervision_Bts_MeasureField;
import com.viettel.database.field.Supervision_Bts_Pillar_AntenField;
import com.viettel.database.field.Supervision_Bts_Power_PoleField;
import com.viettel.database.field.Supervision_CNDTCField;
import com.viettel.database.field.Supervision_Line_BG_CableField;
import com.viettel.database.field.Supervision_Line_BG_FiberField;
import com.viettel.database.field.Supervision_Line_BG_MXField;
import com.viettel.database.field.Supervision_Line_BG_PipeField;
import com.viettel.database.field.Supervision_Line_BG_TankField;
import com.viettel.database.field.Supervision_Line_BackgroundField;
import com.viettel.database.field.Supervision_Line_HangField;
import com.viettel.database.field.Supervision_Line_Hg_CableField;
import com.viettel.database.field.Supervision_Line_Hg_FiberField;
import com.viettel.database.field.Supervision_Line_Hg_MxField;
import com.viettel.database.field.Supervision_Line_Hg_PillarField;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.database.field.Supervision_Measure_ConstrField;
import com.viettel.database.field.Supervision_ProgressField;
import com.viettel.database.field.Supv_Constr_Attach_FileField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.database.field.Work_Items_ValueField;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Ktts_KeyController {
	protected static Ktts_KeyController instance;
	public static String[] ListTableName = new String[] {
			Supv_Constr_Attach_FileField.TABLE_NAME,

			Supervision_Measure_ConstrField.TABLE_NAME,
			Measurement_Detail_ConstrField.TABLE_NAME,
			Supervision_ProgressField.TABLE_NAME,
			Supervision_Line_BackgroundField.TABLE_NAME,
			Supervision_Line_BG_CableField.TABLE_NAME,
			Supervision_Line_BG_FiberField.TABLE_NAME,
			Supervision_Line_BG_MXField.TABLE_NAME,
			Supervision_Line_BG_PipeField.TABLE_NAME,
			Supervision_Line_BG_TankField.TABLE_NAME,
			Special_LocationField.TABLE_NAME,
			Cause_Line_BG_CableField.TABLE_NAME,
			Cause_Line_BG_MXField.TABLE_NAME,
			Cause_Line_BG_PipeField.TABLE_NAME,
			Cause_Line_BG_TankField.TABLE_NAME,
			Cause_Line_BG_SpecialField.TABLE_NAME,
			Acceptance_Line_Bg_TankField.TABLE_NAME,
			Acceptance_Line_Bg_SpecialField.TABLE_NAME,
			Acceptance_Line_Bg_PipeField.TABLE_NAME,

			Supervision_Line_HangField.TABLE_NAME,
			Supervision_Line_Hg_CableField.TABLE_NAME,
			Supervision_Line_Hg_FiberField.TABLE_NAME,
			Supervision_Line_Hg_MxField.TABLE_NAME,
			Supervision_Line_Hg_PillarField.TABLE_NAME,
			Cause_Line_Hg_CableField.TABLE_NAME,
			Cause_Line_Hg_MxField.TABLE_NAME,
			Cause_Line_Hg_PillarField.TABLE_NAME,

			Supervision_BtsField.TABLE_NAME,
			Supervision_Bts_Cat_WorkField.TABLE_NAME,
			Supervision_Bts_EquipField.TABLE_NAME,
			Supervision_Bts_MeasureField.TABLE_NAME,
			Supervision_Bts_Pillar_AntenField.TABLE_NAME,
			Supervision_Bts_Power_PoleField.TABLE_NAME,
			Cause_Bts_Cat_WorkField.TABLE_NAME,
			Cause_Bts_Pillar_AntenField.TABLE_NAME,
			Acceptance_Bts_PillarField.TABLE_NAME,
			Acceptance_Bts_Cat_WorkField.TABLE_NAME,
			Cause_Bts_Power_PoleField.TABLE_NAME,
			Cat_Supv_Constr_MeasureField.TABLE_NAME,

			Cat_Supervision_Constr_WorkField.TABLE_NAME,
			Supervision_BRCD_Field.TABLE_NAME,
			Supervision_BRCD_Kct_Field.TABLE_NAME,
			Supervision_BRCD_Kcn_Field.TABLE_NAME,
			Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
			Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,

			Supervision_BRCD_MXField.TABLE_NAME,
			Acceptance_Brcd_Kct_Field.TABLE_NAME,
			Acceptance_Brcd_Mx_Field.TABLE_NAME,
			Cause_Brcd_Mx_Field.TABLE_NAME,

			Acceptance_Brcd_Kcn_Field.TABLE_NAME,
			Cause_Brcd_Kcn_Field.TABLE_NAME,

			Cause_Brcd_Kct_Field.TABLE_NAME,
			Supervision_BRCD_Kct_design_Field.TABLE_NAME,
			Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,

			Cause_Brcd_Tn_Field.TABLE_NAME,
			Acceptance_Brcd_Tn_Field.TABLE_NAME,
			Supervision_BRCD_Tn_Field.TABLE_NAME,
			// Supervision_BRCD_MXField.TABLE_NAME,

			Cause_Brcd_Drop_Field.TABLE_NAME,
			Acceptance_Brcd_Drop_Field.TABLE_NAME,
			Supervision_BRCD_Drop_Field.TABLE_NAME,
			Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
			Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,

			Cause_Brcd_Box_Field.TABLE_NAME,
			Acceptance_Brcd_Box_Field.TABLE_NAME,
			Supervision_BRCD_Ttb_Field.TABLE_NAME,
			// ---HungTN add new 25/08/2016
			Supervision_CNDTCField.TABLE_NAME,
			//Cat_Construct_Field.TABLE_NAME,
			Constr_Progress_Field.TABLE_NAME,
			//Constr_ObStruction_TypeField.TABLE_NAME,
			Constr_ObStructionField.TABLE_NAME,
			// ---
			Cause_Brcd_Sub_Field.TABLE_NAME,
			Acceptance_Brcd_Sub_Field.TABLE_NAME,
			Supervision_BRCD_Sub_Field.TABLE_NAME,
			Supervision_Locate_Field.TABLE_NAME,
			Cat_Employee_Plan_Field.TABLE_NAME,
			Cat_Sub_Work_ItemField.TABLE_NAME,
			Work_ItemsField.TABLE_NAME,
			Work_Items_ValueField.TABLE_NAME,
			Sub_Work_ItemField.TABLE_NAME,
			Sub_Work_Item_ValueField.TABLE_NAME,
			Constr_Work_LogsField.TABLE_NAME,
			Cat_Constr_TeamField.TABLE_NAME,
			Constr_Team_ProgressField.TABLE_NAME,
			Cat_Cause_Not_WorkField.TABLE_NAME,
            ConstrNodeItemsField.TABLE_NAME
	};

	public Ktts_KeyController() {
		mContext = GlobalInfo.getInstance().getAppContext();
	}

	public static Ktts_KeyController getInstance() {
		if (instance == null) {
			instance = new Ktts_KeyController();
		}
		return instance;
	}

	private Context mContext = null;

	private String[] allColumn = new String[] {
			Ktts_KeyField.COLUMN_TABLE_NAME, Ktts_KeyField.COLUMN_MIN,
			Ktts_KeyField.COLUMN_NEW_MIN, Ktts_KeyField.COLUMN_MAX,
			Ktts_KeyField.COLUMN_NEW_MAX, Ktts_KeyField.COLUMN_CLIENTID };

	public static final String CREATE_KTTS_KEY_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Ktts_KeyField.TABLE_NAME
			+ "("
			+ Ktts_KeyField.COLUMN_TABLE_NAME
			+ " TEXT PRIMARY KEY,"
			+ Ktts_KeyField.COLUMN_MIN
			+ " TEXT DEFAULT '0',"
			+ Ktts_KeyField.COLUMN_NEW_MIN
			+ " TEXT DEFAULT '0',"
			+ Ktts_KeyField.COLUMN_MAX
			+ " TEXT DEFAULT '0',"
			+ Ktts_KeyField.COLUMN_NEW_MAX
			+ " TEXT DEFAULT '0',"
			+ Ktts_KeyField.COLUMN_CLIENTID
			+ " TEXT"
			+ ")";

	public Ktts_KeyController(Context pContext) {
		this.mContext = pContext;
	}

	/**
	 * Ham ghi gia tri cap khoa mac dinh cua he thong
	 * 
	 * @param pClientId
	 * @return
	 */
	public boolean addDefaultTableKey(String pClientId) {
		boolean bResult = false;
		if (StringUtil.isNullOrEmpty(pClientId)) {
			return false;
		}
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		db.beginTransaction();
		try {
			/* Cap nhat gia tri tat ca cac bang */
			for (String curTableName : Ktts_KeyController.ListTableName) {
				ContentValues values = new ContentValues();
				values.put(Ktts_KeyField.COLUMN_TABLE_NAME, curTableName);
				values.put(Ktts_KeyField.COLUMN_MIN, "1");
				values.put(Ktts_KeyField.COLUMN_NEW_MIN, "0");
				values.put(Ktts_KeyField.COLUMN_MAX, "1000");
				values.put(Ktts_KeyField.COLUMN_NEW_MAX, "0");
				values.put(Ktts_KeyField.COLUMN_CLIENTID, pClientId);
				db.insert(Ktts_KeyField.TABLE_NAME, null, values);
			}
			db.setTransactionSuccessful();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.endTransaction();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public boolean addKttsKey(Ktts_KeyEntity pItem) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		ContentValues values = new ContentValues();
		values.put(Ktts_KeyField.COLUMN_TABLE_NAME, pItem.getTableName());

		values.put(Ktts_KeyField.COLUMN_MIN, pItem.getMin());

		values.put(Ktts_KeyField.COLUMN_NEW_MIN, pItem.getNewMin());

		values.put(Ktts_KeyField.COLUMN_MAX, pItem.getMax());

		values.put(Ktts_KeyField.COLUMN_NEW_MAX, pItem.getNewMax());

		values.put(Ktts_KeyField.COLUMN_CLIENTID, pItem.getClientId());

		db.insert(Ktts_KeyField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	/**
	 * Ham cap nhat lai gia tri Max va min moi
	 */
	public boolean updateKttsKey(Long pMax, String pTableName) {
		ContentValues values = new ContentValues();
		values.put(Ktts_KeyField.COLUMN_MAX, pMax);
		String sqlclause = Ktts_KeyField.COLUMN_TABLE_NAME + "= ?";
		String[] sqlPara = new String[] { pTableName };
		return this.updateDB(Ktts_KeyField.TABLE_NAME, values, sqlclause,
				sqlPara);
	}

	/**
	 * Ham cap nhat lai gia tri Max va min moi
	 */
	public boolean updateNewKttsKey(Long pMin, Long pMax, String pTableName) {
		ContentValues values = new ContentValues();
		values.put(Ktts_KeyField.COLUMN_NEW_MIN, pMin);
		values.put(Ktts_KeyField.COLUMN_NEW_MAX, pMax);
		String sqlclause = Ktts_KeyField.COLUMN_TABLE_NAME + "= ?";
		String[] sqlPara = new String[] { pTableName };
		return this.updateDB(Ktts_KeyField.TABLE_NAME, values, sqlclause,
				sqlPara);
	}

	/**
	 * Lay 1 id key tiep theo kieu Long
	 * 
	 * @param pTableName
	 *            Ten bang lay khoa tiep theo
	 * @return 0 la khong lay duoc, > 0 la lay duoc key
	 */
	public synchronized long getKttsNextKey(String pTableName) {
		Long iResult = 0L;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Ktts_KeyField.TABLE_NAME, allColumn,
				Ktts_KeyField.COLUMN_TABLE_NAME + "=?",
				new String[] { pTableName }, null, null, null, "1");
		// TODO chuyen ve chon 1 ban ghi thoi
		if (cursor.moveToFirst()) {
			long lMin = cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MIN));
			long lMax = cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MAX));
			/* Neu khoang hien tai la khong con */
			if (lMin >= lMax) {
				/* Kiem tra xem khoang moi da co chua */
				long lNewMin = cursor.getLong(cursor
						.getColumnIndex(Ktts_KeyField.COLUMN_NEW_MIN));
				long lNewMax = cursor.getLong(cursor
						.getColumnIndex(Ktts_KeyField.COLUMN_NEW_MAX));
				if (lNewMax > lMax) {
					/* Cap nhat lai khoang min max */
					ContentValues values = new ContentValues();
					values.put(Ktts_KeyField.COLUMN_MIN, lNewMin + 1);
					values.put(Ktts_KeyField.COLUMN_MAX, lNewMax);
					String sqlclause = Ktts_KeyField.COLUMN_TABLE_NAME + "= ?";
					String[] sqlPara = new String[] { pTableName };
					boolean bUpdate = this.updateDB(Ktts_KeyField.TABLE_NAME,
							values, sqlclause, sqlPara);
					if (bUpdate) {
						iResult = lNewMin;
					}
				} else {
					/* Neu chua lay duoc khoang key moi */
					return 0L;
				}
			} else {
				/* Cap nhat lai db */
				ContentValues values = new ContentValues();
				values.put(Ktts_KeyField.COLUMN_MIN, String.valueOf(lMin + 1));
				String sqlclause = Ktts_KeyField.COLUMN_TABLE_NAME + "= ?";
				String[] sqlPara = new String[] { pTableName };
				boolean bUpdate = this.updateDB(Ktts_KeyField.TABLE_NAME,
						values, sqlclause, sqlPara);
				if (bUpdate) {
					iResult = lMin;
				}
			}
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return iResult;
	}

	/**
	 * Lay mot khoang key
	 * 
	 * @param pTableName
	 * @param pNumber
	 *            : so key can lay
	 * @return
	 */
	public synchronized List<Long> getKttsNextKey(String pTableName, int pNumber) {
		List<Long> listResult = new ArrayList<Long>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(Ktts_KeyField.TABLE_NAME, allColumn,
				Ktts_KeyField.COLUMN_TABLE_NAME + "=?",
				new String[] { pTableName }, null, null, null, "LIMIT 1");
		if (cursor.moveToFirst()) {
			Long lMin = Long.parseLong(cursor.getString(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MIN)));
			Long lMax = Long.parseLong(cursor.getString(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MAX)));
			if ((lMin + pNumber) >= lMax) {
				return new ArrayList<Long>();
			} else {
				/* Cap nhat lai db */
				ContentValues values = new ContentValues();
				values.put(Ktts_KeyField.COLUMN_MIN,
						String.valueOf(lMin + pNumber));
				String sqlclause = Ktts_KeyField.COLUMN_TABLE_NAME + "= ?";
				String[] sqlPara = new String[] { pTableName };
				boolean bUpdate = this.updateDB(Ktts_KeyField.TABLE_NAME,
						values, sqlclause, sqlPara);
				if (bUpdate) {
					for (int i = 0; i < pNumber; i++) {
						listResult.add(lMin + i);
					}
				}
			}
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
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

	/**
	 * Lay mot cong trinh giam sat.
	 * 
	 * @param tableName Ten bang cong trinh.
	 * @return Cong trinh duoc giam sat.
	 */
	public Ktts_KeyEntity getItem(String tableName) {
		Ktts_KeyEntity curItem = new Ktts_KeyEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);

		Cursor cursor = db.query(Ktts_KeyField.TABLE_NAME, allColumn,
				Ktts_KeyField.COLUMN_TABLE_NAME + "=?",
				new String[] { tableName }, null, null, null, null);
		if (cursor.moveToFirst()) {
			curItem = this.converToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	private Ktts_KeyEntity converToItem(Cursor cursor) {
		Ktts_KeyEntity curItem = new Ktts_KeyEntity();
		try {
			curItem.setTableName(cursor.getString(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_TABLE_NAME)));

			curItem.setMin(cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MIN)));

			curItem.setNewMin(cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_NEW_MIN)));

			curItem.setMax(cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_MAX)));

			curItem.setNewMax(cursor.getLong(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_NEW_MAX)));

			curItem.setClientId(cursor.getString(cursor
					.getColumnIndex(Ktts_KeyField.COLUMN_CLIENTID)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curItem;
	}

}