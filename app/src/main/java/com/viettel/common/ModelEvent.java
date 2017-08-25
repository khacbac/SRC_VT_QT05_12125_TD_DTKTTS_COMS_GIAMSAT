package com.viettel.common;

import com.viettel.constants.Constants;

public class ModelEvent {
	private ActionEvent actionEvent;
	Object modelData;
	int modelCode;
	int code;//dung trong timeout cua Oauth
	String modelMessage;
	// luu giu chuoi datatext tra ve tu server, phuc vu trace log
	private String dataText = Constants.STR_BLANK;
	// luu giu param cua request khi truyen len server
	private String param = Constants.STR_BLANK;
	
	public void setActionEvent(ActionEvent actionEvent) {
		this.actionEvent = actionEvent;
	}
	public ActionEvent getActionEvent() {
		return actionEvent;
	}
	public Object getModelData() {
		return modelData;
	}
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	public void setModelData(Object modelData) {
		this.modelData = modelData;
	}
	public int getModelCode() {
		return modelCode;
	}
	public void setModelCode(int modelCode) {
		this.modelCode = modelCode;
//		if(this.code == OAuthRequestManager.TIMED_OUT){
//			this.modelCode = ErrorConstants.ERROR_SESSION_RESET;
//		}
	}
	public String getModelMessage() {
		return modelMessage;
	}
	public void setModelMessage(String modelMessage) {
		this.modelMessage = modelMessage;
	}
	public void setDataText(String dataText) {
		this.dataText = dataText;
	}
	public String getDataText() {
		return dataText;
	}
	public void setParams(String para) {
		this.param = para;
	}
	public String getParams() {
		return param;
	}
}
