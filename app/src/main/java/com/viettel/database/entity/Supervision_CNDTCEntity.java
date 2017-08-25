package com.viettel.database.entity;

import java.io.Serializable;
import java.util.Date;

public class Supervision_CNDTCEntity implements Serializable {
	private static final long serialVersionUID = 1050294185975395844L;
	private long supervisionConstrId;	
	private long supervisionProgressContructId;
	private Date reportDate;
	private long constructId;
	private int syncStatus;
	private long processId;
	private long employeeId;
	private int isActive;
	private int numberEmployees;
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public long getSupervisionProgressContructId() {
		return supervisionProgressContructId;
	}

	public void setSupervisionProgressContructId(long supervisionProgressContructId) {
		this.supervisionProgressContructId = supervisionProgressContructId;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public long getConstructId() {
		return constructId;
	}

	public void setConstructId(long constructId) {
		this.constructId = constructId;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the numberEmployees
	 */
	public int getNumberEmployees() {
		return numberEmployees;
	}
	/**
	 * @param numberEmployees the numberEmployees to set
	 */
	public void setNumberEmployees(int numberEmployees) {
		this.numberEmployees = numberEmployees;
	}

	

}
