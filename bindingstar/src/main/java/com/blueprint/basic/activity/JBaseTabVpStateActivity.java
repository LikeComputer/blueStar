package com.blueprint.basic.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import april.yun.JPagerSlidingTabStrip;
import april.yun.other.JTabStyleDelegate;
import com.blueprint.R;
import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.blueprint.adapter.frgmt.TabAdapter;
import com.blueprint.helper.UIhelper;
import com.blueprint.widget.JToolbar;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [具体内容由viewpager的fragment展示 此fragment只做容器装tabstrip + viewpager]</p>
 * <h>在onCreate里面会对tabStrip和viewpager进行一系列设置，所以子类需要在super之前获取相关数据</h>
 */
public abstract class JBaseTabVpStateActivity extends JBaseActivity {
    /**
     * ContentView中 最外层 父容器
     */
    protected JToolbar mToolBar;
    public JPagerSlidingTabStrip mBaseTabStrip;
    public ViewPager mBaseViewpager;
    protected BaseFrgmtFractory mBaseFrgmtFractory;
    protected String[] mTabTitles;
    private View mContentView;


    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout4Act());
        mToolBar = findViewById(R.id.jbase_toolbar);
        mContentView = findViewById(R.id.jbase_tab_state);
        mBaseTabStrip = (JPagerSlidingTabStrip) findViewById(R.id.jbase_tab_strip);
        mBaseViewpager = (ViewPager) findViewById(R.id.jbase_viewpager);
        if(requestNoTitleBar()) {
            mToolBar.setVisibility(View.GONE);
        } else {
            initToolBar();
            reConfigToolBar(mToolBar);
            if(setContentBelowTitleBar()) {
                stateLayoutBelowTitleBar();
            }
        }
        initTabStrip();
        setupAdapter();
    }


    protected void reConfigToolBar(JToolbar toolBar) {}


    protected int setContentLayout4Act() {
        return R.layout.jbasic_state_tab_vp_layout;
    }


    /**
     * 内容在toolbar的下面
     */
    public boolean setContentBelowTitleBar() {
        return true;
    }


    protected void stateLayoutBelowTitleBar() {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) mContentView.getLayoutParams();
        layoutParams.topToBottom = R.id.jbase_toolbar;
        layoutParams.bottomToBottom = R.id.parent;
    }


    /**
     * 默认 没有titlebar
     */
    protected boolean requestNoTitleBar() {
        return true;
    }


    protected void initToolBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                doTitleBarLeftClick();
            }
        });
    }


    protected void doTitleBarLeftClick() {
        finish();
    }


    protected void setupAdapter() {
        mBaseViewpager.setAdapter(new TabAdapter(getSupportFragmentManager(), mTabTitles = setTabTitles(),
                mBaseFrgmtFractory = setFrgmtProvider()));
        //        mBaseViewpager.setAdapter(new TabFrgmtAdapter(getFragmentManager(), setTabTitles(), setFrgmtProvider()));
        mBaseViewpager.setOffscreenPageLimit(offScreenPageLimit());
        //不需要加载数据 直接显示内容
        if(mBaseViewpager.getAdapter() instanceof TabAdapter) {
            //在onsizechange之后设置导致indicator不显示
            mBaseTabStrip.bindViewPager(mBaseViewpager);
        } else {
            mBaseTabStrip.setVisibility(View.GONE);
        }
    }


    protected int offScreenPageLimit() {
        return mTabTitles.length;
    }


    protected void initTabStrip() {
        reConfigTabStrip(UIhelper.initTabStrip(mBaseTabStrip.getTabStyleDelegate()).setNeedTabTextColorScrollUpdate(true));
        //        //        2，拿TabStyleDelegate
        //        JTabStyleDelegate tabStyleDelegate = mBaseTabStrip.getTabStyleDelegate();
        //        //        3, 用TabStyleDelegate设置属性
        //        tabStyleDelegate.setShouldExpand(false)
        //                //也可以直接传字符串的颜色，第一个颜色表示checked状态的颜色第二个表示normal状态
        //                .setTextColor(findColor(R.color.colorPrimary), findColor(R.color.jforground_trans_gray))
        //                .setTabTextSize(findDimens(R.dimen.tab_top_textsize)).setTabPadding(findDimens(R.dimen.tab_pading))
        //                .setDividerPadding(0)//tab之间分割线 的上下pading
        //                .setTabPadding(0).setUnderlineHeight(0)//底部横线的高度
        //                .setIndicatorHeight(findDimens(R.dimen.tab_indicator_height))
        //                .setUnderlineHeight(findDimens(R.dimen.tab_underline_height))
        //                .setUnderlineColor(Color.parseColor("#e6e6e6")).setIndicatorColor(findColor(R.color.colorPrimary));
        //        reConfigTabStrip(tabStyleDelegate);
    }


    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate) {
    }


    protected abstract BaseFrgmtFractory setFrgmtProvider();

    protected abstract String[] setTabTitles();
}
