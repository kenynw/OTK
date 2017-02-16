package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/2/14. LiaoPeiKun Inc. All rights reserved.
 */

public class Screenshot implements Parcelable {

    private String cpic1;

    private String cpic2;

    private String pic1;

    private String pic2;

    private String pic3;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.cpic1);
        dest.writeString(this.cpic2);
        dest.writeString(this.pic1);
        dest.writeString(this.pic2);
        dest.writeString(this.pic3);
    }

    public Screenshot() {
    }

    protected Screenshot(Parcel in) {
        this.cpic1 = in.readString();
        this.cpic2 = in.readString();
        this.pic1 = in.readString();
        this.pic2 = in.readString();
        this.pic3 = in.readString();
    }

    public static final Creator<Screenshot> CREATOR = new Creator<Screenshot>() {
        @Override
        public Screenshot createFromParcel(Parcel source) {
            return new Screenshot(source);
        }

        @Override
        public Screenshot[] newArray(int size) {
            return new Screenshot[size];
        }
    };

    public String getCpic1() {
        return cpic1;
    }

    public void setCpic1(String cpic1) {
        this.cpic1 = cpic1;
    }

    public String getCpic2() {
        return cpic2;
    }

    public void setCpic2(String cpic2) {
        this.cpic2 = cpic2;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }
}
