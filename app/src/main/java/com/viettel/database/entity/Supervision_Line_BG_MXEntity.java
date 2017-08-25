package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_Line_BG_MXEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6265616193597778889L;
	private long supervision_Line_Bg_Mx_Id;
	private long supervision_Line_Background_Id;
	private String mxName;
	private Double longItude;
	private Double latItude;
	private int status;
	private String fail_Desc;
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

	public long getSupervision_Line_Bg_Mx_Id() {
		return supervision_Line_Bg_Mx_Id;
	}

	public void setSupervision_Line_Bg_Mx_Id(long supervision_Line_Bg_Mx_Id) {
		this.supervision_Line_Bg_Mx_Id = supervision_Line_Bg_Mx_Id;
	}

	public long getSupervision_Line_Background_Id() {
		return supervision_Line_Background_Id;
	}

	public void setSupervision_Line_Background_Id(
			long supervision_Line_Background_Id) {
		this.supervision_Line_Background_Id = supervision_Line_Background_Id;
	}

	public String getMxName() {
		return mxName;
	}

	public void setMxName(String mxName) {
		this.mxName = mxName;
	}

	public Double getLongItude() {
		return longItude;
	}

	public void setLongItude(Double longItude) {
		this.longItude = longItude;
	}

	public Double getLatItude() {
		return latItude;
	}

	public void setLatItude(Double latItude) {
		this.latItude = latItude;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFail_Desc() {
		return fail_Desc;
	}

	public void setFail_Desc(String fail_Desc) {
		this.fail_Desc = fail_Desc;
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
