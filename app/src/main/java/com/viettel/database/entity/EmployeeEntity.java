package com.viettel.database.entity;

import com.viettel.database.field.EmployeeField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;

public class EmployeeEntity implements Serializable {
	private static final long serialVersionUID = -6951484635734238751L;
	private long id;
	private String userName;
	private String passWord;
	private String code;
	private String groupCode;
	private String fullName;
	private String email;
	private long groupId;
	private String groupName;

	public EmployeeEntity() {
		this.id = 0L;
		this.userName = "";
		this.passWord = "";
		this.email = "";
	}

	public EmployeeEntity(Long curId, String curUserName, String curPassWord,
			String curEmail) {
		this.id = curId;
		this.userName = curUserName;
		this.passWord = curPassWord;
		this.email = curEmail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* Phan conver to json va nguoc lai */
	public static EmployeeEntity parseJson(JSONObject entry) {
		EmployeeEntity item = new EmployeeEntity();
		if (entry == null) {
		} else {
			try {
				item.setId(entry.getLong(EmployeeField.COLUMN_EMPLOYEE_ID));
				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_NAME).equals(
						"null")) {
					item.setUserName(StringUtil.EMPTY_STRING);
				} else {
					item.setUserName(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_NAME));
				}

				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_PASS).equals(
						"null")) {
					item.setPassWord(StringUtil.EMPTY_STRING);
				} else {
					item.setPassWord(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_PASS));
				}

				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_CODE).equals(
						"null")) {
					item.setCode(StringUtil.EMPTY_STRING);
				} else {
					item.setCode(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_CODE));
				}
				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE)
						.equals("null")) {
					item.setGroupCode(StringUtil.EMPTY_STRING);
				} else {
					item.setGroupCode(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_GROUP_CODE));
				}
				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_EMAIL)
						.equals("null")) {
					item.setEmail(StringUtil.EMPTY_STRING);
				} else {
					item.setEmail(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_EMAIL));
				}
				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_FULLNAME)
						.equals("null")) {
					item.setFullName(StringUtil.EMPTY_STRING);
				} else {
					item.setFullName(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_FULLNAME));
				}
				item.setGroupId(entry
						.getLong(EmployeeField.COLUMN_EMPLOYEE_GROUP_ID));

				if (entry.getString(EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME)
						.equals("null")) {
					item.setGroupName(StringUtil.EMPTY_STRING);
				} else {
					item.setGroupName(entry
							.getString(EmployeeField.COLUMN_EMPLOYEE_GROUP_NAME));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return item;
	}
}
