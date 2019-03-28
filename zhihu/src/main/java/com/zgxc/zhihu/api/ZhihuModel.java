package com.zgxc.zhihu.api;

import com.zgxc.library.http.RetrofitWrapper;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

import io.reactivex.Observable;


public class /**/ZhihuModel {

    private static ZhihuModel instance = null;
    private static ZhihuApi mApi = null;
    private static final String BASE_ZHIHU_URL = "http://news-at.zhihu.com/api/4/";

    public ZhihuModel() {
        mApi = RetrofitWrapper.getInstance(BASE_ZHIHU_URL)
                .create(ZhihuApi.class);
    }

    public static ZhihuModel getInstance() {
        if (instance == null) {
            return new ZhihuModel();
        }
        return instance;
    }


    public Observable<ZhihuDailyLatestBean> getDailyLates() {
        return mApi.getDailyLates();
    }





}
