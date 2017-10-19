package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoModel {


    /**
     * Interface lang nghe su kien ket thuc viec them layout.
     */
    interface IeListenerAddItem {

//        void finishAddWorkItem(Cat_Work_Item_TypesEntity arrCat, Context context);
        /**
         * Add SubWorkItemGPONView cho Gpon Tien do.
         * @param gponView
         * @param context
         * @param node SubWorkItemGPONView.
         */
        void finishAddSubWorkItem(ConstrNodeEntity node);
        void finishAddSWKeoCap(ConstrNodeEntity node, WorkItemGPONView wItemKeoCap);

        // Lang nghe ket thuc vie them item right cho moi sub work item.
        void finishAddSWValueIndoor(Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueOLT(Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueKeoCap(Cat_Work_Item_TypesEntity catWorkItem);

        void finishAddSWValueByNode(Cat_Work_Item_TypesEntity catWorkItem, View view);

        void finishAddOdfIndoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems);

        void finishAddOdfOutdoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems);
    }

    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param gponView WorkItemGPONView.
     * @param context Context.
     * @param addLayout IeListenerAddItem.
     */
    void addSubWorkItem(IeListenerAddItem addLayout);

    void addSWKeoCap(WorkItemGPONView wItemKeoCap, IeListenerAddItem addItem);



    /**
     * Add layout for Right item doi voi moi SubWorkItemGPONView tuong ung.
     * @param context Context.
     * @param addLayout IeListenerAddItem.
     */
    void addSWValueIndoor(IeListenerAddItem addLayout);
    void addSWValueOLT(IeListenerAddItem addLayout);
    void addSWValueKeoCap(IeListenerAddItem addLayout);


    void addSWValueByNode(View view,ConstrNodeEntity node, IeListenerAddItem addLayout);

    void addItemOdfIndoor(Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout);
    void addItemOdfOutdoor(Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout);

}
