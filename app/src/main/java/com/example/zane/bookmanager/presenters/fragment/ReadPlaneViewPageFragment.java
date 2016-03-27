package com.example.zane.bookmanager.presenters.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.app.MyApplication;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Read;
import com.example.zane.bookmanager.presenters.adapter.ReadPlaneAdapter;
import com.example.zane.bookmanager.view.ReadPlaneViewPageView;
import com.example.zane.easymvp.presenter.BaseFragmentPresenter;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Zane on 16/3/10.
 */
public class ReadPlaneViewPageFragment extends BaseFragmentPresenter<ReadPlaneViewPageView>{

    private static final String ISFROMREADING = "ISFROMREADING";
    public static final String TAG = "ReadPlaneViewPageFragment";

    private ReadPlaneAdapter readingAdapter;
    private ReadPlaneAdapter wanReadAdapter;
    private boolean isFromReading;
    private List<Book_Read> readingBooks;
    private List<Book_Read> wantReadBooks;
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
        isFromReading = getArguments().getBoolean(ISFROMREADING);
        manager = new GridLayoutManager(getActivity(), 2);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i(TAG, "viewcreated");

        if (isFromReading){
            readingAdapter = new ReadPlaneAdapter(MyApplication.getApplicationContext2());
            fetchReadingData();
            v.setupRecycleView(readingAdapter, manager);

            readingAdapter.setOnItemClickListener(new ReadPlaneAdapter.OnItemClickListener() {
                @Override
                public void OnClick(final int position, final View v) {
                    // TODO: 16/3/22
                    new MaterialDialog.Builder(getActivity())
                            .title(R.string.choose)
                            .items(new CharSequence[]{"打卡", "修改计划", "放弃阅读", "已经读完"})
                            .itemsCallback(new MaterialDialog.ListCallback() {
                                @Override
                                public void onSelection(MaterialDialog materialDialog, View view, int i
                                                               , CharSequence charSequence) {
                                    switch (i) {
                                        case 0:
                                            final TimeClockDialogFragment fragment = TimeClockDialogFragment.newInstance(readingBooks.get(position));
                                            fragment.show(getActivity().getFragmentManager(), "timeClockDislogFragment");
                                            fragment.setOnPositiveClickListener(new TimeClockDialogFragment.OnPositiveClickListener() {
                                                @Override
                                                public void onClick(String pages) {
                                                    fragment.dismiss();
                                                    ContentValues values = new ContentValues();
                                                    values.put("readPages", pages);
                                                    DataSupport.update(Book_Read.class, values, readingBooks.get(position).getId());
                                                    //更新数据
                                                    fetchReadingData();
                                                    readingAdapter.notifyDataSetChanged();
                                                }

                                                @Override
                                                public void onNaviClick() {
                                                    fragment.dismiss();
                                                }
                                            });
                                            break;

                                        case 1:
                                            final ChangePlaneDialogFragment Changefragment = ChangePlaneDialogFragment.newInstance(readingBooks.get(position));
                                            Changefragment.show(getActivity().getFragmentManager(), "changPlaneFragment");
                                            Changefragment.setOnPositiveClickListener(new ChangePlaneDialogFragment.OnPositiveClickListener() {
                                                @Override
                                                public void onClick(String days) {
                                                    //更新阅读计划
                                                    Changefragment.dismiss();
                                                    ContentValues values = new ContentValues();
                                                    values.put("planeDays", days);
                                                    DataSupport.update(Book_Read.class, values, readingBooks.get(position).getId());
                                                    Toast.makeText(getActivity(), "修改成功！", Toast.LENGTH_SHORT).show();
                                                    //更新数据
                                                    fetchReadingData();
                                                    readingAdapter.notifyDataSetChanged();
                                                }

                                                @Override
                                                public void onNaviClick() {
                                                    Changefragment.dismiss();
                                                }
                                            });
                                            break;

                                        case 2:
                                            Snackbar.make(v, "少侠真的要放弃吗？", Snackbar.LENGTH_LONG)
                                                    .setAction("别废话！", new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            //修改Book_DB里面书籍的阅读情况
                                                            ContentValues values = new ContentValues();
                                                            values.put("readSituation", " ");
                                                            DataSupport.updateAll(Book_DB.class, values, "title = ?", readingBooks.get(position).getTitle());
                                                            DataSupport.deleteAll(Book_Read.class, "title = ? and readSituation = ?", readingBooks.get(position).getTitle(), "在读");
                                                            readingAdapter.clear();
                                                            readingBooks = null;

                                                            fetchReadingData();
                                                            readingAdapter.notifyDataSetChanged();
                                                        }
                                                    }).show();
                                            break;
                                        case 3:
                                            //修改Book_DB里面书籍的阅读情况
                                            ContentValues values = new ContentValues();
                                            values.put("readSituation", " ");
                                            DataSupport.updateAll(Book_DB.class, values, "title = ?", readingBooks.get(position).getTitle());
                                            DataSupport.deleteAll(Book_Read.class, "title = ? and readSituation = ?", readingBooks.get(position).getTitle(), "在读");
                                            readingAdapter.clear();
                                            readingBooks = null;
                                            fetchReadingData();
                                            readingAdapter.notifyDataSetChanged();

                                            break;
                                    }
                                }
                            }).show();
                }

                @Override
                public void OnLongClick(final int position, final View v) {

                }
            });
        }else {
            wanReadAdapter = new ReadPlaneAdapter(MyApplication.getApplicationContext2());
            fetchWantReadData();
            v.setupRecycleView(wanReadAdapter, manager);
        }

    }

    //查询正在读的书单的信息
    public void fetchReadingData(){
        readingBooks = DataSupport.where("readSituation = ?", "在读").order("id desc").find(Book_Read.class);
        readingAdapter.setReadingBooks(readingBooks);
    }

    public void fetchWantReadData(){
        wantReadBooks = DataSupport.where("readSituation = ?", "想读").order("id desc").find(Book_Read.class);
        wanReadAdapter.setWantReadBooks(wantReadBooks);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
