package com.example.zane.bookmanager.presenters.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.zane.bookmanager.presenters.fragment.AboutFragment;
import com.example.zane.bookmanager.view.AboutActivityView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 16/3/29.
 */
public class AboutActivity extends BaseActivityPresenter<AboutActivityView>{


    @Override
    public Class<AboutActivityView> getRootViewClass() {
        return AboutActivityView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.setupToolbar(this);
        v.transToAboutFragment(new AboutFragment(), this);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public AppCompatActivity getContext() {
        return this;
    }
}
