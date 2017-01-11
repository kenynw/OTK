package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class News implements Parcelable {

    private int id;

    private String title;

    private String[] img;

    private String create_time;

    private String url;

    private int page_view;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeStringArray(this.img);
        dest.writeString(this.create_time);
        dest.writeString(this.url);
        dest.writeInt(this.page_view);
    }

    public News() {
    }

    protected News(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.img = in.createStringArray();
        this.create_time = in.readString();
        this.url = in.readString();
        this.page_view = in.readInt();
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPage_view() {
        return page_view;
    }

    public void setPage_view(int page_view) {
        this.page_view = page_view;
    }
}
