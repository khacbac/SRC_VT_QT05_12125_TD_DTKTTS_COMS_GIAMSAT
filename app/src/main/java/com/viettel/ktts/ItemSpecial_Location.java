package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Special_LocationGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSpecial_Location extends RelativeLayout implements
OnClickListener{
	private EditText edt_supervision_line_bg_special_name;
	private TextView edt_supervision_line_bg_special_nnkhongdat;
	private CheckBox rdo_line_bg_special_dat;
	private CheckBox rdo_line_bg_special_kdat;
	private ImageView iv_supervision_line_bg_special_vitridau;
	private ImageView iv_supervision_line_bg_special_vitricuoi;
	
	private OnEventControlListener onEvent;
	private Special_LocationGSEntity supervisionLineBGSpecialGS;
	private int countNnkdCheck = 0;
	private int countAcceptCheck = 0;
	
	public ItemSpecial_Location(Context context) {
		super(context);
	}

	public ItemSpecial_Location(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}
	
	public void initRow(View rowRoot) {
		this.edt_supervision_line_bg_special_name = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_special_name);  
		
		this.rdo_line_bg_special_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_special_dat);
		this.rdo_line_bg_special_dat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_special_dat.isChecked()){
					supervisionLineBGSpecialGS
							.setStatus(Constants.SUPERVISION_STATUS.DAT);
					rdo_line_bg_special_kdat.setChecked(false);
					supervisionLineBGSpecialGS.setEdited(true);
					
					if (countAcceptCheck > 0) {
						edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
								.getString(R.string.text_view_dot));
					} else {
						edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
								.getString(R.string.text_empty));
					}
				}else {
					supervisionLineBGSpecialGS.setStatus(Constants.SUPERVISION_STATUS.CHUA_DANH_GIA);
					supervisionLineBGSpecialGS.setEdited(true);
				}
				onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
						null, supervisionLineBGSpecialGS);
			}
		});
		
		this.rdo_line_bg_special_kdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_special_khongdat);
		this.rdo_line_bg_special_kdat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_special_kdat.isChecked()){
					supervisionLineBGSpecialGS
							.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
					rdo_line_bg_special_dat.setChecked(false);
					supervisionLineBGSpecialGS.setEdited(true);
					
					if (countNnkdCheck > 0) {
						edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
								.getString(R.string.text_view_dot));
					} else {
						edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
								.getString(R.string.text_empty));
					}
				} else {
					supervisionLineBGSpecialGS.setStatus(Constants.SUPERVISION_STATUS.CHUA_DANH_GIA);
					supervisionLineBGSpecialGS.setEdited(true);
				}
				onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT,
						null, supervisionLineBGSpecialGS);
			}
		});

		this.edt_supervision_line_bg_special_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_special_nnkhongdat);
		this.edt_supervision_line_bg_special_nnkhongdat.setOnClickListener(this);
		
		this.iv_supervision_line_bg_special_vitridau = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_special_vitridau);
		this.iv_supervision_line_bg_special_vitridau.setOnClickListener(this);
		
		this.iv_supervision_line_bg_special_vitricuoi = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_special_vitricuoi);
		this.iv_supervision_line_bg_special_vitricuoi.setOnClickListener(this);
	}
	
	public void setData(Special_LocationGSEntity curItem) {
		supervisionLineBGSpecialGS = curItem;
		edt_supervision_line_bg_special_name.setText(supervisionLineBGSpecialGS
				.getSpecialName());
		
		if (supervisionLineBGSpecialGS.getStatus() == Constants.TANK_STATUS.DAT) {
			rdo_line_bg_special_dat.setChecked(true);
		} else {
			rdo_line_bg_special_dat.setChecked(false);
		}
		if (supervisionLineBGSpecialGS.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
			rdo_line_bg_special_kdat.setChecked(true);
		} else {
			rdo_line_bg_special_kdat.setChecked(false);
		}
		
		countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineBGSpecialGS
				.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineBGSpecialGS.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		countAcceptCheck = 0;

		for (int i = 0; i < this.supervisionLineBGSpecialGS.getListAcceptance()
				.size(); i++) {
			if (supervisionLineBGSpecialGS.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| supervisionLineBGSpecialGS.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}
		
		if (supervisionLineBGSpecialGS.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}
		
//		if (countNnkdCheck > 0) {
//			// if (supervisionLineBGTankGS.getListCauseUniQualify().size() > 0)
//			// {
//			edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
//					.getString(R.string.text_view_dot));
//		} else {
//			edt_supervision_line_bg_special_nnkhongdat.setText(StringUtil
//					.getString(R.string.text_empty));
//		}
		if (supervisionLineBGSpecialGS.getLatStartLocation() != Constants.ID_DOUBLE_ENTITY_DEFAULT) {
			iv_supervision_line_bg_special_vitridau.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location));
		} else {
			iv_supervision_line_bg_special_vitridau.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location_disable));
		}
		
		if (supervisionLineBGSpecialGS.getLatEndLocation() != Constants.ID_DOUBLE_ENTITY_DEFAULT) {
			iv_supervision_line_bg_special_vitricuoi.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location));
		} else {
			iv_supervision_line_bg_special_vitricuoi.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_location_disable));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.edt_supervision_line_bg_special_nnkhongdat:
			if (supervisionLineBGSpecialGS != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY,
						null, supervisionLineBGSpecialGS);
			}
			break;
		case R.id.iv_supervision_line_bg_special_vitridau:
			if (supervisionLineBGSpecialGS != null) {
				supervisionLineBGSpecialGS
						.setIdField(Constants.BG_SPECIAL_EDIT.LOCATION_START);
				onEvent.onEvent(OnEventControlListener.EVENT_LOCATION_START, null,
						supervisionLineBGSpecialGS);
			}
			break;
		case R.id.iv_supervision_line_bg_special_vitricuoi:
			if (supervisionLineBGSpecialGS != null) {
				supervisionLineBGSpecialGS
						.setIdField(Constants.BG_SPECIAL_EDIT.LOCATION_END);
				onEvent.onEvent(OnEventControlListener.EVENT_LOCATION_END, null,
						supervisionLineBGSpecialGS);
			}
			break;
		default:
			break;
		}

	}
}
