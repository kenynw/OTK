package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/16. LiaoPeiKun Inc. All rights reserved.
 */

public class CashRecord implements Parcelable {

    private String amount;

    private String status;

    private String fee;

    private String date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.amount);
        dest.writeString(this.status);
        dest.writeString(this.fee);
        dest.writeString(this.date);
    }

    public CashRecord() {
    }

    protected CashRecord(Parcel in) {
        this.amount = in.readString();
        this.status = in.readString();
        this.fee = in.readString();
        this.date = in.readString();
    }

    public static final Creator<CashRecord> CREATOR = new Creator<CashRecord>() {
        @Override
        public CashRecord createFromParcel(Parcel source) {
            return new CashRecord(source);
        }

        @Override
        public CashRecord[] newArray(int size) {
            return new CashRecord[size];
        }
    };

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
