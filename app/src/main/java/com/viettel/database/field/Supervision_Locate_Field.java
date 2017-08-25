package com.viettel.database.field;

public class Supervision_Locate_Field implements BaseField {
	/**
	 * ten bang giam sat vi tri
	 */
	public static final String TABLE_NAME = "SUPERVISION_LOCATE";

	/**
	 * id giam sat vi tri
	 */
	public static final String COLUMN_SUPERVISION_LOCATE_ID = "SUPERVISION_LOCATE_ID";

	/**
	 * id giam sat cong trinh
	 */
	public static final String COLUMN_SUPERVISION_CONSTR_ID = "SUPERVISION_CONSTR_ID";

	/**
	 * kiem tra trang thai check in
	 */
	public static final String COLUMN_CHECK_IN_STATUS = "CHECK_IN_STATUS";

	/**
	 * vi tri check in
	 */
	public static final String COLUMN_CHECK_IN_LATITUDE = "CHECK_IN_LATITUDE";
	public static final String COLUMN_CHECK_IN_LONGTITUDE = "CHECK_IN_LONGTITUDE";

	/**
	 * vi tri check out
	 */
	public static final String COLUMN_CHECK_OUT_LATITUDE = "CHECK_OUT_LATITUDE";
	public static final String COLUMN_CHECK_OUT_LONGTITUDE = "CHECK_OUT_LONGTITUDE";

	/**
	 * thoi gian check in
	 */
	public static final String COLUMN_CHECK_IN_DATE = "CHECK_IN_DATE";

	/**
	 * thoi gian check out
	 */
	public static final String COLUMN_CHECK_OUT_DATE = "CHECK_OUT_DATE";

	/**
	 * ke hoach lam viec
	 */
	public static final String COLUMN_USER_PLAN = "USER_PLAN";

	/**
	 * thanh pho giam sat
	 */
	public static final String COLUMN_CAT_PROVINCES = "CAT_PROVINCES";

}
