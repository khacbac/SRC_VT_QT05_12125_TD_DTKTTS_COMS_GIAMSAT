package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class WorkItemRightGPONView extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;

    private Work_ItemsEntity workItem;

    private FinishListener listener;

    public WorkItemRightGPONView(Context context) {
        super(context);
        init(context);
    }

    public WorkItemRightGPONView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemRightGPONView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_gpon, this);
        ButterKnife.bind(this);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (workItem.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE) {
                    workItem.setStatus_id(0);
                    btnTienDo.setText("Chưa làm");
                } else {
                    workItem.setStatus_id(Work_ItemsEntity.STATUS_COMPLETE);
                    btnTienDo.setText("Hoàn thành");
                    if (listener != null && !listener.onFinishListener()) {
                        workItem.setStatus_id(0);
                        btnTienDo.setText("Chưa làm");
                    }
                }
            }
        });
    }

    public void setWorkItem(Work_ItemsEntity workItem) {
        this.workItem = workItem;
        tvTitle.setText(workItem.getWork_item_name());
        if (workItem.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE) {
            btnTienDo.setText("Hoàn thành");
        } else {
            btnTienDo.setText("Chưa làm");
        }

        btnTienDo.setEnabled(!workItem.isCompleted());
    }

    public void setFinishListener(FinishListener listener) {
        this.listener = listener;
    }

    public interface FinishListener {
        public boolean onFinishListener();
    }

}
