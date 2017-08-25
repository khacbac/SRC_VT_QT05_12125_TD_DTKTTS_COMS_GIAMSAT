package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BG_CableGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_BG_Cable extends RelativeLayout implements
		OnClickListener {
	private EditText edt_supervision_line_bg_cable_tukhoang;
	private EditText edt_supervision_line_bg_cable_denkhoang;
	private TextView edt_supervision_line_bg_cable_nnkhongdat;
	private CheckBox rdo_line_bg_dat;
	private CheckBox rdo_line_bg_khongdat;
	private TextView edt_supervision_line_bg_cable_diengiai;
	private ImageView iv_supervision_line_bg_cable_delete;
	private OnEventControlListener onEvent;
	private Supervision_Line_BG_CableGSEntity supervisionLineBGCableGS;

	public ItemSupervision_Line_BG_Cable(Context context) {
		super(context);
	}

	public ItemSupervision_Line_BG_Cable(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_supervision_line_bg_cable_tukhoang = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_cable_tukhoang);  
		// this.edt_supervision_line_bg_cable_tukhoang.setOnClickListener(this);
		this.edt_supervision_line_bg_cable_tukhoang
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {

						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGCableGS.setFromDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineBGCableGS
									.setFromDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineBGCableGS.setEdited(true);
					
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

		this.edt_supervision_line_bg_cable_denkhoang = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_cable_denkhoang);
		// this.edt_supervision_line_bg_cable_denkhoang.setOnClickListener(this);
		this.edt_supervision_line_bg_cable_denkhoang
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGCableGS.setToDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineBGCableGS
									.setToDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineBGCableGS.setEdited(true);
						
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

		this.rdo_line_bg_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_dat);
		this.rdo_line_bg_dat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_dat.isChecked()){
					supervisionLineBGCableGS
							.setStatus(Constants.SUPERVISION_STATUS.DAT);
					rdo_line_bg_khongdat.setChecked(false);
					supervisionLineBGCableGS.setEdited(true);
				}else {
					supervisionLineBGCableGS.setStatus(-1);
					supervisionLineBGCableGS.setEdited(true);
				}
				onEvent.onEvent(
						OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
						null, supervisionLineBGCableGS);
			}
		});
		
		this.rdo_line_bg_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_khongdat);
		this.rdo_line_bg_khongdat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_khongdat.isChecked()){
					supervisionLineBGCableGS
							.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
					rdo_line_bg_dat.setChecked(false);
					supervisionLineBGCableGS.setEdited(true);
				}else {
					supervisionLineBGCableGS.setStatus(-1);
					supervisionLineBGCableGS.setEdited(true);
				}
				onEvent.onEvent(
						OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT,
						null, supervisionLineBGCableGS);
			}
		});
		

		this.edt_supervision_line_bg_cable_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_cable_nnkhongdat);
		this.edt_supervision_line_bg_cable_nnkhongdat.setOnClickListener(this);

		this.edt_supervision_line_bg_cable_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_cable_diengiai);
		this.edt_supervision_line_bg_cable_diengiai.setOnClickListener(this);

		this.iv_supervision_line_bg_cable_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_cable_delete);
		this.iv_supervision_line_bg_cable_delete.setOnClickListener(this);
	}
	
	

	public void setData(Supervision_Line_BG_CableGSEntity curItem) {
		supervisionLineBGCableGS = curItem;

		if (supervisionLineBGCableGS.getFromDistance() > 0) {
			edt_supervision_line_bg_cable_tukhoang.setText(String
					.valueOf(supervisionLineBGCableGS.getFromDistance()));

		} else {
			edt_supervision_line_bg_cable_tukhoang.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionLineBGCableGS.getToDistance() > 0) {
			edt_supervision_line_bg_cable_denkhoang.setText(String
					.valueOf(supervisionLineBGCableGS.getToDistance()));

		} else {
			edt_supervision_line_bg_cable_denkhoang.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (supervisionLineBGCableGS.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
			this.rdo_line_bg_dat.setChecked(true);
			this.rdo_line_bg_khongdat.setChecked(false);
		} else {
			this.rdo_line_bg_dat.setChecked(false);
			this.rdo_line_bg_khongdat.setChecked(true);
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineBGCableGS.getFailDesc())) {
			this.edt_supervision_line_bg_cable_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_bg_cable_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}

		int countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineBGCableGS
				.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineBGCableGS.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		if (countNnkdCheck > 0) {
			// if (supervisionLineBGCableGS.getListCauseUniQualify().size() > 0)
			// {
			edt_supervision_line_bg_cable_nnkhongdat.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			edt_supervision_line_bg_cable_nnkhongdat.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_bg_cable_tukhoang:
			if (supervisionLineBGCableGS != null) {
				supervisionLineBGCableGS
						.setIdField(Constants.BG_CABLE_EDIT.FROM_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineBGCableGS);
			}
			break;
		case R.id.edt_supervision_line_bg_cable_denkhoang:
			if (supervisionLineBGCableGS != null) {
				supervisionLineBGCableGS
						.setIdField(Constants.BG_CABLE_EDIT.TO_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineBGCableGS);
			}
			break;
		case R.id.edt_supervision_line_bg_cable_nnkhongdat:
			if (supervisionLineBGCableGS != null) {
				supervisionLineBGCableGS
						.setIdField(Constants.BG_CABLE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineBGCableGS);
			}
			break;
		case R.id.edt_supervision_line_bg_cable_diengiai:
			if (supervisionLineBGCableGS != null) {
				supervisionLineBGCableGS
						.setIdField(Constants.BG_CABLE_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineBGCableGS);
			}
			break;
		case R.id.iv_supervision_line_bg_cable_delete:
			if (supervisionLineBGCableGS != null) {
				supervisionLineBGCableGS
						.setIdField(Constants.BG_CABLE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_CABLE_EDIT,
						null, supervisionLineBGCableGS);
			}
			break;

		default:
			break;
		}

	}
}
