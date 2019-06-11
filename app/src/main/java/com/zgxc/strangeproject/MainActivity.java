package com.zgxc.strangeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.library.arouter.ARouterUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_zhihu = findViewById(R.id.btn_zhihu);
        Button btn_view = findViewById(R.id.btn_view);


        btn_zhihu.setOnClickListener(this);
        btn_view.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_zhihu:
                ARouterUtils.navigation(ARouterConstant.ZHIHU_MAIN_ACTIVITY);
                break;

                case R.id.btn_view:
                    ARouterUtils.navigation(ARouterConstant.VIEW_MAIN_ACTIVITY);
                    break;

        }



    }
}
