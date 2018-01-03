package com.n4399.miniworld.vp;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.WindowManager;

import com.blueprint.adapter.frgmt.BaseFrgmtFractory;
import com.blueprint.adapter.frgmt.TabAdapter;
import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseActivity;
import com.n4399.miniworld.vp.dynamic.DynamicFrgmt;
import com.n4399.miniworld.vp.live.LiveFrgmt;
import com.n4399.miniworld.vp.me.MeFragmt;
import com.n4399.miniworld.vp.raiders.RaidersFrgmt;
import com.n4399.miniworld.vp.workshop.WorkShopFrgmt;
import com.tbruyelle.rxpermissions2.RxPermissions;

import april.yun.ISlidingTabStrip;
import april.yun.JPagerSlidingTabStrip;
import april.yun.other.JTabStyleDelegate;
import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.blueprint.LibApp.findColor;
import static com.blueprint.LibApp.findDimens;

public class MainActivity extends JBaseActivity {
    @BindView(R.id.main_tab_buttom_strip) JPagerSlidingTabStrip mButtomTabStrip;
    @BindView(R.id.main_tab_pager) ViewPager mViewPager;
    @BindArray(R.array.titles_home_buttom) String[] mTitles;
    private int[] mNormal;
    private int[] mChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        ButterKnife.bind(this);
        mNormal = new int[]{R.drawable.icon_btn_wk_line, R.drawable.icon_btn_riders_line, R.drawable.icon_btn_live_line, R.drawable.icon_btn_me_line};
        mChecked = new int[]{R.drawable.icon_btn_wk_block, R.drawable.icon_btn_riders_block, R.drawable.icon_btn_live_block, R.drawable.icon_btn_me_block}; //for getPageIconResId (需要selector)
        initTagStrip();
        //activity里面用getSupportFragmentManager()  ,fragment中使用getChildFragmentManager()
        mViewPager.setAdapter(new ButtomPagerAdapter(getSupportFragmentManager(), mTitles, new HomeFrgmtProvider()));
        mButtomTabStrip.bindViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(mTitles.length);

        new RxPermissions(this).request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe();
    }

    private void initTagStrip(){
        //        2，拿TabStyleDelegate
        JTabStyleDelegate tabStyleDelegate = mButtomTabStrip.getTabStyleDelegate();
        //        3, 用TabStyleDelegate设置属性
        tabStyleDelegate.setTabIconGravity(Gravity.TOP).setShouldExpand(true)//用过的都知道干啥用的
                //                .setFrameColor(Color.parseColor("#45C01A"))//边框颜色 设置为透明则不画边框
                //也可以直接传字符串的颜色，第一个颜色表示checked状态的颜色第二个表示normal状态
                .setTextColor(findColor(R.color.black333), findColor(R.color.gray999))
                .setTabTextSize(findDimens(R.dimen.tab_textsize)).setDividerPadding(0)//tab之间分割线 的上下pading
                .setTabPadding(0).setUnderlineHeight(0)//底部横线的高度
                .setCornerRadio(0);//设置滚动指示器和边框的圆角半径
    }


    public class ButtomPagerAdapter extends TabAdapter implements ISlidingTabStrip.IconTabProvider {

        public ButtomPagerAdapter(FragmentManager fm, String[] tabtiles, BaseFrgmtFractory frmtFractory){
            super(fm, tabtiles, frmtFractory);
        }

        //返回的是一个数组 第一个normal状态的icon 第二个checked状态下的
        @Override
        public int[] getPageIconResIds(int position){
            return new int[]{mChecked[position], mNormal[position]};

        }

        @Override
        public int getPageIconResId(int position){
            return 0;
        }

    }

    private static class HomeFrgmtProvider extends BaseFrgmtFractory {
        private static final int WORKSHOP = 0;
        private static final int DYNAMIC = 4;
        private static final int RAIDERS = 1;
        private static final int LIVE = 2;
        private static final int ME = 3;

        public Fragment createFragment(int position){
            Fragment fragment = fmCache.get(position);
            if(fragment == null) {
                switch(position) {
                    case WORKSHOP:
                        fragment = new WorkShopFrgmt();
                        break;
                    case DYNAMIC:
                        fragment = new DynamicFrgmt();
                        break;
                    case RAIDERS:
                        fragment = new RaidersFrgmt();
                        break;
                    case LIVE:
                        fragment = new LiveFrgmt();
                        break;
                    case ME:
                        fragment = new MeFragmt();
                        break;
                }
                fmCache.put(position, fragment);
            }
            return fragment;
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        doubleExit();
    }

}
