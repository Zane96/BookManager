package com.example.zane.bookmanager.presenters.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.view.ReadPlaneViewHolder;
import com.example.zane.bookmanager.view.WantReadViewHolder;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

import java.util.List;

/**
 * Created by Zane on 16/3/9.
 */
public class ReadPlaneAdapter extends BaseListAdapterPresenter<Book_Read>{

    private List<Book_Read> books;
    private List<Book_Read> wantReadBooks;
    private OnItemClickListener listener;
    private boolean isReadingBook;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void OnClick(int position, View view);
        void OnLongClick(int position, View view);
    }

    public ReadPlaneAdapter(Context mContext) {
        super(mContext);
    }

    public void setReadingBooks(List<Book_Read> books){
        this.books = books;
        isReadingBook = true;
    }

    public void setWantReadBooks(List<Book_Read> books){
        wantReadBooks = books;
        isReadingBook = false;
    }

    public void clear(){
        books.clear();
    }

    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        if (isReadingBook){
            return new ReadPlaneViewHolder(viewGroup, R.layout.readplane_item_layout);
        } else{
            return new WantReadViewHolder(viewGroup, R.layout.want_to_read_item_layout);
        }
    }


    @Override
    public void OnBindViewHloder(BaseListViewHolderImpl holder, int i) {

        final int position = i;

        if (isReadingBook){
            holder.setData(books.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnClick(position, v);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnLongClick(position, v);
                    return true;
                }
            });

        } else{
            holder.setData(wantReadBooks.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (isReadingBook){
            return books.size();
        } else {
            return wantReadBooks.size();
        }
    }

    @Override
    public int setHeadNum() {
        return 0;
    }

    @Override
    public int setFootNum() {
        return 0;
    }
}
