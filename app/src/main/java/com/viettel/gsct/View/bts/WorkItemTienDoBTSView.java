package com.viettel.gsct.View.bts;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/8/17.
 */

public class WorkItemTienDoBTSView extends LinearLayout {
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rb_check)
    AppCompatRadioButton rbCheck;
    private RadioCheckListener listener;
    private int parentIndex = 0;
    private Cat_Work_Item_TypesEntity entity;

    public WorkItemTienDoBTSView(Context context) {
        super(context);
        init(context);
    }

    public WorkItemTienDoBTSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemTienDoBTSView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_work_item_item_bts, this);
        ButterKnife.bind(this);

        rbCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbCheck.isChecked()) {
                    if (listener != null) {
                        listener.onRadioChecked(parentIndex, entity);
                    }
                }
            }
        });

        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rbCheck.isChecked()) {
                    rbCheck.setChecked(rbCheck.isEnabled());
                } else {
                    if (listener != null) {
                        listener.onTextviewClicked(parentIndex,entity);
                    }
                }
            }
        });
    }

    public void setTitle(int parentIndex, Cat_Work_Item_TypesEntity code) {
        tvTitle.setText(code.getItem_type_name());
        this.parentIndex = parentIndex;
        this.entity = code;
    }

    public String getTitle() {
        return ""+ tvTitle.getText();
    }

    public Cat_Work_Item_TypesEntity getEntity() {
        return entity;
    }

    public void setChecked(boolean checked) {
        rbCheck.setChecked(checked);
    }

    public boolean isChecked() {
        return rbCheck.isChecked();
    }

    public void setRadioCheckListener(RadioCheckListener listener) {
        this.listener = listener;
    }

    public void setEnabledRadioBtn(boolean enable) {
        rbCheck.setEnabled(enable);
    }

    private TiendoBTSItemView parentView;
    public void addTienDoParentView(TiendoBTSItemView rootview) {
        this.parentView = rootview;
    }

    public TiendoBTSItemView getTienDoParentView() {
        return parentView;
    }

    public interface RadioCheckListener {
        void onRadioChecked(int parentIndex, Cat_Work_Item_TypesEntity entity) ;
        void onTextviewClicked(int parentIndex,Cat_Work_Item_TypesEntity entity) ;
    }

}
