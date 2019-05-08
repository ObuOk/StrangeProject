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
import android.widget.Toast;

import com.zgxc.library.base.adapter.BaseRecyclerViewAdapter;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.contract.ZhihuDailyContract;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;
import com.zgxc.zhihu.presenter.ZhihuDailyPresenter;
import com.zgxc.zhihu.ui.adapter.Zhihu_DailyViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Zhihu_DailyFragment extends Fragment implements ZhihuDailyContract.View {


    private ZhihuDailyContract.Presenter mPresenter  =  new ZhihuDailyPresenter(this);
    private RecyclerView mRcl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.zhihu_fragment_main, container, false);

        mRcl = inflate.findViewById(R.id.rcl);

        return inflate;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subscribe();
        initListener();
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

    public void initListener() {


    }

    @Override
    public void setRecyclerView(ZhihuDailyLatestBean datas) {
        List<ZhihuDailyLatestBean.Stories> stories = datas.getStories();
        List<ZhihuDailyLatestBean.Top_Stories> top_stories = datas.getTop_stories();
        final ArrayList<ZhihuDailyLatestBean.Stories> temp_stories = new ArrayList<>();
        //合并stories ， top_stories
        for (ZhihuDailyLatestBean.Top_Stories s: top_stories
             ) {
            ZhihuDailyLatestBean.Stories stories_t = new ZhihuDailyLatestBean.Stories();
            stories_t.ga_prefix = s.ga_prefix;
            stories_t.id = s.id;
            ArrayList<String> images = new ArrayList<>();
            images.add(s.image);
            stories_t.images = images;
            stories_t.title = s.title;
            stories_t.type = s.type;
            stories_t.top = true;
            temp_stories.add(stories_t);
        }
        temp_stories.addAll(stories);
        BaseRecyclerViewAdapter baseRecyclerViewAdapter = new BaseRecyclerViewAdapter( getActivity(), temp_stories, Zhihu_DailyViewHolder.class);
        baseRecyclerViewAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                turningDetailPage(temp_stories, position);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRcl.setLayoutManager(linearLayoutManager);
        mRcl.setAdapter(baseRecyclerViewAdapter);
    }

    @Override
    public void turningDetailPage(ArrayList<ZhihuDailyLatestBean.Stories> temp_stories, int position) {
        Toast.makeText(getActivity(), "item" + position, Toast.LENGTH_SHORT).show();
    }
}
