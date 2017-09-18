package com.viettel.database.entity;

public class Supervision_Bts_Pillar_AntenEntity {
	private long supervision_BTS_PILLAR_ANTEN_ID;
	private String FOUNDATION_NAME;
	private double longitude;
	private double latitude;
	private int status;
	private String dimension_Design;
	private String dimension_Real;
	private String fail_DESC;
	private long supervision_BTS_ID;
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

	public Supervision_Bts_Pillar_AntenEntity() {
		isActive = 1;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getProcessId() {
		return processId;
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

	public long getSupervision_BTS_PILLAR_ANTEN_ID() {
		return supervision_BTS_PILLAR_ANTEN_ID;
	}

	public void setSupervision_BTS_PILLAR_ANTEN_ID(
			long supervision_BTS_PILLAR_ANTEN_ID) {
		this.supervision_BTS_PILLAR_ANTEN_ID = supervision_BTS_PILLAR_ANTEN_ID;
	}

	public String getFOUNDATION_NAME() {
		return FOUNDATION_NAME;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getDimension_Design() {
		return dimension_Design;
	}

	public void setDimension_Design(String dimension_Design) {
		this.dimension_Design = dimension_Design;
	}

	public String getDimension_Real() {
		return dimension_Real;
	}

	public void setDimension_Real(String dimension_Real) {
		this.dimension_Real = dimension_Real;
	}

	public void setFOUNDATION_NAME(String fOUNDATION_NAME) {
		FOUNDATION_NAME = fOUNDATION_NAME;
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

	public String getFail_DESC() {
		return ""+fail_DESC;
	}

	public void setFail_DESC(String fail_DESC) {
		this.fail_DESC = fail_DESC;
	}

	public long getSupervision_BTS_ID() {
		return supervision_BTS_ID;
	}

	public void setSupervision_BTS_ID(long supervision_BTS_ID) {
		this.supervision_BTS_ID = supervision_BTS_ID;
	}

}
