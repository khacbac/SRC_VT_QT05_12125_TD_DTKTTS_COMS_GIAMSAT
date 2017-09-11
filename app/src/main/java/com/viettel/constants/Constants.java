package com.viettel.constants;

import com.viettel.ktts.R;

public class Constants {
	public static final String FILE_SETTING = "com.viettel.ktts_setting";
	public static final String USER_ID_SETTING = "USER_ID_SETTING";
	public static final String USER_FULLNAME_SETTING = "USER_FULLNAME_SETTING";
	public static final String LONGIN_NAME_SETTING = "LONGIN_NAME_SETTING";
	public static final String GROUP_CODE_SETTING = "GROUP_CODE_SETTING";
	public static final String EMPLOYEE_CODE_SETTING = "EMPLOYEE_CODE_SETTING";
	public static final String FILE_PATH_SETTING = "FILE_PATH_SETTING";
	public static final String CLIENTID_SETTING = "CLIENTID";
	public static final String IMAGE_PATH = "/GSCT";
	public static final String IMAGE_PATH_TEMPLATE = "/GSCT/IMAGETEMPLATE";
	public static final String CHECK_IN_STATUS_SETTING = "CHECK_IN_STATUS_SETTING";
	public static final String CHECK_LOGIN_STATUS = "CHECK_LOGIN_STATUS";
	public static final String CHECKIN_TIME_SETTING = "CHECKIN_TIME_SETTING";
	public static final String CHECKIN_TABLE_ID_SETTING = "CHECKIN_TABLE_ID_SETTING";
	public static final int NOTIFICATION_MANAGER = R.string.notify_name;

	// kich thuoc cua hinh anh dinh kem
	public static final int MAX_THUMB_NAIL_WIDTH = 70;
	public static final int MAX_THUMB_NAIL_HEIGHT = 50;
	// kich thuoc anh toi da upload
	public static final int MAX_IMAGE_WIDTH_HEIGHT = 740;
	public static final int MAX_UPLOAD_IMAGE_WIDTH = 740;
	public static final int MAX_UPLOAD_IMAGE_HEIGHT = 740;

	public static final int MAX_IMAGE_CAPTURE = 5;

	public static final long DISTANCEKTTSKEY = 5000;
	public static final String STR_BLANK = "";
	public static final int DROPDOWN_HEIGHT = 33;
	public static final int SEARCH_VALUE_DEFAULT = -1;
	public static final int UNQUALIFY_WIDTH = 900;
	public static final int DROPDOWN_DEFAULT_VALUE = -1;
	public static final int NUMBER_LINE_DEFAULT = 5;
	public static final int NUMBER_LINE_HEIGHT_DEFAULT = 300;
	public static final String IMG_TAKE_INTENT = "android.media.action.IMAGE_CAPTURE";
	public static final String IMG_SELECT_INTENT = "android.intent.action.PICK";
	public static final int TAKE_PICTURE_RESULT = 1202;
	public static final int GET_LOCATION_RESULT = 2012;
	public static final int GET_MEASURE_INFO = 2013;
	public static final int SELECT_PICTURE_RESULT = 1503;
	public static final int ID_ENTITY_DEFAULT = -1;
	public static final double ID_DOUBLE_ENTITY_DEFAULT = -1;
	public static final int NUMBER_ITEM_PERPAGE = 20;
	public static final int NUMBER_MAX_ITEM_SYNC = 20;
	public static final int NUMBER_MAX_ITEM_SYNC_IMG = 3; // max anh dong bo 1
	// req
	public static final float MIN_DISTANCE_FROM_STATION = 500.0f;
	public static final String DOWNLOAD_SUCCESS = "DOWNLOAD_OK";
	public static final String DOWNLOAD_ERROR = "DOWNLOAD_ERROR";
	public static final int UPLOAD_SUCCESS = 200;
	public static final int UPLOAD_ERROR = 201;
	/* Bien dung receiver dong toan bo activity */
	public static final String RECEIVE_ACTION = "project.ktts.action";
	public static final String RECEIVE_HASHCODE = "project.ktts.hashCode";
	public static final int LOCATION_RESULT = 1302;
	public static final String POSITION_PILLAR_ANTEN = "position_pillar_anten";
	/* Sync */
	public static final String MESSAGE_ERROR_NO_CONNECT_SERVER = "Không kết nối được với máy chủ";
	public static final String MESSAGE_ERROR_NO_CONNECTION = "Không kết nối mạng được.";
	public static final String MESSAGE_TIME_OUT = "Connect bị timeout!"; // cuongdk3
	// add
	public static final String HTTPCONNECTION_POST = "POST";
	public static final int IS_ACTIVE = 1;
	public static final String GSCT_TAG_ERROR = "GSCT ERROR";

	public interface ACCEPTANCE_TYPE {
		/* cong va ranh cap trong tuyen ngam */
		public static final String BG_PIPE = "PIPE_ACCEPTANCE";
		/* Be trong tuyen ngam */
		public static final String BG_TANK = "TANK_ACCEPTANCE";
		/* Be trong tuyen ngam */

		public static final String LAP_DAT_THIET_BI = "LAP_DAT_THIET_BI";

		/* vi tri dac biet tuyen ngam */
		public static final String BG_SPECIAL_LOCATION = "SPECIAL_LOCATION_ACCEPTANCE";
		/* thi cong cot */
		public static final String BTS_PILLAR = "PILLAR_ACCEPTANCE";

		/* phong may xay moi */
		public static final String BTS_PHONG_MAY_NEW = "PHONG_MAY_NEW_ACCEPTANCE";

