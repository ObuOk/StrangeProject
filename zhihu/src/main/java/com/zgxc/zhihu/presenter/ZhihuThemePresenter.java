package com.zgxc.zhihu.presenter;

import com.zgxc.zhihu.api.ZhihuModel;
import com.zgxc.zhihu.contract.ZhihuDailyContract;
import com.zgxc.zhihu.contract.ZhihuThemeContract;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;
import com.zgxc.zhihu.model.ZhihuThemeBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ZhihuThemePresenter implements ZhihuThemeContract.Presenter {


    private final ZhihuThemeContract.View mView;

    public ZhihuThemePresenter(ZhihuThemeContract.View view) {
        mView = view;

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void requestData() {
        ZhihuModel.getInstance().getThemeList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ZhihuThemeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhihuThemeBean zhihuDailyLatestBean) {
//                        mView.setRecyclerView(zhihuDailyLatestBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }
}
