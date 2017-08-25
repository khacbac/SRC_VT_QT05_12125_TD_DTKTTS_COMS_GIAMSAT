package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_Hg_CableGSEntity;
import com.viettel.ktts.ItemSupervision_Line_HG_Cable;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_CableAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_Hg_CableGSEntity> listSupervisionLineHgCable;
	List<Supervision_Line_Hg_CableGSEntity> listSearchSupervisionHgCable;

	public Supervision_Line_HG_CableAdapter(Context curcontext,
			List<Supervision_Line_Hg_CableGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineHgCable = data;
		this.listSearchSupervisionHgCable = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionHgCable.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionHgCable.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionHgCable.get(position).getIdCable();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_HG_Cable item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_hg_cable,
					null);
			item = new ItemSupervision_Line_HG_Cable(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_HG_Cable) rowRoot.getTag();
		}
		item.setData((Supervision_Line_Hg_CableGSEntity) getItem(position));
		return rowRoot;
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {

			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint,
					FilterResults results) {
				// TODO Auto-generated method stub
				listSearchSupervisionHgCable = (List<Supervision_Line_Hg_CableGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_Hg_CableGSEntity> FilteredArrList = new ArrayList<Supervision_Line_Hg_CableGSEntity>();

				if (listSearchSupervisionHgCable == null) {
					listSearchSupervisionHgCable = new ArrayList<Supervision_Line_Hg_CableGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineHgCable.size();
					results.values = listSupervisionLineHgCable;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineHgCable.size(); i++) {
						Supervision_Line_Hg_CableGSEntity data = listSupervisionLineHgCable
								.get(i);
						if (true) {
							FilteredArrList.add(data);
						}
					}
					// set the Filtered result to return
					results.count = FilteredArrList.size();
					results.values = FilteredArrList;
				}
				return results;
			}
		};
		return filter;
	}

}
