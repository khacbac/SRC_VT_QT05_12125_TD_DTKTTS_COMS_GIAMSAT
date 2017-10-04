package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoModel {

    /**
     * Interface lang nghe su kien ket thuc viec them layout.
     */
    interface IeListenerAddLayout {
        /**
         * Add WorkItemGPONView cho Gpon Tien do.
         * @param view WorkItemGPONView.
         */
        void addWorkItemByNode(View view);
        /**
         * Add WorkItemGPONView cho Gpon Tien do.
         * @param view WorkItemGPONView.
         */
        void addWorkItemByCongTrinh(View view);
        /**
         * Add SubWorkItemGPONView cho Gpon Tien do.
         * @param view SubWorkItemGPONView.
         */
        void addSubWorkItemByNode(View view);
        /**
         * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
         * @param subView SubWorkItemGPONView.
         */
        void addRightSubWorkItemByNode(View subView);
        /**
         * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
         * @param subView SubWorkItemGPONView.
         */
        void addRightSubWorkItemByCongTrinh(View subView);
    }

    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param entity Work_ItemsEntity.
     * @param context Context.
     * @param addLayout IeListenerAddLayout.
     */
    void addWorkItemGponByNode(Work_ItemsEntity entity,
                               Context context, IeListenerAddLayout addLayout);

    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param entity Work_ItemsEntity.
     * @param context Context.
     * @param addLayout IeListenerAddLayout.
     */
    void addWorkItemGponByCongTrinh(Work_ItemsEntity entity,
                               Context context, IeListenerAddLayout addLayout);

    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param gponView WorkItemGPONView.
     * @param context Context.
     * @param addLayout IeListenerAddLayout.
     */
    void addSubWorkItemGponByNode(WorkItemGPONView gponView,
                                  Context context, IeListenerAddLayout addLayout);

    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     * @param context Context.
     * @param addLayout IeListenerAddLayout.
     */
    void addRightSubWorkGponByNode(View subView,
                                   Context context, IeListenerAddLayout addLayout);

    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     * @param context Context.
     * @param addLayout IeListenerAddLayout.
     */
    void addRightSubWorkGponByCongTrinh(View subView,
                                   Context context, IeListenerAddLayout addLayout);
}
