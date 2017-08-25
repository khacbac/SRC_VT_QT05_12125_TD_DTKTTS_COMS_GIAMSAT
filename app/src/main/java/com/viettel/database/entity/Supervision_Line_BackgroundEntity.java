package com.viettel.database.entity;

import com.viettel.database.field.Supervision_Line_BackgroundField;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;
import java.util.List;

public class Supervision_Line_BackgroundEntity implements Serializable {
	private static final long serialVersionUID = 8348544069036200721L;
	private long supervision_Line_Background_Id;
	private long Supervision_Constr_Id;
	private double long_Total;
	private long tank_New_Total;
	private long tank_New_One_Part;
	private long tank_New_Two_Part;
	private long tank_New_Three_Part;
	private long tank_Old_Total;
	/* Loai chon truc tiep */
	private int bury_Type;
	private double bury_Long;
	private int cable_type;
	private String pipe_Type;
	private long pipe_Number;
	private long mx_Total;
	private String measure_Person;
	private String measure_Person_Mobile;
	private String measure_Group;
	private String measure_Machine_Type;
	private String measure_Machine_Serial;
	private int measure_Status = -1;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long employeeId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public long getSupervision_Line_Background_Id() {
		return supervision_Line_Background_Id;
	}

	public void setSupervision_Line_Background_Id(
			long supervision_Line_Background_Id) {
		this.supervision_Line_Background_Id = supervision_Line_Background_Id;
	}

	public long getSupervision_Constr_Id() {
		return Supervision_Constr_Id;
	}

	public void setSupervision_Constr_Id(long supervision_Constr_Id) {
		Supervision_Constr_Id = supervision_Constr_Id;
	}

	public double getLong_Total() {
		return long_Total;
	}

	public void setLong_Total(Double long_Total) {
		this.long_Total = long_Total;
	}

	public long getTank_New_Total() {
		return tank_New_Total;
	}

	public void setTank_New_Total(long tank_New_Total) {
		this.tank_New_Total = tank_New_Total;
	}

	public long getTank_New_One_Part() {
		return tank_New_One_Part;
	}

	public void setTank_New_One_Part(long tank_New_One_Part) {
		this.tank_New_One_Part = tank_New_One_Part;
	}

	public long getTank_New_Two_Part() {
		return tank_New_Two_Part;
	}

	public void setTank_New_Two_Part(long tank_New_Two_Part) {
		this.tank_New_Two_Part = tank_New_Two_Part;
	}

	public long getTank_New_Three_Part() {
		return tank_New_Three_Part;
	}

	public void setTank_New_Three_Part(long tank_New_Three_Part) {
		this.tank_New_Three_Part = tank_New_Three_Part;
	}

	public long getTank_Old_Total() {
		return tank_Old_Total;
	}

	public void setTank_Old_Total(long tank_Old_Total) {
		this.tank_Old_Total = tank_Old_Total;
	}

	public int getBury_Type() {
		return bury_Type;
	}

	public void setBury_Type(int bury_Type) {
		this.bury_Type = bury_Type;
	}

	public double getBury_Long() {
		return bury_Long;
	}

	public void setBury_Long(double bury_Long) {
		this.bury_Long = bury_Long;
	}

	public int getCable_type() {
		return cable_type;
	}

	public void setCable_type(int cable_type) {
		this.cable_type = cable_type;
	}

	public String getPipe_Type() {
		return pipe_Type;
	}

	public void setPipe_Type(String pipe_Type) {
		this.pipe_Type = pipe_Type;
	}

	public long getPipe_Number() {
		return pipe_Number;
	}

	public void setPipe_Number(long pipe_Number) {
		this.pipe_Number = pipe_Number;
	}

	public long getMx_Total() {
		return mx_Total;
	}

	public void setMx_Total(long mx_Total) {
		this.mx_Total = mx_Total;
	}

	public String getMeasure_Person() {
		return measure_Person;
	}

	public void setMeasure_Person(String measure_Person) {
		this.measure_Person = measure_Person;
	}

	public String getMeasure_Person_Mobile() {
		return measure_Person_Mobile;
	}

	public void setMeasure_Person_Mobile(String measure_Person_Mobile) {
		this.measure_Person_Mobile = measure_Person_Mobile;
	}

	public String getMeasure_Group() {
		return measure_Group;
	}

	public void setMeasure_Group(String measure_Group) {
		this.measure_Group = measure_Group;
	}

