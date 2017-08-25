package com.viettel.ktts;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.view.listener.OnEventControlListener;

public class ItemDropdown extends LinearLayout implements OnClickListener {
	private LinearLayout item_dropdown_ll;
	private TextView item_dropdown_tv;
	private OnEventControlListener onEvent;
	private DropdownItemEntity dropdownItem;

	public ItemDropdown(Context context) {
		super(context);		
	}

	public ItemDropdown(Context context, View rowRoot) {
		super(context);
		this.item_dropdown_ll = (LinearLayout) rowRoot
				.findViewById(R.id.item_dropdown_ll);
		this.item_dropdown_ll.setOnClickListener(this);
		this.item_dropdown_tv = (TextView) rowRoot
				.findViewById(R.id.item_dropdown_tv);
		onEvent = (OnEventControlListener) context;
	}

	public void setData(DropdownItemEntity curDropdownItemEntity) {
		dropdownItem = curDropdownItemEntity;
		this.item_dropdown_tv.setText(this.dropdownItem.getTitle());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.item_dropdown_ll:
			if (dropdownItem != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK, null,
						dropdownItem);
			}
			break;
		default:
			break;
		}

	}

}
