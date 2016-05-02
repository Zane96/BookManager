package com.example.zane.bookmanager.presenters.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.inject.component.DaggerFragmentComponent;
import com.example.zane.bookmanager.inject.module.FragmentModule;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.data.DataManager;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.presenters.activity.AddIsbnActivity;
import com.example.zane.bookmanager.presenters.activity.MyBookDetailInfoActivity;
import com.example.zane.bookmanager.presenters.activity.ZxingScannerActivity;
import com.example.zane.bookmanager.presenters.adapter.MyBookInfoAdapter;
import com.example.zane.bookmanager.utils.ChangeWindowsAlpha;
import com.example.zane.bookmanager.view.MyBookInfoView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.kermit.exutils.utils.ExUtils;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Zane on 16/2/16.
 */
public class MyBookInfoFragment extends BaseFragmentPresenter<MyBookInfoView>{

    public static final String TAG = "MyBookInfoFragment";

    private LinearLayoutManager manager;
    private List<Book_DB> myBooks;
    private MyBookInfoAdapter adapter;
    public static final String BOOK_DB = "BOOK_DB";
    private boolean isSortByDate;
    private int checkByWhitch = 1;
    private String bookName = "";
    private Observable<Integer> observable;
    private Subscriber<Integer> subscriber;
    private RecyclerView recyclerView;


    @Inject
    DataManager dataManager;

    public static MyBookInfoFragment newInstance(){
        MyBookInfoFragment fragment = new MyBookInfoFragment();
        return fragment;
    }

    @Override
    public Class<MyBookInfoView> getRootViewClass() {
        return MyBookInfoView.class;
    }

    @Override
    public AppCompatActivity getContext() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchDataByDate();
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initInject();
        fetchDataByDate();
        adapter.notifyDataSetChanged();
        v.initRecycleView(manager, adapter);
        v.setFabMenu();

