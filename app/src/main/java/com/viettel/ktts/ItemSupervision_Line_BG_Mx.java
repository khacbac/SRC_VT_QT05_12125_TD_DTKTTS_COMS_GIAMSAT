package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_BG_MXGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_BG_Mx extends RelativeLayout implements
		OnClickListener {
	private TextView edt_supervision_line_bg_mx_ten;
	private TextView edt_supervision_line_bg_mx_diengiai;
	private TextView edt_supervision_line_bg_mx_nnkhongdat;
	private ImageView iv_supervision_line_bg_mx_vitri;
	private ImageView iv_supervision_line_bg_mx_delete;
	private OnEventControlListener onEvent;
	private Supervision_Line_BG_MXGSEntity supervisionLineBGMxGS;	

	public ItemSupervision_Line_BG_Mx(Context context) {
		super(context);		
	}

	public ItemSupervision_Line_BG_Mx(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_supervision_line_bg_mx_ten = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_mx_ten);
		this.edt_supervision_line_bg_mx_ten.setOnClickListener(this);

		this.edt_supervision_line_bg_mx_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_mx_diengiai);
		this.edt_supervision_line_bg_mx_diengiai.setOnClickListener(this);

		this.edt_supervision_line_bg_mx_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_mx_nnkhongdat);
		this.edt_supervision_line_bg_mx_nnkhongdat.setOnClickListener(this);

		this.iv_supervision_line_bg_mx_vitri = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_mx_vitri);
		this.iv_supervision_line_bg_mx_vitri.setOnClickListener(this);

		this.iv_supervision_line_bg_mx_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_mx_delete);
		this.iv_supervision_line_bg_mx_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Line_BG_MXGSEntity curItem) {
		supervisionLineBGMxGS = curItem;

		if (!StringUtil.isNullOrEmpty(supervisionLineBGMxGS.getMxName())) {
			edt_supervision_line_bg_mx_ten.setText(supervisionLineBGMxGS
					.getMxName());

		} else {
			edt_supervision_line_bg_mx_ten.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineBGMxGS.getFailDesc())) {
			this.edt_supervision_line_bg_mx_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_bg_mx_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		
		int countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineBGMxGS.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineBGMxGS.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		if (countNnkdCheck > 0) {
		//if (supervisionLineBGMxGS.getListCauseUniQualify().size() > 0) {
			edt_supervision_line_bg_mx_nnkhongdat.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			edt_supervision_line_bg_mx_nnkhongdat.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_bg_mx_ten:
			if (supervisionLineBGMxGS != null) {
				supervisionLineBGMxGS.setIdField(Constants.BG_MX_EDIT.NAME);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineBGMxGS);
			}
			break;
		case R.id.edt_supervision_line_bg_mx_nnkhongdat:
			if (supervisionLineBGMxGS != null) {
				supervisionLineBGMxGS
						.setIdField(Constants.BG_MX_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineBGMxGS);
			}
			break;
		case R.id.edt_supervision_line_bg_mx_diengiai:
			if (supervisionLineBGMxGS != null) {
				supervisionLineBGMxGS
						.setIdField(Constants.BG_MX_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineBGMxGS);
			}
			break;
		case R.id.iv_supervision_line_bg_mx_vitri:
			if (supervisionLineBGMxGS != null) {				
				onEvent.onEvent(OnEventControlListener.EVENT_LOCATION, null,
						supervisionLineBGMxGS);
			}
			break;
		case R.id.iv_supervision_line_bg_mx_delete:
			if (supervisionLineBGMxGS != null) {
				supervisionLineBGMxGS.setIdField(Constants.BG_MX_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineBGMxGS);
			}
			break;

		default:
			break;
		}

	}
}
