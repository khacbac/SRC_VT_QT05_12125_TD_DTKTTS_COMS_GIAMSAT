package com.mvp.example.three.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mvp.example.three.model.entity.SinhVien;
import com.viettel.ktts.R;

import java.util.List;

public class CustomSinhVienAdapter extends BaseAdapter {
    private Context context;
    private List<SinhVien> sinhVienList;

    public CustomSinhVienAdapter(Context context, List<SinhVien> sinhVienList) {
        this.context = context;
        this.sinhVienList = sinhVienList;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return sinhVienList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_custom_sinhvien_adapter,parent,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        SinhVien sinhVien = (SinhVien) getItem(position);
        viewHolder.txtSinhVien.setText(sinhVien.getTenSinhVien());
        return view;
    }

    private class ViewHolder {
        TextView txtSinhVien;
        ViewHolder(View view) {
            txtSinhVien = (TextView) view.findViewById(R.id.txtTenSinhVien);
        }
    }
}
