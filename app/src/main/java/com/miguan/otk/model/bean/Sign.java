package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class Sign implements Parcelable {

    private List<Cause> cause;

    private String score;

    private String money;

    private String currency;

    private int istoday;

    private int isqiandao;

    private int baoxiangscore;

    private String visits;

    private String description;

    public static class Cause implements Parcelable {

        private String sign_in_date;

        private int sign_in_count;

        private String sign;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sign_in_date);
            dest.writeInt(this.sign_in_count);
            dest.writeString(this.sign);
        }

        public Cause() {
        }

        Cause(Parcel in) {
            this.sign_in_date = in.readString();
            this.sign_in_count = in.readInt();
            this.sign = in.readString();
        }

        public static final Creator<Cause> CREATOR = new Creator<Cause>() {
            @Override
            public Cause createFromParcel(Parcel source) {
                return new Cause(source);
            }

            @Override
            public Cause[] newArray(int size) {
                return new Cause[size];
            }
        };

        public String getSign_in_date() {
            return sign_in_date;
        }

        public void setSign_in_date(String sign_in_date) {
            this.sign_in_date = sign_in_date;
        }

        public int getSign_in_count() {
            return sign_in_count;
        }

        public void setSign_in_count(int sign_in_count) {
            this.sign_in_count = sign_in_count;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.cause);
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
        this.cause = in.createTypedArrayList(Cause.CREATOR);
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

    public List<Cause> getCause() {
        return cause;
    }

    public void setCause(List<Cause> cause) {
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
}
