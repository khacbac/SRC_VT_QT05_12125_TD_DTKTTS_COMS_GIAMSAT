package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.field.Constr_ConstructionField;
import com.viettel.database.field.Constr_Construction_EmployeeField;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.utils.DateConvert;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Lop lay du lieu danh sach cong trinh giam sat
 * 
 * @author datht1
 * 
 */
public class Constr_ConstructionController {
	private Context mContext = null;
	public static String[] allColumn = new String[] {
			Constr_ConstructionField.COLUMN_STATION_CODE,
			Constr_ConstructionField.COLUMN_START_POINT_CODE,
			Constr_ConstructionField.COLUMN_END_POINT_CODE,
			Constr_ConstructionField.COLUMN_ADDRESS,
			Constr_ConstructionField.COLUMN_PROVINCE_CODE,
			Constr_ConstructionField.COLUMN_STATION_ID,
			Constr_ConstructionField.COLUMN_CONSTRUCT_ID,
			Constr_ConstructionField.COLUMN_CONSTR_TYPE,
			Constr_ConstructionField.COLUMN_CONSTRT_CODE,
			Constr_ConstructionField.COLUMN_CONSTRT_NAME,
			Constr_ConstructionField.COLUMN_CAT_STATION_TYPE_ID,
			Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS,
			Constr_ConstructionField.COLUMN_STARTING_DATE,
			Constr_ConstructionField.COLUMN_STATUS,
			Constr_ConstructionField.COLUMN_PROCESS_ID,
			Constr_ConstructionField.COLUMN_EMPLOYEE_ID,
			Constr_ConstructionField.COLUMN_LATTITUDE,
			Constr_ConstructionField.COLUMN_LONGTITUDE };
	public static final String CREATE_CONSTR_CONSTRUCTIONS_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Constr_ConstructionField.TABLE_NAME
			+ "("
			+ Constr_ConstructionField.COLUMN_CONSTRUCT_ID
			+ " TEXT PRIMARY KEY,"
			+ Constr_ConstructionField.COLUMN_STATION_CODE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_START_POINT_CODE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_END_POINT_CODE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_ADDRESS
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_PROVINCE_CODE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_STATION_ID
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_CONSTRT_CODE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_CONSTRT_NAME
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_CAT_STATION_TYPE_ID
			+ " INTEGER,"
			+ Constr_ConstructionField.COLUMN_CONSTR_TYPE
			+ " INTEGER,"
			+ Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_STARTING_DATE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_STATUS
			+ " INTEGER,"
			+ Constr_ConstructionField.COLUMN_EMPLOYEE_ID
			+ " INTEGER,"
			+ Constr_ConstructionField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Constr_ConstructionField.COLUMN_LATTITUDE
			+ " TEXT,"
			+ Constr_ConstructionField.COLUMN_LONGTITUDE + " TEXT" + ")";

	public Constr_ConstructionController(Context pContext) {
		this.mContext = pContext;
	}

	/**
	 * Them moi 1 cong trinh giam sat, Dung cho dong bo co so du lieu
	 * 
	 * @param addItem
	 *            Cong trinh duoc them moi
	 * @return true neu them moi thanh cong , false neu them moi that bai
	 */
	public boolean addItem(Constr_ConstructionEntity addItem) {

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(Constr_ConstructionField.COLUMN_STATION_CODE,
				addItem.getStationCode());

		values.put(Constr_ConstructionField.COLUMN_ADDRESS,
				addItem.getAddress());

		values.put(Constr_ConstructionField.COLUMN_PROVINCE_CODE,
				addItem.getProvinceCode());

		values.put(Constr_ConstructionField.COLUMN_STATION_ID,
				addItem.getStationId());

		values.put(Constr_ConstructionField.COLUMN_CONSTRUCT_ID,
				addItem.getConstructId());

		values.put(Constr_ConstructionField.COLUMN_CONSTR_TYPE,
				addItem.getConstrType());

		values.put(Constr_ConstructionField.COLUMN_CONSTRT_CODE,
				addItem.getConstrCode());

		values.put(Constr_ConstructionField.COLUMN_CONSTRT_NAME,
				addItem.getConstrName());

		values.put(Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS,
				addItem.getConstrAddress());

		values.put(Constr_ConstructionField.COLUMN_STATION_ID,
				addItem.getStationId());

		values.put(Constr_ConstructionField.COLUMN_LATTITUDE,
				addItem.getLatitude());
		values.put(Constr_ConstructionField.COLUMN_LONGTITUDE,
				addItem.getLongtitude());

		Date date = addItem.getStartingDate();
		String startingDate = DateConvert.convertDateToString(date);
		values.put(Constr_ConstructionField.COLUMN_STARTING_DATE, startingDate);

		values.put(Constr_ConstructionField.COLUMN_STATUS, addItem.getStatus());

		values.put(Constr_ConstructionField.COLUMN_PROCESS_ID,
				addItem.getProcessId());

		// Inserting Row
		db.insert(Constr_ConstructionField.TABLE_NAME, null, values);
		KttsDatabaseHelper.INSTANCE.close();
		return true;
	}

