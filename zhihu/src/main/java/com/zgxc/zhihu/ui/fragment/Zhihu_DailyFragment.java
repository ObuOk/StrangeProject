package com.zgxc.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgxc.zhihu.R;
import com.zgxc.zhihu.contract.ZhihuDailyContract;
import com.zgxc.zhihu.presenter.ZhihuDailyPresenter;

public class Zhihu_DailyFragment extends Fragment implements ZhihuDailyContract.View {


    private ZhihuDailyContract.Presenter mPresenter  =  new ZhihuDailyPresenter(this);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zhihu_fragment_main, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setData();
    }

    @Override
    public void setData() {
        mPresenter.requestData();
    }
}
