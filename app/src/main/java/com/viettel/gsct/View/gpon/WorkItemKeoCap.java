package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemKeoCap extends BaseCustomWorkItem {

    private static final String TAG = WorkItemKeoCap.class.getSimpleName();
    private View rootView;
    private WorkItemValueKeoCapHeader wIVKCHeaderCapSo8;
    private WorkItemValueKeoCapHeader wIVKCHeaderCapAdss;
    private boolean isWIFinish = false;
    private Work_ItemsEntity wItemEntity;

    public WorkItemKeoCap(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemKeoCap(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemKeoCap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    public boolean isValidate() {
        return wIVKCHeaderCapSo8.isValidate() && wIVKCHeaderCapAdss.isValidate();
    }

    @Override
    public boolean isWorking() {
        return wIVKCHeaderCapSo8.isWorking() || wIVKCHeaderCapAdss.isWorking();
    }

    @Override
    public boolean isFinish() {
        return wIVKCHeaderCapSo8.isFinish() && wIVKCHeaderCapAdss.isFinish();
    }

    @Override
    public void save(long nodeId) {
        Log.d(TAG, "save: called");

        if (wIVKCHeaderCapSo8 != null) {
            wIVKCHeaderCapSo8.save(nodeId);
        }

        if (wIVKCHeaderCapAdss != null) {
            wIVKCHeaderCapAdss.save(nodeId);
        }

        if (wItemEntity != null) {
            if (!wItemEntity.hasStartedDate()) {
                if (isWorking()) {
                    wItemEntity.setStarting_date(GSCTUtils.getDateNow());
                }
            }
            if (isFinish()) {
                if (!wItemEntity.hasCompletedDate()) {
                    wItemEntity.setComplete_date(GSCTUtils.getDateNow());
                }
            }
            Work_ItemsControler.getInstance(getContext()).updateItem(wItemEntity);
        }
    }

    @Override
    public void updateTrangThai() {
        wIVKCHeaderCapSo8.updateTrangThai();
        wIVKCHeaderCapAdss.updateTrangThai();
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_keocap,this);
        ButterKnife.bind(this);
    }

    public void addWorkItem(Work_ItemsEntity wItem){
        this.wItemEntity = wItem;
    }

    public void addCapQuangSo8(WorkItemValueKeoCapHeader view) {
        wIVKCHeaderCapSo8 = view;
        wIVKCHeaderCapSo8.addParentWorkItem(wItemEntity);
        this.addView(wIVKCHeaderCapSo8);
    }

    public void addCapQuangAdss(WorkItemValueKeoCapHeader view) {
        wIVKCHeaderCapAdss = view;
        wIVKCHeaderCapAdss.addParentWorkItem(wItemEntity);
        this.addView(wIVKCHeaderCapAdss);
    }

    public WorkItemValueKeoCapHeader getwIVKCHeaderCapSo8() {
        return wIVKCHeaderCapSo8;
    }

    public WorkItemValueKeoCapHeader getwIVKCHeaderCapAdss() {
        return wIVKCHeaderCapAdss;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }
}
