package com.example.zane.bookmanager.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.adapter.RecommendedBooklistAdapter;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/23.
 */
public class RecommendedBookView extends BaseViewImpl {


    @Bind(R.id.recyclerview_recommendedbook_fragment)
    RecyclerView recyclerviewRecommendedbookFragment;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_recommendedbook_layout;
    }

    public void setUpRecycleView(LinearLayoutManager manager, RecommendedBooklistAdapter adapter){
        recyclerviewRecommendedbookFragment.setLayoutManager(manager);
        recyclerviewRecommendedbookFragment.setAdapter(adapter);
    }

}
