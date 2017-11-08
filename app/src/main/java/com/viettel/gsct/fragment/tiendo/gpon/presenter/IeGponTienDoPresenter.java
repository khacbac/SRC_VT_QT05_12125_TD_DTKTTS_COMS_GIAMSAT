package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.viettel.database.Cat_Work_Item_TypesControler;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoPresenter {
    // them toan bo work item.
    void addWorkItem();

    // Them cac sub work item tuong ung (node).
    // Olt va odf indoor cap nhat theo cong trinh nen khong co node.
    void addSWKeoCap();
    void addSWHanNoi();
    void addSWOutdoor();
    void addSWDoKiem();

    // Them value cho cac sub work cap nhat theo cong trinh.
    void addSWValueInDoor();
    void addSWValueOLT();

    // Them value cho cac sub work cap nhat theo node.
    void addSWValueKeoCap(ConstrNodeEntity node, SubWorkItemGPONView sView);
    void addSWValueHanNoi(ConstrNodeEntity node, SubWorkItemGPONView sView);
    void addSWValueOutdoor(ConstrNodeEntity node, SubWorkItemGPONView sView);
    void addSWValueDoKiem(ConstrNodeEntity node, SubWorkItemGPONView sView);

    // Save tien do.
    void save();

    // Kiem tra dien kien hoan thanh,dieu kien luu.
    boolean checkValidate();

    // Hien thi man hinh xem thong tin vua moi nhap truoc khi luu.
    void showPreviewTienDo(BaseGponPreview mGponPrevFrag);
}
