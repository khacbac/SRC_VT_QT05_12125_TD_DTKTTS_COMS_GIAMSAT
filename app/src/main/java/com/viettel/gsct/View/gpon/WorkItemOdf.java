package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bachk on 11/2/2017.
 */

public class WorkItemOdf extends BaseCustomWorkItem {

    private static final String TAG = WorkItemOdf.class.getSimpleName();
    private View rootView;
    private Work_ItemsEntity wItemEntity;
    private ArrayList<WorkItemValueOdf> listValue = new ArrayList<>();

    @BindView(R.id.btnTienDo)
    AppCompatButton btnTienDo;

    private String chuaLam;
    private String dangLam;
    private String hoanThanh;

    public WorkItemOdf(Context context) {
        super(context);
        initData(context);
    }

    public WorkItemOdf(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public WorkItemOdf(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    @Override
    public boolean isValidate() {
        return true;
    }

    @Override
    public boolean isWorking() {
        for (WorkItemValueOdf odf : listValue) {
            if (odf.isWorking()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFinish() {
        return getResources().getString(R.string.str_tiendo_hoanthanh).equalsIgnoreCase(btnTienDo.getText().toString().trim());
    }

    // Save odf indoor theo cong trinh.
    @Override
    public void save() {
        if (wItemEntity != null) {
            if (!wItemEntity.hasStartedDate()) {
                if (isWorking()) {
                    wItemEntity.setStarting_date(GSCTUtils.getDateNow());
                }
            }
            if (isFinish()) {
                wItemEntity.setComplete_date(GSCTUtils.getDateNow());
            }
            Work_ItemsControler.getInstance(getContext()).updateItem(wItemEntity);
        }
        for (WorkItemValueOdf odf : listValue) {
            odf.save();
        }
    }

    // Save Odf outdoor theo node.
    @Override
    public void save(long nodeId) {
        if (wItemEntity != null) {
            if (!wItemEntity.hasStartedDate()) {
                if (isWorking()) {
                    wItemEntity.setStarting_date(GSCTUtils.getDateNow());
                }
            }
            if (isFinish()) {
                wItemEntity.setComplete_date(GSCTUtils.getDateNow());
            }
            Work_ItemsControler.getInstance(getContext()).updateItem(wItemEntity);
        }
        for (WorkItemValueOdf odf : listValue) {
            odf.save(nodeId);
        }
    }

    @Override
    public void updateTrangThai() {
        if (wItemEntity != null) {
            if (wItemEntity.hasStartedDate()) {
                btnTienDo.setText(dangLam);
            }
        }
        for (WorkItemValueOdf odf : listValue) {
            odf.updateLuyKeLapDat();
        }
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        rootView = inflate(context, R.layout.custom_workitem_odf,this);
        ButterKnife.bind(this);

        chuaLam = getResources().getString(R.string.str_tiendo_chualam);
        dangLam = getResources().getString(R.string.str_tiendo_danglam);
        hoanThanh = getResources().getString(R.string.str_tiendo_hoanthanh);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String tienDo = btnTienDo.getText().toString().trim();
                for (WorkItemValueOdf odf : listValue) {
                    odf.setFinish(false);
                }
                // Ham lay tong tat ca value va luy ke hien tai cua toan bo sub work item value.
                if (chuaLam.equalsIgnoreCase(tienDo)) {
                    btnTienDo.setText(dangLam);
                } else if (dangLam.equalsIgnoreCase(tienDo)) {
                    double value = 0;
                    double oldLuyke = 0;
                    double sum = 0;
                    for (WorkItemValueOdf odf : listValue) {
                        value = odf.getDoubleKhoiLuong();
                        oldLuyke = odf.getDoubleOldLuyKe();
                        sum += (value + oldLuyke);
                    }
                    if (sum == 0) {
                        Toast.makeText(getContext(),getResources().getString(R.string.str_validate_hoanthanh),Toast.LENGTH_SHORT).show();
                    } else {
                        btnTienDo.setText(hoanThanh);
                        for (WorkItemValueOdf odf : listValue) {
                            odf.setFinish(true);
                        }
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

    public void addWorkItem(Work_ItemsEntity wItem) {
        this.wItemEntity = wItem;
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

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public void addValueOdf(WorkItemValueOdf view) {
        if (wItemEntity.hasCompletedDate()) {
            if (!GSCTUtils.getDateNow().equalsIgnoreCase(wItemEntity.getComplete_date())) {
                view.setFinish(true);
            }
        }
        view.addWIEntity(wItemEntity);
        listValue.add(view);
        this.addView(view);
    }

    public ArrayList<WorkItemValueOdf> getListValue() {
        return listValue;
    }

}
