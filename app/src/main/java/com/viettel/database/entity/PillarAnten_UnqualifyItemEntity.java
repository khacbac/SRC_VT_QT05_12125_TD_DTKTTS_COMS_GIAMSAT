package com.viettel.database.entity;

import java.io.Serializable;

public class PillarAnten_UnqualifyItemEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 155011628033103578L;
	private long cat_Cause_Constr_Unqualify_Id = -1;
	private long cause_Bts_Pillar_Anten_Id = -1;
	private String title = "";
	private boolean isUnqulify = false;
	private String imgUrl;

	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}

	public void setCat_Cause_Constr_Unqualify_Id(
			long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
	}

	// public static void setSerialversionuid(long serialversionuid) {
	// serialVersionUID = serialversionuid;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getCause_Bts_Pillar_Anten_Id() {
		return cause_Bts_Pillar_Anten_Id;
	}

	public void setCause_Bts_Pillar_Anten_Id(long cause_Bts_Pillar_Anten_Id) {
		this.cause_Bts_Pillar_Anten_Id = cause_Bts_Pillar_Anten_Id;
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
