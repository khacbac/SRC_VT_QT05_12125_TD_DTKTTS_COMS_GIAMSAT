package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
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

public class WorkItemRightOltDoKiemGpon extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;

    private ArrayList<View> views = new ArrayList<>();

    private Work_ItemsEntity workItem;

    private WorkItemRightGPONView.FinishListener listener;
    private WorkItemRightGPONView.OnStatusBtnTienDo statusTienDo;

    public WorkItemRightOltDoKiemGpon(Context context) {
        super(context);
        init(context);
    }

    public WorkItemRightOltDoKiemGpon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemRightOltDoKiemGpon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_olt_dokiem_gpon, this);
        ButterKnife.bind(this);

        final String chuaLam = getResources().getString(R.string.str_tiendo_chualam);
        final String hoanThanh = getResources().getString(R.string.str_tiendo_hoanthanh);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienDo = btnTienDo.getText().toString().trim();
                btnTienDo.setText(tienDo.equalsIgnoreCase(chuaLam) ? hoanThanh : chuaLam);
            }
        });
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle.setText(tvTitle);
    }
}
