package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemHanNoi extends BaseCustomWorkItem {

    private static final String TAG = WorkItemHanNoi.class.getSimpleName();
    private View rootView;
    @BindView(R.id.lBoChia)
    WorkItemHanNoiBoChia lBoChia;
    @BindView(R.id.lTuThue)
    WorkItemHanNoiTuThue lTuThue;

    private Work_ItemsEntity wItemEntity;

    public WorkItemHanNoi(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemHanNoi(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemHanNoi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }


    @Override
    public void save(long nodeId) {
        lBoChia.save(nodeId);
        lTuThue.save(nodeId);
    }


    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_hannoi,this);
        ButterKnife.bind(this);

    }

    public void addWorkItem(Work_ItemsEntity entity) {
        this.wItemEntity = entity;
    }

    public void addValueBoChia(WorkItemValueHanNoiBoChia view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        lBoChia.addValueBoChia(view);
    }

    public void addValueTuThue(WorkItemValueHanNoiTuThue view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        lTuThue.addValueTuThue(view);
    }

    public WorkItemHanNoiBoChia getlBoChia() {
        return lBoChia;
    }

    public WorkItemHanNoiTuThue getlTuThue() {
        return lTuThue;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

}
