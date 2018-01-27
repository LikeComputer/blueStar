package com.blueprint.basic.frgmt;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blueprint.R;
import com.blueprint.widget.JToolbar;

/**
 * @another 江祖赟
 * @date 2018/1/17.
 */
public abstract class JBaseVMFrgmt<VM extends ViewModel> extends JBaseFragment {

    /**
     * ContentView中 外层 父容器
     */
    protected ViewGroup mContainerLayout;
    /**
     * 可能 位于toolbar下的布局
     */
    protected View mLayoutBelowToolbar;
    protected JToolbar mToolBar;
    protected VM mViewModel;

    /**
     * 子类设置的实际 内容View
     */
    protected View mContentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        mViewModel = ViewModelProviders.of(this).get(initViewModel());

        ViewDataBinding rootViewDataBinding = setContentLayout4Frgmt(inflater, container);
        //查找固定的控件
        findStatbleViews(rootViewDataBinding.getRoot());
        //子类设置的 要显示的布局内容
        ViewDataBinding contentViewDatabinding = onCreateContent(inflater, mContainerLayout);
        if(contentViewDatabinding != null) {
            mContentView = contentViewDatabinding.getRoot();
            if(mContentView.getParent() != mContainerLayout) {
                mContainerLayout.addView(mContentView);
            }
            layoutViews();
            contentViewDatabinding.setVariable(setContentVariableId(), mViewModel);
            setMoreVariableView(contentViewDatabinding);
        }else if(requestNoTitleBar() && mToolBar != null) {
            mToolBar.setVisibility(View.GONE);
        }
        int variableId = setRootVariableId();
        if(variableId != 0) {
            rootViewDataBinding.setVariable(variableId, mViewModel);
        }
        toSubscribeData(mViewModel);
        return rootViewDataBinding.getRoot();
    }
    //=================== for layout config =========


    /**
     * toolbar标题栏/containerLayout内容布局容器
     */
    protected void findStatbleViews(View rootView){
        mLayoutBelowToolbar = mContainerLayout = rootView.findViewById(R.id.jbase_container);
        mToolBar = rootView.findViewById(R.id.jbase_toolbar);
    }


    protected void layoutViews(){
        if(mToolBar != null) {
            if(requestNoTitleBar()) {
                mToolBar.setVisibility(View.GONE);
            }else {
                initToolBar();
                reConfigToolBar(mToolBar);
                if(setContentBelowToolBar()) {
                    layoutContentBelowToolbar();
                }
            }
        }
    }


    protected void layoutContentBelowToolbar(){
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)mLayoutBelowToolbar.getLayoutParams();
        layoutParams.topToBottom = R.id.jbase_toolbar;
        layoutParams.bottomToBottom = R.id.parent;
    }


    /**
     * 内容在toolbar的下面
     */
    public boolean setContentBelowToolBar(){
        return true;
    }


    /**
     * 默认 没有titlebar
     */
    protected boolean requestNoTitleBar(){
        return true;
    }


    //================== for  toolbar =================
    protected void initToolBar(){
    }


    public void setTitle(CharSequence title){
        if(mToolBar != null) {
            mToolBar.setTitle(title);
        }
    }


    /**
     * 复写 更新titlebar样式
     */
    protected void reConfigToolBar(JToolbar titleBar){
    }


    protected void setClicks(){
        //左边的点击事件
    }


    protected void doTitleBarLeftClick(){
    }

    //---------------- about binding ===============


    protected int setRootVariableId(){
        return 0;
    }


    /**
     * 内容ViewBinding的更多数据绑定或者 其余配置
     */
    protected void setMoreVariableView(ViewDataBinding viewDataBinding){
    }


    protected abstract Class<VM> initViewModel();


    /**
     * 需改最外层layout ，同时需要配置{@link #setRootVariableId()},{@link #layoutContentBelowToolbar()},{@link #findStatbleViews(View)}
     */
    protected ViewDataBinding setContentLayout4Frgmt(LayoutInflater inflater, ViewGroup container){
        return DataBindingUtil.inflate(inflater, resetFrgmtLayoutRes(), container, false);
    }


    protected abstract int resetFrgmtLayoutRes();

    /**
     * 将布局添加到 container中<br/>
     * DataBindingUtil.setContentView(activity,layout)<br/>
     * DataBindingUtil.inflate(inflate,layout,parrent,true)<br/>
     * ViewDataBinding binding = DataBindingUtil.bindTo(viewRoot, layoutId);<br/>
     * setViewModel
     */
    protected ViewDataBinding onCreateContent(LayoutInflater layoutInflater, ViewGroup containerLayout){
        int contentLayoutId = setContentLayoutId();
        if(contentLayoutId != 0) {
            return DataBindingUtil.inflate(layoutInflater, contentLayoutId, containerLayout, true);
        }else {
            return null;
        }
    }

    protected abstract int setContentLayoutId();

    protected abstract int setContentVariableId();


    /**
     * 只有 通过fragment标签从布局创建的fragment需要通过该方法 来获取数据之外<BR>
     * 其他情况下都通过{@link #firstUserVisibile()} 里面获取数据{@link #mViewModel}
     */
    protected void toSubscribeData(VM viewModel){
    }
}