		/* phong may lap ghep */
		public static final String BTS_PHONG_MAY_ASSEMBLY = "PHONG_MAY_ASSEMBLY_ACCEPTANCE";

		/* nha may no xay moi */
		public static final String BTS_NHA_MAY_NO_NEW = "NHA_MAY_NO_NEW_ACCEPTANCE";

		/* nha may no lap ghep */
		public static final String BTS_NHA_MAY_NO_ASSEMBLY = "NHA_MAY_NO_ASSEMBLY_ACCEPTANCE";

		/* lap dung cot */
		public static final String BTS_LAP_DUNG_COT = "LAP_DUNG_COT_ACCEPTANCE";

		/* lap dung cot */
		public static final String BTS_TIEP_DIA = "TIEP_DIA_ACCEPTANCE";

		/* thiet bi */
		public static final String BTS_THIET_BI = "THIET_BI_ACCEPTANCE";

		/* keo day dien */
		public static final String BTS_KEO_DIEN = "KEO_DIEN_ACCEPTANCE";

		/* viba */
		public static final String BTS_VIBA = "VIBA_ACCEPTANCE";

		public static final String KEO_CAP_TRUC = "KEO_CAP_TRUC";
		public static final String MANG_XONG_TRUC = "MANG_XONG_TRUC";
		public static final String KEO_CAP_NHANH = "KEO_CAP_NHANH";
		public static final String TU_NHANH = "TU_NHANH";
		public static final String DROP_GO = "DROP_GO";
		public static final String TU_THUE_BAO = "TU_THUE_BAO";

	}

	public interface PROGRESS_TYPE {
		/* Loai bts */
		public static final int BTS_TYPE = 1;
		public static final String THI_CONG_COT_BTS = "THI_CONG_COT_BTS";
		public static final String LAP_DUNG_COT_ANTEN_BTS = "LAP_DUNG_COT_ANTEN_BTS";
		public static final String TIEP_DIA_BTS = "TIEP_DIA_BTS";
		public static final String PHONG_MAY = "PHONG_MAY";
		public static final String NHA_MAY_NO = "NHA_MAY_NO";
		public static final String KEO_DAY_DIEN_PHONG_MAY_NO = "KEO_DAY_DIEN_PHONG_MAY_NO";
		public static final String KEO_DAY_DIEN = "KEO_DAY_DIEN";
		public static final String LAP_DAT_THIET_BI = "LAP_DAT_THIET_BI";
		public static final String LAP_DAT_VIBA = "LAP_DAT_VIBA";

		/* Loai tuyen ngam */
		public static final int LINE_BACKGROUND = 2;
		public static final String BG_TANK = "BG_TANK";
		public static final String BG_PIPE = "BG_PIPE";
		public static final String BG_CABLE = "BG_CABLE";
		public static final String BG_SPECIAL = "BG_SPECIAL";

		/* Loai tuyen TREO */
		public static final int LINE_HANG = 3;
		public static final String HG_PILLAR = "HG_PILLAR";
		public static final String HG_CABLE = "HG_CABLE";

		/* Loai brcd */
		public static final int BRCD_TYPE = 4;
		public static final String BRCD_TN = "BRCD_TN";
		public static final String BRCD_DROPGO = "BRCD_DROPGO";
		public static final String BRCD_TTB = "BRCD_TTB";
	}

	public interface UNQUALIFY_TYPE {
		// -----------------------------------
		/* nha may no xay moi */
		public static final String BTS_NHA_MAY_NO_NEW_UNQUALIFY = "NHA_MAY_NO_NEW_UNQUALIFY";

		/* nha may no vuot lu */
		public static final String BTS_NHA_MAY_NO_VUOT_LU_UNQUALIFY = "NHA_MAY_NO_VUOT_LU_UNQUALIFY";

		/* nha may no tien che */
		public static final String BTS_NHA_MAY_NO_TIEN_CHE_UNQUALIFY = "NHA_MAY_NO_TIEN_CHE_UNQUALIFY";
		// ----------------------------------------------

		/* phong may xay moi */
		public static final String BTS_PHONG_MAY_NEW_UNQUALIFY = "PHONG_MAY_NEW_UNQUALIFY";

		/* phong may lap ghep */
		public static final String BTS_PHONG_MAY_LAP_GHEP_UNQUALIFY = "PHONG_MAY_LAP_GHEP_UNQUALIFY";

		/* phong may cai tao */
		public static final String BTS_PHONG_MAY_CAI_TAO_UNQUALIFY = "PHONG_MAY_CAI_TAO_UNQUALIFY";

		/* Loai tuyen ngam */
		public static final int LINE_BACKGROUND = 2;
		/* Be trong tuyen ngam */
		public static final String BG_TANK = "TANK";
		/* Be trong tuyen ngam */
		public static final String BG_PIPE = "PIPE";
		/* Be trong tuyen ngam */
		public static final String BG_CABLE = "CABLE";
		/* Be trong tuyen ngam */
		public static final String BG_MX = "MX";
		/* vi tri dac biet tuyen ngam */
		public static final String BG_SPECIAL_LOCATION = "SPECIAL_LOCATION";
		/* subheadend */
		public static final int SUB_HEADEND = 5;
		public static final String LAP_DAT_THIET_BI = "LAP_DAT_THIET_BI";

