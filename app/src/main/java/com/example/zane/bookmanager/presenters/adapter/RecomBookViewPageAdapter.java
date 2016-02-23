package com.example.zane.bookmanager.presenters.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zane.bookmanager.presenters.fragment.RecommendedBookFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/2/23.
 */
public class RecomBookViewPageAdapter extends FragmentPagerAdapter{

    private List<RecommendedBookFragment> fragments;
    private List<String> titles;

    public RecomBookViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void addFragments(RecommendedBookFragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
