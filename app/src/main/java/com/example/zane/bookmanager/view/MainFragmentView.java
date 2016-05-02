package com.example.zane.bookmanager.view;

import android.support.v7.app.AppCompatActivity;

import com.example.zane.bookmanager.R;
import com.example.zane.easymvp.view.BaseViewImpl;

/**
 * Created by Zane on 16/2/16.
 */
public class MainFragmentView extends BaseViewImpl{

    @Override
    public int getRootViewId() {
        return R.layout.fragment_main_layout;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {

    }

}
