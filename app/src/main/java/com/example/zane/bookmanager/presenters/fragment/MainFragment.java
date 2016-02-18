package com.example.zane.bookmanager.presenters.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.inject.component.DaggerFragmentComponent;
import com.example.zane.bookmanager.inject.module.FragmentModule;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.presenters.MainActivity;
import com.example.zane.bookmanager.view.MainFragmentView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;
import com.kermit.exutils.utils.ExUtils;

import org.litepal.crud.DataSupport;

/**
 * Created by Zane on 16/2/16.
 */
public class MainFragment extends BaseFragmentPresenter<MainFragmentView>{

    private MainActivity activity;
    public OnScannerButtonListener scannerButtonListener;

    public interface OnScannerButtonListener{
        void onScannerButtonClick();
    }
    public void setScannerButtonListener(OnScannerButtonListener listener){
        scannerButtonListener = listener;
    }

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    @Override
    public Class<MainFragmentView> getRootViewClass() {
        return MainFragmentView.class;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity)getActivity();
        initInject();

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scannerButtonListener.onScannerButtonClick();
            }
        }, R.id.button_scnnar);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book_DB.class);
                ExUtils.Toast(String.valueOf(new Book_DB().getId()));
            }
        }, R.id.button_add);
    }

    public void initInject(){
        DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .activityComponent(activity.getActivityComponent())
                .build()
                .inject(this);
    }
}
