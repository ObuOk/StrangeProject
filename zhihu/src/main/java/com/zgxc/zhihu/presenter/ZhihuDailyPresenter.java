package com.zgxc.zhihu.presenter;

import com.zgxc.zhihu.api.ZhihuModel;
import com.zgxc.zhihu.contract.ZhihuDailyContract;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ZhihuDailyPresenter implements ZhihuDailyContract.Presenter {


    private final ZhihuDailyContract.View mView;

    public ZhihuDailyPresenter(ZhihuDailyContract.View view) {
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
        ZhihuModel.getInstance().getDailyLates()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ZhihuDailyLatestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ZhihuDailyLatestBean zhihuDailyLatestBean) {

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
