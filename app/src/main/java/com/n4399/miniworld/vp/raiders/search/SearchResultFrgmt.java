package com.n4399.miniworld.vp.raiders.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseTabViewpagerFrgmt;
import com.n4399.miniworld.vp.raiders.experience.MapSeekFrgmt;
import com.n4399.miniworld.vp.raiders.news.NewsVideoFrgmt;
import com.n4399.miniworld.vp.raiders.question.QuestionFrgmt;

import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;

import static com.blueprint.LibApp.findColor;
import static com.blueprint.LibApp.findDimens;
import static com.n4399.miniworld.Constants.CKEY_FRGMT;
import static com.n4399.miniworld.Constants.RaidersKey.FM_SEARCHRESULT;

/**
 * @another 江祖赟
 * @date 2017/6/23.
 * 三个frgmt 包括 攻略心得
 */
public class SearchResultFrgmt extends JBaseTabViewpagerFrgmt {

    private String mType;

    @BindArray(R.array.raider_search_result) public String[] mSearchResult;
    @BindArray(R.array.raider_experience) public String[] mExperience;

    public static SearchResultFrgmt getInstance(String type){
        SearchResultFrgmt searchResultFrgmt = new SearchResultFrgmt();
        Bundle bundle = new Bundle();
        bundle.putString(CKEY_FRGMT, type);
        searchResultFrgmt.setArguments(bundle);
        return searchResultFrgmt;
    }

    @Override
    protected boolean requestNoTitleBar(){
        return true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mType = getArguments().getString(CKEY_FRGMT);
    }

    @Override
    protected BaseFrgmtFractory setFrgmtProvider(){

        return new BaseFrgmtFractory() {
            private static final int NEWS = 0;
            private static final int VIDEO = 1;
            private static final int QUESTION = 2;

            @Override
            public Fragment createFragment(int position){
                Fragment fragment = fmCache.get(position);
                if(fragment == null) {
                    switch(position) {
                        case NEWS:
                            fragment = NewsVideoFrgmt.getInstance();
                            break;
                        case VIDEO:
                            fragment = NewsVideoFrgmt.getInstance();
                            break;
                        case QUESTION:
                            if(FM_SEARCHRESULT.equals(mType)) {
                                //游戏问答
                                fragment = QuestionFrgmt.getInstance();
                            }else {
                                //地图种子
                                fragment = MapSeekFrgmt.getInstance();
                            }
                            break;
                    }
                    fmCache.put(position, fragment);
                }
                if(FM_SEARCHRESULT.equals(mType)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(CKEY_FRGMT, FM_SEARCHRESULT);//设置fm类型为搜索页面
                    fragment.setArguments(bundle);
                }
                return fragment;
            }
        };
    }


    @Override
    protected String[] setTabTitles(){
        if(FM_SEARCHRESULT.equals(mType)) {
            return mSearchResult;
        }else {
            return mExperience;
        }
    }


    @Override
    protected void reConfigTabStrip(JTabStyleDelegate tabStyleDelegate){
        if(FM_SEARCHRESULT.equals(mType)) {
            tabStyleDelegate.setShouldExpand(true);
        }else {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mSecTabStrip.getLayoutParams();
            mSecTabStrip.setBackgroundColor(Color.TRANSPARENT);
            layoutParams.height = findDimens(R.dimen.tabheight29);
            layoutParams.leftMargin = findDimens(R.dimen.recv_item_card_magrin);
            layoutParams.topMargin = findDimens(R.dimen.recv_item_card_magrin);
            layoutParams.rightMargin = findDimens(R.dimen.recv_item_card_magrin);
            layoutParams.bottomMargin = findDimens(R.dimen.recv_item_card_magrin);
            tabStyleDelegate.setShouldExpand(true).setCornerRadio(findDimens(R.dimen.recv_item_card_magrin))
                    .setIndicatorHeight(findDimens(R.dimen.tabheight29)).setFrameColor(findColor(R.color.gray999))
                    .setTextColor(Color.WHITE, findColor(R.color.gray999)).setUnderlineColor(Color.TRANSPARENT);
        }
    }
}
