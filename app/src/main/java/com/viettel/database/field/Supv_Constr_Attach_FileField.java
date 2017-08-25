package com.viettel.database.field;

/*Lop dung lay du lieu anh*/
public interface Supv_Constr_Attach_FileField extends BaseField {
	public static final String TABLE_NAME = "SUPV_CONSTR_ATTACH_FILE";
	public static final String COLUMN_SUPV_CONSTR_ATTACH_FILE_ID = "SUPV_CONSTR_ATTACH_FILE_ID";
	public static final String COLUMN_FILE_NAME = "FILE_NAME";
	public static final String COLUMN_FILE_PATH = "FILE_PATH";
	public static final String COLUMN_TABLE_NAME = "TABLE_NAME";
	public static final String COLUMN_OBJECT_ID = "OBJECT_ID";
	public static final String COLUMN_CREATED_DATE = "CREATED_DATE";
	public static final String COLUMN_LONGITUDE = "LONGITUDE";
	public static final String COLUMN_LATITUDE = "LATITUDE";
	public static final String COLUMN_IS_DOWNLOAD = "IS_DOWNLOAD";
	public static final String COLUMN_IS_UPLOAD = "IS_UPLOAD";
	// trang thai phe duyet
	public static final String COLUMN_STATUS_APPROVE = "STATUS_APPROVE";
	// ý kiến cho trang thai phê duyêt
	public static final String COLUMN_REASON_DENY = "REASON_DENY";
}
