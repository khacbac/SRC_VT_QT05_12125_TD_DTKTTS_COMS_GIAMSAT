package com.viettel.sync;

import android.content.Context;
import android.os.Environment;

import com.viettel.common.ActionEvent;
import com.viettel.common.GlobalInfo;
import com.viettel.viettellib.network.http.HTTPMessage;
import com.viettel.viettellib.network.http.HTTPResponse;
import com.viettel.viettellib.network.http.HttpAsyncTask;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class SynLogModel extends AbstractSyncService {
	protected static SyncModel instance;
	public static Context mContext = null;

	protected SynLogModel() {
		if (SyncModel.mContext == null) {
			SyncModel.mContext = GlobalInfo.getInstance().getAppContext();
		}
	}

	public static SyncModel getInstance() {
		if (instance == null) {
			instance = new SyncModel();
		}
		return instance;
	}

	@Override
	public void onReceiveError(HTTPResponse response) {
		// TODO Auto-generated method stub
		super.onReceiveError(response);
	}

	@Override
	public void onReceiveData(HTTPMessage mes) {
		// TODO Auto-generated method stub
		super.onReceiveData(mes);
	}

	public void requestSynLog(String fileName) {
		String filePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath()
				+ "/GSCT/LOG/"
				+ GlobalInfo.getInstance().getEmployeeCode() + "/";
		try {
			String method = "fileUpload.addLogFile";
			Vector<String> data = new Vector<String>();
			data.add("LOG_DATA");
			data.add(readFile(filePath+fileName));
			data.add("FILE_NAME");
			data.add(fileName);
			ActionEvent actionEvent = new ActionEvent();
			sendHttpRequest(method, data, actionEvent, HttpAsyncTask.CONNECT_TIMEOUT);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String readFile(String file) throws IOException {
		return new Scanner(new File(file)).useDelimiter("\\A").next();
	}
}
