package com.viettel.database.field;
/**Lop mo ta thuoc tinh cua giam sat thi cong keo cap
 * @author cuongdk3
 *
 */
public class Supervision_Line_Hg_CableField implements BaseField {
	/**
	 * Ten bang thi cong keo cap
	 */
	public static final String TABLE_NAME = "SUPERVISION_LINE_HG_CABLE";
	
	/**
	 * id giam sat thi cong keo cap
	 */
	public static final String COLUMN_SUPERVISION_LINE_HG_CABLE_ID = "SUPERVISION_LINE_HG_CABLE_ID";
	
	/**
	 * khoang cach tu
	 */
	public static final String COLUMN_FROM_DISTANCE = "FROM_DISTANCE";
	
	/**
	 * den khoang cach
	 */
	public static final String COLUMN_TO_DISTANCE = "TO_DISTANCE";
	
	/**
	 * trang thai
	 * dam bao , khong dam bao
	 */
	public static final String COLUMN_STATUS = "STATUS";
	
	/**
	 * 
	 */
	public static final String COLUMN_FAIL_DESC = "FAIL_DESC";
	
	/**
	 * id khoa ngoai den bang giam sat tuyen treo
	 */
	public static final String COLUMN_SUPERVISION_LINE_HANG_ID = "SUPERVISION_LINE_HANG_ID";
}
