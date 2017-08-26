package com.mvp.example.three.model;

import com.mvp.example.three.model.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public class SinhVienModel {
    private InterfaceMainModel interfaceMainModel;

    public SinhVienModel(InterfaceMainModel interfaceMainModel) {
        this.interfaceMainModel = interfaceMainModel;
    }

    public void createListData() {
        List<SinhVien> sinhViens = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            SinhVien sinhVien = new SinhVien("Sinh vien thu " + i);
            sinhViens.add(sinhVien);
        }
        interfaceMainModel.onLoadDataSuccess(sinhViens);
    }
}
