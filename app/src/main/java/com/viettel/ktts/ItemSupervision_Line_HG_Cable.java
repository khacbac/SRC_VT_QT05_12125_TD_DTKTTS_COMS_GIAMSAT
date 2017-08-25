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
import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_HG_Cable extends RelativeLayout implements
		OnClickListener {
	private EditText edt_line_hg_cable_fromdistance;
	private EditText edt_line_hg_cable_todistance;
	private CheckBox rdo_line_hg_dat;
	private CheckBox rdo_line_hg_khongdat;
	private TextView edt_line_hg_cable_nnkhongdat;
	private TextView edt_line_hg_cable_diengiai;
	private ImageView iv_line_hg_cable_delete;
	private OnEventControlListener onEvent;
	private Supervision_Line_Hg_CableGSEntity supervisionLineHGCableGS;

	public ItemSupervision_Line_HG_Cable(Context context) {
		super(context);
	}

	public ItemSupervision_Line_HG_Cable(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_line_hg_cable_fromdistance = (EditText) rowRoot
				.findViewById(R.id.edt_line_hg_cable_fromdistance);
		// this.edt_line_hg_cable_fromdistance.setOnClickListener(this);
		this.edt_line_hg_cable_fromdistance
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineHGCableGS.setFromDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineHGCableGS
									.setFromDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineHGCableGS.setEdited(true);
					}
				});

		this.edt_line_hg_cable_todistance = (EditText) rowRoot
				.findViewById(R.id.edt_line_hg_cable_todistance);
		// this.edt_line_hg_cable_todistance.setOnClickListener(this);
		this.edt_line_hg_cable_todistance
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineHGCableGS.setToDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineHGCableGS
									.setToDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineHGCableGS.setEdited(true);
					}
				});

		this.rdo_line_hg_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_hg_dat);
		this.rdo_line_hg_dat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionLineHGCableGS
									.setStatus(Constants.SUPERVISION_STATUS.DAT);
							rdo_line_hg_khongdat.setChecked(false);
							supervisionLineHGCableGS.setEdited(true);
						} else{ 
						 if(supervisionLineHGCableGS.getStatus()==Constants.SUPERVISION_STATUS.DAT){
							 supervisionLineHGCableGS.setStatus(-1);
							 supervisionLineHGCableGS.setEdited(true);
						 }
						}
						onEvent.onEvent(
								OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
								null, supervisionLineHGCableGS);
					}
				});
		this.rdo_line_hg_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_hg_khongdat);
		this.rdo_line_hg_khongdat
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							supervisionLineHGCableGS
									.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
							rdo_line_hg_dat.setChecked(false);
							supervisionLineHGCableGS.setEdited(true);
						}else {
							if(supervisionLineHGCableGS.getStatus()==Constants.SUPERVISION_STATUS.CHUADAT){
								supervisionLineHGCableGS.setStatus(-1);
								supervisionLineHGCableGS.setEdited(true);
							}
						}
						onEvent.onEvent(
								OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT,
								null, supervisionLineHGCableGS);
					}
				});

		this.edt_line_hg_cable_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_line_hg_cable_nnkhongdat);
		this.edt_line_hg_cable_nnkhongdat.setOnClickListener(this);

		this.edt_line_hg_cable_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_line_hg_cable_diengiai);
		this.edt_line_hg_cable_diengiai.setOnClickListener(this);

		this.iv_line_hg_cable_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_line_hg_cable_delete);
		this.iv_line_hg_cable_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Line_Hg_CableGSEntity curItem) {
		supervisionLineHGCableGS = curItem;

		if (supervisionLineHGCableGS.getFromDistance() != Constants.ID_ENTITY_DEFAULT) {
			edt_line_hg_cable_fromdistance.setText(String
					.valueOf(supervisionLineHGCableGS.getFromDistance()));

		} else {
			edt_line_hg_cable_fromdistance.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionLineHGCableGS.getToDistance() != Constants.ID_ENTITY_DEFAULT) {
			edt_line_hg_cable_todistance.setText(String
					.valueOf(supervisionLineHGCableGS.getToDistance()));

		} else {
			edt_line_hg_cable_todistance.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionLineHGCableGS.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
			this.rdo_line_hg_dat.setChecked(true);
			this.rdo_line_hg_khongdat.setChecked(false);
		} else {
			this.rdo_line_hg_dat.setChecked(false);
			this.rdo_line_hg_khongdat.setChecked(true);
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineHGCableGS.getFailDesc())) {
			this.edt_line_hg_cable_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_line_hg_cable_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}

		int countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineHGCableGS
				.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineHGCableGS.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		if (countNnkdCheck > 0) {
			// if (supervisionLineHGCableGS.getListCauseUniQualify().size() > 0
			// && (supervisionLineHGCableGS.getStatus() ==
			// Constants.SUPERVISION_STATUS.CHUADAT)) {
			edt_line_hg_cable_nnkhongdat.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			edt_line_hg_cable_nnkhongdat.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edt_line_hg_cable_fromdistance:
			if (supervisionLineHGCableGS != null) {
				supervisionLineHGCableGS
						.setIdField(Constants.HG_CABLE_EDIT.FROM_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineHGCableGS);
			}
			break;
		case R.id.edt_line_hg_cable_todistance:
			if (supervisionLineHGCableGS != null) {
				supervisionLineHGCableGS
						.setIdField(Constants.HG_CABLE_EDIT.TO_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineHGCableGS);
			}
			break;
		case R.id.edt_line_hg_cable_diengiai:
			if (supervisionLineHGCableGS != null) {
				supervisionLineHGCableGS
						.setIdField(Constants.HG_CABLE_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineHGCableGS);
			}
			break;
		case R.id.edt_line_hg_cable_nnkhongdat:
			if (supervisionLineHGCableGS != null) {
				supervisionLineHGCableGS
						.setIdField(Constants.HG_CABLE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineHGCableGS);
			}
			break;

		case R.id.iv_line_hg_cable_delete:
			if (supervisionLineHGCableGS != null) {
				supervisionLineHGCableGS
						.setIdField(Constants.HG_CABLE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineHGCableGS);
			}
			break;

		default:
			break;
		}
	}

}