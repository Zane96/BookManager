package com.example.zane.bookmanager.inject.module;

import android.text.TextUtils;

import com.example.zane.bookmanager.config.DoubanAPI;
import com.example.zane.bookmanager.model.data.remote.DoubanBookService;
import com.example.zane.bookmanager.utils.FileUtils2;
import com.squareup.okhttp.Cache;
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
                Response response = chain.proceed(request);

                String chainControl = request.cacheControl().toString();
                if(TextUtils.isEmpty(chainControl)){
                    chainControl = "public, max-age=60, max-stale=240000";
                }

                return response.newBuilder()
                               .addHeader("Chain-Control", chainControl)
                               .removeHeader("Pragma")
                               .build();
            }
        };
    }

    @Provides
    Cache providesCache(){
        File httpCacheFile = FileUtils2.getDiskCacheDir("response");
        return new Cache(httpCacheFile, 1024 * 10 * 1024);
    }

    @Provides
    DoubanBookService providesDoubanBookService(Interceptor interceptor, Cache cache){

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(interceptor);
        client.setCache(cache);

        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(DoubanAPI.bookApi)
                                    .client(client)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();

        return retrofit.create(DoubanBookService.class);

    }

}