        //搜索功能的接口实现
        adapter.setOnCheckBookListener(new MyBookInfoAdapter.OnCheckBookListener() {
            @Override
            public void onCheckBook(final String book_name) {
                if (!TextUtils.isEmpty(book_name)) {
                    bookName = book_name;
                    switch (checkByWhitch) {
                        case 1:
                            checkBookByAll(book_name);
                            break;
                        case 2:
                            checkBookByName(book_name);
                            break;
                        case 3:
                            checkBookByAuthor(book_name);
                            break;
                        default:
                            checkBookByAll(book_name);
                    }
                } else {
                    //如果输入框为空，那么这个也要为空
                    bookName = "";
                    if (isSortByDate) {
                        fetchDataByDate();
                        adapter.notifyDataSetChanged();
                    } else {
                        fetchDataByName();
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCheckChoose(View v) {
                showPopupView(v);
            }
        });

        //监听滑动，然后判断fab的显示与否
        recyclerView = v.get(R.id.recyclerview_mybookinfo_fragment);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0){
                    v.hideFab();
                }else if (dy < 0){
                    v.showFab();
                }
            }
        });

        //调用zxing的activity，扫描然后返回结果
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                v.closeMenu();
                getActivity().startActivityForResult(new Intent(getActivity()
                                                                       , ZxingScannerActivity.class), MainActivity.requestCode_1);
            }
        }, R.id.fab_scanner_mybookinfo_fragment);
        //调用手动添加isbn号，然后返回数据
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                v.closeMenu();
                getActivity().startActivityForResult(new Intent(getActivity()
                                                                       , AddIsbnActivity.class), MainActivity.requestCode_1);
            }
        }, R.id.fab_add_mybookinfo_fragment );


        //判断哪种排序方式的接口实现
        adapter.setOnSortBookListener(new MyBookInfoAdapter.OnSortBookListener() {
            @Override
            public void onSortByName() {
                fetchDataByName();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onSortByDate() {
                fetchDataByDate();
                adapter.notifyDataSetChanged();
            }
        });

        //item的点击事件的监听
        adapter.setOnItemClickListener(new MyBookInfoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), MyBookDetailInfoActivity.class);
                intent.putExtra(MainActivity.TAG, MainActivity.TAG);
                intent.putExtra(BOOK_DB, myBooks.get(position));
                getActivity().startActivity(intent);
            }

            @Override
            public void onLongClick(final int positon, final View v) {
                new MaterialDialog.Builder(getActivity())
                        .title(R.string.choose)
                        .items(new CharSequence[]{"删除"})
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog materialDialog, View view, int i
                                                           , CharSequence charSequence) {
                                switch (i) {
                                    case 0:
                                        final List<Book_DB> books = DataSupport.where("isbn13 = ?", myBooks.get(positon).getIsbn13()).find(Book_DB.class);
                                        DataSupport.deleteAll(Book_DB.class, "isbn13 = ?", myBooks.get(positon).getIsbn13());
                                        adapter.clear();
                                        if (isSortByDate) {
                                            fetchDataByDate();
                                        } else {
                                            fetchDataByName();
                                        }
                                        adapter.notifyDataSetChanged();

                                        Snackbar.make(v, "少侠想要撤回删除吗？", Snackbar.LENGTH_LONG)
                                                .setAction("撤回！", new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        Book_DB book_db = new Book_DB();
                                                        Book_DB book = books.get(0);
                                                        Log.i(TAG, String.valueOf(book_db));
                                                        book_db.setAuthor(book.getAuthor());
                                                        book_db.setAuthor_intro(book.getAuthor_intro());
                                                        book_db.setImage(book.getImage());
                                                        book_db.setPages(book.getPages());
                                                        book_db.setPrice(book.getPrice());
                                                        book_db.setPubdate(book.getPubdate());
                                                        book_db.setSubtitle(book.getSubtitle());
                                                        book_db.setSummary(book.getSummary());
                                                        book_db.setTitle(book.getTitle());
                                                        book_db.setUrl(book.getUrl());
                                                        book_db.setPublisher(book.getPublisher());
                                                        book_db.setIsbn13(book.getIsbn13());
                                                        book_db.setAverage(book.getAverage());
                                                        book_db.setTag1(book.getTag1());
                                                        book_db.setTag2(book.getTag2());
                                                        book_db.setTag3(book.getTag3());
                                                        book_db.setId(book.getId());
                                                        if (book_db.save()) {
                                                            Snackbar.make(v, "成功撤回！", Snackbar.LENGTH_SHORT).show();
                                                        } else {
                                                            Snackbar.make(v, "撤回失败！", Snackbar.LENGTH_SHORT).show();
                                                        }
                                                        adapter.clear();
                                                        if (isSortByDate) {
                                                            fetchDataByDate();
                                                        } else {
                                                            fetchDataByName();
                                                        }
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                }).show();
                                        break;
                                }
                            }
                        }).show();
            }
        });
    }

    //显示查询条目的pop
    public void showPopupView(View v){

        ChangeWindowsAlpha.changeWindowsAlpha(getActivity(), 0.7f);

        TextView checkAll;
        TextView checkName;
        TextView checkAuthor;

        View popView = LayoutInflater.from(MyApplication.getApplicationContext2())
                .inflate(R.layout.fragment_popupwindows_mybookinfo, null);

        checkAll = (TextView) popView.findViewById(R.id.check_all);
        checkName = (TextView) popView.findViewById(R.id.check_name);
        checkAuthor = (TextView) popView.findViewById(R.id.check_author);

        final PopupWindow popupWindow = new PopupWindow(popView, 350, LinearLayout.LayoutParams.WRAP_CONTENT);

        //popupWindow.showAsDropDown(v);必须放在最后，我日
        popupWindow.setOutsideTouchable(true);
        popView.setFocusableInTouchMode(true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.showAsDropDown(v);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ChangeWindowsAlpha.changeWindowsAlpha(getActivity(), 1f);
            }
        });
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });

        subscriber = new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Integer integer) {
                if(bookName != "") {
                    switch (integer) {
                        case 1:
                            checkBookByAll(bookName);
                            break;
                        case 2:
                            checkBookByName(bookName);
                            break;
                        case 3:
                            checkBookByAuthor(bookName);
                            break;
                        default:
                            checkBookByAll(bookName);
                    }
                }
            }
        };

        //通过rx实现响应式的界面（一旦选择之后，立即查询）
        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkByWhitch = 1;
                observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(1);
                    }
                });
                observable.subscribe(subscriber);
                popupWindow.dismiss();
            }
        });
        checkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkByWhitch = 2;
                observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(2);
                    }
                });
                observable.subscribe(subscriber);
                popupWindow.dismiss();
            }
        });
        checkAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkByWhitch = 3;
                observable = Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        subscriber.onNext(3);
                    }
                });
                observable.subscribe(subscriber);
                popupWindow.dismiss();
            }
        });
    }

    public void checkBookByAll(final String book_name){
        List<Book_DB> books = dataManager.checkBookByAll(myBooks, book_name);
        if (books.size() != 0) {
            adapter.setMyBooks(books);
            adapter.notifyDataSetChanged();
        } else {
            adapter.setMyBooks(new ArrayList<Book_DB>());
            adapter.notifyDataSetChanged();
        }
    }

    public void checkBookByAuthor(final String book_name){
        ExUtils.Toast(myBooks.size()+" author");
        List<Book_DB> books = dataManager.checkBookByAuthor(myBooks, book_name);
        if (books.size() != 0) {
            //myBooks = books;
            adapter.setMyBooks(books);
            adapter.notifyDataSetChanged();
        } else {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void checkBookByName(final String book_name){
        ExUtils.Toast(myBooks.size()+" name");
        List<Book_DB> books = dataManager.checkBookByName(myBooks, book_name);
        if (books.size() != 0) {
            //myBooks = books;
            adapter.setMyBooks(books);
            adapter.notifyDataSetChanged();
        } else {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void init(){
        manager = new LinearLayoutManager(MyApplication.getApplicationContext2());
        adapter = new MyBookInfoAdapter(MyApplication.getApplicationContext2());
    }

    public void initInject(){
        MainActivity activity = (MainActivity)getActivity();
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .activityComponent(activity.getActivityComponent())
                .build()
                .inject(this);
    }

    //现在只是在数据库中查询哈
    //根据扫描时间降序排列
    public void fetchDataByDate(){
        myBooks = DataSupport.order("id desc").find(Book_DB.class);
        Log.i(TAG, "haha " + String.valueOf(myBooks));
        adapter.setMyBooks(myBooks);
        isSortByDate = true;
    }
    //根据书名开头第一个汉字拼音升序排列
    public void fetchDataByName(){
        myBooks = dataManager.fetchDataByName(myBooks);
        adapter.setMyBooks(myBooks);
        isSortByDate = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
