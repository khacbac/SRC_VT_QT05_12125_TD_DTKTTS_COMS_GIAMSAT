package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.Plan_Constr_DetailController;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.TeamInfoView;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/10/17.
 */

public class InfoKeHoachFragment extends BaseFragment {
    private static final String TAG = "InfoChiTietFragment";
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.tv_plan_code)
    TextView tvPlanCode;
    @BindView(R.id.tv_finish_date)
    TextView tvFinishDate;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private Unbinder unbinder;

    @Override
    public void save() {

    }

    public static InfoKeHoachFragment newInstance() {
        InfoKeHoachFragment fragment = new InfoKeHoachFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_kehhoach, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();
        tvDate.setText(GSCTUtils.getDateNow("dd-MM-yyyy"));
        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());
        ArrayList<Plan_Constr_DetailController.PlanEntity> plans = planController.getPlans(constr_ConstructionItem.getConstructId());
        int childCount = rootLayout.getChildCount();
        if (plans.isEmpty()) {
            for (int index = 0; index < childCount; index++) {
                rootLayout.getChildAt(index).setVisibility(View.GONE);
            }
            tvNoData.setVisibility(View.VISIBLE);
            return;
        }
        for (Plan_Constr_DetailController.PlanEntity plan : plans) {
//            Log.e(TAG, "initData: " + plan.planName + " " + plan.finishDate);
            TeamInfoView view = new TeamInfoView(getContext());
            view.setTitle(plan.tenmoc);
            view.setNumber(plan.finishDate);
            rootLayout.addView(view, childCount++);
            tvPlanCode.setText(plan.planCode);
            tvFinishDate.setText(plan.endDate);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
