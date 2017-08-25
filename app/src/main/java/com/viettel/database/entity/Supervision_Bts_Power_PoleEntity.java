package com.viettel.database.entity;

public class Supervision_Bts_Power_PoleEntity {
	public long supervision_BTS_POWER_POLE_ID;
	public String power_POLE_NAME;
	public int status;
	public String fail_DESC;
	private long supervision_BTS_ID;
	private long processId;
	private int sync_Status;
	private int isActive;
	private long employeeId;
	private long supervisionConstrId;
	private long statusApprove;
	
	

	public long getStatusApprove() {
		return statusApprove;
	}

	public void setStatusApprove(long statusApprove) {
		this.statusApprove = statusApprove;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getSupervision_BTS_ID() {
		return supervision_BTS_ID;
	}

	public void setSupervision_BTS_ID(long supervision_BTS_ID) {
		this.supervision_BTS_ID = supervision_BTS_ID;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
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

	public Supervision_Bts_Power_PoleEntity() {

	}

	public long getSupervision_BTS_POWER_POLE_ID() {
		return supervision_BTS_POWER_POLE_ID;
	}

	public void setSupervision_BTS_POWER_POLE_ID(
			long supervision_BTS_POWER_POLE_ID) {
		this.supervision_BTS_POWER_POLE_ID = supervision_BTS_POWER_POLE_ID;
	}

	public String getPower_POLE_NAME() {
		return power_POLE_NAME;
	}

	public void setPower_POLE_NAME(String power_POLE_NAME) {
		this.power_POLE_NAME = power_POLE_NAME;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFail_DESC() {
		return fail_DESC;
	}

	public void setFail_DESC(String fail_DESC) {
		this.fail_DESC = fail_DESC;
	}

}
