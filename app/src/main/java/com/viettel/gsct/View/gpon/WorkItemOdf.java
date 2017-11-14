package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemOdf extends BaseCustomWorkItem {

    private static final String TAG = WorkItemOdf.class.getSimpleName();
    private View rootView;
    private Work_ItemsEntity wItemEntity;
    private ArrayList<WorkItemValueOdf> listValue = new ArrayList<>();

    public WorkItemOdf(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemOdf(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemOdf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    // Save odf indoor theo cong trinh.
    @Override
    public void save() {
        for (WorkItemValueOdf odf : listValue) {
            odf.save();
        }
    }

    // Save Odf outdoor theo node.
    @Override
    public void save(long nodeId) {
        for (WorkItemValueOdf odf : listValue) {
            odf.save(nodeId);
        }
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_odf,this);
        ButterKnife.bind(this);
    }

    public void addWorkItem(Work_ItemsEntity wItem) {
        this.wItemEntity = wItem;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public void addValueOdf(WorkItemValueOdf view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        view.addWIEntity(wItemEntity);
        listValue.add(view);
        this.addView(view);
    }

    public ArrayList<WorkItemValueOdf> getListValue() {
        return listValue;
    }

}