	/**
	 * Lay mot cong trinh giam sat
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public Constr_ConstructionEntity getItem(long itemId) {
		Constr_ConstructionEntity curItem = new Constr_ConstructionEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Constr_ConstructionField.TABLE_NAME, allColumn,
						Constr_ConstructionField.COLUMN_CONSTRUCT_ID + "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			curItem = this.converCursorToItem(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	public int updateStatus(Constr_ConstructionEntity entity) {
		ContentValues cv = new ContentValues();
		cv.put(Constr_ConstructionField.COLUMN_STATUS, entity.getStatus());
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		int ret = db.update(Constr_ConstructionField.TABLE_NAME, cv
				, Constr_ConstructionField.COLUMN_CONSTRUCT_ID +"=?"
				, new String[]{String.valueOf(entity.getConstructId())});
		KttsDatabaseHelper.INSTANCE.close();
		return ret;
	}

	/**
	 * Lay tat ca danh sach cong trinh
	 * 
	 * @return
	 */
	public List<Constr_ConstructionEntity> getAllConstr_Construction() {
		List<Constr_ConstructionEntity> listConstr_Construction = new ArrayList<Constr_ConstructionEntity>();
		// Select All Query
		String selectQuery = "SELECT  * FROM "
				+ Constr_ConstructionField.TABLE_NAME;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Constr_ConstructionEntity curItem = new Constr_ConstructionEntity();
				curItem = this.converCursorToItem(cursor);
				// Adding contact to list
				listConstr_Construction.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listConstr_Construction;
	}

	/**
	 * Lay danh sach cong trinh duoc giao cho nguoi dung
	 * 
	 * @return
	 */
	public List<Constr_Construction_EmployeeEntity> getAllConstr_ConstructionByUser(
			int idUser) {
		List<Constr_Construction_EmployeeEntity> listConstr_Construction = new ArrayList<Constr_Construction_EmployeeEntity>();

		// Select All Query
		String selectQuery = "SELECT C.CONSTRUCT_ID,  C.CONSTRT_CODE, C.CONSTRT_NAME, C.STATION_CODE, C.STATION_ID, S.SUPERVISION_CONSTR_ID, S.SUPV_CONSTR_TYPE,"
				+ " S.SUPERVISION_PROGRESS,S.SUPERVISION_STATUS, S.DESIGN_TYPE, S.SUPERVISION_STATUS"
				+ " FROM CONSTR_CONSTRUCTIONS C"
				+ " INNER JOIN SUPERVISION_CONSTR S ON C.CONSTRUCT_ID= S.CONSTRUCT_ID "
				+ " INNER JOIN SUPERVISION_CONSTR_REQ SR ON S.SUPERVISION_CONSTR_REQ_ID= SR.SUPERVISION_CONSTR_REQ_ID "
				+ " WHERE SR.IS_ACTIVE= 1 AND S.IS_LOCK = 0 AND SR.RECV_EMPLOYEE_ID = "
				+ String.valueOf(idUser);

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();
				curItem = this.converCursorConstruction_EmployeeToItem(cursor);
				// Adding contact to list
				listConstr_Construction.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listConstr_Construction;
	}

