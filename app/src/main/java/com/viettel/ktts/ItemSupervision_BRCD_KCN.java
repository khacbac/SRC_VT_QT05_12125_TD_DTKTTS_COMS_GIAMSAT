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

import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_KCN extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_kcn_stt;
	private TextView tv_supervision_name_brcd_kcn;
	private EditText edt_supervision_brcd_kcn_macap;
	private Button bt_supervision_brcd_kcn_next,
			bt_supervision_brcd_giamsat_kcn, bt_brcd_kcn_delete;
	
	private OnEventControlListener onEvent;
	private Supervision_BRCD_Kcn_Entity supervisionbrcd_kcn;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;

	public ItemSupervision_BRCD_KCN(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_KCN(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.tv_supervision_brcd_kcn_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_kcn_stt);
		this.tv_supervision_name_brcd_kcn = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_name_brcd_kcn);

		this.edt_supervision_brcd_kcn_macap = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_kcn_macap);
		this.edt_supervision_brcd_kcn_macap = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_kcn_macap);
		this.edt_supervision_brcd_kcn_macap.setOnClickListener(this);
		this.edt_supervision_brcd_kcn_macap
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_kcn.setCable_Code(s.toString());
						} else {
							supervisionbrcd_kcn.setCable_Code("");
						}
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

		this.bt_supervision_brcd_giamsat_kcn = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_giamsat_kcn);
		this.bt_supervision_brcd_giamsat_kcn.setOnClickListener(this);

		this.bt_supervision_brcd_kcn_next = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_kcn_next);
		this.bt_supervision_brcd_kcn_next.setOnClickListener(this);
		this.bt_brcd_kcn_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_kcn_delete);
		this.bt_brcd_kcn_delete.setOnClickListener(this);

	}

	public void setData(
			Supervision_BRCD_Kcn_Entity supervision_BRCD_Kcn_Entity,
			int position) {
		supervisionbrcd_kcn = supervision_BRCD_Kcn_Entity;

		tv_supervision_brcd_kcn_stt.setText(String.valueOf(position + 1));
		tv_supervision_name_brcd_kcn.setText(String.valueOf(position + 1));

		supervisionbrcd_kcn.setCable_Name("Nhánh " + (position + 1));

		if (!supervisionbrcd_kcn.getCable_Code().equals("")) {
			edt_supervision_brcd_kcn_macap.setText(String
					.valueOf(supervisionbrcd_kcn.getCable_Code()));

		} else {
			edt_supervision_brcd_kcn_macap.setText(StringUtil
					.getString(R.string.text_empty));
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_supervision_brcd_kcn_next:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_CHI_TIET,
					null, supervisionbrcd_kcn);
			break;
		case R.id.bt_brcd_kcn_delete:
			if (supervisionbrcd_kcn != null) {

				onEvent.onEvent(
						OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_DELETE,
						null, supervisionbrcd_kcn);
			}
			break;
		case R.id.bt_supervision_brcd_giamsat_kcn:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_KCN_GIAM_SAT,
					null, supervisionbrcd_kcn);
			break;
		default:
			break;
		}

	}

}
