package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class SubWorkItemGponOldView extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_value)
    AppCompatEditText etValue;
    @BindView(R.id.tv_luyke)
    TextView tvLuyke;
    @BindView(R.id.tv_donvi)
    TextView tvDonvi;

    public SubWorkItemGponOldView(Context context) {
        super(context);
        init(context);
    }

    public SubWorkItemGponOldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubWorkItemGponOldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_gpon_old, this);
        ButterKnife.bind(this);
    }

    public void setValue(String title, double value, double luyke, String donvi) {

        tvTitle.setText(title);
        if (luyke > 0)
            tvLuyke.setText(String.valueOf(luyke));
        if (value > 0)
            etValue.setText(String.valueOf(value));
        tvDonvi.setText(donvi);
    }

    public void setLuyke(double luyke) {
        if (luyke > 0)
            tvLuyke.setText(String.valueOf(luyke));
        else
            tvLuyke.setText("");
    }


    public double getValue() {
        String value = etValue.getEditableText().toString();
        double ret = 0;
        if (value.length() > 0) {
            try {
                ret = Double.parseDouble(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

    public String getTvTitle() {
        return tvTitle.getText().toString();
    }

    public AppCompatEditText getEtValue() {
        return etValue;
    }

}
