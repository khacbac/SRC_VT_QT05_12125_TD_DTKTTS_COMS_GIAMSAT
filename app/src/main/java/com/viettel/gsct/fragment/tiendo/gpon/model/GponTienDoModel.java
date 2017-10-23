package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;

import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoModel implements IeGponTienDoModel {

    private static final String TAG = GponTienDoModel.class.getSimpleName();
    private Context context;
    private ConstrNodeController nController;
    private Work_ItemsControler wController;

    // Get data from database.
    private ArrayList<Cat_Work_Item_TypesEntity> arrCat;
    private ArrayList<ConstrNodeEntity> listNode;
    private ArrayList<Work_ItemsEntity> listWorkItem;
    private Cat_Work_Item_TypesEntity cwiCapQuang;
    private Cat_Work_Item_TypesEntity cwiAdss;
    private Cat_Work_Item_TypesEntity cwiHanNoi;
    private Cat_Work_Item_TypesEntity cwiOdf;
    private Cat_Work_Item_TypesEntity cwiOlt;
    private Cat_Work_Item_TypesEntity cwiDokiem;

    public GponTienDoModel(Context context) {
        this.context = context;

        nController = new ConstrNodeController(context);
        wController = new Work_ItemsControler(context);

        listNode = nController.getListNodeTest();
        arrCat = BaseFragment.cat_work_item_typesControler.getCates(BaseFragment.constr_ConstructionItem.getConstrType());
        if (!arrCat.isEmpty()) {
            for (Cat_Work_Item_TypesEntity entity : arrCat) {
                if (entity.getCode().contains(Constant.CODE_CAPQUANG)) {
                    cwiCapQuang = entity;
                } else if (entity.getCode().contains(Constant.CODE_ADSS)) {
                    cwiAdss = entity;
                } else if (entity.getCode().contains(Constant.CODE_LAPDAT_HANNOI)) {
                    cwiHanNoi = entity;
                } else if (entity.getCode().contains(Constant.CODE_LAPDAT_ODF)) {
                    cwiOdf = entity;
                } else if (entity.getCode().contains(Constant.CODE_LAPDAT_OLT)) {
                    cwiOlt = entity;
                } else if (entity.getCode().contains(Constant.CODE_DOKIEM)) {
                    cwiDokiem = entity;
                }
            }
        }
    }

    @Override
    public void addSWKeoCap(WorkItemGPONView wItemKeoCap, IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
//            listWorkItem = wController.getListWorkTest(69280340/*node.getContructorId()*/);
            addItem.finishAddSWKeoCap(node);
        }
    }

    @Override
    public void addSWHanNoi(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            addItem.finishAddSWHanNoi(node);
        }
    }

    @Override
    public void addSWOutdoor(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            addItem.finishAddSWOutdoor(node);
        }
    }

    @Override
    public void addSWDoKiem(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            addItem.finishAddSWDoKiem(node);
        }
    }

    @Override
    public void addSWValueKeoCap(SubWorkItemGPONView sView, IeListenerAddItem addLayout) {

        addLayout.finishAddSWValueKeoCap(sView, cwiCapQuang);
        addLayout.finishAddSWValueKeoCap(sView, cwiAdss);


    }

    @Override
    public void addSWValueIndoor(IeListenerAddItem addLayout) {
        addLayout.finishAddSWValueIndoor(cwiOdf);
    }

    @Override
    public void addSWValueOLT(IeListenerAddItem addLayout) {
        addLayout.finishAddSWValueOLT(cwiOlt);
    }

    @Override
    public void addSWValueHanNoi(SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        addLayout.finishAddSWValueHanNoi(sView,cwiHanNoi);
    }

    @Override
    public void addSWValueOutdoor(SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        addLayout.finishAddSWValueOutdoor(sView, cwiOdf);
    }

    @Override
    public void addSWValueDoKiem(SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        addLayout.finishAddSWValueDoKiem(sView,cwiDokiem);
    }

    @Override
    public void addItemOdfIndoor(Cat_Work_Item_TypesEntity catWork, IeListenerAddItem addLayout) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWork.getItem_type_id());
        if (arrSubWorkItems.isEmpty()) {
            return;
        }
        addLayout.finishAddOdfIndoor(arrSubWorkItems,catWork);
    }

    @Override
    public void addItemOdfOutdoor(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWork, IeListenerAddItem addLayout) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWork.getItem_type_id());
        if (arrSubWorkItems.isEmpty()) {
            return;
        }
        addLayout.finishAddOdfOutdoor(sView,arrSubWorkItems,catWork);
    }
}
