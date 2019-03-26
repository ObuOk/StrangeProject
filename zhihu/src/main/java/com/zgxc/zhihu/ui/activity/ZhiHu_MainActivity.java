package com.zgxc.zhihu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.zhihu.R;


@Route(path = ARouterConstant.ZHIHU_MAIN_ACTIVITY)
public class ZhiHu_MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_activity_main);


    }


}
