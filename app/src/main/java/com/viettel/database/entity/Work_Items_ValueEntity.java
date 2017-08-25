package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Work_Items_ValueEntity {
    private long work_item_value_id;
    private String update_date;
    private String value;
    private long work_items_id;
    private long cat_work_item_id;
    private int update_week;
    private long construct_id;
    private int update_month;
    private int update_year;
    private int work_item_id;

    public long getWork_item_value_id() {
        return work_item_value_id;
    }

    public void setWork_item_value_id(long work_item_value_id) {
        this.work_item_value_id = work_item_value_id;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getWork_items_id() {
        return work_items_id;
    }

    public void setWork_items_id(long work_items_id) {
        this.work_items_id = work_items_id;
    }

    public long getCat_work_item_id() {
        return cat_work_item_id;
    }

    public void setCat_work_item_id(long cat_work_item_id) {
        this.cat_work_item_id = cat_work_item_id;
    }

    public int getUpdate_week() {
        return update_week;
    }

    public void setUpdate_week(int update_week) {
        this.update_week = update_week;
    }

    public long getConstruct_id() {
        return construct_id;
    }

    public void setConstruct_id(long construct_id) {
        this.construct_id = construct_id;
    }

    public int getUpdate_month() {
        return update_month;
    }

    public void setUpdate_month(int update_month) {
        this.update_month = update_month;
    }

    public int getUpdate_year() {
        return update_year;
    }

    public void setUpdate_year(int update_year) {
        this.update_year = update_year;
    }

    public int getWork_item_id() {
        return work_item_id;
    }

    public void setWork_item_id(int work_item_id) {
        this.work_item_id = work_item_id;
    }
}
