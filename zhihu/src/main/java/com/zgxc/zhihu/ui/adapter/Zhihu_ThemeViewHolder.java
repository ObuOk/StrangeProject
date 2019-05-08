package com.zgxc.zhihu.ui.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zgxc.library.base.adapter.BaseRecyclerViewAdapter;
import com.zgxc.zhihu.R;
import com.zgxc.zhihu.model.ZhihuDailyLatestBean;

public class Zhihu_ThemeViewHolder extends BaseRecyclerViewAdapter.ModelViewHolder<ZhihuDailyLatestBean.Stories> {


    private final TextView mTv_title;
    private final ImageView mIv_desc;
    private final TextView mTv_top;

    public Zhihu_ThemeViewHolder(View itemView) {
        super(itemView);
        mTv_title = itemView.findViewById(R.id.tv_title);
        mIv_desc = itemView.findViewById(R.id.iv_desc);
        mTv_top = itemView.findViewById(R.id.tv_top);

    }

    @Override
    public void convert(ZhihuDailyLatestBean.Stories item, BaseRecyclerViewAdapter<ZhihuDailyLatestBean.Stories> adapter, Activity activity, int position) {
        mTv_title.setText(item.title);
        Picasso.with(activity).load(item.images.get(0)).into(mIv_desc);
        if (item.top) {
            mTv_top.setVisibility(View.VISIBLE);
        }else {
            mTv_top.setVisibility(View.GONE);
        }
    }




    @Override
    public int getItemLayoutId() {
        return R.layout.zhihu_list_item;
    }
}

