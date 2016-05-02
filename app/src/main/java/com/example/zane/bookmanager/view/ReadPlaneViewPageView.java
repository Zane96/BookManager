package com.example.zane.bookmanager.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.adapter.ReadPlaneAdapter;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneViewPageView extends BaseViewImpl {

    @Bind(R.id.recyclerview_readplane_fragment)
    RecyclerView recyclerviewReadplaneFragment;

    @Override
    public int getRootViewId() {
        return R.layout.viewpager_readplane_layout;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {

    }

    public void setupRecycleView(ReadPlaneAdapter adapter, RecyclerView.LayoutManager manager){
        recyclerviewReadplaneFragment.setAdapter(adapter);
        recyclerviewReadplaneFragment.setLayoutManager(manager);
    }
}
