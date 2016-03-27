//package com.example.zane.bookmanager.presenters.fragment;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.GridLayoutManager;
//import android.view.View;
//
//import com.example.zane.bookmanager.app.MyApplication;
//import com.example.zane.bookmanager.model.bean.Book_Want_Read;
//import com.example.zane.bookmanager.presenters.adapter.WantReadAdapter;
//import com.example.zane.bookmanager.view.WantReadViewpageView;
//import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
//
//import org.litepal.crud.DataSupport;
//
//import java.util.List;
//
///**
// * Created by Zane on 16/3/21.
// */
//public class WantReadViewPageFragment extends BaseFragmentPresenter<WantReadViewpageView>{
//
//    private static final String TAG = "WantReadViewPageFragment";
//    private GridLayoutManager manager;
//    private WantReadAdapter adapter;
//    private List<Book_Want_Read> books;
//
//    public static WantReadViewPageFragment newInstance(){
//        return new WantReadViewPageFragment();
//    }
//
//    @Override
//    public Class<WantReadViewpageView> getRootViewClass() {
//        return WantReadViewpageView.class;
//    }
//
//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        adapter = new WantReadAdapter(MyApplication.getApplicationContext2());
//        manager = new GridLayoutManager(MyApplication.getApplicationContext2(), 2);
//        fetchData();
//        v.setupRecycleView(adapter, manager);
//    }
//
//    public void fetchData(){
//        books = DataSupport.order("id desc").find(Book_Want_Read.class);
//        adapter.setBooks(books);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//}
