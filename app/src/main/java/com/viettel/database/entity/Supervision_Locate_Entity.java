package com.viettel.database.entity;

import java.io.Serializable;
import java.util.Date;

public class Supervision_Locate_Entity implements Serializable {
	/**
	 * hungkm
	 */
	private static final long serialVersionUID = 4428168125145370856L;
	private long locateId;
	private long employeeId;
	private long suppervisionId;
	private long provinceId;
	private long processId;
	private int isActive;
	private int isCheckin;
	private int sync_Status;
	private String checkinLatitude;
	private String checkinLongtitude;
	private String logoutLatitude;
	private String logoutLongtitude;
	private String plan;
	private Date checkinDate;
	private Date checkoutDate;

	public long getLocateId() {
		return locateId;
	}

	public void setLocateId(long locateId) {
		this.locateId = locateId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getSuppervisionId() {
		return suppervisionId;
	}

	public void setSuppervisionId(long suppervisionId) {
		this.suppervisionId = suppervisionId;
	}

	public long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(long provinceId) {
		this.provinceId = provinceId;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsCheckin() {
		return isCheckin;
	}

	public void setIsCheckin(int isCheckin) {
		this.isCheckin = isCheckin;
	}

	public int getSync_Status() {
		return sync_Status;
	}

	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}

	public String getCheckinLatitude() {
		return checkinLatitude;
	}

	public void setCheckinLatitude(String checkinLatitude) {
		this.checkinLatitude = checkinLatitude;
	}

	public String getCheckinLongtitude() {
		return checkinLongtitude;
	}

	public void setCheckinLongtitude(String checkinLongtitude) {
		this.checkinLongtitude = checkinLongtitude;
	}

	public String getLogoutLatitude() {
		return logoutLatitude;
	}

	public void setLogoutLatitude(String logoutLatitude) {
		this.logoutLatitude = logoutLatitude;
	}

	public String getLogoutLongtitude() {
		return logoutLongtitude;
	}

	public void setLogoutLongtitude(String logoutLongtitude) {
		this.logoutLongtitude = logoutLongtitude;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public Date getCheckinDate() {
		return checkinDate;
	}

	public void setCheckinDate(Date checkinDate) {
		this.checkinDate = checkinDate;
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
