package com.viettel.ktts;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.viettel.database.entity.Supervision_Measure_ConstrEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

/**
 * @author cuongdk3
 *
 */
public class ItemSupervision_Measure_Constr extends RelativeLayout implements
		OnClickListener {

	private EditText edt_supervision_measure_constr;
	private ImageView item_view_detail;
	private ImageView item_delete;
	private Supervision_Measure_ConstrEntity supvMeasureConstr;

	private OnEventControlListener onEvent;

	public ItemSupervision_Measure_Constr(Context context) {
		super(context);
	}

	public ItemSupervision_Measure_Constr(Context context, View rowRoot) {
		super(context);
		initRow(rowRoot);
		onEvent = (OnEventControlListener) context;
	}

	public void initRow(View rowRoot) {
		this.edt_supervision_measure_constr = (EditText) rowRoot
				.findViewById(R.id.edt_supervision_measure_constr);

		this.edt_supervision_measure_constr
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub
						if (!StringUtil.isNullOrEmpty(s.toString())) {
							supvMeasureConstr.setName(s.toString());
						} else {
							supvMeasureConstr.setName("");
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

		this.item_view_detail = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_measure_constr_edit);
		this.item_view_detail.setOnClickListener(this);

		this.item_delete = (ImageView) rowRoot
				.findViewById(R.id.iv_supervision_measure_constr_delete);
		this.item_delete.setOnClickListener(this);
	}

	public void setData(Supervision_Measure_ConstrEntity curItem) {
		supvMeasureConstr = curItem;

		if (StringUtil.isNullOrEmpty(supvMeasureConstr.getName())) {
			edt_supervision_measure_constr.setText(StringUtil
					.getString(R.string.text_empty));
		} else {
			edt_supervision_measure_constr.setText(String
					.valueOf(supvMeasureConstr.getName()));
		}
		
		if(curItem.getListMeasureDetail().size() > 0){
			item_view_detail.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_measured));
		} else {
			item_view_detail.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_measure));
		}
		
		item_delete.setImageDrawable(getResources()
				.getDrawable(R.drawable.delete_item_detail));
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click xoa 1 item
		case R.id.iv_supervision_measure_constr_delete:
			onEvent.onEvent(OnEventControlListener.EVENT_DELETE_ROW, null,
					supvMeasureConstr);
			break;
		// click xem thong tin chi tiet do kich thuoc
		case R.id.iv_supervision_measure_constr_edit:
			onEvent.onEvent(OnEventControlListener.EVENT_VIEW_DETAIL, null,
					supvMeasureConstr);
			break;
		default:
			break;
		}
	}

}
