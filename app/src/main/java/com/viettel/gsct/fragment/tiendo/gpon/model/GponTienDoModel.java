package com.viettel.gsct.fragment.tiendo.gpon.model;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemRightHanNoiTuThueGpon;
import com.viettel.gsct.View.gpon.WorkItemRightKeoCapHeaderGpon;
import com.viettel.gsct.View.gpon.WorkItemRightHanNoiBoChiaGpon;
import com.viettel.gsct.View.gpon.WorkItemRightKeoCapItemGpon;
import com.viettel.gsct.View.gpon.WorkItemRightLapDatOdfGpon;
import com.viettel.gsct.View.gpon.WorkItemRightOltDoKiemGpon;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoModel implements IeGponTienDoModel {

    private static final String TAG = GponTienDoModel.class.getSimpleName();

    @Override
    public void addWorkItemGponByNode(Work_ItemsEntity entity, Context context,
                                      IeListenerAddLayout addLayout) {
        WorkItemGPONView view = new WorkItemGPONView(context);
        view.setTitle(entity.getWork_item_name());
        addLayout.addWorkItemByNode(view);
    }

    @Override
    public void addWorkItemGponByCongTrinh(Work_ItemsEntity entity,
                                           Context context, IeListenerAddLayout addLayout) {
        WorkItemGPONView view = new WorkItemGPONView(context);
        view.setTitle(entity.getWork_item_name());
        addLayout.addWorkItemByCongTrinh(view);
    }

    @Override
    public void addSubWorkItemGponByNode(WorkItemGPONView gponView, Context context,
                                         IeListenerAddLayout addLayout) {
        if (Constant.TAG_LAPDAT_ODF_INDOOR.equals(gponView.getTvTitle())
                || Constant.TAG_LAPDAT_OLT.equals(gponView.getTvTitle())) {
            return;
        }
        SubWorkItemGPONView subView = new SubWorkItemGPONView(context);
        subView.setValue("Node", 0, 0, "");
        subView.setWorkItemGPONView(gponView);
        gponView.setRadioButton(subView.getRadioBtnCheck());
        gponView.addSubView(subView);
        addLayout.addSubWorkItemByNode(subView);
    }

    @Override
    public void addRightSubWorkGponByNode(View subView, Context context,
                                          IeListenerAddLayout addLayout) {
        SubWorkItemGPONView subWork = (SubWorkItemGPONView) subView;
        String subWorkType = subWork.getWorkItemGPONView().getTvTitle().trim();
        switch (subWorkType) {
            case Constant.TAG_KEOCAP:
                for (int index = 0; index < 2; index++) {
                    WorkItemRightKeoCapHeaderGpon keoCapGpon
                            = new WorkItemRightKeoCapHeaderGpon(context);
                    keoCapGpon.setTvLoaiCap("Loai cap" + index);
                    for (int i = 0; i < 4; i++) {
                        WorkItemRightKeoCapItemGpon keoCapItemGpon
                                = new WorkItemRightKeoCapItemGpon(context);
                        keoCapItemGpon.setTvItemLoaiCap("Cap " + i);
                        keoCapItemGpon.setTvDvt("mét");
                        keoCapItemGpon.setTvLuyKe("" + i);
                        keoCapGpon.addItemForLoaiCap(keoCapItemGpon);
                    }
                    subWork.addRightItemSubView(keoCapGpon);
//                    subWork.addSubWorkRightItem(keoCapGpon);
                }
                subWork.getRadioBtnCheck().setChecked(true);
                break;
            case Constant.TAG_LAPDAT_HANNOI:
                for (int i = 0; i < 4; i++) {
                    // Item phan bo chia.
                    WorkItemRightHanNoiBoChiaGpon hanNoiBoChiaGpon
                            = new WorkItemRightHanNoiBoChiaGpon(context);
                    hanNoiBoChiaGpon.setTvBochia("Bo chia " + i);
                    hanNoiBoChiaGpon.setTvLuyKe("" + i);
                    subWork.addRightItemSubView(hanNoiBoChiaGpon);

                    // Item phan tu thue.
                    WorkItemRightHanNoiTuThueGpon hanNoiTuThueGpon
                            = new WorkItemRightHanNoiTuThueGpon(context);
                    hanNoiTuThueGpon.setTvTenTu("Tu " + i);
                    hanNoiTuThueGpon.setTvLuyKeLapDat("" + i);
                    hanNoiTuThueGpon.setTvLuyKeHanNoi("" + i);
                    subWork.addRightItemSubView(hanNoiTuThueGpon);
                }
                break;
//            case Constant.TAG_LAPDAT_ODF_INDOOR:
//                break;
            case Constant.TAG_LAPDAT_ODF_OUTDOOR:
                for (int index = 0; index < 4; index++) {
                    WorkItemRightLapDatOdfGpon lapDatOdfGpon
                            = new WorkItemRightLapDatOdfGpon(context);
                    lapDatOdfGpon.setTvTenOdf("ODF Outdoor " + index);
                    lapDatOdfGpon.setTvLuyKe("" + index);
                    subWork.addRightItemSubView(lapDatOdfGpon);
                }
                break;
//            case Constant.TAG_LAPDAT_OLT:
//                for (int index = 0; index < 4; index++) {
//                    WorkItemRightOltDoKiemGpon oltGpon
//                            = new WorkItemRightOltDoKiemGpon(context);
//                    oltGpon.setTvTitle("Olt " + index);
//                    subWork.addRightItemSubView(oltGpon);
//                }
//                break;
            case Constant.TAG_DOKIEM_NGHIEMTHU:
                for (int index = 0; index < 2; index++) {
                    WorkItemRightOltDoKiemGpon doKiemGpon
                            = new WorkItemRightOltDoKiemGpon(context);
                    doKiemGpon.setTvTitle("Do kiem " + index);
                    subWork.addRightItemSubView(doKiemGpon);
                }
                break;
            default:
                break;
        }
        addLayout.addRightSubWorkItemByNode(subWork);
    }

    @Override
    public void addRightSubWorkGponByCongTrinh(View subView,
                                               Context context, IeListenerAddLayout addLayout) {
        WorkItemGPONView workItem = (WorkItemGPONView) subView;
        String workItemType = workItem.getTvTitle();
        switch (workItemType) {
            case Constant.TAG_LAPDAT_ODF_INDOOR:
                for (int index = 0; index < 4; index++) {
                    WorkItemRightLapDatOdfGpon lapDatOdfGpon
                            = new WorkItemRightLapDatOdfGpon(context);
                    lapDatOdfGpon.setTvTenOdf("ODF Indoor " + index);
                    lapDatOdfGpon.setTvLuyKe("" + index);
                    workItem.addRightItemSubView(lapDatOdfGpon);
                }
                break;
            case Constant.TAG_LAPDAT_OLT:
                for (int index = 0; index < 2; index++) {
                    WorkItemRightOltDoKiemGpon oltGpon
                            = new WorkItemRightOltDoKiemGpon(context);
                    oltGpon.setTvTitle("Olt " + index);
                    workItem.addRightItemSubView(oltGpon);
                }
                break;
        }
        addLayout.addRightSubWorkItemByCongTrinh(workItem);
    }
}
