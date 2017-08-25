package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.ktts.ItemConstr_Construction;
import com.viettel.ktts.R;

import java.util.List;

public class Constr_ConstructionAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater inflate;
	List<Constr_Construction_EmployeeEntity> listConstr_Construction;

	public Constr_ConstructionAdapter(Context curcontext,
			List<Constr_Construction_EmployeeEntity> data) {
		this.context = curcontext;
		this.listConstr_Construction = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listConstr_Construction.size();
	}

	@Override
	public Object getItem(int position) {
		return listConstr_Construction.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listConstr_Construction.get(position).getConstructId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemConstr_Construction item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_constr_construction, null);
			item = new ItemConstr_Construction(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemConstr_Construction) rowRoot.getTag();
		}
		Constr_Construction_EmployeeEntity curItemData = (Constr_Construction_EmployeeEntity) getItem(position);
		curItemData.setStt(position + 1);
		try {
			item.setData(curItemData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowRoot;
	}

}
