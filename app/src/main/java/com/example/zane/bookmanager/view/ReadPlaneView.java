package com.example.zane.bookmanager.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.adapter.FragmentViewPageAdapter;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneView extends BaseViewImpl {

    @Bind(R.id.tablayout_readplane_fragment)
    TabLayout tablayoutReadplaneFragment;
    @Bind(R.id.viewpager_readplane_fragment)
    ViewPager viewpagerReadplaneFragment;

    @Override
    public int getRootViewId() {
        return R.layout.fragment_readplane_layout;
    }

    public void init(FragmentViewPageAdapter adapter){
        viewpagerReadplaneFragment.setAdapter(adapter);
        tablayoutReadplaneFragment.setupWithViewPager(viewpagerReadplaneFragment);
    }
}
