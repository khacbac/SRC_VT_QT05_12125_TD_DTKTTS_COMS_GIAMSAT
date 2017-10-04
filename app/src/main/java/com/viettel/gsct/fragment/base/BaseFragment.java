package com.viettel.gsct.fragment.base;


import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.viettel.common.GlobalInfo;
import com.viettel.database.Cat_Sub_Work_ItemControler;
import com.viettel.database.Cat_Work_Item_TypesControler;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Work_ItemsControler;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.ktts.R;

/**
 * Created by hieppq3 on 4/26/17.
 */

public abstract  class BaseFragment extends Fragment {
    public Constr_Construction_EmployeeEntity constr_ConstructionEmployeeItem;
    public Constr_ConstructionEntity constr_ConstructionItem;

    public Work_ItemsControler work_itemsControler;
    public Sub_Work_ItemController sub_work_itemController;

    public Cat_Work_Item_TypesControler cat_work_item_typesControler;
    public Cat_Sub_Work_ItemControler cat_sub_work_itemControler;

    public long userId;

    public void setConstr_Construction_EmployeeEntity(Constr_Construction_EmployeeEntity entity) {
        this.constr_ConstructionEmployeeItem = entity;
    }

    abstract public void save();

    public void showError(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.text_close, null);
        builder.create().show();
    }

    public void initData() {
        userId = GlobalInfo.getInstance().getUserId();
        constr_ConstructionItem = new Constr_ConstructionController(getContext()).getItem(constr_ConstructionEmployeeItem.getConstructId());
        work_itemsControler = new Work_ItemsControler(getContext());
        sub_work_itemController = new Sub_Work_ItemController(getContext());
        cat_work_item_typesControler = new Cat_Work_Item_TypesControler(getContext());
        cat_sub_work_itemControler = new Cat_Sub_Work_ItemControler(getContext());

    }
}
