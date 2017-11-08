package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class WorkItemGponOldView extends LinearLayout {

    View rootView;
    @BindView(R.id.tv_STT)
    TextView tvTitle;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;

    private ArrayList<View> arrSubViews= new ArrayList<>();

    public WorkItemGponOldView(Context context) {
        super(context);
        init(context);
    }

    public WorkItemGponOldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemGponOldView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_work_item_gpon, this);
        ButterKnife.bind(this);

        btnCollapse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrSubViews.isEmpty())
                    return;
                boolean visible = arrSubViews.get(0).getVisibility() == VISIBLE;
                for (View view : arrSubViews) {
                    view.setVisibility(!visible ? VISIBLE : GONE);
                }
            }
        });

        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrSubViews.isEmpty())
                    return;
                boolean visible = arrSubViews.get(0).getVisibility() == VISIBLE;
                for (View view : arrSubViews) {
                    view.setVisibility(!visible ? VISIBLE : GONE);
                }
            }
        });
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void addSubView(View view) {
        arrSubViews.add(view);
    }

}
