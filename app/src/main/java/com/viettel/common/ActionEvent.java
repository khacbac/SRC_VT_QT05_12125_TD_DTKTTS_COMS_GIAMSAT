package com.viettel.common;

import com.viettel.controller.AbstractController;
import com.viettel.viettellib.network.http.HTTPRequest;

public class ActionEvent {
	public int action;
	public Object modelData;
	public Object viewData;
	public Object userData;
	public Object sender;
	public int tag = 0;
	public AbstractController controller;
	/* xu ly cancel request */
	public HTTPRequest request;
	public boolean isBlockRequest;

	public void reset() {
		action = 0;
		modelData = null;
		viewData = null;
		userData = null;
		sender = null;
	}
}
