package com.viettel.gsct.fragment.tiendo.gpon.view;

import android.widget.LinearLayout;

import com.viettel.gsct.View.gpon.SubWorkItemGponOldView;
import com.viettel.gsct.View.gpon.WorkItemHanNoi;
import com.viettel.gsct.View.gpon.WorkItemKeoCap;
import com.viettel.gsct.View.gpon.WorkItemOdf;
import com.viettel.gsct.View.gpon.WorkItemOltAndDoKiem;
import com.viettel.gsct.fragment.base.BaseFragment;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by bachk on 11/4/2017.
 */

public abstract class BaseGponPreview extends BaseFragment{
    @Override
    public void save() {

    }

    // Khoi tao da phan preview cap nhat nhat ky.
    public abstract void initDataForNhatKy(LinkedHashMap<String, String> hashMaps, String[] keyDoiBangrongArr, String[] keyTenHmBangrongArr);

    // Khoi tao data phan preview tien do doi voi cac cong trinh cu chi cap nhat theo cong trinh.
    public  void initDataOldTienDoPreview(HashMap<String, SubWorkItemGponOldView> hmSubWorkItem) {}

    // Khoi tao data phan preview tien do doi voi cac cong trinh moi.Cap nhat ca theo node va theo cong trinh.
    // Khoi tao data phan cap nhat theo node.
    public  void initDataNewTienDoPreviewNode(HashMap<Long, WorkItemKeoCap> hmkeoCap, HashMap<Long, WorkItemHanNoi> hmWHanNoi, HashMap<Long, WorkItemOdf> hmWOdfOutdoor, HashMap<Long, WorkItemOltAndDoKiem> hmWDoKiem) {}
    // Khoi tao data phan cap nhat theo cong trinh.
    public  void initDataNewTienDoPreviewCT(WorkItemOdf itemOdf, WorkItemOltAndDoKiem workItemOlt){}
}
