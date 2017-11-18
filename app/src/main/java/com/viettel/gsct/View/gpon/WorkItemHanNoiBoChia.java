package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;

import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemHanNoiBoChia extends BaseCustomWorkItem {

    private ArrayList<WorkItemValueHanNoiBoChia> listValue = new ArrayList<>();

    public WorkItemHanNoiBoChia(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemHanNoiBoChia(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemHanNoiBoChia(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    public void save(long nodeId) {
        for (WorkItemValueHanNoiBoChia boChia : listValue) {
            boChia.save(nodeId);
        }
    }


    private void initData(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.custom_workitem_hannoi_bochia,this);
        ButterKnife.bind(this);
    }

    public void addValueBoChia(WorkItemValueHanNoiBoChia view) {
        this.addView(view);
        listValue.add(view);
    }

    public void setFinish(boolean finish) {
        for (WorkItemValueHanNoiBoChia boChia : listValue) {
            boChia.setFinish(finish);
        }
    }

    public ArrayList<WorkItemValueHanNoiBoChia> getListValue() {
        return listValue;
    }
}
