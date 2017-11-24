package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import java.util.Locale;

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
        etValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 11 && !editable.toString().contains(".")) {
                    etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else {
                    etValue.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }
                if (editable.toString().contains(".")) {
                    String subAfterDot = editable.toString().substring(editable.toString().indexOf(".") + 1);
                    if (editable.toString().endsWith(".") || subAfterDot.isEmpty()) {
                        etValue.setError("Không phải là số!");
                        etValue.requestFocus();
                    }
                }
            }
        });
    }

    public void setValue(String title, double value, double luyke, String donvi) {

        tvTitle.setText(title);
        if (luyke > 0) {
            if (luyke % 1 == 0) {
                tvLuyke.setText(String.valueOf((long)luyke));
            } else {
                tvLuyke.setText(String.format(Locale.UK,"%.2f",luyke));
            }
        }
        if (value > 0) {
            if (value % 1 == 0) {
                etValue.setText(String.valueOf((long)value));
            } else {
                etValue.setText(String.format(Locale.UK,"%.2f",value));
            }
        }
        tvDonvi.setText(donvi);
    }

    public void setLuyke(double luyke) {
        if (luyke > 0) {
            if (luyke % 1 == 0) {
                tvLuyke.setText(String.valueOf((long) luyke));
            } else {
                tvLuyke.setText(String.format(Locale.UK,"%.2f",luyke));
            }
        } else {
            tvLuyke.setText("");
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

    public void setEdtValueMaxLength(int maxLength) {
        InputFilter[] filter = new InputFilter[1];
        filter[0] = new InputFilter.LengthFilter(maxLength);
        etValue.setFilters(filter);
    }

    public void setEdtDataType(int type) {
        if (type == 0) {
            etValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else {
            etValue.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }
    }

    public void setEdtEnable(boolean enable) {
        etValue.setEnabled(enable);
    }

}
