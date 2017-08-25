package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Constr_Team_ProgressEntity extends BaseEntity {

    private long constr_team_progress_id;
    private int num_of_team;
    private String created_date;
    private long creator_id;
    private long cat_constr_team_id;
    private long construct_id;
    private long constr_work_logs_id;


    public long getConstr_team_progress_id() {
        return constr_team_progress_id;
    }

    public void setConstr_team_progress_id(long constr_team_progress_id) {
        this.constr_team_progress_id = constr_team_progress_id;
    }

    public int getNum_of_team() {
        return num_of_team;
    }

    public void setNum_of_team(int num_of_team) {
        this.num_of_team = num_of_team;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

    public long getCat_constr_team_id() {
        return cat_constr_team_id;
    }

    public void setCat_constr_team_id(long cat_constr_team_id) {
        this.cat_constr_team_id = cat_constr_team_id;
    }

    public long getConstruct_id() {
        return construct_id;
    }

    public void setConstruct_id(long construct_id) {
        this.construct_id = construct_id;
    }

    public long getConstr_work_logs_id() {
        return constr_work_logs_id;
    }

    public void setConstr_work_logs_id(long constr_work_logs_id) {
        this.constr_work_logs_id = constr_work_logs_id;
    }
}
