package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemBts_PowerPole extends RelativeLayout implements
		OnClickListener {
	private TextView tv_tencot;
	private TextView tv_nnkd;
	private TextView et_diengiai;
	private ImageView iv_delete;
	private OnEventControlListener onEvent;
	private Cause_Bts_Power_PoleEntity cause_bts_powerPole;
	//private Context context;

	public ItemBts_PowerPole(Context context) {
		super(context);

//		LayoutInflater inflate = (LayoutInflater) context
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ItemBts_PowerPole(Context context, View rowRoot) {
		super(context);
		//this.context = context;
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		tv_tencot = (TextView) rowRoot
				.findViewById(R.id.bts_power_pole_tv_tencot);
		tv_tencot.setOnClickListener(this);

		tv_nnkd = (TextView) rowRoot.findViewById(R.id.bts_power_pole_tv_nnkd);
		tv_nnkd.setOnClickListener(this);

		et_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_bts_power_pole_diengiai);

		et_diengiai.setOnClickListener(this);

		iv_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_bts_power_pole_delete);

		iv_delete.setOnClickListener(this);
	}

	public void setData(Cause_Bts_Power_PoleEntity cause_bts_powerPole) {
		this.cause_bts_powerPole = cause_bts_powerPole;
		tv_tencot.setText(cause_bts_powerPole.getBts_PowerPoleEntity()
				.getPower_POLE_NAME());

		// this.et_diengiai.setText(cause_bts_powerPole.getBts_PowerPoleEntity()
		// .getFail_DESC());
		if (!StringUtil.isNullOrEmpty(this.cause_bts_powerPole
				.getBts_PowerPoleEntity().getFail_DESC())) {
			this.et_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.et_diengiai.setText(StringUtil.getString(R.string.text_empty));
		}

		int countNnkdCheck = 0;

		for (int i = 0; i < this.cause_bts_powerPole.getListCauseUniQualify().size(); i++) {
			if (!cause_bts_powerPole.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		if (countNnkdCheck > 0) {
		//if (this.cause_bts_powerPole.getListCauseUniQualify().size() > 0) {
			tv_nnkd.setText(StringUtil.getString(R.string.text_view_dot));
		} else {
			tv_nnkd.setText(StringUtil.getString(R.string.text_empty));
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//click ten cot
		case R.id.bts_power_pole_tv_tencot:
			if (this.cause_bts_powerPole != null) {
				this.cause_bts_powerPole
						.setiField(Constants.BTS_POWER_POLE_EDIT.POWER_POLE_NAME);
				onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
						null, this.cause_bts_powerPole);
			}
			break;
		// click nguyen nhan khong dat
		case R.id.bts_power_pole_tv_nnkd:
			cause_bts_powerPole
					.setiField(Constants.BTS_POWER_POLE_EDIT.UNQUALIFY);
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_TANK_UNQUALIFY,
					null, cause_bts_powerPole);
			break;

		// click dien giai
		case R.id.edt_supervision_bts_power_pole_diengiai:
			cause_bts_powerPole
					.setiField(Constants.BTS_POWER_POLE_EDIT.FAIL_DESC);
			onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
					null, cause_bts_powerPole);
			break;

		// click delete
		case R.id.iv_supervision_bts_power_pole_delete:
			onEvent.onEvent(OnEventControlListener.EVENT_DELETE_ROW, null,
					cause_bts_powerPole);
			break;
		default:
			break;
		}
	}
}
