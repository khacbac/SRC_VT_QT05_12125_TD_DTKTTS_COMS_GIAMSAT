package com.viettel.database.entity;

public class Acceptance_Bts_PillarEntity {
	private long acceptance_Bts_Pillar_Id;
	private long supv_Bts_Pillar_Anten_Id;
	private long cat_Cause_Constr_Acceptance_Id;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	
	
	public long getAcceptance_Bts_Pillar_Id() {
		return acceptance_Bts_Pillar_Id;
	}
	public void setAcceptance_Bts_Pillar_Id(long acceptance_Bts_Pillar_Id) {
		this.acceptance_Bts_Pillar_Id = acceptance_Bts_Pillar_Id;
	}
	public long getSupv_Bts_Pillar_Anten_Id() {
		return supv_Bts_Pillar_Anten_Id;
	}
	public void setSupv_Bts_Pillar_Anten_Id(long supv_Bts_Pillar_Anten_Id) {
		this.supv_Bts_Pillar_Anten_Id = supv_Bts_Pillar_Anten_Id;
	}
	
	public long getCat_Cause_Constr_Acceptance_Id() {
		return cat_Cause_Constr_Acceptance_Id;
	}
	public void setCat_Cause_Constr_Acceptance_Id(
			long cat_Cause_Constr_Acceptance_Id) {
		this.cat_Cause_Constr_Acceptance_Id = cat_Cause_Constr_Acceptance_Id;
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
