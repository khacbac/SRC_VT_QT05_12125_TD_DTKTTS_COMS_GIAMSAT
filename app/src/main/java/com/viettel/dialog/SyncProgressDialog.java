package com.viettel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SyncProgressDialog extends Dialog implements android.view.View.OnClickListener
{
	private TextView tvTitle, tvPercent;
	private ProgressBar progressBar;
	private Button butHide;
	private String sTitle, sPercent;
	
	public SyncProgressDialog(Context context)
	{
		super(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
