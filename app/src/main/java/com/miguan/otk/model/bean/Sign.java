package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2017/1/11. LiaoPeiKun Inc. All rights reserved.
 */

public class Sign implements Parcelable {

    protected Sign(Parcel in) {
    }

    public static final Creator<Sign> CREATOR = new Creator<Sign>() {
        @Override
        public Sign createFromParcel(Parcel in) {
            return new Sign(in);
        }

        @Override
        public Sign[] newArray(int size) {
            return new Sign[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
