package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Tn_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_cabinet_id;
	private long SUPERVISION_BRCD_ID;
	
	private String cabinet_Name;
	private double longitude = -1;
	private double latitude = -1;
	private String cable_code = "";
	
	private int status = -1;
	private String Desc = "";
	private int sync_Status;
	private int isActive;
	private long processId;
	private long supervisionConstrId;
	
	private boolean isNew = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;
	private long employeeId;
	
	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	
	public String getCabinet_Name() {
		return cabinet_Name;
	}
	public void setCabinet_Name(String cabinet_Name) {
		this.cabinet_Name = cabinet_Name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
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
	

	public String getCable_code() {
		return cable_code;
	}
	public void setCable_code(String cable_code) {
		this.cable_code = cable_code;
	}
	public long getSupervition_branch_cabinet_id() {
		return supervition_branch_cabinet_id;
	}
	public void setSupervition_branch_cabinet_id(long supervition_branch_cabinet_id) {
		this.supervition_branch_cabinet_id = supervition_branch_cabinet_id;
	}
	public long getSUPERVISION_BRCD_ID() {
		return SUPERVISION_BRCD_ID;
	}
	public void setSUPERVISION_BRCD_ID(long sUPERVISION_BRCD_ID) {
		SUPERVISION_BRCD_ID = sUPERVISION_BRCD_ID;
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
