package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_BRCD_Kct_design_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long SUPERVISION_TRUNK_DESIGN_ID;
	
	private int trunk_cable_length  = -1;
	public long getSUPERVISION_TRUNK_DESIGN_ID() {
		return SUPERVISION_TRUNK_DESIGN_ID;
	}
	public void setSUPERVISION_TRUNK_DESIGN_ID(long sUPERVISION_TRUNK_DESIGN_ID) {
		SUPERVISION_TRUNK_DESIGN_ID = sUPERVISION_TRUNK_DESIGN_ID;
	}
	public int getTrunk_cable_length() {
		return trunk_cable_length;
	}
	public void setTrunk_cable_length(int trunk_cable_length) {
		this.trunk_cable_length = trunk_cable_length;
	}
	public long getSupervition_brcd_id() {
		return supervition_brcd_id;
	}
	public void setSupervition_brcd_id(long supervition_brcd_id) {
		this.supervition_brcd_id = supervition_brcd_id;
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
	private long supervition_brcd_id;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	

	
}
