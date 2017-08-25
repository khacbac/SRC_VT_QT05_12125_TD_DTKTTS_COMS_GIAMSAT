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
import com.viettel.database.entity.Supervision_Line_BG_PipeGSEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_Line_BG_Pipe extends RelativeLayout implements
		OnClickListener {
	private EditText edt_supervision_line_bg_pipe_tube;
	private EditText edt_supervision_line_bg_pipe_tukhoang;
	private EditText edt_supervision_line_bg_pipe_denbe;
	private EditText edt_supervision_line_bg_pipe_denkhoang;
	private TextView edt_supervision_line_bg_pipe_nnkhongdat;
	private TextView edt_supervision_line_bg_pipe_diengiai;
	private ImageView iv_supervision_line_bg_pipe_delete;
	private CheckBox rdo_line_bg_pipe_dat;
	private CheckBox rdo_line_bg_pipe_kdat;
	private OnEventControlListener onEvent;
	private Supervision_Line_BG_PipeGSEntity supervisionLineBGPipeGS;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_Line_BG_Pipe(Context context) {
		super(context);
	}

	public ItemSupervision_Line_BG_Pipe(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.edt_supervision_line_bg_pipe_tube = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_tube);
		// this.edt_supervision_line_bg_pipe_tube.setOnClickListener(this);
		this.edt_supervision_line_bg_pipe_tube
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGPipeGS.setFromTank(s.toString());
						} else
							supervisionLineBGPipeGS.setFromTank("");
						supervisionLineBGPipeGS.setEdited(true);
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

		this.edt_supervision_line_bg_pipe_tukhoang = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_tukhoang);
		// this.edt_supervision_line_bg_pipe_tukhoang.setOnClickListener(this);
		this.edt_supervision_line_bg_pipe_tukhoang
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGPipeGS.setFromDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineBGPipeGS
									.setFromDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineBGPipeGS.setEdited(true);
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

		this.edt_supervision_line_bg_pipe_denbe = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_denbe);
		
		this.rdo_line_bg_pipe_dat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_pipe_dat);
		this.rdo_line_bg_pipe_dat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_pipe_dat.isChecked()){
					supervisionLineBGPipeGS
							.setStatus(Constants.SUPERVISION_STATUS.DAT);
					rdo_line_bg_pipe_kdat.setChecked(false);
					
					if (countAcceptCheck > 0) {
						edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
								.getString(R.string.text_view_dot));
					} else {
						edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
								.getString(R.string.text_empty));
					}
				
				}else {
					supervisionLineBGPipeGS.setStatus(-1);
				}
				supervisionLineBGPipeGS.setEdited(true);
				onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
						null, supervisionLineBGPipeGS);
			}
		});
		
		this.rdo_line_bg_pipe_kdat = (CheckBox) rowRoot
				.findViewById(R.id.rdo_line_bg_pipe_khongdat);
		this.rdo_line_bg_pipe_kdat.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(rdo_line_bg_pipe_kdat.isChecked()){
					supervisionLineBGPipeGS
							.setStatus(Constants.SUPERVISION_STATUS.CHUADAT);
					rdo_line_bg_pipe_dat.setChecked(false);
					
					if (countNnkdCheck > 0) {
						edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
								.getString(R.string.text_view_dot));
					} else {
						edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
								.getString(R.string.text_empty));
					}
				}else {
					supervisionLineBGPipeGS.setStatus(-1);
				}
				supervisionLineBGPipeGS.setEdited(true);
				onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT,
						null, supervisionLineBGPipeGS);
			}
		});
		
		// this.edt_supervision_line_bg_pipe_denbe.setOnClickListener(this);
		this.edt_supervision_line_bg_pipe_denbe
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGPipeGS.setToTank(s.toString());
						} else
							supervisionLineBGPipeGS.setToTank("");
						supervisionLineBGPipeGS.setEdited(true);
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

		this.edt_supervision_line_bg_pipe_denkhoang = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_denkhoang);
		// this.edt_supervision_line_bg_pipe_denkhoang.setOnClickListener(this);
		this.edt_supervision_line_bg_pipe_denkhoang
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionLineBGPipeGS.setToDistance(Long
									.parseLong(s.toString()));
						} else
							supervisionLineBGPipeGS
									.setToDistance(Constants.ID_ENTITY_DEFAULT);
						supervisionLineBGPipeGS.setEdited(true);
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

		this.edt_supervision_line_bg_pipe_nnkhongdat = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_nnkhongdat);
		this.edt_supervision_line_bg_pipe_nnkhongdat.setOnClickListener(this);

		this.edt_supervision_line_bg_pipe_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_line_bg_pipe_diengiai);
		this.edt_supervision_line_bg_pipe_diengiai.setOnClickListener(this);

		this.iv_supervision_line_bg_pipe_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_line_bg_pipe_delete);
		this.iv_supervision_line_bg_pipe_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Line_BG_PipeGSEntity curItem) {
		supervisionLineBGPipeGS = curItem;
		if (!StringUtil.isNullOrEmpty(supervisionLineBGPipeGS.getFromTank())) {
			edt_supervision_line_bg_pipe_tube.setText(supervisionLineBGPipeGS
					.getFromTank());

		} else {
			edt_supervision_line_bg_pipe_tube.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (supervisionLineBGPipeGS.getFromDistance() > 0) {
			edt_supervision_line_bg_pipe_tukhoang.setText(String
					.valueOf(supervisionLineBGPipeGS.getFromDistance()));

		} else {
			edt_supervision_line_bg_pipe_tukhoang.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineBGPipeGS.getToTank())) {
			edt_supervision_line_bg_pipe_denbe.setText(supervisionLineBGPipeGS
					.getToTank());

		} else {
			edt_supervision_line_bg_pipe_denbe.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (supervisionLineBGPipeGS.getToDistance() > 0) {
			edt_supervision_line_bg_pipe_denkhoang.setText(String
					.valueOf(supervisionLineBGPipeGS.getToDistance()));

		} else {
			edt_supervision_line_bg_pipe_denkhoang.setText(StringUtil
					.getString(R.string.text_empty));
		}
		
		if (supervisionLineBGPipeGS.getStatus() == Constants.TANK_STATUS.DAT) {
			rdo_line_bg_pipe_dat.setChecked(true);
		} else {
			rdo_line_bg_pipe_dat.setChecked(false);
		}
		if (supervisionLineBGPipeGS.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
			rdo_line_bg_pipe_kdat.setChecked(true);
		} else {
			rdo_line_bg_pipe_kdat.setChecked(false);
		}

		if (!StringUtil.isNullOrEmpty(supervisionLineBGPipeGS.getFailDesc())) {
			this.edt_supervision_line_bg_pipe_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.edt_supervision_line_bg_pipe_diengiai.setText(StringUtil
					.getString(R.string.text_empty));
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.supervisionLineBGPipeGS
				.getListCauseUniQualify().size(); i++) {
			if (!supervisionLineBGPipeGS.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		countAcceptCheck = 0;

		for (int i = 0; i < this.supervisionLineBGPipeGS.getListAcceptance()
				.size(); i++) {
			if (supervisionLineBGPipeGS.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| supervisionLineBGPipeGS.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}
		
		if (supervisionLineBGPipeGS.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
						.getString(R.string.text_empty));
			}
		}
		
//		if (countNnkdCheck > 0) {
//			edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
//					.getString(R.string.text_view_dot));
//		} else {
//			edt_supervision_line_bg_pipe_nnkhongdat.setText(StringUtil
//					.getString(R.string.text_empty));
//		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edt_supervision_line_bg_pipe_tube:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.FROM_TANK);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.edt_supervision_line_bg_pipe_denbe:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.TO_TANK);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.edt_supervision_line_bg_pipe_tukhoang:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.FROM_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.edt_supervision_line_bg_pipe_denkhoang:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.TO_DISTANCE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.edt_supervision_line_bg_pipe_diengiai:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.FAIL_DESC);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.edt_supervision_line_bg_pipe_nnkhongdat:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.UNQUALIFY);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		case R.id.iv_supervision_line_bg_pipe_delete:
			if (supervisionLineBGPipeGS != null) {
				supervisionLineBGPipeGS
						.setIdField(Constants.BG_PIPE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT,
						null, supervisionLineBGPipeGS);
			}
			break;
		default:
			break;
		}

	}

}
