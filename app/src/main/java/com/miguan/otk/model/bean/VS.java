package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class VS implements Parcelable {

    private String player_a;

    private String player_b;

    private int result;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.player_a);
        dest.writeString(this.player_b);
        dest.writeInt(this.result);
    }

    public VS() {
    }

    protected VS(Parcel in) {
        this.player_a = in.readString();
        this.player_b = in.readString();
        this.result = in.readInt();
    }

    public static final Creator<VS> CREATOR = new Creator<VS>() {
        @Override
        public VS createFromParcel(Parcel source) {
            return new VS(source);
        }

        @Override
        public VS[] newArray(int size) {
            return new VS[size];
        }
    };

    public String getPlayer_a() {
        return player_a;
    }

    public void setPlayer_a(String player_a) {
        this.player_a = player_a;
    }

    public String getPlayer_b() {
        return player_b;
    }

    public void setPlayer_b(String player_b) {
        this.player_b = player_b;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
