package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Sub_Work_ItemEntity {

    private  long id ;
    private long cat_work_item_type_id;
    private String name;
    private String code;
    private String description;
    private int is_active;
    private int orderNum;

    private String unitName;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCat_work_item_type_id() {
        return cat_work_item_type_id;
    }

    public void setCat_work_item_type_id(long cat_work_item_type_id) {
        this.cat_work_item_type_id = cat_work_item_type_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
