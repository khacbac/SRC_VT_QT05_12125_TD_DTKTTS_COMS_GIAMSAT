/*
 * Copyright (c) 2012 d4rxh4wx
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.viettel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.database.field.Cat_Cause_Not_WorkField;
import com.viettel.database.field.Cat_Constr_TeamField;
import com.viettel.database.field.Cat_Milestone_ConstrField;
import com.viettel.database.field.Cat_Plan_For_ConstrField;
import com.viettel.database.field.Cat_Sub_Work_ItemField;
import com.viettel.database.field.Cat_Work_Item_TypesField;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.database.field.ConstrNodeItemsField;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_Team_ProgressField;
import com.viettel.database.field.Constr_Work_LogsField;
import com.viettel.database.field.Plan_Constr_DetailField;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supv_Constr_Attach_FileField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.database.field.Work_Items_ValueField;

public class KttsMultiThreadSQLiteOpenHelper extends
        MultiThreadSQLiteOpenHelper {

    public static final String TAG = KttsMultiThreadSQLiteOpenHelper.class
            .getSimpleName();

    // Database Version
    private static final int DATABASE_VERSION = 48;//32
    // Database Name
    private static final String DATABASE_NAME = "KTTS_DB_MANAGER";

    public KttsMultiThreadSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.e(TAG, "KttsMultiThreadSQLiteOpenHelper: " + DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e(TAG, "onCreate: ");
        /* Tao bang khoa */
        db.execSQL(Ktts_KeyController.CREATE_KTTS_KEY_TABLE);

		/* Tao bang nguoi dung */
        db.execSQL(EmployeeController.CREATE_LOGIN_TABLE);

		/* Tao bang log user */
        db.execSQL(Login_Log_ConstrController.CREATE_LOGIN_LOG_CONSTR_TABLE);

		/* Tao bang yeu cau giam sat cong trinh */
        db.execSQL(Supervision_Constr_REQController.CREATE_SUPERVISION_CONSTR_REQ_TABLE);

		/* Tao bang cong trinh */
        db.execSQL(Constr_ConstructionController.CREATE_CONSTR_CONSTRUCTIONS_TABLE);

		/* Bang mo rong cua cong trinh quan he voi bang yeu cau */
