package com.viettel.database.entity;

import com.viettel.constants.Constants;

import java.io.Serializable;

public class Supervision_Line_BG_FiberEntity implements Serializable {
	private static final long serialVersionUID = 5495442942351032249L;
	private int stt;
	private int idField;
	private long supervision_Line_Bg_Fiber_Id;
	private long supervision_Line_Background_Id;
	private String fiber_Name;
	private float measure_Value_Db;
	private boolean isNew;
	private boolean isEdit;
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

	public Supervision_Line_BG_FiberEntity() {
		measure_Value_Db = Constants.ID_ENTITY_DEFAULT;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public int getIdField() {
		return idField;
	}

	public void setIdField(int idField) {
		this.idField = idField;
	}

	public long getSupervision_Line_Bg_Fiber_Id() {
		return supervision_Line_Bg_Fiber_Id;
	}

	public void setSupervision_Line_Bg_Fiber_Id(
			long supervision_Line_Bg_Fiber_Id) {
		this.supervision_Line_Bg_Fiber_Id = supervision_Line_Bg_Fiber_Id;
	}

	public long getSupervision_Line_Background_Id() {
		return supervision_Line_Background_Id;
	}

	public void setSupervision_Line_Background_Id(
			long supervision_Line_Background_Id) {
		this.supervision_Line_Background_Id = supervision_Line_Background_Id;
	}

	public String getFiber_Name() {
		return fiber_Name;
	}

	public void setFiber_Name(String fiber_Name) {
		this.fiber_Name = fiber_Name;
	}

	public float getMeasure_Value_Db() {
		return measure_Value_Db;
	}

	public void setMeasure_Value_Db(float measure_Value_Db) {
		this.measure_Value_Db = measure_Value_Db;
	}

	public void setMeasure_Value_Db(long measure_Value_Db) {
		this.measure_Value_Db = measure_Value_Db;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
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
