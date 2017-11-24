package com.viettel.database.entity;

import com.viettel.gsct.utils.GSCTUtils;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Work_ItemsEntity extends BaseEntity{

    public static final int STATUS_COMPLETE = 403;
    public static final int STATUS_WORKING = 402;
    public static final int STATUS_NOT_START = 0;
    private long id;
    private String work_item_code;
    private String work_item_name;
    private String update_date = "";
    private String starting_date = "";
    private String complete_date = "";
    private long constr_id;
    private int status;
    private long status_id;
    private long constr_type;
    private int cable_type;
    private long item_type_id;
    private String accept_note_code;
    private String recentCheck;


    private boolean isChange = false;

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }

    private ArrayList<Sub_Work_ItemEntity> subWorkItems = new ArrayList<>();

    public void addSubWorkItem(Sub_Work_ItemEntity subItem) {
        subWorkItems.add(subItem);
    }

    public ArrayList<Sub_Work_ItemEntity> getSubWorkItems() {
        return subWorkItems;
    }

    public Work_ItemsEntity(long constructId, long cat_work_item_id) {
        this.constr_id = constructId;
        this.item_type_id = cat_work_item_id;
    }

    public Work_ItemsEntity() {
    }

    public String getAccept_note_code() {
        return accept_note_code;
    }

    public void setAccept_note_code(String accept_note_code) {
        this.accept_note_code = accept_note_code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWork_item_code() {
        return work_item_code;
    }

    public void setWork_item_code(String work_item_code) {
        this.work_item_code = work_item_code;
    }

    public String getWork_item_name() {
        return work_item_name;
    }

    /**
     * Công trình có sẵn
     * @return
     */
    public boolean isReady() {
        String name =formatString(work_item_name);
        return  name.toLowerCase().contains("co san");
    }

    public  String formatString(String text) {
        if (text == null) return "";
        text = text.trim().replaceAll("Đ", "D").replaceAll("đ", "d");
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[^\\p{ASCII}]", "");
        return text;
    }

    public void setWork_item_name(String work_item_name) {
        this.work_item_name = work_item_name;
    }

    public String getStarting_date() {
        return starting_date == null ? "" : starting_date;
    }

    public void setStarting_date(String starting_date) {
        this.starting_date = starting_date;
    }

    public String getComplete_date() {
        return complete_date == null ? "" : complete_date;
    }

    public boolean isCompleted() {
        return  hasCompletedDate() && !complete_date.equals(GSCTUtils.getDateNow());
    }

    public boolean hasStartedDate() {
        return starting_date != null && starting_date.length() > 0;
    }

    public boolean hasCompletedDate() {
        return  complete_date != null && complete_date.length() > 0 ;
    }

    public void setComplete_date(String complete_date) {
        this.complete_date = complete_date;
    }

    public long getConstr_id() {
        return constr_id;
    }

    public void setConstr_id(long constr_id) {
        this.constr_id = constr_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public long getConstr_type() {
        return constr_type;
    }

    public void setConstr_type(long constr_type) {
        this.constr_type = constr_type;
    }

    public int getCable_type() {
        return cable_type;
    }

    public void setCable_type(int cable_type) {
        this.cable_type = cable_type;
    }

    public long getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(long item_type_id) {
        this.item_type_id = item_type_id;
    }


    public String getUpdate_date() {
        return update_date == null ? "" : update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getRecentCheck() {
        return recentCheck;
    }

    public void setRecentCheck(String recentCheck) {
        this.recentCheck = recentCheck;
    }

    public void updateDate() {
        if (status_id == STATUS_COMPLETE) {
            if (complete_date == null || complete_date.trim().length() == 0)
                complete_date = GSCTUtils.getDateNow();
            if (starting_date == null || starting_date.trim().length() == 0)
                starting_date = GSCTUtils.getDateNow();
        } else if (status_id == STATUS_WORKING) {
            complete_date = "";
            if (starting_date == null || starting_date.trim().length() == 0)
                starting_date = GSCTUtils.getDateNow();
        } else {
            complete_date = "";
            starting_date = "";
        }
    }

}
