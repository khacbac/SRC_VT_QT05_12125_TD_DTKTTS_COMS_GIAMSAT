package com.viettel.utils;

import android.content.Context;
import android.util.Log;

import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.ktts.R;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Lop su dung cac bien cuc bo
 *
 * @author datht1
 */
public class GloablUtils {
    /**
     * save object vao file
     *
     * @param object
     * @param fileName
     * @author: DoanDM
     * @return: void
     * @throws:
     */
    public static void saveObject(Object object, String fileName) {
        try {
            FileOutputStream fos = GlobalInfo.getInstance().getAppContext()
                    .openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Ham sinh ten be tu dong
     *
     * @param iSTT So thu tu cua be
     * @return Ten be tu dong
     */
    public static String getTankName(int iSTT) {
        String sResult = "";
        try {
            String sSTT = String.valueOf(iSTT);
            if (sSTT.length() < 2) {
                sResult = "BM00" + sSTT;
                return sResult;
            }
            if (sSTT.length() < 3) {
                sResult = "BM0" + sSTT;
                return sResult;
            }
            if (sSTT.length() < 4) {
                sResult = "BM" + sSTT;
                return sResult;
            }
        } catch (Exception e) {
            sResult = "";
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return sResult;
    }

    /**
     * Ham sinh ten cot tu dong
     *
     * @param iSTT So thu tu cua cot
     * @return Ten be tu dong
     */
    public static String getPillarName(int iSTT) {
        String sResult = "";
        try {
            String sSTT = String.valueOf(iSTT);
            sResult = "M" + sSTT;
            return sResult;
        } catch (Exception e) {
            sResult = "";
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return sResult;
    }

    /**
     * Ham sinh ten cot trong moi tu dong
     *
     * @param iSTT So thu tu cua cot
     * @return Ten be tu dong
     */
    public static String getPowerPoleName(int iSTT) {
        String sResult = "";
        try {
            String sSTT = String.valueOf(iSTT);
            sResult = "CM" + sSTT;
            return sResult;
        } catch (Exception e) {
            sResult = "";
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return sResult;
    }

    /**
     * Ham sinh ten ao tu dong
     *
     * @param iSTT So thu tu cua AO
     * @return Ten be tu dong
     */
    public static String getPondName(int iSTT) {
        String sResult = "";
        try {
            String sSTT = String.valueOf(iSTT);
            sResult = "Ao " + sSTT;
            return sResult;
        } catch (Exception e) {
            sResult = "";
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return sResult;
    }

    /**
     * Ham lay so thu tu cua cot cuoi cung trong danh sach
     *
     * @param iSTT So thu tu cua cot
     * @return Ten be tu dong
     */
    public static int getPillarAntenLastRow(
            List<Cause_Bts_Pillar_AntenEntity> listData) {
        int stt;

        try {
            if (listData.size() == 0) {
                stt = 0;
            } else {
                String namePillarAnten = listData.get(listData.size() - 1)
                        .getSupervision_Bts_Pillar_AntenEntity()
                        .getFOUNDATION_NAME();
                stt = Integer.parseInt(namePillarAnten.substring(1)) + 1;
            }

        } catch (Exception e) {
            stt = -1;
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return stt;
    }

    /**
     * Ham lay so thu tu cua cot trong moi cuoi cung trong danh sach
     *
     * @param iSTT So thu tu cua cot
     * @return Ten be tu dong
     */
    public static int getPowerPoleIdLastRow(
            List<Cause_Bts_Power_PoleEntity> listData) {
        int stt;

        try {
            if (listData.size() == 0) {
                stt = 0;
            } else {
                String namePowerPole = listData.get(listData.size() - 1)
                        .getBts_PowerPoleEntity().getPower_POLE_NAME();
                stt = Integer.parseInt(namePowerPole.substring(2)) + 1;
            }

        } catch (Exception e) {
            stt = -1;
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return stt;
    }

    /**
     * Ham sinh ten be tu dong
     *
     * @param iSTT So thu tu cua be
     * @return Ten be tu dong
     */
    public static String getFiberName(int iSTT) {
        String sResult = "";
        try {
            String sSTT = String.valueOf(iSTT);
            String fiberName = StringUtil.getString(R.string.line_bg_mx_dk_soi);
            if (sSTT.length() < 2) {
                sResult = fiberName + " 0" + sSTT;
            } else {
                sResult = fiberName + " " + sSTT;
            }

        } catch (Exception e) {
            sResult = "";
            Log.i("Error_Tank_Name", e.getMessage());
        }
        return sResult;
    }

    /**
     * Lay danh sach loai cong trinh
     */
    public static List<DropdownItemEntity> getListConstructionType(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            // if (!sChoice.isEmpty()) {
            // listData.add(new DropdownItemEntity(
            // Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
            // Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            // }
            listData.add(new DropdownItemEntity(
                    Constants.CONSTR_TYPE.BTS,
                    StringUtil.getString(R.string.constr_sonstruction_type_bts),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            listData.add(new DropdownItemEntity(Constants.CONSTR_TYPE.LINE,
                    StringUtil
                            .getString(R.string.constr_sonstruction_type_line),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            listData.add(new DropdownItemEntity(Constants.CONSTR_TYPE.BRCD,
                    StringUtil
                            .getString(R.string.constr_sonstruction_type_brcd),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));

            listData.add(new DropdownItemEntity(Constants.CONSTR_TYPE.SH,
                    StringUtil.getString(R.string.constr_sonstruction_type_sh),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay ten tuyen cua cong trinh
     *
     * @param iConstType : id loai tuyen
     * @return
     */
    public static String getStringConstructionType(int iConstType) {
        String sResult = "";
        switch (iConstType) {
            case Constants.CONSTR_TYPE.BTS:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_type_bts);
                break;
            case Constants.CONSTR_TYPE.LINE:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_type_line);
                break;
            case Constants.CONSTR_TYPE.LINE_HANG:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_setline_tt);
                break;
            case Constants.CONSTR_TYPE.LINE_BACKGROUND:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_setline_tn);
                break;
            case Constants.CONSTR_TYPE.BRCD:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_setline_brcd);
                break;
            case Constants.CONSTR_TYPE.SH:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_setline_sh);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * lay danh sach tien do cong trinh
     */
    public static List<DropdownItemEntity> getListConstructionProgress(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_PROGRESS));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_PROGRESS.UNREALZED,
                    StringUtil
                            .getString(R.string.constr_sonstruction_progress_unrealzed),
                    Constants.DROPDOWN_TYPE.DESIGN_PROGRESS));

            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_PROGRESS.DOING,
                    StringUtil
                            .getString(R.string.constr_sonstruction_progress_doing),
                    Constants.DROPDOWN_TYPE.DESIGN_PROGRESS));

            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_PROGRESS.COMPLETED,
                    StringUtil
                            .getString(R.string.constr_sonstruction_progress_complete),
                    Constants.DROPDOWN_TYPE.DESIGN_PROGRESS));

            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_PROGRESS.UNCOMPLETED,
                    StringUtil
                            .getString(R.string.constr_sonstruction_progress_uncompleted),
                    Constants.DROPDOWN_TYPE.DESIGN_PROGRESS));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay ten tien do cua cong trinh
     *
     * @param iConstType : id loai tuyen
     * @return
     */
    public static String getStringConstructionProgress(int iConstStatus) {
        String sResult = "";
        switch (iConstStatus) {
            case Constants.SUPERVISION_PROGRESS.UNREALZED:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_progress_unrealzed);
                break;
            case Constants.SUPERVISION_PROGRESS.DOING:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_progress_doing);
                break;
            case Constants.SUPERVISION_PROGRESS.COMPLETED:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_progress_complete);
                break;
            case Constants.SUPERVISION_PROGRESS.UNCOMPLETED:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_progress_uncomplete);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * lay danh sach trang thai cong trinh
     */
    public static List<DropdownItemEntity> getListConstructionStatus(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_STATUS));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_STATUS.CHUADAT,
                    StringUtil
                            .getString(R.string.constr_sonstruction_status_chuadat),
                    Constants.DROPDOWN_TYPE.DESIGN_STATUS));

            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_STATUS.DAT,
                    StringUtil
                            .getString(R.string.constr_sonstruction_status_dat),
                    Constants.DROPDOWN_TYPE.DESIGN_STATUS));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_STATUS.CHUA_DANH_GIA,
                    StringUtil
                            .getString(R.string.constr_sonstruction_status_chuadanhgia),
                    Constants.DROPDOWN_TYPE.DESIGN_STATUS));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay ten trang thai cong trinh
     *
     * @param iConstStatus : id trang thai
     * @return
     */
    public static String getStringConstructionStatus(int iConstStatus) {
        String sResult = "";
        switch (iConstStatus) {
            case Constants.SUPERVISION_STATUS.CHUADAT:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_status_chuadat);
                break;
            case Constants.SUPERVISION_STATUS.DAT:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_status_dat);
                break;
            case Constants.SUPERVISION_STATUS.CHUA_DANH_GIA:
                sResult = StringUtil
                        .getString(R.string.constr_sonstruction_status_chuadanhgia);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach thong tin
     *
     * @return
     */
    public static List<DropdownItemEntity> getListLineBackgroundInfo(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_design_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.NHAT_KY_TIEN_DO_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_nhatky_tiendo_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_BACKGROUND_INFO.TIEN_DO_INFO, GlobalInfo
//                    .getInstance().getAppContext().getResources()
//                    .getString(R.string.line_background_tiendo_info),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_vtdb_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_gsbc_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_gscrc_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_gskc_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_gshndk_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.MEASURE_CONSTR_INFO,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.measure_constr_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---HungTN add new 23/08/2016
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG,
//                    GlobalInfo.getInstance().getAppContext().getResources()
//                            .getString(R.string.line_hang_design_info_cndtc),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG, GlobalInfo
//                    .getInstance().getAppContext().getResources()
//                    .getString(R.string.line_hang_design_info_cnv),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---
            listData.add(new DropdownItemEntity(
                    Constants.LINE_BACKGROUND_INFO.KET_LUAN_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_keluan_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListLineHangInfo(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.THIE_TKE_INFO, StringUtil
                    .getString(R.string.line_hang_design_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.NHAT_KY_TIEN_DO_INFO, StringUtil
                    .getString(R.string.line_hang_nhatky_tiendo_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_HANG_INFO.TIEN_DO_INFO, StringUtil
//                    .getString(R.string.line_hang_tiendo_info),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.GS_COT_INFO, StringUtil
                    .getString(R.string.line_hang_pillar_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.GS_CAP_INFO, StringUtil
                    .getString(R.string.line_hang_cable_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.GS_HNDK_INFO, StringUtil
                    .getString(R.string.line_hang_mx_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.MEASURE_CONSTR_INFO, StringUtil
                    .getString(R.string.line_hang_measure_constr),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---HungTN add new 25/08/2016
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_HANG_INFO.CAP_NHAT_DOI_THI_CONG, StringUtil
//                    .getString(R.string.line_hang_design_info_cndtc),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.LINE_HANG_INFO.CAP_NHAT_VUONG, StringUtil
//                    .getString(R.string.line_hang_design_info_cnv),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---

            listData.add(new DropdownItemEntity(
                    Constants.LINE_HANG_INFO.KET_LUAN_INFO, StringUtil
                    .getString(R.string.line_hang_keluan_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdBackgroundInfo(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance()
                    .getAppContext()
                    .getResources()
                    .getString(
                            R.string.brcd_background_design_info_tttk),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.NHAT_KY, GlobalInfo
                    .getInstance()
                    .getAppContext()
                    .getResources()
                    .getString(
                            R.string.line_hang_nhatky_tiendo_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.BRCD_BACKGROUND_INFO.TIEN_DO, GlobalInfo
//                    .getInstance()
//                    .getAppContext()
//                    .getResources()
//                    .getString(
//                            R.string.line_hang_tiendo_info),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(R.string.brcd_background_design_info_kct),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(R.string.brcd_background_design_info_mst),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.brcd_background_design_info_keocapnhanh),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.TU_NHANH,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.brcd_background_design_info_tn),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.DROP_GO,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.brcd_background_design_info_dropgo),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(R.string.brcd_background_design_info_ttb),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---HungTN add new 23/08/2016
//            listData.add(new DropdownItemEntity(
//                    Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG,
//                    GlobalInfo
//                            .getInstance()
//                            .getAppContext()
//                            .getResources()
//                            .getString(
//                                    R.string.brcd_background_design_info_cndtc),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG,
//                    GlobalInfo
//                            .getInstance()
//                            .getAppContext()
//                            .getResources()
//                            .getString(R.string.brcd_background_design_info_cnv),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---

            listData.add(new DropdownItemEntity(Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_background_keluan_info),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListSubInfo(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_info_giamsat),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            //--HungTN add new 25/08/2016
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.CAP_NHAT_DOI_THI_CONG,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_info_cndtc),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.CAP_NHAT_VUONG,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_info_cnv),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            //---
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.KET_LUAN_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.sub_background_design_info_kl),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListSubHSX(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_HSX.NOKIA,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(R.string.sub_background_design_hsx_nokia),
                    Constants.DROPDOWN_TYPE.DESIGN_HSX));
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_HSX.HUAWEI, GlobalInfo
                    .getInstance()
                    .getAppContext()
                    .getResources()
                    .getString(
                            R.string.sub_background_design_hsx_huawei),
                    Constants.DROPDOWN_TYPE.DESIGN_HSX));
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_HSX.ERICSSON,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_hsx_ericsson),
                    Constants.DROPDOWN_TYPE.DESIGN_HSX));
            listData.add(new DropdownItemEntity(Constants.SUBHEADEND_HSX.ZTE,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.sub_background_design_hsx_zte),
                    Constants.DROPDOWN_TYPE.DESIGN_HSX));
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_HSX.ALCATEL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_hsx_alcatel),
                    Constants.DROPDOWN_TYPE.DESIGN_HSX));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListSubHsxInfo(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_info_giamsat),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // HungTN add new 25/08/2016
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.sub_background_design_info_giamsat),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            //---
            listData.add(new DropdownItemEntity(
                    Constants.SUBHEADEND_INFO.KET_LUAN_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.sub_background_design_info_kl),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    // public static List<DropdownItemEntity> getListbrcdLoaicaptruc(
    // String sChoice) {
    // List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
    // try {
    // if (!sChoice.isEmpty()) {
    // listData.add(new DropdownItemEntity(
    // Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
    // Constants.DROPDOWN_TYPE.DESIGN_SO_TRUC));
    // }
    // listData.add(new DropdownItemEntity(
    // Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
    // .getInstance().getAppContext().getResources()
    // .getString(R.string.line_cable_type_haibon),
    // Constants.DROPDOWN_TYPE.DESIGN_SO_TRUC));
    // listData.add(new DropdownItemEntity(
    // Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
    // .getInstance().getAppContext().getResources()
    // .getString(R.string.line_cable_type_bontam),
    // Constants.DROPDOWN_TYPE.DESIGN_SO_TRUC));
    // listData.add(new DropdownItemEntity(
    // Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
    // .getInstance().getAppContext().getResources()
    // .getString(R.string.line_cable_type_chinsau),
    // Constants.DROPDOWN_TYPE.DESIGN_SO_TRUC));
    //
    //
    // } catch (Exception e) {
    // Log.i("Error", e.getMessage() + e.getStackTrace());
    // }
    // return listData;
    // }
    public static List<DropdownItemEntity> getListbrcdLoaicapdrop(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_DROP));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_DROP));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_DROP));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_DROP));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_muoihai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_DROP));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdSoSoidrop_LoaiHai(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_HAI));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdSoSoidrop_LoaiBon(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_BON));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdSoSoidrop_LoaiTam(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAI_TAM));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdSoSoidrop_LoaiMH(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_SOI_DROP_LOAIMH));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdLoaicapnhanh(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.NHAT_KY, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.TIEN_DO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_muoihai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_haibon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bontam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_NHANH));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdsosoicap(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chin),
                    Constants.DROPDOWN_TYPE.DESIGN_SO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdsosoicaptruc_ht(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chin),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_HT));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdsosoicaptruc_bt(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chin),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_BT));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdsosoicaptruc_cs(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chin),
                    Constants.DROPDOWN_TYPE.DESIGN_SOI_CAP_TRUC_CS));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static List<DropdownItemEntity> getListbrcdsotunhanh(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_SO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_khong),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_mot),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_ba),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_nam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_sau),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bay),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));
            listData.add(new DropdownItemEntity(
                    Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chin),
                    Constants.DROPDOWN_TYPE.DESIGN_SO_TU));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringLineBackgroundInfo(int iDesignInfo) {
        String sResult = "";
        switch (iDesignInfo) {
            case Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_info);
                break;
            case Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_vtdb_info);
                break;

            case Constants.LINE_BACKGROUND_INFO.MEASURE_CONSTR_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.measure_constr_info);
                break;

            case Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_gsbc_info);
                break;
            case Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_gscrc_info);
                break;
            case Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_gskc_info);
                break;

            case Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_gshndk_info);
                break;
            //---HungTN add new 25/08/2016
            case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_cndtc_info);
                break;
            case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_cnv_info);
                break;
            //---
            case Constants.LINE_BACKGROUND_INFO.KET_LUAN_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_keluan_info);
                break;
            default:
                break;
        }
        return sResult;
    }

    public static String getStringBRCDBackgroundInfo(int iDesignInfo) {
        String sResult = "";
        switch (iDesignInfo) {
            case Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_tttk);
                break;
            case Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_mst);
                break;
            case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_kct);
                break;

            case Constants.BRCD_BACKGROUND_INFO.TU_NHANH:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_tn);
                break;

            case Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_ttb);
                break;
            case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH:
                sResult = GlobalInfo
                        .getInstance()
                        .getAppContext()
                        .getResources()
                        .getString(R.string.brcd_background_design_info_keocapnhanh);
                break;
            case Constants.BRCD_BACKGROUND_INFO.DROP_GO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_dropgo);
                break;
            //--Hungtn add new 25/08/2016
            case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_cndtc);
                break;
            case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_cnv);
                break;
            //---


            case Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.brcd_background_design_info_kt);
                break;
            default:
                break;
        }
        return sResult;
    }

    public static String getStringSubInfo(int iDesignInfo) {
        String sResult = "";
        switch (iDesignInfo) {
            case Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.sub_background_design_info_giamsat);
                break;
            case Constants.SUBHEADEND_INFO.KET_LUAN_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.sub_background_design_info_kl);
                break;
            default:
                break;
        }
        return sResult;
    }

    public static String getStringLineHangInfo(int iDesignInfo) {
        String sResult = "";
        switch (iDesignInfo) {
            case Constants.LINE_HANG_INFO.THIE_TKE_INFO:
                sResult = StringUtil.getString(R.string.line_hang_design_info);
                break;
            case Constants.LINE_HANG_INFO.GS_COT_INFO:
                sResult = StringUtil.getString(R.string.line_hang_pillar_info);
                break;
            case Constants.LINE_HANG_INFO.GS_CAP_INFO:
                sResult = StringUtil.getString(R.string.line_hang_cable_info);
                break;
            case Constants.LINE_HANG_INFO.MEASURE_CONSTR_INFO:
                sResult = StringUtil.getString(R.string.measure_constr_info);
                break;
            case Constants.LINE_HANG_INFO.GS_HNDK_INFO:
                sResult = StringUtil.getString(R.string.line_hang_mx_info);
                break;
            //---Hungtn add new 25/08/2016
            case Constants.LINE_HANG_INFO.CAP_NHAT_DOI_THI_CONG:
                sResult = StringUtil.getString(R.string.line_hang_design_info_cndtc);
                break;
            case Constants.LINE_HANG_INFO.CAP_NHAT_VUONG:
                sResult = StringUtil.getString(R.string.line_hang_design_info_cnv);
                break;
            //---
            case Constants.LINE_HANG_INFO.KET_LUAN_INFO:
                sResult = StringUtil.getString(R.string.line_hang_keluan_info);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai cap
     *
     * @return
     */
    public static List<DropdownItemEntity> getListLineCableType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.CABLE_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.HAI, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_hai),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.BON, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bon),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.TAM, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_tam),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.MUOI_HAI, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_muoihai),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.HAI_BON, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_haibon),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.BON_TAM, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_bontam),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_CABLE_TYPE.CHIN_SAU, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.line_cable_type_chinsau),
                    Constants.DROPDOWN_TYPE.CABLE_TYPE));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringLineCableInfo(int iCableInfo) {
        String sResult = "";
        switch (iCableInfo) {
            case Constants.SUPERVISION_LINE_CABLE_TYPE.HAI:
                sResult = StringUtil.getString(R.string.line_cable_type_hai);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.BON:
                sResult = StringUtil.getString(R.string.line_cable_type_bon);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.TAM:
                sResult = StringUtil.getString(R.string.line_cable_type_tam);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.MUOI_HAI:
                sResult = StringUtil.getString(R.string.line_cable_type_muoihai);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.HAI_BON:
                sResult = StringUtil.getString(R.string.line_cable_type_haibon);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.BON_TAM:
                sResult = StringUtil.getString(R.string.line_cable_type_bontam);
                break;
            case Constants.SUPERVISION_LINE_CABLE_TYPE.CHIN_SAU:
                sResult = StringUtil.getString(R.string.line_cable_type_chinsau);
                break;
            default:
                break;
        }
        return sResult;
    }

    public static String getStringDesignAssess(int iDesignAssess) {
        String sResult = "";
        switch (iDesignAssess) {
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.PHU_HOP_THUC_TE:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_assess_phtt);
                break;
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.KHONG_PHU_HOP_THUC_TE:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_assess_kphtt);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai cap danh gia thiet ke
     *
     * @return
     */
    public static List<DropdownItemEntity> getListLineDesignAssess(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_ASSESS));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.PHU_HOP_THUC_TE,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.line_background_design_assess_phtt),
                    Constants.DROPDOWN_TYPE.DESIGN_ASSESS));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.KHONG_PHU_HOP_THUC_TE,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.line_background_design_assess_kphtt),
                    Constants.DROPDOWN_TYPE.DESIGN_ASSESS));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay danh sach danh gia thiet ke
     *
     * @return
     */
    public static List<DropdownItemEntity> getListLineDesignType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.DIEN_HINH,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_design_type_dh),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.CHI_TIET,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_design_type_ct),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringDesignType(int iDesignType) {
        String sResult = "";
        switch (iDesignType) {
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.DIEN_HINH:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_type_dh);
                break;
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.CHI_TIET:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_type_ct);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai chon truc tiep cho tuyen ngam
     *
     * @return
     */
    public static List<DropdownItemEntity> getListLineBuryType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.BURY_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.BANG_BAO_HIEU,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_bury_type_bbh),
                    Constants.DROPDOWN_TYPE.BURY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.ONG_HAI_MANH,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.line_background_bury_type_ohm),
                    Constants.DROPDOWN_TYPE.BURY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.ALL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.line_background_bury_type_bbh_ohm),
                    Constants.DROPDOWN_TYPE.BURY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.TUYEN_CHON_TRUC_TIEP,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.line_background_bury_type_tuyen_tructiep),
                    Constants.DROPDOWN_TYPE.BURY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.TUYEN_DI_DUONG,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.line_background_bury_type_tuyen_diduong),
                    Constants.DROPDOWN_TYPE.BURY_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBuryType(int iBuryType) {
        String sResult = "";
        switch (iBuryType) {
            case Constants.ID_ENTITY_DEFAULT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.text_choice1);
                break;
            case Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.BANG_BAO_HIEU:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_bury_type_bbh);
                break;
            case Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.ONG_HAI_MANH:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_bury_type_ohm);
                break;
            case Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.ALL:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_bury_type_bbh_ohm);
                break;
            case Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.TUYEN_CHON_TRUC_TIEP:
                sResult = GlobalInfo
                        .getInstance()
                        .getAppContext()
                        .getResources()
                        .getString(
                                R.string.line_background_bury_type_tuyen_tructiep);
                break;
            case Constants.SUPERVISION_LINE_BACKGROUND_BURY_TYPE.TUYEN_DI_DUONG:
                sResult = GlobalInfo
                        .getInstance()
                        .getAppContext()
                        .getResources()
                        .getString(R.string.line_background_bury_type_tuyen_diduong);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach thong tin thiet ke bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBTSInfo(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_INFO));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.THIET_TKE_INFO, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_info_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.NHAT_KY_TIEN_DO_INFO, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_info_nhatky),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.BTS_INFO.TIEN_DO_INFO, GlobalInfo.getInstance()
