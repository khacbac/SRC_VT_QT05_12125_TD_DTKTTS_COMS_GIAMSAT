package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_BG_CableGSEntity;
import com.viettel.ktts.ItemSupervision_Line_BG_Cable;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_CableAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_BG_CableGSEntity> listSupervisionLineBGCable;
	List<Supervision_Line_BG_CableGSEntity> listSearchSupervisionLineBGCable;

	public Supervision_Line_BG_CableAdapter(Context curcontext,
			List<Supervision_Line_BG_CableGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineBGCable = data;
		this.listSearchSupervisionLineBGCable = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionLineBGCable.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionLineBGCable.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionLineBGCable.get(position).getIdCable();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_BG_Cable item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_bg_cable,
					null);
			item = new ItemSupervision_Line_BG_Cable(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_BG_Cable) rowRoot.getTag();
		}
		item.setData((Supervision_Line_BG_CableGSEntity) getItem(position));
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
				listSearchSupervisionLineBGCable = (List<Supervision_Line_BG_CableGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_BG_CableGSEntity> FilteredArrList = new ArrayList<Supervision_Line_BG_CableGSEntity>();

				if (listSearchSupervisionLineBGCable == null) {
					listSearchSupervisionLineBGCable = new ArrayList<Supervision_Line_BG_CableGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineBGCable.size();
					results.values = listSupervisionLineBGCable;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineBGCable.size(); i++) {
						Supervision_Line_BG_CableGSEntity data = listSupervisionLineBGCable
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