//        db.execSQL("DROP TABLE IF EXISTS " + Supervision_ConstrField.TABLE_NAME);
        db.execSQL(Supervision_ConstrController.CREATE_SUPERVISION_CONSTR_TABLE);

		/* Tao bang nguyen nhan loi */
        db.execSQL(Cat_Cause_Constr_UnQualifyController.CREATE_CAT_CAUSE_CONSTR_UNQUALIFY_TABLE);

		/* Tao bang nguyen nhan loi */
        db.execSQL(Cat_Cause_Constr_AcceptanceController.CREATE_CAT_CAUSE_CONSTR_ACCEPTANCE_TABLE);

        // tao bang do
        db.execSQL(Supervision_Measure_ConstrController.CREATE_SUPERVISION_MEASURE_CONSTR_TABLE);

        // tao bang do chi tiet
        db.execSQL(Measurement_Detail_ConstrController.CREATE_MEASUREMENT_DETAIL_CONSTR_TABLE);

        //
        db.execSQL(Supervision_BtsController.CREATE_SUPERVISION_BTS_TABLE);

		/* Tao bang Tuyen ngam */
        db.execSQL(Supervision_Line_BackgroundController.CREATE_SUPERVISION_LINE_BACKGROUND_TABLE);

		/* Tao bang Vi tri dac biet */
        db.execSQL(Special_LocationController.CREATE_SPECIAL_LOCATION_TABLE);

		/* Tao bang be */
        db.execSQL(Supervision_Line_BG_TankController.CREATE_SUPERVISION_LINE_BG_TANK_TABLE);

		/* Tao bang ong */
        db.execSQL(Supervision_Line_BG_PipeController.CREATE_SUPERVISION_LINE_BG_PIPE_TABLE);

		/* Tao bang ong */
        db.execSQL(Supervision_Line_BG_CableController.CREATE_SUPERVISION_LINE_BG_CABLE_TABLE);

		/* Tao bang ong */
        db.execSQL(Supervision_Line_BG_MxController.CREATE_SUPERVISION_LINE_BG_MX_TABLE);

		/* Tao bang giam sat cap */
        db.execSQL(Supervision_Line_BG_FiberController.CREATE_SUPERVISION_LINE_BG_FIBER_TABLE);

		/* Tao bang be va nguyen nhan loi */
        db.execSQL(Cause_Line_BG_TankController.CREATE_CAUSE_LINE_BG_TANK_TABLE);

		/* Tao bang vi tri dac biet va nguyen nhan loi */
        db.execSQL(Cause_Line_BG_SpecialController.CREATE_CAUSE_LINE_BG_SPECIAL_TABLE);

		/* Tao bang ong va nguyen nhan loi */
        db.execSQL(Cause_Line_BG_PipeController.CREATE_CAUSE_LINE_BG_PIPE_TABLE);

		/* Tao bang ong va nguyen nhan loi */
        db.execSQL(Cause_Line_BG_CableController.CREATE_CAUSE_LINE_BG_CABLE_TABLE);

		/* Tao bang ong va nguyen nhan loi */
        db.execSQL(Cause_Line_BG_MXController.CREATE_CAUSE_LINE_BG_MX_TABLE);

		/* Tao bang li do giam sat cong trinh */
        db.execSQL(Cat_Supervision_Constr_WorkController.CREATE_CAT_SUPERVISION_CONSTR_WORK_TABLE);

		/* Tao bang tien do cong trinh */
        db.execSQL(Cat_Progress_WorkController.CREATE_CAT_PROGRESS_WORK_TABLE);

		/* Tao bang giam sat cat work */
        db.execSQL(Supervision_Bts_Cat_WorkController.CREATE_SUPERVISION_BTS_CAT_WORK_TABLE);
        // Log.i("xxxxxxxxxxPillar",
        // Acceptance_Bts_PillarController.CREATE_ACCEPTANCE_BTS_PILLAR_TABLE);

        // Log.i("xxxxxxxxxxCatWork",
        // Acceptance_Bts_Cat_WorkController.CREATE_ACCEPTANCE_BTS_CAT_WORK_TABLE);

		/* Tao bang li do giam sat cat work */

        db.execSQL(Cause_Bts_Cat_WorkController.CREATE_CAUSE_BTS_CAT_WORK_TABLE);

		/* Tao bang giam sat thi cong cot */
        db.execSQL(Supervision_Bts_Pillar_AntenController.CREATE_SUPERVISION_BTS_PILLAR_ANTEN_TABLE);

		/* Tao bang li do giam sat thi cong cot */
        db.execSQL(Cause_Bts_Pillar_AntenController.CREATE_CAUSE_BTS_PILLAR_ANTEN_TABLE);

		/* Tao bang giam sat thi cong cot */
        db.execSQL(Supervision_Bts_Power_PoleController.CREATE_SUPERVISION_BTS_POWER_POLE_TABLE);

		/* Tao bang li do giam sat thi cong cot */
        db.execSQL(Cause_Bts_Power_PoleController.CREATE_CAUSE_BTS_POWER_POLE_TABLE);

		/* Tao bang hang san xuat */
        db.execSQL(Product_CompanyController.CREATE_PRODUCT_COMPANY_TABLE);

		/* Tao bang hang supervision_bts_equip */
        db.execSQL(Supervision_Bts_EquipController.CREATE_SUPERVISION_BTS_EQUIP_TABLE);

		/* Tao bang giam sat chat luong do kiem */
        db.execSQL(Cat_Supv_Constr_MeasureController.CREATE_CAT_SUPV_CONSTR_MEASURE_TABLE);

		/* Tao bang hang supervision_bts_measure */
        db.execSQL(Supervision_Bts_MeasureController.CREATE_SUPERVISION_BTS_MEASURE_TABLE);

		/* Tao bang tuyen treo */
        db.execSQL(Supervision_Line_HangController.CREATE_SUPERVISION_LINE_HANG_TABLE);

		/* Tao bang cột Tuyen treo */
        db.execSQL(Supervision_Line_Hg_PillarController.CREATE_LINE_HG_PILLAR_TABLE);

		/* Tao bang giam sat cot tuyen treo loi */
        db.execSQL(Cause_Line_Hg_PillarController.CREATE_CAUSE_LINE_HG_PILLAR_TABLE);

		/* Tao bang giam sat keo cap tuyen treo */
        db.execSQL(Supervision_Line_Hg_CableController.CREATE_SUPERVISION_LINE_BG_CABLE_TABLE);

		/* Tao bang giam sat cot tuyen treo loi */
        db.execSQL(Cause_Line_Hg_CableController.CREATE_CAUSE_LINE_HG_CABLE_TABLE);

		/* Tao bang giam sat mang xong tuyen treo loi */
        db.execSQL(Supervision_Line_Hg_MxController.CREATE_SUPERVISION_LINE_HG_MX_TABLE);

		/* Tao bang giam sat soi tuyen treo loi */
        db.execSQL(Supervision_Line_Hg_FiberController.CREATE_SUPERVISION_LINE_BG_FIBER_TABLE);

		/* Tao bang mang xong tuyen treo loi */
        db.execSQL(Cause_Line_Hg_MxController.CREATE_CAUSE_LINE_HG_MX_TABLE);

		/* Tao bang cong ranh cap va nghiem thu */
        db.execSQL(Acceptance_Line_Bg_PipeController.CREATE_ACCEPTANCE_LINE_BG_PIPE_TABLE);

		/* Tao bang be va nghiem thu */
        db.execSQL(Acceptance_Line_Bg_TankController.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);

		/* Tao bang vi tri dac biet va nghiem thu */
        db.execSQL(Acceptance_Line_Bg_SpecialController.CREATE_ACCEPTANCE_LINE_BG_SPECIAL_TABLE);
		/* Tao bang thong tin thiet ke bang rong co dinh */
        db.execSQL(Supervision_BRCD_Controller.CREATE_SUPERVISION_CONSTR_TABLE);

		/* Tao bang cat work va nghiem thu */
        try {
            db.execSQL(Acceptance_Bts_Cat_WorkController.CREATE_ACCEPTANCE_BTS_CAT_WORK_TABLE);
        } catch (Exception e) {
            Log.i("hihi", e.toString());
        }

		/* Tao bang thi cong cot va nghiem thu */
        db.execSQL(Acceptance_Bts_PillarController.CREATE_ACCEPTANCE_BTS_PILLAR_TABLE);

		/* Tao bang giam sat tien do */
        db.execSQL(Supervision_ProgressController.CREATE_SUPERVISION_PROGRESS_TABLE);

		/* Tao bang dinh kem file */
        db.execSQL(Supv_Constr_Attach_FileController.CREATE_SUPV_CONSTR_ATTACH_FILE_TABLE);
		/* Tao bang mang xong */
        db.execSQL(Supervision_BRCD_MxController.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_Kct_design_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_Kct_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Cause_Brcd_Kct_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);
        db.execSQL(Acceptance_Brcd_Kct_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);
        db.execSQL(Cause_Brcd_Mx_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);
        db.execSQL(Acceptance_Brcd_Mx_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);
        db.execSQL(Cause_Brcd_Kcn_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);

        db.execSQL(Acceptance_Brcd_Tn_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);
        db.execSQL(Cause_Brcd_Tn_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);

        db.execSQL(Acceptance_Brcd_Kcn_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);
        db.execSQL(Supervision_BRCD_Tn_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_Kcn_Design_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_GiamSat_Kcn_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_Kcn_Controller.CREATE_SUPERVISION_BRCD_TABLE);

        db.execSQL(Cause_Brcd_Drop_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);
        db.execSQL(Acceptance_Brcd_Drop_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);
        db.execSQL(Supervision_BRCD_Drop_Design_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_GiamSat_DropGo_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_GiamSat_Kct_Controller.CREATE_SUPERVISION_BRCD_TABLE);
        db.execSQL(Supervision_BRCD_Drop_Controller.CREATE_SUPERVISION_BRCD_TABLE);

        db.execSQL(Cause_Brcd_Box_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);
        db.execSQL(Acceptance_Brcd_Box_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);

        db.execSQL(Supervision_BRCD_Ttb_Controller.CREATE_SUPERVISION_BRCD_TABLE);

        db.execSQL(Cause_Brcd_Sub_Controller.CREATE_CAUSE_LINE_BG_TANK_TABLE);
        db.execSQL(Acceptance_Brcd_Sub_Controller.CREATE_ACCEPTANCE_LINE_BG_TANK_TABLE);

        db.execSQL(Supervision_BRCD_Sub_Controller.CREATE_SUPERVISION_BRCD_TABLE);

        // tao bang quan ly dinh vi
        db.execSQL(Supervision_LocateController.CREATE_SUPERVISION_LOCATE_TABLE);
        // quan ly ke hoach
        db.execSQL(Cat_Employee_Plan_Controller.CREATE_PLAN_TABLE);

        //---HungTN add new 24/08/2016
        db.execSQL(Supervision_CNDTCController.CREATE_CAT_CONSTRUCT_TABLE);
        db.execSQL(Supervision_CNDTCController.CREATE_CONSTR_PROGRESS_TABLE);
        db.execSQL(Supervision_CNDTCController.CREATE_SUPERVISION_CNDTC_TABLE);
        db.execSQL(Supervision_CNVController.CREATE_CONSTR_OBSTRUCTION_TABLE);
        db.execSQL(Supervision_CNVController.CREATE_CONSTR_OBSTRUCTION_TYPE_TABLE);
        //---

