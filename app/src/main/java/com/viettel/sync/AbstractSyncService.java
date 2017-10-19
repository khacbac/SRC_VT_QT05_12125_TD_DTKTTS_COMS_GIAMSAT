package com.viettel.sync;

import com.viettel.common.ActionEvent;
import com.viettel.common.LogManager;
import com.viettel.common.ServerPath;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.viettellib.json.me.JSONException;
import com.viettel.viettellib.json.me.JSONObject;
import com.viettel.viettellib.network.http.HTTPListenner;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPMultiPartRequest;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;
import com.viettel.viettellib.network.http.MultiPartInputStream;
import com.viettel.viettellib.network.http.NetworkUtil;

import java.util.Vector;

public abstract class AbstractSyncService implements HTTPListenner {

	public void onReceiveError(HTTPResponse response) {
		// TODO Auto-generated method stub
	}

	public void onReceiveData(HTTPMessage mes) {
		// TODO Auto-generated method stub
	}

	/**
	 * Request text
	 * 
	 * @author: HieuNH
	 * @param method
	 * @param data
	 * @param actionEvent
	 * @return: void
	 * @throws:
	 */
	@SuppressWarnings("rawtypes")
	protected HTTPRequest sendHttpRequest(String method, Vector data,
			ActionEvent actionEvent) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString(method, data));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
//		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re).execute();
		return re;
	}

	/**
	 * Request text
	 * 
	 * @author: HieuNH
	 * @param method
	 * @param data
	 * @param actionEvent
	 * @return: void
	 * @throws:
	 */
	@SuppressWarnings("rawtypes")
	protected HTTPRequest sendHttpRequest(String method, Vector data,
			ActionEvent actionEvent, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONString(method, data));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
//		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}

	/**
	 * 
	 * 
	 * su dung JSONObject chuyen tham so va phuong thuc truy van
	 * 
	 * @author: Duchha
	 * @param: String method
	 * @param :JSONObject data
	 * @param :ActionEvent actionEvent
	 * @param :int timeout
	 * @return:
	 * @throws:
	 */
	protected HTTPRequest sendHttpRequest(String method, JSONObject oject,
			ActionEvent actionEvent, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		HTTPRequest re = new HTTPRequest();
		JSONObject data = new JSONObject();
		try {
			data.put(IntentConstants.INTENT_METHOD, method);
			data.put(IntentConstants.INTENT_PARAMS, oject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		strBuffer.append(data.toString());

		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}

	protected HTTPRequest sendRSAHttpRequestusingAES(String method,
			JSONObject orgObj, ActionEvent actionEvent, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		// strBuffer.append(NetworkUtil.getJSONStringRSA(orgObj, actionEvent);
		JSONObject data = new JSONObject();
		try {
			data.put(IntentConstants.INTENT_METHOD, method);
			data.put(IntentConstants.INTENT_PARAMS, strBuffer);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		strBuffer.append(data.toString());
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
//		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}

	@SuppressWarnings("rawtypes")
	protected HTTPRequest sendRSAHttpRequest(String method, Vector data,
			ActionEvent actionEvent, boolean usingAES, int timeout) {
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(NetworkUtil.getJSONStringRSA(method, data,
				actionEvent, usingAES));
		HTTPRequest re = new HTTPRequest();
		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
//		re.setContentType(HTTPMessage.CONTENT_JSON);
		re.setMethod(Constants.HTTPCONNECTION_POST);
		re.setDataText(strBuffer.toString());
		re.setObserver(this);
		re.setUserData(actionEvent);
		new HttpAsyncTask(re, timeout).execute();
		return re;
	}

	/**
	 * Request multipart voi anh duoc decode tu link image
	 * @param method
	 * @param imgPath
	 * @param actionEvent
	 * @param fileName
	 * @param fileField
	 * @param fileType
	 * @return
	 * @throws Throwable
	 */
	
	@SuppressWarnings("unchecked")
	protected HTTPRequest httpMultiPartRequest(String method, String imgPath,
			ActionEvent actionEvent, String fileName, String fileField,
			String fileType) throws Throwable {		
		HTTPRequest re = new HTTPMultiPartRequest();
//		re.setContentType(HTTPMessage.MULTIPART_JSON);
		String dataText = NetworkUtil.getJSONString(method,
				(Vector<String>) actionEvent.viewData);
		re.setDataTypeSend(HTTPRequest.CONTENT_TYPE_BINARY);
		MultiPartInputStream multiPartStream;
		try {
			multiPartStream = new MultiPartInputStream(dataText, imgPath,
					fileName, fileField, fileType);

			((HTTPMultiPartRequest) re).setMultipartStream(multiPartStream);
			((HTTPMultiPartRequest) re).setDataText(dataText);
			re.setUrl(ServerPath.SERVER_PATH);
			re.setAction(actionEvent.action);
			re.setMethod("POST");
			re.setObserver(this);
			re.setUserData(actionEvent);

			new HttpAsyncTask(re).execute();
		} catch (Throwable e) {
			LogManager.getInstance().writeFile(e.getMessage() + "/"+e.toString());
			throw e;
			
		}
		return re;
	}

	/**
	 * Request multipart voi anh duoc decode tu link image
	 * 
	 * @author: BangHN
	 * @param method
	 * @param data
	 * @param imgPath
	 * @param actionEvent
	 * @param fileName
	 * @param fileField
	 * @param fileType
	 * @param isUsedDecodeBitmapFromImgPath
	 * @return
	 * @return: HTTPRequest
	 * @throws Throwable
	 * @throws:
	 */
	@SuppressWarnings("rawtypes")
	protected HTTPRequest httpMultiPartRequestWithDeoceImageFromImagePath(
			String method, Vector data, String imgPath,
			ActionEvent actionEvent, String fileName, String fileField,
			String fileType) throws Throwable {
		HTTPRequest re = new HTTPMultiPartRequest();
		re.setContentType(HTTPMessage.MULTIPART_JSON);
		re.setDataTypeSend(HTTPRequest.CONTENT_TYPE_BINARY);
		MultiPartInputStream multiPartStream;
		String dataText = NetworkUtil.getJSONString(method, data);
		multiPartStream = new MultiPartInputStream(dataText, imgPath, fileName,
				fileField, fileType);
		((HTTPMultiPartRequest) re).setMultipartStream(multiPartStream);
		((HTTPMultiPartRequest) re).setDataText(dataText);
		re.setUrl(ServerPath.SERVER_PATH);
		re.setAction(actionEvent.action);
		re.setMethod("POST");
		re.setObserver(this);
		re.setUserData(actionEvent);

		new HttpAsyncTask(re).execute();
		return re;
	}

}
