package com.viettel.gsct.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public static BaseFragment newInstance() {
        return new CapNhatNhatKyTienDoPreviewFragment();
    }

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
     * @param subWorkHashMap SubWorkItemTienDoBTSView.
     * @param hashWorkItems Work_ItemsEntity.
     */
    public void initDataForTienDoExpandable(
            ArrayList<TiendoBTSItemView> listBtsTienDoView,
            ConcurrentHashMap<Integer, ArrayList<SubWorkItemTienDoBTSView>> subWorkHashMap,
            ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems) {
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
                            = initDataForWokItemList(workItemTienDoBTSView, work_itemsEntity, stt);
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
            subWorkList = initDataForSubWokList(itemTienDoBTSView);
            contentHashMaps.put(contentList.get(index), subWorkList);
            index++;
        }

        BtsXemTienDoExpandableAdapter mBtsXemTienDoExpandableAdapter =
                new BtsXemTienDoExpandableAdapter(contentList,
                        contentHashMaps, getActivity());

        mListViewDpTienDoThiCong.setAdapter(mBtsXemTienDoExpandableAdapter);

    }

    /**
     * Khoi tao data cho work item.
     * @param workItemTienDoBTSView WorkItemTienDo.
     * @param work_itemsEntity WorkItemEntity.
     * @param stt int.
     * @return ContentProgressPreview.
     */
    private ContentProgressPreview initDataForWokItemList(
            WorkItemTienDoBTSView workItemTienDoBTSView,
            Work_ItemsEntity work_itemsEntity,
            int stt) {
        ContentProgressPreview content = new ContentProgressPreview(
                "" + ++stt,
                workItemTienDoBTSView.getTitle(),
                work_itemsEntity.getStarting_date(),
                work_itemsEntity.getComplete_date()
        );
        if (workItemTienDoBTSView.getEnabledRadioBtn()) {
            content.setNewEdit(true);
        } else {
            content.setNewEdit(false);
        }
        return content;
    }

    /**
     * Khoi tao list data cho moi subwork item theo cac work item tuong ung.
     * @param itemTienDoBTSView List.
     * @return List.
     */
    private List<ContentDetailItemProgressPreview> initDataForSubWokList(
            ArrayList<SubWorkItemTienDoBTSView> itemTienDoBTSView) {
        // List child item.
        List<ContentDetailItemProgressPreview> subWorkList = new ArrayList<>();
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
                    view.getFinishDate()
            );
            if (view.getButtonTienDoStatus().equals("Hoàn thành")
                    && view.getFinishDate().equals(GSCTUtils.getDateNow())) {
                detail.setNewEdit(true);
            } else {
                detail.setNewEdit(false);
            }
            subWorkList.add(detail);
        }
        return subWorkList;
    }


    /**
     * Khoi tao du lieu cho phan xem truoc noi dung tuyen ngam tien do.
     * @param layoutRoot LinearLayout.
     */
    public void initDataForTuyenNgamTienDoExpandable(LinearLayout layoutRoot) {
        // Get List data for adapter.
        List<ContentProgressPreview> contentList = new ArrayList<>();
        List<ContentDetailItemProgressPreview> subWorkList;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>>
                contentHashMaps = new HashMap<>();
        int stt = 0;
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
                if (tienDoNgamView.getWorkItemEntity().isCompleted()
                        && !tienDoNgamView.getWorkItemEntity().getComplete_date().equals(GSCTUtils.getDateNow())) {
                    content.setNewEdit(false);
                } else {
                    if (!tienDoNgamView.getWorkItemEntity().hasStartedDate()
                            && !(tienDoNgamView.getTrangThaiTienDo().equals("Chưa làm"))) {
                        content.setNgayBatDau(GSCTUtils.getDateNow());
                        content.setNewEdit(true);
                    }
                    if (tienDoNgamView.getTrangThaiTienDo().equals("Hoàn thành")) {
                        content.setNgayHoanThanh(GSCTUtils.getDateNow());
                        content.setNewEdit(true);
                    } else {
                        content.setNgayHoanThanh("");
                    }
                }
                contentList.add(content);
                ArrayList<SubWorkItemTienDoNgamView> subWorkItemList = tienDoNgamView.getArrSubItems();
                if (subWorkItemList.size() > 0) {
                    for (SubWorkItemTienDoNgamView view : subWorkItemList) {
                        ContentDetailItemProgressPreview detail = new ContentDetailItemProgressPreview(
                                view.getTvTitle(),
                                "",
                                view.getSubWorkItemEntity().getFinishDate()
                        );
                        if (content.isHasNgayHoanThanh()
                                && !(content.getNgayHoanThanh().equals(GSCTUtils.getDateNow()))) {
                            detail.setNewEdit(false);
                        } else {
                            if (tienDoNgamView.getTrangThaiTienDo().equals("Hoàn thành")) {
                                detail.setDetailNgayHoanThanh(GSCTUtils.getDateNow());
                                detail.setNewEdit(true);
                            }
                        }
                        subWorkList.add(detail);
                    }
                }
                contentHashMaps.put(content,subWorkList);
            }
        }
        BtsXemTienDoExpandableAdapter adapter =
                new BtsXemTienDoExpandableAdapter(contentList,
                        contentHashMaps, getActivity());
        mListViewDpTienDoThiCong.setAdapter(adapter);
    }

    public void initDataForBangRongTienDoExpandable(LinearLayout layoutRoot) {

    }

    @Override
    public void save() {
    }

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung nhat ky.
     * @param listHashMap String.
     */
    public void initDataForNhatKy(HashMap<String,String> listHashMap,
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

        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter = new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
