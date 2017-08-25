package com.viettel.database.entity;

public class CatProgressWorkEntity {
	private long id;
	private String name;
	private int is_Active;
	private long processId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIs_Active() {
		return is_Active;
	}
	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
	}
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
}
