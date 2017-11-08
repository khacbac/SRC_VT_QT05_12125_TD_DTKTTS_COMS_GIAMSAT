package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.util.Log;

import com.viettel.database.Cat_Work_Item_TypesControler;
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
import java.util.HashMap;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoModel implements IeGponTienDoModel {

    private static final String TAG = GponTienDoModel.class.getSimpleName();
    private Context context;
    private ConstrNodeController nController;
    private Work_ItemsControler wController;
    private Cat_Work_Item_TypesControler cwitController;

    // Get data from database.
    private ArrayList<Cat_Work_Item_TypesEntity> arrCat;
    private ArrayList<ConstrNodeEntity> listNode;
    private ArrayList<Work_ItemsEntity> listWorkItem;
    private Cat_Work_Item_TypesEntity cwiKeoCap;
    private Cat_Work_Item_TypesEntity cwiCapQuang;
    private Cat_Work_Item_TypesEntity cwiAdss;
    private Cat_Work_Item_TypesEntity cwiHanNoi;
    private Cat_Work_Item_TypesEntity cwiOdf;
    private Cat_Work_Item_TypesEntity cwiOlt;
    private Cat_Work_Item_TypesEntity cwiDokiem;

    private boolean hasWorkItem = false;

    public GponTienDoModel(Context context) {
        this.context = context;

        nController = new ConstrNodeController(context);
        wController = new Work_ItemsControler(context);
        cwitController = new Cat_Work_Item_TypesControler(context);

//        listNode = nController.getListNodeTest();
        listNode = nController.getListNodeByConstrId(BaseFragment.constr_ConstructionItem.getConstructId());

        Log.d(TAG, "GponTienDoModel: Construct id = " + BaseFragment.constr_ConstructionItem.getConstructId());
//        arrCat = BaseFragment.cat_work_item_typesControler.getCates(BaseFragment.constr_ConstructionItem.getConstrType());
        Log.d(TAG, "GponTienDoModel: constr type = " + BaseFragment.constr_ConstructionItem.getConstrType());
    }

    @Override
    public void addSWKeoCap(WorkItemGPONView wItemKeoCap, IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            listWorkItem = wController.getListWorkTest(node.getContructorId());
            if (listWorkItem.size() > 0) {
                addItem.finishAddSWKeoCap(node);
            }
        }
    }

    @Override
    public void addSWHanNoi(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            listWorkItem = wController.getListWorkTest(node.getContructorId());
            if (listWorkItem.size() > 0) {
                addItem.finishAddSWHanNoi(node);
            }
        }
    }

    @Override
    public void addSWOutdoor(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            listWorkItem = wController.getListWorkTest(node.getContructorId());
            if (listWorkItem.size() > 0) {
                addItem.finishAddSWOutdoor(node);
            }
        }
    }

    @Override
    public void addSWDoKiem(IeListenerAddItem addItem) {
        for (ConstrNodeEntity node : listNode) {
            listWorkItem = wController.getListWorkTest(node.getContructorId());
            if (listWorkItem.size() > 0) {
                addItem.finishAddSWDoKiem(node);
            }
        }
    }

    @Override
    public void addSWValueKeoCap(ConstrNodeEntity node, SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        cwiKeoCap = getCatWorkItemType(node.getContructorId(), Constant.CODE_KEOCAP);
        cwiCapQuang = getCatWorkItemType(node.getContructorId(), Constant.CODE_CAPQUANG);
        cwiAdss = getCatWorkItemType(node.getContructorId(), Constant.CODE_ADSS);

        HashMap<String,Cat_Work_Item_TypesEntity> hmCatWorkItemType = new HashMap<>();

        if (cwiKeoCap != null && cwiCapQuang != null && cwiAdss != null) {
            hmCatWorkItemType.put(Constant.CODE_KEOCAP,cwiKeoCap);
            hmCatWorkItemType.put(Constant.CODE_CAPQUANG,cwiCapQuang);
            hmCatWorkItemType.put(Constant.CODE_ADSS,cwiAdss);
            addLayout.finishAddSWValueKeoCap(node, sView, hmCatWorkItemType);
        }
//        if (cwiCapQuang != null) {
//            addLayout.finishAddSWValueKeoCap(node, sView, cwiCapQuang);
//        }
//        if (cwiAdss != null) {
//            addLayout.finishAddSWValueKeoCap(node, sView, cwiAdss);
//        }
    }

    @Override
    public void addSWValueIndoor(IeListenerAddItem addLayout) {
//        if (!arrCat.isEmpty()) {
//            for (Cat_Work_Item_TypesEntity entity : arrCat) {
//                if (entity.getCode().contains(Constant.CODE_LAPDAT_ODF)) {
//                    cwiOdf = entity;
//                    break;
//                }
//            }
//        }
        if (!listWorkItem.isEmpty()) {
            for (Work_ItemsEntity wi : listWorkItem) {
                Cat_Work_Item_TypesEntity entity = Cat_Work_Item_TypesControler.getInstance(context).getCatWorkItemType(wi.getItem_type_id());
                if (entity != null) {
                    if (entity.getCode().contains(Constant.CODE_LAPDAT_ODF)) {
                        cwiOdf = entity;
                        addLayout.finishAddSWValueIndoor(cwiOdf);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void addSWValueOLT(IeListenerAddItem addLayout) {
//        for (Cat_Work_Item_TypesEntity entity : arrCat) {
//            if (entity.getCode().contains(Constant.CODE_LAPDAT_OLT)) {
//                cwiOlt = entity;
//                break;
//            }
//        }
        if (!listWorkItem.isEmpty()) {
            for (Work_ItemsEntity wi : listWorkItem) {
                Cat_Work_Item_TypesEntity entity = Cat_Work_Item_TypesControler.getInstance(context).getCatWorkItemType(wi.getItem_type_id());
                if (entity != null) {
                    if (entity.getCode().contains(Constant.CODE_LAPDAT_OLT)) {
                        cwiOlt = entity;
                        addLayout.finishAddSWValueOLT(cwiOlt);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void addSWValueHanNoi(ConstrNodeEntity node, SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        cwiHanNoi = getCatWorkItemType(node.getContructorId(), Constant.CODE_LAPDAT_HANNOI);
        if (cwiHanNoi != null) {
            addLayout.finishAddSWValueHanNoi(node, sView, cwiHanNoi);
        }
    }

    @Override
    public void addSWValueOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        cwiOdf = getCatWorkItemType(node.getContructorId(), Constant.CODE_LAPDAT_ODF);
        if (cwiOdf != null) {
            addLayout.finishAddSWValueOutdoor(node, sView, cwiOdf);
        }
    }

    @Override
    public void addSWValueDoKiem(ConstrNodeEntity node, SubWorkItemGPONView sView, IeListenerAddItem addLayout) {
        cwiDokiem = getCatWorkItemType(node.getContructorId(), Constant.CODE_DOKIEM);
        if (cwiDokiem != null) {
            addLayout.finishAddSWValueDoKiem(node, sView, cwiDokiem);
        }
    }

    @Override
    public void addItemOdfIndoor(Cat_Work_Item_TypesEntity catWork, IeListenerAddItem addLayout) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWork.getItem_type_id());
        if (arrSubWorkItems.isEmpty()) {
            return;
        }
        addLayout.finishAddOdfIndoor(arrSubWorkItems, catWork);
    }

    @Override
    public void addItemOdfOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWork, IeListenerAddItem addLayout) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWork.getItem_type_id());
        if (arrSubWorkItems.isEmpty()) {
            return;
        }
        addLayout.finishAddOdfOutdoor(node, sView, arrSubWorkItems, catWork);
    }

    private Cat_Work_Item_TypesEntity getCatWorkItemType(int constrId, String code) {
        Cat_Work_Item_TypesEntity entity = null;
        listWorkItem = wController.getListWorkTest(constrId);
        for (Work_ItemsEntity wItem : listWorkItem) {
            entity = cwitController.getCateByWItemTest(wItem.getItem_type_id(), code);
            if (entity != null) {
                break;
            }
        }
        return entity;
    }
}
