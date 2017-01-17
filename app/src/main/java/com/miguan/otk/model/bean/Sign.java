package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class Sign implements Parcelable {

    private Cause cause;

    private String score;

    private String money;

    private String istoday;

    private String isqiandao;

    private String visits;

    private String description;

    private static class Cause implements Parcelable {

        private String sign_in_date;

        private String sign_in_count;

        private String sign;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.sign_in_date);
            dest.writeString(this.sign_in_count);
            dest.writeString(this.sign);
        }

        public Cause() {
        }

        protected Cause(Parcel in) {
            this.sign_in_date = in.readString();
            this.sign_in_count = in.readString();
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

        public String getSign_in_count() {
            return sign_in_count;
        }

        public void setSign_in_count(String sign_in_count) {
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
        dest.writeParcelable(this.cause, flags);
        dest.writeString(this.score);
        dest.writeString(this.money);
        dest.writeString(this.istoday);
        dest.writeString(this.isqiandao);
        dest.writeString(this.visits);
        dest.writeString(this.description);
    }

    public Sign() {
    }

    protected Sign(Parcel in) {
        this.cause = in.readParcelable(Cause.class.getClassLoader());
        this.score = in.readString();
        this.money = in.readString();
        this.istoday = in.readString();
        this.isqiandao = in.readString();
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

    public Cause getCause() {
        return cause;
    }

    public void setCause(Cause cause) {
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

    public String getIstoday() {
        return istoday;
    }

    public void setIstoday(String istoday) {
        this.istoday = istoday;
    }

    public String getIsqiandao() {
        return isqiandao;
    }

    public void setIsqiandao(String isqiandao) {
        this.isqiandao = isqiandao;
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
