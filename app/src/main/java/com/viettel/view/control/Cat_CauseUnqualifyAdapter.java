package com.viettel.view.control;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.ktts.R;

import java.util.List;

public class Cat_CauseUnqualifyAdapter extends
		ArrayAdapter<Cat_Cause_Constr_UnQualifyEntity> {

	private Context context;
	private ImageView img_warning;
	private List<Cat_Cause_Constr_UnQualifyEntity> listCatCauseUnqualify;
	private TextView tv_catCauseUnqualifyName;
	@SuppressWarnings("unused")
	private CheckBox cb_catCauseUnqualify;
	private Cat_Cause_Constr_UnQualifyEntity CatCauseUnqualify;

	public Cat_CauseUnqualifyAdapter(Context context,
			List<Cat_Cause_Constr_UnQualifyEntity> listCatCauseUnqualify) {
		super(context, android.R.layout.simple_expandable_list_item_1,
				listCatCauseUnqualify);
		this.context = context;
		this.listCatCauseUnqualify = listCatCauseUnqualify;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inf = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inf.inflate(R.layout.item_cat_cause_unqualify, null,
					false);
		}

		CatCauseUnqualify = (Cat_Cause_Constr_UnQualifyEntity) listCatCauseUnqualify.get(position);

		cb_catCauseUnqualify = (CheckBox) convertView.findViewById(R.id.checkbox_cat_cause_unqualify);
		tv_catCauseUnqualifyName = (TextView) convertView.findViewById(R.id.tv_cat_cause_unqualify_name);
		img_warning= (ImageView) convertView.findViewById(R.id.ic_warning);
		tv_catCauseUnqualifyName.setText(CatCauseUnqualify.getName());
		if (CatCauseUnqualify.getIsSerious()==1) {
			img_warning.setVisibility(View.VISIBLE);
			tv_catCauseUnqualifyName.setTextColor(Color.RED);
		}else{
			img_warning.setVisibility(View.GONE);
		}
		return convertView;
	}
}