//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Work_Item_TypesField.TABLE_NAME);
        db.execSQL(Cat_Work_Item_TypesControler.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Sub_Work_ItemField.TABLE_NAME);
        db.execSQL(Cat_Sub_Work_ItemControler.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Work_ItemsField.TABLE_NAME);
        db.execSQL(Work_ItemsControler.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Work_Items_ValueField.TABLE_NAME);
        db.execSQL(Work_Items_ValueControler.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Sub_Work_ItemField.TABLE_NAME);
        db.execSQL(Sub_Work_ItemController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Sub_Work_Item_ValueField.TABLE_NAME);
        db.execSQL(Sub_Work_Item_ValueController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Constr_Work_LogsField.TABLE_NAME);
        db.execSQL(Constr_Work_LogsController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Constr_TeamField.TABLE_NAME);
        db.execSQL(Cat_Constr_TeamController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Constr_Team_ProgressField.TABLE_NAME);
        db.execSQL(Constr_Team_ProgressController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Cause_Not_WorkField.TABLE_NAME);
        db.execSQL(Cat_Cause_Not_WorkController.CREATE_TABLE);
//        db.execSQL(Cat_Cause_Not_WorkController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Plan_Constr_DetailField.TABLE_NAME);
        db.execSQL(Plan_Constr_DetailController.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Milestone_ConstrField.TABLE_NAME);
        db.execSQL(Cat_Milestone_ConstrControler.CREATE_TABLE);
