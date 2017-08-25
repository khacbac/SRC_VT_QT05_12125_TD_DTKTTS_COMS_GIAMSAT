package com.viettel.gsct.fragment;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Plan_Constr_DetailController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.SubWorkItemTienDoBTSView;
import com.viettel.gsct.View.TiendoBTSItemView;
import com.viettel.gsct.View.WorkItemTienDoBTSView;
import com.viettel.ktts.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class BtsTiendoFragment extends BaseTienDoFragment implements WorkItemTienDoBTSView.RadioCheckListener {
    private static final String TAG = "BtsTiendoFragment";
    @BindView(R.id.tv_date)
    TextView tvDate;
    AppCompatEditText etDien;
    @BindView(R.id.layout_left)
    LinearLayout layoutLeft;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.layout_right)
    LinearLayout layoutRight;
    @BindView(R.id.tv_title_bbnt)
    TextView tvTitleBbnt;
    @BindView(R.id.et_bbnt)
    AppCompatEditText etBbnt;
    private Unbinder unbinder;

    private final String[] leftTitle = new String[]{"Nhà", "Cột", "Tiếp địa", "Điện (AC)", "Thiết bị", "Truyền dẫn"};

    private final String[] codeItemsNha = new String[]{"BTS_NCS_XAY", "BTS_NCS_CONT", "BTS_NXM_XAY", "BTS_NXM_CONT"};
    private final String[] codeItemsCot = new String[]{"BTS_ANTEN_COSAN", "BTS_ANTEN_MOI"};
    private final String[] codeItemsDien = new String[]{"BTS_DAC_COSAN", "BTS_DAC", "BTS_DIEN_CN", "BTS_BKKD"};
    private final String[] codeItemsTruyenDan = new String[]{"BTS_TD_COSAN", "HM_TDQ", "BTS_TDVB", "BTS_TDVISAT"};
    private final String[] codeItemTiepDia = new String[]{"BTS_TDCOSAN", "BTS_TDMOI"};
    private final String[] codeItemsThietbi = new String[]{"BTS_HMTB"};

    private ConcurrentHashMap<Integer, TiendoBTSItemView> hashTienDoViews = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, WorkItemTienDoBTSView> hashWorkItemViews = new ConcurrentHashMap<>();

    private long last_cat_work_item_type_id = 0;

    public static BaseFragment newInstance() {
        BtsTiendoFragment fragment = new BtsTiendoFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bts_tien_do, container, false);
        unbinder = ButterKnife.bind(this, layout);

        initViews();
        initData();
        return layout;
    }


    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));

        tvTitleRight.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tvTitleBbnt.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
    }

    public void initData() {
        super.initData();

        //hardcode để update RealFinishDate trong planDetail
        hashPlanCodes.put("BTS_ANTEN_CAPCOT", "Supply_Pillar_Date");
        hashPlanCodes.put("BTS_ANTEN_LAPCOT", "Assemble_Pillar_Date");
        hashPlanCodes.put("BTS_HMTB_CAPTHIETBI", "Supply_Device_Date");
        hashPlanCodes.put("BTS_HMTB_LAPTHIETBI", "Assemble_Device_Date");

        int i = 0;
        ArrayList<String[]> codes = new ArrayList<>();
        codes.add(codeItemsNha);
        codes.add(codeItemsCot);
        codes.add(codeItemTiepDia);
        codes.add(codeItemsDien);
        codes.add(codeItemsThietbi);
        codes.add(codeItemsTruyenDan);

        if (workItems != null) {
            for (Work_ItemsEntity workItem : workItems) {
                hashWorkItems.put(workItem.getItem_type_id(), workItem);
                if (workItem.isReady())
                    workItem.setStatus_id(403);
//                Log.e(TAG, "initData: " + workItem.getId() + " " + workItem.getItem_type_id() + " " + workItem.getWork_item_name() + " " + workItem.getIsActive());
            }
        }

        int index = 0;
        for (String title : leftTitle) {
            TiendoBTSItemView rootview = new TiendoBTSItemView(getContext());
            rootview.setTitle(title);
            ArrayList<Cat_Work_Item_TypesEntity> types = cat_work_item_typesControler.getCates(codes.get(index++));
            if (types.isEmpty()) {
                showNotSyncDialog();
                return;
            }
            layoutLeft.addView(rootview, i++);
            hashTienDoViews.put(index, rootview);
            ArrayList<WorkItemTienDoBTSView> temp = new ArrayList<>();
            boolean flagWorkItemComplete = false;
            for (Cat_Work_Item_TypesEntity type : types) {
                WorkItemTienDoBTSView view = new WorkItemTienDoBTSView(getContext());
                view.setVisibility(View.GONE);
                view.setRadioCheckListener(this);
                view.setTitle(index, type);
                if (hashWorkItems.containsKey(type.getItem_type_id())) {
                    Work_ItemsEntity workItem = hashWorkItems.get(type.getItem_type_id());
                    ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController.getItems(workItem.getId());
                    for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
                        hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
                    }
                    view.setChecked(true);
//                    Log.e(TAG, "initData: " + workItem.getId() + " " + workItem.getWork_item_name()  + " " + workItem.isCompleted() + " " + workItem.getUpdate_date() +" "+  flagWorkItemComplete);
                    if (!flagWorkItemComplete && (!workItem.isReady() && workItem.getStatus_id() == 403 && workItem.hasCompletedDate() && !GSCTUtils.getDateNow().equals(workItem.getComplete_date())))
//                            (workItem.getUpdate_date().length() > 0 && !GSCTUtils.getDateNow().equals(workItem.getUpdate_date())))
                        flagWorkItemComplete = true;
                }
                hashWorkItemViews.put(type.getItem_type_id(), view);
                layoutLeft.addView(view, i++);
                rootview.addSubView(view);
                view.addTienDoParentView(rootview);
                temp.add(view);
            }

            // Nếu workitem hoàn thành thì disable radiobutton
            for (WorkItemTienDoBTSView view : temp)
                view.setEnabledRadioBtn(!flagWorkItemComplete);

        }
    }

    private void updateBBNT() {
        if (last_cat_work_item_type_id > 0 && hashWorkItems.containsKey(last_cat_work_item_type_id)) {
            // Lưu lại số bbnt trước khi chuyển sang workitem khác
            Work_ItemsEntity workItem = hashWorkItems.get(last_cat_work_item_type_id);
            workItem.setAccept_note_code(etBbnt.getText().toString());
        }
    }

    private WorkItemTienDoBTSView lastWorkItemView = null;

    private void showSubWorkItem(final Cat_Work_Item_TypesEntity cat_work_item_type) {
        WorkItemTienDoBTSView view = hashWorkItemViews.get(cat_work_item_type.getItem_type_id());
        if (lastWorkItemView != null) {
            lastWorkItemView.setBackgroundResource(R.color.white);
            lastWorkItemView.getTienDoParentView().setBackgroundResource(R.color.white);
        }
        if (view != null) {
            view.setBackgroundResource(R.color.colorAccentLight);
            view.getTienDoParentView().setBackgroundResource(R.color.colorAccentLight);
            lastWorkItemView = view;
        }

        layoutRight.removeAllViews();
        tvTitleRight.setText("Tiến độ " + cat_work_item_type.getItem_type_name());
        updateBBNT();

        last_cat_work_item_type_id = cat_work_item_type.getItem_type_id();
        Work_ItemsEntity work_itemsEntity = hashWorkItems.get(cat_work_item_type.getItem_type_id());
        if (work_itemsEntity == null) {
            work_itemsEntity = work_itemsControler.getItem(constr_ConstructionItem.getConstructId(), cat_work_item_type.getItem_type_id());
            if (work_itemsEntity != null) work_itemsEntity.setChange(true);
        }
        if (work_itemsEntity != null) {
            if (!hashWorkItems.containsKey(work_itemsEntity.getItem_type_id()))
                hashWorkItems.put(work_itemsEntity.getItem_type_id(), work_itemsEntity);
            ArrayList<Sub_Work_ItemEntity> subWorkItems = sub_work_itemController.getItems(work_itemsEntity.getId());
            for (Sub_Work_ItemEntity subWorkItem : subWorkItems) {
                if (!hashSubWorkItems.containsKey(subWorkItem.getCat_sub_work_item_id()))
                    hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
            }
        } else  {
            work_itemsEntity = new Work_ItemsEntity();
            work_itemsEntity.setItem_type_id(cat_work_item_type.getItem_type_id());
            work_itemsEntity.setWork_item_name(cat_work_item_type.getItem_type_name());
            work_itemsEntity.setConstr_id(constr_ConstructionItem.getConstructId());
            work_itemsEntity.setChange(true);
            if (work_itemsEntity.isReady())
                work_itemsEntity.setStatus_id(403);
            hashWorkItems.put(work_itemsEntity.getItem_type_id(), work_itemsEntity);
        }

//        Log.e(TAG, "showSubWorkItem: " + work_itemsEntity.getId());
        etBbnt.setEnabled(!work_itemsEntity.isCompleted());
        etBbnt.setText(work_itemsEntity.getAccept_note_code());
        ArrayList<Cat_Sub_Work_ItemEntity> arrCatSubWorkItems = cat_sub_work_itemControler.getsubCates(cat_work_item_type.getItem_type_id());
        if (arrCatSubWorkItems.size() > 0) {
            for (Cat_Sub_Work_ItemEntity catSubWorkItem : arrCatSubWorkItems) {
                SubWorkItemTienDoBTSView subView = new SubWorkItemTienDoBTSView(getContext());
                subView.setTitle(catSubWorkItem);
                Sub_Work_ItemEntity subWorkItem = hashSubWorkItems.get(catSubWorkItem.getId());
//                Log.e(TAG, "showSubWorkItem: " +  catSubWorkItem.getId() + " " + (subWorkItem == null));
                subView.setFinishListener(new SubWorkItemTienDoBTSView.FinishListener() {
                    @Override
                    public void onFinishListener(boolean isFinish, long catSubWorkItemId) {

                        Sub_Work_ItemEntity subWorkItemEntity = hashSubWorkItems.get(catSubWorkItemId);
                        if (subWorkItemEntity != null) {
                            subWorkItemEntity.setFinishDate(isFinish ? GSCTUtils.getDateNow() : "");
//                            Log.e(TAG, "onFinishListener: " + subWorkItemEntity.getId() + " " + isFinish + " " + subWorkItemEntity.getFinishDate());
                        }

                    }
                });
                if (subWorkItem == null) {
                    subWorkItem = new Sub_Work_ItemEntity();
                    subWorkItem.setCat_sub_work_item_id(catSubWorkItem.getId());
                    subWorkItem.setCreatorId(userId);
                    hashSubWorkItems.put(subWorkItem.getCat_sub_work_item_id(), subWorkItem);
                }
//                Log.e(TAG, "showSubWorkItem: " + subWorkItem.getId() + " " + catSubWorkItem.getName() + " " + subWorkItem.getWork_item_id() + " " + subWorkItem.getFinishDate() );
                subWorkItem.setCode(catSubWorkItem.getCode());
                subView.setFinish(subWorkItem.getFinishDate() != null && subWorkItem.getFinishDate().length() > 0);
                subView.setEnableTiendo(!subWorkItem.isCompleted() || GSCTUtils.getDateNow().equals(subWorkItem.getFinishDate()));
                layoutRight.addView(subView);
            }
//            etBbnt.setEnabled(true);
        } else {
            SubWorkItemTienDoBTSView subView = new SubWorkItemTienDoBTSView(getContext());
            subView.setFinishListener(new SubWorkItemTienDoBTSView.FinishListener() {
                @Override
                public void onFinishListener(boolean isFinish, long catSubWorkItemId) {
                    Work_ItemsEntity work_itemsEntity = hashWorkItems.get(cat_work_item_type.getItem_type_id());
                    work_itemsEntity.setStatus_id(Work_ItemsEntity.STATUS_COMPLETE);
                }
            });
            subView.setFinish(work_itemsEntity.isReady() || work_itemsEntity.hasCompletedDate());
            subView.setEnableTiendo(!work_itemsEntity.hasCompletedDate() || GSCTUtils.getDateNow().equals(work_itemsEntity.getComplete_date()));
            if (work_itemsEntity.isReady())
                subView.setEnableTiendo(false);
            layoutRight.addView(subView);
//            etBbnt.setEnabled(false);
        }
    }


    @Override
    public void save() {
        boolean flagWorkItemChecked = false;
        Enumeration<Integer> keys = hashTienDoViews.keys();
        while (keys.hasMoreElements()) {
            TiendoBTSItemView parentView = hashTienDoViews.get(keys.nextElement());
            if (parentView.getCatWorkItemTypeId() == 0) {
                flagWorkItemChecked = true;
                break;
            }
        }
        if (flagWorkItemChecked) {
            showError("Bạn phải điền đủ thông tin các hạng mục chính");
            return;
        }

        updateBBNT();

        Enumeration<Work_ItemsEntity> enumWorkItems = hashWorkItems.elements();
        while (enumWorkItems.hasMoreElements()) {
            Work_ItemsEntity workItem = enumWorkItems.nextElement();
            workItem.setIsActive(Constants.ISACTIVE.DEACTIVE);
        }

        Enumeration<Sub_Work_ItemEntity> enumSubWorkItems = hashSubWorkItems.elements();
        while (enumWorkItems.hasMoreElements()) {
            Sub_Work_ItemEntity subWorkItem = enumSubWorkItems.nextElement();
            subWorkItem.setIsActive(Constants.ISACTIVE.DEACTIVE);
        }

        Enumeration<TiendoBTSItemView> parentViews = hashTienDoViews.elements();
        while (parentViews.hasMoreElements()) {
            TiendoBTSItemView parentView = parentViews.nextElement();
            Work_ItemsEntity workItem = hashWorkItems.get(parentView.getCatWorkItemTypeId());
            if (workItem == null) {
                workItem = new Work_ItemsEntity();
                WorkItemTienDoBTSView view = hashWorkItemViews.get(parentView.getCatWorkItemTypeId());
                Cat_Work_Item_TypesEntity catWorkItem = view.getEntity();
                workItem.setItem_type_id(catWorkItem.getItem_type_id());
                workItem.setConstr_id(constr_ConstructionItem.getConstructId());
                workItem.setWork_item_name(catWorkItem.getItem_type_name());
                if (workItem.isReady())
                    workItem.setStatus_id(403);
                hashWorkItems.put(workItem.getItem_type_id(), workItem);
                workItem.setChange(true);
            }
            workItem.setIsActive(Constants.ISACTIVE.ACTIVE);
        }

        Plan_Constr_DetailController planController = new Plan_Constr_DetailController(getContext());

        enumWorkItems = hashWorkItems.elements();
        boolean flagConstructions = true;
        while (enumWorkItems.hasMoreElements()) {
            Work_ItemsEntity workItem = enumWorkItems.nextElement();
            //WorkItem da hoan thanh, ngay hoan thanh khong phai ngay hien tai, khong can phai them sua xoa gi nua
//            Log.e(TAG, "save: complete " + workItem.getId() + " " + workItem.isCompleted() );
            if (workItem.isCompleted() && !workItem.isChange())
                continue;

            workItem.setSyncStatus(workItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            workItem.setEmployeeId(userId);

            workItem.updateDate();
            long workItemId = workItem.getId() == 0 ?
                    Ktts_KeyController.getInstance().getKttsNextKey(Work_ItemsField.TABLE_NAME) : workItem.getId();

            ArrayList<Cat_Sub_Work_ItemEntity> arrCatSubWorkItems = cat_sub_work_itemControler.getsubCates(workItem.getItem_type_id());
            Log.e(TAG, "save: " + workItem.getWork_item_name() + " " + arrCatSubWorkItems.size() + " " + workItem.getStatus_id() );

            if (arrCatSubWorkItems.size() > 0) {
                // Nếu có subWorkItem
                boolean flagComplete = true;
                boolean flagWorking = false;
                for (Cat_Sub_Work_ItemEntity catSubWorkItemEntity : arrCatSubWorkItems) {
                    Sub_Work_ItemEntity subWorkItemEntity = hashSubWorkItems.get(catSubWorkItemEntity.getId());
                    if (subWorkItemEntity != null) {
                        subWorkItemEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
                        subWorkItemEntity.setWork_item_id(workItemId);
                        if (flagComplete)
                            flagComplete = subWorkItemEntity.isCompleted();
                        if (!flagWorking)
                            flagWorking = subWorkItemEntity.isCompleted();
                        if (workItem.getStarting_date().length() == 0 && subWorkItemEntity.isCompleted()) {
                            workItem.setStarting_date(GSCTUtils.getDateNow());
                        }

                        if (hashPlanCodes.containsKey(subWorkItemEntity.getCode())) {
                            planController.updateRealFinishDate(constr_ConstructionItem.getConstructId()
                                    , hashPlanCodes.get(subWorkItemEntity.getCode()), subWorkItemEntity.getFinishDate());
                        }
                    }
                }
                if (flagConstructions)
                    flagConstructions = flagComplete;
                workItem.setStatus_id(flagWorking ? Work_ItemsEntity.STATUS_WORKING : Work_ItemsEntity.STATUS_NOT_START);
                workItem.setStatus_id(flagComplete ? Work_ItemsEntity.STATUS_COMPLETE : workItem.getStatus_id());

                if (workItem.getIsActive() == Constants.ISACTIVE.ACTIVE
                        && !workItem.isReady()
                        && workItem.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE
                        && (workItem.getAccept_note_code() == null || workItem.getAccept_note_code().length() == 0)) {
                    showError("Bạn chưa nhập BBNT cho " + workItem.getWork_item_name());
                    return;
                }
            } else {
                if (flagConstructions)
                    flagConstructions = (workItem.hasCompletedDate());

                if (workItem.getIsActive() == Constants.ISACTIVE.ACTIVE
                        && !workItem.isReady()
                        && workItem.getStatus_id() == Work_ItemsEntity.STATUS_COMPLETE
                        && (workItem.getAccept_note_code() == null || workItem.getAccept_note_code().length() == 0)) {
                    showError("Bạn chưa nhập BBNT cho " + workItem.getWork_item_name());
                    return;
                }
            }

            if (workItem.isReady()) {
                Supervision_ConstrEntity item = new Supervision_ConstrController(getContext()).getItemByConstructId(constr_ConstructionItem.getConstructId());
                if (item != null) {
                    Date date = GSCTUtils.convertString(item.getSysCreateDate(), "yyyy-MM-dd");
                    String strDate = "";
                    if (date != null) {
                        strDate = GSCTUtils.convertDate(date, GSCTUtils.GSCT_PATTERN);
                    }
                    workItem.setComplete_date(strDate);
                    workItem.setStarting_date(strDate);
                }
            }
            if (workItem.getId() > 0) {
                work_itemsControler.updateItem(workItem);
            } else {
                workItem.setId(workItemId);
                workItem.setUpdate_date(GSCTUtils.getDateNow());
                work_itemsControler.addItem(workItem);
            }
        }

        if (flagConstructions) {
            Log.e(TAG, "save hoan thanh: " + constr_ConstructionItem.getConstructId() + " " + constr_ConstructionItem.getConstrType());
            constr_ConstructionItem.setStatus(396);
        } else {
            constr_ConstructionItem.setStatus(0);
        }
        ArrayList<Constr_ConstructionEntity> arr = new ArrayList<>();
        arr.add(constr_ConstructionItem);
        new Constr_ConstructionController(getContext()).updateStatus(constr_ConstructionItem);

        Enumeration<Sub_Work_ItemEntity> enumCatSubWorkItems = hashSubWorkItems.elements();
        while (enumCatSubWorkItems.hasMoreElements()) {
            Sub_Work_ItemEntity subWorkItem = enumCatSubWorkItems.nextElement();

            if (subWorkItem.getFinishDate() != null && subWorkItem.getFinishDate().length() > 0 && !subWorkItem.getFinishDate().equals(GSCTUtils.getDateNow()))
                continue;

            subWorkItem.setSyncStatus(subWorkItem.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            subWorkItem.setEmployeeId(userId);
//            Log.e(TAG, "save subWorkItem: " + subWorkItem.getWork_item_id() + " " + subWorkItem.getId() + " " + subWorkItem.getFinishDate());
            if (subWorkItem.getId() > 0) {
                sub_work_itemController.updateItem(subWorkItem);
            } else {
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Sub_Work_ItemField.TABLE_NAME);
                subWorkItem.setId(id);
                sub_work_itemController.addItem(subWorkItem);
            }
        }

        Toast.makeText(getContext(), "Cập nhật dữ liệu thành công!", Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRadioChecked(int parentIndex, Cat_Work_Item_TypesEntity cat_work_item_type) {
        TiendoBTSItemView parentView = hashTienDoViews.get(parentIndex);
        if (parentView != null) {
            if (parentView.getCatWorkItemTypeId() > 0) {
                WorkItemTienDoBTSView view = hashWorkItemViews.get(parentView.getCatWorkItemTypeId());
                if (view != null) {
                    view.setChecked(false);
                }
            }
            long lastCatWorkItemTypeId = parentView.getCatWorkItemTypeId();
            if (hashWorkItems.containsKey(lastCatWorkItemTypeId)) {
                hashWorkItems.get(lastCatWorkItemTypeId).setChange(true);
            }
            parentView.setCatWorkItemTypeId(cat_work_item_type.getItem_type_id());
            if (hashWorkItems.containsKey(cat_work_item_type.getItem_type_id())) {
                hashWorkItems.get(cat_work_item_type.getItem_type_id()).setChange(true);
            }

            showSubWorkItem(cat_work_item_type);
        }
    }

    @Override
    public void onTextviewClicked(Cat_Work_Item_TypesEntity cat_work_item_type) {
        showSubWorkItem(cat_work_item_type);
    }
}
