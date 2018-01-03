package com.n4399.miniworld.vp.raiders;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.jakewharton.rxbinding2.view.RxView;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabViewpagerFrgmt;
import com.n4399.miniworld.vp.jpublic.SearchActivity;
import com.n4399.miniworld.vp.raiders.news.NewsVideoFrgmt;
import com.n4399.miniworld.vp.raiders.question.QuestionFrgmt;
import com.n4399.miniworld.vp.raiders.recommend.RecomFrgmt;
import com.n4399.miniworld.vp.raiders.search.SearchResultFrgmt;
import com.n4399.miniworld.vp.raiders.synthesis.SynthesisFrgmt;
import com.n4399.miniworld.vp.raiders.turorial.TutorialFrgmt;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.n4399.miniworld.Constants.CKEY_FRGMT;
import static com.n4399.miniworld.Constants.RaidersKey.FM_EXPERIENCE;
import static com.n4399.miniworld.Constants.SearchData.SEARCH_RAIDERS;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [攻略]
 */
public class RaidersFrgmt extends JBaseTabViewpagerFrgmt {

    @BindArray(R.array.titles_sec_raiders) String[] mTabTitles;

    @Override
    public String setTitle(){
        return null;
    }

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){
        return new RaidersFrgmtProvider();
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
        inflater.inflate(R.layout.fm_radiers_search_bar, parent);
        RxView.clicks(parent.findViewById(R.id.raiders_titlebar_search)).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception{
                SearchActivity.start(getActivity(),SEARCH_RAIDERS);
            }
        });
    }

    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        super.reConfigTabStrip(tabStyleDelegate);

    }

    private static class RaidersFrgmtProvider extends BaseFrgmtFractory {
        private static final int RECOMMEND = 0;
        private static final int EXPERIENCE = 1;
        private static final int TUTORIAL = 2;
        private static final int NEWS = 3;
        private static final int SYNTHESIS = 4;
        private static final int QUESTION = 5;

        public Fragment createFragment(int position){
            Fragment fragment = fmCache.get(position);
            if(fragment == null) {
                switch(position) {
                    case RECOMMEND:
                        fragment = new RecomFrgmt();
                        break;
                    case EXPERIENCE:
//                        fragment = new ExperienceFrgmt();
                        fragment = new SearchResultFrgmt();
                        Bundle bundle = new Bundle();
                        bundle.putString(CKEY_FRGMT, FM_EXPERIENCE);
                        fragment.setArguments(bundle);
                        break;
                    case TUTORIAL:
                        fragment = TutorialFrgmt.getInstance();
                        break;
                    case NEWS:
                        fragment = NewsVideoFrgmt.getInstance();
                        break;
                    case SYNTHESIS:
                        fragment = SynthesisFrgmt.getInstance();
                        break;
                    case QUESTION:
                        fragment = QuestionFrgmt.getInstance();
                        break;
                }
                fmCache.put(position, fragment);
            }
            return fragment;
        }
    }

    @Override
    public void firstUserVisibile(){
        super.firstUserVisibile();
    }
}
