package com.viettel.database.entity;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Measure_ConstrEntity {
	private long supervisionMeasureConstrId;
	private String name;
	private int type;
	private long supervisionConstrId;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	
	private List<Measurement_Detail_ConstrEntity> listMeasureDetail;
	private List<Measurement_Detail_ConstrEntity> listDelMeasureDetail;
	private boolean isNew = true;
	private boolean isEdited = false;
	
	
	public Supervision_Measure_ConstrEntity() {
		listMeasureDetail = new ArrayList<Measurement_Detail_ConstrEntity>();
		listDelMeasureDetail = new ArrayList<Measurement_Detail_ConstrEntity>();
	}
	
	public List<Measurement_Detail_ConstrEntity> getListDelMeasureDetail() {
		return listDelMeasureDetail;
	}

	public void setListDelMeasureDetail(
			List<Measurement_Detail_ConstrEntity> listDelMeasureDetail) {
		this.listDelMeasureDetail = listDelMeasureDetail;
	}

	public List<Measurement_Detail_ConstrEntity> getListMeasureDetail() {
		return listMeasureDetail;
	}
	public void setListMeasureDetail(
			List<Measurement_Detail_ConstrEntity> listMeasureDetail) {
		this.listMeasureDetail = listMeasureDetail;
	}
	public long getSupervisionMeasureConstrId() {
		return supervisionMeasureConstrId;
	}
	public void setSupervisionMeasureConstrId(long supervisionMeasureConstrId) {
		this.supervisionMeasureConstrId = supervisionMeasureConstrId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}
	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
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
