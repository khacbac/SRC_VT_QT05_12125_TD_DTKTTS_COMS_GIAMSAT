package com.viettel.database.entity;

public class DropdownItemEntity {
	private int id;
	private String title;
	private String type;
	private float fid;

	public int getId() {
		return id;
	}

	public DropdownItemEntity(int iId, String sTitle) {
		this.id = iId;
		this.title = sTitle;
	}

	public DropdownItemEntity(int iId, String sTitle, String sType) {
		this.id = iId;
		this.title = sTitle;
		this.type = sType;
	}
	
	public DropdownItemEntity(float fId, String sTitle, String sType) {
		this.fid = fId;
		this.title = sTitle;
		this.type = sType;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getFid() {
		return fid;
	}

	public void setFid(float fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
