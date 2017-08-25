package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_KCT extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_stt;
	private TextView tv_supervision_name_brcd_kct;
	private EditText edt_supervision_brcd_kct_chieudai;
	private EditText edt_supervision_brcd_kct_macaptruc;
	private Button bt_supervision_brcd_giamsat_kct;
	private TextView tv_supervision_type_brcd_kct;
	private Button bt_brcd_kct_delete;
	private OnEventControlListener onEvent;
	private Supervision_BRCD_Kct_Entity supervisionbrcd_kct;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_BRCD_KCT(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_KCT(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.tv_supervision_brcd_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_stt);
		this.tv_supervision_type_brcd_kct = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_type_brcd_kct);

		this.tv_supervision_name_brcd_kct = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_name_brcd_kct);
		this.bt_brcd_kct_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_kct_delete);
		this.bt_brcd_kct_delete.setOnClickListener(this);
		this.bt_supervision_brcd_giamsat_kct = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_giamsat_kct);
		this.bt_supervision_brcd_giamsat_kct.setOnClickListener(this);

		this.edt_supervision_brcd_kct_macaptruc = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_kct_macaptruc);
		this.edt_supervision_brcd_kct_macaptruc
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_kct.setCable_code(s.toString());
						} else
							supervisionbrcd_kct.setCable_code("");
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
		this.edt_supervision_brcd_kct_chieudai = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_kct_chieudai);
		this.edt_supervision_brcd_kct_chieudai
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_kct.setLength(Integer.parseInt(s
									.toString()));
						} else
							supervisionbrcd_kct
									.setLength(Constants.ID_ENTITY_DEFAULT);
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

	}

	public void setData(
			Supervision_BRCD_Kct_Entity supervision_BRCD_Kct_Entity,
			int position) {
		supervisionbrcd_kct = supervision_BRCD_Kct_Entity;

		tv_supervision_brcd_stt.setText(String.valueOf(position + 1));
		tv_supervision_name_brcd_kct.setText(String.valueOf(position + 1));
		tv_supervision_type_brcd_kct.setText(""
				+ supervisionbrcd_kct.getCable_type());

		supervisionbrcd_kct.setPosition(position);

		if (!supervisionbrcd_kct.getCable_code().equals("")) {
			edt_supervision_brcd_kct_macaptruc.setText(String
					.valueOf(supervisionbrcd_kct.getCable_code()));

		} else {
			edt_supervision_brcd_kct_macaptruc.setText(StringUtil
					.getString(R.string.text_empty));
		}
		if (supervisionbrcd_kct.getLength() > 0) {
			edt_supervision_brcd_kct_chieudai.setText(String
					.valueOf(supervisionbrcd_kct.getLength()));

		} else {
			edt_supervision_brcd_kct_chieudai.setText(StringUtil
					.getString(R.string.text_empty));
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_supervision_brcd_giamsat_kct:
			if (supervisionbrcd_kct != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_GIAM_SAT,
						null, supervisionbrcd_kct);
			}
			break;
		case R.id.bt_brcd_kct_delete:
			if (supervisionbrcd_kct != null) {
				supervisionbrcd_kct.setIdField(Constants.BG_PIPE_EDIT.DELETE);
				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT,
						null, supervisionbrcd_kct);
			}
			break;
		default:
			break;
		}

	}

}
