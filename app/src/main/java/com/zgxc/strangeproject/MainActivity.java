package com.zgxc.strangeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.library.arouter.ARouterUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouterUtils.navigation(ARouterConstant.ZHIHU_MAIN_ACTIVITY);


    }
}