		/* bang rong co dinh */
		public static final int BRCD = 4;
		public static final String KEO_CAP_TRUC = "KEO_CAP_TRUC";
		public static final String MANG_XONG_TRUC = "MANG_XONG_TRUC";
		public static final String KEO_CAP_NHANH = "KEO_CAP_NHANH";
		public static final String TU_NHANH = "TU_NHANH";
		public static final String DROP_GO = "DROP_GO";
		public static final String TU_THUE_BAO = "TU_THUE_BAO";

		/* Loai tuyen TREO */
		public static final int LINE_HANG = 3;
		/* Cột trong tuyen treo */
		public static final String HG_PILLAR = "LINE_HG_PILLAR";
		/* Be trong tuyen ngam */
		public static final String HG_CABLE = "LINE_HG_CABLE";
		/* Be trong tuyen ngam */
		public static final String HG_MX = "MX";

		/* Loai bts */
		public static final int BTS_TYPE = 1;
		/* Phong may trong bts */
		public static final String SUB_TYPE_PHONG_MAY = "PHONG_MAY";
		/* Nha may no trong bts */
		public static final String SUB_TYPE_NHA_MAY_NO = "NHA_MAY_NO";
		/* Keo day dien nha may no trong bts */
		public static final String SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO = "KEO_DAY_DIEN_PHONG_MAY_NO";
		/* subtype pillar cho mong M0 trong bts */
		public static final String SUB_TYPE_PILLAR = "PILLAR";
		/* subtype pillar_sub cho mong Mx x<>0 trong bts */
		public static final String SUB_TYPE_PILLAR_SUB = "PILLAR_SUB";
		/* subtype pillar trong bts */
		public static final String SUB_TYPE_BTS_PILLAR = "BTS_PILLAR";

		/* work code lap dung cot anten bts */
		public static final String SUB_TYPE_LDCAT = "LAP_DUNG_COT_ANTEN_BTS";
		/* work code tiep dia bts */
		public static final String SUB_TYPE_TDBTS = "TIEP_DIA_BTS";

		/* subtype Cot trong moi trong bts */
		public static final String SUB_TYPE_COT_TRONG_MOI = "COT_TRONG_MOI";
		/* subtype keo day dien nha may no trong bts */
		public static final String SUB_TYPE_KEO_DAY_DIEN = "KEO_DAY_DIEN";

		// subtype nguyen nhan loi cho form lap dat viba
		public static final String SUB_TYPE_ANTENNA = "ANTENNA";
		public static final String SUB_TYPE_CAP_TRUNG_TAN = "CAP_TRUNG_TAN";
		public static final String SUB_TYPE_TINH_TRANG_PHAN_CUNG = "TINH_TRANG_PHAN_CUNG";
		public static final String SUB_TYPE_CAP_NGUON_DC = "CAP_NGUON_DC";
		public static final String SUB_TYPE_CAP_BANG_TAN_GOC = "CAP_BANG_TAN_GOC";

		// subtype nguyen nhan loi cho form lap dat thiet bi
		public static final String SUB_TYPE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
				= "TIEP_DAT_THOAT_SET_THANG_CAP_RACK";
		public static final String SUB_TYPE_4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
				= "4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK";// danh cho loai tram 4G
		public static final String SUB_TYPE_TIEP_DAT_FEEDER = "TIEP_DAT_FEEDER";
		public static final String SUB_TYPE_4G_TIEP_DAT_FEEDER = "4G_TIEP_DAT_FEEDER";
		public static final String SUB_TYPE_THANG_CAP = "THANG_CAP";
		public static final String SUB_TYPE_4G_THANG_CAP = "4G_THANG_CAP";
		public static final String SUB_TYPE_HT_DEN_DIEUHOA_THONGGIO = "HT_DEN_DIEUHOA_THONGGIO";
		public static final String SUB_TYPE_4G_HT_DEN_DIEUHOA_THONGGIO = "4G_HT_DEN_DIEUHOA_THONGGIO";
		public static final String SUB_TYPE_TU_DIEN_AC = "TU_DIEN_AC";
		public static final String SUB_TYPE_4G_TU_DIEN_AC = "4G_TU_DIEN_AC";
		public static final String SUB_TYPE_4G_TU_NGUON_DC_ACQUY = "4G_TU_NGUON_DC_ACQUY";
		public static final String SUB_TYPE_TU_NGUON_DC_ACQUY = "TU_NGUON_DC_ACQUY";
		public static final String SUB_TYPE_TU_BTS = "TU_BTS";
		public static final String SUB_TYPE_4G_TU_BTS = "4G_TU_BTS";
		public static final String SUB_TYPE_RACK_DF = "RACK_DF";
		public static final String SUB_TYPE_4G_RACK_DF = "4G_RACK_DF";
		public static final String SUB_TYPE_CAP_NGUON_THANG_CAP = "CAP_NGUON_THANG_CAP";
		public static final String SUB_TYPE_4G_CAP_NGUON_THANG_CAP = "4G_CAP_NGUON_THANG_CAP";
		public static final String SUB_TYPE_DEN_BAO_KHONG = "DEN_BAO_KHONG";
		public static final String SUB_TYPE_4G_DEN_BAO_KHONG = "4G_DEN_BAO_KHONG";
		public static final String SUB_TYPE_ANTEN_FEEDER_JUMPER = "ANTEN_FEEDER_JUMPER";
		public static final String SUB_TYPE_4G_ANTEN_FEEDER_JUMPER = "4G_ANTEN_FEEDER_JUMPER";
		public static final String SUB_TYPE_TAM_BIT_CAP = "TAM_BIT_CAP";
		public static final String SUB_TYPE_4G_TAM_BIT_CAP = "4G_TAM_BIT_CAP";
		public static final String SUB_TYPE_RRU_OLP = "RRU_OLP";
		public static final String SUB_TYPE_4G_RRU_OLP = "4G_RRU_OLP";
		public static final String SUB_TYPE_CAP_QUANG_NGUON_RRU = "CAP_QUANG_NGUON_RRU";
		public static final String SUB_TYPE_4G_CAP_QUANG_NGUON_RRU = "4G_CAP_QUANG_NGUON_RRU";


