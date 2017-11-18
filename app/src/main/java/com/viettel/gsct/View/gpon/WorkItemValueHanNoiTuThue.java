package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.ConstrNodeEntity;
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
    private long nodeId = 0;
    private ConstrNodeEntity node;

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

        edtSlLapDat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (".".equalsIgnoreCase(editable.toString())) {
                    edtSlLapDat.setError("Không phải là số!");
                    edtSlLapDat.requestFocus();
                }
            }
        });

        edtSlHanNoi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (".".equalsIgnoreCase(editable.toString())) {
                    edtSlHanNoi.setError("Not Number");
                    edtSlHanNoi.requestFocus();
                }
            }
        });
    }

    public void setTvTenTu(String tvTenTu) {
        this.tvTenTu.setText(tvTenTu);
    }

    public String getTenTu() {
        return tvTenTu.getText().toString();
    }

    public void setTvLuyKeLapDat(double luyKeLapDat) {
        this.tvLuyKeLapDat.setText(String.valueOf((int) luyKeLapDat));
    }

    public void setTvLuyKeHanNoi(double luyKeHanNoi) {
        this.tvLuyKeHanNoi.setText(String.valueOf((int) luyKeHanNoi));
    }

    public void setEdtKhoiLuongLapDat(double value) {
        edtSlLapDat.setText(value == 0 ? "" : String.valueOf((int) value));
    }

    public void setEdtKhoiLuongHanNoi(double valueItem) {
        edtSlHanNoi.setText(valueItem == 0 ? "" : String.valueOf((int) valueItem));
    }

    public int getIntegerKhoiLuongLapDat() {
        return (int)(edtSlLapDat.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString().trim()));
    }

    public int getIntegerKhoiLuongHanNoi() {
        return (int)(edtSlHanNoi.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtSlHanNoi.getText().toString().trim()));
    }

    public int getIntegerOldLuyKeLapDat() {
        return (int)(sWIValueController.getOldLuykeByNode(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID()));
    }

    public int getIntegerOldLuyKeHanNoi() {
        return (int)(sWIValueController.getOldLuykeHanNoi(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID()));
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

    public void addCTNodeEntity(ConstrNodeEntity node) {
        this.node = node;
    }

    public void save(long nodeId) {
        this.nodeId = nodeId;
        double value = edtSlLapDat.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString());
        double valueHN = edtSlHanNoi.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlHanNoi.getText().toString());
        if (edtSlLapDat.getText().toString().isEmpty() && edtSlHanNoi.getText().toString().isEmpty()) {
            return;
        }
        new SaveAsync().execute(nodeId, value, valueHN);
    }

    public void updateLuyKeLapDat() {
        double value = edtSlLapDat.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlLapDat.getText().toString());
        double luyke = sWIValueController.getOldLuykeByNode(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID()) + value;
        setTvLuyKeLapDat(luyke);
    }

    public void updateLuyKeHanNoi() {
        double value = edtSlHanNoi.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtSlHanNoi.getText().toString());
        double luykeHanNoi = sWIValueController.getOldLuykeHanNoi(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID()) + value;
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
            double valueHN = Double.parseDouble(objects[2].toString());
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
            swiValue.setValue_item(valueHN);
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
