package me.tatarka.bindingcollectionadapter2.itembindings;

import android.support.annotation.LayoutRes;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * @another 江祖赟
 * @date 2017/12/26 0026.
 */
public class ItemBindModel<T> extends OnItemBind<T> {
    private int variableId;
    private int layoutRes;

    public ItemBindModel(int variableId, int layoutRes){
        this.variableId = variableId;
        this.layoutRes = layoutRes;
    }

    public static <T> ItemBindModel<T> of(int variableId, @LayoutRes int layoutRes){
        return new ItemBindModel<T>(variableId,layoutRes);
    }

    @Override
    public void onItemBind(ItemBinding itemBinding, int position, Object item){
        itemBinding.set(variableId, layoutRes);
    }
}
