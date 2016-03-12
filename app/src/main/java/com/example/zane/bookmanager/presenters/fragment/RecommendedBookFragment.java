package com.example.zane.bookmanager.presenters.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.DaggerFragmentComponent;
import com.example.zane.bookmanager.inject.module.FragmentModule;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Recom;
import com.example.zane.bookmanager.model.data.DataManager;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.activity.BookInfoActivity;
import com.example.zane.bookmanager.presenters.activity.MyBookDetailInfoActivity;
import com.example.zane.bookmanager.presenters.activity.RecommendedBookActivity;
import com.example.zane.bookmanager.presenters.adapter.RecommendedBooklistAdapter;
import com.example.zane.bookmanager.utils.JudgeNetError;
import com.example.zane.bookmanager.view.RecommendedBookView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.kermit.exutils.utils.ExUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by Zane on 16/2/23.
 */
public class RecommendedBookFragment extends BaseFragmentPresenter<RecommendedBookView>{

    public static final String AUTHOR_TAG = "AUTHOR_TAG";
    public static final String ISBYTAGS = "ISBYTAGS";
    public static final String ISBN = "ISBN";
    public static final String TAG = "RecommendedBookFragment";
    private RecommendedBooklistAdapter adapter;
    private List<Book> books_check;
    //为了做到代码复用，所以我需要判断现在是找作者其他书籍还是类似书籍
    private boolean isByTags;
    private String author_tag;
    private LinearLayoutManager linearLayoutManager;

    @Inject
    DataManager dataManager;


    public static RecommendedBookFragment newInstance(String author_tag, boolean isByTags){

        RecommendedBookFragment fragment = new RecommendedBookFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(AUTHOR_TAG, author_tag);
        bundle.putBoolean(ISBYTAGS, isByTags);
        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public Class<RecommendedBookView> getRootViewClass() {
        return RecommendedBookView.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Log.i(TAG, "onViewCreated");
        init();
        initInject();
        v.setUpRecycleView(linearLayoutManager, adapter);

        adapter.setOnItemClickListener(new RecommendedBooklistAdapter.OnItemClickListener() {
            @Override
            public void onClick(int positon) {
                Intent intent = new Intent(getActivity(), MyBookDetailInfoActivity.class);
                intent.putExtra(MainActivity.TAG, TAG);
                intent.putExtra(ISBN, books_check.get(positon));
                startActivity(intent);
            }

            @Override
            public void onLongClick(int positon) {

            }
        });

        //根据不同的查询条件（作者或者类型）来分别组装出不同的url
        //like: https://api.douban.com/v2/book/search?tag=java&fields=id,title,isbn13
        //https://api.douban.com/v2/book/search?q=周志明&fields=id,title,isbn13,author
        if (isByTags){
            String tags[] = author_tag.split("\\.");
            //Log.i(TAG, author_tag+"hehe"+tags.length);
            for (int i = 0; i < tags.length; i++){
                if (tags[i] != null){
                    //Log.i(TAG, tags[i]+"haha");
                    fetchData(tags[i]);
                }
            }
        } else {
            //Log.i(TAG, author_tag);
            String tags[] = author_tag.split("\\. ");
            for (int i = 0; i < tags.length; i++){
                //Log.i(TAG, tags[i] + "haha");
                if(tags[i] != null){
                    fetchData(tags[i]);
                }
            }
        }
    }

    public void fetchData(String param){
        dataManager.getRecomBoonInfo(param, "isbn13")
                .flatMap(new Func1<Book_Recom, Observable<Book_Recom.BooksEntity>>() {
                    @Override
                    public Observable<Book_Recom.BooksEntity> call(Book_Recom book_recom) {
                        return Observable.from(book_recom.getBooks());
                    }
                })
                .map(new Func1<Book_Recom.BooksEntity, String>() {
                    @Override
                    public String call(Book_Recom.BooksEntity booksEntity) {
                        return booksEntity.getIsbn13();
                    }
                })
                .flatMap(new Func1<String, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(String s) {
                        return dataManager.getBookInfo(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Book>() {
                    @Override
                    public void onCompleted() {
                        adapter.setMyBooks(books_check);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        JudgeNetError.judgeWhitchNetError(e);
                    }

                    @Override
                    public void onNext(Book book) {
                        books_check.add(book);
                    }
                });
//        dataManager.getRecomBoonInfo(param, "id,title,isbn13,author,images")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<Book_Recom>() {
//                    @Override
//                    public void onCompleted() {
//                        adapter.setMyBooks(books);
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ExUtils.Toast(String.valueOf(e));
//                    }
//
//                    @Override
//                    public void onNext(Book_Recom book) {
//                        books.addAll(book.getBooks());
//                    }
//                });
    }

    public void init(){
        books_check = new ArrayList<>();
        adapter = new RecommendedBooklistAdapter(MyApplication.getApplicationContext2());
        author_tag = getArguments().getString(AUTHOR_TAG);
        isByTags = getArguments().getBoolean(ISBYTAGS);
        linearLayoutManager = new LinearLayoutManager(MyApplication.getApplicationContext2());
    }

    public void initInject(){
        RecommendedBookActivity activity = (RecommendedBookActivity)getActivity();
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .activityComponent(activity.getActivityComponent())
                .build()
                .inject(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
