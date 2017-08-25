package com.viettel.database.entity;

import com.viettel.database.field.Constr_ConstructionField;
import com.viettel.utils.DateConvert;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Constr_ConstructionEntity implements Serializable {
	private static final long serialVersionUID = -372635297638638721L;
	/* Ma tram */
	private String stationCode;
	/* dia chi tram */
	private String address;
	/* Ma tinh */
	private String provinceCode;
	/* Id Tram giam sat cong trinh */
	private long stationId;
	/* Id Contring */
	private long constructId;
	/* Loai cong trinh */
	private int constrType;
	/* Ma cong trinh */
	private String constrCode;
	/* Ten cong trinh */
	private String constrName;
	/* Dia diem thuc hien cong trinh */
	private String constrAddress;
	/* Ngay khoi cong */
	private Date startingDate;
	/* 21: cong trinh truoc 2010; chuyen sang dung status_id */
	private int status;
	/* Id dong bo du lieu */
	private long processId;
	
	private String latitude;
	private String longtitude;

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public long getStationId() {
		return stationId;
	}

	public void setStationId(long stationId) {
		this.stationId = stationId;
	}

	public long getConstructId() {
		return constructId;
	}

	public void setConstructId(long constructId) {
		this.constructId = constructId;
	}

	public int getConstrType() {
		return constrType;
	}

	public void setConstrType(int constrType) {
		this.constrType = constrType;
	}

	public String getConstrCode() {
		return constrCode;
	}

	public void setConstrCode(String constrCode) {
		this.constrCode = constrCode;
	}

	public String getConstrName() {
		return constrName;
	}

	public void setConstrName(String constrName) {
		this.constrName = constrName;
	}

	public String getConstrAddress() {
		return constrAddress;
	}

	public void setConstrAddress(String constrAddress) {
		this.constrAddress = constrAddress;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(String longtitude) {
		this.longtitude = longtitude;
	}

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Constr_ConstructionEntity> parseJson(JSONObject entry) {
		List<Constr_ConstructionEntity> listResult = new ArrayList<Constr_ConstructionEntity>();
		if (entry == null) {
			return listResult;
		}
		try {
			if (entry.isNull("listResult")) {
				return listResult;
			}
			JSONArray jsonMainArray = entry.getJSONArray("listResult");
			int total = jsonMainArray.length();
			for (int i = 0; i < total; i++) {

				JSONObject jsonItem = jsonMainArray.getJSONObject(i);
				Constr_ConstructionEntity addItem = new Constr_ConstructionEntity();
				addItem.setConstructId(jsonItem
						.getInt(Constr_ConstructionField.COLUMN_CONSTRUCT_ID));

				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_STATION_CODE).equals(
						"null")) {
					addItem.setStationCode(StringUtil.EMPTY_STRING);
				} else {
					addItem.setStationCode(jsonItem
							.getString(Constr_ConstructionField.COLUMN_STATION_CODE));
				}

				if (jsonItem.getString(Constr_ConstructionField.COLUMN_ADDRESS)
						.equals("null")) {
					addItem.setAddress(StringUtil.EMPTY_STRING);
				} else {
					addItem.setAddress(jsonItem
							.getString(Constr_ConstructionField.COLUMN_ADDRESS));
				}

				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_PROVINCE_CODE).equals(
						"null")) {
					addItem.setProvinceCode(StringUtil.EMPTY_STRING);
				} else {
					addItem.setProvinceCode(jsonItem
							.getString(Constr_ConstructionField.COLUMN_PROVINCE_CODE));
				}
				addItem.setStationId(jsonItem
						.getInt(Constr_ConstructionField.COLUMN_STATION_ID));

				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_CONSTRT_CODE).equals(
						"null")) {
					addItem.setConstrCode(StringUtil.EMPTY_STRING);
				} else {
					addItem.setConstrCode(jsonItem
							.getString(Constr_ConstructionField.COLUMN_CONSTRT_CODE));
				}

				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_CONSTRT_NAME).equals(
						"null")) {
					addItem.setConstrName(StringUtil.EMPTY_STRING);
				} else {
					addItem.setConstrName(jsonItem
							.getString(Constr_ConstructionField.COLUMN_CONSTRT_NAME));
				}

				addItem.setConstrType(jsonItem
						.getInt(Constr_ConstructionField.COLUMN_CONSTR_TYPE));

				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS)
						.equals("null")) {
					addItem.setConstrAddress(StringUtil.EMPTY_STRING);
				} else {
					addItem.setConstrAddress(jsonItem
							.getString(Constr_ConstructionField.COLUMN_CONSTRT_ADDRESS));
				}
				// TODO kiem tra loi
				if (jsonItem.getString(
						Constr_ConstructionField.COLUMN_STARTING_DATE).equals(
						"null")) {
				} else {
					addItem.setStartingDate(DateConvert.convertStringToDate(jsonItem
							.getString(Constr_ConstructionField.COLUMN_STARTING_DATE)));
				}

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Constr_ConstructionEntity> parseJsonTest(JSONObject entry) {
		List<Constr_ConstructionEntity> listResult = new ArrayList<Constr_ConstructionEntity>();
		for (int i = 1; i < 10; i++) {
			Constr_ConstructionEntity addConstr_ConstructionAdd = new Constr_ConstructionEntity();
			addConstr_ConstructionAdd.setConstructId(i);
			addConstr_ConstructionAdd.setStationCode("M00001");
			addConstr_ConstructionAdd
					.setAddress("Số nhà 123 Ngõ 24 Từ Liêm - Hà Nội");
			addConstr_ConstructionAdd.setProvinceCode("0842" + i);
			addConstr_ConstructionAdd.setStationId(1);
			addConstr_ConstructionAdd.setConstrCode("CT001" + i);
			addConstr_ConstructionAdd
					.setConstrName("Công trình khai thác tuyến ngầm");
			addConstr_ConstructionAdd.setConstrType(1);
			addConstr_ConstructionAdd.setConstrCode("XL3PTBTS1300434" + i);
			addConstr_ConstructionAdd
					.setConstrAddress("Số nhà 123 Ngõ 24 Từ Liêm - Hà Nội");
			addConstr_ConstructionAdd.setStartingDate(new Date());
			addConstr_ConstructionAdd.setStatus(1);
			addConstr_ConstructionAdd.setProcessId(i);
			listResult.add(addConstr_ConstructionAdd);
		}
		return listResult;
	}

}
