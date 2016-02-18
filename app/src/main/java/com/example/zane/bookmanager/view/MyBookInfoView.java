package com.example.zane.bookmanager.view;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.adapter.MyBookInfoAdapter;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookInfoView extends BaseViewImpl {

    @Bind(R.id.recyclerview_mybookinfo_fragment)
    RecyclerView recyclerviewMybookinfoFragment;


    @Override
    public int getRootViewId() {
        return R.layout.fragment_mybookinfo_layout;
    }

    public void initRecycleView(LinearLayoutManager manager, MyBookInfoAdapter adapter){
        recyclerviewMybookinfoFragment.setLayoutManager(manager);
        recyclerviewMybookinfoFragment.setAdapter(adapter);
    }

}
