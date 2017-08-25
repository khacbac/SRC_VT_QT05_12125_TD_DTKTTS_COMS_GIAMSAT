package com.viettel.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.viettel.ktts.R;
import com.viettel.view.listener.OnEventControlListener;

public class ConclusionDialog extends Dialog implements
		android.view.View.OnClickListener {

	public Activity c;
	public Dialog d;
	public Button yes, no;
	private TextView tv_conclusion_progress;
	private TextView tv_conclusion_status;
	private OnEventControlListener onEvent;
	private String sStatus = "";
	private String sProgress = "";

	public ConclusionDialog(Activity a, String sStatus, String sProgress) {
		super(a);
		// TODO Auto-generated constructor stub
		this.c = a;
		onEvent = (OnEventControlListener) a;
		this.sStatus = sStatus;
		this.sProgress = sProgress;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.conclusion_dialog_layout);
		this.tv_conclusion_progress = (TextView) findViewById(R.id.tv_conclusion_progress);
		this.tv_conclusion_status = (TextView) findViewById(R.id.tv_conclusion_status);
		yes = (Button) findViewById(R.id.btn_yes);
		no = (Button) findViewById(R.id.btn_no);
		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		this.tv_conclusion_progress.setText(this.sProgress);
		this.tv_conclusion_status.setText(this.sStatus);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_yes:
			onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_BG_OK,
					null, null);
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
