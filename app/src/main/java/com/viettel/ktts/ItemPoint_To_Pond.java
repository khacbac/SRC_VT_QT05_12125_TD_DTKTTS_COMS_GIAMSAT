package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viettel.database.entity.Special_LocationEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemPoint_To_Pond extends RelativeLayout implements
		OnClickListener {
	private EditText pond_name;
	private EditText from_stage;
	private EditText to_stage;
	private EditText supplie_type;
	private ImageView item_delete;
	private OnEventControlListener onEvent;
	private Special_LocationEntity specialLocationEntity;

	public ItemPoint_To_Pond(Context context) {
		super(context);
	}

	public ItemPoint_To_Pond(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.pond_name = (EditText) rowRoot
				.findViewById(R.id.edt_special_location_pond_name);
		
		this.from_stage = (EditText) rowRoot
				.findViewById(R.id.edt_special_location_pond_fromstage);
		this.from_stage.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					specialLocationEntity.setFromStage(s.toString());
				} else {
					specialLocationEntity.setFromStage("");
					
				}
				specialLocationEntity.setEdited(true);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		this.to_stage = (EditText) rowRoot
				.findViewById(R.id.edt_special_location_pond_tostage);
		this.to_stage.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					specialLocationEntity.setToStage(s.toString());
				} else {
					specialLocationEntity.setToStage("");
					
				}
				specialLocationEntity.setEdited(true);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		this.supplie_type = (EditText) rowRoot
				.findViewById(R.id.edt_special_location_pond_supplie_type);
		this.supplie_type.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					specialLocationEntity.setSupplieType(s.toString());
				} else {
					specialLocationEntity.setSupplieType("");
				}
				specialLocationEntity.setEdited(true);

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		this.item_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_special_location_pond_delete);
		this.item_delete.setOnClickListener(this);
	}

	public void setData(Special_LocationEntity curItem) {
		specialLocationEntity = curItem;

		pond_name.setText(specialLocationEntity.getBridgeName());
		if (StringUtil.isNullOrEmpty(specialLocationEntity.getFromStage())) {
			from_stage.setText(StringUtil.getString(R.string.text_empty));
		} else {
			from_stage.setText(String.valueOf(specialLocationEntity
					.getFromStage()));
		}

		if (StringUtil.isNullOrEmpty(specialLocationEntity.getToStage())) {
			to_stage.setText(StringUtil.getString(R.string.text_empty));
		} else {
			to_stage.setText(String.valueOf(specialLocationEntity.getToStage()));
		}

		if (StringUtil.isNullOrEmpty(specialLocationEntity.getSupplieType())) {
			supplie_type.setText(StringUtil.getString(R.string.text_empty));
		} else {
			supplie_type.setText(String.valueOf(specialLocationEntity
					.getSupplieType()));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_special_location_pond_delete:
			onEvent.onEvent(
					OnEventControlListener.EVENT_DELETE_ROW_POND,
					null, specialLocationEntity);
			break;
		default:
			break;
		}

	}
}
