package com.viettel.database.entity;

import java.io.Serializable;
import java.util.Date;

public class Cat_Employee_Plan_Entity implements Serializable {
	/**
	 * @author hungkm
	 */
	private static final long serialVersionUID = 4428168125145370856L;
	private long planID;
	private long employeeID;
	private long processId;
	private int isActive;
	private int sync_Status;
	private String planText;
	private Date fromDate;
	private Date toDate;
	private boolean isCheck;

	public long getPlanID() {
		return planID;
	}

	public void setPlanID(long planID) {
		this.planID = planID;
	}

	public long getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getSync_Status() {
		return sync_Status;
	}

	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}

	public String getPlanText() {
		return planText;
	}

	public void setPlanText(String planText) {
		this.planText = planText;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

}
