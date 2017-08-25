package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.ktts.ItemContruction_Acceptance;
import com.viettel.ktts.R;

import java.util.List;

public class ConstructionAcceptanceAdapter extends BaseAdapter {
	
	private Context context;
	LayoutInflater inflate;
	List<Supervision_LBG_UnqualifyItemEntity> listAcceptanceItem;
	
	public ConstructionAcceptanceAdapter(Context curcontext,
			List<Supervision_LBG_UnqualifyItemEntity> data) {
		this.context = curcontext;
		this.listAcceptanceItem = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listAcceptanceItem.size();
	}
	
	@Override
	public Object getItem(int position) {
		return listAcceptanceItem.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return listAcceptanceItem.get(position)
				.getCat_Cause_Constr_Acceptance_Id();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemContruction_Acceptance item;
		if (rowRoot == null) {
			rowRoot = inflate
					.inflate(R.layout.item_contruction_acceptance, null);
			item = new ItemContruction_Acceptance(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemContruction_Acceptance) rowRoot.getTag();
		}
		item.setData((Supervision_LBG_UnqualifyItemEntity) getItem(position));
		return rowRoot;
	}
}
