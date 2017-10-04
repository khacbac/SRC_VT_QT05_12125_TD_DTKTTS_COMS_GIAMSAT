package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
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

public class WorkItemRightKeoCapItemGpon extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_item_loaicap)
    TextView tvItemLoaiCap;
    @BindView(R.id.tv_khoiluong)
    EditText edtKhoiLuong;
    @BindView(R.id.tv_dvt)
    TextView tvDvt;
    @BindView(R.id.tv_luyke)
    TextView tvLuyKe;

    public WorkItemRightKeoCapItemGpon(Context context) {
        super(context);
        init(context);
    }

    public WorkItemRightKeoCapItemGpon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemRightKeoCapItemGpon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_keocap_item_gpon, this);
        ButterKnife.bind(this);
    }

    public void setTvItemLoaiCap(String tvItemLoaiCap) {
        this.tvItemLoaiCap.setText(tvItemLoaiCap);
    }

    public void setTvDvt(String tvDvt) {
        this.tvDvt.setText(tvDvt);
    }

    public void setTvLuyKe(String tvLuyKe) {
        this.tvLuyKe.setText(tvLuyKe);
    }

}
