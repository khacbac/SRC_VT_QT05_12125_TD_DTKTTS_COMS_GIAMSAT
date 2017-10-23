package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemValueHanNoiBoChia;
import com.viettel.gsct.View.gpon.WorkItemValueHanNoiTuThue;
import com.viettel.gsct.View.gpon.WorkItemValueKeoCapHeader;
import com.viettel.gsct.View.gpon.WorkItemValueKeoCap;
import com.viettel.gsct.View.gpon.WorkItemValueOdf;
import com.viettel.gsct.View.gpon.WorkItemValueOltDoKiem;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.GponTienDoModel;
import com.viettel.gsct.fragment.tiendo.gpon.view.IeGponTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.IeGponTienDoModel;

import java.util.ArrayList;

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

    private Work_ItemsControler wController;
    private Sub_Work_Item_ValueController svController;
    private ArrayList<WorkItemValueKeoCap> listSWIValue = new ArrayList<>();

    public GponTienDoTienDoPresenter(IeGponTienDoFragment ieGponTienDoFragment, Context context) {
        this.context = context;
        this.ieGponTienDoFragment = ieGponTienDoFragment;
        ieGponTienDoModel = new GponTienDoModel(context);
        wController = new Work_ItemsControler(context);
        svController = new Sub_Work_Item_ValueController(context);
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
    public void addSWKeoCap() {
        ieGponTienDoModel.addSWKeoCap(wItemKeoCap, this);
    }

    @Override
    public void addSWHanNoi() {
        ieGponTienDoModel.addSWHanNoi(this);
    }

    @Override
    public void addSWOutdoor() {
        ieGponTienDoModel.addSWOutdoor(this);
    }

    @Override
    public void addSWDoKiem() {
        ieGponTienDoModel.addSWDoKiem(this);
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
    public void addSWValueKeoCap(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueKeoCap(sView, this);
    }

    @Override
    public void addSWValueHanNoi(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueHanNoi(sView, this);
    }

    @Override
    public void addSWValueOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueOutdoor(sView, this);
    }

    @Override
    public void addSWValueDoKiem(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueDoKiem(sView, this);
    }

    @Override
    public void saveSWIValue() {
        for (WorkItemValueKeoCap entity : listSWIValue) {
//            entity.saveSWIItemToDB();
        }
    }

    @Override
    public void finishAddSWKeoCap(ConstrNodeEntity node) {
        SubWorkItemGPONView swKeoCap = new SubWorkItemGPONView(context);
        swKeoCap.setValue(node.getNodeName(), 0, 0, "");
        swKeoCap.setWorkItemGPONView(wItemKeoCap);
        wItemKeoCap.setRadioButton(swKeoCap.getRadioBtnCheck());
        wItemKeoCap.addSubView(swKeoCap);
        ieGponTienDoFragment.finishAddSWKeoCap(swKeoCap, node);
    }

    @Override
    public void finishAddSWHanNoi(ConstrNodeEntity node) {
        SubWorkItemGPONView swHanNoi = new SubWorkItemGPONView(context);
        swHanNoi.setValue(node.getNodeName(), 0, 0, "");
        swHanNoi.setWorkItemGPONView(wItemHanNoi);
        wItemHanNoi.setRadioButton(swHanNoi.getRadioBtnCheck());
        wItemHanNoi.addSubView(swHanNoi);
        ieGponTienDoFragment.finishAddSWHanNoi(swHanNoi, node);
    }

    @Override
    public void finishAddSWOutdoor(ConstrNodeEntity node) {
        SubWorkItemGPONView swOutdoor = new SubWorkItemGPONView(context);
        swOutdoor.setValue(node.getNodeName(), 0, 0, "");
        swOutdoor.setWorkItemGPONView(wItemOutdoor);
        wItemOutdoor.setRadioButton(swOutdoor.getRadioBtnCheck());
        wItemOutdoor.addSubView(swOutdoor);
        ieGponTienDoFragment.finishAddSWOutdoor(swOutdoor, node);
    }

    @Override
    public void finishAddSWDoKiem(ConstrNodeEntity node) {
        SubWorkItemGPONView swDoKiem = new SubWorkItemGPONView(context);
        swDoKiem.setValue(node.getNodeName(), 0, 0, "");
        swDoKiem.setWorkItemGPONView(wItemDokiem);
        wItemDokiem.setRadioButton(swDoKiem.getRadioBtnCheck());
        wItemDokiem.addSubView(swDoKiem);
        ieGponTienDoFragment.finishAddSWDoKiem(swDoKiem, node);
    }

    @Override
    public void finishAddSWValueIndoor(Cat_Work_Item_TypesEntity catWorkItem) {
        ieGponTienDoModel.addItemOdfIndoor(catWorkItem, this);
    }

    @Override
    public void finishAddSWValueOLT(Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        ArrayList<View> listItemRight = new ArrayList<>();
        for (Cat_Sub_Work_ItemEntity catEntity : arrSWItem) {
            WorkItemValueOltDoKiem oltGpon = new WorkItemValueOltDoKiem(context);
            oltGpon.setTvTitle(catEntity.getName());
            listItemRight.add(oltGpon);
        }
        ieGponTienDoFragment.finishAddLapDatOltValue(listItemRight);
    }

    @Override
    public void finishAddSWValueKeoCap(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        Log.d(TAG, "finishAddSWValueByNode: called");
        WorkItemValueKeoCapHeader header = new WorkItemValueKeoCapHeader(context);
        header.setTvLoaiCap(catWorkItem.getItem_type_name());

        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        Log.d(TAG, "finishAddSWValueKeoCap: arrSWItem size = " + arrSWItem.size());
        for (Cat_Sub_Work_ItemEntity cswItem : arrSWItem) {

            // Hard code 69280340 for test.
            Work_ItemsEntity wItem = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), 69280340);
            Sub_Work_Item_ValueEntity svItem = svController.getItem(wItem.getId(), cswItem.getId());
            double luyke = svController.getLuyke(wItem.getId(), cswItem.getId());
            double value = svItem != null ? svItem.getValue() : 0;

            WorkItemValueKeoCap item = new WorkItemValueKeoCap(context);
            listSWIValue.add(item);
            item.addSWIValue(svItem);
            item.setTvItemLoaiCap(cswItem.getName());
            item.setTvLuyKe(luyke);
            item.setEdtKhoiLuong(value);
            header.addItemForLoaiCap(item);
        }
        sView.addListSWValue(header);
        ieGponTienDoFragment.finishAddKeoCapValue(header);

    }

    @Override
    public void finishAddSWValueHanNoi(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        for (Cat_Sub_Work_ItemEntity cswItem : arrSWItem) {
            // Hard code 69280340 for test.
            Work_ItemsEntity wItem = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), 69280340);
            Sub_Work_Item_ValueEntity svItem = svController.getItem(wItem.getId(), cswItem.getId());
            double luyke = svController.getLuyke(wItem.getId(), cswItem.getId());
            double value = svItem != null ? svItem.getValue() : 0;
            if (cswItem.getCode().contains("FDH")) {
                WorkItemValueHanNoiTuThue tuthue = new WorkItemValueHanNoiTuThue(context);
                tuthue.setTvTenTu(cswItem.getName());
                tuthue.setTvLuyKeHanNoi(value);
                /*Kiem tra lai data type cua thang VALUE 2*/
                sView.addListSWValue(tuthue);
                ieGponTienDoFragment.finishAddTuThueValue(tuthue);
            } else if (cswItem.getCode().contains("BO_CHIA")) {
                WorkItemValueHanNoiBoChia bochia = new WorkItemValueHanNoiBoChia(context);
                bochia.setTvBochia(cswItem.getName());
                bochia.setTvLuyKe(luyke);
                bochia.setEdtKhoiLuong(value);
                sView.addListSWValue(bochia);
                ieGponTienDoFragment.finishAddBoChiaValue(bochia);
            }
        }
    }

    @Override
    public void finishAddSWValueOutdoor(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        ieGponTienDoModel.addItemOdfOutdoor(sView, catWorkItem, this);
    }

    @Override
    public void finishAddSWValueDoKiem(SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem
                = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        for (Cat_Sub_Work_ItemEntity cswEntity : arrSWItem) {
            // Hard code 69280340 for test.
            Work_ItemsEntity wItem = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), 69280340);
