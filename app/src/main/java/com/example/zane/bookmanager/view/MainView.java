package com.example.zane.bookmanager.view;


import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.activity.AboutActivity;
import com.example.zane.bookmanager.presenters.fragment.MyBookInfoFragment;
import com.example.zane.bookmanager.presenters.fragment.ReadPlaneFragment;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/14.
 */
public class MainView extends BaseViewImpl implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.swiperefreshlayout_main_activity)
    SwipeRefreshLayout swipeRefreshLayout;

    public AppCompatActivity context;
    private MyBookInfoFragment fragment;
    private ReadPlaneFragment readPlaneFragment;


    @Override
    public int getRootViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {

    }

    public void init(AppCompatActivity context, MyBookInfoFragment fragment) {

        this.context = context;
        this.fragment = fragment;
        readPlaneFragment = new ReadPlaneFragment();


        //初始fragment
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_replace, fragment);
        transaction.commit();

        toolbar.setTitle("美味书架");
        context.setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(context, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
    }

    public void transToMyBookInfoFragment() {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_replace, fragment);
        transaction.commit();
    }

    public void transToReadPlaneFragment() {
        FragmentTransaction transaction = context.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_replace, readPlaneFragment);
        transaction.commit();
    }

    public void interTouchEvent(MotionEvent event){
        drawer.onInterceptTouchEvent(event);
    }

//    public void transToAboutFragment(){
//        //这里我们不需要兼容低版本了
//        context.getFragmentManager().beginTransaction().replace(R.id.fragment_replace, aboutFragment).commit();
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_all_book) {
            transToMyBookInfoFragment();
        } else if (id == R.id.my_reading_book) {
            transToReadPlaneFragment();
        } else if (id == R.id.nav_about) {
            context.startActivity(new Intent(context, AboutActivity.class));
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
