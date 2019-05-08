package com.zgxc.zhihu.api;

import com.zgxc.zhihu.model.ZhihuDailyLatestBean;
import com.zgxc.zhihu.model.ZhihuThemeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuApi {

    @GET("news/latest")
    Observable<ZhihuDailyLatestBean> getDailyLates();


    /**
     * 主题日报
     */
    @GET("themes")
    Observable<ZhihuThemeBean> getThemeList();

//    /**
//          * 日报详情
//          */
//    @GET("news/{id}")
//    Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);

//
//
//    /**
//     * 专栏日报
//     */
//    @GET("sections")
//    rx.Observable<ZhiHuSectionBean> getSectionList();
//
//    /**
//     * 热门日报
//     */
//    @GET("news/hot")
//    rx.Observable<ZhiHuHotBean> getHotList();
//
//
//    /**
//     * 日报详情
//     */
//    @GET("news/{id}")
//    rx.Observable<ZhihuDetailBean> getDetailInfo(@Path("id") int id);
//
//    /**
//     * 日报的额外信息
//     */
//    @GET("story-extra/{id}")
//    rx.Observable<ZhiHuDetailExtraBean> getDetailExtraInfo(@Path("id") int id);
//
//    /**
//     * 日报的长评论
//     */
//    @GET("story/{id}/long-comments")
//    rx.Observable<ZhiHuCommentBean> getLongCommentInfo(@Path("id") int id);
//
//    /**
//     * 日报的短评论
//     */
//    @GET("story/{id}/short-comments")
//    rx.Observable<ZhiHuCommentBean> getShortCommentInfo(@Path("id") int id);
//
//    /**
//     * 主题日报详情List
//     */
//    @GET("theme/{id}")
//    rx.Observable<ZhiHuThemeChildBean> getThemeChildList(@Path("id") int id);
//
//    /**
//     * 专栏日报详情
//     */
//    @GET("section/{id}")
//    rx.Observable<ZhiHuSectionChildBean> getSectionChildList(@Path("id") int id);
//
//    /**
//     * 往期日报
//     */
//    @GET("news/before/{date}")
//    rx.Observable<ZhiHuDailyBeforeListBean> getDailyBeforeList(@Path("date") String date);
}
