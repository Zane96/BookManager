package com.example.zane.bookmanager.utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by Zane on 16/2/21.
 */
public class ChangeWindowsAlpha {
    public static void changeWindowsAlpha(Activity activity, float alpha){

        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = alpha;
        activity.getWindow().setAttributes(params);
    }
}
