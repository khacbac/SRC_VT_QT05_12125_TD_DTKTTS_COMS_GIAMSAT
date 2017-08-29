package com.viettel.database.entity;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class ContentDetailItemProgressPreview {
    private String detailTenHangMuc;
    private String detailNgayBatDau;
    private String detailNgayHoanThanh;
    private boolean isNewEdit;

    public ContentDetailItemProgressPreview(String detailTenHangMuc, String detailNgayBatDau, String detailNgayHoanThanh) {
        this.detailTenHangMuc = detailTenHangMuc;
        this.detailNgayBatDau = detailNgayBatDau;
        this.detailNgayHoanThanh = detailNgayHoanThanh;
    }

    public String getDetailTenHangMuc() {
        return detailTenHangMuc;
    }

    public void setDetailTenHangMuc(String detailTenHangMuc) {
        this.detailTenHangMuc = detailTenHangMuc;
    }

    public String getDetailNgayBatDau() {
        return detailNgayBatDau;
    }

    public void setDetailNgayBatDau(String detailNgayBatDau) {
        this.detailNgayBatDau = detailNgayBatDau;
    }

    public String getDetailNgayHoanThanh() {
        return detailNgayHoanThanh;
    }

    public void setDetailNgayHoanThanh(String detailNgayHoanThanh) {
        this.detailNgayHoanThanh = detailNgayHoanThanh;
    }

    public boolean isNewEdit() {
        return isNewEdit;
    }

    public void setNewEdit(boolean newEdit) {
        isNewEdit = newEdit;
    }
}
