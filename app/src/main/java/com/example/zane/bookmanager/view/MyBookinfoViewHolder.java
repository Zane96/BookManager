package com.example.zane.bookmanager.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookinfoViewHolder extends BaseListViewHolderImpl<Book_DB>{

    private ImageView bookImage;
    private TextView bookName;
    private TextView authorName;

    public MyBookinfoViewHolder(ViewGroup parent, int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        bookImage = $(R.id.item_img);
        bookName = $(R.id.item_bookname);
        authorName = $(R.id.item_authorname);
    }

    @Override
    public void setData(Book_DB book) {
        Glide.with(MyApplication.getApplicationContext2())
                .load(book.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bookImage);

        bookName.setText(book.getTitle());
        authorName.setText(book.getAuthor());
    }
}
