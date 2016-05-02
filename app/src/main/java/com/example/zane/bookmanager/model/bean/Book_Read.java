package com.example.zane.bookmanager.model.bean;

import com.example.zane.easymvp.base.IListModel;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Zane on 16/3/8.
 * 阅读计划存储表
 */
public class Book_Read extends DataSupport implements Serializable,IListModel{

    private int id;
    private int planeDays;//计划天数
    private long firstDay;//第一天制定阅读计划的日子
    private String pages;//总页数
    private String readPages;//已读页数
    private String title;
    private String image;
    private String author;
    //是否读完，0表示还在读，1表示还在读
    private int isReaded;
    //在读或者想读
    private String readSituation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(int isReaded) {
        this.isReaded = isReaded;
    }

    public String getReadSituation() {
        return readSituation;
    }

    public void setReadSituation(String readSituation) {
        this.readSituation = readSituation;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReadPages() {
        return readPages;
    }

    public void setReadPages(String readPages) {
        this.readPages = readPages;
    }

    public int getPlaneDays() {
        return planeDays;
    }

    public void setPlaneDays(int planeDays) {
        this.planeDays = planeDays;
    }

    public long getFirstDay() {
        return firstDay;
    }

    public void setFirstDay(long firstDay) {
        this.firstDay = firstDay;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int getModelViewType() {
        return 0;
    }
}
