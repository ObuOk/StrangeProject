package com.zgxc.zhihu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.ui.adapter.Zhihu_MainViewPageAdapter;
import com.zgxc.zhihu.ui.fragment.Zhihu_DailyFragment;

import java.util.ArrayList;


@Route(path = ARouterConstant.ZHIHU_MAIN_ACTIVITY)
public class ZhiHu_MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_activity_main);

        initView();


    }

    private void initView() {
        mTabLayout = findViewById(R.id.tl);
        mViewPager = findViewById(R.id.vp);


        Zhihu_DailyFragment zhihu_dailyFragment = new Zhihu_DailyFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(zhihu_dailyFragment);
        Zhihu_MainViewPageAdapter zhihu_mainViewPageAdapter =
                new Zhihu_MainViewPageAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(zhihu_mainViewPageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }


}
