package com.viettel.database.field;

/**
 * Lop mo ta thuoc tinh cua bang cong trinh goc
 * 
 * @author datht1
 * 
 */
public class Constr_ConstructionField implements BaseField {
	/**
	 * Ten bang ma cong trinh
	 */
	public static final String TABLE_NAME = "CONSTR_CONSTRUCTIONS";
	/* Ma tram */
	public static final String COLUMN_STATION_CODE = "STATION_CODE";
	/* Dia chi tram */
	public static final String COLUMN_ADDRESS = "ADDRESS";
	/* Ma tinh */
	public static final String COLUMN_PROVINCE_CODE = "PROVINCE_CODE";
	/* ID Tram */
	public static final String COLUMN_STATION_ID = "STATION_ID";
	/* Id cong trinh */
	public static final String COLUMN_CONSTRUCT_ID = "CONSTRUCT_ID";
	/* Ma cong trinh */
	public static final String COLUMN_CONSTRT_CODE = "CONSTRT_CODE";
	/* Ten cong trinh */
	public static final String COLUMN_CONSTRT_NAME = "CONSTRT_NAME";
	/* Loai cong trinh */
	public static final String COLUMN_CONSTR_TYPE = "CONSTR_TYPE";
	/* Dia chi cong trinh */
	public static final String COLUMN_CONSTRT_ADDRESS = "CONSTRT_ADDRESS";
	/* Ngay khoi cong */
	public static final String COLUMN_STARTING_DATE = "STARTING_DATE";
	
	public static final String COLUMN_START_POINT_CODE = "START_POINT_CODE";
	
	public static final String COLUMN_END_POINT_CODE = "END_POINT_CODE";
	
	public static final String COLUMN_CAT_STATION_TYPE_ID = "CAT_STATION_TYPE_ID";
	
	/* 21: cong trinh truoc 2010; chuyen sang dung status_id */
	public static final String COLUMN_STATUS = "STATUS";
	
	public static final String COLUMN_LATTITUDE = "LATITUDE";
	public static final String COLUMN_LONGTITUDE = "LONGITUDE";

}
