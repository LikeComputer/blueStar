package com.blueprint.basic.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import com.blueprint.R;
import com.blueprint.widget.JToolbar;
import jzy.easybindpagelist.statehelper.StateDiffViewModel;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [标题+状态界面  有统一处理 basePresenter的subscribe和unsubscribe]
 * 在 onAttachtoWindow会获取数据
 * onContentChanged在setContentView内部调用 然后基类才获取各种控件，所以在onContentChanged中可以获取intent中的数据，就不需要在super.onCreate之前获取数据
 */
public abstract class JBaseTitleActivity<D, VM extends StateDiffViewModel<D>, DBI extends ViewDataBinding> extends JBaseActivity {
    /**
     * ContentView中 最外层 父容器
     */
    protected ConstraintLayout mContainerLayout;
    protected JToolbar mToolBar;
    protected VM mViewModel;
    protected DBI mViewDataBinding;
    /**
     * 子类设置的实际 内容View
     */
    protected View mContentView;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = initViewModel();
        setContentView(setContentLayout4Act());
        mContainerLayout = findViewById(R.id.root_container);
        mToolBar = findViewById(R.id.jbase_toolbar);
        mViewDataBinding = onCreateContent(getLayoutInflater(), mContainerLayout);
        mContentView = mViewDataBinding.getRoot();
        if(mContentView.getParent() != mContainerLayout) {
            mContainerLayout.addView(mContentView);
        }
        if(requestNoTitleBar()) {
            mToolBar.setVisibility(View.GONE);
        } else {
            initToolBar();
            reConfigTitleBar(mToolBar);
            if(setContentBelowTitleBar()) {
                stateLayoutBelowTitleBar();
            }
        }
        toSubscribeData();
    }


    protected int setContentLayout4Act() {
        return R.layout.jbasic_title_layout;
    }


    @Override public void setContentView(int layoutResID) {
        if(mContainerLayout == null) {
            super.setContentView(layoutResID);
        } else {
            //DataBindingUtil.setContentView(activity, layout);
            mContentView = getLayoutInflater().inflate(layoutResID, mContainerLayout);
        }
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
    }


    protected abstract VM initViewModel();


    //onResume之后
    @Override public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }


    @Override public void onContentChanged() {
        super.onContentChanged();
        //该方法是在 setContentView()内部触发的 并不是onCreate结束之后才执行
    }


    /**
     * 默认 有titlebar
     */
    protected boolean requestNoTitleBar() {
        return false;
    }


    protected void initToolBar() {
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                doTitleBarLeftClick();
            }
        });
        setClicks();
    }


    /**
     * 复写 更新titlebar样式
     */
    protected void reConfigTitleBar(JToolbar titleBar) {

    }


    protected void setClicks() {
        //左边的点击事件

    }


    protected void doTitleBarLeftClick() {
        onBackPressed();
    }


    /**
     * 将布局添加到 container中<br/>
     * DataBindingUtil.setContentView(activity,layout)<br/>
     * DataBindingUtil.inflate(inflate,layout,parrent,true)<br/>
     * setViewModel
     */
    protected abstract DBI onCreateContent(LayoutInflater inflater, ConstraintLayout container);


    /**
     * 类似 firstVisibility() 第一次获取数据入口
     */
    protected void toSubscribeData() {
    }
}
