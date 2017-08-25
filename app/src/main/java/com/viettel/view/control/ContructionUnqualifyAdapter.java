package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.ktts.ItemContruction_Unqualify;
import com.viettel.ktts.R;

import java.util.List;

public class ContructionUnqualifyAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem;

	public ContructionUnqualifyAdapter(Context curcontext,
			List<Supervision_LBG_UnqualifyItemEntity> data) {
		this.context = curcontext;
		this.listUnqualifyItem = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listUnqualifyItem.size();
	}

	@Override
	public Object getItem(int position) {
		return listUnqualifyItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listUnqualifyItem.get(position)
				.getCat_Cause_Constr_Unqualify_Id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemContruction_Unqualify item;
		if (rowRoot == null) {
			rowRoot = inflate
					.inflate(R.layout.item_contruction_unqualify, null);
			item = new ItemContruction_Unqualify(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemContruction_Unqualify) rowRoot.getTag();
		}
		item.setData((Supervision_LBG_UnqualifyItemEntity) getItem(position));
		return rowRoot;
	}

}
