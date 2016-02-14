package com.example.zane.bookmanager.inject.component;

import android.app.Application;
import android.content.Context;

import com.example.zane.bookmanager.inject.module.ApplicationModule;
import com.example.zane.bookmanager.inject.module.DoubanBookModule;
import com.example.zane.bookmanager.inject.qualifier.ContextType;
import com.example.zane.bookmanager.model.data.DataManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Zane on 16/2/14.
 */
@Singleton
@Component(modules = {ApplicationModule.class, DoubanBookModule.class})
public interface ApplicationComponent {

    @ContextType("application")Context context();

    DataManager datamanger();
}
