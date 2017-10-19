package com.viettel.sync;

import com.viettel.database.Acceptance_Brcd_Box_Controller;
import com.viettel.database.Acceptance_Brcd_Drop_Controller;
import com.viettel.database.Acceptance_Brcd_Kcn_Controller;
import com.viettel.database.Acceptance_Brcd_Kct_Controller;
import com.viettel.database.Acceptance_Brcd_Mx_Controller;
import com.viettel.database.Acceptance_Brcd_Sub_Controller;
import com.viettel.database.Acceptance_Brcd_Tn_Controller;
import com.viettel.database.Acceptance_Bts_Cat_WorkController;
import com.viettel.database.Acceptance_Bts_PillarController;
import com.viettel.database.Acceptance_Line_Bg_PipeController;
import com.viettel.database.Acceptance_Line_Bg_SpecialController;
import com.viettel.database.Acceptance_Line_Bg_TankController;
import com.viettel.database.Cat_Employee_Plan_Controller;
import com.viettel.database.Cause_Brcd_Box_Controller;
import com.viettel.database.Cause_Brcd_Drop_Controller;
import com.viettel.database.Cause_Brcd_Kcn_Controller;
import com.viettel.database.Cause_Brcd_Kct_Controller;
import com.viettel.database.Cause_Brcd_Mx_Controller;
import com.viettel.database.Cause_Brcd_Sub_Controller;
import com.viettel.database.Cause_Brcd_Tn_Controller;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Cause_Bts_Pillar_AntenController;
import com.viettel.database.Cause_Bts_Power_PoleController;
import com.viettel.database.Cause_Line_BG_CableController;
import com.viettel.database.Cause_Line_BG_MXController;
import com.viettel.database.Cause_Line_BG_PipeController;
import com.viettel.database.Cause_Line_BG_SpecialController;
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Cause_Line_Hg_CableController;
import com.viettel.database.Cause_Line_Hg_MxController;
import com.viettel.database.Cause_Line_Hg_PillarController;
import com.viettel.database.Constr_Team_ProgressController;
import com.viettel.database.Constr_Work_LogsController;
import com.viettel.database.Login_Log_ConstrController;
import com.viettel.database.Measurement_Detail_ConstrController;
import com.viettel.database.Plan_Constr_DetailController;
import com.viettel.database.Special_LocationController;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Controller;
import com.viettel.database.Supervision_BRCD_Drop_Design_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_DropGo_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_GiamSat_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kct_design_Controller;
import com.viettel.database.Supervision_BRCD_MxController;
import com.viettel.database.Supervision_BRCD_Sub_Controller;
import com.viettel.database.Supervision_BRCD_Tn_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_Bts_EquipController;
import com.viettel.database.Supervision_Bts_MeasureController;
import com.viettel.database.Supervision_Bts_Pillar_AntenController;
import com.viettel.database.Supervision_Bts_Power_PoleController;
import com.viettel.database.Supervision_CNDTCController;
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_CableController;
import com.viettel.database.Supervision_Line_BG_FiberController;
import com.viettel.database.Supervision_Line_BG_MxController;
import com.viettel.database.Supervision_Line_BG_PipeController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supervision_Line_BackgroundController;
import com.viettel.database.Supervision_Line_HangController;
import com.viettel.database.Supervision_Line_Hg_CableController;
import com.viettel.database.Supervision_Line_Hg_FiberController;
import com.viettel.database.Supervision_Line_Hg_MxController;
import com.viettel.database.Supervision_Line_Hg_PillarController;
import com.viettel.database.Supervision_LocateController;
import com.viettel.database.Supervision_Measure_ConstrController;
import com.viettel.database.Supervision_ProgressController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.Work_ItemsControler;
import com.viettel.database.Work_Items_ValueControler;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.field.Acceptance_Brcd_Box_Field;
import com.viettel.database.field.Acceptance_Brcd_Drop_Field;
import com.viettel.database.field.Acceptance_Brcd_Kcn_Field;
import com.viettel.database.field.Acceptance_Brcd_Kct_Field;
import com.viettel.database.field.Acceptance_Brcd_Mx_Field;
import com.viettel.database.field.Acceptance_Brcd_Sub_Field;
import com.viettel.database.field.Acceptance_Brcd_Tn_Field;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Acceptance_Bts_PillarField;
import com.viettel.database.field.Acceptance_Line_Bg_PipeField;
import com.viettel.database.field.Acceptance_Line_Bg_SpecialField;
import com.viettel.database.field.Acceptance_Line_Bg_TankField;
import com.viettel.database.field.Cat_Employee_Plan_Field;
import com.viettel.database.field.Cause_Brcd_Box_Field;
import com.viettel.database.field.Cause_Brcd_Drop_Field;
import com.viettel.database.field.Cause_Brcd_Kcn_Field;
import com.viettel.database.field.Cause_Brcd_Kct_Field;
import com.viettel.database.field.Cause_Brcd_Mx_Field;
import com.viettel.database.field.Cause_Brcd_Sub_Field;
import com.viettel.database.field.Cause_Brcd_Tn_Field;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Pillar_AntenField;
import com.viettel.database.field.Cause_Bts_Power_PoleField;
import com.viettel.database.field.Cause_Line_BG_CableField;
import com.viettel.database.field.Cause_Line_BG_MXField;
import com.viettel.database.field.Cause_Line_BG_PipeField;
import com.viettel.database.field.Cause_Line_BG_SpecialField;
import com.viettel.database.field.Cause_Line_BG_TankField;
import com.viettel.database.field.Cause_Line_Hg_CableField;
import com.viettel.database.field.Cause_Line_Hg_MxField;
import com.viettel.database.field.Cause_Line_Hg_PillarField;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.database.field.ConstrNodeItemsField;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_Progress_Field;
import com.viettel.database.field.Constr_Team_ProgressField;
import com.viettel.database.field.Constr_Work_LogsField;
import com.viettel.database.field.Login_Log_ConstrField;
import com.viettel.database.field.Measurement_Detail_ConstrField;
import com.viettel.database.field.Plan_Constr_DetailField;
import com.viettel.database.field.Special_LocationField;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Supervision_BRCD_Drop_Design_Field;
import com.viettel.database.field.Supervision_BRCD_Drop_Field;
import com.viettel.database.field.Supervision_BRCD_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_DropGo_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kcn_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Kcn_Design_Field;
import com.viettel.database.field.Supervision_BRCD_Kcn_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_Kct_design_Field;
import com.viettel.database.field.Supervision_BRCD_MXField;
import com.viettel.database.field.Supervision_BRCD_Sub_Field;
import com.viettel.database.field.Supervision_BRCD_Tn_Field;
import com.viettel.database.field.Supervision_BRCD_Ttb_Field;
import com.viettel.database.field.Supervision_BtsField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_EquipField;
import com.viettel.database.field.Supervision_Bts_MeasureField;
import com.viettel.database.field.Supervision_Bts_Pillar_AntenField;
import com.viettel.database.field.Supervision_Bts_Power_PoleField;
import com.viettel.database.field.Supervision_CNDTCField;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supervision_Line_BG_CableField;
import com.viettel.database.field.Supervision_Line_BG_FiberField;
import com.viettel.database.field.Supervision_Line_BG_MXField;
import com.viettel.database.field.Supervision_Line_BG_PipeField;
import com.viettel.database.field.Supervision_Line_BG_TankField;
import com.viettel.database.field.Supervision_Line_BackgroundField;
import com.viettel.database.field.Supervision_Line_HangField;
import com.viettel.database.field.Supervision_Line_Hg_CableField;
import com.viettel.database.field.Supervision_Line_Hg_FiberField;
import com.viettel.database.field.Supervision_Line_Hg_MxField;
import com.viettel.database.field.Supervision_Line_Hg_PillarField;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.database.field.Supervision_Measure_ConstrField;
import com.viettel.database.field.Supervision_ProgressField;
import com.viettel.database.field.Supv_Constr_Attach_FileField;
import com.viettel.database.field.Supv_Constr_DailyField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.database.field.Work_Items_ValueField;

