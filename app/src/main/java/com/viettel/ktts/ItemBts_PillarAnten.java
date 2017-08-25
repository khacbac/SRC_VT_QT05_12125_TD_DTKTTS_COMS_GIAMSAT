package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemBts_PillarAnten extends RelativeLayout implements
		OnClickListener {
	private TextView tv_mahieumong;
	private CheckBox rd_dat;
	private CheckBox rd_khongdat;
	private TextView et_diengiai;
	private TextView nnkd;
	private OnEventControlListener onEvent;
	private Cause_Bts_Pillar_AntenEntity cause_bts_pillarAnten;
	private TextView size_drc_thietke;
	private TextView size_drc_thucte;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	// private Context context;

	public ItemBts_PillarAnten(Context context) {
		super(context);

	}

	public ItemBts_PillarAnten(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		tv_mahieumong = (TextView) rowRoot.findViewById(R.id.tv_mahieumong);
		rd_dat = (CheckBox) rowRoot.findViewById(R.id.rd_dat);
		rd_khongdat = (CheckBox) rowRoot.findViewById(R.id.rd_khongdat);
		size_drc_thietke = (TextView) rowRoot
				.findViewById(R.id.size_drc_thietke);
		size_drc_thietke.setOnClickListener(this);

		size_drc_thucte = (TextView) rowRoot.findViewById(R.id.size_drc_thucte);
		size_drc_thucte.setOnClickListener(this);

		rd_dat.setOnClickListener(this);
		rd_khongdat.setOnClickListener(this);

		nnkd = (TextView) rowRoot.findViewById(R.id.bt_nnkd);
		nnkd.setOnClickListener(this);
		et_diengiai = (TextView) rowRoot.findViewById(R.id.iv_anh);
		et_diengiai.setOnClickListener(this);

	}

	public void disableComponent() {
		size_drc_thucte.setEnabled(false);
		size_drc_thietke.setEnabled(false);
		rd_dat.setEnabled(false);
		rd_khongdat.setEnabled(false);
		nnkd.setEnabled(false);
		et_diengiai.setEnabled(false);
	}

	//
	public void setData(Cause_Bts_Pillar_AntenEntity cause_bts_pillarAnten) {
		this.cause_bts_pillarAnten = cause_bts_pillarAnten;
		tv_mahieumong.setText(this.cause_bts_pillarAnten
				.getSupervision_Bts_Pillar_AntenEntity().getFOUNDATION_NAME());
		if (this.cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
				.getStatus() == Constants.BTS_ASSESS_PILLAR.DAT) {
			rd_dat.setChecked(true);
		} else
			rd_dat.setChecked(false);

		if (this.cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
				.getStatus() == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
			rd_khongdat.setChecked(true);
		} else
			rd_khongdat.setChecked(false);

		if (!StringUtil.isNullOrEmpty(this.cause_bts_pillarAnten
				.getSupervision_Bts_Pillar_AntenEntity().getFail_DESC())) {
			this.et_diengiai.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.et_diengiai.setText(StringUtil.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(this.cause_bts_pillarAnten
				.getSupervision_Bts_Pillar_AntenEntity().getDimension_Design())) {
			this.size_drc_thietke.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.size_drc_thietke.setText(StringUtil
					.getString(R.string.text_empty));
		}

		if (!StringUtil.isNullOrEmpty(this.cause_bts_pillarAnten
				.getSupervision_Bts_Pillar_AntenEntity().getDimension_Real())) {
			this.size_drc_thucte.setText(StringUtil
					.getString(R.string.text_view_dot));
		} else {
			this.size_drc_thucte.setText(StringUtil
					.getString(R.string.text_empty));
		}

		countNnkdCheck = 0;

		for (int i = 0; i < this.cause_bts_pillarAnten.getListCauseUniQualify()
				.size(); i++) {
			if (!cause_bts_pillarAnten.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheck++;
				break;
			}
		}
		
		countAcceptCheck = 0;

		for (int i = 0; i < this.cause_bts_pillarAnten.getListAcceptance()
				.size(); i++) {
			if (cause_bts_pillarAnten.getListAcceptance().get(i)
					.getLstNewAttachFile().size() > 0
					|| cause_bts_pillarAnten.getListAcceptance().get(i)
							.getLstAttachFile().size() > 0) {
				countAcceptCheck++;
				break;
			}
		}
		
		if (cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity().getStatus() == Constants.TANK_STATUS.DAT) {
			if (countAcceptCheck > 0) {
				nnkd.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else {
				nnkd.setText(StringUtil
						.getString(R.string.text_empty));
			}
		} else {
			if (countNnkdCheck > 0) {
				nnkd.setText(StringUtil.getString(R.string.text_view_dot));
			} else {
				nnkd.setText(StringUtil.getString(R.string.text_empty));
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// chon dat
		case R.id.rd_dat:
			if (rd_dat.isChecked()) {
				cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
						.setStatus(Constants.BTS_ASSESS_PILLAR.DAT);
				rd_khongdat.setChecked(false);
				if (countAcceptCheck > 0) {
					nnkd.setText(StringUtil
							.getString(R.string.text_view_dot));
				} else {
					nnkd.setText(StringUtil
							.getString(R.string.text_empty));
				}
			} else {
				cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity().setStatus(Constants.BTS_ASSESS_PILLAR.NONE);
//				nnkd.setText(StringUtil
//						.getString(R.string.text_empty));
			}
			onEvent.onEvent(OnEventControlListener.EVENT_CHOICE_ACCESS_DAT,
					null, cause_bts_pillarAnten);

			break;
		// chon khong dat
		case R.id.rd_khongdat:
			if (rd_khongdat.isChecked()) {
				cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity()
						.setStatus(Constants.BTS_ASSESS_PILLAR.KHONG_DAT);
				rd_dat.setChecked(false);
				
				if (countNnkdCheck > 0) {
					nnkd.setText(StringUtil.getString(R.string.text_view_dot));
				} else {
					nnkd.setText(StringUtil.getString(R.string.text_empty));
				}
			} else {
				cause_bts_pillarAnten.getSupervision_Bts_Pillar_AntenEntity().setStatus(Constants.BTS_ASSESS_PILLAR.NONE);
//				nnkd.setText(StringUtil
//						.getString(R.string.text_empty));
			}

			onEvent.onEvent(
					OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT, null,
					cause_bts_pillarAnten);
			break;
		// click nguyen nhan khong dat
		case R.id.bt_nnkd:
			onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY,
					null, this.cause_bts_pillarAnten);
			break;
		// click dien giai
		case R.id.iv_anh:
			if (this.cause_bts_pillarAnten != null) {
				this.cause_bts_pillarAnten
						.setiField(Constants.BTS_PILLAR_EDIT.FAIL_DESC);
				onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
						null, this.cause_bts_pillarAnten);
			}

			break;
		// click chon kich thuoc thiet ke
		case R.id.size_drc_thietke:
			if (this.cause_bts_pillarAnten != null) {
				this.cause_bts_pillarAnten
						.setiField(Constants.BTS_PILLAR_EDIT.THIET_KE);
				onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
						null, this.cause_bts_pillarAnten);
			}

			break;
		// click chon kich thuoc thuc te
		case R.id.size_drc_thucte:
			if (this.cause_bts_pillarAnten != null) {
				this.cause_bts_pillarAnten
						.setiField(Constants.BTS_PILLAR_EDIT.THUC_TE);
				onEvent.onEvent(OnEventControlListener.EVENT_SUPERVISION_EDIT,
						null, this.cause_bts_pillarAnten);
			}

			break;
		default:
			break;
		}
	}

}
