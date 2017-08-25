package com.viettel.ktts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.viettel.constants.IntentConstants;
import com.viettel.database.entity.Measurement_Detail_ConstrEntity;
import com.viettel.view.base.BaseActivity;
import com.viettel.view.control.Measurement_Constr_DetailAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cuongdk3
 * 
 */
public class Measurement_Constr_DetailActivity extends BaseActivity {
	private View msConstr_DTView;
	private TextView tv_measurement_constr_detail_value;
	private ImageButton btn_measurement_constr_detail_add;
	private ImageButton btn_measurement_constr_detail_save;
	private ListView lv_measurement_constr_detail_list;
//	private LinearLayout rl_measurement_constr_header;

	private List<Measurement_Detail_ConstrEntity> listMeasureDetail;
	private List<Measurement_Detail_ConstrEntity> listDelMeasureDetail = null;
	private Measurement_Constr_DetailAdapter measurementDetailAdapter;
	private Measurement_Detail_ConstrEntity curItem;

//	private float positionTouch = 0f;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.measurement_constr_detail_activity);
		setTitle(getString(R.string.info_measure_detail));
		initView();
		setData();
	}

	public void initView() {
		msConstr_DTView = addView(R.layout.measurement_constr_detail_activity, R.id.rl_measurement_constr_header);
		tv_measurement_constr_detail_value = (TextView) msConstr_DTView.findViewById(R.id.tv_measurement_constr_detail_value);
		btn_measurement_constr_detail_add = (ImageButton) msConstr_DTView.findViewById(R.id.btn_measurement_constr_detail_add);
		btn_measurement_constr_detail_add.setOnClickListener(this);

		btn_measurement_constr_detail_save = (ImageButton) msConstr_DTView.findViewById(R.id.btn_measurement_constr_detail_save);
		btn_measurement_constr_detail_save.setOnClickListener(this);

		lv_measurement_constr_detail_list = (ListView) msConstr_DTView.findViewById(R.id.lv_measurement_constr_detail_list);

		lv_measurement_constr_detail_list.setItemsCanFocus(true);

//		rl_measurement_constr_header = (LinearLayout) findViewById(R.id.rl_measurement_constr_header);
//		rl_measurement_constr_header.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_measurement_constr_header
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_measurement_constr_header
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
//										rl_measurement_constr_header
//												.setScrollY(0);
//									} else {
//										if ((positionTouch + heightDifference) > screenHeight) {
//											rl_measurement_constr_header.setScrollY(heightDifference
//													- (screenHeight - (Math
//															.round(positionTouch) + heightDifference)));
//										} else
//											rl_measurement_constr_header
//													.setScrollY(heightDifference - 45);
//									}
//								} else {
//									rl_measurement_constr_header.setScrollY(0);
//								}
//
//							}
//						});

		listMeasureDetail = new ArrayList<Measurement_Detail_ConstrEntity>();

		listDelMeasureDetail = new ArrayList<Measurement_Detail_ConstrEntity>();
	}

	// public void hideSoftKeyboard(View v) {
	// Activity activity = (Activity) v.getContext();
	// InputMethodManager inputMethodManager = (InputMethodManager) activity
	// .getSystemService(Activity.INPUT_METHOD_SERVICE);
	// inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
	// 0);
	// }

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//
//		positionTouch = event.getY();
//
//		return super.onTouchEvent(event);
//	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		// click save button
		case R.id.btn_measurement_constr_detail_save:
			Intent returnIntent = new Intent();
			Bundle bundleData = new Bundle();
			bundleData.putSerializable(
					IntentConstants.INTENT_LST_MEASURE_DETAIL,
					(Serializable) listMeasureDetail);

			bundleData.putSerializable(
					IntentConstants.INTENT_LST_DELETE_MEASURE_DETAIL,
					(Serializable) listDelMeasureDetail);

			returnIntent.putExtras(bundleData);
			setResult(RESULT_OK, returnIntent);
			this.finish();
			overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);

			break;

		// click add button
		case R.id.btn_measurement_constr_detail_add:
			Measurement_Detail_ConstrEntity measureConstr = new Measurement_Detail_ConstrEntity();
			this.listMeasureDetail.add(measureConstr);
			this.measurementDetailAdapter.notifyDataSetChanged();
			break;
		}
	}

	@SuppressWarnings("unchecked")
	public void setData() {
		Bundle bundleData = this.getIntent().getExtras();
		String nameComponent = (String) bundleData
				.getSerializable(IntentConstants.INTENT_MEASURE_INFO);

		tv_measurement_constr_detail_value.setText(nameComponent);

		listMeasureDetail = (List<Measurement_Detail_ConstrEntity>) bundleData
				.getSerializable(IntentConstants.INTENT_LST_MEASURE_DETAIL);

		listDelMeasureDetail = (List<Measurement_Detail_ConstrEntity>) bundleData
				.getSerializable(IntentConstants.INTENT_LST_DELETE_MEASURE_DETAIL);
		refreshView();
	}

	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		// xoa 1 hang
		case OnEventControlListener.EVENT_DELETE_ROW:
			curItem = (Measurement_Detail_ConstrEntity) data;

			if (curItem.getMeasurementDetailConstrId() > 0) {
				listDelMeasureDetail.add(curItem);
			}

			// cloneLstMeasureDetail(curItem);
			listMeasureDetail.remove(curItem);
			measurementDetailAdapter.notifyDataSetChanged();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	public void refreshView() {
		this.measurementDetailAdapter = new Measurement_Constr_DetailAdapter(
				this, listMeasureDetail);
		this.lv_measurement_constr_detail_list
				.setAdapter(measurementDetailAdapter);
	}

	@Override
	public void onItemSelected(int position) {
		// TODO Auto-generated method stub
		
	}

}
