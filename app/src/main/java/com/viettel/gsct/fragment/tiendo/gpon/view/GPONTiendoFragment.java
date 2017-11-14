package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.viettel.gsct.View.gpon.SubWorkItemGponOldView;
import com.viettel.gsct.View.gpon.WorkItemGponOldView;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.gsct.View.gpon.WorkItemRightGPONView;
import com.viettel.ktts.R;
import com.viettel.view.listener.IeSave;
import com.viettel.view.listener.IeValidate;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class GPONTiendoFragment extends BaseTienDoFragment {
    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    @BindView(R.id.layout_root_right)
    LinearLayout layoutRootRight;
    private Unbinder unbinder;
    private boolean dialogDismissFlag = true;
    private boolean isFinish = false;
    public static ArrayList<Double> luykes;
    private static GPONTiendoFragment fragment;

    private ConcurrentHashMap<Long, SubWorkItemGponOldView> hashSubWorkItemViews = new ConcurrentHashMap<>();

    private Sub_Work_Item_ValueController sub_work_item_value_controller;
    private HashMap<String,SubWorkItemGponOldView> hmSubWorkItem = new HashMap<>();

    private boolean flagIsRealFinish = true;
//    private ConcurrentHashMap<Long, ArrayList<Sub_Work_ItemField>> hash

    public static BaseFragment newInstance() {
        if (fragment == null) {
            fragment = new GPONTiendoFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gpon_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initViews();
        initData();
        return layout;
    }

    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
        Log.d(TAG, "initViews() called");
    }

    public void initData() {
        super.initData();
        Log.d(TAG, "initData() called");
        luykes = new ArrayList<>();
        sub_work_item_value_controller = new Sub_Work_Item_ValueController(getContext());

        //Hardcode, fuck it
        final HashSet<String> codes = new HashSet<>();
        codes.add("CAPQUANG_SO8_BRCD");
        codes.add("CAPQUANG_ADSS_BRCD");
        codes.add("HANNOI_BRCD");

        HashSet<String> rightCodes = new HashSet<>();
        rightCodes.add("KEOCAP_BRCD");
        rightCodes.add("HANNOI_BRCD");
        rightCodes.add("LAPDAT_BRCD");
        rightCodes.add("DOKIEM_BRCD");

        hashPlanCodes.put("KEOCAP_BRCD", "GPON_Ngay_hoan_thanh_keo_cap");
        hashPlanCodes.put("HANNOI_BRCD", "GPON_Ngay_hoan_thanh_han_noi");
        hashPlanCodes.put("DOKIEM_BRCD", "GPON_Ngay_hoan_thanh_do_kiem_nghiem_thu");
        hashPlanCodes.put("LAPDAT_BRCD", "GPON_Ngay_hoan_thanh_lap_dat");

//        WorkItemRightGPONView rightView = new WorkItemRightGPONView(getContext());

        if (workItems != null && !workItems.isEmpty()) {
            for (Work_ItemsEntity workItem : workItems) {
                Log.d(TAG, "Work item entity name = " + workItem.getWork_item_name());
                if (workItem.hasCompletedDate()) {
                    workItem.setStatus_id(Work_ItemsEntity.STATUS_COMPLETE);
                }
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
        for (final Cat_Work_Item_TypesEntity entity : arr) {
            if (codes.contains(entity.getCode())) {
                WorkItemGponOldView view = new WorkItemGponOldView(getContext());
                Work_ItemsEntity workItem = hashWorkItems.get(entity.getItem_type_id());
                if (workItem == null) {
                    continue;
                }
                workItem.setWork_item_code(entity.getCode());
                view.setTitle(workItem.getWork_item_name());
                layoutRoot.addView(view, ++i);

                ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems = cat_sub_work_itemControler.getsubCates(entity.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity catSubWorkItem : arrSubWorkItems) {
                    SubWorkItemGponOldView subView = new SubWorkItemGponOldView(getContext());
                    Sub_Work_ItemEntity subWorkItem = hashSubWorkItems.get(catSubWorkItem.getId());
                    if (subWorkItem == null) {
                        subWorkItem = new Sub_Work_ItemEntity();
                        subWorkItem.setCat_sub_work_item_id(catSubWorkItem.getId());
                        if (workItem.getId() > 0) {
                            subWorkItem.setWork_item_id(workItem.getId());
                        }
                        workItem.addSubWorkItem(subWorkItem);
                    }
                    subWorkItem.setCode(catSubWorkItem.getCode());
                    view.addSubView(subView);
                    hashSubWorkItemViews.put(catSubWorkItem.getId(), subView);
                    Sub_Work_Item_ValueEntity subWorkItemValue = sub_work_item_value_controller.getItem(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                    double luyke = sub_work_item_value_controller.getLuyke(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                    luykes.add(luyke);
                    double value = subWorkItemValue != null ? subWorkItemValue.getValue() : 0;
                    subView.setValue(catSubWorkItem.getName(), value, luyke, catSubWorkItem.getUnitName());
                    hmSubWorkItem.put(catSubWorkItem.getName(),subView);
                    layoutRoot.addView(subView, ++i);
                }
            }

            if (rightCodes.contains(entity.getCode())) {
                WorkItemRightGPONView rightView = new WorkItemRightGPONView(getContext());
                Work_ItemsEntity workItem = hashWorkItems.get(entity.getItem_type_id());
                if (workItem == null) {
                    continue;
                }
                workItem.setWork_item_code(entity.getCode());
                rightView.setWorkItem(workItem);

                //validate nut kéo c
                if (entity.getCode().equals("KEOCAP_BRCD")) {
                    final ArrayList<SubWorkItemGponOldView> itemEntities = new ArrayList<>();
                    rightView.setFinishListener(new WorkItemRightGPONView.FinishListener() {
                        @Override
                        public boolean onFinishListener() {
                            Enumeration<SubWorkItemGponOldView> enums = hashSubWorkItemViews.elements();
                            while (enums.hasMoreElements()) {
                                SubWorkItemGponOldView subView = enums.nextElement();
                                if (subView.getTvTitle().equals(getString(R.string.str_hop_quang_8Fo))) {
                                    continue;
                                }
                                if (subView.getTvTitle().equals(getString(R.string.str_fdh_16fo))) {
                                    continue;
                                }
                                if (subView.getTvTitle().equals(getString(R.string.str_hop_quang_32fo))) {
                                    continue;
                                }
                                itemEntities.add(subView);
                            }
                            if (itemEntities.size() > 0) {
                                for (int i = 0; i < itemEntities.size(); i++) {
                                    if (itemEntities.get(i).getValue() > 0) {
                                        return true;
                                    }
                                }
                            }
                            showError("Bạn chưa nhập khối lượng cho hạng mục");
                            return false;
                        }
                    });
                    rightView.setOnStatusBtnTienDo(new WorkItemRightGPONView.OnStatusBtnTienDo() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onStatusHoanThanh(final boolean isHoanThanh) {
                            for (int i = 0; i < itemEntities.size(); i++) {
                                final AppCompatEditText edtValue = itemEntities.get(i).getEtValue();
                                setStatusEdtValueFollowStatus(edtValue, isHoanThanh);
                            }
                        }
                    });
                }
                // Validate nut Han Noi.
                if (entity.getCode().equals("HANNOI_BRCD")) {
                    final ArrayList<Cat_Sub_Work_ItemEntity> itemEntities = cat_sub_work_itemControler.getsubCates(entity.getItem_type_id());
                    rightView.setFinishListener(new WorkItemRightGPONView.FinishListener() {
                        @Override
                        public boolean onFinishListener() {
                            for (Cat_Sub_Work_ItemEntity catSubWorkItem : itemEntities) {
                                SubWorkItemGponOldView view = hashSubWorkItemViews.get(catSubWorkItem.getId());
                                if (view.getValue() > 0) {
                                    return true;
                                }
                            }
                            showError("Bạn chưa nhập khối lượng cho hạng mục");
                            return false;
                        }
                    });
                    rightView.setOnStatusBtnTienDo(new WorkItemRightGPONView.OnStatusBtnTienDo() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onStatusHoanThanh(final boolean isHoanThanh) {
                            for (int i = 0; i < itemEntities.size(); i++) {
                                final AppCompatEditText edtValue = hashSubWorkItemViews.get(itemEntities.get(i).getId()).getEtValue();
                                setStatusEdtValueFollowStatus(edtValue, isHoanThanh);
                            }
                        }
                    });
                }
                layoutRootRight.addView(rightView, j++);
            }
        }
    }

    @Override
    public void save() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành," + " bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return;
        }

        super.save();
        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());
        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
            Log.d(TAG, "save() called" + "Work item entity code =" + workItem.getWork_item_code());
            if (workItem.isCompleted()) {
                Log.d(TAG, "save() called" + " Sub work item size complete = " + workItem.getSubWorkItems().size());
            }
            if (workItem.getWork_item_code() == null){
                Log.d(TAG, "save() called" + " Sub work item size null = " + workItem.getSubWorkItems().size());
            }
            if (workItem.isCompleted() || workItem.getWork_item_code() == null)
                continue;
            workItem.setSyncStatus(workItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
            workItem.updateDate();
            if (workItem.getStarting_date().length() == 0) {
                workItem.setStarting_date(GSCTUtils.getDateNow());
            }
            Log.d(TAG, "save() called" + "Work item has updated date - name = " + workItem.getWork_item_name());

            if (workItem.getStatus_id() == 403 && workItem.getWork_item_code().equals("KEOCAP_BRCD")) {
                boolean flagValue = false;
                Enumeration<SubWorkItemGponOldView> enums = hashSubWorkItemViews.elements();
                while (enums.hasMoreElements()) {
                    SubWorkItemGponOldView view = enums.nextElement();
                    if (view.getValue() > 0) {
                        flagValue = true;
                        break;
                    }
                }
                if (!flagValue) {
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
            if (hashPlanCodes.containsKey(workItem.getWork_item_code())) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), hashPlanCodes.get(workItem.getWork_item_code()), workItem.getComplete_date());
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

                SubWorkItemGponOldView view = hashSubWorkItemViews.get(subWorkItem.getCat_sub_work_item_id());
                Log.d(TAG, "SUb work item name = " + view.getTvTitle());
                double value = view != null ? view.getValue() : 0L;
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
                Log.d(TAG, "Luy ke = " + luyke);
                if (view != null) {
                    view.setLuyke(luyke);
                }
            }
        }

        Toast.makeText(getContext(), "Cập nhật tiến độ thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d(TAG, "onDestroyView() called");
    }

    /**
     * Kiem tra validate cho cap nhat tien do.
     *
     * @return boolean.
     */
    public boolean checkValidateGponTienDo() {
        Log.d(TAG, "checkValidateGponTienDo() called");
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành," +
                    " bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
            if (workItem.isCompleted() || workItem.getWork_item_code() == null)
                continue;

            workItem.setSyncStatus(workItem.getProcessId() > 0
                    ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
//            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
//            workItem.updateDate();

            if (workItem.getStatus_id() == 403
                    && workItem.getWork_item_code().equals("KEOCAP_BRCD")) {
                boolean flagValue = false;
                Enumeration<SubWorkItemGponOldView> enums = hashSubWorkItemViews.elements();
                while (enums.hasMoreElements()) {
                    SubWorkItemGponOldView view = enums.nextElement();
                    if (view.getValue() > 0) {
                        flagValue = true;
                        break;
                    }
                }
                if (!flagValue) {
                    showError("Bạn chưa nhập khối lượng cho hạng mục");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void saveTienDo() {
        save();
    }

    @Override
    public boolean checkValidateTienDo() {
        return checkValidateGponTienDo();
    }

    /**
     * Set trang thai input cho edt khoi luong,trong truong hop la da hoan thanh hay chua.
     *
     * @param editText    Edittext.
     * @param isHoanThanh boolean.
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setStatusEdtValueFollowStatus(
            AppCompatEditText editText, final boolean isHoanThanh) {
        editText.setClickable(!isHoanThanh);
        editText.setAlpha(isHoanThanh ? 0.5f : 1);
        editText.setShowSoftInputOnFocus(!isHoanThanh);
        editText.setFocusableInTouchMode(!isHoanThanh);
        editText.setFocusable(!isHoanThanh);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (isHoanThanh) {
                        Toast.makeText(getActivity(),
                                "Bạn chỉ có thể nhâp khối lượng trong trạng thái chưa làm!",
                                Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
                return false;
            }
        });
        Log.d(TAG, "setStatusEdtValueFollowStatus() called with: editText = ["
                + editText + "], isHoanThanh = [" + isHoanThanh + "]");
    }

    @Override
    public void showPreviewTienDo(BaseGponPreview mGponPrevFrag) {
        super.showPreviewTienDo(mGponPrevFrag);
        mGponPrevFrag.initDataOldTienDoPreview(hmSubWorkItem);
    }
}
