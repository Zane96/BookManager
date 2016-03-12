package com.example.zane.bookmanager.presenters.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.view.ReadPlaneViewHolder;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

import java.util.List;

/**
 * Created by Zane on 16/3/9.
 */
public class ReadPlaneAdapter extends BaseListAdapterPresenter<Book_Read>{

    private List<Book_Read> books;

    public ReadPlaneAdapter(Context mContext) {
        super(mContext);
    }

    public void setBooks(List<Book_Read> books){
        this.books = books;
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        return new ReadPlaneViewHolder(viewGroup, R.layout.readplane_item_layout);
    }

    @Override
    public void onBindViewHolder(BaseListViewHolderImpl holder, int position) {
        holder.setData(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
