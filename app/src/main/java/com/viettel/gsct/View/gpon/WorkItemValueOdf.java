package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemValueOdf extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueOdf.class.getSimpleName();
    View rootView;
    @BindView(R.id.tv_ten_odf)
    TextView tvTenOdf;
    @BindView(R.id.tv_khoiluong)
    AppCompatEditText edtKhoiLuong;
    @BindView(R.id.tv_luyke)
    TextView tvLuyKe;

    private Sub_Work_Item_ValueEntity swiValue;
    private Work_ItemsEntity wIEntity;
    private Cat_Sub_Work_ItemEntity cSWIEntity;
    private Sub_Work_Item_ValueController sWIValueController;

    private ArrayList<View> views = new ArrayList<>();

    private Work_ItemsEntity workItem;

    private WorkItemRightGPONView.FinishListener listener;
    private WorkItemRightGPONView.OnStatusBtnTienDo statusTienDo;
    private AppCompatButton btnTienDo;
    private Sub_Work_ItemEntity swiEntity;


    public WorkItemValueOdf(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueOdf(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueOdf(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public boolean isValidate() {
        return true;
    }

    @Override
    public boolean isWorking() {
        return (getDoubleKhoiLuong() + getDoubleOldLuyKe()) != 0;
    }

    @Override
    public boolean isFinish() {
        return true;
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_lapdat_odf_gpon, this);
        ButterKnife.bind(this);
        sWIValueController = new Sub_Work_Item_ValueController(context);
    }

    public void setTvTenOdf(String tvTenOdf) {
        this.tvTenOdf.setText(tvTenOdf);
    }

    public void setTvLuyKe(double luyKe) {
        this.tvLuyKe.setText(String.valueOf(luyKe));
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf(khoiLuong));
    }

    public String getTvTenOdf() {
        return tvTenOdf.getText().toString().trim();
    }

    public double getDoubleKhoiLuong() {
        return edtKhoiLuong.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString().trim());
    }

    public double getDoubleOldLuyKe() {
        return sWIValueController.getOldLuyke(wIEntity.getId(),cSWIEntity.getId());
    }

    public AppCompatEditText getEdtKhoiLuong() {
        return edtKhoiLuong;
    }

    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
    }

    public void addWIEntity(Work_ItemsEntity entity) {
        this.wIEntity = entity;
    }

    public void addCSWIEntity(Cat_Sub_Work_ItemEntity entity) {
        this.cSWIEntity = entity;
    }

    // Save odf indoor theo cong trinh.
    @Override
    public void save() {
        super.save();
        boolean isSWIUpdate = false;
        // Cap nhat sub work item vao Database.
        swiEntity = Sub_Work_ItemController.getInstance(getContext()).getItem(wIEntity.getId(),cSWIEntity.getId());
        if (swiEntity == null) {
            swiEntity = new Sub_Work_ItemEntity(cSWIEntity.getId());
            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
            swiEntity.setId(id);
            swiEntity.setWork_item_id(wIEntity.getId());
            isSWIUpdate = false;
        } else {
            isSWIUpdate = true;
        }
        if (isFinish()) {
            if (!swiEntity.isCompleted()) {
                swiEntity.setFinishDate(GSCTUtils.getDateNow());
            }
        }
        swiEntity.setSyncStatus(swiEntity.getProcessId() > 0
                ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
        swiEntity.setEmployeeId(BaseFragment.userId);
        swiEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
        if (isSWIUpdate) {
            Sub_Work_ItemController.getInstance(getContext()).updateItem(swiEntity);
        } else {
            Sub_Work_ItemController.getInstance(getContext()).addItem(swiEntity);
        }
    }

    // Save odf out door theo node.
    @Override
    public void save(long nodeId) {
        boolean isSWIValueUpdate = false;

        // Cap nhat sub work item value vao Database.
        if (swiValue == null) {
            swiValue = new Sub_Work_Item_ValueEntity();
            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME);
            swiValue.setId(id);
            swiValue.setConstr_node_id(nodeId);
            swiValue.setWork_item_id(wIEntity.getId());
            swiValue.setCat_sub_work_item_id(cSWIEntity.getId());
            swiValue.setAdded_date(GSCTUtils.getDateNow());
            isSWIValueUpdate = false;
        } else {
            isSWIValueUpdate = true;
        }
        swiValue.setValue(edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString()));
        Log.d(TAG, "save: value = " + ""+edtKhoiLuong.getText().toString());
        swiValue.setEmployeeId(BaseFragment.userId);
        swiValue.setIsActive(Constants.ISACTIVE.ACTIVE);
        swiValue.setSyncStatus(swiValue.getProcessId() > 0
                ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);

        if (isSWIValueUpdate) {
            sWIValueController.updateItem(swiValue);
        } else {
            sWIValueController.addItem(swiValue);
        }
    }

    @Override
    public void updateTrangThai() {

    }

    public void updateLuyKeLapDat() {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        double luyke = sWIValueController.getOldLuyke(wIEntity.getId(),cSWIEntity.getId()) + value;
        setTvLuyKe(luyke);
    }

    public void setFinish(boolean finish) {
        edtKhoiLuong.setEnabled(!finish);
    }
}
