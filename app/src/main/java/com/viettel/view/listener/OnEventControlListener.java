package com.viettel.view.listener;

import android.view.View;

public interface OnEventControlListener {
	/* su kien logout */
	public static final int EVENT_OK_LOGOUT = 101010;
	/* su kien logout */
	public static final int EVENT_CANCE = 101011;
	/* bat thong bao */
	public static final int EVENT_MESSAGER_OK = 101012;
	/* lay vi tri google */
	public static final int EVENT_LOCATION = 101013;
	/* open GPS */
	public static final int EVENT_OPEN_GPS_OK = 101014;
	public static final int EVENT_OPEN_GPS_CANCE = 101015;
	/* Thao tac giam sat cong trinh */
	public static final int EVENT_CONSTR_CONSTRUCTION_MONITOR = 111010;
	/* Thao tac giam sat cong trinh */
	public static final int EVENT_CONSTR_CONSTRUCTION_INFO = 111031;
	/* Thao tac loai tuyen */
	public static final int EVENT_CONSTR_CONSTRUCTION_TYPE_OF_LINE = 111011;
	/* Thao tac nhap nguyen nhan khong dat */
	public static final int EVENT_SUPERVISION_TANK_UNQUALIFY = 111015;
	/* Chon nguyen nhan khong dat */
	public static final int EVENT_UNQUALIFY_CHOICE = 111016;
	/* Chon anh */
	public static final int EVENT_UNQUALIFY_TAKE_PHOTO = 111017;
	/* Text chon nguyen nhan khong dat */
	public static final int EVENT_UNQUALIFY_CHECK_UCHECK = 111018;
	/* Chon nguyen nhan khong dat */
	public static final int EVENT_SET_TEXT = 111019;
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_PIPE_EDIT = 111020;
	/* Giam sat thi cong keo cap nhanh chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_KCN_GIAM_SAT= 11567;
	/* Giam sat thi cong keo cap truc chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_KCT_GIAM_SAT= 11667;
	/* Giam sat thi cong keo cap nhanh chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_KCN_DELETE= 11570;
	/* Giam sat thi cong giam sat keo cap nhanh chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_KCN_CHI_TIET= 11568;
	/* Giam sat thi cong keo drop chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_DROP_GIAM_SAT= 11569;
	
	/* Giam sat thi cong keo drop chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_DROP_DELETE= 11570;
	/* Giam sat thi cong keo drop chi tiet */
	public static final int EVENT_SUPERVISION_BRCD_DROP_CHITIET= 11571;
	
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_BRCD_KCT_EDIT= 11143;
	
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_BRCD_TN_EDIT= 11144;
	
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_BRCD_TN_MAP= 11145;
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_TANK_EDIT = 111021;
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_CABLE_EDIT = 111022;
	/* Thao tac set tham so tren List view mang xong */
	public static final int EVENT_SUPERVISION_MX_EDIT = 111023;
	/* Thao tac set tham so tren List view mang xong */
	public static final int EVENT_SUPERVISION_FIBER_EDIT = 111024;

	/* Thao tac set tham so tren List view mang xong */
	public static final int EVENT_SUPERVISION_BG_OK = 111025;
	/* Thao tac xac nhan ghi du lieu */
	public static final int EVENT_CONFIRM_OK = 111026;
	/* Event xac nhan cap nhat ket qua dat cho tuyen ngam co item khong dat */
	public static final int EVENT_CONFIRM_TANK_SAVE_DAT_OK = 111027;
	
	/* Thao tac set tham so tren List view */
	public static final int EVENT_SUPERVISION_PILLAR_EDIT = 111028;
	
	/* Thao tac xac nhan ghi ket luan thanh cong */
	public static final int EVENT_CONFIRM_BG_KL_OK = 111029;
	/* Thao tac xac nhan ghi ket luan thanh cong */
	public static final int EVENT_CONFIRM_HG_KL_OK = 111030;

	/* Su kien dropdown item click */
	public static final int EVENT_DROPDOWN_ITEM_CLICK = 121010;

	/* Su kien chon anh */
	public static final int EVENT_IMG_VIEW = 131020;
	public static final int EVENT_IMG_TAKE_NEW = 131021;
	public static final int EVENT_IMG_TAKE_DELETE = 131022;
	public static final int EVENT_IMG_TAKE_ATTACK = 131023;
	public static final int EVENT_IMG_TAKE_EXIT = 131024;

	/*
	 * su kien chon nguyen nhan khong dat giam sat bts
	 */
	public static final int EVENT_SUPERVISION_UNQUALIFY = 131015;

	/**
	 * su kien chon dien giai trong giam sat bts
	 */
	public static final int EVENT_SUPERVISION_EDIT = 131016;

	/* Thao tac xoa 1 dong tren listview */
	public static final int EVENT_DELETE_ROW = 131014;

	/* Thao tac chon danh gia dat */
	public static final int EVENT_CHOICE_ACCESS_DAT = 140000;

	/* Thao tac chon danh gia khong dat */
	public static final int EVENT_CHOICE_ACCESS_KDAT = 140001;
	
	/* Thao tac xoa 1 item tren diem qua cau cong */
	public static final int EVENT_DELETE_ROW_BRIDGE = 140002;
	
	/* Thao tac xoa 1 item tren diem qua ao ho */
	public static final int EVENT_DELETE_ROW_POND = 140003;
	
	/* Thao tac chon copy paste */
	public static final int EVENT_COPY_PASTE = 140004;
	
	/* lay vi tri google */
	public static final int EVENT_LOCATION_START = 140005;
	
	/* lay vi tri google */
	public static final int EVENT_LOCATION_END = 140006;
	
	/* Chon anh nghiem thu */
	public static final int EVENT_ACCEPTANCE_TAKE_PHOTO = 140007;
	
	/* Dong anh nghiem thu */
	public static final int EVENT_ACCEPTANCE_CHOICE = 140008;
	
	/* event xem thong tin chi tiet do kich thuoc cong trinh */
	public static final int EVENT_VIEW_DETAIL = 140009;
	
	/* Thao tac cap nhat tien do cong trinh */
	public static final int EVENT_CONSTR_CONSTRUCTION_PROGRESS = 140010;
	
	/* Thao tac xac nhan ghi chuyen trang thai tien do cho cong trinh */
	public static final int EVENT_CONFIRM_PROGRESS = 140011;
	
	/* click nut hoan thanh save ngay hoan thanh */
	public static final int EVENT_COMPLETE_PROGRESS = 140012;

	public void onEvent(int eventType, View control, Object data);
}