import java.util.HashMap;
import java.util.Map;

public class SyncTableUpdate {

    private static SyncTableUpdate instance;
    public static String[] ListTableUpdate = new String[]{
            Login_Log_ConstrField.TABLE_NAME,
            Supv_Constr_DailyField.TABLE_NAME,

            Supervision_BtsField.TABLE_NAME,
            Supervision_BRCD_Field.TABLE_NAME,
            Supervision_BRCD_Sub_Field.TABLE_NAME,
            Supervision_Line_HangField.TABLE_NAME,
            Supervision_Line_BackgroundField.TABLE_NAME,

            Supervision_ConstrField.TABLE_NAME,

            Supervision_ProgressField.TABLE_NAME,

            Supervision_Line_BG_FiberField.TABLE_NAME,
            Supervision_Line_BG_TankField.TABLE_NAME,
            Supervision_Line_BG_CableField.TABLE_NAME,
            Special_LocationField.TABLE_NAME,
            Supervision_Line_BG_PipeField.TABLE_NAME,
            Supervision_Line_BG_MXField.TABLE_NAME,
            Cause_Line_BG_TankField.TABLE_NAME,
            Cause_Line_BG_SpecialField.TABLE_NAME,
            Cause_Line_BG_PipeField.TABLE_NAME,
            Cause_Line_BG_CableField.TABLE_NAME,
            Cause_Line_BG_MXField.TABLE_NAME,
            Acceptance_Line_Bg_SpecialField.TABLE_NAME,
            Acceptance_Line_Bg_TankField.TABLE_NAME,
            Acceptance_Line_Bg_PipeField.TABLE_NAME,

            Supervision_Line_Hg_FiberField.TABLE_NAME,
            Supervision_Line_Hg_PillarField.TABLE_NAME,
            Supervision_Line_Hg_MxField.TABLE_NAME,
            Supervision_Line_Hg_CableField.TABLE_NAME,
            Cause_Line_Hg_PillarField.TABLE_NAME,
            Cause_Line_Hg_MxField.TABLE_NAME,
            Cause_Line_Hg_CableField.TABLE_NAME,

            Supervision_Bts_EquipField.TABLE_NAME,
            Supervision_Bts_MeasureField.TABLE_NAME,
            Supervision_Bts_Power_PoleField.TABLE_NAME,
            Supervision_Bts_Pillar_AntenField.TABLE_NAME,
            Supervision_Measure_ConstrField.TABLE_NAME,
            Supervision_Bts_Cat_WorkField.TABLE_NAME,
            Measurement_Detail_ConstrField.TABLE_NAME,
            Cause_Bts_Pillar_AntenField.TABLE_NAME,
            Cause_Bts_Cat_WorkField.TABLE_NAME,
            Cause_Bts_Power_PoleField.TABLE_NAME,
            Acceptance_Bts_PillarField.TABLE_NAME,
            Acceptance_Bts_Cat_WorkField.TABLE_NAME,

            Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
            Supervision_BRCD_Drop_Field.TABLE_NAME,

            Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
            Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
            Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
            Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
            Supervision_BRCD_Kcn_Field.TABLE_NAME,
            Supervision_BRCD_Kct_design_Field.TABLE_NAME,
            Supervision_BRCD_Kct_Field.TABLE_NAME,
            Supervision_BRCD_MXField.TABLE_NAME,
            Supervision_BRCD_Tn_Field.TABLE_NAME,
            Supervision_BRCD_Ttb_Field.TABLE_NAME,
            Cause_Brcd_Sub_Field.TABLE_NAME,
            Cause_Brcd_Box_Field.TABLE_NAME,
            Cause_Brcd_Drop_Field.TABLE_NAME,
            Cause_Brcd_Kcn_Field.TABLE_NAME,
            Cause_Brcd_Kct_Field.TABLE_NAME,
            Cause_Brcd_Mx_Field.TABLE_NAME,
            Cause_Brcd_Tn_Field.TABLE_NAME,
            Acceptance_Brcd_Sub_Field.TABLE_NAME,
            Acceptance_Brcd_Box_Field.TABLE_NAME,
            Acceptance_Brcd_Drop_Field.TABLE_NAME,
            Acceptance_Brcd_Kcn_Field.TABLE_NAME,
            Acceptance_Brcd_Kct_Field.TABLE_NAME,
            Acceptance_Brcd_Mx_Field.TABLE_NAME,
            Acceptance_Brcd_Tn_Field.TABLE_NAME,

            Supv_Constr_Attach_FileField.TABLE_NAME,
            Supervision_Locate_Field.TABLE_NAME,
            Cat_Employee_Plan_Field.TABLE_NAME,
            Supervision_CNDTCField.TABLE_NAME,
            Constr_Progress_Field.TABLE_NAME,
            Constr_ObStructionField.TABLE_NAME,

            Work_ItemsField.TABLE_NAME,
            Work_Items_ValueField.TABLE_NAME,
            Sub_Work_ItemField.TABLE_NAME,
            Sub_Work_Item_ValueField.TABLE_NAME,
            Constr_Work_LogsField.TABLE_NAME,
            Constr_Team_ProgressField.TABLE_NAME,
            Plan_Constr_DetailField.TABLE_NAME,
            ConstrNodeItemsField.TABLE_NAME
    };

