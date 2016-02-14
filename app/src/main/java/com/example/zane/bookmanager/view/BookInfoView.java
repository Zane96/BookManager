package com.example.zane.bookmanager.view;

import android.widget.TextView;
import android.widget.Toast;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.easymvp.view.BaseViewImpl;

import butterknife.Bind;

/**
 * Created by Zane on 16/2/14.
 */
public class BookInfoView extends BaseViewImpl {
    @Bind(R.id.text_bookinfo)
    TextView textBookinfo;

    @Override
    public int getRootViewId() {
        return R.layout.activity_bookinfo_layout;
    }

    public void test(Book book) {
        textBookinfo.setText(book.getAuthor_intro());
    }
}
