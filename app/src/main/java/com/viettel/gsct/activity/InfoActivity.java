package com.viettel.gsct.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.constants.IntentConstants;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.fragment.BaseFragment;
import com.viettel.gsct.fragment.InfoChiTietFragment;
import com.viettel.gsct.fragment.InfoKeHoachFragment;
import com.viettel.gsct.fragment.InfoNhatKyFragment;
import com.viettel.gsct.fragment.InfoTiendoFragment;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hieppq3 on 5/10/17.
 */

public class InfoActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener,InfoNhatKyFragment.OnDataPassToActivity{
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
    @BindView(R.id.tv_nhat_ky_tien_do)
    AppCompatTextView tvNhatKy;
    @BindView(R.id.tv_ke_hoach)
    AppCompatTextView tvKeHoach;
    @BindView(R.id.fr_content)
    FrameLayout frContent;
    @BindView(R.id.fr_content_nhatky)
    FrameLayout mFrameNhatKy;
    @BindView(R.id.fr_content_tiendo)
    FrameLayout mFrameTienDo;
    @BindView(R.id.linear_content_nhatky_tiendo)
    LinearLayout mLiLayoutForCapNhatNhatKyTienDo;
    @BindView(R.id.tv_ma_tram_nk_td)
    TextView mTxtMaTramForCaseNhatKyTienDo;
    @BindView(R.id.tv_ma_cong_trinh_nk_td)
    TextView mTxtMaCongTrinhForCaseNhatKyTienDo;
    @BindView(R.id.btnTienDoThiCongHangMuc)
    Button mBtnTienDoThiCongHangMuc;
    @BindView(R.id.btnNhatKyHangNgay)
    Button mBtnNhatKyHangNgay;
    @BindView(R.id.spinner_choose_log_date)
    Spinner mSpinnerChooseLogDate;

    public static String mStrMaTram;
    public static String mStrMaCongTrinh;


    private Constr_Construction_EmployeeEntity constr_ConstructionEmployee;

    private BaseFragment mFragment;
    private BaseFragment fragmentNhatKy;
    private BaseFragment fragmentTienDo;
    private ArrayList<AppCompatTextView> arrBtns = new ArrayList<>();
    private InterfaceInteractiveWithFragment mInteractiveWithFragment;
    private List<String> mListLogDate;
    private List<String> listFormatDate;

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
                getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });

//        tvTienDo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mFragment instanceof InfoTiendoFragment)
//                    return;
//                mFragment = InfoTiendoFragment.newInstance();
//                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fr_content_nhatky, mFragment).commit();
//                clearColorFocus();
//                v.setBackgroundResource(R.color.colorAccent);
//            }
//        });

        tvNhatKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLiLayoutForCapNhatNhatKyTienDo.setVisibility(View.VISIBLE);
                fragmentNhatKy = InfoNhatKyFragment.newInstance();
                transitFragment(R.id.fr_content_nhatky, fragmentNhatKy, v, "NhatKyFragment");

                fragmentTienDo = InfoTiendoFragment.newInstance();
                transitFragment(R.id.fr_content_tiendo, fragmentTienDo, v, "TienDoFragment");

                mBtnTienDoThiCongHangMuc.performClick();

            }
        });

        tvKeHoach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFragment instanceof InfoKeHoachFragment)
                    return;
                mFragment = InfoKeHoachFragment.newInstance();
                mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
                getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, mFragment).commit();
                clearColorFocus();
                v.setBackgroundResource(R.color.colorAccent);
            }
        });
    }

    private void initSpinner(List<String> listData) {
        if (listData == null) {
            return;
        }
        listFormatDate = new ArrayList<>();
        for (int i = 0; i < listData.size(); i++) {
            listFormatDate.add(GSCTUtils.standardlizeTime(listData.get(i)));
        }
        ArrayAdapter<String> adapterForSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listFormatDate);
        mSpinnerChooseLogDate.setAdapter(adapterForSpinner);
        mSpinnerChooseLogDate.setOnItemSelectedListener(this);
    }

    private void initData() {
        constr_ConstructionEmployee = (Constr_Construction_EmployeeEntity) getIntent().getExtras()
                .getSerializable(IntentConstants.INTENT_DATA);

        mStrMaTram = constr_ConstructionEmployee.getStationCode();
        mStrMaCongTrinh = String.valueOf(constr_ConstructionEmployee.getConstrCode());

        tvMaTram.setText(mStrMaTram);
        tvMaCongTrinh.setText(mStrMaCongTrinh);

        mTxtMaTramForCaseNhatKyTienDo.setText(mStrMaTram);
        mTxtMaCongTrinhForCaseNhatKyTienDo.setText(mStrMaCongTrinh);

        mFragment = InfoChiTietFragment.newInstance();
        mFragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr_content, mFragment).commit();

        Log.e(TAG, "initData: constructID " + constr_ConstructionEmployee.getConstructId());
    }

    @OnClick(R.id.btnTienDoThiCongHangMuc)
    public void onBtnTienDoThiCongHangMucClick() {
        mFrameNhatKy.setVisibility(View.GONE);
        mFrameTienDo.setVisibility(View.VISIBLE);
        mBtnTienDoThiCongHangMuc.setBackgroundColor(Color.CYAN);
        mBtnNhatKyHangNgay.setBackgroundColor(Color.WHITE);

    }

    @OnClick(R.id.btnNhatKyHangNgay)
    public void onBtnNhatKyHangNgayClick() {
        mFrameNhatKy.setVisibility(View.VISIBLE);
        mFrameTienDo.setVisibility(View.GONE);
        mBtnTienDoThiCongHangMuc.setBackgroundColor(Color.WHITE);
        mBtnNhatKyHangNgay.setBackgroundColor(Color.CYAN);
    }

    private void transitFragment(int id,BaseFragment fragment,View v,String tag) {
        fragment.setConstr_Construction_EmployeeEntity(constr_ConstructionEmployee);
        getSupportFragmentManager().beginTransaction().replace(id, fragment, tag).commit();
        clearColorFocus();
        v.setBackgroundResource(R.color.colorAccent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Date = " + mListLogDate.get(position),Toast.LENGTH_SHORT).show();
//        mInteractiveWithFragment.sendLogDateToFragment(mListLogDate.get(position));
        InfoNhatKyFragment nhatKyFragment = (InfoNhatKyFragment) getSupportFragmentManager().findFragmentByTag("NhatKyFragment");
        nhatKyFragment.updateContentWithDate(mListLogDate.get(position));
//        nhatKyFragment.toastFromActivity(mListLogDate.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDataPass(List<String> listData) {
        if (listData.size() > 0) {
            mListLogDate = listData;
            initSpinner(listData);
        }
    }

    public interface InterfaceInteractiveWithFragment {
        void sendLogDateToFragment(String date);
    }

    public void onIterfaceSendDataToFragment(String date) {
        mInteractiveWithFragment.sendLogDateToFragment(date);
    }
}
