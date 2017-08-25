package com.viettel.database.field;
/**
 * Bang quan he giua nguoi dung va cong trinh Cong trinh duoc giao cho ai giam
 * sat
 * 
 * @author datht1
 * 
 */
public class Supervision_Constr_REQField implements BaseField {
	public static final String TABLE_NAME = "SUPERVISION_CONSTR_REQ";
	/* Id bang */
	public static final String COLUMN_SUPERVISION_CONSTR_REQ_ID = "SUPERVISION_CONSTR_REQ_ID";	
	/* Id nguoi giao */
	public static final String COLUMN_REQ_EMPLOYEE_ID = "REQ_EMPLOYEE_ID";
	/* Id nguoi nhan */
	public static final String COLUMN_RECV_EMPLOYEE_ID = "RECV_EMPLOYEE_ID";	
	/* Group nguoi dung */
	public static final String COLUMN_GROUP_ID = "GROUP_ID";
	/* Ma tram- chua chac chan */
	public static final String COLUMN_CODE = "CODE";
	/* Yeu cau giam sat */
	public static final String COLUMN_REQUEST_NAME = "REQUEST_NAME";
	/* Ten nguoi nhan */
	public static final String COLUMN_RECV_NAME = "RECV_NAME";
	/* Loai cong trinh */
	public static final String COLUMN_CONSTR_TYPE = "CONSTR_TYPE";
	/* Ngay giao giam sat */
	public static final String COLUMN_REQUEST_DATE = "REQUEST_DATE";
	/* Ngay hoan thanh giam sat */
	public static final String COLUMN_FINISH_DATE = "FINISH_DATE";
	/* Ten ke hoach */
	public static final String COLUMN_PLAN_NAME = "PLAN_NAME";
	/* Id nguoi tao ban ghi */
	public static final String COLUMN_CREATOR_ID = "CREATOR_ID";
	/* Ngay tao ban ghi */
	public static final String COLUMN_CREATE_DATE = "CREATE_DATE";
	/* Trang thai giao */
	public static final String COLUMN_IS_ACTIVE = "IS_ACTIVE";
}
