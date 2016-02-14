package com.example.zane.bookmanager.inject.component;

import com.example.zane.bookmanager.inject.module.ActivityModule;
import com.example.zane.bookmanager.inject.scope.ActivityLife;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.ZxingScannerActivity;

import dagger.Component;

/**
 * Created by Zane on 16/2/14.
 */
@ActivityLife
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {

    // TODO: 16/2/14
    void inject(MainActivity activity);
    void inject(ZxingScannerActivity activity);
}

