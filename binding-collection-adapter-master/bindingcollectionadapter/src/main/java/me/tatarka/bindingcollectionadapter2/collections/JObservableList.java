package me.tatarka.bindingcollectionadapter2.collections;

import android.databinding.ObservableArrayList;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/12/17 0017.
 */
public class JObservableList<T> extends ObservableArrayList<T> {

    public interface JOnListChangedCallback<T> {

        /**
         *
         * @param ts
         * @param fromPosition
         * @param count
         * @param payload   暂时没用到
         */
        void onChanged(JObservableList<T> ts, int fromPosition, int count, Object payload);

        void onItemRangeInserted(JObservableList<T> ts, int fromPosition, int count);

        void onItemRangeRemoved(JObservableList<T> ts, int fromPosition, int count);

        void onMoved(JObservableList<T> ts, int fromPosition, int toPosition);

        /**
         * @param ts
         * @param oldSize  -1 小于0 表示移除了一个集合{@link #removeAll(Collection)}
         */
        void onClear(JObservableList<T> ts, int oldSize);
    }

    protected ArrayList<JOnListChangedCallback> mListeners = new ArrayList<>();


    public void addOnListChangedCallback2(JOnListChangedCallback listener){
        if(!mListeners.contains(listener)) {
            mListeners.add(listener);
        }
    }

    public ArrayList<JOnListChangedCallback> getListChangedCallbacks(){
        return mListeners;
    }

    public void removeOnListChangedCallback2(JOnListChangedCallback listener){
        mListeners.remove(listener);
    }

    public T change(int index, T object){
        T set = super.set(index, object);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onChanged(this, index, 1, 0);
        }
        return set;
    }

    public T change(int index, T object, Object payload){
        T set = super.set(index, object);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onChanged(this, index, 1, payload);
        }
        return set;
    }

    public void swap(int fromPosition, int toPosition){
        T toItem = get(toPosition);
        super.set(toPosition, get(fromPosition));
        super.set(fromPosition, toItem);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onMoved(this, fromPosition, toPosition);
        }
    }

    @Override
    public T remove(int index){
        T remove = super.remove(index);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onItemRangeRemoved(this, index, 1);
        }
        return remove;
    }

    @Override
    public boolean removeAll(Collection<?> c){
        Log.e("JObservableList", "不建议使用该方法 removeAll");
        boolean b = super.removeAll(c);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onClear(this, -1);
        }
        return b;
    }

    /**
     * 不会触发 变化监听 ，必须使用{@link #remove(int, int)}
     * @param fromIndex
     * @param toIndex
     */
    @Deprecated
    @Override
    protected void removeRange(int fromIndex, int toIndex){
        super.removeRange(fromIndex, toIndex);
    }

    public void remove(int position, int count){
        for(JOnListChangedCallback listener : mListeners) {
            listener.onItemRangeRemoved(this, position, count);
        }
        removeRange(position, position+count);
    }

    @Override
    public boolean add(T object){
        int oldSize = size();
        boolean add = super.add(object);
        if(add) {
            for(JOnListChangedCallback listener : mListeners) {
                listener.onItemRangeInserted(this, oldSize, 1);
            }
        }
        return add;
    }

    @Override
    public void add(int index,T object){
        super.add(index,object);
        for(JOnListChangedCallback listener : mListeners) {
            listener.onItemRangeInserted(this, index, 1);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection){
        int oldSize = size();
        boolean addAll = super.addAll(collection);
        if(addAll) {
            for(JOnListChangedCallback listener : mListeners) {
                listener.onItemRangeInserted(this, oldSize, collection.size());
            }
        }
        return addAll;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        boolean addAll = super.addAll(index, collection);
        if(addAll) {
            for(JOnListChangedCallback listener : mListeners) {
                listener.onItemRangeInserted(this, index, collection.size());
            }
        }
        return addAll;
    }

    @Override
    public void clear(){
        int oldSize = size();
        super.clear();
        if(oldSize>0) {
            for(JOnListChangedCallback listener : mListeners) {
                listener.onClear(this, oldSize);
            }
        }
    }

    /**
     * 修改所有数据
     * @param newList
     */
    public void changeAll(List<T> newList){
        clear();
        addAll(newList);
    }

    /**
     * 不会触发 监听 之改变内容
     * @param newList
     */
    public void changeAllSilent(List<T> newList){
        super.clear();
        super.addAll(newList);
    }
}
