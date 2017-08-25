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

import com.viettel.database.entity.Supervision_BRCD_Drop_Entity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemSupervision_BRCD_DROP extends RelativeLayout implements
		OnClickListener {
	private TextView tv_supervision_brcd_drop_stt;
	private TextView tv_supervision_brcd_name_drop;
	private TextView tv_supervision_brcd_cable_type_drop;
	private EditText edt_supervision_brcd_drop_macap;
	private OnEventControlListener onEvent;
	private Supervision_BRCD_Drop_Entity supervisionbrcd_drop;
	private int countAcceptCheck = 0;
	private int countNnkdCheck = 0;
	private Button bt_supervision_brcd_drop_next,
			bt_supervision_brcd_next_giamsat_drop, bt_brcd_drop_delete;

	public ItemSupervision_BRCD_DROP(Context context) {
		super(context);
	}

	public ItemSupervision_BRCD_DROP(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {

		this.tv_supervision_brcd_drop_stt = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_drop_stt);
		this.tv_supervision_brcd_name_drop = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_name_drop);
		this.tv_supervision_brcd_cable_type_drop = (TextView) rowRoot
				.findViewById(R.id.tv_supervision_brcd_cable_type_drop);

		this.edt_supervision_brcd_drop_macap = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_brcd_drop_macap);
		this.edt_supervision_brcd_drop_macap
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supervisionbrcd_drop.setDrop_code(s.toString());
						} else {
							supervisionbrcd_drop.setDrop_code("");
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

		this.bt_supervision_brcd_drop_next = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_drop_next);
		this.bt_supervision_brcd_drop_next.setOnClickListener(this);

		this.bt_supervision_brcd_next_giamsat_drop = (Button) rowRoot
				.findViewById(R.id.bt_supervision_brcd_next_giamsat_drop);
		this.bt_supervision_brcd_next_giamsat_drop.setOnClickListener(this);

		this.bt_brcd_drop_delete = (Button) rowRoot
				.findViewById(R.id.bt_brcd_drop_delete);
		this.bt_brcd_drop_delete.setOnClickListener(this);
		// this.supervisionbrcd_drop.setName("" + name.toString().trim());

	}

	public void setData(
			Supervision_BRCD_Drop_Entity supervision_BRCD_Drop_Entity,
			int position) {
		supervisionbrcd_drop = supervision_BRCD_Drop_Entity;

		tv_supervision_brcd_drop_stt.setText(String.valueOf(position + 1));
		tv_supervision_brcd_cable_type_drop.setText(""
				+ supervisionbrcd_drop.getCab_type());
		supervisionbrcd_drop.setPosition(position);

		tv_supervision_brcd_name_drop.setText(supervisionbrcd_drop.getName());
		// name = supervisionbrcd_drop.getName();
		if (!supervisionbrcd_drop.getDrop_code().equals("")) {
			edt_supervision_brcd_drop_macap.setText(String
					.valueOf(supervisionbrcd_drop.getDrop_code()));

		} else {
			edt_supervision_brcd_drop_macap.setText(StringUtil
					.getString(R.string.text_empty));
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.bt_supervision_brcd_drop_next:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_CHITIET,
					null, supervisionbrcd_drop);
			break;
		case R.id.bt_supervision_brcd_next_giamsat_drop:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_GIAM_SAT,
					null, supervisionbrcd_drop);
			break;
		case R.id.bt_brcd_drop_delete:
			onEvent.onEvent(
					OnEventControlListener.EVENT_SUPERVISION_BRCD_DROP_DELETE,
					null, supervisionbrcd_drop);
			break;
		default:
			break;
		}

	}

}
