package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Plan_Constr_DetailController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.SubWorkItemGPONView;
import com.viettel.gsct.View.WorkItemGPONView;
import com.viettel.gsct.View.WorkItemRightGPONView;
import com.viettel.ktts.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class GPONTiendoFragment extends BaseTienDoFragment {
    private static final String TAG = "BtsTiendoFragment";
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    @BindView(R.id.layout_root_right)
    LinearLayout layoutRootRight;
    private Unbinder unbinder;
    private boolean dialogDismissFlag = true;
    public boolean mIsCheckValidate;

    private ConcurrentHashMap<Long, SubWorkItemGPONView> hashSubWorkItemViews = new ConcurrentHashMap<>();

    Sub_Work_Item_ValueController sub_work_item_value_controller;

    private boolean flagIsRealFinish = true;
//    private ConcurrentHashMap<Long, ArrayList<Sub_Work_ItemField>> hash

    public static BaseFragment newInstance() {
        GPONTiendoFragment fragment = new GPONTiendoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gpon_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initViews();
        initData();
        return layout;
    }

    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
    }

    public void initData() {
        super.initData();
        sub_work_item_value_controller = new Sub_Work_Item_ValueController(getContext());

        //Hardcode, fuck it
        HashSet<String> codes = new HashSet<>();
        codes.add("CAPQUANG_SO8_BRCD");
        codes.add("CAPQUANG_ADSS_BRCD");

        HashSet<String> rightCodes = new HashSet<>();
        rightCodes.add("KEOCAP_BRCD");
        rightCodes.add("HANNOI_BRCD");
        rightCodes.add("LAPDAT_BRCD");
        rightCodes.add("DOKIEM_BRCD");

        hashPlanCodes.put("KEOCAP_BRCD", "GPON_Ngay_hoan_thanh_keo_cap");
        hashPlanCodes.put("HANNOI_BRCD", "GPON_Ngay_hoan_thanh_han_noi");
        hashPlanCodes.put("DOKIEM_BRCD", "GPON_Ngay_hoan_thanh_do_kiem_nghiem_thu");
        hashPlanCodes.put("LAPDAT_BRCD", "GPON_Ngay_hoan_thanh_lap_dat");

        if (workItems != null && !workItems.isEmpty()) {
            for (Work_ItemsEntity workItem : workItems) {
                if (workItem.hasCompletedDate())
                    workItem.setStatus_id(Work_ItemsEntity.STATUS_COMPLETE);
                hashWorkItems.put(workItem.getItem_type_id(), workItem);
                ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController.getItems(workItem.getId());
                for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
                    workItem.addSubWorkItem(subWorkItem);
                    hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
                }
                if (flagIsRealFinish)
                    flagIsRealFinish = workItem.isCompleted();
            }
        }

        ArrayList<Cat_Work_Item_TypesEntity> arr = cat_work_item_typesControler.getCates(constr_ConstructionItem.getConstrType());
        if (arr.isEmpty()) {
            showNotSyncDialog();
            return;
        }
        int i = 1;
        int j = 1;
        for (Cat_Work_Item_TypesEntity entity : arr) {
            if (codes.contains(entity.getCode())) {
                WorkItemGPONView view = new WorkItemGPONView(getContext());
                Work_ItemsEntity workItem = hashWorkItems.get(entity.getItem_type_id());
                if (workItem == null) {
                    continue;
//                    workItem = new Work_ItemsEntity();
//                    workItem.setItem_type_id(entity.getItem_type_id());
//                    workItem.setConstr_id(constr_ConstructionItem.getConstructId());
//                    workItem.setWork_item_name(entity.getItem_type_name());
//                    hashWorkItems.put(workItem.getItem_type_id(), workItem);
                }
                workItem.setWork_item_code(entity.getCode());
                view.setTitle(workItem.getWork_item_name());
                layoutRoot.addView(view, ++i);

                ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems = cat_sub_work_itemControler.getsubCates(entity.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity catSubWorkItem : arrSubWorkItems) {
//                Log.e(TAG, "initData: " + catSubWorkItem.getId() + " " + catSubWorkItem.getName() );
                    SubWorkItemGPONView subView = new SubWorkItemGPONView(getContext());
                    Sub_Work_ItemEntity subWorkItem = hashSubWorkItems.get(catSubWorkItem.getId());
                    if (subWorkItem == null) {
                        subWorkItem = new Sub_Work_ItemEntity();
                        subWorkItem.setCat_sub_work_item_id(catSubWorkItem.getId());
                        if (workItem.getId() > 0)
                            subWorkItem.setWork_item_id(workItem.getId());
                        workItem.addSubWorkItem(subWorkItem);
                    }
                    subWorkItem.setCode(catSubWorkItem.getCode());
                    view.addSubView(subView);
                    hashSubWorkItemViews.put(catSubWorkItem.getId(), subView);
                    Sub_Work_Item_ValueEntity subWorkItemValue = sub_work_item_value_controller.getItem(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                    double luyke = sub_work_item_value_controller.getLuyke(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                    double value = subWorkItemValue != null ? subWorkItemValue.getValue() : 0;
                    subView.setValue(catSubWorkItem.getName(), value, luyke, catSubWorkItem.getUnitName());
                    layoutRoot.addView(subView, ++i);
                }
            } else {
                if (rightCodes.contains(entity.getCode())) {
                    WorkItemRightGPONView view = new WorkItemRightGPONView(getContext());
                    Work_ItemsEntity workItem = hashWorkItems.get(entity.getItem_type_id());
                    if (workItem == null) {
                        continue;
//                        workItem = new Work_ItemsEntity();
//                        workItem.setItem_type_id(entity.getItem_type_id());
//                        workItem.setConstr_id(constr_ConstructionItem.getConstructId());
//                        workItem.setWork_item_name(entity.getItem_type_name());
//                        hashWorkItems.put(workItem.getItem_type_id(), workItem);
                    }
                    workItem.setWork_item_code(entity.getCode());
                    view.setWorkItem(workItem);

                    //validate nut kéo c
                    if (entity.getCode().equals("KEOCAP_BRCD")) {
                        view.setFinishListener(new WorkItemRightGPONView.FinishListener() {
                            @Override
                            public boolean onFinishListener() {
                                Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
                                while (enums.hasMoreElements()) {
                                    SubWorkItemGPONView view = enums.nextElement();
                                    if (view.getValue() > 0)
                                        return true;
                                }
                                showError("Bạn chưa nhập khối lượng cho hạng mục");
                                return false;
                            }
                        });
                    }

                    layoutRootRight.addView(view, j++);
                }
            }
        }
    }

    @Override
    public void save() {
        mIsCheckValidate = true;
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            mIsCheckValidate = false;
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành, bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return;
        }

        super.save();
        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());

        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
            if (workItem.isCompleted() || workItem.getWork_item_code() == null)
                continue;

            workItem.setSyncStatus(workItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
            workItem.updateDate();

            if (workItem.getStatus_id() == 403 && workItem.getWork_item_code().equals("KEOCAP_BRCD")) {
                boolean flagValue = false;
                Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
                while (enums.hasMoreElements()) {
                    SubWorkItemGPONView view = enums.nextElement();
                    if (view.getValue() > 0) {
                        flagValue = true;
                        break;
                    }
                }
                if (!flagValue) {
                    mIsCheckValidate = false;
                    showError("Bạn chưa nhập khối lượng cho hạng mục");
                    return;
                }
            }
            if (workItem.getId() > 0) {
                work_itemsControler.updateItem(workItem);
            } else {
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Work_ItemsField.TABLE_NAME);
                workItem.setId(id);
                work_itemsControler.addItem(workItem);
            }
            Log.e(TAG, "save: " + workItem.getId() + " " + workItem.getWork_item_code() + " " + workItem.getWork_item_name() );
            if (hashPlanCodes.containsKey(workItem.getWork_item_code())) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId()
                        , hashPlanCodes.get(workItem.getWork_item_code()), workItem.getComplete_date());
            }

            for (Sub_Work_ItemEntity subWorkItem : workItem.getSubWorkItems()) {
                subWorkItem.setWork_item_id(workItem.getId());
                subWorkItem.setFinishDate(workItem.getComplete_date());
                subWorkItem.setSyncStatus(subWorkItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
                subWorkItem.setEmployeeId(userId);
                subWorkItem.setIsActive(Constants.ISACTIVE.ACTIVE);
                if (subWorkItem.getId() > 0) {
                    sub_work_itemController.updateItem(subWorkItem);
                } else {
                    long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
                    subWorkItem.setId(id);
                    sub_work_itemController.addItem(subWorkItem);
                }

                SubWorkItemGPONView view = hashSubWorkItemViews.get(subWorkItem.getCat_sub_work_item_id());
                double value = view != null ? view.getValue() : 0L;
//                Log.e(TAG, "save: " + subWorkItem.hashCode() + " " + (view == null) );
//                Log.e(TAG, "save: " + subWorkItem.getId() + " " + workItem.getWork_item_name() + " " + value );
                Sub_Work_Item_ValueEntity subWorkItemValue = sub_work_item_value_controller.getItem(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                if (subWorkItemValue == null) {
                    subWorkItemValue = new Sub_Work_Item_ValueEntity();
                    subWorkItemValue.setWork_item_id(subWorkItem.getWork_item_id());
                    subWorkItemValue.setCat_sub_work_item_id(subWorkItem.getCat_sub_work_item_id());
                    subWorkItemValue.setValue(value);
                }

                subWorkItemValue.setValue(value);
                subWorkItemValue.setAdded_date(GSCTUtils.getDateNow());
                subWorkItemValue.setEmployeeId(userId);
                subWorkItemValue.setIsActive(Constants.ISACTIVE.ACTIVE);
                subWorkItemValue.setSyncStatus(subWorkItemValue.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
                if (subWorkItemValue.getId() > 0) {
                    sub_work_item_value_controller.updateItem(subWorkItemValue);
                } else {
                    subWorkItemValue.setId(Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME));
                    sub_work_item_value_controller.addItem(subWorkItemValue);
                }

                double luyke = sub_work_item_value_controller.getLuyke(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                view.setLuyke(luyke);
            }
        }

        Toast.makeText(getContext(), "Cập nhật tiến độ thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void registerListenerEventBus() {
        EventBus.getDefault().post(layoutRootRight);
    }

    public boolean checkValidateGponTienDo() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành, bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
            if (workItem.isCompleted() || workItem.getWork_item_code() == null)
                continue;

            workItem.setSyncStatus(workItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
            workItem.updateDate();

            if (workItem.getStatus_id() == 403 && workItem.getWork_item_code().equals("KEOCAP_BRCD")) {
                boolean flagValue = false;
                Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
                while (enums.hasMoreElements()) {
                    SubWorkItemGPONView view = enums.nextElement();
                    if (view.getValue() > 0) {
                        flagValue = true;
                        break;
                    }
                }
                if (!flagValue) {
                    mIsCheckValidate = false;
                    showError("Bạn chưa nhập khối lượng cho hạng mục");
                    return false;
                }
            }
        }
        return true;
    }
}
