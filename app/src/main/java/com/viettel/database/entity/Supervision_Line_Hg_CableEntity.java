package com.viettel.database.entity;

public class Supervision_Line_Hg_CableEntity {

	private long supervision_LINE_HG_CABLE_ID;
	private long from_DISTANCE;
	private long to_DISTANCE;
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

	public Supervision_Line_Hg_CableEntity() {

	}

	public long getSupervision_LINE_HG_CABLE_ID() {
		return supervision_LINE_HG_CABLE_ID;
	}

	public void setSupervision_LINE_HG_CABLE_ID(
			long supervision_LINE_HG_CABLE_ID) {
		this.supervision_LINE_HG_CABLE_ID = supervision_LINE_HG_CABLE_ID;
	}

	public long getFrom_DISTANCE() {
		return from_DISTANCE;
	}

	public void setFrom_DISTANCE(long from_DISTANCE) {
		this.from_DISTANCE = from_DISTANCE;
	}

	public long getTo_DISTANCE() {
		return to_DISTANCE;
	}

	public void setTo_DISTANCE(long to_DISTANCE) {
		this.to_DISTANCE = to_DISTANCE;
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

	public void setSupervision_LINE_HANG_ID(long supervision_LINE_HANG_ID) {
		this.supervision_LINE_HANG_ID = supervision_LINE_HANG_ID;
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