		public static final String SUB_TYPE_THIET_BI = "THIET_BI";
		public static final String SUB_TYPE_VIBA = "VIBA";

		// subtype cho giam sat dau thap
		public static final String SUB_TYPE_BTS_VIBA_LOW = "BTS_VIBA_LOW";
		// subtype cho giam sat dau thap
		public static final String SUB_TYPE_BTS_VIBA_HIGH = "BTS_VIBA_HIGH";
	}

	public interface DROPDOWN_TYPE {
		/* Loai cong trinh man hinh danh sach cong trinh */
		public static final String DESIGN_TYPE = "DesignType";

		/* Loai cap man hinh thiet ke tuyen ngam */
		public static final String CABLE_TYPE = "CableType";
		/* Tien do cong trinh */
		public static final String DESIGN_PROGRESS = "DesignProgress";

		/* Trang thai cong trinh */
		public static final String DESIGN_STATUS = "DesignStatus";
		/* thong tin thiet ke */
		public static final String DESIGN_INFO = "DesignInfo";
		/* hang san xuat */
		public static final String DESIGN_HSX = "DesignHsx";
		/* thong tin loai cap */
		public static final String DESIGN_SO = "DesignSo";
		public static final String DESIGN_SOI_CAP_TRUC_HT = "DesignSoicaptrucHT";
		public static final String DESIGN_SOI_CAP_TRUC_BT = "DesignSoicaptrucBT";
		public static final String DESIGN_SOI_CAP_TRUC_CS = "DesignSoicaptrucCS";
		public static final String DESIGN_SO_TU = "DesignSoTu";
		public static final String DESIGN_SO_TRUC = "DesignSoTruc";
		public static final String DESIGN_SO_DROP = "DesignSoDrop";
		public static final String DESIGN_SO_SOI_DROP_LOAI_HAI = "DesignSoSoiDropLoaiHai";

		public static final String DESIGN_SO_SOI_DROP_LOAI_BON = "DesignSoSoiDropLoaiBon";
		public static final String DESIGN_SO_SOI_DROP_LOAI_TAM = "DesignSoSoiDropLoaiTam";
		public static final String DESIGN_SO_SOI_DROP_LOAIMH = "DesignSoSoiDropLoaiMH";
		public static final String DESIGN_SO_NHANH = "DesignSoNhanh";
		/* thong tin thiet ke */
		public static final String DESIGN_ASSESS = "DesignAssess";
		/* Thong tin */
		public static final String BURY_TYPE = "BuryType";
		/* Vi tri cot anten */
		public static final String POSITION_PILLAR_ANTEN = "PositionPillarAnten";
		/* Loai cot anten */
		public static final String PILLAR_ANTEN_TYPE = "PillarAntenType";
		/* Loai nha */
		public static final String HOUSE_TYPE = "HouseType";
		/* Loai nha may no */
		public static final String FACTORY_TYPE = "FactoryType";
		/* Danh gia chat luong cot */
		public static final String ASSESS_PILLAR = "AssessPillar";
		/* Danh gia chat luong lap dung cot */
		public static final String ASSESS_BUILD_PILLAR = "AssessBuildPillar";
		/* Danh gia chat luong lap dung cot */
		public static final String STATUS_PILLAR = "StatusPillar";
		/* Trang thai thi cong phong may */
		public static final String STATUS_TCPM = "StatusTcpm";
		/* Trang thai thi cong nha may no */
		public static final String STATUS_TCNMN = "StatusTcnmn";
		/* Trang thai thi cong nha may no */
		public static final String STATUS_TCKD = "StatusTckd";
		/* Trang thai thi cong nha may no */
		public static final String DIDAY_TYPE = "DidayType";

		/* Diem dau thi cong keo dien */
		public static final String TYPE_DIEM_DAU = "DiemDau";

		/* loai tu 3g */
		public static final String TYPE_3G = "3gType";

		/* loai tu 2g */
		public static final String TYPE_2G = "2gType";

		/* so mong co trong thi cong cot */
		public static final String FOUND_NUM = "foundNum";

		/* loai thiet bi */
		public static final String EQUIP_TYPE = "equipType";

		/* tan so */
		public static final String TAN_SO = "tanSo";

		/* kich thuoc trong */
		public static final String KICH_THUOC_TRONG = "kichThuocTrong";

		/* loai xay dung */
		public static final String CONSTRUCTION_TYPE = "constructionType";

	}

	public interface CONTEXT_MENU {
		public static final int COPY = 1;
		public static final int PASTE = 2;
	}

	public interface SYNC_STEP {
		public static final int GET_CLIENTID = 1;
		public static final int GET_KTTSKEY = 2;
		public static final int UPDATE_DATA = 3;
		public static final int GET_DATA = 4;
		public static final int UPDATE_IMAGE = 5;
		public static final int GET_IMAGE = 6;
	}

	public interface SYNC_STATUS {
		public static final int ADD = 0;
		public static final int UPDATED = 1;
		public static final int EDIT = 2;
	}

