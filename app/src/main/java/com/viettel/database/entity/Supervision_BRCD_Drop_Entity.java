package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Drop_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_drop_id;
	private long SUPERVISION_BRCD_ID;

	private String drop_code = "";
	private long supervition_branch_design_id;
	private String name;

	private int sync_Status;
	private int isActive;
	private int cab_type;

	private long processId;
	private long supervisionConstrId;

	private boolean isNew = false;
	private int iField = Constants.ID_ENTITY_DEFAULT;
	private long employeeId;
	private int position = 0;

	List<Supervision_LBG_UnqualifyItemEntity> listCauseUniQualify = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptance = new ArrayList<Supervision_LBG_UnqualifyItemEntity>();

	public long getSupervition_branch_design_id() {
		return supervition_branch_design_id;
	}

	public void setSupervition_branch_design_id(
			long supervition_branch_design_id) {
		this.supervition_branch_design_id = supervition_branch_design_id;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getCab_type() {
		return cab_type;
	}

	public void setCab_type(int cab_type) {
		this.cab_type = cab_type;
	}

	public int getiField() {
		return iField;
	}

	public void setiField(int iField) {
		this.iField = iField;
	}

	public long getSUPERVISION_BRCD_ID() {
		return SUPERVISION_BRCD_ID;
	}

	public void setSUPERVISION_BRCD_ID(long sUPERVISION_BRCD_ID) {
		SUPERVISION_BRCD_ID = sUPERVISION_BRCD_ID;
	}

	public long getSupervition_branch_drop_id() {
		return supervition_branch_drop_id;
	}

	public void setSupervition_branch_drop_id(long supervition_branch_drop_id) {
		this.supervition_branch_drop_id = supervition_branch_drop_id;
	}

	public String getDrop_code() {
		return drop_code;
	}

	public void setDrop_code(String drop_code) {
		this.drop_code = drop_code;
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
