package com.example.zane.bookmanager.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

/**
 * Created by Zane on 16/3/21.
 */
public class WantReadViewHolder extends BaseListViewHolderImpl<Book_Read>{

    private ImageView bookImg;
    private TextView bookName;
    private TextView author;

    public WantReadViewHolder(ViewGroup parent, int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        bookImg = $(R.id.imageview_wantread);
        bookName = $(R.id.textview_wantread_bookname);
        author = $(R.id.textview_wantread_author);
    }

    @Override
    public void setData(Book_Read book_want_read) {
        Glide.with(MyApplication.getApplicationContext2())
                .load(book_want_read.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bookImg);
        bookName.setText(book_want_read.getTitle());
        author.setText(book_want_read.getAuthor());
    }
}
