package com.n4399.miniworld.vp.raiders.synthesis;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.n4399.miniworld.R;
import com.n4399.miniworld.vp.basic.JBaseFragment;
import com.n4399.miniworld.vp.raiders.synthesis.sort.SynthesDetailActivy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 江祖赟.
 * @date 2017/6/7
 * @des [图鉴/合成]
 */
public class SynthesisFrgmt extends JBaseFragment implements View.OnClickListener {
    @BindView(R.id.synthe_tjdq_show_all) RelativeLayout syntheTjdqShowAll;
    @BindView(R.id.sync_tjdq_lay1) LinearLayout syncTjdqLay1;
    @BindView(R.id.synthe_wpbk_show_all) RelativeLayout syntheWpbkShowAll;
    @BindView(R.id.sync_tjdq_lay3) LinearLayout syncTjdqLay3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View rootView = container.inflate(getContext(), R.layout.fm_radiers_synthe, null);
        ButterKnife.bind(this, rootView);
        setLinearlayoutItemClick(syncTjdqLay1);
        setLinearlayoutItemClick(syncTjdqLay3);
        return rootView;
    }

    private void setLinearlayoutItemClick(LinearLayout syncTjdqLay1){
        for(int i = 0; i<syncTjdqLay1.getChildCount(); i++) {
            View child = syncTjdqLay1.getChildAt(i);
            child.setTag(i);
            child.setOnClickListener(this);
        }
    }

    @OnClick(R.id.synthe_tjdq_show_all)
    public void showTjdaAll(){
        SynthesDetailActivy.startActivity(getActivity(), SynthesDetailActivy.图鉴大全, 0);
        Toast.makeText(getContext(), "图鉴大全", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.synthe_wpbk_show_all)
    public void showWpbkAll(){
        SynthesDetailActivy.startActivity(getActivity(), SynthesDetailActivy.物品百科, 0);
        Toast.makeText(getContext(), "物品百科", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v){
        int position = (int)v.getTag()+1;
        if(( (LinearLayout)v.getParent() ).getId() == R.id.sync_tjdq_lay1) {
            SynthesDetailActivy.startActivity(getActivity(), SynthesDetailActivy.图鉴大全, position);
        }else {
            SynthesDetailActivy.startActivity(getActivity(), SynthesDetailActivy.物品百科, position);
        }
    }

    public static Fragment getInstance(){
        return new SynthesisFrgmt();
    }
}
