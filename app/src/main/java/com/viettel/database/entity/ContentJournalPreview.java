package com.viettel.database.entity;

/**
 * Created by doanLV4 on 8/16/2017.
 */

public class ContentJournalPreview {
    private String sttNoiDung;
    private String strDoiThiCong;
    private String strSoNguoi;

    public ContentJournalPreview(String sttNoiDung, String strDoiThiCong, String strSoNguoi) {
        this.sttNoiDung = sttNoiDung;
        this.strDoiThiCong = strDoiThiCong;
        this.strSoNguoi = strSoNguoi;
    }

    public String getSttNoiDung() {
        return sttNoiDung;
    }

    public void setSttNoiDung(String sttNoiDung) {
        this.sttNoiDung = sttNoiDung;
    }

    public String getStrDoiThiCong() {
        return strDoiThiCong;
    }

    public void setStrDoiThiCong(String strDoiThiCong) {
        this.strDoiThiCong = strDoiThiCong;
    }

    public String getStrSoNguoi() {
        return strSoNguoi;
    }

    public void setStrSoNguoi(String strSoNguoi) {
        this.strSoNguoi = strSoNguoi;
    }
}
