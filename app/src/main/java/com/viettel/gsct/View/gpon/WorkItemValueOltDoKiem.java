package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.os.AsyncTask;
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
    private ConstrNodeEntity node;

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

    public void addSWIEntity(Sub_Work_ItemEntity itemEntity) {
        this.swiEntity = itemEntity;
        if (swiEntity != null) {
            if (swiEntity.hasFinishDate()) {
                btnTienDo.setText(getResources().getString(R.string.str_tiendo_hoanthanh));
                if (!swiEntity.getFinishDate().equalsIgnoreCase(GSCTUtils.getDateNow())) {
                    btnTienDo.setEnabled(false);
                }
            }
        }
    }

    public void addSWIValue(Sub_Work_Item_ValueEntity entity) {
        this.swiValue = entity;
        if (swiValue != null) {
            if (swiValue.hadAddedDate()) {
                btnTienDo.setText(getResources().getString(R.string.str_tiendo_hoanthanh));
                if (!swiValue.getAdded_date().equalsIgnoreCase(GSCTUtils.getDateNow())) {
                    btnTienDo.setEnabled(false);
                }
            }
        }
    }

    public void addCSWIEntity(Cat_Sub_Work_ItemEntity entity) {
        this.cswiEntity = entity;
    }

    public void addCTNodeEntity(ConstrNodeEntity node) {
        this.node = node;
    }

    // Save Do kiem,cap nhat theo node.
    @Override
    public void save(long nodeId) {
        new SaveAsyncByNode().execute(nodeId,isFinish());
    }

    // Sace olt cap nhat theo cong trinh.
    @Override
    public void save() {
        if (swiEntity != null) {
            if (swiEntity.hasFinishDate() && !swiEntity.getFinishDate().equalsIgnoreCase(GSCTUtils.getDateNow())) {
                return;
            }
        }
        new SaveAsynByCt().execute(isFinish());
    }

    // Check cong trinh hoan thanh chua,chi check trong ngay.
    public boolean isFinish() {
        return getResources().getString(R.string.str_tiendo_hoanthanh).equalsIgnoreCase(btnTienDo.getText().toString());
    }

    public Sub_Work_ItemEntity getSwiEntity() {
        return swiEntity;
    }

    public Sub_Work_Item_ValueEntity getSwiValue() {
        return swiValue;
    }

    class SaveAsynByCt extends AsyncTask<Boolean, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Boolean... objects) {
            boolean isSWIUpdate = false;
            // Cap nhat sub work item vao Database.
            if (swiEntity == null) {
                swiEntity = new Sub_Work_ItemEntity(cswiEntity.getId());
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
                swiEntity.setId(id);
                swiEntity.setWork_item_id(wiEntity.getId());
                isSWIUpdate = false;
            } else {
                isSWIUpdate = true;
            }
            if (objects[0]) {
                if (!swiEntity.hasFinishDate()) {
                    swiEntity.setFinishDate(GSCTUtils.getDateNow());
                }
            }
            swiEntity.setSyncStatus(swiEntity.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            swiEntity.setEmployeeId(BaseFragment.userId);
            swiEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
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
        }
    }

    class SaveAsyncByNode extends AsyncTask<Object, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Object... objects) {
            long nodeId = Long.parseLong(objects[0].toString());
            boolean isFinish = Boolean.parseBoolean(objects[1].toString());
            boolean isSWIValueUpdate = false;

            // Cap nhat sub work item value vao Database.
            if (swiValue == null) {
                swiValue = new Sub_Work_Item_ValueEntity();
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME);
                swiValue.setId(id);
                swiValue.setConstr_node_id(nodeId);
                swiValue.setWork_item_id(wiEntity.getId());
                swiValue.setCat_sub_work_item_id(cswiEntity.getId());
                isSWIValueUpdate = false;
            } else {
                isSWIValueUpdate = true;
            }
            Log.d(TAG, "doInBackground: isFinish = " + isFinish);
            if (isFinish) {
                if (!swiValue.hadAddedDate()) {
                    swiValue.setAdded_date(GSCTUtils.getDateNow());
                }
            }
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
        }
    }
}
