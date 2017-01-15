package com.miguan.otk.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2016/12/23. LiaoPeiKun Inc. All rights reserved.
 */

public class Schedule implements Parcelable {

    private int id;

    private int competition_id;

    private String type;

    private String title;

    private User user_a;

    private User user_b;

    private String result;

    private int round_count;

    private int round;

    private String message;

    private List<Schedule> battles;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.competition_id);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeParcelable(this.user_a, flags);
        dest.writeParcelable(this.user_b, flags);
        dest.writeString(this.result);
        dest.writeInt(this.round_count);
        dest.writeInt(this.round);
        dest.writeString(this.message);
        dest.writeTypedList(this.battles);
    }

    public Schedule() {
    }

    protected Schedule(Parcel in) {
        this.id = in.readInt();
        this.competition_id = in.readInt();
        this.type = in.readString();
        this.title = in.readString();
        this.user_a = in.readParcelable(User.class.getClassLoader());
        this.user_b = in.readParcelable(User.class.getClassLoader());
        this.result = in.readString();
        this.round_count = in.readInt();
        this.round = in.readInt();
        this.message = in.readString();
        this.battles = in.createTypedArrayList(Schedule.CREATOR);
    }

    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
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

    public User getUser_a() {
        return user_a;
    }

    public void setUser_a(User user_a) {
        this.user_a = user_a;
    }

    public User getUser_b() {
        return user_b;
    }

    public void setUser_b(User user_b) {
        this.user_b = user_b;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRound_count() {
        return round_count;
    }

    public void setRound_count(int round_count) {
        this.round_count = round_count;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Schedule> getBattles() {
        return battles;
    }

    public void setBattles(List<Schedule> battles) {
        this.battles = battles;
    }
}
