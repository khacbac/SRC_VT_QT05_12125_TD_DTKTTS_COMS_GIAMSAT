package com.viettel.database.entity;

public class Acceptance_Line_Bg_PipeEntity {
	private long acceptance_Line_Bg_Pipe_Id;
	private long supervision_Line_Bg_Pipe_Id;
	private long cat_Cause_Constr_Acceptance_Id;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	public long getAcceptance_Line_Bg_Pipe_Id() {
		return acceptance_Line_Bg_Pipe_Id;
	}
	public void setAcceptance_Line_Bg_Pipe_Id(long acceptance_Line_Bg_Pipe_Id) {
		this.acceptance_Line_Bg_Pipe_Id = acceptance_Line_Bg_Pipe_Id;
	}
	public long getSupervision_Line_Bg_Pipe_Id() {
		return supervision_Line_Bg_Pipe_Id;
	}
	public void setSupervision_Line_Bg_Pipe_Id(long supervision_Line_Bg_Pipe_Id) {
		this.supervision_Line_Bg_Pipe_Id = supervision_Line_Bg_Pipe_Id;
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
