package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.viettel.ktts.R;

import butterknife.ButterKnife;

/**
 * Created by bachk on 10/30/2017.
 */

public class CustomPreviewIndoor extends LinearLayout {
    public CustomPreviewIndoor(Context context) {
        super(context);
        initData(context);
    }

    public CustomPreviewIndoor(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public CustomPreviewIndoor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.custom_layout_preview_indoor,this);
        ButterKnife.bind(this);
    }
}
