package com.viettel.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Sub_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long SUPERVISION_TRUNK_SUB_ID;
	
	private int num_device ;
	private String product_id;
	private int status;
	private String Desc;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;
	
	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
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
	
	public long getSUPERVISION_TRUNK_SUB_ID() {
		return SUPERVISION_TRUNK_SUB_ID;
	}
	public void setSUPERVISION_TRUNK_SUB_ID(long sUPERVISION_TRUNK_SUB_ID) {
		SUPERVISION_TRUNK_SUB_ID = sUPERVISION_TRUNK_SUB_ID;
	}
	public int getNum_device() {
		return num_device;
	}
	public void setNum_device(int num_device) {
		this.num_device = num_device;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
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
	public void setDesc(String Desc) {
		this.Desc = Desc;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
