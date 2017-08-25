package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.ktts.ItemSupervision_Line_BG_Tank;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BGAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_BG_TankGSEntity> listSupervisionLineBGTank;
	List<Supervision_Line_BG_TankGSEntity> listSearchSupervisionLineBGTank;

	public Supervision_Line_BGAdapter(Context curcontext,
			List<Supervision_Line_BG_TankGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineBGTank = data;
		this.listSearchSupervisionLineBGTank = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionLineBGTank.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionLineBGTank.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionLineBGTank.get(position).getIdTank();
	}
	
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_BG_Tank item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_bg_tank,
					null);
			item = new ItemSupervision_Line_BG_Tank(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_BG_Tank) rowRoot.getTag();
		}
		item.setData((Supervision_Line_BG_TankGSEntity) getItem(position));
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
				listSearchSupervisionLineBGTank = (List<Supervision_Line_BG_TankGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_BG_TankGSEntity> FilteredArrList = new ArrayList<Supervision_Line_BG_TankGSEntity>();

				if (listSearchSupervisionLineBGTank == null) {
					listSearchSupervisionLineBGTank = new ArrayList<Supervision_Line_BG_TankGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineBGTank.size();
					results.values = listSupervisionLineBGTank;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineBGTank.size(); i++) {
						Supervision_Line_BG_TankGSEntity data = listSupervisionLineBGTank
								.get(i);
						if (data.getTankName().toLowerCase()
								.contains(constraint.toString())) {
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
