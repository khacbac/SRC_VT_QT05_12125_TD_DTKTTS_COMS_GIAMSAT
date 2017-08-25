package com.viettel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.viettel.common.FileManager;
import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Supv_Constr_Attach_FileField;
import com.viettel.service.GpsServices;
import com.viettel.utils.DateConvert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supv_Constr_Attach_FileController {
	private Context mContext = null;
	public static final String[] allColumn = new String[] {
			Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
			Supv_Constr_Attach_FileField.COLUMN_FILE_NAME,
			Supv_Constr_Attach_FileField.COLUMN_FILE_PATH,
			Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME,
			Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID,
			Supv_Constr_Attach_FileField.COLUMN_CREATED_DATE,
			Supv_Constr_Attach_FileField.COLUMN_LONGITUDE,
			Supv_Constr_Attach_FileField.COLUMN_LATITUDE,
			Supv_Constr_Attach_FileField.COLUMN_STATUS_APPROVE,
			Supv_Constr_Attach_FileField.COLUMN_REASON_DENY,
			Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS,
			Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE,
			Supv_Constr_Attach_FileField.COLUMN_PROCESS_ID,
			Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID,
			Supv_Constr_Attach_FileField.COLUMN_SUPERVISION_CONSTR_ID
			
	};

	public static final String CREATE_SUPV_CONSTR_ATTACH_FILE_TABLE = "CREATE TABLE IF NOT EXISTS "
			+ Supv_Constr_Attach_FileField.TABLE_NAME
			+ "("
			+ Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
			+ " TEXT PRIMARY KEY,"
			+ Supv_Constr_Attach_FileField.COLUMN_FILE_NAME
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_FILE_PATH
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID
			+ " INTEGER,"
			+ Supv_Constr_Attach_FileField.COLUMN_CREATED_DATE
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_LONGITUDE
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_LATITUDE
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_STATUS_APPROVE
			+ " INTEGER,"
			+ Supv_Constr_Attach_FileField.COLUMN_REASON_DENY
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD
			+ " INTEGER DEFAULT 1,"
			+ Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD
			+ " INTEGER DEFAULT 0,"
			+ Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS
			+ " INTEGER,"
			+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE
			+ " INTEGER,"
			+ Supv_Constr_Attach_FileField.COLUMN_PROCESS_ID
			+ " INTEGER,"
			+ Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID
			+ " TEXT,"
			+ Supv_Constr_Attach_FileField.COLUMN_SUPERVISION_CONSTR_ID
			+" TEXT)";

	public Supv_Constr_Attach_FileController(Context pContext) {
		this.mContext = pContext;
	}

	/**
	 * Ham coppy file va ghi file cua nguyen nhan khong dat
	 * 
	 * @param idConstruct
	 * @param sourePathFile
	 * @param idObject
	 * @param sTableName
	 */
	public void coppyAndAddAttachFile( Constr_Construction_EmployeeEntity construct, String sourePathFile,
			long idObject, String sTableName) {
		String sFileName = FileManager.getFileName(idObject);
		String sFilePath = FileManager.getSaveFilePath(
				String.valueOf(construct.getConstructId()), sFileName);
		FileManager.coppyFile(sourePathFile, sFilePath);
		Supv_Constr_Attach_FileEntity addFileAttach = new Supv_Constr_Attach_FileEntity();
		addFileAttach.setFile_Name(sFileName);
		addFileAttach.setFile_Path(sFilePath);
		addFileAttach.setObject_Id(idObject);
		addFileAttach.setTable_Name(sTableName);
		addFileAttach.setLongitude(GpsServices.longLocation);
		addFileAttach.setLatitude(GpsServices.latLocation);
		addFileAttach.setIsActive(Constants.ISACTIVE.ACTIVE);
		addFileAttach.setProcessId(0);
		addFileAttach.setSync_Status(Constants.SYNC_STATUS.ADD);
		addFileAttach.setEmployeeId(GlobalInfo.getInstance().getUserId());
		addFileAttach.setSupervisionConstrId(construct.getSupervision_Constr_Id());
		long idFileAttach = Ktts_KeyController.getInstance().getKttsNextKey(
				Supv_Constr_Attach_FileField.TABLE_NAME);
		addFileAttach.setSupv_Constr_Attach_file_Id(idFileAttach);

		this.addAttachReturnId(addFileAttach);
	}

	/* tao moi nguoi dung va tra ve Id */
	public boolean addAttachReturnId(Supv_Constr_Attach_FileEntity attachFile) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		ContentValues values = new ContentValues();
		values.put(
				Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
				attachFile.getSupv_Constr_Attach_file_Id());
		values.put(Supv_Constr_Attach_FileField.COLUMN_FILE_NAME,
				attachFile.getFile_Name());
		values.put(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH,
				attachFile.getFile_Path());
		values.put(Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID,
				attachFile.getObject_Id());
		values.put(Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME,
				attachFile.getTable_Name());
		Date date = new Date();
		String createdDate = DateConvert.convertDateToString(date);
		values.put(Supv_Constr_Attach_FileField.COLUMN_CREATED_DATE,
				createdDate);

		values.put(Supv_Constr_Attach_FileField.COLUMN_LONGITUDE,
				attachFile.getLongitude());

		values.put(Supv_Constr_Attach_FileField.COLUMN_LATITUDE,
				attachFile.getLatitude());
		
		values.put(Supv_Constr_Attach_FileField.COLUMN_STATUS_APPROVE,
				attachFile.getStatusApprove());
		
		values.put(Supv_Constr_Attach_FileField.COLUMN_REASON_DENY,
				attachFile.getResonDeny());

		values.put(Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS,
				attachFile.getSync_Status());

		values.put(Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE,
				attachFile.getIsActive());

		values.put(Supv_Constr_Attach_FileField.COLUMN_PROCESS_ID,
				attachFile.getProcessId());

		values.put(Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID,
				attachFile.getEmployeeId());
		
		values.put(Supv_Constr_Attach_FileField.COLUMN_SUPERVISION_CONSTR_ID,
				attachFile.getSupervisionConstrId());
		// Inserting Row
		Long idItem = db.insert(Supv_Constr_Attach_FileField.TABLE_NAME, null,
				values);
		KttsDatabaseHelper.INSTANCE.close();
		if (idItem > 0) {
			bResult = true;
		}
		return bResult;
	}

	/* Get Attach file */
	public Supv_Constr_Attach_FileEntity getAttachFile(String pTableName,
			long pObjectId) {
		Supv_Constr_Attach_FileEntity attachFile = new Supv_Constr_Attach_FileEntity();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn,
				Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME + "=? AND "
						+ Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID
						+ "= ? AND "
						+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE
						+ "= ? ",
				new String[] { pTableName, String.valueOf(pObjectId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			attachFile = convertCursorToEntity(cursor);
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return attachFile;
	}
	
	/* Get Attach file */
	public List<Supv_Constr_Attach_FileEntity> getLstAttachFile(String pTableName,
			long pObjectId) {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		Cursor cursor = db.query(
				Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn,
				Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME + "=? AND "
						+ Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID
						+ "= ? AND "
						+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE
						+ "= ? ",
				new String[] { pTableName, String.valueOf(pObjectId),
						String.valueOf(Constants.ISACTIVE.ACTIVE) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity attachFile = new Supv_Constr_Attach_FileEntity();
				attachFile = convertCursorToEntity(cursor);
				listResult.add(attachFile);
			} while (cursor.moveToNext());
		}
		cursor.close();
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}

	/**
	 * ham lay tat ca file dinh kem
	 * 
	 * @return
	 */
	public List<Supv_Constr_Attach_FileEntity> getAllAttachFile() {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ?";
		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn, sqlCause, new String[] { String.valueOf(0) }, null,
				null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}

	/**
	 * Lay so luong anh can download ve Tablet khong gioi han
	 * 
	 * @param pTableName
	 * @param pObjectId
	 * @return
	 */
	public int getAttachFileDownLoad() {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD
				+ "= ? AND "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID  + " = ? ";
		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn, sqlCause, new String[] { String.valueOf(0), "0" , "1",String.valueOf(GlobalInfo.getInstance().getUserId())},
				null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult.size();
	}
	
	/**
	 * Lay danh sach anh can download ve Tablet co gioi han
	 * 
	 * @param pTableName
	 * @param pObjectId
	 * @return
	 */
	public List<Supv_Constr_Attach_FileEntity> getAttachFileDownLoad(int limit) {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD
				+ "= ? AND "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID  + " = ? ";
		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn, sqlCause, new String[] { String.valueOf(0), "0" , "1",String.valueOf(GlobalInfo.getInstance().getUserId())},
				null, null, null, String.valueOf(limit));
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}
	
	/**
	 * Lay danh sach anh can download ve Tablet khong gioi han
	 * 
	 * @param pTableName
	 * @param pObjectId
	 * @return
	 */
	public List<Supv_Constr_Attach_FileEntity> getAllAttachFileDownLoad() {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD
				+ "= ? AND "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID  + " = ? ";
		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn, sqlCause, new String[] { String.valueOf(0), "0" , "1", String.valueOf(GlobalInfo.getInstance().getUserId())},
				null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}

	/**
	 * Lay danh sach anh can upload len server
	 * 
	 * @param pTableName
	 * @param pObjectId
	 * @return
	 */
	public List<Supv_Constr_Attach_FileEntity> getAttachFileUpload(int limit) {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD
				+ "= ? and "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = ? ";
		db.rawQuery("Select * From "+Supv_Constr_Attach_FileField.TABLE_NAME
				+ " where "+sqlCause+" LIMIT ?,? ", new String[] { String.valueOf(0), "0", "1" });
		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
				allColumn, sqlCause, new String[] { String.valueOf(0), "0" },
				null, null, null, String.valueOf(limit));
		
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}
	
	public List<Supv_Constr_Attach_FileEntity> getAttachFileUploadTest(int start,int limit) {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD
				+ "= ? and "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = 1 ";
		Cursor cursor = db.rawQuery("Select * From "+Supv_Constr_Attach_FileField.TABLE_NAME
				+ " where "+sqlCause+" LIMIT ?,? ", new String[] { String.valueOf(0), "0",String.valueOf(start),String.valueOf(limit)});
//		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
//				allColumn, sqlCause, new String[] { String.valueOf(0), "0" },
//				null, null, null, String.valueOf(limit));
		
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}
	
	/** cuongdk3
	 * lay ra tong so file can upload len server
	 */
	public List<Supv_Constr_Attach_FileEntity> getAllAttachFileUpload() {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD
				+ "= ? and "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = 1 ";
		Cursor cursor = db.rawQuery("Select * From "+Supv_Constr_Attach_FileField.TABLE_NAME
				+ " where "+sqlCause, new String[] { String.valueOf(0), "0"});
		
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult;
	}
	
	/**
	 * cuongdk3
	 * set trang thai is_upload = 1 de khong upload anh co size = 0 len server
	 */
	public boolean isUploaded(long idItem) {
		boolean bResult = false;
		
		ContentValues values = new ContentValues();

		values.put(Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD,
				Constants.ISACTIVE.ACTIVE);
		
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sClause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " = ?";
		String[] sParas = new String[] { String.valueOf(idItem) };
		int iResult = db.update(Supv_Constr_Attach_FileField.TABLE_NAME,values,
				sClause, sParas);
		if (iResult > 0) {
			bResult = true;
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}
	
	
	/**
	 * lay ra tong so anh can upload len server
	 * @param idItem
	 * @return
	 */
	public int getNumberAttachFileUpload() {
		List<Supv_Constr_Attach_FileEntity> listResult = new ArrayList<Supv_Constr_Attach_FileEntity>();
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sqlCause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " > ? AND "
				+ Supv_Constr_Attach_FileField.COLUMN_IS_UPLOAD
				+ "= ? and "+ Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE + " = 1 ";
		Cursor cursor = db.rawQuery("Select * From "+Supv_Constr_Attach_FileField.TABLE_NAME
				+ " where "+sqlCause, new String[] { String.valueOf(0), "0"});
//		Cursor cursor = db.query(Supv_Constr_Attach_FileField.TABLE_NAME,
//				allColumn, sqlCause, new String[] { String.valueOf(0), "0" },
//				null, null, null, String.valueOf(limit));
		
		if (cursor.moveToFirst()) {
			do {
				Supv_Constr_Attach_FileEntity curItem = new Supv_Constr_Attach_FileEntity();
				curItem = this.convertCursorToEntity(cursor);
				listResult.add(curItem);
			} while (cursor.moveToNext());
			cursor.close();
		}
		KttsDatabaseHelper.INSTANCE.close();
		return listResult.size();
	}

	public boolean delete(int idItem) {
		boolean bResult = false;
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sClause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " = ?";
		String[] sParas = new String[] { String.valueOf(idItem) };
		int iResult = db.delete(Supv_Constr_Attach_FileField.TABLE_NAME,
				sClause, sParas);
		if (iResult > 0) {
			bResult = true;
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}
	
	public boolean setIsDownloaded(Long idItem) {
		boolean bResult = false;
		ContentValues values = new ContentValues();

		values.put(Supv_Constr_Attach_FileField.COLUMN_IS_DOWNLOAD,
				Constants.ISACTIVE.ACTIVE);
		
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String sClause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ " = ?";
		String[] sParas = new String[] { String.valueOf(idItem) };
		int iResult = db.update(Supv_Constr_Attach_FileField.TABLE_NAME, values, sClause, sParas);
		
		if (iResult > 0) {
			bResult = true;
		}
		KttsDatabaseHelper.INSTANCE.close();
		return bResult;
	}

	public boolean delete(Supv_Constr_Attach_FileEntity pItem) {
		ContentValues values = new ContentValues();

		values.put(Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE,
				Constants.ISACTIVE.DEACTIVE);
		
		values.put(Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);
		
		String sqlclause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(pItem
				.getSupv_Constr_Attach_file_Id()) };
		return this.updateDB(Supv_Constr_Attach_FileField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	public boolean updatePathNameFile(long idItem, String filePath,
			String fileName, int syncStatus) {
		ContentValues values = new ContentValues();

		values.put(Supv_Constr_Attach_FileField.COLUMN_FILE_NAME, fileName);

		values.put(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH, filePath);

		values.put(Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS,
				Constants.SYNC_STATUS.ADD);

		String sqlclause = Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID
				+ "= ?";
		String[] sqlPara = new String[] { String.valueOf(idItem) };
		return this.updateDB(Supv_Constr_Attach_FileField.TABLE_NAME, values,
				sqlclause, sqlPara);
	}

	private Supv_Constr_Attach_FileEntity convertCursorToEntity(Cursor cursor) {
		Supv_Constr_Attach_FileEntity attachFile = new Supv_Constr_Attach_FileEntity();
		attachFile
				.setSupv_Constr_Attach_file_Id(cursor.getLong(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID)));
		attachFile
				.setFile_Name(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_FILE_NAME)));
		attachFile
				.setFile_Path(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH)));
		attachFile
				.setObject_Id(cursor.getLong(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID)));
		attachFile
				.setTable_Name(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME)));
		attachFile
				.setCreated_Date(DateConvert.convertStringToDate(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_CREATED_DATE))));
		attachFile
				.setLongitude(Double.parseDouble(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_LONGITUDE))));
		attachFile
				.setLatitude(Double.parseDouble(cursor.getString(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_LATITUDE))));
