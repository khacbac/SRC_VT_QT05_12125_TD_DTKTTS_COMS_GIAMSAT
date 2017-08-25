package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_Line_HG_PillarGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_HG_Pillar extends RelativeLayout implements
		OnClickListener {
	private TextView edt_supervision_line_hg_pillar_code;
	private TextView edt_supervision_line_hg_pillar_nnkhongdat;
	private TextView edt_supervision_line_hg_pillar_diengiai;
	private ImageView iv_supervision_line_hg_pillar_delete;
	private OnEventControlListener onEvent;
	private Supervision_Line_HG_PillarGSEntity supervisionLineHGPillarGS;	

	public ItemSupervision_Line_HG_Pillar(Context context) {
		super(context);
	}

	public ItemSupervision_Line_HG_Pillar(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.edt_supervision_line_hg_pillar_code = (TextView) rowRoot
				.findViewById(R.id.edt_itemsupervision_line_hg_pillar_code);
		this.edt_supervision_line_hg_pillar_code.setOnClickListener(this);

		this.edt_supervision_line_hg_pillar_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_itemsupervision_line_hg_pillar_nnkhongdat);
		this.edt_supervision_line_hg_pillar_nnkhongdat.setOnClickListener(this);

		this.edt_supervision_line_hg_pillar_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_itemsupervision_line_hg_pillar_diengiai);
		this.edt_supervision_line_hg_pillar_diengiai.setOnClickListener(this);

		this.iv_supervision_line_hg_pillar_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_itemsupervision_line_hg_pillar_delete);
		this.iv_supervision_line_hg_pillar_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Line_HG_PillarGSEntity curItem) {
		supervisionLineHGPillarGS = curItem;

		if (!StringUtil.isNullOrEmpty(supervisionLineHGPillarGS.getName())) {
			edt_supervision_line_hg_pillar_code
					.setText(supervisionLineHGPillarGS.getName());

		} else {
			edt_supervision_line_hg_pillar_code.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineHGPillarGS.getFailDesc())) {
			this.edt_supervision_line_hg_pillar_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_hg_pillar_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}
		
		int countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineHGPillarGS.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineHGPillarGS.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		if (countNnkdCheck > 0) {
		//if (supervisionLineHGPillarGS.getListCauseUniQualify().size() > 0) {
			edt_supervision_line_hg_pillar_nnkhongdat.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			edt_supervision_line_hg_pillar_nnkhongdat.setText(StringUtil
					.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edt_itemsupervision_line_hg_pillar_code:
			if (supervisionLineHGPillarGS != null) {
				supervisionLineHGPillarGS
						.setIdField(Constants.HG_PILLAR_EDIT.CODE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PILLAR_EDIT,
						null, supervisionLineHGPillarGS);
			}
			break;
		case R.id.edt_itemsupervision_line_hg_pillar_diengiai:
			if (supervisionLineHGPillarGS != null) {
				supervisionLineHGPillarGS
						.setIdField(Constants.HG_PILLAR_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PILLAR_EDIT,
						null, supervisionLineHGPillarGS);
			}
			break;
		case R.id.edt_itemsupervision_line_hg_pillar_nnkhongdat:
			if (supervisionLineHGPillarGS != null) {
				supervisionLineHGPillarGS
						.setIdField(Constants.HG_PILLAR_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PILLAR_EDIT,
						null, supervisionLineHGPillarGS);
			}
			break;

		case R.id.iv_itemsupervision_line_hg_pillar_delete:
			if (supervisionLineHGPillarGS != null) {
				supervisionLineHGPillarGS
						.setIdField(Constants.HG_PILLAR_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PILLAR_EDIT,
						null, supervisionLineHGPillarGS);
			}
			break;

		default:
			break;
		}
	}

}