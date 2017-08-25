package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_TankGSEntity implements Serializable {

	private static final long serialVersionUID = -2318794811947856713L;
	/* Id be */
	private long idTank = Constants.ID_ENTITY_DEFAULT;
	/* Ten be */
	private String tankName = "";
	/* So dan */
	private long numberPart = Constants.ID_ENTITY_DEFAULT;
	/* Danh gia: 1 dat, 0 khong dat */
	private int status = Constants.ID_ENTITY_DEFAULT;
	/* Dien giai */
	private String failDesc = "";
	/* Vi tri toa do */
	private Double lonLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private Double latLocation = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;

	/* Bien dung dong bo du lieu */
	private int sync_Status;
	private int isActive;
	private long processId;
	private long supervisionConstrId;

	public List<Supervision_LBG_UnqualifyItemEntity> getListAcceptance() {
		return listAcceptance;
	}

	public void setListAcceptance(
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptance) {
		this.listAcceptance = listAcceptance;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getIdTank() {
		return idTank;
	}

	public void setIdTank(long id) {
		this.idTank = id;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
	}

	public long getNumberPart() {
		return numberPart;
	}

	public void setNumberPart(long numberPart) {
		this.numberPart = numberPart;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFailDesc() {
		return failDesc;
	}

	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
	}

	public Double getLonLocation() {
		return lonLocation;
	}

	public void setLonLocation(Double lonLocation) {
		this.lonLocation = lonLocation;
	}

	public Double getLatLocation() {
		return latLocation;
	}

	public void setLatLocation(Double latLocation) {
		this.latLocation = latLocation;
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

	public int getiField() {
		return iField;
	}

	public void setiField(int iField) {
		this.iField = iField;
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
