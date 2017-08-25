package com.viettel.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Drop_Design_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_drop_id;
	private long supervition_branch_drop_design_id;
	private int cable_Type;
	private int pillar_Old_Number;
	private int pillar_New_Number;
	private int cable_length;

	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

	public long getSupervition_branch_drop_id() {
		return supervition_branch_drop_id;
	}

	public void setSupervition_branch_drop_id(long supervition_branch_drop_id) {
		this.supervition_branch_drop_id = supervition_branch_drop_id;
	}

	public long getSupervition_branch_drop_design_id() {
		return supervition_branch_drop_design_id;
	}

	public void setSupervition_branch_drop_design_id(
			long supervition_branch_drop_design_id) {
		this.supervition_branch_drop_design_id = supervition_branch_drop_design_id;
	}

	public int getCable_Type() {
		return cable_Type;
	}

	public void setCable_Type(int cable_Type) {
		this.cable_Type = cable_Type;
	}

	public int getPillar_Old_Number() {
		return pillar_Old_Number;
	}

	public void setPillar_Old_Number(int pillar_Old_Number) {
		this.pillar_Old_Number = pillar_Old_Number;
	}

	public int getPillar_New_Number() {
		return pillar_New_Number;
	}

	public void setPillar_New_Number(int pillar_New_Number) {
		this.pillar_New_Number = pillar_New_Number;
	}


	public int getCable_length() {
		return cable_length;
	}

	public void setCable_length(int cable_length) {
		this.cable_length = cable_length;
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

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getListCauseUniQualify() {
		return listCauseUniQualify;
	}

	public void setListCauseUniQualify(
			List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify) {
		this.listCauseUniQualify = listCauseUniQualify;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getListAcceptance() {
		return listAcceptance;
	}

	public void setListAcceptance(
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptance) {
		this.listAcceptance = listAcceptance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
