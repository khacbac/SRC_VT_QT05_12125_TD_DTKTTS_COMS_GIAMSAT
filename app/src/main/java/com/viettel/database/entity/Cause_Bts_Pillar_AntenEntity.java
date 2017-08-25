package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class Cause_Bts_Pillar_AntenEntity {

	private long cause_BTS_PILLAR_ANTEN_ID = Constants.ID_ENTITY_DEFAULT;
	private Supervision_Bts_Pillar_AntenEntity supervision_Bts_Pillar_AntenEntity;
	private List<Cat_Cause_Constr_UnQualifyEntity> list_Cat_Cause_Constr_UnQualifyEntity;
	private List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;
	private long employeeId;
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

	public Cause_Bts_Pillar_AntenEntity() {
		supervision_Bts_Pillar_AntenEntity = new Supervision_Bts_Pillar_AntenEntity();
		list_Cat_Cause_Constr_UnQualifyEntity = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getCause_BTS_PILLAR_ANTEN_ID() {
		return cause_BTS_PILLAR_ANTEN_ID;
	}

	public void setCause_BTS_PILLAR_ANTEN_ID(long cause_BTS_PILLAR_ANTEN_ID) {
		this.cause_BTS_PILLAR_ANTEN_ID = cause_BTS_PILLAR_ANTEN_ID;
	}

	public Supervision_Bts_Pillar_AntenEntity getSupervision_Bts_Pillar_AntenEntity() {
		return supervision_Bts_Pillar_AntenEntity;
	}

	public void setSupervision_Bts_Pillar_AntenEntity(
			Supervision_Bts_Pillar_AntenEntity supervision_Bts_Pillar_AntenEntity) {
		this.supervision_Bts_Pillar_AntenEntity = supervision_Bts_Pillar_AntenEntity;
	}

	public List<Cat_Cause_Constr_UnQualifyEntity> getList_Cat_Cause_Constr_UnQualifyEntity() {
		return list_Cat_Cause_Constr_UnQualifyEntity;
	}

	public void setList_Cat_Cause_Constr_UnQualifyEntity(
			List<Cat_Cause_Constr_UnQualifyEntity> list_Cat_Cause_Constr_UnQualifyEntity) {
		this.list_Cat_Cause_Constr_UnQualifyEntity = list_Cat_Cause_Constr_UnQualifyEntity;
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

}
