//package com.example.zane.bookmanager.presenters.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.zane.bookmanager.R;
//import com.example.zane.bookmanager.model.bean.Book_Want_Read;
//import com.example.zane.bookmanager.view.WantReadViewHolder;
//import com.example.zane.easymvp.presenter.BaseListAdapterPresenter;
//import com.example.zane.easymvp.view.BaseListViewHolderImpl;
//
//import java.util.List;
//
///**
// * Created by Zane on 16/3/21.
// */
//public class WantReadAdapter extends BaseListAdapterPresenter<Book_Want_Read>{
//
//    private List<Book_Want_Read> books;
//    private OnItemClickListener listener;
//
//    public void setOnItemClickListener(OnItemClickListener listener){
//        this.listener = listener;
//    }
//
//    public interface OnItemClickListener{
//        void OnClick(int position);
//        void OnLongClick(int position);
//    }
//
//    public WantReadAdapter(Context mContext) {
//        super(mContext);
//    }
//
//    public void setBooks(List<Book_Want_Read> books){
//        this.books = books;
//    }
//
//    public void clear(){
//        books.clear();
//    }
//
//    @Override
//    public BaseListViewHolderImpl OnCreatViewHolder(ViewGroup viewGroup, int i) {
//        return new WantReadViewHolder(viewGroup, R.layout.viewpager_readplane_layout);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseListViewHolderImpl holder, final int position) {
//        if (books != null){
//            holder.setData(books.get(position));
//
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.OnClick(position);
//                }
//            });
//
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    listener.OnLongClick(position);
//                    return true;
//                }
//            });
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return books.size();
//    }
//}
