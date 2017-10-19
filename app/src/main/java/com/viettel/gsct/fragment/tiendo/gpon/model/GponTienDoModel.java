package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoModel implements IeGponTienDoModel {

    private static final String TAG = GponTienDoModel.class.getSimpleName();

    private Context context;

    private ConstrNodeController controller;

    // Get data from database.
    private ArrayList<Cat_Work_Item_TypesEntity> arrCat;
    private ArrayList<ConstrNodeEntity> listNode;

    public GponTienDoModel(Context context) {
        this.context = context;

        controller = new ConstrNodeController(context);
        listNode = controller.getListNodeTest();
        arrCat = BaseFragment.cat_work_item_typesControler
                .getCates(BaseFragment.constr_ConstructionItem.getConstrType());
        Log.d(TAG, "GponTienDoModel: cat size = " + arrCat.size());

    }

    @Override
    public void addSubWorkItem(IeListenerAddItem addLayout) {
        for (ConstrNodeEntity node : listNode) {
            addLayout.finishAddSubWorkItem(node);
        }
    }

    @Override
    public void addSWKeoCap(WorkItemGPONView wItemKeoCap, IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            addItem.finishAddSWKeoCap(node,wItemKeoCap);
        }
    }

    @Override
    public void addSWValueIndoor(IeListenerAddItem addLayout) {
        for (Cat_Work_Item_TypesEntity catWorkItem : arrCat) {
            addLayout.finishAddSWValueIndoor(catWorkItem);
        }
    }

    @Override
    public void addSWValueOLT(IeListenerAddItem addLayout) {
        for (Cat_Work_Item_TypesEntity catWorkItem : arrCat) {
            addLayout.finishAddSWValueOLT(catWorkItem);
        }
    }

    @Override
    public void addSWValueKeoCap(IeListenerAddItem addLayout) {
        for (Cat_Work_Item_TypesEntity catWorkItem : arrCat) {
            addLayout.finishAddSWValueKeoCap(catWorkItem);
        }
    }

    @Override
    public void addSWValueByNode(View view, ConstrNodeEntity node, IeListenerAddItem addLayout) {
        // Dummy 69280340 for test.
        ArrayList<Cat_Work_Item_TypesEntity> arrCat = BaseFragment.cat_work_item_typesControler
                .getCatWorkByNode(/*node.getContructorId()*/69280340);
        if (arrCat.isEmpty()) {
            return;
        }
        for (Cat_Work_Item_TypesEntity catWork : arrCat) {
            Log.d(TAG, "addSWValueByNode: cat work name = " + catWork.getItem_type_name());
            addLayout.finishAddSWValueByNode(catWork,view);
        }
     }

    @Override
    public void addItemOdfIndoor(Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                = BaseFragment.cat_sub_work_itemControler.getsubCates(entity.getItem_type_id());
        if (arrSubWorkItems.isEmpty()) {
            return;
        }
        addLayout.finishAddOdfIndoor(arrSubWorkItems);
    }

    @Override
    public void addItemOdfOutdoor(Cat_Work_Item_TypesEntity entity, IeListenerAddItem addLayout) {

    }
}
