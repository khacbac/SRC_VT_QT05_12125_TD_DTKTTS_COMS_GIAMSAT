package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_MXGSEntity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	/* Id loai cong */
	private long idMX = Constants.ID_ENTITY_DEFAULT;
	private String mxName = "";
	private Double longItude = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	private Double latItude = Constants.ID_DOUBLE_ENTITY_DEFAULT;
	/* Danh gia: 1 dat, 0 khong dat */
	private int status = Constants.ID_ENTITY_DEFAULT;
	/* Dien giai */
	private String failDesc = "";
	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	/* Dung danh dau truong bat popup sua du lieu */
	private int idField = Constants.ID_ENTITY_DEFAULT;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getIdMX() {
		return idMX;
	}

	public void setIdMX(long idMX) {
		this.idMX = idMX;
	}

	public String getMxName() {
		return mxName;
	}

	public void setMxName(String mxName) {
		this.mxName = mxName;
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

	public String getFailDesc() {
		return failDesc;
	}

	public void setFailDesc(String failDesc) {
		this.failDesc = failDesc;
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
