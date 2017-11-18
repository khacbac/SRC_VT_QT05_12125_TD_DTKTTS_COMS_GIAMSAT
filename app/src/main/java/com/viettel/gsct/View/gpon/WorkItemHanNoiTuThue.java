package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemHanNoiTuThue extends BaseCustomWorkItem {

    private static final String TAG = WorkItemHanNoiTuThue.class.getSimpleName();
    private ArrayList<WorkItemValueHanNoiTuThue> listValue = new ArrayList<>();

    public WorkItemHanNoiTuThue(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemHanNoiTuThue(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemHanNoiTuThue(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    public void save(long nodeId) {
        for (WorkItemValueHanNoiTuThue tuThue : listValue) {
            tuThue.save(nodeId);
        }
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.custom_workitem_hannoi_tuthue,this);
        ButterKnife.bind(this);
    }

    public void addValueTuThue(WorkItemValueHanNoiTuThue view) {
        this.addView(view);
        listValue.add(view);
    }


    public void setFinish(boolean finish) {
        for (WorkItemValueHanNoiTuThue tuThue : listValue) {
            tuThue.setFinish(finish);
        }
    }

    public ArrayList<WorkItemValueHanNoiTuThue> getListValue() {
        return listValue;
    }
}
