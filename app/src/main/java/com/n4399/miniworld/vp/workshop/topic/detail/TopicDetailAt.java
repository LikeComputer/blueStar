package com.n4399.miniworld.vp.workshop.topic.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blueprint.Consistent;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.BitmapHelper;
import com.blueprint.helper.DpHelper;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.WStopic;
import com.n4399.miniworld.vp.basic.JAbsListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.Consistent.TransitionName.TRANS_IMG;
import static com.blueprint.Consistent.TransitionName.TRANS_TV;

/**
 * @another [https://github.com/ZuYun]
 * @desc [专题详情]
 */
public class TopicDetailAt extends JAbsListActivity<HotMapBean,HotMapBean> implements TopicDetailContract.View {

    @BindView(R.id.at_ws_topic_detail_iv_blurbg) ImageView atWsTopicDetailIvBlurbg;
    @BindView(R.id.at_ws_topic_detail_tv_title) TextView atWsTopicDetailTvTitle;
    @BindView(R.id.at_ws_topic_detail_iv_icon) ImageView atWsTopicDetailIvIcon;
    @BindView(R.id.at_ws_topic_detail_tv_desc) TextView atWsTopicDetailTvDesc;
    private TopicDetailPresenter mPresenter;
    private WStopic mWStopic;

    public static void start(Activity activity, WStopic wStopic, RecyclerHolder holder){
        Intent intent = new Intent(activity, TopicDetailAt.class);
        intent.putExtra(Consistent.BUND_TAG, wStopic);
        View pic = holder.getView(R.id.item_ws_topic_iv_icon);
        View title = holder.getView(R.id.item_ws_topic_tv_title);
        ViewCompat.setTransitionName(pic, TRANS_IMG);
        ViewCompat.setTransitionName(title, TRANS_TV);
        Pair.create(pic, TRANS_IMG);
        ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, Pair.create(pic, TRANS_IMG), Pair.create(title, TRANS_TV))
                .toBundle());
    }

    @Override
    public void finishAfterTransition(){
        super.finishAfterTransition();
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        mWStopic = getIntent().getParcelableExtra(Consistent.BUND_TAG);
        return mPresenter = new TopicDetailPresenter(this);
    }

    @Override
    protected boolean requestNoTitleBar(){
        return true;
    }

    @Override
    public int setCustomLayout(){
        //在父类的onCreate中使用--onCreateContent--setCustomLayout
        return R.layout.at_topic_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=21) {

            setEnterSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots){
                    super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                }

                @Override
                public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots){
                    super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
                    mSwipeRefreshLayout.setTag(true);//共享动画结束后 执行展示动画
                    if(mListData.size()>0) {
                        aniShowContent();
                    }
                }
            });
        }else {
            mCommonRecv.post(new Runnable() {
                @Override
                public void run(){
                    mSwipeRefreshLayout.setTag(true);//共享动画结束后 执行展示动画
                    if(mListData.size()>0) {
                        aniShowContent();
                    }
                }
            });
        }
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        refreshViews();
        mSwipeRefreshLayout.setTranslationY(300);
        mSwipeRefreshLayout.setAlpha(0);
        atWsTopicDetailIvIcon.setScaleX(0);
        atWsTopicDetailIvIcon.setScaleY(0);
        mSwipeRefreshLayout.setTag(false);

    }

    private void aniShowContent(){
        if(( (Boolean)mSwipeRefreshLayout.getTag() )) {
            mSwipeRefreshLayout.setTag(false);
            mSwipeRefreshLayout.animate().translationY(0).alpha(1).setDuration(600).start();
            atWsTopicDetailIvIcon.animate().scaleX(1).scaleY(1).setDuration(600).setStartDelay(300);
        }
    }

    private void refreshViews(){
        ViewCompat.setTransitionName(atWsTopicDetailIvBlurbg, Consistent.TransitionName.TRANS_IMG);
        ViewCompat.setTransitionName(atWsTopicDetailTvTitle, Consistent.TransitionName.TRANS_TV);
        atWsTopicDetailTvTitle.setText(mWStopic.getTitle());
        atWsTopicDetailTvDesc.setText(mWStopic.getDesc());

        Glide.with(this).load(mWStopic.getPic()).transform(new BitmapTransformation(this) {
            @Override
            public String getId(){
                return "circle";
            }

            @Override
            protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight){
                Bitmap bitmap = BitmapHelper.fastBlur(BitmapHelper.scaleTRangeBitmap(toTransform, 1/6f), 16, true);
                toTransform.recycle();
                return bitmap;
            }
        }).into(atWsTopicDetailIvIcon);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(HotMapBean.class, new SimpleRecvBinder(R.layout.item_ws_map));
    }

    @Override
    public void showSucceed(List<HotMapBean> data){
        super.showSucceed(data);
        aniShowContent();
    }

    @Override
    public boolean setEnableSwipeRefresh(){
        return true;
    }

    public void backfinish(View view){
        onBackPressed();
    }

}