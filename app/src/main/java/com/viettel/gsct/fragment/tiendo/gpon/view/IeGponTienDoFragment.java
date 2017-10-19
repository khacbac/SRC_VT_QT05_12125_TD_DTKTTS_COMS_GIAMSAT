package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.view.View;
import android.widget.LinearLayout;

import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;

import java.util.ArrayList;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public interface IeGponTienDoFragment {
    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param view WorkItemGPONView.
     */
    void finishAddWKeoCap(WorkItemGPONView view);
    void finishAddWHanNoi(WorkItemGPONView view);
    void finishAddWOdfInDoor(WorkItemGPONView view);
    void finishAddWOdfOutDoor(WorkItemGPONView view);
    void finishAddWOlt(WorkItemGPONView view);
    void finishAddWDoKiem(WorkItemGPONView view);
    /**
     * Add WorkItemGPONView cho Gpon Tien do.
     * @param view WorkItemGPONView.
     */


    /**
     * Add SubWorkItemGPONView cho Gpon Tien do.
     * @param view SubWorkItemGPONView.
     * @param node
     */
    void finishAddSubWorkItem(View view, ConstrNodeEntity node);

    void finishAddSWKeoCap(View view);

    void finishAddKeoCapValue(View view);
    void finishAddLapDatHanNoiValue(View view);
    void finishAddLapDatOdfInDoorValue(ArrayList<View> listRightView);
    void finishAddLapDatOdfOutDoorValue(View view);
    void finishAddLapDatOltValue(ArrayList<View> listRightView);
    void finishAddDoKiemNghiemThuValue(View view);
}
