package com.zgxc.zhihu.api;

import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ZhihuApi {

    @GET("news/latest")
    Observable<ZhihuDailyLatestBean> getDailyLates();


}
