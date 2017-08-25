package com.viettel.database.entity;

public class Acceptance_Bts_Cat_WorkEntity {
	private long acceptance_Bts_Cat_Work_Id;
	private long supervision_Bts_Cat_Work_Id;
	private long cat_Cause_Constr_Acceptance_Id;
	private String type;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	
	public long getAcceptance_Bts_Cat_Work_Id() {
		return acceptance_Bts_Cat_Work_Id;
	}
	public void setAcceptance_Bts_Cat_Work_Id(long acceptance_Bts_Cat_Work_Id) {
		this.acceptance_Bts_Cat_Work_Id = acceptance_Bts_Cat_Work_Id;
	}
	public long getSupervision_Bts_Cat_Work_Id() {
		return supervision_Bts_Cat_Work_Id;
	}
	public void setSupervision_Bts_Cat_Work_Id(long supervision_Bts_Cat_Work_Id) {
		this.supervision_Bts_Cat_Work_Id = supervision_Bts_Cat_Work_Id;
	}
	
	public long getCat_Cause_Constr_Acceptance_Id() {
		return cat_Cause_Constr_Acceptance_Id;
	}
	public void setCat_Cause_Constr_Acceptance_Id(
			long cat_Cause_Constr_Acceptance_Id) {
		this.cat_Cause_Constr_Acceptance_Id = cat_Cause_Constr_Acceptance_Id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
