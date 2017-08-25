package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.ktts.ItemDropdown;
import com.viettel.ktts.R;

import java.util.List;

public class DropdownAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater inflate;
	List<DropdownItemEntity> listDropdown;

	public DropdownAdapter(Context curcontext, List<DropdownItemEntity> data) {
		this.context = curcontext;
		this.listDropdown = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listDropdown.size();
	}

	@Override
	public Object getItem(int position) {
		return listDropdown.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listDropdown.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemDropdown item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.dropdown_item, null);
			item = new ItemDropdown(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemDropdown) rowRoot.getTag();
		}
		item.setData((DropdownItemEntity) getItem(position));
		return rowRoot;
	}

}
