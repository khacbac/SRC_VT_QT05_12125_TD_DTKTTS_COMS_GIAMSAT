package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.ktts.ItemSupervision_BRCD_TTB;
import com.viettel.ktts.R;

import java.util.List;

public class Supervision_BRCD_TTB_Adapter extends BaseAdapter implements
		Filterable {
	private Context context;
	LayoutInflater inflate;
	List<Supervision_BRCD_Ttb_Entity> listSupervisionLineBGPipe;
	List<Supervision_BRCD_Ttb_Entity> listSearchSupervisionLineBGPipe;

	public Supervision_BRCD_TTB_Adapter(Context curcontext,
			List<Supervision_BRCD_Ttb_Entity> listSupervisionTTB) {
		this.context = curcontext;
		this.listSupervisionLineBGPipe = listSupervisionTTB;
		this.listSearchSupervisionLineBGPipe = listSupervisionTTB;
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
		ItemSupervision_BRCD_TTB item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_supervision_brcd_ttb,
					null);
			item = new ItemSupervision_BRCD_TTB(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemSupervision_BRCD_TTB) rowRoot.getTag();
		}
		item.setData((Supervision_BRCD_Ttb_Entity) getItem(position),position);
		return rowRoot;
	}

	@Override
	public Filter getFilter() {
		// TODO Auto-generated method stub
		return null;
	}



}
