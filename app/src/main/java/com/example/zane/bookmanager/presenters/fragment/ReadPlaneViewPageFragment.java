package com.example.zane.bookmanager.presenters.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.presenters.adapter.ReadPlaneAdapter;
import com.example.zane.bookmanager.view.ReadPlaneViewPageView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneViewPageFragment extends BaseFragmentPresenter<ReadPlaneViewPageView>{

    private static final String ISFROMREADING = "ISFROMREADING";
    public static final String TAG = "ReadPlaneViewPageFragment";

    private ReadPlaneAdapter adapter;
    private boolean isFromReading;
    private List<Book_Read> readingBooks;
    private GridLayoutManager manager;

    public static ReadPlaneViewPageFragment newInstance(boolean tag){

        ReadPlaneViewPageFragment fragment = new ReadPlaneViewPageFragment();

        Bundle bundle = new Bundle();
        bundle.putBoolean(ISFROMREADING, tag);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public Class<ReadPlaneViewPageView> getRootViewClass() {
        return ReadPlaneViewPageView.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "creat");
        isFromReading = getArguments().getBoolean(ISFROMREADING);
        adapter = new ReadPlaneAdapter(getActivity());
        manager = new GridLayoutManager(getActivity(), 2);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "viewcreat");

        if (isFromReading){
            fetchReadingData();
            v.setupRecycleView(adapter, manager);
        }else {

        }
    }

    //查询正在读的书单的信息
    public void fetchReadingData(){
        readingBooks = DataSupport.order("id desc").find(Book_Read.class);
        adapter.setBooks(readingBooks);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
