package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemHanNoi extends BaseCustomWorkItem {

    private static final String TAG = WorkItemHanNoi.class.getSimpleName();
    private View rootView;
    @BindView(R.id.lBoChia)
    WorkItemHanNoiBoChia lBoChia;
    @BindView(R.id.lTuThue)
    WorkItemHanNoiTuThue lTuThue;
    @BindView(R.id.btnTienDoHanNoi)
    AppCompatButton btnTienDo;

    private String chuaLam;
    private String dangLam;
    private String hoanThanh;

    private Work_ItemsEntity wItemEntity;

    public WorkItemHanNoi(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemHanNoi(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemHanNoi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    public boolean isValidate() {
        return lBoChia.isValidate() && lTuThue.isValidate();
    }

    @Override
    public boolean isWorking() {
        return lBoChia.isWorking() || lTuThue.isWorking();
    }

    @Override
    public boolean isFinish() {
        return getResources().getString(R.string.str_tiendo_hoanthanh).equalsIgnoreCase(btnTienDo.getText().toString().trim());
    }

    @Override
    public void save(long nodeId) {
        lBoChia.save(nodeId);
        lTuThue.save(nodeId);
        if (wItemEntity != null) {
            if (!wItemEntity.hasStartedDate()) {
                if (isWorking()) {
                    wItemEntity.setStarting_date(GSCTUtils.getDateNow());
                }
            }
            if (isFinish()) {
                if (!wItemEntity.hasCompletedDate()) {
                    wItemEntity.setComplete_date(GSCTUtils.getDateNow());
                }
            }
            Work_ItemsControler.getInstance(getContext()).updateItem(wItemEntity);
        }
    }

    // Ham cap nhat lai trang thai hien thi cua toan bo view sau khi user nhan save.
    @Override
    public void updateTrangThai() {
        if (wItemEntity != null) {
            if (wItemEntity.hasStartedDate()) {
                btnTienDo.setText(dangLam);
            }
        }
        lBoChia.updateTrangThai();
        lTuThue.updateTrangThai();
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_hannoi,this);
        ButterKnife.bind(this);

        chuaLam = getResources().getString(R.string.str_tiendo_chualam);
        dangLam = getResources().getString(R.string.str_tiendo_danglam);
        hoanThanh = getResources().getString(R.string.str_tiendo_hoanthanh);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String tienDo = btnTienDo.getText().toString().trim();
                lTuThue.setFinish(false);
                lBoChia.setFinish(false);
                // Ham lay tong tat ca value va luy ke hien tai cua toan bo sub work item value.
                double sumBoChia = 0;
                double sumTuThue = 0;
                if (chuaLam.equalsIgnoreCase(tienDo)) {
                    btnTienDo.setText(dangLam);
                } else if (dangLam.equalsIgnoreCase(tienDo)) {
                    sumBoChia = lBoChia.getAllValue();
                    Log.d(TAG, "onClick: bo chia = " + sumBoChia);
                    sumTuThue = lTuThue.getAllValue();
                    Log.d(TAG, "onClick: tu thue = " + sumTuThue);
                    if (sumBoChia == 0 || sumTuThue == 0) {
                        Toast.makeText(getContext(),getResources().getString(R.string.str_validate_hoanthanh),Toast.LENGTH_SHORT).show();
                    } else {
                        btnTienDo.setText(hoanThanh);
                        lTuThue.setFinish(true);
                        lBoChia.setFinish(true);
                    }
                } else if (hoanThanh.equalsIgnoreCase(tienDo)) {
                    if (wItemEntity != null) {
                        if (wItemEntity.hasStartedDate()) {
                            btnTienDo.setText(dangLam);
                        } else {
                            btnTienDo.setText(chuaLam);
                        }
                    }
                }
            }
        });
    }

    public void addWorkItem(Work_ItemsEntity entity) {
        this.wItemEntity = entity;
        if (wItemEntity.hasStartedDate()) {
            btnTienDo.setText(dangLam);
        }
        if (wItemEntity.hasCompletedDate()) {
            btnTienDo.setText(hoanThanh);
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                btnTienDo.setEnabled(false);

            }
        }
    }

    public void addValueBoChia(WorkItemValueHanNoiBoChia view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        lBoChia.addValueBoChia(view);
    }

    public void addValueTuThue(WorkItemValueHanNoiTuThue view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        lTuThue.addValueTuThue(view);
    }

    public WorkItemHanNoiBoChia getlBoChia() {
        return lBoChia;
    }

    public WorkItemHanNoiTuThue getlTuThue() {
        return lTuThue;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }
}
