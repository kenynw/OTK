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

    private String a_user_id;

    private String a_status;

    private String a_name;

    private String a_img;

    private String a_score;

    private String b_user_id;

    private String b_status;

    private String b_name;

    private String b_img;

    private String b_score;

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
        dest.writeString(this.a_user_id);
        dest.writeString(this.a_status);
        dest.writeString(this.a_name);
        dest.writeString(this.a_img);
        dest.writeString(this.a_score);
        dest.writeString(this.b_user_id);
        dest.writeString(this.b_status);
        dest.writeString(this.b_name);
        dest.writeString(this.b_img);
        dest.writeString(this.b_score);
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
        this.a_user_id = in.readString();
        this.a_status = in.readString();
        this.a_name = in.readString();
        this.a_img = in.readString();
        this.a_score = in.readString();
        this.b_user_id = in.readString();
        this.b_status = in.readString();
        this.b_name = in.readString();
        this.b_img = in.readString();
        this.b_score = in.readString();
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

    public String getA_user_id() {
        return a_user_id;
    }

    public void setA_user_id(String a_user_id) {
        this.a_user_id = a_user_id;
    }

    public String getA_status() {
        return a_status;
    }

    public void setA_status(String a_status) {
        this.a_status = a_status;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public String getA_img() {
        return a_img;
    }

    public void setA_img(String a_img) {
        this.a_img = a_img;
    }

    public String getA_score() {
        return a_score;
    }

    public void setA_score(String a_score) {
        this.a_score = a_score;
    }

    public String getB_user_id() {
        return b_user_id;
    }

    public void setB_user_id(String b_user_id) {
        this.b_user_id = b_user_id;
    }

    public String getB_status() {
        return b_status;
    }

    public void setB_status(String b_status) {
        this.b_status = b_status;
    }

    public String getB_name() {
        return b_name;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }

    public String getB_score() {
        return b_score;
    }

    public void setB_score(String b_score) {
        this.b_score = b_score;
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
