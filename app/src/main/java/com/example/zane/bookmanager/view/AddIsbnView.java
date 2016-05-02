package com.example.zane.bookmanager.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zane.bookmanager.R;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/3/28.
 */
public class AddIsbnView extends BaseViewImpl {


    @Bind(R.id.edittext_addisbn)
    EditText edittextAddisbn;
    @Bind(R.id.button_addisbn)
    Button buttonAddisbn;
    @Bind(R.id.toolbar_addisbn_activity)
    Toolbar toolbarAddisbnActivity;

    private AppCompatActivity context;

    @Override
    public int getRootViewId() {
        return R.layout.activity_addisbn_layout;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {
        context = appCompatActivity;
    }

    public String getIsbn() {
        return edittextAddisbn.getText().toString();
    }

    public void setupToolbar(){
        toolbarAddisbnActivity.setTitle("");
        context.setSupportActionBar(toolbarAddisbnActivity);
        context.getSupportActionBar().setHomeButtonEnabled(true);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarAddisbnActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
    }
}
