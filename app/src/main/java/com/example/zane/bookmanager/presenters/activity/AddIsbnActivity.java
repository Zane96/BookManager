package com.example.zane.bookmanager.presenters.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.view.AddIsbnView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 16/3/28.
 */
public class AddIsbnActivity extends BaseActivityPresenter<AddIsbnView>{

    private static final String TAG = "AddIsbnActivity";
    public static final int resultCode = 1002;

    @Override
    public Class<AddIsbnView> getRootViewClass() {
        return AddIsbnView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        v.setupToolbar();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                if (v.getIsbn().equals("")){
                    Toast.makeText(AddIsbnActivity.this, "输入不能为空哦~", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.ISBN, v.getIsbn());
                    setResult(resultCode, intent);
                    finish();
                }
            }
        }, R.id.button_addisbn);
    }

    @Override
    public void inDestory() {

    }

    @Override
    public AppCompatActivity getContext() {
        return this;
    }
}
