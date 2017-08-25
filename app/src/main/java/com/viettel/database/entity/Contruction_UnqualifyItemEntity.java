package com.viettel.database.entity;
import java.io.Serializable;

public class Contruction_UnqualifyItemEntity implements Serializable {	
	
	private static final long serialVersionUID = 7810155547032154441L;
	private long id = -1;
	private String title = "";
	private boolean isUnqulify = false;
	private String imgUrl;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public boolean isUnqulify() {
		return isUnqulify;
	}
	
	public void setUnqulify(boolean isUnqulify) {
		this.isUnqulify = isUnqulify;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
