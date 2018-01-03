package me.tatarka.bindingcollectionadapter2;

import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Callback for setting up a {@link ItemBinding} for an item in the collection.
 *
 * @param <T>
 *         the item type
 */
public abstract class OnItemBind<T> {
    private SparseArray<WeakReference<Object>> extraBindings;

    /**
     * Bind an extra variable to the view with the given variable id. The same instance will be
     * provided to all views the binding is bound to.
     */
    public final OnItemBind<T> bindExtra(int variableId, Object value){
        if(extraBindings == null) {
            extraBindings = new SparseArray<>(1);
        }
        extraBindings.put(variableId, new WeakReference<Object>(value));
        return this;
    }

    /**
     * Called on each item in the collection, allowing you to modify the given {@link ItemBinding}.
     * Note that you should not do complex processing in this method as it's called many times.
     * 更新 itemBinding layoutRes
     */
    public abstract void onItemBind(ItemBinding itemBinding, int position, T item);

    public SparseArray<WeakReference<Object>> getExtraBindings(){
        return extraBindings;
    }

    public void setExtraBindings(SparseArray<WeakReference<Object>> extraBindings){
        this.extraBindings = extraBindings;
    }
}
