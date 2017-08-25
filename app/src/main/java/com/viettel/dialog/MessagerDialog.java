package com.viettel.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.ktts.R;
import com.viettel.view.listener.OnEventControlListener;

public class MessagerDialog extends Dialog implements
		android.view.View.OnClickListener {

	public Activity c;
	public Dialog d;
	public Button yes;
	private TextView tv_dialog_message_content;
	private String sMessage = "";
	private OnEventControlListener onEvent;
	private int iEventControllListener = Constants.ID_ENTITY_DEFAULT;

	public MessagerDialog(Activity a, String curMessage) {
		super(a);
		this.sMessage = curMessage;
		this.c = a;
		onEvent = (OnEventControlListener) a;
	}

	public MessagerDialog(Activity a, String curMessage, int pEvent) {
		super(a);
		this.sMessage = curMessage;
		this.c = a;
		this.onEvent = (OnEventControlListener) a;
		this.iEventControllListener = pEvent;

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_messager_layout);
		this.tv_dialog_message_content = (TextView) findViewById(R.id.tv_dialog_message_content);
		yes = (Button) findViewById(R.id.btn_messager_yes);
		yes.setOnClickListener(this);
		this.tv_dialog_message_content.setText(sMessage);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_messager_yes:
			if (iEventControllListener != Constants.ID_ENTITY_DEFAULT) {
				onEvent.onEvent(this.iEventControllListener, null, null);
			} else {
				onEvent.onEvent(OnEventControlListener.EVENT_MESSAGER_OK, null,
						null);
			}
			break;
		default:
			break;
		}
		dismiss();
	}
}
