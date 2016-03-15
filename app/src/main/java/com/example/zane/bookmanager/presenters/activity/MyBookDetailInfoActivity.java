package com.example.zane.bookmanager.presenters.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.DaggerActivityComponent;
import com.example.zane.bookmanager.inject.module.ActivityModule;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.model.data.DataManager;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.fragment.MyBookInfoFragment;
import com.example.zane.bookmanager.presenters.fragment.ReadPlaneDialogFragment;
import com.example.zane.bookmanager.presenters.fragment.RecommendedBookFragment;
import com.example.zane.bookmanager.view.MyBookDetailInfoView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.github.clans.fab.FloatingActionButton;
import com.kermit.exutils.utils.ExUtils;

import org.litepal.crud.DataSupport;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Zane on 16/2/18.
 */
public class MyBookDetailInfoActivity extends BaseActivityPresenter<MyBookDetailInfoView>{

    private Toolbar toolbar;
    public static final String BOOKDB = "BOOKDB";
    private String whereToComeFrom;

    @Inject
    DataManager dataManager;

    @Override
    public Class<MyBookDetailInfoView> getRootViewClass() {
        return MyBookDetailInfoView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        initInject();
        whereToComeFrom = getIntent().getStringExtra(MainActivity.TAG);
        toolbar = v.get(R.id.toolbar_mybookdetailinfo_activity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        v.setupNestScrollView();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //判断是从哪一个activity跳转而来，因为我需要代码复用。！
        if (whereToComeFrom.equals(MainActivity.TAG)) {
            v.setupMyBookInfoFabMenuFromMainActivity();
            final Book_DB book = (Book_DB) getIntent().getSerializableExtra(MyBookInfoFragment.BOOK_DB);
            v.setTextviewBookIntroMybookdetail(book.getSummary());
            v.setTextviewAuthorIntroMybookdetail(book.getAuthor_intro());
            v.setupToolbar(book.getImage(), book.getTitle());
            v.setTextviewAuthornameMybookdetail(book.getAuthor());
            v.setTextviewBooknameMybookdetail(book.getTitle());
            v.setTextviewPagesMybookdetail(book.getPages());
            v.setTextviewPriceMybookdetail(book.getPrice());
            v.setTextviewPublishdateMybookdetail(book.getPubdate());
            v.setTextviewPublishnameMybookdetail(book.getPublisher());

            //fab监听，跳转到推荐书籍页面去
            FloatingActionButton fab = v.getFab_checkRecommendedBook();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    v.clodeMenu();
                    Intent intent = new Intent(MyBookDetailInfoActivity.this, RecommendedBookActivity.class);
                    intent.putExtra(BOOKDB, book);
                    startActivity(intent);
                }
            });

            // TODO: 16/3/8 添加到想要读书的fab点击监听
            FloatingActionButton fab_read = v.getFab_addto_read();
            fab_read.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    v.clodeMenu();
                    //先判断是不是已经添加到阅读计划了
                    if (book.getReadSituation() != null){
                        Toast.makeText(MyBookDetailInfoActivity.this, "你已经添加了哦～", Toast.LENGTH_SHORT).show();
                    }else {

                        //弹出用户输入计划阅读天数的dialog
                        final ReadPlaneDialogFragment readPlaneDialogFragment = new ReadPlaneDialogFragment();
                        readPlaneDialogFragment.show(getFragmentManager(), "planeDaysDialog");
                        readPlaneDialogFragment.setOnPositiveClickListener(new ReadPlaneDialogFragment.OnPositiveClickListener() {
                            @Override
                            public void onClick(String days) {
                                if (days == null || days.equals("0") || days.equals("")){
                                    Toast.makeText(MyBookDetailInfoActivity.this, "你输入的天数为空哦～", Toast.LENGTH_SHORT).show();
                                }else {
                                    Book_Read book_read = new Book_Read();
                                    book_read.setAuthor(book.getAuthor());
                                    book_read.setTitle(book.getTitle());
                                    book_read.setImage(book.getImage());
                                    book_read.setPages(book.getPages());
                                    book_read.setPlaneDays(Integer.parseInt(days));
                                    book_read.setReadPages("0");
                                    book_read.setUsedDays(0);

                                    //更新这本书的阅读情况
                                    ContentValues values = new ContentValues();
                                    values.put("readSituation", getResources().getString(R.string.reading));
                                    DataSupport.update(Book_DB.class, values, book.getId());

                                    if (book_read.save()) {
                                        Toast.makeText(MyBookDetailInfoActivity.this, "添加成功！～", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MyBookDetailInfoActivity.this, "添加失败！～", Toast.LENGTH_SHORT).show();
                                    }
                                    readPlaneDialogFragment.dismiss();
                                }

                            }

                            @Override
                            public void onNaviClick() {
//                                Book_Read book_read = new Book_Read();
//                                book_read.setAuthor(book.getAuthor());
//                                book_read.setTitle(book.getTitle());
//                                book_read.setImage(book.getImage());
//                                book_read.setPages(book.getPages());
//                                book_read.setReadPages("0");
//                                book_read.setPlaneDays(0);
//                                book_read.setUsedDays(0);
//                                book_read.save();
//
//                                if (book_read.save()) {
//                                    Toast.makeText(MyBookDetailInfoActivity.this, "添加成功！～", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(MyBookDetailInfoActivity.this, "添加失败！～", Toast.LENGTH_SHORT).show();
//                                }
                                readPlaneDialogFragment.dismiss();
                            }
                        });
                    }
                }
            });

        } else if(whereToComeFrom.equals(RecommendedBookFragment.TAG)){
            Book book = (Book)getIntent().getSerializableExtra(RecommendedBookFragment.ISBN);

            v.setTextviewBookIntroMybookdetail(book.getSummary());
            v.setTextviewAuthorIntroMybookdetail(book.getAuthor_intro());
            v.setupToolbar(book.getImages().getLarge(), book.getTitle());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < book.getAuthor().size(); i++){
                sb.append(book.getAuthor().get(i)).append(". ");
            }
            v.setTextviewAuthornameMybookdetail(sb.toString());
            v.setTextviewBooknameMybookdetail(book.getTitle());
            v.setTextviewPagesMybookdetail(book.getPages());
            v.setTextviewPriceMybookdetail(book.getPrice());
            v.setTextviewPublishdateMybookdetail(book.getPubdate());
            v.setTextviewPublishnameMybookdetail(book.getPublisher());
        }
    }

    public void initInject(){
        MyApplication app = MyApplication.getApplicationContext2();
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(app.getAppComponent())
                .build()
                .inject(this);

    }

    @Override
    public void inDestory() {

    }
}
