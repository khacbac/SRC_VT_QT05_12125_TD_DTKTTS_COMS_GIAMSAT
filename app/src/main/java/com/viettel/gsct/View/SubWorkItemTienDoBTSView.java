package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/8/17.
 */

public class SubWorkItemTienDoBTSView extends LinearLayout {
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;

    private Cat_Sub_Work_ItemEntity entity;
    private boolean isFinish = false;
    private FinishListener listener;

    public SubWorkItemTienDoBTSView(Context context) {
        super(context);
        init(context);
    }

    public SubWorkItemTienDoBTSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubWorkItemTienDoBTSView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_item_bts, this);
        ButterKnife.bind(this);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setFinish(!isFinish);
                if (listener != null) {
                    listener.onFinishListener(isFinish, entity == null ? 0 : entity.getId());
                }
            }
        });

    }

    public void setFinish(boolean finish) {
        isFinish = finish;
        btnTienDo.setText(isFinish ? "Hoàn thành" : "Chưa làm");
    }

    public void setEnableTiendo(boolean enable) {
        btnTienDo.setEnabled(enable);
    }

    public void setTitle(Cat_Sub_Work_ItemEntity entity) {
        tvTitle.setText(entity.getName());
        this.entity = entity;
    }

    public void setFinishListener(FinishListener listener) {
        this.listener = listener;
    }

    public interface FinishListener {
        void onFinishListener(boolean isFinish, long catSubWorkItemId);
    }
}
