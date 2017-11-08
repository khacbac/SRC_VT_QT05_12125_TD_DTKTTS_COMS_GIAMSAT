package com.viettel.gsct.fragment.base;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.gsct.fragment.tiendo.gpon.view.BaseGponPreview;
import com.viettel.view.listener.IeSave;
import com.viettel.view.listener.IeValidate;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hieppq3 on 5/6/17.
 */

public class BaseTienDoFragment extends BaseFragment implements IeSave.IeCapNhatTienDoInteractor, IeValidate.IecheckValidateTienDo {
    private static final String TAG = BaseTienDoFragment.class.getSimpleName();
    public static ArrayList<Work_ItemsEntity> workItems;
    public static ConcurrentHashMap<Long, Work_ItemsEntity> hashWorkItems = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Long, Sub_Work_ItemEntity> hashSubWorkItems = new ConcurrentHashMap<>();

    protected ConcurrentHashMap<String, String> hashPlanCodes = new ConcurrentHashMap<>();

    @Override
    public void initData() {
        super.initData();
//        if (constr_ConstructionItem.getStatus() >= 395)
//            Toast.makeText(getContext(), "Công trình đang chờ hoàn thành,
//        bạn không thể cập nhật thêm tiến độ!", Toast.LENGTH_SHORT).show();
        workItems = work_itemsControler.getItems(constr_ConstructionItem.getConstructId());
        Log.d(TAG, "initData: constr id = " + constr_ConstructionItem.getConstructId());
//        ArrayList<String> allColumn = work_itemsControler.getListColumn();
//        for (String columnName : allColumn) {
//            Log.d(TAG, "initData: column name = " + columnName);
//        }
    }


    protected void showNotSyncDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Bạn phải đồng bộ lại dữ liệu để sử dụng chức năng này!");
        builder.setCancelable(false);
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });
        builder.create().show();
    }

    @Override
    public void save() {
        boolean flagConstructions = !workItems.isEmpty();
        for (Work_ItemsEntity entity : workItems) {
//            if ((entity.getStarting_date() != null && entity.getStarting_date().length() > 0)
//                    && entity.getComplete_date() != null && entity.getComplete_date().length() > 0) {
//                entity.setStatus_id(403);
//            } else {
            if (entity.getStatus_id() != Work_ItemsEntity.STATUS_COMPLETE)
                flagConstructions = false;
//                if (entity.getStarting_date() != null && entity.getStarting_date().length() > 0) {
//                    entity.setStatus_id(402);
//                } else {
//                    entity.setStatus_id(0);
//                }
//            }
        }
        if (flagConstructions) {
            Log.e(TAG, "save hoan thanh: " + constr_ConstructionItem.getConstructId()
                    + " " + constr_ConstructionItem.getConstrType() );
            constr_ConstructionItem.setStatus(
                    constr_ConstructionItem.getConstrType() == 82 ? 396 : 395);
        } else {
            constr_ConstructionItem.setStatus(0);
        }

        ArrayList<Constr_ConstructionEntity> arr = new ArrayList<>();
        arr.add(constr_ConstructionItem);
        new Constr_ConstructionController(getContext()).updateStatus(constr_ConstructionItem);
    }

    @Override
    public void saveTienDo() {
        save();
    }

    @Override
    public boolean checkValidateTienDo() {
        return false;
    }

    public void registerListenerEventBus() {}

    public void showPreviewTienDo(BaseGponPreview mGponPrevFrag){}
}
