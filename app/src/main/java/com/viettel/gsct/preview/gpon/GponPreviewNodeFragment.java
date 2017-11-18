package com.viettel.gsct.preview.gpon;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.TabLayout;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.FrameLayout;
        import android.widget.TextView;

        import com.viettel.common.KeyEventCommon;
        import com.viettel.database.entity.ContentDetailItemProgressGpon2Preview;
        import com.viettel.database.entity.ContentJournalPreview;
        import com.viettel.database.entity.ContentProgressPreview;
        import com.viettel.database.entity.Sub_Work_ItemEntity;
        import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
        import com.viettel.database.entity.Work_ItemsEntity;
        import com.viettel.database.field.ConstrNodeController;
        import com.viettel.gsct.View.gpon.WorkItemHanNoi;
        import com.viettel.gsct.View.gpon.WorkItemKeoCap;
        import com.viettel.gsct.View.gpon.WorkItemOdf;
        import com.viettel.gsct.View.gpon.WorkItemOltAndDoKiem;
        import com.viettel.gsct.View.gpon.WorkItemValueHanNoiBoChia;
        import com.viettel.gsct.View.gpon.WorkItemValueHanNoiTuThue;
        import com.viettel.gsct.View.gpon.WorkItemValueKeoCap;
        import com.viettel.gsct.View.gpon.WorkItemValueOdf;
        import com.viettel.gsct.View.gpon.WorkItemValueOltDoKiem;
        import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;
        import com.viettel.gsct.fragment.tiendo.gpon.view.GponTienDoPreviewFragment;
        import com.viettel.gsct.utils.GSCTUtils;
        import com.viettel.ktts.R;
        import com.viettel.utils.NestedExpandableListView;
        import com.viettel.utils.NestedListView;
        import com.viettel.view.control.BtsXemNhatKyAdapter;
        import com.viettel.view.control.TienDoGpon2PreviewAdapter;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.LinkedHashMap;
        import java.util.List;

        import butterknife.BindView;
        import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/26/2017.
 */

public class GponPreviewNodeFragment extends BaseGponPreview implements GponTienDoPreviewFragment.ViewCallBack {

