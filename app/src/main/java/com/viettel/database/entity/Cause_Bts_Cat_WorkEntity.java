package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Cat_WorkEntity {
	private long cause_Bts_Cat_Work_Id;
	private int stt;
	private String tenHangMuc;
	private String workCode;
	private List<Cat_Cause_Constr_UnQualifyEntity> listConstrUnqualifyEntity;
	private Supervision_Bts_Cat_WorkEntity bts_Cat_WorkEntity;
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

	public long getProcessId() {
		return processId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
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

	private List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify;
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

	public List<Supervision_LBG_UnqualifyItemEntity> getListAcceptance() {
		return listAcceptance;
	}

	public void setListAcceptance(
			List<Supervision_LBG_UnqualifyItemEntity> listAcceptance) {
		this.listAcceptance = listAcceptance;
	}

	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;

	public Cause_Bts_Cat_WorkEntity() {
		bts_Cat_WorkEntity = new Supervision_Bts_Cat_WorkEntity();
		listConstrUnqualifyEntity = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
		listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	}

	public String getTenHangMuc() {
		return tenHangMuc;
	}

	public String getWorkCode() {
		return workCode;
	}

	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}

	public void setTenHangMuc(String tenHangMuc) {
		this.tenHangMuc = tenHangMuc;
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

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public void setiField(int iField) {
		this.iField = iField;
	}

	public void setBts_Cat_WorkEntity(
			Supervision_Bts_Cat_WorkEntity bts_Cat_WorkEntity) {
		this.bts_Cat_WorkEntity = bts_Cat_WorkEntity;
	}

	public long getCause_Bts_Cat_Work_Id() {
		return cause_Bts_Cat_Work_Id;
	}

	public void setCause_Bts_Cat_Work_Id(long cause_Bts_Cat_Work_Id) {
		this.cause_Bts_Cat_Work_Id = cause_Bts_Cat_Work_Id;
	}

	public List<Cat_Cause_Constr_UnQualifyEntity> getListConstrUnqualifyEntity() {
		return listConstrUnqualifyEntity;
	}

	public void setListConstrUnqualifyEntity(
			List<Cat_Cause_Constr_UnQualifyEntity> listConstrUnqualifyEntity) {
		this.listConstrUnqualifyEntity = listConstrUnqualifyEntity;
	}

	public Supervision_Bts_Cat_WorkEntity getBts_Cat_WorkEntity() {
		return bts_Cat_WorkEntity;
	}

}
