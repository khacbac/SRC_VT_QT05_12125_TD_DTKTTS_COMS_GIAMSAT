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
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.gsct.fragment.BtsNhatkyFragment;
import com.viettel.gsct.fragment.BtsTiendoFragment;
import com.viettel.ktts.HomeActivity;
import com.viettel.ktts.R;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;

import butterknife.BindView;

/**
 * Created by admin2 on 04/04/2017.
 */

public class BtsActivity extends SupervisionBtsBaseActivity {
    private static final String TAG = "BtsActivity";
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoHomeActivity(new Bundle());
        finish();
    }

    private void initVariables() {
        constr_ConstructionItem = getConstr_Construction_Employee();
        infoId = getIntent().getExtras().getInt(ARG_INFO, 0);
    }

    private void initHeader() {
        setTitle(getString(R.string.supervision_bts_title));
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
                R.array.array_bts_info, R.layout.spinner_textview);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spThongTin.setAdapter(adapter);
        spThongTin.setSelection(infoId - 1);
        if (infoId == Constants.BTS_INFO.NHAT_KY_INFO) {
            fragment = BtsNhatkyFragment.newInstance();
        } else if (infoId == Constants.BTS_INFO.TIEN_DO_INFO) {
            fragment = BtsTiendoFragment.newInstance();
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
                    case Constants.BTS_INFO.THIET_TKE_INFO:
                        gotoSupervisionBtsActivity(bundleMonitorData);
                        break;
                    case Constants.BTS_INFO.NHAT_KY_INFO:
                        fragment = BtsNhatkyFragment.newInstance();
                        fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragment).commit();
                        break;
                    case Constants.BTS_INFO.TIEN_DO_INFO:
                        fragment = BtsTiendoFragment.newInstance();
                        fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionItem);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, fragment).commit();
                        break;
                    case Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO:
                        gotoSupervisionBtsPillarActivity(bundleMonitorData);
                        break;
                    case Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO:
                        gotoSupervisionBtsCatWorkActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.KEO_DIEN_INFO:
                        gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO:
                        gotoSupervisionBtsEquipActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.LAP_DAT_VIBA_INFO:
                        gotoSupervisionBtsVibaActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO:
                        gotoSupervisionBtsMeasureActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.MEASURE_CONSTR_INFO:
                        gotoSupervisionMeasureConstrActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG:
                        gotoSupervisionCNDTCActivity(bundleMonitorData);

                        break;
                    case Constants.BTS_INFO.CAP_NHAT_VUONG:
                        gotoSupervisionCNVCActivity(bundleMonitorData);
                        break;
                    case Constants.BTS_INFO.KET_LUAN_INFO:
                        gotoSupervisionBtsKLActivity(bundleMonitorData);

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
