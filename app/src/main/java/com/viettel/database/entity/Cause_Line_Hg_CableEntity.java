package com.viettel.database.entity;

import java.io.Serializable;

public class Cause_Line_Hg_CableEntity implements Serializable {
	private static final long serialVersionUID = -1987014281196430180L;
	private long cause_Line_Hg_Cable_Id;
	private long supervision_Line_Hg_Cable_Id;
	private long cat_Cause_Constr_Unqualify_Id;
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

	public long getCause_Line_Hg_Cable_Id() {
		return cause_Line_Hg_Cable_Id;
	}

	public void setCause_Line_Hg_Cable_Id(long cause_Line_Hg_Cable_Id) {
		this.cause_Line_Hg_Cable_Id = cause_Line_Hg_Cable_Id;
	}

	public long getSupervision_Line_Hg_Cable_Id() {
		return supervision_Line_Hg_Cable_Id;
	}

	public void setSupervision_Line_Hg_Cable_Id(
			long supervision_Line_Hg_Cable_Id) {
		this.supervision_Line_Hg_Cable_Id = supervision_Line_Hg_Cable_Id;
	}

	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}

	public void setCat_Cause_Constr_Unqualify_Id(
			long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
