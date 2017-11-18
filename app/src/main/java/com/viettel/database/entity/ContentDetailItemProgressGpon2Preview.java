package com.viettel.database.entity;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class ContentDetailItemProgressGpon2Preview {
    private String detailTenHangMuc;
    private String detailSLLapDat;
    private String detailSLHanNoi;
    private String txtNgayHoanThanh;
    private String detailLuyKe;
    private String detailLuyKeHanNoi;
    private boolean isNewEdit;
    private boolean hideIcon = false;
    private boolean hasSLHanNoi = false;

    public ContentDetailItemProgressGpon2Preview() {
    }

    public ContentDetailItemProgressGpon2Preview(String detailTenHangMuc, String detailSLLapDat, String txtNgayHoanThanh, String detailLuyKe) {
        this.detailTenHangMuc = detailTenHangMuc;
        this.detailSLLapDat = detailSLLapDat;
        this.txtNgayHoanThanh = txtNgayHoanThanh;
        this.detailLuyKe = detailLuyKe;
    }

    public ContentDetailItemProgressGpon2Preview(String detailTenHangMuc, String detailSLLapDat, String detailSLHanNoi, String txtNgayHoanThanh, String detailLuyKe, String detailLuyKeHanNoi) {
        this.detailTenHangMuc = detailTenHangMuc;
        this.detailSLLapDat = detailSLLapDat;
        this.detailSLHanNoi = detailSLHanNoi;
        this.txtNgayHoanThanh = txtNgayHoanThanh;
        this.detailLuyKe = detailLuyKe;
        this.detailLuyKeHanNoi = detailLuyKeHanNoi;
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

    public String getDetailLuyKeHanNoi() {
        return detailLuyKeHanNoi;
    }

    public void setDetailLuyKeHanNoi(String detailLuyKeHanNoi) {
        this.detailLuyKeHanNoi = detailLuyKeHanNoi;
    }

    public String getDetailSLHanNoi() {
        return detailSLHanNoi;
    }

    public void setDetailSLHanNoi(String detailSLHanNoi) {
        this.detailSLHanNoi = detailSLHanNoi;
    }

    public boolean isHideIcon() {
        return hideIcon;
    }

    public void setHideIcon(boolean hideIcon) {
        this.hideIcon = hideIcon;
    }

    public boolean isHasSLHanNoi() {
        return hasSLHanNoi;
    }

    public void setHasSLHanNoi(boolean hasSLHanNoi) {
        this.hasSLHanNoi = hasSLHanNoi;
    }
}
