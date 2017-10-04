package com.viettel.gsct.fragment.tiendo.gpon.view;

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
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.WorkItemRightLapDatOdfGpon;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemRightHanNoiBoChiaGpon;
import com.viettel.gsct.View.gpon.WorkItemRightHanNoiTuThueGpon;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.IeGponTienDoPresenter;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.GponTienDoTienDoPresenter;
import com.viettel.ktts.R;
import com.viettel.view.listener.IeSave;
import com.viettel.view.listener.IeValidate;

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

public class GPONTiendo2Fragment extends BaseTienDoFragment
        implements IeSave.IeCapNhatTienDoInteractor, IeValidate.IecheckValidateTienDo
        , IeGponTienDoFragment, WorkItemGPONView.IeShowWorkItemByItemType {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.txtNgayCapNhat)
    TextView tvDate;
    @BindView(R.id.layout_root_left)
    LinearLayout layoutRootLeft;
    @BindView(R.id.layout_root_right)
    LinearLayout layoutRootRight;
    @BindView(R.id.layoutRightForKeoCap)
    LinearLayout layoutForRightKeoCap;
    @BindView(R.id.layoutRightForHanNoi)
    LinearLayout layoutRightForHanNoi;
    @BindView(R.id.layoutRightForHanNoiBoChia)
    LinearLayout layoutRightForHanNoiBoChia;
    @BindView(R.id.layoutRightForHanNoiTuThue)
    LinearLayout layoutRightForHanNoiTuThue;
    @BindView(R.id.layoutRightForOdfOutdoor)
    LinearLayout layoutRightForOdfOutdoor;
    @BindView(R.id.layoutRightForOdfIndoor)
    LinearLayout layoutRightForOdfIndoor;
    @BindView(R.id.layoutRightForOlt)
    LinearLayout layoutRightForOlt;
    @BindView(R.id.layoutRightForDoKiem)
    LinearLayout layoutRightForDoKiem;

    private Unbinder unbinder;
    private boolean dialogDismissFlag = true;
    private boolean isFinish = false;
    // Presenter cho cap nhat tien do Gpon.
    private IeGponTienDoPresenter itemGponPresenter;

    private ConcurrentHashMap<Long,
            SubWorkItemGPONView> hashSubWorkItemViews = new ConcurrentHashMap<>();

    Sub_Work_Item_ValueController sub_work_item_value_controller;

    private boolean flagIsRealFinish = true;

    public static BaseFragment newInstance() {
        return new GPONTiendo2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gpon_tien_do_2, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initViews();
        initData();
        return layout;
    }

    /**
     * Khoi tao all View.
     */
    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
        Log.d(TAG, "initViews() called");
        // Khoi tao presenter.
        itemGponPresenter = new GponTienDoTienDoPresenter(this);
    }

    /**
     * Khoi tao all Data.
     */
    public void initData() {
        super.initData();
        Log.d(TAG, "initData() called");
        sub_work_item_value_controller = new Sub_Work_Item_ValueController(getContext());

        //Hardcode, fuck it
        final HashSet<String> codes = new HashSet<>();
//        codes.add("CAPQUANG_SO8_BRCD");
//        codes.add("CAPQUANG_ADSS_BRCD");
        codes.add("KEOCAP_BRCD");
        codes.add("HANNOI_BRCD");
        codes.add("LAPDAT_BRCD");
        codes.add("DOKIEM_BRCD");

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
                ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController
                        .getItems(workItem.getId());
                for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
                    workItem.addSubWorkItem(subWorkItem);
                    hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
                }
                if (flagIsRealFinish) {
                    flagIsRealFinish = workItem.isCompleted();
                }
            }
        }

        ArrayList<Cat_Work_Item_TypesEntity> arr = cat_work_item_typesControler
                .getCates(constr_ConstructionItem.getConstrType());
        if (arr.isEmpty()) {
            showNotSyncDialog();
            return;
        }

        String[] arrWorkItem = getResources().getStringArray(R.array.gpon_work_item);
        for (String anArrWorkItem : arrWorkItem) {
            Work_ItemsEntity workItem = new Work_ItemsEntity();
            workItem.setWork_item_name(anArrWorkItem);
            if (Constant.TAG_LAPDAT_ODF_INDOOR.equals(anArrWorkItem)
                    || Constant.TAG_LAPDAT_OLT.equals(anArrWorkItem)) {
                // Them item cho work item by cong trinh.
                itemGponPresenter.addWorkItemGponByCongTrinh(workItem, getContext());
            } else {
                // Them item cho work item by node.
                itemGponPresenter.addWorkItemGponByNode(workItem, getContext());
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            Log.d(TAG, "initData: get code = " + arr.get(i).getCode());
            if (codes.contains(arr.get(i).getCode())) {
//                WorkItemGPONView view = new WorkItemGPONView(getContext());
                Work_ItemsEntity workItem = hashWorkItems.get(arr.get(i).getItem_type_id());
                if (workItem == null) {
                    continue;
                }
                workItem.setWork_item_code(arr.get(i).getCode());
//                view.setTitle(workItem.getWork_item_name());

//                // Them item cho work item.
//                itemGponPresenter.addWorkItemGponByNode(workItem, getContext());

                ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                        = cat_sub_work_itemControler.getsubCates(arr.get(i).getItem_type_id());
                /*for (*//*int j = 0; j < arrSubWorkItems.size(); j++*//*int j = 0; j < 2; j++) {
                    SubWorkItemGPONView subView = new SubWorkItemGPONView(getContext());
//                    Sub_Work_ItemEntity subWorkItem = hashSubWorkItems.get(arrSubWorkItems.get(j).getId());
//                    if (subWorkItem == null) {
//                        subWorkItem = new Sub_Work_ItemEntity();
//                        subWorkItem.setCat_sub_work_item_id(arrSubWorkItems.get(j).getId());
//                        if (workItem.getId() > 0)
//                            subWorkItem.setWork_item_id(workItem.getId());
//                        workItem.addSubWorkItemByNode(subWorkItem);
//                    }
//                    subWorkItem.setCode(arrSubWorkItems.get(j).getCode());
//                    view.addRightItemSubView(subView);
//                    hashSubWorkItemViews.put(arrSubWorkItems.get(j).getId(), subView);
//                    Sub_Work_Item_ValueEntity subWorkItemValue
//                            = sub_work_item_value_controller.getItem(subWorkItem.getWork_item_id(),
//                            subWorkItem.getCat_sub_work_item_id());
                    double luyke = 0*//*sub_work_item_value_controller
                            .getLuyke(subWorkItem.getWork_item_id(),
                                    subWorkItem.getCat_sub_work_item_id())*//*;
                    luykes.add(luyke);
                    double value = *//*subWorkItemValue != null ? subWorkItemValue.getValue() :*//* 0;
                    subView.setValue("Node",
                            value, luyke, ""*//*arrSubWorkItems.get(j).getUnitName()*//*);
                    subView.setWorkItemGPONView(view);
                    view.addRightItemSubView(subView);
                    view.setRadioButton(subView.getRadioBtnCheck());
                    layoutRootLeft.addView(subView);
                    itemGponPresenter.addSubWorkItemGponByNode(gponView, getContext());
                    subView.setOnRadioCheckChangeListener(this);

                    LinearLayout layoutRight = new LinearLayout(getContext());
                    layoutRight.setOrientation(LinearLayout.VERTICAL);
                    layoutRight.setVisibility(View.GONE);
                    if (i == 0) {
                        layoutForRightKeoCap.addView(layoutRight);
                        subView.setSubWorkRightLayout(layoutRight);
                        for (int index = 0; index < 4; index++) {
                            WorkItemRightKeoCapGpon keoCapGpon
                                    = new WorkItemRightKeoCapGpon(getContext());
                            keoCapGpon.setTvItemLoaiCap("Loai " + index);
                            keoCapGpon.setEdtKhoiLuong("" + index);
                            keoCapGpon.setTvLuyKe("" + index);
                            subView.addSubWorkRightItem(keoCapGpon);
                        }
                        if (j == 0) {
                            subView.getRadioBtnCheck().setChecked(true);
                        }
                    } else if (i == 1) {
                        layoutRightForHanNoi.addView(layoutRight);
                        subView.setSubWorkRightLayout(layoutRight);
                        for (int index = 0; index < 4; index++) {
                            WorkItemRightLapDatHanNoiGpon hanNoiGpon
                                    = new WorkItemRightLapDatHanNoiGpon(getContext());
                            hanNoiGpon.setTvTenTu("Tu " + index);
                            hanNoiGpon.setTvLuyKe("" + index);
                            hanNoiGpon.setTvLuyKeLapDat("" + index);
                            hanNoiGpon.setTvLuyKeHanNoi("" + index);
                            subView.addSubWorkRightItem(hanNoiGpon);
                        }
                    } else if (i == 2) {
                        layoutRightForOdfOutdoor.addView(layoutRight);
                        subView.setSubWorkRightLayout(layoutRight);
                        for (int index = 0; index < 4; index++) {
                            WorkItemRightLapDatOdfGpon lapDatOdfGpon
                                    = new WorkItemRightLapDatOdfGpon(getContext());
                            lapDatOdfGpon.setTvTenOdf("Odf " + index);
                            lapDatOdfGpon.setTvLuyKe("" + index);
                            subView.addSubWorkRightItem(lapDatOdfGpon);
                        }
                    }
                }*/
//                switch (i) {
//                    case 0:
//                        for (int index = 0; index < 4; index++) {
//                            WorkItemRightKeoCapGpon keoCapGpon = new WorkItemRightKeoCapGpon(getContext());
//                            keoCapGpon.setTvItemLoaiCap("Loai " + index);
//                            keoCapGpon.setEdtKhoiLuong("" + index);
//                            keoCapGpon.setTvLuyKe("" + index);
//                            layoutForRightKeoCap.addView(keoCapGpon);
//                        }
////                        itemGponPresenter.addItemToRightKeoCap();
//                        break;
//                    case 1:
//                        for (int index = 0; index < 4; index++) {
//                            WorkItemRightLapDatHanNoiGpon hanNoiGpon
//                                    = new WorkItemRightLapDatHanNoiGpon(getContext());
//                            hanNoiGpon.setTvTenTu("Tu " + index);
//                            hanNoiGpon.setTvLuyKe("" + index);
//                            hanNoiGpon.setTvLuyKeLapDat("" + index);
//                            hanNoiGpon.setTvLuyKeHanNoi("" + index);
//                            layoutRightForHanNoi.addView(hanNoiGpon);
//                        }
////                        itemGponPresenter.addItemToRightHanNoi();
//                        break;
//                    case 2:
//                        for (int index = 0; index < 4; index++) {
//                            WorkItemRightLapDatOdfGpon lapDatOdfGpon
//                                    = new WorkItemRightLapDatOdfGpon(getContext());
//                            lapDatOdfGpon.setTvTenOdf("Odf " + index);
//                            lapDatOdfGpon.setTvLuyKe("" + index);
//                            layoutRightForOdfOutdoor.addView(lapDatOdfGpon);
//                        }
////                        itemGponPresenter.addItemToRightLapDatOdf();
//                        break;
//                    case 3:
////                        itemGponPresenter.addItemToRightLapDatOlt();
//                        break;
//                    case 4:
////                        itemGponPresenter.addItemToRightDoKiem();
//                        break;
//                    default:
//                        break;
//                }

            }


//            if (rightCodes.contains(arr.get(i).getCode())) {
//                WorkItemRightGPONView rightView = new WorkItemRightGPONView(getContext());
//                Work_ItemsEntity workItem = hashWorkItems.get(arr.get(i).getItem_type_id());
//                if (workItem == null) {
//                    continue;
//                }
//                workItem.setWork_item_code(arr.get(i).getCode());
//                rightView.setWorkItem(workItem);
//
//                //validate nut kéo c
//                if (arr.get(i).getCode().equals("KEOCAP_BRCD")) {
//                    final ArrayList<SubWorkItemGPONView> itemEntities
//                            = new ArrayList<SubWorkItemGPONView>();
//                    rightView.setFinishListener(new WorkItemRightGPONView.FinishListener() {
//                        @Override
//                        public boolean onFinishListener() {
//                            Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
//                            while (enums.hasMoreElements()) {
//                                SubWorkItemGPONView subView = enums.nextElement();
//                                if (subView.getTvTitle().equals(getString(R.string.str_hop_quang_8Fo))) {
//                                    continue;
//                                }
//                                if (subView.getTvTitle().equals(getString(R.string.str_fdh_16fo))) {
//                                    continue;
//                                }
//                                if (subView.getTvTitle().equals(getString(R.string.str_hop_quang_32fo))) {
//                                    continue;
//                                }
//                                itemEntities.add(subView);
//                            }
//                            if (itemEntities.size() > 0) {
//                                for (int i = 0; i < itemEntities.size(); i++) {
//                                    if (itemEntities.get(i).getValue() > 0) {
//                                        return true;
//                                    }
//                                }
//                            }
//                            showError("Bạn chưa nhập khối lượng cho hạng mục");
//                            return false;
//                        }
//                    });
//                    rightView.setOnStatusBtnTienDo(new WorkItemRightGPONView.OnStatusBtnTienDo() {
//                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                        @Override
//                        public void onStatusHoanThanh(final boolean isHoanThanh) {
//                            for (int i = 0; i < itemEntities.size(); i++) {
//                                final AppCompatEditText edtValue = itemEntities.get(i).getEtValue();
//                                setStatusEdtValueFollowStatus(edtValue, isHoanThanh);
//                            }
//                        }
//                    });
//                }
//                // Validtae nut Han Noi.
//                if (arr.get(i).getCode().equals("HANNOI_BRCD")) {
//                    final ArrayList<Cat_Sub_Work_ItemEntity> itemEntities
//                            = cat_sub_work_itemControler.getsubCates(arr.get(i).getItem_type_id());
//                    rightView.setFinishListener(new WorkItemRightGPONView.FinishListener() {
//                        @Override
//                        public boolean onFinishListener() {
//                            for (Cat_Sub_Work_ItemEntity catSubWorkItem : itemEntities) {
//                                SubWorkItemGPONView view
//                                        = hashSubWorkItemViews.get(catSubWorkItem.getId());
//                                if (view.getValue() > 0) {
//                                    return true;
//                                }
//                            }
//                            showError("Bạn chưa nhập khối lượng cho hạng mục");
//                            return false;
//                        }
//                    });
//                    rightView.setOnStatusBtnTienDo(new WorkItemRightGPONView.OnStatusBtnTienDo() {
//                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//                        @Override
//                        public void onStatusHoanThanh(final boolean isHoanThanh) {
//                            for (int i = 0; i < itemEntities.size(); i++) {
//                                final AppCompatEditText edtValue = hashSubWorkItemViews
//                                        .get(itemEntities.get(i).getId()).getEtValue();
//                                setStatusEdtValueFollowStatus(edtValue, isHoanThanh);
//                            }
//                        }
//                    });
//                }
//                layoutRootRight.addView(rightView);
//            }
        }
////         Hard code chu biet lam sao duoc,haizz.
//        for (int index = 0; index < layoutRootLeft.getChildCount(); index++) {
//            if (layoutRootLeft.getChildAt(index) instanceof WorkItemGPONView) {
//                WorkItemGPONView view = (WorkItemGPONView) layoutRootLeft.getChildAt(index);
//            }
//            if (layoutRootLeft.getChildAt(index) instanceof SubWorkItemGPONView) {
//                SubWorkItemGPONView view = (SubWorkItemGPONView) layoutRootLeft.getChildAt(index);
//            }
//        }
    }

    /**
     * Luu data xuong database.
     */
    @Override
    public void save() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành," +
                    " bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return;
        }

        super.save();
        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());

        Enumeration<Long> keys = hashWorkItems.keys();
        while (keys.hasMoreElements()) {
            Work_ItemsEntity workItem = hashWorkItems.get(keys.nextElement());
            Log.d(TAG, "save() called" + "Work item entity code =" + workItem.getWork_item_code());
            if (workItem.isCompleted()) {
                Log.d(TAG, "save() called" + " Sub work item size complete = "
                        + workItem.getSubWorkItems().size());
            }
            if (workItem.getWork_item_code() == null) {
                Log.d(TAG, "save() called" + " Sub work item size null = "
                        + workItem.getSubWorkItems().size());
            }
            if (workItem.isCompleted() || workItem.getWork_item_code() == null)
                continue;

            workItem.setSyncStatus(workItem.getProcessId() > 0
                    ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
            workItem.updateDate();
            if (workItem.getStarting_date().length() == 0) {
                workItem.setStarting_date(GSCTUtils.getDateNow());
            }
            Log.d(TAG, "save() called" + "Work item has updated date - name = "
                    + workItem.getWork_item_name());

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
            Log.e(TAG, "save: " + workItem.getId() + " "
                    + workItem.getWork_item_code() + " " + workItem.getWork_item_name());
            if (hashPlanCodes.containsKey(workItem.getWork_item_code())) {
                planController.updateRealFinishDate(constr_ConstructionItem.getConstructId()
                        , hashPlanCodes.get(workItem.getWork_item_code()), workItem.getComplete_date());
            }
//            Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
            for (Sub_Work_ItemEntity subWorkItem : workItem.getSubWorkItems()) {
                Log.d(TAG, "save() called" + " Sub_Work_ItemEntity " + subWorkItem.getCode());
                subWorkItem.setWork_item_id(workItem.getId());
                subWorkItem.setFinishDate(workItem.getComplete_date());
                subWorkItem.setSyncStatus(subWorkItem.getProcessId() > 0
                        ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
                subWorkItem.setEmployeeId(userId);
                subWorkItem.setIsActive(Constants.ISACTIVE.ACTIVE);
                if (subWorkItem.getId() > 0) {
                    sub_work_itemController.updateItem(subWorkItem);
                } else {
                    long id = Ktts_KeyController.getInstance()
                            .getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
                    subWorkItem.setId(id);
                    sub_work_itemController.addItem(subWorkItem);
                }

                SubWorkItemGPONView view = hashSubWorkItemViews
                        .get(subWorkItem.getCat_sub_work_item_id());
//                SubWorkItemGPONView view = enums.nextElement();
//                while (enums.hasMoreElements()) {
//                    view = enums.nextElement();
//                    Log.d(TAG,"SubWorkItemGPONView Name = " + view.getTvTitle());
//                }
                Log.d(TAG, "SUb work item name = " + view.getTvTitle());
                double value = view != null ? view.getValue() : 0L;
                Sub_Work_Item_ValueEntity subWorkItemValue = sub_work_item_value_controller
                        .getItem(subWorkItem.getWork_item_id(),
                                subWorkItem.getCat_sub_work_item_id());
                if (subWorkItemValue == null) {
                    subWorkItemValue = new Sub_Work_Item_ValueEntity();
                    subWorkItemValue.setWork_item_id(subWorkItem.getWork_item_id());
                    subWorkItemValue.setCat_sub_work_item_id(subWorkItem
                            .getCat_sub_work_item_id());
                    subWorkItemValue.setValue(value);
                }

                subWorkItemValue.setValue(value);
                subWorkItemValue.setAdded_date(GSCTUtils.getDateNow());
                subWorkItemValue.setEmployeeId(userId);
                subWorkItemValue.setIsActive(Constants.ISACTIVE.ACTIVE);
                subWorkItemValue.setSyncStatus(subWorkItemValue.getProcessId() > 0
                        ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
                if (subWorkItemValue.getId() > 0) {
                    sub_work_item_value_controller.updateItem(subWorkItemValue);
                } else {
                    subWorkItemValue.setId(Ktts_KeyController.getInstance()
                            .getKttsNextKey(Sub_Work_Item_ValueField.TABLE_NAME));
                    sub_work_item_value_controller.addItem(subWorkItemValue);
                }

                double luyke = sub_work_item_value_controller.getLuyke(
                        subWorkItem.getWork_item_id(), subWorkItem.getCat_sub_work_item_id());
                Log.d(TAG, "Luy ke = " + luyke);
                if (view != null) {
                    view.setLuyke(luyke);
                }
            }
        }

        Toast.makeText(getContext(), "Cập nhật tiến độ thành công", Toast.LENGTH_SHORT).show();

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
                Enumeration<SubWorkItemGPONView> enums = hashSubWorkItemViews.elements();
                while (enums.hasMoreElements()) {
                    SubWorkItemGPONView view = enums.nextElement();
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

    /**
     * Thêm WorkItem cho Gpon.
     *
     * @param view WorkItemGPONView.
     */
    @Override
    public void addWorkItemGponByNode(View view) {
        layoutRootLeft.addView(view);
        WorkItemGPONView gponView = (WorkItemGPONView) view;
        gponView.setShowWorkItemByItemType(this);
        // Dummy 2 node.
        for (int j = 0; j < 2; j++) {
            // Them item cho Sub Work item.
            itemGponPresenter.addSubWorkItemGponByNode(gponView, getContext());
        }
    }

    @Override
    public void addWorkItemGponByCongTrinh(View view) {
        layoutRootLeft.addView(view);
        WorkItemGPONView gponView = (WorkItemGPONView) view;
        gponView.setShowWorkItemByItemType(this);
        itemGponPresenter.addRightSubWorkGponByCongTrinh(gponView,getContext());
    }

    /**
     * Thêm SubWorkItem cho Gpon,được get từ mỗi WorkItem tương ứng.
     *
     * @param view SubWorkItemGPONView.
     */
    @Override
    public void addSubWorkItemGponByNode(View view) {
//        SubWorkItemGPONView subView = (SubWorkItemGPONView) view;
        layoutRootLeft.addView(view);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addRightSubWorkGponByNode(view, getContext());
    }

    /**
     * Thêm layout right,layout này sẽ được lấy ra và hiển thị theo mỗi SubWorkItem tương ứng.
     *
     * @param subView SubWorkItemGPONView.
     */
    @Override
    public void addRightSubWorkItemGponByNode(View subView) {
        SubWorkItemGPONView subWorkItemGPONView = (SubWorkItemGPONView) subView;
        ArrayList<View> allSubViews = subWorkItemGPONView.getAllRightSubViews();
        switch (subWorkItemGPONView.getWorkItemGPONView().getTvTitle().trim()) {
            case Constant.TAG_KEOCAP:
                for (View view : allSubViews) {
                    layoutForRightKeoCap.addView(view);
                }
                break;
            case Constant.TAG_LAPDAT_HANNOI:
                for (View view : allSubViews) {
                    if (view instanceof WorkItemRightHanNoiBoChiaGpon) {
                        WorkItemRightHanNoiBoChiaGpon boChiaView
                                = (WorkItemRightHanNoiBoChiaGpon) view;
                        Log.d(TAG, "addRightSubWorkItemGponByNode: " + "Add layout for bo chia");
                        layoutRightForHanNoiBoChia.addView(boChiaView);
                    } else if (view instanceof WorkItemRightHanNoiTuThueGpon) {
                        WorkItemRightHanNoiTuThueGpon tuThueView
                                = (WorkItemRightHanNoiTuThueGpon) view;
                        Log.d(TAG, "addRightSubWorkItemGponByNode: " + "Add layout for tu thue");
                        layoutRightForHanNoiTuThue.addView(tuThueView);
                    }
                }
                break;
//            case Constant.TAG_LAPDAT_ODF_INDOOR:
//                break;
            case Constant.TAG_LAPDAT_ODF_OUTDOOR:
                for (View view : allSubViews) {
                    layoutRightForOdfOutdoor.addView(view);
                }
                break;
//            case Constant.TAG_LAPDAT_OLT:
//                for (View view : allSubViews) {
//                    layoutRightForOlt.addView(view);
//                }
//                break;
            case Constant.TAG_DOKIEM_NGHIEMTHU:
                for (View view : allSubViews) {
                    layoutRightForDoKiem.addView(view);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addRightSubWorkItemGponByCongTrinh(View subView) {
        WorkItemGPONView workItem = (WorkItemGPONView) subView;
        ArrayList<View> allSubViews = workItem.getAllRightSubViews();
        Log.d(TAG, "addRightSubWorkItemGponByCongTrinh: title = " + workItem.getTvTitle());
        switch (workItem.getTvTitle()) {
            case Constant.TAG_LAPDAT_ODF_INDOOR:
                for (View view : allSubViews) {
                    WorkItemRightLapDatOdfGpon odfView = (WorkItemRightLapDatOdfGpon) view;
                    Log.d(TAG, "addRightSubWorkItemGponByCongTrinh: odf title = " + odfView.getTvTenOdf());
                    layoutRightForOdfIndoor.addView(view);
                }
                break;
            case Constant.TAG_LAPDAT_OLT:
                for (View view : allSubViews) {
                    layoutRightForOlt.addView(view);
                }
                break;
        }
    }

    /**
     * Ẩn toàn bộ phần layout bên phải, hiện thị các layout tùy theo WorkItem.
     */
    private void hideAllLayout() {
        layoutForRightKeoCap.setVisibility(View.GONE);
        layoutRightForHanNoi.setVisibility(View.GONE);
        layoutRightForOdfIndoor.setVisibility(View.GONE);
        layoutRightForOdfOutdoor.setVisibility(View.GONE);
        layoutRightForOlt.setVisibility(View.GONE);
        layoutRightForDoKiem.setVisibility(View.GONE);
        for (int i = 0; i < layoutRootLeft.getChildCount(); i++) {
            if (layoutRootLeft.getChildAt(i) instanceof WorkItemGPONView) {
                WorkItemGPONView gponView = (WorkItemGPONView) layoutRootLeft.getChildAt(i);
                gponView.getRootLayout().setBackgroundColor(
                        getResources().getColor(R.color.white_color));
            }
        }
    }

    /**
     * Hiển thị layout theo WorkItem tương ứng.
     *
     * @param strType String.
     */
    @Override
    public void showWorkItemByItemType(String strType) {
        hideAllLayout();
        switch (strType) {
            case Constant.TAG_KEOCAP:
                layoutForRightKeoCap.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_HANNOI:
                layoutRightForHanNoi.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_ODF_INDOOR:
                layoutRightForOdfIndoor.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_ODF_OUTDOOR:
                layoutRightForOdfOutdoor.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_OLT:
                layoutRightForOlt.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_DOKIEM_NGHIEMTHU:
                layoutRightForDoKiem.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    /**
     * Gui thong tin thong qua Event Bus.
     */
    public void registerListenerEventBus() {
        EventBus.getDefault().post(layoutRootRight);
        Log.d(TAG, "registerListenerEventBus() called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d(TAG, "onDestroyView() called");
    }
}