//            BaseTienDoFragment.hashWorkItems.put(wItem.getItem_type_id(),wItem);
            Sub_Work_Item_ValueEntity svItem = svController.getItem(wItem.getId(), cswEntity.getId());

            Log.d(TAG, "finishAddSWValueDoKiem: wItem id = " + wItem.getId());
            Log.d(TAG, "finishAddSWValueDoKiem: cswEntity id = " + cswEntity.getId());

            WorkItemValueOltDoKiem dokiemItem = new WorkItemValueOltDoKiem(context);
            dokiemItem.setTvTitle(cswEntity.getName());
            sView.addListSWValue(dokiemItem);
            ieGponTienDoFragment.finishAddOdfDoKiemValue(dokiemItem);
        }
    }

    @Override
    public void finishAddOdfIndoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork) {
        for (Cat_Sub_Work_ItemEntity entity : arrSubWorkItems) {
            if (entity.getCode().contains("INDOOR")) {
                // Hard code 69280340 for test.
                Work_ItemsEntity wItem = wController.getWorkByCatTest(catWork.getItem_type_id(), 69280340);
                Sub_Work_Item_ValueEntity svItem = svController.getItem(wItem.getId(), entity.getId());
                double luyke = svController.getLuyke(wItem.getId(), entity.getId());
                double value = svItem != null ? svItem.getValue() : 0;

                WorkItemValueOdf odfIndoor = new WorkItemValueOdf(context);
                odfIndoor.setTvTenOdf(entity.getName());
                odfIndoor.setTvLuyKe(luyke);
                odfIndoor.setEdtKhoiLuong(value);

                ieGponTienDoFragment.finishAddOdfInDoorValue(odfIndoor);
            }
        }
    }

    @Override
    public void finishAddOdfOutdoor(SubWorkItemGPONView sView, ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork) {
        for (Cat_Sub_Work_ItemEntity entity : arrSubWorkItems) {
            if (entity.getCode().contains("OUTDOOR")) {

                // Hard code 69280340 for test.
                Work_ItemsEntity wItem = wController.getWorkByCatTest(catWork.getItem_type_id(), 69280340);
                Sub_Work_Item_ValueEntity svItem = svController.getItem(wItem.getId(), entity.getId());
                double luyke = svController.getLuyke(wItem.getId(), entity.getId());
                double value = svItem != null ? svItem.getValue() : 0;

                WorkItemValueOdf odfOutdoor = new WorkItemValueOdf(context);
                odfOutdoor.setTvTenOdf(entity.getName());
                odfOutdoor.setTvLuyKe(luyke);
                odfOutdoor.setEdtKhoiLuong(value);
                sView.addListSWValue(odfOutdoor);
                ieGponTienDoFragment.finishAddOdfOutDoorValue(odfOutdoor);
            }
        }
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
