package com.viettel.database.entity;

import java.util.List;

public class CauseConstrUnQualifyGSEntity {
	private long idUnQualify;
	private String nameUnQualify;
	private List<String> imgList;

	public long getIdUnQualify() {
		return idUnQualify;
	}

	public void setIdUnQualify(long idUnQualify) {
		this.idUnQualify = idUnQualify;
	}

	public String getNameUnQualify() {
		return nameUnQualify;
	}

	public void setNameUnQualify(String nameUnQualify) {
		this.nameUnQualify = nameUnQualify;
	}

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
}
