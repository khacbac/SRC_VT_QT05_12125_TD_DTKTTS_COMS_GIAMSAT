package com.viettel.gsct.View.info;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.gsct.utils.GSCTUtils;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/12/17.
 */

public class SubWorkItemInfoView extends LinearLayout {
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_start_date)
    TextView tvStartDate;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_khoi_luong)
    TextView tvKhoiLuong;
    @BindView(R.id.layoutKhoiLuong)
    LinearLayout layoutKhoiLuong;

    public SubWorkItemInfoView(Context context) {
        super(context);
        init(context);
    }

    public SubWorkItemInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SubWorkItemInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_sub_work_item_info, this);
        ButterKnife.bind(this);
    }

    public void setValue(String title, String khoiluong,String startDate, String endDate) {
        tvTitle.setText(title);
        tvKhoiLuong.setText(khoiluong);
        tvStartDate.setText(GSCTUtils.standardlizeTime(startDate));
        tvEndDate.setText(GSCTUtils.standardlizeTime(endDate));
    }

    public void setStartedDate(String startDate) {
        tvStartDate.setText(GSCTUtils.standardlizeTime(startDate));
    }

    public void setFinishDate(String finishDate) {
        tvEndDate.setText(GSCTUtils.standardlizeTime(finishDate));
    }

    public void setHienThiCotKhoiLuong(boolean isDisplay) {
        if (isDisplay) {
            layoutKhoiLuong.setVisibility(VISIBLE);
        } else {
            layoutKhoiLuong.setVisibility(GONE);
        }
    }

    public TextView getTvKhoiLuong() {
        return tvKhoiLuong;
    }
}
