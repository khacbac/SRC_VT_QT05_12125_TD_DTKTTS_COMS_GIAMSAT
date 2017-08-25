package com.viettel.sync;

import com.viettel.viettellib.json.me.JSONArray;

public class SyncTableInfo {
	private String tableName;
	private String columnKey;
	private String[] allColumn;
	private int actionId;
	private JSONArray jsonData;
	/**
	 * Bien yeu cau request tiep theo(update du lieu va dong bo du lieu)
	 */
	private boolean requestNext = false;
	
	/**
	 * Bien theo doi dang dong bo bang du lieu nao
	 */
	private boolean requestTable = true;
	
	public boolean isRequestTable() {
		return requestTable;
	}

	public void setRequestTable(boolean requestTable) {
		this.requestTable = requestTable;
	}

	public SyncTableInfo(String pTableName, String pColumnKey) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.requestTable = true;
	}
	
	public SyncTableInfo(String pTableName, String pColumnKey, int pActionId) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.actionId = pActionId;
		this.requestTable = true;
	}

	public SyncTableInfo(String pTableName, String pColumnKey, int pActionId,
			JSONArray jsonData) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.actionId = pActionId;
		this.jsonData = jsonData;
		this.requestTable = true;
	}

	public SyncTableInfo(String pTableName, String pColumnKey, int pActionId,
			String[] pAllColumn) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.actionId = pActionId;
		this.allColumn = pAllColumn;
		this.requestTable = true;
	}

	public SyncTableInfo(String pTableName, String pColumnKey, int pActionId,
			JSONArray jsonData, String[] pAllColumn) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.actionId = pActionId;
		this.jsonData = jsonData;
		this.allColumn = pAllColumn;
		this.requestTable = true;
	}

	public SyncTableInfo(String pTableName, String pColumnKey, int pActionId,
			JSONArray jsonData, String[] pAllColumn, boolean pRequestNext) {
		this.tableName = pTableName;
		this.columnKey = pColumnKey;
		this.actionId = pActionId;
		this.jsonData = jsonData;
		this.allColumn = pAllColumn;
		this.requestNext = pRequestNext;
		this.requestTable = true;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnKey() {
		return columnKey;
	}

	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}

	public int getActionId() {
		return actionId;
	}

	public boolean isRequestNext() {
		return requestNext;
	}

	public void setRequestNext(boolean requestNext) {
		this.requestNext = requestNext;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String[] getAllColumn() {
		return allColumn;
	}

	public void setAllColumn(String[] allColumn) {
		this.allColumn = allColumn;
	}

	public JSONArray getJsonData() {
		return jsonData;
	}

	public void setJsonData(JSONArray jsonData) {
		this.jsonData = jsonData;
	}

}
