package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Special_LocationGSEntity;
import com.viettel.ktts.ItemSpecial_Location;
import com.viettel.ktts.R;

import java.util.List;

public class Supervision_Line_BG_SpecialAdapter extends BaseAdapter{

	private Context context;
	LayoutInflater inflate;
	List<Special_LocationGSEntity> listSupervisionLineBGSpecial;
	
	public Supervision_Line_BG_SpecialAdapter(Context curcontext,
			List<Special_LocationGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineBGSpecial = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listSupervisionLineBGSpecial.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listSupervisionLineBGSpecial.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listSupervisionLineBGSpecial.get(position).getIdSpecial();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSpecial_Location item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_special_location,
					null);
			item = new ItemSpecial_Location(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSpecial_Location) rowRoot.getTag();
		}
		item.setData((Special_LocationGSEntity) getItem(position));
		return rowRoot;
	}

}
