package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemRightKeoCapHeaderGpon;
import com.viettel.gsct.View.gpon.WorkItemRightKeoCapItemGpon;
import com.viettel.gsct.View.gpon.WorkItemRightLapDatOdfGpon;
import com.viettel.gsct.View.gpon.WorkItemRightOltDoKiemGpon;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.GponTienDoModel;
import com.viettel.gsct.fragment.tiendo.gpon.view.IeGponTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.IeGponTienDoModel;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoTienDoPresenter implements IeGponTienDoPresenter,
        IeGponTienDoModel.IeListenerAddItem {

    private static final String TAG = GponTienDoTienDoPresenter.class.getSimpleName();
    private Context context;
    private IeGponTienDoFragment ieGponTienDoFragment;
    private IeGponTienDoModel ieGponTienDoModel;

    private WorkItemGPONView wItemKeoCap;
    private WorkItemGPONView wItemHanNoi;
    private WorkItemGPONView wItemOdfIndoor;
    private WorkItemGPONView wItemOutdoor;
    private WorkItemGPONView wItemOlt;
    private WorkItemGPONView wItemDokiem;

    private SubWorkItemGPONView subView;

    public GponTienDoTienDoPresenter(IeGponTienDoFragment ieGponTienDoFragment,Context context) {
        this.context = context;
        this.ieGponTienDoFragment = ieGponTienDoFragment;
        ieGponTienDoModel = new GponTienDoModel(context);
    }

    @Override
    public void addWorkItem() {
        initWorkItemGpon();
        ieGponTienDoFragment.finishAddWKeoCap(wItemKeoCap);
        ieGponTienDoFragment.finishAddWHanNoi(wItemHanNoi);
        ieGponTienDoFragment.finishAddWOdfInDoor(wItemOdfIndoor);
        ieGponTienDoFragment.finishAddWOdfOutDoor(wItemOutdoor);
        ieGponTienDoFragment.finishAddWOlt(wItemOlt);
        ieGponTienDoFragment.finishAddWDoKiem(wItemDokiem);
    }

    @Override
    public void addSubWorkItem(WorkItemGPONView gponView) {
        ieGponTienDoModel.addSubWorkItem(this);
    }

    @Override
    public void addSWKeoCap() {
        ieGponTienDoModel.addSWKeoCap(wItemKeoCap,this);
    }

    @Override
    public void addSWValueInDoor() {
        ieGponTienDoModel.addSWValueIndoor(this);
    }

    @Override
    public void addSWValueOLT() {
        ieGponTienDoModel.addSWValueOLT(this);
    }

    @Override
    public void addSWValueKeoCap(ConstrNodeEntity node) {
        ieGponTienDoModel.addSWValueKeoCap(this);
    }



    @Override
    public void addSWValueByNode(View view, ConstrNodeEntity node) {
        ieGponTienDoModel.addSWValueByNode(view,node,this);
    }


    @Override
    public void finishAddSubWorkItem(ConstrNodeEntity node) {
        subView = new SubWorkItemGPONView(context);
        subView.setValue(node.getNodeName(), 0, 0, "");
//        subView.setWorkItemGPONView(gponView);
//        gponView.setRadioButton(subView.getRadioBtnCheck());
//        gponView.addSubView(subView);
        ieGponTienDoFragment.finishAddSubWorkItem(subView, node);
    }

    @Override
    public void finishAddSWKeoCap(ConstrNodeEntity node, WorkItemGPONView wItemKeoCap) {
        if (wItemKeoCap.getTvTitle().equals(this.wItemKeoCap.getTvTitle())) {
            SubWorkItemGPONView swKeoCap = new SubWorkItemGPONView(context);
            swKeoCap.setValue(node.getNodeName(), 0, 0, "");
            swKeoCap.setWorkItemGPONView(wItemKeoCap);
            wItemKeoCap.setRadioButton(swKeoCap.getRadioBtnCheck());
            wItemKeoCap.addSubView(swKeoCap);
            ieGponTienDoFragment.finishAddSWKeoCap(swKeoCap,node);
        }
    }

    @Override
    public void finishAddSWValueIndoor(Cat_Work_Item_TypesEntity catWorkItem) {
        if (catWorkItem.getCode().equals(Constant.CODE_LAPDAT_ODF)) {
            if (Constant.TAG_LAPDAT_ODF_INDOOR.equals(wItemOdfIndoor.getTvTitle())) {
                ieGponTienDoModel.addItemOdfIndoor(catWorkItem, this);
            }
        }
    }

    @Override
    public void finishAddSWValueOLT(Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        ArrayList<View> listItemRight = new ArrayList<>();

        if (catWorkItem.getCode().equals(Constant.CODE_LAPDAT_OLT)) {
            for (Cat_Sub_Work_ItemEntity catEntity : arrSWItem) {
                WorkItemRightOltDoKiemGpon oltGpon = new WorkItemRightOltDoKiemGpon(context);
                oltGpon.setTvTitle(catEntity.getName());
                listItemRight.add(oltGpon);
            }
            ieGponTienDoFragment.finishAddLapDatOltValue(listItemRight);
        }
    }

    @Override
    public void finishAddSWValueKeoCap(Cat_Work_Item_TypesEntity catWorkItem) {
        switch (catWorkItem.getCode()) {
            case Constant.CODE_CAPQUANG:
            case Constant.CODE_ADSS:
                Log.d(TAG, "finishAddSWValueByNode: called");
                WorkItemRightKeoCapHeaderGpon header = new WorkItemRightKeoCapHeaderGpon(context);
                header.setTvLoaiCap(catWorkItem.getItem_type_name());

                ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                        = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity catEntity : arrSWItem) {
                    WorkItemRightKeoCapItemGpon item = new WorkItemRightKeoCapItemGpon(context);
                    item.setTvItemLoaiCap(catEntity.getName());
                    header.addItemForLoaiCap(item);
                }

                ieGponTienDoFragment.finishAddKeoCapValue(header);

                break;
        }
    }

    @Override
    public void finishAddSWValueByNode(Cat_Work_Item_TypesEntity catWorkItem, View view) {

        switch (catWorkItem.getCode()) {
//            case Constant.CODE_CAPQUANG:
//            case Constant.CODE_ADSS:
//                Log.d(TAG, "finishAddSWValueByNode: called");
//                WorkItemRightKeoCapHeaderGpon header = new WorkItemRightKeoCapHeaderGpon(context);
//                header.setTvLoaiCap(catWorkItem.getItem_type_name());
//
//                ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
//                        = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
//                for (Cat_Sub_Work_ItemEntity catEntity : arrSWItem) {
//                    WorkItemRightKeoCapItemGpon item = new WorkItemRightKeoCapItemGpon(context);
//                    item.setTvItemLoaiCap(catEntity.getName());
//                    header.addItemForLoaiCap(item);
//                }
//
//                ieGponTienDoFragment.finishAddKeoCapValue(header);
//
//                break;
            case Constant.CODE_LAPDAT_HANNOI:

                break;
            case Constant.CODE_LAPDAT_ODF:
                if (view instanceof WorkItemGPONView) {
                    if (Constant.TAG_LAPDAT_ODF_OUTDOOR.equals(wItemOutdoor.getTvTitle())) {
                        ieGponTienDoModel.addItemOdfOutdoor(catWorkItem, this);
                    }
                }
                break;
            case Constant.CODE_DOKIEM:

                break;
            default:
                break;
        }
    }

    @Override
    public void finishAddOdfIndoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems) {

        HashSet<String> codeIndoor = new HashSet<>();
        codeIndoor.add("ODF_12_INDOOR_LAPDAT_BRCD");
        codeIndoor.add("ODF_24_INDOOR_LAPDAT_BRCD");
        codeIndoor.add("ODF_48_INDOOR_LAPDAT_BRCD");
        codeIndoor.add("ODF_96_INDOOR_LAPDAT_BRCD");

        ArrayList<View> listItemRight = new ArrayList<>();

        for (Cat_Sub_Work_ItemEntity entity : arrSubWorkItems) {
            if (codeIndoor.contains(entity.getCode())) {
                WorkItemRightLapDatOdfGpon lapDatOdfGpon
                        = new WorkItemRightLapDatOdfGpon(context);
                lapDatOdfGpon.setTvTenOdf(entity.getName());
                lapDatOdfGpon.setTvLuyKe("");
                listItemRight.add(lapDatOdfGpon);
            }

        }
        ieGponTienDoFragment.finishAddLapDatOdfInDoorValue(listItemRight);
    }

    @Override
    public void finishAddOdfOutdoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems) {

    }

    private void initWorkItemGpon() {
        // Khoi tao work tiem keo cap.
        wItemKeoCap = new WorkItemGPONView(context);
        wItemKeoCap.setTitle(Constant.TAG_KEOCAP);

        // Khoi tao work tiem han noi.
        wItemHanNoi = new WorkItemGPONView(context);
        wItemHanNoi.setTitle(Constant.TAG_LAPDAT_HANNOI);

        // Khoi tao work tiem indoor.
        wItemOdfIndoor = new WorkItemGPONView(context);
        wItemOdfIndoor.setTitle(Constant.TAG_LAPDAT_ODF_INDOOR);

        // Khoi tao work tiem outdoor.
        wItemOutdoor = new WorkItemGPONView(context);
        wItemOutdoor.setTitle(Constant.TAG_LAPDAT_ODF_OUTDOOR);

        // Khoi tao work tiem olt.
        wItemOlt = new WorkItemGPONView(context);
        wItemOlt.setTitle(Constant.TAG_LAPDAT_OLT);

        // Khoi tao work tiem do kiem.
        wItemDokiem = new WorkItemGPONView(context);
        wItemDokiem.setTitle(Constant.TAG_DOKIEM_NGHIEMTHU);
    }

}
