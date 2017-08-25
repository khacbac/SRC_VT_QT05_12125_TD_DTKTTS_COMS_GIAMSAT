package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Measurement_Detail_ConstrEntity;
import com.viettel.ktts.ItemMeasument_Constr_Detail;
import com.viettel.ktts.R;

import java.util.List;

public class Measurement_Constr_DetailAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater inflate;
	private List<Measurement_Detail_ConstrEntity> listMeasureDetail;
	
	public Measurement_Constr_DetailAdapter(Context curcontext,
			List<Measurement_Detail_ConstrEntity> data) {
		this.context = curcontext;
		this.listMeasureDetail = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		return listMeasureDetail.size();
	}

	@Override
	public Object getItem(int position) {
		return listMeasureDetail.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listMeasureDetail.get(position).getMeasurementDetailConstrId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemMeasument_Constr_Detail item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_measurement_constr_detail,
					null);
			item = new ItemMeasument_Constr_Detail(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemMeasument_Constr_Detail) rowRoot.getTag();
		}
		item.setData((Measurement_Detail_ConstrEntity) getItem(position));
		return rowRoot;
	}
}
