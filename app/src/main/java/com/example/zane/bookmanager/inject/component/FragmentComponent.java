package com.example.zane.bookmanager.inject.component;

import com.example.zane.bookmanager.inject.module.FragmentModule;
import com.example.zane.bookmanager.inject.scope.FragmentLife;
import com.example.zane.bookmanager.presenters.fragment.MainFragment;
import com.example.zane.bookmanager.presenters.fragment.MyBookInfoFragment;

import dagger.Component;

/**
 * Created by Zane on 16/2/16.
 */
@FragmentLife
@Component(modules = FragmentModule.class, dependencies = ActivityComponent.class)
public interface FragmentComponent {
    void inject(MainFragment fragment);
    void inject(MyBookInfoFragment fragment);
}
