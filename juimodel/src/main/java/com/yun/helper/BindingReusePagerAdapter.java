package com.yun.helper;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/12/26.
 */
public class BindingReusePagerAdapter<T extends BindingReusePagerAdapter.JVpItem> extends PagerAdapter {

    public static final int TAG_VP_HOLDER = 0x11111111;

    private SparseArray<LinkedList<BindingHolder>> holders = new SparseArray<>(1);
    private List<T> mItems = new ArrayList<>();
    private LayoutInflater mLayoutInflater;


    /**
     * 获取 view type
     *
     * @param position position
     * @return type
     */
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutRes();
    }


    /**
     * 创建 holder
     *
     * @param parent parent
     * @param viewType type
     * @return holder
     */
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mLayoutInflater = LayoutInflater.from(parent.getContext());
        return new BindingHolder(DataBindingUtil.inflate(mLayoutInflater, viewType, parent, false));
    }


    /**
     * 绑定 holder
     *
     * @param holder holder
     * @param position position
     */
    public void onBindViewHolder(BindingHolder holder, int position) {
        final T item = mItems.get(position);
        holder.getBinding().setVariable(item.getBRid(), item);
        holder.getBinding().executePendingBindings();
    }


    @Override public int getCount() {
        return mItems.size();
    }


    @Override public Object instantiateItem(ViewGroup container, int position) {
        position = getRealPosition(position);
        // 获取 position 对应的 type 对应布局
        int itemViewType = getItemViewType(position);
        // 根据 type 找到缓存的 list
        LinkedList<BindingHolder> holderList = holders.get(itemViewType);
        BindingHolder holder;
        if(holderList == null) {
            // 如果 list 为空，表示没有缓存
            // 调用 onCreateViewHolder 创建一个 holder
            holder = onCreateViewHolder(container, itemViewType);
            holder.itemView.setTag(TAG_VP_HOLDER, holder);
        } else {
            holder = holderList.pollLast();
            if(holder == null) {
                // 如果 list size = 0，表示没有缓存
                // 调用 onCreateViewHolder 创建一个 holder
                holder = onCreateViewHolder(container, itemViewType);
                holder.itemView.setTag(TAG_VP_HOLDER, holder);
            }
        }
        holder.position = position;
        holder.viewType = itemViewType;
        // 调用 onBindViewHolder 对 itemView 填充数据
        onBindViewHolder(holder, position);
        container.addView(holder.itemView);
        return holder.itemView;
    }


    @Override public void destroyItem(ViewGroup container, int position, Object object) {
        position = getRealPosition(position);
        View view = (View) object;
        container.removeView(view);
        BindingHolder holder = (BindingHolder) view.getTag(TAG_VP_HOLDER);
        int itemViewType = holder.viewType;
        LinkedList<BindingHolder> holderList = holders.get(itemViewType);
        if(holderList == null) {
            holderList = new LinkedList<>();
            holders.append(itemViewType, holderList);
        }
        // 缓存 holder
        holderList.push(holder);
    }


    @Override public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    public void addAll(List<T> data) {
        mItems.addAll(data);
        notifyDataSetChanged();
    }


    public void setItems(List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }


    protected int getRealPosition(int position) {
        return position;
    }


    public static class BindingHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mViewDataBinding;
        public int position;
        public int viewType;


        public BindingHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
        }


        public ViewDataBinding getBinding() {
            return mViewDataBinding;
        }
    }

    public interface JVpItem {
        int getBRid();

        int getLayoutRes();
    }
}