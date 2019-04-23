package com.zgxc.library.base.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zgxc.library.annotation.RecyclerViewItemId;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;



public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ModelViewHolder> {

    private final int mItemLayoutId;
    private final Class<? extends ModelViewHolder<T>> vh;
    public Activity mActivity;
    public List<T> mDatas;
    private ModelViewHolder<T> mHolder;


    public BaseRecyclerViewAdapter(Class<ModelViewHolder<T>> vh,Activity activity, List<T> datas) {
        this.mDatas = datas;
        this.mActivity = activity;
        this.vh = vh;
        //组件化下使用注解不方便，原因是R文件下布局id 非常量，无法传入注解参数中，
        // 改成反射获取
        //      int value = vh.getAnnotation(RecyclerViewItemId.class).value();
        int value = -1;
        try {
            Method getItemLayoutId = vh.getDeclaredMethod("getItemLayoutId");
            ModelViewHolder<T> tModelViewHolder = vh.newInstance();
            value = (int) getItemLayoutId.invoke(tModelViewHolder);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (value == -1) {
            throw new IllegalArgumentException("BaseRecyclerViewAdapter.ModelViewHolder子类的getItemLayoutId() 必须返回正确的itemId");
        }
        mItemLayoutId = value;

    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            View v = LayoutInflater.from(mActivity).inflate(mItemLayoutId,parent,false);
            mHolder = vh.getConstructor(View.class).newInstance(v);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return mHolder;
    }

    public void add(int positon, T data) {
        mDatas.add(positon, data);
        notifyItemInserted(positon);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 500);

    }

    public void add(T data) {
        mDatas.add(data);
        notifyDataSetChanged();

    }

    public void add(List<T> data) {
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        }, 500);
    }

    public void replace(int position, T data) {
        mDatas.remove(position);
        mDatas.add(position == 0 ? position : position - 1, data);
        notifyDataSetChanged();
    }

    public void addAll(List<T> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void refresh() {
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return mDatas;
    }



    @Override
    public void onBindViewHolder(ModelViewHolder viewHolder, int position) {
        viewHolder.convert(mDatas.get(position),this,mActivity,position);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static abstract class ModelViewHolder<T> extends RecyclerView.ViewHolder {

        public ModelViewHolder(View itemView) {
            super(itemView);
        }
        public abstract void convert(T item, BaseRecyclerViewAdapter<T> adapter, Activity activity, int position);
        public abstract int getItemLayoutId();
    }
}
