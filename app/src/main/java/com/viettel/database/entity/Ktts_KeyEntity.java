package com.viettel.database.entity;

import com.viettel.database.field.Ktts_KeyField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;

public class Ktts_KeyEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8029789964948520706L;
	private String tableName;
	private long min;
	private long newMin;
	private long max;
	private long newMax;
	private String clientId;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getNewMin() {
		return newMin;
	}

	public void setNewMin(long newMin) {
		this.newMin = newMin;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public long getNewMax() {
		return newMax;
	}

	public void setNewMax(long newMax) {
		this.newMax = newMax;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/* Phan conver to json va nguoc lai */
	public static Ktts_KeyEntity parseJson(JSONObject entry) {
		Ktts_KeyEntity item = new Ktts_KeyEntity();
		if (entry == null) {
		} else {
			try {
				item.setMax(entry.getLong(Ktts_KeyField.COLUMN_MAX));
				item.setMin(entry
						.getLong(Ktts_KeyField.COLUMN_MIN));
				if (entry.getString(Ktts_KeyField.COLUMN_CLIENTID).equals(
						"null")) {
					item.setClientId(StringUtil.EMPTY_STRING);
				} else {
					item.setClientId(entry
							.getString(Ktts_KeyField.COLUMN_CLIENTID));
				}
				if (entry.getString(Ktts_KeyField.COLUMN_TABLE_NAME).equals(
						"null")) {
					item.setTableName(StringUtil.EMPTY_STRING);
				} else {
					item.setTableName(entry
							.getString(Ktts_KeyField.COLUMN_TABLE_NAME));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return item;
	}
}