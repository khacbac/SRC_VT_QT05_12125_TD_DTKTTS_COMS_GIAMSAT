package com.viettel.database.field;

/**Lop mo ta thuoc tinh cua giam sat do kiem (do kiem soi thuoc cong trinh tuyen treo)
 * @author cuongdk3
 *
 */
public class Supervision_Line_Hg_FiberField  implements BaseField{
	/**
	 * ten bang do kiem soi
	 */
	public static final String TABLE_NAME = "SUPERVISION_LINE_HG_FIBER";
	
	/**
	 * id do kiem soi
	 */
	public static final String COLUMN_SUPERVISION_LINE_HG_FIBER_ID = "SUPERVISION_LINE_HG_FIBER_ID";
	
	/**
	 * ten soi
	 */
	public static final String COLUMN_FIBER_NAME = "FIBER_NAME";
	
	/**
	 * gia tri soi (don vi db)
	 */
	public static final String COLUMN_MEASURE_VALUE_DB = "MEASURE_VALUE_DB";
	
	/**
	 * id khoa ngoai den bang giam sat tuyen treo
	 */
	public static final String COLUMN_SUPERVISION_LINE_HANG_ID = "SUPERVISION_LINE_HANG_ID";
}
