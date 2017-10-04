package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoFragment {
    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param view WorkItemGPONView.
     */
    void addWorkItemGponByNode(View view);

    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param view WorkItemGPONView.
     */
    void addWorkItemGponByCongTrinh(View view);


    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param view SubWorkItemGPONView.
     */
    void addSubWorkItemGponByNode(View view);

    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     */
    void addRightSubWorkItemGponByNode(View subView);

    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     */
    void addRightSubWorkItemGponByCongTrinh(View subView);
}
