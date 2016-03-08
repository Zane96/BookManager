package com.example.zane.bookmanager.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.easymvp.view.BaseViewImpl;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.kermit.exutils.utils.ExUtils;

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
    @Bind(R.id.fabmenu_down_mybookinfo_fragment)
    FloatingActionMenu fabmenuDownMybookinfoFragment;
    @Bind(R.id.nestedscrollview_mydetailbook)
    NestedScrollView nestedscrollviewMydetailbook;

    private FloatingActionButton fab_checkRecommendedBook;


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

    public void setupMyBookInfoFabMenuFromMainActivity() {
        fabmenuDownMybookinfoFragment.setClosedOnTouchOutside(true);
        fab_checkRecommendedBook = new FloatingActionButton(MyApplication.getApplicationContext2());
        fab_checkRecommendedBook.setButtonSize(FloatingActionButton.SIZE_MINI);
        fab_checkRecommendedBook.setLabelText("查看相关书籍");
        fab_checkRecommendedBook.setImageResource(R.drawable.ic_menu_send);
        fabmenuDownMybookinfoFragment.addMenuButton(fab_checkRecommendedBook);
    }

    public void clodeMenu() {
        fabmenuDownMybookinfoFragment.close(true);
    }

    public void setupNestScrollView(){
        nestedscrollviewMydetailbook.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY){
                    fabmenuDownMybookinfoFragment.hideMenu(true);
                } else if(scrollY < oldScrollY){
                    fabmenuDownMybookinfoFragment.showMenu(true);
                }
            }
        });
    }

    //好像如果java代码添加控件的话，只能通过函数把控件暴露出去，不能用view的set方法！这点怎么解决？
    public FloatingActionButton getFab_checkRecommendedBook() {
        return fab_checkRecommendedBook;
    }
}
