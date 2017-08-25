package com.viettel.gsct.View;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hieppq3 on 5/2/17.
 */

public class TeamView extends LinearLayout {
    private static final String TAG = "WorkItemTienDoNgamView";
    View rootView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_team_number)
    AppCompatEditText etTeamNumber;

    public TeamView(Context context) {
        super(context);
        init(context);
    }

    public TeamView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TeamView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.layout_team, this);
        ButterKnife.bind(this);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setNumber(String number) {
        etTeamNumber.setText(number);
    }

    public int getTeamNumber() {
        String number = etTeamNumber.getEditableText().toString();
        if (number.trim().length() == 0)
            return 0;
        int ret = 0;

        try {
            ret = Integer.valueOf(number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return ret;

    }

}
