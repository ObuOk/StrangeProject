package com.zgxc.zhihu.contract;

import com.zgxc.library.base.mvp.BasePresenter;
import com.zgxc.library.base.mvp.BaseView;

public interface ZhihuDailyContract {

    public interface Presenter extends BasePresenter {

        void requestData();
    }

    public interface View extends BaseView {
        void setData();
    }

}
