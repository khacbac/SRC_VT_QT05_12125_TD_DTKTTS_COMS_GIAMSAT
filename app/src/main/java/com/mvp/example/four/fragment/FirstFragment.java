package com.mvp.example.four.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viettel.ktts.R;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class FirstFragment extends Fragment {
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page,String title) {
        FirstFragment firstFragment = new FirstFragment();
        Bundle arg = new Bundle();
        arg.putInt("someInt",page);
        arg.putString("someTitle",title);
        firstFragment.setArguments(arg);
        return firstFragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt",0);
        title = getArguments().getString("someTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_first_fragment,container,false);
        TextView txtLabel = (TextView) view.findViewById(R.id.txtTitle);
        txtLabel.setText(page + " --- " + title);
        return view;
    }
}
