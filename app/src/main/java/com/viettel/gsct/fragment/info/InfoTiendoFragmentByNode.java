package com.viettel.gsct.fragment.info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.database.Cat_Sub_Work_ItemControler;
import com.viettel.database.Cat_Work_Item_TypesControler;
import com.viettel.database.Sub_Work_ItemController;
import com.viettel.database.Sub_Work_Item_ValueController;
import com.viettel.database.entity.Cat_Sub_Work_ItemEntity;
import com.viettel.database.entity.Cat_Work_Item_TypesEntity;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Sub_Work_ItemEntity;
import com.viettel.database.entity.Sub_Work_Item_ValueEntity;
import com.viettel.database.entity.Work_ItemsEntity;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.gsct.View.constant.Constant;
import com.viettel.gsct.View.info.SubWorkItemInfoView;
import com.viettel.gsct.View.info.WorkItemInfoView;
import com.viettel.gsct.fragment.base.BaseTienDoFragment;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hieppq3 on 5/11/17.
 */

public class InfoTiendoFragmentByNode extends BaseTienDoFragment {

    private final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;
    @BindView(R.id.layoutByCT)
    LinearLayout layoutByCT;
    @BindView(R.id.bottomLayout)
    LinearLayout bottomLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.bottomLayoutByNode)
    LinearLayout bottomLayoutByNode;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.layoutKhoiLuong)
    LinearLayout layoutKhoiLuong;
    private Unbinder unbinder;

    private Work_ItemsEntity wItemOdf = null;
    private Work_ItemsEntity wItemOlt = null;
    private Work_ItemsEntity wItemKeoCap = null;
    private Work_ItemsEntity wItemSo8 = null;
    private Work_ItemsEntity wItemAdss = null;
    private Work_ItemsEntity wItemHanNoi = null;
    private Work_ItemsEntity wItemDoKiem = null;

    public static InfoTiendoFragmentByNode newInstance() {
        return new InfoTiendoFragmentByNode();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_info_tien_do_by_node, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initData();
        return layout;
    }

    @Override
    public void initData() {
        super.initData();

        ArrayList<Work_ItemsEntity> workItems = work_itemsControler.getItems(constr_ConstructionItem.getConstructId());
        // Hien thi thong bao khong co du lieu neu work item = null.
        if (workItems.isEmpty()) {
            rootLayout.setVisibility(View.GONE);
            tvNoData.setVisibility(View.VISIBLE);
            return;
        }

        for (Work_ItemsEntity wItem : workItems) {
            switch (wItem.getWork_item_name()) {
                case Constant.TAG_LAPDAT:
                    wItemOdf = wItem;
                    break;
                case Constant.TAG_KEOCAP:
                    wItemKeoCap = wItem;
                    break;
                case Constant.TAG_CAPSO8:
                    wItemSo8 = wItem;
                    break;
                case Constant.TAG_CAPADSS:
                    wItemAdss = wItem;
                    break;
                case Constant.TAG_HANNOI:
                    wItemHanNoi = wItem;
                    break;
                case Constant.TAG_DOKIEM_NGHIEMTHU:
                    wItemDoKiem = wItem;
                    break;
                case Constant.TAG_OLT:
                    wItemOlt = wItem;
                    break;
                default:
                    break;
            }
        }

        // Khoi tao info cho cac cong trinh cao nhat theo cong trinh.

        if (wItemOdf != null) {
            Cat_Work_Item_TypesEntity cwitOdf = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemOdf.getItem_type_id(),Constant.CODE_LAPDAT_ODF);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemOdf.getWork_item_name() + "Odf Indoor",wItemOdf.getStarting_date(),wItemOdf.getComplete_date());
            bottomLayout.addView(view);

            if (cwitOdf != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWI = Cat_Sub_Work_ItemControler.getInstance(getActivity()).getsubCatesByCode(cwitOdf.getItem_type_id(),"INDOOR");
                for (Cat_Sub_Work_ItemEntity entity : listCSWI) {
                    double luyKe = Sub_Work_ItemController.getInstance(getActivity()).getLuyke(wItemOdf.getId(),entity.getId());
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(entity.getName(),""+(int)luyKe,"","");
                    bottomLayout.addView(subView);
                }
            }
        }
        if (wItemOlt != null) {
            Cat_Work_Item_TypesEntity cwitOlt = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemOlt.getItem_type_id(),Constant.CODE_LAPDAT_OLT);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemOlt.getWork_item_name(),wItemOlt.getStarting_date(),wItemOlt.getComplete_date());
            bottomLayout.addView(view);

            if (cwitOlt != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWI = Cat_Sub_Work_ItemControler.getInstance(getActivity()).getsubCates(cwitOlt.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity entity : listCSWI) {
                    Sub_Work_ItemEntity swiEntity = Sub_Work_ItemController.getInstance(getActivity()).getItem(wItemOlt.getId(),entity.getId());
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(entity.getName(),"","","");
                    if (swiEntity != null) {
                        subView.setFinishDate(swiEntity.getFinishDate());
                    }
                    bottomLayout.addView(subView);
                }
            }
        }

        // Khoi tao info cho cac cong trinh cap nhat theo node.
        ArrayList<ConstrNodeEntity> listNode = ConstrNodeController.getInstance(getActivity()).getListNodeByConstrId(constr_ConstructionItem.getConstructId());
        for (ConstrNodeEntity nodeEntity : listNode) {
            tabLayout.addTab(tabLayout.newTab().setText(nodeEntity.getNodeName()).setTag(nodeEntity.getNodeID()));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bottomLayoutByNode.removeAllViews();
                initInfoKeoCap(tab.getTag());
                initInfoCapSo8(tab.getTag());
                initInfoCapAdss(tab.getTag());
                initInfoHanNoi(tab.getTag());
                initInfoOdfOutDoor(tab.getTag());
                initInfoDoKiem(tab.getTag());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    bottomLayoutByNode.removeAllViews();
                    initInfoKeoCap(tab.getTag());
                    initInfoCapSo8(tab.getTag());
                    initInfoCapAdss(tab.getTag());
                    initInfoHanNoi(tab.getTag());
                    initInfoOdfOutDoor(tab.getTag());
                    initInfoDoKiem(tab.getTag());
                }
            }
        });

        TabLayout.Tab tab = tabLayout.getTabAt(0);
        if (tab != null) {
            tab.select();
        }

    }


    private void initInfoKeoCap(Object tag) {
        if (wItemKeoCap != null) {
            Cat_Work_Item_TypesEntity cwitKeoCap = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemKeoCap.getItem_type_id(),Constant.CODE_KEOCAP);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemKeoCap.getWork_item_name(),wItemKeoCap.getStarting_date(),wItemKeoCap.getComplete_date());
            bottomLayoutByNode.addView(view);

            if (cwitKeoCap != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCates(cwitKeoCap.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeByNode(wItemKeoCap.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),""+((double)Math.round(luyke * 100) / 100),"","");
                    bottomLayoutByNode.addView(subView);
                }
            }

        }
    }

    private void initInfoCapSo8(Object tag) {
        if (wItemSo8 != null) {
            Cat_Work_Item_TypesEntity cwitSo8 = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemSo8.getItem_type_id(),Constant.CODE_CAPQUANG);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemSo8.getWork_item_name(),wItemSo8.getStarting_date(),wItemSo8.getComplete_date());
            bottomLayoutByNode.addView(view);

            if (cwitSo8 != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCates(cwitSo8.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeByNode(wItemSo8.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),""+((double)Math.round(luyke * 100) / 100),"","");
                    bottomLayoutByNode.addView(subView);
                }
            }

        }
    }

    private void initInfoCapAdss(Object tag) {
        if (wItemAdss != null) {
            Cat_Work_Item_TypesEntity cwitAdss = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemAdss.getItem_type_id(),Constant.CODE_ADSS);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemAdss.getWork_item_name(),wItemAdss.getStarting_date(),wItemAdss.getComplete_date());
            bottomLayoutByNode.addView(view);

            if (cwitAdss != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCates(cwitAdss.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeByNode(wItemAdss.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),""+((double)Math.round(luyke * 100) / 100),"","");
                    bottomLayoutByNode.addView(subView);
                }
            }

        }
    }

    private void initInfoHanNoi(Object tag) {
        if (wItemHanNoi != null) {
            Cat_Work_Item_TypesEntity cwitHanNoi = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemHanNoi.getItem_type_id(),Constant.CODE_LAPDAT_HANNOI);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemHanNoi.getWork_item_name(),wItemHanNoi.getStarting_date(),wItemHanNoi.getComplete_date());
            bottomLayoutByNode.addView(view);
            SubWorkItemInfoView subViewHeader = new SubWorkItemInfoView(getContext());
            subViewHeader.setValue("","Lắp đặt","Hàn nối","","");
            bottomLayoutByNode.addView(subViewHeader);
            if (cwitHanNoi != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCates(cwitHanNoi.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeByNode(wItemHanNoi.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    double luykeHanNoi = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeHanNoiByNode(wItemHanNoi.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),""+(int)luyke,""+(int)luykeHanNoi,"","");
                    bottomLayoutByNode.addView(subView);
                }
            }
        }
    }

    private void initInfoOdfOutDoor(Object tag) {
        if (wItemOdf != null) {
            Cat_Work_Item_TypesEntity cwitOutDoor = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemOdf.getItem_type_id(),Constant.CODE_LAPDAT_ODF);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemOdf.getWork_item_name(),wItemOdf.getStarting_date(),wItemOdf.getComplete_date());
            bottomLayoutByNode.addView(view);

            if (cwitOutDoor != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCatesByCode(cwitOutDoor.getItem_type_id(),"OUTDOOR");
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    double luyke = Sub_Work_Item_ValueController.getInstance(getContext()).getLuykeByNode(wItemOdf.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),""+(int)luyke,"","");
                    bottomLayoutByNode.addView(subView);
                }
            }
        }
    }

    private void initInfoDoKiem(Object tag) {
        if (wItemDoKiem != null) {
            Cat_Work_Item_TypesEntity cwitDoKiem = Cat_Work_Item_TypesControler.getInstance(getContext()).getCateByWItemTest(wItemDoKiem.getItem_type_id(),Constant.CODE_DOKIEM);
            WorkItemInfoView view = new WorkItemInfoView(getContext());
            view.setValue(wItemDoKiem.getWork_item_name(),wItemDoKiem.getStarting_date(),wItemDoKiem.getComplete_date());
            bottomLayoutByNode.addView(view);

            if (cwitDoKiem != null) {
                ArrayList<Cat_Sub_Work_ItemEntity> listCSWIEntity = Cat_Sub_Work_ItemControler.getInstance(getContext()).getsubCates(cwitDoKiem.getItem_type_id());
                for (Cat_Sub_Work_ItemEntity cswiEntity : listCSWIEntity) {
                    Sub_Work_Item_ValueEntity swivEntity = Sub_Work_Item_ValueController.getInstance(getContext()).getItemByNodeDoKiem(wItemDoKiem.getId(),cswiEntity.getId(),Long.parseLong(String.valueOf(tag)));
                    SubWorkItemInfoView subView = new SubWorkItemInfoView(getContext());
                    subView.setValue(cswiEntity.getName(),"","","");
                    if (swivEntity != null) {
                        subView.setFinishDate(swivEntity.getAdded_date());
                    }
                    bottomLayoutByNode.addView(subView);
                }
            }
        }

    }


    @Override
    public void save() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
