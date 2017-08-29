package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.viettel.database.entity.ContentDetailItemProgressPreview;
import com.viettel.database.entity.ContentProgressPreview;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.SubWorkItemTienDoNgamView;
import com.viettel.gsct.View.WorkItemTienDoNgamView;
import com.viettel.ktts.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class TruyenDanNgamTiendoFragment extends BaseTienDoFragment {
    private static final String TAG = "NgamTiendoFragment";

    public static final int TYPE_TUYEN_NGAM = 1;
    public static final int TYPE_TUYEN_TREO = 2;

    private int type = TYPE_TUYEN_NGAM;

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;
    private Unbinder unbinder;
    private boolean dialogDismissFlag = true;

    private ConcurrentHashMap<Long, SubWorkItemTienDoNgamView> hashSubWorkItemViews = new ConcurrentHashMap<>();
    Sub_Work_Item_ValueController sub_work_item_value_controller;

    private boolean flagIsRealFinish = true;


//    private ConcurrentHashMap<Long, ArrayList<Sub_Work_ItemField>> hash

    public static BaseFragment newInstance(int type) {
        TruyenDanNgamTiendoFragment fragment = new TruyenDanNgamTiendoFragment();
        Bundle arg = new Bundle();
        arg.putInt("type", type);
        fragment.setArguments(arg);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_truyen_dan_ngam_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }

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
        if (workItems.isEmpty()) {
            showNotSyncDialog();
            return;
        }

        ArrayList<Cat_Work_Item_TypesEntity> arr = cat_work_item_typesControler.getCates(constr_ConstructionItem.getConstrType());
        if (arr.isEmpty()) {
            showNotSyncDialog();
            return;
        }

        HashSet<String> setCateTypeCode = new HashSet<>();
        if (type == TYPE_TUYEN_NGAM) {
            setCateTypeCode.add("TD_XD");
            setCateTypeCode.add("TD_KCNGAM");
            setCateTypeCode.add("TD_LTB");
            setCateTypeCode.add("TD_TQ");

            hashPlanCodes.put("TD_TQ", "NGAM_Ngay_hoan_thanh_han_noi");
            hashPlanCodes.put("TD_KCNGAM", "NGAM_Thoi_gian_bat_dau_keo_cap");
            hashPlanCodes.put("TD_KCNGAM", "NGAM_Thoi_gian_hoan_thanh_keo_cap");
        } else if (type == TYPE_TUYEN_TREO) {
            setCateTypeCode.add("TD_TC");
            setCateTypeCode.add("TD_KC");
            setCateTypeCode.add("TD_LTB");
            setCateTypeCode.add("TD_TQ");
        }

        Set<Long> itemTypeIds = new HashSet<>();
        for (final Cat_Work_Item_TypesEntity entity : arr) {
            if (!setCateTypeCode.contains(entity.getCode())) continue;
//            Log.e(TAG, "initData: " + entity.getCode() );
            itemTypeIds.add(entity.getItem_type_id());
        }

        if (workItems != null && !workItems.isEmpty()) {
            for (Work_ItemsEntity workItem : workItems) {
                if (!itemTypeIds.contains(workItem.getItem_type_id()))
                    continue;
                hashWorkItems.put(workItem.getItem_type_id(), workItem);
                ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController.getItems(workItem.getId());

                for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
                    workItem.addSubWorkItem(subWorkItem);
                    hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
                }
                // Nếu status_id = 403 (có ngày bắt đầu vào ngày kết thúc)
                // Nhưng ngày kết thúc == ngày hiện tại thì vẫn được sửa nội dung ==>>> Chưa thực sự kết thúc
                // Ngày kết thúc khác ngày hiện tại  ====>>>> thực sự đã kết thúc
                if (flagIsRealFinish)
                    flagIsRealFinish = workItem.isCompleted();
            }
        }

        int childCount = layoutRoot.getChildCount();
        for (final Cat_Work_Item_TypesEntity entity : arr) {

            // Lọc các code không đúng (hardcode)
            if (!setCateTypeCode.contains(entity.getCode())) continue;

            final WorkItemTienDoNgamView view = new WorkItemTienDoNgamView(getContext());
            Work_ItemsEntity workItem = hashWorkItems.get(entity.getItem_type_id());
//            Log.e(TAG, "initData: " + entity.getItem_type_id() + " " + (workItem == null) );
            if (workItem == null) {
                continue;
//                workItem = new Work_ItemsEntity();
//                workItem.setItem_type_id(entity.getItem_type_id());
//                workItem.setConstr_id(constr_ConstructionItem.getConstructId());
//                hashWorkItems.put(workItem.getItem_type_id(), workItem);
            }
            workItem.setWork_item_name(entity.getItem_type_name());
            workItem.setWork_item_code(entity.getCode());
//            Log.e(TAG, "initData: " + workItem.getId() + " " + workItem.getWork_item_name() );
            view.setWorkItemEntity(workItem);
            layoutRoot.addView(view, childCount++);

            ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems = cat_sub_work_itemControler.getsubCates(entity.getItem_type_id());
            for (Cat_Sub_Work_ItemEntity catSubWorkItem : arrSubWorkItems) {
                SubWorkItemTienDoNgamView subView = new SubWorkItemTienDoNgamView(getContext());
                view.addSubItem(subView);
                Sub_Work_ItemEntity subWorkItem = hashSubWorkItems.get(catSubWorkItem.getId());
                if (subWorkItem == null) {
                    subWorkItem = new Sub_Work_ItemEntity();
                    subWorkItem.setCat_sub_work_item_id(catSubWorkItem.getId());
                    subWorkItem.setCreatorId(userId);
                    workItem.addSubWorkItem(subWorkItem);
                }
                subWorkItem.setCode(catSubWorkItem.getCode());

                // Do server bi lỗi dữ liệu nên phải có đoạn code kiểm tra lại
                if (!workItem.getSubWorkItems().contains(subWorkItem)) {
                    workItem.addSubWorkItem(subWorkItem);
                }
//                Log.e(TAG, "initData: " + catSubWorkItem.getCat_work_item_type_id() + " " + subWorkItem.getWork_item_id() );
                hashSubWorkItemViews.put(catSubWorkItem.getId(), subView);
                Sub_Work_Item_ValueEntity subWorkItemValue = sub_work_item_value_controller.getItem(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                double luyke = sub_work_item_value_controller.getLuyke(subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                double value = subWorkItemValue != null ? subWorkItemValue.getValue() : 0;
                subView.setSubWorkItemEntity(subWorkItem, catSubWorkItem.getName(), value, luyke, catSubWorkItem.getUnitName());

                subView.setTextChangeListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.length() > 0) {
                            Work_ItemsEntity wi = hashWorkItems.get(entity.getItem_type_id());
                            if (wi != null && wi.getStatus_id() != Work_ItemsEntity.STATUS_COMPLETE && wi.getStatus_id() != Work_ItemsEntity.STATUS_WORKING) {
                                wi.setStatus_id(Work_ItemsEntity.STATUS_WORKING);
                                view.setWorkItemEntity(wi);
                            }
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                layoutRoot.addView(subView, childCount++);
            }
        }
    }

    @Override
    public void save() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành, bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return;
        }
        super.save();
        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());
        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