//		 trang thai phe duyet
		attachFile
		.setStatusApprove(cursor.getInt(cursor
				.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_STATUS_APPROVE)));
		// Ly do cho trang thai phe duyet
		attachFile
		.setResonDeny(cursor.getString(cursor
				.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_REASON_DENY)));
		

		attachFile
				.setSync_Status(cursor.getInt(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS)));

		attachFile
				.setIsActive(cursor.getInt(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE)));
		
		attachFile
				.setEmployeeId(cursor.getLong(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID)));

		attachFile
				.setProcessId(cursor.getLong(cursor
						.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_PROCESS_ID)));
		
		attachFile
		.setSupervisionConstrId(cursor.getLong(cursor
				.getColumnIndex(Supv_Constr_Attach_FileField.COLUMN_SUPERVISION_CONSTR_ID)));
		return attachFile;
	}
	
	//cuongdk3 add set image upload test
	public void setImageUploadTest(){
		SQLiteDatabase db = KttsDatabaseHelper.INSTANCE.open(mContext);
		String strQuery = "update supv_constr_attach_file set is_upload = 0 where   "
                + "supv_constr_attach_file_id in (  "
                + "select supv_constr_attach_file_id from supv_constr_attach_file where object_id in (  "
                + "  select acceptance_bts_catwork_id from acceptance_bts_cat_work  where is_active = 1 and supervision_bts_cat_work_id in (  "
                + "    select supervision_bts_cat_work_id from supervision_bts_cat_work where supervision_bts_id in (  "
                + "        select supervision_bts_id from supervision_bts where supervision_constr_id in (  "
                + "          33133, 34360, 34345, 35729, 34377, 34363, 34362, 35092, 35091  "
                + "        )  "
                + "    )  "
                + "  )  "
                + ")     or object_id in (  "
                + "  select cause_bts_pillar_anten_id from cause_bts_pillar_anten where is_active = 1 and supv_bts_pillar_anten_id in (  "
                + "    select supv_bts_pillar_anten_id from supervision_bts_pillar_anten where supervision_bts_id in (  "
                + "        select supervision_bts_id from supervision_bts where supervision_constr_id in (  "
                + "          33133, 34360, 34345, 35729, 34377, 34363, 34362, 35092, 35091  "
                + "        )  "
                + "    )  "
                + "  )  "
                + ")   or object_id in (  "
                + "    select acceptance_bts_pillar_id from acceptance_bts_pillar  where is_active = 1 and supv_bts_pillar_anten_id in (  "
                + "        select supv_bts_pillar_anten_id from supervision_bts_pillar_anten where supervision_bts_id in (  "
                + "          select supervision_bts_id from supervision_bts where supervision_constr_id in (  "
                + "            33133, 34360, 34345, 35729, 34377, 34363, 34362, 35092, 35091  "
                + "          )  "
                + "      )  "
                + "    )  "
                + " ) "
                + ") ";
		
		db.execSQL(strQuery);
		KttsDatabaseHelper.INSTANCE.close();
	}
	
	
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
}