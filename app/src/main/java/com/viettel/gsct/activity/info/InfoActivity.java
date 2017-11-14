package com.viettel.gsct.activity.info;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.entity.ConstrNodeEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.field.ConstrNodeController;
import com.viettel.gsct.fragment.base.BaseFragment;
import com.viettel.gsct.fragment.info.InfoChiTietFragment;
import com.viettel.gsct.fragment.info.InfoKeHoachFragment;
import com.viettel.gsct.fragment.info.InfoNhatKyFragment;
import com.viettel.gsct.fragment.info.InfoTiendoFragment;
import com.viettel.gsct.fragment.info.InfoTiendoFragmentByNode;
import com.viettel.ktts.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.viettel.gsct.fragment.base.BaseFragment.constr_ConstructionItem;

/**
 * Created by hieppq3 on 5/10/17.
 */

public class InfoActivity extends AppCompatActivity {
    private static final String TAG = "InfoActivity";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_ma_tram)
    TextView tvMaTram;
    @BindView(R.id.tv_ma_cong_trinh)
    TextView tvMaCongTrinh;
    @BindView(R.id.tv_chi_tiet)
    AppCompatTextView tvChiTiet;
    @BindView(R.id.tv_tien_do)
    AppCompatTextView tvTienDo;
    @BindView(R.id.tv_nhat_ky)
    AppCompatTextView tvNhatKy;
    @BindView(R.id.tv_ke_hoach)
    AppCompatTextView tvKeHoach;
    @BindView(R.id.fr_content)
    FrameLayout frContent;

    private Constr_Construction_EmployeeEntity constr_ConstructionEmployee;

    private BaseFragment mFragment;
    private ArrayList<AppCompatTextView> arrBtns = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        arrBtns.add(tvChiTiet);
        arrBtns.add(tvTienDo);
        arrBtns.add(tvNhatKy);
        arrBtns.add(tvKeHoach);
        initData();
        initToolbar();

        initListener();
    }

    protected void initToolbar() {
//        super.initToolbar();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thông tin chung");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                ;
            }
        });
    }

    private void clearColorFocus() {
        for (View view : arrBtns)
            view.setBackgroundResource(R.color.white);
    }

    private void initListener() {
        tvChiTiet.setBackgroundResource(R.color.colorAccent);
        tvChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment instanceof InfoChiTietFragment)
                    return;
                mFragment = InfoChiTietFragment.newInstance();
                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });

        tvTienDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment instanceof InfoTiendoFragment) {
                    return;
                }
                // Kiem tra la cong trinh cu hay moi de cap nhat.Cong trinh moi cap nhat theo node.Cong trinh cu cap nhat theo cong trinh.
                ArrayList<ConstrNodeEntity> listNode = ConstrNodeController.getInstance(getApplicationContext()).getListNodeByConstrId(constr_ConstructionItem.getConstructId());
                if (constr_ConstructionEmployee.getSupvConstrType() == Constants.CONSTR_TYPE.BRCD && !listNode.isEmpty()) {
                    Log.d(TAG, "onClick: xem info theo node");
                    mFragment = InfoTiendoFragmentByNode.newInstance();
                } else {
                    mFragment = InfoTiendoFragment.newInstance();
                }
                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
                getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });

        tvNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment instanceof InfoNhatKyFragment)
                    return;
                mFragment = InfoNhatKyFragment.newInstance();
                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });

        tvKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment instanceof InfoKeHoachFragment)
                    return;
                mFragment = InfoKeHoachFragment.newInstance();
                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
                getSupportFragmentManager()
                        .beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });
    }

    private void initData() {
        constr_ConstructionEmployee = (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);

        tvMaTram.setText(constr_ConstructionEmployee.getStationCode());
        tvMaCongTrinh.setText(String.valueOf(constr_ConstructionEmployee.getConstrCode()));

        mFragment = InfoChiTietFragment.newInstance();
        mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, mFragment).commit();

        Log.e(TAG, "initData: constructID " + constr_ConstructionEmployee.getConstructId());
    }
}
