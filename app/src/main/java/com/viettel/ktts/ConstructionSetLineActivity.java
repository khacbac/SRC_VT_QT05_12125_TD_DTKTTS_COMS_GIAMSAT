package com.viettel.ktts;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

public class ConstructionSetLineActivity extends HomeBaseActivity {
	private Constr_Construction_EmployeeEntity itemBundleData;	
	private View constrSetLineView;
	private EditText trl_constr_construction_setline_row1_edt_mct;
	private EditText trl_constr_construction_setline_row1_edt_mt;
	private EditText trl_constr_construction_setline_row2_edt_dd;
	private EditText trl_constr_construction_setline_row2_edt_dc;
	private CheckBox cb_constr_construction_setline_lt_tn;
	private CheckBox cb_constr_construction_setline_lt_tt;
	private Button btn_constr_construction_save;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.constructionsetline_layout);
		setTitle(getString(R.string.constr_sonstruction_setline_title));
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		this.initData();
//		setHeader();
	}

	private void initData() {
		/* Lay du lieu task Received */
		
		Bundle bundleData = this.getIntent().getExtras();
		itemBundleData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		constrSetLineView = addView(R.layout.constructionsetline_layout, R.id.rl_login);
		this.trl_constr_construction_setline_row1_edt_mct = (EditText) constrSetLineView
				.findViewById(R.id.trl_constr_construction_setline_row1_edt_mct);
		this.trl_constr_construction_setline_row1_edt_mt = (EditText) constrSetLineView
				.findViewById(R.id.trl_constr_construction_setline_row1_edt_mt);
		this.trl_constr_construction_setline_row2_edt_dd = (EditText) constrSetLineView
				.findViewById(R.id.trl_constr_construction_setline_row2_edt_dd);
		this.trl_constr_construction_setline_row2_edt_dc = (EditText) constrSetLineView
				.findViewById(R.id.trl_constr_construction_setline_row2_edt_dc);
		this.cb_constr_construction_setline_lt_tn = (CheckBox) constrSetLineView
				.findViewById(R.id.cb_constr_construction_setline_lt_tn);
		this.cb_constr_construction_setline_lt_tn
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							cb_constr_construction_setline_lt_tt
									.setChecked(false);
						}
					}
				});
		this.cb_constr_construction_setline_lt_tt = (CheckBox) constrSetLineView
				.findViewById(R.id.cb_constr_construction_setline_lt_tt);
		this.cb_constr_construction_setline_lt_tt
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							cb_constr_construction_setline_lt_tn
									.setChecked(false);
						}
					}
				});
		this.btn_constr_construction_save = (Button) constrSetLineView
				.findViewById(R.id.btn_constr_construction_save);
		this.btn_constr_construction_save.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		setData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_constr_construction_save:
			boolean btn = cb_constr_construction_setline_lt_tn.isChecked();
			boolean btt = cb_constr_construction_setline_lt_tt.isChecked();
			if (!btn && !btt) {
				this.showDialog(
						getString(R.string.constr_sonstruction_setline_noselectline),
						OnEventControlListener.EVENT_CANCE);
				break;
			}
			ConfirmDialog confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save));
			confirmSave.setCancelable(false);
			confirmSave.show();
			break;
		default:
			break;
		}
	}

	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		case OnEventControlListener.EVENT_CONFIRM_OK:
			this.saveData();
			break;
		case OnEventControlListener.EVENT_MESSAGER_OK:
			this.gotoHomeActivity(new Bundle());
			this.finish();
			break;
		default:
			break;
		}
	}

	/**
	 * Ham update lai loai tuyen cong trinh va trang thai cua cong trinh
	 */
	private void saveData() {
		boolean btn = cb_constr_construction_setline_lt_tn.isChecked();
		boolean btt = cb_constr_construction_setline_lt_tt.isChecked();
		int iConstrType = 0;
		if (btn) {
			iConstrType = Constants.CONSTR_TYPE.LINE_BACKGROUND;
		}
		if (btt) {
			iConstrType = Constants.CONSTR_TYPE.LINE_HANG;
		}
		Supervision_ConstrController supervisionController = new Supervision_ConstrController(
				this);
		boolean updateConstr = supervisionController.updateSupvContrType(
				this.itemBundleData.getSupervision_Constr_Id(), iConstrType);
		if (updateConstr) {
			this.showDialog(StringUtil.getString(R.string.text_update_success),
					OnEventControlListener.EVENT_MESSAGER_OK);
		} else {
			this.showDialog(StringUtil.getString(R.string.text_update_error),
					OnEventControlListener.EVENT_MESSAGER_OK);
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) constrSetLineView.findViewById(R.id.actionbar);
		myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
		// buttom back
		myActionBar.setBackAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoHomeActivity(new Bundle());
				finish();
			}

			@Override
			public int getDrawable() {
				return R.drawable.backicon;
			}
		});
		// buttom home
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoHomeActivity(new Bundle());
			}

			@Override
			public int getDrawable() {
				return R.drawable.home;
			}
		});
		/* Dong bo du lieu */
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				requestSync(ConstructionSetLineActivity.this);
			}

			@Override
			public int getDrawable() {
				return R.drawable.icon_rotate;
			}
		});
		// buttom loguot
		myActionBar.addAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				gotoLogOut();
			}

			@Override
			public int getDrawable() {
				return R.drawable.logout;
			}
		});
	}

	private void setData() {		
		if (this.itemBundleData != null) {
			this.trl_constr_construction_setline_row1_edt_mct
					.setText(this.itemBundleData.getConstrCode());
			this.trl_constr_construction_setline_row1_edt_mt
					.setText(this.itemBundleData.getStationCode());
			this.trl_constr_construction_setline_row2_edt_dd
					.setText(this.itemBundleData.getStartPointCode());
			this.trl_constr_construction_setline_row2_edt_dc
					.setText(this.itemBundleData.getEndPointCode());
		}

	}

//	private void requestSync() {
//		if (this.check3GWifi()) {
//			showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
//			Bundle bundle = new Bundle();
//			ActionEvent e = new ActionEvent();
//			e.action = ActionEventConstant.REQEST_SYNC;
//			e.viewData = bundle;
//			e.isBlockRequest = true;
//			e.sender = this;
//			Home_Controller.getInstance().handleViewEvent(e);
//		} else {
//			this.show3GWifiOffline();
//		}
//	}

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:
			SyncTask.closeDialog();
			
			Toast.makeText(this,
					StringUtil.getString(R.string.text_sync_success),
					Toast.LENGTH_LONG).show();
			break;
		default:
			super.handleModelViewEvent(modelEvent);
			break;
		}
		closeProgressDialog();
	}
}
