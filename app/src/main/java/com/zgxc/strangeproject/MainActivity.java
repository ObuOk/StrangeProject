package com.zgxc.strangeproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.library.arouter.ARouterUtils;
import com.zgxc.zhihu.ui.activity.ZhiHu_MainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void navigation(View view) {
//        ARouterUtils.navigation(ARouterConstant.ZHIHU_MAIN_ACTIVITY);
        startActivity(new Intent(this, ZhiHu_MainActivity.class));
    }
}
