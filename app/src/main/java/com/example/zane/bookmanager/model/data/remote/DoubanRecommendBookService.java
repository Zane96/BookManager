package com.example.zane.bookmanager.model.data.remote;

import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Recom;

import java.util.List;

import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Zane on 16/2/23.
 */
public interface DoubanRecommendBookService {

    //根据书籍作者名字获得相应的图书
    @Headers("Cache-Control: public, max-age=3600")
    @GET("search")
    Observable<Book_Recom> getRecomBookInfo(@Query("q") String q, @Query("fields") String fields);

}
