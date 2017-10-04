package com.viettel.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.constants.Constants;
import com.viettel.controller.Home_Controller;
import com.viettel.controller.SupervisionBtsController;
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cat_Supv_Constr_MeasureController;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Cause_Bts_Pillar_AntenController;
import com.viettel.database.Cause_Bts_Power_PoleController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_MeasureController;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.entity.Cat_Supv_Constr_MeasureEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_MeasureEntity;
import com.viettel.gsct.activity.bts.BtsActivity;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsBaseActivity extends BaseActivity {

    /**
     * chuyen toi form home
     *
     * @param data
     */
    protected void gotoHomeActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_HOME_ACTIVITY;
        e.sender = this;
        Home_Controller.getInstance().handleSwitchActivity(e);
    }

    protected void gotoMakePlanActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_PLAN_ACTIVITY;
        e.sender = this;
        Home_Controller.getInstance().handleSwitchActivity(e);
    }

    protected void gotoAboutActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_ABOUT_ACTIVITY;
        e.sender = this;
        Home_Controller.getInstance().handleSwitchActivity(e);
    }

    public void gotoBtsActivity(Bundle bundleMonitorData, int isInfomation) {
        if (isInfomation == Constants.BTS_INFO.THIET_TKE_INFO) {

            gotoSupervisionBtsActivity(bundleMonitorData);
        }

        if (isInfomation == Constants.BTS_INFO.NHAT_KY_TIEN_DO_INFO) {
            gotoNhatkyBtsActivity(bundleMonitorData);
        }

//		if (isInfomation == Constants.BTS_INFO.TIEN_DO_INFO) {
//			gotoTiendoBtsActivity(bundleMonitorData);
//		}

        if (isInfomation == Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO) {

            gotoSupervisionBtsPillarActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO) {

            gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.KEO_DIEN_INFO) {

            gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO) {

            gotoSupervisionBtsEquipActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.LAP_DAT_VIBA_INFO) {

            gotoSupervisionBtsVibaActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO) {

            gotoSupervisionBtsMeasureActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.MEASURE_CONSTR_INFO) {
            gotoSupervisionMeasureConstrActivity(bundleMonitorData);
        }
        if (isInfomation == Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG) {
            gotoSupervisionCNDTCActivity(bundleMonitorData);
        }

        if (isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG) {
            closeProgressDialog();
            gotoSupervisionCNVCActivity(bundleMonitorData);
        }

        if (isInfomation == Constants.BTS_INFO.KET_LUAN_INFO) {

            gotoSupervisionBtsKLActivity(bundleMonitorData);
        }
    }


    /**
     * chuyen toi form thiet ke bts
     *
     * @param data
     */
    protected void gotoSupervisionBtsActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    protected void gotoNhatkyBtsActivity(Bundle data) {
        Bundle extras = data;
        Intent intent = new Intent(this, BtsActivity.class);

        if (extras != null) {
            extras.putInt(BtsActivity.ARG_INFO, Constants.BTS_INFO.NHAT_KY_TIEN_DO_INFO);
            intent.putExtras(extras);
        }
        startActivity(intent);
    }

