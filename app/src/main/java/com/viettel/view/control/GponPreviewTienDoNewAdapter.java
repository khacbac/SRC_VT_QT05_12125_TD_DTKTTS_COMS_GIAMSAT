package com.viettel.view.control;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.viettel.database.entity.ContentProgressPreview;

import java.util.HashMap;
import java.util.List;

/**
 * Created by bachk on 11/4/2017.
 */

public class GponPreviewTienDoNewAdapter extends BaseExpandableListAdapter {

    private List<ContentProgressPreview> lHeader;
    private HashMap<ContentProgressPreview,List<ContentProgressPreview>> hmItem;
    private Context mContext;

    public GponPreviewTienDoNewAdapter(List<ContentProgressPreview> lHeader, HashMap<ContentProgressPreview, List<ContentProgressPreview>> hmItem, Context mContext) {
        this.lHeader = lHeader;
        this.hmItem = hmItem;
        this.mContext = mContext;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
