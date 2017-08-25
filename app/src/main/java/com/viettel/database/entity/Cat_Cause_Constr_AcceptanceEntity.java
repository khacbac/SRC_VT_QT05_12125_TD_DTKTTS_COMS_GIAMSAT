package com.viettel.database.entity;

public class Cat_Cause_Constr_AcceptanceEntity {
	private long cat_Cause_Constr_Acceptance_Id;
	private String name;
	private int type;
	private String sub_Type;
	private String category;
	private int is_Active;
	private int is_Mandatory;
	private long processId;
	
	
	public long getCat_Cause_Constr_Acceptance_Id() {
		return cat_Cause_Constr_Acceptance_Id;
	}
	public void setCat_Cause_Constr_Acceptance_Id(
			long cat_Cause_Constr_Acceptance_Id) {
		this.cat_Cause_Constr_Acceptance_Id = cat_Cause_Constr_Acceptance_Id;
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
	
	
	public int getIs_Mandatory() {
		return is_Mandatory;
	}
	public void setIs_Mandatory(int is_Mandatory) {
		this.is_Mandatory = is_Mandatory;
	}
	public String getSub_Type() {
		return sub_Type;
	}
	public void setSub_Type(String sub_Type) {
		this.sub_Type = sub_Type;
	}
	public int getIs_Active() {
		return is_Active;
	}
	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
}
