package com.viettel.common;

import com.viettel.ktts.R;

import static com.viettel.utils.StringUtil.getString;

/**
 * Created by doanLV4 on 8/17/2017.
 */

public class KeyEventCommon {

    // Key for pass data.
    public static final String KEY_TUYEN_NGAM = "TuyenNgam";
    public static final String KEY_TUYEN_TREO = "TuyenTreo";
    public static final String KEY_BTS = "Bts";
    // Key for cap nhat nhat ky common.
    public static final String KEY_THOITIET = "ThoiTiet";
    public static final String KEY_NOIDUNG_CONGVIEC = "NoiDungCongViec";
    public static final String KEY_THAYDOI = "ThayDoi";
    public static final String KEY_GIAMSAT = "GiamSat";
    public static final String KEY_THICONG = "ThiCong";
    public static final String KEY_TEN_TRAM_TUYEN = "TenTramTuyen";

    // Key for cap nhat nhat ky bts.
    public static final String KEY_DOI_XAYDUNG = "XayDung";
    public static final String KEY_DOI_LAPDAT = "LapDat";
    public static final String KEY_DOI_TIEPDIA = "TiepDia";
    public static final String KEY_DOI_TKCAP = "TKCap";
    public static final String KEY_DOI_TKDIEN = "TKDien";
    public static final String KEY_DOI_THIETBI = "ThietBi";
    public static final String[] KEY_DOI_BTS_ARR =
            {
                    KEY_DOI_XAYDUNG,
                    KEY_DOI_LAPDAT,
                    KEY_DOI_TIEPDIA,
                    KEY_DOI_TKCAP,
                    KEY_DOI_TKDIEN,
                    KEY_DOI_THIETBI
            };
    public static final String[] KEY_TEN_HM_BTS_ARR =
            {
                    getString(R.string.txt_doi_xay_dung),
                    getString(R.string.txt_doi_lap_dung),
                    getString(R.string.txt_doi_tiep_dia),
                    getString(R.string.txt_doi_trienkhai_cap),
                    getString(R.string.txt_doi_trienkhai_dien),
                    getString(R.string.txt_doi_thietbi)
            };

    // Key for cap nhat nhat ky tuyen ngam.
    public static final String KEY_DOI_DAORANH = "DaoRanh";
    public static final String KEY_DOI_XAYBE = "XayBe";
    public static final String KEY_DOI_KEOCAP_NGAM = "KeoCapNgam";
    public static final String KEY_DOI_MAY = "May";
    public static final String[] KEY_DOI_TUYENNGAM_ARR =
            {
                    KEY_DOI_DAORANH,
                    KEY_DOI_XAYBE,
                    KEY_DOI_KEOCAP_NGAM,
                    KEY_DOI_MAY
            };
    public static final String[] KEY_TEN_HM_TUYENNGAM_ARR =
            {
                    getString(R.string.txt_doi_dao_ranh),
                    getString(R.string.txt_doi_xay_be),
                    getString(R.string.txt_doi_keo_cap),
                    getString(R.string.txt_may)
            };

    // Key for cap nhat nhat ky tuyen treo.
    public static final String KEY_DOI_KEOCAP_TREO = "KeoCapTreo";
    public static final String KEY_DOI_TRONGCOT = "TrongCot";
    public static final String[] KEY_DOI_TUYENTREO_ARR =
            {
                    KEY_DOI_KEOCAP_TREO,
                    KEY_DOI_TRONGCOT
            };
    public static final String[] KEY_TEN_HM_TUYENTREO_ARR =
            {
                    getString(R.string.txt_doi_keo_cap),
                    getString(R.string.txt_doi_trong_cot)
            };

    // Key for cap nhat nhat ky tuyen bang rong co dinh.
    public static final String KEY_DOI_KEOCAP_BANGRONG = "KeoCapBangRong";
    public static final String KEY_DOI_HANNOI_BANGRONG = "HanNoiBangRong";
    public static final String[] KEY_DOI_BANGRONG_ARR =
            {
                    KEY_DOI_KEOCAP_BANGRONG,
                    KEY_DOI_HANNOI_BANGRONG
            };
    public static final String[] KEY_TEN_HM_BANGRONG_ARR =
            {
                    getString(R.string.txt_doi_keo_cap),
                    getString(R.string.txt_doi_han_noi)
            };

    // Key for Fragment.
    public static final String KEY_NHATKY_FRAGMENT = "FragmentNhatKy";
    public static final String KEY_TIENDO_FRAGMENT = "FragmentTienDo";
    public static final String KEY_PREVIEW_FRAGMENT = "FragmentPreview";
}
