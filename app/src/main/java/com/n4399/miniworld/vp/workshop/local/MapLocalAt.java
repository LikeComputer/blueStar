package com.n4399.miniworld.vp.workshop.local;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.blueprint.LibApp;
import com.blueprint.basic.common.GeneralListPresenter;
import com.blueprint.helper.DpHelper;
import com.n4399.miniworld.R;
import com.n4399.miniworld.data.bean.HotMapBean;
import com.n4399.miniworld.vp.basic.JAbsListActivity;
import com.n4399.miniworld.vp.holderbinder.MapLocalBinder;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @another [https://github.com/ZuYun]
 * @desc [本地资源管理]
 */
public class MapLocalAt extends JAbsListActivity<HotMapBean,HotMapBean> {

    private MapLocalPresenter mPresenter;
    private TextView mRightEdit;
    private TextSwitcher mTopRightSwitcher;
    private MapLocalBinder mMapLocalBinder;

    public static void start(Activity activity){
        Intent intent = new Intent(activity, MapLocalAt.class);
        ActivityCompat.startActivity(activity, intent,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected GeneralListPresenter initListPresenter(){
        return mPresenter = new MapLocalPresenter(this);
    }

    @Override
    protected String setTitle(){
        return LibApp.findString(R.string.frgmt_ws_loacl_mapmanager);
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout fmContent){
        super.onCreateContent(inflater, fmContent);
        customTopRight();
    }

    private void customTopRight(){
        RelativeLayout titleContent = (RelativeLayout)mTitleBar.getTitleTextView().getParent();
        mTopRightSwitcher = new TextSwitcher(this);
        mTopRightSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView(){
                TextView rightEdit = new TextView(LibApp.getContext());
                rightEdit.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                rightEdit.setTextColor(LibApp.findColor(R.color.j_white));
                rightEdit.setGravity(Gravity.CENTER_VERTICAL|Gravity.LEFT);
                rightEdit.setPadding(0, 0, (int)DpHelper.dp2px(14), 0);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -1);
                rightEdit.setLayoutParams(layoutParams);
                return rightEdit;
            }
        });
        mTopRightSwitcher.setInAnimation(LibApp.getContext(), android.R.anim.slide_in_left);
        mTopRightSwitcher.setOutAnimation(LibApp.getContext(), android.R.anim.slide_out_right);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -1);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        titleContent.addView(mTopRightSwitcher, layoutParams);
        mTopRightSwitcher.setCurrentText(LibApp.findString(R.string.frgmt_ws_local_map_manage_edit));
        mTopRightSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(mMapLocalBinder.getMode() == HotMapBean.MODE_NORMAL) {
                    mMapLocalBinder.setMode(HotMapBean.MODE_SELECT);
                    mTopRightSwitcher.setText(LibApp.findString(R.string.frgmt_ws_local_map_manage_done));
                    mRecvAdapter.notifyDataSetChanged();
                }else {
                    System.out.println(mCommonRecv.getItemAnimator().getRemoveDuration());
                    mMapLocalBinder.setMode(HotMapBean.MODE_NORMAL);
                    mTopRightSwitcher.setText(LibApp.findString(R.string.frgmt_ws_local_map_manage_edit));
                    int N = mListData.size();
                    boolean haveMoved = false;
                    for(int i = 0; i<N; ) {
                        if(mListData.get(i).isSelected()) {
                            final int remove = i;
                            mRecvAdapter.removeItem(remove);
                            haveMoved = true;
                        }else {
                            i++;
                        }
                        N = mListData.size();
                    }
                    if(haveMoved) {
                        mCommonRecv.postDelayed(new Runnable() {
                            @Override
                            public void run(){
                                mRecvAdapter.notifyDataSetChanged();
                            }
                        }, 444);
                    }else {
                        mRecvAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager(){
        mCommonRecv.setPadding(0, 0, 0, 0);
        return new LinearLayoutManager(getApplicationContext());
    }

    @Override
    protected void register2Adapter(MultiTypeAdapter multiTypeAdapter){
        mMapLocalBinder = new MapLocalBinder();
        multiTypeAdapter.register(HotMapBean.class, mMapLocalBinder);
    }

}