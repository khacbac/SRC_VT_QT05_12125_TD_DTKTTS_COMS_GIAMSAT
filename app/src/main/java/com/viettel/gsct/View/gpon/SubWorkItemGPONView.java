package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.constant.Constant;
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
    RelativeLayout rootLayout;

    private WorkItemGPONView workItemGPONView;
    private IeOnRadioCheckChangedListener ieOnRadioCheckChangedListener;
    private Work_ItemsEntity wItemEntity;

    // Phan view chua toan bo value hien thi.
    // Duoc su dung cho viec hide/show tuy theo radio button click.
    private View valueView;

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
                Log.d(TAG, "onCheckedChanged: node check = " + getTvTitle());
                if (wItemEntity != null) {
                    wItemEntity.setRecentCheck(getTvTitle());
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            Work_ItemsControler.getInstance(context).updateItem(wItemEntity);
                        }
                    });
                }
                if (workItemGPONView != null) {
                    ieOnRadioCheckChangedListener.onRadioCheckChange(isChecked, radioBtnCheck, SubWorkItemGPONView.this);
                }
                if (isChecked) {
                    if (valueView != null) {
                        valueView.setVisibility(VISIBLE);
                    }
                } else {
                    if (valueView != null) {
                        valueView.setVisibility(GONE);
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
                        ieOnRadioCheckChangedListener.onRadioCheckChange(true, radioBtnCheck, SubWorkItemGPONView.this);
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
                        ieOnRadioCheckChangedListener.onRadioCheckChange(true, radioBtnCheck, SubWorkItemGPONView.this);
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

    public void setWorkItemGPONView(WorkItemGPONView workItemGPONView) {
        this.workItemGPONView = workItemGPONView;
    }

    public String getTvTitle() {
        return tvTitle.getText().toString();
    }

    public AppCompatRadioButton getRadioBtnCheck() {
        return radioBtnCheck;
    }

    public interface IeOnRadioCheckChangedListener {
        void onRadioCheckChange(boolean isCheck, AppCompatRadioButton radioButton, SubWorkItemGPONView gponView);
    }

    public void setOnRadioCheckChangeListener(IeOnRadioCheckChangedListener listener) {
        this.ieOnRadioCheckChangedListener = listener;
    }

    /**
     * TODO: Viet lai cho tat ca work item.
     */
    public void addSWValue(View view) {
        view.setVisibility(GONE);
        this.valueView = view;
    }

    public RelativeLayout getRootLayout() {
        return rootLayout;
    }

    public void setFinish(boolean isFinish) {
        radioBtnCheck.setChecked(isFinish);
        radioBtnCheck.setEnabled(!isFinish);
    }

    public void addWItemEntity(Work_ItemsEntity wItem) {
        this.wItemEntity = wItem;
        if (getTvTitle().equalsIgnoreCase(wItem.getRecentCheck())) {
            radioBtnCheck.setChecked(true);
        }
    }

    @Override
    public View getRootView() {
        return rootView;
    }
}
