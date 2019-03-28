package com.zgxc.strangeproject;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        Utils.init(this);
        LogUtils.Config config = LogUtils.getConfig();
        //边框开关，设置打开
        config.setBorderSwitch(true);
        //logcat 是否打印，设置打印
        config.setConsoleSwitch(true);
        //设置打印日志总开关，线上时关闭
        config.setLogSwitch(true);
    }
}
