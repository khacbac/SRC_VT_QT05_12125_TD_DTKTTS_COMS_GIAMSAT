package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_BG_Tank extends RelativeLayout implements
		OnClickListener, TextWatcher {
	private TextView tv_supervision_line_bg_tank_name;
	private EditText edt_supervision_line_bg_tank_sodan;
	private CheckBox rdo_supervision_line_bg_tank_dat;
	private CheckBox rdo_supervision_line_bg_tank_khongdat;
	private TextView edt_supervision_line_bg_tank_nn_khongdat;
	private TextView edt_supervision_line_bg_tank_diengiai;
	private ImageView iv_supervision_line_bg_tank_vitri;
	private OnEventControlListener onEvent;
	private Supervision_Line_BG_TankGSEntity supervisionLineBGTankGS;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_Line_BG_Tank(Context context) {
		super(context);
	}

	public ItemSupervision_Line_BG_Tank(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.tv_supervision_line_bg_tank_name = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_line_bg_tank_name);

		this.edt_supervision_line_bg_tank_sodan = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_tank_sodan);
		this.edt_supervision_line_bg_tank_sodan.addTextChangedListener(this);

		this.rdo_supervision_line_bg_tank_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_supervision_line_bg_tank_dat);

		this.rdo_supervision_line_bg_tank_dat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionLineBGTankGS
									.setStatus(Constants.TANK_STATUS.DAT);
							supervisionLineBGTankGS.setEdited(true);
							rdo_supervision_line_bg_tank_khongdat
									.setChecked(false);
							
							if (countAcceptCheck > 0) {
								edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						}else if(supervisionLineBGTankGS.getStatus()==Constants.TANK_STATUS.DAT){
							supervisionLineBGTankGS
								.setStatus(Constants.TANK_STATUS.NONE);
							supervisionLineBGTankGS.setEdited(true);
						}
//						onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_DAT, null,
//								supervisionLineBGTankGS);
					}
				});

		this.rdo_supervision_line_bg_tank_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_supervision_line_bg_tank_khongdat);

		this.rdo_supervision_line_bg_tank_khongdat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionLineBGTankGS
									.setStatus(Constants.TANK_STATUS.KHONG_DAT);
							supervisionLineBGTankGS.setEdited(true);
							rdo_supervision_line_bg_tank_dat.setChecked(false);
							
							if (countNnkdCheck > 0) {
								edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						}else if(supervisionLineBGTankGS.getStatus()==Constants.TANK_STATUS.KHONG_DAT){
							supervisionLineBGTankGS
								.setStatus(Constants.TANK_STATUS.NONE);
							supervisionLineBGTankGS.setEdited(true);
						}
//						onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT, null,
//								supervisionLineBGTankGS);
					}
				});

		this.edt_supervision_line_bg_tank_nn_khongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_tank_nn_khongdat);
		this.edt_supervision_line_bg_tank_nn_khongdat.setOnClickListener(this);

		this.edt_supervision_line_bg_tank_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_tank_diengiai);
		this.edt_supervision_line_bg_tank_diengiai.setOnClickListener(this);

		this.iv_supervision_line_bg_tank_vitri = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_tank_vitri);
		this.iv_supervision_line_bg_tank_vitri.setOnClickListener(this);
	}

	public void setData(Supervision_Line_BG_TankGSEntity curItem) {
		supervisionLineBGTankGS = curItem;
		tv_supervision_line_bg_tank_name.setText(supervisionLineBGTankGS
				.getTankName());
		if (supervisionLineBGTankGS.getNumberPart() > 0) {
			edt_supervision_line_bg_tank_sodan.setText(String
					.valueOf(supervisionLineBGTankGS.getNumberPart()));
		} else {
			edt_supervision_line_bg_tank_sodan.setText("");
		}
		// if (supervisionLineBGTankGS.getNumberPart() > 0) {
		// edt_supervision_line_bg_tank_sodan.setText(String
		// .valueOf(supervisionLineBGTankGS.getNumberPart()));
		// }
		if (supervisionLineBGTankGS.getStatus() == Constants.TANK_STATUS.DAT) {
			rdo_supervision_line_bg_tank_dat.setChecked(true);
		} else {
			rdo_supervision_line_bg_tank_dat.setChecked(false);
		}
		if (supervisionLineBGTankGS.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
			rdo_supervision_line_bg_tank_khongdat.setChecked(true);
		} else {
			rdo_supervision_line_bg_tank_khongdat.setChecked(false);
		}
		if (!StringUtil.isNullOrEmpty(supervisionLineBGTankGS.getFailDesc())) {
			this.edt_supervision_line_bg_tank_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_bg_tank_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineBGTankGS
				.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineBGTankGS.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		countAcceptCheck = 0;

		for (int i = 0; i < this.supervisionLineBGTankGS.getListAcceptance()
				.size(); i++) {
			if (supervisionLineBGTankGS.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| supervisionLineBGTankGS.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}

		if (supervisionLineBGTankGS.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_tank_nn_khongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}

		if (supervisionLineBGTankGS.getLatLocation() != Constants.ID_DOUBLE_ENTITY_DEFAULT) {
			iv_supervision_line_bg_tank_vitri.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location));
		} else {
			iv_supervision_line_bg_tank_vitri.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location_disable));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_bg_tank_nn_khongdat:
			if (supervisionLineBGTankGS != null) {
				supervisionLineBGTankGS
						.setiField(Constants.BG_TANK_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_TANK_EDIT,
						null, supervisionLineBGTankGS);
			}
			break;
		case R.id.edt_supervision_line_bg_tank_diengiai:
			if (supervisionLineBGTankGS != null) {
				supervisionLineBGTankGS
						.setiField(Constants.BG_TANK_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_TANK_EDIT,
						null, supervisionLineBGTankGS);
			}
			break;
		case R.id.iv_supervision_line_bg_tank_vitri:
			if (supervisionLineBGTankGS != null) {
				supervisionLineBGTankGS
						.setiField(Constants.BG_TANK_EDIT.LOCATION);
				onEvent.onEvent(OnEventControlListener.EVENT_LOCATION, null,
						supervisionLineBGTankGS);
			}
			break;
		default:
			break;
		}

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		this.requestFocus();
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		if (!StringUtil.isNullOrEmpty(s.toString())) {
			supervisionLineBGTankGS.setNumberPart(Long.parseLong(s.toString()));
		} else
			supervisionLineBGTankGS.setNumberPart(Constants.ID_ENTITY_DEFAULT);
		supervisionLineBGTankGS.setEdited(true);
	}
}