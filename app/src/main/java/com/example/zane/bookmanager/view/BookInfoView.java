package com.example.zane.bookmanager.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

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
    @Bind(R.id.toolbar_bookinfo_activity)
    Toolbar toolbarBookinfoActivity;
    @Bind(R.id.collapsing_toolbar_bookinfo)
    CollapsingToolbarLayout collapsingToolbarBookinfo;
    @Bind(R.id.appbar_bookinfo)
    AppBarLayout appbarBookinfo;
    @Bind(R.id.fab_add_bookinfo_fragment)
    FloatingActionButton fabAddBookinfoFragment;
    @Bind(R.id.fabmenu_down_bookinfo_fragment)
    FloatingActionMenu fabmenuDownBookinfoFragment;
    @Bind(R.id.nestedscrollview_bookinfo)
    NestedScrollView nestedscrollviewBookinfo;


    @Override
    public int getRootViewId() {
        return R.layout.activity_bookinfo_layout;
    }

    public void setupToolbar(String url) {
        Glide.with(MyApplication.getApplicationContext2())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .fitCenter()
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

    public void setupFabMenu() {
        fabmenuDownBookinfoFragment.setClosedOnTouchOutside(true);
    }

    public void setupNestScrollView() {
        nestedscrollviewBookinfo.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fabmenuDownBookinfoFragment.hideMenu(true);
                } else if (scrollY < oldScrollY) {
                    fabmenuDownBookinfoFragment.showMenu(true);
                }
            }
        });
    }

    public void closeMenu(){
        fabmenuDownBookinfoFragment.close(true);
    }
}
