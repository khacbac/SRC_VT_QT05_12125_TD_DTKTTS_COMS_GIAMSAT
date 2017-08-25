package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Work_Item_TypesEntity {
    private long item_type_id;
    private String item_type_name;
    private String description;
    private int is_active;
    private String code ;
    private long constr_type_id;
    private String temp_group;
    private int parent_id;
    private String unitName;
    private int orderNum;

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public long getItem_type_id() {
        return item_type_id;
    }

    public void setItem_type_id(long item_type_id) {
        this.item_type_id = item_type_id;
    }

    public String getItem_type_name() {
        return item_type_name;
    }

    public void setItem_type_name(String item_type_name) {
        this.item_type_name = item_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getConstr_type_id() {
        return constr_type_id;
    }

    public void setConstr_type_id(long constr_type_id) {
        this.constr_type_id = constr_type_id;
    }

    public String getTemp_group() {
        return temp_group;
    }

    public void setTemp_group(String temp_group) {
        this.temp_group = temp_group;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
