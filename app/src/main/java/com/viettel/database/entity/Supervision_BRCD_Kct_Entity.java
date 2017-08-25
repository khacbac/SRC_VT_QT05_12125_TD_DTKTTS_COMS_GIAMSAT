package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Kct_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long SUPERVISION_TRUNK_CABLE_ID;
	private boolean check = false;
	private String FAIL_DESC = "";
	private int length = 0;
	private String cable_code = "";
	private int position = 0;
	private int cable_type = 0;
	private long supervition_brcd_id;
	private int sync_Status;
	private int isActive;
	private long processId;
	private int idField = Constants.ID_ENTITY_DEFAULT;
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

	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = false;
	private long employeeId;

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getCable_code() {
		return cable_code;
	}

	public void setCable_code(String cable_code) {
		this.cable_code = cable_code;
	}

	public int getCable_type() {
		return cable_type;
	}

	public void setCable_type(int cable_type) {
		this.cable_type = cable_type;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public long getSUPERVISION_TRUNK_CABLE_ID() {
		return SUPERVISION_TRUNK_CABLE_ID;
	}

	public void setSUPERVISION_TRUNK_CABLE_ID(long sUPERVISION_TRUNK_CABLE_ID) {
		SUPERVISION_TRUNK_CABLE_ID = sUPERVISION_TRUNK_CABLE_ID;
	}


	public int getIdField() {
		return idField;
	}

	public void setIdField(int idField) {
		this.idField = idField;
	}

	public String getFAIL_DESC() {
		return FAIL_DESC;
	}

	public void setFAIL_DESC(String fAIL_DESC) {
		FAIL_DESC = fAIL_DESC;
	}

	public long getSupervition_brcd_id() {
		return supervition_brcd_id;
	}

	public void setSupervition_brcd_id(long supervition_brcd_id) {
		this.supervition_brcd_id = supervition_brcd_id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getIdPipe() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getFromTank() {
		// TODO Auto-generated method stub
		return null;
	}

}