    public static final Map<String, String[]> mapUpdateFields;

    static {
        mapUpdateFields = new HashMap<String, String[]>();
        mapUpdateFields.put(Login_Log_ConstrField.TABLE_NAME, Login_Log_ConstrController.allColumn);
        mapUpdateFields.put(Supervision_ConstrField.TABLE_NAME, Supervision_ConstrController.allColumnUpate);
        mapUpdateFields.put(Supv_Constr_DailyField.TABLE_NAME, Supervision_ConstrController.allColumnDailyUpate);
        mapUpdateFields.put(Supervision_ProgressField.TABLE_NAME, Supervision_ProgressController.allColumn);
        mapUpdateFields.put(Supervision_Line_BackgroundField.TABLE_NAME, Supervision_Line_BackgroundController.allColumn);
        mapUpdateFields.put(Supervision_Line_BG_FiberField.TABLE_NAME, Supervision_Line_BG_FiberController.allColumn);
        mapUpdateFields.put(Supervision_Line_BG_TankField.TABLE_NAME, Supervision_Line_BG_TankController.allColumn);
        mapUpdateFields.put(Supervision_Line_BG_CableField.TABLE_NAME, Supervision_Line_BG_CableController.allColumn);
        mapUpdateFields.put(Special_LocationField.TABLE_NAME, Special_LocationController.allColumn);
        mapUpdateFields.put(Supervision_Line_BG_PipeField.TABLE_NAME, Supervision_Line_BG_PipeController.allColumn);
        mapUpdateFields.put(Supervision_Line_BG_MXField.TABLE_NAME, Supervision_Line_BG_MxController.allColumn);
        mapUpdateFields.put(Cause_Line_BG_TankField.TABLE_NAME, Cause_Line_BG_TankController.allColumn);
        mapUpdateFields.put(Cause_Line_BG_SpecialField.TABLE_NAME, Cause_Line_BG_SpecialController.allColumn);
        mapUpdateFields.put(Cause_Line_BG_PipeField.TABLE_NAME, Cause_Line_BG_PipeController.allColumn);
        mapUpdateFields.put(Cause_Line_BG_CableField.TABLE_NAME, Cause_Line_BG_CableController.allColumn);
        mapUpdateFields.put(Cause_Line_BG_MXField.TABLE_NAME, Cause_Line_BG_MXController.allColumn);
        mapUpdateFields.put(Acceptance_Line_Bg_SpecialField.TABLE_NAME, Acceptance_Line_Bg_SpecialController.allColumn);
        mapUpdateFields.put(Acceptance_Line_Bg_TankField.TABLE_NAME, Acceptance_Line_Bg_TankController.allColumn);
        mapUpdateFields.put(Acceptance_Line_Bg_PipeField.TABLE_NAME, Acceptance_Line_Bg_PipeController.allColumn);
        mapUpdateFields.put(Supervision_Line_HangField.TABLE_NAME, Supervision_Line_HangController.allColumn);
        mapUpdateFields.put(Supervision_Line_Hg_FiberField.TABLE_NAME, Supervision_Line_Hg_FiberController.allColumn);
        mapUpdateFields.put(Supervision_Line_Hg_PillarField.TABLE_NAME, Supervision_Line_Hg_PillarController.allColumn);
        mapUpdateFields.put(Supervision_Line_Hg_MxField.TABLE_NAME, Supervision_Line_Hg_MxController.allColumn);
        mapUpdateFields.put(Supervision_Line_Hg_CableField.TABLE_NAME, Supervision_Line_Hg_CableController.allColumn);
        mapUpdateFields.put(Cause_Line_Hg_PillarField.TABLE_NAME, Cause_Line_Hg_PillarController.allColumn);
        mapUpdateFields.put(Cause_Line_Hg_MxField.TABLE_NAME, Cause_Line_Hg_MxController.allColumn);
        mapUpdateFields.put(Cause_Line_Hg_CableField.TABLE_NAME, Cause_Line_Hg_CableController.allColumn);
        mapUpdateFields.put(Supervision_BtsField.TABLE_NAME, Supervision_BtsController.allColumn);
        mapUpdateFields.put(Supervision_Bts_EquipField.TABLE_NAME, Supervision_Bts_EquipController.allColumn);
        mapUpdateFields.put(Supervision_Bts_MeasureField.TABLE_NAME, Supervision_Bts_MeasureController.allColumn);
        mapUpdateFields.put(Supervision_Bts_Power_PoleField.TABLE_NAME, Supervision_Bts_Power_PoleController.allColumn);
        mapUpdateFields.put(Supervision_Bts_Pillar_AntenField.TABLE_NAME, Supervision_Bts_Pillar_AntenController.allColumn);
        mapUpdateFields.put(Supervision_Bts_Cat_WorkField.TABLE_NAME, Supervision_Bts_Cat_WorkController.allColumn);
        mapUpdateFields.put(Cause_Bts_Pillar_AntenField.TABLE_NAME, Cause_Bts_Pillar_AntenController.allColumn);
        mapUpdateFields.put(Cause_Bts_Cat_WorkField.TABLE_NAME, Cause_Bts_Cat_WorkController.allColumn);
        mapUpdateFields.put(Cause_Bts_Power_PoleField.TABLE_NAME, Cause_Bts_Power_PoleController.allColumn);
        mapUpdateFields.put(Acceptance_Bts_PillarField.TABLE_NAME, Acceptance_Bts_PillarController.allColumn);
        mapUpdateFields.put(Acceptance_Bts_Cat_WorkField.TABLE_NAME, Acceptance_Bts_Cat_WorkController.allColumn);
        mapUpdateFields.put(Supervision_Measure_ConstrField.TABLE_NAME, Supervision_Measure_ConstrController.allColumn);
        mapUpdateFields.put(Measurement_Detail_ConstrField.TABLE_NAME, Measurement_Detail_ConstrController.allColumn);
        mapUpdateFields.put(Supv_Constr_Attach_FileField.TABLE_NAME, Supv_Constr_Attach_FileController.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Sub_Field.TABLE_NAME, Supervision_BRCD_Sub_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Drop_Design_Field.TABLE_NAME, Supervision_BRCD_Drop_Design_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Drop_Field.TABLE_NAME, Supervision_BRCD_Drop_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Field.TABLE_NAME, Supervision_BRCD_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME, Supervision_BRCD_GiamSat_DropGo_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME, Supervision_BRCD_GiamSat_Kcn_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME, Supervision_BRCD_GiamSat_Kct_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Kcn_Design_Field.TABLE_NAME, Supervision_BRCD_Kcn_Design_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Kcn_Field.TABLE_NAME, Supervision_BRCD_Kcn_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Kct_design_Field.TABLE_NAME, Supervision_BRCD_Kct_design_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Kct_Field.TABLE_NAME, Supervision_BRCD_Kct_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_MXField.TABLE_NAME, Supervision_BRCD_MxController.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Tn_Field.TABLE_NAME, Supervision_BRCD_Tn_Controller.allColumn);
        mapUpdateFields.put(Supervision_BRCD_Ttb_Field.TABLE_NAME, Supervision_BRCD_Ttb_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Sub_Field.TABLE_NAME, Cause_Brcd_Sub_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Box_Field.TABLE_NAME, Cause_Brcd_Box_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Drop_Field.TABLE_NAME, Cause_Brcd_Drop_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Kcn_Field.TABLE_NAME, Cause_Brcd_Kcn_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Kct_Field.TABLE_NAME, Cause_Brcd_Kct_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Mx_Field.TABLE_NAME, Cause_Brcd_Mx_Controller.allColumn);
        mapUpdateFields.put(Cause_Brcd_Tn_Field.TABLE_NAME, Cause_Brcd_Tn_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Sub_Field.TABLE_NAME, Acceptance_Brcd_Sub_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Box_Field.TABLE_NAME, Acceptance_Brcd_Box_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Drop_Field.TABLE_NAME, Acceptance_Brcd_Drop_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Kcn_Field.TABLE_NAME, Acceptance_Brcd_Kcn_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Kct_Field.TABLE_NAME, Acceptance_Brcd_Kct_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Mx_Field.TABLE_NAME, Acceptance_Brcd_Mx_Controller.allColumn);
        mapUpdateFields.put(Acceptance_Brcd_Tn_Field.TABLE_NAME, Acceptance_Brcd_Tn_Controller.allColumn);

        mapUpdateFields.put(Supervision_Locate_Field.TABLE_NAME, Supervision_LocateController.allColumn);
        mapUpdateFields.put(Cat_Employee_Plan_Field.TABLE_NAME, Cat_Employee_Plan_Controller.allColumn);
        //Cap nhat doi thi cong
        mapUpdateFields.put(Supervision_CNDTCField.TABLE_NAME, Supervision_CNDTCController.allColumnConstrProgressContruct);
        mapUpdateFields.put(Constr_Progress_Field.TABLE_NAME, Supervision_CNDTCController.allColumnConstrProgress);
        //Cap nhat vuong
        mapUpdateFields.put(Constr_ObStructionField.TABLE_NAME, Supervision_CNVController.allColumnConstr_Obstruction);

        mapUpdateFields.put(Work_ItemsField.TABLE_NAME, Work_ItemsControler.allColumn);
        mapUpdateFields.put(Work_Items_ValueField.TABLE_NAME, Work_Items_ValueControler.allColumn);
        mapUpdateFields.put(Sub_Work_ItemField.TABLE_NAME, Sub_Work_ItemController.allColumn);
        mapUpdateFields.put(Sub_Work_Item_ValueField.TABLE_NAME, Sub_Work_Item_ValueController.allColumn);
        mapUpdateFields.put(Constr_Work_LogsField.TABLE_NAME, Constr_Work_LogsController.allColumn);
        mapUpdateFields.put(Constr_Team_ProgressField.TABLE_NAME, Constr_Team_ProgressController.allColumn);
        mapUpdateFields.put(Plan_Constr_DetailField.TABLE_NAME, Plan_Constr_DetailController.allColumn);

        mapUpdateFields.put(ConstrNodeItemsField.TABLE_NAME, ConstrNodeController.allColumn);

    }

