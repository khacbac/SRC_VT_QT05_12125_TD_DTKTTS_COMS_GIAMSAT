package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_GiamSat_Kcn_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_cable_id;
	private long SUPERVISION_BRANCH_CABLE_FIBER_ID;
	
	private int start_section = -1;
	private int end_section= -1;
	
	
	private int status = -1;
	private String Desc="";
	private int sync_Status;
	private int isActive;
	private long processId;
	private long supervisionConstrId;
	
	private boolean isNew = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;
	private long employeeId;
	
	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public int getiField() {
		return iField;
	}
	public void setiField(int iField) {
		this.iField = iField;
	}
	public long getSupervition_branch_cable_id() {
		return supervition_branch_cable_id;
	}
	public void setSupervition_branch_cable_id(long supervition_branch_cable_id) {
		this.supervition_branch_cable_id = supervition_branch_cable_id;
	}
	
	
	public long getSUPERVISION_BRANCH_CABLE_FIBER_ID() {
		return SUPERVISION_BRANCH_CABLE_FIBER_ID;
	}
	public void setSUPERVISION_BRANCH_CABLE_FIBER_ID(
			long sUPERVISION_BRANCH_CABLE_FIBER_ID) {
		SUPERVISION_BRANCH_CABLE_FIBER_ID = sUPERVISION_BRANCH_CABLE_FIBER_ID;
	}
	
	
	
	public int getStart_section() {
		return start_section;
	}
	public void setStart_section(int start_section) {
		this.start_section = start_section;
	}
	public int getEnd_section() {
		return end_section;
	}
	public void setEnd_section(int end_section) {
		this.end_section = end_section;
	}
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
	public long getIdPipe() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	
}