//                    .getAppContext().getResources()
//                    .getString(R.string.bts_info_tiendo),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_info_tctd_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_info_tcpm_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.KEO_DIEN_INFO, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_info_tckd_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_info_tcldtb_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.LAP_DAT_VIBA_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_info_tcldvb_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_info_tchn_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.MEASURE_CONSTR_INFO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_info_measure_constr),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---HungTN add new 23/08/2016
//            listData.add(new DropdownItemEntity(
//                    Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG, GlobalInfo
//                    .getInstance().getAppContext().getResources()
//                    .getString(R.string.bts_info_cndtc),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
//            listData.add(new DropdownItemEntity(
//                    Constants.BTS_INFO.CAP_NHAT_VUONG, GlobalInfo.getInstance()
//                    .getAppContext().getResources()
//                    .getString(R.string.bts_info_cnv),
//                    Constants.DROPDOWN_TYPE.DESIGN_INFO));
            // ---

            listData.add(new DropdownItemEntity(
                    Constants.BTS_INFO.KET_LUAN_INFO, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_info_kl_design),
                    Constants.DROPDOWN_TYPE.DESIGN_INFO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBTSInfo(int iDesignInfo) {
        String sResult = "";
        switch (iDesignInfo) {
            case Constants.BTS_INFO.THIET_TKE_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.line_background_design_info);
                break;
            case Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tctd_design);
                break;
            case Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tcpm_design);
                break;
            case Constants.BTS_INFO.KEO_DIEN_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tckd_design);
                break;
            case Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tcldtb_design);
                break;
            case Constants.BTS_INFO.LAP_DAT_VIBA_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tcldvb_design);
                break;
            case Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_tchn_design);
                break;
            case Constants.BTS_INFO.MEASURE_CONSTR_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_measure_constr);
                break;
            //---HungTN add new 24/08/2016
            case Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_cndtc);
                break;
            case Constants.BTS_INFO.CAP_NHAT_VUONG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_cnv);
                break;
            //---
            case Constants.BTS_INFO.KET_LUAN_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_kl_design);
                break;
            case Constants.BTS_INFO.NHAT_KY_TIEN_DO_INFO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_info_nhat_ky);
                break;
