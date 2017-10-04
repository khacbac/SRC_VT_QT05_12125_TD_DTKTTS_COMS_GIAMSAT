package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by doanLV4 on 9/19/2017.
 */

public class WorkItemRightHanNoiTuThueGpon extends LinearLayout {

    View rootView;

    @BindView(R.id.tv_tentu)
    TextView tvTenTu;
    @BindView(R.id.tv_sl_lapdat)
    AppCompatEditText edtSlLapDat;
    @BindView(R.id.tv_luyke_ld)
    TextView tvLuyKeLapDat;
    @BindView(R.id.tv_sl_hannoi)
    AppCompatEditText edtSlHanNoi;
    @BindView(R.id.tv_luyke_hn)
    TextView tvLuyKeHanNoi;

    private ArrayList<View> views = new ArrayList<>();

    private Work_ItemsEntity workItem;

    private WorkItemRightGPONView.FinishListener listener;
    private WorkItemRightGPONView.OnStatusBtnTienDo statusTienDo;

    public WorkItemRightHanNoiTuThueGpon(Context context) {
        super(context);
        init(context);
    }

    public WorkItemRightHanNoiTuThueGpon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemRightHanNoiTuThueGpon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_right_hannoi_tuthue_gpon, this);
        ButterKnife.bind(this);
    }

    public void setTvTenTu(String tvTenTu) {
        this.tvTenTu.setText(tvTenTu);
    }

    public void setTvLuyKeLapDat(String tvLuyKeLapDat) {
        this.tvLuyKeLapDat.setText(tvLuyKeLapDat);
    }

    public void setTvLuyKeHanNoi(String tvLuyKeHanNoi) {
        this.tvLuyKeHanNoi.setText(tvLuyKeHanNoi);
    }
}
