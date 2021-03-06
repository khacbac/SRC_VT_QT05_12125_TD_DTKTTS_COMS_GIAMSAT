package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Measurement_Detail_ConstrController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Measure_ConstrController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Measurement_Detail_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_HangEntity;
import com.viettel.database.entity.Supervision_Measure_ConstrEntity;
import com.viettel.database.field.Measurement_Detail_ConstrField;
import com.viettel.database.field.Supervision_Measure_ConstrField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_BG_Measure_ConstrAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_Measure_ConstrActivity extends
		LineBaseActivity {
	private final String TAG = this.getClass().getSimpleName();
	private View spvLineHG_MSConstrView;
	private TextView tv_constr_line_measure_constr_dropdown;
	private TextView tv_constr_line_measure_constr_info_line_value;
	private TextView tv_constr_line_measure_constr_info_line_station_code_value;
	private ListView lv_line_bg_measure_constr_list;
	private Button btn_line_bg_measure_constr_save;
	private ImageButton btn_line_bg_measure_constr_add;
	private RelativeLayout rl_supervision_line_bg_measure_constr;

	private Bundle bundleData;

	private int iDesignInfo = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;

	private Supervision_Line_HangEntity supervision_Line_HangData;
	private Supervision_Line_BG_Measure_ConstrAdapter measureConstrAdapter;
	private List<Supervision_Measure_ConstrEntity> listMeasureConstr;
	private Supervision_Measure_ConstrEntity curEditItem;
	private boolean outOfKey = false;

	private Supervision_Measure_ConstrController supervisionMeasureController;
	private Measurement_Detail_ConstrController measurementDetailController;

	/* Cong trinh giam sat */
	private Constr_Construction_EmployeeEntity itemConstrData;

	private float positionTouch = 0f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_hg_measure_constr_activity);
		setTitle(getString(R.string.line_hang_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvLineHG_MSConstrView = addView(R.layout.supervision_line_hg_measure_constr_activity, R.id.rl_supervision_line_bg_measure_constr);
		this.tv_constr_line_measure_constr_dropdown = (TextView) spvLineHG_MSConstrView
				.findViewById(R.id.tv_constr_line_measure_constr_dropdown);
		this.tv_constr_line_measure_constr_dropdown.setOnClickListener(this);

		this.tv_constr_line_measure_constr_info_line_value = (TextView) spvLineHG_MSConstrView
				.findViewById(R.id.tv_constr_line_measure_constr_info_line_value);

		this.tv_constr_line_measure_constr_info_line_station_code_value = (TextView) spvLineHG_MSConstrView
				.findViewById(R.id.tv_constr_line_measure_constr_info_line_station_code_value);

		this.btn_line_bg_measure_constr_save = (Button) spvLineHG_MSConstrView
				.findViewById(R.id.btn_line_bg_measure_constr_save);
		this.btn_line_bg_measure_constr_save.setOnClickListener(this);

		this.btn_line_bg_measure_constr_add = (ImageButton) spvLineHG_MSConstrView
				.findViewById(R.id.btn_line_bg_measure_constr_add);
		this.btn_line_bg_measure_constr_add.setOnClickListener(this);

		rl_supervision_line_bg_measure_constr = (RelativeLayout) spvLineHG_MSConstrView
				.findViewById(R.id.rl_supervision_line_bg_measure_constr);
//		this.rl_supervision_line_bg_measure_constr.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_supervision_line_bg_measure_constr
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_supervision_line_bg_measure_constr
//										.getRootView().getHeight();
//								int heightDifference = screenHeight
//										- (r.bottom - r.top);
//								int resourceId = getResources()
//										.getIdentifier("status_bar_height",
//												"dimen", "android");
//								if (resourceId > 0) {
//									heightDifference -= getResources()
//											.getDimensionPixelSize(resourceId);
//								}
//
//								if (heightDifference > 0) {
//									if (positionTouch >= heightDifference) {
//										rl_supervision_line_bg_measure_constr
//												.setScrollY(0);
//									} else {
//										if ((positionTouch + heightDifference) > screenHeight) {
//											rl_supervision_line_bg_measure_constr.setScrollY(heightDifference
//													- (screenHeight - (Math
//															.round(positionTouch) + heightDifference)));
//										} else
//											rl_supervision_line_bg_measure_constr
//													.setScrollY(heightDifference - 25);
//									}
//								} else {
//									rl_supervision_line_bg_measure_constr
//											.setScrollY(0);
//								}
//
//							}
//						});

		lv_line_bg_measure_constr_list = (ListView) spvLineHG_MSConstrView
				.findViewById(R.id.lv_line_bg_measure_constr_list);

		listMeasureConstr = new ArrayList<Supervision_Measure_ConstrEntity>();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	private void setData() {
		bundleData = this.getIntent().getExtras();
		this.itemConstrData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);

		this.supervision_Line_HangData = (Supervision_Line_HangEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA_EX);

		this.iDesignInfo = bundleData.getInt(IntentConstants.INTENT_DESIGNINFO);

		this.tv_constr_line_measure_constr_dropdown.setText(GloablUtils
				.getStringLineHangInfo(iDesignInfo));

		this.tv_constr_line_measure_constr_info_line_value
				.setText(this.itemConstrData.getConstrCode());

		this.tv_constr_line_measure_constr_info_line_station_code_value
				.setText(itemConstrData.getStationCode());

		supervisionMeasureController = new Supervision_Measure_ConstrController(
				this);
		measurementDetailController = new Measurement_Detail_ConstrController(
				this);

		refreshListView();

		/* Drop down */
		this.listDesignInfo = GloablUtils.getListLineHangInfo("");

		if (itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.btn_line_bg_measure_constr_save.setVisibility(View.GONE);
		}
	}

	public void refreshListView() {
		listMeasureConstr = supervisionMeasureController.getAllMeasureConstr(
				itemConstrData.getSupervision_Constr_Id(),
				Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_LINE_HG);
        Log.d(TAG, "refreshListView() called contruct Id = "
                + itemConstrData.getSupervision_Constr_Id());
        Log.d(TAG, "refreshListView() called listMeasureConstr size = " + listMeasureConstr.size());
        List<Measurement_Detail_ConstrEntity> lstMearsureDetail = null;
		for (Supervision_Measure_ConstrEntity curItem : listMeasureConstr) {
			lstMearsureDetail = measurementDetailController
					.getAllMeasureDetailyByMeasureDetailId(curItem
							.getSupervisionMeasureConstrId());
			Log.d(TAG, "refreshListView() called"
					+ "lstMearsureDetail size = " + lstMearsureDetail.size());
			curItem.setListMeasureDetail(lstMearsureDetail);
		}

		this.measureConstrAdapter
                = new Supervision_Line_BG_Measure_ConstrAdapter(this, listMeasureConstr);
		this.lv_line_bg_measure_constr_list.setAdapter(measureConstrAdapter);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_constr_line_measure_constr_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;

		// click save button
		case R.id.btn_line_bg_measure_constr_save:
			String messageError = "";
			for (Supervision_Measure_ConstrEntity curItemData : listMeasureConstr) {
				messageError = this.checkVailid(curItemData);
				if (!StringUtil.isNullOrEmpty(messageError)) {
					break;
				}
			}
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
			} else {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			break;

		// click add button
		case R.id.btn_line_bg_measure_constr_add:
			Supervision_Measure_ConstrEntity measureConstr = new Supervision_Measure_ConstrEntity();
			this.listMeasureConstr.add(measureConstr);
			this.measureConstrAdapter.notifyDataSetChanged();
			break;
		}
	}

	private String checkVailid(Supervision_Measure_ConstrEntity itemCheck) {
		String sReslut = "";
		try {
			if (StringUtil.isNullOrEmpty(itemCheck.getName())) {
				sReslut = StringUtil
						.getString(R.string.measure_constr_name_empty);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();

			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				if (this.iDesignInfo != dropdownItem.getId()) {
					bundleData = new Bundle();
					bundleData.putSerializable(IntentConstants.INTENT_DATA,
							itemConstrData);
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_HangData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoLineHangActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		// xem thong tin chi tiet
		case OnEventControlListener.EVENT_VIEW_DETAIL:
			curEditItem = (Supervision_Measure_ConstrEntity) data;
			getMeasureDetail(curEditItem);
			break;
		// xac nhan save thong tin do
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			break;
		// su kien xoa 1 dong tren listview
		case OnEventControlListener.EVENT_DELETE_ROW:
			this.curEditItem = (Supervision_Measure_ConstrEntity) data;
			this.showAskOptionDialog(StringUtil
					.getString(R.string.text_delete_title), StringUtil
					.getString(R.string.delete_row_measurement_constr));

			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}

	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();

		if (curEditItem.getSupervisionMeasureConstrId() > 0) {
			supervisionMeasureController.delete(curEditItem);
		}
		listMeasureConstr.remove(curEditItem);
		this.measureConstrAdapter.notifyDataSetChanged();
	}

	public void saveData() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			long idAddMeasure = 0;
			long idAddMeasureDetail = 0;

			supervisionMeasureController = new Supervision_Measure_ConstrController(this);
			measurementDetailController = new Measurement_Detail_ConstrController(this);

			for (Supervision_Measure_ConstrEntity curItem : listMeasureConstr) {
				if (curItem.isNew()) {
					// them moi thong tin do
					Supervision_Measure_ConstrEntity itemAdd = new Supervision_Measure_ConstrEntity();

					idAddMeasure = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Measure_ConstrField.TABLE_NAME);

					if (idAddMeasure == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					itemAdd.setSupervisionMeasureConstrId(idAddMeasure);
					itemAdd.setSupervisionConstrId(itemConstrData
							.getSupervision_Constr_Id());

					itemAdd.setName(curItem.getName());
					itemAdd.setType(Constants.MEASUREMENT_CONSTR_TYPE.MEASURE_LINE_HG);
					itemAdd.setEmployeeId(idUser);
					itemAdd.setSync_Status(Constants.SYNC_STATUS.ADD);
					itemAdd.setIsActive(Constants.ISACTIVE.ACTIVE);
					itemAdd.setProcessId(0);

					supervisionMeasureController.addItem(itemAdd);

					// them moi thong tin do chi tiet
					for (Measurement_Detail_ConstrEntity itemAddDetail : curItem
							.getListMeasureDetail()) {
						Measurement_Detail_ConstrEntity itemDetail = new Measurement_Detail_ConstrEntity();

						idAddMeasureDetail = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Measurement_Detail_ConstrField.TABLE_NAME);
						if (idAddMeasureDetail == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						itemDetail
								.setMeasurementDetailConstrId(idAddMeasureDetail);
						itemDetail.setNameComponent(itemAddDetail
								.getNameComponent());
						itemDetail.setLongValue(itemAddDetail.getLongValue());
						itemDetail.setWidthValue(itemAddDetail.getWidthValue());
						itemDetail.setHeighValue(itemAddDetail.getHeighValue());
						itemDetail.setDiameter(itemAddDetail.getDiameter());
						itemDetail.setOtherValue(itemAddDetail.getOtherValue());
						itemDetail.setSupervisionMeasureConstrId(idAddMeasure);
						itemDetail.setSync_Status(Constants.SYNC_STATUS.ADD);
						itemDetail.setIsActive(Constants.ISACTIVE.ACTIVE);
						itemDetail.setProcessId(0);
						itemDetail.setEmployeeId(idUser);

						measurementDetailController.addItem(itemDetail);
					}
				} else {
					// neu sua thi cap nhat
					if (curItem.isEdited()) {

						// cap nhat thong tin do
						supervisionMeasureController.updateItem(curItem);

						// cap nhat thong tin do chi tiet
						for (Measurement_Detail_ConstrEntity itemEditDetail : curItem
								.getListDelMeasureDetail()) {
							measurementDetailController.delete(itemEditDetail);
						}
						for (Measurement_Detail_ConstrEntity itemEditDetail : curItem
								.getListMeasureDetail()) {
							if (itemEditDetail.getMeasurementDetailConstrId() > 0) {
								// neu danh dau xoa thi xoa di

								measurementDetailController
										.updateItem(itemEditDetail);

							} else {
								Measurement_Detail_ConstrEntity itemDetail = new Measurement_Detail_ConstrEntity();

								idAddMeasureDetail = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Measurement_Detail_ConstrField.TABLE_NAME);
								if (idAddMeasureDetail == 0) {
									outOfKey = true;
									return;
								} else
									outOfKey = false;

								itemDetail
										.setMeasurementDetailConstrId(idAddMeasureDetail);
								itemDetail.setNameComponent(itemEditDetail
										.getNameComponent());
								itemDetail.setLongValue(itemEditDetail
										.getLongValue());
								itemDetail.setWidthValue(itemEditDetail
										.getWidthValue());
								itemDetail.setHeighValue(itemEditDetail
										.getHeighValue());
								itemDetail.setDiameter(itemEditDetail
										.getDiameter());
								itemDetail.setOtherValue(itemEditDetail
										.getOtherValue());
								itemDetail
										.setSupervisionMeasureConstrId(curItem
												.getSupervisionMeasureConstrId());
								itemDetail
										.setSync_Status(Constants.SYNC_STATUS.ADD);
								itemDetail
										.setIsActive(Constants.ISACTIVE.ACTIVE);
								itemDetail.setProcessId(0);
								itemDetail.setEmployeeId(idUser);

								measurementDetailController.addItem(itemDetail);
							}
						}
					}
				}
			}

			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(itemConstrData
					.getSupervision_Constr_Id());

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
//			bundleData = this.getIntent().getExtras();
//			boolean bDeny = checkCauseDenyBackgroundLine(bundleData);
//			Log.i("Check_Deny", String.valueOf(bDeny));
//			if (bDeny) {
//				constr_Controller.updateStatus(
//						itemConstrData.getSupervision_Constr_Id(), 0);
//			} else {
//				constr_Controller.updateStatus(
//						itemConstrData.getSupervision_Constr_Id(), 1);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case Constants.GET_MEASURE_INFO:
			if (resultCode == Activity.RESULT_OK) {
				List<Measurement_Detail_ConstrEntity> listMeasureDetail
                        = (List<Measurement_Detail_ConstrEntity>) data.getSerializableExtra(
                                IntentConstants.INTENT_LST_MEASURE_DETAIL);

				List<Measurement_Detail_ConstrEntity> listDelMeasureDetail
                        = (List<Measurement_Detail_ConstrEntity>) data.getSerializableExtra(
                                IntentConstants.INTENT_LST_DELETE_MEASURE_DETAIL);
				if (curEditItem.getListDelMeasureDetail() != null
						&& curEditItem.getListDelMeasureDetail().size() > 0) {
					this.curEditItem.getListMeasureDetail().clear();
				}
				this.curEditItem.setListMeasureDetail(listMeasureDetail);
//                Log.d(TAG, "onActivityResult: listMeasureDetail size" + listMeasureDetail.size());
                this.curEditItem.setListDelMeasureDetail(listDelMeasureDetail);

				this.measureConstrAdapter.notifyDataSetChanged();
			}
			break;
		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineHG_MSConstrView.findViewById(R.id.actionbar);
		myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
		// icon back
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
				finish();
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
				requestSync(Supervision_Line_HG_Measure_ConstrActivity.this);
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

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			saveData();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_Line_HG_Measure_ConstrActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_Line_HG_Measure_ConstrActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:
			refreshListView();
//			closeProgressDialog();
			SyncTask.closeDialog();
			Toast.makeText(this,
					StringUtil.getString(R.string.text_sync_success),
					Toast.LENGTH_LONG).show();
			break;
		default:
			super.handleModelViewEvent(modelEvent);
			break;
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
}
