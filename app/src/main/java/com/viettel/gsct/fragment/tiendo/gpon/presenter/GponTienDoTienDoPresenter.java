package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.widget.Toast;

import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemHanNoi;
import com.viettel.gsct.View.gpon.WorkItemKeoCap;
import com.viettel.gsct.View.gpon.WorkItemOdf;
import com.viettel.gsct.View.gpon.WorkItemOltAndDoKiem;
import com.viettel.gsct.View.gpon.WorkItemValueHanNoiBoChia;
import com.viettel.gsct.View.gpon.WorkItemValueHanNoiTuThue;
import com.viettel.gsct.View.gpon.WorkItemValueKeoCapHeader;
import com.viettel.gsct.View.gpon.WorkItemValueKeoCap;
import com.viettel.gsct.View.gpon.WorkItemValueOdf;
import com.viettel.gsct.View.gpon.WorkItemValueOltDoKiem;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.GponTienDoModel;
import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;
import com.viettel.gsct.fragment.tiendo.gpon.view.IeGponTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.IeGponTienDoModel;
import com.viettel.gsct.preview.common.GponPreviewNodeFragment;
import com.viettel.gsct.utils.GSCTUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoTienDoPresenter implements IeGponTienDoPresenter, IeGponTienDoModel.IeListenerAddItem {

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

    // Controller.
    private Work_ItemsControler wController;
    private Sub_Work_Item_ValueController svController;

    // Hash map work item cap nhat theo tung node.
    private LinkedHashMap<Long, WorkItemKeoCap> hmWKeoCap = new LinkedHashMap<>();
    private HashMap<Long, WorkItemHanNoi> hmWHanNoi = new HashMap<>();
    private HashMap<Long, WorkItemOdf> hmWOdfOutdoor = new HashMap<>();
    private HashMap<Long, WorkItemOltAndDoKiem> hmWDoKiem = new HashMap<>();

    // Work item cap nhat theo cong trinh.
    private WorkItemOdf workItemOdfIndoor;
    private WorkItemOltAndDoKiem workItemOlt;

    public GponTienDoTienDoPresenter(IeGponTienDoFragment ieGponTienDoFragment, Context context) {
        this.context = context;
        this.ieGponTienDoFragment = ieGponTienDoFragment;
        ieGponTienDoModel = new GponTienDoModel(context);
        wController = new Work_ItemsControler(context);
        svController = new Sub_Work_Item_ValueController(context);
    }

    // Them toan bo work item, do van de design nen phai hard code.
    @Override
    public void addWorkItem() {
        if (BaseTienDoFragment.workItems.isEmpty()) {
            ieGponTienDoFragment.errorLoadWorkitem();
            return;
        }
        initWorkItemGpon();
        ieGponTienDoFragment.finishAddWKeoCap(wItemKeoCap);
        ieGponTienDoFragment.finishAddWHanNoi(wItemHanNoi);
        ieGponTienDoFragment.finishAddWOdfOutDoor(wItemOutdoor);
        ieGponTienDoFragment.finishAddWOdfInDoor(wItemOdfIndoor);
        ieGponTienDoFragment.finishAddWOlt(wItemOlt);
        ieGponTienDoFragment.finishAddWDoKiem(wItemDokiem);
    }

    // Them sub work item cho phan keo cap.
    @Override
    public void addSWKeoCap() {
        ieGponTienDoModel.addSWKeoCap(wItemKeoCap, this);
    }

    // Them sub work item cho phan han noi.
    @Override
    public void addSWHanNoi() {
        ieGponTienDoModel.addSWHanNoi(this);
    }

    // Them sub work item cho phan odf out door.
    @Override
    public void addSWOutdoor() {
        ieGponTienDoModel.addSWOutdoor(this);
    }

    // Them sub work item cho phan do kiem nghiem thu.
    @Override
    public void addSWDoKiem() {
        ieGponTienDoModel.addSWDoKiem(this);
    }

    // Them sub work item value cho phan odf indoor.
    // Work item nay cap nhat theo cong trinh.
    @Override
    public void addSWValueInDoor() {
        ieGponTienDoModel.addSWValueIndoor(this);
    }

    // Them sub work item value cho phan lap dat OLT.
    // Work item nay cap nhat theo cong trinh.
    @Override
    public void addSWValueOLT() {
        ieGponTienDoModel.addSWValueOLT(this);
    }

    // Them sub work item value cho phan keo cap.
    @Override
    public void addSWValueKeoCap(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueKeoCap(node, sView, this);
    }

    // Them sub work item value cho phan han noi.
    @Override
    public void addSWValueHanNoi(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueHanNoi(node, sView, this);
    }

    // Them sub work item value cho phan out door.
    @Override
    public void addSWValueOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueOutdoor(node, sView, this);
    }

    // Them sub work item value cho phan do kiem nghiem thu.
    @Override
    public void addSWValueDoKiem(ConstrNodeEntity node, SubWorkItemGPONView sView) {
        ieGponTienDoModel.addSWValueDoKiem(node, sView, this);
    }

    // Luu thong tin vua nhap xuong DB.
    @Override
    public void save() {
        // Luu data vao Database.
        saveDataToDataBase();
    }

    @Override
    public boolean checkValidate() {
        for (long kcKey : hmWKeoCap.keySet()) {
            if (!hmWKeoCap.get(kcKey).validate()) {
                Toast.makeText(context,"Số bạn vừa nhập không hợp lệ,vui lòng nhập lại!",Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }


    // Ham luu du lieu vua nhap xuong DB.
    private void saveDataToDataBase() {
        for (long kcKey : hmWKeoCap.keySet()) {
            hmWKeoCap.get(kcKey).save(kcKey);
        }
        for (long hnKey : hmWHanNoi.keySet()) {
            hmWHanNoi.get(hnKey).save(hnKey);
        }
        workItemOdfIndoor.save();
        for (long odKey : hmWOdfOutdoor.keySet()) {
            hmWOdfOutdoor.get(odKey).save(odKey);
        }
        workItemOlt.save();
        for (long odKey : hmWDoKiem.keySet()) {
            hmWDoKiem.get(odKey).save(odKey);
        }
    }

    // Model thong bao viec hoan thanh them Node cho work item keo cap.
    @Override
    public void finishAddSWKeoCap(ConstrNodeEntity node) {
        SubWorkItemGPONView swKeoCap = new SubWorkItemGPONView(context);
        swKeoCap.setValue(node.getNodeName(), 0, 0, "");
        swKeoCap.setWorkItemGPONView(wItemKeoCap);
        wItemKeoCap.addRadioButton(swKeoCap.getRadioBtnCheck());
        wItemKeoCap.addSubView(swKeoCap);
        ieGponTienDoFragment.finishAddSWKeoCap(swKeoCap, node);
    }

    // Model thong bao viec hoan thanh them Node cho work item han noi.
    @Override
    public void finishAddSWHanNoi(ConstrNodeEntity node) {
        SubWorkItemGPONView swHanNoi = new SubWorkItemGPONView(context);
        swHanNoi.setValue(node.getNodeName(), 0, 0, "");
        swHanNoi.setWorkItemGPONView(wItemHanNoi);
        wItemHanNoi.addRadioButton(swHanNoi.getRadioBtnCheck());
        wItemHanNoi.addSubView(swHanNoi);
        ieGponTienDoFragment.finishAddSWHanNoi(swHanNoi, node);
    }

    // Model thong bao viec hoan thanh them Node cho work item outdoor.
    @Override
    public void finishAddSWOutdoor(ConstrNodeEntity node) {
        SubWorkItemGPONView swOutdoor = new SubWorkItemGPONView(context);
        swOutdoor.setValue(node.getNodeName(), 0, 0, "");
        swOutdoor.setWorkItemGPONView(wItemOutdoor);
        wItemOutdoor.addRadioButton(swOutdoor.getRadioBtnCheck());
        wItemOutdoor.addSubView(swOutdoor);
        ieGponTienDoFragment.finishAddSWOutdoor(swOutdoor, node);
    }

    // Model thong bao viec hoan thanh them Node cho work item do kiem.
    @Override
    public void finishAddSWDoKiem(ConstrNodeEntity node) {
        SubWorkItemGPONView swDoKiem = new SubWorkItemGPONView(context);
        swDoKiem.setValue(node.getNodeName(), 0, 0, "");
        swDoKiem.setWorkItemGPONView(wItemDokiem);
        wItemDokiem.addRadioButton(swDoKiem.getRadioBtnCheck());
        wItemDokiem.addSubView(swDoKiem);
        ieGponTienDoFragment.finishAddSWDoKiem(swDoKiem, node);
    }

    // Model thong bao hoan thanh them value phan door.
    @Override
    public void finishAddSWValueIndoor(Cat_Work_Item_TypesEntity catWorkItem) {
        ieGponTienDoModel.addItemOdfIndoor(catWorkItem, this);
    }

    // Model thong bao hoan thanh them value phan olt.
    @Override
    public void finishAddSWValueOLT(Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        workItemOlt = new WorkItemOltAndDoKiem(context);

        Work_ItemsEntity wItemEntity = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), BaseFragment.constr_ConstructionItem.getConstructId());
        if (wItemEntity != null) {
            workItemOlt.addWorkitem(wItemEntity);
            for (Cat_Sub_Work_ItemEntity catEntity : arrSWItem) {
                Sub_Work_ItemEntity swiEntity = Sub_Work_ItemController.getInstance(context).getItem(wItemEntity.getId(),catEntity.getId());
                WorkItemValueOltDoKiem oltGpon = new WorkItemValueOltDoKiem(context);
                oltGpon.setTvTitle(catEntity.getName());
                oltGpon.addWIEntity(wItemEntity);
                oltGpon.addCSWIEntity(catEntity);
                oltGpon.addSWIEntity(swiEntity);
                workItemOlt.addValueItem(oltGpon);
            }
            ieGponTienDoFragment.finishAddLapDatOltValue(workItemOlt);
        }
    }


    // Model thong bao hoan thanh them value phan keo cap.
    @Override
    public void finishAddSWValueKeoCap(ConstrNodeEntity node, SubWorkItemGPONView sView, HashMap<String, Cat_Work_Item_TypesEntity> hmCatWorkItem) {
        WorkItemKeoCap keoCap = new WorkItemKeoCap(context);

        // Lay work item keo cap.No se chua 2 work item khac la Cap Quang so 8 va Cap Quang Adss.
        Cat_Work_Item_TypesEntity cwiKeoCap = hmCatWorkItem.get(Constant.CODE_KEOCAP);
        Work_ItemsEntity wIKeoCap = wController.getWorkByCatTest(cwiKeoCap.getItem_type_id(), node.getContructorId());
        if (wIKeoCap != null) {
            keoCap.addWorkItem(wIKeoCap);
            // Disable cac node da hoan thanh keo cap.
            sView.setFinish(wIKeoCap.hasCompletedDate() && (!GSCTUtils.getDateNow().equalsIgnoreCase(wIKeoCap.getComplete_date())));
        }

        // Khoi tao work item cap quang hinh so 8.
        Cat_Work_Item_TypesEntity cwiCapQuang = hmCatWorkItem.get(Constant.CODE_CAPQUANG);
        WorkItemValueKeoCapHeader hdCapQuang = new WorkItemValueKeoCapHeader(context);
        hdCapQuang.setTvLoaiCap(cwiCapQuang.getItem_type_name());
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem = BaseFragment.cat_sub_work_itemControler.getsubCates(cwiCapQuang.getItem_type_id());
        Work_ItemsEntity wItemCapSo8 = wController.getWorkByCatTest(cwiCapQuang.getItem_type_id(), node.getContructorId());
        if (wItemCapSo8 != null) {
            for (Cat_Sub_Work_ItemEntity cswItem : arrSWItem) {
                Sub_Work_Item_ValueEntity svItem = svController.getItemByNode(wItemCapSo8.getId(), cswItem.getId(),node.getNodeID());
                double luyke = svController.getLuykeByNode(wItemCapSo8.getId(), cswItem.getId(),node.getNodeID());
                double value = svItem != null ? svItem.getValue() : 0;

                WorkItemValueKeoCap item = new WorkItemValueKeoCap(context);
                item.addSWIValue(svItem);
                item.addCSWIEntity(cswItem);
                item.setTvItemLoaiCap(cswItem.getName());
                item.setTvLuyKe(luyke);
                item.setEdtKhoiLuong(value);
                item.addCTNodeEntity(node);
                hdCapQuang.addItemValue(item);

                if (svItem != null) {
                    if (svItem.hadAddedDate() && svItem.getAdded_date().equalsIgnoreCase(GSCTUtils.getDateNow())) {
                        sView.getRadioBtnCheck().setChecked(true);
                    }
                }
            }
            hdCapQuang.addWorkItem(wItemCapSo8);
            keoCap.addCapQuangSo8(hdCapQuang);
        }

        // Khoi tao work item cap quang adss.
        Cat_Work_Item_TypesEntity cwiAdss = hmCatWorkItem.get(Constant.CODE_ADSS);
        WorkItemValueKeoCapHeader hdAdss = new WorkItemValueKeoCapHeader(context);
        hdAdss.setTvLoaiCap(cwiAdss.getItem_type_name());
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItemAdss = BaseFragment.cat_sub_work_itemControler.getsubCates(cwiAdss.getItem_type_id());
        Work_ItemsEntity wItemAdss = wController.getWorkByCatTest(cwiAdss.getItem_type_id(), node.getContructorId());
        if (wItemAdss != null) {
            for (Cat_Sub_Work_ItemEntity cswItem : arrSWItemAdss) {
                Sub_Work_Item_ValueEntity svItem = svController.getItemByNode(wItemAdss.getId(), cswItem.getId(),node.getNodeID());
                double luyke = svController.getLuykeByNode(wItemAdss.getId(), cswItem.getId(),node.getNodeID());
                double value = svItem != null ? svItem.getValue() : 0;

                WorkItemValueKeoCap item = new WorkItemValueKeoCap(context);
                item.addSWIValue(svItem);
                item.addCSWIEntity(cswItem);
                item.setTvItemLoaiCap(cswItem.getName());
                item.setTvLuyKe(luyke);
                item.setEdtKhoiLuong(value);
                item.addCTNodeEntity(node);
                hdAdss.addItemValue(item);

                if (svItem != null) {
                    if (svItem.hadAddedDate() && svItem.getAdded_date().equalsIgnoreCase(GSCTUtils.getDateNow())) {
                        sView.getRadioBtnCheck().setChecked(true);
                    }
                }
            }
            hdAdss.addWorkItem(wItemAdss);
            keoCap.addCapQuangAdss(hdAdss);
        }
        sView.addSWValue(keoCap);
        hmWKeoCap.put(node.getNodeID(), keoCap);
        ieGponTienDoFragment.finishAddKeoCapValue(keoCap);
    }

    // Model thong bao hoan thanh them value phan han noi.
    @Override
    public void finishAddSWValueHanNoi(ConstrNodeEntity node, SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        WorkItemHanNoi wItemHanNoi = new WorkItemHanNoi(context);

        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        // Hard code 69280340 for test.
        Work_ItemsEntity wItem = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), node.getContructorId());
        if (wItem != null) {
            wItemHanNoi.addWorkItem(wItem);
            // Disable cac node da hoan thanh keo cap.
            sView.setFinish(wItem.hasCompletedDate() && (!GSCTUtils.getDateNow().equalsIgnoreCase(wItem.getComplete_date())));

            for (Cat_Sub_Work_ItemEntity cswItem : arrSWItem) {
                Sub_Work_Item_ValueEntity svItem = svController.getItemByNode(wItem.getId(), cswItem.getId(),node.getNodeID());
                double luyke = svController.getLuykeByNode(wItem.getId(), cswItem.getId(),node.getNodeID());
                double luykeHanNoi = svController.getLuykeHanNoiByNode(wItem.getId(), cswItem.getId(),node.getNodeID());
                double value = svItem != null ? svItem.getValue() : 0;
                double value_item = svItem != null ? svItem.getValue_item() : 0;
                if (cswItem.getCode().contains("FDH")) {
                    WorkItemValueHanNoiTuThue tuthue = new WorkItemValueHanNoiTuThue(context);
                    tuthue.setTvTenTu(cswItem.getName());
                    tuthue.setTvLuyKeLapDat(luyke);
                    tuthue.setTvLuyKeHanNoi(luykeHanNoi);
                    tuthue.setEdtKhoiLuongLapDat(value);
                    tuthue.setEdtKhoiLuongHanNoi(value_item);
                    tuthue.addWIEntity(wItem);
                    tuthue.addSWIValue(svItem);
                    tuthue.addCSWIEntity(cswItem);
                    tuthue.addCTNodeEntity(node);
                    wItemHanNoi.addValueTuThue(tuthue);
                } else if (cswItem.getCode().contains("BO_CHIA")) {
                    WorkItemValueHanNoiBoChia bochia = new WorkItemValueHanNoiBoChia(context);
                    bochia.setTvBochia(cswItem.getName());
                    bochia.setTvLuyKe(luyke);
                    bochia.setEdtKhoiLuong(value);
                    bochia.addWIEntity(wItem);
                    bochia.addSWIValue(svItem);
                    bochia.addCSWIEntity(cswItem);
                    bochia.addCTNodeEntity(node);
                    wItemHanNoi.addValueBoChia(bochia);
                }

                if (svItem != null) {
                    if (svItem.hadAddedDate() && GSCTUtils.getDateNow().equalsIgnoreCase(svItem.getAdded_date())) {
                        sView.getRadioBtnCheck().setChecked(true);
                    }
                }
            }
        }
        sView.addSWValue(wItemHanNoi);
        hmWHanNoi.put(node.getNodeID(), wItemHanNoi);
        ieGponTienDoFragment.finishAddHanNoiValue(wItemHanNoi);
    }

    // Model thong bao hoan thanh them value phan outdoor.
    @Override
    public void finishAddSWValueOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        ieGponTienDoModel.addItemOdfOutdoor(node, sView, catWorkItem, this);
    }

    // Model thong bao hoan thanh them value phan do kiem.
    @Override
    public void finishAddSWValueDoKiem(ConstrNodeEntity node, SubWorkItemGPONView sView, Cat_Work_Item_TypesEntity catWorkItem) {
        ArrayList<Cat_Sub_Work_ItemEntity> arrSWItem = BaseFragment.cat_sub_work_itemControler.getsubCates(catWorkItem.getItem_type_id());
        WorkItemOltAndDoKiem wiDoKiem = new WorkItemOltAndDoKiem(context);
        // Hard code 69280340 for test.
        Work_ItemsEntity wItem = wController.getWorkByCatTest(catWorkItem.getItem_type_id(), node.getContructorId());
        if (wItem != null) {
            wiDoKiem.addWorkitem(wItem);
            // Disable cac node da hoan thanh keo cap.
            sView.setFinish(wItem.hasCompletedDate() && (!GSCTUtils.getDateNow().equalsIgnoreCase(wItem.getComplete_date())));

            for (Cat_Sub_Work_ItemEntity cswEntity : arrSWItem) {
                Sub_Work_Item_ValueEntity svItem = svController.getItemByNodeDoKiem(wItem.getId(), cswEntity.getId(),node.getNodeID());
                WorkItemValueOltDoKiem dokiemItem = new WorkItemValueOltDoKiem(context);
                dokiemItem.setTvTitle(cswEntity.getName());
                dokiemItem.addWIEntity(wItem);
                dokiemItem.addCSWIEntity(cswEntity);
                dokiemItem.addSWIValue(svItem);
                dokiemItem.addCTNodeEntity(node);
                wiDoKiem.addValueItem(dokiemItem);

                if (svItem != null) {
                    if (svItem.hadAddedDate() && GSCTUtils.getDateNow().equalsIgnoreCase(svItem.getAdded_date())) {
                        sView.getRadioBtnCheck().setChecked(true);
                    }
                }
            }
        }
        sView.addSWValue(wiDoKiem);
        hmWDoKiem.put(node.getNodeID(), wiDoKiem);
        ieGponTienDoFragment.finishAddOdfDoKiemValue(wiDoKiem);

    }

    // Model thong bao hoan thanh them value phan indoor.
    @Override
    public void finishAddOdfIndoor(ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork) {
        workItemOdfIndoor = new WorkItemOdf(context);
        Work_ItemsEntity wItem = wController.getWorkByCatTest(catWork.getItem_type_id(), BaseFragment.constr_ConstructionItem.getConstructId());
        if (wItem != null) {
            workItemOdfIndoor.addWorkItem(wItem);
            for (Cat_Sub_Work_ItemEntity entity : arrSubWorkItems) {
                if (entity.getCode().contains("INDOOR")) {
                    Sub_Work_ItemEntity swiEntity = Sub_Work_ItemController.getInstance(context).getItemForGetValue(wItem.getId(),entity.getId());
                    double luyKe = Sub_Work_ItemController.getInstance(context).getLuyke(wItem.getId(),entity.getId());
                    double value = swiEntity != null ? swiEntity.getValue() : 0;

                    WorkItemValueOdf odfIndoor = new WorkItemValueOdf(context);
                    odfIndoor.setOdfTAG(Constant.TAG_LAPDAT_ODF_INDOOR);
                    odfIndoor.setTvTenOdf(entity.getName());
                    odfIndoor.setTvLuyKe(luyKe);
                    odfIndoor.setEdtKhoiLuong(value);
                    odfIndoor.addSWIEntity(swiEntity);
                    odfIndoor.addCSWIEntity(entity);
                    workItemOdfIndoor.addValueOdf(odfIndoor);
                }
            }
        }
//        this.workItemOdfIndoor = wiOdf;
        ieGponTienDoFragment.finishAddOdfInDoorValue(workItemOdfIndoor);
    }

    // Model thong bao hoan thanh them value phan outdoor.
    @Override
    public void finishAddOdfOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView, ArrayList<Cat_Sub_Work_ItemEntity> arrSubWorkItems, Cat_Work_Item_TypesEntity catWork) {
        WorkItemOdf wiOdf = new WorkItemOdf(context);
        // Hard code 69280340 for test.
        Work_ItemsEntity wItem = wController.getWorkByCatTest(catWork.getItem_type_id(), node.getContructorId());
        if (wItem != null) {
            wiOdf.addWorkItem(wItem);
            // Disable cac node da hoan thanh keo cap.
            sView.setFinish(wItem.hasCompletedDate() && (!GSCTUtils.getDateNow().equalsIgnoreCase(wItem.getComplete_date())));

            for (Cat_Sub_Work_ItemEntity entity : arrSubWorkItems) {
                if (entity.getCode().contains("OUTDOOR")) {
                    Sub_Work_Item_ValueEntity svItem = svController.getItemByNode(wItem.getId(), entity.getId(),node.getNodeID());
                    double luyke = svController.getLuykeByNode(wItem.getId(), entity.getId(),node.getNodeID());
                    double value = svItem != null ? svItem.getValue() : 0;

                    WorkItemValueOdf odfOutdoor = new WorkItemValueOdf(context);
                    odfOutdoor.setOdfTAG(Constant.TAG_LAPDAT_ODF_OUTDOOR);
                    odfOutdoor.setTvTenOdf(entity.getName());
                    odfOutdoor.setTvLuyKe(luyke);
                    odfOutdoor.setEdtKhoiLuong(value);
                    odfOutdoor.addCSWIEntity(entity);
                    odfOutdoor.addSWIValue(svItem);
                    odfOutdoor.addCTNodeEntity(node);
                    wiOdf.addValueOdf(odfOutdoor);

                    if (svItem != null) {
                        if (svItem.hadAddedDate() && GSCTUtils.getDateNow().equalsIgnoreCase(svItem.getAdded_date())) {
                            sView.getRadioBtnCheck().setChecked(true);
                        }
                    }
                }
            }
        }
        sView.addSWValue(wiOdf);
        hmWOdfOutdoor.put(node.getNodeID(), wiOdf);
        ieGponTienDoFragment.finishAddOdfOutDoorValue(wiOdf);
    }

    // Khoi tao work item cho cap nhat tien do Gpon.Phan nay la hard code.
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

    @Override
    public void showPreviewTienDo(BaseGponPreview gponPreview) {
        GponPreviewNodeFragment gponFragment = null;
        if (gponPreview instanceof GponPreviewNodeFragment) {
            gponFragment = (GponPreviewNodeFragment) gponPreview;
        }
        if (gponFragment != null) {
            gponFragment.initDataNewTienDoPreviewCT(workItemOdfIndoor,workItemOlt);
            gponFragment.initDataNewTienDoPreviewNode(hmWKeoCap,hmWHanNoi,hmWOdfOutdoor,hmWDoKiem);
        }
    }

}
