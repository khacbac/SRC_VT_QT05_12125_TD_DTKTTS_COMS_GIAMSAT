package com.mvp.example.three.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.mvp.example.three.adapter.CustomSinhVienAdapter;
import com.mvp.example.three.model.entity.SinhVien;
import com.mvp.example.three.presenter.MainPresenter;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.List;


public class ThreeActivity extends AppCompatActivity implements InterfaceMainView{

    //Presenter.
    private MainPresenter mainPresenter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        Button button = (Button) findViewById(R.id.btn_load_data);
        listView = (ListView) findViewById(R.id.list_view);
        initMainPresenter();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.loadData();
            }
        });
    }

    private void initMainPresenter() {
        mainPresenter = new MainPresenter(this);
    }

    @Override
    public void displayListViewData(List<SinhVien> models) {
        listView.setAdapter(new CustomSinhVienAdapter(this,models));
    }
}
