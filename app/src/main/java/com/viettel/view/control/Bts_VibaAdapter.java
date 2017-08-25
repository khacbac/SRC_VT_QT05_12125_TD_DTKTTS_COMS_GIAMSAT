package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.ktts.ItemBts_Viba;
import com.viettel.ktts.R;

import java.util.List;

public class Bts_VibaAdapter extends BaseAdapter {
	private final Context context;
	LayoutInflater inflate;

	List<Cause_Bts_Cat_WorkEntity> listCause_Bts_CatWork;

	public Bts_VibaAdapter(Context curcontext,
			List<Cause_Bts_Cat_WorkEntity> data) {
		this.context = curcontext;
		this.listCause_Bts_CatWork = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listCause_Bts_CatWork.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listCause_Bts_CatWork.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemBts_Viba item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_bts_viba, null);
			item = new ItemBts_Viba(this.context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemBts_Viba) rowRoot.getTag();
		}
		item.setData((Cause_Bts_Cat_WorkEntity) getItem(position));
		return rowRoot;
	}
}
