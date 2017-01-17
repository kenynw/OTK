package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Mission implements Parcelable {

    private int id;

    private String title;

    private String comment;

    private String score;

    /**
     * (int) - 状态是否完成任务（0：完成任务未领取撒币，1：完成任务已领取撒币 2：没做任务）
     */
    private int status;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.comment);
        dest.writeString(this.score);
        dest.writeInt(this.status);
    }

    public Mission() {
    }

    protected Mission(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.comment = in.readString();
        this.score = in.readString();
        this.status = in.readInt();
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

    public int getMission_id() {
        return id;
    }

    public void setMission_id(int mission_id) {
        this.id = mission_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