//	protected void gotoTiendoBtsActivity(Bundle data) {
//		Bundle extras = data;
//		Intent intent = new Intent(this, BtsActivity.class);
//		if (extras != null) {
//			extras.putInt(BtsActivity.ARG_INFO, Constants.BTS_INFO.TIEN_DO_INFO);
//			intent.putExtras(extras);
//		}
//		startActivity(intent);
//	}

    /**
     * chuyen toi form thi cong tip dia
     *
     * @param data
     */
    protected void gotoSupervisionBtsPillarActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_PILLAR_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form xac dinh vi tri cot
     *
     * @param data
     */
    protected void gotoSupervisionBtsPillarPosition(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_POSITION_BTS_PILLAR_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form thi cong phong may va nha may no
     *
     * @param data
     */
    protected void gotoSupervisionBtsCatWorkActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_CAT_WORK_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form thi cong keo dien
     *
     * @param data
     */
    protected void gotoSupervisionBtsPowerPoleActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_POWER_POLE_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form lap dat thiet bi
     *
     * @param data
     */
    protected void gotoSupervisionBtsEquipActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_EQUIP_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form lap dat thiet bi
     *
     * @param data
     */
    protected void gotoSupervisionBtsVibaActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_VIBA_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form lap dat thiet bi
     *
     * @param data
     */
    protected void gotoSupervisionBtsMeasureActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_MEASURE_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form do kich thuoc cong trinh
     *
     * @param data
     */
    protected void gotoSupervisionMeasureConstrActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_MEASURE_CONSTR_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    //--- HungTN add new 23/08/2016

    /**
     * chuyen toi form cap nhat doi thi cong
     *
     * @param data
     */
    protected void gotoSupervisionCNDTCActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_CNDTC_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /**
     * chuyen toi form cap nhat doi thi cong
     *
     * @param data
     */
    protected void gotoSupervisionCNVCActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_CNV_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    //--- end

    /**
     * chuyen toi form ket luan cong trinh tram bts
     *
     * @param data
     */
    protected void gotoSupervisionBtsKLActivity(Bundle data) {
        ActionEvent e = new ActionEvent();
        e.viewData = data;
        e.action = ActionEventConstant.GOTO_SUPERVISION_BTS_KL_ACTIVITY;
        e.sender = this;
        SupervisionBtsController.getInstance().handleSwitchActivity(e);
    }

    /* Check nguyen nhan khong dat */
    public boolean checkCauseDeny(Constr_Construction_EmployeeEntity constr_ConstructionItem) {

        String sResult = "";

        List<Cat_Supervision_Constr_WorkEntity> listConstrWork;
        List<Cat_Supervision_Constr_WorkEntity> listConstrWorkLow;

		/* Get data KL */
        Supervision_BtsController bts_Controller = new Supervision_BtsController(
                this);

        Supervision_BtsEntity btsEntity = bts_Controller
                .getSupervisionBts(constr_ConstructionItem
                        .getSupervision_Constr_Id());

        Cause_Bts_Pillar_AntenController btsPillarAntenController
                = new Cause_Bts_Pillar_AntenController(this);
        Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
                this);
        Cause_Bts_Power_PoleController btsPowerPoleController = new Cause_Bts_Power_PoleController(
                this);
        List<Cause_Bts_Power_PoleEntity> listPowerPole = new ArrayList<Cause_Bts_Power_PoleEntity>();
        listPowerPole = btsPowerPoleController
                .getListCauseBts_Power_PoleEntity(btsEntity
                        .getSupervision_Bts_Id());

        // lay danh sach danh gia thi cong cot
        // danh sach thi cong cot
        List<Cause_Bts_Pillar_AntenEntity> listPillar = btsPillarAntenController
                .getListCauseBts_PillarAntenEntity(btsEntity
                        .getSupervision_Bts_Id());
        // lay danh gia lap dung cot
        Cause_Bts_Cat_WorkEntity catWorkEntityLdc = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT,
                        btsEntity.getSupervision_Bts_Id());

        // lay danh gia lap dung cot anten va thang cap ngoai troi

        // lay danh gia thi cong tiep dia
        Cause_Bts_Cat_WorkEntity catWorkEntityTctd = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TDBTS,
                        btsEntity.getSupervision_Bts_Id());

        // lay danh gia thi cong phong may
        Cause_Bts_Cat_WorkEntity causeCatWorkEntityPm = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY,
                        btsEntity.getSupervision_Bts_Id());

        // lay danh gia thi cong nha may no
        Cause_Bts_Cat_WorkEntity causeCatWorkEntityNmn = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO,
                        btsEntity.getSupervision_Bts_Id());

        // lay danh gia thic ogn keo day dien nha may no
        Cause_Bts_Cat_WorkEntity causeCatWorkEntityKdd_Nmn = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO,
                        btsEntity.getSupervision_Bts_Id());

        // lay danh gia thi cong keo day dien
        Cause_Bts_Cat_WorkEntity causeCatWorkEntityKdd = causeCatWorkController
                .getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN,
                        btsEntity.getSupervision_Bts_Id());

        // setListDataForEquip();
        listConstrWork = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

        Cat_Supervision_Constr_WorkController constrWorkController
                = new Cat_Supervision_Constr_WorkController(this);

        // lay ten constr work tuong ung voi work code tiep dat thoat set
        Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Thoatset = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

        // lay ten constr work tuong ung voi work code tiep dat feeder
        Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Feeder = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER);

        // lay ten constr work tuong ung voi work code thang cap
        Cat_Supervision_Constr_WorkEntity constrWorkThangcap = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP);

        // lay ten constr work tuong ung voi work code he thong den dieu hoa
        Cat_Supervision_Constr_WorkEntity constrWorkHethongden = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO);

        // lay ten constr work tuong ung voi work code tu dien ac
        Cat_Supervision_Constr_WorkEntity constrWorkTudienAc = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC);

        // lay ten constr work tuong ung voi work code tu nguon dc acquy
        Cat_Supervision_Constr_WorkEntity constrWorkTunguon = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY);

        // lay ten constr work tuong ung voi work code tu bts
        Cat_Supervision_Constr_WorkEntity constrWorkTuBts = constrWorkController
                .getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS);

        // lay ten constr work tuong ung voi work code rack df
        Cat_Supervision_Constr_WorkEntity constrWorkRackDf = constrWorkController
                .getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF);

        // lay ten constr work tuong ung voi work code cap nguon thang cap
        Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Thangcap = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP);

        // lay ten constr work tuong ung voi work code den bao khong
        Cat_Supervision_Constr_WorkEntity constrWorkDenbaokhong = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG);

        // lay ten constr work tuong ung voi work code antenfeeder
        Cat_Supervision_Constr_WorkEntity constrWorkAntenFeeder = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER);

        // lay ten constr work tuong ung voi work code tam bit cap
        Cat_Supervision_Constr_WorkEntity constrWorkTambitcap = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP);

        // lay ten constr work tuong ung voi work code rru olp
        Cat_Supervision_Constr_WorkEntity constrWorkRru = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP);

        // lay ten constr work tuong ung voi work code cap quang nguon rru
        Cat_Supervision_Constr_WorkEntity constrWorkCapquangnguon = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU);

        listConstrWork.add(constrWorkTiepdat_Thoatset);
        listConstrWork.add(constrWorkTiepdat_Feeder);
        listConstrWork.add(constrWorkThangcap);
        listConstrWork.add(constrWorkHethongden);
        listConstrWork.add(constrWorkTudienAc);

        listConstrWork.add(constrWorkTunguon);
        listConstrWork.add(constrWorkTuBts);
        listConstrWork.add(constrWorkRackDf);
        listConstrWork.add(constrWorkCapnguon_Thangcap);
        listConstrWork.add(constrWorkDenbaokhong);

        listConstrWork.add(constrWorkAntenFeeder);
        listConstrWork.add(constrWorkTambitcap);
        listConstrWork.add(constrWorkRru);
        listConstrWork.add(constrWorkCapquangnguon);

        // lay danh sach danh gia lap dat thiet bi
        List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkEquipEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

        // getDataFromDatabaseEquip();
        causeCatWorkController = new
                Cause_Bts_Cat_WorkController(
                this);
        for (int i = 0; i < listConstrWork.size(); i++) {
            Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

            String workcode = listConstrWork.get(i).getWork_Code();

            if (workcode.equals(
                    Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(Constants.BTS_CONSTR_WORK
                                        .WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
                                btsEntity.getSupervision_Bts_Id());

            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER,
                        btsEntity.getSupervision_Bts_Id());
            }
            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP,
                        btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO,
                                btsEntity.getSupervision_Bts_Id());
            }
            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP,
                                btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER,
                                btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP,
                        btsEntity.getSupervision_Bts_Id());
            }

            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP,
                        btsEntity.getSupervision_Bts_Id());
            }

            // item.setListCauseUniQualify(convertUnqualifyToItem(item
            // .getListConstrUnqualifyEntity()));
            // item.setWorkCode(workcode);
            // item.setStt(i + 1);
            // item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

            if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
                    || item.getBts_Cat_WorkEntity().getStatus() >= 0) {
                listCauseCatWorkEquipEntity.add(item);
            }

        }

        // setListDataLowViba();
        listConstrWorkLow = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

        constrWorkController = new
                Cat_Supervision_Constr_WorkController(
                this);

        // lay ten constr work tuong ung voi work code thi cong ngoai troi
        Cat_Supervision_Constr_WorkEntity constrWorkThicong_ngtroi = constrWorkController
                .getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);

        // lay ten constr work tuong ung voi work code cap trung tan
        Cat_Supervision_Constr_WorkEntity constrWorkCap_trungtan = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);

        // lay ten constr work tuong ung voi work code tinh trang phan cung
        Cat_Supervision_Constr_WorkEntity constrWorkTinhtrang_phancung = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);

        // lay ten constr work tuong ung voi work code cap nguon DC
        Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Dc = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);

        // lay ten constr work tuong ung voi work code cap bang tan goc
        Cat_Supervision_Constr_WorkEntity constrWorkCapbangtan = constrWorkController
                .getListConstrWorkEntityByWorkCode(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);

        listConstrWorkLow.add(constrWorkThicong_ngtroi);
        listConstrWorkLow.add(constrWorkCap_trungtan);
        listConstrWorkLow.add(constrWorkTinhtrang_phancung);
        listConstrWorkLow.add(constrWorkCapnguon_Dc);
        listConstrWorkLow.add(constrWorkCapbangtan);

        // lay danh sach danh gia lap dat viba dau thap
        List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkLowVibaEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

        // getDataFromDatabaseVibaLow();
        causeCatWorkController = new
                Cause_Bts_Cat_WorkController(
                this);
        String worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW;
        for (int i = 0; i < listConstrWorkLow.size(); i++) {
            Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

            String workcode = listConstrWorkLow.get(i).getWork_Code();

            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA, worktype,
                        btsEntity.getSupervision_Bts_Id());

            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG,
                                worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }

            // item.setListCauseUniQualify(convertUnqualifyToItem(item
            // .getListConstrUnqualifyEntity()));
            // item.setWorkCode(workcode);
            // item.setStt(i + 1);
            // item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

            if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
                    || item.getBts_Cat_WorkEntity().getStatus() >= 0) {
                listCauseCatWorkLowVibaEntity.add(item);
            }

        }

        // setListDataHeightViba();
        List<Cat_Supervision_Constr_WorkEntity> listConstrWorkHeigh = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

        constrWorkController = new
                Cat_Supervision_Constr_WorkController(
                this);

        // lay ten constr work tuong ung voi work code thi cong ngoai troi
        constrWorkThicong_ngtroi =
                constrWorkController
                        .getListConstrWorkEntityByWorkCode(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);

        // lay ten constr work tuong ung voi work code cap trung tan
        constrWorkCap_trungtan =
                constrWorkController
                        .getListConstrWorkEntityByWorkCode(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);

        // lay ten constr work tuong ung voi work code tinh trang phan cung
        constrWorkTinhtrang_phancung =
                constrWorkController
                        .getListConstrWorkEntityByWorkCode(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);

        // lay ten constr work tuong ung voi work code cap nguon DC
        constrWorkCapnguon_Dc =
                constrWorkController
                        .getListConstrWorkEntityByWorkCode(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);

        // lay ten constr work tuong ung voi work code cap bang tan goc
        constrWorkCapbangtan =
                constrWorkController
                        .getListConstrWorkEntityByWorkCode(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);

        listConstrWorkHeigh.add(constrWorkThicong_ngtroi);
        listConstrWorkHeigh.add(constrWorkCap_trungtan);
        listConstrWorkHeigh.add(constrWorkTinhtrang_phancung);
        listConstrWorkHeigh.add(constrWorkCapnguon_Dc);
        listConstrWorkHeigh.add(constrWorkCapbangtan);

        // lay danh sach danh gia lap dat viba dau cao
        List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkHeighVibaEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

        // getDataFromDatabaseVibaHeigh();
        causeCatWorkController = new
                Cause_Bts_Cat_WorkController(
                this);
        worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH;

        for (int i = 0; i < listConstrWorkHeigh.size(); i++) {
            Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

            String workcode = listConstrWorkHeigh.get(i).getWork_Code();

            if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA, worktype,
                        btsEntity.getSupervision_Bts_Id());

            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG)) {
                item = causeCatWorkController
                        .getCause_Bts_Cat_WorkEntity(
                                Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG,
                                worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }
            if (workcode
                    .equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC)) {
                item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
                        Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC,
                        worktype, btsEntity.getSupervision_Bts_Id());
            }

            // item.setListCauseUniQualify(convertUnqualifyToItem(item
            // .getListConstrUnqualifyEntity()));
            // item.setWorkCode(workcode);
            // item.setStt(i + 1);
            // item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

            if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
                    || item.getBts_Cat_WorkEntity().getStatus() >= 0) {
                listCauseCatWorkHeighVibaEntity.add(item);
            }

        }

        // lay du lieu thi cong han noi
        // prepareListData();
        Cat_Supv_Constr_MeasureController constrMeasureController
                = new Cat_Supv_Constr_MeasureController(this);

        // 2g
        Cat_Supv_Constr_MeasureEntity constrMeasureAll2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumper2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureFeeder2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureConnecter2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureAnten2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_2G);

        Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten2G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_2G);

        // 3g
        Cat_Supv_Constr_MeasureEntity constrMeasureAll3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumper3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureFeeder3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureConnecter3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureAnten3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_3G);

        Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten3G = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_3G);

        // them du lieu vao tung group title
        List<Cat_Supv_Constr_MeasureEntity> feeder2G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
        feeder2G.add(constrMeasureAll2G);
        feeder2G.add(constrMeasureJumper2G);
        feeder2G.add(constrMeasureFeeder2G);
        feeder2G.add(constrMeasureJumperFeeder2G);
        feeder2G.add(constrMeasureConnecter2G);
        feeder2G.add(constrMeasureAnten2G);
        feeder2G.add(constrMeasureResoantAnten2G);

        List<Cat_Supv_Constr_MeasureEntity> feeder3G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
        feeder3G.add(constrMeasureAll3G);
        feeder3G.add(constrMeasureJumper3G);
        feeder3G.add(constrMeasureFeeder3G);
        feeder3G.add(constrMeasureJumperFeeder3G);
        feeder3G.add(constrMeasureConnecter3G);
        feeder3G.add(constrMeasureAnten3G);
        feeder3G.add(constrMeasureResoantAnten3G);

        List<Cat_Supv_Constr_MeasureEntity> viba = new ArrayList<Cat_Supv_Constr_MeasureEntity>();

        Cat_Supv_Constr_MeasureEntity constrMeasureCapacityViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_CAPACITY_VIBA);

        Cat_Supv_Constr_MeasureEntity constrMeasureObtainViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_OBTAIN_VIBA);

        Cat_Supv_Constr_MeasureEntity constrMeasureBerViba = constrMeasureController
                .getConstrMeasureEntityByWorkCode(
                        Constants.BTS_CONSTR_MEASURE.WORK_CODE_BER_VIBA);

        viba.add(constrMeasureCapacityViba);
        viba.add(constrMeasureObtainViba);
        viba.add(constrMeasureBerViba);

