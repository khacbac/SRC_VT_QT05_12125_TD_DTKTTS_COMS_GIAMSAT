package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/8/17.
 */

public class TiendoBTSItemView  extends LinearLayout{
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;

    private ArrayList<View> arrSubViews= new ArrayList<>();
    private long catWorkItemTypeId = 0;

    public TiendoBTSItemView(Context context) {
        super(context);
        init(context);
    }

    public TiendoBTSItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TiendoBTSItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_tien_do_item_bts, this);
        ButterKnife.bind(this);

        btnCollapse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrSubViews.isEmpty())
                    return;
                boolean visible = arrSubViews.get(0).getVisibility() == VISIBLE;
                for (View view : arrSubViews) {
                    view.setVisibility(!visible ? VISIBLE : GONE);
                }
                AnimationSet animSet = new AnimationSet(true);
                animSet.setInterpolator(new DecelerateInterpolator());
                animSet.setFillAfter(true);
                animSet.setFillEnabled(true);
                if (!visible) {
                    final RotateAnimation animRotate = new RotateAnimation(0.0f, -90.0f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);

                    animRotate.setDuration(500);
                    animRotate.setFillAfter(true);
                    animSet.addAnimation(animRotate);
                    btnCollapse.startAnimation(animSet);
                } else {
//                    final RotateAnimation animRotate = new RotateAnimation(0.0f, 90.0f,
//                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//
//                    animRotate.setDuration(500);
//                    animRotate.setFillAfter(true);
//                    animSet.addAnimation(animRotate);
                }

            }
        });




        tvTitle.setOnClickListener(new View.OnClickListener() {
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

    public long getCatWorkItemTypeId() {
        return catWorkItemTypeId;
    }

    public void setCatWorkItemTypeId(long catWorkItemTypeId) {
        this.catWorkItemTypeId = catWorkItemTypeId;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void addSubView(View view) {
        arrSubViews.add(view);
    }
}
