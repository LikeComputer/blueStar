package me.tatarka.bindingcollectionadapter2.view_adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;

import me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.Utils;
import me.tatarka.bindingcollectionadapter2.collections.JObservableList;

/**
 * A {@link PagerAdapter} that binds mDataLists to layouts using the given {@link ItemBinding} or {@link
 * OnItemBind}. If you give it an {@link ObservableList} it will also updated itself based on
 * changes to that list.
 */
public class BindingViewPagerAdapter<T> extends PagerAdapter implements BindingCollectionAdapter<T>, JObservableList.JOnListChangedCallback {

    public static final int TAG_VP_HOLDER = 0x11111111;
    public static final int TAG_ROOTVIEW_ID = 0x22222222;
    private ItemBinding<T> itemBinding;
    private JObservableList<T> items;
    private PageTitles<T> pageTitles;
    private SparseArray<LinkedList<ViewDataBinding>> mViewDataBinds = new SparseArray<>(1);
    private SparseArray<LinkedList<ViewDataBinding>> mItemBindings = new SparseArray<>(1);

    @Override
    public void setItemBinding(ItemBinding<T> itemBinding){
        this.itemBinding = itemBinding;
    }

    @Override
    public ItemBinding<T> getItemBinding(){
        return itemBinding;
    }

    @Override
    public void setItems(@Nullable JObservableList<T> items){
        if(this.items == items) {
            return;
        }
        this.items = items;
        this.items.addOnListChangedCallback2(this);
        notifyDataSetChanged();
    }

    @Override
    public T getAdapterItem(int position){
        return items.get(position);
    }

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutRes, ViewGroup viewGroup){
        return DataBindingUtil.inflate(inflater, layoutRes, viewGroup, false);
    }

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, @LayoutRes int layoutRes, int position, T item){
        if(itemBinding.bind(binding, item)) {
            binding.executePendingBindings();
        }
    }

    /**
     * Sets the page titles for the adapter.
     */
    public void setPageTitles(@Nullable PageTitles<T> pageTitles){
        this.pageTitles = pageTitles;
    }

    @Override
    public int getCount(){
        return items == null ? 0 : items.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        T item = items.get(position);
        if(item instanceof PageTitle) {
            return ( (PageTitle)item ).getPageTitle();
        }else {
            return pageTitles == null ? "null" : pageTitles.getPageTitle(position, item);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        T item = items.get(position);
        itemBinding.updateItemLayoutRes(position, item);//更新 BR.id和layoutRes(不同类布局更具位置/数据类型，切换)
        int itemViewType = itemBinding.layoutRes();//一类layoutRes对应一个type
        // 根据 type 找到缓存的 list 某一类布局缓存的 viewdatabinding集合
        LinkedList<ViewDataBinding> itemBindingList = mItemBindings.get(itemViewType);
        ViewDataBinding itembinding;
        if(itemBindingList == null) {
            // 如果 list 为空，表示没有缓存
            // 调用 onCreateViewHolder 创建一个 holder
            itembinding = onCreateBinding(LayoutInflater.from(container.getContext()), itemBinding.layoutRes(), container);
        }else {
            itembinding = itemBindingList.pollLast();
            if(itembinding == null) {
                // 如果 list size = 0，表示没有缓存
                // 调用 onCreateViewHolder 创建一个 holder
                itembinding = onCreateBinding(LayoutInflater.from(container.getContext()), itemBinding.layoutRes(), container);
            }
        }
        onBindBinding(itembinding, itemBinding.variableId(), itemBinding.layoutRes(), position, item);
        View rootView = itembinding.getRoot();
        container.addView(rootView);
        rootView.setTag(TAG_VP_HOLDER, itembinding);
        rootView.setTag(TAG_ROOTVIEW_ID, itemViewType);
        rootView.setTag(item);
        return rootView;
    }

    //item很多的时候切换太快 导致销毁不够及时，会导致多调用onCreateBinding()`
    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        View view = (View)object;
        ViewDataBinding itemBinding = (ViewDataBinding)view.getTag(TAG_VP_HOLDER);
        int itemViewType = (int)view.getTag(TAG_ROOTVIEW_ID);
        LinkedList<ViewDataBinding> holderList = mItemBindings.get(itemViewType);
        if(holderList == null) {
            holderList = new LinkedList<>();
            mItemBindings.append(itemViewType, holderList);
        }
        // 缓存 holder
        holderList.push(itemBinding);
        container.removeView(view);
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view == object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int getItemPosition(Object object){
        T item = (T)( (View)object ).getTag();
        if(items != null) {
            for(int i = 0; i<items.size(); i++) {
                if(item == items.get(i)) {
                    return i;
                }
            }
        }
        return POSITION_NONE;
    }

    @Override
    public void onChanged(JObservableList jObservableList, int fromPosition, int count, Object payload){
        Utils.ensureChangeOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeInserted(JObservableList jObservableList, int fromPosition, int count){
        Utils.ensureChangeOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public void onItemRangeRemoved(JObservableList jObservableList, int fromPosition, int count){
        Utils.ensureChangeOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public void onMoved(JObservableList jObservableList, int fromPosition, int toPosition){
        Utils.ensureChangeOnMainThread();
        notifyDataSetChanged();
    }

    @Override
    public void onClear(JObservableList jObservableList, int oldSize){
        Utils.ensureChangeOnMainThread();
        notifyDataSetChanged();
    }

    public interface PageTitles<T> {
        CharSequence getPageTitle(int position, T item);
    }

    public interface PageTitle {
        CharSequence getPageTitle();
    }
}
