package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_Ttb_Entity implements Serializable {

	private static final long serialVersionUID = -2250749004245738084L;
	private long supervition_branch_box_id;
	private long SUPERVISION_BRCD_ID;
	private long supervition_branch_design_id;
	private String Box_Name;
	private int cab_type;
	private double longitude = -1;
	private double latitude = -1;

	private int sosoicapdrop;
	private String Box_code = "";
	private int sotuthuebao;
	private int position = 0;
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

	public long getSupervition_branch_design_id() {
		return supervition_branch_design_id;
	}

	public void setSupervition_branch_design_id(
			long supervition_branch_design_id) {
		this.supervition_branch_design_id = supervition_branch_design_id;
	}

	public int getSosoicapdrop() {
		return sosoicapdrop;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setSosoicapdrop(int sosoicapdrop) {
		this.sosoicapdrop = sosoicapdrop;
	}

	public int getSotuthuebao() {
		return sotuthuebao;
	}

	public int getCab_type() {
		return cab_type;
	}


	public String getBox_code() {
		return Box_code;
	}

	public void setBox_code(String box_code) {
		Box_code = box_code;
	}

	public void setCab_type(int cab_type) {
		this.cab_type = cab_type;
	}

	public void setSotuthuebao(int sotuthuebao) {
		this.sotuthuebao = sotuthuebao;
	}

	public long getSupervition_branch_box_id() {
		return supervition_branch_box_id;
	}

	public void setSupervition_branch_box_id(long supervition_branch_box_id) {
		this.supervition_branch_box_id = supervition_branch_box_id;
	}

	public long getSUPERVISION_BRCD_ID() {
		return SUPERVISION_BRCD_ID;
	}

	public void setSUPERVISION_BRCD_ID(long sUPERVISION_BRCD_ID) {
		SUPERVISION_BRCD_ID = sUPERVISION_BRCD_ID;
	}

	public String getBox_Name() {
		return Box_Name;
	}

	public void setBox_Name(String box_Name) {
		Box_Name = box_Name;
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

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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
