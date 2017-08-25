package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.SubWorkItemInfoView;
import com.viettel.gsct.View.WorkItemInfoView;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/11/17.
 */

public class InfoTiendoFragment extends BaseFragment {

    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private Unbinder unbinder;

    public static InfoTiendoFragment newInstance() {
        InfoTiendoFragment fragment = new InfoTiendoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();
        ArrayList<Work_ItemsEntity> workItems = work_itemsControler.getItems(constr_ConstructionItem.getConstructId());
        List<String> updateDates = new ArrayList<>();
        updateDates = work_itemsControler.getAllLogDate(constr_ConstructionItem.getConstructId());
        int childCount = rootLayout.getChildCount();
        if (workItems.isEmpty()) {
            for (int index = 0; index < childCount; index++) {
                rootLayout.getChildAt(index).setVisibility(View.GONE);
            }
            tvNoData.setVisibility(View.VISIBLE);
            return;
        }
        for (Work_ItemsEntity workItem : workItems) {
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(workItem.getWork_item_name(), workItem.getStarting_date(), workItem.getComplete_date());
            rootLayout.addView(view, childCount++);
            ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController.getItems(workItem.getId());
            for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
//                // Công trình GPON , hardcode
//                if (constr_ConstructionItem.getConstrType() == 394 && )

                Cat_Sub_Work_ItemEntity catSubWorkItem = cat_sub_work_itemControler.getCatSubWorkItem(subWorkItem.getCat_sub_work_item_id());
                SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                subView.setValue(catSubWorkItem.getName(), "", subWorkItem.getFinishDate());
                rootLayout.addView(subView, childCount++);
            }
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
}
