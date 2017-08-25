package com.viettel.database.entity;

public class Supervision_Bts_MeasureEntity {
	public long supervision_BTS_MEASURE_ID;
	public String measure_NAME;
	public float measure_VALUE;
	public String measure_MACHINE_TYPE;
	public String measure_MACHINE_SERIAL;
	public String measure_PERSON;
	public String measure_PERSON_MOBILE;
	public String measure_GROUP;
	public int measure_Status;
	public long supervision_Bts_Id;
	public long cat_Supv_Constr_Measure_Id;
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

	public int getMeasure_Status() {
		return measure_Status;
	}

	public long getProcessId() {
		return processId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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

	public void setMeasure_Status(int measure_Status) {
		this.measure_Status = measure_Status;
	}

	public long getSupervision_Bts_Id() {
		return supervision_Bts_Id;
	}

	public void setSupervision_Bts_Id(long supervision_Bts_Id) {
		this.supervision_Bts_Id = supervision_Bts_Id;
	}

	public long getCat_Supv_Constr_Measure_Id() {
		return cat_Supv_Constr_Measure_Id;
	}

	public void setCat_Supv_Constr_Measure_Id(long cat_Supv_Constr_Measure_Id) {
		this.cat_Supv_Constr_Measure_Id = cat_Supv_Constr_Measure_Id;
	}

	public Supervision_Bts_MeasureEntity() {

	}

	public long getSupervision_BTS_MEASURE_ID() {
		return supervision_BTS_MEASURE_ID;
	}

	public void setSupervision_BTS_MEASURE_ID(long supervision_BTS_MEASURE_ID) {
		this.supervision_BTS_MEASURE_ID = supervision_BTS_MEASURE_ID;
	}

	public String getMeasure_NAME() {
		return measure_NAME;
	}

	public void setMeasure_NAME(String measure_NAME) {
		this.measure_NAME = measure_NAME;
	}

	public float getMeasure_VALUE() {
		return measure_VALUE;
	}

	public void setMeasure_VALUE(float measure_VALUE) {
		this.measure_VALUE = measure_VALUE;
	}

	public String getMeasure_MACHINE_TYPE() {
		return measure_MACHINE_TYPE;
	}

	public void setMeasure_MACHINE_TYPE(String measure_MACHINE_TYPE) {
		this.measure_MACHINE_TYPE = measure_MACHINE_TYPE;
	}

	public String getMeasure_MACHINE_SERIAL() {
		return measure_MACHINE_SERIAL;
	}

	public void setMeasure_MACHINE_SERIAL(String measure_MACHINE_SERIAL) {
		this.measure_MACHINE_SERIAL = measure_MACHINE_SERIAL;
	}

	public String getMeasure_PERSON() {
		return measure_PERSON;
	}

	public void setMeasure_PERSON(String measure_PERSON) {
		this.measure_PERSON = measure_PERSON;
	}

	public String getMeasure_PERSON_MOBILE() {
		return measure_PERSON_MOBILE;
	}

	public void setMeasure_PERSON_MOBILE(String measure_PERSON_MOBILE) {
		this.measure_PERSON_MOBILE = measure_PERSON_MOBILE;
	}

	public String getMeasure_GROUP() {
		return measure_GROUP;
	}

	public void setMeasure_GROUP(String measure_GROUP) {
		this.measure_GROUP = measure_GROUP;
	}

}
