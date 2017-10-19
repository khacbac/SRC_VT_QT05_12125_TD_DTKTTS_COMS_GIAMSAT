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
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.Sub_Work_ItemField;
import com.viettel.database.field.Sub_Work_Item_ValueField;
import com.viettel.database.field.Work_ItemsField;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.IeGponTienDoPresenter;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.GponTienDoTienDoPresenter;
import com.viettel.ktts.R;
import com.viettel.view.listener.IeSave;
import com.viettel.view.listener.IeValidate;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Enumeration;
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
    private ArrayList<ConstrNodeEntity> listNode;

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
        itemGponPresenter = new GponTienDoTienDoPresenter(this,getContext());
    }

    /**
     * Khoi tao all Data.
     */
    public void initData() {
        super.initData();
        // Them all Work Item Entity.
        itemGponPresenter.addWorkItem();
    }

    @Override
    public void finishAddWKeoCap(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
//        itemGponPresenter.addSWKeoCap();
        itemGponPresenter.addSWKeoCap();
    }

    @Override
    public void finishAddWHanNoi(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSubWorkItem(view);
    }

    @Override
    public void finishAddWOdfInDoor(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWValueInDoor();
    }

    @Override
    public void finishAddWOdfOutDoor(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        // Doi voi truong hop cap nhat theo node moi co sub work item.
        itemGponPresenter.addSubWorkItem(view);
    }

    @Override
    public void finishAddWOlt(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWValueOLT();
        Log.d(TAG, "finishAddWOlt: called");
    }

    @Override
    public void finishAddWDoKiem(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        // Doi voi truong hop cap nhat theo node moi co sub work item.
        itemGponPresenter.addSubWorkItem(view);
    }

    /**
     * Thêm SubWorkItem cho Gpon,được get từ mỗi WorkItem tương ứng.
     * Chi doi voi truong hop cap nhat theo Node thi moi co subwork item.
     *
     * @param view SubWorkItemGPONView.
     * @param node
     */
    @Override
    public void finishAddSubWorkItem(View view, ConstrNodeEntity node) {
        layoutRootLeft.addView(view);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueByNode(view,node);
    }

    @Override
    public void finishAddSWKeoCap(View view, ConstrNodeEntity node) {
        layoutRootLeft.addView(view);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueKeoCap(node);
    }


    @Override
    public void finishAddKeoCapValue(View view) {
        layoutForRightKeoCap.addView(view);
//        layoutForRightKeoCap.setVisibility(View.VISIBLE);
    }

    @Override
    public void finishAddLapDatHanNoiValue(View view) {

    }

    @Override
    public void finishAddLapDatOdfInDoorValue(ArrayList<View> listRightView) {
        for (View sView : listRightView) {
            layoutRightForOdfIndoor.addView(sView);
        }
    }

    @Override
    public void finishAddLapDatOdfOutDoorValue(View view) {
    }

    @Override
    public void finishAddLapDatOltValue(ArrayList<View> listRightView) {
        for (View sView : listRightView) {
            layoutRightForOlt.addView(sView);
        }
    }

    @Override
    public void finishAddDoKiemNghiemThuValue(View view) {

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
