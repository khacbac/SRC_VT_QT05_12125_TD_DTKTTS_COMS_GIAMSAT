package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_PipeGSEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8995961285728453747L;
	/* Id loai cong */
	private long idPipe = Constants.ID_ENTITY_DEFAULT;
	/* tu be */
	private String fromTank = "";
	private long fromDistance = Constants.ID_ENTITY_DEFAULT;
	/* den be */
	private String toTank = "";
	private long toDistance = Constants.ID_ENTITY_DEFAULT;
	/* Danh gia: 1 dat, 0 khong dat */
	private int status = Constants.ID_ENTITY_DEFAULT;
	/* Dien giai */
	private String failDesc = "";
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
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Supervision_Line_BG_PipeGSEntity() {
		this.idPipe = Constants.ID_ENTITY_DEFAULT;
		this.fromDistance = Constants.ID_ENTITY_DEFAULT;
		this.toDistance = Constants.ID_ENTITY_DEFAULT;
		this.status = Constants.ID_ENTITY_DEFAULT;
		this.idField = Constants.ID_ENTITY_DEFAULT;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getListAcceptance() {
		return listAcceptance;
	}

	public void setListAcceptance(
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptance) {
		this.listAcceptance = listAcceptance;
	}

	public long getIdPipe() {
		return idPipe;
	}

	public void setIdPipe(long idPipe) {
		this.idPipe = idPipe;
	}

	public String getFromTank() {
		return fromTank;
	}

	public void setFromTank(String fromTank) {
		this.fromTank = fromTank;
	}

	public long getFromDistance() {
		return fromDistance;
	}

	public void setFromDistance(long fromDistance) {
		this.fromDistance = fromDistance;
	}

	public String getToTank() {
		return toTank;
	}

	public void setToTank(String toTank) {
		this.toTank = toTank;
	}

	public long getToDistance() {
		return toDistance;
	}

	public void setToDistance(long toDistance) {
		this.toDistance = toDistance;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public int getIdField() {
		return idField;
	}

	public void setIdField(int idField) {
		this.idField = idField;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
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

}
