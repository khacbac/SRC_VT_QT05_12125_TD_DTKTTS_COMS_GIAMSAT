package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Power_PoleEntity {
	private long cause_Bts_Power_Pole_Id;
	private List<Cat_Cause_Constr_UnQualifyEntity> listConstrUnqualifyEntity;
	private Supervision_Bts_Power_PoleEntity bts_PowerPoleEntity;

	private List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify;

	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;

	private long processId;
	private int sync_Status;
	private int isActive;
	private long employeeId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Cause_Bts_Power_PoleEntity() {
		bts_PowerPoleEntity = new Supervision_Bts_Power_PoleEntity();
		listConstrUnqualifyEntity = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
		listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getCause_Bts_Power_Pole_Id() {
		return cause_Bts_Power_Pole_Id;
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

	public void setCause_Bts_Power_Pole_Id(long cause_Bts_Power_Pole_Id) {
		this.cause_Bts_Power_Pole_Id = cause_Bts_Power_Pole_Id;
	}

	public List<Cat_Cause_Constr_UnQualifyEntity> getListConstrUnqualifyEntity() {
		return listConstrUnqualifyEntity;
	}

	public void setListConstrUnqualifyEntity(
			List<Cat_Cause_Constr_UnQualifyEntity> listConstrUnqualifyEntity) {
		this.listConstrUnqualifyEntity = listConstrUnqualifyEntity;
	}

	public Supervision_Bts_Power_PoleEntity getBts_PowerPoleEntity() {
		return bts_PowerPoleEntity;
	}

	public void setBts_PowerPoleEntity(
			Supervision_Bts_Power_PoleEntity bts_PowerPoleEntity) {
		this.bts_PowerPoleEntity = bts_PowerPoleEntity;
	}

	public List<Supervision_LBG_UnqualifyItemEntity> getListCauseUniQualify() {
		return listCauseUniQualify;
	}

	public void setListCauseUniQualify(
			List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify) {
		this.listCauseUniQualify = listCauseUniQualify;
	}

}
