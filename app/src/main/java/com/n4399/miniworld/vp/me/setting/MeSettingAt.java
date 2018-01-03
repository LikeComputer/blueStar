package com.n4399.miniworld.vp.me.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.blueprint.LibApp;
import com.blueprint.basic.JBasePresenter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTitleActivity;

import april.yun.widget.PromptView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.blueprint.Consistent.ErrorCode.ERROR_EMPTY;

/**
 * @another [https://github.com/ZuYun]
 * @desc [描述]
 */
public class MeSettingAt extends JBaseTitleActivity implements MeSettingContract.View {

    @BindView(R.id.me_setting_version_update) PromptView meSettingVersionUpdate;
    @BindView(R.id.me_setting_cachesize) PromptView meSettingCachesize;
    private MeSettingPresenter mPresenter;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MeSettingAt.class);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected JBasePresenter initPresenter(){
        return mPresenter = new MeSettingPresenter(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.me_setting_title);
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout container){
        View rootview = inflater.inflate(R.layout.at_me_setting, container);
    }

    @Override
    public void showLoading(){
        mMultiStateLayout.showStateLoading();
    }

    @Override
    public void showError(int eCode){
        if(eCode == ERROR_EMPTY) {
            mMultiStateLayout.showStateEmpty();
        }else {
            mMultiStateLayout.showStateError();
        }
    }

    @Override
    public void showSucceed(Object data){
        mMultiStateLayout.showStateSucceed();
    }

    @Override
    public void showUpdateMsg(){

    }

    @Override
    public void showCache(String cache){
        ( (View)meSettingCachesize.getParent() ).setVisibility(View.VISIBLE);
        meSettingCachesize.setText(cache);
    }

    public void logOut(View view){

    }

    public void aboutus(View view){

    }

    public void feedback(View view){

    }

    public void checkversion(View view){

    }

    public void clearcache(View view){
        mPresenter.clearCache();
    }
}