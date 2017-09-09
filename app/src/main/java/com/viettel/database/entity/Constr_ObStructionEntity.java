package com.viettel.database.entity;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Constr_ObStructionEntity implements Serializable {
	private static final long serialVersionUID = 1804807171511630316L ;
	private final String TAG = this.getClass().getSimpleName();

	/* ID bang */
	private long ConstrObStructionId;
	/* Id loai danh muc vuong */
	private long ConstrObStructionTypeId;
	/* Ngay update */
	private Date updateDate;
	/* Update by */
	private String updatedBy;
	
	private String description;
	
	private long constructId;
	
	private long prevStatusId;
	
	private Date startDate;
	
	private Date endDate;
	
	private String solvingMethod;
	
	private Date createdDate;

	private long Cat_Employee_id;

	private String updateDescription;
	
	private long supervisionConstrId;

	private long constr_work_logs_id;
	
	private int stt;
	
	private String type;
	private String result;
	private int syncStatus;
	private long processId;
	private long employeeId;
	private int isActive;
	
	private Supv_Constr_Attach_FileEntity attachFile = null;
	private Supv_Constr_Attach_FileEntity newAttachFile = null;
	private List<Supv_Constr_Attach_FileEntity> lstAttachFile = null;
	private List<Supv_Constr_Attach_FileEntity> lstNewAttachFile = null;
	
	public Constr_ObStructionEntity() {
		attachFile = new Supv_Constr_Attach_FileEntity();
		newAttachFile = new Supv_Constr_Attach_FileEntity();
		lstAttachFile = new ArrayList<Supv_Constr_Attach_FileEntity>();
		lstNewAttachFile = new ArrayList<Supv_Constr_Attach_FileEntity>();
	}

	public long getConstr_work_logs_id() {
		return constr_work_logs_id;
	}

	public void setConstr_work_logs_id(long constr_work_logs_id) {
		this.constr_work_logs_id = constr_work_logs_id;
	}

	public long getCat_Employee_id() {
		return Cat_Employee_id;
	}

	public void setCat_Employee_id(long cat_Employee_id) {
		Cat_Employee_id = cat_Employee_id;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getConstrObStructionId() {
		return ConstrObStructionId;
	}

	public void setConstrObStructionId(long constrObStructionId) {
		ConstrObStructionId = constrObStructionId;
	}

	public long getConstrObStructionTypeId() {
		Log.d(TAG,"ConstrObStruction Type id = " + ConstrObStructionTypeId);
		return ConstrObStructionTypeId;
	}

	public void setConstrObStructionTypeId(long constrObStructionTypeId) {
		ConstrObStructionTypeId = constrObStructionTypeId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getConstructId() {
		return constructId;
	}

	public void setConstructId(long constructId) {
		this.constructId = constructId;
	}

	public long getPrevStatusId() {
		return prevStatusId;
	}

	public void setPrevStatusId(long prevStatusId) {
		this.prevStatusId = prevStatusId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSolvingMethod() {
		return solvingMethod;
	}

	public void setSolvingMethod(String solvingMethod) {
		this.solvingMethod = solvingMethod;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdateDescription() {
		return updateDescription;
	}

	public void setUpdateDescription(String updateDescription) {
		this.updateDescription = updateDescription;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Supv_Constr_Attach_FileEntity getNewAttachFile() {
		return newAttachFile;
	}

	public void setNewAttachFile(Supv_Constr_Attach_FileEntity newAttachFile) {
		this.newAttachFile = newAttachFile;
	}

	public Supv_Constr_Attach_FileEntity getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(Supv_Constr_Attach_FileEntity attachFile) {
		this.attachFile = attachFile;
	}

	public List<Supv_Constr_Attach_FileEntity> getLstAttachFile() {
		return lstAttachFile;
	}

	public void setLstAttachFile(
			List<Supv_Constr_Attach_FileEntity> lstAttachFile) {
		this.lstAttachFile = lstAttachFile;
	}

	public List<Supv_Constr_Attach_FileEntity> getLstNewAttachFile() {
		return lstNewAttachFile;
	}

	public void setLstNewAttachFile(
			List<Supv_Constr_Attach_FileEntity> lstNewAttachFile) {
		this.lstNewAttachFile = lstNewAttachFile;
	}

}
