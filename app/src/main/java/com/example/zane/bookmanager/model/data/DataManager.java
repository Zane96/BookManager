package com.example.zane.bookmanager.model.data;

import android.content.Context;

import com.example.zane.bookmanager.inject.qualifier.ContextType;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Recom;
import com.example.zane.bookmanager.model.data.remote.DoubanBookService;
import com.example.zane.bookmanager.model.data.remote.DoubanRecommendBookService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by Zane on 16/2/14.
 */
@Singleton
public class DataManager {

    private DoubanBookService doubanBookService;
    private DoubanRecommendBookService doubanRecommendBookService;
    private Context context;

    @Inject
    public DataManager(@ContextType("application")Context context, DoubanBookService doubanBookService, DoubanRecommendBookService doubanRecommendBookService){
        this.doubanBookService = doubanBookService;
        this.doubanRecommendBookService = doubanRecommendBookService;
        this.context = context;
    }


    //返回book的信息
    public Observable<Book> getBookInfo(String isbn){
        return doubanBookService.getBookInfo(isbn);
    }

    //返回推荐书籍信息
    public Observable<Book_Recom> getRecomBoonInfo(String author_tag, String fields){
        return doubanRecommendBookService.getRecomBookInfo(author_tag, fields);
    }
}
