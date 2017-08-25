package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Cause_Not_WorkEntity {

    private int cat_cause_not_work_id;
    private String name;
    private String code;
    private int is_active;

    public int getCat_cause_not_work_id() {
        return cat_cause_not_work_id;
    }

    public void setCat_cause_not_work_id(int cat_cause_not_work_id) {
        this.cat_cause_not_work_id = cat_cause_not_work_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
