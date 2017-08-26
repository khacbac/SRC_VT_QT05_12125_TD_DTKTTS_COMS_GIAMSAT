package com.mvp.example.three.model;

import com.mvp.example.three.model.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public interface InterfaceMainModel {
    void onLoadDataSuccess(List<SinhVien> models);
}
