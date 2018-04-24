package com.blueprint.basic.frgmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.R;
import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.blueprint.adapter.frgmt.TabAdapter;
import com.blueprint.helper.UIhelper;
import com.blueprint.widget.JToolbar;

import april.yun.JPagerSlidingTabStrip;
import april.yun.other.JTabStyleDelegate;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [具体内容由viewpager的fragment展示 此fragment只做容器装tabstrip + viewpager]
 */
public abstract class JBaseTabVpFrgmt extends JBaseFragment {

    protected JToolbar mToolBar;
    public JPagerSlidingTabStrip mTabStrip;
    public ViewPager mViewpager;
    protected int mCurrentItem = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = inflater.inflate(setContentLayout4Frgmt(), container, false);
        mTabStrip = rootView.findViewById(R.id.jbase_tab_strip);
        mViewpager = rootView.findViewById(R.id.jbase_viewpager);
        if(!requestNoToolBar()) {
            mToolBar = rootView.findViewById(R.id.jbase_toolbar);
            initToolBar();
            reConfigToolBar(mToolBar);
            if(setContentBelowTitleBar()) {
                stateLayoutBelowTitleBar();
            }
        }
        initTabStrip();
        setupAdapter();
        extrasConfiguration(rootView);
        mViewpager.setCurrentItem(mCurrentItem);
        return rootView;
    }

    public void setCurrentItem(int currentItem){
        mCurrentItem = currentItem;
        if(mViewpager != null && mViewpager.getCurrentItem() != mCurrentItem) {
            mViewpager.setCurrentItem(mCurrentItem);
        }
    }

    protected void reConfigToolBar(JToolbar toolBar){
    }


    /**
     * 默认 没有
     */
    protected boolean requestNoToolBar(){
        return true;
    }

    public boolean setContentBelowTitleBar(){
        return true;
    }


    protected void stateLayoutBelowTitleBar(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)mTabStrip.getLayoutParams();
        layoutParams.topToBottom = R.id.jbase_toolbar;
    }


    protected void initToolBar(){
        //标题内容

    }

    public void setTitle(CharSequence title){
        if(mToolBar != null) {
            mToolBar.setTitle(title);
        }
    }

    protected int setContentLayout4Frgmt(){
        if(requestNoToolBar()) {
            return R.layout.jbasic_toolbar_tab_vp_layout;
        }else {
            return R.layout.jbasic_tab_vp_layout;
        }
    }


    protected void extrasConfiguration(View rootView){
    }


    private void setupAdapter(){
        // http://blog.csdn.net/a1274624994/article/details/53575976
        //getFragmentManager()有问题 浪费好多时间：getChildFragmentManager  T_T
        mViewpager.setAdapter(new TabAdapter(getChildFragmentManager(), setTabTitles(), setFrgmtProvider()));
        mViewpager.setOffscreenPageLimit(setTabTitles().length);
        if(mViewpager.getAdapter() instanceof TabAdapter) {
            //在onsizechange之后设置导致indicator不显示
            mTabStrip.bindViewPager(mViewpager);
        }else {
            mTabStrip.setVisibility(View.GONE);
        }
    }


    protected void initTabStrip(){
        //        //        2，拿TabStyleDelegate
        //        JTabStyleDelegate tabStyleDelegate = mBaseTabStrip.getTabStyleDelegate();
        //        //        3, 用TabStyleDelegate设置属性
        //        tabStyleDelegate.setShouldExpand(false)
        //                //也可以直接传字符串的颜色，第一个颜色表示checked状态的颜色第二个表示normal状态
        //                .setTextColor(Color.parseColor("#45C01A"), Color.GRAY)
        //                .setTabTextSize(LibApp.findDimens(R.dimen.tab_top_textsize)).setTabPadding(LibApp.findDimens(R.dimen.tab_pading))
        //                .setDividerPadding(0)//tab之间分割线 的上下pading
        //                .setTabPadding(0).setUnderlineHeight(0)//底部横线的高度
        //                .setIndicatorHeight(8).setIndicatorColor(Color.parseColor("#45C01A"));
        reConfigTabStrip(UIhelper.initTabStrip(mTabStrip.getTabStyleDelegate()).setNeedTabTextColorScrollUpdate(true));
    }


    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
    }

    protected abstract BaseFrgmtFractory setFrgmtProvider();

    protected abstract String[] setTabTitles();

    //在可见的时候 初始化 viewPager 只在需要的时候 获取数据
    //    protected void setupAdapter(){
    //        if(!mIsFirstVisibile) {
    //            super.setupAdapter();
    //        }
    //    }
    //
    @Override
    public void firstUserVisibile(){
        //        setupAdapter();
    }
}
