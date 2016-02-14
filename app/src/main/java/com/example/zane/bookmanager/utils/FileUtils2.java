package com.example.zane.bookmanager.utils;

import android.content.Context;
import android.os.Environment;

import com.example.zane.bookmanager.app.MyApplication;

import java.io.File;

/**
 * Created by Zane on 16/2/14.
 */
public class FileUtils2 {
    static Context mApplicationContext = MyApplication.getApplicationContext2();

    public static File getDiskCacheDir(String uniqueName) {

        String cachePath;
        if(!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            cachePath = mApplicationContext.getCacheDir().getPath();
        } else {
            cachePath = mApplicationContext.getExternalCacheDir().getPath();
        }

        return new File(cachePath + File.separator + uniqueName);
    }
}
