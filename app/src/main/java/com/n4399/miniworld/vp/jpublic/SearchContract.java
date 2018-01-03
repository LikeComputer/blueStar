package com.n4399.miniworld.vp.jpublic;

import com.blueprint.basic.JBasePresenter;
import com.blueprint.basic.JBaseView;
import java.util.List;

/**
 * @another 江祖赟
 * @date 2017/6/22.
 */
public class SearchContract {
    public interface View<D> extends JBaseView<D> {
        void showHisSearchList(List<D> keys);

        void showHotSearchKey(List<String> keys);

        void hideHisSearchList();

        void delHisListSearchItem(int position);
    }

    interface Presenter extends JBasePresenter {

        void searchByKey(String key);

        void clearHistory();

        void deleteHisListItem(int position);

        void loadSearchHistory();

        void loadHotKeys();
    }
}
