package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Special_LocationEntity;
import com.viettel.ktts.ItemPoint_To_Bridge;
import com.viettel.ktts.R;

import java.util.List;

public class Point_To_BridgeAdapter extends BaseAdapter {

	private Context context;
	LayoutInflater inflate;
	List<Special_LocationEntity> listSpecialPoint;

	public Point_To_BridgeAdapter(Context curcontext,
			List<Special_LocationEntity> data) {
		this.context = curcontext;
		this.listSpecialPoint = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listSpecialPoint.size();
	}

	@Override
	public Object getItem(int position) {
		return listSpecialPoint.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSpecialPoint.get(position).getSpecialLocationId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemPoint_To_Bridge item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_point_to_bridge,
					null);
			item = new ItemPoint_To_Bridge(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemPoint_To_Bridge) rowRoot.getTag();
		}
		item.setData((Special_LocationEntity) getItem(position));
		return rowRoot;
	}
}
