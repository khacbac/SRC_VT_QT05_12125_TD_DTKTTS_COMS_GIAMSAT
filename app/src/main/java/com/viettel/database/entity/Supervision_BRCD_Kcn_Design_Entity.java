package com.viettel.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Kcn_Design_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_cable_id;
	private long supervition_branch_design_id;
	private int pillar_Old_Number;
	private int pillar_New_Number;
	private int cable_Drop_Num_Two;
	private int cable_Drop_Num_Four;
	private int cable_Drop_Num_Eight;
	private int cable_Drop_Num_Twelve;
	private int num_Box_Two;
	private int num_Box_Four;
	private int num_Box_Eight;
	private int num_Box_Twelve;
	private int length;
	private int cable_Type;
	private int status;
	private String Desc;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDesc() {
		return Desc;
	}

	public void setDesc(String desc) {
		Desc = desc;
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


	public int getCable_Type() {
		return cable_Type;
	}

	public void setCable_Type(int cable_Type) {
		this.cable_Type = cable_Type;
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

	public long getSupervition_branch_cable_id() {
		return supervition_branch_cable_id;
	}

	public void setSupervition_branch_cable_id(long supervition_branch_cable_id) {
		this.supervition_branch_cable_id = supervition_branch_cable_id;
	}

	public long getSupervition_branch_design_id() {
		return supervition_branch_design_id;
	}

	public void setSupervition_branch_design_id(
			long supervition_branch_design_id) {
		this.supervition_branch_design_id = supervition_branch_design_id;
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

	public int getCable_Drop_Num_Two() {
		return cable_Drop_Num_Two;
	}

	public void setCable_Drop_Num_Two(int cable_Drop_Num_Two) {
		this.cable_Drop_Num_Two = cable_Drop_Num_Two;
	}

	public int getCable_Drop_Num_Four() {
		return cable_Drop_Num_Four;
	}

	public void setCable_Drop_Num_Four(int cable_Drop_Num_Four) {
		this.cable_Drop_Num_Four = cable_Drop_Num_Four;
	}

	public int getCable_Drop_Num_Eight() {
		return cable_Drop_Num_Eight;
	}

	public void setCable_Drop_Num_Eight(int cable_Drop_Num_Eight) {
		this.cable_Drop_Num_Eight = cable_Drop_Num_Eight;
	}

	public int getCable_Drop_Num_Twelve() {
		return cable_Drop_Num_Twelve;
	}

	public void setCable_Drop_Num_Twelve(int cable_Drop_Num_Twelve) {
		this.cable_Drop_Num_Twelve = cable_Drop_Num_Twelve;
	}

	public int getNum_Box_Two() {
		return num_Box_Two;
	}

	public void setNum_Box_Two(int num_Box_Two) {
		this.num_Box_Two = num_Box_Two;
	}

	public int getNum_Box_Four() {
		return num_Box_Four;
	}

	public void setNum_Box_Four(int num_Box_Four) {
		this.num_Box_Four = num_Box_Four;
	}

	public int getNum_Box_Eight() {
		return num_Box_Eight;
	}

	public void setNum_Box_Eight(int num_Box_Eight) {
		this.num_Box_Eight = num_Box_Eight;
	}

	public int getNum_Box_Twelve() {
		return num_Box_Twelve;
	}

	public void setNum_Box_Twelve(int num_Box_Twelve) {
		this.num_Box_Twelve = num_Box_Twelve;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
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
