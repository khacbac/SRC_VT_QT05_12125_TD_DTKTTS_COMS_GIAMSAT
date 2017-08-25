package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import com.viettel.database.entity.Product_CompanyEntity;
import com.viettel.ktts.R;

import java.util.List;

public class Bts_CompanyAdapter extends ArrayAdapter<Product_CompanyEntity> {

	private LayoutInflater inflater;
	private List<Product_CompanyEntity> listProductCompany;
	private Product_CompanyEntity productSelector;
	@SuppressWarnings("unused")
	private Context context;

//	public Bts_CompanyAdapter(Context context, int textViewResourceId,
//			List<Product_CompanyEntity> objects) {
//		super(context, textViewResourceId, objects);
//		// TODO Auto-generated constructor stub
//		this.listProductCompany = objects;
//		inflater = (LayoutInflater) activity
//				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//	}

	public Bts_CompanyAdapter(Context context, int textViewResourceId,
			List<Product_CompanyEntity> objects) {
		super(context, textViewResourceId, objects);

		this.context = context;
		this.listProductCompany = objects;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		
		return getCustomView(position, convertView, parent);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}

	public View getCustomView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		if(rowView == null){
			rowView = inflater.inflate(R.layout.simple_spinner_dropdown_item,
					parent, false);
		}

		this.productSelector = (Product_CompanyEntity) listProductCompany
				.get(position);
		
		CheckedTextView itemSelect = (CheckedTextView) rowView
				.findViewById(R.id.product_company_ctv);
		itemSelect.setText(productSelector.getName());

		return rowView;
	}

}
