package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.viettel.database.entity.ContentDetailItemProgressPreview;
import com.viettel.database.entity.ContentProgressPreview;
import com.viettel.ktts.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class BtsXemTienDoExpandableAdapter extends BaseExpandableListAdapter {
    private List<ContentProgressPreview> progresslHeaderPreviewList;
    private HashMap<ContentProgressPreview,List<ContentDetailItemProgressPreview>> progresslItemPreviewList;
    private Context mContext;
    private boolean needDisplayKhoiLuong;

    public BtsXemTienDoExpandableAdapter(
            List<ContentProgressPreview> progresslHeaderPreviewList,
            HashMap<ContentProgressPreview, List<ContentDetailItemProgressPreview>> progresslItemPreviewList,
            Context mContext) {
        this.progresslHeaderPreviewList = progresslHeaderPreviewList;
        this.progresslItemPreviewList = progresslItemPreviewList;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return progresslHeaderPreviewList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return progresslItemPreviewList.get(progresslHeaderPreviewList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return progresslHeaderPreviewList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return progresslItemPreviewList
                .get(progresslHeaderPreviewList
                        .get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderForHeader viewHolderForHeader;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_progress_preview_ex_listview_header,null);
            viewHolderForHeader = new ViewHolderForHeader(convertView);
            convertView.setTag(viewHolderForHeader);
        } else {
            viewHolderForHeader = (ViewHolderForHeader) convertView.getTag();
        }
        ContentProgressPreview progressPreview = (ContentProgressPreview) getGroup(groupPosition);
        viewHolderForHeader.txtSttProgress.setText(progressPreview.getSttProgress());
        viewHolderForHeader.txtTenHangMuc.setText(progressPreview.getTenHangMuc());
        viewHolderForHeader.txtNgayBatDau.setText(progressPreview.getNgayBatDau());
        viewHolderForHeader.txtNgayHoanThanh.setText(progressPreview.getNgayHoanThanh());
        if (((ContentProgressPreview) getGroup(groupPosition)).isNewEdit()) {
            viewHolderForHeader.txtSttProgress.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForHeader.txtTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForHeader.txtNgayBatDau.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForHeader.txtNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.teal));
        } else {
            viewHolderForHeader.txtSttProgress.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForHeader.txtTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForHeader.txtNgayBatDau.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForHeader.txtNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
        }
        if (!needDisplayKhoiLuong) {
            viewHolderForHeader.txtKhoiLuong.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderForItem viewHolderForItem;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_progress_preview_ex_listview_item,null);
            viewHolderForItem = new ViewHolderForItem(convertView);
            convertView.setTag(viewHolderForItem);
        } else {
            viewHolderForItem = (ViewHolderForItem) convertView.getTag();
        }
        ContentDetailItemProgressPreview item = (ContentDetailItemProgressPreview) getChild(groupPosition,childPosition);
        viewHolderForItem.txtDetailTenHangMuc.setText(item.getDetailTenHangMuc());
        viewHolderForItem.txtDetailNgayBatDau.setText(item.getDetailNgayBatDau());
        viewHolderForItem.txtDetailNgayHoanThanh.setText(item.getDetailNgayHoanThanh());
        viewHolderForItem.txtDetailKhoiLuong.setText(item.getDetailKhoiLuong());

        if (((ContentDetailItemProgressPreview) getChild(groupPosition,childPosition)).isNewEdit()) {
            viewHolderForItem.txtDetailTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtDetailNgayBatDau.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtDetailNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtDetailKhoiLuong.setTextColor(mContext.getResources().getColor(R.color.teal));
        } else {
            viewHolderForItem.txtDetailTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtDetailNgayBatDau.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtDetailNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtDetailKhoiLuong.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
        }
        if (!needDisplayKhoiLuong) {
            viewHolderForItem.txtDetailKhoiLuong.setVisibility(View.GONE);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolderForHeader {
        TextView txtSttProgress;
        TextView txtTenHangMuc;
        TextView txtNgayBatDau;
        TextView txtNgayHoanThanh;
        TextView txtKhoiLuong;
        public ViewHolderForHeader(View view) {
            txtSttProgress = (TextView) view.findViewById(R.id.txtSttProgress);
            txtTenHangMuc = (TextView) view.findViewById(R.id.txtTenHangMuc);
            txtNgayBatDau = (TextView) view.findViewById(R.id.txtNgayBatDau);
            txtNgayHoanThanh = (TextView) view.findViewById(R.id.txtNgayHoanThanh);
            txtKhoiLuong = (TextView) view.findViewById(R.id.txtKhoiLuong);
        }
    }

    private class ViewHolderForItem {
        TextView txtDetailTenHangMuc;
        TextView txtDetailNgayBatDau;
        TextView txtDetailNgayHoanThanh;
        TextView txtDetailKhoiLuong;
        public ViewHolderForItem(View view) {
            txtDetailTenHangMuc = (TextView) view.findViewById(R.id.txtDetailTenHangMuc);
            txtDetailNgayBatDau = (TextView) view.findViewById(R.id.txtDetailNgayBatDau);
            txtDetailNgayHoanThanh = (TextView) view.findViewById(R.id.txtDetailNgayHoanThanh);
            txtDetailKhoiLuong = (TextView) view.findViewById(R.id.txtDetailKhoiLuong);
        }
    }

    public boolean isNeedDisplayKhoiLuong() {
        return needDisplayKhoiLuong;
    }

    public void setNeedDisplayKhoiLuong(boolean needDisplayKhoiLuong) {
        this.needDisplayKhoiLuong = needDisplayKhoiLuong;
    }
}
