package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Mission implements Parcelable {

    private String mission_name;

    private String mission_state;

    private String mission_image;

    private String mission_desc;

    private String mission_bonus;

    private String mission_status;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mission_name);
        dest.writeString(this.mission_state);
        dest.writeString(this.mission_image);
        dest.writeString(this.mission_desc);
        dest.writeString(this.mission_bonus);
        dest.writeString(this.mission_status);
    }

    public Mission() {
    }

    protected Mission(Parcel in) {
        this.mission_name = in.readString();
        this.mission_state = in.readString();
        this.mission_image = in.readString();
        this.mission_desc = in.readString();
        this.mission_bonus = in.readString();
        this.mission_status = in.readString();
    }

    public static final Creator<Mission> CREATOR = new Creator<Mission>() {
        @Override
        public Mission createFromParcel(Parcel source) {
            return new Mission(source);
        }

        @Override
        public Mission[] newArray(int size) {
            return new Mission[size];
        }
    };

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getMission_state() {
        return mission_state;
    }

    public void setMission_state(String mission_state) {
        this.mission_state = mission_state;
    }

    public String getMission_image() {
        return mission_image;
    }

    public void setMission_image(String mission_image) {
        this.mission_image = mission_image;
    }

    public String getMission_desc() {
        return mission_desc;
    }

    public void setMission_desc(String mission_desc) {
        this.mission_desc = mission_desc;
    }

    public String getMission_bonus() {
        return mission_bonus;
    }

    public void setMission_bonus(String mission_bonus) {
        this.mission_bonus = mission_bonus;
    }

    public String getMission_status() {
        return mission_status;
    }

    public void setMission_status(String mission_status) {
        this.mission_status = mission_status;
    }
}