//        db.execSQL("DROP TABLE IF EXISTS " + Cat_Plan_For_ConstrField.TABLE_NAME);
        db.execSQL(Cat_Plan_For_ConstrControler.CREATE_TABLE);

        db.execSQL(ConstrNodeController.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "onUpgrade() from " + oldVersion + " to " + newVersion);

        if (newVersion > oldVersion) {
            switch (oldVersion) {
                case 34:
                    db.execSQL("ALTER TABLE " + Supervision_ConstrField.TABLE_NAME + " ADD COLUMN "+  Supervision_ConstrField.COLUMN_LINE_TYPE + " INTEGER;");
                    db.execSQL("ALTER TABLE " + Supervision_ConstrField.TABLE_NAME + " ADD COLUMN "+  Supervision_ConstrField.COLUMN_CONSTRUCTORNAME + " TEXT;");
                    db.execSQL("ALTER TABLE " + Supervision_ConstrField.TABLE_NAME + " ADD COLUMN "+  Supervision_ConstrField.COLUMN_SUPERVISORNAME + " TEXT;");
                    db.execSQL("ALTER TABLE " + Supervision_ConstrField.TABLE_NAME + " ADD COLUMN "+  Supervision_ConstrField.COLUMN_STATUSNAME + " TEXT;");
                    db.execSQL("ALTER TABLE " + Supervision_ConstrField.TABLE_NAME + " ADD COLUMN "+  Supervision_ConstrField.COLUMN_SYS_CREATE_DATE + " TEXT;");

                    db.execSQL("ALTER TABLE " + Constr_ObStructionField.TABLE_NAME + " ADD COLUMN "+  Constr_ObStructionField.CONSTR_WORK_LOGS_ID + " INTEGER;");

                    db.execSQL(Cat_Work_Item_TypesControler.CREATE_TABLE);
                    db.execSQL(Cat_Sub_Work_ItemControler.CREATE_TABLE);
                    db.execSQL(Work_ItemsControler.CREATE_TABLE);
                    db.execSQL(Work_Items_ValueControler.CREATE_TABLE);
                    db.execSQL(Sub_Work_ItemController.CREATE_TABLE);
                    db.execSQL(Sub_Work_Item_ValueController.CREATE_TABLE);
                    db.execSQL(Constr_Work_LogsController.CREATE_TABLE);
                    db.execSQL(Cat_Constr_TeamController.CREATE_TABLE);
                    db.execSQL(Constr_Team_ProgressController.CREATE_TABLE);
                    db.execSQL(Cat_Cause_Not_WorkController.CREATE_TABLE);
                    db.execSQL(Plan_Constr_DetailController.CREATE_TABLE);
                    db.execSQL(Cat_Milestone_ConstrControler.CREATE_TABLE);
                    db.execSQL(Cat_Plan_For_ConstrControler.CREATE_TABLE);
                    break;
                case 35:
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: version 35" + "Add Constr Node Table");
                    break;
                case 36:
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: version " + oldVersion + " Add Constr Node Table");
                    break;
                case 37:
                    String changeName = "ALTER TABLE NODE_ITEMS RENAME TO " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(changeName);
                    Log.d(TAG, "onUpgrade: version " + oldVersion + " change name success");
                    break;
                case 38:
                    String addContrNodeIdColumn = "ALTER TABLE " + Work_ItemsField.TABLE_NAME + " ADD COLUMN "+  Work_ItemsField.CONSTR_NODE_ID + " INTEGER;";
                    db.execSQL(addContrNodeIdColumn);
                    Log.d(TAG, "onUpgrade: version " + oldVersion + " add column success");
                    break;
                case 39:
                    String dropTable = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 40:
                    String dropTable40 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable40);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 41:
                    String dropTable41 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable41);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 42:
                    String dropTable42 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable42);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 44:
                    String dropTable43 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable43);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 45:
                    String dropTable45 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable45);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 46:
                    String dropTable46 = "DROP TABLE IF EXISTS " + ConstrNodeItemsField.TABLE_NAME;
                    db.execSQL(dropTable46);
                    db.execSQL(ConstrNodeController.CREATE_TABLE);
                    Log.d(TAG, "onUpgrade: ReCreate Table Success");
                    break;
                case 47:
                    String addWorkItemNodeId = "ALTER TABLE " + Work_ItemsField.TABLE_NAME + " ADD COLUMN "+  Work_ItemsField.CONSTR_NODE_ID + " INTEGER;";
                    db.execSQL(addWorkItemNodeId);
                    Log.d(TAG, "onUpgrade: add column success");
                    break;
                default:
                    onCreate(db);
                    break;
            }
//            if (newVersion == DATABASE_VERSION) { // phien ban db update them
//				onCreate(db);
//            }
        }
    }

}
