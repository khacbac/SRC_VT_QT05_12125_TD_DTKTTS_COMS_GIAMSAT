package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.view.View;

import com.viettel.database.Cat_Work_Item_TypesControler;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoPresenter {

    void addWorkItem();

    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param gponView WorkItemGPONView.
     * @param context Context.
     */
    void addSubWorkItem(WorkItemGPONView gponView);

    void addSWKeoCap();


    void addSWValueInDoor();

    void addSWValueOLT();

    void addSWValueKeoCap(ConstrNodeEntity node);


    void addSWValueByNode(View view, ConstrNodeEntity node);


}
