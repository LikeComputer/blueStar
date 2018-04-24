//package com.yun.juimodel.ui.home.vm.gamedetail;
//
//import com.yun.juimodel.BR;
//import com.yun.juimodel.R;
//import com.yun.juimodel.data.PageDataWrapper;
//import com.yun.juimodel.ui.home.m.Comment;
//import com.yun.juimodel.ui.home.m.GameDetail;
//import com.yun.juimodel.ui.home.m.GameInfo;
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.functions.Consumer;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//import jzy.easybindpagelist.loadmorehelper.LoadMoreObjectViewModel;
//import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;
//
///**
// * @another 江祖赟
// * @date 2018/1/2.
// * 游戏详情评论
// */
//public class CommentViewModel extends LoadMoreObjectViewModel {
//
//    //private GameDetailAct.getGameDataListener mGameDataListener;
//
//
//    {
//        pageState.set(0);
//    }
//
//
//    //拿到接口数据之后 刷新到 commentPageData
//    PageDataWrapper<Object> commentPageData = new PageDataWrapper<>();
//
//
//    public CommentViewModel() {
//    }
//
//
//    @Override public void toGetData(HashMap mapParam) {
//        collectDisposables(Observable.just(0)
//                                     .delay(2, TimeUnit.SECONDS)
//                                     .observeOn(AndroidSchedulers.mainThread())
//                                     .subscribe(new Consumer<Integer>() {
//                                         @Override public void accept(Integer integer) throws Exception {
//                                             if(isFirstPage()) {
//                                                 commentPageData.getList().clear();
//                                                 //我的评论item
//                                                 commentPageData.getList().add(new Comment.ScoreListBean());
//                                                 //其他评论数据
//                                                 List<Object> commentBeans = new ArrayList<>();
//                                                 for (int i = 0; i < 10; i++) {
//                                                     commentBeans.add(new Comment.CommentBean());
//                                                 }
//                                                 commentPageData.getList().addAll(commentBeans);
//                                                 //是否有下一页数据 存储
//                                                 commentPageData.setHasNext(true);
//                                                 afterGetDataSucceed(commentPageData);
//                                                 GameDetail gameDetail = new GameDetail();
//                                                 gameDetail.info = new GameInfo();
//                                                 gameDetail.detail = new GameDetail.DetailBean();
//                                                 //if(mGameDataListener != null) {
//                                                 //    mGameDataListener.afterGetData(gameDetail);
//                                                 //}
//                                             } else {
//                                                 //非第一页 数据主关注 其他评论
//                                                 //直接使用 接口的 pageDataWrapper数据
//                                                 PageDataWrapper<Object> no1CommentPageData = new PageDataWrapper<>();
//                                                 List<Object> commentBeans = new ArrayList<>();
//                                                 for (int i = getDataLists().size(); i < getDataLists().size() + 10; i++) {
//                                                     commentBeans.add(new Comment.CommentBean());
//                                                 }
//                                                 no1CommentPageData.getList().addAll(commentBeans);
//                                                 afterGetDataSucceed(no1CommentPageData);
//                                             }
//                                         }
//                                     }));
//    }
//
//
//    private void afterGetDataSucceed(PageDataWrapper<Object> commentPageData) {
//
//    }
//
//
//    public void refreshedData(List newData) {
//        refreshedAllData(newData, false);
//        hideLoading();
//    }
//
//
//    @Override protected void registItemTypes(OnItemBindClass<Object> multipleItems) {
//        multipleItems.regist(Comment.CommentBean.class, BR.commentModel, R.layout.item_gamedetail_comment)
//                     .regist(Comment.ScoreListBean.class, BR.commenHead, R.layout.item_gamedetail_comment_head);
//    }
//
//
//    //public void setOnGameDataGetListener(GameDetailAct.getGameDataListener gameDataListener) {
//    //    mGameDataListener = gameDataListener;
//    //}
//}
