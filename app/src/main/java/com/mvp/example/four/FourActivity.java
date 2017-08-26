package com.mvp.example.four;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.example.four.adapter.MyPagerAdapter;
import com.viettel.ktts.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FourActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    MyPagerAdapter myPagerAdapter;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.vpPager)
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        ButterKnife.bind(this);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
    }

    // This method will be invoked when the current page is scrolled
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // This method will be invoked when a new page becomes selected.
    @Override
    public void onPageSelected(int position) {
        Toast.makeText(FourActivity.this,
                "Selected page position: " + position, Toast.LENGTH_SHORT).show();
    }

    // Called when the scroll state changes:
    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.btnSave)
    public void onBtnSaveClick() {
        if (viewPager != null) {
            viewPager.setCurrentItem(2);
        }
    }
}
