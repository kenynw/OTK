package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Against implements Parcelable {

    private int id;

    private int competition_id;

    private String type;

    private String title;

    private User user_a;

    private User user_b;

    private String result;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.competition_id);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeParcelable(this.user_a, flags);
        dest.writeParcelable(this.user_b, flags);
        dest.writeString(this.result);
    }

    public Against() {
    }

    protected Against(Parcel in) {
        this.id = in.readInt();
        this.competition_id = in.readInt();
        this.type = in.readString();
        this.title = in.readString();
        this.user_a = in.readParcelable(User.class.getClassLoader());
        this.user_b = in.readParcelable(User.class.getClassLoader());
        this.result = in.readString();
    }

    public static final Creator<Against> CREATOR = new Creator<Against>() {
        @Override
        public Against createFromParcel(Parcel source) {
            return new Against(source);
        }

        @Override
        public Against[] newArray(int size) {
            return new Against[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser_a() {
        return user_a;
    }

    public void setUser_a(User user_a) {
        this.user_a = user_a;
    }

    public User getUser_b() {
        return user_b;
    }

    public void setUser_b(User user_b) {
        this.user_b = user_b;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
