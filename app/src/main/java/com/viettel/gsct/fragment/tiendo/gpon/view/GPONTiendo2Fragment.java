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

    /**
     * Khoi tao all View.
     */
    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
        Log.d(TAG, "initViews() called");
        // Khoi tao presenter.
        itemGponPresenter = new GponTienDoTienDoPresenter(this, getContext());
        hideAllLayout();
    }

    /**
     * Khoi tao all Data.
     */
    public void initData() {
        super.initData();
        // Them all Work Item Entity.
        itemGponPresenter.addWorkItem();

        // Hien thi item dau tien
        swiKeoCap.getRadioBtnCheck().setChecked(true);
    }


    @Override
    public void finishAddWKeoCap(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWKeoCap();
    }

    @Override
    public void finishAddWHanNoi(WorkItemGPONView view) {
        layoutRootLeft.addView(view);
        view.setShowWorkItemByItemType(this);
        itemGponPresenter.addSWHanNoi();
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
        itemGponPresenter.addSWOutdoor();
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
        itemGponPresenter.addSWDoKiem();
    }

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

    @Override
    public void finishAddSWHanNoi(SubWorkItemGPONView sView, ConstrNodeEntity node) {
        layoutRootLeft.addView(sView);
        // Them item cho right Sub Work Item.
        itemGponPresenter.addSWValueHanNoi(node, sView);
    }

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

    @Override
    public void finishAddKeoCapValue(View view) {
        lKeoCapValue.addView(view);
    }

    @Override
    public void finishAddHanNoiValue(View view) {
        lHanNoiValue.addView(view);
    }

    @Override
    public void finishAddOdfInDoorValue(View indoor) {
        Log.d(TAG, "finishAddOdfInDoorValue: called");
        lIndoorValue.addView(indoor);
    }

    @Override
    public void finishAddOdfOutDoorValue(View sView) {
        lOutdoorValue.addView(sView);
    }

    @Override
    public void finishAddOdfDoKiemValue(View view) {
        lDoKiemValue.addView(view);
    }

    @Override
    public void finishAddLapDatOltValue(View view) {
        lOltValue.addView(view);
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

    /**
     * Kiem tra validate cho cap nhat tien do.
     *
     * @return boolean.
     */
    public boolean checkValidateGponTienDo() {
        return itemGponPresenter.checkValidate();
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
                        Toast.makeText(getActivity(), "Bạn chỉ có thể nhâp khối lượng trong trạng thái chưa làm!", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                }
                return false;
            }
        });
        Log.d(TAG, "setStatusEdtValueFollowStatus() called with: editText = [" + editText + "], isHoanThanh = [" + isHoanThanh + "]");
    }

    /**
     * Ẩn toàn bộ phần layout bên phải, hiện thị các layout tùy theo WorkItem.
     */
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

    /**
     * Luu data xuong database.
     */
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

    @Override
    public void showPreviewTienDo(BaseGponPreview mGponPrevFrag) {
        super.showPreviewTienDo(mGponPrevFrag);
        itemGponPresenter.showPreviewTienDo(mGponPrevFrag);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        Log.d(TAG, "onDestroyView() called");
    }

}
