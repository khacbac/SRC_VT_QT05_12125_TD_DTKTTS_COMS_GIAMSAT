package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.viettel.database.Cat_Constr_TeamController;
import com.viettel.database.Constr_Team_ProgressController;
import com.viettel.database.Constr_Work_LogsController;
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.entity.Cat_Constr_TeamEntity;
import com.viettel.database.entity.Constr_ObStructionEntity;
import com.viettel.database.entity.Constr_Team_ProgressEntity;
import com.viettel.database.entity.Constr_Work_LogsEntity;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.TeamInfoView;
import com.viettel.ktts.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/11/17.
 */

public class InfoNhatKyFragment extends BaseFragment implements AdapterView.OnItemSelectedListener{

    private final String TAG = this.getClass().getSimpleName();

//    @BindView(R.id.tv_date)
//    TextView tvDate;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.left_layout)
    LinearLayout leftLayout;
    @BindView(R.id.tv_thicong)
    TextView tvThicong;
    @BindView(R.id.tv_thoitiet)
    TextView tvThoitiet;
    @BindView(R.id.tv_thaydoi)
    TextView tvThaydoi;
    @BindView(R.id.tv_giam_sat)
    TextView tvGiamSat;
    @BindView(R.id.tv_y_kien_thi_cong)
    TextView tvYKienThiCong;
    @BindView(R.id.tvNoiDungCongViec)
    TextView tvNoiDungCongViec;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.spinner_action_swtich_date)
    AppCompatSpinner spinnerSwitchDate;
    @BindView(R.id.scrollForAllView)
    ScrollView mScrollAllView;
    private Unbinder unbinder;
    private List<String> allListLogDate;

    public static InfoNhatKyFragment newInstance() {
        InfoNhatKyFragment fragment = new InfoNhatKyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_nhat_ky, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        initSpinnerForSwitchLogDate();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();
        allListLogDate = new Constr_Work_LogsController((getContext()))
                .getListLogDate(constr_ConstructionItem.getConstructId());

        if (allListLogDate.size() <= 0) {
            mScrollAllView.setVisibility(View.GONE);
            return;
        }
        for (String logDate : allListLogDate) {
            Log.d(TAG, "initData: logDate = " + logDate);
        }
    }


    private void initDataWithDate(String date) {
        Constr_Work_LogsEntity entity = new Constr_Work_LogsController(getContext())
                .getItem(constr_ConstructionItem.getConstructId(), date);
        leftLayout.removeAllViews();
        int leftChildCount = 0;
        tvThicong.setText(entity.getIs_work() == 1 ? "Có" : "Không");
        if (entity.getIs_work() == 1) {
            Cat_Constr_TeamController controller = new Cat_Constr_TeamController(getContext());
            ArrayList<Constr_Team_ProgressEntity> arrTeamEntity
                    = new Constr_Team_ProgressController(getContext())
                    .getItems(entity.getConstr_work_logs_id());
            if (arrTeamEntity != null && arrTeamEntity.size() > 0) {
                for (Constr_Team_ProgressEntity team : arrTeamEntity) {
                    Cat_Constr_TeamEntity catConstrTeamEntity
                            = controller.getItem(team.getCat_constr_team_id());
                    if (catConstrTeamEntity != null) {
                        TeamInfoView view = new TeamInfoView(getContext());
                        view.setTitle(catConstrTeamEntity.getName());
                        view.setNumber(team.getNum_of_team() + "");
                        leftLayout.addView(view, leftChildCount++);
                    }
                }
            }
        } else {
            TeamInfoView view = new TeamInfoView(getContext());
            view.setTitle("Nguyên nhân");
            view.setNumber(getResources()
                    .getStringArray(R.array.array_bts_nguyen_nhan)[entity.getCat_cause_not_work_id()]);
            leftLayout.addView(view, leftChildCount++);
            Constr_ObStructionEntity obstructionEntity = new Supervision_CNVController(getContext())
                    .getItemConstrObStruction(entity.getConstr_work_logs_id());
            if (obstructionEntity != null) {
                TeamInfoView viewVuong = new TeamInfoView(getContext());
                viewVuong.setTitle("Loại vướng");
                if ((int) obstructionEntity.getConstrObStructionTypeId() <
                        getResources().getStringArray(R.array.array_bts_loai_vuong).length) {
                    viewVuong.setNumber(getResources().getStringArray(R.array.array_bts_loai_vuong)
                            [(int) obstructionEntity.getConstrObStructionTypeId()]);
                }
                leftLayout.addView(viewVuong, leftChildCount);
                ++leftChildCount;
            }
        }
        int id = entity.getCat_Weather_id() - 1;
        int length = getResources().getStringArray(R.array.array_bts_thoi_tiet).length;
        if (id < 0) id = 0;
        if (id > length) id = length; //
        tvThoitiet.setText(getResources().getStringArray(R.array.array_bts_thoi_tiet)[id]);
        tvNoiDungCongViec.setText(entity.getWork_content());
        tvThaydoi.setText(entity.getAddition_change_arise());
        tvYKienThiCong.setText(entity.getConstructor_comments());
        tvGiamSat.setText(entity.getMonitor_comments());
    }

    /**
     * Khoi tao spinner,voi danh sach la cac ngay cap nhat nhat ky gan nhat.
     */
    private void initSpinnerForSwitchLogDate() {
//        allListLogDate = new Constr_Work_LogsController((getContext()))
//                .getListLogDate(constr_ConstructionItem.getConstructId());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                allListLogDate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSwitchDate.setAdapter(adapter);
//        for (String str : allListLogDate) {
//            Log.d(TAG,"Date = " + GSCTUtils.standardlizeTime(str));
//        }
        spinnerSwitchDate.setOnItemSelectedListener(this);
        if(allListLogDate.contains(GSCTUtils.getDateNow())) {
            spinnerSwitchDate.setSelection(allListLogDate.indexOf(GSCTUtils.getDateNow()));
        } else {
            spinnerSwitchDate.setSelection(allListLogDate.size() - 1);
        }
    }

    @Override
    public void save() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String date = parent.getItemAtPosition(position).toString();
        Log.d(TAG,"Date = " + date + " -- Position: " + position);
        initDataWithDate(date);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
