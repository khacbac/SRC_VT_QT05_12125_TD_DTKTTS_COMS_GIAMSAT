package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_Hg_FiberEntity;
import com.viettel.ktts.ItemSupervision_Line_HG_Fiber;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_HG_FiberAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_Hg_FiberEntity> listSupervisionLineHGFiber;
	List<Supervision_Line_Hg_FiberEntity> listSearchSupervisionLineHGFiber;

	public Supervision_Line_HG_FiberAdapter(Context curcontext,
			List<Supervision_Line_Hg_FiberEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineHGFiber = data;
		this.listSearchSupervisionLineHGFiber = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionLineHGFiber.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionLineHGFiber.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionLineHGFiber.get(position)
				.getSupervision_Line_Hg_Fiber_Id();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_HG_Fiber item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_hg_fiber,
					null);
			item = new ItemSupervision_Line_HG_Fiber(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_HG_Fiber) rowRoot.getTag();
		}
		Supervision_Line_Hg_FiberEntity itemData = (Supervision_Line_Hg_FiberEntity) getItem(position);
		itemData.setStt(position + 1);
		item.setData(itemData);
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
				listSearchSupervisionLineHGFiber = (List<Supervision_Line_Hg_FiberEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_Hg_FiberEntity> FilteredArrList = new ArrayList<Supervision_Line_Hg_FiberEntity>();

				if (listSearchSupervisionLineHGFiber == null) {
					listSearchSupervisionLineHGFiber = new ArrayList<Supervision_Line_Hg_FiberEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineHGFiber.size();
					results.values = listSupervisionLineHGFiber;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineHGFiber.size(); i++) {
						Supervision_Line_Hg_FiberEntity data = listSupervisionLineHGFiber
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
