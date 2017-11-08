package com.viettel.sync;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ErrorConstants;
import com.viettel.common.FileManager;
import com.viettel.common.GlobalInfo;
import com.viettel.common.LogManager;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
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
import com.viettel.database.Cat_Cause_Constr_AcceptanceController;
import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.Cat_Cause_Not_WorkController;
import com.viettel.database.Cat_Constr_TeamController;
import com.viettel.database.Cat_Employee_Plan_Controller;
import com.viettel.database.Cat_Milestone_ConstrControler;
import com.viettel.database.Cat_Plan_For_ConstrControler;
import com.viettel.database.Cat_Progress_WorkController;
import com.viettel.database.Cat_Sub_Work_ItemControler;
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cat_Supv_Constr_MeasureController;
import com.viettel.database.Cat_Work_Item_TypesControler;
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
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Constr_Team_ProgressController;
import com.viettel.database.Constr_Work_LogsController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Login_Log_ConstrController;
import com.viettel.database.Measurement_Detail_ConstrController;
import com.viettel.database.Plan_Constr_DetailController;
import com.viettel.database.Product_CompanyController;
import com.viettel.database.Special_LocationController;
import com.viettel.database.SqlliteSyncModel;
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
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Constr_REQController;
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
import com.viettel.database.entity.EmployeeEntity;
import com.viettel.database.entity.Ktts_KeyEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
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
import com.viettel.database.field.BaseField;
import com.viettel.database.field.Cat_Cause_Constr_AcceptanceField;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;
import com.viettel.database.field.Cat_Cause_Not_WorkField;
import com.viettel.database.field.Cat_Constr_TeamField;
import com.viettel.database.field.Cat_Employee_Plan_Field;
import com.viettel.database.field.Cat_Milestone_ConstrField;
import com.viettel.database.field.Cat_Plan_For_ConstrField;
import com.viettel.database.field.Cat_Progress_WorkField;
import com.viettel.database.field.Cat_Sub_Work_ItemField;
import com.viettel.database.field.Cat_Supervision_Constr_WorkField;
import com.viettel.database.field.Cat_Supv_Constr_MeasureField;
import com.viettel.database.field.Cat_Work_Item_TypesField;
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
import com.viettel.database.field.Constr_ConstructionField;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_ObStruction_TypeField;
import com.viettel.database.field.Constr_Team_ProgressField;
import com.viettel.database.field.Constr_Work_LogsField;
import com.viettel.database.field.EmployeeField;
import com.viettel.database.field.Login_Log_ConstrField;
import com.viettel.database.field.Measurement_Detail_ConstrField;
import com.viettel.database.field.Plan_Constr_DetailField;
import com.viettel.database.field.Product_CompanyField;
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
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.database.field.Supervision_Constr_REQField;
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
import com.viettel.service.GpsServices;
import com.viettel.utils.Mylog;
import com.viettel.utils.PreferenceUtil;
import com.viettel.utils.SqliteUtil;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;
import com.viettel.viettellib.network.http.HTTPClient;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class SyncModel extends AbstractSyncService {
    private static final String TAG = "SyncModel";
    // TODO phan nay con chua kiem tra ky
    public static boolean bStop = false;
    public static boolean bRunning = false;
    protected static SyncModel instance;
    public static Context mContext = null;

    // bien phan tram dong bo
    Double percent = (double) 10;
    // bien update cho phan tram dong bo
    Double percentLeap = (double) 30 / 75;
    private Handler mHandler;
    // so bang can day du lieu len server
    private int numberTableUpdate = 0;
    // so anh can lay tu server ve
    private int numberGetImage = 0;
    // so anh can day len server
    private int numberPutImage = 0;
    // truong de nhan biet bang du lieu nao dang duoc update
    private int tableUpdateCurent = 0;
    private int tableUpdateNumCurent = 0;

    protected SyncModel() {
        if (SyncModel.mContext == null) {
            SyncModel.mContext = GlobalInfo.getInstance().getAppContext();
        }
    }

    public static SyncModel getInstance() {
        if (instance == null) {
            instance = new SyncModel();
        }
        return instance;
    }

    public void onReceiveData(HTTPMessage mes) {

        ActionEvent actionEvent = (ActionEvent) mes.getUserData();
        ModelEvent model = new ModelEvent();
        model.setDataText(mes.getDataText());
        model.setCode(mes.getCode());
        model.setParams(((HTTPResponse) mes).getRequest().getDataText());
        model.setActionEvent(actionEvent);
        Log.e(TAG, "onReceiveData() called with: mes = " + mes.getAction() + " \n\t" + model.getDataText());
        // DMD check null or empty
        if (StringUtil.isNullOrEmpty((String) mes.getDataText())) {
            model.setModelCode(ErrorConstants.ERROR_COMMON);
            Home_Controller.getInstance().handleErrorModelEvent(model);
            return;
        }

        switch (mes.getAction()) {
            case ActionEventConstant.REQEST_LOGIN:
                this.receiveLogin_Handler(mes, model);
                break;
            // REQUEST KEY
            case ActionEventConstant.REQUEST_KEYKTTS:
                this.receiveKttsKeyHandler(mes, model);
                break;

            case ActionEventConstant.REQUEST_GETDATA:

                this.receiveGetDataHandler(mes, model);
                break;

            case ActionEventConstant.REQUEST_UPDATEDATA:
                this.receiveUpdateDataHandler(mes, model);
                break;
            case ActionEventConstant.REQUEST_UPDATEIMAGE:
                this.receiveUpdateImageHandler(mes, model);
                break;
            case ActionEventConstant.REQUEST_DOWNLOADIMAGE:
                this.receiveGetImageHandler(mes, model);
                break;

        }
    }

    public void onReceiveError(HTTPResponse response) {
        Log.d(TAG, "onReceiveError() called with: response = [" + response + "]");
        SyncModel.bStop = true;
        ActionEvent actionEvent = (ActionEvent) response.getUserData();
        ModelEvent model = new ModelEvent();
        model.setDataText(response.getDataText());
        model.setParams(((HTTPResponse) response).getRequest().getDataText());
        model.setActionEvent(actionEvent);
        model.setModelCode(response.getErrorCode());
        model.setCode(response.getErrorCode()); // cuongdk3 add
        // model.setModelCode(ErrorConstants.ERROR_NO_CONNECTION);
        model.setModelMessage(response.getErrMessage());
        Home_Controller.getInstance().handleErrorModelEvent(model);
    }

	/* Dang nhap he thong */

    public HTTPRequest requestLoginHTTP(ActionEvent e) {
        HTTPRequest re = null;
        try {
            Bundle bundle = (Bundle) e.viewData;
            JSONObject json = new JSONObject();
            json.put(IntentConstants.INTENT_LOGIN_NAME,
                    bundle.getString(IntentConstants.INTENT_LOGIN_NAME));
            json.put(IntentConstants.INTENT_PASSWORD,
                    bundle.getString(IntentConstants.INTENT_PASSWORD));
            json.put(IntentConstants.INTENT_CLIENTID,
                    bundle.getString(IntentConstants.INTENT_CLIENTID));
            re = (HTTPRequest) sendHttpRequest("authenticate.login", json, e,
                    HttpAsyncTask.CONNECT_TIMEOUT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return (HTTPRequest) re;
    }

	/* Don nhan thong bao dang nhap */

    protected void receiveLogin_Handler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        try {
            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            int errCode = jsonresult.getInt("errorCode");
            int userId = jsonresult.getJSONObject("response").getInt(
                    EmployeeField.COLUMN_EMPLOYEE_ID);
            String sessionId = jsonresult.getJSONObject("response").getString(
                    "sessionId");
            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS && userId > 0) {
                HTTPClient.sessionID = sessionId;
                model.setModelCode(errCode);
                EmployeeEntity loginItem = EmployeeEntity.parseJson(jsonresult
                        .getJSONObject("response"));
                model.setModelData(loginItem);
                Home_Controller.getInstance().handleModelEvent(model);
            } else {
                model.setModelCode(errCode);
                Home_Controller.getInstance().handleErrorModelEvent(model);
            }
        } catch (Exception e) {
            model.setModelCode(ErrorConstants.ERROR_COMMON);
            Home_Controller.getInstance().handleErrorModelEvent(model);
        }
    }

    /**
     * Ham yeu cau cap moi khoan khoa cho bang
     *
     * @param item
     * @return
     */
    protected HTTPRequest requestKttsKey(ActionEvent e) {
        HTTPRequest re = null;
        try {
            Ktts_KeyEntity curItem = (Ktts_KeyEntity) e.userData;
            JSONObject json = new JSONObject();
            json.put(IntentConstants.INTENT_USER_ID, GlobalInfo.getInstance()
                    .getUserId());
            json.put(IntentConstants.INTENT_CLIENTID, GlobalInfo.getInstance()
                    .getClientId());
            json.put(IntentConstants.INTENT_TABLE_NAME, curItem.getTableName());
            json.put(IntentConstants.INTENT_KEY_MIN, curItem.getMin());
            json.put(IntentConstants.INTENT_KEY_MAX, curItem.getMax());
            Log.e(TAG, "requestKttsKey: " + json);
            re = sendHttpRequest("provideGId.processGId", json, e,
                    HttpAsyncTask.CONNECT_TIMEOUT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return re;
    }

    /**
     * Ham lay khoang gia tri Id he thong
     *
     * @param mes
     * @param model
     */

    protected void receiveKttsKeyHandler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        try {
            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            JSONObject jsonResponseData = jsonresult.getJSONObject("response");
            int errCode = jsonresult.getInt("errorCode");
            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS) {
                model.setModelCode(errCode);
                Ktts_KeyEntity item = (Ktts_KeyEntity) model.getActionEvent().userData;
                Ktts_KeyEntity itemGet = Ktts_KeyEntity
                        .parseJson(jsonResponseData);
                if (itemGet.getClientId().equals(
                        GlobalInfo.getInstance().getClientId())) {
                    if (item.getMax() == 0L) {
                        Ktts_KeyEntity addItem = new Ktts_KeyEntity();
                        addItem.setTableName(itemGet.getTableName());
                        addItem.setClientId(itemGet.getClientId());
                        addItem.setMin(itemGet.getMin());
                        addItem.setMax(itemGet.getMax());
                        addItem.setNewMin(0L);
                        addItem.setNewMax(0L);
                        Ktts_KeyController.getInstance().addKttsKey(addItem);
                    } else {
                        Ktts_KeyController.getInstance().updateNewKttsKey(
                                itemGet.getMin(), itemGet.getMax(),
                                itemGet.getTableName());
                    }
                }
                SyncQueue.getInstance().removeTableGetKey(item.getTableName());
                /* Neu ket thuc qua trinh lay khoa thi chuyen buoc */
                if (SyncQueue.getInstance().checkGetKeyFinish()) {
                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncGetData(model.getActionEvent());
                }

            } else {
                /* Goi ham bat thong bao loi */
                model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                Home_Controller.getInstance().handleErrorModelEvent(model);
            }
        } catch (Exception e) {
			/* Su ly khi bi loi */
            e.printStackTrace();
        }
    }

    /**
     * Ham request du lieu tren server ve
     *
     * @param item
     * @return
     */

    protected HTTPRequest requestGetData(boolean pFilterUser, ActionEvent e,
                                         String pMethod, SyncTableInfo pTableInfo) {
        HTTPRequest re = null;
        try {
            long idUser = GlobalInfo.getInstance().getUserId();
            ActionEvent action = new ActionEvent();
            action.sender = e.sender;
            action.action = pTableInfo.getActionId();
            action.isBlockRequest = true;
            action.userData = pTableInfo;
            long iMaxProcessId = 0;
            if (pFilterUser) {
                iMaxProcessId = SqlliteSyncModel.getInstance().getMaxProcessId(
                        pTableInfo.getTableName(), idUser);
            } else {
                iMaxProcessId = SqlliteSyncModel.getInstance().getMaxProcessId(
                        pTableInfo.getTableName());
            }
            Log.d(TAG, "requestGetData: table get = " + pTableInfo.getTableName());
            JSONObject json = new JSONObject();
            json.put("CAT_EMPLOYEE_ID", String.valueOf(idUser));
            json.put("PROCESS_ID", iMaxProcessId);
            json.put("tableName", pTableInfo.getTableName());
            re = sendHttpRequest(pMethod, json, action,
                    HttpAsyncTask.CONNECT_TIMEOUT);
            // Gan vao hang doi
            SyncQueue.getInstance().addTableGet(pTableInfo.getTableName());

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(TAG, "requestGetData: error message = " + ex.getMessage());
        }
        return re;
    }

    private void logLargeString(String str, String tableName) {
        if(str.length() > 3000) {
            Log.d(TAG,"From " + tableName + " = :" + str.substring(0, 3000));
            logLargeString(str.substring(3000), tableName);
        } else {
            Log.d(TAG,"From " + tableName + " = :" + str); // continuation
        }
    }

    /**
     * ham don nhan ket qua lay du lieu tu server ve
     *
     * @param mes
     * @param model
     */

    protected void receiveGetDataHandler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        try {
            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            JSONObject jsonResponseData = jsonresult.getJSONObject("response");
            ActionEvent actionEvent = model.getActionEvent();
            int errCode = jsonresult.getInt("errorCode");

            SyncTableInfo tableInfo = (SyncTableInfo) actionEvent.userData;
            if (tableInfo.getTableName().equals(Sub_Work_Item_ValueField.TABLE_NAME)) {
                logLargeString(jsonResponseData.toString(), tableInfo.getTableName());
            }
            Log.d(TAG, "receiveGetDataHandler: " + tableInfo.getTableName() +  " Data get = "+ jsonResponseData);
            if (tableInfo.getTableName().equals(Work_ItemsField.TABLE_NAME)) {
                Log.d(TAG, "receiveGetDataHandler: work_items data = " + mes.getDataText());
            }

            percent = percent + percentLeap;
            Message msg2 = new Message();
            Bundle bundle2 = new Bundle();
            bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                    + tableInfo.getTableName());
            msg2.setData(bundle2);
            mHandler.sendMessage(msg2);

            // Log.i("-------", jsonresult.toString());
            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS) {
                /* Update du lieu vao co so du lieu */
                ArrayList<HashMap<String, String>> listData;
				/* Chi ra 2 truong hop voi 2 bang tu them idUser vao bang */
                if (tableInfo.getTableName().equals(
                        Supervision_ConstrField.TABLE_NAME)
                        || tableInfo.getTableName().equals(
                        Constr_ConstructionField.TABLE_NAME)
                        || tableInfo.getTableName().equals(
                        Supervision_Constr_REQField.TABLE_NAME)) {
                    listData = SqliteUtil.converJsonToHasMap(jsonResponseData,
                            tableInfo.getAllColumn(), GlobalInfo.getInstance()
                                    .getUserId());
//					Mylog.i("id", "update du lieu 3 bang");
//					LogManager.getInstance().writeFile("update du lieu 3 bang");

                } else {
                    listData = SqliteUtil.converJsonToHasMap(jsonResponseData,
                            tableInfo.getAllColumn());
                }

				/* Kiem tra xem co phai lay du lieu file khong */

                if (tableInfo.getTableName().equals(
                        Supv_Constr_Attach_FileField.TABLE_NAME)) {
                    SqlliteSyncModel.updateInsertGetFileData(listData,
                            tableInfo);
                } else {
                    SqlliteSyncModel.updateInsertGetData(listData, tableInfo);
                }

                SyncQueue.getInstance()
                        .removeTableGet(tableInfo.getTableName());
                // new listData ma size > maxsize yeu cau dong bo lan nua
                if (SyncQueue.getInstance().checkGetFinish()) {
                    percent = 30D;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                    bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                            + tableInfo.getTableName());
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncUpateData(actionEvent);

                }

            } else {
                SyncQueue.getInstance()
                        .removeTableGet(tableInfo.getTableName());
                if (SyncQueue.getInstance().checkGetFinish()) {
                    percent = 30D;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                    bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                            + tableInfo.getTableName());
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncUpateData(actionEvent);

                }
                model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                Home_Controller.getInstance().handleErrorModelEvent(model);
            }

        } catch (Exception e) {
            Log.d(TAG, "receiveGetDataHandler: error = " + e.getMessage());
            e.printStackTrace();
            LogManager.getInstance().writeFile(e.getMessage());
            model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
            Home_Controller.getInstance().handleErrorModelEvent(model);
        }
    }

    /**
     * Ham yeu cau update du lieu len server
     *
     * @param actionEvent
     * @return
     */

    protected HTTPRequest requesttUpdateData(ActionEvent e,
                                             SyncTableInfo pTableInfo) {
        HTTPRequest re = null;
        try {
            // if (pTableInfo.isRequestTable()) {
            // /* Them vao danh sach bang dang cap nhat */
            // SyncQueue.getInstance().addTableUpdate(
            // pTableInfo.getTableName());
            //
            // }
            // if(pTableInfo.getTableName().equalsIgnoreCase(Cause_Line_Hg_CableField.TABLE_NAME)){
            // System.out.println("cuongdk3");
            // }
            ActionEvent actionEvent = new ActionEvent();
            actionEvent.action = pTableInfo.getActionId();
            actionEvent.sender = e.sender;
            actionEvent.isBlockRequest = e.isBlockRequest;
            actionEvent.userData = pTableInfo;
            JSONObject json = new JSONObject();
            json.put(IntentConstants.INTENT_LIST_DATA, pTableInfo.getJsonData());
            json.put("tableName", pTableInfo.getTableName());
            Log.e(TAG, "requesttUpdateData: " +  pTableInfo.getTableName());
//            LogManager.getInstance().writeFile(
//                    "put table_name: " + pTableInfo.getTableName()
//                            + "  lstData: " + pTableInfo.getJsonData());
            if (pTableInfo.getTableName().equalsIgnoreCase(
                    Supv_Constr_DailyField.TABLE_NAME)) {
                re = sendHttpRequest(
                        "serverDataProcess.processReceivedDataDaily", json,
                        actionEvent, HttpAsyncTask.CONNECT_TIMEOUT);
            } else if (pTableInfo.getTableName().equalsIgnoreCase(
                    Login_Log_ConstrField.TABLE_NAME)) {

                String sUserName = "";
                try {
                    PreferenceUtil preUtil = new PreferenceUtil(mContext);
                    sUserName = preUtil
                            .getStringPreference(Constants.LONGIN_NAME_SETTING);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                json.put("loginName", sUserName);
                re = sendHttpRequest("serverDataProcess.insertLoginData", json,
                        actionEvent, HttpAsyncTask.CONNECT_TIMEOUT);
            } else
                re = sendHttpRequest("serverDataProcess.processReceivedData",
                        json, actionEvent, HttpAsyncTask.CONNECT_TIMEOUT);

        } catch (Exception ex) {
            ex.printStackTrace();
            LogManager.getInstance().writeFile(ex.getMessage());
        }
        return re;
    }

    /**
     * ham don nhan update du lieu tu client
     *
     * @param mes
     * @param model
     */
    protected void receiveUpdateDataHandler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        ActionEvent actionEvent = model.getActionEvent();
        SyncTableInfo tableInfo = (SyncTableInfo) actionEvent.userData;
        try {

            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            JSONObject jsonResponseData = jsonresult.getJSONObject("response");

            int errCode = jsonresult.getInt("errorCode");

            if (tableInfo.isRequestTable()) {
                percentLeap = (double) 15 / numberTableUpdate;
                percent = percent + percentLeap;
                Message msg2 = new Message();
                Bundle bundle2 = new Bundle();
                bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
                bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                        + tableInfo.getTableName());
                msg2.setData(bundle2);
                mHandler.sendMessage(msg2);
                tableUpdateNumCurent++;
            }

            // if (tableInfo.getTableName().equals("SUPERVISION_LINE_HANG")) {
            Log.d(TAG, "receiveUpdateDataHandler() called with: mes = [" + mes + "], model = [" + model + "]");
            Log.d(TAG, tableInfo.getTableName() + " " + tableInfo.getColumnKey());
//			LogManager.getInstance().writeFile(
//					"update bang " + tableInfo.getTableName());
//			LogManager.getInstance().writeFile("errorCode " + errCode);
            // }
            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS) {
                if (tableInfo.getTableName().equalsIgnoreCase(Login_Log_ConstrField.TABLE_NAME)) {
                    String[] sColumnPid = new String[]{
                            tableInfo.getColumnKey(), "status"};
                    ArrayList<HashMap<String, String>> listData = SqliteUtil
                            .converJsonToHasMap(jsonResponseData, sColumnPid);
                    Login_Log_ConstrController loginLogController = new Login_Log_ConstrController(
                            mContext);
                    for (HashMap<String, String> map : listData) {
                        // if (map.get("status").equals("true")) {
                        loginLogController
                                .deleteLoginLogConstr(Long.valueOf(map
                                        .get(Login_Log_ConstrField.COLUMN_LOG_ID)));
                        // }
                    }

					/* 2. Kiem tra xem co update tiep bang hien tai khong */
                    if (tableInfo.isRequestNext()) {
                        JSONArray jsonUpdateData = SqlliteSyncModel
                                .getDataJsonFromLogUser(GlobalInfo
                                                .getInstance().getUserId(), tableInfo
                                                .getTableName(), tableInfo
                                                .getAllColumn(), 0,
                                        Constants.NUMBER_MAX_ITEM_SYNC);
                        if (jsonUpdateData.length() == Constants.NUMBER_MAX_ITEM_SYNC) {
                            tableInfo.setRequestNext(true);
                        } else {
                            tableInfo.setRequestNext(false);
                        }
                        if (jsonUpdateData.length() > 0) {
                            tableInfo.setJsonData(jsonUpdateData);
                            tableInfo.setRequestTable(false);
                            SyncModel.getInstance().requesttUpdateData(
                                    model.getActionEvent(), tableInfo);
                        } else {
                            tableUpdateCurent += 1; // tro sang bang tiep theo
                            if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                                syncUpateNextData(actionEvent,
                                        tableUpdateCurent); // dong bo bang tiep
                                // theo
                            }
                        }
                    } else {

                        // dong bo bang khac
                        tableUpdateCurent += 1; // tro sang bang tiep theo
                        if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                            syncUpateNextData(actionEvent, tableUpdateCurent); // dong
                            // bo
                            // bang
                            // tiep
                            // theo
                        }
                    }
                } else {
					/* 1. Cap nhat lai processId va syncstatus cho database */
                    Log.e(TAG, "receiveUpdateDataHandler: " + tableInfo.getTableName() + "\n\t" + jsonResponseData);
                    String[] sColumnPid = new String[]{BaseField.COLUMN_PROCESS_ID,
                            tableInfo.getColumnKey()};
                    ArrayList<HashMap<String, String>> listData = SqliteUtil
                            .converJsonToHasMap(jsonResponseData, sColumnPid);

                    SqlliteSyncModel.updateSyncStatusProcessId(listData, tableInfo);

					/* 2. Kiem tra xem co update tiep bang hien tai khong */
                    if (tableInfo.isRequestNext()) {
                        tableInfo.setRequestTable(false);
                        JSONArray jsonUpdateData = null;
                        if (tableInfo.getTableName().equalsIgnoreCase(
                                Supv_Constr_Attach_FileField.TABLE_NAME)) {
                            jsonUpdateData = SqlliteSyncModel
                                    .getDataJsonSyncFile(GlobalInfo
                                                    .getInstance().getUserId(),
                                            tableInfo.getTableName(), tableInfo
                                                    .getAllColumn(), null, 0,
                                            Constants.NUMBER_MAX_ITEM_SYNC);
                        } else {
                            jsonUpdateData = SqlliteSyncModel.getDataJsonSync(
                                    GlobalInfo.getInstance().getUserId(),
                                    tableInfo.getTableName(),
                                    tableInfo.getAllColumn(), null, 0,
                                    Constants.NUMBER_MAX_ITEM_SYNC);
                        }

                        if (jsonUpdateData.length() == Constants.NUMBER_MAX_ITEM_SYNC) {
                            tableInfo.setRequestNext(true);
                        } else {
                            tableInfo.setRequestNext(false);
                        }
                        if (jsonUpdateData.length() > 0) {
                            tableInfo.setJsonData(jsonUpdateData);
                            tableInfo.setRequestNext(true);
                            SyncModel.getInstance().requesttUpdateData(
                                    model.getActionEvent(), tableInfo);
                            Log.i("update bang---", "vao update data tiep");
                            // this.requesttUpdateData(e, new SyncTableInfo(
                            // Supervision_ConstrField.TABLE_NAME,
                            // Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
                            // ActionEventConstant.REQUEST_UPDATEDATA,
                            // jsonScData,
                            // Supervision_ConstrController.allColumnUpate,
                            // bRequestNext));
                        } else {
                            tableUpdateCurent += 1; // tro sang bang tiep theo
                            if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                                syncUpateNextData(actionEvent,
                                        tableUpdateCurent); // dong bo bang tiep
                                // theo
                            }
                        }
                    } else {
                        // xoa table dong bo tu hang doi
                        // SyncQueue.getInstance().removeTableUpdate(
                        // tableInfo.getTableName());
                        // dong bo bang khac
                        tableUpdateCurent += 1; // tro sang bang tiep theo
                        if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                            syncUpateNextData(actionEvent, tableUpdateCurent); // dong
                            // bo
                            // bang
                            // tiep
                            // theo
                        }
                    }
                }

                // if (SyncQueue.getInstance().checkUpdateFinish()) {
