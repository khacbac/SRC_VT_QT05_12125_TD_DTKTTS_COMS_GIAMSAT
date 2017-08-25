package com.viettel.database.field;
/**
 * Lop mo ta thuoc tinh giam sat thi cong cot trong moi 
 * @author cuongdk3
 *
 */
public class Supervision_Line_Hg_PillarField implements BaseField{
	/**
	 * ten bang giam sat cot trong moi
	 */
	public static final String TABLE_NAME = "SUPERVISION_LINE_HG_PILLAR";
	
	/**
	 * id giam sat thi cong cot trong moi
	 */
	public static final String COLUMN_SUPERVISION_LINE_HG_PILLAR_ID = "SUPERVISION_LINE_HG_PILLAR_ID";
	
	/**
	 * ten cot trong
	 */
	public static final String COLUMN_PILLAR_NAME = "PILLAR_NAME";
	
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
