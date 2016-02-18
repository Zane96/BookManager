package com.example.zane.bookmanager.presenters.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.presenters.activity.MyBookDetailInfoActivity;
import com.example.zane.bookmanager.presenters.adapter.MyBookInfoAdapter;
import com.example.zane.bookmanager.view.MyBookDetailInfoView;
import com.example.zane.bookmanager.view.MyBookInfoView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.kermit.exutils.utils.ExUtils;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Zane on 16/2/16.
 */
public class MyBookInfoFragment extends BaseFragmentPresenter<MyBookInfoView>{

    private LinearLayoutManager manager;
    private List<Book_DB> myBooks;
    private MyBookInfoAdapter adapter;
    public static final String BOOK_DB = "BOOK_DB";

    public static MyBookInfoFragment newInstance(){
        MyBookInfoFragment fragment = new MyBookInfoFragment();
        return fragment;
    }

    @Override
    public Class<MyBookInfoView> getRootViewClass() {
        return MyBookInfoView.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        fetchData();
        v.initRecycleView(manager, adapter);



        adapter.setOnItemClickListener(new MyBookInfoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), MyBookDetailInfoActivity.class);
                intent.putExtra(BOOK_DB, myBooks.get(position));
                getActivity().startActivity(intent);
            }

            @Override
            public void onLongClick(final int positon) {

                new MaterialDialog.Builder(getActivity())
                        .title(R.string.choose)
                        .items(new CharSequence[]{"删除"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog materialDialog, View view, int i
                                                           , CharSequence charSequence) {
                                switch (i) {
                                    case 0:
                                        DataSupport.delete(Book_DB.class, myBooks.get(positon).getId());
                                        myBooks.remove(positon);
                                        adapter.clear();
                                        fetchData();
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(getActivity(), "已删除", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        }).show();
            }
        });
    }

    public void init(){
        manager = new LinearLayoutManager(MyApplication.getApplicationContext2());
        adapter = new MyBookInfoAdapter(MyApplication.getApplicationContext2());
    }

    //现在只是在数据库中查询哈
    public void fetchData(){
        myBooks = DataSupport.order("id desc").find(Book_DB.class);
        adapter.setMyBooks(myBooks);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
