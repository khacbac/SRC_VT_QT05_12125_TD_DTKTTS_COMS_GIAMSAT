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

public class WorkItemRightKeoCapHeaderGpon extends LinearLayout {

    View rootView;
    @BindView(R.id.txtLoaiCap)
    TextView tvLoaiCap;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;
    @BindView(R.id.layoutForItem)
    LinearLayout layoutForItem;

    private ArrayList<View> views = new ArrayList<>();

    private Work_ItemsEntity workItem;

    private WorkItemRightGPONView.FinishListener listener;
    private WorkItemRightGPONView.OnStatusBtnTienDo statusTienDo;

    public WorkItemRightKeoCapHeaderGpon(Context context) {
        super(context);
        init(context);
    }

    public WorkItemRightKeoCapHeaderGpon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemRightKeoCapHeaderGpon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
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

    public Button getBtnCollapse() {
        return btnCollapse;
    }

    public void setBtnCollapse(Button btnCollapse) {
        this.btnCollapse = btnCollapse;
    }

    public void addItemForLoaiCap(View view) {
        layoutForItem.addView(view);
    }
}
