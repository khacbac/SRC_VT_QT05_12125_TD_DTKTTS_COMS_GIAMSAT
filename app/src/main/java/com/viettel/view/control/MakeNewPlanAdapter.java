package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.DateUtil;
import com.viettel.database.entity.Cat_Employee_Plan_Entity;
import com.viettel.ktts.R;

import java.util.Date;
import java.util.List;

public class MakeNewPlanAdapter extends BaseAdapter {
	private Context mContext;
	private List<Cat_Employee_Plan_Entity> listPlan;
	private LayoutInflater inflate;
	OnClickEditListener editListener;

	public void setEditListener(OnClickEditListener editListener) {
		this.editListener = editListener;
	}

	public MakeNewPlanAdapter(Context mContext,
			List<Cat_Employee_Plan_Entity> listPlan) {
		super();
		this.mContext = mContext;
		this.listPlan = listPlan;
		this.inflate = (LayoutInflater) this.mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listPlan.size();
	}

	@Override
	public Object getItem(int arg0) {
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflate.inflate(R.layout.item_add_plan, null);
			holder.edChooseDate = (TextView) convertView
					.findViewById(R.id.ed_choose_date);
			holder.edPlan = (TextView) convertView.findViewById(R.id.ed_plan);
			holder.imvEdit = (ImageView) convertView
					.findViewById(R.id.imv_edit);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		if (listPlan.get(position).getFromDate().getTime()<= new Date().getTime()) {
			holder.imvEdit.setImageResource(R.drawable.icon_lock);
		} else {
			holder.imvEdit.setImageResource(R.drawable.icon_edit);
		}

		if (listPlan.get(position).getFromDate() != null) {
			holder.edChooseDate.setText(DateUtil
					.convertDateToStringPlan(listPlan.get(position).getFromDate()));
		}
		holder.edPlan.setText(listPlan.get(position).getPlanText());
		holder.imvEdit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listPlan.get(position).getFromDate().getTime()<=new Date().getTime()) {
					Toast.makeText(mContext, mContext.getString(R.string.edit_plan_require), Toast.LENGTH_SHORT).show();
				}else {
					editListener.clickListener(position);	
				}
				
			}
		});

		return convertView;
	}

	public void clickEdit() {

	}

	public class ViewHolder {
		TextView edChooseDate;
		TextView edPlan;
		ImageView imvEdit;
	}

	public interface OnClickEditListener {
		public void clickListener(int position);
	}

}
