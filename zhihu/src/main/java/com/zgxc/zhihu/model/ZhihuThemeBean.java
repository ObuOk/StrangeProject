package com.zgxc.zhihu.model;


import com.zgxc.library.base.adapter.BaseRecyclerViewAdapter;

import java.util.List;

/**
 *
 *{
 "limit": 1000,
 "subscribed": [],
 "others": []
 }
 *
 */
public class ZhihuThemeBean extends BaseRecyclerViewAdapter.BaseDataBean {


    @Override
    public int getItemViewType() {
        return 0;
    }
}
