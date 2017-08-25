package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.ktts.R;

import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class ConstructAdapter extends ArrayAdapter{

	Context context;
	ArrayList<Constr_ConstructionEntity> ar;
	TextView stt;
	TextView construction_id;
	TextView station_id;
	TextView category_construction;
	TextView status;
	private Constr_ConstructionEntity construction;
	
	@SuppressWarnings("unchecked")
	public ConstructAdapter(Context context, ArrayList<Constr_ConstructionEntity> ar) {
		super(context, android.R.layout.simple_expandable_list_item_1,ar);
		this.context = context;
		this.ar = ar;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView == null){
			LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.constructor_adapter, null, false);
		}
		
		stt = (TextView) convertView.findViewById(R.id.stt_work);
		construction_id = (TextView) convertView.findViewById(R.id.id_work);
		station_id = (TextView) convertView.findViewById(R.id.id_station);
		category_construction = (TextView) convertView.findViewById(R.id.category_work);
		status = (TextView) convertView.findViewById(R.id.txt_status);
		
		setConstruction((Constr_ConstructionEntity) ar.get(position));
//		stt.setText(String.valueOf(1));
//		construction_id.setText(String.valueOf(construction.getConstructId()));
//		station_id.setText(String.valueOf(construction.getSupervisorId()));
//		category_construction.setText(String.valueOf(construction.getConstrType()));
//		status.setText(String.valueOf(construction.getStatus()));
//		stt.setText(String.valueOf(1));
//		construction_id.setText(String.valueOf(2));
//		station_id.setText(String.valueOf(3));
//		category_construction.setText(String.valueOf(4));
//		status.setText(String.valueOf(5));
		return convertView;
	}

	public Constr_ConstructionEntity getConstruction() {
		return construction;
	}

	public void setConstruction(Constr_ConstructionEntity construction) {
		this.construction = construction;
	}
}
