package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_HG_PillarGSEntity;
import com.viettel.ktts.ItemSupervision_Line_HG_Pillar;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_PillarAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_HG_PillarGSEntity> listSupervisionLineHgPillar;
	List<Supervision_Line_HG_PillarGSEntity> listSearchSupervisionHgPillar;

	public Supervision_Line_HG_PillarAdapter(Context curcontext,
			List<Supervision_Line_HG_PillarGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineHgPillar = data;
		this.listSearchSupervisionHgPillar = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionHgPillar.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionHgPillar.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionHgPillar.get(position).getIdPillar();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_HG_Pillar item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_hg_pillar,
					null);
			item = new ItemSupervision_Line_HG_Pillar(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_HG_Pillar) rowRoot.getTag();
		}
		item.setData((Supervision_Line_HG_PillarGSEntity) getItem(position));
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
				listSearchSupervisionHgPillar = (List<Supervision_Line_HG_PillarGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_HG_PillarGSEntity> FilteredArrList = new ArrayList<Supervision_Line_HG_PillarGSEntity>();

				if (listSearchSupervisionHgPillar == null) {
					listSearchSupervisionHgPillar = new ArrayList<Supervision_Line_HG_PillarGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineHgPillar.size();
					results.values = listSupervisionLineHgPillar;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineHgPillar.size(); i++) {
						Supervision_Line_HG_PillarGSEntity data = listSupervisionLineHgPillar
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
