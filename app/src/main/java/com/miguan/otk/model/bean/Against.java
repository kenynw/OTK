package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Against implements Parcelable {

    private int id;

    private int competition_id;

    private int auser_id;

    private int buser_id;

    private int winner_id;

    private String type;

    private String title;

    private String ausername;

    private String busername;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.competition_id);
        dest.writeInt(this.auser_id);
        dest.writeInt(this.buser_id);
        dest.writeInt(this.winner_id);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.ausername);
        dest.writeString(this.busername);
    }

    public Against() {
    }

    protected Against(Parcel in) {
        this.id = in.readInt();
        this.competition_id = in.readInt();
        this.auser_id = in.readInt();
        this.buser_id = in.readInt();
        this.winner_id = in.readInt();
        this.type = in.readString();
        this.title = in.readString();
        this.ausername = in.readString();
        this.busername = in.readString();
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

    public int getAuser_id() {
        return auser_id;
    }

    public void setAuser_id(int auser_id) {
        this.auser_id = auser_id;
    }

    public int getBuser_id() {
        return buser_id;
    }

    public void setBuser_id(int buser_id) {
        this.buser_id = buser_id;
    }

    public int getWinner_id() {
        return winner_id;
    }

    public void setWinner_id(int winner_id) {
        this.winner_id = winner_id;
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

    public String getAusername() {
        return ausername;
    }

    public void setAusername(String ausername) {
        this.ausername = ausername;
    }

    public String getBusername() {
        return busername;
    }

    public void setBusername(String busername) {
        this.busername = busername;
    }
}