package com.viettel.database.entity;

public class Supervision_Line_Hg_PillarEntity {

	private long supervision_LINE_HG_PILLAR_ID;
	private String pillar_NAME;
	private int status;
	private String fail_DESC;
	private long supervision_LINE_HANG_ID;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Supervision_Line_Hg_PillarEntity() {

	}

	public long getSupervision_LINE_HG_PILLAR_ID() {
		return supervision_LINE_HG_PILLAR_ID;
	}

	public void setSupervision_LINE_HG_PILLAR_ID(
			long supervision_LINE_HG_PILLAR_ID) {
		this.supervision_LINE_HG_PILLAR_ID = supervision_LINE_HG_PILLAR_ID;
	}

	public String getPillar_NAME() {
		return pillar_NAME;
	}

	public void setPillar_NAME(String pillar_NAME) {
		this.pillar_NAME = pillar_NAME;
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

	public long getSupervision_LINE_HANG_ID() {
		return supervision_LINE_HANG_ID;
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

	public void setSupervision_LINE_HANG_ID(long supervision_LINE_HANG_ID) {
		this.supervision_LINE_HANG_ID = supervision_LINE_HANG_ID;
	}

}
