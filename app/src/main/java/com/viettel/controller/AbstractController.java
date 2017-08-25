package com.viettel.controller;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.ErrorConstants;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.view.base.BaseActivity;

public abstract class AbstractController {
	abstract public void handleViewEvent(ActionEvent e);

	abstract public void handleSwitchActivity(ActionEvent e);

	/**
	 * Xu ly du lieu tu model tra ve --> TH loi
	 * 
	 * @author: TruongHN
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 */
	public void handleErrorModelEvent(final ModelEvent modelEvent) {
		final ActionEvent e = modelEvent.getActionEvent();
		if (modelEvent.getModelCode() == ErrorConstants.ERROR_SESSION_RESET) {
			Log.i(Constants.GSCT_TAG_ERROR, "NOT_LOGIN");
		}
		handleCommonError(modelEvent);
		BaseActivity ac = (BaseActivity) e.sender;
		if (ac.isFinished) {
			return;
		}
		ac.runOnUiThread(new Runnable() {
			public void run() {				
				BaseActivity view = (BaseActivity) e.sender;
				view.handleErrorModelViewEvent(modelEvent);
			}

		});
	}

	/**
	 * 
	 * Xu ly cac loi chung
	 * 
	 * @author: AnhND
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 */
	public void handleCommonError(ModelEvent modelEvent) {
		ActionEvent actionEvent = modelEvent.getActionEvent();
		switch (modelEvent.getModelCode()) {
		case ErrorConstants.ERROR_SESSION_RESET:
			actionEvent.controller = this;
			break;
		}
	}

	/**
	 * Xu ly du lieu tu model tra ve --> TH thanh cong
	 * 
	 * @author: TruongHN
	 * @param modelEvent
	 * @return: void
	 * @throws:
	 */
	public void handleModelEvent(final ModelEvent modelEvent) {
		final ActionEvent e = modelEvent.getActionEvent();
		BaseActivity ac = (BaseActivity) e.sender;
		if (ac != null && !ac.isFinished) {
			ac.runOnUiThread(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					BaseActivity base = (BaseActivity) e.sender;
					if (base != null) {
						base.handleModelViewEvent(modelEvent);
					}
				}
			});
		}
	}
}
