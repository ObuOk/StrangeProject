package com.zgxc.zhihu.ui.adapter;

import android.app.Activity;
import android.view.View;

import com.zgxc.library.annotation.RecyclerViewItemId;
import com.zgxc.library.base.adapter.BaseRecyclerViewAdapter;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.R2;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

public class Zhihu_DailyViewHolder extends BaseRecyclerViewAdapter.ModelViewHolder<ZhihuDailyLatestBean> {

    public Zhihu_DailyViewHolder(View itemView) {
        super(itemView);

    }

    @Override
    public void convert(ZhihuDailyLatestBean item, BaseRecyclerViewAdapter<ZhihuDailyLatestBean> adapter, Activity activity, int position) {

    }

    @Override
    public int getItemLayoutId() {
        return R.layout.zhihu_item_dailylist;
    }
}

