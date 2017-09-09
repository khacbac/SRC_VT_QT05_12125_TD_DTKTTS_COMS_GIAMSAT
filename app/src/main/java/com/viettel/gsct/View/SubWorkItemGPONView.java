package com.viettel.gsct.View;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class SubWorkItemGPONView extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_value)
    AppCompatEditText etValue;
    @BindView(R.id.tv_luyke)
    TextView tvLuyke;
    @BindView(R.id.tv_donvi)
    TextView tvDonvi;

    public SubWorkItemGPONView(Context context) {
        super(context);
        init(context);
    }

    public SubWorkItemGPONView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubWorkItemGPONView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(final Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_gpon, this);
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
