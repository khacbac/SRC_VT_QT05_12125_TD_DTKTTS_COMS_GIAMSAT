package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemValueKeoCapHeader extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueKeoCapHeader.class.getSimpleName();
    View rootView;
    @BindView(R.id.txtLoaiCap)
    TextView tvLoaiCap;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;
    @BindView(R.id.layoutForItem)
    LinearLayout layoutForItem;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;

    private String chuaLam;
    private String dangLam;
    private String hoanThanh;

    private ArrayList<WorkItemValueKeoCap> listValue = new ArrayList<>();

//    private WorkItemValueKeoCap wIValue;
    private Work_ItemsEntity wParentItem;
    private Work_ItemsEntity wItemEntity;

    public WorkItemValueKeoCapHeader(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueKeoCapHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueKeoCapHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_keocap_header_gpon, this);
        ButterKnife.bind(this);
        btnCollapse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutForItem.setVisibility(layoutForItem.getVisibility() == VISIBLE ? GONE : VISIBLE);
            }
        });

        chuaLam = getResources().getString(R.string.str_tiendo_chualam);
        dangLam = getResources().getString(R.string.str_tiendo_danglam);
        hoanThanh = getResources().getString(R.string.str_tiendo_hoanthanh);
        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienDo = btnTienDo.getText().toString().trim();
                for (WorkItemValueKeoCap keoCap : listValue) {
                    keoCap.setFinish(false);
                }
                // Ham lay tong tat ca value va luy ke hien tai cua toan bo sub work item value.
                double sum = 0;
                if (chuaLam.equalsIgnoreCase(tienDo)) {
                    btnTienDo.setText(dangLam);
                } else if (dangLam.equalsIgnoreCase(tienDo)) {
                    if (listValue.size() > 0) {
                        for (WorkItemValueKeoCap valueItem : listValue) {
                            sum += (valueItem.getDoubleOldLuyKe() + valueItem.getDoubleKhoiLuong());
                        }
                        Log.d(TAG, "onClick: Sum = " + sum);
                        if (sum == 0) {
                            Toast.makeText(context,getResources().getString(R.string.str_validate_hoanthanh),Toast.LENGTH_SHORT).show();
                        } else {
                            btnTienDo.setText(hoanThanh);
                            for (WorkItemValueKeoCap keoCap : listValue) {
                                keoCap.setFinish(true);
                            }
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

    public TextView getTvLoaiCap() {
        return tvLoaiCap;
    }

    public void setTvLoaiCap(String tvLoaiCap) {
        this.tvLoaiCap.setText(tvLoaiCap);
    }

    public String getTenLoaiCap() {
        return tvLoaiCap.getText().toString();
    }

    public Button getBtnCollapse() {
        return btnCollapse;
    }

    public void setBtnCollapse(Button btnCollapse) {
        this.btnCollapse = btnCollapse;
    }

    public void addItemValue(WorkItemValueKeoCap view) {
//        this.wIValue = view;
        layoutForItem.addView(view);
        listValue.add(view);
    }

    // Truong hop dac biet.
    // Work item kep cap co chua 2 work item khac.
    public void addParentWorkItem(Work_ItemsEntity item) {
        this.wParentItem = item;
        if (wParentItem.hasCompletedDate()) {
            setStatusHoanThanh(true);
        }
    }

    // Them work item Cap quang so 8 hoac Adss.
    public void addWorkItem(Work_ItemsEntity item) {
        this.wItemEntity = item;
        if (wItemEntity.hasStartedDate()) {
            setStatusDangLam();
        }
        if (wItemEntity.hasCompletedDate()) {
            boolean isFinish = wItemEntity.getComplete_date().equalsIgnoreCase(GSCTUtils.getDateNow());
            setStatusHoanThanh(isFinish);
        }
        for (WorkItemValueKeoCap keoCap : listValue) {
            keoCap.addWIEntity(wItemEntity);
        }
    }

    public Work_ItemsEntity getwParentItem() {
        return wParentItem;
    }

    public AppCompatButton getBtnTienDo() {
        return btnTienDo;
    }


    @Override
    public boolean isValidate() {
        for (WorkItemValueKeoCap keoCap : listValue) {
            if (!keoCap.isValidate()) {
                return false;
            }
        }
        return true;
    }

    // Neu work item bat dau lam viec.
    @Override
    public boolean isWorking() {
        for (WorkItemValueKeoCap keoCap : listValue) {
            if (keoCap.isWorking()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFinish() {
        return hoanThanh.equalsIgnoreCase(btnTienDo.getText().toString().trim());
    }

    @Override
    public void save(long nodeId) {
        if (!listValue.isEmpty()) {
            for (WorkItemValueKeoCap kcValue : listValue) {
                kcValue.save(nodeId);
            }
        }
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

    @Override
    public void updateTrangThai() {
        if (wItemEntity != null) {
            if (wItemEntity.hasStartedDate()) {
                setStatusDangLam();
            }
            if (wItemEntity.hasCompletedDate()) {
                setStatusHoanThanh(false);
            }
            if (!listValue.isEmpty()) {
                for (WorkItemValueKeoCap kcValue : listValue) {
                    kcValue.updateTrangThai();
                }
            }
        }
    }

    private void setStatusDangLam() {
        btnTienDo.setText(dangLam);
    }

    private void setStatusHoanThanh(boolean isFinish) {
        btnTienDo.setText(hoanThanh);
        if (isFinish) {
            btnTienDo.setEnabled(false);
        }
    }

    // Ham tra ve so luong luy ke sau khi luu.Dung de hien thi phan preview.
    public double getAllValueAlterSave() {
        double sum = 0;
        if (!listValue.isEmpty()) {
            for (WorkItemValueKeoCap kcValue : listValue) {
                sum += (kcValue.getAllValueAlterSave());
            }
        }
        return sum;
    }

    public Work_ItemsEntity getwItemEntity() {
        return wItemEntity;
    }

    public ArrayList<WorkItemValueKeoCap> getListValue() {
        return listValue;
    }
}
