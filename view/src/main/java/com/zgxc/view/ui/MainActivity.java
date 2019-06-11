package com.zgxc.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zgxc.library.arouter.ARouterConstant;
import com.zgxc.view.R;
import com.zgxc.view.ui.view.RecylePage;


@Route(path = ARouterConstant.VIEW_MAIN_ACTIVITY)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_main);

        RecylePage rp = findViewById(R.id.rp);
        View v1 = findViewById(R.id.v1);
        View v2 = findViewById(R.id.v2);
        View v3 = findViewById(R.id.v3);

        rp.setOnClickListener(this);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
//        Toast.makeText(this, "被点击了", Toast.LENGTH_SHORT).show();
        int i = view.getId();
        RecylePage.LayoutParam layoutParams = (RecylePage.LayoutParam) view.getLayoutParams();
        if (i == R.id.v1) {
            Toast.makeText(this, "v1 ===" + layoutParams.from, Toast.LENGTH_SHORT).show();
        } else if (i == R.id.v2) {
            Toast.makeText(this, "v2 ===" + layoutParams.from, Toast.LENGTH_SHORT).show();

        } else if (i == R.id.v3) {
            Toast.makeText(this, "v3 ===" + layoutParams.from, Toast.LENGTH_SHORT).show();

        }
    }
}
