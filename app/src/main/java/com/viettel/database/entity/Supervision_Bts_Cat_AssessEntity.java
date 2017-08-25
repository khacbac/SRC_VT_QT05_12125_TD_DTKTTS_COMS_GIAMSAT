package com.viettel.database.entity;

public class Supervision_Bts_Cat_AssessEntity {
	public long supervision_BTS_CAT_ASSESS_ID;
	public String assess_NAME;
	public String assess_CODE;
	public String dimension_DESIGN;
	public String dimension_REAL;
	public int status;
	public String fail_DESC;
	private long employeeId;
	
	public Supervision_Bts_Cat_AssessEntity(){
		
	}
	
	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getSupervision_BTS_CAT_ASSESS_ID() {
		return supervision_BTS_CAT_ASSESS_ID;
	}
	
	public void setSupervision_BTS_CAT_ASSESS_ID(long supervision_BTS_CAT_ASSESS_ID) {
		this.supervision_BTS_CAT_ASSESS_ID = supervision_BTS_CAT_ASSESS_ID;
	}
	
	public String getAssess_NAME() {
		return assess_NAME;
	}
	
	public void setAssess_NAME(String assess_NAME) {
		this.assess_NAME = assess_NAME;
	}
	
	public String getAssess_CODE() {
		return assess_CODE;
	}
	
	public void setAssess_CODE(String assess_CODE) {
		this.assess_CODE = assess_CODE;
	}
	
	public String getDimension_DESIGN() {
		return dimension_DESIGN;
	}
	
	public void setDimension_DESIGN(String dimension_DESIGN) {
		this.dimension_DESIGN = dimension_DESIGN;
	}
	
	public String getDimension_REAL() {
		return dimension_REAL;
	}
	
	public void setDimension_REAL(String dimension_REAL) {
		this.dimension_REAL = dimension_REAL;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getFail_DESC() {
		return fail_DESC;
	}
	
	public void setFail_DESC(String fail_DESC) {
		this.fail_DESC = fail_DESC;
	}
	
	
}
