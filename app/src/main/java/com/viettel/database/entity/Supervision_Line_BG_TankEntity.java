package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_Line_BG_TankEntity implements Serializable {

	private static final long serialVersionUID = 1695389459512931337L;
	private long supervision_Line_Bg_Tank_Id;
	private long supervision_Line_Bg_Tank_Server_Id;
	private long supervision_Line_Background_Id;
	private String tank_Name;
	private double lonLocation;
	private double latLocation;
	private long num_Part;
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

	public long getSupervision_Line_Bg_Tank_Id() {
		return supervision_Line_Bg_Tank_Id;
	}

	public void setSupervision_Line_Bg_Tank_Id(long supervision_Line_Bg_Tank_Id) {
		this.supervision_Line_Bg_Tank_Id = supervision_Line_Bg_Tank_Id;
	}

	public long getSupervision_Line_Bg_Tank_Server_Id() {
		return supervision_Line_Bg_Tank_Server_Id;
	}

	public void setSupervision_Line_Bg_Tank_Server_Id(
			long supervision_Line_Bg_Tank_Server_Id) {
		this.supervision_Line_Bg_Tank_Server_Id = supervision_Line_Bg_Tank_Server_Id;
	}

	public long getSupervision_Line_Background_Id() {
		return supervision_Line_Background_Id;
	}

	public void setSupervision_Line_Background_Id(
			long supervision_Line_Background_Id) {
		this.supervision_Line_Background_Id = supervision_Line_Background_Id;
	}

	public String getTank_Name() {
		return tank_Name;
	}

	public void setTank_Name(String tank_Name) {
		this.tank_Name = tank_Name;
	}

	public double getLonLocation() {
		return lonLocation;
	}

	public void setLonLocation(double lonLocation) {
		this.lonLocation = lonLocation;
	}

	public double getLatLocation() {
		return latLocation;
	}

	public void setLatLocation(double latLocation) {
		this.latLocation = latLocation;
	}

	public long getNum_Part() {
		return num_Part;
	}

	public void setNum_Part(long num_Part) {
		this.num_Part = num_Part;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}
}
