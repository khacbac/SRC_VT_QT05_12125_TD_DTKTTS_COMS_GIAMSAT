package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
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
        // Lang nghe ket thuc them subwork item.
        void finishAddSWKeoCap(ConstrNodeEntity node);
        void finishAddSWHanNoi(ConstrNodeEntity node);
        void finishAddSWOutdoor(ConstrNodeEntity node);
        void finishAddSWDoKiem(ConstrNodeEntity node);

        // Lang nghe ket thuc viec them value cho subwork item tuong ung.
        void finishAddSWValueIndoor(Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueOLT(Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueKeoCap(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueHanNoi(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueOutdoor(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddSWValueDoKiem(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem);
        void finishAddOdfIndoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork);
        void finishAddOdfOutdoor(SubWorkItemGPONView sView, ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork);
    }
    // Them subwork item.
    void addSWKeoCap(WorkItemGPONView wItemKeoCap, IeListenerAddItem addItem);
    void addSWHanNoi(IeListenerAddItem addItem);
    void addSWOutdoor(IeListenerAddItem addItem);
    void addSWDoKiem(IeListenerAddItem addItem);

    // Them subwork value.
    void addSWValueIndoor(IeListenerAddItem addLayout);
    void addSWValueOLT(IeListenerAddItem addLayout);
    void addSWValueKeoCap(SubWorkItemGPONView sView, IeListenerAddItem addLayout);
    void addSWValueHanNoi(SubWorkItemGPONView sView, IeListenerAddItem addLayout);
    void addSWValueOutdoor(SubWorkItemGPONView sView, IeListenerAddItem addLayout);
    void addSWValueDoKiem(SubWorkItemGPONView sView, IeListenerAddItem addLayout);
    void addItemOdfIndoor(Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout);
    void addItemOdfOutdoor(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout);

}
