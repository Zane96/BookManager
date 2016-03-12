package com.example.zane.bookmanager.presenters.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.ActivityComponent;
import com.example.zane.bookmanager.inject.component.DaggerActivityComponent;
import com.example.zane.bookmanager.inject.module.ActivityModule;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.presenters.adapter.FragmentViewPageAdapter;
import com.example.zane.bookmanager.presenters.fragment.RecommendedBookFragment;
import com.example.zane.bookmanager.view.RecommendedBookActivityView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 16/2/23.
 */
public class RecommendedBookActivity extends BaseActivityPresenter<RecommendedBookActivityView>{

    private FragmentViewPageAdapter adapter;
    private Book_DB book_db;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private ActivityComponent activityComponent;
    public static final String TAG = "RecommendedBookActivity";

    @Override
    public Class<RecommendedBookActivityView> getRootViewClass() {
        return RecommendedBookActivityView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        initInject();
        viewPager = v.get(R.id.viewpager_recommendedactivity);
        book_db = (Book_DB)getIntent().getSerializableExtra(MyBookDetailInfoActivity.BOOKDB);
        setViewPager();
        v.init(this, viewPager);

        toolbar = v.get(R.id.toolbar_recommendedactivity);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void initInject(){
        MyApplication app = MyApplication.getApplicationContext2();
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(app.getAppComponent())
                .build();
        activityComponent.inject(this);

    }
    public ActivityComponent getActivityComponent(){
        return activityComponent;
    }

    public void setViewPager(){
        adapter = new FragmentViewPageAdapter(getSupportFragmentManager());
        Log.i(TAG, book_db.getAuthor());
        adapter.addFragments(RecommendedBookFragment.newInstance(book_db.getAuthor(), false), "作者其他书籍");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(book_db.getTag1())
                .append(".")
                .append(book_db.getTag2())
                .append(".")
                .append(book_db.getTag3())
                .append(".");
        adapter.addFragments(RecommendedBookFragment.newInstance(stringBuilder.toString(), true), "相关其他书籍");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void inDestory() {

    }
}
