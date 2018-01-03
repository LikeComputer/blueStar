package com.n4399.miniworld.vp.live;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.jakewharton.rxbinding2.view.RxView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabViewpagerFrgmt;
import com.n4399.miniworld.vp.jpublic.SearchActivity;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.n4399.miniworld.Constants.SearchData.SEARCH_LIVE;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [直播]
 */
public class LiveFrgmt extends JBaseTabViewpagerFrgmt {

    @BindArray(R.array.titles_sec_live) String[] titles;

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout fmContent){
        super.onCreateContent(inflater, fmContent);
        RelativeLayout parent = (RelativeLayout)mTitleBar.getTitleTextView().getParent();
        parent.removeAllViews();
        View inflate = inflater.inflate(R.layout.fm_radiers_search_bar, parent);
        ( (TextView)inflate.findViewById(R.id.common_search_tv) ).setText(R.string.frgmt_live_title);
        RxView.clicks(parent.findViewById(R.id.raiders_titlebar_search)).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception{
                SearchActivity.start(getActivity(),SEARCH_LIVE);
            }
        });
    }

    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        tabStyleDelegate.setShouldExpand(true);
    }

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){
        return new BaseFrgmtFractory() {
            private static final int LIVE = 0;
            private static final int RECOMMAND = 1;

            @Override
            public Fragment createFragment(int position){
                Fragment fragment = fmCache.get(position);
                if(fragment == null) {
                    switch(position) {
                        case LIVE:
                            fragment = LiveRecFrgmt.getInstance(LiveRecFrgmt.LIVE_ING);
                            break;
                        case RECOMMAND:
                            fragment = LiveRecFrgmt.getInstance(LiveRecFrgmt.LIVE_RECO);
                    }
                    fmCache.put(position, fragment);
                }
                return fragment;
            }

        };
    }

    @Override
    protected String[] setTabTitles(){
        return titles;
    }

    @Override
    protected String setTitle(){
        return null;
    }
}