	public interface ISACTIVE {
		public static final int ACTIVE = 1;
		public static final int DEACTIVE = 0;
	}

	/* Loai cong trinh */
	public interface TANK_STATUS {
		public static final int NONE = -1;
		public static final int KHONG_DAT = 0;
		public static final int DAT = 1;
	}

	/* Trang thai giam sat hang muc Dat, Khong dat */
	public interface MX_STATUS {
		public static final int KHONG_DAT = 0;
		public static final int DAT = 1;
	}

	/* Loai cong trinh */
	public interface CONSTR_TYPE {
		public static final int BTS = 1;
		public static final int BRCD = 6;

		public static final int SH = 5;
		public static final int LINE_BACKGROUND = 2;
		public static final int LINE_HANG = 3;
		public static final int LINE = 4;
	}

	/* Subheadend */
	public interface CONSTR_TYPE_SUB {
		public static final int GS = 1;
		public static final int KL = 2;

	}

	/* Tien do thuc hien giam sat cong trinh */
	public interface SUPERVISION_PROGRESS {
		public static final int UNREALZED = 1;
		public static final int DOING = 2;
		public static final int COMPLETED = 3;
		public static final int UNCOMPLETED = 4;
	}

	// loai vi tri dac biet
	public interface SPECIAL_LOCATION_TYPE {
		public static final int POINT_TO_BRIDGE = 1;
		public static final int POINT_TO_POND = 2;
	}

	/* Trang thai giam sat cong trinh */
	public interface SUPERVISION_STATUS {
		/* chua dat */
		public static final int CHUADAT = 0;
		/* Dat */
		public static final int DAT = 1;
		/* Chua danh gia */
		public static final int CHUA_DANH_GIA = 2;
	}

	// --- HungTN add new 23/08/2016
	/* Trang thai cập nhật đội thi công */
	public interface SUPERVISION_CNDTC {
		/* Doi xay dung */
		public static final String GROUP_BUILDER = "DXD";
		/* Doi lap dung */
		public static final String GROUP_ASSEMBLY = "DLD";
		/* Doi lap TB */
		public static final String GROUP_EQUIMENT = "DLTB";
	}

	// ---
	/* Trang thai giam sat cong trinh */
	public interface SUPERVISION_LOCK {
		public static final int LOCK = 1;
		public static final int UN_LOCK = 0;

	}

	/* Thong tin giam sat cong trinh */
	public interface LINE_BACKGROUND_INFO {
		public static final int THIE_TKE_INFO = 1;
		public static final int NHAT_KY_TIEN_DO_INFO = 2;
		//		public static final int TIEN_DO_INFO = 3;
		public static final int VI_TRI_DAC_BIET_INFO = 3;
		public static final int BE_CAP_INFO = 4;
		public static final int CONG_RANH_CAP_INFO = 5;
		public static final int KEO_CAP_INFO = 6;
		public static final int HAN_NOI_DO_KIEM_INFO = 7;
		public static final int MEASURE_CONSTR_INFO = 8;
		public static final int KET_LUAN_INFO = 9;

		// ---HungTN add 23/08/2016
		public static final int CAP_NHAT_DOI_THI_CONG = 10;
		public static final int CAP_NHAT_VUONG = 11;
//		public static final int NHAT_KY_TIEN_DO_INFO = 13;

		// ---
	}

	/* Thong tin giam sat bang rong co dinh */
	public interface BRCD_BACKGROUND_INFO {
		public static final int THONG_TIN_TK = 1;
		public static final int NHAT_KY_TIEN_DO_INFO = 2;
//		public static final int NHAT_KY = 2;
//		public static final int TIEN_DO = 3;
		public static final int KEO_CAP_TRUC = 3;
		public static final int MANG_SONG_TRUC = 4;
		public static final int KEO_CAP_NHANH = 5;
		public static final int TU_NHANH = 6;
		public static final int DROP_GO = 7;
		public static final int TU_THUE_BAO = 8;
		public static final int CAP_NHAT_DOI_THI_CONG = 10;
		public static final int CAP_NHAT_VUONG = 11;
		public static final int KET_LUAN_INFO = 9;


		// ---HungTN add 23/08/2016


		// ---
	}

	/* Thong tin giam sat subheadend */
	public interface SUBHEADEND_INFO {
		public static final int GIAM_SAT_LAP_DAT_THIET_BI = 1;
		public static final int KET_LUAN_INFO = 2;
		// ---HungTN add 25/08/2016
		public static final int CAP_NHAT_DOI_THI_CONG = 3;
		public static final int CAP_NHAT_VUONG = 4;
		// ---
	}

	public interface SUBHEADEND_HSX {
		public static final int NOKIA = 1;
		public static final int HUAWEI = 2;
		public static final int ERICSSON = 3;
		public static final int ZTE = 4;
		public static final int ALCATEL = 5;
	}

	/* Thong tin giam sat loai cap */
	public interface BRCD_LOAI_CAP {
		public static final int HAI_TU = 1;
		public static final int BON_TAM = 4;
		public static final int CHIN_SAU = 2;
		public static final int MUOI_HAI = 3;

	}

	/* Thong tin giam sat cong trinh tuyen treo */
	public interface LINE_HANG_INFO {
		public static final int THIE_TKE_INFO = 1;
		public static final int NHAT_KY_TIEN_DO_INFO = 2;
		//		public static final int TIEN_DO_INFO = 3;
		public static final int GS_COT_INFO = 3;
		public static final int GS_CAP_INFO = 4;
		public static final int MEASURE_CONSTR_INFO = 6;
		public static final int GS_HNDK_INFO = 5;
		public static final int KET_LUAN_INFO = 7;
		// ---HungTN add 25/08/2016
		public static final int CAP_NHAT_DOI_THI_CONG = 8;
		public static final int CAP_NHAT_VUONG = 9;
		// ---

	}

