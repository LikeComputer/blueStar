package com.n4399.miniworld.vp.raiders.experience;

import com.blueprint.basic.JBasePresenter;
import com.blueprint.basic.JBaseView;
import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/6/21.
 */
public class ExperienceContract {
    public interface View extends JBaseView {

        void showTextRaiders(List data);

        void showVideoRaiders(List data);

        void showMapSeed(List data);

        void onMoreLoad(List data);

    }

    public interface Presenter extends JBasePresenter {

        void loadTextRaiders();

        void loadVideoRaiders();

        void loadMapSeed();

        void up2LoadMore();
    }
}
