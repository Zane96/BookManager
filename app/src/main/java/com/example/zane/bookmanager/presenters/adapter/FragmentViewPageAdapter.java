package com.example.zane.bookmanager.presenters.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zane.bookmanager.presenters.fragment.ReadPlaneViewPageFragment;
import com.example.zane.bookmanager.presenters.fragment.RecommendedBookFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/2/23.
 * 所有fragment＋viewpager的适配器，代码复用
 */
public class FragmentViewPageAdapter extends FragmentPagerAdapter{

    private List<RecommendedBookFragment> fragments;
    private List<ReadPlaneViewPageFragment> readPlaneViewPageFragments;
    private List<String> titles;

    public FragmentViewPageAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        readPlaneViewPageFragments = new ArrayList<>();
        titles = new ArrayList<>();
    }

    public void addFragments(RecommendedBookFragment fragment, String title){
        fragments.add(fragment);
        titles.add(title);
    }

    public void addReadPlaneFragments(ReadPlaneViewPageFragment fragment, String title){
        readPlaneViewPageFragments.add(fragment);
        titles.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size() == 0){
            return readPlaneViewPageFragments.get(position);
        }

        return fragments.get(position);
    }

    @Override
    public int getCount() {

        if (fragments.size() == 0){
            return readPlaneViewPageFragments.size();
        }

        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
