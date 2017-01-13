package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/13. LiaoPeiKun Inc. All rights reserved.
 */

public class Splash implements Parcelable {

    private String url;

    private String img;

    private String img_and;

    private String img_ios;

    private int show_time;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.img);
        dest.writeString(this.img_and);
        dest.writeString(this.img_ios);
        dest.writeInt(this.show_time);
    }

    public Splash() {
    }

    protected Splash(Parcel in) {
        this.url = in.readString();
        this.img = in.readString();
        this.img_and = in.readString();
        this.img_ios = in.readString();
        this.show_time = in.readInt();
    }

    public static final Creator<Splash> CREATOR = new Creator<Splash>() {
        @Override
        public Splash createFromParcel(Parcel source) {
            return new Splash(source);
        }

        @Override
        public Splash[] newArray(int size) {
            return new Splash[size];
        }
    };

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

    public String getImg_and() {
        return img_and;
    }

    public void setImg_and(String img_and) {
        this.img_and = img_and;
    }

    public String getImg_ios() {
        return img_ios;
    }

    public void setImg_ios(String img_ios) {
        this.img_ios = img_ios;
    }

    public int getShow_time() {
        return show_time;
    }

    public void setShow_time(int show_time) {
        this.show_time = show_time;
    }
}
