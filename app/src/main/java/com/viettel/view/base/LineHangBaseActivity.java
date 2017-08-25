package com.viettel.view.base;

import android.os.Bundle;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.controller.LineHang_Controller;

public class LineHangBaseActivity extends BaseActivity {
	protected void gotoHomeActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_HOME_ACTIVITY;
		e.sender = this;
		LineHang_Controller.getInstance().handleSwitchActivity(e);
	}	
	protected void gotoMakePlanActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_PLAN_ACTIVITY;
		e.sender = this;
		LineHang_Controller.getInstance().handleSwitchActivity(e);
	}
	protected void gotoAboutActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_ABOUT_ACTIVITY;
		e.sender = this;
		Home_Controller.getInstance().handleSwitchActivity(e);
	}
	protected void gotoLineHangActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUPERVISION_LINE_HANG_ACTIVITY;
		e.sender = this;		
		LineHang_Controller.getInstance().handleSwitchActivity(e);
	}	
	protected void gotoBRCDActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_LINE_BG);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_BRCD_BACKGROUND_ACTIVITY;
		e.sender = this;		
		Home_Controller.getInstance().handleSwitchActivity(e);
	}	
	protected void gotoSUBActivity(Bundle data) {
		ActionEvent e = new ActionEvent();
		data.putInt(IntentConstants.INTENT_MEASURE_TYPE,
				Constants.CONSTR_TYPE_SUB.GS);
		e.viewData = data;
		e.action = ActionEventConstant.GOTO_SUB_BACKGROUND_ACTIVITY;
		e.sender = this;		
		Home_Controller.getInstance().handleSwitchActivity(e);
	}
	@Override
	public void onItemSelected(int position) {
		// TODO Auto-generated method stub
		
		switch (position) {
		case 0:
			gotoHomeActivity(new Bundle());
			finish();
			break;
		case 1:
			
			break;
		case 2:
			gotoMakePlanActivity(new Bundle());
			break;
		case 3:
			requestSync(this);
	
			break;
		case 4:
			gotoAboutActivity(new Bundle());
			break;
		case 5:
			
			break;
		case 6:
			gotoLogOut();
			break;

		default:
			break;
		}
		
	}	
}