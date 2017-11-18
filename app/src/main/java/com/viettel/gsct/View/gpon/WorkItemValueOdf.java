package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
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
import com.viettel.gsct.View.constant.Constant;
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
    private ConstrNodeEntity node;
    private Sub_Work_ItemEntity swiEntity;
    private String odfTAG = "";


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

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_lapdat_odf_gpon, this);
        ButterKnife.bind(this);
        sWIValueController = new Sub_Work_Item_ValueController(context);

        edtKhoiLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (".".equalsIgnoreCase(editable.toString())) {
                    edtKhoiLuong.setError("Không phải là số!");
                    edtKhoiLuong.requestFocus();
                }
            }
        });
    }

    public void setTvTenOdf(String tvTenOdf) {
        this.tvTenOdf.setText(tvTenOdf);
    }

    public void setTvLuyKe(double luyKe) {
        this.tvLuyKe.setText(String.valueOf((int)luyKe));
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf((int)khoiLuong));
    }

    public String getTvTenOdf() {
        return tvTenOdf.getText().toString().trim();
    }

    public int getDoubleKhoiLuong() {
        return (int)(edtKhoiLuong.getText().toString().trim().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString().trim()));
    }

    public int getDoubleOldLuyKe() {
        if (Constant.TAG_LAPDAT_ODF_INDOOR.equalsIgnoreCase(odfTAG)) {
            return (int)(Sub_Work_ItemController.getInstance(getContext()).getOldLuyke(wIEntity.getId(), cSWIEntity.getId()));
        } else {
            return (int)(sWIValueController.getOldLuykeByNode(wIEntity.getId(), cSWIEntity.getId(),node.getNodeID()));
        }
    }

    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
    }

    public void addSWIEntity(Sub_Work_ItemEntity itemEntity) {
        this.swiEntity = itemEntity;
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

    // Save odf indoor theo cong trinh.
    @Override
    public void save() {
        super.save();
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        if (edtKhoiLuong.getText().toString().isEmpty()) {
            return;
        }
        new SaveAsynByCt().execute(value);
    }

    // Save odf out door theo node.
    @Override
    public void save(long nodeId) {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        if (edtKhoiLuong.getText().toString().isEmpty()) {
            return;
        }
        new SaveAsyncByNode().execute(nodeId, value);
    }

    public void updateLuyKe() {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        double luyke = 0;
        if (Constant.TAG_LAPDAT_ODF_INDOOR.equalsIgnoreCase(odfTAG)) {
            luyke = Sub_Work_ItemController.getInstance(getContext()).getOldLuyke(wIEntity.getId(), cSWIEntity.getId()) + value;
        } else {
            luyke = sWIValueController.getOldLuykeByNode(wIEntity.getId(), cSWIEntity.getId(),node.getNodeID()) + value;
        }
        setTvLuyKe(luyke);
    }

    public void setFinish(boolean finish) {
        edtKhoiLuong.setEnabled(!finish);
    }

    public void setOdfTAG(String odfTAG) {
        this.odfTAG = odfTAG;
    }

    class SaveAsyncByNode extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Object... objects) {
            long nodeId = Long.parseLong(objects[0].toString());
            double value = Double.parseDouble(objects[1].toString());
            boolean isSWIValueUpdate = false;
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
            updateLuyKe();
        }
    }

    class SaveAsynByCt extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... objects) {
            double value = Double.parseDouble(objects[0].toString());
            boolean isSWIUpdate = false;
            // Cap nhat sub work item vao Database.
            if (swiEntity == null) {
                swiEntity = new Sub_Work_ItemEntity(cSWIEntity.getId());
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
                swiEntity.setId(id);
                swiEntity.setWork_item_id(wIEntity.getId());
                swiEntity.setFinishDate(GSCTUtils.getDateNow());
                isSWIUpdate = false;
            } else {
                isSWIUpdate = true;
            }
            swiEntity.setSyncStatus(swiEntity.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            swiEntity.setEmployeeId(BaseFragment.userId);
            swiEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
            swiEntity.setValue(value);
            return isSWIUpdate;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                Sub_Work_ItemController.getInstance(getContext()).updateItem(swiEntity);
            } else {
                Sub_Work_ItemController.getInstance(getContext()).addItem(swiEntity);
            }
            updateLuyKe();
        }
    }
}
