package com.viettel.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_MXEntity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long SUPERVISION_TRUNK_MX_ID;
	private long SUPERVISION_BRCD_ID;
	
	private Double longItude = -1.0;
	private Double latItude = -1.0;
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
	public long getSUPERVISION_TRUNK_MX_ID() {
		return SUPERVISION_TRUNK_MX_ID;
	}
	public void setSUPERVISION_TRUNK_MX_ID(long sUPERVISION_TRUNK_MX_ID) {
		SUPERVISION_TRUNK_MX_ID = sUPERVISION_TRUNK_MX_ID;
	}
	public long getSUPERVISION_BRCD_ID() {
		return SUPERVISION_BRCD_ID;
	}
	public void setSUPERVISION_BRCD_ID(long sUPERVISION_BRCD_ID) {
		SUPERVISION_BRCD_ID = sUPERVISION_BRCD_ID;
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