//				Log.i("update bang---", "tableUpdateNumCurent: "
//						+ tableUpdateNumCurent);
//				LogManager.getInstance().writeFile(
//						"numberTableUpdate: " + numberTableUpdate
//								+ "  tableUpdateNumCurent: "
//								+ tableUpdateNumCurent
//								+ " tableInfo.isRequestNext():"
//								+ tableInfo.isRequestNext());
//				Log.i("update bang---", "numberTableUpdate: "
//						+ numberTableUpdate + "  tableUpdateNumCurent: "
//						+ tableUpdateNumCurent + " tableInfo.isRequestNext():"
//						+ tableInfo.isRequestNext());
                if (numberTableUpdate == tableUpdateNumCurent && !tableInfo.isRequestNext()) {
                    percent = 50D;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                    bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                            + tableInfo.getTableName());
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncGetImage(actionEvent);
                }

            } else {
                // SyncQueue.getInstance().removeTableUpdate(
                // tableInfo.getTableName());
                // if (SyncQueue.getInstance().checkUpdateFinish()) {
                Log.i("update bang---", "tableUpdateNumCurent: "
                        + tableUpdateNumCurent);
                LogManager.getInstance().writeFile(
                        "numberTableUpdate: " + numberTableUpdate
                                + "  tableUpdateNumCurent: "
                                + tableUpdateNumCurent
                                + " tableInfo.isRequestNext():"
                                + tableInfo.isRequestNext());
                LogManager.getInstance().writeFile("put data error " + errCode);

                if (numberTableUpdate == tableUpdateNumCurent
                        && !tableInfo.isRequestNext()) {
                    percent = 50D;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                    bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                            + tableInfo.getTableName());
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncGetImage(actionEvent);
                    // model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
                    // model.setCode(ErrorConstants.ERROR_CODE_SUCCESS);
                    // Home_Controller.getInstance().handleModelEvent(model);
                } else {
                    tableUpdateCurent += 1; // tro sang bang tiep theo
                    if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                        syncUpateNextData(actionEvent, tableUpdateCurent); // dong
                        // bo
                        // bang
                        // tiep
                        // theo
                    }
                }

                // model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                // model.setModelCode(ErrorConstants.ERR_SYNC_CONNECTION);
                // Home_Controller.getInstance().handleErrorModelEvent(model);
            }

        } catch (Exception e) {
			/* Su ly khi bi loi */
            e.printStackTrace();
            LogManager.getInstance().writeFile(
                    "get put data exception: " + e.getMessage());
            LogManager.getInstance().writeFile(
                    "update bang---" + "tableUpdateNumCurent: "
                            + tableUpdateNumCurent);
            LogManager.getInstance().writeFile(
                    "numberTableUpdate: " + numberTableUpdate
                            + "  tableUpdateNumCurent: " + tableUpdateNumCurent
                            + " tableInfo.isRequestNext():"
                            + tableInfo.isRequestNext());
            // model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
            // Home_Controller.getInstance().handleErrorModelEvent(model);

            Log.i("update bang---", "tableUpdateNumCurent: "
                    + tableUpdateNumCurent);
            if (numberTableUpdate == tableUpdateNumCurent
                    && !tableInfo.isRequestNext()) {
                percent = 50D;
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                        + tableInfo.getTableName());
                msg.setData(bundle);
                mHandler.sendMessage(msg);

                model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                SyncModel.getInstance().syncGetImage(actionEvent);
            } else {
                tableUpdateCurent += 1; // tro sang bang tiep theo
                if (tableUpdateCurent < SyncTableUpdate.ListTableUpdate.length) {
                    syncUpateNextData(actionEvent, tableUpdateCurent); // dong
                    // bo
                    // bang
                    // tiep
                    // theo
                }
            }
        }
    }

    /**
     * Ham update file dung json va base string 64
     *
     * @param e
     * @param pFilePath
     * @param pAction
     * @param idFile
     * @return
     */
    protected HTTPRequest requestGetImage(ActionEvent e,
                                          Supv_Constr_Attach_FileEntity pFileEntity, int pAction) {
        HTTPRequest re = null;
        try {
            ActionEvent action = new ActionEvent();
            action.sender = e.sender;
            action.action = pAction;
            action.isBlockRequest = true;
            action.userData = pFileEntity;
            JSONObject json = new JSONObject();
            json.put(
                    Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                    String.valueOf(pFileEntity.getSupv_Constr_Attach_file_Id()));
            json.put(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH,
                    String.valueOf(pFileEntity.getFile_Path()));
            re = sendHttpRequest("fileUpload.requestFile", json, action,
                    HttpAsyncTask.CONNECT_TIMEOUT);
            System.out.println("sending -------");
            LogManager.getInstance().writeFile("sending -------");
            // Gan vao hang doi
            SyncQueue.getInstance().addFileDownload(
                    pFileEntity.getSupv_Constr_Attach_file_Id());

        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e("Loi file:", "Co Loi");
            LogManager.getInstance().writeFile("Loi file: " + "Co Loi");
        }
        return re;
    }

    /**
     * ham don nhan lay anh tu server ve du lieu tu client
     *
     * @param mes
     * @param model
     */
    protected void receiveGetImageHandler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        try {
            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            JSONObject jsonResponseData = jsonresult.getJSONObject("response");
            ActionEvent actionEvent = model.getActionEvent();
            int errCode = jsonresult.getInt("errorCode");
            Supv_Constr_Attach_FileEntity pFileEntity = (Supv_Constr_Attach_FileEntity) actionEvent.userData;

            percentLeap = (double) 25 / numberGetImage;
            percent = percent + percentLeap;
            Message msg2 = new Message();
            Bundle bundle2 = new Bundle();
            bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Get Image");
            msg2.setData(bundle2);
            mHandler.sendMessage(msg2);

            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS) {
				/* Lay thong tin file */
                String sRESULT_CODE = jsonResponseData.getString("RESULT_CODE");
                if (sRESULT_CODE.equals("1")) {
                    Log.i("Ghi file",
                            "Ghi file : "
                                    + pFileEntity
                                    .getSupv_Constr_Attach_file_Id());
                    LogManager.getInstance().writeFile(
                            "Ghi file : "
                                    + pFileEntity
                                    .getSupv_Constr_Attach_file_Id());
					/* Ghi file theo duong dan */
                    String sFileData = jsonResponseData.getString("DATA_FILE");
                    boolean bSaveFile = FileManager.saveFileDownload(sFileData,
                            GlobalInfo.getInstance().getFilePath()
                                    + pFileEntity.getFile_Path());
                    if (bSaveFile) {
                        SqlliteSyncModel.updateDownloadFileSuccess(pFileEntity
                                .getSupv_Constr_Attach_file_Id());
                    }
                } else {
                    Log.i("idFile", pFileEntity.getSupv_Constr_Attach_file_Id()
                            + "," + pFileEntity.getEmployeeId());
                    Log.i("Ghi file", "File khong ton tai");
                    LogManager.getInstance().writeFile(
                            "idFile : "
                                    + pFileEntity
                                    .getSupv_Constr_Attach_file_Id()
                                    + "," + pFileEntity.getEmployeeId());
                    LogManager.getInstance().writeFile(
                            "Ghi file : " + "File khong ton tai");
                    SqlliteSyncModel.updateDownloadFileSuccess(pFileEntity
                            .getSupv_Constr_Attach_file_Id());
                }
                SyncQueue.getInstance().removeFileDownload(
                        pFileEntity.getSupv_Constr_Attach_file_Id());
                if (SyncQueue.getInstance().checkFileDownload()) {
                    // System.out.println("check file download");
                    // cuongdk3 add
                    // neu co yeu cau upload anh tiep thi req upload tiep
                    if (pFileEntity.isRequestNext()) {
                        System.gc();

                        List<Supv_Constr_Attach_FileEntity> listDownLoad = new Supv_Constr_Attach_FileController(
                                SyncModel.mContext)
                                .getAttachFileDownLoad(Constants.NUMBER_MAX_ITEM_SYNC_IMG);

                        int iSizeDownLoad = listDownLoad.size();

                        boolean bRequestNext = (iSizeDownLoad == Constants.NUMBER_MAX_ITEM_SYNC_IMG) ? true
                                : false;

                        if (iSizeDownLoad > 0) {
                            // System.out.println("yeu cau request tiep");
                            for (Supv_Constr_Attach_FileEntity itemFileDownLoad : listDownLoad) {
                                itemFileDownLoad.setRequestNext(bRequestNext);
                                this.requestGetImage(
                                        model.getActionEvent(),
                                        itemFileDownLoad,
                                        ActionEventConstant.REQUEST_DOWNLOADIMAGE);
                            }
                        } else {
                            // System.out.println("update anh len");
                            percent = 75D;
                            Message msg = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                                    "Syncing: Get Image");
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);

                            model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                            SyncModel.getInstance()
                                    .syncUpdateImage(actionEvent);

                        }
                    } else {
                        percent = 75D;
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                        bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                                "Syncing: Get Image");
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);

                        // System.out.println("update anh len");
                        model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                        SyncModel.getInstance().syncUpdateImage(actionEvent);

                    }
                    // end

                    // dath1
                    // model.getActionEvent().action =
                    // ActionEventConstant.REQEST_SYNC;
                    // SyncModel.getInstance().syncUpdateImage(actionEvent);
                }

            } else {
                Log.i("Ghi file", "Nhan file khong thanh cong");
                LogManager.getInstance().writeFile(
                        "Ghi file : " + "Nhan file khong thanh cong");
                SyncQueue.getInstance().removeFileUpload(
                        pFileEntity.getSupv_Constr_Attach_file_Id());
                if (SyncQueue.getInstance().checkFileUpload()) {
                    percent = 75D;
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                    bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                            "Syncing: Get Image");
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    SyncModel.getInstance().syncUpdateImage(actionEvent);
                }
                // model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                // Home_Controller.getInstance().handleErrorModelEvent(model);
            }

        } catch (Exception e) {
            Log.i("Ghi file", "Co su co");
            LogManager.getInstance().writeFile(
                    "Ghi file : " + "Co su co, " + e.getMessage());
			/* Su ly khi bi loi */
            model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
            Home_Controller.getInstance().handleErrorModelEvent(model);
        }
    }

    /**
     * Ham update file dung json va base string 64
     *
     * @param e
     * @param pFilePath
     * @param pAction
     * @param idFile
     * @return
     */
    protected HTTPRequest requestUpdateImage(ActionEvent e, int pAction,
                                             Supv_Constr_Attach_FileEntity pFileUpload) {
        HTTPRequest re = null;
        try {
            Log.i("Day file:", "Day file:" + pFileUpload.getFile_Path());
            LogManager.getInstance().writeFile(
                    "Day file: " + pFileUpload.getFile_Path());
            ActionEvent action = new ActionEvent();
            action.sender = e.sender;
            action.action = pAction;
            action.isBlockRequest = true;
            // action.userData = pFileUpload.getSupv_Constr_Attach_file_Id();
            action.userData = pFileUpload;
            Vector<String> data = new Vector<String>();
            data.add(Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID);
            data.add(String.valueOf(pFileUpload.getSupv_Constr_Attach_file_Id()));
            data.add(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH);
            data.add(pFileUpload.getFile_Path());
            // cuongdk3 add
            data.add("fileItem");
            JSONObject json = new JSONObject();
            SimpleDateFormat dateView = new SimpleDateFormat("yyyy-dd-MM");
            JSONArray jsonArrayData = new JSONArray();
            if (pFileUpload != null) {
                json.put(
                        Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                        pFileUpload.getSupv_Constr_Attach_file_Id());
                json.put(Supv_Constr_Attach_FileField.COLUMN_FILE_NAME,
                        pFileUpload.getFile_Name());
                json.put(Supv_Constr_Attach_FileField.COLUMN_FILE_PATH,
                        pFileUpload.getFile_Path());
                json.put(Supv_Constr_Attach_FileField.COLUMN_OBJECT_ID,
                        pFileUpload.getObject_Id());
                json.put(Supv_Constr_Attach_FileField.COLUMN_TABLE_NAME,
                        pFileUpload.getTable_Name());
                json.put(Supv_Constr_Attach_FileField.COLUMN_CREATED_DATE,
                        dateView.format(pFileUpload.getCreated_Date()));
                json.put(Supv_Constr_Attach_FileField.COLUMN_LONGITUDE,
                        pFileUpload.getLongitude());
                json.put(Supv_Constr_Attach_FileField.COLUMN_LATITUDE,
                        pFileUpload.getLatitude());
                json.put(Supv_Constr_Attach_FileField.COLUMN_SYNC_STATUS,
                        pFileUpload.getSync_Status());
                json.put(Supv_Constr_Attach_FileField.COLUMN_IS_ACTIVE,
                        pFileUpload.getIsActive());
                json.put(Supv_Constr_Attach_FileField.COLUMN_EMPLOYEE_ID,
                        pFileUpload.getEmployeeId());
                json.put(Supv_Constr_Attach_FileField.COLUMN_PROCESS_ID,
                        pFileUpload.getProcessId());
                json.put(Supv_Constr_Attach_FileField.COLUMN_SUPERVISION_CONSTR_ID,
                        pFileUpload.getSupervisionConstrId());

                jsonArrayData.put(json);
            }
            data.add(jsonArrayData.toString());
            // end
            // Gan vao hang doi
            SyncQueue.getInstance().addFileUpload(
                    pFileUpload.getSupv_Constr_Attach_file_Id());
            re = httpMultiPartRequestWithDeoceImageFromImagePath(
                    "fileUpload.addImage", data, GlobalInfo.getInstance()
                            .getFilePath() + pFileUpload.getFile_Path(),
                    action, pFileUpload.getFile_Name(), "photo", "image/png");

        } catch (Exception ex) {
            ex.printStackTrace();
            SyncQueue.getInstance().removeFileUpload(
                    pFileUpload.getSupv_Constr_Attach_file_Id());
            SqlliteSyncModel.updateUploadFileSuccess(pFileUpload
                    .getSupv_Constr_Attach_file_Id());
            Log.e("Loi file:", "Loi file:" + pFileUpload.getFile_Path());
            LogManager.getInstance().writeFile(
                    "Loi day file: " + pFileUpload.getFile_Path());
            LogManager.getInstance().writeFile(
                    "Loi day file msg: " + ex.getMessage());

        } catch (Throwable ex1) {
            ex1.printStackTrace();
            SyncQueue.getInstance().removeFileUpload(
                    pFileUpload.getSupv_Constr_Attach_file_Id());
            SqlliteSyncModel.updateUploadFileSuccess(pFileUpload
                    .getSupv_Constr_Attach_file_Id());
            Log.e("Loi file:", "Loi file:" + pFileUpload.getFile_Path());

            LogManager.getInstance().writeFile(
                    "Loi day file: " + pFileUpload.getFile_Path());
            LogManager.getInstance().writeFile(
                    "Loi day file msg: " + ex1.getMessage());
        }
        return re;
    }

    /**
     * ham don nhan update du lieu tu client
     *
     * @param mes
     * @param model
     */
    protected void receiveUpdateImageHandler(HTTPMessage mes, ModelEvent model) {
        JSONObject json;
        try {
            json = new JSONObject(mes.getDataText());
            JSONObject jsonresult = json.getJSONObject("result");
            JSONObject jsonResponseData = jsonresult.getJSONObject("response");
            ActionEvent actionEvent = model.getActionEvent();
            int errCode = jsonresult.getInt("errorCode");
            // long idFile = Long.parseLong(actionEvent.userData.toString());
            Supv_Constr_Attach_FileEntity itemFile = ((Supv_Constr_Attach_FileEntity) actionEvent.userData);
            long idFile = itemFile.getSupv_Constr_Attach_file_Id();

            percentLeap = (double) 25 / numberPutImage;
            Log.i("numberPutImage", "percent = " + percent
                    + ", numberPutImage= " + numberPutImage
                    + ", percentLeap = " + percentLeap);
            percent = percent + percentLeap;
            Message msg2 = new Message();
            Bundle bundle2 = new Bundle();
            bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Put Image");
            msg2.setData(bundle2);
            mHandler.sendMessage(msg2);

            if (errCode == ErrorConstants.ERROR_CODE_SUCCESS) {
                Log.i("Day file:", "Thanh cong day file:" + idFile);
                LogManager.getInstance().writeFile(
                        "Thanh cong day file: " + idFile);
                // cuongdk3 add
                String[] sColumnPid = new String[]{
                        BaseField.COLUMN_PROCESS_ID,
                        Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID};
                ArrayList<HashMap<String, String>> listData = SqliteUtil
                        .converJsonToHasMap(jsonResponseData, sColumnPid);

                SqlliteSyncModel
                        .updateSyncStatusProcessId(
                                listData,
                                new SyncTableInfo(
                                        Supv_Constr_Attach_FileField.TABLE_NAME,
                                        Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID));
                // end

                SqlliteSyncModel.updateUploadFileSuccess(idFile);
                SyncQueue.getInstance().removeFileUpload(idFile);
                if (SyncQueue.getInstance().checkFileUpload()) {
                    // neu co yeu cau upload anh tiep thi req upload tiep
                    if (itemFile.isRequestNext()) {
                        // MemoryUtils.getInstance().getCacheCleanTask()
                        // .execute(MemoryUtils.getInstance().cacheRootPath);
                        List<Supv_Constr_Attach_FileEntity> listUpLoad = new Supv_Constr_Attach_FileController(
                                SyncModel.mContext).getAttachFileUploadTest(0// itemFile.getNumberUpload()+Constants.NUMBER_MAX_ITEM_SYNC_IMG
                                , Constants.NUMBER_MAX_ITEM_SYNC_IMG);

                        int iSizeDownLoad = listUpLoad.size();

                        boolean bRequestNext = (iSizeDownLoad == Constants.NUMBER_MAX_ITEM_SYNC_IMG) ? true
                                : false;

                        if (iSizeDownLoad > 0) {
                            File fileSave = null;
                            int chekcFileUp = 0;
                            for (Supv_Constr_Attach_FileEntity itemFileUpload : listUpLoad) {
                                fileSave = new File(GlobalInfo.getInstance()
                                        .getFilePath()
                                        + itemFileUpload.getFile_Path());
                                if (fileSave.exists() && fileSave.length() > 0) {
                                    itemFileUpload.setRequestNext(bRequestNext);

                                    // itemFileUpload.setNumberUpload(itemFile.getNumberUpload()
                                    // +Constants.NUMBER_MAX_ITEM_SYNC_IMG);
                                    this.requestUpdateImage(
                                            model.getActionEvent(),
                                            ActionEventConstant.REQUEST_UPDATEIMAGE,
                                            itemFileUpload);

                                    chekcFileUp++;
                                }
                            }
                            if (chekcFileUp == 0) {
                                percent = 100D;
                                Message msg = new Message();
                                Bundle bundle = new Bundle();
                                bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                                bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                                        "Syncing: Put Image");
                                msg.setData(bundle);
                                mHandler.sendMessage(msg);

                                model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                                model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
                                Home_Controller.getInstance().handleModelEvent(
                                        model);

                            }
                        } else {
                            percent = 100D;
                            Message msg = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                                    "Syncing: Put Image");
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);

                            model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                            model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
                            Home_Controller.getInstance().handleModelEvent(
                                    model);

                        }
                    } else {
                        percent = 100D;
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                        bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                                "Syncing: Put Image");
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);

                        model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                        model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
                        Home_Controller.getInstance().handleModelEvent(model);

                    }
                }
            } else {
                SqlliteSyncModel.updateUploadFileSuccess(idFile);
                Log.i("Day file:", "khong thanh cong day file:" + idFile);
                LogManager.getInstance().writeFile(
                        "Day file: " + "khong thanh cong day file " + idFile
                                + " ErrorCode = " + errCode);
                SyncQueue.getInstance().removeFileUpload(idFile);
                if (SyncQueue.getInstance().checkFileUpload()) {
                    model.getActionEvent().action = ActionEventConstant.REQEST_SYNC;
                    model.setModelCode(ErrorConstants.ERR_SYNC_CONNECTION);
                    model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                    Home_Controller.getInstance().handleErrorModelEvent(model);
                }
                model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
                Home_Controller.getInstance().handleErrorModelEvent(model);
            }

        } catch (Exception e) {
            Log.e("Loi file:",
                    "co loi xay ra--------------------------------------------------------- "
                            + e.getMessage());
            LogManager
                    .getInstance()
                    .writeFile(
                            "Loi file: "
                                    + "co loi xay ra---------------------------------------------------------");

            LogManager
                    .getInstance()
                    .writeFile(
                            "Loi file msg: "
                                    + "co loi xay ra---------------------------------------------------------"
                                    + e.getMessage());
			/* Su ly khi bi loi */
            model.setCode(ErrorConstants.ERR_SYNC_CONNECTION);
            Home_Controller.getInstance().handleErrorModelEvent(model);
        }
    }

    /**
     * Ham lay key cho cac bang
     */
    public void syncKttsKey(ActionEvent e) {
        Log.e(TAG, "syncKttsKey: ");
        boolean bUpdateKey = false;
        for (String tableName : Ktts_KeyController.ListTableName) {
            Ktts_KeyEntity curItem = Ktts_KeyController.getInstance().getItem(
                    tableName);
			/* Lan dau cai dat curItem.getTableName() trong */
            Log.e(TAG, "syncKttsKey: " + curItem.getTableName() + " . " + tableName);
            if (StringUtil.isNullOrEmpty(curItem.getTableName())) {
                SyncQueue.getInstance().addTableGetKey(tableName);
                curItem.setTableName(tableName);
                curItem.setClientId(GlobalInfo.getInstance().getClientId());
                curItem.setMin(0L);
                curItem.setMax(0L);
                ActionEvent curActionEvent = new ActionEvent();
                curActionEvent.action = ActionEventConstant.REQUEST_KEYKTTS;
                curActionEvent.sender = e.sender;
                curActionEvent.isBlockRequest = true;
                curActionEvent.userData = curItem;
                this.requestKttsKey(curActionEvent);
                bUpdateKey = true;
            } else {
                Long lDistance = curItem.getMax() - curItem.getMin();
                if (lDistance < Constants.DISTANCEKTTSKEY
                        && curItem.getMax() == curItem.getNewMax()) {
                    SyncQueue.getInstance().addTableGetKey(tableName);
                    curItem.setTableName(tableName);
                    curItem.setClientId(GlobalInfo.getInstance().getClientId());
                    curItem.setMin(curItem.getMin());
                    curItem.setMax(curItem.getMax());
                    ActionEvent curActionEvent = new ActionEvent();
                    curActionEvent.action = ActionEventConstant.REQUEST_KEYKTTS;
                    curActionEvent.sender = e.sender;
                    curActionEvent.isBlockRequest = true;
                    curActionEvent.userData = curItem;
                    this.requestKttsKey(curActionEvent);
                    bUpdateKey = true;
                }
            }
        }
		/* Neu khong co khoa can update */
        if (!bUpdateKey) {
            e.action = ActionEventConstant.REQEST_SYNC;
            SyncModel.getInstance().syncGetData(e);
        }
    }

    /**
     * TODO: Lấy key cho các bảng trong DB
     *
     * @param e
     * @param handler : Update ProgressBar
     */
    public void syncKttsKey(ActionEvent e, Handler handler) {
        boolean bUpdateKey = false;
        percent = (double) 0;
        percentLeap = (double) 10 / Ktts_KeyController.ListTableName.length;
        this.mHandler = handler;

        for (String tableName : Ktts_KeyController.ListTableName) {
            Ktts_KeyEntity curItem = Ktts_KeyController.getInstance().getItem(
                    tableName);
			/* Lan dau cai dat curItem.getTableName() trong */
            if (StringUtil.isNullOrEmpty(curItem.getTableName())) {
                SyncQueue.getInstance().addTableGetKey(tableName);
                curItem.setTableName(tableName);
                curItem.setClientId(GlobalInfo.getInstance().getClientId());
                curItem.setMin(0L);
                curItem.setMax(0L);
                ActionEvent curActionEvent = new ActionEvent();
                curActionEvent.action = ActionEventConstant.REQUEST_KEYKTTS;
                curActionEvent.sender = e.sender;
                curActionEvent.isBlockRequest = true;
                curActionEvent.userData = curItem;
                this.requestKttsKey(curActionEvent);
                bUpdateKey = true;

            } else {
                Long lDistance = curItem.getMax() - curItem.getMin();
                System.out.println("Client ID: " + curItem.getClientId());
                if (lDistance < Constants.DISTANCEKTTSKEY
                        && curItem.getMax() == curItem.getNewMax()) {
                    SyncQueue.getInstance().addTableGetKey(tableName);
                    curItem.setTableName(tableName);
                    curItem.setClientId(GlobalInfo.getInstance().getClientId());
                    curItem.setMin(curItem.getMin());
                    curItem.setMax(curItem.getMax());
                    ActionEvent curActionEvent = new ActionEvent();
                    curActionEvent.action = ActionEventConstant.REQUEST_KEYKTTS;
                    curActionEvent.sender = e.sender;
                    curActionEvent.isBlockRequest = true;
                    curActionEvent.userData = curItem;
                    this.requestKttsKey(curActionEvent);
                    bUpdateKey = true;

                }
            }

            percent = percent + percentLeap;
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Get key for sync: "
                    + tableName);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }

        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putDouble(SyncTask.KEY_PERCENT, 10D);
        bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Checking table: Ok!");
        msg.setData(bundle);
        handler.sendMessage(msg);

		/* Neu khong co khoa can update */
        if (!bUpdateKey) {
            e.action = ActionEventConstant.REQEST_SYNC;
            SyncModel.getInstance().syncGetData(e);
        }
    }

	/* Lay du lieu tu tren server ve */

    public void syncGetData(ActionEvent e) {
        Log.d(TAG, "syncGetData() called with: e = [" + e + "]");
        percent = (double) 10;

        percentLeap = (double) 20 / 75;
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Work_Item_TypesField.TABLE_NAME,
                        Cat_Work_Item_TypesField.ITEM_TYPE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Work_Item_TypesControler.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Sub_Work_ItemField.TABLE_NAME,
                        Cat_Sub_Work_ItemField.ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Sub_Work_ItemControler.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Constr_TeamField.TABLE_NAME,
                        Cat_Constr_TeamField.CAT_CONSTR_TEAM_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Constr_TeamController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Cause_Not_WorkField.TABLE_NAME,
                        Cat_Cause_Not_WorkField.CAT_CAUSE_NOT_WORK_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Cause_Not_WorkController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Constr_ObStruction_TypeField.TABLE_NAME,
                        Constr_ObStruction_TypeField.COLUMN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_CNVController.allColumnConstr_Obstruction_Type));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Milestone_ConstrField.TABLE_NAME,
                        Cat_Milestone_ConstrField.CAT_MILESTONE_CONSTR_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Milestone_ConstrControler.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Plan_For_ConstrField.TABLE_NAME,
                        Cat_Plan_For_ConstrField.PLAN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Plan_For_ConstrControler.allColumn));
        Log.e(TAG, "syncGetData: xxxxxxxx" );

        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                ConstrNodeItemsField.TABLE_NAME,
                                ConstrNodeItemsField.CONSTR_NODE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                ConstrNodeController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Work_ItemsField.TABLE_NAME,
                        Work_ItemsField.WORK_ITEM_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Work_ItemsControler.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Work_Items_ValueField.TABLE_NAME,
                        Work_Items_ValueField.WORK_ITEMS_VALUE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Work_Items_ValueControler.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Sub_Work_ItemField.TABLE_NAME,
                        Sub_Work_ItemField.ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Sub_Work_ItemController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Sub_Work_Item_ValueField.TABLE_NAME,
                        Sub_Work_Item_ValueField.ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Sub_Work_Item_ValueController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Constr_Work_LogsField.TABLE_NAME,
                        Constr_Work_LogsField.CONSTR_WORK_LOGS_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Constr_Work_LogsController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Constr_Team_ProgressField.TABLE_NAME,
                        Constr_Team_ProgressField.CONSTR_TEAM_PROGRESS_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Constr_Team_ProgressController.allColumn));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Constr_ObStructionField.TABLE_NAME,
                        Constr_ObStructionField.COLUMN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_CNVController.allColumnConstr_Obstruction));

        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Plan_Constr_DetailField.TABLE_NAME,
                        Plan_Constr_DetailField.PLAN_CONSTR_DETAIL_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Plan_Constr_DetailController.allColumn));


        // CONSTR_CONSTRUCTIONS
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.processServerData",
                new SyncTableInfo(Constr_ConstructionField.TABLE_NAME,
                        Constr_ConstructionField.COLUMN_CONSTRUCT_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Constr_ConstructionController.allColumn));

        // SUPERVISION_CONSTR_REQ
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.processServerData",
                        new SyncTableInfo(
                                Supervision_Constr_REQField.TABLE_NAME,
                                Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Constr_REQController.allColumn));
        // SUPERVISION_CONSTR
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.processServerData",
                new SyncTableInfo(Supervision_ConstrField.TABLE_NAME,
                        Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_ConstrController.allColumn));

        // CAT_CAUSE_CONSTR_UNQUALIFY
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
                                Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Cause_Constr_UnQualifyController.allColumn));

        // CAT_CAUSE_CONSTR_ACCEPTANCE
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
                                Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Cause_Constr_AcceptanceController.allColumn));

        // CAT_SUPERVISION_CONSTR_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Supervision_Constr_WorkField.TABLE_NAME,
                                Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Supervision_Constr_WorkController.allColumn));

        // PRODUCT_COMPANY
        SyncModel.getInstance().requestGetData(
                false,
                e,
                "serverDataProcess.getListProductCompanyTable",
                new SyncTableInfo(Product_CompanyField.TABLE_NAME,
                        Product_CompanyField.COLUMN_COMPANY_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Product_CompanyController.allColumn));

        // CAT_PROGRESS_WORK
        SyncModel.getInstance().requestGetData(
                false,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Progress_WorkField.TABLE_NAME,
                        Cat_Progress_WorkField.COLUMN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Progress_WorkController.allColumn));

        // CAT_SUPV_CONSTR_MEASURE
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Supv_Constr_MeasureField.TABLE_NAME,
                                Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Supv_Constr_MeasureController.allColumn)); //

        // SUPERVISION_LOCATE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Supervision_Locate_Field.TABLE_NAME,
                        Supervision_Locate_Field.COLUMN_SUPERVISION_LOCATE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_LocateController.allColumn));

        // Cat_Employee_Plan
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cat_Employee_Plan_Field.TABLE_NAME,
                        Cat_Employee_Plan_Field.COLUMN_PLAN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Employee_Plan_Controller.allColumn));
        // SUPERVISION_PROGRESS
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_ProgressField.TABLE_NAME,
                                Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_ProgressController.allColumn)); //
        // SUPERVISION_LINE_BACKGROUND
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BackgroundField.TABLE_NAME,
                                Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BackgroundController.allColumn)); //
        // SPECIAL_LOCATION
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Special_LocationField.TABLE_NAME,
                        Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Special_LocationController.allColumn)); //
        // SUPERVISION_LINE_BG_FIBER
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_FiberField.TABLE_NAME,
                                Supervision_Line_BG_FiberField.COLUMN_SUPERVISION_LINE_BG_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_FiberController.allColumn)); //
        // SUPERVISION_LINE_BG_TANK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_TankField.TABLE_NAME,
                                Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_TankController.allColumn)); //
        // SUPERVISION_LINE_BG_CABLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_CableField.TABLE_NAME,
                                Supervision_Line_BG_CableField.COLUMN_SUPERVISION_LINE_BG_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_CableController.allColumn)); //
        // SUPERVISION_LINE_BG_PIPE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_PipeField.TABLE_NAME,
                                Supervision_Line_BG_PipeField.COLUMN_SUPERVISION_LINE_BG_PIPE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_PipeController.allColumn)); //
        // SUPERVISION_LINE_BG_MX
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_MXField.TABLE_NAME,
                                Supervision_Line_BG_MXField.COLUMN_SUPERVISION_LINE_BG_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_MxController.allColumn));
        // CAUSE_LINE_BG_TANK
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_TankField.TABLE_NAME,
                        Cause_Line_BG_TankField.COLUMN_CAUSE_LINE_BG_TANK_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_TankController.allColumn));
        // CAUSE_LINE_BG_SPECIAL
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Line_BG_SpecialField.TABLE_NAME,
                                Cause_Line_BG_SpecialField.COLUMN_CAUSE_LINE_BG_SPECIAL_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Line_BG_SpecialController.allColumn));
        // CAUSE_LINE_BG_PIPE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_PipeField.TABLE_NAME,
                        Cause_Line_BG_PipeField.COLUMN_CAUSE_LINE_BG_PIPE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_PipeController.allColumn));
        // CAUSE_LINE_BG_CABLE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_CableField.TABLE_NAME,
                        Cause_Line_BG_CableField.COLUMN_CAUSE_LINE_BG_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_CableController.allColumn));
        // CAUSE_LINE_BG_MX
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_MXField.TABLE_NAME,
                        Cause_Line_BG_MXField.COLUMN_CAUSE_LINE_BG_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_MXController.allColumn)); // Tuyen Treo //
        // SUPERVISION_LINE_HANG
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_HangField.TABLE_NAME,
                                Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_HangController.allColumn)); //
        // SUPERVISION_LINE_HG_FIBER
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_FiberField.TABLE_NAME,
                                Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_FiberController.allColumn)); //
        // SUPERVISION_LINE_HG_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_PillarField.TABLE_NAME,
                                Supervision_Line_Hg_PillarField.COLUMN_SUPERVISION_LINE_HG_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_PillarController.allColumn)); //
        // SUPERVISION_LINE_HG_MX
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_MxField.TABLE_NAME,
                                Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_MxController.allColumn)); //
        // SUPERVISION_LINE_HG_CABLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_CableField.TABLE_NAME,
                                Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_CableController.allColumn)); //
        // CAUSE_LINE_HG_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Line_Hg_PillarField.TABLE_NAME,
                                Cause_Line_Hg_PillarField.COLUMN_CAUSE_LINE_HG_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Line_Hg_PillarController.allColumn));
        // CAUSE_LINE_HG_MX
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_Hg_MxField.TABLE_NAME,
                        Cause_Line_Hg_MxField.COLUMN_CAUSE_LINE_HG_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_Hg_MxController.allColumn));
        // CAUSE_LINE_HG_CABLE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_Hg_CableField.TABLE_NAME,
                        Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_Hg_CableController.allColumn));
        // SUPERVISION_SUB
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Sub_Field.TABLE_NAME,
                                Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Sub_Controller.allColumn));
        // SUPERVISION_BRCD
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Supervision_BRCD_Field.TABLE_NAME,
                        Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_BRCD_Controller.allColumn));
        // SUPERVISION_BRCD_Drop_Design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
                                Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Drop_Design_Controller.allColumn));
        // SUPERVISION_BRCD_Drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Drop_Field.TABLE_NAME,
                                Supervision_BRCD_Drop_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Drop_Controller.allColumn));
        // SUPERVISION_BRCD_giamsat_drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_DropGo_Controller.allColumn));
        // SUPERVISION_BRCD_giamsat_keocapnhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_Kcn_Controller.allColumn));
        // SUPERVISION_BRCD_giamsat_keocaptruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_Kct_Controller.allColumn));
        // SUPERVISION_BRCD_keocapnhanh_design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
                                Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kcn_Design_Controller.allColumn));
        // SUPERVISION_BRCD_keocapnhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kcn_Field.TABLE_NAME,
                                Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kcn_Controller.allColumn));
        // SUPERVISION_BRCD_keocaptruc_design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kct_design_Field.TABLE_NAME,
                                Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kct_design_Controller.allColumn));
        // SUPERVISION_BRCD_keocaptruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kct_Field.TABLE_NAME,
                                Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kct_Controller.allColumn));
        // SUPERVISION_BRCD_Mangtruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_MXField.TABLE_NAME,
                                Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_MxController.allColumn));
        // SUPERVISION_BRCD_tunhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Tn_Field.TABLE_NAME,
                                Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Tn_Controller.allColumn));
        // SUPERVISION_BRCD
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Ttb_Field.TABLE_NAME,
                                Supervision_BRCD_Ttb_Field.COLUMN_SUPERVISION_BRANCH_BOX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Ttb_Controller.allColumn));
        // CAUSE_Sub
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Sub_Field.TABLE_NAME,
                        Cause_Brcd_Sub_Field.COLUMN_CAUSE_SUBHEADEND_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Sub_Controller.allColumn));
        // CAUSE_BRCD_Box
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Box_Field.TABLE_NAME,
                        Cause_Brcd_Box_Field.COLUMN_CAUSE_BRANCH_BOX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Box_Controller.allColumn));
        // CAUSE_brcd_drop
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Drop_Field.TABLE_NAME,
                        Cause_Brcd_Drop_Field.COLUMN_CAUSE_DROP_FIBER_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Drop_Controller.allColumn));
        // CAUSE_Brcd_kcn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Brcd_Kcn_Field.TABLE_NAME,
                                Cause_Brcd_Kcn_Field.COLUMN_CAUSE_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Brcd_Kcn_Controller.allColumn));
        // CAUSE_Brcd_kct
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Kct_Field.TABLE_NAME,
                        Cause_Brcd_Kct_Field.COLUMN_CAUSE_TRUNK_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Kct_Controller.allColumn));
        // CAUSE_Brcd_mx
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Mx_Field.TABLE_NAME,
                        Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Mx_Controller.allColumn));
        // CAUSE_brcd_tn
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Tn_Field.TABLE_NAME,
                        Cause_Brcd_Tn_Field.COLUMN_CAUSE_BRANCH_CABINET_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Tn_Controller.allColumn));
        // ACCEPTANCE_Sub
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Sub_Field.TABLE_NAME,
                                Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Sub_Controller.allColumn));
        // ACCEPTANCE_brcd_box
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Box_Field.TABLE_NAME,
                                Acceptance_Brcd_Box_Field.COLUMN_ACCEPTANCE_BRANCH_BOX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Box_Controller.allColumn));
        // ACCEPTANCE_brcd_drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Drop_Field.TABLE_NAME,
                                Acceptance_Brcd_Drop_Field.COLUMN_ACCEPTANCE_DROP_FIBRE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Drop_Controller.allColumn));
        // ACCEPTANCE_brcd_kcn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Kcn_Field.TABLE_NAME,
                                Acceptance_Brcd_Kcn_Field.COLUMN_ACCEPTANCE_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Kcn_Controller.allColumn));
        // ACCEPTANCE_brcd_kct
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Kct_Field.TABLE_NAME,
                                Acceptance_Brcd_Kct_Field.COLUMN_ACCEPTANCE_TRUNK_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Kct_Controller.allColumn));
        // ACCEPTANCE_brcd_mx
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Acceptance_Brcd_Mx_Field.TABLE_NAME,
                        Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Acceptance_Brcd_Mx_Controller.allColumn));
        // ACCEPTANCE_brcd_tn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Tn_Field.TABLE_NAME,
                                Acceptance_Brcd_Tn_Field.COLUMN_ACCEPTANCE_BRANCH_CABINET_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Tn_Controller.allColumn));
        // SUPERVISION_BTS
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Supervision_BtsField.TABLE_NAME,
                        Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_BtsController.allColumn));
        // SUPERVISION_BTS_EQUIP
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_EquipField.TABLE_NAME,
                                Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_EquipController.allColumn)); //
        // SUPERVISION_BTS_MEASURE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_MeasureField.TABLE_NAME,
                                Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_MeasureController.allColumn)); //
        // SUPERVISION_BTS_POWER_POLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Power_PoleField.TABLE_NAME,
                                Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Power_PoleController.allColumn));

        // SUPERVISION_BTS_PILLAR_ANTEN
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Pillar_AntenField.TABLE_NAME,
                                Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Pillar_AntenController.allColumn)); //
        // SUPERVISION_BTS_CAT_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Cat_WorkField.TABLE_NAME,
                                Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Cat_WorkController.allColumn)); //
        // CAUSE_BTS_PILLAR_ANTEN
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Bts_Pillar_AntenField.TABLE_NAME,
                                Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Bts_Pillar_AntenController.allColumn));

        // CAUSE_BTS_CAT_WORK
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Bts_Cat_WorkField.TABLE_NAME,
                        Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Bts_Cat_WorkController.allColumn));
        // CAUSE_BTS_POWER_POLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Bts_Power_PoleField.TABLE_NAME,
                                Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Bts_Power_PoleController.allColumn));

        // ACCEPTANCE_LINE_BG_SPECIAL
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_SpecialField.TABLE_NAME,
                                Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_SpecialController.allColumn));

        // ACCEPTANCE_LINE_BG_TANK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_TankField.TABLE_NAME,
                                Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_TankController.allColumn));

        // ACCEPTANCE_LINE_BG_PIPE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_PipeField.TABLE_NAME,
                                Acceptance_Line_Bg_PipeField.COLUMN_ACCEPTANCE_LINE_BG_PIPE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_PipeController.allColumn));

        // ACCEPTANCE_BTS_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Bts_PillarField.TABLE_NAME,
                                Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Bts_PillarController.allColumn));

        // ACCEPTANCE_BTS_CAT_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                                Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Bts_Cat_WorkController.allColumn));

        // SUPERVISION_MEASURE_CONSTR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Measure_ConstrField.TABLE_NAME,
                                Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Measure_ConstrController.allColumn));

        // MEASUREMENT_DETAIL_CONSTR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Measurement_Detail_ConstrField.TABLE_NAME,
                                Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Measurement_Detail_ConstrController.allColumn));
        // ---HungTN add new 25/08/2016
        // CONSTR_PROGRESS_CONSTRUCT
