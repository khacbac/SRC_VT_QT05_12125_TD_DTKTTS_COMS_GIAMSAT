package com.viettel.gsct.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.info.SubWorkItemInfoView;
import com.viettel.gsct.View.info.WorkItemInfoView;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/11/17.
 */

public class InfoTiendoFragment extends BaseTienDoFragment {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;
    @BindView(R.id.bottomChildLayout)
    LinearLayout bottomChildLayout;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.layoutKhoiLuong)
    LinearLayout layoutKhoiLuong;
    private Unbinder unbinder;

    public static InfoTiendoFragment newInstance() {
        InfoTiendoFragment fragment = new InfoTiendoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();

        ArrayList<String> listColumn = work_itemsControler.getListColumn();
        for (String column : listColumn) {
            Log.d(TAG, "initData: column = " + column);
        }
//        ArrayList<String> lisNodeId = work_itemsControler.getConstrNodeId();
//        if (!lisNodeId.isEmpty()) {
//            for (String nodeId : lisNodeId) {
//                Log.d(TAG, "initData: node id = " + nodeId);
//            }
//        }



        ArrayList<Work_ItemsEntity> workItems = work_itemsControler.getItems(
                constr_ConstructionItem.getConstructId());
        Log.d(TAG,"Construct Id = " + constr_ConstructionEmployeeItem.getSupvConstrType());
        for (Work_ItemsEntity entity : workItems) {
            Log.d(TAG,"Work item name = " + entity.getWork_item_name());
            Log.d(TAG, "Work item update date = " + entity.getUpdate_date());
        }
        int childCount = rootLayout.getChildCount();
        if (workItems.isEmpty()) {
            for (int index = 0; index < childCount; index++) {
                rootLayout.getChildAt(index).setVisibility(View.GONE);
            }
            tvNoData.setVisibility(View.VISIBLE);
            return;
        }
        int bottomChildCount = 0;
        bottomLayout.removeAllViews();
        for (Work_ItemsEntity workItem : workItems) {
            if (workItem.isCompleted()) {
                Log.d(TAG, "initData() called" + " work item complete = "
                        + workItem.getWork_item_name());
            }
            Log.d(TAG, "initData() called" + " Work item name = " + workItem.getWork_item_name());
            if (workItem.hasStartedDate() && !workItem.getWork_item_name().equals("")) {
                WorkItemInfoView view = new WorkItemInfoView(getContext());
                view.setValue(
                        workItem.getWork_item_name(),
                        workItem.getStarting_date(),
                        workItem.getComplete_date()
                );
                if (constr_ConstructionEmployeeItem.getSupvConstrType() == Constants.CONSTR_TYPE.BTS ||
                        constr_ConstructionEmployeeItem.getSupvConstrType() == Constants.CONSTR_TYPE.SH) {
                    view.setHienThiCotKhoiLuong(false);
                    layoutKhoiLuong.setVisibility(View.GONE);
                }
                bottomLayout.addView(view, bottomChildCount);
                bottomChildCount++;
                ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController
                        .getItems(workItem.getId());
                if (subWorkItems.size() > 0) {
                    Log.d(TAG, "Sub work item list Size = " + subWorkItems.size());
                }
                for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
//                // Công trình GPON , hardcode
//                if (constr_ConstructionItem.getConstrType() == 394 && )
                    Log.d(TAG, "initData: loop Sukwork item entity code = " + subWorkItem.getCode());
                    Cat_Sub_Work_ItemEntity catSubWorkItem = cat_sub_work_itemControler
                            .getCatSubWorkItem(subWorkItem.getCat_sub_work_item_id());
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    double luyke = new Sub_Work_Item_ValueController(getContext())
                            .getLuyke(subWorkItem.getWork_item_id(),
                                    subWorkItem.getCat_sub_work_item_id());
                    subView.setValue(
                            catSubWorkItem.getName(),
                            String.format(Locale.UK,"%.2f",luyke),
                            "",
                            subWorkItem.getFinishDate()
                    );
                    if (luyke % 1 == 0) {
                        subView.setLuyKe(String.valueOf((long)luyke));
                    }
                    if (constr_ConstructionEmployeeItem.getSupvConstrType() == Constants.CONSTR_TYPE.BTS ||
                            constr_ConstructionEmployeeItem.getSupvConstrType() == Constants.CONSTR_TYPE.SH) {
                        subView.setHienThiCotKhoiLuong(false);
                    }
                    Log.d(TAG, "Luy ke = " + luyke);
                    bottomLayout.addView(subView, bottomChildCount);
                    bottomChildCount++;
                }
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
