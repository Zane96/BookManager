package com.example.zane.bookmanager.view;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.fragment.AboutFragment;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/3/29.
 */
public class AboutActivityView extends BaseViewImpl {
    @Bind(R.id.toolbar_about_activity)
    Toolbar toolbarAboutActivity;
    @Bind(R.id.fragment_replace_about)
    FrameLayout fragmentReplaceAbout;

    @Override
    public int getRootViewId() {
        return R.layout.activity_about_layout;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {

    }

    public void transToAboutFragment(AboutFragment fragment, AppCompatActivity activity){
        activity.getFragmentManager().beginTransaction().replace(R.id.fragment_replace_about, fragment).commit();
    }

    public void setupToolbar(final AppCompatActivity activity){
        toolbarAboutActivity.setTitle("关于");
        activity.setSupportActionBar(toolbarAboutActivity);
        activity.getSupportActionBar().setHomeButtonEnabled(true);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarAboutActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
}
