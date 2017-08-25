package com.viettel.database.entity;

import android.util.Log;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Sub_Work_ItemEntity extends BaseEntity{
    private long id;
    private long cat_sub_work_item_id;
    private String finishDate;
    private long work_item_id;
    private String code;

    private long creatorId;

    public Sub_Work_ItemEntity() {

    }

    public Sub_Work_ItemEntity(long cat_sub_work_item_id) {
        this.cat_sub_work_item_id = cat_sub_work_item_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCat_sub_work_item_id() {
        return cat_sub_work_item_id;
    }

    public void setCat_sub_work_item_id(long cat_sub_work_item_id) {
        this.cat_sub_work_item_id = cat_sub_work_item_id;
    }

    public String getFinishDate() {
        return finishDate == null ? "" : finishDate;
    }

    private static final String TAG = "Sub_Work_ItemEntity";
    public void setFinishDate(String finishDate) {
//        Log.e(TAG, "setFinishDate: "+ id + " " + finishDate+".");
        this.finishDate = finishDate;
    }

    public long getWork_item_id() {
        return work_item_id;
    }

    public void setWork_item_id(long work_item_id) {
        this.work_item_id = work_item_id;
    }

    public boolean isCompleted() {
        return finishDate != null && finishDate.length() > 0;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

}
