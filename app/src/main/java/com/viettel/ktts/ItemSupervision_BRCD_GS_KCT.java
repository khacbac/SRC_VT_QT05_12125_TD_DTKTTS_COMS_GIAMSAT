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
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kct_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_GS_KCT extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_giamsat_kcn_stt;
	private TextView edt_supervision_brcd_giamsat_kcn_nnkhongdat;
	private EditText edt_supervision_brcd_gs_kcn_doandau,
			edt_supervision_brcd_gs_kcn_doancuoi;
	private TextView tv_supervision_brcd_giamsat_kcn_diengiai;
	private Button bt_brcd_giamsat_kcn_delete;
	private CheckBox rdo_brcd_giamsat_kcn_dat;
	private CheckBox rdo_brcd_giamsat_kcn_khongdat;
	private OnEventControlListener onEvent;
	private Supervision_BRCD_GiamSat_Kct_Entity supervisionbrcd_tn = new Supervision_BRCD_GiamSat_Kct_Entity();
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_BRCD_GS_KCT(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_GS_KCT(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.bt_brcd_giamsat_kcn_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_giamsat_kcn_delete);
		this.bt_brcd_giamsat_kcn_delete.setOnClickListener(this);
		this.tv_supervision_brcd_giamsat_kcn_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_giamsat_kcn_stt);
		this.edt_supervision_brcd_gs_kcn_doandau = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_gs_kcn_doandau);
		this.edt_supervision_brcd_gs_kcn_doandau.setOnClickListener(this);
		this.edt_supervision_brcd_gs_kcn_doandau
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_tn.setStart_section(Integer
									.parseInt(s.toString()));
						} else {
							supervisionbrcd_tn
									.setStart_section(Constants.ID_ENTITY_DEFAULT);
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
		this.edt_supervision_brcd_gs_kcn_doancuoi = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_gs_kcn_doancuoi);
		this.edt_supervision_brcd_gs_kcn_doancuoi.setOnClickListener(this);

		this.edt_supervision_brcd_gs_kcn_doancuoi
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_tn.setEnd_section(Integer
									.parseInt(s.toString()));
						} else {
							supervisionbrcd_tn
									.setEnd_section(Constants.ID_ENTITY_DEFAULT);
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

		this.edt_supervision_brcd_giamsat_kcn_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_brcd_giamsat_kcn_nnkhongdat);
		this.edt_supervision_brcd_giamsat_kcn_nnkhongdat
				.setOnClickListener(this);
		this.tv_supervision_brcd_giamsat_kcn_diengiai = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_giamsat_kcn_diengiai);
		this.tv_supervision_brcd_giamsat_kcn_diengiai.setOnClickListener(this);
		this.rdo_brcd_giamsat_kcn_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_giamsat_kcn_dat);

		this.rdo_brcd_giamsat_kcn_dat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_tn
									.setStatus(Constants.SUPERVISION_STATUS.DAT);
							rdo_brcd_giamsat_kcn_khongdat.setChecked(false);

							if (countAcceptCheck > 0) {
								edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
								supervisionbrcd_tn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.rdo_brcd_giamsat_kcn_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_brcd_giamsat_kcn_khongdat);

		this.rdo_brcd_giamsat_kcn_khongdat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionbrcd_tn
									.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
							rdo_brcd_giamsat_kcn_dat.setChecked(false);

							if (countNnkdCheck > 0) {
								edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_view_dot));
							} else {
								edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
										.getString(R.string.text_empty));
							}
						} else {
							if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
								supervisionbrcd_tn.setStatus(-1);

								if (countAcceptCheck > 0) {
									edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_view_dot));
								} else {
									edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
											.getString(R.string.text_empty));
								}
							}
						}
					}
				});

		this.edt_supervision_brcd_giamsat_kcn_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_brcd_giamsat_kcn_nnkhongdat);
		this.edt_supervision_brcd_giamsat_kcn_nnkhongdat
				.setOnClickListener(this);

	}

	public void setData(
			Supervision_BRCD_GiamSat_Kct_Entity supervision_BRCD_GiamSat_Kct_Entity,
			int position) {
		supervisionbrcd_tn = supervision_BRCD_GiamSat_Kct_Entity;

		tv_supervision_brcd_giamsat_kcn_stt.setText(String
				.valueOf(position + 1));
		// tv_supervision_name_brcd_giamsat_kcn.setText(String
		// .valueOf(position + 1));

		if (supervisionbrcd_tn.getStart_section() >= 0) {
			edt_supervision_brcd_gs_kcn_doandau.setText(String
					.valueOf(supervisionbrcd_tn.getStart_section()));

		} else {
			edt_supervision_brcd_gs_kcn_doandau.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (supervisionbrcd_tn.getEnd_section() >= 0) {
			edt_supervision_brcd_gs_kcn_doancuoi.setText(String
					.valueOf(supervisionbrcd_tn.getEnd_section()));

		} else {
			edt_supervision_brcd_gs_kcn_doancuoi.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (supervisionbrcd_tn.getStatus() == 0) {
			rdo_brcd_giamsat_kcn_khongdat.setChecked(true);

		}
		if (supervisionbrcd_tn.getStatus() == 1) {
			rdo_brcd_giamsat_kcn_dat.setChecked(true);
		}

		if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
			rdo_brcd_giamsat_kcn_dat.setChecked(true);
		} else {
			rdo_brcd_giamsat_kcn_dat.setChecked(false);
		}
		if (supervisionbrcd_tn.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
			rdo_brcd_giamsat_kcn_khongdat.setChecked(true);
		} else {
			rdo_brcd_giamsat_kcn_khongdat.setChecked(false);
		}

		if (!StringUtil.isNullOrEmpty(supervisionbrcd_tn.getDesc())) {
			this.tv_supervision_brcd_giamsat_kcn_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.tv_supervision_brcd_giamsat_kcn_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (this.tv_supervision_brcd_giamsat_kcn_diengiai.equals(null)) {
		} else {
			this.tv_supervision_brcd_giamsat_kcn_diengiai
					.setText(supervisionbrcd_tn.getDesc());
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
				edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_brcd_giamsat_kcn_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_brcd_giamsat_kcn_delete:
			if (supervisionbrcd_tn != null) {
				supervisionbrcd_tn.setiField(Constants.BG_PIPE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT,
						null, supervisionbrcd_tn);
			}
			break;
		case R.id.edt_supervision_brcd_giamsat_kcn_nnkhongdat:
			if (supervisionbrcd_tn != null) {
				supervisionbrcd_tn.setiField(Constants.BG_PIPE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT,
						null, supervisionbrcd_tn);
			}
			break;
		case R.id.tv_supervision_brcd_giamsat_kcn_diengiai:
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
