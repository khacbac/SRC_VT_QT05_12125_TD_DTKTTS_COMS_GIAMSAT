package com.viettel.common;

public class ActionEventConstant {
	public static final int LOGIN = 1;
	public static final int LOG_OUT = 2;
	public static final int REQEST_LOGIN = 101010;
	public static final int ERROR_CODE_SUCCESS = 200;
	public static final int INVALID = -1;
	public static final int ACTIVITY_FINISH = 101011;
	public static final int ACTIVITY_CHECKOUT = 101000;
	/* Go to Activity */
	public static final int GOTO_HOME_ACTIVITY = 111010;
	/* Set loai tuyen cho cong trinh */
	public static final int GOTO_SET_LINE_ACTIVITY = 111011;
	/* Giam sat bts */
	public static final int GOTO_SUPERVISION_BTS_ACTIVITY = 111012;
	/* Giam sat tuyen ngam */
	public static final int GOTO_SUPERVISION_LINE_BACKGROUND_ACTIVITY = 111013;
	/* Giam sat bang rong co dinh */
	public static final int GOTO_BRCD_BACKGROUND_ACTIVITY = 123432;
	/* Giam sat subheadend */
	public static final int GOTO_SUB_BACKGROUND_ACTIVITY = 123433;
	/* Giam sat tuyen treo */
	public static final int GOTO_SUPERVISION_LINE_HANG_ACTIVITY = 111014;
	/* Giam sat bang rong co dinh */
	public static final int GOTO_SUPERVISION_BRCD_MT_ACTIVITY = 1400001;
	public static final int GOTO_SUPERVISION_BRCD_TTTK_ACTIVITY = 1400002;
	public static final int GOTO_SUPERVISION_SUB_ACTIVITY = 1400003;
	/* Giam sat bts (giam sat cot va tiep dia) */
	public static final int GOTO_SUPERVISION_BTS_PILLAR_ACTIVITY = 120000;

	/* Giam sat bts (giam sat cot va tiep dia) */
	public static final int GOTO_POSITION_BTS_PILLAR_ACTIVITY = 120001;

	/* Giam sat bts (giam sat thi cong phong may va nha may no) */
	public static final int GOTO_SUPERVISION_BTS_CAT_WORK_ACTIVITY = 120002;

	/* Giam sat bts (giam sat thi cong keo dien) */
	public static final int GOTO_SUPERVISION_BTS_POWER_POLE_ACTIVITY = 120003;

	/* Giam sat bts (giam sat lap dat thiet bi) */
	public static final int GOTO_SUPERVISION_BTS_EQUIP_ACTIVITY = 120004;

	/* Giam sat bts (giam sat lap dat viba) */
	public static final int GOTO_SUPERVISION_BTS_VIBA_ACTIVITY = 120005;

	/* Giam sat bts (giam sat do kiem va tich hop) */
	public static final int GOTO_SUPERVISION_BTS_MEASURE_ACTIVITY = 120006;
	
	/* giam sat do kich thuoc cong trinh */
	public static final int GOTO_SUPERVISION_MEASURE_CONSTR_ACTIVITY = 120008;
	
	//--HungTN add new 23/8/2016
	/* cap nhat doi thi cong */
	public static final int GOTO_SUPERVISION_CNDTC_ACTIVITY = 120009;
	public static final int GOTO_SUPERVISION_CNV_ACTIVITY = 120010;
	
	//---

	/* Giam sat bts (giam sat do kiem va tich hop) */
	public static final int GOTO_SUPERVISION_BTS_KL_ACTIVITY = 120007;
	
	/* Tao moi ke hoach */
	public static final int GOTO_PLAN_ACTIVITY = 150000;
	
	/* man hinh about app */
	public static final int GOTO_ABOUT_ACTIVITY = 150001; 

	/* Sync */
	/* Yeu cau dong bo du lieu */
	public static final int REQEST_SYNC = 1400001;
	/* Sync */
	/* Yeu cau lay map */
	public static final int MAP = 1400456;
	/* Yeu lay nguoi dung dang nhap */
	public static final int REQUEST_SYNC_EMPLOYEE = 1300001;

	/* Yeu cau lay khoa cua mot bang */
	public static final int REQUEST_KEYKTTS = 1300006;

	/* Yeu cau lay du lieu tren server ve */
	public static final int REQUEST_GETDATA = 1300007;
	
	/* Yeu cau gui du lieu len server */
	public static final int REQUEST_UPDATEDATA = 1300008;
	
	/* Yeu cau gui file len server */
	public static final int REQUEST_UPDATEIMAGE = 1300010;
	
	/* Yeu cau download anh */
	public static final int REQUEST_DOWNLOADIMAGE = 1300012;

	/* Yeu cau lay du lieu cong trinh */
	public static final int REQUEST_GET_CONSTR_CONSTRUCTIONS = 13000020;
	
	/* hungkm - Yeu cau gui file log len server */
	public static final int REQUEST_UPDATE_LOGFILE = 13000021;

}
