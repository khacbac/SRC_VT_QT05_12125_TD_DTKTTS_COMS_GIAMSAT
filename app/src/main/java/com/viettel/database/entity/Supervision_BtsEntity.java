package com.viettel.database.entity;


public class Supervision_BtsEntity {

	private Supervision_ConstrEntity supervision_ConstrEntity;
	private long supervision_Bts_Id;
	private int pillar_ANTEN_TYPE;
	private int pillar_ANTEN;
	private double pillar_HIGH;
	private int foundation_NUM;
	private int house_TYPE;
	private String house_NAME;
	private int house_GENERATOR;
	private double pillar_LONGITUDE;
	private double pillar_LATITUDE;
	private int pillar_STATUS_QUALITY;
	private int pillar_STATUS_WORKING;
	private int power_CONNECT_POINT;
	private int power_POLE_NEW_TOTAL;
	private String power_CABLE_TYPE;
	private long processId;
	private int sync_Status;
	private int isActive;
	private long employeeId;
	private int constructionType;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Supervision_BtsEntity() {
		supervision_ConstrEntity = new Supervision_ConstrEntity();
		foundation_NUM = 0;
		processId = 0;
	}

	public int getConstructionType() {
		return constructionType;
	}

	public void setConstructionType(int constructionType) {
		this.constructionType = constructionType;
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

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Supervision_ConstrEntity getSupervision_ConstrEntity() {
		return supervision_ConstrEntity;
	}

	public void setSupervision_ConstrEntity(
			Supervision_ConstrEntity supervision_ConstrEntity) {
		this.supervision_ConstrEntity = supervision_ConstrEntity;
	}

	public long getSupervision_Bts_Id() {
		return supervision_Bts_Id;
	}

	public void setSupervision_Bts_Id(long supervision_Bts_Id) {
		this.supervision_Bts_Id = supervision_Bts_Id;
	}

	public int getPillar_ANTEN_TYPE() {
		return pillar_ANTEN_TYPE;
	}

	public void setPillar_ANTEN_TYPE(int pillar_ANTEN_TYPE) {
		this.pillar_ANTEN_TYPE = pillar_ANTEN_TYPE;
	}

	public int getPillar_ANTEN() {
		return pillar_ANTEN;
	}

	public void setPillar_ANTEN(int pillar_ANTEN) {
		this.pillar_ANTEN = pillar_ANTEN;
	}

	public double getPillar_HIGH() {
		return pillar_HIGH;
	}

	public void setPillar_HIGH(double pillar_HIGH) {
		this.pillar_HIGH = pillar_HIGH;
	}

	public int getFoundation_NUM() {
		return foundation_NUM;
	}

	public void setFoundation_NUM(int foundation_NUM) {
		this.foundation_NUM = foundation_NUM;
	}

	public int getHouse_TYPE() {
		return house_TYPE;
	}

	public void setHouse_TYPE(int house_TYPE) {
		this.house_TYPE = house_TYPE;
	}

	public String getHouse_NAME() {
		return house_NAME;
	}

	public void setHouse_NAME(String house_NAME) {
		this.house_NAME = house_NAME;
	}

	public int getHouse_GENERATOR() {
		return house_GENERATOR;
	}

	public void setHouse_GENERATOR(int house_GENERATOR) {
		this.house_GENERATOR = house_GENERATOR;
	}

	public double getPillar_LONGITUDE() {
		return pillar_LONGITUDE;
	}

	public void setPillar_LONGITUDE(double pillar_LONGITUDE) {
		this.pillar_LONGITUDE = pillar_LONGITUDE;
	}

	public double getPillar_LATITUDE() {
		return pillar_LATITUDE;
	}

	public void setPillar_LATITUDE(double pillar_LATITUDE) {
		this.pillar_LATITUDE = pillar_LATITUDE;
	}

	public int getPillar_STATUS_QUALITY() {
		return pillar_STATUS_QUALITY;
	}

	public void setPillar_STATUS_QUALITY(int pillar_STATUS_QUALITY) {
		this.pillar_STATUS_QUALITY = pillar_STATUS_QUALITY;
	}

	public int getPillar_STATUS_WORKING() {
		return pillar_STATUS_WORKING;
	}

	public void setPillar_STATUS_WORKING(int pillar_STATUS_WORKING) {
		this.pillar_STATUS_WORKING = pillar_STATUS_WORKING;
	}

	public int getPower_CONNECT_POINT() {
		return power_CONNECT_POINT;
	}

	public void setPower_CONNECT_POINT(int power_CONNECT_POINT) {
		this.power_CONNECT_POINT = power_CONNECT_POINT;
	}

	public int getPower_POLE_NEW_TOTAL() {
		return power_POLE_NEW_TOTAL;
	}

	public void setPower_POLE_NEW_TOTAL(int power_POLE_NEW_TOTAL) {
		this.power_POLE_NEW_TOTAL = power_POLE_NEW_TOTAL;
	}

	public String getPower_CABLE_TYPE() {
		return power_CABLE_TYPE;
	}

	public void setPower_CABLE_TYPE(String power_CABLE_TYPE) {
		this.power_CABLE_TYPE = power_CABLE_TYPE;
	}

}
