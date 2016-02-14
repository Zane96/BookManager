package com.example.zane.bookmanager.presenters;

import android.os.Bundle;

import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.view.BookInfoView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;

/**
 * Created by Zane on 16/2/14.
 */
public class BookInfoActivity extends BaseActivityPresenter<BookInfoView>{

    private Book book;

    @Override
    public Class<BookInfoView> getRootViewClass() {
        return BookInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        book = (Book)getIntent().getSerializableExtra(MainActivity.BOOK_INFO);
        v.test(book);
    }

    @Override
    public void inDestory() {

    }
}
