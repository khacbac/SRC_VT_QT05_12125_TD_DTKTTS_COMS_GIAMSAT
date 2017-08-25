package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Cat_Constr_TeamEntity {

    private long cat_constr_team_id;
    private String name;
    private String code;
    private long constr_type;
    private int is_active;

    public long getCat_constr_team_id() {
        return cat_constr_team_id;
    }

    public void setCat_constr_team_id(long cat_constr_team_id) {
        this.cat_constr_team_id = cat_constr_team_id;
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

    public long getConstr_type() {
        return constr_type;
    }

    public void setConstr_type(long constr_type) {
        this.constr_type = constr_type;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }
}
