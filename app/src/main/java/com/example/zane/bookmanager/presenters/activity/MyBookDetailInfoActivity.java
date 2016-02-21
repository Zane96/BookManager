package com.example.zane.bookmanager.presenters.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.presenters.fragment.MyBookInfoFragment;
import com.example.zane.bookmanager.view.MyBookDetailInfoView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookDetailInfoActivity extends BaseActivityPresenter<MyBookDetailInfoView>{

    private Toolbar toolbar;

    @Override
    public Class<MyBookDetailInfoView> getRootViewClass() {
        return MyBookDetailInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {

        toolbar = v.get(R.id.toolbar_mybookdetailinfo_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Book_DB book = (Book_DB)getIntent().getSerializableExtra(MyBookInfoFragment.BOOK_DB);
        v.setTextviewBookIntroMybookdetail(book.getSummary());
        v.setTextviewAuthorIntroMybookdetail(book.getAuthor_intro());
        v.setupToolbar(book.getImage(), book.getTitle());
        v.setTextviewAuthornameMybookdetail(book.getAuthor());
        v.setTextviewBooknameMybookdetail(book.getTitle());
        v.setTextviewPagesMybookdetail(book.getPages());
        v.setTextviewPriceMybookdetail(book.getPrice());
        v.setTextviewPublishdateMybookdetail(book.getPubdate());
        v.setTextviewPublishnameMybookdetail(book.getPublisher());
    }

    @Override
    public void inDestory() {

    }
}
