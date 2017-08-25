package com.viettel.database.field;

/**Lop mo ta thuoc tinh cua giam sat han noi
 * @author cuongdk3
 *
 */
public class Supervision_Bts_MeasureField implements BaseField {
	/**
	 * ten bang giam sat han noi
	 */
	public static final String TABLE_NAME = "SUPERVISION_BTS_MEASURE";
	
	/**
	 * id giam sat han noi
	 */
	public static final String COLUMN_SUPERVISION_BTS_MEASURE_ID = "SUPERVISION_BTS_MEASURE_ID";
	
	/**
	 * ten phep do
	 */
	public static final String COLUMN_MEASURE_NAME = "MEASURE_NAME";
	
	/**
	 * gia tri do
	 */
	public static final String COLUMN_MEASURE_VALUE = "MEASURE_VALUE";
	
	/**
	 * loai may do
	 */
	public static final String COLUMN_MEASURE_MACHINE_TYPE = "MEASURE_MACHINE_TYPE";
	
	/**
	 * serial
	 */
	public static final String COLUMN_MEASURE_MACHINE_SERIAL = "MEASURE_MACHINE_SERIAL";
	
	/**
	 * ten nguoi do
	 */
	public static final String COLUMN_MEASURE_PERSON = "MEASURE_PERSON";
	
	/**
	 * so dien thoai nguoi do
	 */
	public static final String COLUMN_MEASURE_PERSON_MOBILE = "MEASURE_PERSON_MOBILE";	
	
	/**
	 * don vi
	 */
	public static final String COLUMN_MEASURE_GROUP = "MEASURE_GROUP";	
	/**
	 * status
	 */
	public static final String COLUMN_MEASURE_STATUS = "MEASURE_STATUS";	
	
	/**
	 * ID REFRENCE
	 */
	public static final String COLUMN_CAT_SUPV_CONSTR_MEASURE_ID = "CAT_SUPV_CONSTR_MEASURE_ID";
	
	/**
	 * id khoa ngoai den bang giam sat bts
	 */
	public static final String COLUMN_SUPERVISION_BTS_ID = "SUPERVISION_BTS_ID";
}
