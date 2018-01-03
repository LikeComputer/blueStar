package com.n4399.miniworld.vp.raiders.question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.blueprint.adapter.OnItemClickListener;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.GrideDividerDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.GameQueBean;
import com.n4399.miniworld.data.event.SearchEvent;
import com.n4399.miniworld.vp.basic.BaseSearchResultFrgmt;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.LibApp.findDimens;
import static com.n4399.miniworld.Constants.CKEY_FRGMT;
import static com.n4399.miniworld.Constants.RaidersKey.FM_SEARCHRESULT;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [包括所有和搜索]
 */
public class QuestionFrgmt extends BaseSearchResultFrgmt<GameQueBean,GameQueBean> implements OnItemClickListener<GameQueBean> {

    private String mType;

    public static Fragment getInstance(){
        return new QuestionFrgmt();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        if(getArguments() != null) {
            mType = getArguments().getString(CKEY_FRGMT);
        }
        super.onCreate(savedInstanceState, FM_SEARCHRESULT.equals(mType));
    }

    @Override
    protected GeneralListPresenter<GameQueBean> initListPresenter(){
        TAG = "QuestionFrgmt 游戏问答";
        return new GeneralListPresenter<GameQueBean>(this);
    }

    @Override
    public void toDoSearch(SearchEvent searchEvent){
        if(getUserVisibleHint()) {
            mGeneralListPresenter.search(searchEvent.search_key);
        }
    }


    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new GridLayoutManager(getContext(), 2);
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new GrideDividerDecoration(findDimens(R.dimen.card_pading_8));
    }


    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(GameQueBean.class, new SimpleRecvBinder(this,R.layout.recv_item_raiders_gamequestion));
    }


    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }

    @Override
    public void showSucceed(List<GameQueBean> data){
        mListData = new ArrayList<>();
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        mListData.add(new GameQueBean());
        super.showSucceed(mListData);
    }

    @Override
    public void onItemClicked(GameQueBean itemData, int position){
        Toast.makeText(getContext(), ""+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMoreLoad(List<GameQueBean> containerData){

    }
}
