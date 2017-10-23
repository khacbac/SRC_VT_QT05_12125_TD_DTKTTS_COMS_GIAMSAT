package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemValueKeoCap extends LinearLayout {

    private static final String TAG = WorkItemValueKeoCap.class.getSimpleName();
    View rootView;
    @BindView(R.id.tv_item_loaicap)
    TextView tvItemLoaiCap;
    @BindView(R.id.tv_khoiluong)
    EditText edtKhoiLuong;
    @BindView(R.id.tv_dvt)
    TextView tvDvt;
    @BindView(R.id.tv_luyke)
    TextView tvLuyKe;

    private Sub_Work_Item_ValueEntity swiValue;
    private double mKhoiLuong = 0;
    private Sub_Work_Item_ValueController swiValueController;

    public WorkItemValueKeoCap(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueKeoCap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueKeoCap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_keocap_item_gpon, this);
        ButterKnife.bind(this);
        swiValueController = new Sub_Work_Item_ValueController(context);
    }

    public void setTvItemLoaiCap(String tvItemLoaiCap) {
        this.tvItemLoaiCap.setText(tvItemLoaiCap);
    }

    public void setTvDvt(String tvDvt) {
        this.tvDvt.setText(tvDvt);
    }

    public void setTvLuyKe(double luyKe) {
        this.tvLuyKe.setText(String.valueOf(luyKe));
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        this.mKhoiLuong = khoiLuong;
        edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf(khoiLuong));
    }

    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
    }

    public void saveSWIItemToDB() {
        if (swiValue == null) {
            swiValue = new Sub_Work_Item_ValueEntity();
        }
        swiValue.setValue(mKhoiLuong);
        swiValue.setAdded_date(GSCTUtils.getDateNow());
        swiValueController.addItem(swiValue);

    }
}
