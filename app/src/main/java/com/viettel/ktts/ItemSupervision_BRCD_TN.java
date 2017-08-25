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
import com.viettel.database.entity.Supervision_BRCD_Tn_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_TN extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_tn_stt;
	private TextView tv_supervision_name_brcd_tn;
	private TextView edt_supervision_brcd_tn_nnkhongdat;
	private TextView tv_supervision_brcd_tn_diengiai;
	private EditText edt_supervision_brcd_tn_macap;
	private Button bt_supervision_brcd_tn_map, bt_brcd_tn_delete;
	private CheckBox rdo_brcd_tn_dat;
	private CheckBox rdo_brcd_tn_khongdat;
	private OnEventControlListener onEvent;
	private Supervision_BRCD_Tn_Entity supervisionbrcd_tn = new Supervision_BRCD_Tn_Entity();
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_BRCD_TN(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_TN(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.bt_brcd_tn_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_tn_delete);
		this.bt_brcd_tn_delete.setOnClickListener(this);
		this.tv_supervision_brcd_tn_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_tn_stt);
		this.tv_supervision_name_brcd_tn = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_name_brcd_tn);
		this.tv_supervision_brcd_tn_diengiai = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_tn_diengiai);
		this.tv_supervision_brcd_tn_diengiai.setOnClickListener(this);
		this.bt_supervision_brcd_tn_map = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_tn_map);
		this.bt_supervision_brcd_tn_map.setOnClickListener(this);
		this.rdo_brcd_tn_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_tn_dat);
		this.edt_supervision_brcd_tn_macap = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_tn_macap);
		this.edt_supervision_brcd_tn_macap
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_tn.setCable_code(s.toString());
						} else {
							supervisionbrcd_tn.setCable_code("");
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

		this.rdo_brcd_tn_dat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_tn
									.setStatus(Constants.SUPERVISION_STATUS.DAT);
							rdo_brcd_tn_khongdat.setChecked(false);

							if (countAcceptCheck > 0) {
								edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
								supervisionbrcd_tn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.rdo_brcd_tn_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_tn_khongdat);

		this.rdo_brcd_tn_khongdat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_tn
									.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
							rdo_brcd_tn_dat.setChecked(false);

							if (countNnkdCheck > 0) {
								edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
								supervisionbrcd_tn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.edt_supervision_brcd_tn_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_brcd_tn_nnkhongdat);
		this.edt_supervision_brcd_tn_nnkhongdat.setOnClickListener(this);

	}

	public void setData(Supervision_BRCD_Tn_Entity supervision_BRCD_Tn_Entity,
			int position) {
		supervisionbrcd_tn = supervision_BRCD_Tn_Entity;

		tv_supervision_brcd_tn_stt.setText(String.valueOf(position + 1));
		tv_supervision_name_brcd_tn.setText(String.valueOf(position + 1));
		if (!supervisionbrcd_tn.getCable_code().equals("")) {
			edt_supervision_brcd_tn_macap.setText(String
					.valueOf(supervisionbrcd_tn.getCable_code()));

		} else {
			edt_supervision_brcd_tn_macap.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionbrcd_tn.getStatus() == 0) {
			rdo_brcd_tn_khongdat.setChecked(true);

		}
		if (supervisionbrcd_tn.getStatus() == 1) {
			rdo_brcd_tn_dat.setChecked(true);
		}

		if (supervisionbrcd_tn.getStatus() == 0) {
			rdo_brcd_tn_khongdat.setChecked(true);

		}
		if (supervisionbrcd_tn.getStatus() == 1) {
			rdo_brcd_tn_dat.setChecked(true);
		}

		if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
			rdo_brcd_tn_dat.setChecked(true);
		} else {
			rdo_brcd_tn_dat.setChecked(false);
		}
		if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
			rdo_brcd_tn_khongdat.setChecked(true);
		} else {
			rdo_brcd_tn_khongdat.setChecked(false);
		}

		if (!StringUtil.isNullOrEmpty(supervisionbrcd_tn.getDesc())) {
			this.tv_supervision_brcd_tn_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.tv_supervision_brcd_tn_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (this.tv_supervision_brcd_tn_diengiai.equals(null)) {
		} else {
			this.tv_supervision_brcd_tn_diengiai.setText(supervisionbrcd_tn
					.getDesc());
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionbrcd_tn.getListCauseUniQualify()
				.size(); i++) {
			if (!supervisionbrcd_tn.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		countAcceptCheck = 0;

		for (int i = 0; i < this.supervisionbrcd_tn.getListAcceptance().size(); i++) {
			if (supervisionbrcd_tn.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| supervisionbrcd_tn.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}

		if (supervisionbrcd_tn.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_tn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_brcd_tn_delete:
			if (supervisionbrcd_tn != null) {
				supervisionbrcd_tn.setiField(Constants.BG_PIPE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT,
						null, supervisionbrcd_tn);
			}
			break;
		case R.id.bt_supervision_brcd_tn_map:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_MAP, null,
					supervisionbrcd_tn);
			break;
		case R.id.edt_supervision_brcd_tn_nnkhongdat:
			if (supervisionbrcd_tn != null) {
				supervisionbrcd_tn.setiField(Constants.BG_PIPE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT,
						null, supervisionbrcd_tn);
			}
			break;
		case R.id.tv_supervision_brcd_tn_diengiai:
			if (supervisionbrcd_tn != null) {
				supervisionbrcd_tn.setiField(Constants.BG_PIPE_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT,
						null, supervisionbrcd_tn);
			}
			break;
		default:
			break;
		}

	}

}
