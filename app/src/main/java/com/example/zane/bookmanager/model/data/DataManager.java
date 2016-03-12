package com.example.zane.bookmanager.model.data;

import android.content.Context;
import android.text.TextUtils;

import com.example.zane.bookmanager.inject.qualifier.ContextType;
import com.example.zane.bookmanager.model.bean.Book;
import com.example.zane.bookmanager.model.bean.Book_DB;
import com.example.zane.bookmanager.model.bean.Book_Recom;
import com.example.zane.bookmanager.model.data.remote.DoubanBookService;
import com.example.zane.bookmanager.model.data.remote.DoubanRecommendBookService;

import org.litepal.crud.DataSupport;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Zane on 16/2/14.
 */
@Singleton
public class DataManager {

    private DoubanBookService doubanBookService;
    private DoubanRecommendBookService doubanRecommendBookService;
    private Context context;

    @Inject
    public DataManager(@ContextType("application")Context context, DoubanBookService doubanBookService, DoubanRecommendBookService doubanRecommendBookService){
        this.doubanBookService = doubanBookService;
        this.doubanRecommendBookService = doubanRecommendBookService;
        this.context = context;
    }


    //返回book的信息
    public Observable<Book> getBookInfo(String isbn){
        return doubanBookService.getBookInfo(isbn);
    }

    //返回推荐书籍信息
    public Observable<Book_Recom> getRecomBoonInfo(String author_tag, String fields){
        return doubanRecommendBookService.getRecomBookInfo(author_tag, fields);
    }

    //根据名字从数据库取出我的书单
    public List<Book_DB> fetchDataByName(List<Book_DB> myBooks){
        Map<String, Book_DB> maps = new HashMap<>();
        List<String> bookName = new ArrayList<>();
        List<Book_DB> books = new ArrayList<>();

        myBooks = DataSupport.findAll(Book_DB.class);

        for (Book_DB book : myBooks) {
            maps.put(book.getTitle(), book);
            bookName.add(book.getTitle());
        }

        Collections.sort(bookName, Collator.getInstance(Locale.CHINESE));

        for (int i = 0; i < myBooks.size(); i++){
            books.add(maps.get(bookName.get(i)));
        }

        return books;
    }

    //根据书名排序
    public List<Book_DB> checkBookByName(List<Book_DB> myBooks, final String book_name){
        final Map<String, Book_DB> maps = new HashMap<>();
        final List<Book_DB> books = new ArrayList<>();
        for (int i = 0; i < book_name.length(); i++) {
            TextUtils.substring(book_name, 0, i + 1);
            final int I = i;
            Observable.from(myBooks)
                    .map(new Func1<Book_DB, String>() {
                        @Override
                        public String call(Book_DB book_db) {
                            maps.put(book_db.getTitle(), book_db);
                            return book_db.getTitle();
                        }
                    })
                    .filter(new Func1<String, Boolean>() {
                        @Override
                        public Boolean call(String s) {
                            String n = "";
                            for (int j = 0; j < s.length() - I; j++) {
                                n = TextUtils.substring(s, j, I + j + 1);
                                if (n.equals(book_name)) {
                                    break;
                                }
                            }
                            return n.equals(book_name);
                        }
                    })
                    .map(new Func1<String, Book_DB>() {
                        @Override
                        public Book_DB call(String s) {
                            return maps.get(s);
                        }
                    })
                    .subscribe(new Action1<Book_DB>() {
                        @Override
                        public void call(Book_DB book_db) {
                            books.add(book_db);
                        }
                    });
        }

        return books;
    }

    //根据作者名字来获取数据库书籍信息
    public List<Book_DB> checkBookByAuthor(List<Book_DB> myBooks, final String book_name){
        final Map<String, Book_DB> maps = new HashMap<>();
        final List<Book_DB> books = new ArrayList<>();
        for (int i = 0; i < book_name.length(); i++) {
            TextUtils.substring(book_name, 0, i + 1);
            final int I = i;
            Observable.from(myBooks)
                    .map(new Func1<Book_DB, String>() {
                        @Override
                        public String call(Book_DB book_db) {
                            maps.put(book_db.getAuthor(), book_db);
                            return book_db.getAuthor();
                        }
                    })
                    .filter(new Func1<String, Boolean>() {
                        @Override
                        public Boolean call(String s) {
                            String n = "";
                            for (int j = 0; j < s.length() - I; j++) {
                                n = TextUtils.substring(s, j, I + j + 1);
                                if (n.equals(book_name)) {
                                    break;
                                }
                            }
                            return n.equals(book_name);
                        }
                    })
                    .map(new Func1<String, Book_DB>() {
                        @Override
                        public Book_DB call(String s) {
                            return maps.get(s);
                        }
                    })
                    .subscribe(new Action1<Book_DB>() {
                        @Override
                        public void call(Book_DB book_db) {
                            books.add(book_db);
                        }
                    });

        }

        return books;
    }

    public List<Book_DB> checkBookByAll(List<Book_DB> myBooks, final String book_name){
        List<Book_DB> books = new ArrayList<>();
        for (int i = 0; i < book_name.length(); i++) {
            TextUtils.substring(book_name, 0, i + 1);
            books.addAll(checkBookByAuthor(myBooks, book_name));
            books.addAll(checkBookByName(myBooks, book_name));
        }
        return books;
    }
}
