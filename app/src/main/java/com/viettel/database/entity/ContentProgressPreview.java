package com.viettel.database.entity;

/**
 * Created by doanLV4 on 8/16/2017.
 */

public class ContentProgressPreview {
    private String sttProgress;
    private String tenHangMuc;
    private String ngayBatDau;
    private String ngayHoanThanh;
    private boolean isNewEdit;
    private boolean hasNgayHoanThanh;

    public ContentProgressPreview(String sttProgress, String tenHangMuc, String ngayBatDau, String ngayHoanThanh) {
        this.sttProgress = sttProgress;
        this.tenHangMuc = tenHangMuc;
        this.ngayBatDau = ngayBatDau;
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getSttProgress() {
        return sttProgress;
    }

    public void setSttProgress(String sttProgress) {
        this.sttProgress = sttProgress;
    }

    public String getTenHangMuc() {
        return tenHangMuc;
    }

    public void setTenHangMuc(String tenHangMuc) {
        this.tenHangMuc = tenHangMuc;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(String ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public boolean isNewEdit() {
        return isNewEdit;
    }

    public void setNewEdit(boolean newEdit) {
        isNewEdit = newEdit;
    }

    public boolean isHasNgayHoanThanh() {
        return ngayHoanThanh != null && ngayHoanThanh.length() > 0;
    }
}
