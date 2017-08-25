package com.viettel.ktts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.common.DateConvert;
import com.viettel.constants.IntentConstants;
import com.viettel.database.entity.Constr_ObStructionEntity;

import java.util.List;

public class ItemSupervision_CNV_List extends BaseAdapter {
	private final Context context;
	LayoutInflater inflate;

	private TextView tv_stt;
	private TextView tv_type;
	private TextView tv_date;
	private TextView tv_result;

	private ImageView iv_view;
	private ImageView iv_progress;
	//KienPV add new 10/09/2016
	private ImageView iv_delete;
	private onItemClick lsn;
    //---
	List<Constr_ObStructionEntity> listData;
	private Bundle bundleMonitorData;

	public ItemSupervision_CNV_List(Context curcontext,
			List<Constr_ObStructionEntity> data) {
		this.context = curcontext;
		this.listData = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		rowRoot = inflate.inflate(R.layout.item_cnv_list, null);
		if (listData != null && listData.size() > 0) {
			final Constr_ObStructionEntity item = listData.get(position);
			tv_stt = (TextView) rowRoot.findViewById(R.id.tv_stt);
			tv_type = (TextView) rowRoot.findViewById(R.id.tv_type);
			tv_date = (TextView) rowRoot.findViewById(R.id.tv_date);
			tv_result = (TextView) rowRoot.findViewById(R.id.tv_result);

			tv_stt.setText("" + item.getStt());
			tv_type.setText(item.getType());
			tv_date.setText(DateConvert.convertDateToString(item.getCreatedDate()));
			tv_result.setText(item.getResult());

			iv_progress = (ImageView) rowRoot.findViewById(R.id.iv_progress);
			iv_progress.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context,
							SupervisionBts_CNV_Edit_Activity.class);
					bundleMonitorData.putSerializable(
							IntentConstants.INTENT_CNV_ITEM, item);
					bundleMonitorData.putBoolean(
							IntentConstants.INTENT_CNV_ITEM_VIEW, false);
					intent.putExtras(bundleMonitorData);

					context.startActivity(intent);
				}
			});
			iv_view = (ImageView) rowRoot.findViewById(R.id.iv_view);
			iv_view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context,
							SupervisionBts_CNV_Edit_Activity.class);
					bundleMonitorData.putSerializable(
							IntentConstants.INTENT_CNV_ITEM, item);
					bundleMonitorData.putBoolean(
							IntentConstants.INTENT_CNV_ITEM_VIEW, true);
					intent.putExtras(bundleMonitorData);
					context.startActivity(intent);
				}
			});
			iv_delete = (ImageView) rowRoot.findViewById(R.id.iv_delete);
			iv_delete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(lsn != null){lsn.onDelete(item);}
				}
			});
		}

		return rowRoot;
	}

	public Bundle getBundleMonitorData() {
		return bundleMonitorData;
	}

	public void setBundleMonitorData(Bundle bundleMonitorData) {
		this.bundleMonitorData = bundleMonitorData;
	}
	//KienPV add new 10/09/2016
	public void setOnItemClick(onItemClick lsn) {
        this.lsn = lsn;
    }
	public interface onItemClick {
        public abstract void onDelete(Constr_ObStructionEntity item);
    }
	//---
}