package com.example.zane.bookmanager.presenters.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.DaggerFragmentComponent;
import com.example.zane.bookmanager.inject.module.FragmentModule;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.activity.MyBookDetailInfoActivity;
import com.example.zane.bookmanager.presenters.adapter.MyBookInfoAdapter;
import com.example.zane.bookmanager.view.MyBookInfoView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.kermit.exutils.utils.ExUtils;

import org.litepal.crud.DataSupport;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Zane on 16/2/16.
 */
public class MyBookInfoFragment extends BaseFragmentPresenter<MyBookInfoView>{

    private LinearLayoutManager manager;
    private List<Book_DB> myBooks;
    private MyBookInfoAdapter adapter;
    public static final String BOOK_DB = "BOOK_DB";
    private OnAddButtonListener scannerButtonListener;
    private boolean isSortByDate;


    public interface OnAddButtonListener{
        void onAddButtonClick();
    }
    public void setAddButtonListener(OnAddButtonListener listener){
        scannerButtonListener = listener;
    }

    public static MyBookInfoFragment newInstance(){
        MyBookInfoFragment fragment = new MyBookInfoFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
//        fetchDataByDate();
//        adapter.notifyDataSetChanged();
    }

    @Override
    public Class<MyBookInfoView> getRootViewClass() {
        return MyBookInfoView.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initInject();
        fetchDataByDate();
        v.initRecycleView(manager, adapter);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExUtils.Toast("点击");
                scannerButtonListener.onAddButtonClick();
            }
        }, R.id.fab_mybookinfo_fragment);

        adapter.setOnSortBookListener(new MyBookInfoAdapter.OnSortBookListener() {
            @Override
            public void onSortByName() {
                fetchFataByName();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSortByDate() {
                fetchDataByDate();
                adapter.notifyDataSetChanged();
            }
        });
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
                                        DataSupport.deleteAll(Book_DB.class, "isbn13 = ?", myBooks.get(positon).getIsbn13());
                                        myBooks.remove(positon);
                                        adapter.clear();
                                        if (isSortByDate) {
                                            fetchDataByDate();
                                        } else {
                                            fetchFataByName();
                                        }
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
    public void initInject(){
        MainActivity activity = (MainActivity)getActivity();
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .activityComponent(activity.getActivityComponent())
                .build()
                .inject(this);
    }

    //现在只是在数据库中查询哈
    public void fetchDataByDate(){
        myBooks = DataSupport.order("id desc").find(Book_DB.class);
        adapter.setMyBooks(myBooks);
        isSortByDate = true;
    }
    public void fetchFataByName(){
        Map<String, Book_DB> maps = new HashMap<>();
        List<String> bookName = new ArrayList<>();
        List<Book_DB> books = new ArrayList<>();

        myBooks = DataSupport.findAll(Book_DB.class);

        for (Book_DB book : myBooks) {
            maps.put(book.getTitle(), book);
            bookName.add(book.getTitle());
        }

        Collections.sort(bookName, Collator.getInstance(Locale.CHINESE));

        for (int i = 0; i < myBooks.size(); i++){
            books.add(maps.get(bookName.get(i)));
        }
        myBooks = books;
        //myBooks = DataSupport.order("title asc").find(Book_DB.class);
        adapter.setMyBooks(myBooks);
        isSortByDate = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
