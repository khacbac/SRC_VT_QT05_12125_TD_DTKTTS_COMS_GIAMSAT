package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class WorkItemGPONView extends LinearLayout implements SubWorkItemGPONView.IeOnRadioCheckChangedListener {

    View rootView;
    @BindView(R.id.tv_STT)
    TextView tvTitle;
    @BindView(R.id.btn_collapse)
    Button btnCollapse;
    @BindView(R.id.rootLayout)
    RelativeLayout rootLayout;

    private ArrayList<AppCompatRadioButton> radioButtons = new ArrayList<>();
    private ArrayList<View> arrSubViews= new ArrayList<>();
    private IeShowWorkItemByItemType ieShowWorkItemByItemType;

    public WorkItemGPONView(Context context) {
        super(context);
        init(context);
    }

    public WorkItemGPONView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WorkItemGPONView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(final Context context) {
        setOrientation(HORIZONTAL);
        rootView = inflate(context, R.layout.layout_work_item_gpon, this);
        ButterKnife.bind(this);
        btnCollapse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrSubViews.isEmpty()) {
                    String strWorkItemName = tvTitle.getText().toString().trim();
                    if (Constant.TAG_LAPDAT_ODF_INDOOR.equals(strWorkItemName)
                            || Constant.TAG_LAPDAT_OLT.equals(strWorkItemName)) {
                        ieShowWorkItemByItemType.showWorkItemByItemType(strWorkItemName);
                        rootLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentLight));
                    }
                    return;
                }
                boolean visible = arrSubViews.get(0).getVisibility() == VISIBLE;
                for (View view : arrSubViews) {
                    view.setVisibility(!visible ? VISIBLE : GONE);
                }

                AnimationSet animSet = new AnimationSet(true);
                animSet.setInterpolator(new DecelerateInterpolator());
                animSet.setFillAfter(true);
                animSet.setFillEnabled(true);
                RotateAnimation animRotate;
                if (!visible) {
                    animRotate = new RotateAnimation(0.0f, -90.0f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                } else {
                    animRotate = new RotateAnimation(0.0f, 0.0f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                            RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                }
                animRotate.setDuration(500);
                animRotate.setFillAfter(true);
                animSet.addAnimation(animRotate);
                btnCollapse.startAnimation(animSet);
            }
        });

        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCollapse.performClick();
            }
        });
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public String getTvTitle() {
        return ""+tvTitle.getText().toString();
    }

    public void addSubView(View view) {
        SubWorkItemGPONView subWork = (SubWorkItemGPONView) view;
        arrSubViews.add(subWork);
        subWork.setVisibility(GONE);
        subWork.setOnRadioCheckChangeListener(this);
    }

    public void addRadioButton(AppCompatRadioButton radioButton) {
        radioButtons.add(radioButton);
    }

    @Override
    public void onRadioCheckChange(boolean isCheck, AppCompatRadioButton radioButton, SubWorkItemGPONView subWorkView) {
        if (isCheck) {
            if (radioButtons.size() > 0) {
                for (AppCompatRadioButton radioBtn : radioButtons) {
                    radioBtn.setChecked(false);
                }
            }
            radioButton.setChecked(true);
            ieShowWorkItemByItemType.showWorkItemByItemType(tvTitle.getText().toString().trim());
            rootLayout.setBackgroundColor(getResources().getColor(R.color.colorAccentLight));
        }
    }

    public interface IeShowWorkItemByItemType {
        void showWorkItemByItemType(String strType);
    }

    public void setShowWorkItemByItemType(IeShowWorkItemByItemType itemByItemType) {
        this.ieShowWorkItemByItemType = itemByItemType;
    }

    public RelativeLayout getRootLayout() {
        return rootLayout;
    }
}
