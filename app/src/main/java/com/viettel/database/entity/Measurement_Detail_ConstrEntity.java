package com.viettel.database.entity;

import java.io.Serializable;

public class Measurement_Detail_ConstrEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8935567797571937128L;
	private long measurementDetailConstrId;
	private String nameComponent;
	private String longValue;
	private String widthValue;
	private String heighValue;
	private String diameter;
	private String otherValue;
	private long supervisionMeasureConstrId;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	/* Gia tri danh dau them moi hoac sua doi */
	private boolean isNew = true;
	private boolean isEdited = false;
	
	public long getMeasurementDetailConstrId() {
		return measurementDetailConstrId;
	}
	public void setMeasurementDetailConstrId(long measurementDetailConstrId) {
		this.measurementDetailConstrId = measurementDetailConstrId;
	}
	public String getNameComponent() {
		return nameComponent;
	}
	public void setNameComponent(String nameComponent) {
		this.nameComponent = nameComponent;
	}
	public String getLongValue() {
		return longValue;
	}
	public void setLongValue(String longValue) {
		this.longValue = longValue;
	}
	public String getWidthValue() {
		return widthValue;
	}
	public void setWidthValue(String widthValue) {
		this.widthValue = widthValue;
	}
	public String getHeighValue() {
		return heighValue;
	}
	public void setHeighValue(String heighValue) {
		this.heighValue = heighValue;
	}
	public String getDiameter() {
		return diameter;
	}
	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}
	public String getOtherValue() {
		return otherValue;
	}
	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}
	public long getSupervisionMeasureConstrId() {
		return supervisionMeasureConstrId;
	}
	public void setSupervisionMeasureConstrId(long supervisionMeasureConstrId) {
		this.supervisionMeasureConstrId = supervisionMeasureConstrId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	
}
