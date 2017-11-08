package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.Work_ItemsControler;
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

public class WorkItemValueOltDoKiem extends BaseCustomWorkItem {

    private static final String TAG = WorkItemValueOltDoKiem.class.getSimpleName();
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_tien_do)
    AppCompatButton btnTienDo;

    private Cat_Sub_Work_ItemEntity cswiEntity;
    private Work_ItemsEntity wiEntity;
    private Sub_Work_ItemEntity swiEntity;
    private Sub_Work_Item_ValueEntity swiValue;

    public WorkItemValueOltDoKiem(Context context) {
        super(context);
        init(context);
    }

    public WorkItemValueOltDoKiem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemValueOltDoKiem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public boolean isValidate() {
        return true;
    }

    @Override
    public boolean isWorking() {
        return true;
    }

    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_olt_dokiem_gpon, this);
        ButterKnife.bind(this);

        final String chuaLam = getResources().getString(R.string.str_tiendo_chualam);
        final String hoanThanh = getResources().getString(R.string.str_tiendo_hoanthanh);

        btnTienDo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tienDo = btnTienDo.getText().toString().trim();
                btnTienDo.setText(tienDo.equalsIgnoreCase(chuaLam) ? hoanThanh : chuaLam);
            }
        });
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle.setText(tvTitle);
    }

    public String getTenTitle() {
        return tvTitle.getText().toString();
    }

    public void addWIEntity(Work_ItemsEntity entity) {
        this.wiEntity = entity;

    }

    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
    }

    public void addCSWIEntity(Cat_Sub_Work_ItemEntity entity) {
        this.cswiEntity = entity;
        if (wiEntity != null) {
            swiEntity = Sub_Work_ItemController.getInstance(getContext()).getItem(wiEntity.getId(),cswiEntity.getId());
            if (swiEntity != null) {
                if (swiEntity.isCompleted()) {
                    btnTienDo.setText(getResources().getString(R.string.str_tiendo_hoanthanh));
                    if (!GSCTUtils.getDateNow().equalsIgnoreCase(swiEntity.getFinishDate())) {
                        btnTienDo.setEnabled(false);
                    }
                }
            }

        }
    }

    public boolean isFinish() {
        return getResources().getString(R.string.str_tiendo_hoanthanh).equalsIgnoreCase(btnTienDo.getText().toString().trim());
    }

    // Save Do kiem,cap nhat theo node.
    @Override
    public void save(long nodeId) {
        boolean isSWIValueUpdate = false;

        // Cap nhat sub work item value vao Database.
        if (swiValue == null) {
            swiValue = new Sub_Work_Item_ValueEntity();
            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME);
            swiValue.setId(id);
            swiValue.setConstr_node_id(nodeId);
            swiValue.setWork_item_id(wiEntity.getId());
            swiValue.setCat_sub_work_item_id(cswiEntity.getId());
//            swiValue.setAdded_date(GSCTUtils.getDateNow());
            isSWIValueUpdate = false;
        } else {
            isSWIValueUpdate = true;
        }
        if (isFinish()) {
            swiValue.setAdded_date(GSCTUtils.getDateNow());
        }
        swiValue.setEmployeeId(BaseFragment.userId);
        swiValue.setIsActive(Constants.ISACTIVE.ACTIVE);
        swiValue.setSyncStatus(swiValue.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);

        if (isSWIValueUpdate) {
            Sub_Work_Item_ValueController.getInstance(getContext()).updateItem(swiValue);
        } else {
            Sub_Work_Item_ValueController.getInstance(getContext()).addItem(swiValue);
        }
    }

    // Sace olt cap nhat theo cong trinh.
    @Override
    public void save() {
        boolean isSWIUpdate = false;
        // Cap nhat sub work item vao Database.
        swiEntity = Sub_Work_ItemController.getInstance(getContext()).getItem(wiEntity.getId(),cswiEntity.getId());
        if (swiEntity == null) {
            swiEntity = new Sub_Work_ItemEntity(cswiEntity.getId());
            long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
            swiEntity.setId(id);
            swiEntity.setWork_item_id(wiEntity.getId());
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

    @Override
    public void updateTrangThai() {

    }

    public Cat_Sub_Work_ItemEntity getCswiEntity() {
        return cswiEntity;
    }

    public Sub_Work_ItemEntity getSwiEntity() {
        return swiEntity;
    }
}
