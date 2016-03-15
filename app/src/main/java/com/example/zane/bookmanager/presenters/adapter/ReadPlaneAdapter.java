package com.example.zane.bookmanager.presenters.adapter;

import android.content.Context;
import android.view.View;
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
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void OnClick(int position);
        void OnLongClick(int position);
    }

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
    public void onBindViewHolder(BaseListViewHolderImpl holder, final int position) {
        holder.setData(books.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(position);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.OnLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