//            case Constants.BTS_INFO.TIEN_DO_INFO:
//                sResult = GlobalInfo.getInstance().getAppContext().getResources()
//                        .getString(R.string.bts_info_tien_do);
//                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach thong tin thiet ke bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsDesignType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.DIEN_HINH,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_design_type_dh),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.CHI_TIET,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_design_type_ct),
                    Constants.DROPDOWN_TYPE.DESIGN_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsDesignType(int iDesignType) {
        String sResult = "";
        switch (iDesignType) {
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.DIEN_HINH:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_type_dh);
                break;
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_TYPE.CHI_TIET:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_type_ct);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach danh gia thiet ke bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsDesignAssess(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DESIGN_ASSESS));
            }
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.PHU_HOP_THUC_TE,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_design_assess_phtt),
                    Constants.DROPDOWN_TYPE.DESIGN_ASSESS));
            listData.add(new DropdownItemEntity(
                    Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.KHONG_PHU_HOP_THUC_TE,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_design_assess_kphtt),
                    Constants.DROPDOWN_TYPE.DESIGN_ASSESS));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsDesignAssess(int iDesignAssess) {
        String sResult = "";
        switch (iDesignAssess) {
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.PHU_HOP_THUC_TE:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_assess_phtt);
                break;
            case Constants.SUPERVISION_CONSTR_NOTE_DESIGN_ASSESS.KHONG_PHU_HOP_THUC_TE:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_assess_kphtt);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach vi tri cot anten trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsPosPillarAnten(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.POSITION_PILLAR_ANTEN));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_POS_PILLAR_ANTEN.TREN_MAI, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_trenmai),
                    Constants.DROPDOWN_TYPE.POSITION_PILLAR_ANTEN));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_POS_PILLAR_ANTEN.DUOI_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_duoidat),
                    Constants.DROPDOWN_TYPE.POSITION_PILLAR_ANTEN));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_POS_PILLAR_ANTEN.CO_SAN, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_cosan),
                    Constants.DROPDOWN_TYPE.POSITION_PILLAR_ANTEN));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsPillarAnten(int iPosPillarAnten) {
        String sResult = "";
        switch (iPosPillarAnten) {
            case Constants.BTS_POS_PILLAR_ANTEN.TREN_MAI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_trenmai);
                break;
            case Constants.BTS_POS_PILLAR_ANTEN.DUOI_DAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_duoidat);
                break;
            case Constants.BTS_POS_PILLAR_ANTEN.CO_SAN:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_cosan);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai cot anten trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsPillarType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_PILLAR_ANTEN_TYPE.DAY_CO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_type_dayco),
                    Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_PILLAR_ANTEN_TYPE.TU_DUNG, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_type_tudung),
                    Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_PILLAR_ANTEN_TYPE.COT_THAP,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_design_pillar_type_cotthap),
                    Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_PILLAR_ANTEN_TYPE.COT_COC, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_design_pillar_type_cotcoc),
                    Constants.DROPDOWN_TYPE.PILLAR_ANTEN_TYPE));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsPillarType(int iPillarType) {
        String sResult = "";
        switch (iPillarType) {
            case Constants.BTS_PILLAR_ANTEN_TYPE.DAY_CO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_type_dayco);
                break;
            case Constants.BTS_PILLAR_ANTEN_TYPE.TU_DUNG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_type_tudung);
                break;
            case Constants.BTS_PILLAR_ANTEN_TYPE.COT_THAP:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_type_cotthap);
                break;
            case Constants.BTS_PILLAR_ANTEN_TYPE.COT_COC:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_pillar_type_cotcoc);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai nha trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsHouseType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.LAP_GHEP, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_design_house_type_lapghep),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.XAY_MOI, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_design_house_type_xaymoi),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.CAI_TAO, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_design_house_type_caitao),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.CO_SAN, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_design_house_type_cosan),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.XAY_MOI_VL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_house_type_xaymoi_vuotlu),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_house_type_lapghep_vuotlu),
                    Constants.DROPDOWN_TYPE.HOUSE_TYPE));
        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsHouseType(int iHouseType) {
        String sResult = "";
        switch (iHouseType) {
            case Constants.BTS_HOUSE_TYPE.LAP_GHEP:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_lapghep);
                break;
            case Constants.BTS_HOUSE_TYPE.XAY_MOI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_xaymoi);
                break;
            case Constants.BTS_HOUSE_TYPE.CAI_TAO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_caitao);
                break;
            case Constants.BTS_HOUSE_TYPE.CO_SAN:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_cosan);
                break;
            case Constants.BTS_HOUSE_TYPE.XAY_MOI_VL:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_xaymoi_vuotlu);
                break;
            case Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_lapghep_vuotlu);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai nha may no trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsFactoryType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.FACTORY_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_FACTORY_TYPE.NHA_XAY_MOI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_factory_type_nhaxaymoi),
                    Constants.DROPDOWN_TYPE.FACTORY_TYPE));
            // listData.add(new DropdownItemEntity(
            // Constants.BTS_FACTORY_TYPE.NHA_VUOT_LU,
            // GlobalInfo
            // .getInstance()
            // .getAppContext()
            // .getResources()
            // .getString(
            // R.string.bts_design_factory_type_nhavuotlu),
            // Constants.DROPDOWN_TYPE.FACTORY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_FACTORY_TYPE.NHA_TIEN_CHE,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_factory_type_nhatienche),
                    Constants.DROPDOWN_TYPE.FACTORY_TYPE));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_FACTORY_TYPE.XAY_MOI_VL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_house_type_xaymoi_vuotlu),
                    Constants.DROPDOWN_TYPE.FACTORY_TYPE));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_FACTORY_TYPE.LAP_GHEP_VL,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_house_type_lapghep_vuotlu),
                    Constants.DROPDOWN_TYPE.FACTORY_TYPE));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_FACTORY_TYPE.LIEN_PHONG_MAY,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_design_house_type_lien_phong_may),
                    Constants.DROPDOWN_TYPE.FACTORY_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsFactoryType(int iFactoryType) {
        String sResult = "";
        switch (iFactoryType) {
            case Constants.BTS_FACTORY_TYPE.NHA_XAY_MOI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_factory_type_nhaxaymoi);
                break;
            // case Constants.BTS_FACTORY_TYPE.NHA_VUOT_LU:
            // sResult = GlobalInfo.getInstance().getAppContext().getResources()
            // .getString(R.string.bts_design_factory_type_nhavuotlu);
            // break;
            case Constants.BTS_FACTORY_TYPE.NHA_TIEN_CHE:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_factory_type_nhatienche);
                break;
            case Constants.BTS_FACTORY_TYPE.XAY_MOI_VL:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_xaymoi_vuotlu);
                break;
            case Constants.BTS_FACTORY_TYPE.LAP_GHEP_VL:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_lapghep_vuotlu);
                break;
            case Constants.BTS_FACTORY_TYPE.LIEN_PHONG_MAY:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_design_house_type_lien_phong_may);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach danh gia chat luong cot trong giam sat bts (giam sat
     * thi cong va tiep dia)
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsAssessPillar(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.ASSESS_PILLAR));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_danhgia_dat),
                    Constants.DROPDOWN_TYPE.ASSESS_PILLAR));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.KHONG_DAT,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(R.string.bts_assess_pillar_danhgia_kodat),
                    Constants.DROPDOWN_TYPE.ASSESS_PILLAR));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsAssessPillar(int iAssessPillar) {
        String sResult = "";
        switch (iAssessPillar) {
            case Constants.BTS_ASSESS_PILLAR.DAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_danhgia_dat);
                break;
            case Constants.BTS_ASSESS_PILLAR.KHONG_DAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_danhgia_kodat);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach so mong co trong giam sat bts (giam sat thi cong cot va
     * tiep dia)
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsFoundNum(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.FOUND_NUM));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_FOUND_NUM.THREE_FOUND, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_three_found),
                    Constants.DROPDOWN_TYPE.FOUND_NUM));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_FOUND_NUM.FOUR_FOUND, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_four_found),
                    Constants.DROPDOWN_TYPE.FOUND_NUM));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_FOUND_NUM.SIX_FOUND, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_six_found),
                    Constants.DROPDOWN_TYPE.FOUND_NUM));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsFoundNum(int iFoundNum) {
        String sResult = "";
        switch (iFoundNum) {
            case Constants.BTS_FOUND_NUM.THREE_FOUND:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_three_found);
                break;
            case Constants.BTS_FOUND_NUM.FOUR_FOUND:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_four_found);
                break;
            case Constants.BTS_FOUND_NUM.SIX_FOUND:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_six_found);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach danh gia chat luong lap dung cot trong giam sat bts
     * (giam sat thi cong va tiep dia)
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsAssessBuildPillar(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.ASSESS_BUILD_PILLAR));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_dat),
                    Constants.DROPDOWN_TYPE.ASSESS_BUILD_PILLAR));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.KHONG_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_khongdat),
                    Constants.DROPDOWN_TYPE.ASSESS_BUILD_PILLAR));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay danh sach trang thai thi cong tiep dia trong giam sat bts (giam
     * sat thi cong va tiep dia)
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsStatusPillar(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.STATUS_PILLAR));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_dat),
                    Constants.DROPDOWN_TYPE.STATUS_PILLAR));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_ASSESS_PILLAR.KHONG_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_khongdat),
                    Constants.DROPDOWN_TYPE.STATUS_PILLAR));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay danh sach trang thai thi cong phong may trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsCatWorkStatusTcpm(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.STATUS_TCPM));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_dat),
                    Constants.DROPDOWN_TYPE.STATUS_TCPM));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.KHONG_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_khongdat),
                    Constants.DROPDOWN_TYPE.STATUS_TCPM));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsCatWorkStatus(int iStatus) {
        String sResult = "";
        switch (iStatus) {
            case Constants.BTS_CAT_WORK_STATUS.DAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_dat);
                break;
            case Constants.BTS_CAT_WORK_STATUS.KHONG_DAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_pillar_khongdat);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach trang thai thi cong nha may no trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsCatWorkStatusTcnmn(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.STATUS_TCNMN));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_dat),
                    Constants.DROPDOWN_TYPE.STATUS_TCNMN));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.KHONG_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_khongdat),
                    Constants.DROPDOWN_TYPE.STATUS_TCNMN));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay danh sach trang thai thi cong keo dien trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsCatWorkStatusTckd(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.STATUS_TCKD));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.DAT, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_dat),
                    Constants.DROPDOWN_TYPE.STATUS_TCKD));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_STATUS.KHONG_DAT, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_pillar_khongdat),
                    Constants.DROPDOWN_TYPE.STATUS_TCKD));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    /**
     * Ham lay danh sach loai di day thi cong keo dien trong giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsCatWorDidayType(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.DIDAY_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_DIDAY_TYPE.DI_NGAM, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_cat_work_dingam),
                    Constants.DROPDOWN_TYPE.DIDAY_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_CAT_WORK_DIDAY_TYPE.TREO_CAO, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_cat_work_treocao),
                    Constants.DROPDOWN_TYPE.DIDAY_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsCatWorkDidayType(int iDidayType) {
        String sResult = "";
        switch (iDidayType) {
            case Constants.BTS_CAT_WORK_DIDAY_TYPE.DI_NGAM:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_cat_work_dingam);
                break;
            case Constants.BTS_CAT_WORK_DIDAY_TYPE.TREO_CAO:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_cat_work_treocao);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach diem dau trong thi cong keo dien giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsPowerPoleDiemdau(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.TYPE_DIEM_DAU));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_DIEM_DAU_TYPE.CO_SAN, GlobalInfo
                    .getInstance().getAppContext().getResources()
                    .getString(R.string.bts_assess_power_pole_cosan),
                    Constants.DROPDOWN_TYPE.TYPE_DIEM_DAU));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_DIEM_DAU_TYPE.PHAI_KEO_MOI, GlobalInfo
                    .getInstance()
                    .getAppContext()
                    .getResources()
                    .getString(
                            R.string.bts_assess_power_pole_phaikeomoi),
                    Constants.DROPDOWN_TYPE.TYPE_DIEM_DAU));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsPowerPoleDiemdau(int iDiemdau) {
        String sResult = "";
        switch (iDiemdau) {
            case Constants.BTS_DIEM_DAU_TYPE.CO_SAN:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_power_pole_cosan);
                break;
            case Constants.BTS_DIEM_DAU_TYPE.PHAI_KEO_MOI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_assess_power_pole_phaikeomoi);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai tu 2g trong thi cong keo dien giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBts2gType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.TYPE_2G));
            }
            listData.add(new DropdownItemEntity(Constants.BTS_2G_TYPE.PHAN_TAN,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_3g_type_phantan),
                    Constants.DROPDOWN_TYPE.TYPE_2G));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_2G_TYPE.TAP_TRUNG, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_equip_3g_type_taptrung),
                    Constants.DROPDOWN_TYPE.TYPE_2G));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBts2gType(int is2gType) {
        String sResult = "";
        switch (is2gType) {
            case Constants.BTS_2G_TYPE.PHAN_TAN:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_3g_type_phantan);
                break;
            case Constants.BTS_2G_TYPE.TAP_TRUNG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_3g_type_taptrung);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai tu 3g trong thi cong keo dien giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBts3gType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.TYPE_3G));
            }
            listData.add(new DropdownItemEntity(Constants.BTS_3G_TYPE.PHAN_TAN,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_3g_type_phantan),
                    Constants.DROPDOWN_TYPE.TYPE_3G));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_3G_TYPE.TAP_TRUNG, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_equip_3g_type_taptrung),
                    Constants.DROPDOWN_TYPE.TYPE_3G));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBts3gType(int is3gType) {
        String sResult = "";
        switch (is3gType) {
            case Constants.BTS_3G_TYPE.PHAN_TAN:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_3g_type_phantan);
                break;
            case Constants.BTS_3G_TYPE.TAP_TRUNG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_3g_type_taptrung);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai thiet bi giam sat bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsEquipType(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.EQUIP_TYPE));
            }
            listData.add(new DropdownItemEntity(Constants.BTS_EQUIP_TYPE.QUANG,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_equip_type_quang),
                    Constants.DROPDOWN_TYPE.EQUIP_TYPE));
            listData.add(new DropdownItemEntity(Constants.BTS_EQUIP_TYPE.VIBA,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_equip_type_viba),
                    Constants.DROPDOWN_TYPE.EQUIP_TYPE));
            listData.add(new DropdownItemEntity(Constants.BTS_EQUIP_TYPE.VSAT,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_equip_type_vsat),
                    Constants.DROPDOWN_TYPE.EQUIP_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsEquipType(int isEquipType) {
        String sResult = "";
        switch (isEquipType) {
            case Constants.BTS_EQUIP_TYPE.QUANG:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_equip_type_quang);
                break;
            case Constants.BTS_EQUIP_TYPE.VIBA:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_equip_type_viba);
                break;

            case Constants.BTS_EQUIP_TYPE.VSAT:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_equip_type_vsat);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach tan so giam sat thiet bi bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsTanSo(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.TAN_SO));
            }
            listData.add(new DropdownItemEntity(Constants.BTS_TAN_SO.HAI,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_tanso_hai),
                    Constants.DROPDOWN_TYPE.TAN_SO));
            listData.add(new DropdownItemEntity(Constants.BTS_TAN_SO.BAY,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_tanso_bay),
                    Constants.DROPDOWN_TYPE.TAN_SO));

            listData.add(new DropdownItemEntity(Constants.BTS_TAN_SO.MUOI_LAM,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_tanso_muoi_lam),
                    Constants.DROPDOWN_TYPE.TAN_SO));

            listData.add(new DropdownItemEntity(Constants.BTS_TAN_SO.MUOI_TAM,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_tanso_muoi_tam),
                    Constants.DROPDOWN_TYPE.TAN_SO));

            listData.add(new DropdownItemEntity(Constants.BTS_TAN_SO.HAI_BA,
                    GlobalInfo.getInstance().getAppContext().getResources()
                            .getString(R.string.bts_equip_tanso_hai_ba),
                    Constants.DROPDOWN_TYPE.TAN_SO));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsTanSo(int isTanSo) {
        String sResult = "";
        switch (isTanSo) {
            case Constants.BTS_TAN_SO.HAI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_tanso_hai);
                break;
            case Constants.BTS_TAN_SO.BAY:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_tanso_bay);
                break;
            case Constants.BTS_TAN_SO.MUOI_LAM:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_tanso_muoi_lam);
                break;
            case Constants.BTS_TAN_SO.MUOI_TAM:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_tanso_muoi_tam);
                break;
            case Constants.BTS_TAN_SO.HAI_BA:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_equip_tanso_hai_ba);
                break;

            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach tan so giam sat thiet bi bts
     *
     * @return
     */
    public static List<DropdownItemEntity> getListBtsKtTrong(String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));
            }
            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.KHONG_PHAY_BA,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_equip_kichthuoctrong_khong_phay_ba),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));
            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.KHONG_PHAY_SAU,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_equip_kichthuoctrong_khong_phay_sau),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.MOT_PHAY_HAI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_equip_kichthuoctrong_mot_phay_hai),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.MOT_PHAY_NAM,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_equip_kichthuoctrong_mot_phay_nam),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.HAI_PHAY_BON,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_equip_kichthuoctrong_hai_phay_bon),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));

            listData.add(new DropdownItemEntity(
                    Constants.BTS_KICH_THUOC_TRONG.BA, GlobalInfo.getInstance()
                    .getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_ba),
                    Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringBtsKtTrong(float isKtTrong) {
        String sResult = "";

        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.KHONG_PHAY_BA) {
            sResult = GlobalInfo.getInstance().getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_khong_phay_ba);
        }
        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.KHONG_PHAY_SAU) {
            sResult = GlobalInfo
                    .getInstance()
                    .getAppContext()
                    .getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_khong_phay_sau);
        }
        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.MOT_PHAY_HAI) {
            sResult = GlobalInfo.getInstance().getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_mot_phay_hai);
        }
        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.MOT_PHAY_NAM) {
            sResult = GlobalInfo.getInstance().getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_mot_phay_nam);
        }
        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.HAI_PHAY_BON) {
            sResult = GlobalInfo.getInstance().getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_hai_phay_bon);
        }
        if (isKtTrong == Constants.BTS_KICH_THUOC_TRONG.BA) {
            sResult = GlobalInfo.getInstance().getAppContext().getResources()
                    .getString(R.string.bts_equip_kichthuoctrong_ba);
        }

        return sResult;
    }

    /**
     * Ham lay ten loai tram
     *
     * @param iStationType : id loai tram
     * @return
     */
    public static String getStringStationType(int iStationType) {
        String sResult = "";
        switch (iStationType) {
            case Constants.STATION_TYPE.TYPE_2G:
                sResult = StringUtil.getString(R.string.bts_pillar_station_type_2g);
                break;
            case Constants.STATION_TYPE.TYPE_3G:
                sResult = StringUtil.getString(R.string.bts_pillar_station_type_3g);
                break;
            case Constants.STATION_TYPE.TYPE_COSITE:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_cosite);
                break;
            case Constants.STATION_TYPE.TYPE_AP_DEPEND:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_ap_depend);
                break;
            case Constants.STATION_TYPE.TYPE_AP_INDEPEND:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_ap_independ);
                break;
            case Constants.STATION_TYPE.TYPE_XE_PHAT_SONG_LUU_DONG:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_xe_ps_luudong);
                break;
            case Constants.STATION_TYPE.TYPE_4G:
                sResult = StringUtil.getString(R.string.bts_pillar_station_type_4g);
                break;
            case Constants.STATION_TYPE.TYPE_POP:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_pop);
                break;
            case Constants.STATION_TYPE.TYPE_REPEATER:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tram_repeater);
                break;
            case Constants.STATION_TYPE.TYPE_TRUYEN_DAN:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tram_truyen_dan);
                break;
            case Constants.STATION_TYPE.TYPE_KHACH_HANG:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tram_khach_hang);
                break;
            case Constants.STATION_TYPE.TYPE_TONG_TRAM_KHU_VUC:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tong_tram_kv);
                break;
            case Constants.STATION_TYPE.TYPE_TONG_TRAM_CHI_NHANH:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tong_tram_chi_nhanh);
                break;
            case Constants.STATION_TYPE.TYPE_TONG_TRAM_DUONG_TRUC:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tong_tram_duong_truc);
                break;
            case Constants.STATION_TYPE.TYPE_PHONG_LAB:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_phong_lab);
                break;
            case Constants.STATION_TYPE.TYPE_DIEM_TRUYEN_DAN:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_diem_truyen_dan);
                break;
            case Constants.STATION_TYPE.TYPE_HEAD_END:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_head_end);
                break;
            case Constants.STATION_TYPE.TYPE_SUB_HEAD_END:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_sub_headend);
                break;
            case Constants.STATION_TYPE.TYPE_DAI_TRUYEN_HINH:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_dai_truyen_hinh);
                break;
            case Constants.STATION_TYPE.TYPE_TRAM_ACCU:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_tram_accu);
                break;
            case Constants.STATION_TYPE.TYPE_TRUYEN_HINH_CAP:
                sResult = StringUtil
                        .getString(R.string.bts_pillar_station_type_truyenhinhcap);
                break;
            default:
                break;
        }
        return sResult;
    }

    /**
     * Ham lay danh sach loai xay dung
     *
     * @return
     */
    public static List<DropdownItemEntity> getListConstructionBtsType(
            String sChoice) {
        List<DropdownItemEntity> listData = new ArrayList<DropdownItemEntity>();
        try {
            if (!sChoice.isEmpty()) {
                listData.add(new DropdownItemEntity(
                        Constants.DROPDOWN_DEFAULT_VALUE, sChoice,
                        Constants.DROPDOWN_TYPE.CONSTRUCTION_TYPE));
            }
            listData.add(new DropdownItemEntity(
                    Constants.CONSTRUCTION_TYPE.XAY_MOI,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_pillar_construction_type_xaymoi),
                    Constants.DROPDOWN_TYPE.CONSTRUCTION_TYPE));
            listData.add(new DropdownItemEntity(
                    Constants.CONSTRUCTION_TYPE.NANG_CAP,
                    GlobalInfo
                            .getInstance()
                            .getAppContext()
                            .getResources()
                            .getString(
                                    R.string.bts_pillar_construction_type_nangcap),
                    Constants.DROPDOWN_TYPE.CONSTRUCTION_TYPE));

        } catch (Exception e) {
            Log.i("Error", e.getMessage() + e.getStackTrace());
        }
        return listData;
    }

    public static String getStringConstructionBtsType(int isConstructionType) {
        String sResult = "";
        switch (isConstructionType) {
            case Constants.CONSTRUCTION_TYPE.XAY_MOI:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_pillar_construction_type_xaymoi);
                break;
            case Constants.CONSTRUCTION_TYPE.NANG_CAP:
                sResult = GlobalInfo.getInstance().getAppContext().getResources()
                        .getString(R.string.bts_pillar_construction_type_nangcap);
                break;

            default:
                break;
        }
        return sResult;
    }

}