package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.ktts.ItemBts_PowerPole;
import com.viettel.ktts.R;

import java.util.List;

public class Bts_PowerPoleAdapter extends BaseAdapter {
	private final Context context;
	LayoutInflater inflate;

	List<Cause_Bts_Power_PoleEntity> listCause_Bts_PowerPole;

	public Bts_PowerPoleAdapter(Context curcontext,
			List<Cause_Bts_Power_PoleEntity> data) {
		this.context = curcontext;
		this.listCause_Bts_PowerPole = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listCause_Bts_PowerPole.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listCause_Bts_PowerPole.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemBts_PowerPole item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_bts_power_pole, null);
			item = new ItemBts_PowerPole(this.context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemBts_PowerPole) rowRoot.getTag();
		}
		item.setData((Cause_Bts_Power_PoleEntity) getItem(position));
		return rowRoot;
	}
}
