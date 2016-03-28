package com.example.zane.bookmanager.presenters.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zane.bookmanager.presenters.adapter.FragmentViewPageAdapter;
import com.example.zane.bookmanager.view.ReadPlaneView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneFragment extends BaseFragmentPresenter<ReadPlaneView>{

    private FragmentViewPageAdapter adapter;
    public static final String TAG = "ReadPlaneFragment";

    @Override
    public Class<ReadPlaneView> getRootViewClass() {
        return ReadPlaneView.class;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "creat");
        //安卓遇到的一个坑。不能返回getActivity().getSupportFragmentManager().
        //因为这里是fragment里面嵌套的fragment。fuck
        adapter = new FragmentViewPageAdapter(this.getChildFragmentManager());
        setupViewPage();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "viewcreat");
        v.init(adapter);
    }

    public void setupViewPage(){
        adapter.addReadPlaneFragments(ReadPlaneViewPageFragment.newInstance(true), "阅读计划");
        adapter.addReadPlaneFragments(ReadPlaneViewPageFragment.newInstance(false), "想读书单");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "destory");
    }
}
