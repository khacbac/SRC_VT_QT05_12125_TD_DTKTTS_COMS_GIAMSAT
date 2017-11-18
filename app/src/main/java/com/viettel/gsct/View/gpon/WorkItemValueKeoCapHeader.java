package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemValueKeoCapHeader extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueKeoCapHeader.class.getSimpleName();
    View rootView;
    @BindView(R.id.txtLoaiCap)
    TextView tvLoaiCap;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;
    @BindView(R.id.layoutForItem)
    LinearLayout layoutForItem;

    private ArrayList<WorkItemValueKeoCap> listValue = new ArrayList<>();
    private Work_ItemsEntity wParentItem;
    private Work_ItemsEntity wItemEntity;

    public WorkItemValueKeoCapHeader(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueKeoCapHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueKeoCapHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_keocap_header_gpon, this);
        ButterKnife.bind(this);
        btnCollapse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutForItem.setVisibility(layoutForItem.getVisibility() == VISIBLE ? GONE : VISIBLE);
            }
        });
    }

    public void setTvLoaiCap(String tvLoaiCap) {
        this.tvLoaiCap.setText(tvLoaiCap);
    }

    public String getTenLoaiCap() {
        return tvLoaiCap.getText().toString();
    }

    public void addItemValue(WorkItemValueKeoCap view) {
        layoutForItem.addView(view);
        listValue.add(view);
    }

    // Truong hop dac biet.
    // Work item kep cap co chua 2 work item khac.
    public void addParentWorkItem(Work_ItemsEntity item) {
        this.wParentItem = item;
    }

    // Them work item Cap quang so 8 hoac Adss.
    public void addWorkItem(Work_ItemsEntity item) {
        this.wItemEntity = item;
        for (WorkItemValueKeoCap keoCap : listValue) {
            keoCap.addWIEntity(wItemEntity);
        }
    }

    @Override
    public boolean validate() {
        for (WorkItemValueKeoCap kcValue : listValue) {
            if (!kcValue.validate()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void save(long nodeId) {
        if (!listValue.isEmpty()) {
            for (WorkItemValueKeoCap kcValue : listValue) {
                kcValue.save(nodeId);
            }
        }
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public ArrayList<WorkItemValueKeoCap> getListValue() {
        return listValue;
    }

}