    private static final String TAG = GponPreviewNodeFragment.class.getSimpleName();

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
    @BindView(R.id.txtDpCongViecThiCongTrongNgay)
    TextView mTxtCongViecTrongNgay;
    @BindView(R.id.listViewDpQuanSoDoiThiCong)
    NestedListView mListViewDpQuanSoDoiThiCong;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.frameTiendoByNode)
    FrameLayout frame_tiendo;

    private static GponPreviewNodeFragment fragment;
    // Phan xem preview theo node.
    private TienDoGpon2PreviewAdapter adapterForNode;
    // Phan xem preview theo cong trinh.
    private TienDoGpon2PreviewAdapter adapterForCT;

    public static GponPreviewNodeFragment newInstance() {
        if (fragment == null) {
            fragment = new GponPreviewNodeFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_nhatky_tiendo_gpon2_preview, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_save) {
            Log.d(TAG, "onOptionsItemSelected: gpon preview save");
            save();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void save() {
        Log.d(TAG, "save: called from gpon preview");
    }

    @Override
    public void initDataForNhatKy(LinkedHashMap<String, String> hashMaps, String[] keyDois, String[] keyTenHangMucs) {
        txtDpTramTuyen.setText(hashMaps.get(KeyEventCommon.KEY_TEN_TRAM_TUYEN));
        mTxtCongViecTrongNgay.setText(hashMaps.get(KeyEventCommon.KEY_NOIDUNG_CONGVIEC));
        txtDpThoiTiet.setText(hashMaps.get(KeyEventCommon.KEY_THOITIET));
        txtDpThayDoiBoSung.setText(hashMaps.get(KeyEventCommon.KEY_THAYDOI));
        txtDpYKienGiamSat.setText(hashMaps.get(KeyEventCommon.KEY_GIAMSAT));
        txtDpYKienDonViThiCong.setText(hashMaps.get(KeyEventCommon.KEY_THICONG));
        List<ContentJournalPreview> mContentJournalPreviewList = new ArrayList<>();
        int stt = 0;
        for (int i = 0; i < keyDois.length; i++) {
            mContentJournalPreviewList.add(new ContentJournalPreview("" + ++stt, keyTenHangMucs[i], hashMaps.get(keyDois[i])));
        }
        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter = new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

     // Doi voi cac work item cap nhat theo cong trinh.
     // Phan hien thi thong tin user da nhap truoc khi luu.
    @Override
    public void initDataNewTienDoPreviewCT(WorkItemOdf itemOdf, WorkItemOltAndDoKiem workItemOlt) {
        super.initDataNewTienDoPreviewCT(itemOdf, workItemOlt);
        final GponTienDoPreviewFragment fragment = new GponTienDoPreviewFragment();
        adapterForCT = new TienDoGpon2PreviewAdapter(getActivity());
        adapterForCT.setNeedDisplayKhoiLuong(true);
        fragment.setViewCallBack(this, adapterForCT);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameTiendoByCT, fragment).commit();
        int stt = 0;
        stt = initDataPreviewOdfIndoor(itemOdf, stt);
        initDataPreviewOlt(workItemOlt, stt);
    }

     // Doi voi cac work item cap nhat theo node.
     // Phan hien thi thong tin user da nhap truoc khi luu.
    @Override
    public void initDataNewTienDoPreviewNode(final HashMap<Long, WorkItemKeoCap> hmkeoCap, final HashMap<Long, WorkItemHanNoi> hmWHanNoi, final HashMap<Long, WorkItemOdf> hmWOdfOutdoor, final HashMap<Long, WorkItemOltAndDoKiem> hmWDoKiem) {
        super.initDataNewTienDoPreviewNode(hmkeoCap, hmWHanNoi, hmWOdfOutdoor, hmWDoKiem);
        tabLayout.removeAllTabs();
        for (long nodeId : hmkeoCap.keySet()) {
            tabLayout.addTab(tabLayout.newTab().setText(ConstrNodeController.getInstance(getActivity()).getNodeNameById(nodeId)).setTag(nodeId));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final GponTienDoPreviewFragment fragment = new GponTienDoPreviewFragment();
        adapterForNode = new TienDoGpon2PreviewAdapter(getActivity());
        adapterForNode.setNeedDisplayKhoiLuong(true);
        fragment.setViewCallBack(this, adapterForNode);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameTiendoByNode, fragment).commit();
                Log.d(TAG, "onTabSelected: tab " + tab.getPosition());
                adapterForNode.resetData();
                int stt = 0;
                stt = initDataPreviewKeoCap(hmkeoCap, tab.getTag(), stt);
                stt = initDataPreviewHanNoi(hmWHanNoi, tab.getTag(), stt);
                stt = initDataPreviewOdfOutdoor(hmWOdfOutdoor, tab.getTag(), stt);
                initDataPreviewDoKiem(hmWDoKiem, tab.getTag(), stt);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameTiendoByNode, fragment).commit();
                    adapterForNode.resetData();
                    int stt = 0;
                    stt = initDataPreviewKeoCap(hmkeoCap, tab.getTag(), stt);
                    stt = initDataPreviewHanNoi(hmWHanNoi, tab.getTag(), stt);
                    stt = initDataPreviewOdfOutdoor(hmWOdfOutdoor, tab.getTag(), stt);
                    initDataPreviewDoKiem(hmWDoKiem, tab.getTag(), stt);
                }
            }
        });

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }
    }

    @Override
    public void upDateTextView(NestedExpandableListView listView, TienDoGpon2PreviewAdapter adapter) {
        listView.setAdapter(adapter);
    }

    // Hien thi data preview keo cap truoc khi luu.
    public int initDataPreviewKeoCap(HashMap<Long, WorkItemKeoCap> hmkeoCap, Object nodeId, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();
//        adapterForNode.setHasSlHN(false);

        WorkItemKeoCap kc = hmkeoCap.get(Long.parseLong(nodeId.toString()));

        // Them item work item keo cap.
        Work_ItemsEntity wEntity = kc.getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name(), "", "");
            lHeader.add(content);
            hmItem.put(content, lSubWork);
        }

        // Them item work item cap quang hinh so 8.
        Work_ItemsEntity wItemSo8 = kc.getwIVKCHeaderCapSo8().getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wItemSo8 != null) {
            ContentProgressPreview contentCapSo8 = new ContentProgressPreview("" + ++stt, kc.getwIVKCHeaderCapSo8().getTenLoaiCap(), "", "");
            lHeader.add(contentCapSo8);
            for (int i = 0; i < kc.getwIVKCHeaderCapSo8().getListValue().size(); i++) {
                WorkItemValueKeoCap valueKC = kc.getwIVKCHeaderCapSo8().getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueKC.getTenItem(), ""+ valueKC.getDoubleKhoiLuong(), "" , "" + ((double)Math.round((valueKC.getDoubleKhoiLuong() + valueKC.getDoubleOldLuyKe()) * 100) / 100));
                if (valueKC.getDoubleKhoiLuong() > 0) {
                    item.setNewEdit(true);
                    contentCapSo8.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            hmItem.put(contentCapSo8, lSubWork);
        }

        // Them item cap quang Adss.
        Work_ItemsEntity wItemAdss = kc.getwIVKCHeaderCapAdss().getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wItemAdss != null) {
            ContentProgressPreview contentAdss = new ContentProgressPreview("" + ++stt, kc.getwIVKCHeaderCapAdss().getTenLoaiCap(), wItemAdss.getStarting_date(), wItemAdss.getComplete_date());
            lHeader.add(contentAdss);
            for (int i = 0; i < kc.getwIVKCHeaderCapAdss().getListValue().size(); i++) {
                WorkItemValueKeoCap valueKC = kc.getwIVKCHeaderCapAdss().getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueKC.getTenItem(), ""+valueKC.getDoubleKhoiLuong(), "", "" + ((double)Math.round((valueKC.getDoubleKhoiLuong() + valueKC.getDoubleOldLuyKe()) * 100) / 100));
                if (valueKC.getDoubleKhoiLuong() > 0) {
                    item.setNewEdit(true);
                    contentAdss.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            hmItem.put(contentAdss, lSubWork);
        }

        adapterForNode.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi data preview han noi truoc khi luu.
    private int initDataPreviewHanNoi(HashMap<Long, WorkItemHanNoi> hmWHanNoi, Object tag, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();
//        adapterForNode.setHasSlHN(true);
        WorkItemHanNoi hn = hmWHanNoi.get(Long.parseLong(tag.toString()));
        Work_ItemsEntity wEntity = hn.getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name(), wEntity.getStarting_date(), wEntity.getComplete_date());
            lHeader.add(content);
            ContentDetailItemProgressGpon2Preview itemHeader = new ContentDetailItemProgressGpon2Preview("", "Lắp đặt", "Hàn nối", "","Lắp đặt","Hàn nối");
            itemHeader.setHideIcon(true);
            itemHeader.setHasSLHanNoi(true);
            lSubWork.add(itemHeader);

            for (int i = 0; i < hn.getlBoChia().getListValue().size(); i++) {
                WorkItemValueHanNoiBoChia valueBC = hn.getlBoChia().getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueBC.getTextBochia(), ""+valueBC.getIntegerKhoiLuong(), "", "" + (valueBC.getIntegerKhoiLuong() + valueBC.getIntegerOldLuyKe()));
                item.setHasSLHanNoi(true);
                if (valueBC.getIntegerKhoiLuong() > 0) {
                    item.setNewEdit(true);
                    content.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            for (int i = 0; i < hn.getlTuThue().getListValue().size(); i++) {
                WorkItemValueHanNoiTuThue valueTT = hn.getlTuThue().getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueTT.getTenTu(), ""+valueTT.getIntegerKhoiLuongLapDat(), ""+valueTT.getIntegerKhoiLuongHanNoi(), "", "" + (valueTT.getIntegerKhoiLuongLapDat() + valueTT.getIntegerOldLuyKeLapDat()),"" + (valueTT.getIntegerKhoiLuongHanNoi() + valueTT.getIntegerOldLuyKeHanNoi()));
                item.setHasSLHanNoi(true);
                if (valueTT.getIntegerKhoiLuongHanNoi() > 0) {
                    item.setNewEdit(true);
                    content.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            hmItem.put(content, lSubWork);
            adapterForNode.setListData(lHeader, hmItem);
        }

        return stt;
    }

    // Hien thi data preview odf out door truoc khi luu.
    private int initDataPreviewOdfOutdoor(HashMap<Long, WorkItemOdf> hmWOdfOutdoor, Object tag, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();
//        adapterForNode.setHasSlHN(false);
        WorkItemOdf odf = hmWOdfOutdoor.get(Long.parseLong(tag.toString()));
        Work_ItemsEntity wEntity = odf.getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name() + " ODF Outdoor", wEntity.getStarting_date(), wEntity.getComplete_date());
            lHeader.add(content);
            for (int i = 0; i < odf.getListValue().size(); i++) {
                WorkItemValueOdf valueOdf = odf.getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueOdf.getTvTenOdf(),
                        ""+valueOdf.getDoubleKhoiLuong(), "", "" + (valueOdf.getDoubleKhoiLuong() + valueOdf.getDoubleOldLuyKe()));
                if (valueOdf.getDoubleKhoiLuong() > 0) {
                    item.setNewEdit(true);
                    content.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            hmItem.put(content, lSubWork);
            adapterForNode.setListData(lHeader, hmItem);
        }

        return stt;
    }

    // Hien thi data preview do kiem truoc khi luu.
    private int initDataPreviewDoKiem(HashMap<Long, WorkItemOltAndDoKiem> hmWDoKiem, Object tag, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();
//        adapterForNode.setHasSlHN(false);
        WorkItemOltAndDoKiem doKiem = hmWDoKiem.get(Long.parseLong(tag.toString()));
        Work_ItemsEntity wEntity = doKiem.getwItemEntity();
        lSubWork = new ArrayList<>();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name(), wEntity.getStarting_date(), wEntity.getComplete_date());
            lHeader.add(content);
            for (int i = 0; i < doKiem.getListItemValue().size(); i++) {
                WorkItemValueOltDoKiem valueDoKiem = doKiem.getListItemValue().get(i);
                ContentDetailItemProgressGpon2Preview itemDetail = new ContentDetailItemProgressGpon2Preview();
                itemDetail.setDetailTenHangMuc(valueDoKiem.getTenTitle());
                Sub_Work_Item_ValueEntity swivEntity = valueDoKiem.getSwiValue();
                if (swivEntity != null) {
                    if (valueDoKiem.isFinish()) {
                        if (!swivEntity.hadAddedDate() || GSCTUtils.getDateNow().equalsIgnoreCase(swivEntity.getAdded_date())) {
                            itemDetail.setDetailNgayHoanThanh(GSCTUtils.getDateNow());
                            itemDetail.setNewEdit(true);
                            content.setNewEdit(true);
                        } else {
                            itemDetail.setDetailNgayHoanThanh(swivEntity.getAdded_date());
                        }
                    }
                }
                lSubWork.add(itemDetail);
            }
            hmItem.put(content, lSubWork);
            adapterForNode.setListData(lHeader, hmItem);
        }
        return stt;
    }


    // Hien thi data preview odf indoor truoc khi luu.
    private int initDataPreviewOdfIndoor(WorkItemOdf itemOdf, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();

        lSubWork = new ArrayList<>();
        Work_ItemsEntity wEntity = itemOdf.getwItemEntity();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name() + " ODF Indoor", wEntity.getStarting_date(), wEntity.getComplete_date());
            lHeader.add(content);
            for (int i = 0; i < itemOdf.getListValue().size(); i++) {
                WorkItemValueOdf valueOdf = itemOdf.getListValue().get(i);
                ContentDetailItemProgressGpon2Preview item = new ContentDetailItemProgressGpon2Preview(valueOdf.getTvTenOdf(), ""+valueOdf.getDoubleKhoiLuong(), "", "" + (valueOdf.getDoubleKhoiLuong() + valueOdf.getDoubleOldLuyKe()));
                if (valueOdf.getDoubleKhoiLuong() > 0) {
                    item.setNewEdit(true);
                    content.setNewEdit(true);
                }
                lSubWork.add(item);
            }
            hmItem.put(content, lSubWork);
            adapterForCT.setListData(lHeader, hmItem);
        }
        return stt;
    }

    // Hien thi data preview odf indoor truoc khi luu.
    private void initDataPreviewOlt(WorkItemOltAndDoKiem workItemOlt, int stt) {
        // Get List data for adapterForNode.
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressGpon2Preview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();

        lSubWork = new ArrayList<>();
        Work_ItemsEntity wEntity = workItemOlt.getwItemEntity();
        if (wEntity != null) {
            ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wEntity.getWork_item_name(), wEntity.getStarting_date(), wEntity.getComplete_date());
            lHeader.add(content);
            for (int i = 0; i < workItemOlt.getListItemValue().size(); i++) {
                WorkItemValueOltDoKiem valueDoKiem = workItemOlt.getListItemValue().get(i);
                ContentDetailItemProgressGpon2Preview itemDetail = new ContentDetailItemProgressGpon2Preview();
                itemDetail.setDetailTenHangMuc(valueDoKiem.getTenTitle());
                Sub_Work_ItemEntity swiEntity = valueDoKiem.getSwiEntity();
                if (swiEntity != null) {
                    if (valueDoKiem.isFinish()) {
                        if (!swiEntity.hasFinishDate() || GSCTUtils.getDateNow().equalsIgnoreCase(swiEntity.getFinishDate())) {
                            itemDetail.setDetailNgayHoanThanh(GSCTUtils.getDateNow());
                            itemDetail.setNewEdit(true);
                            content.setNewEdit(true);
                        } else {
                            itemDetail.setDetailNgayHoanThanh(swiEntity.getFinishDate());
                        }
                    }
                }
                lSubWork.add(itemDetail);
            }
            hmItem.put(content, lSubWork);
            adapterForCT.setListData(lHeader, hmItem);
        }
    }

}
