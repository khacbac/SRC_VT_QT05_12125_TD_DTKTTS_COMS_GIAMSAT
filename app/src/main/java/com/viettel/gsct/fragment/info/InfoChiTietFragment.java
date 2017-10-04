package com.viettel.gsct.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/10/17.
 */

public class InfoChiTietFragment extends BaseFragment {
    private static final String TAG = "InfoChiTietFragment";
    @BindView(R.id.tv_loai_tram)
    AppCompatTextView tvLoaiTram;
    @BindView(R.id.tv_dia_diem)
    AppCompatTextView tvDiaDiem;
    @BindView(R.id.tv_don_vi)
    AppCompatTextView tvDonVi;
    @BindView(R.id.tv_trang_thai)
    AppCompatTextView tvTrangThai;
    @BindView(R.id.tv_giam_sat)
    AppCompatTextView tvGiamSat;
    private Unbinder unbinder;

    @Override
    public void save() {

    }

    public static InfoChiTietFragment newInstance() {
        InfoChiTietFragment fragment = new InfoChiTietFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_chitiet, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();

        tvLoaiTram.setText(constr_ConstructionItem.getStationCode());
        tvDiaDiem.setText(constr_ConstructionItem.getAddress());

        Supervision_ConstrEntity item = new Supervision_ConstrController(getContext()).getItemByConstructId(constr_ConstructionItem.getConstructId());
        tvDonVi.setText(item.getConstructorName());
        tvGiamSat.setText(item.getSupervisorName());
        tvTrangThai.setText(item.getStatusName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
