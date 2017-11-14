package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Truy Menh.
 * 11/2/2017.
 */

public class WorkItemOltAndDoKiem extends BaseCustomWorkItem {

    private static final String TAG = WorkItemOltAndDoKiem.class.getSimpleName();
    private View rootView;
    private Work_ItemsEntity wItemEntity;
    private ArrayList<WorkItemValueOltDoKiem> listItemValue = new ArrayList<>();

    public WorkItemOltAndDoKiem(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemOltAndDoKiem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemOltAndDoKiem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    // Save OLT theo cong trinh.
    @Override
    public void save() {
        if (!listItemValue.isEmpty()) {
            for (WorkItemValueOltDoKiem item : listItemValue) {
                item.save();
            }
        }
    }

    // Save do kiem theo node.
    @Override
    public void save(long nodeId) {
        if (!listItemValue.isEmpty()) {
            for (WorkItemValueOltDoKiem item : listItemValue) {
                item.save(nodeId);
            }
        }
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_olt_and_dokiem,this);
        ButterKnife.bind(this);
    }

    public void addWorkitem(Work_ItemsEntity wItem) {
        this.wItemEntity = wItem;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public void addValueItem(WorkItemValueOltDoKiem view) {
        this.addView(view);
        listItemValue.add(view);
    }

    public ArrayList<WorkItemValueOltDoKiem> getListItemValue() {
        return listItemValue;
    }
}
