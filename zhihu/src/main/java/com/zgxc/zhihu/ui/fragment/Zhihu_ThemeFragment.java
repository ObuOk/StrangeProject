package com.zgxc.zhihu.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgxc.library.base.adapter.BaseRecyclerViewAdapter;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.contract.ZhihuDailyContract;
import com.zgxc.zhihu.contract.ZhihuThemeContract;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;
import com.zgxc.zhihu.presenter.ZhihuDailyPresenter;
import com.zgxc.zhihu.presenter.ZhihuThemePresenter;
import com.zgxc.zhihu.ui.adapter.Zhihu_DailyViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Zhihu_ThemeFragment extends Fragment implements ZhihuThemeContract.View {


    private ZhihuThemeContract.Presenter mPresenter  =  new ZhihuThemePresenter(this);
    private RecyclerView mRcl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zhihu_fragment_theme, container, false);

        mRcl = inflate.findViewById(R.id.rcl);

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

    @Override
    public void setRecyclerView(ZhihuDailyLatestBean datas) {
    }
}
