package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */

public class User implements Parcelable {

    private String token;

    private String username;

    private int uid;

    private int money;

    private String photo;

    private int currency;

    private String qq;

    private String email;

    private String actuality;

    private String birthday;

    private String sign;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.username);
        dest.writeInt(this.uid);
        dest.writeInt(this.money);
        dest.writeString(this.photo);
        dest.writeInt(this.currency);
        dest.writeString(this.qq);
        dest.writeString(this.email);
        dest.writeString(this.actuality);
        dest.writeString(this.birthday);
        dest.writeString(this.sign);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.token = in.readString();
        this.username = in.readString();
        this.uid = in.readInt();
        this.money = in.readInt();
        this.photo = in.readString();
        this.currency = in.readInt();
        this.qq = in.readString();
        this.email = in.readString();
        this.actuality = in.readString();
        this.birthday = in.readString();
        this.sign = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActuality() {
        return actuality;
    }

    public void setActuality(String actuality) {
        this.actuality = actuality;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