	/**
	 * Ham search cong trinh theo nguoi dung
	 * 
	 * @param idUser
	 *            Id nguoi dung
	 * @param iStatus
	 *            id trang thai
	 * @param iType
	 *            id loai cong trinh(Bts..)
	 * @param iProgress
	 *            tien do cong trinh
	 * 
	 * @param stationCode
	 *            ma tram hoac ma tuyen
	 * 
	 * @param startPointCode
	 *            diem dau cong trinh
	 * 
	 * @param endPointCode
	 *            diem cuoi cong trinh
	 * 
	 * @return
	 */
	public List<Constr_Construction_EmployeeEntity> seachConstr_ConstructionByUser(
			Long idUser, String sConstrCode, int iStatus, int iType,
			int iProgress, String stationCode, String startPointCode,
			String endPointCode, int start, int limit) {
		//TODO gia tri tra ve test
	//	return seachConstr_ConstructionByUserTest();
		
		List<Constr_Construction_EmployeeEntity> listConstr_Construction = new ArrayList<Constr_Construction_EmployeeEntity>();
		// Select All Query
		String selectQuery = "SELECT C.CONSTRUCT_ID,  C.CONSTRT_CODE, C.CONSTRT_NAME, C.STATION_CODE, C.STATION_ID, C.START_POINT_CODE, C.END_POINT_CODE, S.SUPERVISION_CONSTR_ID, S.SUPV_CONSTR_TYPE,"
				+ " S.SUPERVISION_PROGRESS,S.SUPERVISION_STATUS, S.DESIGN_TYPE, S.SUPERVISION_STATUS, C.CAT_STATION_TYPE_ID,"
				+ " SR.REQUEST_DATE, SR.FINISH_DATE, S.CAT_PROGRESS_WORK_ID, CPW.NAME, S.HAND_OVER_DATE,S.STARTED_DATE,S.COMPLETED_DATE, S.DEPLOY_EXPECTED_DAY "
				+ " FROM CONSTR_CONSTRUCTIONS C "
				+ " INNER JOIN SUPERVISION_CONSTR S ON C.CONSTRUCT_ID= S.CONSTRUCT_ID "
				+ " INNER JOIN SUPERVISION_CONSTR_REQ SR ON S.SUPERVISION_CONSTR_REQ_ID= SR.SUPERVISION_CONSTR_REQ_ID "
				+ " INNER JOIN CAT_PROGRESS_WORK CPW ON CPW.ID = S.CAT_PROGRESS_WORK_ID "
				+ " WHERE SR.IS_ACTIVE= 1 AND S.IS_LOCK = 0 AND S.IS_ACTIVE= 1";

		StringBuilder sCause = new StringBuilder();
		sCause.append(" AND C.CONSTRT_CODE like '%" + sConstrCode + "%'");
		sCause.append(" AND C.STATION_CODE like '%" + stationCode + "%'");
		if (iType != Constants.CONSTR_TYPE.BTS) {
			sCause.append(" AND C.START_POINT_CODE like '%" + startPointCode
					+ "%'");
			sCause.append(" AND C.END_POINT_CODE like '%" + endPointCode + "%'");
		}

		sCause.append(" AND SR.RECV_EMPLOYEE_ID = " + String.valueOf(idUser));

		if (iStatus > Constants.SEARCH_VALUE_DEFAULT) {
			sCause.append(" AND S.SUPERVISION_STATUS = "
					+ String.valueOf(iStatus));
		}
		if (iType > Constants.SEARCH_VALUE_DEFAULT) {
			if (iType == Constants.CONSTR_TYPE.BTS) {
				sCause.append(" AND S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.BTS));
			} else if (iType == Constants.CONSTR_TYPE.BRCD) {
				sCause.append(" AND S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.BRCD));
			} else if (iType == Constants.CONSTR_TYPE.SH) {
				sCause.append(" AND S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.SH));
			} else {
				sCause.append(" AND ( " );
				sCause.append(" 	  S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.LINE));
				sCause.append("      OR S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.LINE_BACKGROUND));
				sCause.append("      OR S.SUPV_CONSTR_TYPE = "
						+ String.valueOf(Constants.CONSTR_TYPE.LINE_HANG));
				sCause.append(" ) " );
			}
		}
		if (iProgress > Constants.SEARCH_VALUE_DEFAULT) {
			sCause.append(" AND S.CAT_PROGRESS_WORK_ID = "
					+ String.valueOf(iProgress));
		}
		sCause.append(" ORDER BY " + Supervision_ConstrField.COLUMN_ACCEPT_DATE
				+ " DESC");
		sCause.append(" LIMIT " + String.valueOf(start) + ","
				+ String.valueOf(limit));
		selectQuery += sCause.toString();
		Log.e("TAG", "seachConstr_ConstructionByUser: " + selectQuery );
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();
				curItem = this.converCursorConstruction_EmployeeToItem(cursor);
				// Adding contact to list
				listConstr_Construction.add(curItem);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		// return contact list
		return listConstr_Construction;
	}

	// HungTN test
	private List<Constr_Construction_EmployeeEntity> seachConstr_ConstructionByUserTest(){
		List<Constr_Construction_EmployeeEntity> listConstr_Construction = new ArrayList<Constr_Construction_EmployeeEntity>();
		//Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();
		Constr_Construction_EmployeeEntity curItem1 = new Constr_Construction_EmployeeEntity();
		Constr_Construction_EmployeeEntity curItem2 = new Constr_Construction_EmployeeEntity();
		//kienpv test 8/9/2016
		for(int i = 0; i<5;i++){
			Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();
			curItem.setConstructId(12345668L);

			curItem.setStationId(234567L);

			curItem.setStationCode("STO3232323");

			curItem.setStartPointCode("POI23445");

			curItem.setEndPointCode("ENDPOI232323");

			curItem.setSupvConstrType(i+1); // loai cong trinh

			curItem.setSupervision_Constr_Id(121212L);

			curItem.setCatStationTypeId(4444444L);

			curItem.setConstrCode("CON33335555");

			curItem.setConstrName("CONAME666666888888");

			curItem.setRequestDate("22/08/2016");

			curItem.setFinishDate("23/08/2016");

			curItem.setCatProgressWorkId(1111);

			curItem.setHandOverDate("10/08/2016");

			curItem.setStartedDate("11/08/2016");

			curItem.setCompletedDate("12/08/2016");

			curItem.setNameProgress("abc123");

			curItem.setDeployExpectedDay(i);

			String sProgress = "20";
			if (StringUtil.isNullOrEmpty(sProgress)) {
				curItem.setConstrStatusId(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setConstrProgressId(Integer.parseInt(sProgress));
			}

			String sStatus = "1";
			if (StringUtil.isNullOrEmpty(sStatus)) {
				curItem.setConstrStatusId(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setConstrStatusId(Integer.parseInt(sStatus));
			}

			String sDesignType = "1";
			if (StringUtil.isNullOrEmpty(sDesignType)) {
				curItem.setDesignType(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setDesignType(Integer.parseInt(sDesignType));
			}
			
			listConstr_Construction.add(curItem);
		}
		//--
//		curItem1 = curItem;
//		curItem1.setSupvConstrType(2);
//		listConstr_Construction.add(curItem1);
//		curItem2 = curItem;
//		curItem2.setSupvConstrType(3);
		return listConstr_Construction;
		
	}
	
	
	/**
	 * Ham convert ra thuoc tinh nguoi dung danh cho danh sach cong trinh giam
	 * sat
	 * 
	 * @param cursor
	 *            Vi tri con tro hien tai
	 * @return Tra ve mot Entity cong trinh, neu khong co tra ve cong trinh voi
	 *         gia tri thuoc tinh mac dinh
	 */
	private Constr_Construction_EmployeeEntity converCursorConstruction_EmployeeToItem(
			Cursor cursor) {
		Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();
		try {
			curItem.setConstructId(cursor.getLong(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_CONSTRUCT_ID)));

			curItem.setStationId(cursor.getLong(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_STATION_ID)));

			curItem.setStationCode(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_STATION_CODE)));

			curItem.setStartPointCode(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_START_POINT_CODE)));

			curItem.setEndPointCode(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_END_POINT_CODE)));

			curItem.setSupvConstrType(Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_SUPV_CONSTR_TYPE))));

			curItem.setSupervision_Constr_Id(cursor.getLong(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_SUPERVISION_CONSTR_ID)));

			curItem.setCatStationTypeId(cursor.getLong(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_CAT_STATION_TYPE_ID)));

			curItem.setConstrCode(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_CONSTRT_CODE)));

			curItem.setConstrName(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_CONSTRT_NAME)));

			curItem.setRequestDate(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_REQUEST_DATE)));

			curItem.setFinishDate(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_FINISH_DATE)));

			curItem.setCatProgressWorkId(cursor.getInt(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_CAT_PROGRESS_WORK_ID)));

			curItem.setHandOverDate(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_HAND_OVER_DATE)));

			curItem.setStartedDate(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_STARTED_DATE)));

			curItem.setCompletedDate(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_COMPLETED_DATE)));

			curItem.setNameProgress(cursor.getString(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_NAME_PROGRESS)));

			curItem.setDeployExpectedDay(cursor.getInt(cursor
					.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_DEPLOY_EXPECTED_DAY)));

			String sProgress = cursor
					.getString(cursor
							.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_SUPERVISION_PROGRESS));
			if (StringUtil.isNullOrEmpty(sProgress)) {
				curItem.setConstrStatusId(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setConstrProgressId(Integer.parseInt(sProgress));
			}

			String sStatus = cursor
					.getString(cursor
							.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_SUPERVISION_STATUS));
			if (StringUtil.isNullOrEmpty(sStatus)) {
				curItem.setConstrStatusId(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setConstrStatusId(Integer.parseInt(sStatus));
			}

			String sDesignType = cursor
					.getString(cursor
							.getColumnIndex(Constr_Construction_EmployeeField.COLUMN_DESIGN_TYPE));
			if (StringUtil.isNullOrEmpty(sDesignType)) {
				curItem.setDesignType(Constants.ID_ENTITY_DEFAULT);
			} else {
				curItem.setDesignType(Integer.parseInt(sDesignType));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return curItem;
	}

	/**
	 * Ham convert mot doi tuong Cursor sang Entity Convert tat ca cac thuoc
	 * tinh
	 * 
	 * @param cursor
	 *            Vi tri con tro hien tai
	 * @return Tra ve mot Entity cong trinh, neu khong co tra ve cong trinh voi
	 *         gia tri thuoc tinh mac dinh
	 */
	private Constr_ConstructionEntity converCursorToItem(Cursor cursor) {
		Constr_ConstructionEntity curItem = new Constr_ConstructionEntity();
		try {
			curItem.setConstructId(cursor.getLong(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRUCT_ID)));

			curItem.setStationCode(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_STATION_CODE)));

			curItem.setAddress(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_ADDRESS)));

			curItem.setProvinceCode(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_PROVINCE_CODE)));

			curItem.setStationId(cursor.getLong(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_STATION_ID)));

			curItem.setConstrType(Integer.parseInt(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTR_TYPE))));

			curItem.setConstrCode(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRT_CODE)));

			curItem.setConstrName(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRT_NAME)));

			curItem.setConstrAddress(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS)));

			curItem.setStartingDate(DateConvert.convertStringToDate(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_STARTING_DATE))));

			curItem.setStatus(cursor.getInt(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_STATUS)));

			curItem.setLatitude(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_LATTITUDE)));
			curItem.setLongtitude(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_LONGTITUDE)));

		} catch (Exception e) {
			// TODO: handle exception
		}
		return curItem;
	}

	/**
	 * Ham lay cong trinh de set loai tuyen cho cong trinh
	 * 
	 * @param idContruction
	 * @return
	 */
	public Constr_Construction_EmployeeEntity getConstr_ConstructionStation(
			long idContruction) {
		Constr_Construction_EmployeeEntity curItem = new Constr_Construction_EmployeeEntity();

		String selectQuery = "SELECT C.CONSTRUCT_ID,  C.CONSTRT_CODE, C.CONSTRT_NAME,"
				+ " C.STATION_CODE"
				+ " FROM CONSTR_CONSTRUCTIONS C"
				+ " WHERE C.CONSTRUCT_ID = " + String.valueOf(idContruction);

		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor != null) {
			cursor.moveToFirst();
			curItem.setConstructId(cursor.getLong(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRUCT_ID)));

			curItem.setConstrCode(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_CONSTRT_CODE)));

			curItem.setStationCode(cursor.getString(cursor
					.getColumnIndex(Constr_ConstructionField.COLUMN_STATION_CODE)));

			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return curItem;
	}

	/**
	 * Ham kiem tra xem ban ghi da ton tai chua
	 * 
	 * @param itemId
	 *            Id cong trinh
	 * @return Cong trinh duoc giam sat
	 */
	public boolean checkExitItem(long itemId) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db
				.query(Constr_ConstructionField.TABLE_NAME,
						new String[] { Constr_ConstructionField.COLUMN_CONSTRUCT_ID },
						Constr_ConstructionField.COLUMN_CONSTRUCT_ID + "=?",
						new String[] { String.valueOf(itemId) }, null, null,
						null, null);
		if (cursor.moveToFirst()) {
			bResult = true;
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}



	/**
	 * Ham cap nhat gia tri dong bo tu server ve
	 * 
	 * @param listData
	 * @return
	 */
	public boolean updateGetData(List<Constr_ConstructionEntity> listData) {
		boolean bResult = false;
		for (Constr_ConstructionEntity curItem : listData) {
			if (this.checkExitItem(curItem.getConstructId())) {
				// TODO viet ham update
			} else {
				this.addItem(curItem);
			}
		}
		return bResult;
	}

}
