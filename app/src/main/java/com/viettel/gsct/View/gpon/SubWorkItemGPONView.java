package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class SubWorkItemGPONView extends LinearLayout {

    private static final String TAG = SubWorkItemGPONView.class.getSimpleName();
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_value)
    AppCompatEditText etValue;
    @BindView(R.id.tv_luyke)
    TextView tvLuyke;
    @BindView(R.id.tv_donvi)
    TextView tvDonvi;
    @BindView(R.id.rb_check)
    AppCompatRadioButton radioBtnCheck;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;

    private WorkItemGPONView workItemGPONView;
    private IeOnRadioCheckChangedListener ieOnRadioCheckChangedListener;

    private ArrayList<View> listSWValue = new ArrayList<>();

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

        radioBtnCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (workItemGPONView != null) {
                    ieOnRadioCheckChangedListener.onRadioCheckChange(isChecked,
                            radioBtnCheck, SubWorkItemGPONView.this);
                }
                if (isChecked) {
                    for (View view : listSWValue) {
                        view.setVisibility(VISIBLE);
                    }
                } else {
                    for (View view : listSWValue) {
                        view.setVisibility(GONE);
                    }
                }
            }
        });
        radioBtnCheck.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioBtnCheck.isChecked()) {
                    radioBtnCheck.performClick();
                } else {
                    if (workItemGPONView != null) {
                        ieOnRadioCheckChangedListener.onRadioCheckChange(true,
                                radioBtnCheck, SubWorkItemGPONView.this);
                    }
                }
            }
        });
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!radioBtnCheck.isChecked()) {
                    radioBtnCheck.performClick();
                } else {
                    if (workItemGPONView != null) {
                        ieOnRadioCheckChangedListener.onRadioCheckChange(true,
                                radioBtnCheck, SubWorkItemGPONView.this);
                    }
                }
            }
        });
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

    public WorkItemGPONView getWorkItemGPONView() {
        return workItemGPONView;
    }

    public void setWorkItemGPONView(WorkItemGPONView workItemGPONView) {
        this.workItemGPONView = workItemGPONView;
    }

    public String getTvTitle() {
        return tvTitle.getText().toString();
    }

    public AppCompatEditText getEtValue() {
        return etValue;
    }

    public AppCompatRadioButton getRadioBtnCheck() {
        return radioBtnCheck;
    }

    public interface IeOnRadioCheckChangedListener {
        void onRadioCheckChange(boolean isCheck,
                                AppCompatRadioButton radioButton, SubWorkItemGPONView gponView);
    }

    public void setOnRadioCheckChangeListener(IeOnRadioCheckChangedListener listener) {
        this.ieOnRadioCheckChangedListener = listener;
    }

    public void addListSWValue(View view) {
        view.setVisibility(GONE);
        listSWValue.add(view);
    }

    public ArrayList<View> getListSWValue() {
        return listSWValue;
    }

    public LinearLayout getRootLayout() {
        return rootLayout;
    }
}
