package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_HG_MXGSEntity;
import com.viettel.ktts.ItemSupervision_Line_HG_Mx;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_MxAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_HG_MXGSEntity> listSupervisionLineHGMx;
	List<Supervision_Line_HG_MXGSEntity> listSearchSupervisionLineHGMx;

	public Supervision_Line_HG_MxAdapter(Context curcontext,
			List<Supervision_Line_HG_MXGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineHGMx = data;
		this.listSearchSupervisionLineHGMx = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionLineHGMx.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionLineHGMx.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionLineHGMx.get(position).getIdMX();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_HG_Mx item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_hg_mx,
					null);
			item = new ItemSupervision_Line_HG_Mx(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_HG_Mx) rowRoot.getTag();
		}
		item.setData((Supervision_Line_HG_MXGSEntity) getItem(position));
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
				listSearchSupervisionLineHGMx = (List<Supervision_Line_HG_MXGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_HG_MXGSEntity> FilteredArrList = new ArrayList<Supervision_Line_HG_MXGSEntity>();

				if (listSearchSupervisionLineHGMx == null) {
					listSearchSupervisionLineHGMx = new ArrayList<Supervision_Line_HG_MXGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineHGMx.size();
					results.values = listSupervisionLineHGMx;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineHGMx.size(); i++) {
						Supervision_Line_HG_MXGSEntity data = listSupervisionLineHGMx
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
