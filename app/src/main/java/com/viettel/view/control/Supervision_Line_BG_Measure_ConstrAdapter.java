package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Supervision_Measure_ConstrEntity;
import com.viettel.ktts.ItemSupervision_Measure_Constr;
import com.viettel.ktts.R;

import java.util.List;

public class Supervision_Line_BG_Measure_ConstrAdapter extends BaseAdapter {
	
	private Context context;
	LayoutInflater inflate;
	List<Supervision_Measure_ConstrEntity> listMeasureConstr;
	
	public Supervision_Line_BG_Measure_ConstrAdapter(Context curcontext,
			List<Supervision_Measure_ConstrEntity> data) {
		this.context = curcontext;
		this.listMeasureConstr = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listMeasureConstr.size();
	}

	@Override
	public Object getItem(int position) {
		return listMeasureConstr.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listMeasureConstr.get(position).getSupervisionMeasureConstrId();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemSupervision_Measure_Constr item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_measure_constr,
					null);
			item = new ItemSupervision_Measure_Constr(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_Measure_Constr) rowRoot.getTag();
		}
		item.setData((Supervision_Measure_ConstrEntity) getItem(position));
		return rowRoot;
	}
	
}
