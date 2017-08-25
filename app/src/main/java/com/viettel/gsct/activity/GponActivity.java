package com.viettel.gsct.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.gsct.fragment.BtsNhatkyFragment;
import com.viettel.gsct.fragment.GPONTiendoFragment;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.base.SupervisionBtsBaseActivity;

import butterknife.BindView;

/**
 * Created by admin2 on 04/04/2017.
 */

public class GponActivity extends HomeBaseActivity {
    private static final String TAG = "GponActivity";
    public static final String ARG_INFO = "SupervisionBtsBaseActivity.INFO";

    @BindView(R.id.tv_tram)
    TextView tvTram;
    @BindView(R.id.tv_ma_cong_trinh)
    TextView tvMaCongTrinh;
    @BindView(R.id.sp_thong_tin)
    AppCompatSpinner spThongTin;

    private Constr_Construction_EmployeeEntity constr_ConstructionItem;
    private Supervision_BtsEntity btsEntity;
    private boolean flagFirstTime = true;
    private int infoId = 0;
    private BaseFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bts);
        addView(R.layout.activity_bts_thietket, R.id.root_view);
        initVariables();
        initViews();
        initHeader();
    }

    public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
        return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret = super.onCreateOptionsMenu(menu);
        mSave.setVisible(true);
        return ret;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoHomeActivity(new Bundle());
        finish();
    }

    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:
                // SyncModel.bRunning = false;

                SyncTask.closeDialog();

                Toast.makeText(this,
                        StringUtil.getString(R.string.text_sync_success),
                        Toast.LENGTH_LONG).show();
                break;
            default:
                super.handleModelViewEvent(modelEvent);
                break;
        }
    }

    private void initVariables() {
        constr_ConstructionItem = getConstr_Construction_Employee();
        infoId = getIntent().getExtras().getInt(ARG_INFO, 0);
    }

    private void initHeader() {
        setTitle(getString(R.string.line_background_header_title_brcd_mt));
        tvTram.setText(constr_ConstructionItem
                .getStationCode());
        tvMaCongTrinh.setText(String.valueOf(constr_ConstructionItem
                .getConstrCode()));

        Supervision_BtsController bts_Controller = new Supervision_BtsController(
                this);
        btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
                .getSupervision_Constr_Id());
    }


    private void initViews() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_gpon_info, R.layout.spinner_textview);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spThongTin.setAdapter(adapter);
        spThongTin.setSelection(infoId - 1);
        if (infoId == Constants.BTS_INFO.NHAT_KY_INFO) {
            fragment = BtsNhatkyFragment.newInstance();
        } else if (infoId == Constants.BTS_INFO.TIEN_DO_INFO) {
            fragment = GPONTiendoFragment.newInstance();
        }

        if (fragment != null) {
            fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragment).commit();
        }
        spThongTin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundleMonitorData = new Bundle();
                bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA, constr_ConstructionItem);
                int isInfomation = position + 1;
                bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO, isInfomation);
                if (flagFirstTime) {
                    flagFirstTime = false;
                    return;
                }
                switch (isInfomation) {
                    case Constants.BTS_INFO.NHAT_KY_INFO:
                        fragment = BtsNhatkyFragment.newInstance();
                        fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragment).commit();
                        break;
                    case Constants.BTS_INFO.TIEN_DO_INFO:
                        fragment = GPONTiendoFragment.newInstance();
                        fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragment).commit();
                        break;
                    default:
                        gotoBRCDActivityFromFragment(bundleMonitorData);
//                        ActionEvent e = new ActionEvent();
//                        bundleMonitorData.putInt(IntentConstants.INTENT_MEASURE_TYPE,
//                                Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_LINE_BG);
//                        e.viewData = bundleMonitorData;
//                        e.action = ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY;
//                        e.sender = this;
//                        Home_Controller.getInstance().handleSwitchActivity(e);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (item.getItemId() == R.id.action_save && fragment != null) {
            fragment.save();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
