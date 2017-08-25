package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_Line_BG_PipeGSEntity;
import com.viettel.ktts.ItemSupervision_Line_BG_Pipe;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class Supervision_Line_BG_PipeAdapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Line_BG_PipeGSEntity> listSupervisionLineBGPipe;
	List<Supervision_Line_BG_PipeGSEntity> listSearchSupervisionLineBGPipe;

	public Supervision_Line_BG_PipeAdapter(Context curcontext,
			List<Supervision_Line_BG_PipeGSEntity> data) {
		this.context = curcontext;
		this.listSupervisionLineBGPipe = data;
		this.listSearchSupervisionLineBGPipe = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listSearchSupervisionLineBGPipe.size();
	}

	@Override
	public Object getItem(int position) {
		return listSearchSupervisionLineBGPipe.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listSearchSupervisionLineBGPipe.get(position).getIdPipe();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Line_BG_Pipe item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_line_bg_pipe,
					null);
			item = new ItemSupervision_Line_BG_Pipe(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Line_BG_Pipe) rowRoot.getTag();
		}
		item.setData((Supervision_Line_BG_PipeGSEntity) getItem(position));
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
				listSearchSupervisionLineBGPipe = (List<Supervision_Line_BG_PipeGSEntity>) results.values;
				notifyDataSetChanged();
			}

			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				FilterResults results = new FilterResults();
				List<Supervision_Line_BG_PipeGSEntity> FilteredArrList = new ArrayList<Supervision_Line_BG_PipeGSEntity>();

				if (listSearchSupervisionLineBGPipe == null) {
					listSearchSupervisionLineBGPipe = new ArrayList<Supervision_Line_BG_PipeGSEntity>();
				}
				if (StringUtil.isNullOrEmpty(constraint.toString())) {

					// set the Original result to return
					results.count = listSupervisionLineBGPipe.size();
					results.values = listSupervisionLineBGPipe;
				} else {
					constraint = constraint.toString().toLowerCase();
					for (int i = 0; i < listSupervisionLineBGPipe.size(); i++) {
						Supervision_Line_BG_PipeGSEntity data = listSupervisionLineBGPipe
								.get(i);
						if (data.getFromTank().toLowerCase()
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
