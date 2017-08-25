package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Special_LocationGSEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43820419200963530L;

	/* Id loai cong */
	private long idSpecial = Constants.ID_ENTITY_DEFAULT;
	private long supervision_Line_BgId = Constants.ID_ENTITY_DEFAULT;
	private String specialName;
	
	/* Danh gia: 1 dat, 0 khong dat */
	private int status = Constants.ID_ENTITY_DEFAULT;
	// diem dau
	private Double lonStartLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private Double latStartLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	// diem cuoi
	private Double lonEndLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private Double latEndLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;

	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	/* Dung danh dau truong bat popup sua du lieu */
	private int idField = Constants.ID_ENTITY_DEFAULT;
	private int sync_Status;
	private int isActive;
	private long processId;
	
	public List<Supervision_LBG_UnqualifyItemEntity> getListAcceptance() {
		return listAcceptance;
	}
	public void setListAcceptance(
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptance) {
		this.listAcceptance = listAcceptance;
	}
	public String getSpecialName() {
		return specialName;
	}
	public void setSpecialName(String specialName) {
		this.specialName = specialName;
	}
	public long getIdSpecial() {
		return idSpecial;
	}
	public void setIdSpecial(long idSpecial) {
		this.idSpecial = idSpecial;
	}
	public long getSupervision_Line_BgId() {
		return supervision_Line_BgId;
	}
	public void setSupervision_Line_BgId(long supervision_Line_BgId) {
		this.supervision_Line_BgId = supervision_Line_BgId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Double getLonStartLocation() {
		return lonStartLocation;
	}
	public void setLonStartLocation(Double lonStartLocation) {
		this.lonStartLocation = lonStartLocation;
	}
	public Double getLatStartLocation() {
		return latStartLocation;
	}
	public void setLatStartLocation(Double latStartLocation) {
		this.latStartLocation = latStartLocation;
	}
	public Double getLonEndLocation() {
		return lonEndLocation;
	}
	public void setLonEndLocation(Double lonEndLocation) {
		this.lonEndLocation = lonEndLocation;
	}
	public Double getLatEndLocation() {
		return latEndLocation;
	}
	public void setLatEndLocation(Double latEndLocation) {
		this.latEndLocation = latEndLocation;
	}
	public List<Supervision_LBG_UnqualifyItemEntity> getListCauseUniQualify() {
		return listCauseUniQualify;
	}
	public void setListCauseUniQualify(
			List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify) {
		this.listCauseUniQualify = listCauseUniQualify;
	}
	public boolean isNew() {
		return isNew;
	}
	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}
	public boolean isEdited() {
		return isEdited;
	}
	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}
	public int getIdField() {
		return idField;
	}
	public void setIdField(int idField) {
		this.idField = idField;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
