package com.viettel.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.Cat_Progress_WorkController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ConfirmDialog extends Dialog implements
		android.view.View.OnClickListener {

	public Activity c;
	public Dialog d;
	public Button yes, no;
	private TextView dialog_title;
	private TextView tv_message;
	private String sTitle = "";
	private String sMessage = "";
	private OnEventControlListener onEvent;
	private int iEventControllListener = Constants.ID_ENTITY_DEFAULT;
	private Constr_Construction_EmployeeEntity constrItem;

	public ConfirmDialog(Activity a, String curMessage) {
		super(a);
		this.sMessage = curMessage;
		this.c = a;
		onEvent = (OnEventControlListener) a;
	}
	
	public ConfirmDialog(Activity a, String curMessage, int event) {
		super(a);
		this.sMessage = curMessage;
		this.c = a;
		iEventControllListener = event;
		onEvent = (OnEventControlListener) a;
	}
	
	public ConfirmDialog(Activity a, Constr_Construction_EmployeeEntity constrItem) {
		super(a);
//		this.sMessage = curMessage;
		this.c = a;
		this.constrItem = constrItem;
		String nameProgress = "";
		if(constrItem.getCatProgressWorkId() == 1){
			nameProgress = new Cat_Progress_WorkController(a).getName(2);
			this.sMessage = a.getString(R.string.text_tiendo,nameProgress);
		} else {
			if(constrItem.getCatProgressWorkId() == 2){
				nameProgress = new Cat_Progress_WorkController(a).getName(3);
				this.sMessage = a.getString(R.string.text_tiendo,nameProgress);
			} else {
				if(constrItem.getCatProgressWorkId() == 3){
					nameProgress = new Cat_Progress_WorkController(a).getName(4);
					this.sMessage = a.getString(R.string.text_tiendo,nameProgress);
				} 
//				else {
//					nameProgress = new Cat_Progress_WorkController(a).getName(4);
//					this.sMessage = a.getString(R.string.text_tiendo_complete,nameProgress);
//				}
			}
		}
		iEventControllListener = OnEventControlListener.EVENT_CONFIRM_PROGRESS;
		onEvent = (OnEventControlListener) a;
	}

	public ConfirmDialog(Activity a, String curTitle, String curMessage,
			int curEventControllListener) {
		super(a);
		this.sTitle = curTitle;
		this.sMessage = curMessage;
		this.c = a;
		onEvent = (OnEventControlListener) a;
		this.iEventControllListener = curEventControllListener;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.confirm_dialog_layout);
		this.tv_message = (TextView) findViewById(R.id.tv_message);
		this.dialog_title = (TextView) findViewById(R.id.dialog_title);
		yes = (Button) findViewById(R.id.btn_yes);
		no = (Button) findViewById(R.id.btn_no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		if (!StringUtil.isNullOrEmpty(sTitle)) {
			this.dialog_title.setText(sTitle);
		}
		this.tv_message.setText(sMessage);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_yes:
			if (this.iEventControllListener == Constants.ID_ENTITY_DEFAULT) {
				onEvent.onEvent(OnEventControlListener.EVENT_CONFIRM_OK, null,
						null);
			} else {
				
				onEvent.onEvent(this.iEventControllListener, null, constrItem);
			}
			break;
		case R.id.btn_no:
			dismiss();
			break;
		default:
			break;
		}
		dismiss();
	}
}
