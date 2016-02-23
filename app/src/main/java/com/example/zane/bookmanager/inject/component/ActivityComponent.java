package com.example.zane.bookmanager.inject.component;

import android.app.Activity;

import com.example.zane.bookmanager.inject.module.ActivityModule;
import com.example.zane.bookmanager.inject.scope.ActivityLife;
import com.example.zane.bookmanager.model.data.DataManager;
import com.example.zane.bookmanager.presenters.activity.BookInfoActivity;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.activity.MyBookDetailInfoActivity;
import com.example.zane.bookmanager.presenters.activity.RecommendedBookActivity;
import com.example.zane.bookmanager.presenters.activity.ZxingScannerActivity;

import dagger.Component;

/**
 * Created by Zane on 16/2/14.
 */
@ActivityLife
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);
    void inject(ZxingScannerActivity activity);
    void inject(BookInfoActivity activity);
    void inject(RecommendedBookActivity activity);
    void inject(MyBookDetailInfoActivity activity);

    Activity provideActivity();

    DataManager datamanger();
}

