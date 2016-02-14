package com.example.zane.bookmanager.model.data;

import android.content.Context;

import com.example.zane.bookmanager.inject.qualifier.ContextType;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.data.remote.DoubanBookService;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Zane on 16/2/14.
 */
@Singleton
public class DataManager {

    private DoubanBookService doubanBookService;
    private Context context;

    @Inject
    public DataManager(@ContextType("application")Context context, DoubanBookService doubanBookService){
        this.doubanBookService = doubanBookService;
        this.context = context;
    }


    //返回book的信息
    public Observable<Book> getBookInfo(String isbn){
        return doubanBookService.getBookInfo(isbn);
    }
}
