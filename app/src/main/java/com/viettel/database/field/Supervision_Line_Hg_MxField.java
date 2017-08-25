package com.viettel.database.field;

/**Lop mo ta thuoc tinh cua giam sat thi cong han noi (kiem tra mang xong thuoc cong trinh tuyen treo)
 * @author cuongdk3
 *
 */
public class Supervision_Line_Hg_MxField implements BaseField {
	/**
	 * ten bang kiem tra mang xong
	 */
	public static final String TABLE_NAME = "SUPERVISION_LINE_HG_MX";
	
	/**
	 * id mang xong
	 */
	public static final String COLUMN_SUPERVISION_LINE_HG_MX_ID = "SUPERVISION_LINE_HG_MX_ID";
	
	/**
	 * ten mang xong
	 */
	public static final String COLUMN_MX_NAME = "MX_NAME";
	
	/**
	 * kinh do
	 */
	public static final String COLUMN_LONGITUDE = "LONGITUDE";
	
	/**
	 * vi do
	 */
	public static final String COLUMN_LATITUDE = "LATITUDE";
	
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
