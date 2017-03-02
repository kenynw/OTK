package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class Sign implements Parcelable {

    private String[] cause;

    private String score;

    private String money;

    private String currency;

    private int istoday;

    private int isqiandao;

    private int baoxiangscore;

    private String visits;

    private String description;

    public String[] getCause() {
        return cause;
    }

    public void setCause(String[] cause) {
        this.cause = cause;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getIstoday() {
        return istoday;
    }

    public void setIstoday(int istoday) {
        this.istoday = istoday;
    }

    public int getIsqiandao() {
        return isqiandao;
    }

    public void setIsqiandao(int isqiandao) {
        this.isqiandao = isqiandao;
    }

    public int getBaoxiangscore() {
        return baoxiangscore;
    }

    public void setBaoxiangscore(int baoxiangscore) {
        this.baoxiangscore = baoxiangscore;
    }

    public String getVisits() {
        return visits;
    }

    public void setVisits(String visits) {
        this.visits = visits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.cause);
        dest.writeString(this.score);
        dest.writeString(this.money);
        dest.writeString(this.currency);
        dest.writeInt(this.istoday);
        dest.writeInt(this.isqiandao);
        dest.writeInt(this.baoxiangscore);
        dest.writeString(this.visits);
        dest.writeString(this.description);
    }

    public Sign() {
    }

    protected Sign(Parcel in) {
        this.cause = in.createStringArray();
        this.score = in.readString();
        this.money = in.readString();
        this.currency = in.readString();
        this.istoday = in.readInt();
        this.isqiandao = in.readInt();
        this.baoxiangscore = in.readInt();
        this.visits = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Sign> CREATOR = new Creator<Sign>() {
        @Override
        public Sign createFromParcel(Parcel source) {
            return new Sign(source);
        }

        @Override
        public Sign[] newArray(int size) {
            return new Sign[size];
        }
    };
}
