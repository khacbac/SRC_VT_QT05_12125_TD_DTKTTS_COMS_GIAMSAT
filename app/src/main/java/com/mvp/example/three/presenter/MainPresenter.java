package com.mvp.example.three.presenter;

import com.mvp.example.three.model.InterfaceMainModel;
import com.mvp.example.three.model.SinhVienModel;
import com.mvp.example.three.model.entity.SinhVien;
import com.mvp.example.three.view.InterfaceMainView;

import java.util.List;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class MainPresenter implements InterfaceMainModel{
    private SinhVienModel sinhVienModel;
    private InterfaceMainView interfaceMainView;

    public MainPresenter(InterfaceMainView interfaceMainView) {
        this.interfaceMainView = interfaceMainView;
        sinhVienModel = new SinhVienModel(this);
    }

    public void loadData() {
        sinhVienModel.createListData();
    }

    @Override
    public void onLoadDataSuccess(List<SinhVien> models) {
        interfaceMainView.displayListViewData(models);
    }
}