    public static String[] ListColumnIdTableUpdate = new String[]{

            Login_Log_ConstrField.COLUMN_LOG_ID,
            Supv_Constr_DailyField.COLUMN_SUPERVISION_CONSTR_ID,

            Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
            Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
            Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
            Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
            Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,


            Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,

            Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,

            Supervision_Line_BG_FiberField.COLUMN_SUPERVISION_LINE_BG_FIBER_ID,
            Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
            Supervision_Line_BG_CableField.COLUMN_SUPERVISION_LINE_BG_CABLE_ID,
            Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
            Supervision_Line_BG_PipeField.COLUMN_SUPERVISION_LINE_BG_PIPE_ID,
            Supervision_Line_BG_MXField.COLUMN_SUPERVISION_LINE_BG_MX_ID,
            Cause_Line_BG_TankField.COLUMN_CAUSE_LINE_BG_TANK_ID,
            Cause_Line_BG_SpecialField.COLUMN_CAUSE_LINE_BG_SPECIAL_ID,
            Cause_Line_BG_PipeField.COLUMN_CAUSE_LINE_BG_PIPE_ID,
            Cause_Line_BG_CableField.COLUMN_CAUSE_LINE_BG_CABLE_ID,
            Cause_Line_BG_MXField.COLUMN_CAUSE_LINE_BG_MX_ID,
            Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID,
            Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID,
            Acceptance_Line_Bg_PipeField.COLUMN_ACCEPTANCE_LINE_BG_PIPE_ID,

            Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
            Supervision_Line_Hg_PillarField.COLUMN_SUPERVISION_LINE_HG_PILLAR_ID,
            Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
            Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
            Cause_Line_Hg_PillarField.COLUMN_CAUSE_LINE_HG_PILLAR_ID,
            Cause_Line_Hg_MxField.COLUMN_CAUSE_LINE_HG_MX_ID,
            Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,

            Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
            Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
            Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
            Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
            Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
            Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
            Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
            Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
            Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
            Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
            Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
            Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,


            Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
            Supervision_BRCD_Drop_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,

            Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
            Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_FIBER_ID,
            Supervision_BRCD_GiamSat_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_FIBER_ID,
            Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
            Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
            Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
            Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
            Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
            Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
            Supervision_BRCD_Ttb_Field.COLUMN_SUPERVISION_BRANCH_BOX_ID,
            Cause_Brcd_Sub_Field.COLUMN_CAUSE_SUBHEADEND_ID,
            Cause_Brcd_Box_Field.COLUMN_CAUSE_BRANCH_BOX_ID,
            Cause_Brcd_Drop_Field.COLUMN_CAUSE_DROP_FIBER_ID,
            Cause_Brcd_Kcn_Field.COLUMN_CAUSE_BRANCH_CABLE_FIBER_ID,
            Cause_Brcd_Kct_Field.COLUMN_CAUSE_TRUNK_CABLE_ID,
            Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
            Cause_Brcd_Tn_Field.COLUMN_CAUSE_BRANCH_CABINET_ID,
            Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID,
            Acceptance_Brcd_Box_Field.COLUMN_ACCEPTANCE_BRANCH_BOX_ID,
            Acceptance_Brcd_Drop_Field.COLUMN_ACCEPTANCE_DROP_FIBRE_ID,
            Acceptance_Brcd_Kcn_Field.COLUMN_ACCEPTANCE_BRANCH_CABLE_FIBER_ID,
            Acceptance_Brcd_Kct_Field.COLUMN_ACCEPTANCE_TRUNK_CABLE_ID,
            Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID,
            Acceptance_Brcd_Tn_Field.COLUMN_ACCEPTANCE_BRANCH_CABINET_ID,

            Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
            Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID,
            Cat_Employee_Plan_Field.COLUMN_PLAN_ID,
            Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
            Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
            Constr_ObStructionField.COLUMN_ID,

            Work_ItemsField.WORK_ITEM_ID,
            Work_Items_ValueField.WORK_ITEM_ID,
            Sub_Work_ItemField.ID,
            Sub_Work_Item_ValueField.ID,
            Constr_Work_LogsField.CONSTR_WORK_LOGS_ID,
            Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID,
            Plan_Constr_DetailField.PLAN_CONSTR_DETAIL_ID

    };

    public static SyncTableUpdate getInstance() {
        if (instance == null) {
            instance = new SyncTableUpdate();
        }
        return instance;
    }
}
