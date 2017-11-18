package com.viettel.gsct.View.gpon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.viettel.ktts.R;


/**
 * Created by bachk on 11/3/2017.
 */

public abstract class BaseCustomWorkItem extends LinearLayout {
    private static final String TAG = BaseCustomWorkItem.class.getSimpleName();

    public BaseCustomWorkItem(Context context) {
        super(context);
    }

    public BaseCustomWorkItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCustomWorkItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean validate(){
        return true;
    }
    // Truong hop cac item chi luu theo cong trinh.
    public void save(){}
    // Truong hop cac cong trinh luu theo node.
    public void save(long nodeId){}

}
