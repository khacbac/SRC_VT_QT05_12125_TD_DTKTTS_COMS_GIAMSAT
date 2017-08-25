package com.viettel.database.entity;

public class Constr_ObStruction_TypeEntity {

	private long constrObStructionTypeId;
	private String name;
	private int isActive;
	private String desciption;
	private int type;
	private int process_id;
	private int position;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public long getConstrObStructionTypeId() {
		return constrObStructionTypeId;
	}
	public void setConstrObStructionTypeId(long constrObStructionTypeId) {
		this.constrObStructionTypeId = constrObStructionTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getProcess_id() {
		return process_id;
	}

	public void setProcess_id(int process_id) {
		this.process_id = process_id;
	}
}
