package com.viettel.database.entity;

import java.io.Serializable;

public class ImageEntity implements Serializable {
	
	private static final long serialVersionUID = -9072688496361365693L;
	private int id;
	private String url;
	private boolean isDelete = false;
	private boolean isAttack = false;
	private boolean isNewAttack = false;
	// trang thai phe duyet
	private int statusApprove;
	private String reasonDeny;
	
	
	public int getStatusApprove() {
		return statusApprove;
	}

	public void setStatusApprove(int statusApprove) {
		this.statusApprove = statusApprove;
	}

	public boolean isAttack() {
		return isAttack;
	}

	public void setAttack(boolean isAttack) {
		this.isAttack = isAttack;
	}

	public boolean isNewAttack() {
		return isNewAttack;
	}

	public void setNewAttack(boolean isNewAttack) {
		this.isNewAttack = isNewAttack;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the reasonDeny
	 */
	public String getReasonDeny() {
		return reasonDeny;
	}

	/**
	 * @param reasonDeny the reasonDeny to set
	 */
	public void setReasonDeny(String reasonDeny) {
		this.reasonDeny = reasonDeny;
	}
	
}
