package com.example.zane.bookmanager.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookDetailInfoView extends BaseViewImpl {

    @Bind(R.id.image_mybookdetailinfo_activity)
    ImageView imageviewMybookdetailActivity;
    @Bind(R.id.textview_bookname_mybookdetail)
    TextView textviewBooknameMybookdetail;
    @Bind(R.id.textview_authorname_mybookdetail)
    TextView textviewAuthornameMybookdetail;
    @Bind(R.id.textview_publishname_mybookdetail)
    TextView textviewPublishnameMybookdetail;
    @Bind(R.id.textview_price_mybookdetail)
    TextView textviewPriceMybookdetail;
    @Bind(R.id.textview_publishdate_mybookdetail)
    TextView textviewPublishdateMybookdetail;
    @Bind(R.id.textview_pages_mybookdetail)
    TextView textviewPagesMybookdetail;
    @Bind(R.id.textview_author_intro_mybookdetail)
    TextView textviewAuthorIntroMybookdetail;
    @Bind(R.id.textview_book_intro_mybookdetail)
    TextView textviewBookIntroMybookdetail;
    @Bind(R.id.toolbar_mybookdetailinfo_activity)
    Toolbar toolbarMybookdetailinfoActivity;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.appbar_detail)
    AppBarLayout appbarDetail;
    @Bind(R.id.fab_mydetialbookinfo_activity)
    FloatingActionButton fabMydetialbookinfoActivity;


    @Override
    public int getRootViewId() {
        return R.layout.activity_mybookdetailinfo_layout;
    }


    public void setupToolbar(String url, String title) {
        collapsingToolbar.setTitle(title);
        Glide.with(MyApplication.getApplicationContext2())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
                .error(R.mipmap.ic_launcher)
                .into(imageviewMybookdetailActivity);
    }

    public void setTextviewBooknameMybookdetail(String bookName) {
        textviewBooknameMybookdetail.setText(bookName);
    }

    public void setTextviewAuthornameMybookdetail(String s) {
        textviewAuthornameMybookdetail.setText(s);
    }

    public void setTextviewPublishnameMybookdetail(String s) {
        textviewPublishnameMybookdetail.setText(s);
    }

    public void setTextviewPriceMybookdetail(String s) {
        textviewPriceMybookdetail.setText(s);
    }

    public void setTextviewPublishdateMybookdetail(String s) {
        textviewPublishdateMybookdetail.setText(s);
    }

    public void setTextviewPagesMybookdetail(String s) {
        textviewPagesMybookdetail.setText(s);
    }

    public void setTextviewAuthorIntroMybookdetail(String s) {
        textviewAuthorIntroMybookdetail.setText(s);
    }

    public void setTextviewBookIntroMybookdetail(String s) {
        textviewBookIntroMybookdetail.setText(s);
    }
}
