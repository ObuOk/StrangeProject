package com.zgxc.zhihu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.tencent.smtt.sdk.WebView;
import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.ui.adapter.Zhihu_MainViewPageAdapter;
import com.zgxc.zhihu.ui.fragment.Zhihu_DailyFragment;
import com.zgxc.zhihu.ui.fragment.Zhihu_ThemeFragment;

import java.util.ArrayList;


@Route(path = ARouterConstant.ZHIHU_MAIN_ACTIVITY)
public class ZhiHu_DetailActivity extends AppCompatActivity {


    private WebView mWb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_activity_main);

        initView();


    }

    private void initView() {
        mWb = findViewById(R.id.wb);


    }


}
