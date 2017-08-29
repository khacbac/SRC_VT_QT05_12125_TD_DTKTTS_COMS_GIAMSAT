package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class WorkItemTienDoNgamView extends LinearLayout {
    private static final String TAG = "WorkItemTienDoNgamView";
    View rootView;
    @BindView(R.id.tv_STT)
    TextView tvSTT;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;
    Work_ItemsEntity entity;
    private ArrayList<SubWorkItemTienDoNgamView> arrSubItems = new ArrayList<>();
    private boolean flagIsWorking = false; // = true neu co mot truong subWorkItem da nhap du lieu
    // btnTiendo chi co hai trang hai hoan thanh / dang lam

    public WorkItemTienDoNgamView(Context context) {
        super(context);
        init(context);
    }

    public WorkItemTienDoNgamView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemTienDoNgamView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.layout_work_item_tien_do_ngam, this);
        ButterKnife.bind(this);
        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (entity.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE) {
                    if (!entity.hasCompletedDate() || GSCTUtils.getDateNow().equals(entity.getComplete_date())) {
                        if (isWorking()) {
                            entity.setStatus_id(Work_ItemsEntity.STATUS_WORKING);
                            btnTienDo.setText("Đang làm");
                        } else {
                            entity.setStatus_id(0);
                            btnTienDo.setText("Chưa làm");
                        }

                        for(SubWorkItemTienDoNgamView view : arrSubItems) {
                            view.setEditeTextEnable(true);
                        }
                    } else {
                        Toast.makeText(getContext(), "Bạn chỉ được thay đổi trạng thái trong ngày!", Toast.LENGTH_SHORT).show();
                    }
                    return;
                } else if (entity.getStatus_id() == Work_ItemsEntity.STATUS_WORKING) {
                    boolean isFinish = true;
                    for (SubWorkItemTienDoNgamView view : arrSubItems) {
                        isFinish = !(view.getEtValueNumber().equals("") && view.getTvLuykeNumber().equals(""));
                        if (!isFinish) {
                            Toast.makeText(getContext(),"Bạn phải nhập khối lượng cho "
                                    + view.getTvTitle(),Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if (isFinish) {
                        entity.setStatus_id(Work_ItemsEntity.STATUS_COMPLETE);
                        btnTienDo.setText("Hoàn thành");
                    }
                } else {
                    entity.setStatus_id(Work_ItemsEntity.STATUS_WORKING);
                    btnTienDo.setText("Đang làm");
                }
            }
        });
    }

    public void setWorkItemEntity(Work_ItemsEntity entity) {
        this.entity = entity;
        tvTitle.setText(entity.getWork_item_name());
//        tvSTT.setText(String.valueOf(position));
        if (entity.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE) {
            btnTienDo.setText("Hoàn thành");
        } else if (entity.getStatus_id() == Work_ItemsEntity.STATUS_WORKING) {
            btnTienDo.setText("Đang làm");
        } else {
            btnTienDo.setText("Chưa làm");
        }
    }

    public Work_ItemsEntity getWorkItemEntity() {
        return entity;
    }

    public String getTitle() {
        return tvTitle.getText().toString();
    }

    public String getTrangThaiTienDo() {
        return btnTienDo.getText().toString();
    }

    public boolean isWorking() {
        for (SubWorkItemTienDoNgamView view : arrSubItems) {
            if (view.getValue() > 0)
                return true;
        }
        return false;
    }

    public void disable() {
        btnTienDo.setEnabled(false);
    }

    public void addSubItem(SubWorkItemTienDoNgamView view) {
        arrSubItems.add(view);
//        Log.e(TAG, "addSubItem: " + entity.getStatus_id() );
        view.setEditeTextEnable(entity.getStatus_id() != Work_ItemsEntity.STATUS_COMPLETE || GSCTUtils.getDateNow().equals(entity.getComplete_date()));
    }

    public ArrayList<SubWorkItemTienDoNgamView> getArrSubItems() {
        return arrSubItems;
    }
}
