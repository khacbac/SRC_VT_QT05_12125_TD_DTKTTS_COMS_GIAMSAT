package com.viettel.gsct.fragment.tiendo.gpon.presenter;

import android.content.Context;
import android.view.View;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.gpon.WorkItemGPONView;
import com.viettel.gsct.fragment.tiendo.gpon.model.GponTienDoModel;
import com.viettel.gsct.fragment.tiendo.gpon.view.IeGponTienDoFragment;
import com.viettel.gsct.fragment.tiendo.gpon.model.IeGponTienDoModel;

/**
 * Created by doanLV4 on 9/20/2017.
 */

public class GponTienDoTienDoPresenter implements IeGponTienDoPresenter,
        IeGponTienDoModel.IeListenerAddLayout {

    private IeGponTienDoFragment ieGponTienDoFragment;
    private IeGponTienDoModel ieGponTienDoModel;

    public GponTienDoTienDoPresenter(IeGponTienDoFragment ieGponTienDoFragment) {
        this.ieGponTienDoFragment = ieGponTienDoFragment;
        ieGponTienDoModel = new GponTienDoModel();
    }

    @Override
    public void addWorkItemGponByNode(Work_ItemsEntity entity, Context context) {
        ieGponTienDoModel.addWorkItemGponByNode(entity, context, this);
    }

    @Override
    public void addWorkItemGponByCongTrinh(Work_ItemsEntity entity, Context context) {
        ieGponTienDoModel.addWorkItemGponByCongTrinh(entity,context,this);
    }

    @Override
    public void addSubWorkItemGponByNode(WorkItemGPONView gponView, Context context) {
        ieGponTienDoModel.addSubWorkItemGponByNode(gponView, context, this);
    }

    @Override
    public void addRightSubWorkGponByNode(View subView, Context context) {
        ieGponTienDoModel.addRightSubWorkGponByNode(subView,context,this);
    }

    @Override
    public void addRightSubWorkGponByCongTrinh(View subView, Context context) {
        ieGponTienDoModel.addRightSubWorkGponByCongTrinh(subView,context,this);
    }

    @Override
    public void addWorkItemByNode(View view) {
        ieGponTienDoFragment.addWorkItemGponByNode(view);
    }

    @Override
    public void addWorkItemByCongTrinh(View view) {
        ieGponTienDoFragment.addWorkItemGponByCongTrinh(view);
    }

    @Override
    public void addSubWorkItemByNode(View view) {
        ieGponTienDoFragment.addSubWorkItemGponByNode(view);
    }

    @Override
    public void addRightSubWorkItemByNode(View subView) {
        ieGponTienDoFragment.addRightSubWorkItemGponByNode(subView);
    }

    @Override
    public void addRightSubWorkItemByCongTrinh(View subView) {
        ieGponTienDoFragment.addRightSubWorkItemGponByCongTrinh(subView);
    }
}
