package com.viettel.view.listener;

/**
 * Created by doanlv4 on 8/29/2017.
 */

public interface IeValidate {
    // Kiem tra validate tu cap nhat nhat ky.
    interface IecheckValidateNhatKy {
        boolean checkValidateNhatKy(boolean isThiCong);
    }
    // Kiem tra validate tu cap nhat tien do.
    interface IecheckValidateTienDo {
        boolean checkValidateTienDo();
    }
}
