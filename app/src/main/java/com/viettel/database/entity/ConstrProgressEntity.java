package com.viettel.database.entity;

public class ConstrProgressEntity {

	private long constrProgressId;
	private long catConstructId;
	private long constrProgressConstructId;
	//KienPV add new 12/09/2016
	private int constrProgressNumberEmployees;//   CONSTR_PROGRESS_NUMBER_EMPLOYEES
	public int getConstrProgressNumberEmployees() {
		return constrProgressNumberEmployees;
	}
	public void setConstrProgressNumberEmployees(int constrProgressNumberEmployees) {
		this.constrProgressNumberEmployees = constrProgressNumberEmployees;
	}
	//---
	private long supervisionConstrId;
	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}
	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
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
	private int syncStatus;
	private long processId;
	private long employeeId;
	private int isActive;
	
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public long getConstrProgressId() {
		return constrProgressId;
	}
	public void setConstrProgressId(long constrProgressId) {
		this.constrProgressId = constrProgressId;
	}
	public long getCatConstructId() {
		return catConstructId;
	}
	public void setCatConstructId(long catConstructId) {
		this.catConstructId = catConstructId;
	}
	public long getConstrProgressConstructId() {
		return constrProgressConstructId;
	}
	public void setConstrProgressConstructId(long constrProgressConstructId) {
		this.constrProgressConstructId = constrProgressConstructId;
	}
	
}
