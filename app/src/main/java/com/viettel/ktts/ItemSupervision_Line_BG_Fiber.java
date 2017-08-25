package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BG_FiberEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_BG_Fiber extends RelativeLayout implements
		OnClickListener {
	private TextView edt_supervision_line_bg_fiber_stt;
	private TextView edt_supervision_line_bg_fiber_ten;
	private TextView edt_supervision_line_bg_fiber_ketqua;
	private OnEventControlListener onEvent;
	private Supervision_Line_BG_FiberEntity supervisionLineBGFiber;

	public ItemSupervision_Line_BG_Fiber(Context context) {
		super(context);
	}

	public ItemSupervision_Line_BG_Fiber(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_supervision_line_bg_fiber_stt = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_fiber_stt);

		this.edt_supervision_line_bg_fiber_ten = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_fiber_ten);

		this.edt_supervision_line_bg_fiber_ketqua = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_fiber_ketqua);
		this.edt_supervision_line_bg_fiber_ketqua.setOnClickListener(this);
	}

	public void setData(Supervision_Line_BG_FiberEntity curItem) {
		supervisionLineBGFiber = curItem;
		this.edt_supervision_line_bg_fiber_stt.setText(String.valueOf(curItem
				.getStt()));

		if (!StringUtil.isNullOrEmpty(supervisionLineBGFiber.getFiber_Name())) {
			this.edt_supervision_line_bg_fiber_ten.setText(curItem
					.getFiber_Name());
		} else {
			this.edt_supervision_line_bg_fiber_ten.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionLineBGFiber.getMeasure_Value_Db() != Constants.ID_ENTITY_DEFAULT) {
			this.edt_supervision_line_bg_fiber_ketqua.setText(String
					.valueOf(curItem.getMeasure_Value_Db()));
		} else {
			this.edt_supervision_line_bg_fiber_ketqua.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_bg_fiber_ketqua:
			if (supervisionLineBGFiber != null) {
				supervisionLineBGFiber
						.setIdField(Constants.BG_FIBER_EDIT.VALUE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_FIBER_EDIT,
						null, supervisionLineBGFiber);
			}
			break;
		default:
			break;
		}

	}
}
