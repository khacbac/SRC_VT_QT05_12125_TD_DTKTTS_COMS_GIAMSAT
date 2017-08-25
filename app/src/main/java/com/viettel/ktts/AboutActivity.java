package com.viettel.ktts;

import android.os.Bundle;
import android.view.View;

import com.viettel.view.base.BaseActivity;
import com.viettel.view.base.HomeBaseActivity;

public class AboutActivity extends HomeBaseActivity {
	private View aboutView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.title_about_activity));
		initView();
	}

	public void initView() {
		aboutView = addView(R.layout.about_activity, R.id.about_act);
	}

//	@Override
//	public void onItemSelected(int position) {
//
//	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
	}

}
