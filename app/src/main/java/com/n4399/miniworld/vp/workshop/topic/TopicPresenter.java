package com.n4399.miniworld.vp.workshop.topic;

import com.blueprint.basic.common.GeneralListContract;
import com.blueprint.basic.common.GeneralListPresenter;
import com.n4399.miniworld.data.bean.WStopic;

import java.util.ArrayList;
import java.util.List;

public class TopicPresenter extends GeneralListPresenter {
    public TopicPresenter(GeneralListContract.View view){
        super(view);
    }

    @Override
    public void subscribe(Object from){
        List<WStopic> wStopics = new ArrayList<>();
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());
        wStopics.add(new WStopic());

        mListView.showSucceed(wStopics);
    }
}