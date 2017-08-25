package com.viettel.database.entity;

import com.viettel.constants.Constants;
import com.viettel.database.field.Supervision_Constr_REQField;
import com.viettel.utils.DateConvert;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Lop quan he giau nguoi dung va cong trinh
 * 
 * @author datht1
 * 
 */
public class Supervision_Constr_REQEntity implements Serializable {

	private static final long serialVersionUID = 3586889356619164017L;
	private long supervision_Constr_Req_Id;
	private long req_Employee_Id;
	private long recv_Employee_Id;
	private long group_Id;
	private String code;
	private String request_Name;
	private String recv_Name;
	private int constr_Type;
	private Date request_Date;
	private Date finish_Date;
	private String plan_Name;
	private long creator_Id;
	private Date create_Date;
	private int is_Active;
	private long processId;

	public long getSupervision_Constr_Req_Id() {
		return supervision_Constr_Req_Id;
	}

	public void setSupervision_Constr_Req_Id(long supervision_Constr_Req_Id) {
		this.supervision_Constr_Req_Id = supervision_Constr_Req_Id;
	}

	public long getReq_Employee_Id() {
		return req_Employee_Id;
	}

	public void setReq_Employee_Id(long req_Employee_Id) {
		this.req_Employee_Id = req_Employee_Id;
	}

	public long getRecv_Employee_Id() {
		return recv_Employee_Id;
	}

	public void setRecv_Employee_Id(long recv_Employee_Id) {
		this.recv_Employee_Id = recv_Employee_Id;
	}

	public long getGroup_Id() {
		return group_Id;
	}

	public void setGroup_Id(long group_Id) {
		this.group_Id = group_Id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRequest_Name() {
		return request_Name;
	}

	public void setRequest_Name(String request_Name) {
		this.request_Name = request_Name;
	}

	public String getRecv_Name() {
		return recv_Name;
	}

	public void setRecv_Name(String recv_Name) {
		this.recv_Name = recv_Name;
	}

	public int getConstr_Type() {
		return constr_Type;
	}

	public void setConstr_Type(int constr_Type) {
		this.constr_Type = constr_Type;
	}

	public Date getRequest_Date() {
		return request_Date;
	}

	public void setRequest_Date(Date request_Date) {
		this.request_Date = request_Date;
	}

	public Date getFinish_Date() {
		return finish_Date;
	}

	public void setFinish_Date(Date finish_Date) {
		this.finish_Date = finish_Date;
	}

	public String getPlan_Name() {
		return plan_Name;
	}

	public void setPlan_Name(String plan_Name) {
		this.plan_Name = plan_Name;
	}

	public long getCreator_Id() {
		return creator_Id;
	}

	public void setCreator_Id(long creator_Id) {
		this.creator_Id = creator_Id;
	}

	public Date getCreate_Date() {
		return create_Date;
	}

	public void setCreate_Date(Date create_Date) {
		this.create_Date = create_Date;
	}

	public int getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
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

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Supervision_Constr_REQEntity> parseJson(JSONObject entry) {
		List<Supervision_Constr_REQEntity> listResult = new ArrayList<Supervision_Constr_REQEntity>();
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
				Supervision_Constr_REQEntity addItem = new Supervision_Constr_REQEntity();
				addItem.setSupervision_Constr_Req_Id(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_SUPERVISION_CONSTR_REQ_ID));

				addItem.setReq_Employee_Id(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_REQ_EMPLOYEE_ID));

				addItem.setRecv_Employee_Id(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_RECV_EMPLOYEE_ID));

				addItem.setGroup_Id(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_GROUP_ID));

				if (jsonItem.getString(Supervision_Constr_REQField.COLUMN_CODE)
						.equals("null")) {
					addItem.setCode(StringUtil.EMPTY_STRING);
				} else {
					addItem.setCode(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_CODE));
				}

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_REQUEST_NAME)
						.equals("null")) {
					addItem.setRequest_Name(StringUtil.EMPTY_STRING);
				} else {
					addItem.setRequest_Name(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_REQUEST_NAME));
				}

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_RECV_NAME).equals(
						"null")) {
					addItem.setRecv_Name(StringUtil.EMPTY_STRING);
				} else {
					addItem.setRecv_Name(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_RECV_NAME));
				}

				addItem.setConstr_Type(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_CONSTR_TYPE));

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_REQUEST_DATE)
						.equals("null")) {
				} else {
					addItem.setRequest_Date(DateConvert.convertStringToDate(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_REQUEST_DATE)));
				}

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_FINISH_DATE).equals(
						"null")) {
				} else {
					addItem.setFinish_Date(DateConvert.convertStringToDate(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_FINISH_DATE)));
				}

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_PLAN_NAME).equals(
						"null")) {
					addItem.setPlan_Name(StringUtil.EMPTY_STRING);
				} else {
					addItem.setPlan_Name(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_PLAN_NAME));
				}

				addItem.setCreator_Id(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_CREATOR_ID));

				if (jsonItem.getString(
						Supervision_Constr_REQField.COLUMN_CREATE_DATE).equals(
						"null")) {
				} else {
					addItem.setCreate_Date(DateConvert.convertStringToDate(jsonItem
							.getString(Supervision_Constr_REQField.COLUMN_CREATE_DATE)));
				}

				addItem.setIs_Active(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_IS_ACTIVE));

				addItem.setProcessId(jsonItem
						.getInt(Supervision_Constr_REQField.COLUMN_PROCESS_ID));

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Supervision_Constr_REQEntity> parseJsonTest(
			JSONObject entry) {
		List<Supervision_Constr_REQEntity> listResult = new ArrayList<Supervision_Constr_REQEntity>();
		for (int i = 1; i < 10; i++) {
			Supervision_Constr_REQEntity addSupervision_Constr_REQ = new Supervision_Constr_REQEntity();
			addSupervision_Constr_REQ.setSupervision_Constr_Req_Id(i);
			addSupervision_Constr_REQ.setReq_Employee_Id(1);
			addSupervision_Constr_REQ.setRecv_Employee_Id(1);
			addSupervision_Constr_REQ.setGroup_Id(12);
			addSupervision_Constr_REQ.setCode("PT006");
			addSupervision_Constr_REQ.setRecv_Name("Hoàn thành gấp nhé");
			addSupervision_Constr_REQ.setRequest_Name("Sếp của Hoàng Tiến Đạt");
			addSupervision_Constr_REQ.setRecv_Name("Hoàng Tiến Đạt");
			if ((i % 2) == 0) {
				addSupervision_Constr_REQ
						.setConstr_Type(Constants.CONSTR_TYPE.BTS);
			} else {
				addSupervision_Constr_REQ
						.setConstr_Type(Constants.CONSTR_TYPE.LINE);
			}
			addSupervision_Constr_REQ.setRequest_Date(new Date());
			addSupervision_Constr_REQ.setFinish_Date(new Date());
			addSupervision_Constr_REQ
					.setPlan_Name("Kế hoạch trong tháng 12/2014");
			addSupervision_Constr_REQ.setCreator_Id(1);
			addSupervision_Constr_REQ.setCreate_Date(new Date());
			addSupervision_Constr_REQ.setIs_Active(1);
			addSupervision_Constr_REQ.setProcessId(i);
			listResult.add(addSupervision_Constr_REQ);
		}
		return listResult;
	}

}