	public interface LINE_HANG_PILLAR_NEW_TYPE {
		public static final int BE_TONG = 1;
		public static final int GO = 2;

	}

	/* Loai chon truc tiep */
	public interface SUPERVISION_LINE_BACKGROUND_BURY_TYPE {
		public static final int BANG_BAO_HIEU = 1;
		public static final int ONG_HAI_MANH = 2;
		public static final int ALL = 3;
		public static final int TUYEN_CHON_TRUC_TIEP = 4;
		public static final int TUYEN_DI_DUONG = 5;
	}

	/* Loai Thiet ke */
	public interface SUPERVISION_CONSTR_NOTE_DESIGN_TYPE {
		public static final int DIEN_HINH = 1;
		public static final int CHI_TIET = 2;
	}

	/* Danh gia thiet ke */
	public interface SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS {
		public static final int PHU_HOP_THUC_TE = 1;
		public static final int KHONG_PHU_HOP_THUC_TE = 2;
	}

	/* Loai cap */
	public interface SUPERVISION_LINE_CABLE_TYPE {
		public static final int HAI = 2;
		public static final int BON = 4;
		public static final int TAM = 8;
		public static final int MUOI_HAI = 12;
		public static final int HAI_BON = 24;
		public static final int BON_TAM = 48;
		public static final int CHIN_SAU = 96;
	}

	/* Thong tin giam sat BTS */
	public interface BTS_INFO {
		public static final int THIET_TKE_INFO = 1;
		public static final int NHAT_KY_TIEN_DO_INFO = 2;
		//		public static final int TIEN_DO_INFO = 3;
		public static final int THI_CONG_TIEP_DIA_INFO = 3;
		public static final int THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO = 4;
		public static final int KEO_DIEN_INFO = 5;
		public static final int LAP_DAT_THIET_BI_INFO = 6;
		public static final int LAP_DAT_VIBA_INFO = 7;
		public static final int THI_CONG_HAN_NOI_INFO = 8;
		public static final int MEASURE_CONSTR_INFO = 9;
		public static final int KET_LUAN_INFO = 10;
		// ---HungTN add 23/08/2016
		public static final int CAP_NHAT_DOI_THI_CONG = 11;
		public static final int CAP_NHAT_VUONG = 12;

		// ---
	}

	/* Vi tri Cot anten BTS */
	public interface BTS_POS_PILLAR_ANTEN {
		public static final int TREN_MAI = 1;
		public static final int DUOI_DAT = 2;
		public static final int CO_SAN = 3;
	}

	/* Loai Cot anten BTS */
	public interface BTS_PILLAR_ANTEN_TYPE {
		public static final int DAY_CO = 1;
		public static final int TU_DUNG = 2;
		public static final int COT_THAP = 3;
		public static final int COT_COC = 4;
	}

	/* So mong co */
	public interface BTS_FOUND_NUM {
		public static final int THREE_FOUND = 3;
		public static final int FOUR_FOUND = 4;
		public static final int SIX_FOUND = 6;
	}

	/* Loai nha BTS */
	public interface BTS_HOUSE_TYPE {
		public static final int LAP_GHEP = 1;
		public static final int XAY_MOI = 2;
		public static final int CAI_TAO = 3;
		public static final int CO_SAN = 4;
		public static final int XAY_MOI_VL = 5;
		public static final int LAP_GHEP_VL = 6;
	}

	/* Loai nha may no BTS */
	public interface BTS_FACTORY_TYPE {
		public static final int NHA_XAY_MOI = 1;
		public static final int NHA_VUOT_LU = 2;
		public static final int NHA_TIEN_CHE = 3;
		public static final int XAY_MOI_VL = 4;
		public static final int LAP_GHEP_VL = 5;
		public static final int LIEN_PHONG_MAY = 6;
	}

	/* Danh gia chat luong cot anten */
	public interface BTS_ASSESS_PILLAR {
		public static final int NONE = -1;
		public static final int DAT = 1;
		public static final int KHONG_DAT = 0;
	}

	/* Danh gia chat luong cot anten */
	public interface BTS_CAT_WORK_STATUS {
		public static final int NONE = -1;
		public static final int DAT = 1;
		public static final int KHONG_DAT = 0;
	}

	/* Danh gia chat luong cot anten */
	public interface BTS_CAT_WORK_DIDAY_TYPE {
		public static final int DI_NGAM = 1;
		public static final int TREO_CAO = 2;
	}

	/* Diem dau thi cong keo dien bts */
	public interface BTS_DIEM_DAU_TYPE {
		public static final int CO_SAN = 1;
		public static final int PHAI_KEO_MOI = 2;
	}

	/* kiem tra trang thai checkout/bi checkout */
	public interface SUPV_LOCATE_CO_STATUS {
		public static final int TU_CHECKOUT = 1;
		public static final int BI_CHECKOUT = 0;
	}

	/* Thong tin giam sat chat luong cong trinh */
	public interface BTS_CONSTR_WORK {
		/* work code lap dung cot anten bts */
		public static final String WORK_CODE_LDCAT = "LAP_DUNG_COT_ANTEN_BTS";
		/* work code tiep dia bts */
		public static final String WORK_CODE_TDBTS = "TIEP_DIA_BTS";

