package com.example.zane.bookmanager.presenters.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.view.RecommendedBookViewHolder;
import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zane on 16/2/23.
 */
public class RecommendedBooklistAdapter extends BaseListAdapterPresenter<Book>{

    private List<Book> myBooks;
    private OnItemClickListener listener;


    public interface OnItemClickListener{
        void onClick(int positon);
        void onLongClick(int positon);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public RecommendedBooklistAdapter(Context mContext) {
        super(mContext);
        myBooks = new ArrayList<>();
    }

    public void setMyBooks(List<Book> books){
        myBooks = books;
    }

    public void clear(){
        myBooks.clear();
    }


    @Override
    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
        return new RecommendedBookViewHolder(viewGroup, R.layout.mybookinfo_item_layout);
    }

    @Override
    public void onBindViewHolder(BaseListViewHolderImpl holder, final int position) {
        holder.setData(myBooks.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myBooks.size();
    }
}