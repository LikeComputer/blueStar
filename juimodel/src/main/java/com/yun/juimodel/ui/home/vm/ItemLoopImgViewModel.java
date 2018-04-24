package com.yun.juimodel.ui.home.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import com.yun.helper.BindingReusePagerAdapter;
import com.yun.juimodel.R;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;
import me.tatarka.bindingcollectionadapter2.recv.LayoutManagers;

/**
 * Created by evan on 6/14/15.
 */
public class ItemLoopImgViewModel extends ExtrasBindViewModel implements LayoutManagers.FullSpan{
    public static final int layoutRes = R.layout.item_vp_loopimg;
    public ObservableList<BindingReusePagerAdapter.JVpItem> items = new ObservableArrayList<>();

    public ItemLoopImgViewModel(List<BindingReusePagerAdapter.JVpItem> items) {
        this.items.addAll(items) ;
    }
}
