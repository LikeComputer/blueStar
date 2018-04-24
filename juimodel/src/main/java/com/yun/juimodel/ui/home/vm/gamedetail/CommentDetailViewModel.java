package com.yun.juimodel.ui.home.vm.gamedetail;

import android.databinding.Bindable;
import com.yun.juimodel.BR;
import com.yun.juimodel.R;
import com.yun.juimodel.data.PageDataWrapper;
import com.yun.juimodel.ui.home.m.Comment;
import com.yun.juimodel.ui.home.m.CommonDetail;
import com.yun.juimodel.ui.home.m.GameInfo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

/**
 * @another 江祖赟
 * @date 2018/1/2.
 * 游戏详情评论
 */
public class CommentDetailViewModel extends LoadMoreObjectViewModel {

    @Bindable
    public GameInfo gameInfo;
    //拿到接口数据之后 刷新到 commentPageData 第一页的时候会用到添加数据头
    PageDataWrapper<Object> commentPageData = new PageDataWrapper<>();


    public CommentDetailViewModel() {
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
        notifyPropertyChanged(BR.gameInfo);
    }

    @Override public void toGetData(HashMap mapParam) {
        collectDisposables(Observable.just(0).delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override public void accept(Integer integer) throws Exception {
                if (isFirstPage()) {
                    gameInfo = new GameInfo();
                    notifyPropertyChanged(BR.gameInfo);
                    commentPageData.getList().clear();
                    //我的评论item
                    commentPageData.getList().add(new Comment.CommentBean().asDetail());
                    //其他 回复 数据
                    List<Object> commentBeans = new ArrayList<>();
                    for (int i = 0; i < 10; i++) {
                        commentBeans.add(new CommonDetail.ReplyDetail());
                    }
                    commentPageData.getList().addAll(commentBeans);
                    //是否有下一页数据 存储
                    commentPageData.setHasNext(true);
                    afterGetDataSucceed(commentPageData);
                }
                else {
                    //非第一页 数据主关注 其他 回复
                    //直接使用 接口的 pageDataWrapper数据
                    PageDataWrapper<Object> no1CommentPageData = new PageDataWrapper<>();
                    List<Object> commentBeans = new ArrayList<>();
                    for (int i = getDataLists().size(); i < getDataLists().size()+10; i++) {
                        commentBeans.add(new CommonDetail.ReplyDetail());
                    }
                    no1CommentPageData.getList().addAll(commentBeans);
                    afterGetDataSucceed(no1CommentPageData);
                }

            }
        }));
    }


    private void afterGetDataSucceed(PageDataWrapper<Object> commentPageData) {

    }


    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
        multipleItems.regist(Comment.CommentBean.class, BR.commentModel, R.layout.item_gamedetail_comment)
                     .regist(CommonDetail.ReplyDetail.class, BR.replayViewModel, R.layout.item_comment_detail_reply);
    }


    public void publishReply(CommonDetail.ReplyDetail replyDetail) {
        getDataLists().add(1, replyDetail);
        mRecyclerView.get().getAdapter().notifyDataSetChanged();
        mRecyclerView.get().smoothScrollToPosition(1);
    }
}
