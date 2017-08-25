package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.viettel.database.entity.ImageEntity;
import com.viettel.ktts.ItemImage;
import com.viettel.ktts.R;

import java.util.List;

public class Image_ViewAdapter extends BaseAdapter {
	private Context context;
	LayoutInflater inflate;
	List<ImageEntity> listImage;

	public Image_ViewAdapter(Context curcontext, List<ImageEntity> data) {
		this.context = curcontext;
		this.listImage = data;
		this.inflate = (LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return listImage.size();
	}

	@Override
	public Object getItem(int position) {
		return listImage.get(position);
	}

	@Override
	public long getItemId(int position) {
		return listImage.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowRoot = convertView;
		ItemImage item;
		if (rowRoot == null) {
			rowRoot = inflate.inflate(R.layout.item_image, null);
			item = new ItemImage(context, rowRoot);
			rowRoot.setTag(item);
		} else {
			item = (ItemImage) rowRoot.getTag();
		}
		item.setData((ImageEntity) getItem(position));
		return rowRoot;
	}

}
