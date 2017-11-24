package com.viettel.gsct.preview.gpon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viettel.common.KeyEventCommon;
import com.viettel.database.Cat_Sub_Work_ItemControler;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ContentDetailItemProgressPreview;
import com.viettel.database.entity.ContentJournalPreview;
import com.viettel.database.entity.ContentProgressPreview;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGponOldView;
import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;
import com.viettel.ktts.R;
import com.viettel.utils.NestedExpandableListView;
import com.viettel.utils.NestedListView;
import com.viettel.view.control.BtsXemNhatKyAdapter;
import com.viettel.view.control.TienDoPreviewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/26/2017.
 */

public class GponPreviewFragment extends BaseGponPreview {

    private static final String TAG = GponPreviewFragment.class.getSimpleName();

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
    @BindView(R.id.expandableListViewDpTienDoThiCongTrongNgay)
    NestedExpandableListView mListViewDpTienDoThiCong;

    private static GponPreviewFragment fragment;
    private TienDoPreviewAdapter adapter;

    public static GponPreviewFragment newInstance() {
        if (fragment == null) {
            fragment = new GponPreviewFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_nhatky_tiendo_gpon_preview, container, false);
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

    // Khoi tao du lieu cho phan xem truoc noi dung nhat ky.
    @Override
    public void initDataForNhatKy(LinkedHashMap<String, String> listHashMap, String[] keyDois, String[] keyTenHangMucs) {
        txtDpTramTuyen.setText(listHashMap.get(KeyEventCommon.KEY_TEN_TRAM_TUYEN));
        mTxtCongViecTrongNgay.setText(listHashMap.get(KeyEventCommon.KEY_NOIDUNG_CONGVIEC));
        txtDpThoiTiet.setText(listHashMap.get(KeyEventCommon.KEY_THOITIET));
        txtDpThayDoiBoSung.setText(listHashMap.get(KeyEventCommon.KEY_THAYDOI));
        txtDpYKienGiamSat.setText(listHashMap.get(KeyEventCommon.KEY_GIAMSAT));
        txtDpYKienDonViThiCong.setText(listHashMap.get(KeyEventCommon.KEY_THICONG));
        List<ContentJournalPreview> mContentJournalPreviewList = new ArrayList<>();
        int stt = 0;
        for (int i = 0; i < keyDois.length; i++) {
            mContentJournalPreviewList.add(new ContentJournalPreview("" + ++stt, keyTenHangMucs[i], listHashMap.get(keyDois[i])));
        }
        BtsXemNhatKyAdapter mBtsXemNhatKyAdapter = new BtsXemNhatKyAdapter(mContentJournalPreviewList, getActivity());
        mListViewDpQuanSoDoiThiCong.setAdapter(mBtsXemNhatKyAdapter);
    }

    // Khoi tao phan man hinh xem thong tin vua nhap truoc khi luu cho cong trinh Gpon chi co cap nhat theo cong trinh.
    @Override
    public void initDataOldTienDoPreview(HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        super.initDataOldTienDoPreview(hmSubWorkItem);
        adapter = new TienDoPreviewAdapter(getActivity());
        adapter.setNeedDisplayKhoiLuong(true);
        mListViewDpTienDoThiCong.setAdapter(adapter);
        ArrayList<Cat_Work_Item_TypesEntity> arrCat = cat_work_item_typesControler.getCates(constr_ConstructionItem.getConstrType());
        int stt = 0;
        if (!arrCat.isEmpty()) {
           for (Cat_Work_Item_TypesEntity cwiEntity : arrCat) {
               switch (cwiEntity.getCode()) {
                   case Constant.CODE_KEOCAP:
                       stt = initPreviewForKeoCap(stt,cwiEntity,hmSubWorkItem);
                       break;
                   case Constant.CODE_CAPQUANG:
                       stt = initPreviewForCapSo8(stt,cwiEntity,hmSubWorkItem);
                       break;
                   case Constant.CODE_ADSS:
                       stt = initPreviewForCapAdss(stt,cwiEntity,hmSubWorkItem);
                       break;
                   case Constant.CODE_LAPDAT_HANNOI:
                       stt = initPreviewForHanNoi(stt,cwiEntity,hmSubWorkItem);
                       break;
                   case Constant.CODE_LAPDAT_ODF:
                       stt = initPreviewForLapDat(stt,cwiEntity,hmSubWorkItem);
                       break;
                   case Constant.CODE_DOKIEM:
                       stt = initPreviewForDoKiem(stt,cwiEntity,hmSubWorkItem);
                       break;
                   default:
                       break;
               }
           }
        }
    }

    // Hien thi thong tin vua nhap cho phan keo cap.
    private int initPreviewForKeoCap(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi thong tin vua nhap cho phan cap quang hinh so 8.
    private int initPreviewForCapSo8(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        ArrayList<Cat_Sub_Work_ItemEntity> listCSWI = Cat_Sub_Work_ItemControler.getInstance(getActivity()).getsubCates(cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWI) {
            double luyke = Sub_Work_Item_ValueController.getInstance(getActivity()).getOldLuyke(wItem.getId(),cswiEntity.getId());
            SubWorkItemGponOldView swGpon = hmSubWorkItem.get(cswiEntity.getName());
            double value = swGpon == null ? 0 : swGpon.getValue();
            double khoiLuong = value + luyke;
            ContentDetailItemProgressPreview item = new ContentDetailItemProgressPreview(cswiEntity.getName(), "", "", String.format(Locale.UK,"%.2f",khoiLuong));
            if (khoiLuong % 1 == 0) {
                item.setDetailKhoiLuong(String.valueOf((long)khoiLuong));
            }
            if (value > 0) {
                item.setNewEdit(true);
                content.setNewEdit(true);
            }
            lSubWork.add(item);
        }
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi thong tin vua nhap cho phan cap quang Adss.
    private int initPreviewForCapAdss(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        ArrayList<Cat_Sub_Work_ItemEntity> listCSWI = Cat_Sub_Work_ItemControler.getInstance(getActivity()).getsubCates(cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        Log.d(TAG, "initPreviewForCapAdss: code = " + cwiEntity.getCode());
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWI) {
            double luyke = Sub_Work_Item_ValueController.getInstance(getActivity()).getOldLuyke(wItem.getId(),cswiEntity.getId());
            SubWorkItemGponOldView swGpon = hmSubWorkItem.get(cswiEntity.getName());
            double value = swGpon == null ? 0 : swGpon.getValue();
            double khoiLuong = value + luyke;
            ContentDetailItemProgressPreview item = new ContentDetailItemProgressPreview(cswiEntity.getName(), "", "", String.format(Locale.UK,"%.2f",khoiLuong));
            if (khoiLuong % 1 == 0) {
                item.setDetailKhoiLuong(String.valueOf((long)khoiLuong));
            }
            if (value > 0) {
                item.setNewEdit(true);
                content.setNewEdit(true);
            }
            lSubWork.add(item);
        }
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi thong tin vua nhap cho phan han noi.
    private int initPreviewForHanNoi(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        ArrayList<Cat_Sub_Work_ItemEntity> listCSWI = Cat_Sub_Work_ItemControler.getInstance(getActivity()).getsubCates(cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        Log.d(TAG, "initPreviewForHanNoi: code = " + cwiEntity.getCode());
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWI) {
            double luyke = Sub_Work_Item_ValueController.getInstance(getActivity()).getOldLuyke(wItem.getId(),cswiEntity.getId());
            SubWorkItemGponOldView swGpon = hmSubWorkItem.get(cswiEntity.getName());
            double value = swGpon == null ? 0 : swGpon.getValue();
            double khoiLuong = value + luyke;
            ContentDetailItemProgressPreview item = new ContentDetailItemProgressPreview(cswiEntity.getName(), "", "", String.format(Locale.UK,"%.2f",khoiLuong));
            if (khoiLuong % 1 == 0) {
                item.setDetailKhoiLuong(String.valueOf((long)khoiLuong));
            }
            if (value > 0) {
                item.setNewEdit(true);
                content.setNewEdit(true);
            }
            lSubWork.add(item);
        }
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi thong tin vua nhap cho phan lap dat.
    private int initPreviewForLapDat(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        Log.d(TAG, "initPreviewForLapDat: code = " + cwiEntity.getCode());
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }

    // Hien thi thong tin vua nhap cho phan do kiem nghiem thu..
    private int initPreviewForDoKiem(int stt, Cat_Work_Item_TypesEntity cwiEntity, HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {
        Work_ItemsEntity wItem = Work_ItemsControler.getInstance(getActivity()).getItem(constr_ConstructionItem.getConstructId(),cwiEntity.getItem_type_id());
        if (wItem == null) {
            return stt;
        }
        Log.d(TAG, "initPreviewForDoKiem: code = " + cwiEntity.getCode());
        List<ContentProgressPreview> lHeader = new ArrayList<>();
        List<ContentDetailItemProgressPreview> lSubWork;
        HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> hmItem = new HashMap<>();
        lSubWork = new ArrayList<>();
        ContentProgressPreview content = new ContentProgressPreview("" + ++stt, wItem.getWork_item_name(), wItem.getStarting_date(), wItem.getComplete_date());
        lHeader.add(content);
        hmItem.put(content, lSubWork);
        adapter.setListData(lHeader, hmItem);
        return stt;
    }
}
