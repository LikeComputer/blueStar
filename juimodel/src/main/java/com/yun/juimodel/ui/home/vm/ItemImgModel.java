package com.yun.juimodel.ui.home.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;
import android.widget.Toast;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;

/**
 * Created by evan on 6/14/15.
 */
public class ItemImgModel extends BaseObservable {
    public static final int layoutRes = R.layout.demo_inner_recv_img;
    public final boolean checkable;
    @Bindable public int index;
    @Bindable public boolean checked;


    public ItemImgModel(int index) {
        this.index = index;
        this.checkable = true;
    }

    //    public int getIndex() {
    //        return index;
    //    }
    //
    //    public boolean isChecked() {
    //        return checked;
    //    }


    public boolean onToggleChecked(View v) {
        //        if (!checkable) {
        //            return false;
        //        }
        checked = !checked;
        notifyPropertyChanged(BR.checked);
        return true;
    }


    public void clickShow(View v, int index) {

        Toast.makeText(v.getContext(), index + "----", Toast.LENGTH_SHORT).show();
    }
}
