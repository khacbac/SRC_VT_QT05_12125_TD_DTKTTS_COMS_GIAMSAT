package com.viettel.database.entity;

import java.io.Serializable;

public class Special_LocationEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long specialLocationId;
	private int specialLocationType;
	private String supplieType;
	private String bridgeName;
	private String fromStage;
	private String toStage;
	private long supervisionLineBackgroundId;
	private int status;
	private double lonStartLocation;
	private double latStartLocation;
	private double lonEndLocation;
	private double latEndLocation;

	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getLonStartLocation() {
		return lonStartLocation;
	}

	public void setLonStartLocation(double lonStartLocation) {
		this.lonStartLocation = lonStartLocation;
	}

	public double getLatStartLocation() {
		return latStartLocation;
	}

	public void setLatStartLocation(double latStartLocation) {
		this.latStartLocation = latStartLocation;
	}

	public double getLonEndLocation() {
		return lonEndLocation;
	}

	public void setLonEndLocation(double lonEndLocation) {
		this.lonEndLocation = lonEndLocation;
	}

	public double getLatEndLocation() {
		return latEndLocation;
	}

	public void setLatEndLocation(double latEndLocation) {
		this.latEndLocation = latEndLocation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	private boolean isNew = true;
	private boolean isEdited = false;

	public long getSpecialLocationId() {
		return specialLocationId;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public int getSync_Status() {
		return sync_Status;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
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

	public void setSpecialLocationId(long specialLocationId) {
		this.specialLocationId = specialLocationId;
	}

	public int getSpecialLocationType() {
		return specialLocationType;
	}

	public void setSpecialLocationType(int specialLocationType) {
		this.specialLocationType = specialLocationType;
	}

	public String getSupplieType() {
		return supplieType;
	}

	public void setSupplieType(String supplieType) {
		this.supplieType = supplieType;
	}

	public String getBridgeName() {
		return bridgeName;
	}

	public void setBridgeName(String bridgeName) {
		this.bridgeName = bridgeName;
	}

	public String getFromStage() {
		return fromStage;
	}

	public void setFromStage(String fromStage) {
		this.fromStage = fromStage;
	}

	public String getToStage() {
		return toStage;
	}

	public void setToStage(String toStage) {
		this.toStage = toStage;
	}

	public long getSupervisionLineBackgroundId() {
		return supervisionLineBackgroundId;
	}

	public void setSupervisionLineBackgroundId(long supervisionLineBackgroundId) {
		this.supervisionLineBackgroundId = supervisionLineBackgroundId;
	}

}
