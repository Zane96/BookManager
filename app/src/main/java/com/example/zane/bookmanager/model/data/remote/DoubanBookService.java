package com.example.zane.bookmanager.model.data.remote;

import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Recom;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/2/14.
 */
public interface DoubanBookService {

    //根据书籍isbn号获取书籍信息
    @GET("{isbn}")
    Observable<Book> getBookInfo(@Path("isbn") String isbn);

}