//            Log.e(TAG, "save: " + workItem.getId() + " " + workItem.isCompleted() + " " + workItem.getStatus_id() + " " + workItem.hashCode() );
            if (workItem.isCompleted())
                continue;

//            Log.e(TAG, "save: " + workItem.getStatus() );
            workItem.setSyncStatus(workItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
            workItem.updateDate();
            if (workItem.getId() > 0) {
                work_itemsControler.updateItem(workItem);
            } else {
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Work_ItemsField.TABLE_NAME);
                workItem.setId(id);
                work_itemsControler.addItem(workItem);
            }

            // tuyến cáp ngầm
            if (workItem.getWork_item_code().equals("TD_TQ")) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Ngay_hoan_thanh_han_noi", workItem.getComplete_date());
            } else if (workItem.getWork_item_code().equals("TD_KCNGAM")) {
                if (workItem.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE) {
                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_hoan_thanh_keo_cap", workItem.getComplete_date());
                    if (workItem.hasCompletedDate() && workItem.getComplete_date().equals(workItem.getStarting_date())) {
                        planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_bat_dau_keo_cap", workItem.getStarting_date());
                    }
                } else if (workItem.getStatus_id() == Work_ItemsEntity.STATUS_WORKING) {
                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_bat_dau_keo_cap", workItem.getStarting_date());
                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_hoan_thanh_keo_cap", "");
                } else {
                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_bat_dau_keo_cap", "");
                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "NGAM_Thoi_gian_hoan_thanh_keo_cap", "");
                }
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

                SubWorkItemTienDoNgamView view = hashSubWorkItemViews.get(subWorkItem.getCat_sub_work_item_id());
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

                // Tuyến cáp ngầm
                if (workItem.getWork_item_code().equals("TD_KCNGAM")) {
                    Log.e(TAG, "save: " + subWorkItem.getCode());
                    if (subWorkItem.getCode().equals("KC_DLCCTT")) {
                        planController.updateLuyKe(constr_ConstructionItem.getConstructId(), "NGAM_Dao_lap_cap_chon_truc_tiep", luyke);
                    } else if (subWorkItem.getCode().equals("KC_KCCB")) {
                        planController.updateLuyKe(constr_ConstructionItem.getConstructId(), "NGAM_Keo_cap_cong_be", luyke);
                    }
                }

                // Tuyến cáp treo
                if (workItem.getWork_item_code().equals("TD_KC")) {
                    if (subWorkItem.getCode().equals("SO_M_CAP_KEO_DUOC")) {
                        if (luyke > 0) {
                            if (workItem.getStatus_id() == 403) {
                                String realFinishDate = planController.getRealFinishDate(constr_ConstructionItem.getConstructId(), "Start_Date_Extensive_Cable");
                                if (realFinishDate == null || realFinishDate.length() == 0) {
                                    planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "Start_Date_Extensive_Cable", GSCTUtils.getDateNow());
                                }
                                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "End_Date_Extensive_Cable", GSCTUtils.getDateNow());
                            } else {
                                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "Start_Date_Extensive_Cable", GSCTUtils.getDateNow());
                                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "End_Date_Extensive_Cable", "");
                            }
                        } else {
                            if (workItem.getStatus_id() != 403) {
                                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "End_Date_Extensive_Cable", "");
                                if (workItem.getStatus_id() != 402) planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "Start_Date_Extensive_Cable", "");
                            }
                        }
                    }
                }
                view.setLuyke(luyke);
            }

            //Tuyến cáp treo
            if (workItem.getWork_item_code().equals("TD_TC")) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "End_Date_pillar_Cable", workItem.getStatus_id() == 403 ? workItem.getComplete_date() : "");
            }

            //Tuyến cáp treo
            if (workItem.getWork_item_code().equals("TD_TC") && workItem.getStatus_id() == 402) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "Start_Date_pillar_Cable", GSCTUtils.getDateNow());
            }
        }

        //Tuyến cáp treo
        planController.updateRealFinishDate(constr_ConstructionItem.getConstructId(), "End_Date_Usage", constr_ConstructionItem.getStatus() >= 395 ? GSCTUtils.getDateNow() : "");

        Toast.makeText(getContext(), "Cập nhật tiến độ thành công", Toast.LENGTH_SHORT).show();

    }

    public boolean checkValidateTuyenNgamTienDo() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành, bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void registerListenerEventBus() {
        EventBus.getDefault().post(layoutRoot);
    }
}
