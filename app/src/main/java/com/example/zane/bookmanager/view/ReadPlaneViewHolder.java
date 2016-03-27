package com.example.zane.bookmanager.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.utils.TimeCaluUtils;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

/**
 * Created by Zane on 16/3/9.
 */
public class ReadPlaneViewHolder extends BaseListViewHolderImpl<Book_Read>{

    private ImageView bookImage;
    private TextView bookName;
    private TextView readPages;
    private TextView readDays;

    public ReadPlaneViewHolder(ViewGroup parent, int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        bookImage = $(R.id.imageview_readplane);
        bookName = $(R.id.textview_readplane_bookname);
        readPages = $(R.id.textview_readplane_readpages);
        readDays = $(R.id.textview_readplane_readdays);
    }

    @Override
    public void setData(Book_Read book_read) {
        Glide.with(MyApplication.getApplicationContext2())
                .load(book_read.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bookImage);
        bookName.setText(book_read.getTitle());
        readPages.setText(book_read.getReadPages());
        readDays.setText(String.valueOf(TimeCaluUtils.CaluDays(book_read.getFirstDay())));
    }
}
