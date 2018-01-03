package com.n4399.miniworld.vp.workshop.material;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.MPackage;
import com.n4399.miniworld.vp.basic.JAbsListFrgmt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [材质包]
 */
public class MaterialFrgmt extends JAbsListFrgmt<MPackage,MPackage> {

    public static Fragment getInstance(){
        return new MaterialFrgmt();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new GeneralListPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(MPackage.class, new SimpleRecvBinder<MPackage>(R.layout.item_ws_material_package));
    }

    @Override
    public void showSucceed(List<MPackage> data){
        data = new ArrayList<>();
        data.add(new MPackage());
        data.add(new MPackage());
        data.add(new MPackage());
        data.add(new MPackage());
        data.add(new MPackage());
        data.add(new MPackage());
        data.add(new MPackage());
        super.showSucceed(data);
    }
}
