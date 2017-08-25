package com.viettel.database.field;

/**
 * Lop mo ta thuoc tinh cua giam sat lap dat thiet bi
 * 
 * @author cuongdk3
 * 
 */
public class Supervision_Bts_EquipField implements BaseField {
	/**
	 * ten bang giam sat thiet bi
	 */
	public static final String TABLE_NAME = "SUPERVISION_BTS_EQUIP";

	/**
	 * id giam sat thiet bi
	 */
	public static final String COLUMN_SUPERVISION_BTS_EQUIP_ID = "SUPERVISION_BTS_EQUIP_ID";

	/**
	 * id hang san suat 2g (thong tin thiet bi)
	 */
	public static final String COLUMN_BTS_2G_COMPANY_ID = "BTS_2G_COMPANY_ID";
	
	/**
	 * id hang san suat 3g (thong tin thiet bi)
	 */
	public static final String COLUMN_BTS_3G_COMPANY_ID = "BTS_3G_COMPANY_ID";

	/**
	 * so tu 2G
	 */
	public static final String COLUMN_2G_TOTAL = "TOTAL_2G";

	/**
	 * so tu 3G
	 */
	public static final String COLUMN_3G_TOTAL = "TOTAL_3G";

	/**
	 * loai tu 2G
	 */
	public static final String COLUMN_2G_TYPE = "TYPE_2G";
	
	/**
	 * loai tu 3G
	 */
	public static final String COLUMN_3G_TYPE = "TYPE_3G";

	/**
	 * id khoa ngoai den bang giam sat bts
	 */
	public static final String COLUMN_SUPERVISION_BTS_ID = "SUPERVISION_BTS_ID";
	
	/**
	 * LOAI THIET BI 
	 */
	public static final String COLUMN_TRANS_TYPE = "TRANS_TYPE";
	
	/**
	 * id HANG SAN XUAT (THONG TIN THIET BI TRUYEN DAN)
	 */
	public static final String COLUMN_TRANS_COMPANY_ID = "TRANS_COMPANY_ID";
	
	/**
	 * TAN SO
	 */
	public static final String COLUMN_TRANS_FREQ = "TRANS_FREQ";
	
	/**
	 * KICH THUOC TRONG
	 */
	public static final String COLUMN_TRANS_VIBA_DIMENSION = "TRANS_VIBA_DIMENSION";
}
