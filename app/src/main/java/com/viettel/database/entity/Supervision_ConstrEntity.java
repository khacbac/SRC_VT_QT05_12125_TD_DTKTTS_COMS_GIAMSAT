package com.viettel.database.entity;

import com.viettel.constants.Constants;
import com.viettel.database.field.Supervision_ConstrField;
import com.viettel.utils.DateConvert;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Supervision_ConstrEntity implements Serializable {
	private static final long serialVersionUID = 1050294185975395843L;
	private long supervision_Constr_Id;
	private int supv_Constr_Type;
	private int supervision_Progress;
	private int supervision_Status;
	private int is_Accept;
	private Date accept_Date;
	private int is_Lock;
	private Date finish_Date;
	private long supv_Group_Level;
	private int design_Type;
	private int design_Assess;
	private String design_Description;
	private String supv_Constr_Note_Code;
	private long supervision_Constr_Req_Id;
	private long construct_Id;
	private int syncStatus;
	private long processId;
	private long employeeId;
	
	private int deployExpectedDay;
	private int catProgressWorkId;
	
	private Date handOverDate;
	private Date startedDate;
	private Date completedDate;

	private int lineType;
	private String constructorName;
	private String supervisorName;
	private String statusName;
	private String sysCreateDate;

	public String getSysCreateDate() {
		return sysCreateDate;
	}

	public void setSysCreateDate(String sysCreateDate) {
		this.sysCreateDate = sysCreateDate;
	}

	public int getLineType() {
		return lineType;
	}

	public void setLineType(int lineType) {
		this.lineType = lineType;
	}

	public String getConstructorName() {
		return constructorName;
	}

	public void setConstructorName(String constructorName) {
		this.constructorName = constructorName;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getHandOverDate() {
		return handOverDate;
	}

	public void setHandOverDate(Date handOverDate) {
		this.handOverDate = handOverDate;
	}

	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}

	public Date getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}

	public int getCatProgressWorkId() {
		return catProgressWorkId;
	}

	public void setCatProgressWorkId(int catProgressWorkId) {
		this.catProgressWorkId = catProgressWorkId;
	}

	public int getDeployExpectedDay() {
		return deployExpectedDay;
	}

	public void setDeployExpectedDay(int deployExpectedDay) {
		this.deployExpectedDay = deployExpectedDay;
	}

	public long getSupervision_Constr_Id() {
		return supervision_Constr_Id;
	}

	public void setSupervision_Constr_Id(long supervision_Constr_Id) {
		this.supervision_Constr_Id = supervision_Constr_Id;
	}

	public int getSupv_Constr_Type() {
		return supv_Constr_Type;
	}

	public void setSupv_Constr_Type(int supv_Constr_Type) {
		this.supv_Constr_Type = supv_Constr_Type;
	}

	public int getSupervision_Progress() {
		return supervision_Progress;
	}

	public void setSupervision_Progress(int supervision_Progress) {
		this.supervision_Progress = supervision_Progress;
	}

	public int getSupervision_Status() {
		return supervision_Status;
	}

	public void setSupervision_Status(int supervision_Status) {
		this.supervision_Status = supervision_Status;
	}

	public int getIs_Accept() {
		return is_Accept;
	}

	public void setIs_Accept(int is_Accept) {
		this.is_Accept = is_Accept;
	}

	public Date getAccept_Date() {
		return accept_Date;
	}

	public void setAccept_Date(Date accept_Date) {
		this.accept_Date = accept_Date;
	}

	public int getIs_Lock() {
		return is_Lock;
	}

	public void setIs_Lock(int is_Lock) {
		this.is_Lock = is_Lock;
	}

	public Date getFinish_Date() {
		return finish_Date;
	}

	public void setFinish_Date(Date finish_Date) {
		this.finish_Date = finish_Date;
	}

	public long getSupv_Group_Level() {
		return supv_Group_Level;
	}

	public void setSupv_Group_Level(long supv_Group_Level) {
		this.supv_Group_Level = supv_Group_Level;
	}

	public int getDesign_Type() {
		return design_Type;
	}

	public void setDesign_Type(int design_Type) {
		this.design_Type = design_Type;
	}

	public int getDesign_Assess() {
		return design_Assess;
	}

	public void setDesign_Assess(int design_Assess) {
		this.design_Assess = design_Assess;
	}

	public String getDesign_Description() {
		return design_Description;
	}

	public void setDesign_Description(String design_Description) {
		this.design_Description = design_Description;
	}

	public String getSupv_Constr_Note_Code() {
		return supv_Constr_Note_Code;
	}

	public void setSupv_Constr_Note_Code(String supv_Constr_Note_Code) {
		this.supv_Constr_Note_Code = supv_Constr_Note_Code;
	}

	public long getSupervision_Constr_Req_Id() {
		return supervision_Constr_Req_Id;
	}

	public void setSupervision_Constr_Req_Id(long supervision_Constr_Req_Id) {
		this.supervision_Constr_Req_Id = supervision_Constr_Req_Id;
	}

	public long getConstruct_Id() {
		return construct_Id;
	}

	public void setConstruct_Id(long construct_Id) {
		this.construct_Id = construct_Id;
	}

	public int getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(int syncStatus) {
		this.syncStatus = syncStatus;
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

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Supervision_ConstrEntity> parseJson(JSONObject entry) {
		List<Supervision_ConstrEntity> listResult = new ArrayList<Supervision_ConstrEntity>();
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
				Supervision_ConstrEntity addItem = new Supervision_ConstrEntity();
				addItem.setSupervision_Constr_Id(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_ID));

				addItem.setSupv_Constr_Type(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPV_CONSTR_TYPE));

				addItem.setSupervision_Progress(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPERVISION_PROGRESS));

				addItem.setSupervision_Status(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPERVISION_STATUS));

				addItem.setIs_Accept(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_IS_ACCEPT));

				if (jsonItem.getString(
						Supervision_ConstrField.COLUMN_ACCEPT_DATE).equals(
						"null")) {
				} else {
					addItem.setAccept_Date(DateConvert.convertStringToDate(jsonItem
							.getString(Supervision_ConstrField.COLUMN_ACCEPT_DATE)));
				}

				addItem.setIs_Lock(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_IS_LOCK));

				if (jsonItem.getString(
						Supervision_ConstrField.COLUMN_FINISH_DATE).equals(
						"null")) {
				} else {
					addItem.setFinish_Date(DateConvert.convertStringToDate(jsonItem
							.getString(Supervision_ConstrField.COLUMN_FINISH_DATE)));
				}

				addItem.setSupv_Group_Level(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPV_GROUP_LEVEL));

				addItem.setDesign_Type(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_DESIGN_TYPE));

				addItem.setDesign_Assess(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_DESIGN_ASSESS));

				addItem.setDesign_Assess(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_DESIGN_ASSESS));

				if (jsonItem.getString(
						Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION)
						.equals("null")) {
					addItem.setDesign_Description(StringUtil.EMPTY_STRING);
				} else {
					addItem.setDesign_Description(jsonItem
							.getString(Supervision_ConstrField.COLUMN_DESIGN_DESCRIPTION));
				}

				if (jsonItem.getString(
						Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE)
						.equals("null")) {
					addItem.setSupv_Constr_Note_Code(StringUtil.EMPTY_STRING);
				} else {
					addItem.setSupv_Constr_Note_Code(jsonItem
							.getString(Supervision_ConstrField.COLUMN_SUPV_CONSTR_NOTE_CODE));
				}

				addItem.setConstruct_Id(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_CONSTRUCT_ID));

				addItem.setSupervision_Constr_Req_Id(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_SUPERVISION_CONSTR_REQ_ID));

				addItem.setProcessId(jsonItem
						.getInt(Supervision_ConstrField.COLUMN_PROCESS_ID));

				addItem.setSyncStatus(Constants.SYNC_STATUS.UPDATED);

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Supervision_ConstrEntity> parseJsonTest(JSONObject entry) {
		List<Supervision_ConstrEntity> listResult = new ArrayList<Supervision_ConstrEntity>();
		for (int i = 1; i < 10; i++) {
			Supervision_ConstrEntity addSupervision_ConstrItem = new Supervision_ConstrEntity();
			addSupervision_ConstrItem.setSupervision_Constr_Id(i);
			addSupervision_ConstrItem.setConstruct_Id(i);
			addSupervision_ConstrItem.setSupervision_Constr_Req_Id(i);
			if ((i % 2) == 0) {
				addSupervision_ConstrItem
						.setSupv_Constr_Type(Constants.CONSTR_TYPE.BTS);
			} else {
				addSupervision_ConstrItem
						.setSupv_Constr_Type(Constants.CONSTR_TYPE.LINE);
			}
			addSupervision_ConstrItem
					.setSupervision_Progress(Constants.SUPERVISION_PROGRESS.UNREALZED);
			addSupervision_ConstrItem
					.setSupervision_Status(Constants.SUPERVISION_STATUS.CHUADAT);
			addSupervision_ConstrItem
					.setIs_Lock(Constants.SUPERVISION_LOCK.UN_LOCK);
			addSupervision_ConstrItem.setProcessId(i);
			addSupervision_ConstrItem.setEmployeeId(1);
			listResult.add(addSupervision_ConstrItem);
		}
		return listResult;
	}

}
