package com.n4399.miniworld;

/**
 * @author 江祖赟.
 * @date 2017/6/9
 * @des [一句话描述]
 */
public interface Constants {

    String SEARCH_KEY = "searchkey";
    String CKEY_FRGMT = "cfragment";

    interface RaidersKey {
        //页面3个fm判断类型用
        String FM_SEARCHRESULT = "s_result";
        String FM_EXPERIENCE = "experience";
    }

    interface SharePrenceKey {
        String SEARCH_HISTORY = "search_history";
        String SEARCH_HISTORY_RAIDERS = "his_raiders";
    }

    interface Login4399 {
        String CLIENT_ID = "b83af400acbb47c9b6247720d1dffd43";
        final String REDIRECT_URL = "http://ptlogin.3304399.net/resource/images/ptlogin_mask.png";
    }

    //    service/user/search 用户搜索
    //    service/strategy/searchlist 攻略搜索
    //    service/map/search 地图搜索
    //    service/video/searchlist 直播搜搜
    interface SearchData {
        String SEARCH_RAIDERS = "jstrategy";
        String SEARCH_LIVE = "jvideo";
        String SEARCH_MAP = "jmap";
        String SEARCH_USER = "juser";
        String SEARCH_ROOM = "jroom";
    }
}
