package com.example.zane.bookmanager.presenters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.DaggerActivityComponent;
import com.example.zane.bookmanager.inject.module.ActivityModule;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.data.DataManager;
import com.example.zane.bookmanager.view.MainView;
import com.example.zane.easymvp.presenter.BaseActivityPresenter;
import com.kermit.exutils.utils.ExUtils;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivityPresenter<MainView> {

    public static final int requestCode = 1001;
    public static final String ISBN = "ISBN";
    public static final String BOOK_INFO = "BOOKINFO";
    private String isbn;

    @Inject
    DataManager datamanager;

    @Override
    public Class<MainView> getRootViewClass() {
        return MainView.class;
    }

    @Override
    public void inCreat(Bundle bundle) {
        v.initView();
        v.init(this);
        initInject();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, ZxingScannerActivity.class)
                , requestCode);
            }
        }, R.id.button_scnnar);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        isbn = data.getStringExtra(ISBN);

        // TODO: 16/2/14
        datamanager.getBookInfo(isbn)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Book>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("MainActivity2", String.valueOf(e));
                    }

                    @Override
                    public void onNext(Book book) {
                        ExUtils.ToastLong(String.valueOf(book));
                        Intent intent = new Intent(MainActivity.this, BookInfoActivity.class);
                        intent.putExtra(BOOK_INFO, book);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void inDestory() {
        v.removeView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
