package com.viettel.gsct.preview.gpon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viettel.ktts.R;
import com.viettel.utils.NestedExpandableListView;
import com.viettel.view.control.TienDoGpon2PreviewAdapter;
import com.viettel.view.control.TienDoPreviewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by bachk on 11/4/2017.
 */

@SuppressLint("ValidFragment")
public class GponTienDoPreviewFragment extends Fragment {

    @BindView(R.id.nExpandleListView)
    NestedExpandableListView nExpandleListView;

    private ViewCallBack viewCallBack;
    private TienDoGpon2PreviewAdapter adapter;

    @SuppressLint("ValidFragment")
    public GponTienDoPreviewFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gpon_tiendo_preview,container,false);
        ButterKnife.bind(this,view);
        viewCallBack.upDateTextView(nExpandleListView,adapter);
        return view;
    }

    public interface ViewCallBack {
        void upDateTextView(NestedExpandableListView listView, TienDoGpon2PreviewAdapter adapter);
    }

    public void setViewCallBack(ViewCallBack cb,TienDoGpon2PreviewAdapter adapter) {
        this.viewCallBack = cb;
        this.adapter = adapter;
    }
}