		/* work code cho phong may */
		public static final String WORK_CODE_PHONG_MAY = "PHONG_MAY";
		/* work code nha may no */
		public static final String WORK_CODE_NHA_MAY_NO = "NHA_MAY_NO";
		/* work code keo day dien nha may no */
		public static final String WORK_CODE_KEO_DAY_DIEN_PHONG_MAY_NO = "KEO_DAY_DIEN_PHONG_MAY_NO";

		/* work code KEO DAY DIEN bts */
		public static final String WORK_CODE_KEO_DAY_DIEN = "KEO_DAY_DIEN";

		// workcode cho phan lap dat viba
		public static final String WORK_CODE_ANTENNA = "ANTENNA";
		public static final String WORK_CODE_CAP_TRUNG_TAN = "CAP_TRUNG_TAN";
		public static final String WORK_CODE_TINH_TRANG_PHAN_CUNG = "TINH_TRANG_PHAN_CUNG";
		public static final String WORK_CODE_CAP_NGUON_DC = "CAP_NGUON_DC";
		public static final String WORK_CODE_CAP_BANG_TAN_GOC = "CAP_BANG_TAN_GOC";

		// workcode cho phan lap dat thiet bi
		public static final String WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
                = "TIEP_DAT_THOAT_SET_THANG_CAP_RACK";
		public static final String WORK_CODE_TIEP_DAT_FEEDER = "TIEP_DAT_FEEDER";
		public static final String WORK_CODE_THANG_CAP = "THANG_CAP";
		public static final String WORK_CODE_HT_DEN_DIEUHOA_THONGGIO = "HT_DEN_DIEUHOA_THONGGIO";
		public static final String WORK_CODE_TU_DIEN_AC = "TU_DIEN_AC";
		public static final String WORK_CODE_TU_NGUON_DC_ACQUY = "TU_NGUON_DC_ACQUY";
		public static final String WORK_CODE_TU_BTS = "TU_BTS";
		public static final String WORK_CODE_RACK_DF = "RACK_DF";
		public static final String WORK_CODE_CAP_NGUON_THANG_CAP = "CAP_NGUON_THANG_CAP";
		public static final String WORK_CODE_DEN_BAO_KHONG = "DEN_BAO_KHONG";
		public static final String WORK_CODE_ANTEN_FEEDER_JUMPER = "ANTEN_FEEDER_JUMPER";
		public static final String WORK_CODE_TAM_BIT_CAP = "TAM_BIT_CAP";
		public static final String WORK_CODE_RRU_OLP = "RRU_OLP";
		public static final String WORK_CODE_CAP_QUANG_NGUON_RRU = "CAP_QUANG_NGUON_RRU";
	}

	/* thong tin giam sat chat luong do kiem */
	public interface BTS_CONSTR_MEASURE {
		// Đo kiểm chất lượng tuyến anten-feeder 2G
		public static final String WORK_CODE_VSWR_ALL_2G = "VSWR_ALL_2G";
		public static final String WORK_CODE_VSWR_JUMPER_2G = "VSWR_JUMPER_2G";
		public static final String WORK_CODE_VSWR_FEEDER_2G = "VSWR_FEEDER_2G";
		public static final String WORK_CODE_VSWR_JUMPER_FEEDER_2G = "VSWR_JUMPER_FEEDER_2G";
		public static final String WORK_CODE_VSWR_CONNECTER_2G = "VSWR_CONNECTER_2G";
		public static final String WORK_CODE_VSWR_ANTEN_2G = "VSWR_ANTEN_2G";
		public static final String WORK_CODE_VSWR_RESOANT_ANTEN_2G = "VSWR_RESOANT_ANTEN_2G";

		// Đo kiểm chất lượng tuyến anten-feeder 3G
		public static final String WORK_CODE_VSWR_ALL_3G = "VSWR_ALL_3G";
		public static final String WORK_CODE_VSWR_JUMPER_3G = "VSWR_JUMPER_3G";
		public static final String WORK_CODE_VSWR_FEEDER_3G = "VSWR_FEEDER_3G";
		public static final String WORK_CODE_VSWR_JUMPER_FEEDER_3G = "VSWR_JUMPER_FEEDER_3G";
		public static final String WORK_CODE_VSWR_CONNECTER_3G = "VSWR_CONNECTER_3G";
		public static final String WORK_CODE_VSWR_ANTEN_3G = "VSWR_ANTEN_3G";
		public static final String WORK_CODE_VSWR_RESOANT_ANTEN_3G = "VSWR_RESOANT_ANTEN_3G";

		// Đo kiểm Viba
		public static final String WORK_CODE_CAPACITY_VIBA = "CAPACITY_VIBA";
		public static final String WORK_CODE_OBTAIN_VIBA = "OBTAIN_VIBA";
		public static final String WORK_CODE_BER_VIBA = "BER_VIBA";

	}

	/* Loai 2G thi cong lap dat thiet bi */
	public interface BTS_2G_TYPE {
		public static final int PHAN_TAN = 1;
		public static final int TAP_TRUNG = 2;
	}

	/* Loai 3G thi cong lap dat thiet bi */
	public interface BTS_3G_TYPE {
		public static final int PHAN_TAN = 1;
		public static final int TAP_TRUNG = 2;
	}

	/* Loai thiet bi trong thi cong lap dat thiet bi */
	public interface BTS_EQUIP_TYPE {
		public static final int QUANG = 1;
		public static final int VIBA = 2;
		public static final int VSAT = 3;
	}

