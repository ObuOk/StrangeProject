package com.zgxc.zhihu.contract;

import com.zgxc.library.base.mvp.BasePresenter;
import com.zgxc.library.base.mvp.BaseView;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

public interface ZhihuThemeContract {

     interface Presenter extends BasePresenter {

        void requestData();
    }

     interface View extends BaseView {
        void setData();
        void setRecyclerView(ZhihuDailyLatestBean datas);
    }

}
