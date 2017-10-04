package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoPresenter {
    /**
     * Add WorkItemGPONView cho Gpon Tien do theo node.
     * @param entity Work_ItemsEntity.
     * @param context Context.
     */
    void addWorkItemGponByNode(Work_ItemsEntity entity, Context context);

    /**
     * Add WorkItemGPONView cho Gpon Tien do theo cong trinh.
     * @param entity Work_ItemsEntity.
     * @param context Context.
     */
    void addWorkItemGponByCongTrinh(Work_ItemsEntity entity, Context context);

    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param gponView WorkItemGPONView.
     * @param context Context.
     */
    void addSubWorkItemGponByNode(WorkItemGPONView gponView, Context context);

    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     * @param context Context.
     */
    void addRightSubWorkGponByNode(View subView, Context context);
    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param subView SubWorkItemGPONView.
     * @param context Context.
     */
    void addRightSubWorkGponByCongTrinh(View subView, Context context);

}
