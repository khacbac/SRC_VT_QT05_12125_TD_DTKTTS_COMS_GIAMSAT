package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_Line_BG_CableEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 216521536442991651L;
	private long supervision_Line_Bg_Cable_Id;
	private long supervision_Line_Background_Id;
	private long fromDistance;
	private long toDistance;
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

	public long getSupervision_Line_Bg_Cable_Id() {
		return supervision_Line_Bg_Cable_Id;
	}

	public void setSupervision_Line_Bg_Cable_Id(
			long supervision_Line_Bg_Cable_Id) {
		this.supervision_Line_Bg_Cable_Id = supervision_Line_Bg_Cable_Id;
	}

	public long getSupervision_Line_Background_Id() {
		return supervision_Line_Background_Id;
	}

	public void setSupervision_Line_Background_Id(
			long supervision_Line_Background_Id) {
		this.supervision_Line_Background_Id = supervision_Line_Background_Id;
	}

	public long getFromDistance() {
		return fromDistance;
	}

	public void setFromDistance(long fromDistance) {
		this.fromDistance = fromDistance;
	}

	public long getToDistance() {
		return toDistance;
	}

	public void setToDistance(long toDistance) {
		this.toDistance = toDistance;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
