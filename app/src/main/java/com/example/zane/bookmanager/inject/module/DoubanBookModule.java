package com.example.zane.bookmanager.inject.module;

import android.text.TextUtils;

import com.example.zane.bookmanager.config.DoubanAPI;
import com.example.zane.bookmanager.model.data.remote.DoubanBookService;
import com.example.zane.bookmanager.model.data.remote.DoubanRecommendBookService;
import com.example.zane.bookmanager.utils.FileUtils2;
import com.kermit.exutils.utils.ExUtils;
import com.kermit.exutils.utils.NetUtils;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by Zane on 16/2/14.
 */
@Module
public class DoubanBookModule {

    @Provides
    Interceptor providesIntercepter(){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //更改请求头
                if (!NetUtils.hasNetwork()){
                    //如果没有网络，那么就强制使用缓存数据
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                //获得返回头，如果有网络，就缓存一分钟,没有网络缓存四周
                Response originalResponse = chain.proceed(request);
                //更改响应头
                if (NetUtils.hasNetwork()){
                    String cacheControl = request.cacheControl().toString();
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                }else {
                    return originalResponse.newBuilder()
                                   .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                                   .removeHeader("Pragma")
                                   .build();
                }
            }
        };
    }

    @Provides
    Cache providesCache(){
        File httpCacheFile = FileUtils2.getDiskCacheDir("response");
        return new Cache(httpCacheFile, 1024 * 100 * 1024);
    }

    @Provides
    DoubanBookService providesDoubanBookService(Interceptor interceptor, Cache cache){

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(interceptor);
        client.interceptors().add(interceptor);
        client.setCache(cache);


        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(DoubanAPI.bookApi)
                                    .client(client)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();

        return retrofit.create(DoubanBookService.class);

    }
    @Provides
    DoubanRecommendBookService providesDoubanRecommendBookService(Interceptor interceptor, Cache cache){

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(interceptor);
        client.interceptors().add(interceptor);
        client.setCache(cache);

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(DoubanAPI.bookRecomApi)
                                    .client(client)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();

        return retrofit.create(DoubanRecommendBookService.class);

    }

}
