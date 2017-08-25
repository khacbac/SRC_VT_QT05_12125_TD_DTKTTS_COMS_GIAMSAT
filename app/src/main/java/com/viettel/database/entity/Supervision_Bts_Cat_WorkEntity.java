package com.viettel.database.entity;

public class Supervision_Bts_Cat_WorkEntity {
	private long supervision_Bts_Cat_Work_Id;
	private String dimension_Design;
	private String dimension_Real;
	private int pulling_Wire_Type;
	private int status;
	private String fail_Desc;
	private long cat_Supervision_Constr_Work_Id;
	private long supervision_Bts_Id;
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

	public Supervision_Bts_Cat_WorkEntity() {
		status = -1;
		fail_Desc = "";
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getCat_Supervision_Constr_Work_Id() {
		return cat_Supervision_Constr_Work_Id;
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

	public long getSupervision_Bts_Id() {
		return supervision_Bts_Id;
	}

	public void setSupervision_Bts_Id(long supervision_Bts_Id) {
		this.supervision_Bts_Id = supervision_Bts_Id;
	}

	public void setCat_Supervision_Constr_Work_Id(
			long cat_Supervision_Constr_Work_Id) {
		this.cat_Supervision_Constr_Work_Id = cat_Supervision_Constr_Work_Id;
	}

	public int getPulling_Wire_Type() {
		return pulling_Wire_Type;
	}

	public void setPulling_Wire_Type(int pulling_Wire_Type) {
		this.pulling_Wire_Type = pulling_Wire_Type;
	}

	public long getSupervision_Bts_Cat_Work_Id() {
		return supervision_Bts_Cat_Work_Id;
	}

	public void setSupervision_Bts_Cat_Work_Id(long supervision_Bts_Cat_Work_Id) {
		this.supervision_Bts_Cat_Work_Id = supervision_Bts_Cat_Work_Id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getFail_Desc() {
		return fail_Desc;
	}

	public void setFail_Desc(String fail_Desc) {
		this.fail_Desc = fail_Desc;
	}

}
