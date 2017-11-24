package com.viettel.gsct.View.gpon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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

import java.util.Locale;

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
    private ConstrNodeEntity node;
    private boolean isValidate = true;

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

        edtKhoiLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isValidate = true;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 11 && !editable.toString().contains(".")) {
                    edtKhoiLuong.setInputType(InputType.TYPE_CLASS_NUMBER);
                } else {
                    edtKhoiLuong.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
                }
                if (editable.toString().contains(".")) {
                    String subAfterDot = editable.toString().substring(editable.toString().indexOf(".") + 1);
                    if (subAfterDot.isEmpty()) {
                        String strValidtae = "Không phải là số!";
                        edtKhoiLuong.setError(strValidtae);
                        edtKhoiLuong.requestFocus();
                        isValidate = false;
                    }
                }
            }
        });
    }

    public void setTvItemLoaiCap(String tvItemLoaiCap) {
        this.tvItemLoaiCap.setText(tvItemLoaiCap);
    }

    public String getTenItem() {
        return tvItemLoaiCap.getText().toString();
    }

    public void setTvLuyKe(double luyKe) {
        if (luyKe % 1 == 0) {
            this.tvLuyKe.setText(String.valueOf((long)luyKe));
        } else {
            this.tvLuyKe.setText(String.format(Locale.UK,"%.2f",luyKe));
        }
    }

    public void setEdtKhoiLuong(double khoiLuong) {
        if (khoiLuong % 1 == 0) {
            edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.valueOf((long)khoiLuong));
        } else {
            edtKhoiLuong.setText(khoiLuong == 0 ? "" : String.format(Locale.UK,"%.2f",khoiLuong));
        }
    }

    public String getKhoiLuong() {
        return ""+edtKhoiLuong.getText().toString();
    }

    public double getDoubleKhoiLuong() {
        return edtKhoiLuong.getText().toString().trim().isEmpty() ? 0 : Double.valueOf(edtKhoiLuong.getText().toString().trim());
    }

    // Lay so luy ke tru ngay hom nay.
    public double getDoubleOldLuyKe() {
        return Sub_Work_Item_ValueController.getInstance(getContext()).getOldLuykeByNode(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID());
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

    @Override
    public void save(long nodeId) {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        if (edtKhoiLuong.getText().toString().isEmpty()) {
            return;
        }
        new SaveAsync().execute(nodeId, value);
    }

    public void updateLuyKe() {
        double value = edtKhoiLuong.getText().toString().isEmpty() ? 0 : Double.parseDouble(edtKhoiLuong.getText().toString());
        double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getOldLuykeByNode(wIEntity.getId(),cSWIEntity.getId(),node.getNodeID()) + value;
        setTvLuyKe(luyke);
    }

    public void setFinish(boolean isFinish) {
        edtKhoiLuong.setEnabled(!isFinish);
    }

    class SaveAsync extends AsyncTask<Object,Void,Boolean> {

        @Override
        protected Boolean doInBackground(Object... objects) {
            boolean isSWIValueUpdate = false;
            long nodeId = Long.parseLong(objects[0].toString());
            double value = Double.parseDouble(objects[1].toString());
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
                    Sub_Work_Item_ValueController.getInstance(getContext()).updateItem(swiValue);
                } else {
                    Sub_Work_Item_ValueController.getInstance(getContext()).addItem(swiValue);
                }
                updateLuyKe();
        }
    }

    @Override
    public boolean validate() {
        return isValidate;
    }
}
