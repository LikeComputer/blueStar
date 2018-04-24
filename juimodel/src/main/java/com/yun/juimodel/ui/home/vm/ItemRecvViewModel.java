package com.yun.juimodel.ui.home.vm;

import android.view.View;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.ui.home.m.Home;
import com.yun.juimodel.ui.home.v.HomeMoreAct;
import com.yun.juimodel.ui.home.v.More2BookAct;
import java.util.List;
import me.tatarka.bindingcollectionadapter2.collections.JObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.ExtrasBindViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;
import me.tatarka.bindingcollectionadapter2.recv.LayoutManagers;

import static com.yun.juimodel.ui.home.v.More2BookAct.HOME_MORE_BOOKGAME;

/**
 * Created by evan on 6/14/15.
 * 内部 recycleview
 */
public class ItemRecvViewModel extends ExtrasBindViewModel implements LayoutManagers.FullSpan {

    public static final int layoutRes = R.layout.demo_inner_recv;

    public static final int MORE_WELFARE = 0;
    public static final int MORE_GAMEBOOK = 1;
    public static final int MORE_GAMERES = 2;
    public int moreType = -1;
    public boolean needShowMore;
    public String title = "鉴赏家推荐游戏";
    //所有的数据集合 必须是 JObservableList
    public JObservableList items = new JObservableList();
    //OnItemBindClass
    //注册 item 和 对应的布局
    public OnItemBindClass recvListBinding = new OnItemBindClass()
            //游戏资源
            .regist(Home.ResourceBean.class, BR.gameRes, R.layout.item_home_game_res)
            //鉴赏家推荐游戏
            .regist(Home.HotRecommendBean.class, BR.hotRecom, R.layout.item_home_hotgame_recom)
            //福利中心
            .regist(Home.WelfareBean.class, BR.welfareModel, R.layout.item_home_welfare)
            //游戏预定
            .regist(Home.GameBookBean.class, BR.gameBook, R.layout.item_home_gamebook);

    //public ItemBinding recvListBinding = ItemBinding.of(com.a4399.funnycore.BR.itemImgModel,
    //        R.layout.demo_inner_recv_img);


    public ItemRecvViewModel() {
        for (int i = 0; i < 10; i++) {
            items.add(new Home.HotRecommendBean());
        }
    }


    public ItemRecvViewModel(List data) {
        items.addAll(data);
    }


    public ItemRecvViewModel(boolean needShowMore, List data) {
        this.needShowMore = needShowMore;
        items.addAll(data);
    }


    public ItemRecvViewModel asWelfare() {
        needShowMore = true;
        moreType = MORE_WELFARE;
        title = "福利中心";
        return this;
    }


    public ItemRecvViewModel asGameBook() {
        needShowMore = true;
        moreType = MORE_GAMEBOOK;
        title = "新游预约";
        return this;
    }
    public ItemRecvViewModel asGameRes() {
        needShowMore = true;
        moreType = MORE_GAMERES;
        title = "游戏资源";
        //游戏资源数量小于5显示长图
        Home.ResourceBean.styleLone=items.size()<5;
        return this;
    }


    /**
     * 查看更多
     */
    public void showMore(View view) {
        if (moreType == MORE_WELFARE) {
            HomeMoreAct.startAct4View(view,HomeMoreAct.HOME_MORE_WEALFARE);
        }
        else if (moreType == MORE_GAMEBOOK) {
            More2BookAct.startAct4View(view, HOME_MORE_BOOKGAME);
        }
        else if (moreType == MORE_GAMERES) {
            HomeMoreAct.startAct4View(view,HomeMoreAct.HOME_MORE_GAMERES);
        }
    }
}
