package com.zgxc.zhihu.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class Zhihu_MainViewPageAdapter extends FragmentPagerAdapter {

    private static final String[] arr_title= {"最热"};
    private List<Fragment> mFragments;

    public Zhihu_MainViewPageAdapter(FragmentManager fm) {
        super(fm);
    }
    public Zhihu_MainViewPageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }
    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arr_title[position];
    }
}
