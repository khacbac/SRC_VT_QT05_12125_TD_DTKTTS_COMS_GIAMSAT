package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_HG_Mx extends RelativeLayout implements
		OnClickListener {
	private TextView edt_supervision_line_hg_mx_ten;
	private TextView edt_supervision_line_hg_mx_diengiai;
	private TextView edt_supervision_line_hg_mx_nnkhongdat;
	private ImageView iv_supervision_line_hg_mx_vitri;
	private ImageView iv_supervision_line_hg_mx_delete;
	private OnEventControlListener onEvent;
	private Supervision_Line_HG_MXGSEntity supervisionLineHGMxGS;	

	public ItemSupervision_Line_HG_Mx(Context context) {
		super(context);		
	}

	public ItemSupervision_Line_HG_Mx(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_supervision_line_hg_mx_ten = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_hg_mx_ten);
		this.edt_supervision_line_hg_mx_ten.setOnClickListener(this);

		this.edt_supervision_line_hg_mx_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_hg_mx_diengiai);
		this.edt_supervision_line_hg_mx_diengiai.setOnClickListener(this);

		this.edt_supervision_line_hg_mx_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_hg_mx_nnkhongdat);
		this.edt_supervision_line_hg_mx_nnkhongdat.setOnClickListener(this);

		this.iv_supervision_line_hg_mx_vitri = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_hg_mx_vitri);
		this.iv_supervision_line_hg_mx_vitri.setOnClickListener(this);

		this.iv_supervision_line_hg_mx_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_hg_mx_delete);
		this.iv_supervision_line_hg_mx_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Line_HG_MXGSEntity curItem) {
		supervisionLineHGMxGS = curItem;

		if (!StringUtil.isNullOrEmpty(supervisionLineHGMxGS.getMxName())) {
			edt_supervision_line_hg_mx_ten.setText(supervisionLineHGMxGS
					.getMxName());

		} else {
			edt_supervision_line_hg_mx_ten.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineHGMxGS.getFailDesc())) {
			this.edt_supervision_line_hg_mx_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_hg_mx_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		
		int countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineHGMxGS.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineHGMxGS.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		if (countNnkdCheck > 0) {
		//if (supervisionLineHGMxGS.getListCauseUniQualify().size() > 0) {
			edt_supervision_line_hg_mx_nnkhongdat.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			edt_supervision_line_hg_mx_nnkhongdat.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_hg_mx_ten:
			if (supervisionLineHGMxGS != null) {
				supervisionLineHGMxGS.setIdField(Constants.BG_MX_EDIT.NAME);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineHGMxGS);
			}
			break;
		case R.id.edt_supervision_line_hg_mx_nnkhongdat:
			if (supervisionLineHGMxGS != null) {
				supervisionLineHGMxGS
						.setIdField(Constants.BG_MX_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineHGMxGS);
			}
			break;
		case R.id.edt_supervision_line_hg_mx_diengiai:
			if (supervisionLineHGMxGS != null) {
				supervisionLineHGMxGS
						.setIdField(Constants.BG_MX_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineHGMxGS);
			}
			break;
		case R.id.iv_supervision_line_hg_mx_vitri:
			if (supervisionLineHGMxGS != null) {				
				onEvent.onEvent(OnEventControlListener.EVENT_LOCATION, null,
						supervisionLineHGMxGS);
			}
			break;
		case R.id.iv_supervision_line_hg_mx_delete:
			if (supervisionLineHGMxGS != null) {
				supervisionLineHGMxGS.setIdField(Constants.BG_MX_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_MX_EDIT, null,
						supervisionLineHGMxGS);
			}
			break;

		default:
			break;
		}

	}
}
