package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_Line_HangEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2785240711868026773L;
	private long supervision_Line_Hang_Id;
	private long supervision_Constr_Id;
	private long pillar_New_Total;
	private int pillar_New_Type;
	private long pillar_New_High;
	private double pillar_New_Long;
	private long pillar_Old_Total;
	private double pillar_Old_Long;
	private long cable_Range;
	private int cable_Type;
	private long mx_Total;
	private double long_Total;
	private String measure_Person;
	private String measure_Person_Mobile;
	private String measure_Group;
	private String measure_Machine_Type;
	private String measure_Machine_Serial;
	private int measure_Status = -1;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getSupervision_Line_Hang_Id() {
		return supervision_Line_Hang_Id;
	}

	public void setSupervision_Line_Hang_Id(long supervision_Line_Hang_Id) {
		this.supervision_Line_Hang_Id = supervision_Line_Hang_Id;
	}

	public long getSupervision_Constr_Id() {
		return supervision_Constr_Id;
	}

	public void setSupervision_Constr_Id(long supervision_Constr_Note_Id) {
		this.supervision_Constr_Id = supervision_Constr_Note_Id;
	}

	public long getPillar_New_Total() {
		return pillar_New_Total;
	}

	public void setPillar_New_Total(long pillar_New_Total) {
		this.pillar_New_Total = pillar_New_Total;
	}

	public int getPillar_New_Type() {
		return pillar_New_Type;
	}

	public void setPillar_New_Type(int pillar_New_Type) {
		this.pillar_New_Type = pillar_New_Type;
	}

	public long getPillar_New_High() {
		return pillar_New_High;
	}

	public void setPillar_New_High(long pillar_New_High) {
		this.pillar_New_High = pillar_New_High;
	}

	public double getPillar_New_Long() {
		return pillar_New_Long;
	}

	public void setPillar_New_Long(double pillar_New_Long) {
		this.pillar_New_Long = pillar_New_Long;
	}

	public long getPillar_Old_Total() {
		return pillar_Old_Total;
	}

	public void setPillar_Old_Total(long pillar_Old_Total) {
		this.pillar_Old_Total = pillar_Old_Total;
	}

	public double getPillar_Old_Long() {
		return pillar_Old_Long;
	}

	public void setPillar_Old_Long(double pillar_Old_Long) {
		this.pillar_Old_Long = pillar_Old_Long;
	}

	public long getCable_Range() {
		return cable_Range;
	}

	public void setCable_Range(long cable_Range) {
		this.cable_Range = cable_Range;
	}

	public int getCable_Type() {
		return cable_Type;
	}

	public void setCable_Type(int cable_Type) {
		this.cable_Type = cable_Type;
	}

	public long getMx_Total() {
		return mx_Total;
	}

	public void setMx_Total(long mx_Total) {
		this.mx_Total = mx_Total;
	}

	public double getLong_Total() {
		return long_Total;
	}

	public void setLong_Total(double long_Total) {
		this.long_Total = long_Total;
	}

	public String getMeasure_Person() {
		return measure_Person;
	}

	public void setMeasure_Person(String measure_Person) {
		this.measure_Person = measure_Person;
	}

	public String getMeasure_Person_Mobile() {
		return measure_Person_Mobile;
	}

	public void setMeasure_Person_Mobile(String measure_Person_Mobile) {
		this.measure_Person_Mobile = measure_Person_Mobile;
	}

	public String getMeasure_Group() {
		return measure_Group;
	}

	public void setMeasure_Group(String measure_Group) {
		this.measure_Group = measure_Group;
	}

	public String getMeasure_Machine_Type() {
		return measure_Machine_Type;
	}

	public void setMeasure_Machine_Type(String measure_Machine_Type) {
		this.measure_Machine_Type = measure_Machine_Type;
	}

	public String getMeasure_Machine_Serial() {
		return measure_Machine_Serial;
	}

	public void setMeasure_Machine_Serial(String measure_Machine_Serial) {
		this.measure_Machine_Serial = measure_Machine_Serial;
	}

	public int getMeasure_Status() {
		return measure_Status;
	}

	public void setMeasure_Status(int measure_Status) {
		this.measure_Status = measure_Status;
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

}
