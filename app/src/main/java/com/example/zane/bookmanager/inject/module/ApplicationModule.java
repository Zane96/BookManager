package com.example.zane.bookmanager.inject.module;

import android.content.Context;

import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.qualifier.ContextType;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zane on 16/2/14.
 */
@Module
public class ApplicationModule {

    private MyApplication mApplication;

    public ApplicationModule(MyApplication application){
        mApplication = application;
    }

    @Provides
    @Singleton
    MyApplication providesApplication(){
        return mApplication;
    }

    @Provides
    @Singleton
    @ContextType("application")
    Context providesContext(){
        return MyApplication.getApplicationContext2();
    }
}
