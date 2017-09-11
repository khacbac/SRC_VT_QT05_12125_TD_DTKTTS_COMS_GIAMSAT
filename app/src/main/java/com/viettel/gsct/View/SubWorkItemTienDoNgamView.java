package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class SubWorkItemTienDoNgamView extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_value)
    AppCompatEditText etValue;
    Sub_Work_ItemEntity entity;
    @BindView(R.id.tv_luyke)
    TextView tvLuyke;
    @BindView(R.id.tv_don_vi)
    TextView tvDonVi;

    public SubWorkItemTienDoNgamView(Context context) {
        super(context);
        init(context);
    }

    public SubWorkItemTienDoNgamView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubWorkItemTienDoNgamView(Context context,
                                     @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        rootView = inflate(context, R.layout.layout_sub_work_item_tien_do_ngam, this);
        ButterKnife.bind(this);
    }

    public void setSubWorkItemEntity(Sub_Work_ItemEntity entity,
                                     String title, double value, double luyke, String donvi) {
        this.entity = entity;
        tvTitle.setText(title);
        if (value > 0)
            etValue.setText(String.valueOf(value));
        if (luyke > 0)
            tvLuyke.setText(String.valueOf(luyke));
        tvDonVi.setText(donvi);
    }

    public void setLuyke(double luyke) {
        if (luyke > 0)
            tvLuyke.setText(String.valueOf(luyke));
        else
            tvLuyke.setText("");
    }

    public double getValue() {
        String ret = etValue.getEditableText().toString();
        if (ret.length() == 0) return 0L;
        try {
            return Double.parseDouble(ret);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public double getLuyKe() {
        String ret = tvLuyke.getText().toString();
        if (ret.equals("")) return 0L;
        try {
            return Double.parseDouble(ret);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public void setEditeTextEnable(boolean enable) {
        etValue.setEnabled(enable);
    }

    public void setTextChangeListener(TextWatcher listener) {
        etValue.addTextChangedListener(listener);
    }

    public String getTvTitle() {
        return tvTitle.getText().toString();
    }

    public String getEtValueNumber() {
        return etValue.getEditableText().toString();
    }

    public String getTvLuykeNumber() {
        return tvLuyke.getText().toString();
    }

    public double getKhoiLuong() {
        return getLuyKe() + getValue();
    }
}
