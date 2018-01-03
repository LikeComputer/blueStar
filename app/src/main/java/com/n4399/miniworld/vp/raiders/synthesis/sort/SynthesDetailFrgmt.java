package com.n4399.miniworld.vp.raiders.synthesis.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.TjdqBean;
import com.n4399.miniworld.data.bean.WpdqBean;
import com.n4399.miniworld.vp.basic.JBasicWebViewAt;
import com.n4399.miniworld.vp.jpublic.SearchListFrgmt;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.Consistent.Common.DIFF_INDEX;
import static com.blueprint.Consistent.Common.DIFF_TYPE;
import static com.n4399.miniworld.vp.raiders.synthesis.sort.SynthesDetailActivy.图鉴大全;

/**
 * @another 江祖赟
 * @date 2017/6/23.
 * 图鉴合成详情
 */
public class SynthesDetailFrgmt extends SearchListFrgmt<Object> implements OnItemClickListener<Object> {

    private int mSpanCount;
    private Disposable mSubscribe;
    private String mType;

    public static Fragment getInstance(String type, int index){
        SynthesDetailFrgmt synthesDetailFrgmt = new SynthesDetailFrgmt();
        Bundle bundle = new Bundle();
        bundle.putString(DIFF_TYPE, type);
        bundle.putInt(DIFF_INDEX, index);
        synthesDetailFrgmt.setArguments(bundle);
        return synthesDetailFrgmt;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        //图鉴 pokedex  物品 goods  【service/pokedex/getlist】
        mType = arguments.getString(DIFF_TYPE);
        int index = arguments.getInt(DIFF_INDEX);//二级分类。。区分地址  类型 参数为index  1：作物，2：工具，3：杂物，4：方块。默认0返回所有类型
        if(图鉴大全.equals(mType)) {
            mSpanCount = 3;
        }else {
            mSpanCount = 4;
        }
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return new SynthesListPresenter(this);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(), mSpanCount);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(LibApp.findDimens(R.dimen.card_pading_8));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(TjdqBean.class, new SimpleRecvBinder(this, R.layout.recv_item_raiders_tjdq));
        multiTypeAdapter.register(WpdqBean.class, new SimpleRecvBinder(this, R.layout.recv_item_raiders_wpbk));
    }

    @Override
    protected CharSequence setEditHintContent(){
        return LibApp.findString(R.string.fm_radiers_search_hint);
    }

    @Override
    public void showSucceed(List<Object> data){
        mListData = new ArrayList<>();
        if(图鉴大全.equals(mType)) {
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
            mListData.add(new TjdqBean());
        }else {
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
            mListData.add(new WpdqBean());
        }
        super.showSucceed(mListData);
    }

    @Override
    public void onItemClicked(Object itemData, int position){
        JBasicWebViewAt.start(getActivity(), Consistent.TEMP.HTML);
    }
}
