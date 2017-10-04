package com.viettel.gsct.preview.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.common.KeyEventCommon;
import com.viettel.database.entity.ContentDetailItemProgressPreview;
import com.viettel.database.entity.ContentJournalPreview;
import com.viettel.database.entity.ContentProgressPreview;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.bts.SubWorkItemTienDoBTSView;
import com.viettel.gsct.View.line.SubWorkItemTienDoNgamView;
import com.viettel.gsct.View.bts.TiendoBTSItemView;
import com.viettel.gsct.View.gpon.WorkItemRightGPONView;
import com.viettel.gsct.View.bts.WorkItemTienDoBTSView;
import com.viettel.gsct.View.line.WorkItemTienDoNgamView;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.tiendo.gpon.view.GPONTiendoFragment;
import com.viettel.gsct.fragment.tiendo.other.TruyenDanNgamTiendoFragment;
import com.viettel.ktts.R;
import com.viettel.utils.NestedExpandableListView;
import com.viettel.utils.NestedListView;
import com.viettel.view.control.BtsXemNhatKyAdapter;
import com.viettel.view.control.BtsXemTienDoExpandableAdapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NhatKyTienDoPreviewFragment extends BaseFragment {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.txtDpThoiTiet)
    TextView txtDpThoiTiet;
    @BindView(R.id.txtDpThayDoiBoSung)
    TextView txtDpThayDoiBoSung;
    @BindView(R.id.txtDpYKienGiamSat)
    TextView txtDpYKienGiamSat;
    @BindView(R.id.txtDpYKienDonViThiCong)
    TextView txtDpYKienDonViThiCong;
    @BindView(R.id.txtDpTramTuyen)
    TextView txtDpTramTuyen;
    private Unbinder unbinder;
    @BindView(R.id.listViewDpQuanSoDoiThiCong)
    NestedListView mListViewDpQuanSoDoiThiCong;
    @BindView(R.id.expandableListViewDpTienDoThiCongTrongNgay)
    NestedExpandableListView mListViewDpTienDoThiCong;
    @BindView(R.id.txtDpCongViecThiCongTrongNgay)
    TextView mTxtCongViecTrongNgay;
    @BindView(R.id.txtKhoiLuong)
    TextView txtKhoiLuong;

    public static BaseFragment newInstance() {
        return new NhatKyTienDoPreviewFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_capnhat_nhatky_tiendo, container, false);
        unbinder = ButterKnife.bind(this, view);
        listenerOnClick();
        initData();
        return view;
    }

    private void listenerOnClick() {
        mListViewDpTienDoThiCong.setOnGroupExpandListener(
                new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                    }
                });
    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung tien do.
     *
     * @param listBtsTienDoView TiendoBTSItemView.
     * @param subWorkHashMap    SubWorkItemTienDoBTSView.
     * @param hashWorkItems     Work_ItemsEntity.
     */
    public void initDataForTienDoExpandable(
            ArrayList<TiendoBTSItemView> listBtsTienDoView,
            ConcurrentHashMap<Integer, ArrayList<SubWorkItemTienDoBTSView>> subWorkHashMap,
            ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems) {

        // Khong can hien thi muc khoi luong doi voi cong trinh nay.
        txtKhoiLuong.setVisibility(View.GONE);
        // Get List data for adapter.
        List<ContentProgressPreview> contentList = new ArrayList<>();
        List<ContentDetailItemProgressPreview> subWorkList;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>>
                contentHashMaps = new HashMap<>();

        int stt = 0;
        for (int i = 0; i < listBtsTienDoView.size(); i++) {
            for (View view : listBtsTienDoView.get(i).getSubView()) {
                WorkItemTienDoBTSView workItemTienDoBTSView = (WorkItemTienDoBTSView) view;
                if (workItemTienDoBTSView.isChecked()) {
                    Work_ItemsEntity work_itemsEntity = hashWorkItems
                            .get(workItemTienDoBTSView.getEntity().getItem_type_id());
                    // Get work item.
                    ContentProgressPreview content
                            = initDataForWokItemList(workItemTienDoBTSView, work_itemsEntity, ++stt);
                    contentList.add(content);
                }
            }
        }
        int index = 0;
        Enumeration<Integer> keySubWorkView = subWorkHashMap.keys();
        while (keySubWorkView.hasMoreElements()) {
            ArrayList<SubWorkItemTienDoBTSView> itemTienDoBTSView =
                    subWorkHashMap.get(keySubWorkView.nextElement());
            // Khoi tao du lieu cho list child item ung voi moi work item.
            subWorkList = initDataForSubWokList(itemTienDoBTSView, contentList.get(index));
            contentHashMaps.put(contentList.get(index), subWorkList);
            index++;
        }

        BtsXemTienDoExpandableAdapter mBtsXemTienDoExpandableAdapter =
                new BtsXemTienDoExpandableAdapter(contentList,
                        contentHashMaps, getActivity());
        mBtsXemTienDoExpandableAdapter.setNeedDisplayKhoiLuong(false);
        mListViewDpTienDoThiCong.setAdapter(mBtsXemTienDoExpandableAdapter);

    }

    /**
     * Khoi tao data cho work item.
     *
     * @param workItemTienDoBTSView WorkItemTienDo.
     * @param work_itemsEntity      WorkItemEntity.
     * @param stt                   int.
     * @return ContentProgressPreview.
     */
    private ContentProgressPreview initDataForWokItemList(
            WorkItemTienDoBTSView workItemTienDoBTSView,
            Work_ItemsEntity work_itemsEntity,
            int stt) {
        return new ContentProgressPreview(
                "" + stt,
                workItemTienDoBTSView.getTitle(),
                work_itemsEntity.getStarting_date(),
                work_itemsEntity.getComplete_date()
        );
    }

    /**
     * Khoi tao list data cho moi subwork item theo cac work item tuong ung.
     *
     * @param itemTienDoBTSView List.
     * @return List.
     */
    private List<ContentDetailItemProgressPreview> initDataForSubWokList(
            ArrayList<SubWorkItemTienDoBTSView> itemTienDoBTSView,
            ContentProgressPreview content) {
        // List child item.
        List<ContentDetailItemProgressPreview> subWorkList = new ArrayList<>();
        boolean isChange = false;
        for (SubWorkItemTienDoBTSView view : itemTienDoBTSView) {
            if (!view.hasFinishDate()) {
                if (view.getButtonTienDoStatus().equals("Hoàn thành")
                        && !view.getFinishDate().equals(GSCTUtils.getDateNow())) {
                    view.setFinishDate(GSCTUtils.getDateNow());
                } else {
                    view.setFinishDate("");
                }
            }
            ContentDetailItemProgressPreview detail = new ContentDetailItemProgressPreview(
                    view.getTitle(),
                    "",
                    view.getFinishDate(),
                    ""
            );
            if (view.hasChangeTrangThaiTienDo()) {
                isChange = true;
                detail.setNewEdit(true);
            } else {
                detail.setNewEdit(false);
            }
            subWorkList.add(detail);
        }
        if (isChange) {
            content.setNewEdit(true);
        }
        return subWorkList;
    }


    /**
     * Khoi tao du lieu cho phan xem truoc noi dung tuyen ngam tien do.
     *
     * @param layoutRoot LinearLayout.
     */
    public void initDataForTuyenNgamTienDoExpandable(LinearLayout layoutRoot) {
        // Get list luy ke ban dau.
        ArrayList<Double> luykes = TruyenDanNgamTiendoFragment.luykes;
        // Get List data for adapter.
        List<ContentProgressPreview> contentList = new ArrayList<>();
        List<ContentDetailItemProgressPreview> subWorkList;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>>
                contentHashMaps = new HashMap<>();
        int stt = 0;
        int index = 0;
        for (int i = 0; i < layoutRoot.getChildCount(); i++) {
            if (layoutRoot.getChildAt(i) instanceof WorkItemTienDoNgamView) {
                subWorkList = new ArrayList<>();
                WorkItemTienDoNgamView tienDoNgamView =
                        (WorkItemTienDoNgamView) layoutRoot.getChildAt(i);
                ContentProgressPreview content = new ContentProgressPreview(
                        "" + ++stt,
                        tienDoNgamView.getTitle(),
                        tienDoNgamView.getWorkItemEntity().getStarting_date(),
                        tienDoNgamView.getWorkItemEntity().getComplete_date()
                );
                if (!tienDoNgamView.getWorkItemEntity().isCompleted()
                        || tienDoNgamView.getWorkItemEntity()
                        .getComplete_date().equals(GSCTUtils.getDateNow())) {
                    if (!tienDoNgamView.getWorkItemEntity().hasStartedDate()
                            && !(tienDoNgamView.getTrangThaiTienDo().equals("Chưa làm"))) {
                        content.setNgayBatDau(GSCTUtils.getDateNow());
                    }
                    if (tienDoNgamView.getTrangThaiTienDo().equals("Hoàn thành")) {
                        content.setNgayHoanThanh(GSCTUtils.getDateNow());
                    } else {
                        content.setNgayHoanThanh("");
                    }
                }
                if (tienDoNgamView.hasChangeTrangThaiTienDo()) {
                    content.setNewEdit(true);
                } else {
                    content.setNewEdit(false);
                }
                contentList.add(content);
                ArrayList<SubWorkItemTienDoNgamView> subWorkItemList = tienDoNgamView.getArrSubItems();
                if (subWorkItemList.size() > 0) {
                    boolean hasEdit = false;
                    for (SubWorkItemTienDoNgamView view : subWorkItemList) {
                        double luyke = 0;
                        if (index < luykes.size()) {
                            luyke = luykes.get(index++);
                        }
                        Log.d(TAG,"Luy ke = " + luyke);
                        ContentDetailItemProgressPreview detail = new ContentDetailItemProgressPreview(
                                view.getTvTitle(),
                                "",
                                "",
                                "" + (luyke + view.getValue())
                        );
                        if (luyke != (luyke + view.getValue())) {
                            detail.setNewEdit(true);
                            hasEdit = true;
                        } else {
                            detail.setNewEdit(false);
                        }
                        subWorkList.add(detail);
                    }
                    if (tienDoNgamView.hasChangeTrangThaiTienDo()) {
                        hasEdit = true;
                    }
                    if (hasEdit) {
                        content.setNewEdit(true);
                    } else {
                        content.setNewEdit(false);
                    }
                }
                contentHashMaps.put(content, subWorkList);
            }
        }
        BtsXemTienDoExpandableAdapter adapter =
                new BtsXemTienDoExpandableAdapter(contentList,
                        contentHashMaps, getActivity());
        adapter.setNeedDisplayKhoiLuong(true);
        mListViewDpTienDoThiCong.setAdapter(adapter);
    }

    /**
     * Khoi tao data cho phan xem truoc cua Gpon.
     *
     * @param layoutRoot Layout.
     */
    public void initDataForBangRongTienDoExpandable(LinearLayout layoutRoot) {
        // Get list luy ke ban dau.
        ArrayList<Double> luykes = GPONTiendoFragment.luykes;
        // Get list left data.
        LinearLayout layoutLeft = GPONTiendoFragment.layoutRoot;
        ArrayList<SubWorkItemGPONView> gponViews = new ArrayList<>();
        for (int index = 0; index < layoutLeft.getChildCount(); index++) {
            if (layoutLeft.getChildAt(index) instanceof SubWorkItemGPONView) {
                SubWorkItemGPONView view = (SubWorkItemGPONView) layoutLeft.getChildAt(index);
                gponViews.add(view);
            }
        }
        // Get List data for adapter.
        List<ContentProgressPreview> contentList = new ArrayList<>();
        List<ContentDetailItemProgressPreview> subWorkList;
        HashMap<ContentProgressPreview,
                List<ContentDetailItemProgressPreview>> contentHashMaps = new HashMap<>();
        if (layoutRoot.getChildCount() > 0) {
            int stt = 0;
            for (int i = 0; i < layoutRoot.getChildCount(); i++) {
                if (layoutRoot.getChildAt(i) instanceof WorkItemRightGPONView) {
                    WorkItemRightGPONView view = (WorkItemRightGPONView) layoutRoot.getChildAt(i);
                    if (view.getStatusTienDo().equals("Hoàn thành")) {
                        if (!view.getWorkItem().hasCompletedDate()) {
                            view.getWorkItem().setComplete_date(GSCTUtils.getDateNow());
                        }
                    } else {
                        view.getWorkItem().setComplete_date("");
                    }
                    ContentProgressPreview content = new ContentProgressPreview(
                            "" + ++stt,
                            view.getTitle(),
                            view.getWorkItem().getStarting_date(),
                            view.getWorkItem().getComplete_date()
                    );
                    if (view.getBtnTienDo().isEnabled()) {
                        content.setNewEdit(true);
                    } else {
                        content.setNewEdit(false);
                    }
                    contentList.add(content);
                    subWorkList = new ArrayList<>();
                    if (gponViews.size() > 3) {
                        boolean hasEdit = false;
                        if (view.getTitle().equals("Hàn nối")) {
                            for (int j = 0; j < 3; j++) {
                                SubWorkItemGPONView subView = gponViews.get(j);
                                double luyke = 0;
                                if (j < luykes.size()) {
                                    luyke = luykes.get(j);
                                }
                                ContentDetailItemProgressPreview item
                                        = new ContentDetailItemProgressPreview(
                                        subView.getTvTitle(),
                                        "",
                                        "",
                                        "" + (luyke + subView.getValue())
                                );
                                if (luyke != (luyke + subView.getValue())) {
                                    hasEdit = true;
                                    item.setNewEdit(true);
                                } else {
                                    item.setNewEdit(false);
                                }
                                subWorkList.add(item);
                            }
                            if (hasEdit) {
                                content.setNewEdit(true);
                            } else {
                                content.setNewEdit(false);
                            }
                        } else if (view.getTitle().equals("Kéo cáp")) {
                            for (int j = 3; j < gponViews.size(); j++) {
                                SubWorkItemGPONView subView = gponViews.get(j);
                                double luyke = 0;
                                if (j < luykes.size()) {
                                    luyke = luykes.get(j);
                                }
                                ContentDetailItemProgressPreview item =
                                        new ContentDetailItemProgressPreview(
                                                subView.getTvTitle(),
                                                "",
                                                "",
                                                "" + (luyke + subView.getValue())
                                        );
                                if (luyke != (luyke + subView.getValue())) {
                                    hasEdit = true;
                                    item.setNewEdit(true);
                                } else {
                                    item.setNewEdit(false);
                                }
                                subWorkList.add(item);
                            }
                            if (hasEdit) {
                                content.setNewEdit(true);
                            } else {
                                content.setNewEdit(false);
                            }
                        }
                    }
                    contentHashMaps.put(content, subWorkList);
                }
            }
        }

        BtsXemTienDoExpandableAdapter adapter =
                new BtsXemTienDoExpandableAdapter(contentList,
                        contentHashMaps, getActivity());
        adapter.setNeedDisplayKhoiLuong(true);
        mListViewDpTienDoThiCong.setAdapter(adapter);
    }

    @Override
    public void save() {
    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung nhat ky.
     *
     * @param listHashMap String.
     */
    public void initDataForNhatKy(LinkedHashMap<String, String> listHashMap,
                                  String[] keyDois, String[] keyTenHangMucs) {
        txtDpTramTuyen.setText(listHashMap.get(KeyEventCommon.KEY_TEN_TRAM_TUYEN));
        mTxtCongViecTrongNgay.setText(listHashMap.get(KeyEventCommon.KEY_NOIDUNG_CONGVIEC));
        txtDpThoiTiet.setText(listHashMap.get(KeyEventCommon.KEY_THOITIET));
        txtDpThayDoiBoSung.setText(listHashMap.get(KeyEventCommon.KEY_THAYDOI));
        txtDpYKienGiamSat.setText(listHashMap.get(KeyEventCommon.KEY_GIAMSAT));
        txtDpYKienDonViThiCong.setText(listHashMap.get(KeyEventCommon.KEY_THICONG));

        List<ContentJournalPreview> mContentJournalPreviewList = new ArrayList<>();
        int stt = 0;
        for (int i = 0; i < keyDois.length; i++) {
            mContentJournalPreviewList.add(new ContentJournalPreview(
                    "" + ++stt,
                    keyTenHangMucs[i],
                    listHashMap.get(keyDois[i])
            ));
        }

        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter =
                new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void initData() {
        super.initData();
    }
}
