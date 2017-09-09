package com.viettel.view.listener;

/**
 * Created by doanlv4 on 8/29/2017.
 */

public interface IeSave {
    /**
     * Created by doanlv4 on 8/29/2017.
     */

    // Chiu trach nhiem lang nghe su kien tra ve tu phan cap nhat nhat ky.
    interface IeCapNhatNhatKyInteractor {
        void saveNhatKy();
    }

    /**
     * Created by doanlv4 on 8/29/2017.
     */
    // Chiu trach nhiem lang nghe su kien tra ve tu phan cap nhat tien do.
    interface IeCapNhatTienDoInteractor {
        void saveTienDo();
    }
}
