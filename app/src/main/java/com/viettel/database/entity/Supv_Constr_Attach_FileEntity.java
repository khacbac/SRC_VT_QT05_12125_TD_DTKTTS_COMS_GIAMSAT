package com.viettel.database.entity;

import java.io.Serializable;
import java.util.Date;

public class Supv_Constr_Attach_FileEntity implements Serializable {
	private static final long serialVersionUID = 8826171991728761752L;
	private long supv_Constr_Attach_file_Id = -1;
	private String file_Name = "";
	private String file_Path = "";
	private String table_Name = "";
	private long object_Id = -1;
	private Date created_Date = null;
	private double longitude = -1.0;
	private double latitude = -1.0;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;
	private boolean requestNext = false;
	private int numberUpload;
	// trang thai phe duyet
	private int statusApprove;
	// y kien cho trang thai phe duyet
	private String resonDeny = "";
	
	
	public String getResonDeny() {
		return resonDeny;
	}

	public void setResonDeny(String resonDeny) {
		this.resonDeny = resonDeny;
	}

	public int getStatusApprove() {
		return statusApprove;
	}

	public void setStatusApprove(int statusApprove) {
		this.statusApprove = statusApprove;
	}

	public int getNumberUpload() {
		return numberUpload;
	}

	public void setNumberUpload(int numberUpload) {
		this.numberUpload = numberUpload;
	}

	public boolean isRequestNext() {
		return requestNext;
	}

	public void setRequestNext(boolean requestNext) {
		this.requestNext = requestNext;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getSupv_Constr_Attach_file_Id() {
		return supv_Constr_Attach_file_Id;
	}

	public void setSupv_Constr_Attach_file_Id(long supv_Constr_Attach_file_Id) {
		this.supv_Constr_Attach_file_Id = supv_Constr_Attach_file_Id;
	}

	public String getFile_Name() {
		return file_Name;
	}

	public void setFile_Name(String file_Name) {
		this.file_Name = file_Name;
	}

	public String getFile_Path() {
		return file_Path;
	}

	public void setFile_Path(String file_Path) {
		this.file_Path = file_Path;
	}

	public String getTable_Name() {
		return table_Name;
	}

	public void setTable_Name(String table_Name) {
		this.table_Name = table_Name;
	}

	public long getObject_Id() {
		return object_Id;
	}

	public void setObject_Id(long object_Id) {
		this.object_Id = object_Id;
	}

	public Date getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Date created_Date) {
		this.created_Date = created_Date;
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

}
