package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_LHG_UnqualifyItemEntity implements Serializable {

	private static final long serialVersionUID = 7810155547032154441L;
	private long cat_Cause_Constr_Unqualify_Id = -1;
	private long cause_Line_Hg_Id = -1;
	private String title = "";
	private boolean isUnqulify = false;
	private String imgUrl;
	
	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}
	
	public void setCat_Cause_Constr_Unqualify_Id(long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
	}
	
	public long getCause_Line_Hg_Id() {
		return cause_Line_Hg_Id;
	}
	
	public void setCause_Line_Hg_Id(long cause_Line_Hg_Id) {
		this.cause_Line_Hg_Id = cause_Line_Hg_Id;
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

}
