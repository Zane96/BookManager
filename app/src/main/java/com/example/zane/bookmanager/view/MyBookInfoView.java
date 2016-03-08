package com.example.zane.bookmanager.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.adapter.MyBookInfoAdapter;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookInfoView extends BaseViewImpl {

    @Bind(R.id.recyclerview_mybookinfo_fragment)
    RecyclerView recyclerviewMybookinfoFragment;
    @Bind(R.id.fabmenu_mybookinfo_fragment)
    FloatingActionMenu fabmenuMybookinfoFragment;
    @Bind(R.id.fab_scanner_mybookinfo_fragment)
    FloatingActionButton fabScannerMybookinfoFragment;
    @Bind(R.id.fab_add_mybookinfo_fragment)
    FloatingActionButton fabAddMybookinfoFragment;


    @Override
    public int getRootViewId() {
        return R.layout.fragment_mybookinfo_layout;
    }

    public void initRecycleView(LinearLayoutManager manager, MyBookInfoAdapter adapter) {
        recyclerviewMybookinfoFragment.setLayoutManager(manager);
        recyclerviewMybookinfoFragment.setAdapter(adapter);
        recyclerviewMybookinfoFragment.setItemAnimator(new DefaultItemAnimator());
    }

    public void showFab() {
        fabmenuMybookinfoFragment.showMenuButton(true);
    }

    public void hideFab() {
        fabmenuMybookinfoFragment.hideMenuButton(true);
    }

    public void setFabMenu(){
        fabmenuMybookinfoFragment.setClosedOnTouchOutside(true);
    }

    public void closeMenu(){
        fabmenuMybookinfoFragment.close(true);
    }


}
