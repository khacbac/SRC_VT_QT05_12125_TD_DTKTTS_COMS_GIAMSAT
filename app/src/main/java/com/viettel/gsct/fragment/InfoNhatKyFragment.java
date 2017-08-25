package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/11/17.
 */

public class InfoNhatKyFragment extends BaseFragment {

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
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
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private Unbinder unbinder;

    public static InfoNhatKyFragment newInstance() {
        InfoNhatKyFragment fragment = new InfoNhatKyFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_nhat_ky, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();
        Constr_Work_LogsEntity entity = new Constr_Work_LogsController(getContext()).getItem(constr_ConstructionItem.getConstructId(), GSCTUtils.getDateNow());
        tvDate.setText(GSCTUtils.getDateNow("dd-MM-yyyy"));
        int childCount = rootLayout.getChildCount();
        if (entity == null) {
            for (int index = 0; index < childCount; index++) {
                rootLayout.getChildAt(index).setVisibility(View.GONE);
            }
            tvNoData.setVisibility(View.VISIBLE);
            return;
        }
        tvDate.setText(GSCTUtils.standardlizeTime(entity.getLog_date()));
        tvThicong.setText(entity.getIs_work() == 1 ? "Có" : "Không");

        if (entity.getIs_work() == 1) {
            Cat_Constr_TeamController controller = new Cat_Constr_TeamController(getContext());
            ArrayList<Constr_Team_ProgressEntity> arrTeamEntity = new Constr_Team_ProgressController(getContext()).getItems(entity.getConstr_work_logs_id());
            if (arrTeamEntity != null && arrTeamEntity.size() > 0) {
                for (Constr_Team_ProgressEntity team : arrTeamEntity) {
                    Cat_Constr_TeamEntity catConstrTeamEntity = controller.getItem(team.getCat_constr_team_id());
                    if (catConstrTeamEntity != null) {
                        TeamInfoView view = new TeamInfoView(getContext());
                        view.setTitle(catConstrTeamEntity.getName());
                        view.setNumber(team.getNum_of_team() + "");
                        rootLayout.addView(view, childCount++);
                    }
                }
            }
        } else {
            TeamInfoView view = new TeamInfoView(getContext());
            view.setTitle("Nguyên nhân");
            view.setNumber(getResources().getStringArray(R.array.array_bts_nguyen_nhan)[entity.getCat_cause_not_work_id()]);
            rootLayout.addView(view, childCount++);
            Constr_ObStructionEntity obstructionEntity = new Supervision_CNVController(getContext()).getItemConstrObStruction(entity.getConstr_work_logs_id());
            if (obstructionEntity != null) {
                TeamInfoView viewVuong = new TeamInfoView(getContext());
                viewVuong.setTitle("Loại vướng");
                viewVuong.setNumber(getResources().getStringArray(R.array.array_bts_loai_vuong)[(int) obstructionEntity.getConstrObStructionTypeId()]);
                rootLayout.addView(viewVuong, childCount++);
            }
        }
        int id = entity.getCat_Weather_id() - 1;
        int length = getResources().getStringArray(R.array.array_bts_thoi_tiet).length;
        if (id < 0) id = 0;
        if (id > length) id = length; //
        tvThoitiet.setText(getResources().getStringArray(R.array.array_bts_thoi_tiet)[id]);
        tvThaydoi.setText(entity.getWork_content());
        tvYKienThiCong.setText(entity.getConstructor_comments());
        tvGiamSat.setText(entity.getMonitor_comments());
    }

    private static final String TAG = "InfoNhatKyFragment";

    @Override
    public void save() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
