package com.viettel.ktts;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.DateUtil;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.database.Cat_Employee_Plan_Controller;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.entity.Cat_Employee_Plan_Entity;
import com.viettel.database.field.Cat_Employee_Plan_Field;
import com.viettel.sync.SyncTask;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.MakeNewPlanAdapter;
import com.viettel.view.control.MakeNewPlanAdapter.OnClickEditListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MakePlanActivity extends HomeBaseActivity {
	private View makePlanView;
	boolean isUpdate;
	private Calendar fromCalendar, tempCalendar;
	private Cat_Employee_Plan_Controller plan_Controller;

	// danh sach ke hoach
	private ListView lvPlan;
	private ImageView imv_add_new;
	private TextView tv_description;
	private List<Cat_Employee_Plan_Entity> lstPlans;
	private MakeNewPlanAdapter planAdapter;
	int tempPosition = -1;
	private Dialog planDialog;

	// bien phan trang
	private boolean bFirst = true;
	private boolean bEndList = false;
	private boolean bLoaded = true;
	protected int itemLoaded = 0;
	protected int itemPerLoad = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getString(R.string.title_make_new_plan));
		initView();
	}

	private void initView() {
		plan_Controller = new Cat_Employee_Plan_Controller(this);
		makePlanView = addView(R.layout.make_new_plan_activity,
				R.id.rl_make_plan);

		//
		tv_description = (TextView) makePlanView
				.findViewById(R.id.tv_description);
		imv_add_new = (ImageView) makePlanView.findViewById(R.id.imv_add_new);
		imv_add_new.setOnClickListener(this);
		lvPlan = (ListView) makePlanView.findViewById(R.id.lv_plan);
		lstPlans = new ArrayList<Cat_Employee_Plan_Entity>();
		
		planAdapter = new MakeNewPlanAdapter(MakePlanActivity.this, lstPlans);
		planAdapter.setEditListener(new OnClickEditListener() {

			@Override
			public void clickListener(int position) {
				initEditDialog(position);
			}
		});
		lvPlan.setOnScrollListener(Scroll);
		lvPlan.setAdapter(planAdapter);
		new loadMore().execute();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.imv_add_new:
			initEditDialog(-1);
			break;

		default:
			break;
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_collapse) {
			// if (!isUpdate) {
			//
			// } else {
			// onBackPressed();
			// }
			onBackPressed();

		}
		return super.onOptionsItemSelected(item);
	}

	// them moi mot plan khu nhan vao cap nhat
	public boolean addNewPlan(String planText, Calendar fCalendar) {
		long planID = Ktts_KeyController.getInstance().getKttsNextKey(
				Cat_Employee_Plan_Field.TABLE_NAME);
		if (planID == 0) {
			return false;
		}
		Cat_Employee_Plan_Entity plan_Entity = new Cat_Employee_Plan_Entity();
		plan_Entity.setPlanID(planID);
		plan_Entity.setEmployeeID(GlobalInfo.getInstance().getUserId());
		plan_Entity.setFromDate(fCalendar.getTime());
		plan_Entity.setToDate(fCalendar.getTime());
		plan_Entity.setPlanText(planText);
		plan_Entity.setSync_Status(Constants.SYNC_STATUS.ADD);
		plan_Entity.setProcessId(0);
		plan_Entity.setIsActive(1);
		return plan_Controller.insertPlanEntity(plan_Entity);
	}

	public void sortListByDate() {
		Collections.sort(lstPlans, new Comparator<Cat_Employee_Plan_Entity>() {

			@Override
			public int compare(Cat_Employee_Plan_Entity lhs,
					Cat_Employee_Plan_Entity rhs) {
				// TODO Auto-generated method stub
				return rhs.getFromDate().compareTo(lhs.getFromDate());
			}
		});
	}

	public void visibilityTextDes() {
		if (lstPlans.isEmpty()) {
			tv_description.setVisibility(View.VISIBLE);
		} else {
			tv_description.setVisibility(View.GONE);
		}
	}

	@Override
	public void onItemSelected(int position) {
		if (position == 2) {
			return;
		}
		super.onItemSelected(position);
	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

	public void initEditDialog(int position) {
		final Dialog dialog = new Dialog(MakePlanActivity.this,
				android.R.style.Theme_Translucent_NoTitleBar);
		dialog.setTitle(null);
		dialog.setContentView(R.layout.edit_plan_dialog);
		initViewDialog(dialog, position);
		dialog.show();
		planDialog = dialog;
	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		if (plan_Controller.deletePlanEntity(lstPlans.get(tempPosition))) {
			lstPlans.remove(tempPosition);
			planAdapter.notifyDataSetChanged();
			// refreshListPlan();
			if (planDialog != null && planDialog.isShowing()) {
				Toast.makeText(MakePlanActivity.this,
						getString(R.string.plan_delete_success),
						Toast.LENGTH_LONG).show();
				planDialog.dismiss();
			}
		}
	}

	@Override
	public void actionNegative() {
		super.actionNegative();
		if (planDialog != null && planDialog.isShowing()) {
			planDialog.dismiss();
		}
	}

//	public void refreshListPlan() {
//		lstPlans.clear();
//		lstPlans = plan_Controller.getListPlan(1,8);
//		planAdapter.notifyDataSetChanged();
//	}

	public void initViewDialog(final Dialog dialog, final int position) {
		final TextView tv_date = (TextView) dialog.findViewById(R.id.tv_date);
		ImageView imv_edit_date = (ImageView) dialog
				.findViewById(R.id.imv_choose_date);
		ImageView imv_save = (ImageView) dialog.findViewById(R.id.imv_save);
		ImageView imv_close = (ImageView) dialog.findViewById(R.id.imv_close);
		final EditText ed_make_plan = (EditText) dialog
				.findViewById(R.id.ed_make_plan);
		if (position >= 0) {
			fromCalendar.setTime(lstPlans.get(position).getFromDate());
			tv_date.setText(DateUtil.convertDateToStringPlan(fromCalendar
					.getTime()));
			ed_make_plan.setText(lstPlans.get(position).getPlanText());
		} else {
			tv_date.setText(DateUtil.convertDateToStringPlan(fromCalendar
					.getTime()));
		}

		imv_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (position < 0) {
					dialog.dismiss();
				} else {
					tempPosition = position;
					showAskOptionDialog(MakePlanActivity.this, null,
							getString(R.string.delete_notify),
							getString(R.string.text_ok));
					// dialog.dismiss();
				}
			}
		});
		imv_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (ed_make_plan.getText().toString().isEmpty()) {
					showAlertDialogCheckInRequire(MakePlanActivity.this,
							getString(R.string.no_input_text_plan),
							getString(R.string.text_close_button));
					return;
				}
				if (position >= 0) {
					lstPlans.get(position).setPlanText(
							ed_make_plan.getText().toString());
					lstPlans.get(position).setFromDate(fromCalendar.getTime());
					lstPlans.get(position).setToDate(fromCalendar.getTime());
					if (lstPlans.get(position).getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
						lstPlans.get(position).setSync_Status(
								Constants.SYNC_STATUS.EDIT);
					}
					if (plan_Controller.updatePlanEntity(lstPlans.get(position))) {
						sortListByDate();
						planAdapter.notifyDataSetChanged();
						// refreshListPlan();
						Toast.makeText(MakePlanActivity.this,
								getString(R.string.plan_edit_success),
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					}else {
						showAlertDialogCheckInRequire(MakePlanActivity.this,
								getString(R.string.plan_edit_notify),
								getString(R.string.text_close_button));
						return;
					}
				} else{
					if (addNewPlan(ed_make_plan.getText().toString(),
							fromCalendar)) {
						// refreshListPlan();
						refreshListPlan();
						Toast.makeText(MakePlanActivity.this,
								getString(R.string.plan_add_new_success),
								Toast.LENGTH_LONG).show();
						dialog.dismiss();
					
					} else {
						showAlertDialogCheckInRequire(MakePlanActivity.this,
								getString(R.string.text_out_of_key),
								getString(R.string.text_close_button));
						return;
					}
				}
					
			}
		});
		imv_edit_date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog fromDatePickerDialog = new DatePickerDialog(
						MakePlanActivity.this, new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								tempCalendar.set(year, monthOfYear, dayOfMonth);
								Calendar temp2 = Calendar.getInstance();
								temp2.set(year, monthOfYear, dayOfMonth);
								temp2.add(Calendar.DAY_OF_MONTH, 1);
								if (temp2.getTimeInMillis() <= new Date()
										.getTime()) {
									showAlertDialogCheckInRequire(
											MakePlanActivity.this,
											getString(R.string.error_compare_2date),
											getString(R.string.text_close_button));
								} else if (lstPlans.isEmpty()) {
									fromCalendar.set(year, monthOfYear,
											dayOfMonth);
									tv_date.setText(DateUtil
											.convertDateToStringPlan(fromCalendar
													.getTime()));
								} else if (!lstPlans.isEmpty()
										&& !searchItem(lstPlans, tempCalendar,
												position)) {
									fromCalendar.set(year, monthOfYear,
											dayOfMonth);
									tv_date.setText(DateUtil
											.convertDateToStringPlan(fromCalendar
													.getTime()));
								} else {
									showAlertDialogCheckInRequire(
											MakePlanActivity.this,
											getString(R.string.edit_plan_require2),
											getString(R.string.text_close_button));
								}

							}
						}, fromCalendar.get(Calendar.YEAR), fromCalendar
								.get(Calendar.MONTH), fromCalendar
								.get(Calendar.DAY_OF_MONTH));
				fromDatePickerDialog.show();
			}
		});

		dialog.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss(DialogInterface dialog) {
				fromCalendar = Calendar.getInstance();
				// if (!lstPlans.isEmpty()
				// && (lstPlans.get(0).getFromDate().getTime() >= fromCalendar
				// .getTimeInMillis())) {
				// fromCalendar.setTime(lstPlans.get(0).getFromDate());
				// fromCalendar.add(Calendar.DAY_OF_MONTH, 1);
				// }
				if (!lstPlans.isEmpty()) {
					if (lstPlans.get(0).getFromDate().getTime() >= new Date()
							.getTime()) {
						fromCalendar.setTime(lstPlans.get(0).getFromDate());
						tempCalendar.setTime(lstPlans.get(0).getFromDate());
					}
					fromCalendar.add(Calendar.DAY_OF_MONTH, 1);
					tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
				}

			}
		});

	}

	public boolean searchItem(List<Cat_Employee_Plan_Entity> list,
			Calendar calendar, int position) {
		
		if( position<0 && plan_Controller.checkItemByDate(calendar.getTime())){
			return true;
		}else if (position>=0 && (calendar.getTimeInMillis() != list.get(position).getFromDate().getTime())
				&&plan_Controller.checkItemByDate(calendar.getTime())) {
			return true;
		}
//		int i = 0;
//		while (i < list.size()
//				&& list.get(i).getFromDate().getTime() > new Date().getTime()) {
//			if ((position < 0)
//					&& calendar.getTimeInMillis() == list.get(i).getFromDate()
//							.getTime()) {
//				// inList = true;
//				return true;
//			} else if ((position >= 0)
//					&& (list.get(i).getPlanID() != list.get(position)
//							.getPlanID())
//					&& calendar.getTimeInMillis() == list.get(i).getFromDate()
//							.getTime()) {
//				return true;
//			} else {
//				i++;
//			}
//
//		}
		return false;
	}

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:
			SyncTask.closeDialog();
			refreshListPlan();
			Toast.makeText(this,
					StringUtil.getString(R.string.text_sync_success),
					Toast.LENGTH_LONG).show();
			break;
		default:
			super.handleModelViewEvent(modelEvent);
			break;
		}

	}
	
	public void refreshListPlan(){
		this.itemLoaded = 0;
		this.bEndList = false;
		this.lstPlans.clear();
		this.planAdapter.notifyDataSetChanged();
		new loadMore().execute();
	}
	
	private void getData(int itemLoaded) {
		List<Cat_Employee_Plan_Entity> tempListPlan = plan_Controller.getListPlan(itemLoaded, itemPerLoad,GlobalInfo.getInstance().getUserId());
		lstPlans.addAll(tempListPlan);
		lvPlan.setSelectionFromTop(currentPosition, 0);
		planAdapter.notifyDataSetChanged();
	}
	
	/* phan hien thi phan trang */
	OnScrollListener Scroll = new OnScrollListener() {

		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
		}

		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			if (view.getLastVisiblePosition() == totalItemCount - 1) {
				if (bLoaded && !bEndList && !bFirst) {
					new loadMore().execute();
				}
			}
		}
	};
	private int currentPosition = 0; // vi tri hien tai

	private class loadMore extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			showProgressDialog(StringUtil.getString(R.string.text_loading));
			bLoaded = false;
		}

		@Override
		protected Void doInBackground(Void... params) {
			runOnUiThread(new Runnable() {

				public void run() {
					currentPosition = lvPlan
							.getFirstVisiblePosition();
					getData(itemLoaded);
				}
			});

			return null;
		}

		protected void onPostExecute(Void unused) {
			// Xac dinh ket thuc list chua
			itemLoaded = itemLoaded + itemPerLoad;
			if (lstPlans.size() < itemLoaded) {
				bEndList = true;
			}
			// Thay doi khong phai lan dau lay du lieu
			if (bFirst) {
				bFirst = false;
			}
			bLoaded = true;
			planAdapter.notifyDataSetChanged();
			visibilityTextDes();
			if(currentPosition<=20){
				fromCalendar = Calendar.getInstance();
				tempCalendar = Calendar.getInstance();
				if (!lstPlans.isEmpty()) {
					if (lstPlans.get(0).getFromDate().getTime() >= new Date().getTime()) {
						fromCalendar.setTime(lstPlans.get(0).getFromDate());
						tempCalendar.setTime(lstPlans.get(0).getFromDate());
					}
					fromCalendar.add(Calendar.DAY_OF_MONTH, 1);
					tempCalendar.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
			closeProgressDialog();
		}
	}

}