	/* tan so trong thi cong lap dat thiet bi */
	public interface BTS_TAN_SO {
		public static final int HAI = 2;
		public static final int BAY = 7;
		public static final int MUOI_LAM = 15;
		public static final int MUOI_TAM = 18;
		public static final int HAI_BA = 23;
	}

	/* kich thuoc trong trong thi cong lap dat thiet bi */
	public interface BTS_KICH_THUOC_TRONG {
		public static final float KHONG_PHAY_BA = 0.3f;
		public static final float KHONG_PHAY_SAU = 0.6f;
		public static final float MOT_PHAY_HAI = 1.2f;
		public static final float MOT_PHAY_NAM = 1.5f;
		public static final float HAI_PHAY_BON = 2.4f;
		public static final float BA = 3f;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BTS_POWER_POLE_EDIT {
		public static final int UNQUALIFY = 1;
		public static final int FAIL_DESC = 2;
		public static final int POWER_POLE_NAME = 3;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BTS_VIBA_EDIT {
		public static final int UNQUALIFY = 1;
		public static final int FAIL_DESC = 2;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BTS_PILLAR_EDIT {
		public static final int UNQUALIFY = 1;
		public static final int FAIL_DESC = 2;
		public static final int THIET_KE = 3;
		public static final int THUC_TE = 4;

	}

	/* Danh sach thu tu edit text tren list view */
	public interface BTS_EQUIP_EDIT {
		public static final int UNQUALIFY = 1;
		public static final int FAIL_DESC = 2;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BG_PIPE_EDIT {
		public static final int FROM_TANK = 1;
		public static final int FROM_DISTANCE = 2;
		public static final int TO_TANK = 3;
		public static final int TO_DISTANCE = 4;
		public static final int FAIL_DESC = 5;
		public static final int UNQUALIFY = 6;
		public static final int DELETE = 7;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BG_CABLE_EDIT {
		public static final int FROM_DISTANCE = 1;
		public static final int TO_DISTANCE = 2;
		public static final int FAIL_DESC = 3;
		public static final int UNQUALIFY = 4;
		public static final int DELETE = 5;
	}

	/* Danh sach thu tu edit text tren list view do kiem */
	public interface BG_MX_EDIT {
		public static final int NAME = 1;
		public static final int FAIL_DESC = 2;
		public static final int UNQUALIFY = 3;
		public static final int LOCATION = 4;
		public static final int DELETE = 5;
	}

	/* Danh sach thu tu edit text tren list view do kiem */
	public interface BG_FIBER_EDIT {
		public static final int VALUE = 1;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BG_TANK_EDIT {
		public static final int NUM_PART = 1;
		public static final int UNQUALIFY = 2;
		public static final int FAIL_DESC = 3;
		public static final int LOCATION = 4;
	}

	/* Danh sach thu tu edit text tren list view */
	public interface BG_SPECIAL_EDIT {
		public static final int UNQUALIFY = 1;
		public static final int LOCATION_START = 2;
		public static final int LOCATION_END = 3;
	}

	/* Danh sach thu tu edit text tren list view cột cáp treo */
	public interface HG_PILLAR_EDIT {
		public static final int CODE = 1;
		public static final int UNQUALIFY = 2;
		public static final int FAIL_DESC = 3;
		public static final int DELETE = 4;
	}

	public interface HG_CABLE_EDIT {
		public static final int FROM_DISTANCE = 1;
		public static final int TO_DISTANCE = 2;
		public static final int FAIL_DESC = 3;
		public static final int UNQUALIFY = 4;
		public static final int DELETE = 5;
	}

	// loai tram
	public interface STATION_TYPE {
		public static final int TYPE_2G = 1;
		public static final int TYPE_3G = 2;
		public static final int TYPE_COSITE = 3;
		public static final int TYPE_AP_DEPEND = 4;
		public static final int TYPE_AP_INDEPEND = 5;
		public static final int TYPE_XE_PHAT_SONG_LUU_DONG = 6;
		public static final int TYPE_4G = 7;
		public static final int TYPE_POP = 8;
		public static final int TYPE_REPEATER = 9;
		public static final int TYPE_TRUYEN_DAN = 10;
		public static final int TYPE_KHACH_HANG = 11;
		public static final int TYPE_TONG_TRAM_KHU_VUC = 12;
		public static final int TYPE_TONG_TRAM_CHI_NHANH = 13;
		public static final int TYPE_TONG_TRAM_DUONG_TRUC = 14;
		public static final int TYPE_PHONG_LAB = 15;
		public static final int TYPE_DIEM_TRUYEN_DAN = 17;
		public static final int TYPE_HEAD_END = 18;
		public static final int TYPE_SUB_HEAD_END = 19;
		public static final int TYPE_DAI_TRUYEN_HINH = 20;
		public static final int TYPE_TRAM_ACCU = 21;
		public static final int TYPE_TRUYEN_HINH_CAP = 22;
	}

	// loai xay dung
	public interface CONSTRUCTION_TYPE {
		public static final int XAY_MOI = 1;
		public static final int NANG_CAP = 2;
	}

	// loai do kich thuoc cong trinh
	public interface MEASUREMENT_CONSTR_TYPE {
		public static final int MEASURE_BTS = 1;
		public static final int MEASURE_LINE_BG = 2;
		public static final int MEASURE_LINE_HG = 3;
		public static final int MEASURE_BRCD = 4;
	}
	//--- HungTN add new 25/08/2016
	public static final String XAY_DUNG = "XD";
	public static final String LAP_DUNG = "LD";
	public static final String THIET_BI = "TB";
	//---
}