//		SyncModel
//		.getInstance()
//		.requestGetData(
//				true,
//				e,
//				"serverDataProcess.getClientData",
//				new SyncTableInfo(
//						Supervision_CNDTCField.TABLE_NAME,
//						Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
//						ActionEventConstant.REQUEST_GETDATA,
//						Supervision_CNDTCController.allColumnConstrProgressContruct));
//
//
//				// CAT_CONSTRUCT
//				SyncModel.getInstance().requestGetData(
//						false,
//						e,
//						"serverDataProcess.getCategoryTable",
//						new SyncTableInfo(Cat_Construct_Field.TABLE_NAME,
//								Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNDTCController.allColumnCatContruct));
//
//
//				// CONSTR_PROGRESS
//				SyncModel.getInstance().requestGetData(
//						true,
//						e,
//						"serverDataProcess.getClientData",
//						new SyncTableInfo(Constr_Progress_Field.TABLE_NAME,
//								Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNDTCController.allColumnConstrProgress));
//
//		// CONSTR_OBSTRUCTION_TYPE
//		SyncModel
//				.getInstance()
//				.requestGetData(
//						false,
//						e,
//						"serverDataProcess.getCategoryTable",
//						new SyncTableInfo(
//								Constr_ObStruction_TypeField.TABLE_NAME,
//								Constr_ObStruction_TypeField.COLUMN_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNVController.allColumnConstr_Obstruction_Type));
//
//		// CONSTR_OBSTRUCTION
//		SyncModel.getInstance().requestGetData(
//				true,
//				e,
//				"serverDataProcess.getClientData",
//				new SyncTableInfo(Constr_ObStructionField.TABLE_NAME,
//						Constr_ObStructionField.COLUMN_ID,
//						ActionEventConstant.REQUEST_GETDATA,
//						Supervision_CNVController.allColumnConstr_Obstruction));
//
//		// ---
//		// SUPV_CONSTR_ATTACH_FILE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supv_Constr_Attach_FileField.TABLE_NAME,
                                Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supv_Constr_Attach_FileController.allColumn));


