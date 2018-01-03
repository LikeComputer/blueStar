package com.n4399.miniworld.vp.workshop.mapdetail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blueprint.Consistent;
import com.blueprint.LibApp;
import com.blueprint.adapter.RecyclerHolder;
import com.blueprint.adapter.SimpleRecvBinder;
import com.blueprint.adapter.decoration.JDividerItemDecoration;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.BitmapHelper;
import com.blueprint.helper.DpHelper;
import com.blueprint.widget.JDownloadProgView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.data.bean.MapDetailBean;
import com.n4399.miniworld.vp.basic.JAbsListActivity;
import com.n4399.miniworld.vp.jpublic.HopmapDownloadPresenter;

import java.util.List;

import april.yun.JPagerSlidingTabStrip;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.drakeet.multitype.MultiTypeAdapter;

import static com.blueprint.LibApp.findString;
import static com.blueprint.helper.UIhelper.initTabStrip;
import static com.n4399.miniworld.helper.Data2UIhelper.downloadProbutton;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MapDetailAt extends JAbsListActivity<HotMapBean,Object> implements MapDetailContract.View, CompoundButton.OnCheckedChangeListener, JDownloadProgView.OnDownloadProgressListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.frgmt_wshorp_mapdetail_download) JDownloadProgView frgmtWshorpMapdetailDownload;
    @BindArray(R.array.frgmt_workshop_mapdetail) String[] titles;
    private MapDetailPresenter mPresenter;
    private HotMapBean mHotMapBean;
    private ImageView mTopBgImage;
    private HopmapDownloadPresenter mDownloadPresenter;
    private JPagerSlidingTabStrip mTabStrip;

    public static void start(Activity activity, HotMapBean hotMapBean, RecyclerHolder holder){
        Intent intent = new Intent(activity, MapDetailAt.class);
        intent.putExtra(Consistent.BUND_TAG, hotMapBean);
        View imageView = holder.getView(R.id.im_recv_common_icon);
        View downloadprogress = holder.getView(R.id.item_wshop_recom_tv_action);
        ViewCompat.setTransitionName(imageView, Consistent.TransitionName.TRANS_IMG);
        ViewCompat.setTransitionName(downloadprogress, Consistent.TransitionName.TRANS_IMG2);
        ActivityCompat.startActivity(activity, intent, ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, Pair.create(imageView, ViewCompat.getTransitionName(imageView)),
                        Pair.create(downloadprogress, ViewCompat.getTransitionName(downloadprogress))).toBundle());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        //        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT>=21) {
            setEnterSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onSharedElementStart(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots){
                    super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots);
                }

                @Override
                public void onSharedElementEnd(List<String> sharedElementNames, List<View> sharedElements, List<View> sharedElementSnapshots){
                    super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots);
                    mCommonRecv.setTag(true);//可以执行显示动画了
                    if(mListData.size()>0) {
                        aniShowRecv();
                    }
                }
            });
        }else {
            mCommonRecv.post(new Runnable() {
                @Override
                public void run(){
                    mCommonRecv.setTag(true);//可以执行显示动画了
                    if(mListData.size()>0) {
                        aniShowRecv();
                    }
                }
            });
        }
        //        getWindow().setEnterTransition(new CircularReveal(mTopBgImage));
        downloadProbutton(frgmtWshorpMapdetailDownload, findString(R.string.item_wshop_recom_down_download),
                findString(R.string.item_wshop_recom_down_open));
        mDownloadPresenter = new HopmapDownloadPresenter(mHotMapBean);
        mTabStrip.bindTitles(titles);

        ViewCompat.setTransitionName(frgmtWshorpMapdetailDownload, Consistent.TransitionName.TRANS_IMG2);
        frgmtWshorpMapdetailDownload.setProgress(mHotMapBean.getDown()/100f);
        frgmtWshorpMapdetailDownload.setOnDownloadProgressListener(this);
        mCommonRecv.setTag(false);//需要执行过显示动画

        mCommonRecv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int mNewState;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);
                if(mNewState == 2) {
                    if(!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        mTabStrip.setCurrentPosition(0);
                    }else if(!ViewCompat.canScrollVertically(recyclerView, -1)) {
                        mTabStrip.setCurrentPosition(2);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState){
                mNewState = newState;
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisibleItemPosition = Math
                        .min(( (LinearLayoutManager)recyclerView.getLayoutManager() ).findFirstVisibleItemPosition(),
                                2);
                mTabStrip.setCurrentPosition(firstVisibleItemPosition);
            }
        });
    }

    private void aniShowRecv(){
        if(( (Boolean)mCommonRecv.getTag() )) {
            mCommonRecv.setTag(false);
            mCommonRecv.animate().alpha(1).translationY(0).setDuration(500).start();
        }
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        mHotMapBean = getIntent().getParcelableExtra(Consistent.BUND_TAG);
        return mPresenter = new MapDetailPresenter(this);
    }

    @Override
    protected String setTitle(){
        return null;
    }

    @Override
    public int setCustomLayout(){
        //在父类的onCreate中使用--onCreateContent--setCustomLayout
        return R.layout.at_workshop_map_detail;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        super.onCreateContent(inflater, container);
        //添加底部图片
        addBlurImageTopBg();
        //修改titlebar
        customTitleBar();
        //新增 新控件点击时间
        mCommonRecv.setTranslationY((int)DpHelper.dp2px(248));
        mCommonRecv.setAlpha(0);
    }

    private void customTitleBar(){
        mTitleBar.setBackgroundColor(Color.TRANSPARENT);
        mTitleBar.removeView(mTitleBar.getRightIcon());
        mTitleBar.removeView(mTitleBar.getTitleTextView());
        LayoutInflater.from(mTitleBar.getContext()).inflate(R.layout.frgmt_workshop_mapdetail_titlbar, mTitleBar);
        CheckBox collect = (CheckBox)mTitleBar.findViewById(R.id.frgmt_wshorp_mapdetail_selection);
        mTabStrip = (JPagerSlidingTabStrip)mTitleBar.findViewById(R.id.frgmt_wshorp_mapdetail_jsts);
        collect.setOnCheckedChangeListener(this);
        initTabStrip(mTabStrip.getTabStyleDelegate()).setUnderlineColor(Color.TRANSPARENT);
        mTabStrip.setOnPageChangeListener(this);
    }

    private void addBlurImageTopBg(){
        RelativeLayout parent = (RelativeLayout)mTitleBar.getParent();
        mTopBgImage = new ImageView(this);
        mTopBgImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mTopBgImage.setLayoutParams(new RelativeLayout.LayoutParams(-1, (int)DpHelper.dp2px(248)));

        //blue处理
        //        mTopBgImage.setLayerType(LAYER_TYPE_SOFTWARE, null);//关闭硬件加速会导致 共享动画失效
        //        topBgImage.getpa.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID));

        Glide.with(this).load(mHotMapBean.getPic()).transform(new BitmapTransformation(this) {
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
        }).into(mTopBgImage);
        ViewCompat.setTransitionName(mTopBgImage, Consistent.TransitionName.TRANS_IMG);
        //        PicHelper.loadImage(mHotMapBean.getPic(), mTopBgImage);
        parent.addView(mTopBgImage, 0);
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    public RecyclerView.ItemDecoration setItemDecoration(){
        return new JDividerItemDecoration((int)DpHelper.dp2px(1));
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        multiTypeAdapter.register(HotMapBean.class, new SimpleRecvBinder(R.layout.item_ws_map));
        multiTypeAdapter.register(MapDetailBean.class, new SimpleRecvBinder(R.layout.item_ws_map_detail));
    }

    @Override
    public void showSucceed(List<Object> data){
//        super.showSucceed(data);
        aniShowRecv();
    }

    @OnClick(R.id.frgmt_wshorp_mapdetail_download)
    public void onViewClicked(){
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){

    }

    @Override
    public void onStart(JDownloadProgView jProgView){
        mDownloadPresenter.onStart(jProgView);
    }

    @Override
    public void onPause(JDownloadProgView jProgView, float progress){
        mDownloadPresenter.onPause(jProgView, progress);
    }

    @Override
    public void onResume(JDownloadProgView jProgView, float progress){
        mDownloadPresenter.onResume(jProgView, progress);
    }

    @Override
    public void onAfterDone(JDownloadProgView jProgView){
        //打开 游戏
        Toast.makeText(LibApp.getContext(), "打开游戏", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

    }

    @Override
    public void onPageSelected(int position){
        ( (LinearLayoutManager)mCommonRecv.getLayoutManager() ).scrollToPositionWithOffset(position, 0);
    }

    @Override
    public void onPageScrollStateChanged(int state){

    }
}