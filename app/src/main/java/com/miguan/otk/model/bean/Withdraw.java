package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class Withdraw implements Parcelable {

    private int money;

    private String alipay_account;

    private List<Withdraw> records;

    private String time;

    private String status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.money);
        dest.writeString(this.alipay_account);
        dest.writeTypedList(this.records);
        dest.writeString(this.time);
        dest.writeString(this.status);
    }

    public Withdraw() {
    }

    protected Withdraw(Parcel in) {
        this.money = in.readInt();
        this.alipay_account = in.readString();
        this.records = in.createTypedArrayList(Withdraw.CREATOR);
        this.time = in.readString();
        this.status = in.readString();
    }

    public static final Creator<Withdraw> CREATOR = new Creator<Withdraw>() {
        @Override
        public Withdraw createFromParcel(Parcel source) {
            return new Withdraw(source);
        }

        @Override
        public Withdraw[] newArray(int size) {
            return new Withdraw[size];
        }
    };

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public List<Withdraw> getRecords() {
        return records;
    }

    public void setRecords(List<Withdraw> records) {
        this.records = records;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
