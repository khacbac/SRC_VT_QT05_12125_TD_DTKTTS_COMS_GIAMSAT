package com.viettel.view.listener;

import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.View.SubWorkItemTienDoBTSView;
import com.viettel.gsct.View.TiendoBTSItemView;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public interface InterfacePassDataFromTienDoToActivity {
    void passDataFromTienDoToActivity(
            ArrayList<TiendoBTSItemView> listData,
            ConcurrentHashMap<Integer,
                    ArrayList<SubWorkItemTienDoBTSView>> subWorkItemTienDoBTSViewHashMap,
            ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems);
}
