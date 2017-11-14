package com.viettel.database.entity;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class ContentDetailItemProgressGpon2Preview {
    private String detailTenHangMuc;
    private String detailSLLapDat;
    private String txtNgayHoanThanh;
    private String detailLuyKe;
    private boolean isNewEdit;

    public ContentDetailItemProgressGpon2Preview() {
    }

    public ContentDetailItemProgressGpon2Preview(String detailTenHangMuc, String detailSLLapDat, String txtNgayHoanThanh, String detailLuyKe) {
        this.detailTenHangMuc = detailTenHangMuc;
        this.detailSLLapDat = detailSLLapDat;
        this.txtNgayHoanThanh = txtNgayHoanThanh;
        this.detailLuyKe = detailLuyKe;
    }

    public String getDetailTenHangMuc() {
        return detailTenHangMuc;
    }

    public void setDetailTenHangMuc(String detailTenHangMuc) {
        this.detailTenHangMuc = detailTenHangMuc;
    }

    public String getDetailSLLapDat() {
        return detailSLLapDat;
    }

    public void setDetailSLLapDat(String detailSLLapDat) {
        this.detailSLLapDat = detailSLLapDat;
    }

    public String getDetailNgayHoanThanh() {
        return txtNgayHoanThanh;
    }

    public void setDetailNgayHoanThanh(String detailNgayHoanThanh) {
        this.txtNgayHoanThanh = detailNgayHoanThanh;
    }

    public boolean isNewEdit() {
        return isNewEdit;
    }

    public void setNewEdit(boolean newEdit) {
        isNewEdit = newEdit;
    }

    public String getDetailLuyKe() {
        return detailLuyKe;
    }

    public void setDetailLuyKe(String detailLuyKe) {
        this.detailLuyKe = detailLuyKe;
    }
}
