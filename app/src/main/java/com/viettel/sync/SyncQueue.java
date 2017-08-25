package com.viettel.sync;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.viettel.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class SyncQueue {
	protected static SyncQueue instance;
	private boolean errorStop = false;
	private int step;
	private List<String> listTableGetKey;
	private List<String> listTableUpdate;
	private List<String> listTableGet;
	private List<Long> listFileDownLoad;
	private List<Long> listFileUpload;

	public static SyncQueue getInstance() {
		if (instance == null) {
			instance = new SyncQueue();
		}
		return instance;
	}

	public SyncQueue() {
		this.errorStop = false;
		this.step = Constants.SYNC_STEP.GET_CLIENTID;
		this.listTableGetKey = new ArrayList<String>();
		this.listTableUpdate = new ArrayList<String>();
		this.listTableGet = new ArrayList<String>();
		this.listFileDownLoad = new ArrayList<Long>();
		this.listFileUpload = new ArrayList<Long>();

	}

	/**
	 * Ham reset de dong bo lai tu dau
	 */
	public void resetData() {
		this.errorStop = false;
		this.step = Constants.SYNC_STEP.GET_CLIENTID;
		this.listTableGetKey.clear();
		this.listTableUpdate.clear();
		this.listTableGet.clear();
		this.listFileDownLoad.clear();
		this.listFileUpload.clear();
	}
	
	/**
	 * Ham reset de dong bo lai tu dau
	 */
	public void resetData(Handler handler) {
		Message msg = new Message();
		Bundle bundle = new Bundle();
		bundle.putInt(SyncTask.KEY_PERCENT, 0);
		bundle.putString(SyncTask.KEY_SYNC_DATA_TYPE, "Checking for sync ...");
		msg.setData(bundle);
		handler.sendMessage(msg);
		
		this.errorStop = false;
		this.step = Constants.SYNC_STEP.GET_CLIENTID;
		this.listTableGetKey.clear();
		this.listTableUpdate.clear();
		this.listTableGet.clear();
		this.listFileDownLoad.clear();
		this.listFileUpload.clear();
	}

	public void addTableGetKey(String pTableName) {
		this.listTableGetKey.add(pTableName);
	}

	public void removeTableGetKey(String pTableName) {
		this.listTableGetKey.remove(pTableName);
	}

	public boolean checkGetKeyFinish() {
		if (this.listTableGetKey.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addTableUpdate(String pTableName) {
		this.listTableUpdate.add(pTableName);
	}

	public void removeTableUpdate(String pTableName) {
		this.listTableUpdate.remove(pTableName);
	}

	public boolean checkUpdateFinish() {
		if (this.listTableUpdate.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addTableGet(String pTableName) {
		this.listTableGet.add(pTableName);
	}

	public void removeTableGet(String pTableName) {
		this.listTableGet.remove(pTableName);
	}

	public boolean checkGetFinish() {
		if (this.listTableGet.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/* Phan kiem tra download anh */
	public void addFileDownload(long idFile) {
		this.listFileDownLoad.add(idFile);
	}

	public void removeFileDownload(Object idFile) {
		this.listFileDownLoad.remove(idFile);
	}

	public boolean checkFileDownload() {
		if (this.listFileDownLoad.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/* Phan kiem tra upload anh */
	public void addFileUpload(long idFile) {
		this.listFileUpload.add(idFile);
	}

	public void removeFileUpload(Object idFile) {
		this.listFileUpload.remove(idFile);
	}

	public boolean checkFileUpload() {
		if (this.listFileUpload.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isErrorStop() {
		return errorStop;
	}

	public void setErrorStop(boolean errorStop) {
		this.errorStop = errorStop;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

}
