package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
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

public class WorkItemValueOdf extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_ten_odf)
    TextView tvTenOdf;
    @BindView(R.id.tv_khoiluong)
    AppCompatEditText edtKhoiLuong;
    @BindView(R.id.tv_luyke)
    TextView tvLuyKe;

    private ArrayList<View> views = new ArrayList<>();

    private Work_ItemsEntity workItem;

    private WorkItemRightGPONView.FinishListener listener;
    private WorkItemRightGPONView.OnStatusBtnTienDo statusTienDo;

    public WorkItemValueOdf(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueOdf(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueOdf(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_lapdat_odf_gpon, this);
        ButterKnife.bind(this);
    }

    public void setTvTenOdf(String tvTenOdf) {
        this.tvTenOdf.setText(tvTenOdf);
    }

    public void setTvLuyKe(double luyKe) {
        this.tvLuyKe.setText(String.valueOf(luyKe));
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf(khoiLuong));
    }

    public String getTvTenOdf() {
        return tvTenOdf.getText().toString().trim();
    }
}
