package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Sub_Work_Item_ValueEntity extends BaseEntity{
    private long id;
    private String value_2;
    private String added_date;
    private String expectation_value;
    private String note;
    private int team_number;
    private int total_progress;
    private double value;
    private long cat_sub_work_item_id;
    private long work_item_id;
    private double value_item;
    private long constr_node_id;

    public Sub_Work_Item_ValueEntity(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValue_2() {
        return value_2;
    }

    public void setValue_2(String value_2) {
        this.value_2 = value_2;
    }

    public String getAdded_date() {
        return added_date;
    }

    public void setAdded_date(String added_date) {
        this.added_date = added_date;
    }

    public boolean hadAddedDate() {
        return added_date != null && added_date.length() > 0;
    }

    public String getExpectation_value() {
        return expectation_value;
    }

    public void setExpectation_value(String expectation_value) {
        this.expectation_value = expectation_value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTeam_number() {
        return team_number;
    }

    public void setTeam_number(int team_number) {
        this.team_number = team_number;
    }

    public int getTotal_progress() {
        return total_progress;
    }

    public void setTotal_progress(int total_progress) {
        this.total_progress = total_progress;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public long getCat_sub_work_item_id() {
        return cat_sub_work_item_id;
    }

    public void setCat_sub_work_item_id(long cat_sub_work_item_id) {
        this.cat_sub_work_item_id = cat_sub_work_item_id;
    }

    public long getWork_item_id() {
        return work_item_id;
    }

    public void setWork_item_id(long work_item_id) {
        this.work_item_id = work_item_id;
    }

    public double getValue_item() {
        return value_item;
    }

    public void setValue_item(double value_item) {
        this.value_item = value_item;
    }

    public long getConstr_node_id() {
        return constr_node_id;
    }

    public void setConstr_node_id(long constr_node_id) {
        this.constr_node_id = constr_node_id;
    }
}