	public String getMeasure_Machine_Type() {
		return measure_Machine_Type;
	}

	public void setMeasure_Machine_Type(String measure_Machine_Type) {
		this.measure_Machine_Type = measure_Machine_Type;
	}

	public String getMeasure_Machine_Serial() {
		return measure_Machine_Serial;
	}

	public void setMeasure_Machine_Serial(String measure_Machine_Serial) {
		this.measure_Machine_Serial = measure_Machine_Serial;
	}

	public int getMeasure_Status() {
		return measure_Status;
	}

	public void setMeasure_Status(int measure_Status) {
		this.measure_Status = measure_Status;
	}

	public int getSync_Status() {
		return sync_Status;
	}

	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* Ham chuyen doi list tuyen ngam ra chuoi json */
	public static String pageListToJson(
			List<Supervision_Line_BackgroundEntity> pListSync) {
		JSONArray arr = new JSONArray();
		try {
			Supervision_Line_BackgroundEntity.ConvertListBG(arr, pListSync);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr.toString();
	}

	private static JSONArray ConvertListBG(JSONArray arr,
			List<Supervision_Line_BackgroundEntity> pListSync)
			throws JSONException {
		for (int i = 0; i < pListSync.size(); i++) {
			JSONObject itemJson = new JSONObject();
			Supervision_Line_BackgroundEntity itemData = pListSync.get(i);
			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_SUPERVISION_LINE_BACKGROUND_ID,
					itemData.getSupervision_Line_Background_Id());
			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_SUPERVISION_CONSTR_ID,
					itemData.getSupervision_Constr_Id());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_LONG_TOTAL,
					(itemData.getLong_Total() < 0) ? "-1" : itemData
							.getLong_Total());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TOTAL,
					(itemData.getTank_New_Total() < 0) ? "-1" : itemData
							.getTank_New_Total());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_TANK_NEW_ONE_PART,
					(itemData.getTank_New_One_Part() < 0) ? "-1" : itemData
							.getTank_New_One_Part());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_TANK_NEW_TWO_PART,
					(itemData.getTank_New_Two_Part() < 0) ? "-1" : itemData
							.getTank_New_Two_Part());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_TANK_NEW_THREE_PART,
					(itemData.getTank_New_Three_Part() < 0) ? "-1" : itemData
							.getTank_New_Three_Part());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_TANK_OLD_TOTAL,
					(itemData.getTank_Old_Total() < 0) ? "-1" : itemData
							.getTank_Old_Total());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_BURY_TYPE,
					(itemData.getBury_Type() < 0) ? "-1" : itemData
							.getBury_Type());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_BURY_LONG,
					(itemData.getBury_Long() < 0) ? "-1" : itemData
							.getBury_Long());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_CABLE_TYPE,
					(itemData.getCable_type() < 0) ? "-1" : itemData
							.getCable_type());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_PIPE_NUM,
					(itemData.getPipe_Number() < 0) ? "-1" : itemData
							.getPipe_Number());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_PIPE_TYPE,
					(itemData.getPipe_Type() == null) ? "" : itemData
							.getPipe_Type());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MX_TOTAL,
					(itemData.getMx_Total() < 0) ? "-1" : itemData
							.getMx_Total());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON,
					(itemData.getMeasure_Person() == null) ? "" : itemData
							.getMeasure_Person());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_PERSON_MOBILE,
					(itemData.getMeasure_Person_Mobile() == null) ? ""
							: itemData.getMeasure_Person_Mobile());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_GROUP,
					(itemData.getMeasure_Group() == null) ? "" : itemData
							.getMeasure_Group());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_TYPE,
					(itemData.getMeasure_Machine_Type() == null) ? ""
							: itemData.getMeasure_Machine_Type());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_MACHINE_SERIAL,
					(itemData.getMeasure_Machine_Serial() == null) ? ""
							: itemData.getMeasure_Machine_Serial());

			itemJson.put(
					Supervision_Line_BackgroundField.COLUMN_MEASURE_STATUS,
					(itemData.getMeasure_Status() < 0) ? "-1" : itemData
							.getMeasure_Status());

			itemJson.put(Supervision_Line_BackgroundField.COLUMN_SYNC_STATUS,
					itemData.getSync_Status());

			itemJson.put(Supervision_Line_BackgroundField.COLUMN_IS_ACTIVE,
					itemData.getIsActive());

			arr.put(itemJson);
		}
		return arr;
	}
}
