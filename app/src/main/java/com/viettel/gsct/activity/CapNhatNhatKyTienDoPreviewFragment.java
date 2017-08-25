package com.viettel.gsct.activity;

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
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.SubWorkItemTienDoBTSView;
import com.viettel.gsct.View.SubWorkItemTienDoNgamView;
import com.viettel.gsct.View.TiendoBTSItemView;
import com.viettel.gsct.View.WorkItemTienDoBTSView;
import com.viettel.gsct.View.WorkItemTienDoNgamView;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.ktts.R;
import com.viettel.utils.NestedExpandableListView;
import com.viettel.utils.NestedListView;
import com.viettel.view.control.BtsXemNhatKyAdapter;
import com.viettel.view.control.BtsXemTienDoExpandableAdapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CapNhatNhatKyTienDoPreviewFragment extends BaseFragment {

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cap_nhat_nhat_ky_tien_do,container,false);
        unbinder = ButterKnife.bind(this, view);
        listenerOnClick();
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
     * @param listBtsTienDoView TiendoBTSItemView.
     * @param subWorkItemTienDoBTSViewHashMap SubWorkItemTienDoBTSView.
     * @param hashWorkItems Work_ItemsEntity.
     */
    private void initDataForTienDoExpandable(
            ArrayList<TiendoBTSItemView> listBtsTienDoView,
            ConcurrentHashMap<Integer, ArrayList<SubWorkItemTienDoBTSView>>
                    subWorkItemTienDoBTSViewHashMap,
            ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems) {
        // Get List data for adapter.
        List<ContentProgressPreview> mContentProgressPreviewList = new ArrayList<>();
        List<ContentDetailItemProgressPreview> listSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>>
                mContentProgressPreviewListHashMap = new HashMap<>();

        int stt = 0;
        for (int i = 0; i < listBtsTienDoView.size(); i++) {
            listSubWork = new ArrayList<>();
            for (View view : listBtsTienDoView.get(i).getSubView()) {
                WorkItemTienDoBTSView workItemTienDoBTSView = (WorkItemTienDoBTSView) view;
                if (workItemTienDoBTSView.isChecked()) {
                    if (workItemTienDoBTSView.getEnabledRadioBtn()) {
                        workItemTienDoBTSView.setTxtActive(true);
                    } else {
                        workItemTienDoBTSView.setTxtActive(false);
                    }
                    Work_ItemsEntity work_itemsEntity = hashWorkItems
                            .get(workItemTienDoBTSView.getEntity().getItem_type_id());
                    mContentProgressPreviewList.add(
                            new ContentProgressPreview(
                                    "" + ++stt,
                                    workItemTienDoBTSView.getTitle(),
                                    work_itemsEntity.getStarting_date(),
                                    work_itemsEntity.getComplete_date()
                            )
                    );
                }
            }
        }

        int index = 0;
        Enumeration<Integer> keySubWorkView = subWorkItemTienDoBTSViewHashMap.keys();
        while (keySubWorkView.hasMoreElements()) {
            listSubWork = new ArrayList<>();
            ArrayList<SubWorkItemTienDoBTSView> itemTienDoBTSView =
                    subWorkItemTienDoBTSViewHashMap.get(keySubWorkView.nextElement());
            boolean hasFinishdate;
            for (SubWorkItemTienDoBTSView view : itemTienDoBTSView) {
                hasFinishdate = true;
                if (view.getFinishDate().equals("")) {
                    if (view.getButtonTienDoStatus().equals("Chưa làm")) {
                        hasFinishdate = false;
                    }
                }
                if (hasFinishdate) {
                    view.setFinishDate(GSCTUtils.getDateNow());
                }
                listSubWork.add(
                        new ContentDetailItemProgressPreview(
                                view.getTitle(),
                                "",
                                view.getFinishDate()
                        )
                );
            }
            mContentProgressPreviewListHashMap
                    .put(mContentProgressPreviewList.get(index), listSubWork);
            index++;
        }

        BtsXemTienDoExpandableAdapter mBtsXemTienDoExpandableAdapter =
                new BtsXemTienDoExpandableAdapter(mContentProgressPreviewList,
                mContentProgressPreviewListHashMap, getActivity());

        mListViewDpTienDoThiCong.setAdapter(mBtsXemTienDoExpandableAdapter);

    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung tuyen ngam tien do.
     * @param layoutRoot LinearLayout.
     */
    public void initDataForTuyenNgamTienDoExpandable(LinearLayout layoutRoot) {
        // Get List data for adapter.
        List<ContentProgressPreview> listWorkItem = new ArrayList<>();
        List<ContentDetailItemProgressPreview> listSubWorkItem;
        HashMap<ContentProgressPreview,
                List<ContentDetailItemProgressPreview>> listHashMap = new HashMap<>();
        int stt = 0;
        Log.d("BacHK","Count = " + layoutRoot.getChildCount());
        if (layoutRoot.getChildCount() > 0) {
            for (int i = 0; i < layoutRoot.getChildCount(); i++) {
                listSubWorkItem = new ArrayList<>();
                if (layoutRoot.getChildAt(i) instanceof WorkItemTienDoNgamView) {
                    // Get work item tien do.
                    WorkItemTienDoNgamView tienDoNgamView
                            = (WorkItemTienDoNgamView) layoutRoot.getChildAt(i);
                    listWorkItem.add(
                            new ContentProgressPreview (
                                    "" + ++stt,
                                    tienDoNgamView.getTitle(),
                                    tienDoNgamView.getWorkItemEntity().getStarting_date(),
                                    tienDoNgamView.getWorkItemEntity().getComplete_date()
                            ));
                    Log.d("BacHK","Ten hang muc = "
                            + ((WorkItemTienDoNgamView) layoutRoot.getChildAt(i)).getTitle());
                    if (tienDoNgamView.getArrSubItems().size() > 0) {
                        // Get list SubWork Item Tien Do.
                        ArrayList<SubWorkItemTienDoNgamView> arrSubItems
                                = tienDoNgamView.getArrSubItems();
                        for (SubWorkItemTienDoNgamView item : arrSubItems) {
//                            Log.d("BacHK","Sub item = " + item.getTvTitle());
//                            // Get Sub Work Item Tien Do.
//                            Log.d("BacHK","Sub item finish date = "
//                                    + item.getSubWorkItemEntity().getFinishDate());
                            listSubWorkItem.add(
                                    new ContentDetailItemProgressPreview(
                                            item.getTvTitle(),
                                            "",
                                            item.getSubWorkItemEntity().getFinishDate()
                                    ));
                        }
                    }
                    listHashMap.put(listWorkItem.get(i),listSubWorkItem);
//                    Log.d("BacHK","Tien do = " + tienDoNgamView.getTrangThaiTienDo());
//                    Log.d("BacHK","Start date = " + tienDoNgamView
//                            .getWorkItemEntity().getStarting_date());
//                    Log.d("BacHK","Complete date = " + tienDoNgamView
//                            .getWorkItemEntity().getComplete_date());
                    if ((tienDoNgamView.getTrangThaiTienDo().equals("Hoàn thành"))) {
                        // Kiem tra item da hoan thanh tu truoc hay hoan thanh vao hom nay.
                        if (tienDoNgamView.getWorkItemEntity().hasCompletedDate() &&
                                !GSCTUtils.getDateNow().equals(tienDoNgamView
                                        .getWorkItemEntity().getComplete_date())) {
                            // Item duoc hoan thanh tu truoc,khong the chinh sua.
                            Log.d("BacHK", "Da hoan thanh tu truoc");
                        } else {
                            // Item duoc hoan thanh vao ngay hom nay.
                            Log.d("BacHK", "Da hoan thanh vao hom nay");
                        }
                    }
                    Log.d("BacHK","--------------------------------------");
                }
            }
            BtsXemTienDoExpandableAdapter mBtsXemTienDoExpandableAdapter =
                    new BtsXemTienDoExpandableAdapter(listWorkItem, listHashMap, getActivity());
            mListViewDpTienDoThiCong.setAdapter(mBtsXemTienDoExpandableAdapter);
        }
    }

    @Override
    public void save() {
    }

    /**
     * Update data from Nhat Ky.
     * @param listHashMap String.
     * @param key String.
     */
    public void updateDataForCapNhatNhatKy(HashMap<String,String> listHashMap,String key) {
        txtDpTramTuyen.setText(listHashMap.get(KeyEventCommon.KEY_TEN_TRAM_TUYEN));
        switch (key) {
            case KeyEventCommon.KEY_BTS:
                initDataFromBtsForJournal(listHashMap);
                break;
            case KeyEventCommon.KEY_TUYEN_NGAM:
                initDataFromTuyenNgamForJournal(listHashMap);
                break;
        }
    }

    /**
     * Update data from Bts tien do.
     * @param listBtsTienDoView TiendoBTSItemView.
     * @param subWorkItemTienDoBTSViewHashMap SubWorkItemTienDoBTSView.
     * @param hashWorkItems Work_ItemsEntity.
     */
    public void updateDataForCapNhatTienDo(
            ArrayList<TiendoBTSItemView> listBtsTienDoView,
            ConcurrentHashMap<Integer,
                    ArrayList<SubWorkItemTienDoBTSView>> subWorkItemTienDoBTSViewHashMap,
            ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems) {
        initDataForTienDoExpandable(listBtsTienDoView,
                subWorkItemTienDoBTSViewHashMap, hashWorkItems);
    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung nhat ky tu Bts.
     * @param listHashMap String.
     */
    private void initDataFromBtsForJournal(HashMap<String,String> listHashMap) {
        mTxtCongViecTrongNgay.setText(listHashMap.get(KeyEventCommon.KEY_NOIDUNG_CONGVIEC));
        txtDpThoiTiet.setText(listHashMap.get(KeyEventCommon.KEY_THOITIET));
        txtDpThayDoiBoSung.setText(listHashMap.get(KeyEventCommon.KEY_THAYDOI));
        txtDpYKienGiamSat.setText(listHashMap.get(KeyEventCommon.KEY_GIAMSAT));
        txtDpYKienDonViThiCong.setText(listHashMap.get(KeyEventCommon.KEY_THICONG));

        List<ContentJournalPreview> mContentJournalPreviewList = new ArrayList<>();
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "1",
                getString(R.string.txt_doi_xay_dung),
                listHashMap.get(KeyEventCommon.KEY_DOI_XAYDUNG)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "2",
                getString(R.string.txt_doi_lap_dung),
                listHashMap.get(KeyEventCommon.KEY_DOI_LAPDAT)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "3",
                getString(R.string.txt_doi_tiep_dia),
                listHashMap.get(KeyEventCommon.KEY_DOI_TIEPDIA)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "4",
                getString(R.string.txt_doi_trienkhai_cap),
                listHashMap.get(KeyEventCommon.KEY_DOI_TKCAP)));
        mContentJournalPreviewList.add(new ContentJournalPreview("5",
                getString(R.string.txt_doi_trienkhai_dien),
                listHashMap.get(KeyEventCommon.KEY_DOI_TKDIEN)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "6",
                getString(R.string.txt_doi_thietbi),
                listHashMap.get(KeyEventCommon.KEY_DOI_THIETBI)));

        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter = new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung nhat ky tu Tuyen Ngam.
     * @param listHashMap String.
     */
    private void initDataFromTuyenNgamForJournal(HashMap<String, String> listHashMap) {
        mTxtCongViecTrongNgay.setText(listHashMap.get(KeyEventCommon.KEY_NOIDUNG_CONGVIEC));
        txtDpThoiTiet.setText(listHashMap.get(KeyEventCommon.KEY_THOITIET));
        txtDpThayDoiBoSung.setText(listHashMap.get(KeyEventCommon.KEY_THAYDOI));
        txtDpYKienGiamSat.setText(listHashMap.get(KeyEventCommon.KEY_GIAMSAT));
        txtDpYKienDonViThiCong.setText(listHashMap.get(KeyEventCommon.KEY_THICONG));

        List<ContentJournalPreview> mContentJournalPreviewList = new ArrayList<>();
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "1",
                getString(R.string.txt_doi_dao_ranh),
                listHashMap.get(KeyEventCommon.KEY_DOI_DAORANH)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "2",
                getString(R.string.txt_doi_xay_be),
                listHashMap.get(KeyEventCommon.KEY_DOI_XAYBE)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "3",
                getString(R.string.txt_doi_keo_cap),
                listHashMap.get(KeyEventCommon.KEY_DOI_KEOCAP)));
        mContentJournalPreviewList.add(new ContentJournalPreview(
                "4",
                getString(R.string.txt_may),
                listHashMap.get(KeyEventCommon.KEY_DOI_MAY)));

        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter
                = new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
