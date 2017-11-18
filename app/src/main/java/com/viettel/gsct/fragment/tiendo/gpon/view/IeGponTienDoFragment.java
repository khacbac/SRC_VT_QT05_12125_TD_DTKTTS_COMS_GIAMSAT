package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.view.View;
import android.widget.LinearLayout;

import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.gpon.SubWorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.View.gpon.WorkItemValueOdf;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoFragment {

    // Truong hop ko lay duoc work item.
    void errorLoadWorkitem();

    // Thong bao hoan thanh them work item cho main view.
    void finishAddWKeoCap(WorkItemGPONView view);
    void finishAddWHanNoi(WorkItemGPONView view);
    void finishAddWOdfInDoor(WorkItemGPONView view);
    void finishAddWOdfOutDoor(WorkItemGPONView view);
    void finishAddWOlt(WorkItemGPONView view);
    void finishAddWDoKiem(WorkItemGPONView view);

    // Thong bao hoan thanh them subwork item cho main view.
    void finishAddSWKeoCap(SubWorkItemGPONView view, ConstrNodeEntity node);
    void finishAddSWHanNoi(SubWorkItemGPONView view, ConstrNodeEntity node);
    void finishAddSWOutdoor(SubWorkItemGPONView view, ConstrNodeEntity node);
    void finishAddSWDoKiem(SubWorkItemGPONView view, ConstrNodeEntity node);

    // Thong bao hoan thanh them subwork value doi voi cac subwork item tuong ung cho main view.
    void finishAddKeoCapValue(View view);
    void finishAddHanNoiValue(View view);
    void finishAddLapDatOltValue(View view);
    void finishAddOdfInDoorValue(View view);
    void finishAddOdfOutDoorValue(View view);
    void finishAddOdfDoKiemValue(View view);
}
