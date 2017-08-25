package com.viettel.database.entity;

import android.util.Log;

import com.viettel.gsct.GSCTUtils;

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
    private String work_item_address;
    private int constr_form; //Hinh thuc thuc hien(1-tu thuc hien 2-chi dinh thau 3-dau thau)
    private int super_form ; //Hinh thuc giam sat(1-tu thuc hien 2-chi dinh thau 3-dau thau)
    private int constructor_id;
    private int supervisor_id;
    private String update_date = "";
    private String starting_date = "";
    private String complete_date = "";
    private long engineering_cost;
    private long device_cost;
    private long other_cost;
    private long contingency_cost;
    private long constr_id;
    private String contract_id;
    private int status;
    private String real_complete_date;
    private long invest_project_id;
    private long total_real_cost;
    private long liquidate_cost;
    private String liquidate_date;
    private int start_by;  //Bat dau hang muc tren: 0:PC, 1:PDA
    private long update_status_by;
    private long accept_by;
    private long norms_id;
    private long status_id;
    private String work_item_type_name;
    private long constr_type;
    private int cable_type;
    private int total_work;
    private int current_work;
    private int material_type;
    private long constr_design_id;
    private long province_id;
    private String idTram; // id tram
    private long item_type_id;
    private String accept_note_code;


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

    public String getWork_item_address() {
        return work_item_address;
    }

    public void setWork_item_address(String work_item_address) {
        this.work_item_address = work_item_address;
    }

    public int getConstr_form() {
        return constr_form;
    }

    public void setConstr_form(int constr_form) {
        this.constr_form = constr_form;
    }

    public int getSuper_form() {
        return super_form;
    }

    public void setSuper_form(int super_form) {
        this.super_form = super_form;
    }

    public int getConstructor_id() {
        return constructor_id;
    }

    public void setConstructor_id(int constructor_id) {
        this.constructor_id = constructor_id;
    }

    public int getSupervisor_id() {
        return supervisor_id;
    }

    public void setSupervisor_id(int supervisor_id) {
        this.supervisor_id = supervisor_id;
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

    public boolean hasCompletedDate() {
        return  complete_date != null && complete_date.length() > 0 ;
    }

    public void setComplete_date(String complete_date) {
        this.complete_date = complete_date;
    }

    public long getEngineering_cost() {
        return engineering_cost;
    }

    public void setEngineering_cost(long engineering_cost) {
        this.engineering_cost = engineering_cost;
    }

    public long getDevice_cost() {
        return device_cost;
    }

    public void setDevice_cost(long device_cost) {
        this.device_cost = device_cost;
    }

    public long getOther_cost() {
        return other_cost;
    }

    public void setOther_cost(long other_cost) {
        this.other_cost = other_cost;
    }

    public long getContingency_cost() {
        return contingency_cost;
    }

    public void setContingency_cost(long contingency_cost) {
        this.contingency_cost = contingency_cost;
    }

    public long getConstr_id() {
        return constr_id;
    }

    public void setConstr_id(long constr_id) {
        this.constr_id = constr_id;
    }

    public String getContract_id() {
        return contract_id;
    }

    public void setContract_id(String contract_id) {
        this.contract_id = contract_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReal_complete_date() {
        return real_complete_date;
    }

    public void setReal_complete_date(String real_complete_date) {
        this.real_complete_date = real_complete_date;
    }

    public long getInvest_project_id() {
        return invest_project_id;
    }

    public void setInvest_project_id(long invest_project_id) {
        this.invest_project_id = invest_project_id;
    }

    public long getTotal_real_cost() {
        return total_real_cost;
    }

    public void setTotal_real_cost(long total_real_cost) {
        this.total_real_cost = total_real_cost;
    }

    public long getLiquidate_cost() {
        return liquidate_cost;
    }

    public void setLiquidate_cost(long liquidate_cost) {
        this.liquidate_cost = liquidate_cost;
    }

    public String getLiquidate_date() {
        return liquidate_date;
    }

    public void setLiquidate_date(String liquidate_date) {
        this.liquidate_date = liquidate_date;
    }

    public int getStart_by() {
        return start_by;
    }

    public void setStart_by(int start_by) {
        this.start_by = start_by;
    }

    public long getUpdate_status_by() {
        return update_status_by;
    }

    public void setUpdate_status_by(long update_status_by) {
        this.update_status_by = update_status_by;
    }

    public long getAccept_by() {
        return accept_by;
    }

    public void setAccept_by(long accept_by) {
        this.accept_by = accept_by;
    }

    public long getNorms_id() {
        return norms_id;
    }

    public void setNorms_id(long norms_id) {
        this.norms_id = norms_id;
    }

    public long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(long status_id) {
        this.status_id = status_id;
    }

    public String getWork_item_type_name() {
        return work_item_type_name;
    }

    public void setWork_item_type_name(String work_item_type_name) {
        this.work_item_type_name = work_item_type_name;
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

    public int getTotal_work() {
        return total_work;
    }

    public void setTotal_work(int total_work) {
        this.total_work = total_work;
    }

    public int getCurrent_work() {
        return current_work;
    }

    public void setCurrent_work(int current_work) {
        this.current_work = current_work;
    }

    public int getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(int material_type) {
        this.material_type = material_type;
    }

    public long getConstr_design_id() {
        return constr_design_id;
    }

    public void setConstr_design_id(long constr_design_id) {
        this.constr_design_id = constr_design_id;
    }

    public long getProvince_id() {
        return province_id;
    }

    public void setProvince_id(long province_id) {
        this.province_id = province_id;
    }

    public String getIdTram() {
        return idTram;
    }

    public void setIdTram(String idTram) {
        this.idTram = idTram;
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

    private static final String TAG = "Work_ItemsEntity";
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
