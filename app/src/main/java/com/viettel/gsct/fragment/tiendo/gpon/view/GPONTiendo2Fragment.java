package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.IeGponTienDoPresenter;
import com.viettel.gsct.fragment.tiendo.gpon.presenter.GponTienDoTienDoPresenter;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class GPONTiendo2Fragment extends BaseTienDoFragment implements IeGponTienDoFragment, WorkItemGPONView.IeShowWorkItemByItemType/*, View.OnClickListener*/ {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.txtNgayCapNhat)
    TextView tvDate;
    @BindView(R.id.layout_root_left)
    LinearLayout layoutRootLeft;
    @BindView(R.id.layout_root_right)
    LinearLayout layoutRootRight;
    @BindView(R.id.lKeoCapValue)
    LinearLayout lKeoCapValue;
    @BindView(R.id.lHanNoiValue)
    LinearLayout lHanNoiValue;
    @BindView(R.id.lOutdoorValue)
    LinearLayout lOutdoorValue;
    @BindView(R.id.lIndoorValue)
    LinearLayout lIndoorValue;
    @BindView(R.id.lOltValue)
    LinearLayout lOltValue;
    @BindView(R.id.lDoKiemValue)
    LinearLayout lDoKiemValue;
    @BindView(R.id.rootAllLayout)
    LinearLayout rootAllLayout;

    private Unbinder unbinder;
    // Presenter cho cap nhat tien do Gpon.
    private IeGponTienDoPresenter itemGponPresenter;
    private boolean flagIsRealFinish = true;

    private boolean isFirstShow = true;
    // Ung voi node dau tien.
    private SubWorkItemGPONView swiKeoCap;

    public static BaseFragment newInstance() {
        return new GPONTiendo2Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_gpon_tien_do_2, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initViews();
        initData();
        return layout;
    }

    // Khoi tao all view.
    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
        // Khoi tao presenter.
        itemGponPresenter = new GponTienDoTienDoPresenter(this, getContext());
        hideAllLayout();
    }

    // Khoi tao work item.
    public void initData() {
        super.initData();
        // Them all Work Item Entity.
        itemGponPresenter.addWorkItem();

        if (swiKeoCap != null) {
            AppCompatRadioButton radioBtnCheck = swiKeoCap.getRadioBtnCheck();
            if (radioBtnCheck != null) {
                // Hien thi item dau tien
                radioBtnCheck.setChecked(true);
            }
        }
    }

    // Truong hop ko tra ve duoc list woik item cua cong trinh.
    @Override
    public void errorLoadWorkitem() {
        rootAllLayout.setVisibility(View.GONE);
    }

    // Them work item keo cap cho cong trinh.
    @Override
    public void finishAddWKeoCap(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWKeoCap();
    }

    // Them work item han noi cho cong trinh.
    @Override
    public void finishAddWHanNoi(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWHanNoi();
    }

    // Them work item indoor cho cong trinh.
    @Override
    public void finishAddWOdfInDoor(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWValueInDoor();
    }

    // Them work item outdoor cho cong trinh.
    @Override
    public void finishAddWOdfOutDoor(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        // Doi voi truong hop cap nhat theo node moi co sub work item.
        itemGponPresenter.addSWOutdoor();
    }

    // Them work item olt cho cong trinh.
    @Override
    public void finishAddWOlt(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWValueOLT();
        Log.d(TAG, "finishAddWOlt: called");
    }

    // Them work item do kiem cho cong trinh.
    @Override
    public void finishAddWDoKiem(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        // Doi voi truong hop cap nhat theo node moi co sub work item.
        itemGponPresenter.addSWDoKiem();
    }

    // Hien thi layout cho cac node doi voi work item keo cap.
    @Override
    public void finishAddSWKeoCap(SubWorkItemGPONView sView, ConstrNodeEntity node) {
        layoutRootLeft.addView(sView);
        if (isFirstShow) {
            this.swiKeoCap = sView;
            isFirstShow = false;
        }
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueKeoCap(node, sView);
    }

    // Hien thi layout cho cac node doi voi work item han noi.
    @Override
    public void finishAddSWHanNoi(SubWorkItemGPONView sView, ConstrNodeEntity node) {
        layoutRootLeft.addView(sView);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueHanNoi(node, sView);
    }

    // Hien thi layout cho cac node doi voi work item lap dat phan outdoor.
    @Override
    public void finishAddSWOutdoor(SubWorkItemGPONView sView, ConstrNodeEntity node) {
        layoutRootLeft.addView(sView);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueOutdoor(node, sView);
    }

    @Override
    public void finishAddSWDoKiem(SubWorkItemGPONView sView, ConstrNodeEntity node) {
        layoutRootLeft.addView(sView);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueDoKiem(node, sView);
    }

    // Hien thi layout phan value cho work item keo cap.
    @Override
    public void finishAddKeoCapValue(View view) {
        lKeoCapValue.addView(view);
    }

    // Hien thi layout phan value cho work item han noi.
    @Override
    public void finishAddHanNoiValue(View view) {
        lHanNoiValue.addView(view);
    }

    // Hien thi layout phan value cho work item indoor.
    @Override
    public void finishAddOdfInDoorValue(View indoor) {
        lIndoorValue.addView(indoor);
    }

    // Hien thi layout phan value cho work item outdoor.
    @Override
    public void finishAddOdfOutDoorValue(View sView) {
        lOutdoorValue.addView(sView);
    }

    // Hien thi layout phan value cho work item do kiem.
    @Override
    public void finishAddOdfDoKiemValue(View view) {
        lDoKiemValue.addView(view);
    }

    // Hien thi layour phan value cho work item olt.
    @Override
    public void finishAddLapDatOltValue(View view) {
        lOltValue.addView(view);
    }

    // Hiển thị layout theo WorkItem tương ứng.
    @Override
    public void showWorkItemByItemType(String strType) {
        hideAllLayout();
        switch (strType) {
            case Constant.TAG_KEOCAP:
                lKeoCapValue.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_HANNOI:
                lHanNoiValue.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_ODF_INDOOR:
                lIndoorValue.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_ODF_OUTDOOR:
                lOutdoorValue.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_LAPDAT_OLT:
                lOltValue.setVisibility(View.VISIBLE);
                break;
            case Constant.TAG_DOKIEM_NGHIEMTHU:
                lDoKiemValue.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    // Kiem tra validate cho cap nhat tien do.
    @Override
    public boolean checkValidateTienDo() {
        return itemGponPresenter.checkValidate();
    }

    // Ẩn toàn bộ phần layout bên phải, hiện thị các layout tùy theo WorkItem.
    private void hideAllLayout() {
        lKeoCapValue.setVisibility(View.GONE);
        lHanNoiValue.setVisibility(View.GONE);
        lIndoorValue.setVisibility(View.GONE);
        lOutdoorValue.setVisibility(View.GONE);
        lOltValue.setVisibility(View.GONE);
        lDoKiemValue.setVisibility(View.GONE);
        for (int i = 0; i < layoutRootLeft.getChildCount(); i++) {
            if (layoutRootLeft.getChildAt(i) instanceof WorkItemGPONView) {
                WorkItemGPONView gponView = (WorkItemGPONView) layoutRootLeft.getChildAt(i);
                gponView.getRootLayout().setBackgroundColor(getResources().getColor(R.color.white_color));
            }
        }
    }

    // Luu data xuong database.
    @Override
    public void save() {
        if (constr_ConstructionItem.getStatus() >= 395 && flagIsRealFinish) {
            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành," + " bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
            return;
        }
        super.save();
        itemGponPresenter.save();
        Toast.makeText(getContext(), "Cập nhật tiến độ thành công", Toast.LENGTH_SHORT).show();
    }

    // Hien thi man hinh xem thong tin vua nhap truoc khi luu.
    @Override
    public void showPreviewTienDo(BaseGponPreview mGponPrevFrag) {
        super.showPreviewTienDo(mGponPrevFrag);
        itemGponPresenter.showPreviewTienDo(mGponPrevFrag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
