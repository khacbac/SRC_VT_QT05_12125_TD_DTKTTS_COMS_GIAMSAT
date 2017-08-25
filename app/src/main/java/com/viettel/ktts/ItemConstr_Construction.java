package com.viettel.ktts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ItemConstr_Construction extends RelativeLayout implements
		OnClickListener {
	private TextView tv_stt;
	private TextView tv_macongtrinh;
	private TextView tv_loaicongtrinh;
	private TextView tv_tiendo;
	private TextView tv_trangthai;
	private TextView tv_requestdate;
	private TextView tv_finishdate;
	private ImageView iv_thaotac;
	private ImageView iv_loaituyen;
	private ImageView iv_progress;
	private OnEventControlListener onEvent;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;

	public ItemConstr_Construction(Context context) {
		super(context);
	}

	public ItemConstr_Construction(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		tv_stt = (TextView) rowRoot.findViewById(R.id.tv_stt);
		tv_macongtrinh = (TextView) rowRoot.findViewById(R.id.tv_macongtrinh);
		tv_loaicongtrinh = (TextView) rowRoot
				.findViewById(R.id.tv_loaicongtrinh);
		tv_tiendo = (TextView) rowRoot.findViewById(R.id.tv_tiendo);
		tv_trangthai = (TextView) rowRoot.findViewById(R.id.tv_trangthai);
		tv_requestdate = (TextView) rowRoot.findViewById(R.id.tv_request_date);
		tv_finishdate = (TextView) rowRoot.findViewById(R.id.tv_finish_date);
		iv_thaotac = (ImageView) rowRoot.findViewById(R.id.iv_thaotac);
		iv_loaituyen = (ImageView) rowRoot.findViewById(R.id.iv_loaituyen);
		iv_progress = (ImageView) rowRoot.findViewById(R.id.iv_progress);
		iv_progress.setOnClickListener(this);
		iv_thaotac.setOnClickListener(this);
		iv_loaituyen.setOnClickListener(this);

		rowRoot.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (constr_ConstructionItem != null) {
					onEvent.onEvent(
							OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_INFO,
							null, constr_ConstructionItem);
				}
			}
		});
	}

	public void setData(
			Constr_Construction_EmployeeEntity cur_Constr_ConstructionItem)
			throws Exception {
		constr_ConstructionItem = cur_Constr_ConstructionItem;

		tv_stt.setText(String.valueOf(constr_ConstructionItem.getStt()));

		tv_macongtrinh.setText(this.constr_ConstructionItem.getConstrCode());
		if (constr_ConstructionItem.getSupvConstrType() == 5
				|| constr_ConstructionItem.getSupvConstrType() == 6) {
			tv_tiendo.setText("");
		}else {
			tv_tiendo.setText(this.constr_ConstructionItem.getNameProgress());
	
		}
		tv_loaicongtrinh.setText(GloablUtils
				.getStringConstructionType(this.constr_ConstructionItem
						.getSupvConstrType()));
		// tv_tiendo.setText(GloablUtils
		// .getStringConstructionProgress(this.constr_ConstructionEmployeeItem
		// .getConstrProgressId()));
		

		tv_trangthai.setText(GloablUtils
				.getStringConstructionStatus(this.constr_ConstructionItem
						.getConstrStatusId()));

		tv_requestdate.setText(this.constr_ConstructionItem.getRequestDate());

		// if(constr_ConstructionEmployeeItem.getConstrCode().equals("C.Nhung_02")){
		// System.out.println("vao day");
		// }

		// trang thai cho ban giao mat bang
		if (constr_ConstructionItem.getCatProgressWorkId() > 1) {
			tv_finishdate.setBackgroundColor(Color.parseColor("#00FFFF"));

			long diff = 0;
			if (constr_ConstructionItem.getCatProgressWorkId() == 4) {
				diff = diffDate(
						this.constr_ConstructionItem.getCompletedDate(),
						this.constr_ConstructionItem.getHandOverDate());
			} else {
				diff = diffDate(this.constr_ConstructionItem.getHandOverDate());
			}
			if (diff >= 0) {
				if ((this.constr_ConstructionItem.getDeployExpectedDay() - diff) < 0) {
					tv_finishdate.setBackgroundColor(Color.RED);
				}
				tv_finishdate.setText(String
						.valueOf(this.constr_ConstructionItem
								.getDeployExpectedDay() - diff));
			} else {
				tv_finishdate.setBackgroundColor(Color.GREEN);
				tv_finishdate.setText(StringUtil.EMPTY_STRING);
			}

			// } else {
			// tv_finishdate.setBackgroundColor(Color.parseColor("#00FFFF"));
			// tv_finishdate.setText(StringUtil.EMPTY_STRING);
			// }
		} else {
			tv_finishdate.setBackgroundColor(Color.parseColor("#00FFFF"));
			tv_finishdate.setText(StringUtil.EMPTY_STRING);
		}

		if (this.constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| this.constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.iv_thaotac.setImageResource(R.drawable.icon_lock);
		} else {
			this.iv_thaotac.setImageResource(R.drawable.icon_edit);
		}

		if (constr_ConstructionItem.getSupvConstrType() != Constants.CONSTR_TYPE.LINE) {
			this.iv_loaituyen.setImageResource(R.drawable.icon_lock);
		} else {
			this.iv_loaituyen.setImageResource(R.drawable.icon_edit);
		}

	}

	@SuppressLint("SimpleDateFormat")
	public long diffDate(String date) throws Exception {
		long resuleDate = 0;
		if (!StringUtil.isNullOrEmpty(date)) {
			long sysDate = Calendar.getInstance().getTime().getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
			Date currentDate = format.parse(date);
			resuleDate = (sysDate - currentDate.getTime())
					/ (24 * 60 * 60 * 1000);
		}

		return resuleDate;
	}

	@SuppressLint("SimpleDateFormat")
	public long diffDate(String toDate, String date) throws Exception {
		long resuleDate = 0;
		if (!StringUtil.isNullOrEmpty(date)) {
			// long sysDate = Calendar.getInstance().getTime().getTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
			Date currentDate = format.parse(date);
			Date dToDate = format.parse(toDate);
			resuleDate = (dToDate.getTime() - currentDate.getTime())
					/ (24 * 60 * 60 * 1000);
		}

		return resuleDate;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_progress:
			if (constr_ConstructionItem != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_PROGRESS,
						null, constr_ConstructionItem);
			}
			break;
		case R.id.iv_thaotac:
			if (constr_ConstructionItem != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_MONITOR,
						null, constr_ConstructionItem);
			}
			break;
		case R.id.iv_loaituyen:
			if (constr_ConstructionItem != null) {
				if (constr_ConstructionItem.getSupvConstrType() == Constants.CONSTR_TYPE.LINE) {
					onEvent.onEvent(
							OnEventControlListener.EVENT_CONSTR_CONSTRUCTION_TYPE_OF_LINE,
							null, constr_ConstructionItem);
				}
				break;
			}
		default:
			break;
		}
	}
}
