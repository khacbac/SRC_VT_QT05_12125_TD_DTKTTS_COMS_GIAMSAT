package com.viettel.database.entity;

import java.util.Date;

public class Supervision_ProgressEntity {
	private long supervisionProgressId;
	private int type;
	private String subType;
	private Date completeDate;
	private long supervisionConstrId;
	private int syncStatus;
	private long processId;
	private long employeeId;
	
	public long getSupervisionProgressId() {
		return supervisionProgressId;
	}
	public void setSupervisionProgressId(long supervisionProgressId) {
		this.supervisionProgressId = supervisionProgressId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public int getSyncStatus() {
		return syncStatus;
	}
	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}
	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}
	public int getSync_Status() {
		return syncStatus;
	}
	public void setSync_Status(int syncStatus) {
		this.syncStatus = syncStatus;
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
