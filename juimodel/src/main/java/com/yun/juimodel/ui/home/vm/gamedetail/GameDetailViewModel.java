package com.yun.juimodel.ui.home.vm.gamedetail;

import android.databinding.Bindable;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.ui.home.m.GameDetail;
import com.yun.juimodel.ui.home.m.GameInfo;
import java.util.HashMap;
import jzy.easybindpagelist.statehelper.BaseDiffSteteViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @another 江祖赟
 * @date 2018/1/2.
 */
public class GameDetailViewModel extends BaseDiffSteteViewModel {

    {pageState.set(3);}

    //注册 detail info 图片的item 推荐游戏的item
    public static OnItemBindClass gameDetailBinding = new OnItemBindClass()
            .regist(String.class, BR.prewPic,R.layout.item_gamedetail_prewpics)
             .regist(GameDetail.RecommendBean.class, BR.recomGames,R.layout.item_gamedetail_recomgames);

    @Bindable public GameInfo gameInfo;


    @Override
    protected void toGetData(HashMap mapParam) {
        //数据 由两个tab获取然后传过来
        hideLoading();
    }


    public void showGameDetail(GameInfo gameInfo) {
        if(this.gameInfo == null) {
            this.gameInfo = gameInfo;
            hideLoading();
            notifyPropertyChanged(BR.gameInfo);
        }
    }
}
