package com.zgxc.library.base.adapter;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;



public class BaseRecyclerViewAdapter<T extends BaseRecyclerViewAdapter.BaseDataBean> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ModelViewHolder> {

    private final int [] mItemLayoutId;
    private final Class<? extends ModelViewHolder<T>>[] vh;
    public Activity mActivity;
    public List<T> mDatas;
    private ModelViewHolder<T> mHolder;
    private static OnItemClickListener mItemClickListener;


    public BaseRecyclerViewAdapter(Activity activity, List<T> datas, Class<? extends ModelViewHolder<T>>... vh) {
        this.mDatas = datas;
        this.mActivity = activity;
        this.vh = vh;
        //组件化下使用注解不方便，原因是R文件下布局id 非常量，无法传入注解参数中，
        // 改成反射获取
        //      int value = vh.getAnnotation(RecyclerViewItemId.class).value();
        int[] value = {-1};
        try {
            for (int i = 0; i < vh.length; i ++) {
                Class<? extends ModelViewHolder<T>> vhClass = vh[i];
                Method getItemLayoutId = vhClass.getDeclaredMethod("getItemLayoutId");
                Constructor<? extends ModelViewHolder<T>> constructor = vhClass.getConstructor(View.class);
                ModelViewHolder<T> tModelViewHolder = constructor.newInstance(new View(mActivity));
                value[i] = (int) getItemLayoutId.invoke(tModelViewHolder);
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (value[0] == -1) {
            throw new IllegalArgumentException("BaseRecyclerViewAdapter.ModelViewHolder子类的getItemLayoutId() 必须返回正确的itemId");
        }
        mItemLayoutId = value;

    }

    @Override
    public int getItemViewType(int position) {
        if (vh.length > 1) {
            T t = mDatas.get(position);
            return t.getItemViewType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public ModelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try {
            View v;
            if (mItemLayoutId.length == 1) {
                 v = LayoutInflater.from(mActivity).inflate(mItemLayoutId[0],parent,false);
                mHolder = vh[0].getConstructor(View.class).newInstance(v);
            }else {
                if (viewType < mItemLayoutId.length) {
                     v = LayoutInflater.from(mActivity).inflate(mItemLayoutId[viewType],parent,false);
                    mHolder = vh[viewType].getConstructor(View.class).newInstance(v);
                }else {
                    throw new IllegalArgumentException("BaseDataBean的子类返回itemViewType错误");
                }
            }



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

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }



    @Override
    public void onBindViewHolder(ModelViewHolder viewHolder, final int position) {
        viewHolder.convert(mDatas.get(position),this,mActivity,position);
        if (mItemLayoutId != null) {
            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static abstract class ModelViewHolder<T extends BaseRecyclerViewAdapter.BaseDataBean> extends RecyclerView.ViewHolder {
        public View mView;
        public ModelViewHolder(View itemView) {
            super(itemView);
            this.mView = itemView;
        }
        public abstract void convert(T item, BaseRecyclerViewAdapter<T> adapter, Activity activity, int position);
        public abstract int getItemLayoutId();
    }

    public static abstract class BaseDataBean {
        public abstract int getItemViewType();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


}
