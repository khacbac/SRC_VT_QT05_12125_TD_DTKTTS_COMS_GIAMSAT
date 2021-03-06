package com.viettel.database.entity;

import java.io.Serializable;

public class Cause_Line_Hg_MxEntity implements Serializable {

	private static final long serialVersionUID = -3920785860897307692L;
	private long cause_Line_Bg_Mx_Id;
	private long supervision_Line_Bg_MX_Id;
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

	public long getCause_Line_Bg_Mx_Id() {
		return cause_Line_Bg_Mx_Id;
	}

	public void setCause_Line_Bg_Mx_Id(long cause_Line_Bg_Mx_Id) {
		this.cause_Line_Bg_Mx_Id = cause_Line_Bg_Mx_Id;
	}

	public long getSupervision_Line_Bg_MX_Id() {
		return supervision_Line_Bg_MX_Id;
	}

	public void setSupervision_Line_Bg_MX_Id(long supervision_Line_Bg_MX_Id) {
		this.supervision_Line_Bg_MX_Id = supervision_Line_Bg_MX_Id;
	}

	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}

	public void setCat_Cause_Constr_Unqualify_Id(
			long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
