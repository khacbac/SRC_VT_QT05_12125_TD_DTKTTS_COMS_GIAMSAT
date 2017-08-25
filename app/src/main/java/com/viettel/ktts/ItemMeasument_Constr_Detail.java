package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viettel.database.entity.Measurement_Detail_ConstrEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

/**
 * @author cuongdk3
 *
 */
public class ItemMeasument_Constr_Detail extends RelativeLayout implements
OnClickListener{

	private Measurement_Detail_ConstrEntity supvMeasureConstr;
	private OnEventControlListener onEvent;
	private EditText edt_measurement_constr_detail_name_component;
	private EditText edt_measurement_constr_detail_long_value;
	private EditText edt_measurement_constr_detail_width_value;
	private EditText edt_measurement_constr_detail_height_value;
	private EditText edt_measurement_constr_detail_dk_value;
	private EditText edt_measurement_constr_detail_other_value;
	private ImageView edt_measurement_constr_detail_delete;

	public ItemMeasument_Constr_Detail(Context context) {
		super(context);
	}

	public ItemMeasument_Constr_Detail(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		edt_measurement_constr_detail_name_component = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_name_component);
		
		this.edt_measurement_constr_detail_name_component
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setNameComponent(s.toString());
				} else {
					supvMeasureConstr.setNameComponent("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_long_value = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_long_value);
		
		this.edt_measurement_constr_detail_long_value
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setLongValue(s.toString());
				} else {
					supvMeasureConstr.setLongValue("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_width_value = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_width_value);
		
		this.edt_measurement_constr_detail_width_value
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setWidthValue(s.toString());
				} else {
					supvMeasureConstr.setWidthValue("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_height_value = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_height_value);
		
		this.edt_measurement_constr_detail_height_value
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setHeighValue(s.toString());
				} else {
					supvMeasureConstr.setHeighValue("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_dk_value = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_dk_value);
		
		this.edt_measurement_constr_detail_dk_value
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setDiameter(s.toString());
				} else {
					supvMeasureConstr.setDiameter("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_other_value = (EditText) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_other_value);
		
		this.edt_measurement_constr_detail_other_value
		.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start,
					int before, int count) {
				// TODO Auto-generated method stub
				if (!StringUtil.isNullOrEmpty(s.toString())) {
					supvMeasureConstr.setOtherValue(s.toString());
				} else {
					supvMeasureConstr.setOtherValue("");
				}
				supvMeasureConstr.setEdited(true);
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
		
		edt_measurement_constr_detail_delete = (ImageView) rowRoot
				.findViewById(R.id.edt_measurement_constr_detail_delete);
		edt_measurement_constr_detail_delete.setOnClickListener(this);
	}
	
	public void setData(Measurement_Detail_ConstrEntity item){
		this.supvMeasureConstr = item;
		
		if (StringUtil.isNullOrEmpty(item.getNameComponent())) {
			edt_measurement_constr_detail_name_component.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_name_component.setText(String.valueOf(item
					.getNameComponent()));
		}
		
		if (StringUtil.isNullOrEmpty(item.getLongValue())) {
			edt_measurement_constr_detail_long_value.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_long_value.setText(String.valueOf(item
					.getLongValue()));
		}
		
		if (StringUtil.isNullOrEmpty(item.getWidthValue())) {
			edt_measurement_constr_detail_width_value.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_width_value.setText(String.valueOf(item
					.getWidthValue()));
		}
		
		if (StringUtil.isNullOrEmpty(item.getHeighValue())) {
			edt_measurement_constr_detail_height_value.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_height_value.setText(String.valueOf(item
					.getHeighValue()));
		}
		
		if (StringUtil.isNullOrEmpty(item.getDiameter())) {
			edt_measurement_constr_detail_dk_value.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_dk_value.setText(String.valueOf(item
					.getDiameter()));
		}
		
		if (StringUtil.isNullOrEmpty(item.getOtherValue())) {
			edt_measurement_constr_detail_other_value.setText(StringUtil.getString(R.string.text_empty));
		} else {
			edt_measurement_constr_detail_other_value.setText(String.valueOf(item
					.getOtherValue()));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click xoa 1 item
		case R.id.edt_measurement_constr_detail_delete:
			onEvent.onEvent(OnEventControlListener.EVENT_DELETE_ROW, null,
					supvMeasureConstr);
			break;
		
		default:
			break;
		}
	}

}
