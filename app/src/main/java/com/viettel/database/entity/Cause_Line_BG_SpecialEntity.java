package com.viettel.database.entity;

import java.io.Serializable;

public class Cause_Line_BG_SpecialEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long cause_Line_Bg_Special_Id;
	private long special_Location_Id;
	private long cat_Cause_Constr_Unqualify_Id;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	
	public long getCause_Line_Bg_Special_Id() {
		return cause_Line_Bg_Special_Id;
	}
	public void setCause_Line_Bg_Special_Id(long cause_Line_Bg_Special_Id) {
		this.cause_Line_Bg_Special_Id = cause_Line_Bg_Special_Id;
	}
	public long getSpecial_Location_Id() {
		return special_Location_Id;
	}
	public void setSpecial_Location_Id(long special_Location_Id) {
		this.special_Location_Id = special_Location_Id;
	}
	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}
	public void setCat_Cause_Constr_Unqualify_Id(long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
	}
	public int getSync_Status() {
		return sync_Status;
	}
	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
