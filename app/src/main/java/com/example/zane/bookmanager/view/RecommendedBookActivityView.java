package com.example.zane.bookmanager.view;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.activity.RecommendedBookActivity;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/23.
 */
public class RecommendedBookActivityView extends BaseViewImpl {

    @Bind(R.id.toolbar_recommendedactivity)
    Toolbar toolbarRecommendedactivity;
    @Bind(R.id.tablayout_recommendedactivity)
    TabLayout tablayoutRecommendedactivity;
    @Bind(R.id.fab)
    FloatingActionButton fab;


    @Override
    public int getRootViewId() {
        return R.layout.activity_recommended_layout;
    }

    public void init(RecommendedBookActivity activity, ViewPager viewPager) {
        activity.setSupportActionBar(toolbarRecommendedactivity);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tablayoutRecommendedactivity.setupWithViewPager(viewPager);
    }
}
