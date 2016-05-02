package com.example.zane.bookmanager.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
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
    @Bind(R.id.fab_checkRecommendedBook)
    FloatingActionButton fabCheckRecommendedBook;
    @Bind(R.id.fab_addto_read)
    FloatingActionButton fabAddtoRead;
    @Bind(R.id.fab_addwant_to_read)
    FloatingActionButton fabAddwantToRead;

    private FloatingActionButton fab_checkRecommendedBook;
    private FloatingActionButton fab_addto_read;
    private FloatingActionButton fab_addwant_to_read;
    private AppCompatActivity context;


    @Override
    public int getRootViewId() {
        return R.layout.activity_mybookdetailinfo_layout;
    }

    @Override
    public void setActivityContext(AppCompatActivity appCompatActivity) {
        context = appCompatActivity;
    }


    public void setupToolbar(String url, String title) {

        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(context.getResources().getColor(android.R.color.transparent));
        Glide.with(MyApplication.getApplicationContext2())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .animate(R.anim.image_in)
                .override(ExUtils.getScreenWidth(), ExUtils.dip2px(256))
                .error(R.mipmap.ic_launcher)
                .into(imageviewMybookdetailActivity);
        context.setSupportActionBar(toolbarMybookdetailinfoActivity);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarMybookdetailinfoActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });
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
//        fabmenuDownMybookinfoFragment.setClosedOnTouchOutside(true);
//        fab_checkRecommendedBook = new FloatingActionButton(MyApplication.getApplicationContext2());
//        fab_checkRecommendedBook.setButtonSize(FloatingActionButton.SIZE_MINI);
//        fab_checkRecommendedBook.setLabelText("查看相关书籍");
//        fab_checkRecommendedBook.setColorNormal(R.color.fab_normal);
//        fab_checkRecommendedBook.setColorPressed(R.color.fab_press);
//        fab_checkRecommendedBook.setColorRipple(R.color.fab_ripple);
//        fab_checkRecommendedBook.setImageResource(R.drawable.ic_menu_send);
//        fabmenuDownMybookinfoFragment.addMenuButton(fab_checkRecommendedBook);
//
//        fab_addto_read = new FloatingActionButton(MyApplication.getApplicationContext2());
//        fab_addto_read.setButtonSize(FloatingActionButton.SIZE_MINI);
//        fab_addto_read.setLabelText("添加到阅读计划");
//        fab_addto_read.setColorNormalResId(R.color.fab_press);
//
//        fab_addto_read.setImageResource(R.drawable.ic_menu_send);
//        fabmenuDownMybookinfoFragment.addMenuButton(fab_addto_read);
//
//        fab_addwant_to_read = new FloatingActionButton(MyApplication.getApplicationContext2());
//        fab_addwant_to_read.setButtonSize(FloatingActionButton.SIZE_MINI);
//        fab_addwant_to_read.setLabelText("添加到想读书单");
//        fab_addwant_to_read.setColorNormal(R.color.fab_normal);
//        fab_addwant_to_read.setColorPressed(R.color.fab_press);
//        fab_addwant_to_read.setColorRipple(R.color.fab_ripple);
//        fab_addwant_to_read.setImageResource(R.drawable.ic_menu_send);
//        fabmenuDownMybookinfoFragment.addMenuButton(fab_addwant_to_read);
    }

    public void clodeMenu() {
        fabmenuDownMybookinfoFragment.close(true);
    }

    public void setupNestScrollView() {
        nestedscrollviewMydetailbook.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    fabmenuDownMybookinfoFragment.hideMenu(true);
                } else if (scrollY < oldScrollY) {
                    fabmenuDownMybookinfoFragment.showMenu(true);
                }
            }
        });
    }

    //好像如果java代码添加控件的话，只能通过函数把控件暴露出去，不能用view的set方法！这点怎么解决？
    public FloatingActionButton getFab_checkRecommendedBook() {
        return fabCheckRecommendedBook;
    }

    public FloatingActionButton getFab_addto_read() {
        return fabAddtoRead;
    }

    public FloatingActionButton getFab_addwant_to_read() {
        return fabAddwantToRead;
    }
}
