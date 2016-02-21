package com.example.zane.bookmanager.view;

import android.media.Image;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zane.bookmanager.R;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.easymvp.view.BaseListViewHolderImpl;

/**
 * Created by Zane on 16/2/19.
 */
public class MyBookInfoTopViewHolder extends BaseListViewHolderImpl<Book_DB>{

    public EditText editText;
    public TextView textView_name;
    public TextView textView_date;
    public ImageButton checkChoose;
    public ImageButton checkDelet;

    public MyBookInfoTopViewHolder(ViewGroup parent, int res) {
        super(parent, res);
    }

    @Override
    public void initView() {
        editText = $(R.id.edit_checkbook);
        textView_date = $(R.id.sortbook_date);
        textView_name = $(R.id.sortbook_name);
        checkChoose = $(R.id.checkchoose);
        checkDelet = $(R.id.checkdelet);
    }

    @Override
    public void setData(Book_DB book_db) {
    }
    public TextView getTextView_name(){
        return textView_name;
    }
    public TextView getTextView_date(){
        return textView_date;
    }
    public EditText getEditText_checkBook(){
        return editText;
    }
    public ImageButton getCheckChoose(){return checkChoose;}
    public ImageButton getCheckDelet(){
        return checkDelet;
    }

}
