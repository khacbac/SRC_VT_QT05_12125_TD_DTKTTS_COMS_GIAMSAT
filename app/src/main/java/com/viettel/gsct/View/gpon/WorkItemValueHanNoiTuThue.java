package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
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

public class WorkItemValueHanNoiTuThue extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueHanNoiTuThue.class.getSimpleName();
    View rootView;

    @BindView(R.id.tv_tentu)
    TextView tvTenTu;
    @BindView(R.id.tv_sl_lapdat)
    AppCompatEditText edtSlLapDat;
    @BindView(R.id.tv_luyke_ld)
    TextView tvLuyKeLapDat;
    @BindView(R.id.tv_sl_hannoi)
    AppCompatEditText edtSlHanNoi;
    @BindView(R.id.tv_luyke_hn)
    TextView tvLuyKeHanNoi;

    private Sub_Work_Item_ValueEntity swiValue;
    private Work_ItemsEntity wIEntity;
    private Cat_Sub_Work_ItemEntity cSWIEntity;
    private Sub_Work_Item_ValueController sWIValueController;

    public WorkItemValueHanNoiTuThue(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueHanNoiTuThue(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueHanNoiTuThue(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_hannoi_tuthue_gpon, this);
        ButterKnife.bind(this);
        sWIValueController = new Sub_Work_Item_ValueController(context);
    }

    public void setTvTenTu(String tvTenTu) {
        this.tvTenTu.setText(tvTenTu);
    }

    public String getTenTu() {
        return tvTenTu.getText().toString();
    }

    public void setTvLuyKeLapDat(double luyKeLapDat) {
        this.tvLuyKeLapDat.setText(String.valueOf(luyKeLapDat));
    }

    public void setTvLuyKeHanNoi(double luyKeHanNoi) {
        this.tvLuyKeHanNoi.setText(String.valueOf(luyKeHanNoi));
    }

    public void setEdtKhoiLuongLapDat(double value) {
        edtSlLapDat.setText(value == 0 ? "" : String.valueOf(value));
    }

    public void setEdtKhoiLuongHanNoi(double valueItem) {
        edtSlHanNoi.setText(valueItem == 0 ? "" : String.valueOf(valueItem));
    }

    public double getDoubleKhoiLuongLapDat() {
        return edtSlLapDat.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString().trim());
    }

    public double getDoubleKhoiLuongHanNoi() {
        return edtSlHanNoi.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtSlHanNoi.getText().toString().trim());
    }

    public double getDoubleOldLuyKeLapDat() {
        return sWIValueController.getOldLuyke(wIEntity.getId(),cSWIEntity.getId());
    }

    public double getDoubleOldLuyKeHanNoi() {
        return sWIValueController.getOldLuykeHanNoi(wIEntity.getId(),cSWIEntity.getId());
    }

    public AppCompatEditText getEdtSlHanNoi() {
        return edtSlHanNoi;
    }

    public AppCompatEditText getEdtSlLapDat() {
        return edtSlLapDat;
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

    public void save(long nodeId) {
        double value = edtSlLapDat.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString());
        new SaveAsync().execute(nodeId,value);
    }

    public void updateLuyKeLapDat() {
        double value = edtSlLapDat.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString());
        double luyke = sWIValueController.getOldLuyke(wIEntity.getId(),cSWIEntity.getId()) + value;
        setTvLuyKeLapDat(luyke);
    }

    public void updateLuyKeHanNoi() {
        double value = edtSlHanNoi.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlHanNoi.getText().toString());
        double luykeHanNoi = sWIValueController.getOldLuykeHanNoi(wIEntity.getId(),cSWIEntity.getId()) + value;
        setTvLuyKeHanNoi(luykeHanNoi);
    }

    public void setFinish(boolean finish) {
        edtSlLapDat.setEnabled(!finish);
        edtSlHanNoi.setEnabled(!finish);
    }

    class SaveAsync extends AsyncTask<Object,Void,Boolean> {
        @Override
        protected Boolean doInBackground(Object... objects) {
            long nodeId = Long.parseLong(objects[0].toString());
            double value = Double.parseDouble(objects[1].toString());
            boolean isSWIValueUpdate;

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
            swiValue.setValue(value);
            swiValue.setEmployeeId(BaseFragment.userId);
            swiValue.setIsActive(Constants.ISACTIVE.ACTIVE);
            swiValue.setSyncStatus(swiValue.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            return isSWIValueUpdate;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                sWIValueController.updateItem(swiValue);
            } else {
                sWIValueController.addItem(swiValue);
            }
            updateLuyKeLapDat();
            updateLuyKeHanNoi();
        }
    }
}
