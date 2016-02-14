package com.example.zane.bookmanager.model.data.remote;

import com.example.zane.bookmanager.model.bean.Book;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Zane on 16/2/14.
 */
public interface DoubanBookService {

    @GET("{isbn}")
    Observable<Book> getBookInfo(@Path("isbn") String isbn);

}
