package com.n4399.miniworld.vp.workshop;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.jakewharton.rxbinding2.view.RxView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabViewpagerFrgmt;
import com.n4399.miniworld.vp.workshop.featured.FeaturedFrgmt;
import com.n4399.miniworld.vp.workshop.local.MapLocalAt;
import com.n4399.miniworld.vp.workshop.mapsearch.MapSearchAt;
import com.n4399.miniworld.vp.workshop.material.MaterialFrgmt;
import com.n4399.miniworld.vp.workshop.online.OnlineFrgmt;
import com.n4399.miniworld.vp.workshop.player.PlayerFrgmt;
import com.n4399.miniworld.vp.workshop.recommend.RecomFrgmt;
import com.n4399.miniworld.vp.workshop.topic.TopicFrgmt;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [工坊]
 */
public class WorkShopFrgmt extends JBaseTabViewpagerFrgmt {

    @BindArray(R.array.titles_sec_workshop) String[] mTabTitles;

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){
        return new WorkShopFrgmtProvider();
    }

    @Override
    protected String[] setTabTitles(){
        return mTabTitles;
    }

    @Override
    protected void onCreateContent(LayoutInflater inflater, RelativeLayout fmContent){
        super.onCreateContent(inflater, fmContent);
        RelativeLayout parent = (RelativeLayout)mTitleBar.getTitleTextView().getParent();
        parent.removeAllViews();
        inflater.inflate(R.layout.fm_workshop_search_bar, parent);
        RxView.clicks(parent.findViewById(R.id.raiders_titlebar_search)).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception{
                MapSearchAt.start(getActivity());
            }
        });
        RxView.clicks(parent.findViewById(R.id.frgmt_wshorp_home_local)).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception{
                MapLocalAt.start(getActivity());
            }
        });
    }

    public static class WorkShopFrgmtProvider extends BaseFrgmtFractory {
        private static final int RECOMMEND = 0;
        private static final int FEATURED = 1;
        private static final int TOPIC = 2;
        private static final int ONLINE = 3;
        private static final int PLAYER = 5;
        private static final int MATERIAL = 4;

        public Fragment createFragment(int position){
            Fragment fragment = fmCache.get(position);
            if(fragment == null) {
                switch(position) {
                    case RECOMMEND:
                        fragment = new RecomFrgmt();
                        break;
                    case FEATURED:
                        fragment = new FeaturedFrgmt();
                        break;
                    case TOPIC:
                        fragment = TopicFrgmt.getInstance();
                        break;
                    case ONLINE:
                        fragment = OnlineFrgmt.getInstance();
                        break;
                    case PLAYER:
                        fragment = new PlayerFrgmt();
                        break;
                    case MATERIAL:
                        fragment = MaterialFrgmt.getInstance();
                        break;
                }
                fmCache.put(position, fragment);
            }
            return fragment;
        }
    }

    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        super.reConfigTabStrip(tabStyleDelegate);
        tabStyleDelegate.setShouldExpand(true);
    }
}
