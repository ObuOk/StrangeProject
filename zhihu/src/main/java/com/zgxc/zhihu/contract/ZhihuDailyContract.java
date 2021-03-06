package com.zgxc.zhihu.contract;

import com.zgxc.library.base.mvp.BasePresenter;
import com.zgxc.library.base.mvp.BaseView;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

import java.util.ArrayList;

public interface ZhihuDailyContract {

     interface Presenter extends BasePresenter {

        void requestData();
    }

     interface View extends BaseView {
        void setData();
        void setRecyclerView(ZhihuDailyLatestBean datas);
        void turningDetailPage(ArrayList<ZhihuDailyLatestBean.Stories> temp_stories, int position);
    }

}
