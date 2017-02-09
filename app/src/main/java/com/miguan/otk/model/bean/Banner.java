package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/12. LiaoPeiKun Inc. All rights reserved.
 */

public class Banner implements Parcelable {

    private int id;

    private String title;

    /**
     * 1:文章，2：比赛，3：外链
     */
    private int type;

    private String url;

    private String img;

    private String sort_id;

    private String status;

    private String create_time;

    private String update_time;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeString(this.url);
        dest.writeString(this.img);
        dest.writeString(this.sort_id);
        dest.writeString(this.status);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
    }

    public Banner() {
    }

    protected Banner(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.type = in.readInt();
        this.url = in.readString();
        this.img = in.readString();
        this.sort_id = in.readString();
        this.status = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
    }

    public static final Creator<Banner> CREATOR = new Creator<Banner>() {
        @Override
        public Banner createFromParcel(Parcel source) {
            return new Banner(source);
        }

        @Override
        public Banner[] newArray(int size) {
            return new Banner[size];
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
