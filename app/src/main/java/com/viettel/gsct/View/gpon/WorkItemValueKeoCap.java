package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemValueKeoCap extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueKeoCap.class.getSimpleName();
    View rootView;
    @BindView(R.id.tv_item_loaicap)
    TextView tvItemLoaiCap;
    @BindView(R.id.tv_khoiluong)
    AppCompatEditText edtKhoiLuong;
    @BindView(R.id.tv_dvt)
    TextView tvDvt;
    @BindView(R.id.tv_luyke)
    TextView tvLuyKe;

    private Sub_Work_Item_ValueEntity swiValue;
    private Work_ItemsEntity wIEntity;
    private Cat_Sub_Work_ItemEntity cSWIEntity;

    public WorkItemValueKeoCap(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueKeoCap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueKeoCap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_keocap_item_gpon, this);
        ButterKnife.bind(this);
    }

    public void setTvItemLoaiCap(String tvItemLoaiCap) {
        this.tvItemLoaiCap.setText(tvItemLoaiCap);
    }

    public String getTenItem() {
        return tvItemLoaiCap.getText().toString();
    }

    public void setTvDvt(String tvDvt) {
        this.tvDvt.setText(tvDvt);
    }

    public void setTvLuyKe(double luyKe) {
        this.tvLuyKe.setText(String.valueOf(luyKe));
    }

    public double getLuyKe() {
        if (tvLuyKe.getText().toString().isEmpty()) {
            return 0;
        }

        return Double.parseDouble(tvLuyKe.getText().toString());
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf(khoiLuong));
    }

    public String getKhoiLuong() {
        return ""+edtKhoiLuong.getText().toString();
    }

    public double getDoubleKhoiLuong() {
        return edtKhoiLuong.getText().toString().trim().isEmpty() ? 0 : Double.valueOf(edtKhoiLuong.getText().toString().trim());
    }

    // Lay so luy ke tru ngay hom nay.
    public double getDoubleOldLuyKe() {
        return Sub_Work_Item_ValueController.getInstance(getContext()).getOldLuyke(wIEntity.getId(),cSWIEntity.getId());
    }


    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
    }

    public void addWIEntity(Work_ItemsEntity entity) {
        this.wIEntity = entity;
        if (wIEntity.hasCompletedDate()) {
            if (!(wIEntity.getComplete_date().equalsIgnoreCase(GSCTUtils.getDateNow()))) {
                edtKhoiLuong.setEnabled(false);
            }
        }
    }

    public void addCSWIEntity(Cat_Sub_Work_ItemEntity entity) {
        this.cSWIEntity = entity;
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
        return false;
    }

    @Override
    public void save(long nodeId) {
        boolean isSWIValueUpdate = false;
//        boolean isSWIUpdate = false;

        // Cap nhat sub work item value vao Database.
        if (swiValue == null) {
            swiValue = new Sub_Work_Item_ValueEntity();
            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME);
            swiValue.setId(id);
            swiValue.setConstr_node_id(nodeId);
            Log.d(TAG, "save: wItem name = " + wIEntity.getWork_item_name());
            swiValue.setWork_item_id(wIEntity.getId());
            swiValue.setCat_sub_work_item_id(cSWIEntity.getId());
            swiValue.setAdded_date(GSCTUtils.getDateNow());
            isSWIValueUpdate = false;
        } else {
            isSWIValueUpdate = true;
        }
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        swiValue.setValue(value);
        swiValue.setEmployeeId(BaseFragment.userId);
        swiValue.setIsActive(Constants.ISACTIVE.ACTIVE);
        swiValue.setSyncStatus(swiValue.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);

        if (isSWIValueUpdate) {
            Sub_Work_Item_ValueController.getInstance(getContext()).updateItem(swiValue);
            Log.d(TAG, "save: update item");
        } else {
            Sub_Work_Item_ValueController.getInstance(getContext()).addItem(swiValue);
            Log.d(TAG, "save: insert item");
        }

//        // Cap nhat Sub Work Item vao Database.
//        Sub_Work_ItemEntity swiEntity = Sub_Work_ItemController.getInstance(getContext()).getItem(wIEntity.getId(),cSWIEntity.getId());
//        if (swiEntity == null) {
//            swiEntity = new Sub_Work_ItemEntity(cSWIEntity.getId());
//            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
//            swiEntity.setId(id);
//            swiEntity.setWork_item_id(wIEntity.getId());
//            isSWIUpdate = false;
//        } else {
//            isSWIUpdate = true;
//        }
//        swiEntity.setFinishDate(wIEntity.getComplete_date());
//        swiEntity.setSyncStatus(swiEntity.getProcessId() > 0
//                ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
//        swiEntity.setEmployeeId(BaseFragment.userId);
//        swiEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
//        if (isSWIUpdate) {
//            Sub_Work_ItemController.getInstance(getContext()).updateItem(swiEntity);
//        } else {
//            Sub_Work_ItemController.getInstance(getContext()).addItem(swiEntity);
//        }
    }

    @Override
    public void updateTrangThai() {
        updateLuyKe();
    }

    public void updateLuyKe() {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getOldLuyke(wIEntity.getId(),cSWIEntity.getId()) + value;
        setTvLuyKe(luyke);
    }

    public void setFinish(boolean isFinish) {
        edtKhoiLuong.setEnabled(!isFinish);
    }

    // Ham tra ve so luong luy ke sau khi luu.Dung de hien thi phan preview.
    public double getAllValueAlterSave() {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getOldLuyke(wIEntity.getId(),cSWIEntity.getId()) + value;
        return value + luyke;
    }
}