//		 SyncQueue.getInstance().checkGetFinish();
//		 syncUpateData(e);
    }

    /**
     * TODO: Get Data from server and Update Progress Bar
     *
     * @param e       : input Data
     * @param handler : Update Progress Bar
     */
    public void syncGetData(ActionEvent e, Handler handler) {

        percent = (double) 10;

        percentLeap = (double) 20 / 75;

        percent = percent + percentLeap;
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putDouble(SyncTask.KEY_PERCENT, percent);
        bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                + Constr_ConstructionField.TABLE_NAME);
        msg.setData(bundle);
        handler.sendMessage(msg);

        // CONSTR_CONSTRUCTIONS
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.processServerData",
                new SyncTableInfo(Constr_ConstructionField.TABLE_NAME,
                        Constr_ConstructionField.COLUMN_CONSTRUCT_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Constr_ConstructionController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Constr_ConstructionField.TABLE_NAME,
                Supervision_Constr_REQField.TABLE_NAME);

        // SUPERVISION_CONSTR_REQ
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.processServerData",
                        new SyncTableInfo(
                                Supervision_Constr_REQField.TABLE_NAME,
                                Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Constr_REQController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Constr_REQField.TABLE_NAME,
                Supervision_ConstrField.TABLE_NAME);

        // SUPERVISION_CONSTR
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.processServerData",
                new SyncTableInfo(Supervision_ConstrField.TABLE_NAME,
                        Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_ConstrController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_ConstrField.TABLE_NAME,
                Cat_Cause_Constr_UnQualifyField.TABLE_NAME);

        // CAT_CAUSE_CONSTR_UNQUALIFY
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
                                Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Cause_Constr_UnQualifyController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cat_Cause_Constr_UnQualifyField.TABLE_NAME,
                Cat_Cause_Constr_AcceptanceField.TABLE_NAME);

        // CAT_CAUSE_CONSTR_ACCEPTANCE
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
                                Cat_Cause_Constr_AcceptanceField.COLUMN_CAT_CAUSE_CONSTR_ACCEPTANCE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Cause_Constr_AcceptanceController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cat_Cause_Constr_AcceptanceField.TABLE_NAME,
                Cat_Supervision_Constr_WorkField.TABLE_NAME);

        // CAT_SUPERVISION_CONSTR_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Supervision_Constr_WorkField.TABLE_NAME,
                                Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Supervision_Constr_WorkController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cat_Supervision_Constr_WorkField.TABLE_NAME,
                Product_CompanyField.TABLE_NAME);

        // PRODUCT_COMPANY
        SyncModel.getInstance().requestGetData(
                false,
                e,
                "serverDataProcess.getListProductCompanyTable",
                new SyncTableInfo(Product_CompanyField.TABLE_NAME,
                        Product_CompanyField.COLUMN_COMPANY_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Product_CompanyController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Product_CompanyField.TABLE_NAME,
                Cat_Progress_WorkField.TABLE_NAME);

        // CAT_PROGRESS_WORK
        SyncModel.getInstance().requestGetData(
                false,
                e,
                "serverDataProcess.getCategoryTable",
                new SyncTableInfo(Cat_Progress_WorkField.TABLE_NAME,
                        Cat_Progress_WorkField.COLUMN_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cat_Progress_WorkController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cat_Progress_WorkField.TABLE_NAME,
                Cat_Supv_Constr_MeasureField.TABLE_NAME);

        // CAT_SUPV_CONSTR_MEASURE
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getCategoryTable",
                        new SyncTableInfo(
                                Cat_Supv_Constr_MeasureField.TABLE_NAME,
                                Cat_Supv_Constr_MeasureField.COLUMN_CAT_SUPV_CONSTR_MEASURE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cat_Supv_Constr_MeasureController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cat_Supv_Constr_MeasureField.TABLE_NAME,
                Supervision_ProgressField.TABLE_NAME);

        // SUPERVISION_PROGRESS
        SyncModel
                .getInstance()
                .requestGetData(
                        false,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_ProgressField.TABLE_NAME,
                                Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_ProgressController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_ProgressField.TABLE_NAME,
                Supervision_Line_BackgroundField.TABLE_NAME);

        // SUPERVISION_LINE_BACKGROUND
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BackgroundField.TABLE_NAME,
                                Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BackgroundController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BackgroundField.TABLE_NAME,
                Special_LocationField.TABLE_NAME);

        // SPECIAL_LOCATION
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Special_LocationField.TABLE_NAME,
                        Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Special_LocationController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Special_LocationField.TABLE_NAME,
                Supervision_Line_BG_FiberField.TABLE_NAME);

        // SUPERVISION_LINE_BG_FIBER
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_FiberField.TABLE_NAME,
                                Supervision_Line_BG_FiberField.COLUMN_SUPERVISION_LINE_BG_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_FiberController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BG_FiberField.TABLE_NAME,
                Supervision_Line_BG_TankField.TABLE_NAME);

        // SUPERVISION_LINE_BG_TANK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_TankField.TABLE_NAME,
                                Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_TankController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BG_TankField.TABLE_NAME,
                Supervision_Line_BG_CableField.TABLE_NAME);

        // SUPERVISION_LINE_BG_CABLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_CableField.TABLE_NAME,
                                Supervision_Line_BG_CableField.COLUMN_SUPERVISION_LINE_BG_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_CableController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BG_CableField.TABLE_NAME,
                Supervision_Line_BG_PipeField.TABLE_NAME);

        // SUPERVISION_LINE_BG_PIPE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_PipeField.TABLE_NAME,
                                Supervision_Line_BG_PipeField.COLUMN_SUPERVISION_LINE_BG_PIPE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_PipeController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BG_PipeField.TABLE_NAME,
                Supervision_Line_BG_MXField.TABLE_NAME);

        // SUPERVISION_LINE_BG_MX
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_BG_MXField.TABLE_NAME,
                                Supervision_Line_BG_MXField.COLUMN_SUPERVISION_LINE_BG_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_BG_MxController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_BG_MXField.TABLE_NAME,
                Cause_Line_BG_TankField.TABLE_NAME);

        // CAUSE_LINE_BG_TANK
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_TankField.TABLE_NAME,
                        Cause_Line_BG_TankField.COLUMN_CAUSE_LINE_BG_TANK_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_TankController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_BG_TankField.TABLE_NAME,
                Cause_Line_BG_SpecialField.TABLE_NAME);

        // CAUSE_LINE_BG_SPECIAL
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Line_BG_SpecialField.TABLE_NAME,
                                Cause_Line_BG_SpecialField.COLUMN_CAUSE_LINE_BG_SPECIAL_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Line_BG_SpecialController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_BG_SpecialField.TABLE_NAME,
                Cause_Line_BG_PipeField.TABLE_NAME);

        // CAUSE_LINE_BG_PIPE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_PipeField.TABLE_NAME,
                        Cause_Line_BG_PipeField.COLUMN_CAUSE_LINE_BG_PIPE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_PipeController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_BG_PipeField.TABLE_NAME,
                Cause_Line_BG_CableField.TABLE_NAME);

        // CAUSE_LINE_BG_CABLE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_CableField.TABLE_NAME,
                        Cause_Line_BG_CableField.COLUMN_CAUSE_LINE_BG_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_CableController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_BG_CableField.TABLE_NAME,
                Cause_Line_BG_MXField.TABLE_NAME);

        // CAUSE_LINE_BG_MX
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_BG_MXField.TABLE_NAME,
                        Cause_Line_BG_MXField.COLUMN_CAUSE_LINE_BG_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_BG_MXController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_BG_MXField.TABLE_NAME,
                Supervision_Line_HangField.TABLE_NAME);

        // SUPERVISION_LINE_HANG
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_HangField.TABLE_NAME,
                                Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_HangController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_HangField.TABLE_NAME,
                Supervision_Line_Hg_FiberField.TABLE_NAME);

        // SUPERVISION_LINE_HG_FIBER
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_FiberField.TABLE_NAME,
                                Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_FiberController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_Hg_FiberField.TABLE_NAME,
                Supervision_Line_Hg_PillarField.TABLE_NAME);

        // SUPERVISION_LINE_HG_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_PillarField.TABLE_NAME,
                                Supervision_Line_Hg_PillarField.COLUMN_SUPERVISION_LINE_HG_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_PillarController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_Hg_PillarField.TABLE_NAME,
                Supervision_Line_Hg_MxField.TABLE_NAME);

        // SUPERVISION_LINE_HG_MX
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_MxField.TABLE_NAME,
                                Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_MxController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_Hg_MxField.TABLE_NAME,
                Supervision_Line_Hg_CableField.TABLE_NAME);

        // SUPERVISION_LINE_HG_CABLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Line_Hg_CableField.TABLE_NAME,
                                Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Line_Hg_CableController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Line_Hg_CableField.TABLE_NAME,
                Cause_Line_Hg_PillarField.TABLE_NAME);

        // CAUSE_LINE_HG_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Line_Hg_PillarField.TABLE_NAME,
                                Cause_Line_Hg_PillarField.COLUMN_CAUSE_LINE_HG_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Line_Hg_PillarController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_Hg_PillarField.TABLE_NAME,
                Cause_Line_Hg_MxField.TABLE_NAME);

        // CAUSE_LINE_HG_MX
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_Hg_MxField.TABLE_NAME,
                        Cause_Line_Hg_MxField.COLUMN_CAUSE_LINE_HG_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_Hg_MxController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_Hg_MxField.TABLE_NAME,
                Cause_Line_Hg_CableField.TABLE_NAME);

        // CAUSE_LINE_HG_CABLE
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Line_Hg_CableField.TABLE_NAME,
                        Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Line_Hg_CableController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Line_Hg_CableField.TABLE_NAME,
                Supervision_BRCD_Sub_Field.TABLE_NAME);

        // SUPERVISION_SUB
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Sub_Field.TABLE_NAME,
                                Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Sub_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Sub_Field.TABLE_NAME,
                Supervision_BRCD_Field.TABLE_NAME);

        // SUPERVISION_BRCD
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Supervision_BRCD_Field.TABLE_NAME,
                        Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_BRCD_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Field.TABLE_NAME,
                Supervision_BRCD_Drop_Design_Field.TABLE_NAME);

        // SUPERVISION_BRCD_Drop_Design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
                                Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Drop_Design_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
                Supervision_BRCD_Drop_Field.TABLE_NAME);

        // SUPERVISION_BRCD_Drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Drop_Field.TABLE_NAME,
                                Supervision_BRCD_Drop_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Drop_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Drop_Field.TABLE_NAME,
                Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME);

        // SUPERVISION_BRCD_giamsat_drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_DropGo_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
                Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME);

        // SUPERVISION_BRCD_giamsat_keocapnhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_Kcn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
                Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME);

        // SUPERVISION_BRCD_giamsat_keocaptruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
                                Supervision_BRCD_GiamSat_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_GiamSat_Kct_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
                Supervision_BRCD_Kcn_Design_Field.TABLE_NAME);

        // SUPERVISION_BRCD_keocapnhanh_design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
                                Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kcn_Design_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
                Supervision_BRCD_Kcn_Field.TABLE_NAME);

        // SUPERVISION_BRCD_keocapnhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kcn_Field.TABLE_NAME,
                                Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kcn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Kcn_Field.TABLE_NAME,
                Supervision_BRCD_Kct_design_Field.TABLE_NAME);

        // SUPERVISION_BRCD_keocaptruc_design
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kct_design_Field.TABLE_NAME,
                                Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kct_design_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Kct_design_Field.TABLE_NAME,
                Supervision_BRCD_Kct_Field.TABLE_NAME);

        // SUPERVISION_BRCD_keocaptruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Kct_Field.TABLE_NAME,
                                Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Kct_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Kct_Field.TABLE_NAME,
                Supervision_BRCD_MXField.TABLE_NAME);

        // SUPERVISION_BRCD_Mangtruc
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_MXField.TABLE_NAME,
                                Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_MxController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_MXField.TABLE_NAME,
                Supervision_BRCD_Tn_Field.TABLE_NAME);

        // SUPERVISION_BRCD_tunhanh
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Tn_Field.TABLE_NAME,
                                Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Tn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Tn_Field.TABLE_NAME,
                Supervision_BRCD_Ttb_Field.TABLE_NAME);

        // SUPERVISION_BRCD
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_BRCD_Ttb_Field.TABLE_NAME,
                                Supervision_BRCD_Ttb_Field.COLUMN_SUPERVISION_BRANCH_BOX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_BRCD_Ttb_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BRCD_Ttb_Field.TABLE_NAME,
                Cause_Brcd_Sub_Field.TABLE_NAME);

        // CAUSE_Sub
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Sub_Field.TABLE_NAME,
                        Cause_Brcd_Sub_Field.COLUMN_CAUSE_SUBHEADEND_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Sub_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Sub_Field.TABLE_NAME,
                Cause_Brcd_Box_Field.TABLE_NAME);

        // CAUSE_BRCD_Box
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Box_Field.TABLE_NAME,
                        Cause_Brcd_Box_Field.COLUMN_CAUSE_BRANCH_BOX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Box_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Box_Field.TABLE_NAME,
                Cause_Brcd_Drop_Field.TABLE_NAME);

        // CAUSE_brcd_drop
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Drop_Field.TABLE_NAME,
                        Cause_Brcd_Drop_Field.COLUMN_CAUSE_DROP_FIBER_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Drop_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Drop_Field.TABLE_NAME,
                Cause_Brcd_Kcn_Field.TABLE_NAME);

        // CAUSE_Brcd_kcn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Brcd_Kcn_Field.TABLE_NAME,
                                Cause_Brcd_Kcn_Field.COLUMN_CAUSE_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Brcd_Kcn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Kcn_Field.TABLE_NAME,
                Cause_Brcd_Kct_Field.TABLE_NAME);

        // CAUSE_Brcd_kct
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Kct_Field.TABLE_NAME,
                        Cause_Brcd_Kct_Field.COLUMN_CAUSE_TRUNK_CABLE_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Kct_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Kct_Field.TABLE_NAME, Cause_Brcd_Mx_Field.TABLE_NAME);

        // CAUSE_Brcd_mx
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Mx_Field.TABLE_NAME,
                        Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Mx_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Mx_Field.TABLE_NAME, Cause_Brcd_Tn_Field.TABLE_NAME);

        // CAUSE_brcd_tn
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Brcd_Tn_Field.TABLE_NAME,
                        Cause_Brcd_Tn_Field.COLUMN_CAUSE_BRANCH_CABINET_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Brcd_Tn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Brcd_Tn_Field.TABLE_NAME,
                Acceptance_Brcd_Sub_Field.TABLE_NAME);

        // ACCEPTANCE_Sub
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Sub_Field.TABLE_NAME,
                                Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Sub_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Sub_Field.TABLE_NAME,
                Acceptance_Brcd_Box_Field.TABLE_NAME);

        // ACCEPTANCE_brcd_box
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Box_Field.TABLE_NAME,
                                Acceptance_Brcd_Box_Field.COLUMN_ACCEPTANCE_BRANCH_BOX_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Box_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Box_Field.TABLE_NAME,
                Acceptance_Brcd_Drop_Field.TABLE_NAME);

        // ACCEPTANCE_brcd_drop
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Drop_Field.TABLE_NAME,
                                Acceptance_Brcd_Drop_Field.COLUMN_ACCEPTANCE_DROP_FIBRE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Drop_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Drop_Field.TABLE_NAME,
                Acceptance_Brcd_Kcn_Field.TABLE_NAME);

        // ACCEPTANCE_brcd_kcn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Kcn_Field.TABLE_NAME,
                                Acceptance_Brcd_Kcn_Field.COLUMN_ACCEPTANCE_BRANCH_CABLE_FIBER_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Kcn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Kcn_Field.TABLE_NAME,
                Acceptance_Brcd_Kct_Field.TABLE_NAME);

        //

        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Kct_Field.TABLE_NAME,
                                Acceptance_Brcd_Kct_Field.COLUMN_ACCEPTANCE_TRUNK_CABLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Kct_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Kct_Field.TABLE_NAME,
                Acceptance_Brcd_Mx_Field.TABLE_NAME);

        // ACCEPTANCE_brcd_mx
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Acceptance_Brcd_Mx_Field.TABLE_NAME,
                        Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Acceptance_Brcd_Mx_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Mx_Field.TABLE_NAME,
                Acceptance_Brcd_Tn_Field.TABLE_NAME);

        // ACCEPTANCE_brcd_tn
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Brcd_Tn_Field.TABLE_NAME,
                                Acceptance_Brcd_Tn_Field.COLUMN_ACCEPTANCE_BRANCH_CABINET_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Brcd_Tn_Controller.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Brcd_Tn_Field.TABLE_NAME,
                Supervision_BtsField.TABLE_NAME);

        // SUPERVISION_BTS
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Supervision_BtsField.TABLE_NAME,
                        Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Supervision_BtsController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_BtsField.TABLE_NAME,
                Supervision_Bts_EquipField.TABLE_NAME);

        // SUPERVISION_BTS_EQUIP
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_EquipField.TABLE_NAME,
                                Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_EquipController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Bts_EquipField.TABLE_NAME,
                Supervision_Bts_MeasureField.TABLE_NAME);

        // SUPERVISION_BTS_MEASURE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_MeasureField.TABLE_NAME,
                                Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_MeasureController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Bts_MeasureField.TABLE_NAME,
                Supervision_Bts_Power_PoleField.TABLE_NAME);

        // SUPERVISION_BTS_POWER_POLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Power_PoleField.TABLE_NAME,
                                Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Power_PoleController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Bts_Power_PoleField.TABLE_NAME,
                Supervision_Bts_Pillar_AntenField.TABLE_NAME);

        // SUPERVISION_BTS_PILLAR_ANTEN
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Pillar_AntenField.TABLE_NAME,
                                Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Pillar_AntenController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Bts_Pillar_AntenField.TABLE_NAME,
                Supervision_Bts_Cat_WorkField.TABLE_NAME);

        // SUPERVISION_BTS_CAT_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Bts_Cat_WorkField.TABLE_NAME,
                                Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Bts_Cat_WorkController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Bts_Cat_WorkField.TABLE_NAME,
                Cause_Bts_Pillar_AntenField.TABLE_NAME);

        // CAUSE_BTS_PILLAR_ANTEN
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Bts_Pillar_AntenField.TABLE_NAME,
                                Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Bts_Pillar_AntenController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Bts_Pillar_AntenField.TABLE_NAME,
                Cause_Bts_Cat_WorkField.TABLE_NAME);

        // CAUSE_BTS_CAT_WORK
        SyncModel.getInstance().requestGetData(
                true,
                e,
                "serverDataProcess.getClientData",
                new SyncTableInfo(Cause_Bts_Cat_WorkField.TABLE_NAME,
                        Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
                        ActionEventConstant.REQUEST_GETDATA,
                        Cause_Bts_Cat_WorkController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Bts_Cat_WorkField.TABLE_NAME,
                Cause_Bts_Power_PoleField.TABLE_NAME);

        // CAUSE_BTS_POWER_POLE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Cause_Bts_Power_PoleField.TABLE_NAME,
                                Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Cause_Bts_Power_PoleController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Cause_Bts_Cat_WorkField.TABLE_NAME,
                Acceptance_Line_Bg_SpecialField.TABLE_NAME);

        // ACCEPTANCE_LINE_BG_SPECIAL
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_SpecialField.TABLE_NAME,
                                Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_SpecialController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Line_Bg_SpecialField.TABLE_NAME,
                Acceptance_Line_Bg_TankField.TABLE_NAME);

        // ACCEPTANCE_LINE_BG_TANK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_TankField.TABLE_NAME,
                                Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_TankController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Line_Bg_TankField.TABLE_NAME,
                Acceptance_Line_Bg_PipeField.TABLE_NAME);

        // ACCEPTANCE_LINE_BG_PIPE
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Line_Bg_PipeField.TABLE_NAME,
                                Acceptance_Line_Bg_PipeField.COLUMN_ACCEPTANCE_LINE_BG_PIPE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Line_Bg_PipeController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Line_Bg_PipeField.TABLE_NAME,
                Acceptance_Bts_PillarField.TABLE_NAME);

        // ACCEPTANCE_BTS_PILLAR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Bts_PillarField.TABLE_NAME,
                                Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Bts_PillarController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Bts_PillarField.TABLE_NAME,
                Acceptance_Bts_Cat_WorkField.TABLE_NAME);

        // ACCEPTANCE_BTS_CAT_WORK
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                                Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Acceptance_Bts_Cat_WorkController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Acceptance_Bts_Cat_WorkField.TABLE_NAME,
                Supervision_Measure_ConstrField.TABLE_NAME);

        // SUPERVISION_MEASURE_CONSTR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supervision_Measure_ConstrField.TABLE_NAME,
                                Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supervision_Measure_ConstrController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Supervision_Measure_ConstrField.TABLE_NAME,
                Measurement_Detail_ConstrField.TABLE_NAME);

        // MEASUREMENT_DETAIL_CONSTR
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Measurement_Detail_ConstrField.TABLE_NAME,
                                Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Measurement_Detail_ConstrController.allColumn));

        percent = percent + percentLeap;

        updateProgressBarDoInGetData(handler, percent,
                Measurement_Detail_ConstrField.TABLE_NAME,
                Supv_Constr_Attach_FileField.TABLE_NAME);
        //Supervision_CNDTCField.TABLE_NAME);

        // CONSTR_PROGRESS_CONSTRUCT
        // ---HungTN add new 25/08/2016
