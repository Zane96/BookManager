package com.example.zane.bookmanager.app;

import android.app.Application;

import com.example.zane.bookmanager.inject.component.ApplicationComponent;
import com.example.zane.bookmanager.inject.component.DaggerApplicationComponent;
import com.example.zane.bookmanager.inject.module.ApplicationModule;
import com.example.zane.bookmanager.inject.module.DoubanBookModule;
import com.jude.utils.JUtils;

import org.litepal.LitePalApplication;

import im.fir.sdk.FIR;

/**
 * Created by Zane on 16/2/14.
 */
public class MyApplication extends Application{

    private static MyApplication application;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        JUtils.initialize(this);
        LitePalApplication.initialize(this);
        FIR.init(this);
    }

    public ApplicationComponent getAppComponent(){
        return DaggerApplicationComponent
                       .builder()
                       .applicationModule(new ApplicationModule(this))
                       .doubanBookModule(new DoubanBookModule())
                       .build();
    }

    public static MyApplication getApplicationContext2(){
        return application;
    }

}
