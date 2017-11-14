package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
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

    public TextView getTvLoaiCap() {
        return tvLoaiCap;
    }

    public void setTvLoaiCap(String tvLoaiCap) {
        this.tvLoaiCap.setText(tvLoaiCap);
    }

    public String getTenLoaiCap() {
        return tvLoaiCap.getText().toString();
    }

    public Button getBtnCollapse() {
        return btnCollapse;
    }

    public void setBtnCollapse(Button btnCollapse) {
        this.btnCollapse = btnCollapse;
    }

    public void addItemValue(WorkItemValueKeoCap view) {
//        this.wIValue = view;
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

    public Work_ItemsEntity getwParentItem() {
        return wParentItem;
    }


    @Override
    public void save(long nodeId) {
        if (!listValue.isEmpty()) {
            for (WorkItemValueKeoCap kcValue : listValue) {
                kcValue.save(nodeId);
            }
        }
    }

    // Ham tra ve so luong luy ke sau khi luu.Dung de hien thi phan preview.
    public double getAllValueAlterSave() {
        double sum = 0;
        if (!listValue.isEmpty()) {
            for (WorkItemValueKeoCap kcValue : listValue) {
                sum += (kcValue.getAllValueAlterSave());
            }
        }
        return sum;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public ArrayList<WorkItemValueKeoCap> getListValue() {
        return listValue;
    }

}
