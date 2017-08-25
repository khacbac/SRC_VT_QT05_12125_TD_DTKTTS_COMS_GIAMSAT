package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.viettel.database.entity.ContentJournalPreview;
import com.viettel.ktts.R;

import java.util.List;

/**
 * Created by doanLV4 on 8/16/2017.
 */

public class BtsXemNhatKyAdapter extends BaseAdapter {

    private List<ContentJournalPreview> journalPreviewList;
    private Context mContext;

    public BtsXemNhatKyAdapter(List<ContentJournalPreview> journalPreviewList, Context mContext) {
        this.journalPreviewList = journalPreviewList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return journalPreviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return journalPreviewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_view_layout_journal_preview,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContentJournalPreview item = (ContentJournalPreview) getItem(position);
        viewHolder.mTxtSttNoiDung.setText(item.getSttNoiDung());
        viewHolder.mTxtDoiThiCong.setText(item.getStrDoiThiCong());
        viewHolder.mTxtSoNguoi.setText(item.getStrSoNguoi());
        return convertView;
    }

    private class ViewHolder {
        TextView mTxtSttNoiDung;
        TextView mTxtDoiThiCong;
        TextView mTxtSoNguoi;

        ViewHolder (View view){
            mTxtSttNoiDung = (TextView) view.findViewById(R.id.txtSttNoiDung);
            mTxtDoiThiCong = (TextView) view.findViewById(R.id.txtTenDoiThiCong);
            mTxtSoNguoi = (TextView) view.findViewById(R.id.txtSoNguoi);
        }
    }
}
