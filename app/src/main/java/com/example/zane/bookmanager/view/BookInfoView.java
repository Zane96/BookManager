package com.example.zane.bookmanager.view;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.easymvp.view.BaseViewImpl;

import java.util.List;

import butterknife.Bind;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Zane on 16/2/14.
 */
public class BookInfoView extends BaseViewImpl {


    @Bind(R.id.imageview_bookinfo_activity)
    ImageView imageviewBookinfoActivity;
    @Bind(R.id.textview_bookname_bookinfoactivity)
    TextView textviewBooknameBookinfoactivity;
    @Bind(R.id.textview_authorname_bookinfoactivity)
    TextView textviewAuthornameBookinfoactivity;
    @Bind(R.id.textview_publishname_bookinfoactivity)
    TextView textviewPublishnameBookinfoactivity;
    @Bind(R.id.textview_price_bookinfoactivity)
    TextView textviewPriceBookinfoactivity;

    @Override
    public int getRootViewId() {
        return R.layout.activity_bookinfo_layout;
    }

    public void setBookImage(String url, Context context) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageviewBookinfoActivity);
    }

    public void setBookInfo(String bookName, List<String> authorName, String publishName, String price) {
        final StringBuilder builder = new StringBuilder();
        textviewBooknameBookinfoactivity.setText(bookName);
        Observable.from(authorName)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        builder.append(s).append(". ");
                    }
                });
        textviewAuthornameBookinfoactivity.setText(builder.toString());
        textviewPublishnameBookinfoactivity.setText(publishName);
        textviewPriceBookinfoactivity.setText(price);
    }
}
