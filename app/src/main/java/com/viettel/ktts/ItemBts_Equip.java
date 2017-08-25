package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemBts_Equip extends RelativeLayout implements OnClickListener {
	private TextView tv_stt;
	private TextView tv_tenhangmuc;
	private CheckBox rd_dat;
	private CheckBox rd_khongdat;
	private TextView tv_nnkd;
	private TextView tv_diengiai;
	private OnEventControlListener onEvent;
	private Cause_Bts_Cat_WorkEntity cause_bts_catWork;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemBts_Equip(Context context) {
		super(context);

		// LayoutInflater inflate = (LayoutInflater) context
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public ItemBts_Equip(Context context, View rowRoot) {
		super(context);
		// this.context = context;
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		tv_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_bts_equip_stt);
		tv_tenhangmuc = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_bts_equip_tenhangmuc);
		rd_dat = (CheckBox) rowRoot
				.findViewById(R.id.rd_supervision_bts_equip_dat);
		rd_khongdat = (CheckBox) rowRoot
				.findViewById(R.id.rd_supervision_bts_equip_khongdat);
		rd_dat.setOnClickListener(this);
		rd_khongdat.setOnClickListener(this);

		tv_nnkd = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_bts_equip_nn_khongdat);
		tv_nnkd.setOnClickListener(this);
		tv_diengiai = (TextView) rowRoot
				.findViewById(R.id.edt_supervision_bts_equip_diengiai);
		tv_diengiai.setOnClickListener(this);
	}

	public void setData(Cause_Bts_Cat_WorkEntity cause_bts_catWork) {
		this.cause_bts_catWork = cause_bts_catWork;
		tv_stt.setText(String.valueOf(cause_bts_catWork.getStt()));
		tv_tenhangmuc.setText(cause_bts_catWork.getTenHangMuc());
		if (cause_bts_catWork.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
			rd_dat.setChecked(true);
		} else
			rd_dat.setChecked(false);

		if (cause_bts_catWork.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
			rd_khongdat.setChecked(true);
		} else
			rd_khongdat.setChecked(false);

		if (!StringUtil.isNullOrEmpty(cause_bts_catWork.getBts_Cat_WorkEntity()
				.getFail_Desc())) {
			this.tv_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.tv_diengiai.setText(StringUtil.getString(R.string.text_empty));
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.cause_bts_catWork.getListCauseUniQualify()
				.size(); i++) {
			if (!cause_bts_catWork.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheck++;
				break;
			}
		}

		countAcceptCheck = 0;

		for (int i = 0; i < this.cause_bts_catWork.getListAcceptance().size(); i++) {
			if (cause_bts_catWork.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| cause_bts_catWork.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}
		if (cause_bts_catWork.getBts_Cat_WorkEntity()
				.getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				tv_nnkd.setText(StringUtil.getString(R.string.text_view_dot));
			} else {
				tv_nnkd.setText(StringUtil.getString(R.string.text_empty));
			}
		} else if(cause_bts_catWork.getBts_Cat_WorkEntity()
				.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
			if (countNnkdCheck > 0) {
				tv_nnkd.setText(StringUtil.getString(R.string.text_view_dot));
			} else {
				tv_nnkd.setText(StringUtil.getString(R.string.text_empty));
			}
		}else {
			tv_nnkd.setText(StringUtil.getString(R.string.text_empty));
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		// click nguyen nhan khong dat
		switch (arg0.getId()) {
		case R.id.edt_supervision_bts_equip_nn_khongdat:
			cause_bts_catWork.setiField(Constants.BTS_EQUIP_EDIT.UNQUALIFY);
			onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY,
					null, cause_bts_catWork);
			break;
		// click dien giai
		case R.id.edt_supervision_bts_equip_diengiai:
			cause_bts_catWork.setiField(Constants.BTS_EQUIP_EDIT.FAIL_DESC);
			onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
					null, cause_bts_catWork);
			break;
		// chon dat
		case R.id.rd_supervision_bts_equip_dat:
			if (rd_dat.isChecked()) {
				cause_bts_catWork.getBts_Cat_WorkEntity().setStatus(
						Constants.BTS_CAT_WORK_STATUS.DAT);
				if (rd_khongdat.isChecked()) {
					rd_khongdat.setChecked(false);

				}
				
				if (countAcceptCheck > 0) {
					tv_nnkd.setText(StringUtil
							.getString(R.string.text_view_dot));
				} else {
					tv_nnkd.setText(StringUtil
							.getString(R.string.text_empty));
				}

			} else {
				if (!rd_khongdat.isChecked()) {
					cause_bts_catWork.getBts_Cat_WorkEntity().setStatus(-1);
				}
			}
			onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
					null, cause_bts_catWork);
			break;
		// chon khong dat
		case R.id.rd_supervision_bts_equip_khongdat:
			if (rd_khongdat.isChecked()) {
				cause_bts_catWork.getBts_Cat_WorkEntity().setStatus(
						Constants.BTS_CAT_WORK_STATUS.KHONG_DAT);
				if (rd_dat.isChecked()) {
					rd_dat.setChecked(false);

				}
				
				if (countNnkdCheck > 0) {
					tv_nnkd.setText(StringUtil.getString(R.string.text_view_dot));
				} else {
					tv_nnkd.setText(StringUtil.getString(R.string.text_empty));
				}

			} else {
				if (!rd_dat.isChecked()) {
					cause_bts_catWork.getBts_Cat_WorkEntity().setStatus(-1);
				}
			}
			onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT,
					null, cause_bts_catWork);
			break;
		default:
			break;
		}
	}
}
