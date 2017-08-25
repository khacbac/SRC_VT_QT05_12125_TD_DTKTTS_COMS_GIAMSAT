package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_TTB extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_ttb_stt;
	private TextView tv_supervision_brcd_name_ttb;
	private TextView tv_supervision_brcd_cab_type_ttb;
	private Button bt_supervision_brcd_ttb_map, bt_brcd_ttb_delete;
	private EditText edt_supervision_brcd_ttb_macap;
	private TextView edt_supervision_brcd_ttb_nnkhongdat;
	private CheckBox rdo_brcd_ttb_dat;
	private CheckBox rdo_brcd_ttb_khongdat;
	private TextView tv_supervision_brcd_ttb_diengiai;
	private OnEventControlListener onEvent;
	private Supervision_BRCD_Ttb_Entity supervisionbrcd_kcn;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_BRCD_TTB(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_TTB(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.bt_brcd_ttb_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_ttb_delete);
		this.edt_supervision_brcd_ttb_macap = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_ttb_macap);
		this.edt_supervision_brcd_ttb_macap
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supervisionbrcd_kcn.setBox_code(s.toString());
				} else {
					supervisionbrcd_kcn.setBox_code("");
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start,
					int count, int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		this.bt_brcd_ttb_delete.setOnClickListener(this);
		this.tv_supervision_brcd_cab_type_ttb = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_cab_type_ttb);
		this.tv_supervision_brcd_ttb_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_ttb_stt);
		this.tv_supervision_brcd_name_ttb = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_name_ttb);
		this.edt_supervision_brcd_ttb_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_brcd_ttb_nnkhongdat);
		this.tv_supervision_brcd_ttb_diengiai = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_ttb_diengiai);
		this.tv_supervision_brcd_ttb_diengiai.setOnClickListener(this);
		this.rdo_brcd_ttb_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_ttb_dat);
		this.bt_supervision_brcd_ttb_map = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_ttb_map);
		this.bt_supervision_brcd_ttb_map.setOnClickListener(this);
		this.rdo_brcd_ttb_dat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_kcn
									.setStatus(Constants.SUPERVISION_STATUS.DAT);
							rdo_brcd_ttb_khongdat.setChecked(false);

							if (countAcceptCheck > 0) {
								edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_kcn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
								supervisionbrcd_kcn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.rdo_brcd_ttb_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_ttb_khongdat);

		this.rdo_brcd_ttb_khongdat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_kcn
									.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
							rdo_brcd_ttb_dat.setChecked(false);

							if (countNnkdCheck > 0) {
								edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_kcn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
								supervisionbrcd_kcn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.edt_supervision_brcd_ttb_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_brcd_ttb_nnkhongdat);
		this.edt_supervision_brcd_ttb_nnkhongdat.setOnClickListener(this);

	}

	public void setData(
			Supervision_BRCD_Ttb_Entity supervision_BRCD_Ttb_Entity,
			int position) {
		supervisionbrcd_kcn = supervision_BRCD_Ttb_Entity;

		tv_supervision_brcd_ttb_stt.setText(String.valueOf(position + 1));
		tv_supervision_brcd_name_ttb.setText(""
				+ supervisionbrcd_kcn.getBox_Name());
		tv_supervision_brcd_cab_type_ttb.setText(""
				+ supervisionbrcd_kcn.getCab_type());
		
		if (!supervisionbrcd_kcn.getBox_code().equals("")) {
			edt_supervision_brcd_ttb_macap.setText(String
					.valueOf(supervisionbrcd_kcn.getBox_code()));

		} else {
			edt_supervision_brcd_ttb_macap.setText(StringUtil
					.getString(R.string.text_empty));
		}

		supervisionbrcd_kcn.setPosition(position);

		if (supervisionbrcd_kcn.getStatus() == 0) {
			rdo_brcd_ttb_khongdat.setChecked(true);

		}
		if (supervisionbrcd_kcn.getStatus() == 1) {
			rdo_brcd_ttb_dat.setChecked(true);
		}

		if (supervisionbrcd_kcn.getStatus() == 0) {
			rdo_brcd_ttb_khongdat.setChecked(true);

		}
		if (supervisionbrcd_kcn.getStatus() == 1) {
			rdo_brcd_ttb_dat.setChecked(true);
		}

		if (supervisionbrcd_kcn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
			rdo_brcd_ttb_dat.setChecked(true);
		} else {
			rdo_brcd_ttb_dat.setChecked(false);
		}
		if (supervisionbrcd_kcn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
			rdo_brcd_ttb_khongdat.setChecked(true);
		} else {
			rdo_brcd_ttb_khongdat.setChecked(false);
		}

		if (!StringUtil.isNullOrEmpty(supervisionbrcd_kcn.getDesc())) {
			this.tv_supervision_brcd_ttb_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.tv_supervision_brcd_ttb_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (this.tv_supervision_brcd_ttb_diengiai.equals(null)) {
		} else {
			this.tv_supervision_brcd_ttb_diengiai.setText(supervisionbrcd_kcn
					.getDesc());
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionbrcd_kcn.getListCauseUniQualify()
				.size(); i++) {
			if (!supervisionbrcd_kcn.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		countAcceptCheck = 0;

		for (int i = 0; i < this.supervisionbrcd_kcn.getListAcceptance().size(); i++) {
			if (supervisionbrcd_kcn.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| supervisionbrcd_kcn.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}

		if (supervisionbrcd_kcn.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_ttb_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_brcd_ttb_delete:
			if (supervisionbrcd_kcn != null) {
				supervisionbrcd_kcn.setiField(Constants.BG_PIPE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT,
						null, supervisionbrcd_kcn);
			}
			break;
		case R.id.edt_supervision_brcd_ttb_nnkhongdat:
			if (supervisionbrcd_kcn != null) {
				supervisionbrcd_kcn.setiField(Constants.BG_PIPE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT,
						null, supervisionbrcd_kcn);
			}
			break;
		case R.id.tv_supervision_brcd_ttb_diengiai:
			if (supervisionbrcd_kcn != null) {
				supervisionbrcd_kcn.setiField(Constants.BG_PIPE_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT,
						null, supervisionbrcd_kcn);
			}
			break;
		case R.id.bt_supervision_brcd_ttb_map:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_MAP, null,
					supervisionbrcd_kcn);
			break;
		default:
			break;
		}

	}

}
