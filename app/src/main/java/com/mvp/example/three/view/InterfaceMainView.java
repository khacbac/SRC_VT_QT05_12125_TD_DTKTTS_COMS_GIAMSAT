package com.mvp.example.three.view;

import com.mvp.example.three.model.SinhVienModel;
import com.mvp.example.three.model.entity.SinhVien;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doanLV4 on 8/26/2017.
 */

public interface InterfaceMainView {
    void displayListViewData(List<SinhVien> models);
}
