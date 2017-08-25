package com.viettel.camera.entity;

import java.io.Serializable;

public class Constr_Construction_EmployeeEntity implements  Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* so thu tu cong trinh */
	private int stt;
	/* Id Cong trinh */
	private long constructId;
	/* Id yeu cau giam sat*/
	private long supervision_Constr_Id;	
	/* Loai tuyen */
	private int supvConstrType;
	/* tien do cong trinh */
	private int constrProgressId;	
	/* Trang thai cong trinh */
	private int constrStatusId;	
	/* Ma cong trinh */
	private String constrCode;
	/* ma tuyen cong trinh */
	private String stationCode;
	/* Id tuyen cong trinh */
	private long stationId;
	/* Diem dau cong trinh */
	private String startPointCode;
	/* Diem cuoi cong trinh */
	private String endPointCode;
	/* Ten cong trinh */
	private String constrName;
	/* Loai tuyen */
	private int designType;
	/* Loai tram */
	private long catStationTypeId;
	
	/* ngay giao */
	private String requestDate;
	/* ngay ket thuc */
	private String finishDate;
	
	private int deployExpectedDay;
	private int catProgressWorkId;
	private String handOverDate;
	private String startedDate;
	private String completedDate;
	private String nameProgress;
	
	public String getHandOverDate() {
		return handOverDate;
	}

	public void setHandOverDate(String handOverDate) {
		this.handOverDate = handOverDate;
	}

	public String getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(String startedDate) {
		this.startedDate = startedDate;
	}

	public String getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(String completedDate) {
		this.completedDate = completedDate;
	}

	public String getNameProgress() {
		return nameProgress;
	}

	public void setNameProgress(String nameProgress) {
		this.nameProgress = nameProgress;
	}

	public int getDeployExpectedDay() {
		return deployExpectedDay;
	}

	public void setDeployExpectedDay(int deployExpectedDay) {
		this.deployExpectedDay = deployExpectedDay;
	}

	public int getCatProgressWorkId() {
		return catProgressWorkId;
	}

	public void setCatProgressWorkId(int catProgressWorkId) {
		this.catProgressWorkId = catProgressWorkId;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public long getCatStationTypeId() {
		return catStationTypeId;
	}

	public void setCatStationTypeId(long catStationTypeId) {
		this.catStationTypeId = catStationTypeId;
	}

	public long getConstructId() {
		return constructId;
	}

	public long getSupervision_Constr_Id() {
		return supervision_Constr_Id;
	}

	public void setSupervision_Constr_Id(long supervision_Constr_Id) {
		this.supervision_Constr_Id = supervision_Constr_Id;
	}

	public void setConstructId(long constructId) {
		this.constructId = constructId;
	}
	
	public int getSupvConstrType() {
		return supvConstrType;
	}

	public void setSupvConstrType(int supvConstrType) {
		this.supvConstrType = supvConstrType;
	}

	public int getConstrProgressId() {
		return constrProgressId;
	}

	public void setConstrProgressId(int constrProgressId) {
		this.constrProgressId = constrProgressId;
	}

	public int getConstrStatusId() {
		return constrStatusId;
	}

	public void setConstrStatusId(int constrStatusId) {
		this.constrStatusId = constrStatusId;
	}
	public String getConstrCode() {
		return constrCode;
	}

	public void setConstrCode(String constrCode) {
		this.constrCode = constrCode;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public long getStationId() {
		return stationId;
	}

	public void setStationId(long stationId) {
		this.stationId = stationId;
	}

	
	public String getStartPointCode() {
		return startPointCode;
	}

	public void setStartPointCode(String startPointCode) {
		this.startPointCode = startPointCode;
	}

	public String getEndPointCode() {
		return endPointCode;
	}

	public void setEndPointCode(String endPointCode) {
		this.endPointCode = endPointCode;
	}

	public String getConstrName() {
		return constrName;
	}

	public void setConstrName(String constrName) {
		this.constrName = constrName;
	}

	public int getDesignType() {
		return designType;
	}

	public void setDesignType(int designType) {
		this.designType = designType;
	}
	
}
