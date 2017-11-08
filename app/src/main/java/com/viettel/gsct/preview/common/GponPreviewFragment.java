package com.viettel.gsct.preview.common;

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
import com.viettel.database.entity.ContentJournalPreview;
import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;
import com.viettel.ktts.R;
import com.viettel.utils.NestedListView;
import com.viettel.view.control.BtsXemNhatKyAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    private static GponPreviewFragment fragment;

    public static GponPreviewFragment newInstance() {
        if (fragment == null) {
            fragment = new GponPreviewFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    /**
     * Khoi tao du lieu cho phan xem truoc noi dung nhat ky.
     *
     * @param listHashMap String.
     */
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
    public void initDataOldTienDoPreview() {
        super.initDataOldTienDoPreview();
    }
}
