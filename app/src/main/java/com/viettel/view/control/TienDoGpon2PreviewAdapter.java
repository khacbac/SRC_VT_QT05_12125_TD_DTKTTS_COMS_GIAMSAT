package com.viettel.view.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.database.entity.ContentDetailItemProgressGpon2Preview;
import com.viettel.database.entity.ContentProgressPreview;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class TienDoGpon2PreviewAdapter extends BaseExpandableListAdapter {
    private List<ContentProgressPreview> lHeader = new ArrayList<>();
    private HashMap<ContentProgressPreview,List<ContentDetailItemProgressGpon2Preview>> hmItem = new HashMap<>();
    private Context mContext;
    private boolean needDisplayKhoiLuong;
    private boolean hasSlHanNoi = false;

    public TienDoGpon2PreviewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public TienDoGpon2PreviewAdapter(List<ContentProgressPreview> lHeader, HashMap<ContentProgressPreview,List<ContentDetailItemProgressGpon2Preview>> hmItem, Context mContext) {
        this.lHeader = lHeader;
        this.hmItem = hmItem;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return lHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hmItem.get(lHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hmItem.get(lHeader.get(groupPosition)).get(childPosition);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_progress_preview_gpon2_ex_listview_header,null);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_progress_preview_gpon2_ex_listview_item,null);
            viewHolderForItem = new ViewHolderForItem(convertView);
            convertView.setTag(viewHolderForItem);
        } else {
            viewHolderForItem = (ViewHolderForItem) convertView.getTag();
        }
//        if (hasSlHanNoi) {
//            viewHolderForItem.setHasSlHanNoi();
//        }
        ContentDetailItemProgressGpon2Preview item = (ContentDetailItemProgressGpon2Preview) getChild(groupPosition,childPosition);
        viewHolderForItem.txtDetailTenHangMuc.setText(item.getDetailTenHangMuc());
        viewHolderForItem.txtSLLapDat.setText(item.getDetailSLLapDat());
        viewHolderForItem.txtNgayHoanThanh.setText(item.getDetailNgayHoanThanh());
        viewHolderForItem.txtDetailKhoiLuong.setText(item.getDetailLuyKe());
        viewHolderForItem.txtSLHanNoi.setText(item.getDetailSLHanNoi());
        viewHolderForItem.txtLuyKeHanHoi.setText(item.getDetailLuyKeHanNoi());
        if (item.isHideIcon()) {
            viewHolderForItem.iCon.setVisibility(View.GONE);
        } else {
            viewHolderForItem.iCon.setVisibility(View.VISIBLE);
        }
        if (item.isHasSLHanNoi()) {
            viewHolderForItem.setHasSlHanNoi();
        }
        if (((ContentDetailItemProgressGpon2Preview) getChild(groupPosition,childPosition)).isNewEdit()) {
            viewHolderForItem.txtDetailTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtSLLapDat.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtDetailKhoiLuong.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtSLHanNoi.setTextColor(mContext.getResources().getColor(R.color.teal));
            viewHolderForItem.txtLuyKeHanHoi.setTextColor(mContext.getResources().getColor(R.color.teal));
        } else {
            viewHolderForItem.txtDetailTenHangMuc.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtSLLapDat.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtNgayHoanThanh.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtDetailKhoiLuong.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtSLHanNoi.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
            viewHolderForItem.txtLuyKeHanHoi.setTextColor(mContext.getResources().getColor(R.color.trans_black_50));
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
        ViewHolderForHeader(View view) {
            txtSttProgress = (TextView) view.findViewById(R.id.txtSttProgress);
            txtTenHangMuc = (TextView) view.findViewById(R.id.txtTenHangMuc);
            txtNgayBatDau = (TextView) view.findViewById(R.id.txtNgayBatDau);
            txtNgayHoanThanh = (TextView) view.findViewById(R.id.txtNgayHoanThanh);
            txtKhoiLuong = (TextView) view.findViewById(R.id.txtKhoiLuong);
        }
    }

    private class ViewHolderForItem {
        TextView txtDetailTenHangMuc;
        TextView txtSLLapDat;
        TextView txtNgayHoanThanh;
        TextView txtDetailKhoiLuong;
        TextView txtSLHanNoi;
        TextView txtLuyKeHanHoi;
        ImageView iCon;
        ViewHolderForItem(View view) {
            txtDetailTenHangMuc = (TextView) view.findViewById(R.id.txtDetailTenHangMuc);
            txtSLLapDat = (TextView) view.findViewById(R.id.txtSLLapDat);
            txtNgayHoanThanh = (TextView) view.findViewById(R.id.txtNgayHoanThanh);
            txtDetailKhoiLuong = (TextView) view.findViewById(R.id.txtDetailKhoiLuong);
            txtSLHanNoi = (TextView) view.findViewById(R.id.txtSLHanNoi);
            txtLuyKeHanHoi = (TextView) view.findViewById(R.id.txtDetailKLHanNoi);
            iCon = (ImageView) view.findViewById(R.id.imgForTenHangMuc);
        }

        void setHasSlHanNoi() {
            txtSLHanNoi.setVisibility(View.VISIBLE);
            txtLuyKeHanHoi.setVisibility(View.VISIBLE);
        }
    }

    public void setNeedDisplayKhoiLuong(boolean needDisplayKhoiLuong) {
        this.needDisplayKhoiLuong = needDisplayKhoiLuong;
    }

    public void setHasSlHN(boolean hadSLHN) {
        this.hasSlHanNoi = hadSLHN;
    }


    public void setListData(List<ContentProgressPreview> lHeader,HashMap<ContentProgressPreview, List<ContentDetailItemProgressGpon2Preview>> hmItem) {
        this.lHeader.addAll(lHeader);
        this.hmItem.putAll(hmItem);
        notifyDataSetChanged();
    }

    public void resetData() {
        this.lHeader.clear();
        this.hmItem.clear();
        notifyDataSetChanged();
    }
}
