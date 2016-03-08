package com.example.zane.bookmanager.model.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by Zane on 16/3/8.
 * 阅读计划存储表
 */
public class Book_Read extends DataSupport implements Serializable{

    private int planeDays;//计划天数
    private int usedDays;//已经读过的天数
    private String pages;//总页数
    private String readPages;//已读页数
    private String title;
    private String image;

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

    public int getUsedDays() {
        return usedDays;
    }

    public void setUsedDays(int usedDays) {
        this.usedDays = usedDays;
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
}