//		// lay du lieu phan ket luan
//		Supervision_ConstrController supervisionConstroller = new Supervision_ConstrController(
//				this);
//		Supervision_ConstrEntity constrItem = supervisionConstroller
//				.getItem(constr_ConstructionEmployeeItem.getSupervision_Constr_Id());

        if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
                && btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.XAY_MOI) {

            for (Cause_Bts_Pillar_AntenEntity i : listPillar) {
                if (i.getSupervision_Bts_Pillar_AntenEntity().getStatus() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_pillar_khongdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            if (btsEntity.getPillar_STATUS_QUALITY() != -1) {
                if (btsEntity.getPillar_STATUS_QUALITY() == Constants.BTS_ASSESS_PILLAR.DAT) {

                    if (catWorkEntityLdc.getBts_Cat_WorkEntity().getStatus() == 0) {
                        sResult = StringUtil
                                .getString(R.string.supervision_bts_kl_lapdungcot_khongdat);
                        Log.i("Cause_Deny", sResult);
                    }
                } else {
                    if (btsEntity.getPillar_STATUS_QUALITY()
                            == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
                        sResult = StringUtil.getString(
                                R.string.supervision_bts_kl_danhgia_chatluong_cot_khongdat);
                        Log.i("Cause_Deny", sResult);
                    }
                }
            }

            if (catWorkEntityTctd.getBts_Cat_WorkEntity().getStatus() != -1
                    && catWorkEntityTctd.getBts_Cat_WorkEntity().getStatus() == 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_tiepdia_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            if (causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() != -1
                    && causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() == 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_phongmay_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            if (causeCatWorkEntityNmn.getBts_Cat_WorkEntity().getStatus() != -1
                    && causeCatWorkEntityNmn.getBts_Cat_WorkEntity().getStatus() == 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_nhamayno_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            if (causeCatWorkEntityKdd_Nmn.getBts_Cat_WorkEntity().getStatus() != -1
                    && causeCatWorkEntityKdd_Nmn.getBts_Cat_WorkEntity().getStatus() == 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_keodaydien_nmn_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() != -1
                    && causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() == 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_keodaydien_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            if (listPowerPole.size() > 0) {
                sResult = StringUtil
                        .getString(R.string.supervision_bts_kl_cottrongmoi_khongdat);
                Log.i("Cause_Deny", sResult);
            }

            for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkEquipEntity) {
                if (i.getBts_Cat_WorkEntity().getStatus() != -1
                        && i.getBts_Cat_WorkEntity().getStatus() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_lapdatthietbi_khongdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkLowVibaEntity) {
                if (i.getBts_Cat_WorkEntity().getStatus() != -1
                        && i.getBts_Cat_WorkEntity().getStatus() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_lapdatviba_low_khongdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkHeighVibaEntity) {
                if (i.getBts_Cat_WorkEntity().getStatus() != -1
                        && i.getBts_Cat_WorkEntity().getStatus() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_lapdatviba_heigh_khongdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            Supervision_Bts_MeasureController measureController
                    = new Supervision_Bts_MeasureController(this);

            for (int i = 0; i < feeder2G.size(); i++) {
                Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
                        .getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
                                feeder2G.get(i).getCat_Supv_Constr_Measure_Id());
                if (supvBtsMeasureEntity != null
                        && supvBtsMeasureEntity.getMeasure_Status() != -1
                        && supvBtsMeasureEntity.getMeasure_Status() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_hangmuc)
                            + " "
                            + feeder2G.get(i).getMeasure_Name()
                            + " "
                            + StringUtil
                            .getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            for (int i = 0; i < feeder3G.size(); i++) {
                Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
                        .getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
                                feeder3G.get(i).getCat_Supv_Constr_Measure_Id());
                if (supvBtsMeasureEntity != null
                        && supvBtsMeasureEntity.getMeasure_Status() != -1
                        && supvBtsMeasureEntity.getMeasure_Status() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_hangmuc)
                            + " "
                            + feeder3G.get(i).getMeasure_Name()
                            + " "
                            + StringUtil
                            .getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
                    Log.i("Cause_Deny", sResult);
                }
            }

            for (int i = 0; i < viba.size(); i++) {
                Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
                        .getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
                                viba.get(i).getCat_Supv_Constr_Measure_Id());
                if (supvBtsMeasureEntity != null
                        && supvBtsMeasureEntity.getMeasure_Status() != -1
                        && supvBtsMeasureEntity.getMeasure_Status() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_hangmuc)
                            + " "
                            + viba.get(i).getMeasure_Name()
                            + " "
                            + StringUtil
                            .getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
                    Log.i("Cause_Deny", sResult);
                }
            }
        } else {
            for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkEquipEntity) {
                if (i.getBts_Cat_WorkEntity().getStatus() != -1
                        && i.getBts_Cat_WorkEntity().getStatus() == 0) {
                    sResult = StringUtil
                            .getString(R.string.supervision_bts_kl_lapdatthietbi_khongdat);
                    Log.i("Cause_Deny", sResult);
                }
            }
        }

        if (sResult.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onItemSelected(int position) {
        // TODO Auto-generated method stub

        switch (position) {
            case 0:
                gotoHomeActivity(new Bundle());
                finish();
                break;
            case 1:

                break;
            case 2:
                gotoMakePlanActivity(new Bundle());
                break;
            case 3:
                requestSync(this);

                break;
            case 4:
                gotoAboutActivity(new Bundle());
                break;
            case 5:

                break;
            case 6:
                gotoLogOut();
                break;

            default:
                break;
        }

    }

}