//		SyncModel
//				.getInstance()
//				.requestGetData(
//						true,
//						e,
//						"serverDataProcess.getClientData",
//						new SyncTableInfo(
//								Supervision_CNDTCField.TABLE_NAME,
//								Supervision_CNDTCField.COLUMN_CONSTR_PROGRESS_CONSTRUCT_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNDTCController.allColumnConstrProgressContruct));
//
//		percent = percent + percentLeap;
//
//		updateProgressBarDoInGetData(handler, percent,
//				Supervision_CNDTCField.TABLE_NAME,
//				Cat_Construct_Field.TABLE_NAME);
//
//		// CAT_CONSTRUCT
//		SyncModel.getInstance().requestGetData(
//				false,
//				e,
//				"serverDataProcess.getCategoryTable",
//				new SyncTableInfo(Cat_Construct_Field.TABLE_NAME,
//						Cat_Construct_Field.COLUMN_CAT_CONSTRUCT_ID,
//						ActionEventConstant.REQUEST_GETDATA,
//						Supervision_CNDTCController.allColumnCatContruct));
//
//		percent = percent + percentLeap;
//
//		updateProgressBarDoInGetData(handler, percent,
//				Cat_Construct_Field.TABLE_NAME,
//				Constr_Progress_Field.TABLE_NAME);
//
//		// CONSTR_PROGRESS
//		SyncModel.getInstance().requestGetData(
//				true,
//				e,
//				"serverDataProcess.getClientData",
//				new SyncTableInfo(Constr_Progress_Field.TABLE_NAME,
//						Constr_Progress_Field.COLUMN_CONSTR_PROGRESS_ID,
//						ActionEventConstant.REQUEST_GETDATA,
//						Supervision_CNDTCController.allColumnConstrProgress));
//
//		percent = percent + percentLeap;
//
//		updateProgressBarDoInGetData(handler, percent,
//				Constr_Progress_Field.TABLE_NAME,
//				Constr_ObStruction_TypeField.TABLE_NAME);
//
//		// CONSTR_OBSTRUCTION_TYPE
//				SyncModel.getInstance().requestGetData(
//						false,
//						e,
//						"serverDataProcess.getCategoryTable",
//						new SyncTableInfo(Constr_ObStruction_TypeField.TABLE_NAME,
//								Constr_ObStruction_TypeField.COLUMN_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNVController.allColumnConstr_Obstruction_Type));
//
//				percent = percent + percentLeap;
//
//				updateProgressBarDoInGetData(handler, percent,
//						Constr_ObStruction_TypeField.TABLE_NAME,
//						Constr_ObStructionField.TABLE_NAME);
//
//				// CONSTR_OBSTRUCTION
//				SyncModel.getInstance().requestGetData(
//						true,
//						e,
//						"serverDataProcess.getClientData",
//						new SyncTableInfo(Constr_ObStructionField.TABLE_NAME,
//								Constr_ObStructionField.COLUMN_ID,
//								ActionEventConstant.REQUEST_GETDATA,
//								Supervision_CNVController.allColumnConstr_Obstruction));
//
//				percent = percent + percentLeap;
//
//				updateProgressBarDoInGetData(handler, percent,
//						Constr_ObStructionField.TABLE_NAME,
//						Supv_Constr_Attach_FileField.TABLE_NAME);

        // ---

        // SUPV_CONSTR_ATTACH_FILE Table_Number = 75
        SyncModel
                .getInstance()
                .requestGetData(
                        true,
                        e,
                        "serverDataProcess.getClientData",
                        new SyncTableInfo(
                                Supv_Constr_Attach_FileField.TABLE_NAME,
                                Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                                ActionEventConstant.REQUEST_GETDATA,
                                Supv_Constr_Attach_FileController.allColumn));

        Message msg2 = new Message();
        Bundle bundle2 = new Bundle();
        bundle2.putDouble(SyncTask.KEY_PERCENT, 40);
        bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: "
                + Supv_Constr_Attach_FileField.TABLE_NAME);
        msg2.setData(bundle2);
        handler.sendMessage(msg2);

        // TODO Download List Images
        // SyncQueue.getInstance().checkGetFinish();
        // syncUpateData(e);
    }

    private void updateProgressBarDoInGetData(Handler handler, Double percent,
                                              String curTable, String nextTable) {
        Message msg = new Message();
        Bundle bundle = new Bundle();

        bundle.putDouble(SyncTask.KEY_PERCENT, percent);
        bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: " + curTable);
        msg.setData(bundle);
        handler.sendMessage(msg);

        Message msg2 = new Message();
        Bundle bundle2 = new Bundle();

        bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
        bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: " + nextTable);
        msg2.setData(bundle2);
        handler.sendMessage(msg2);
    }

    public void syncUpateNextData(ActionEvent e, int tableNext) {
        boolean bUpdateData = false;
        boolean bRequestNext = false;
        long idUser = GlobalInfo.getInstance().getUserId();
        int iMaxGet = Constants.NUMBER_MAX_ITEM_SYNC;

        JSONArray jsonArrayData = null;
        int lCountItem = 0;
        JSONObject itemSupv = null;

        SyncTableUpdate syncTableUpdate = SyncTableUpdate.getInstance();
        for (int i = tableNext; i < syncTableUpdate.ListTableUpdate.length; i++) {
            if (syncTableUpdate.ListTableUpdate[i]
                    .equals(Login_Log_ConstrField.TABLE_NAME)) {
                jsonArrayData = SqlliteSyncModel.getDataJsonFromLogUser(idUser,
                        Login_Log_ConstrField.TABLE_NAME,
                        Login_Log_ConstrController.allColumn, 0, iMaxGet);

                lCountItem = jsonArrayData.length();
                bRequestNext = (lCountItem == iMaxGet) ? true : false;
                if (lCountItem > 0) {
                    tableUpdateCurent = i;
                    bUpdateData = true;
                    this.requesttUpdateData(e, new SyncTableInfo(
                            Login_Log_ConstrField.TABLE_NAME,
                            Login_Log_ConstrField.COLUMN_LOG_ID,
                            ActionEventConstant.REQUEST_UPDATEDATA,
                            jsonArrayData,
                            Login_Log_ConstrController.allColumn, bRequestNext));
                    break;
                } else {
                    continue;
                }
            } else {
                if (syncTableUpdate.ListTableUpdate[i]
                        .equals(Supv_Constr_DailyField.TABLE_NAME)) {
                    // SUPV_CONSTR_DAILY (nhat ki giam sat)
                    try {
                        SimpleDateFormat dateView = new SimpleDateFormat(
                                "yyyy-MM-dd hh:mm:ss");
                        JSONArray jsonScData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        Supervision_ConstrField.TABLE_NAME,
                                        Supervision_ConstrController.allColumnUpate,
                                        null, 0, 1000);

                        JSONArray jsonScDailyData = new JSONArray();
                        for (int j = 0; j < jsonScData.length(); j++) {
                            JSONObject itemDaily = new JSONObject();

                            itemSupv = jsonScData.getJSONObject(j);

                            itemDaily
                                    .put(Supv_Constr_DailyField.COLUMN_SUPERVISION_CONSTR_ID,
                                            itemSupv.get(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_SYNC_DATE,
                                    dateView.format(Calendar.getInstance()
                                            .getTime()));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LONGITUDE,
                                    GpsServices.longLocation);
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LATITUDE,
                                    GpsServices.latLocation);

                            jsonScDailyData.put(itemDaily);
                        }

                        lCountItem = jsonScDailyData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;
                        if (lCountItem > 0) {
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            Supv_Constr_DailyField.TABLE_NAME,
                                            Supv_Constr_DailyField.COLUMN_SUPV_CONSTR_DAILY_ID,
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonScDailyData,
                                            Supervision_ConstrController.allColumnDailyUpate,
                                            bRequestNext));
                            break;
                        } else {
                            continue;
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (syncTableUpdate.ListTableUpdate[i]
                            .equals(Supv_Constr_Attach_FileField.TABLE_NAME)) {
                        JSONArray jsonFileData = SqlliteSyncModel
                                .getDataJsonSyncFile(
                                        idUser,
                                        Supv_Constr_Attach_FileField.TABLE_NAME,
                                        Supv_Constr_Attach_FileController.allColumn,
                                        null, 0, iMaxGet);

                        lCountItem = jsonFileData.length();

                        if (lCountItem > 0) {
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            Supv_Constr_Attach_FileField.TABLE_NAME,
                                            Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonFileData,
                                            Supv_Constr_Attach_FileController.allColumn,
                                            bRequestNext));
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        jsonArrayData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        syncTableUpdate.ListTableUpdate[i],
                                        syncTableUpdate.mapUpdateFields
                                                .get(syncTableUpdate.ListTableUpdate[i]),
                                        null, 0, iMaxGet);
                        lCountItem = jsonArrayData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;

                        if (lCountItem > 0) {
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            syncTableUpdate.ListTableUpdate[i],
                                            syncTableUpdate.ListColumnIdTableUpdate[i],
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonArrayData,
                                            syncTableUpdate.mapUpdateFields
                                                    .get(syncTableUpdate.ListTableUpdate[i]),
                                            bRequestNext));
                            break;
                        } else {
                            continue;
                        }
                    }

                }
            }
        }

        // if (!bUpdateData) {
        // e.action = ActionEventConstant.REQEST_SYNC;
        // // ModelEvent model = new ModelEvent();
        // // model.setActionEvent(e);
        // // model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
        // // Home_Controller.getInstance().handleModelEvent(model);
        // percent = 50D;
        // Message msg = new Message();
        // Bundle bundle = new Bundle();
        // bundle.putDouble(SyncTask.KEY_PERCENT, percent);
        // bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Get Image");
        // msg.setData(bundle);
        // mHandler.sendMessage(msg);
        //
        // SyncModel.getInstance().syncGetImage(e);
        //
        // }
    }

	/* gui du lieu tu client len server */

    public void syncUpateData(ActionEvent e) {
        boolean bUpdateData = false;
        boolean bRequestNext = false;
        long idUser = GlobalInfo.getInstance().getUserId();
        int iMaxGet = Constants.NUMBER_MAX_ITEM_SYNC;

        percent = (double) 30;
        numberTableUpdate = 0;
        tableUpdateNumCurent = 0;
        // LOGIN_LOG_CONSTR
        // JSONArray jsonLlcData =
        // SqlliteSyncModel.getDataJsonFromLogUser(idUser,
        // Login_Log_ConstrField.TABLE_NAME,
        // Login_Log_ConstrController.allColumn, 0, iMaxGet);
        //
        // int lCountItem = jsonLlcData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Login_Log_ConstrField.TABLE_NAME,
        // Login_Log_ConstrField.COLUMN_LOG_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonLlcData,
        // Login_Log_ConstrController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }

        JSONArray jsonArrayData = null;
        int lCountItem = 0;
        JSONObject itemSupv = null;

        SyncTableUpdate syncTableUpdate = SyncTableUpdate.getInstance();
        for (int i = 0; i < syncTableUpdate.ListTableUpdate.length; i++) {
            if (syncTableUpdate.ListTableUpdate[i].equals(Login_Log_ConstrField.TABLE_NAME)) {
                jsonArrayData = SqlliteSyncModel.getDataJsonFromLogUser(idUser,
                        Login_Log_ConstrField.TABLE_NAME,
                        Login_Log_ConstrController.allColumn, 0, iMaxGet);
                lCountItem = jsonArrayData.length();
                if (lCountItem > 0) {
                    Log.i("bang--", syncTableUpdate.ListTableUpdate[i]);
                    numberTableUpdate++;
                } else {
                    continue;
                }
            } else {
                if (syncTableUpdate.ListTableUpdate[i].equals(Supv_Constr_DailyField.TABLE_NAME)) {
                    // SUPV_CONSTR_DAILY (nhat ki giam sat)
                    try {
                        SimpleDateFormat dateView = new SimpleDateFormat(
                                "yyyy-MM-dd hh:mm:ss");
                        JSONArray jsonScData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        Supervision_ConstrField.TABLE_NAME,
                                        Supervision_ConstrController.allColumnUpate,
                                        null, 0, 1000);

                        JSONArray jsonScDailyData = new JSONArray();
                        for (int j = 0; j < jsonScData.length(); j++) {
                            JSONObject itemDaily = new JSONObject();

                            itemSupv = jsonScData.getJSONObject(j);

                            itemDaily
                                    .put(Supv_Constr_DailyField.COLUMN_SUPERVISION_CONSTR_ID,
                                            itemSupv.get(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_SYNC_DATE,
                                    dateView.format(Calendar.getInstance()
                                            .getTime()));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LONGITUDE,
                                    GpsServices.longLocation);
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LATITUDE,
                                    GpsServices.latLocation);

                            jsonScDailyData.put(itemDaily);
                        }

                        lCountItem = jsonScDailyData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;
                        if (lCountItem > 0) {
                            Log.i("bang--", syncTableUpdate.ListTableUpdate[i]);
                            numberTableUpdate++;
                        } else {
                            continue;
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (syncTableUpdate.ListTableUpdate[i].equals(Supv_Constr_Attach_FileField.TABLE_NAME)) {
                        JSONArray jsonFileData = SqlliteSyncModel
                                .getDataJsonSyncFile(
                                        idUser,
                                        Supv_Constr_Attach_FileField.TABLE_NAME,
                                        Supv_Constr_Attach_FileController.allColumn,
                                        null, 0, iMaxGet);

                        lCountItem = jsonFileData.length();

                        if (lCountItem > 0) {
                            numberTableUpdate++;
                        } else {
                            continue;
                        }
                    } else {
                        jsonArrayData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        syncTableUpdate.ListTableUpdate[i],
                                        syncTableUpdate.mapUpdateFields.get(syncTableUpdate.ListTableUpdate[i]),
                                        null, 0, iMaxGet);
                        lCountItem = jsonArrayData.length();
                        if (lCountItem > 0) {
                            Log.i("bang--", syncTableUpdate.ListTableUpdate[i]);
                            numberTableUpdate++;
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        Log.i("update bang---", "numberTableUpdate: " + numberTableUpdate);

        for (int i = 0; i < syncTableUpdate.ListTableUpdate.length; i++) {

            percentLeap = (double) 5 / syncTableUpdate.ListTableUpdate.length;
            percent = percent + percentLeap;
            Message msg2 = new Message();
            Bundle bundle2 = new Bundle();
            bundle2.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle2.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                    "Syncing: Calculate.....");
            msg2.setData(bundle2);
            mHandler.sendMessage(msg2);

            if (syncTableUpdate.ListTableUpdate[i]
                    .equals(Login_Log_ConstrField.TABLE_NAME)) {
                jsonArrayData = SqlliteSyncModel.getDataJsonFromLogUser(idUser,
                        Login_Log_ConstrField.TABLE_NAME,
                        Login_Log_ConstrController.allColumn, 0, iMaxGet);

                lCountItem = jsonArrayData.length();
                bRequestNext = (lCountItem == iMaxGet) ? true : false;
                if (lCountItem > 0) {
                    tableUpdateCurent = i;
                    bUpdateData = true;
                    this.requesttUpdateData(e, new SyncTableInfo(
                            Login_Log_ConstrField.TABLE_NAME,
                            Login_Log_ConstrField.COLUMN_LOG_ID,
                            ActionEventConstant.REQUEST_UPDATEDATA,
                            jsonArrayData,
                            Login_Log_ConstrController.allColumn, bRequestNext));
                    break;
                } else {
                    continue;
                }
            } else {
                if (syncTableUpdate.ListTableUpdate[i]
                        .equals(Supv_Constr_DailyField.TABLE_NAME)) {
                    // SUPV_CONSTR_DAILY (nhat ki giam sat)
                    try {
                        SimpleDateFormat dateView = new SimpleDateFormat(
                                "yyyy-MM-dd hh:mm:ss");
                        JSONArray jsonScData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        Supervision_ConstrField.TABLE_NAME,
                                        Supervision_ConstrController.allColumnUpate,
                                        null, 0, 1000);

                        JSONArray jsonScDailyData = new JSONArray();
                        for (int j = 0; j < jsonScData.length(); j++) {
                            JSONObject itemDaily = new JSONObject();

                            itemSupv = jsonScData.getJSONObject(j);

                            itemDaily
                                    .put(Supv_Constr_DailyField.COLUMN_SUPERVISION_CONSTR_ID,
                                            itemSupv.get(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_SYNC_DATE,
                                    dateView.format(Calendar.getInstance()
                                            .getTime()));
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LONGITUDE,
                                    GpsServices.longLocation);
                            itemDaily.put(
                                    Supv_Constr_DailyField.COLUMN_LATITUDE,
                                    GpsServices.latLocation);

                            jsonScDailyData.put(itemDaily);
                        }

                        lCountItem = jsonScDailyData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;
                        if (lCountItem > 0) {
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            Supv_Constr_DailyField.TABLE_NAME,
                                            Supv_Constr_DailyField.COLUMN_SUPV_CONSTR_DAILY_ID,
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonScDailyData,
                                            Supervision_ConstrController.allColumnDailyUpate,
                                            bRequestNext));

                            break;
                        } else {
                            continue;
                        }
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                } else {
                    if (syncTableUpdate.ListTableUpdate[i]
                            .equals(Supv_Constr_Attach_FileField.TABLE_NAME)) {
                        JSONArray jsonFileData = SqlliteSyncModel
                                .getDataJsonSyncFile(
                                        idUser,
                                        Supv_Constr_Attach_FileField.TABLE_NAME,
                                        Supv_Constr_Attach_FileController.allColumn,
                                        null, 0, iMaxGet);

                        lCountItem = jsonFileData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;

                        if (lCountItem > 0) {
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            Supv_Constr_Attach_FileField.TABLE_NAME,
                                            Supv_Constr_Attach_FileField.COLUMN_SUPV_CONSTR_ATTACH_FILE_ID,
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonFileData,
                                            Supv_Constr_Attach_FileController.allColumn,
                                            bRequestNext));
                            break;
                        } else {
                            continue;
                        }
                    } else {
                        jsonArrayData = SqlliteSyncModel
                                .getDataJsonSync(
                                        idUser,
                                        syncTableUpdate.ListTableUpdate[i],
                                        syncTableUpdate.mapUpdateFields
                                                .get(syncTableUpdate.ListTableUpdate[i]),
                                        null, 0, iMaxGet);
                        lCountItem = jsonArrayData.length();
                        bRequestNext = (lCountItem == iMaxGet) ? true : false;
//                        Log.e(TAG, "syncUpateData: " + syncTableUpdate.ListTableUpdate[i] + " " + lCountItem);
                        if (lCountItem > 0) {
                            Log.e(TAG, "syncUpateData: " + syncTableUpdate.ListTableUpdate[i] + "\n\t" + jsonArrayData.toString());
                            tableUpdateCurent = i;
                            bUpdateData = true;
                            this.requesttUpdateData(
                                    e,
                                    new SyncTableInfo(
                                            syncTableUpdate.ListTableUpdate[i],
                                            syncTableUpdate.ListColumnIdTableUpdate[i],
                                            ActionEventConstant.REQUEST_UPDATEDATA,
                                            jsonArrayData,
                                            syncTableUpdate.mapUpdateFields.get(syncTableUpdate.ListTableUpdate[i]),
                                            bRequestNext));

                            break;
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        // SUPERVISION_CONSTR
        // JSONArray jsonScData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_ConstrField.TABLE_NAME,
        // Supervision_ConstrController.allColumnUpate, null, 0, iMaxGet);
        //
        // lCountItem = jsonScData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_ConstrField.TABLE_NAME,
        // Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonScData,
        // Supervision_ConstrController.allColumnUpate, bRequestNext));
        // bUpdateData = true;
        // }

        // TODO: Sync Images
        if (!bUpdateData) {
            e.action = ActionEventConstant.REQEST_SYNC;
            // ModelEvent model = new ModelEvent();
            // model.setActionEvent(e);
            // model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
            // Home_Controller.getInstance().handleModelEvent(model);
            percent = 50D;
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Get Image");
            msg.setData(bundle);
            mHandler.sendMessage(msg);

            SyncModel.getInstance().syncGetImage(e);

        }
        // <editor-fold defaultstate="collapsed" desc="ngam">
        // // SUPERVISION_PROGRESS
        // JSONArray jsonProgressData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_ProgressField.TABLE_NAME,
        // Supervision_ProgressController.allColumn, null, 0, iMaxGet);
        // lCountItem = jsonProgressData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_ProgressField.TABLE_NAME,
        // Supervision_ProgressField.COLUMN_SUPERVISION_PROGRESS_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonProgressData,
        // Supervision_ProgressController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // /* 1. Tuyen ngam */
        // // SUPERVISION_LINE_BACKGROUND
        // JSONArray jsonBgData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_BackgroundField.TABLE_NAME,
        // Supervision_Line_BackgroundController.allColumn, null, 0,
        // iMaxGet);
        // lCountItem = jsonBgData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BackgroundField.TABLE_NAME,
        // Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBgData,
        // Supervision_Line_BackgroundController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_BG_FIBER
        // JSONArray jsonBgFiberData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Supervision_Line_BG_FiberField.TABLE_NAME,
        // Supervision_Line_BG_FiberController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBgFiberData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BG_FiberField.TABLE_NAME,
        // Supervision_Line_BG_FiberField.COLUMN_SUPERVISION_LINE_BG_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgFiberData,
        // Supervision_Line_BG_FiberController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_BG_TANK
        // JSONArray jsonBgTankData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_BG_TankField.TABLE_NAME,
        // Supervision_Line_BG_TankController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgTankData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BG_TankField.TABLE_NAME,
        // Supervision_Line_BG_TankField.COLUMN_SUPERVISION_LINE_BG_TANK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgTankData,
        // Supervision_Line_BG_TankController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_BG_CABLE
        // JSONArray jsonBgCableData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Supervision_Line_BG_CableField.TABLE_NAME,
        // Supervision_Line_BG_CableController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBgCableData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BG_CableField.TABLE_NAME,
        // Supervision_Line_BG_CableField.COLUMN_SUPERVISION_LINE_BG_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgCableData,
        // Supervision_Line_BG_CableController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SPECIAL_LOCATION
        // JSONArray jsonBgSpecialLocationData =
        // SqlliteSyncModel.getDataJsonSync(
        // idUser, Special_LocationField.TABLE_NAME,
        // Special_LocationController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgSpecialLocationData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Special_LocationField.TABLE_NAME,
        // Special_LocationField.COLUMN_SPECIAL_LOCATION_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgSpecialLocationData,
        // Special_LocationController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_BG_PIPE
        // JSONArray jsonBgPipeData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_BG_PipeField.TABLE_NAME,
        // Supervision_Line_BG_PipeController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgPipeData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BG_PipeField.TABLE_NAME,
        // Supervision_Line_BG_PipeField.COLUMN_SUPERVISION_LINE_BG_PIPE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgPipeData,
        // Supervision_Line_BG_PipeController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_BG_MX
        // JSONArray jsonBgMxData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_BG_MXField.TABLE_NAME,
        // Supervision_Line_BG_MxController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgMxData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_BG_MXField.TABLE_NAME,
        // Supervision_Line_BG_MXField.COLUMN_SUPERVISION_LINE_BG_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgMxData,
        // Supervision_Line_BG_MxController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_BG_TANK
        // JSONArray jsonBgCauseTankData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_BG_TankField.TABLE_NAME,
        // Cause_Line_BG_TankController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgCauseTankData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_BG_TankField.TABLE_NAME,
        // Cause_Line_BG_TankField.COLUMN_CAUSE_LINE_BG_TANK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgCauseTankData,
        // Cause_Line_BG_TankController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_BG_SPECIAL
        // JSONArray jsonBgCauseSpecialData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_BG_SpecialField.TABLE_NAME,
        // Cause_Line_BG_SpecialController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgCauseSpecialData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_BG_SpecialField.TABLE_NAME,
        // Cause_Line_BG_SpecialField.COLUMN_CAUSE_LINE_BG_SPECIAL_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgCauseSpecialData,
        // Cause_Line_BG_SpecialController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_BG_PIPE
        // JSONArray jsonBgCausePipeData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_BG_PipeField.TABLE_NAME,
        // Cause_Line_BG_PipeController.allColumn, null, 0, iMaxGet);
        // lCountItem = jsonBgCausePipeData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_BG_PipeField.TABLE_NAME,
        // Cause_Line_BG_PipeField.COLUMN_CAUSE_LINE_BG_PIPE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgCausePipeData,
        // Cause_Line_BG_PipeController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_BG_CABLE
        // JSONArray jsonBgCauseCableData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_BG_CableField.TABLE_NAME,
        // Cause_Line_BG_CableController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgCauseCableData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_BG_CableField.TABLE_NAME,
        // Cause_Line_BG_CableField.COLUMN_CAUSE_LINE_BG_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgCauseCableData,
        // Cause_Line_BG_CableController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_BG_MX
        // JSONArray jsonBgCauseMxData =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Line_BG_MXField.TABLE_NAME,
        // Cause_Line_BG_MXController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgCauseMxData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_BG_MXField.TABLE_NAME,
        // Cause_Line_BG_MXField.COLUMN_CAUSE_LINE_BG_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBgCauseMxData,
        // Cause_Line_BG_MXController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // ACCEPTANCE_LINE_BG_SPECIAL
        // JSONArray jsonBgAcceptSpecialData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Acceptance_Line_Bg_SpecialField.TABLE_NAME,
        // Acceptance_Line_Bg_SpecialController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBgAcceptSpecialData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Line_Bg_SpecialField.TABLE_NAME,
        // Acceptance_Line_Bg_SpecialField.COLUMN_ACCEPTANCE_LINE_BG_SPECIAL_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgAcceptSpecialData,
        // Acceptance_Line_Bg_SpecialController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // ACCEPTANCE_LINE_BG_TANK
        // JSONArray jsonBgAcceptTankData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Acceptance_Line_Bg_TankField.TABLE_NAME,
        // Acceptance_Line_Bg_TankController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgAcceptTankData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Line_Bg_TankField.TABLE_NAME,
        // Acceptance_Line_Bg_TankField.COLUMN_ACCEPTANCE_LINE_BG_TANK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgAcceptTankData,
        // Acceptance_Line_Bg_TankController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // ACCEPTANCE_LINE_BG_PIPE
        // JSONArray jsonBgAcceptPipeData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Acceptance_Line_Bg_PipeField.TABLE_NAME,
        // Acceptance_Line_Bg_PipeController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBgAcceptPipeData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Line_Bg_PipeField.TABLE_NAME,
        // Acceptance_Line_Bg_PipeField.COLUMN_ACCEPTANCE_LINE_BG_PIPE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBgAcceptPipeData,
        // Acceptance_Line_Bg_PipeController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // /* 2. Tuyen treo */
        // // SUPERVISION_LINE_HANG
        // JSONArray jsonHgData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_HangField.TABLE_NAME,
        // Supervision_Line_HangController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonHgData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_Line_HangField.TABLE_NAME,
        // Supervision_Line_HangField.COLUMN_SUPERVISION_LINE_HANG_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonHgData,
        // Supervision_Line_HangController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_HG_FIBER
        // JSONArray jsonHgFiberData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Supervision_Line_Hg_FiberField.TABLE_NAME,
        // Supervision_Line_Hg_FiberController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonHgFiberData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_Hg_FiberField.TABLE_NAME,
        // Supervision_Line_Hg_FiberField.COLUMN_SUPERVISION_LINE_HG_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgFiberData,
        // Supervision_Line_Hg_FiberController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_HG_PILLAR
        // JSONArray jsonHgPillarData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_Hg_PillarField.TABLE_NAME,
        // Supervision_Line_Hg_PillarController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonHgPillarData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_Hg_PillarField.TABLE_NAME,
        // Supervision_Line_Hg_PillarField.COLUMN_SUPERVISION_LINE_HG_PILLAR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgPillarData,
        // Supervision_Line_Hg_PillarController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_HG_MX
        // JSONArray jsonHgMxData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Line_Hg_MxField.TABLE_NAME,
        // Supervision_Line_Hg_MxController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonHgMxData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_Hg_MxField.TABLE_NAME,
        // Supervision_Line_Hg_MxField.COLUMN_SUPERVISION_LINE_HG_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgMxData,
        // Supervision_Line_Hg_MxController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_LINE_HG_CABLE
        // JSONArray jsonHgCableData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Supervision_Line_Hg_CableField.TABLE_NAME,
        // Supervision_Line_Hg_CableController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonHgCableData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Line_Hg_CableField.TABLE_NAME,
        // Supervision_Line_Hg_CableField.COLUMN_SUPERVISION_LINE_HG_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgCableData,
        // Supervision_Line_Hg_CableController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // JSONArray jsonScDataTest = SqlliteSyncModel.getDataJsonSyncTest(
        // // Cause_Line_Hg_PillarField.TABLE_NAME,
        // // Cause_Line_Hg_PillarController.allColumn, null, 0, iMaxGet);
        // // CAUSE_LINE_HG_PILLAR
        // JSONArray jsonHgCausePillarData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_Hg_PillarField.TABLE_NAME,
        // Cause_Line_Hg_PillarController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonHgCausePillarData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_Hg_PillarField.TABLE_NAME,
        // Cause_Line_Hg_PillarField.COLUMN_CAUSE_LINE_HG_PILLAR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgCausePillarData,
        // Cause_Line_Hg_PillarController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_HG_MX
        // JSONArray jsonHgCauseMxData =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Line_Hg_MxField.TABLE_NAME,
        // Cause_Line_Hg_MxController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonHgCauseMxData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_Hg_MxField.TABLE_NAME,
        // Cause_Line_Hg_MxField.COLUMN_CAUSE_LINE_HG_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonHgCauseMxData,
        // Cause_Line_Hg_MxController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // CAUSE_LINE_HG_CABLE
        // JSONArray jsonHgCauseCableData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Line_Hg_CableField.TABLE_NAME,
        // Cause_Line_Hg_CableController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonHgCauseCableData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Line_Hg_CableField.TABLE_NAME,
        // Cause_Line_Hg_CableField.COLUMN_CAUSE_LINE_HG_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonHgCauseCableData,
        // Cause_Line_Hg_CableController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // /* 3. BTS */
        // // SUPERVISION_BTS
        // JSONArray jsonBtsData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BtsField.TABLE_NAME,
        // Supervision_BtsController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_BtsField.TABLE_NAME,
        // Supervision_BtsField.COLUMN_SUPERVISION_BTS_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBtsData,
        // Supervision_BtsController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_BTS_EQUIP
        // JSONArray jsonBtsEquipData = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Bts_EquipField.TABLE_NAME,
        // Supervision_Bts_EquipController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsEquipData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_Bts_EquipField.TABLE_NAME,
        // Supervision_Bts_EquipField.COLUMN_SUPERVISION_BTS_EQUIP_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBtsEquipData,
        // Supervision_Bts_EquipController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // SUPERVISION_BTS_MEASURE
        // JSONArray jsonBtsMeasureData =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Bts_MeasureField.TABLE_NAME,
        // Supervision_Bts_MeasureController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsMeasureData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Bts_MeasureField.TABLE_NAME,
        // Supervision_Bts_MeasureField.COLUMN_SUPERVISION_BTS_MEASURE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsMeasureData,
        // Supervision_Bts_MeasureController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_BTS_POWER_POLE
        // JSONArray jsonBtsPowerPoleData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Supervision_Bts_Power_PoleField.TABLE_NAME,
        // Supervision_Bts_Power_PoleController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBtsPowerPoleData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Bts_Power_PoleField.TABLE_NAME,
        // Supervision_Bts_Power_PoleField.COLUMN_SUPERVISION_BTS_POWER_POLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsPowerPoleData,
        // Supervision_Bts_Power_PoleController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_BTS_PILLAR_ANTEN
        // JSONArray jsonBtsPillarAntenData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Supervision_Bts_Pillar_AntenField.TABLE_NAME,
        // Supervision_Bts_Pillar_AntenController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBtsPillarAntenData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Bts_Pillar_AntenField.TABLE_NAME,
        // Supervision_Bts_Pillar_AntenField.COLUMN_SUPV_BTS_PILLAR_ANTEN_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsPillarAntenData,
        // Supervision_Bts_Pillar_AntenController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // SUPERVISION_BTS_CAT_WORK
        // JSONArray jsonBtsCatWorkData =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_Bts_Cat_WorkField.TABLE_NAME,
        // Supervision_Bts_Cat_WorkController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsCatWorkData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Bts_Cat_WorkField.TABLE_NAME,
        // Supervision_Bts_Cat_WorkField.COLUMN_SUPERVISION_BTS_CAT_WORK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsCatWorkData,
        // Supervision_Bts_Cat_WorkController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_BTS_PILLAR_ANTEN
        // JSONArray jsonBtsCausePillarAntenData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Cause_Bts_Pillar_AntenField.TABLE_NAME,
        // Cause_Bts_Pillar_AntenController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonBtsCausePillarAntenData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Cause_Bts_Pillar_AntenField.TABLE_NAME,
        // Cause_Bts_Pillar_AntenField.COLUMN_CAUSE_BTS_PILLAR_ANTEN_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsCausePillarAntenData,
        // Cause_Bts_Pillar_AntenController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_BTS_CAT_WORK
        // JSONArray jsonBtsCauseCatWorkData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Bts_Cat_WorkField.TABLE_NAME,
        // Cause_Bts_Cat_WorkController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsCauseCatWorkData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Bts_Cat_WorkField.TABLE_NAME,
        // Cause_Bts_Cat_WorkField.COLUMN_CAUSE_BTS_CAT_WORK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsCauseCatWorkData,
        // Cause_Bts_Cat_WorkController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_BTS_POWER_POLE
        // JSONArray jsonBtsCausePowerPoleData =
        // SqlliteSyncModel.getDataJsonSync(
        // idUser, Cause_Bts_Power_PoleField.TABLE_NAME,
        // Cause_Bts_Power_PoleController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsCausePowerPoleData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Bts_Power_PoleField.TABLE_NAME,
        // Cause_Bts_Power_PoleField.COLUMN_CAUSE_BTS_POWER_POLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsCausePowerPoleData,
        // Cause_Bts_Power_PoleController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // ACCEPTANCE_BTS_PILLAR
        // JSONArray jsonBtsAcceptPillarData = SqlliteSyncModel.getDataJsonSync(
        // idUser, Acceptance_Bts_PillarField.TABLE_NAME,
        // Acceptance_Bts_PillarController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsAcceptPillarData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Bts_PillarField.TABLE_NAME,
        // Acceptance_Bts_PillarField.COLUMN_ACCEPTANCE_BTS_PILLAR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsAcceptPillarData,
        // Acceptance_Bts_PillarController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // ACCEPTANCE_BTS_CAT_WORK
        // JSONArray jsonBtsAcceptCatWorkData =
        // SqlliteSyncModel.getDataJsonSync(
        // idUser, Acceptance_Bts_Cat_WorkField.TABLE_NAME,
        // Acceptance_Bts_Cat_WorkController.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBtsAcceptCatWorkData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Bts_Cat_WorkField.TABLE_NAME,
        // Acceptance_Bts_Cat_WorkField.COLUMN_ACCEPTANCE_BTS_CATWORK_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBtsAcceptCatWorkData,
        // Acceptance_Bts_Cat_WorkController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // SUPERVISION_MEASURE_CONSTR
        // JSONArray jsonSupervisionMeasureData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Supervision_Measure_ConstrField.TABLE_NAME,
        // Supervision_Measure_ConstrController.allColumn, null,
        // 0, iMaxGet);
        //
        // lCountItem = jsonSupervisionMeasureData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_Measure_ConstrField.TABLE_NAME,
        // Supervision_Measure_ConstrField.COLUMN_SUPERVISION_MEASURE_CONSTR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonSupervisionMeasureData,
        // Supervision_Measure_ConstrController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // MEASUREMENT_DETAIL_CONSTR
        // JSONArray jsonMeasurementDetailData = SqlliteSyncModel
        // .getDataJsonSync(idUser,
        // Measurement_Detail_ConstrField.TABLE_NAME,
        // Measurement_Detail_ConstrController.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonMeasurementDetailData.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Measurement_Detail_ConstrField.TABLE_NAME,
        // Measurement_Detail_ConstrField.COLUMN_MEASUREMENT_DETAIL_CONSTR_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonMeasurementDetailData,
        // Measurement_Detail_ConstrController.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // cuongdk3 bo di, theo giai phap dong bo moi, dong bo anh truoc moi
        // cap
        // // nhat trang thai

        // /* 4. Bang rong co dinh */
        // // SUPERVISION_SUB
        // JSONArray jsonSub = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Sub_Field.TABLE_NAME,
        // Supervision_BRCD_Sub_Controller.allColumn, null, 0, iMaxGet);
        // lCountItem = jsonSub.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Sub_Field.TABLE_NAME,
        // Supervision_BRCD_Sub_Field.COLUMN_SUPERVISION_SUBHEADEND_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonSub,
        // Supervision_BRCD_Sub_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_DROP_DESIGN
        // JSONArray jsonDropDesign = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
        // Supervision_BRCD_Drop_Design_Controller.allColumn, null, 0,
        // iMaxGet);
        // lCountItem = jsonDropDesign.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Drop_Design_Field.TABLE_NAME,
        // Supervision_BRCD_Drop_Design_Field.COLUMN_SUPERVISION_BRANCH_DROP_DESIGN_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonDropDesign,
        // Supervision_BRCD_Drop_Design_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_DROP
        // JSONArray jsonDrop = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Drop_Field.TABLE_NAME,
        // Supervision_BRCD_Drop_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonDrop.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Drop_Field.TABLE_NAME,
        // Supervision_BRCD_Drop_Field.COLUMN_SUPERVISION_BRANCH_DROP_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonDrop,
        // Supervision_BRCD_Drop_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_BRCD
        // JSONArray jsonBrcd = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Field.TABLE_NAME,
        // Supervision_BRCD_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcd.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_BRCD_Field.TABLE_NAME,
        // Supervision_BRCD_Field.COLUMN_SUPERVISION_BRCD_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcd,
        // Supervision_BRCD_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_GIAMSAT_DROPGO
        // JSONArray jsonGSDropGo = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_DropGo_Controller.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonGSDropGo.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_GiamSat_DropGo_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_DropGo_Field.COLUMN_SUPERVISION_DROPGO_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonGSDropGo,
        // Supervision_BRCD_GiamSat_DropGo_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SPECIAL_GIAMSAT_KEOCAPNHANH
        // JSONArray jsonGSKCN = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_Kcn_Controller.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonGSKCN.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_GiamSat_Kcn_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonGSKCN,
        // Supervision_BRCD_GiamSat_Kcn_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // //TODO
        // // SPECIAL_GIAMSAT_KEOCAPTRUC
        // JSONArray jsonGSKCT = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_Kct_Controller.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonGSKCT.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME,
        // Supervision_BRCD_GiamSat_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonGSKCT,
        // Supervision_BRCD_GiamSat_Kct_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_KEOCAPNHANH_DESIGN
        // JSONArray jsonKCNDesign = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
        // Supervision_BRCD_Kcn_Design_Controller.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonKCNDesign.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Kcn_Design_Field.TABLE_NAME,
        // Supervision_BRCD_Kcn_Design_Field.COLUMN_SUPERVISION_BRANCH_DESIGN_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonKCNDesign,
        // Supervision_BRCD_Kcn_Design_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // SUPERVISION_KEOCAPNHANH
        // JSONArray jsonKCN = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Kcn_Field.TABLE_NAME,
        // Supervision_BRCD_Kcn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonKCN.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Kcn_Field.TABLE_NAME,
        // Supervision_BRCD_Kcn_Field.COLUMN_SUPERVISION_BRANCH_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonKCN,
        // Supervision_BRCD_Kcn_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // KEOCAPTRUC_DESIGN
        // JSONArray jsonKCTDesign = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Kct_design_Field.TABLE_NAME,
        // Supervision_BRCD_Kct_design_Controller.allColumn, null, 0,
        // iMaxGet);
        //
        // lCountItem = jsonKCTDesign.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Kct_design_Field.TABLE_NAME,
        // Supervision_BRCD_Kct_design_Field.COLUMN_SUPERVISION_TRUNK_DESIGN_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonKCTDesign,
        // Supervision_BRCD_Kct_design_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // KEOCAPTRUC
        // JSONArray jsonKCT = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Kct_Field.TABLE_NAME,
        // Supervision_BRCD_Kct_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonKCT.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Kct_Field.TABLE_NAME,
        // Supervision_BRCD_Kct_Field.COLUMN_SUPERVISION_TRUNK_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonKCT,
        // Supervision_BRCD_Kct_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // Mangtruc
        // JSONArray jsonMx = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_MXField.TABLE_NAME,
        // Supervision_BRCD_MxController.allColumn, null, 0, iMaxGet);
        // lCountItem = jsonMx.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Supervision_BRCD_MXField.TABLE_NAME,
        // Supervision_BRCD_MXField.COLUMN_SUPERVISION_TRUNK_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonMx,
        // Supervision_BRCD_MxController.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // TUNHANH
        // JSONArray jsonTN = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Tn_Field.TABLE_NAME,
        // Supervision_BRCD_Tn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonTN.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Tn_Field.TABLE_NAME,
        // Supervision_BRCD_Tn_Field.COLUMN_SUPERVISION_BRANCH_CABINET_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonTN,
        // Supervision_BRCD_Tn_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // TUTHUEBAO
        // JSONArray jsonTtb = SqlliteSyncModel.getDataJsonSync(idUser,
        // Supervision_BRCD_Ttb_Field.TABLE_NAME,
        // Supervision_BRCD_Ttb_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonTtb.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Supervision_BRCD_Ttb_Field.TABLE_NAME,
        // Supervision_BRCD_Ttb_Field.COLUMN_SUPERVISION_BRANCH_BOX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonTtb,
        // Supervision_BRCD_Ttb_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        //
        // }
        // // CAUSE_Brcd_Sub
        // JSONArray jsonSubCause = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Sub_Field.TABLE_NAME,
        // Cause_Brcd_Sub_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonSubCause.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Sub_Field.TABLE_NAME,
        // Cause_Brcd_Sub_Field.COLUMN_CAUSE_SUBHEADEND_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonSubCause,
        // Cause_Brcd_Sub_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_Brcd_box
        // JSONArray jsonbrcdCauseBox = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Box_Field.TABLE_NAME,
        // Cause_Brcd_Box_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonbrcdCauseBox.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Box_Field.TABLE_NAME,
        // Cause_Brcd_Box_Field.COLUMN_CAUSE_BRANCH_BOX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonbrcdCauseBox,
        // Cause_Brcd_Box_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_brcd_drop
        // JSONArray jsonBrcdCauseDrop =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Drop_Field.TABLE_NAME,
        // Cause_Brcd_Drop_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdCauseDrop.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Drop_Field.TABLE_NAME,
        // Cause_Brcd_Drop_Field.COLUMN_CAUSE_DROP_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdCauseDrop,
        // Cause_Brcd_Drop_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_Brcd_kcn
        // JSONArray jsonBrcdCauseKcn = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Kcn_Field.TABLE_NAME,
        // Cause_Brcd_Kcn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdCauseKcn.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Kcn_Field.TABLE_NAME,
        // Cause_Brcd_Kcn_Field.COLUMN_CAUSE_BRANCH_CABLE_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdCauseKcn,
        // Cause_Brcd_Kcn_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_Brcd_kct
        // JSONArray jsonBrcdCausekct = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Kct_Field.TABLE_NAME,
        // Cause_Brcd_Kct_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdCausekct.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Kct_Field.TABLE_NAME,
        // Cause_Brcd_Kct_Field.COLUMN_CAUSE_TRUNK_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdCausekct,
        // Cause_Brcd_Kct_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_Brcd_mx
        // JSONArray jsonBrcdCauseMx = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Mx_Field.TABLE_NAME,
        // Cause_Brcd_Mx_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdCauseMx.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Mx_Field.TABLE_NAME,
        // Cause_Brcd_Mx_Field.COLUMN_CAUSE_TRUNK_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdCauseMx,
        // Cause_Brcd_Mx_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        //
        // // CAUSE_Brcd_tn
        // JSONArray jsonBrcdCauseTn = SqlliteSyncModel.getDataJsonSync(idUser,
        // Cause_Brcd_Tn_Field.TABLE_NAME,
        // Cause_Brcd_Tn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdCauseTn.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Cause_Brcd_Tn_Field.TABLE_NAME,
        // Cause_Brcd_Tn_Field.COLUMN_CAUSE_BRANCH_CABINET_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdCauseTn,
        // Cause_Brcd_Tn_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_sub
        // JSONArray jsonSubAccep = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Sub_Field.TABLE_NAME,
        // Acceptance_Brcd_Sub_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonSubAccep.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Brcd_Sub_Field.TABLE_NAME,
        // Acceptance_Brcd_Sub_Field.COLUMN_ACCEPTANCE_SUBHEADEND_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonSubAccep,
        // Acceptance_Brcd_Sub_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_box
        // JSONArray jsonBrcdAccepBox = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Box_Field.TABLE_NAME,
        // Acceptance_Brcd_Box_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepBox.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Brcd_Box_Field.TABLE_NAME,
        // Acceptance_Brcd_Box_Field.COLUMN_ACCEPTANCE_BRANCH_BOX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdAccepBox,
        // Acceptance_Brcd_Box_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_drop
        // JSONArray jsonBrcdAccepDrop =
        // SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Drop_Field.TABLE_NAME,
        // Acceptance_Brcd_Drop_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepDrop.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Brcd_Drop_Field.TABLE_NAME,
        // Acceptance_Brcd_Drop_Field.COLUMN_ACCEPTANCE_DROP_FIBRE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdAccepDrop,
        // Acceptance_Brcd_Drop_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_kcn
        // JSONArray jsonBrcdAccepKcn = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Kcn_Field.TABLE_NAME,
        // Acceptance_Brcd_Kcn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepKcn.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Brcd_Kcn_Field.TABLE_NAME,
        // Acceptance_Brcd_Kcn_Field.COLUMN_ACCEPTANCE_BRANCH_CABLE_FIBER_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBrcdAccepKcn,
        // Acceptance_Brcd_Kcn_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_Kct
        // JSONArray jsonBrcdAccepKct = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Kct_Field.TABLE_NAME,
        // Acceptance_Brcd_Kct_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepKct.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Brcd_Kct_Field.TABLE_NAME,
        // Acceptance_Brcd_Kct_Field.COLUMN_ACCEPTANCE_TRUNK_CABLE_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdAccepKct,
        // Acceptance_Brcd_Kct_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_mx
        // JSONArray jsonBrcdAccepMx = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Mx_Field.TABLE_NAME,
        // Acceptance_Brcd_Mx_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepMx.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(e, new SyncTableInfo(
        // Acceptance_Brcd_Mx_Field.TABLE_NAME,
        // Acceptance_Brcd_Mx_Field.COLUMN_ACCEPTANCE_TRUNK_MX_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA, jsonBrcdAccepMx,
        // Acceptance_Brcd_Mx_Controller.allColumn, bRequestNext));
        // bUpdateData = true;
        // }
        // // accep_brcd_tn
        // JSONArray jsonBrcdAccepTn = SqlliteSyncModel.getDataJsonSync(idUser,
        // Acceptance_Brcd_Tn_Field.TABLE_NAME,
        // Acceptance_Brcd_Tn_Controller.allColumn, null, 0, iMaxGet);
        //
        // lCountItem = jsonBrcdAccepTn.length();
        // bRequestNext = (lCountItem == iMaxGet) ? true : false;
        //
        // if (lCountItem > 0) {
        // this.requesttUpdateData(
        // e,
        // new SyncTableInfo(
        // Acceptance_Brcd_Tn_Field.TABLE_NAME,
        // Acceptance_Brcd_Tn_Field.COLUMN_ACCEPTANCE_BRANCH_CABINET_ID,
        // ActionEventConstant.REQUEST_UPDATEDATA,
        // jsonBrcdAccepTn,
        // Acceptance_Brcd_Tn_Controller.allColumn,
        // bRequestNext));
        // bUpdateData = true;
        // }
        // </editor-fold>

    }

    /* Lay anh tren server ve */
    public void syncGetImage(ActionEvent e) {
        // TODO: Get Images form Server
        LogManager.getInstance().writeFile("request get image");

        percent = 50D;

        Supv_Constr_Attach_FileController fileController = new Supv_Constr_Attach_FileController(
                SyncModel.mContext);

        File fileSave = null;
        List<Supv_Constr_Attach_FileEntity> listAllDownLoad = fileController
                .getAllAttachFileDownLoad();
        // gan lai trang thai da download anh roi thi khong download lai nua
        for (Supv_Constr_Attach_FileEntity itemFileDownLoad : listAllDownLoad) {
            fileSave = new File(GlobalInfo.getInstance().getFilePath()
                    + itemFileDownLoad.getFile_Path());
            if (fileSave.exists()) {
                fileController.setIsDownloaded(itemFileDownLoad
                        .getSupv_Constr_Attach_file_Id());
            }
        }

        List<Supv_Constr_Attach_FileEntity> listDownLoad = fileController
                .getAttachFileDownLoad(Constants.NUMBER_MAX_ITEM_SYNC_IMG);
        // int iSizeDownLoad = listDownLoad.size();
        // if (iSizeDownLoad > 0) {
        // for (Supv_Constr_Attach_FileEntity itemFileDownLoad : listDownLoad) {
        //
        // this.requestGetImage(e, itemFileDownLoad,
        // ActionEventConstant.REQUEST_DOWNLOADIMAGE);
        //
        // }
        // }
        // cuongdk3 add
        int iSizeDownLoad = listDownLoad.size();

        boolean bRequestNext = (iSizeDownLoad == Constants.NUMBER_MAX_ITEM_SYNC_IMG) ? true
                : false;

        this.numberGetImage = fileController.getAttachFileDownLoad();

        if (iSizeDownLoad > 0) {

            for (Supv_Constr_Attach_FileEntity itemFileDownLoad : listDownLoad) {
                itemFileDownLoad.setRequestNext(bRequestNext);
                this.requestGetImage(e, itemFileDownLoad,
                        ActionEventConstant.REQUEST_DOWNLOADIMAGE);
            }
        }
		/*
		 * TODO: Neu lay anh tu server ve xong thi chuyen sang update anh len
		 * server
		 */
        else {
            percent = 75D;
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Get Image");
            msg.setData(bundle);
            mHandler.sendMessage(msg);

            SyncModel.getInstance().syncUpdateImage(e);
        }
        // SyncModel.getInstance().syncUpdateImage(e);
    }

    /* Upload anh tu Tablet len server */
    public void syncUpdateImage(ActionEvent e) {
        Supv_Constr_Attach_FileController fileController = new Supv_Constr_Attach_FileController(
                SyncModel.mContext);
        percent = 75D;

        // lay ra tong so file can upload, file nao ton tai roi va co size = 0
        // thi gan is_upload = 1 de khong upload len server nua
        List<Supv_Constr_Attach_FileEntity> listAllUpLoad = fileController
                .getAllAttachFileUpload();
        File fileUpload = null;
        for (Supv_Constr_Attach_FileEntity itemUpload : listAllUpLoad) {
            fileUpload = new File(GlobalInfo.getInstance().getFilePath()
                    + itemUpload.getFile_Path());
            if ((fileUpload.exists() && fileUpload.length() == 0)
                    || !fileUpload.exists()) {
                fileController.isUploaded(itemUpload
                        .getSupv_Constr_Attach_file_Id());
            }
        }

        List<Supv_Constr_Attach_FileEntity> listUpLoad = fileController
                .getAttachFileUploadTest(0, Constants.NUMBER_MAX_ITEM_SYNC_IMG);

        int iSizeDownLoad = listUpLoad.size();

        numberPutImage = fileController.getNumberAttachFileUpload();

        boolean bRequestNext = (iSizeDownLoad == Constants.NUMBER_MAX_ITEM_SYNC_IMG) ? true
                : false;
        // cuongdk3 add
        if (iSizeDownLoad > 0) {
            File fileSave = null;
            int chekcFileUp = 0;
            for (Supv_Constr_Attach_FileEntity itemFileUpload : listUpLoad) {
                fileSave = new File(GlobalInfo.getInstance().getFilePath()
                        + itemFileUpload.getFile_Path());

                if (fileSave.exists() && fileSave.length() > 0) {
                    itemFileUpload.setRequestNext(bRequestNext);
                    // itemFileUpload.setNumberUpload(0);
                    this.requestUpdateImage(e,
                            ActionEventConstant.REQUEST_UPDATEIMAGE,
                            itemFileUpload);
                    chekcFileUp++;
                }
            }
            if (chekcFileUp == 0) {
                percent = 100D;
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putDouble(SyncTask.KEY_PERCENT, percent);
                bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE,
                        "Syncing: Put Image");
                msg.setData(bundle);
                mHandler.sendMessage(msg);
                Log.e(TAG, "syncUpdateImage: 1 " );
                e.action = ActionEventConstant.REQEST_SYNC;
                ModelEvent model = new ModelEvent();
                model.setActionEvent(e);
                model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
                Home_Controller.getInstance().handleModelEvent(model);

            }

        }
		/* Neu update anh len server het thi thong bao thanh cong */
        else {
            percent = 100D;
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putDouble(SyncTask.KEY_PERCENT, percent);
            bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Syncing: Put Image");
            msg.setData(bundle);
            mHandler.sendMessage(msg);
            Log.e(TAG, "syncUpdateImage: 2 " );
            e.action = ActionEventConstant.REQEST_SYNC;
            ModelEvent model = new ModelEvent();
            model.setActionEvent(e);
            model.setModelCode(ErrorConstants.ERROR_CODE_SUCCESS);
            Home_Controller.getInstance().handleModelEvent(model);

        }
    }
}
