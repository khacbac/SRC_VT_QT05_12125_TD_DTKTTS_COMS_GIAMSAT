package com.viettel.database.entity;

/**
 * Created by hieppq3 on 4/26/17.
 */

public class Constr_Work_LogsEntity extends BaseEntity {
    private long constr_work_logs_id;
    private String log_date;
    private String work_content;
    private String created_date;
    private int status_ca;
    private int is_active;
    private long created_user_id;
    private String addition_change_arise;
    private String constructor_comments;
    private String monitor_comments;
    private String approval_date;
    private int estimate_work_item_id;
    private int A_monitor_id;
    private int B_monitor_id;
    private long construct_id;
    private int cat_cause_not_work_id;
    private int is_work;

    private int cat_Weather_id;

    public int getCat_Weather_id() {
        return cat_Weather_id;
    }

    public void setCat_Weather_id(int cat_Weather_id) {
        this.cat_Weather_id = cat_Weather_id;
    }

    public long getConstr_work_logs_id() {
        return constr_work_logs_id;
    }

    public void setConstr_work_logs_id(long constr_work_logs_id) {
        this.constr_work_logs_id = constr_work_logs_id;
    }

    public String getLog_date() {
        return log_date;
    }

    public void setLog_date(String log_date) {
        this.log_date = log_date;
    }

    public String getWork_content() {
        return work_content;
    }

    public void setWork_content(String work_content) {
        this.work_content = work_content;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getStatus_ca() {
        return status_ca;
    }

    public void setStatus_ca(int status_ca) {
        this.status_ca = status_ca;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public long getCreated_user_id() {
        return created_user_id;
    }

    public void setCreated_user_id(long created_user_id) {
        this.created_user_id = created_user_id;
    }

    public String getAddition_change_arise() {
        return addition_change_arise;
    }

    public void setAddition_change_arise(String addition_change_arise) {
        this.addition_change_arise = addition_change_arise;
    }

    public String getConstructor_comments() {
        return constructor_comments;
    }

    public void setConstructor_comments(String constructor_comments) {
        this.constructor_comments = constructor_comments;
    }

    public String getMonitor_comments() {
        return monitor_comments;
    }

    public void setMonitor_comments(String monitor_comments) {
        this.monitor_comments = monitor_comments;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(String approval_date) {
        this.approval_date = approval_date;
    }

    public int getEstimate_work_item_id() {
        return estimate_work_item_id;
    }

    public void setEstimate_work_item_id(int estimate_work_item_id) {
        this.estimate_work_item_id = estimate_work_item_id;
    }

    public int getA_monitor_id() {
        return A_monitor_id;
    }

    public void setA_monitor_id(int a_monitor_id) {
        A_monitor_id = a_monitor_id;
    }

    public int getB_monitor_id() {
        return B_monitor_id;
    }

    public void setB_monitor_id(int b_monitor_id) {
        B_monitor_id = b_monitor_id;
    }

    public long getConstruct_id() {
        return construct_id;
    }

    public void setConstruct_id(long construct_id) {
        this.construct_id = construct_id;
    }

    public int getCat_cause_not_work_id() {
        return cat_cause_not_work_id;
    }

    public void setCat_cause_not_work_id(int cat_cause_not_work_id) {
        this.cat_cause_not_work_id = cat_cause_not_work_id;
    }

    public int getIs_work() {
        return is_work;
    }

    public void setIs_work(int is_work) {
        this.is_work = is_work;
    }
}
